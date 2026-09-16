package org.webrtc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.WifiInfo;
import android.net.wifi.p2p.WifiP2pGroup;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.webrtc.NetworkChangeDetector;
import org.webrtc.NetworkMonitorAutoDetect;
/* loaded from: classes5.dex */
public class NetworkMonitorAutoDetect extends BroadcastReceiver implements NetworkChangeDetector {
    private static final long INVALID_NET_ID = -1;
    private static final String TAG = "NetworkMonitorAutoDetect";
    private static boolean includeWifiDirect;
    private final ConnectivityManager.NetworkCallback allNetworkCallback;
    final Set<Network> availableNetworks = new HashSet();
    private NetworkChangeDetector.ConnectionType connectionType;
    private ConnectivityManagerDelegate connectivityManagerDelegate;
    private final Context context;
    private final IntentFilter intentFilter;
    private boolean isRegistered;
    private final ConnectivityManager.NetworkCallback mobileNetworkCallback;
    private final NetworkChangeDetector.Observer observer;
    private WifiDirectManagerDelegate wifiDirectManagerDelegate;
    private WifiManagerDelegate wifiManagerDelegate;
    private String wifiSSID;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class NetworkState {
        private final boolean connected;
        private final int subtype;
        private final int type;
        private final int underlyingNetworkSubtypeForVpn;
        private final int underlyingNetworkTypeForVpn;

        public NetworkState(boolean connected, int type, int subtype, int underlyingNetworkTypeForVpn, int underlyingNetworkSubtypeForVpn) {
            this.connected = connected;
            this.type = type;
            this.subtype = subtype;
            this.underlyingNetworkTypeForVpn = underlyingNetworkTypeForVpn;
            this.underlyingNetworkSubtypeForVpn = underlyingNetworkSubtypeForVpn;
        }

        public boolean isConnected() {
            return this.connected;
        }

        public int getNetworkType() {
            return this.type;
        }

        public int getNetworkSubType() {
            return this.subtype;
        }

        public int getUnderlyingNetworkTypeForVpn() {
            return this.underlyingNetworkTypeForVpn;
        }

        public int getUnderlyingNetworkSubtypeForVpn() {
            return this.underlyingNetworkSubtypeForVpn;
        }
    }

    /* loaded from: classes5.dex */
    class SimpleNetworkCallback extends ConnectivityManager.NetworkCallback {
        final Set<Network> availableNetworks;

