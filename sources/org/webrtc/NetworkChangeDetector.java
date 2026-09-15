package org.webrtc;

import java.util.List;
/* loaded from: classes5.dex */
public interface NetworkChangeDetector {

    /* loaded from: classes5.dex */
    public enum ConnectionType {
        CONNECTION_UNKNOWN,
        CONNECTION_ETHERNET,
        CONNECTION_WIFI,
        CONNECTION_5G,
        CONNECTION_4G,
        CONNECTION_3G,
        CONNECTION_2G,
        CONNECTION_UNKNOWN_CELLULAR,
        CONNECTION_BLUETOOTH,
        CONNECTION_VPN,
        CONNECTION_NONE
    }

    void destroy();

    List<NetworkInformation> getActiveNetworkList();

    ConnectionType getCurrentConnectionType();

    boolean supportNetworkCallback();

    /* loaded from: classes5.dex */
    public static class IPAddress {
        public final byte[] address;

        public IPAddress(byte[] address) {
            this.address = address;
        }

        private byte[] getAddress() {
            return this.address;
        }
    }

    /* loaded from: classes5.dex */
    public static class NetworkInformation {
        public final long handle;
        public final IPAddress[] ipAddresses;
        public final String name;
        public final ConnectionType type;
        public final ConnectionType underlyingTypeForVpn;

        public NetworkInformation(String name, ConnectionType type, ConnectionType underlyingTypeForVpn, long handle, IPAddress[] addresses) {
            this.name = name;
            this.type = type;
            this.underlyingTypeForVpn = underlyingTypeForVpn;
            this.handle = handle;
            this.ipAddresses = addresses;
        }

        private IPAddress[] getIpAddresses() {
            return this.ipAddresses;
        }

        private ConnectionType getConnectionType() {
            return this.type;
        }

        private ConnectionType getUnderlyingConnectionTypeForVpn() {
            return this.underlyingTypeForVpn;
        }

        private long getHandle() {
            return this.handle;
        }

        private String getName() {
            return this.name;
        }
    }

    /* loaded from: classes5.dex */
    public static abstract class Observer {
        public abstract void onConnectionTypeChanged(ConnectionType connectionType);

        public abstract void onNetworkConnect(NetworkInformation networkInformation);

        public abstract void onNetworkDisconnect(long j);

        public abstract void onNetworkPreference(List<ConnectionType> list, int i);

        public String getFieldTrialsString() {
            return "";
        }
    }
}
