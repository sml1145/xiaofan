package com.xiaofan.bangfan;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.VibratorManager;
import android.util.Log;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.BluetoothRemoteService;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
/* compiled from: BluetoothRemoteService.kt */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\u0018\u0000 F2\u00020\u0001:\u0005EFGHIB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0018H\u0002J\b\u0010\u001c\u001a\u00020\u0012H\u0002J\b\u0010\u001d\u001a\u00020\u001aH\u0002J\b\u0010\u001e\u001a\u00020\u001aH\u0002J\b\u0010\u001f\u001a\u00020\u001aH\u0002J\b\u0010 \u001a\u00020\u001aH\u0002J\u0010\u0010!\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0018H\u0002J\u0010\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u0012H\u0002J\b\u0010$\u001a\u00020\u0012H\u0002J\u0012\u0010%\u001a\u00020\u00182\b\u0010&\u001a\u0004\u0018\u00010\u0018H\u0002J\u0014\u0010'\u001a\u0004\u0018\u00010(2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\b\u0010+\u001a\u00020\u001aH\u0016J\b\u0010,\u001a\u00020\u001aH\u0016J\u0010\u0010-\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020/H\u0002J\u001a\u00100\u001a\u00020\u001a2\u0006\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u000104H\u0002J\"\u00105\u001a\u00020\f2\b\u0010)\u001a\u0004\u0018\u00010*2\u0006\u00106\u001a\u00020\f2\u0006\u00107\u001a\u00020\fH\u0016J\u0014\u00108\u001a\u0004\u0018\u00010\u00182\b\u00109\u001a\u0004\u0018\u000104H\u0002J\u0010\u0010:\u001a\u00020\u001a2\u0006\u0010;\u001a\u00020\u0010H\u0002J\b\u0010<\u001a\u00020\u001aH\u0002J\u001a\u0010=\u001a\u00020\u001a2\u0006\u0010>\u001a\u00020\f2\b\u00103\u001a\u0004\u0018\u00010\u0018H\u0002J\u001a\u0010?\u001a\u00020\u001a2\u0006\u0010>\u001a\u00020\f2\b\u00103\u001a\u0004\u0018\u00010\u0018H\u0002J\b\u0010@\u001a\u00020\u001aH\u0002J\u0010\u0010A\u001a\u00020\u00122\u0006\u0010B\u001a\u00020\u0018H\u0002J\u0010\u0010C\u001a\u00020\u001a2\u0006\u0010D\u001a\u00020\u0012H\u0002R\u0014\u0010\u0003\u001a\b\u0018\u00010\u0004R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0018\u00010\bR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0018\u00010\nR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Lcom/xiaofan/bangfan/BluetoothRemoteService;", "Landroid/app/Service;", "()V", "acceptThread", "Lcom/xiaofan/bangfan/BluetoothRemoteService$AcceptThread;", "adapter", "Landroid/bluetooth/BluetoothAdapter;", "connectThread", "Lcom/xiaofan/bangfan/BluetoothRemoteService$ConnectThread;", "connectedThread", "Lcom/xiaofan/bangfan/BluetoothRemoteService$ConnectedThread;", "dialAttempts", "", "dialRunnable", "Ljava/lang/Runnable;", "lastVolAt", "", "lastVolIsUp", "", "main", "Landroid/os/Handler;", "rng", "Ljava/util/Random;", "targetMac", "", "attemptConnect", "", "mac", "btReady", "closeAllThreads", "connectionLost", "createChannel", "doListenOnly", "doPair", "handleVolume", "isUp", "hasConnectPerm", "norm", "m", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onIncoming", "b", "", "onSocketConnected", "socket", "Landroid/bluetooth/BluetoothSocket;", "peer", "Landroid/bluetooth/BluetoothDevice;", "onStartCommand", "flags", "startId", "safeName", "d", "scheduleDial", "delayMs", "scheduleDialRetry", "setState", "s", "startAsForeground", "stopEverything", "toast", "t", "vibrate", "short", "AcceptThread", "Companion", "ConnectThread", "ConnectedThread", "Listener", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class BluetoothRemoteService extends Service {
    public static final String ACTION_CONNECT = "com.xiaofan.bangfan.action.BT_CONNECT";
    public static final String ACTION_DISCONNECT = "com.xiaofan.bangfan.action.BT_DISCONNECT";
    public static final String ACTION_LISTEN = "com.xiaofan.bangfan.action.BT_LISTEN";
    public static final String ACTION_PAIR = "com.xiaofan.bangfan.action.BT_PAIR";
    public static final String ACTION_STOP = "com.xiaofan.bangfan.action.BT_STOP";
    private static final String CHANNEL_ID = "xiaofan_bt_remote";
    public static final Companion Companion = new Companion(null);
    private static final long DIAL_BASE_MS = 600;
    private static final long DIAL_JITTER_MS = 700;
    public static final String EXTRA_MAC = "bt_mac";
    private static final int MAX_DIAL_ATTEMPTS = 14;
    private static final int NOTIF_ID = 2007;
    private static final String SPP_NAME = "XiaoFanRemote";
    private static final UUID SPP_UUID;
    public static final int STATE_CONNECTED = 3;
    public static final int STATE_CONNECTING = 2;
    public static final int STATE_IDLE = 0;
    public static final int STATE_LISTENING = 1;
    private static final long VOL_DEBOUNCE_MS = 280;
    private static volatile BluetoothRemoteService instance;
    private static volatile Listener listener;
    private static volatile String peerName;
    private static volatile int state;
    private AcceptThread acceptThread;
    private BluetoothAdapter adapter;
    private ConnectThread connectThread;
    private ConnectedThread connectedThread;
    private volatile int dialAttempts;
    private volatile long lastVolAt;
    private volatile boolean lastVolIsUp;
    private volatile String targetMac;
    private final Handler main = new Handler(Looper.getMainLooper());
    private final Random rng = new Random();
    private final Runnable dialRunnable = new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            BluetoothRemoteService.dialRunnable$lambda$1(BluetoothRemoteService.this);
        }
    };

    /* compiled from: BluetoothRemoteService.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, d2 = {"Lcom/xiaofan/bangfan/BluetoothRemoteService$Listener;", "", "onBtState", "", "state", "", "peerName", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public interface Listener {
        void onBtState(int i, String str);
    }

    /* compiled from: BluetoothRemoteService.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$J\u0006\u0010%\u001a\u00020&J\u000e\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020&J\u0016\u0010)\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010*\u001a\u00020\u0004J\b\u0010+\u001a\u0004\u0018\u00010\u0004J\u0010\u0010,\u001a\u00020\"2\b\u0010-\u001a\u0004\u0018\u00010\u001eJ\u000e\u0010.\u001a\u00020\"2\u0006\u0010#\u001a\u00020$J\u0006\u0010/\u001a\u00020\u000fJ\u000e\u00100\u001a\u00020\"2\u0006\u0010#\u001a\u00020$J\u0006\u00101\u001a\u00020&R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/xiaofan/bangfan/BluetoothRemoteService$Companion;", "", "()V", "ACTION_CONNECT", "", "ACTION_DISCONNECT", "ACTION_LISTEN", "ACTION_PAIR", "ACTION_STOP", "CHANNEL_ID", "DIAL_BASE_MS", "", "DIAL_JITTER_MS", "EXTRA_MAC", "MAX_DIAL_ATTEMPTS", "", "NOTIF_ID", "SPP_NAME", "SPP_UUID", "Ljava/util/UUID;", "getSPP_UUID", "()Ljava/util/UUID;", "STATE_CONNECTED", "STATE_CONNECTING", "STATE_IDLE", "STATE_LISTENING", "VOL_DEBOUNCE_MS", "instance", "Lcom/xiaofan/bangfan/BluetoothRemoteService;", "listener", "Lcom/xiaofan/bangfan/BluetoothRemoteService$Listener;", "peerName", "state", "disconnect", "", "ctx", "Landroid/content/Context;", "isConnected", "", "onVolumeKey", "isUp", "pair", "mac", "peerNow", "setListener", "l", "startListen", "stateNow", "stop", "volumeRemoteActive", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final UUID getSPP_UUID() {
            return BluetoothRemoteService.SPP_UUID;
        }

        public final boolean isConnected() {
            return BluetoothRemoteService.state == 3;
        }

        public final int stateNow() {
            return BluetoothRemoteService.state;
        }

        public final String peerNow() {
            return BluetoothRemoteService.peerName;
        }

        public final void setListener(Listener l) {
            BluetoothRemoteService.listener = l;
            if (l != null) {
                l.onBtState(BluetoothRemoteService.state, BluetoothRemoteService.peerName);
            }
        }

        public final void pair(Context ctx, String mac) {
            Intrinsics.checkNotNullParameter(ctx, "ctx");
            Intrinsics.checkNotNullParameter(mac, "mac");
            Intent i = new Intent(ctx, BluetoothRemoteService.class).setAction(BluetoothRemoteService.ACTION_PAIR).putExtra(BluetoothRemoteService.EXTRA_MAC, mac);
            Intrinsics.checkNotNullExpressionValue(i, "putExtra(...)");
            if (Build.VERSION.SDK_INT >= 26) {
                ctx.startForegroundService(i);
            } else {
                ctx.startService(i);
            }
        }

        public final void startListen(Context ctx) {
            Intrinsics.checkNotNullParameter(ctx, "ctx");
            Intent i = new Intent(ctx, BluetoothRemoteService.class).setAction(BluetoothRemoteService.ACTION_LISTEN);
            Intrinsics.checkNotNullExpressionValue(i, "setAction(...)");
            if (Build.VERSION.SDK_INT >= 26) {
                ctx.startForegroundService(i);
            } else {
                ctx.startService(i);
            }
        }

        public final void disconnect(Context ctx) {
            Intrinsics.checkNotNullParameter(ctx, "ctx");
            try {
                ctx.startService(new Intent(ctx, BluetoothRemoteService.class).setAction(BluetoothRemoteService.ACTION_DISCONNECT));
            } catch (Throwable th) {
            }
        }

        public final void stop(Context ctx) {
            Intrinsics.checkNotNullParameter(ctx, "ctx");
            try {
                ctx.startService(new Intent(ctx, BluetoothRemoteService.class).setAction(BluetoothRemoteService.ACTION_STOP));
            } catch (Throwable th) {
            }
        }

        public final boolean onVolumeKey(boolean isUp) {
            BluetoothRemoteService s = BluetoothRemoteService.instance;
            if (s == null) {
                return false;
            }
            return s.handleVolume(isUp);
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0020  */
        /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final boolean volumeRemoteActive() {
            /*
                r6 = this;
                r0 = 0
                int r1 = com.xiaofan.bangfan.BluetoothRemoteService.access$getState$cp()     // Catch: java.lang.Throwable -> L22
                r2 = 3
                if (r1 != r2) goto L23
                com.xiaofan.bangfan.BluetoothRemoteService r1 = com.xiaofan.bangfan.BluetoothRemoteService.access$getInstance$cp()     // Catch: java.lang.Throwable -> L22
                r2 = 1
                if (r1 == 0) goto L1d
                r3 = 0
                com.xiaofan.bangfan.AppPrefs r4 = com.xiaofan.bangfan.AppPrefs.INSTANCE     // Catch: java.lang.Throwable -> L22
                r5 = r1
                android.content.Context r5 = (android.content.Context) r5     // Catch: java.lang.Throwable -> L22
                boolean r4 = r4.btVolumeRemote(r5)     // Catch: java.lang.Throwable -> L22
                if (r4 != r2) goto L1d
                r1 = r2
                goto L1e
            L1d:
                r1 = r0
            L1e:
                if (r1 == 0) goto L23
                r0 = r2
                goto L23
            L22:
                r1 = move-exception
            L23:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.BluetoothRemoteService.Companion.volumeRemoteActive():boolean");
        }
    }

    static {
        UUID fromString = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        Intrinsics.checkNotNullExpressionValue(fromString, "fromString(...)");
        SPP_UUID = fromString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void dialRunnable$lambda$1(BluetoothRemoteService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String it = this$0.targetMac;
        if (it != null) {
            this$0.attemptConnect(it);
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        instance = this;
        Object systemService = getSystemService("bluetooth");
        BluetoothManager bluetoothManager = systemService instanceof BluetoothManager ? (BluetoothManager) systemService : null;
        this.adapter = bluetoothManager != null ? bluetoothManager.getAdapter() : null;
        createChannel();
        startAsForeground(0, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0019, code lost:
        if (r0.equals(com.xiaofan.bangfan.BluetoothRemoteService.ACTION_STOP) == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0053, code lost:
        if (r0.equals(com.xiaofan.bangfan.BluetoothRemoteService.ACTION_DISCONNECT) == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0056, code lost:
        stopEverything();
     */
    @Override // android.app.Service
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int onStartCommand(android.content.Intent r4, int r5, int r6) {
        /*
            r3 = this;
            if (r4 == 0) goto L7
            java.lang.String r0 = r4.getAction()
            goto L8
        L7:
            r0 = 0
        L8:
            r1 = 1
            if (r0 == 0) goto L59
            int r2 = r0.hashCode()
            switch(r2) {
                case -1943442613: goto L4d;
                case -1371351050: goto L40;
                case 593096905: goto L1c;
                case 593204721: goto L13;
                default: goto L12;
            }
        L12:
            goto L59
        L13:
            java.lang.String r2 = "com.xiaofan.bangfan.action.BT_STOP"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L56
            goto L59
        L1c:
            java.lang.String r2 = "com.xiaofan.bangfan.action.BT_PAIR"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L25
            goto L59
        L25:
            java.lang.String r0 = "bt_mac"
            java.lang.String r0 = r4.getStringExtra(r0)
            r2 = r0
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            if (r2 == 0) goto L39
            boolean r2 = kotlin.text.StringsKt.isBlank(r2)
            if (r2 == 0) goto L37
            goto L39
        L37:
            r2 = 0
            goto L3a
        L39:
            r2 = r1
        L3a:
            if (r2 != 0) goto L59
            r3.doPair(r0)
            goto L59
        L40:
            java.lang.String r2 = "com.xiaofan.bangfan.action.BT_LISTEN"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L49
            goto L59
        L49:
            r3.doListenOnly()
            goto L59
        L4d:
            java.lang.String r2 = "com.xiaofan.bangfan.action.BT_DISCONNECT"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L56
            goto L59
        L56:
            r3.stopEverything()
        L59:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.BluetoothRemoteService.onStartCommand(android.content.Intent, int, int):int");
    }

    private final boolean btReady() {
        BluetoothAdapter a = this.adapter;
        if (a == null) {
            return false;
        }
        try {
            return a.isEnabled();
        } catch (Throwable th) {
            return false;
        }
    }

    private final String norm(String m) {
        String upperCase = StringsKt.replace$default(StringsKt.replace$default(m == null ? "" : m, ":", "", false, 4, (Object) null), "-", "", false, 4, (Object) null).toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        return upperCase;
    }

    private final void doPair(String mac) {
        BluetoothDevice bluetoothDevice = null;
        if (!btReady()) {
            toast("请先在系统设置里打开蓝牙");
            setState(0, null);
        } else if (!hasConnectPerm()) {
            toast("没有蓝牙连接权限，请到“连接设备”重新授权");
            setState(0, null);
        } else {
            this.main.removeCallbacks(this.dialRunnable);
            closeAllThreads();
            this.targetMac = mac;
            this.dialAttempts = 0;
            try {
                BluetoothAdapter bluetoothAdapter = this.adapter;
                if (bluetoothAdapter != null) {
                    bluetoothDevice = bluetoothAdapter.getRemoteDevice(mac);
                }
            } catch (Throwable th) {
            }
            BluetoothDevice peer = bluetoothDevice;
            setState(1, safeName(peer));
            AcceptThread it = new AcceptThread();
            it.start();
            this.acceptThread = it;
            scheduleDial(this.rng.nextInt(700) + 300);
        }
    }

    private final boolean hasConnectPerm() {
        return Build.VERSION.SDK_INT < 31 || checkSelfPermission("android.permission.BLUETOOTH_CONNECT") == 0;
    }

    private final void scheduleDial(long delayMs) {
        this.main.removeCallbacks(this.dialRunnable);
        this.main.postDelayed(this.dialRunnable, delayMs);
    }

    private final void doListenOnly() {
        if (!btReady()) {
            toast("请先在系统设置里打开蓝牙");
            setState(0, null);
            return;
        }
        this.main.removeCallbacks(this.dialRunnable);
        closeAllThreads();
        setState(1, null);
        AcceptThread it = new AcceptThread();
        it.start();
        this.acceptThread = it;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x004b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void attemptConnect(java.lang.String r11) {
        /*
            r10 = this;
            int r0 = com.xiaofan.bangfan.BluetoothRemoteService.state
            r1 = 3
            if (r0 != r1) goto L6
            return
        L6:
            android.bluetooth.BluetoothAdapter r0 = r10.adapter
            if (r0 != 0) goto Lb
            return
        Lb:
            r1 = 0
            java.util.Set r2 = r0.getBondedDevices()     // Catch: java.lang.Throwable -> L41
            if (r2 == 0) goto L3c
            java.lang.Iterable r2 = (java.lang.Iterable) r2     // Catch: java.lang.Throwable -> L41
            r3 = 0
            java.util.Iterator r4 = r2.iterator()     // Catch: java.lang.Throwable -> L41
        L1a:
            boolean r5 = r4.hasNext()     // Catch: java.lang.Throwable -> L41
            if (r5 == 0) goto L34
            java.lang.Object r5 = r4.next()     // Catch: java.lang.Throwable -> L41
            r6 = r5
            android.bluetooth.BluetoothDevice r6 = (android.bluetooth.BluetoothDevice) r6     // Catch: java.lang.Throwable -> L41
            r7 = 0
            java.lang.String r8 = r6.getAddress()     // Catch: java.lang.Throwable -> L41
            r9 = 1
            boolean r8 = kotlin.text.StringsKt.equals(r8, r11, r9)     // Catch: java.lang.Throwable -> L41
            if (r8 == 0) goto L1a
            goto L35
        L34:
            r5 = r1
        L35:
            android.bluetooth.BluetoothDevice r5 = (android.bluetooth.BluetoothDevice) r5     // Catch: java.lang.Throwable -> L41
            if (r5 != 0) goto L3a
            goto L3c
        L3a:
            r1 = r5
            goto L42
        L3c:
            android.bluetooth.BluetoothDevice r1 = r0.getRemoteDevice(r11)     // Catch: java.lang.Throwable -> L41
            goto L42
        L41:
            r2 = move-exception
        L42:
            if (r1 != 0) goto L4b
            java.lang.String r2 = "找不到这台已配对设备"
            r10.toast(r2)
            return
        L4b:
            com.xiaofan.bangfan.BluetoothRemoteService$ConnectThread r2 = r10.connectThread     // Catch: java.lang.Throwable -> L53
            if (r2 == 0) goto L54
            r2.cancel()     // Catch: java.lang.Throwable -> L53
            goto L54
        L53:
            r2 = move-exception
        L54:
            r2 = 2
            java.lang.String r3 = r10.safeName(r1)
            r10.setState(r2, r3)
            com.xiaofan.bangfan.BluetoothRemoteService$ConnectThread r2 = new com.xiaofan.bangfan.BluetoothRemoteService$ConnectThread
            r2.<init>(r10, r1)
            r3 = r2
            r4 = 0
            r3.start()
            r10.connectThread = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.BluetoothRemoteService.attemptConnect(java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scheduleDialRetry() {
        this.dialAttempts++;
        if (state == 3 || this.targetMac == null) {
            return;
        }
        if (this.dialAttempts <= 14) {
            Log.i("BtRemote", "dial retry " + this.dialAttempts);
            if (state != 2) {
                setState(1, peerName);
            }
            long backoff = RangesKt.coerceAtMost(((this.dialAttempts / 3) + 1) * DIAL_BASE_MS, 2600L);
            scheduleDial(this.rng.nextInt(700) + backoff);
            return;
        }
        setState(1, peerName);
        toast("还没连上，请确认两台已配对、都点了“连接设备”，且都已授权蓝牙");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onSocketConnected(BluetoothSocket socket, BluetoothDevice peer) {
        ConnectedThread it;
        this.main.removeCallbacks(this.dialRunnable);
        if (state == 3) {
            Log.i("BtRemote", "duplicate socket arrived, closing");
            try {
                socket.close();
                return;
            } catch (Throwable th) {
                return;
            }
        }
        try {
            it = new ConnectedThread(this, socket);
            it.start();
        } catch (Throwable th2) {
            Log.e("BtRemote", "open streams failed", th2);
            it = null;
        }
        ConnectedThread ct = it;
        if (ct == null) {
            try {
                socket.close();
            } catch (Throwable th3) {
            }
            setState(1, peerName);
            scheduleDialRetry();
            return;
        }
        this.connectedThread = ct;
        try {
            AcceptThread acceptThread = this.acceptThread;
            if (acceptThread != null) {
                acceptThread.cancel();
            }
        } catch (Throwable th4) {
        }
        this.acceptThread = null;
        try {
            ConnectThread connectThread = this.connectThread;
            if (connectThread != null) {
                connectThread.cancel();
            }
        } catch (Throwable th5) {
        }
        this.connectThread = null;
        AppPrefs.INSTANCE.setBtLastPeer(this, peer != null ? peer.getAddress() : null);
        setState(3, safeName(peer));
        this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                BluetoothRemoteService.onSocketConnected$lambda$7(BluetoothRemoteService.this);
            }
        });
        ct.write(new byte[]{BtProto.CMD_PING});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onSocketConnected$lambda$7(BluetoothRemoteService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.vibrate(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void connectionLost() {
        this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                BluetoothRemoteService.connectionLost$lambda$9(BluetoothRemoteService.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void connectionLost$lambda$9(BluetoothRemoteService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.toast("蓝牙连接已断开");
        this$0.vibrate(false);
        this$0.closeAllThreads();
        String mac = this$0.targetMac;
        if (mac == null) {
            this$0.setState(0, null);
            return;
        }
        this$0.dialAttempts = 0;
        this$0.setState(1, peerName);
        AcceptThread it = new AcceptThread();
        it.start();
        this$0.acceptThread = it;
        this$0.scheduleDial(this$0.rng.nextInt(600) + 400);
    }

    private final void closeAllThreads() {
        try {
            ConnectThread connectThread = this.connectThread;
            if (connectThread != null) {
                connectThread.cancel();
            }
        } catch (Throwable th) {
        }
        this.connectThread = null;
        try {
            AcceptThread acceptThread = this.acceptThread;
            if (acceptThread != null) {
                acceptThread.cancel();
            }
        } catch (Throwable th2) {
        }
        this.acceptThread = null;
        try {
            ConnectedThread connectedThread = this.connectedThread;
            if (connectedThread != null) {
                connectedThread.cancel();
            }
        } catch (Throwable th3) {
        }
        this.connectedThread = null;
    }

    private final void stopEverything() {
        this.main.removeCallbacks(this.dialRunnable);
        closeAllThreads();
        this.targetMac = null;
        setState(0, null);
        try {
            stopForeground(1);
        } catch (Throwable th) {
        }
        stopSelf();
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.main.removeCallbacks(this.dialRunnable);
        closeAllThreads();
        state = 0;
        instance = null;
        super.onDestroy();
    }

    private final String safeName(BluetoothDevice d) {
        String str;
        if (d != null) {
            try {
                str = d.getName();
            } catch (Throwable th) {
                str = null;
            }
        } else {
            str = null;
        }
        if (str == null) {
            if (d != null) {
                return d.getAddress();
            }
            return null;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean handleVolume(boolean isUp) {
        if (AppPrefs.INSTANCE.btVolumeRemote(this) && state == 3) {
            long now = SystemClock.uptimeMillis();
            if (isUp != this.lastVolIsUp || now - this.lastVolAt >= VOL_DEBOUNCE_MS) {
                this.lastVolAt = now;
                this.lastVolIsUp = isUp;
                byte cmd = isUp ? (byte) 2 : (byte) 1;
                ConnectedThread connectedThread = this.connectedThread;
                boolean ok = connectedThread != null ? connectedThread.write(new byte[]{cmd}) : false;
                if (ok) {
                    vibrate(true);
                } else {
                    toast("还没连上，发不出去");
                }
                return true;
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onIncoming(byte b) {
        if (b == 16) {
            ConnectedThread connectedThread = this.connectedThread;
            if (connectedThread != null) {
                connectedThread.write(new byte[]{BtProto.CMD_PONG});
            }
        } else if (b != 17) {
            if (b != 1) {
                if (b == 2) {
                    this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$$ExternalSyntheticLambda5
                        @Override // java.lang.Runnable
                        public final void run() {
                            BluetoothRemoteService.onIncoming$lambda$11(BluetoothRemoteService.this);
                        }
                    });
                    return;
                }
                return;
            }
            this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    BluetoothRemoteService.onIncoming$lambda$10(BluetoothRemoteService.this);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onIncoming$lambda$10(BluetoothRemoteService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        boolean ok = TurnManager.INSTANCE.requestTurn(this$0, "蓝牙遥控-下一页");
        if (AppPrefs.INSTANCE.btRecvTip(this$0)) {
            XiaoFanVoice.INSTANCE.tip(this$0, ok ? "对方让我翻下一页" : "翻页没成功，检查无障碍");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onIncoming$lambda$11(BluetoothRemoteService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        boolean ok = TurnManager.INSTANCE.requestPrev(this$0, "蓝牙遥控-上一页");
        if (AppPrefs.INSTANCE.btRecvTip(this$0)) {
            XiaoFanVoice.INSTANCE.tip(this$0, ok ? "对方让我回上一页" : "回退没成功，检查无障碍");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: BluetoothRemoteService.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tJ\b\u0010\n\u001a\u00020\tH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/xiaofan/bangfan/BluetoothRemoteService$AcceptThread;", "Ljava/lang/Thread;", "(Lcom/xiaofan/bangfan/BluetoothRemoteService;)V", "fallback", "Landroid/bluetooth/BluetoothServerSocket;", "primary", "stopped", "", "cancel", "", "run", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public final class AcceptThread extends Thread {
        private BluetoothServerSocket fallback;
        private BluetoothServerSocket primary;
        private volatile boolean stopped;

        public AcceptThread() {
            setName("xf-bt-accept");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            BluetoothSocket bluetoothSocket;
            BluetoothServerSocket bluetoothServerSocket;
            BluetoothServerSocket bluetoothServerSocket2;
            int listenTries = 0;
            while (!this.stopped) {
                try {
                    BluetoothAdapter bluetoothAdapter = BluetoothRemoteService.this.adapter;
                    bluetoothServerSocket = bluetoothAdapter != null ? bluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord(BluetoothRemoteService.SPP_NAME, BluetoothRemoteService.Companion.getSPP_UUID()) : null;
                } catch (Throwable th) {
                    bluetoothServerSocket = null;
                }
                this.primary = bluetoothServerSocket;
                if (this.primary == null) {
                    try {
                        BluetoothAdapter bluetoothAdapter2 = BluetoothRemoteService.this.adapter;
                        bluetoothServerSocket2 = bluetoothAdapter2 != null ? bluetoothAdapter2.listenUsingRfcommWithServiceRecord(BluetoothRemoteService.SPP_NAME, BluetoothRemoteService.Companion.getSPP_UUID()) : null;
                    } catch (Throwable th2) {
                        Log.e("BtRemote", "listen failed", th2);
                        bluetoothServerSocket2 = null;
                    }
                    this.fallback = bluetoothServerSocket2;
                }
                if (this.primary != null || this.fallback != null) {
                    break;
                }
                listenTries++;
                if (listenTries >= 10 || this.stopped) {
                    return;
                }
                try {
                    Thread.sleep(800L);
                } catch (InterruptedException e) {
                    return;
                }
            }
            BluetoothSocket peerSocket = null;
            while (!this.stopped) {
                try {
                    BluetoothServerSocket bluetoothServerSocket3 = this.primary;
                    if (bluetoothServerSocket3 == null || (bluetoothSocket = bluetoothServerSocket3.accept()) == null) {
                        BluetoothServerSocket bluetoothServerSocket4 = this.fallback;
                        bluetoothSocket = bluetoothServerSocket4 != null ? bluetoothServerSocket4.accept() : null;
                    }
                } catch (Throwable th3) {
                    bluetoothSocket = null;
                }
                peerSocket = bluetoothSocket;
                if (peerSocket != null) {
                    break;
                } else if (this.stopped) {
                    return;
                } else {
                    try {
                        Thread.sleep(300L);
                    } catch (InterruptedException e2) {
                        return;
                    }
                }
            }
            if (peerSocket != null) {
                final BluetoothRemoteService bluetoothRemoteService = BluetoothRemoteService.this;
                final BluetoothSocket s = peerSocket;
                bluetoothRemoteService.main.post(new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$AcceptThread$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        BluetoothRemoteService.AcceptThread.run$lambda$1$lambda$0(BluetoothRemoteService.this, s);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void run$lambda$1$lambda$0(BluetoothRemoteService this$0, BluetoothSocket s) {
            BluetoothDevice bluetoothDevice;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(s, "$s");
            try {
                bluetoothDevice = s.getRemoteDevice();
            } catch (Throwable th) {
                bluetoothDevice = null;
            }
            this$0.onSocketConnected(s, bluetoothDevice);
        }

        public final void cancel() {
            this.stopped = true;
            try {
                BluetoothServerSocket bluetoothServerSocket = this.primary;
                if (bluetoothServerSocket != null) {
                    bluetoothServerSocket.close();
                }
            } catch (Throwable th) {
            }
            try {
                BluetoothServerSocket bluetoothServerSocket2 = this.fallback;
                if (bluetoothServerSocket2 != null) {
                    bluetoothServerSocket2.close();
                }
            } catch (Throwable th2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: BluetoothRemoteService.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/xiaofan/bangfan/BluetoothRemoteService$ConnectThread;", "Ljava/lang/Thread;", "device", "Landroid/bluetooth/BluetoothDevice;", "(Lcom/xiaofan/bangfan/BluetoothRemoteService;Landroid/bluetooth/BluetoothDevice;)V", "done", "", "sock", "Landroid/bluetooth/BluetoothSocket;", "cancel", "", "run", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public final class ConnectThread extends Thread {
        private final BluetoothDevice device;
        private volatile boolean done;
        private volatile BluetoothSocket sock;
        final /* synthetic */ BluetoothRemoteService this$0;

        public ConnectThread(BluetoothRemoteService this$0, BluetoothDevice device) {
            Intrinsics.checkNotNullParameter(device, "device");
            this.this$0 = this$0;
            this.device = device;
            setName("xf-bt-connect");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            BluetoothSocket s;
            try {
                BluetoothAdapter bluetoothAdapter = this.this$0.adapter;
                if (bluetoothAdapter != null) {
                    bluetoothAdapter.cancelDiscovery();
                }
            } catch (Throwable th) {
            }
            BluetoothSocket bluetoothSocket = null;
            try {
                s = this.device.createInsecureRfcommSocketToServiceRecord(BluetoothRemoteService.Companion.getSPP_UUID());
            } catch (Throwable th2) {
                s = null;
            }
            this.sock = s;
            boolean connected = false;
            if (s != null) {
                try {
                    s.connect();
                    connected = true;
                } catch (Throwable th3) {
                    Log.w("BtRemote", "insecure connect failed", th3);
                    try {
                        s.close();
                    } catch (Throwable th4) {
                    }
                    s = null;
                }
            }
            if (!connected && !this.done) {
                try {
                    bluetoothSocket = this.device.createRfcommSocketToServiceRecord(BluetoothRemoteService.Companion.getSPP_UUID());
                } catch (Throwable th5) {
                }
                s = bluetoothSocket;
                this.sock = s;
                if (s != null) {
                    try {
                        s.connect();
                        connected = true;
                    } catch (Throwable th6) {
                        Log.w("BtRemote", "secure connect failed", th6);
                        try {
                            s.close();
                        } catch (Throwable th7) {
                        }
                        s = null;
                    }
                }
            }
            final BluetoothSocket okSock = s;
            if (connected && okSock != null && !this.done) {
                Handler handler = this.this$0.main;
                final BluetoothRemoteService bluetoothRemoteService = this.this$0;
                handler.post(new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$ConnectThread$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        BluetoothRemoteService.ConnectThread.run$lambda$0(BluetoothRemoteService.this, okSock, this);
                    }
                });
            } else if (!this.done) {
                Handler handler2 = this.this$0.main;
                final BluetoothRemoteService bluetoothRemoteService2 = this.this$0;
                handler2.post(new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$ConnectThread$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        BluetoothRemoteService.ConnectThread.run$lambda$1(BluetoothRemoteService.this);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void run$lambda$0(BluetoothRemoteService this$0, BluetoothSocket $okSock, ConnectThread this$1) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            this$0.onSocketConnected($okSock, this$1.device);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void run$lambda$1(BluetoothRemoteService this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.scheduleDialRetry();
        }

        public final void cancel() {
            this.done = true;
            try {
                BluetoothSocket bluetoothSocket = this.sock;
                if (bluetoothSocket != null) {
                    bluetoothSocket.close();
                }
            } catch (Throwable th) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: BluetoothRemoteService.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u000e\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/xiaofan/bangfan/BluetoothRemoteService$ConnectedThread;", "Ljava/lang/Thread;", "socket", "Landroid/bluetooth/BluetoothSocket;", "(Lcom/xiaofan/bangfan/BluetoothRemoteService;Landroid/bluetooth/BluetoothSocket;)V", "inS", "Ljava/io/InputStream;", "outS", "Ljava/io/OutputStream;", "stopped", "", "writeLock", "", "cancel", "", "run", "write", "bytes", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public final class ConnectedThread extends Thread {
        private final InputStream inS;
        private final OutputStream outS;
        private final BluetoothSocket socket;
        private volatile boolean stopped;
        final /* synthetic */ BluetoothRemoteService this$0;
        private final Object writeLock;

        public ConnectedThread(BluetoothRemoteService this$0, BluetoothSocket socket) {
            Intrinsics.checkNotNullParameter(socket, "socket");
            this.this$0 = this$0;
            this.socket = socket;
            InputStream inputStream = this.socket.getInputStream();
            Intrinsics.checkNotNullExpressionValue(inputStream, "getInputStream(...)");
            this.inS = inputStream;
            OutputStream outputStream = this.socket.getOutputStream();
            Intrinsics.checkNotNullExpressionValue(outputStream, "getOutputStream(...)");
            this.outS = outputStream;
            this.writeLock = new Object();
            setName("xf-bt-connected");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            int n;
            byte[] buf = new byte[64];
            while (!this.stopped) {
                try {
                    n = this.inS.read(buf);
                } catch (Throwable th) {
                    n = -1;
                }
                if (n <= 0) {
                    if (!this.stopped) {
                        this.this$0.connectionLost();
                        return;
                    }
                    return;
                }
                List<Byte> cmds = BtProto.INSTANCE.parse(buf, n);
                for (Byte b : cmds) {
                    byte c = b.byteValue();
                    this.this$0.onIncoming(c);
                }
            }
        }

        public final boolean write(byte[] bytes) {
            boolean z;
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            synchronized (this.writeLock) {
                try {
                    this.outS.write(bytes);
                    this.outS.flush();
                    z = true;
                } catch (Throwable th) {
                    Log.e("BtRemote", "write failed", th);
                    z = false;
                }
            }
            return z;
        }

        public final void cancel() {
            this.stopped = true;
            try {
                this.socket.close();
            } catch (Throwable th) {
            }
        }
    }

    private final void setState(final int s, final String peer) {
        state = s;
        peerName = peer;
        startAsForeground(s, peer);
        final Listener l = listener;
        this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                BluetoothRemoteService.setState$lambda$12(BluetoothRemoteService.Listener.this, s, peer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setState$lambda$12(Listener $l, int $s, String $peer) {
        if ($l != null) {
            $l.onBtState($s, $peer);
        }
    }

    private final void createChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel ch = new NotificationChannel(CHANNEL_ID, "小翻蓝牙遥控", 2);
            ch.setDescription("双机蓝牙翻页遥控");
            Object systemService = getSystemService("notification");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
            ((NotificationManager) systemService).createNotificationChannel(ch);
        }
    }

    private final void startAsForeground(int s, String peer) {
        String text;
        switch (s) {
            case 1:
                text = "正在和另一台小翻建立连接…（两台都点“连接设备”）";
                break;
            case 2:
                text = "正在连接 " + (peer == null ? "对方设备" : peer) + "…";
                break;
            case 3:
                text = "已连接 " + (peer == null ? "对方" : peer) + "：音量-下一页、音量+上一页，熄屏也可遥控";
                break;
            default:
                text = "蓝牙遥控已开启，尚未连接";
                break;
        }
        Notification.Builder builder = Build.VERSION.SDK_INT >= 26 ? new Notification.Builder(this, CHANNEL_ID) : new Notification.Builder(this);
        Notification notif = builder.setContentTitle("小翻蓝牙遥控").setContentText(text).setSmallIcon(17301632).setOngoing(true).build();
        Intrinsics.checkNotNullExpressionValue(notif, "build(...)");
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                startForeground(NOTIF_ID, notif, 16);
            } else {
                startForeground(NOTIF_ID, notif);
            }
        } catch (Throwable th) {
            try {
                startForeground(NOTIF_ID, notif);
            } catch (Throwable th2) {
            }
        }
    }

    private final void vibrate(boolean z) {
        Vibrator v;
        try {
            if (Build.VERSION.SDK_INT >= 31) {
                Object systemService = getSystemService("vibrator_manager");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.os.VibratorManager");
                v = ((VibratorManager) systemService).getDefaultVibrator();
            } else {
                Object systemService2 = getSystemService("vibrator");
                Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.os.Vibrator");
                v = (Vibrator) systemService2;
            }
            Intrinsics.checkNotNull(v);
            long ms = z ? 28L : 60L;
            if (Build.VERSION.SDK_INT < 26) {
                v.vibrate(ms);
            } else {
                v.vibrate(VibrationEffect.createOneShot(ms, -1));
            }
        } catch (Throwable th) {
        }
    }

    private final boolean toast(final String t) {
        return this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                BluetoothRemoteService.toast$lambda$13(BluetoothRemoteService.this, t);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void toast$lambda$13(BluetoothRemoteService this$0, String t) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(t, "$t");
        Toast.makeText(this$0.getApplicationContext(), t, 0).show();
    }
}