        SimpleNetworkCallback(Set<Network> availableNetworks) {
            this.availableNetworks = availableNetworks;
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            Logging.d(NetworkMonitorAutoDetect.TAG, "Network handle: " + NetworkMonitorAutoDetect.networkToNetId(network) + " becomes available: " + network.toString());
            synchronized (this.availableNetworks) {
                this.availableNetworks.add(network);
            }
            onNetworkChanged(network);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            Logging.d(NetworkMonitorAutoDetect.TAG, "handle: " + NetworkMonitorAutoDetect.networkToNetId(network) + " capabilities changed: " + networkCapabilities.toString());
            onNetworkChanged(network);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
            Logging.d(NetworkMonitorAutoDetect.TAG, "handle: " + NetworkMonitorAutoDetect.networkToNetId(network) + " link properties changed");
            onNetworkChanged(network);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLosing(Network network, int maxMsToLive) {
            Logging.d(NetworkMonitorAutoDetect.TAG, "Network handle: " + NetworkMonitorAutoDetect.networkToNetId(network) + ", " + network.toString() + " is about to lose in " + maxMsToLive + "ms");
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            Logging.d(NetworkMonitorAutoDetect.TAG, "Network handle: " + NetworkMonitorAutoDetect.networkToNetId(network) + ", " + network.toString() + " is disconnected");
            synchronized (this.availableNetworks) {
                this.availableNetworks.remove(network);
            }
            NetworkMonitorAutoDetect.this.observer.onNetworkDisconnect(NetworkMonitorAutoDetect.networkToNetId(network));
        }

        private void onNetworkChanged(Network network) {
            NetworkChangeDetector.NetworkInformation networkInformation = NetworkMonitorAutoDetect.this.connectivityManagerDelegate.networkToInfo(network);
            if (networkInformation != null) {
                NetworkMonitorAutoDetect.this.observer.onNetworkConnect(networkInformation);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class ConnectivityManagerDelegate {
        private final Set<Network> availableNetworks;
        private final ConnectivityManager connectivityManager;
        private final boolean getAllNetworksFromCache;
        private final boolean includeOtherUidNetworks;
        private final boolean requestVPN;

        ConnectivityManagerDelegate(Context context, Set<Network> availableNetworks, String fieldTrialsString) {
            this((ConnectivityManager) context.getSystemService("connectivity"), availableNetworks, fieldTrialsString);
        }

        ConnectivityManagerDelegate(ConnectivityManager connectivityManager, Set<Network> availableNetworks, String fieldTrialsString) {
            this.connectivityManager = connectivityManager;
            this.availableNetworks = availableNetworks;
            this.getAllNetworksFromCache = checkFieldTrial(fieldTrialsString, "getAllNetworksFromCache", false);
            this.requestVPN = checkFieldTrial(fieldTrialsString, "requestVPN", false);
            this.includeOtherUidNetworks = checkFieldTrial(fieldTrialsString, "includeOtherUidNetworks", false);
        }

        private static boolean checkFieldTrial(String fieldTrialsString, String key, boolean defaultValue) {
            if (fieldTrialsString.contains(key + ":true")) {
                return true;
            }
            if (fieldTrialsString.contains(key + ":false")) {
                return false;
            }
            return defaultValue;
        }

        NetworkState getNetworkState() {
            if (this.connectivityManager == null) {
                return new NetworkState(false, -1, -1, -1, -1);
            }
            return getNetworkState(this.connectivityManager.getActiveNetworkInfo());
        }

        NetworkState getNetworkState(Network network) {
            NetworkInfo underlyingActiveNetworkInfo;
            if (network == null || this.connectivityManager == null) {
                return new NetworkState(false, -1, -1, -1, -1);
            }
            NetworkInfo networkInfo = this.connectivityManager.getNetworkInfo(network);
            if (networkInfo == null) {
                Logging.w(NetworkMonitorAutoDetect.TAG, "Couldn't retrieve information from network " + network.toString());
                return new NetworkState(false, -1, -1, -1, -1);
            } else if (networkInfo.getType() != 17) {
                NetworkCapabilities networkCapabilities = this.connectivityManager.getNetworkCapabilities(network);
                if (networkCapabilities == null || !networkCapabilities.hasTransport(4)) {
                    return getNetworkState(networkInfo);
                }
                return new NetworkState(networkInfo.isConnected(), 17, -1, networkInfo.getType(), networkInfo.getSubtype());
            } else if (networkInfo.getType() == 17) {
                if (network.equals(this.connectivityManager.getActiveNetwork()) && (underlyingActiveNetworkInfo = this.connectivityManager.getActiveNetworkInfo()) != null && underlyingActiveNetworkInfo.getType() != 17) {
                    return new NetworkState(networkInfo.isConnected(), 17, -1, underlyingActiveNetworkInfo.getType(), underlyingActiveNetworkInfo.getSubtype());
                }
                return new NetworkState(networkInfo.isConnected(), 17, -1, -1, -1);
            } else {
                return getNetworkState(networkInfo);
            }
        }

        private NetworkState getNetworkState(NetworkInfo networkInfo) {
            if (networkInfo == null || !networkInfo.isConnected()) {
                return new NetworkState(false, -1, -1, -1, -1);
            }
            return new NetworkState(true, networkInfo.getType(), networkInfo.getSubtype(), -1, -1);
        }

        Network[] getAllNetworks() {
            Network[] networkArr;
            if (this.connectivityManager == null) {
                return new Network[0];
            }
            if (supportNetworkCallback() && this.getAllNetworksFromCache) {
                synchronized (this.availableNetworks) {
                    networkArr = (Network[]) this.availableNetworks.toArray(new Network[0]);
                }
                return networkArr;
            }
            return this.connectivityManager.getAllNetworks();
        }

        List<NetworkChangeDetector.NetworkInformation> getActiveNetworkList() {
            Network[] allNetworks;
            if (!supportNetworkCallback()) {
                return null;
            }
            ArrayList<NetworkChangeDetector.NetworkInformation> netInfoList = new ArrayList<>();
            for (Network network : getAllNetworks()) {
                NetworkChangeDetector.NetworkInformation info = networkToInfo(network);
                if (info != null) {
                    netInfoList.add(info);
                }
            }
            return netInfoList;
        }

        long getDefaultNetId() {
            NetworkInfo defaultNetworkInfo;
            NetworkInfo networkInfo;
            if (supportNetworkCallback() && (defaultNetworkInfo = this.connectivityManager.getActiveNetworkInfo()) != null) {
                Network[] networks = getAllNetworks();
                long defaultNetId = -1;
                for (Network network : networks) {
                    if (hasInternetCapability(network) && (networkInfo = this.connectivityManager.getNetworkInfo(network)) != null && networkInfo.getType() == defaultNetworkInfo.getType()) {
                        if (defaultNetId != -1) {
                            throw new RuntimeException("Multiple connected networks of same type are not supported.");
                        }
                        defaultNetId = NetworkMonitorAutoDetect.networkToNetId(network);
                    }
                }
                return defaultNetId;
            }
            return -1L;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public NetworkChangeDetector.NetworkInformation networkToInfo(Network network) {
            if (network == null || this.connectivityManager == null) {
                return null;
            }
            LinkProperties linkProperties = this.connectivityManager.getLinkProperties(network);
            if (linkProperties == null) {
                Logging.w(NetworkMonitorAutoDetect.TAG, "Detected unknown network: " + network.toString());
                return null;
            } else if (linkProperties.getInterfaceName() == null) {
                Logging.w(NetworkMonitorAutoDetect.TAG, "Null interface name for network " + network.toString());
                return null;
            } else {
                NetworkState networkState = getNetworkState(network);
                NetworkChangeDetector.ConnectionType connectionType = NetworkMonitorAutoDetect.getConnectionType(networkState);
                if (connectionType == NetworkChangeDetector.ConnectionType.CONNECTION_NONE) {
                    Logging.d(NetworkMonitorAutoDetect.TAG, "Network " + network.toString() + " is disconnected");
                    return null;
                }
                if (connectionType == NetworkChangeDetector.ConnectionType.CONNECTION_UNKNOWN || connectionType == NetworkChangeDetector.ConnectionType.CONNECTION_UNKNOWN_CELLULAR) {
                    Logging.d(NetworkMonitorAutoDetect.TAG, "Network " + network.toString() + " connection type is " + connectionType + " because it has type " + networkState.getNetworkType() + " and subtype " + networkState.getNetworkSubType());
                }
                NetworkChangeDetector.ConnectionType underlyingConnectionTypeForVpn = NetworkMonitorAutoDetect.getUnderlyingConnectionTypeForVpn(networkState);
                NetworkChangeDetector.NetworkInformation networkInformation = new NetworkChangeDetector.NetworkInformation(linkProperties.getInterfaceName(), connectionType, underlyingConnectionTypeForVpn, NetworkMonitorAutoDetect.networkToNetId(network), getIPAddresses(linkProperties));
                return networkInformation;
            }
        }

        boolean hasInternetCapability(Network network) {
            NetworkCapabilities capabilities;
            return (this.connectivityManager == null || (capabilities = this.connectivityManager.getNetworkCapabilities(network)) == null || !capabilities.hasCapability(12)) ? false : true;
        }

        NetworkRequest createNetworkRequest() {
            NetworkRequest.Builder builder = new NetworkRequest.Builder().addCapability(12);
            if (this.requestVPN) {
                builder.removeCapability(15);
            }
            if (Build.VERSION.SDK_INT >= 31 && this.includeOtherUidNetworks) {
                builder.setIncludeOtherUidNetworks(true);
            }
            return builder.build();
        }

        public void registerNetworkCallback(ConnectivityManager.NetworkCallback networkCallback) {
            this.connectivityManager.registerNetworkCallback(createNetworkRequest(), networkCallback);
        }

        public void requestMobileNetwork(ConnectivityManager.NetworkCallback networkCallback) {
            NetworkRequest.Builder builder = new NetworkRequest.Builder();
            builder.addCapability(12).addTransportType(0);
            this.connectivityManager.requestNetwork(builder.build(), networkCallback);
        }

        NetworkChangeDetector.IPAddress[] getIPAddresses(LinkProperties linkProperties) {
            NetworkChangeDetector.IPAddress[] ipAddresses = new NetworkChangeDetector.IPAddress[linkProperties.getLinkAddresses().size()];
            int i = 0;
            for (LinkAddress linkAddress : linkProperties.getLinkAddresses()) {
                ipAddresses[i] = new NetworkChangeDetector.IPAddress(linkAddress.getAddress().getAddress());
                i++;
            }
            return ipAddresses;
        }

        public void releaseCallback(ConnectivityManager.NetworkCallback networkCallback) {
            if (supportNetworkCallback()) {
                Logging.d(NetworkMonitorAutoDetect.TAG, "Unregister network callback");
                this.connectivityManager.unregisterNetworkCallback(networkCallback);
            }
        }

        public boolean supportNetworkCallback() {
            return this.connectivityManager != null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class WifiManagerDelegate {
        private final Context context;

        WifiManagerDelegate(Context context) {
            this.context = context;
        }

        WifiManagerDelegate() {
            this.context = null;
        }

        String getWifiSSID() {
            WifiInfo wifiInfo;
            String ssid;
            Intent intent = this.context.registerReceiver(null, new IntentFilter("android.net.wifi.STATE_CHANGE"));
            if (intent != null && (wifiInfo = (WifiInfo) intent.getParcelableExtra("wifiInfo")) != null && (ssid = wifiInfo.getSSID()) != null) {
                return ssid;
            }
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class WifiDirectManagerDelegate extends BroadcastReceiver {
        private static final int WIFI_P2P_NETWORK_HANDLE = 0;
        private final Context context;
        private final NetworkChangeDetector.Observer observer;
        private NetworkChangeDetector.NetworkInformation wifiP2pNetworkInfo;

        WifiDirectManagerDelegate(NetworkChangeDetector.Observer observer, Context context) {
            this.context = context;
            this.observer = observer;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.p2p.STATE_CHANGED");
            intentFilter.addAction("android.net.wifi.p2p.CONNECTION_STATE_CHANGE");
            context.registerReceiver(this, intentFilter);
            if (Build.VERSION.SDK_INT > 28) {
                WifiP2pManager manager = (WifiP2pManager) context.getSystemService("wifip2p");
                WifiP2pManager.Channel channel = manager.initialize(context, context.getMainLooper(), null);
                manager.requestGroupInfo(channel, new WifiP2pManager.GroupInfoListener() { // from class: org.webrtc.NetworkMonitorAutoDetect$WifiDirectManagerDelegate$$ExternalSyntheticLambda0
                    @Override // android.net.wifi.p2p.WifiP2pManager.GroupInfoListener
                    public final void onGroupInfoAvailable(WifiP2pGroup wifiP2pGroup) {
                        NetworkMonitorAutoDetect.WifiDirectManagerDelegate.this.m2127x61cecc50(wifiP2pGroup);
                    }
                });
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.net.wifi.p2p.CONNECTION_STATE_CHANGE".equals(intent.getAction())) {
                WifiP2pGroup wifiP2pGroup = (WifiP2pGroup) intent.getParcelableExtra("p2pGroupInfo");
                m2127x61cecc50(wifiP2pGroup);
            } else if ("android.net.wifi.p2p.STATE_CHANGED".equals(intent.getAction())) {
                int state = intent.getIntExtra("wifi_p2p_state", 0);
                onWifiP2pStateChange(state);
            }
        }

        public void release() {
            this.context.unregisterReceiver(this);
        }

        public List<NetworkChangeDetector.NetworkInformation> getActiveNetworkList() {
            if (this.wifiP2pNetworkInfo != null) {
                return Collections.singletonList(this.wifiP2pNetworkInfo);
            }
            return Collections.emptyList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onWifiP2pGroupChange */
        public void m2127x61cecc50(WifiP2pGroup wifiP2pGroup) {
            if (wifiP2pGroup == null || wifiP2pGroup.getInterface() == null) {
                return;
            }
            try {
                NetworkInterface wifiP2pInterface = NetworkInterface.getByName(wifiP2pGroup.getInterface());
                List<InetAddress> interfaceAddresses = Collections.list(wifiP2pInterface.getInetAddresses());
                NetworkChangeDetector.IPAddress[] ipAddresses = new NetworkChangeDetector.IPAddress[interfaceAddresses.size()];
                for (int i = 0; i < interfaceAddresses.size(); i++) {
                    ipAddresses[i] = new NetworkChangeDetector.IPAddress(interfaceAddresses.get(i).getAddress());
                }
                this.wifiP2pNetworkInfo = new NetworkChangeDetector.NetworkInformation(wifiP2pGroup.getInterface(), NetworkChangeDetector.ConnectionType.CONNECTION_WIFI, NetworkChangeDetector.ConnectionType.CONNECTION_NONE, 0L, ipAddresses);
                this.observer.onNetworkConnect(this.wifiP2pNetworkInfo);
            } catch (SocketException e) {
                Logging.e(NetworkMonitorAutoDetect.TAG, "Unable to get WifiP2p network interface", e);
            }
        }

        private void onWifiP2pStateChange(int state) {
            if (state == 1) {
                this.wifiP2pNetworkInfo = null;
                this.observer.onNetworkDisconnect(0L);
            }
        }
    }

    public NetworkMonitorAutoDetect(NetworkChangeDetector.Observer observer, Context context) {
        this.observer = observer;
        this.context = context;
        String fieldTrialsString = observer.getFieldTrialsString();
        this.connectivityManagerDelegate = new ConnectivityManagerDelegate(context, this.availableNetworks, fieldTrialsString);
        this.wifiManagerDelegate = new WifiManagerDelegate(context);
        NetworkState networkState = this.connectivityManagerDelegate.getNetworkState();
        this.connectionType = getConnectionType(networkState);
        this.wifiSSID = getWifiSSID(networkState);
        this.intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        if (includeWifiDirect) {
            this.wifiDirectManagerDelegate = new WifiDirectManagerDelegate(observer, context);
        }
        registerReceiver();
        if (this.connectivityManagerDelegate.supportNetworkCallback()) {
            ConnectivityManager.NetworkCallback tempNetworkCallback = new ConnectivityManager.NetworkCallback();
            try {
                this.connectivityManagerDelegate.requestMobileNetwork(tempNetworkCallback);
            } catch (SecurityException e) {
                Logging.w(TAG, "Unable to obtain permission to request a cellular network.");
                tempNetworkCallback = null;
            }
            this.mobileNetworkCallback = tempNetworkCallback;
            this.allNetworkCallback = new SimpleNetworkCallback(this.availableNetworks);
            this.connectivityManagerDelegate.registerNetworkCallback(this.allNetworkCallback);
            return;
        }
        this.mobileNetworkCallback = null;
        this.allNetworkCallback = null;
    }

    public static void setIncludeWifiDirect(boolean enable) {
        includeWifiDirect = enable;
    }

    @Override // org.webrtc.NetworkChangeDetector
    public boolean supportNetworkCallback() {
        return this.connectivityManagerDelegate.supportNetworkCallback();
    }

    void setConnectivityManagerDelegateForTests(ConnectivityManagerDelegate delegate) {
        this.connectivityManagerDelegate = delegate;
    }

    void setWifiManagerDelegateForTests(WifiManagerDelegate delegate) {
        this.wifiManagerDelegate = delegate;
    }

    boolean isReceiverRegisteredForTesting() {
        return this.isRegistered;
    }

    @Override // org.webrtc.NetworkChangeDetector
    public List<NetworkChangeDetector.NetworkInformation> getActiveNetworkList() {
        List<NetworkChangeDetector.NetworkInformation> connectivityManagerList = this.connectivityManagerDelegate.getActiveNetworkList();
        if (connectivityManagerList == null) {
            return null;
        }
        ArrayList<NetworkChangeDetector.NetworkInformation> result = new ArrayList<>(connectivityManagerList);
        if (this.wifiDirectManagerDelegate != null) {
            result.addAll(this.wifiDirectManagerDelegate.getActiveNetworkList());
        }
        return result;
    }

    @Override // org.webrtc.NetworkChangeDetector
    public void destroy() {
        if (this.allNetworkCallback != null) {
            this.connectivityManagerDelegate.releaseCallback(this.allNetworkCallback);
        }
        if (this.mobileNetworkCallback != null) {
            this.connectivityManagerDelegate.releaseCallback(this.mobileNetworkCallback);
        }
        if (this.wifiDirectManagerDelegate != null) {
            this.wifiDirectManagerDelegate.release();
        }
        unregisterReceiver();
    }

    private void registerReceiver() {
        if (this.isRegistered) {
            return;
        }
        this.isRegistered = true;
        this.context.registerReceiver(this, this.intentFilter);
    }

    private void unregisterReceiver() {
        if (!this.isRegistered) {
            return;
        }
        this.isRegistered = false;
        this.context.unregisterReceiver(this);
    }

    public NetworkState getCurrentNetworkState() {
        return this.connectivityManagerDelegate.getNetworkState();
    }

    public long getDefaultNetId() {
        return this.connectivityManagerDelegate.getDefaultNetId();
    }

    private static NetworkChangeDetector.ConnectionType getConnectionType(boolean isConnected, int networkType, int networkSubtype) {
        if (!isConnected) {
            return NetworkChangeDetector.ConnectionType.CONNECTION_NONE;
        }
        switch (networkType) {
            case 0:
            case 4:
            case 5:
                switch (networkSubtype) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                    case 16:
                        return NetworkChangeDetector.ConnectionType.CONNECTION_2G;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                    case 17:
                        return NetworkChangeDetector.ConnectionType.CONNECTION_3G;
                    case 13:
                    case 18:
                        return NetworkChangeDetector.ConnectionType.CONNECTION_4G;
                    case 19:
                    default:
                        return NetworkChangeDetector.ConnectionType.CONNECTION_UNKNOWN_CELLULAR;
                    case 20:
                        return NetworkChangeDetector.ConnectionType.CONNECTION_5G;
                }
            case 1:
                return NetworkChangeDetector.ConnectionType.CONNECTION_WIFI;
            case 6:
                return NetworkChangeDetector.ConnectionType.CONNECTION_4G;
            case 7:
                return NetworkChangeDetector.ConnectionType.CONNECTION_BLUETOOTH;
            case 9:
                return NetworkChangeDetector.ConnectionType.CONNECTION_ETHERNET;
            case 17:
                return NetworkChangeDetector.ConnectionType.CONNECTION_VPN;
            default:
                return NetworkChangeDetector.ConnectionType.CONNECTION_UNKNOWN;
        }
    }

    public static NetworkChangeDetector.ConnectionType getConnectionType(NetworkState networkState) {
        return getConnectionType(networkState.isConnected(), networkState.getNetworkType(), networkState.getNetworkSubType());
    }

    @Override // org.webrtc.NetworkChangeDetector
    public NetworkChangeDetector.ConnectionType getCurrentConnectionType() {
        return getConnectionType(getCurrentNetworkState());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static NetworkChangeDetector.ConnectionType getUnderlyingConnectionTypeForVpn(NetworkState networkState) {
        if (networkState.getNetworkType() != 17) {
            return NetworkChangeDetector.ConnectionType.CONNECTION_NONE;
        }
        return getConnectionType(networkState.isConnected(), networkState.getUnderlyingNetworkTypeForVpn(), networkState.getUnderlyingNetworkSubtypeForVpn());
    }

    private String getWifiSSID(NetworkState networkState) {
        if (getConnectionType(networkState) != NetworkChangeDetector.ConnectionType.CONNECTION_WIFI) {
            return "";
        }
        return this.wifiManagerDelegate.getWifiSSID();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        NetworkState networkState = getCurrentNetworkState();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            connectionTypeChanged(networkState);
        }
    }

    private void connectionTypeChanged(NetworkState networkState) {
        NetworkChangeDetector.ConnectionType newConnectionType = getConnectionType(networkState);
        String newWifiSSID = getWifiSSID(networkState);
        if (newConnectionType == this.connectionType && newWifiSSID.equals(this.wifiSSID)) {
            return;
        }
        this.connectionType = newConnectionType;
        this.wifiSSID = newWifiSSID;
        Logging.d(TAG, "Network connectivity changed, type is: " + this.connectionType);
        this.observer.onConnectionTypeChanged(newConnectionType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long networkToNetId(Network network) {
        return network.getNetworkHandle();
    }
}
