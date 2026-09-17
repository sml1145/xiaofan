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
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.media.VolumeProvider;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.VibratorManager;
import android.provider.Settings;
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
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.UByte;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.webrtc.MediaStreamTrack;
/* compiled from: BluetoothRemoteService.kt */
@Metadata(d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001e\u0018\u0000 k2\u00020\u0001:\u0006jklmnoB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010*\u001a\u00020+H\u0002J\u0010\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020'H\u0002J\b\u0010.\u001a\u00020+H\u0002J\b\u0010/\u001a\u00020\u0017H\u0002J \u00100\u001a\u00020\n2\u0006\u00101\u001a\u00020\b2\u0006\u00102\u001a\u00020\n2\u0006\u00103\u001a\u00020\nH\u0002J\b\u00104\u001a\u00020+H\u0002J\b\u00105\u001a\u00020+H\u0002J\b\u00106\u001a\u00020+H\u0002J\b\u00107\u001a\u00020+H\u0002J\u0010\u00108\u001a\u00020+2\u0006\u0010-\u001a\u00020'H\u0002J\b\u00109\u001a\u00020+H\u0002J\u0010\u0010:\u001a\u00020\u00172\u0006\u0010;\u001a\u00020\u0017H\u0002J\u0012\u0010<\u001a\u00020+2\b\u0010=\u001a\u0004\u0018\u00010>H\u0002J\b\u0010?\u001a\u00020\u0017H\u0002J\u0018\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020\u0017H\u0002J\u0012\u0010E\u001a\u00020'2\b\u0010F\u001a\u0004\u0018\u00010'H\u0002J\u0014\u0010G\u001a\u0004\u0018\u00010H2\b\u0010I\u001a\u0004\u0018\u00010JH\u0016J\b\u0010K\u001a\u00020+H\u0016J\b\u0010L\u001a\u00020+H\u0016J\u0010\u0010M\u001a\u00020+2\u0006\u0010N\u001a\u00020OH\u0002J\u001a\u0010P\u001a\u00020+2\u0006\u0010B\u001a\u00020C2\b\u0010Q\u001a\u0004\u0018\u00010RH\u0002J\"\u0010S\u001a\u00020\n2\b\u0010I\u001a\u0004\u0018\u00010J2\u0006\u0010T\u001a\u00020\n2\u0006\u0010U\u001a\u00020\nH\u0016J\b\u0010V\u001a\u00020+H\u0002J\b\u0010W\u001a\u00020\u0015H\u0002J\u0014\u0010X\u001a\u0004\u0018\u00010'2\b\u0010Y\u001a\u0004\u0018\u00010RH\u0002J\u0010\u0010Z\u001a\u00020+2\u0006\u0010[\u001a\u00020\u0015H\u0002J\b\u0010\\\u001a\u00020+H\u0002J\u001a\u0010]\u001a\u00020+2\u0006\u0010^\u001a\u00020\n2\b\u0010Q\u001a\u0004\u0018\u00010'H\u0002J\b\u0010_\u001a\u00020+H\u0002J\u001a\u0010`\u001a\u00020+2\u0006\u0010^\u001a\u00020\n2\b\u0010Q\u001a\u0004\u0018\u00010'H\u0002J\b\u0010a\u001a\u00020+H\u0002J\b\u0010b\u001a\u00020+H\u0002J\b\u0010c\u001a\u00020+H\u0002J\b\u0010d\u001a\u00020+H\u0002J\b\u0010e\u001a\u00020+H\u0002J\u0010\u0010f\u001a\u00020\u00172\u0006\u0010g\u001a\u00020'H\u0002J\u0010\u0010h\u001a\u00020+2\u0006\u0010i\u001a\u00020\u0017H\u0002R\u0014\u0010\u0003\u001a\b\u0018\u00010\u0004R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0018\u00010\u000eR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0018\u00010\u0010R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0018\u00010\u0019R\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006p"}, d2 = {"Lcom/xiaofan/bangfan/BluetoothRemoteService;", "Landroid/app/Service;", "()V", "acceptThread", "Lcom/xiaofan/bangfan/BluetoothRemoteService$AcceptThread;", "adapter", "Landroid/bluetooth/BluetoothAdapter;", MediaStreamTrack.AUDIO_TRACK_KIND, "Landroid/media/AudioManager;", "baseMusic", "", "baseRing", "baseRingerMode", "connectThread", "Lcom/xiaofan/bangfan/BluetoothRemoteService$ConnectThread;", "connectedThread", "Lcom/xiaofan/bangfan/BluetoothRemoteService$ConnectedThread;", "dialAttempts", "dialRunnable", "Ljava/lang/Runnable;", "lastVolAt", "", "lastVolIsUp", "", "linkWakeLock", "Landroid/os/PowerManager$WakeLock;", "Landroid/os/PowerManager;", "main", "Landroid/os/Handler;", "mediaSession", "Landroid/media/session/MediaSession;", "nodeId", "rng", "Ljava/util/Random;", "settingsHandler", "settingsThread", "Landroid/os/HandlerThread;", "suppressDial", "targetMac", "", "volumeObserver", "Landroid/database/ContentObserver;", "acquireLinkWakeLock", "", "attemptConnect", "mac", "becomeListenerOnly", "btReady", "clampVolume", "am", "stream", "v", "closeAllThreads", "connectionLost", "createChannel", "doListenOnly", "doPair", "ensureDialing", "handleVolume", "isUp", "handleVolumeSettingChanged", "uri", "Landroid/net/Uri;", "hasConnectPerm", "negotiate", "Lcom/xiaofan/bangfan/BluetoothRemoteService$Nego;", "socket", "Landroid/bluetooth/BluetoothSocket;", "outbound", "norm", "m", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onIncoming", "b", "", "onSocketConnected", "peer", "Landroid/bluetooth/BluetoothDevice;", "onStartCommand", "flags", "startId", "releaseLinkWakeLock", "resolveNodeId", "safeName", "d", "scheduleDial", "delayMs", "scheduleDialRetry", "setState", "s", "snapshotVolumeBaseline", "startAsForeground", "startMediaSessionKeys", "startVolumeWatcher", "stopEverything", "stopMediaSessionKeys", "stopVolumeWatcher", "toast", "t", "vibrate", "short", "AcceptThread", "Companion", "ConnectThread", "ConnectedThread", "Listener", "Nego", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class BluetoothRemoteService extends Service {
    public static final String ACTION_CONNECT = "com.xiaofan.bangfan.action.BT_CONNECT";
    public static final String ACTION_DISCONNECT = "com.xiaofan.bangfan.action.BT_DISCONNECT";
    public static final String ACTION_LISTEN = "com.xiaofan.bangfan.action.BT_LISTEN";
    public static final String ACTION_PAIR = "com.xiaofan.bangfan.action.BT_PAIR";
    public static final String ACTION_RELOAD_VOLUME_CHANNEL = "com.xiaofan.bangfan.action.BT_VOL_CHAN";
    public static final String ACTION_STOP = "com.xiaofan.bangfan.action.BT_STOP";
    private static final String CHANNEL_ID = "xiaofan_bt_remote";
    public static final Companion Companion = new Companion(null);
    private static final long DIAL_BASE_MS = 600;
    private static final long DIAL_JITTER_MS = 700;
    public static final String EXTRA_MAC = "bt_mac";
    private static final int HELLO_LEN = 9;
    private static final byte HELLO_MAGIC = -91;
    private static final long HELLO_TIMEOUT_MS = 2000;
    private static final int MAX_DIAL_ATTEMPTS = 14;
    private static final long MAX_LINK_WAKE_MS = 86400000;
    private static final int NOTIF_ID = 2007;
    private static final String SPP_NAME = "XiaoFanRemote";
    private static final UUID SPP_UUID;
    public static final int STATE_CONNECTED = 3;
    public static final int STATE_CONNECTING = 2;
    public static final int STATE_IDLE = 0;
    public static final int STATE_LISTENING = 1;
    private static final String VOLUME_MUSIC_KEY = "volume_music";
    private static final String VOLUME_RING_KEY = "volume_ring";
    private static final long VOL_DEBOUNCE_MS = 280;
    private static final long VOL_DEBOUNCE_VIDEO_MS = 120;
    private static volatile BluetoothRemoteService instance;
    private static volatile Listener listener;
    private static volatile String peerName;
    private static volatile int state;
    private AcceptThread acceptThread;
    private BluetoothAdapter adapter;
    private AudioManager audio;
    private ConnectThread connectThread;
    private ConnectedThread connectedThread;
    private volatile int dialAttempts;
    private volatile long lastVolAt;
    private volatile boolean lastVolIsUp;
    private PowerManager.WakeLock linkWakeLock;
    private MediaSession mediaSession;
    private volatile long nodeId;
    private Handler settingsHandler;
    private HandlerThread settingsThread;
    private volatile boolean suppressDial;
    private volatile String targetMac;
    private ContentObserver volumeObserver;
    private final Handler main = new Handler(Looper.getMainLooper());
    private final Random rng = new Random();
    private volatile int baseMusic = -1;
    private volatile int baseRing = -1;
    private volatile int baseRingerMode = 2;
    private final Runnable dialRunnable = new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$$ExternalSyntheticLambda7
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

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: BluetoothRemoteService.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/xiaofan/bangfan/BluetoothRemoteService$Nego;", "", "(Ljava/lang/String;I)V", "KEEPER", "REJECT", "FAIL", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public enum Nego {
        KEEPER,
        REJECT,
        FAIL;
        
        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<Nego> getEntries() {
            return $ENTRIES;
        }
    }

    /* compiled from: BluetoothRemoteService.kt */
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-J\u0006\u0010.\u001a\u00020/J\u0006\u00100\u001a\u00020+J\u000e\u00101\u001a\u00020/2\u0006\u00102\u001a\u00020/J\u0016\u00103\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u00104\u001a\u00020\u0004J\b\u00105\u001a\u0004\u0018\u00010\u0004J\u0010\u00106\u001a\u00020+2\b\u00107\u001a\u0004\u0018\u00010'J\u000e\u00108\u001a\u00020+2\u0006\u0010,\u001a\u00020-J\u0006\u00109\u001a\u00020\u0010J\u000e\u0010:\u001a\u00020+2\u0006\u0010,\u001a\u00020-J\u0006\u0010;\u001a\u00020/R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lcom/xiaofan/bangfan/BluetoothRemoteService$Companion;", "", "()V", "ACTION_CONNECT", "", "ACTION_DISCONNECT", "ACTION_LISTEN", "ACTION_PAIR", "ACTION_RELOAD_VOLUME_CHANNEL", "ACTION_STOP", "CHANNEL_ID", "DIAL_BASE_MS", "", "DIAL_JITTER_MS", "EXTRA_MAC", "HELLO_LEN", "", "HELLO_MAGIC", "", "HELLO_TIMEOUT_MS", "MAX_DIAL_ATTEMPTS", "MAX_LINK_WAKE_MS", "NOTIF_ID", "SPP_NAME", "SPP_UUID", "Ljava/util/UUID;", "getSPP_UUID", "()Ljava/util/UUID;", "STATE_CONNECTED", "STATE_CONNECTING", "STATE_IDLE", "STATE_LISTENING", "VOLUME_MUSIC_KEY", "VOLUME_RING_KEY", "VOL_DEBOUNCE_MS", "VOL_DEBOUNCE_VIDEO_MS", "instance", "Lcom/xiaofan/bangfan/BluetoothRemoteService;", "listener", "Lcom/xiaofan/bangfan/BluetoothRemoteService$Listener;", "peerName", "state", "disconnect", "", "ctx", "Landroid/content/Context;", "isConnected", "", "onModeChanged", "onVolumeKey", "isUp", "pair", "mac", "peerNow", "setListener", "l", "startListen", "stateNow", "stop", "volumeRemoteActive", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
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

        public final void onModeChanged() {
            final BluetoothRemoteService s = BluetoothRemoteService.instance;
            if (s == null) {
                return;
            }
            s.main.post(new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$Companion$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    BluetoothRemoteService.Companion.onModeChanged$lambda$0(BluetoothRemoteService.this);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onModeChanged$lambda$0(BluetoothRemoteService s) {
            Intrinsics.checkNotNullParameter(s, "$s");
            try {
                s.startAsForeground(BluetoothRemoteService.state, BluetoothRemoteService.peerName);
            } catch (Throwable th) {
            }
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
        Object systemService2 = getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        this.audio = systemService2 instanceof AudioManager ? (AudioManager) systemService2 : null;
        this.nodeId = resolveNodeId();
        createChannel();
        startAsForeground(0, null);
        startVolumeWatcher();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001c A[Catch: all -> 0x002a, TRY_LEAVE, TryCatch #0 {all -> 0x002a, blocks: (B:2:0x0001, B:4:0x0010, B:10:0x001c), top: B:15:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final long resolveNodeId() {
        /*
            r3 = this;
            android.content.ContentResolver r0 = r3.getContentResolver()     // Catch: java.lang.Throwable -> L2a
            java.lang.String r1 = "android_id"
            java.lang.String r0 = android.provider.Settings.Secure.getString(r0, r1)     // Catch: java.lang.Throwable -> L2a
            r1 = r0
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch: java.lang.Throwable -> L2a
            if (r1 == 0) goto L19
            boolean r1 = kotlin.text.StringsKt.isBlank(r1)     // Catch: java.lang.Throwable -> L2a
            if (r1 == 0) goto L17
            goto L19
        L17:
            r1 = 0
            goto L1a
        L19:
            r1 = 1
        L1a:
            if (r1 != 0) goto L2b
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch: java.lang.Throwable -> L2a
            r1 = 16
            java.lang.String r2 = kotlin.text.StringsKt.take(r0, r1)     // Catch: java.lang.Throwable -> L2a
            long r1 = com.xiaofan.bangfan.BluetoothRemoteService$$ExternalSyntheticBackport1.m(r2, r1)     // Catch: java.lang.Throwable -> L2a
            return r1
        L2a:
            r0 = move-exception
        L2b:
            java.util.Random r0 = r3.rng
            long r0 = r0.nextLong()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.BluetoothRemoteService.resolveNodeId():long");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x003f, code lost:
        if (r0.equals(com.xiaofan.bangfan.BluetoothRemoteService.ACTION_STOP) == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0079, code lost:
        if (r0.equals(com.xiaofan.bangfan.BluetoothRemoteService.ACTION_DISCONNECT) == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x007c, code lost:
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
            if (r0 == 0) goto L7f
            int r2 = r0.hashCode()
            switch(r2) {
                case -1943442613: goto L73;
                case -1371351050: goto L66;
                case 593096905: goto L42;
                case 593204721: goto L39;
                case 1774581197: goto L14;
                default: goto L12;
            }
        L12:
            goto L7f
        L14:
            java.lang.String r2 = "com.xiaofan.bangfan.action.BT_VOL_CHAN"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L1e
            goto L7f
        L1e:
            int r0 = com.xiaofan.bangfan.BluetoothRemoteService.state
            r2 = 3
            if (r0 != r2) goto L35
            com.xiaofan.bangfan.AppPrefs r0 = com.xiaofan.bangfan.AppPrefs.INSTANCE
            r2 = r3
            android.content.Context r2 = (android.content.Context) r2
            boolean r0 = r0.btVolumeRemote(r2)
            if (r0 == 0) goto L35
            r3.snapshotVolumeBaseline()
            r3.startMediaSessionKeys()
            goto L7f
        L35:
            r3.stopMediaSessionKeys()
            goto L7f
        L39:
            java.lang.String r2 = "com.xiaofan.bangfan.action.BT_STOP"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L7c
            goto L7f
        L42:
            java.lang.String r2 = "com.xiaofan.bangfan.action.BT_PAIR"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L4b
            goto L7f
        L4b:
            java.lang.String r0 = "bt_mac"
            java.lang.String r0 = r4.getStringExtra(r0)
            r2 = r0
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            if (r2 == 0) goto L5f
            boolean r2 = kotlin.text.StringsKt.isBlank(r2)
            if (r2 == 0) goto L5d
            goto L5f
        L5d:
            r2 = 0
            goto L60
        L5f:
            r2 = r1
        L60:
            if (r2 != 0) goto L7f
            r3.doPair(r0)
            goto L7f
        L66:
            java.lang.String r2 = "com.xiaofan.bangfan.action.BT_LISTEN"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L6f
            goto L7f
        L6f:
            r3.doListenOnly()
            goto L7f
        L73:
            java.lang.String r2 = "com.xiaofan.bangfan.action.BT_DISCONNECT"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L7c
            goto L7f
        L7c:
            r3.stopEverything()
        L7f:
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
            this.suppressDial = false;
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

    /* JADX WARN: Removed duplicated region for block: B:31:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0057 A[EXC_TOP_SPLITTER, SYNTHETIC] */
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
            boolean r0 = r10.suppressDial
            if (r0 == 0) goto L12
            java.lang.String r0 = "BtRemote"
            java.lang.String r1 = "suppress dial (listener role)"
            android.util.Log.i(r0, r1)
            return
        L12:
            android.bluetooth.BluetoothAdapter r0 = r10.adapter
            if (r0 != 0) goto L17
            return
        L17:
            r1 = 0
            java.util.Set r2 = r0.getBondedDevices()     // Catch: java.lang.Throwable -> L4d
            if (r2 == 0) goto L48
            java.lang.Iterable r2 = (java.lang.Iterable) r2     // Catch: java.lang.Throwable -> L4d
            r3 = 0
            java.util.Iterator r4 = r2.iterator()     // Catch: java.lang.Throwable -> L4d
        L26:
            boolean r5 = r4.hasNext()     // Catch: java.lang.Throwable -> L4d
            if (r5 == 0) goto L40
            java.lang.Object r5 = r4.next()     // Catch: java.lang.Throwable -> L4d
            r6 = r5
            android.bluetooth.BluetoothDevice r6 = (android.bluetooth.BluetoothDevice) r6     // Catch: java.lang.Throwable -> L4d
            r7 = 0
            java.lang.String r8 = r6.getAddress()     // Catch: java.lang.Throwable -> L4d
            r9 = 1
            boolean r8 = kotlin.text.StringsKt.equals(r8, r11, r9)     // Catch: java.lang.Throwable -> L4d
            if (r8 == 0) goto L26
            goto L41
        L40:
            r5 = r1
        L41:
            android.bluetooth.BluetoothDevice r5 = (android.bluetooth.BluetoothDevice) r5     // Catch: java.lang.Throwable -> L4d
            if (r5 != 0) goto L46
            goto L48
        L46:
            r1 = r5
            goto L4e
        L48:
            android.bluetooth.BluetoothDevice r1 = r0.getRemoteDevice(r11)     // Catch: java.lang.Throwable -> L4d
            goto L4e
        L4d:
            r2 = move-exception
        L4e:
            if (r1 != 0) goto L57
            java.lang.String r2 = "找不到这台已配对设备"
            r10.toast(r2)
            return
        L57:
            com.xiaofan.bangfan.BluetoothRemoteService$ConnectThread r2 = r10.connectThread     // Catch: java.lang.Throwable -> L5f
            if (r2 == 0) goto L60
            r2.cancel()     // Catch: java.lang.Throwable -> L5f
            goto L60
        L5f:
            r2 = move-exception
        L60:
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
        try {
            BluetoothAdapter bluetoothAdapter = this.adapter;
            if (bluetoothAdapter != null) {
                bluetoothAdapter.cancelDiscovery();
            }
        } catch (Throwable th6) {
        }
        setState(3, safeName(peer));
        acquireLinkWakeLock();
        snapshotVolumeBaseline();
        startMediaSessionKeys();
        this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$$ExternalSyntheticLambda9
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
        this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$$ExternalSyntheticLambda10
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
        this$0.releaseLinkWakeLock();
        this$0.closeAllThreads();
        String mac = this$0.targetMac;
        if (mac == null) {
            this$0.setState(0, null);
            return;
        }
        this$0.dialAttempts = 0;
        this$0.suppressDial = false;
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
        stopMediaSessionKeys();
    }

    private final void stopEverything() {
        this.main.removeCallbacks(this.dialRunnable);
        closeAllThreads();
        releaseLinkWakeLock();
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
        releaseLinkWakeLock();
        stopVolumeWatcher();
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

    private final void acquireLinkWakeLock() {
        PowerManager.WakeLock wakeLock;
        PowerManager.WakeLock $this$acquireLinkWakeLock_u24lambda_u2410;
        try {
            boolean z = true;
            if (this.linkWakeLock == null) {
                Object systemService = getSystemService("power");
                PowerManager.WakeLock $this$acquireLinkWakeLock_u24lambda_u24102 = null;
                PowerManager pm = systemService instanceof PowerManager ? (PowerManager) systemService : null;
                if (pm != null && ($this$acquireLinkWakeLock_u24lambda_u2410 = pm.newWakeLock(1, "xiaofan:bt-remote")) != null) {
                    $this$acquireLinkWakeLock_u24lambda_u2410.setReferenceCounted(false);
                    $this$acquireLinkWakeLock_u24lambda_u24102 = $this$acquireLinkWakeLock_u24lambda_u2410;
                }
                this.linkWakeLock = $this$acquireLinkWakeLock_u24lambda_u24102;
            }
            PowerManager.WakeLock wakeLock2 = this.linkWakeLock;
            if (wakeLock2 == null || !wakeLock2.isHeld()) {
                z = false;
            }
            if (!z && (wakeLock = this.linkWakeLock) != null) {
                wakeLock.acquire(MAX_LINK_WAKE_MS);
            }
            Log.i("BtRemote", "link wakelock acquired");
        } catch (Throwable th) {
            Log.w("BtRemote", "acquire wakelock failed", th);
        }
    }

    private final void releaseLinkWakeLock() {
        PowerManager.WakeLock wakeLock;
        try {
            PowerManager.WakeLock wakeLock2 = this.linkWakeLock;
            boolean z = false;
            if (wakeLock2 != null && wakeLock2.isHeld()) {
                z = true;
            }
            if (!z || (wakeLock = this.linkWakeLock) == null) {
                return;
            }
            wakeLock.release();
        } catch (Throwable th) {
        }
    }

    private final void startVolumeWatcher() {
        try {
            HandlerThread th = new HandlerThread("xf-volume-observer");
            th.start();
            this.settingsThread = th;
            final Handler h = new Handler(th.getLooper());
            this.settingsHandler = h;
            ContentObserver contentObserver = new ContentObserver(h) { // from class: com.xiaofan.bangfan.BluetoothRemoteService$startVolumeWatcher$obs$1
                @Override // android.database.ContentObserver
                public void onChange(boolean selfChange, Uri uri) {
                    this.handleVolumeSettingChanged(uri);
                }
            };
            this.volumeObserver = contentObserver;
            ContentResolver r = getContentResolver();
            r.registerContentObserver(Settings.System.getUriFor(VOLUME_MUSIC_KEY), false, contentObserver);
            r.registerContentObserver(Settings.System.getUriFor(VOLUME_RING_KEY), false, contentObserver);
        } catch (Throwable th2) {
            Log.w("BtRemote", "volume watcher start failed", th2);
        }
    }

    private final void snapshotVolumeBaseline() {
        AudioManager am = this.audio;
        if (am == null) {
            return;
        }
        try {
            this.baseRingerMode = am.getRingerMode();
            this.baseMusic = clampVolume(am, 3, am.getStreamVolume(3));
            this.baseRing = clampVolume(am, 2, am.getStreamVolume(2));
            int i = this.baseMusic;
            int i2 = this.baseRing;
            Log.i("BtRemote", "volume baseline music=" + i + " ring=" + i2 + " ringer=" + this.baseRingerMode);
        } catch (Throwable th) {
            Log.w("BtRemote", "snapshot baseline failed", th);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int clampVolume(android.media.AudioManager r4, int r5, int r6) {
        /*
            r3 = this;
            int r0 = r4.getStreamMaxVolume(r5)
            r1 = 1
            if (r0 > r1) goto L9
            goto L12
        L9:
            if (r6 > 0) goto Lc
            goto L13
        Lc:
            if (r6 < r0) goto L11
            int r1 = r0 + (-1)
            goto L13
        L11:
        L12:
            r1 = r6
        L13:
            if (r1 == r6) goto L1c
            r2 = 0
            r4.setStreamVolume(r5, r1, r2)     // Catch: java.lang.Throwable -> L1b
            goto L1c
        L1b:
            r2 = move-exception
        L1c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.BluetoothRemoteService.clampVolume(android.media.AudioManager, int, int):int");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleVolumeSettingChanged(Uri uri) {
        AudioManager am;
        String name;
        Pair pair;
        int cur;
        if (state != 3 || !AppPrefs.INSTANCE.btVolumeRemote(this) || (am = this.audio) == null || uri == null || (name = uri.getLastPathSegment()) == null) {
            return;
        }
        if (Intrinsics.areEqual(name, VOLUME_MUSIC_KEY)) {
            pair = TuplesKt.to(3, Integer.valueOf(this.baseMusic));
        } else if (!Intrinsics.areEqual(name, VOLUME_RING_KEY)) {
            return;
        } else {
            pair = TuplesKt.to(2, Integer.valueOf(this.baseRing));
        }
        int stream = ((Number) pair.component1()).intValue();
        int base = ((Number) pair.component2()).intValue();
        if (base < 0) {
            return;
        }
        try {
            cur = Settings.System.getInt(getContentResolver(), name);
        } catch (Throwable th) {
            cur = base;
        }
        int dir = VolumeKeyGate.INSTANCE.volumeChangeDirection(cur, base);
        if (dir == 0) {
            return;
        }
        boolean isUp = dir > 0;
        Log.i("BtRemote", "screen-off volume key: stream=" + stream + " cur=" + cur + " base=" + base + " up=" + isUp);
        handleVolume(isUp);
        try {
            am.setStreamVolume(stream, base, 0);
        } catch (Throwable th2) {
            Log.w("BtRemote", "restore volume failed", th2);
        }
        if (stream == 2) {
            try {
                if (am.getRingerMode() != this.baseRingerMode) {
                    am.setRingerMode(this.baseRingerMode);
                }
            } catch (Throwable th3) {
            }
        }
    }

    private final void stopVolumeWatcher() {
        try {
            ContentObserver it = this.volumeObserver;
            if (it != null) {
                getContentResolver().unregisterContentObserver(it);
            }
        } catch (Throwable th) {
        }
        this.volumeObserver = null;
        try {
            HandlerThread handlerThread = this.settingsThread;
            if (handlerThread != null) {
                handlerThread.quitSafely();
            }
        } catch (Throwable th2) {
        }
        this.settingsThread = null;
        this.settingsHandler = null;
        this.baseMusic = -1;
        this.baseRing = -1;
    }

    private final void startMediaSessionKeys() {
        stopMediaSessionKeys();
        try {
            MediaSession session = new MediaSession(getApplicationContext(), "xiaofan-remote");
            session.setFlags(3);
            session.setPlaybackToRemote(new VolumeProvider() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$startMediaSessionKeys$provider$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1, 15, 7);
                }

                @Override // android.media.VolumeProvider
                public void onSetVolumeTo(int volume) {
                }

                @Override // android.media.VolumeProvider
                public void onAdjustVolume(int direction) {
                    if (direction != 0 && BluetoothRemoteService.state == 3 && AppPrefs.INSTANCE.btVolumeRemote(BluetoothRemoteService.this)) {
                        Log.i("BtRemote", "media session volume key up=" + (direction > 0));
                        BluetoothRemoteService.this.handleVolume(direction > 0);
                    }
                }
            });
            PlaybackState state2 = new PlaybackState.Builder().setActions(518L).setState(3, 0L, 1.0f).build();
            session.setPlaybackState(state2);
            session.setActive(true);
            this.mediaSession = session;
            Log.i("BtRemote", "media session volume channel active");
        } catch (Throwable th) {
            Log.w("BtRemote", "media session start failed", th);
        }
    }

    private final void stopMediaSessionKeys() {
        try {
            MediaSession $this$stopMediaSessionKeys_u24lambda_u2413 = this.mediaSession;
            if ($this$stopMediaSessionKeys_u24lambda_u2413 != null) {
                $this$stopMediaSessionKeys_u24lambda_u2413.setActive(false);
                $this$stopMediaSessionKeys_u24lambda_u2413.release();
            }
        } catch (Throwable th) {
        }
        this.mediaSession = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean handleVolume(boolean isUp) {
        if (AppPrefs.INSTANCE.btVolumeRemote(this)) {
            byte cmd = 3;
            if (state != 3) {
                return false;
            }
            boolean videoMode = Intrinsics.areEqual(AppPrefs.INSTANCE.btMode(this), "video");
            long debounce = videoMode ? VOL_DEBOUNCE_VIDEO_MS : VOL_DEBOUNCE_MS;
            long now = SystemClock.uptimeMillis();
            if (isUp != this.lastVolIsUp || now - this.lastVolAt >= debounce) {
                this.lastVolAt = now;
                this.lastVolIsUp = isUp;
                acquireLinkWakeLock();
                if (videoMode) {
                    if (isUp) {
                        cmd = 4;
                    }
                } else {
                    cmd = isUp ? (byte) 2 : (byte) 1;
                }
                ConnectedThread connectedThread = this.connectedThread;
                boolean ok = connectedThread != null ? connectedThread.write(new byte[]{cmd}) : false;
                Log.i("BtRemote", "volume key -> cmd=" + (cmd & UByte.MAX_VALUE) + " video=" + videoMode + " up=" + isUp + " write=" + ok + " t=" + SystemClock.uptimeMillis());
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
                if (b != 2) {
                    if (b == 3) {
                        Log.i("BtRemote", "recv SWIPE_UP t=" + SystemClock.uptimeMillis());
                        final boolean ok = TurnManager.INSTANCE.requestSwipeUp(this, "蓝牙遥控-上滑");
                        if (AppPrefs.INSTANCE.btRecvTip(this)) {
                            this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$$ExternalSyntheticLambda4
                                @Override // java.lang.Runnable
                                public final void run() {
                                    BluetoothRemoteService.onIncoming$lambda$16(BluetoothRemoteService.this, ok);
                                }
                            });
                            return;
                        }
                        return;
                    } else if (b == 4) {
                        Log.i("BtRemote", "recv SWIPE_DOWN t=" + SystemClock.uptimeMillis());
                        final boolean ok2 = TurnManager.INSTANCE.requestSwipeDown(this, "蓝牙遥控-下滑");
                        if (AppPrefs.INSTANCE.btRecvTip(this)) {
                            this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$$ExternalSyntheticLambda5
                                @Override // java.lang.Runnable
                                public final void run() {
                                    BluetoothRemoteService.onIncoming$lambda$17(BluetoothRemoteService.this, ok2);
                                }
                            });
                            return;
                        }
                        return;
                    } else {
                        return;
                    }
                }
                this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$$ExternalSyntheticLambda12
                    @Override // java.lang.Runnable
                    public final void run() {
                        BluetoothRemoteService.onIncoming$lambda$15(BluetoothRemoteService.this);
                    }
                });
                return;
            }
            this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$$ExternalSyntheticLambda11
                @Override // java.lang.Runnable
                public final void run() {
                    BluetoothRemoteService.onIncoming$lambda$14(BluetoothRemoteService.this);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onIncoming$lambda$14(BluetoothRemoteService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        boolean ok = TurnManager.INSTANCE.requestTurn(this$0, "蓝牙遥控-下一页");
        if (AppPrefs.INSTANCE.btRecvTip(this$0)) {
            XiaoFanVoice.INSTANCE.tip(this$0, ok ? "对方让我翻下一页" : "翻页没成功，检查无障碍");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onIncoming$lambda$15(BluetoothRemoteService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        boolean ok = TurnManager.INSTANCE.requestPrev(this$0, "蓝牙遥控-上一页");
        if (AppPrefs.INSTANCE.btRecvTip(this$0)) {
            XiaoFanVoice.INSTANCE.tip(this$0, ok ? "对方让我回上一页" : "回退没成功，检查无障碍");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onIncoming$lambda$16(BluetoothRemoteService this$0, boolean $ok) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XiaoFanVoice.INSTANCE.tip(this$0, $ok ? "对方让我上滑" : "上滑没成功，检查无障碍");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onIncoming$lambda$17(BluetoothRemoteService this$0, boolean $ok) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XiaoFanVoice.INSTANCE.tip(this$0, $ok ? "对方让我下滑" : "下滑没成功，检查无障碍");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v5, types: [com.xiaofan.bangfan.BluetoothRemoteService$Nego] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    public final Nego negotiate(BluetoothSocket socket, boolean outbound) {
        int i;
        String str = "BtRemote";
        try {
            OutputStream out = socket.getOutputStream();
            InputStream input = socket.getInputStream();
            try {
                long my = this.nodeId;
                byte[] it = new byte[9];
                it[0] = HELLO_MAGIC;
                int i2 = 0;
                while (true) {
                    if (i2 >= 8) {
                        break;
                    }
                    it[i2 + 1] = (byte) (my >>> (56 - (i2 * 8)));
                    i2++;
                }
                out.write(it);
                out.flush();
                byte[] buf = new byte[9];
                long deadline = SystemClock.uptimeMillis() + HELLO_TIMEOUT_MS;
                int got = 0;
                while (got < 9) {
                    if (SystemClock.uptimeMillis() > deadline) {
                        return Nego.FAIL;
                    }
                    int av = input.available();
                    if (av > 0) {
                        int r = input.read(buf, got, 9 - got);
                        if (r < 0) {
                            return Nego.FAIL;
                        }
                        got += r;
                    } else {
                        SystemClock.sleep(15L);
                    }
                }
                if (buf[0] != -91) {
                    return Nego.FAIL;
                }
                int i3 = 0;
                long peer = 0;
                for (i = 8; i3 < i; i = 8) {
                    peer = (peer << i) | (buf[i3 + 1] & 255);
                    i3++;
                }
                long peer2 = peer;
                boolean keeper = BtLinkRole.INSTANCE.keep(my, peer, outbound);
                try {
                    Log.i("BtRemote", "hello outbound=" + outbound + " my=" + my + " peer=" + peer2 + " keeper=" + keeper);
                    str = keeper ? Nego.KEEPER : Nego.REJECT;
                    return str;
                } catch (Throwable th) {
                    th = th;
                    Log.w(str, "negotiate failed", th);
                    return Nego.FAIL;
                }
            } catch (Throwable th2) {
                th = th2;
                Log.w(str, "negotiate failed", th);
                return Nego.FAIL;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void becomeListenerOnly() {
        if (state == 3) {
            return;
        }
        this.suppressDial = true;
        this.main.removeCallbacks(this.dialRunnable);
        try {
            ConnectThread connectThread = this.connectThread;
            if (connectThread != null) {
                connectThread.cancel();
            }
        } catch (Throwable th) {
        }
        this.connectThread = null;
        if (this.acceptThread == null) {
            AcceptThread it = new AcceptThread();
            it.start();
            this.acceptThread = it;
        }
        setState(1, peerName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void ensureDialing() {
        if (state != 3 && !this.suppressDial && this.connectThread == null) {
            if (this.dialAttempts > 14) {
                this.dialAttempts = 0;
            }
            scheduleDial(this.rng.nextInt(400) + 250);
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

        /* compiled from: BluetoothRemoteService.kt */
        @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        /* loaded from: classes4.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Nego.values().length];
                try {
                    iArr[Nego.KEEPER.ordinal()] = 1;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[Nego.REJECT.ordinal()] = 2;
                } catch (NoSuchFieldError e2) {
                }
                try {
                    iArr[Nego.FAIL.ordinal()] = 3;
                } catch (NoSuchFieldError e3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public AcceptThread() {
            setName("xf-bt-accept");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            final BluetoothSocket s;
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
            while (!this.stopped) {
                try {
                    BluetoothServerSocket bluetoothServerSocket3 = this.primary;
                    if (bluetoothServerSocket3 == null || (s = bluetoothServerSocket3.accept()) == null) {
                        BluetoothServerSocket bluetoothServerSocket4 = this.fallback;
                        s = bluetoothServerSocket4 != null ? bluetoothServerSocket4.accept() : null;
                    }
                } catch (Throwable th3) {
                    s = null;
                }
                if (s != null) {
                    Nego nego = BluetoothRemoteService.this.negotiate(s, false);
                    switch (WhenMappings.$EnumSwitchMapping$0[nego.ordinal()]) {
                        case 1:
                            Handler handler = BluetoothRemoteService.this.main;
                            final BluetoothRemoteService bluetoothRemoteService = BluetoothRemoteService.this;
                            handler.post(new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$AcceptThread$$ExternalSyntheticLambda0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    BluetoothRemoteService.AcceptThread.run$lambda$0(BluetoothRemoteService.this, s);
                                }
                            });
                            return;
                        case 2:
                            try {
                                s.close();
                            } catch (Throwable th4) {
                            }
                            Handler handler2 = BluetoothRemoteService.this.main;
                            final BluetoothRemoteService bluetoothRemoteService2 = BluetoothRemoteService.this;
                            handler2.post(new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$AcceptThread$$ExternalSyntheticLambda1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    BluetoothRemoteService.AcceptThread.run$lambda$1(BluetoothRemoteService.this);
                                }
                            });
                            continue;
                        case 3:
                            try {
                                s.close();
                                continue;
                            } catch (Throwable th5) {
                                break;
                            }
                    }
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
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void run$lambda$0(BluetoothRemoteService this$0, BluetoothSocket $s) {
            BluetoothDevice bluetoothDevice;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            try {
                bluetoothDevice = $s.getRemoteDevice();
            } catch (Throwable th) {
                bluetoothDevice = null;
            }
            this$0.onSocketConnected($s, bluetoothDevice);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void run$lambda$1(BluetoothRemoteService this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.ensureDialing();
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

        /* compiled from: BluetoothRemoteService.kt */
        @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        /* loaded from: classes4.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Nego.values().length];
                try {
                    iArr[Nego.KEEPER.ordinal()] = 1;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[Nego.REJECT.ordinal()] = 2;
                } catch (NoSuchFieldError e2) {
                }
                try {
                    iArr[Nego.FAIL.ordinal()] = 3;
                } catch (NoSuchFieldError e3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

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
                switch (WhenMappings.$EnumSwitchMapping$0[this.this$0.negotiate(okSock, true).ordinal()]) {
                    case 1:
                        Handler handler = this.this$0.main;
                        final BluetoothRemoteService bluetoothRemoteService = this.this$0;
                        handler.post(new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$ConnectThread$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                BluetoothRemoteService.ConnectThread.run$lambda$0(BluetoothRemoteService.this, okSock, this);
                            }
                        });
                        return;
                    case 2:
                        try {
                            okSock.close();
                        } catch (Throwable th8) {
                        }
                        Handler handler2 = this.this$0.main;
                        final BluetoothRemoteService bluetoothRemoteService2 = this.this$0;
                        handler2.post(new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$ConnectThread$$ExternalSyntheticLambda1
                            @Override // java.lang.Runnable
                            public final void run() {
                                BluetoothRemoteService.ConnectThread.run$lambda$1(BluetoothRemoteService.this);
                            }
                        });
                        return;
                    case 3:
                        try {
                            okSock.close();
                        } catch (Throwable th9) {
                        }
                        Handler handler3 = this.this$0.main;
                        final BluetoothRemoteService bluetoothRemoteService3 = this.this$0;
                        handler3.post(new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$ConnectThread$$ExternalSyntheticLambda2
                            @Override // java.lang.Runnable
                            public final void run() {
                                BluetoothRemoteService.ConnectThread.run$lambda$2(BluetoothRemoteService.this);
                            }
                        });
                        return;
                    default:
                        return;
                }
            } else if (!this.done) {
                Handler handler4 = this.this$0.main;
                final BluetoothRemoteService bluetoothRemoteService4 = this.this$0;
                handler4.post(new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$ConnectThread$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        BluetoothRemoteService.ConnectThread.run$lambda$3(BluetoothRemoteService.this);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void run$lambda$0(BluetoothRemoteService this$0, BluetoothSocket $okSock, ConnectThread this$1) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            this$0.connectThread = null;
            this$0.onSocketConnected($okSock, this$1.device);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void run$lambda$1(BluetoothRemoteService this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.connectThread = null;
            this$0.becomeListenerOnly();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void run$lambda$2(BluetoothRemoteService this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.connectThread = null;
            this$0.scheduleDialRetry();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void run$lambda$3(BluetoothRemoteService this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.connectThread = null;
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
                BluetoothRemoteService.setState$lambda$20(BluetoothRemoteService.Listener.this, s, peer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setState$lambda$20(Listener $l, int $s, String $peer) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void startAsForeground(int s, String peer) {
        String text;
        switch (s) {
            case 1:
                text = "正在和另一台小翻建立连接…（两台都点“连接设备”）";
                break;
            case 2:
                text = "正在连接 " + (peer == null ? "对方设备" : peer) + "…";
                break;
            case 3:
                if (!Intrinsics.areEqual(AppPrefs.INSTANCE.btMode(this), "video")) {
                    text = "已连接 " + (peer != null ? peer : "对方") + "（阅读模式）：音量-下一页、音量+上一页，熄屏也可遥控";
                    break;
                } else {
                    text = "已连接 " + (peer != null ? peer : "对方") + "（短视频模式）：音量-上滑、音量+下滑，熄屏也可遥控";
                    break;
                }
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
        return this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.BluetoothRemoteService$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                BluetoothRemoteService.toast$lambda$21(BluetoothRemoteService.this, t);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void toast$lambda$21(BluetoothRemoteService this$0, String t) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(t, "$t");
        Toast.makeText(this$0.getApplicationContext(), t, 0).show();
    }
}
