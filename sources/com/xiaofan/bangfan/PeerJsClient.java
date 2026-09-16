package com.xiaofan.bangfan;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.xiaofan.bangfan.PeerJsClient;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.json.JSONObject;
import org.webrtc.DataChannel;
import org.webrtc.IceCandidate;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaStream;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.RtpReceiver;
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;
/* compiled from: PeerJsClient.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 G2\u00020\u0001:\u0003GHIB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\rH\u0002J\u0006\u0010&\u001a\u00020$J\u0006\u0010'\u001a\u00020$J\b\u0010(\u001a\u00020$H\u0002J\n\u0010)\u001a\u0004\u0018\u00010\u0019H\u0002J\u0010\u0010*\u001a\u00020$2\u0006\u0010+\u001a\u00020\u0005H\u0002J\b\u0010,\u001a\u00020$H\u0002J\u0010\u0010-\u001a\u00020$2\u0006\u0010.\u001a\u00020\u0005H\u0002J\u0010\u0010/\u001a\u00020$2\u0006\u00100\u001a\u000201H\u0002J \u00102\u001a\u00020$2\u0006\u00103\u001a\u00020\u00052\u0006\u00104\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u0005H\u0002J\u0010\u00105\u001a\u00020$2\u0006\u00106\u001a\u00020\u0005H\u0002J\u0016\u00107\u001a\u00020$2\f\u00108\u001a\b\u0012\u0004\u0012\u00020$09H\u0002J\u0010\u0010:\u001a\u00020$2\u0006\u0010;\u001a\u00020\u001cH\u0002J\b\u0010<\u001a\u00020=H\u0002J\u000e\u0010>\u001a\u00020\u00142\u0006\u00106\u001a\u00020\u0005J\u0010\u0010?\u001a\u00020$2\u0006\u0010@\u001a\u000201H\u0002J\b\u0010A\u001a\u00020$H\u0002J\u000e\u0010B\u001a\u00020$2\u0006\u0010C\u001a\u00020\u0005J\b\u0010D\u001a\u00020$H\u0002J\u0010\u0010E\u001a\u00020\u00052\u0006\u0010F\u001a\u00020\u0005H\u0002R\u0016\u0010\n\u001a\n \u000b*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00020\u001c0\u001bj\b\u0012\u0004\u0012\u00020\u001c`\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Lcom/xiaofan/bangfan/PeerJsClient;", "", "context", "Landroid/content/Context;", "selfId", "", "listener", "Lcom/xiaofan/bangfan/PeerJsClient$Listener;", "serverBase", "(Landroid/content/Context;Ljava/lang/String;Lcom/xiaofan/bangfan/PeerJsClient$Listener;Ljava/lang/String;)V", "appContext", "kotlin.jvm.PlatformType", "channel", "Lorg/webrtc/DataChannel;", "closed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "connectionId", "heartbeat", "Ljava/lang/Runnable;", "isOfferer", "", "main", "Landroid/os/Handler;", "offerStarted", "pc", "Lorg/webrtc/PeerConnection;", "pendingCandidates", "Ljava/util/ArrayList;", "Lorg/webrtc/IceCandidate;", "Lkotlin/collections/ArrayList;", "remoteDescSet", "remoteId", "signalingOpen", "webSocket", "Lokhttp3/WebSocket;", "bindChannel", "", "dc", "close", "connectSignaling", "createOfferInternal", "createPeerConnection", "fail", NotificationCompat.CATEGORY_MESSAGE, "flushPendingCandidates", "handleAnswer", "sdp", "handleCandidate", "payload", "Lorg/json/JSONObject;", "handleOffer", "src", "connId", "handleSignaling", "text", "post", "fn", "Lkotlin/Function0;", "relayCandidate", "c", "rtcConfig", "Lorg/webrtc/PeerConnection$RTCConfiguration;", "send", "sendSignaling", "obj", "startHeartbeat", "startOffer", "remoteRealId", "stopHeartbeat", "stripLocalAddress", "cand", "Companion", "Listener", "SimpleSdpObserver", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class PeerJsClient {
    private static final String KEY = "peerjs";
    private static final String TAG = "GomokuPeer";
    private static final String VERSION = "1.5.4";
    private static PeerConnectionFactory factory;
    private static volatile boolean initialized;
    private final Context appContext;
    private DataChannel channel;
    private final AtomicBoolean closed;
    private String connectionId;
    private Runnable heartbeat;
    private boolean isOfferer;
    private final Listener listener;
    private final Handler main;
    private boolean offerStarted;
    private PeerConnection pc;
    private final ArrayList<IceCandidate> pendingCandidates;
    private boolean remoteDescSet;
    private String remoteId;
    private final String selfId;
    private final String serverBase;
    private boolean signalingOpen;
    private WebSocket webSocket;
    public static final Companion Companion = new Companion(null);
    private static final String[] STUN = {"stun:stun.l.google.com:19302", "stun:stun1.l.google.com:19302", "stun:stun2.l.google.com:19302"};

    /* compiled from: PeerJsClient.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0006H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0006H&J\b\u0010\u000b\u001a\u00020\u0003H&¨\u0006\f"}, d2 = {"Lcom/xiaofan/bangfan/PeerJsClient$Listener;", "", "onChannelOpen", "", "onClosed", "reason", "", "onError", "error", "onMessage", "text", "onSignalingOpen", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public interface Listener {
        void onChannelOpen();

        void onClosed(String str);

        void onError(String str);

        void onMessage(String str);

        void onSignalingOpen();
    }

    public PeerJsClient(Context context, String selfId, Listener listener, String serverBase) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(selfId, "selfId");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(serverBase, "serverBase");
        this.selfId = selfId;
        this.listener = listener;
        this.serverBase = serverBase;
        this.appContext = context.getApplicationContext();
        this.main = new Handler(Looper.getMainLooper());
        this.closed = new AtomicBoolean(false);
        this.pendingCandidates = new ArrayList<>();
    }

    public /* synthetic */ PeerJsClient(Context context, String str, Listener listener, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str, listener, (i & 8) != 0 ? AppPrefs.DEFAULT_GOMOKU_SERVER : str2);
    }

    /* compiled from: PeerJsClient.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/xiaofan/bangfan/PeerJsClient$Companion;", "", "()V", "KEY", "", "STUN", "", "[Ljava/lang/String;", "TAG", "VERSION", "factory", "Lorg/webrtc/PeerConnectionFactory;", "initialized", "", "ensureFactory", "context", "Landroid/content/Context;", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final synchronized PeerConnectionFactory ensureFactory(Context context) {
            PeerConnectionFactory it = PeerJsClient.factory;
            if (it != null) {
                return it;
            }
            PeerConnectionFactory.initialize(PeerConnectionFactory.InitializationOptions.builder(context.getApplicationContext()).createInitializationOptions());
            PeerConnectionFactory f = PeerConnectionFactory.builder().createPeerConnectionFactory();
            PeerJsClient.factory = f;
            Intrinsics.checkNotNull(f);
            return f;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void post(final Function0<Unit> function0) {
        this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.PeerJsClient$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                PeerJsClient.post$lambda$0(PeerJsClient.this, function0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void post$lambda$0(PeerJsClient this$0, Function0 fn) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(fn, "$fn");
        if (this$0.closed.get()) {
            return;
        }
        fn.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fail(final String msg) {
        Log.w(TAG, msg);
        post(new Function0<Unit>() { // from class: com.xiaofan.bangfan.PeerJsClient$fail$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AtomicBoolean atomicBoolean;
                PeerJsClient.Listener listener;
                atomicBoolean = PeerJsClient.this.closed;
                if (atomicBoolean.get()) {
                    return;
                }
                listener = PeerJsClient.this.listener;
                listener.onError(msg);
            }
        });
    }

    public final void connectSignaling() {
        try {
            String token = GomokuSecurity.INSTANCE.randomConnectionId();
            String base = StringsKt.trimEnd(this.serverBase, '/');
            String url = base + "?key=peerjs&id=" + URLEncoder.encode(this.selfId, "UTF-8") + "&token=" + token + "&version=1.5.4";
            OkHttpClient client = new OkHttpClient.Builder().pingInterval(20L, TimeUnit.SECONDS).connectTimeout(12L, TimeUnit.SECONDS).readTimeout(0L, TimeUnit.MILLISECONDS).build();
            Request req = new Request.Builder().url(url).build();
            this.webSocket = client.newWebSocket(req, new WebSocketListener() { // from class: com.xiaofan.bangfan.PeerJsClient$connectSignaling$1
                @Override // okhttp3.WebSocketListener
                public void onOpen(WebSocket webSocket, Response response) {
                    String str;
                    Intrinsics.checkNotNullParameter(webSocket, "webSocket");
                    Intrinsics.checkNotNullParameter(response, "response");
                    str = PeerJsClient.this.selfId;
                    Log.i("GomokuPeer", "signaling socket open as " + str);
                }

                @Override // okhttp3.WebSocketListener
                public void onMessage(WebSocket webSocket, String text) {
                    Intrinsics.checkNotNullParameter(webSocket, "webSocket");
                    Intrinsics.checkNotNullParameter(text, "text");
                    PeerJsClient.this.handleSignaling(text);
                }

                @Override // okhttp3.WebSocketListener
                public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                    Intrinsics.checkNotNullParameter(webSocket, "webSocket");
                    Intrinsics.checkNotNullParameter(t, "t");
                    PeerJsClient peerJsClient = PeerJsClient.this;
                    String message = t.getMessage();
                    if (message == null) {
                        message = "网络异常";
                    }
                    peerJsClient.fail("信令连接失败：" + message);
                }

                @Override // okhttp3.WebSocketListener
                public void onClosed(WebSocket webSocket, int code, String reason) {
                    Intrinsics.checkNotNullParameter(webSocket, "webSocket");
                    Intrinsics.checkNotNullParameter(reason, "reason");
                    PeerJsClient peerJsClient = PeerJsClient.this;
                    final PeerJsClient peerJsClient2 = PeerJsClient.this;
                    peerJsClient.post(new Function0<Unit>() { // from class: com.xiaofan.bangfan.PeerJsClient$connectSignaling$1$onClosed$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke  reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            AtomicBoolean atomicBoolean;
                            PeerJsClient.Listener listener;
                            atomicBoolean = PeerJsClient.this.closed;
                            if (atomicBoolean.get()) {
                                return;
                            }
                            listener = PeerJsClient.this.listener;
                            listener.onClosed("信令已断开");
                        }
                    });
                }
            });
        } catch (Throwable th) {
            fail("无法连接信令服务器：" + th.getMessage());
        }
    }

    public final void startOffer(String remoteRealId) {
        Intrinsics.checkNotNullParameter(remoteRealId, "remoteRealId");
        this.remoteId = remoteRealId;
        this.isOfferer = true;
        this.connectionId = GomokuSecurity.INSTANCE.randomConnectionId();
        if (this.signalingOpen) {
            createOfferInternal();
        } else {
            Log.i(TAG, "waiting signaling open before offer");
        }
    }

    private final PeerConnection.RTCConfiguration rtcConfig() {
        String[] strArr = STUN;
        Collection destination$iv$iv = new ArrayList(strArr.length);
        for (String str : strArr) {
            destination$iv$iv.add(PeerConnection.IceServer.builder(str).createIceServer());
        }
        List servers = (List) destination$iv$iv;
        PeerConnection.RTCConfiguration cfg = new PeerConnection.RTCConfiguration(servers);
        cfg.sdpSemantics = PeerConnection.SdpSemantics.UNIFIED_PLAN;
        cfg.bundlePolicy = PeerConnection.BundlePolicy.MAXBUNDLE;
        cfg.rtcpMuxPolicy = PeerConnection.RtcpMuxPolicy.REQUIRE;
        return cfg;
    }

    private final PeerConnection createPeerConnection() {
        Companion companion = Companion;
        Context appContext = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        PeerConnectionFactory f = companion.ensureFactory(appContext);
        return f.createPeerConnection(rtcConfig(), new PeerConnection.Observer() { // from class: com.xiaofan.bangfan.PeerJsClient$createPeerConnection$1

            /* compiled from: PeerJsClient.kt */
            @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            /* loaded from: classes4.dex */
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[PeerConnection.IceConnectionState.values().length];
                    try {
                        iArr[PeerConnection.IceConnectionState.FAILED.ordinal()] = 1;
                    } catch (NoSuchFieldError e) {
                    }
                    try {
                        iArr[PeerConnection.IceConnectionState.CLOSED.ordinal()] = 2;
                    } catch (NoSuchFieldError e2) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // org.webrtc.PeerConnection.Observer
            public void onSignalingChange(PeerConnection.SignalingState state) {
            }

            @Override // org.webrtc.PeerConnection.Observer
            public void onIceConnectionChange(PeerConnection.IceConnectionState state) {
                Log.i("GomokuPeer", "ice state=" + state);
                switch (state == null ? -1 : WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
                    case 1:
                        PeerJsClient.this.fail("网络打洞失败，双方网络可能受限");
                        return;
                    case 2:
                        PeerJsClient peerJsClient = PeerJsClient.this;
                        final PeerJsClient peerJsClient2 = PeerJsClient.this;
                        peerJsClient.post(new Function0<Unit>() { // from class: com.xiaofan.bangfan.PeerJsClient$createPeerConnection$1$onIceConnectionChange$1
                            /* JADX INFO: Access modifiers changed from: package-private */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke  reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                PeerJsClient.Listener listener;
                                listener = PeerJsClient.this.listener;
                                listener.onClosed("连接已关闭");
                            }
                        });
                        return;
                    default:
                        return;
                }
            }

            @Override // org.webrtc.PeerConnection.Observer
            public void onIceConnectionReceivingChange(boolean receiving) {
            }

            @Override // org.webrtc.PeerConnection.Observer
            public void onIceGatheringChange(PeerConnection.IceGatheringState state) {
            }

            @Override // org.webrtc.PeerConnection.Observer
            public void onIceCandidate(IceCandidate candidate) {
                Intrinsics.checkNotNullParameter(candidate, "candidate");
                PeerJsClient.this.relayCandidate(candidate);
            }

            @Override // org.webrtc.PeerConnection.Observer
            public void onIceCandidatesRemoved(IceCandidate[] candidates) {
            }

            @Override // org.webrtc.PeerConnection.Observer
            public void onAddStream(MediaStream stream) {
            }

            @Override // org.webrtc.PeerConnection.Observer
            public void onRemoveStream(MediaStream stream) {
            }

            @Override // org.webrtc.PeerConnection.Observer
            public void onDataChannel(DataChannel dc) {
                Intrinsics.checkNotNullParameter(dc, "dc");
                Log.i("GomokuPeer", "host got data channel");
                PeerJsClient.this.bindChannel(dc);
            }

            @Override // org.webrtc.PeerConnection.Observer
            public void onRenegotiationNeeded() {
            }

            @Override // org.webrtc.PeerConnection.Observer
            public void onAddTrack(RtpReceiver receiver, MediaStream[] streams) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void relayCandidate(IceCandidate c) {
        String conn;
        String sdp = c.sdp;
        if (sdp == null) {
            return;
        }
        String clean = stripLocalAddress(sdp);
        String remote = this.remoteId;
        if (remote == null || (conn = this.connectionId) == null) {
            return;
        }
        JSONObject msg = new JSONObject();
        JSONObject payload = new JSONObject();
        JSONObject cand = new JSONObject();
        cand.put("candidate", clean);
        String str = c.sdpMid;
        if (str == null) {
            str = "0";
        }
        cand.put("sdpMid", str);
        cand.put("sdpMLineIndex", c.sdpMLineIndex);
        payload.put("type", "CANDIDATE");
        payload.put("candidate", cand);
        msg.put("type", "CANDIDATE");
        msg.put("src", this.selfId);
        msg.put("dst", remote);
        msg.put("connectionId", conn);
        msg.put("payload", payload);
        sendSignaling(msg);
    }

    private final String stripLocalAddress(String cand) {
        Regex regex = new Regex("\\sraddr\\s+\\S+");
        return new Regex("\\srport\\s+\\S+").replace(regex.replace(cand, ""), "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createOfferInternal() {
        if (this.offerStarted) {
            return;
        }
        this.offerStarted = true;
        try {
            final PeerConnection conn = this.pc;
            if (conn == null) {
                conn = createPeerConnection();
                if (conn != null) {
                    this.pc = conn;
                } else {
                    conn = null;
                }
                if (conn == null) {
                    fail("无法创建 WebRTC 连接");
                    return;
                }
            }
            DataChannel.Init $this$createOfferInternal_u24lambda_u243 = new DataChannel.Init();
            $this$createOfferInternal_u24lambda_u243.ordered = true;
            DataChannel dc = conn.createDataChannel(this.connectionId, $this$createOfferInternal_u24lambda_u243);
            Intrinsics.checkNotNull(dc);
            bindChannel(dc);
            MediaConstraints constraints = new MediaConstraints();
            conn.createOffer(new SimpleSdpObserver() { // from class: com.xiaofan.bangfan.PeerJsClient$createOfferInternal$1
                @Override // com.xiaofan.bangfan.PeerJsClient.SimpleSdpObserver, org.webrtc.SdpObserver
                public void onCreateSuccess(SessionDescription desc) {
                    String str;
                    String str2;
                    String str3;
                    String str4;
                    if (desc == null) {
                        PeerJsClient.this.fail("创建连接失败：空 SDP");
                        return;
                    }
                    conn.setLocalDescription(new PeerJsClient.SimpleSdpObserver(), desc);
                    JSONObject msg = new JSONObject();
                    JSONObject payload = new JSONObject();
                    payload.put("type", "offer").put("sdp", desc.description);
                    msg.put("type", "OFFER");
                    str = PeerJsClient.this.selfId;
                    msg.put("src", str);
                    str2 = PeerJsClient.this.remoteId;
                    msg.put("dst", str2);
                    str3 = PeerJsClient.this.connectionId;
                    msg.put("connectionId", str3);
                    msg.put("payload", payload);
                    PeerJsClient.this.sendSignaling(msg);
                    str4 = PeerJsClient.this.remoteId;
                    Log.i("GomokuPeer", "offerer sent OFFER to " + str4);
                }

                @Override // com.xiaofan.bangfan.PeerJsClient.SimpleSdpObserver, org.webrtc.SdpObserver
                public void onCreateFailure(String error) {
                    PeerJsClient.this.fail("创建连接失败：" + error);
                }
            }, constraints);
        } catch (Throwable th) {
            fail("发起连接异常：" + th.getMessage());
        }
    }

    private final void handleOffer(final String src, final String connId, String sdp) {
        try {
            Log.i(TAG, "host received OFFER from " + src + " conn=" + connId);
            this.remoteId = src;
            this.connectionId = connId;
            this.isOfferer = false;
            final PeerConnection conn = this.pc;
            if (conn == null) {
                conn = createPeerConnection();
                if (conn != null) {
                    this.pc = conn;
                } else {
                    conn = null;
                }
                if (conn == null) {
                    fail("无法创建 WebRTC 连接");
                    return;
                }
            }
            conn.setRemoteDescription(new SimpleSdpObserver() { // from class: com.xiaofan.bangfan.PeerJsClient$handleOffer$1
                @Override // com.xiaofan.bangfan.PeerJsClient.SimpleSdpObserver, org.webrtc.SdpObserver
                public void onSetSuccess() {
                    PeerJsClient.this.remoteDescSet = true;
                    PeerJsClient.this.flushPendingCandidates();
                    PeerConnection peerConnection = conn;
                    final PeerJsClient peerJsClient = PeerJsClient.this;
                    final PeerConnection peerConnection2 = conn;
                    final String str = src;
                    final String str2 = connId;
                    peerConnection.createAnswer(new PeerJsClient.SimpleSdpObserver() { // from class: com.xiaofan.bangfan.PeerJsClient$handleOffer$1$onSetSuccess$1
                        @Override // com.xiaofan.bangfan.PeerJsClient.SimpleSdpObserver, org.webrtc.SdpObserver
                        public void onCreateSuccess(SessionDescription desc) {
                            String str3;
                            if (desc == null) {
                                PeerJsClient.this.fail("应答失败：空 SDP");
                                return;
                            }
                            peerConnection2.setLocalDescription(new PeerJsClient.SimpleSdpObserver(), desc);
                            JSONObject msg = new JSONObject();
                            JSONObject payload = new JSONObject();
                            payload.put("type", "answer").put("sdp", desc.description);
                            msg.put("type", "ANSWER");
                            str3 = PeerJsClient.this.selfId;
                            msg.put("src", str3);
                            msg.put("dst", str);
                            msg.put("connectionId", str2);
                            msg.put("payload", payload);
                            PeerJsClient.this.sendSignaling(msg);
                        }

                        @Override // com.xiaofan.bangfan.PeerJsClient.SimpleSdpObserver, org.webrtc.SdpObserver
                        public void onCreateFailure(String error) {
                            PeerJsClient.this.fail("应答失败：" + error);
                        }
                    }, new MediaConstraints());
                }
            }, new SessionDescription(SessionDescription.Type.OFFER, sdp));
        } catch (Throwable th) {
            fail("处理连接请求异常：" + th.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleAnswer(String sdp) {
        PeerConnection conn = this.pc;
        if (conn == null) {
            return;
        }
        conn.setRemoteDescription(new SimpleSdpObserver() { // from class: com.xiaofan.bangfan.PeerJsClient$handleAnswer$1
            @Override // com.xiaofan.bangfan.PeerJsClient.SimpleSdpObserver, org.webrtc.SdpObserver
            public void onSetSuccess() {
                PeerJsClient.this.remoteDescSet = true;
                PeerJsClient.this.flushPendingCandidates();
            }
        }, new SessionDescription(SessionDescription.Type.ANSWER, sdp));
    }

    private final void handleCandidate(JSONObject payload) {
        try {
            JSONObject cand = payload.optJSONObject("candidate");
            if (cand == null) {
                return;
            }
            IceCandidate ic = new IceCandidate(cand.optString("sdpMid", "0"), cand.optInt("sdpMLineIndex", 0), cand.optString("candidate", ""));
            if (this.remoteDescSet) {
                PeerConnection peerConnection = this.pc;
                if (peerConnection != null) {
                    peerConnection.addIceCandidate(ic);
                }
            } else {
                synchronized (this.pendingCandidates) {
                    this.pendingCandidates.add(ic);
                }
            }
        } catch (Throwable th) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void flushPendingCandidates() {
        Iterable list;
        synchronized (this.pendingCandidates) {
            list = new ArrayList(this.pendingCandidates);
            this.pendingCandidates.clear();
        }
        Iterable $this$forEach$iv = list;
        for (Object element$iv : $this$forEach$iv) {
            IceCandidate it = (IceCandidate) element$iv;
            try {
                Result.Companion companion = Result.Companion;
                PeerJsClient $this$flushPendingCandidates_u24lambda_u248_u24lambda_u247 = this;
                PeerConnection peerConnection = $this$flushPendingCandidates_u24lambda_u248_u24lambda_u247.pc;
                Result.m273constructorimpl(peerConnection != null ? Boolean.valueOf(peerConnection.addIceCandidate(it)) : null);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                Result.m273constructorimpl(ResultKt.createFailure(th));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void handleSignaling(String text) {
        JSONObject payload;
        JSONObject payload2;
        final JSONObject payload3;
        try {
            JSONObject msg = new JSONObject(text);
            Log.i(TAG, "rx signaling type=" + msg.optString("type") + " src=" + msg.optString("src") + " dst=" + msg.optString("dst"));
            String optString = msg.optString("type");
            if (optString != null) {
                String str = "房间不存在，或房间号/密码不正确";
                switch (optString.hashCode()) {
                    case -999709341:
                        if (optString.equals("CANDIDATE") && (payload = msg.optJSONObject("payload")) != null) {
                            handleCandidate(payload);
                            return;
                        }
                        return;
                    case 2331:
                        if (optString.equals("ID")) {
                            post(new Function0<Unit>() { // from class: com.xiaofan.bangfan.PeerJsClient$handleSignaling$2
                                /* JADX INFO: Access modifiers changed from: package-private */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    PeerJsClient.Listener listener;
                                    boolean z;
                                    PeerJsClient.this.signalingOpen = true;
                                    PeerJsClient.this.startHeartbeat();
                                    listener = PeerJsClient.this.listener;
                                    listener.onSignalingOpen();
                                    z = PeerJsClient.this.isOfferer;
                                    if (z) {
                                        PeerJsClient.this.createOfferInternal();
                                    }
                                }
                            });
                            return;
                        }
                        return;
                    case 2432586:
                        if (optString.equals("OPEN")) {
                            post(new Function0<Unit>() { // from class: com.xiaofan.bangfan.PeerJsClient$handleSignaling$1
                                /* JADX INFO: Access modifiers changed from: package-private */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    PeerJsClient.Listener listener;
                                    boolean z;
                                    PeerJsClient.this.signalingOpen = true;
                                    PeerJsClient.this.startHeartbeat();
                                    listener = PeerJsClient.this.listener;
                                    listener.onSignalingOpen();
                                    z = PeerJsClient.this.isOfferer;
                                    if (z) {
                                        PeerJsClient.this.createOfferInternal();
                                    }
                                }
                            });
                            return;
                        }
                        return;
                    case 66247144:
                        if (optString.equals("ERROR")) {
                            JSONObject err = msg.optJSONObject("error");
                            String etype = err != null ? err.optString("type") : null;
                            if (etype == null) {
                                etype = "";
                            }
                            switch (etype.hashCode()) {
                                case -1463068571:
                                    if (!etype.equals("peer-unavailable")) {
                                        str = "联机错误：" + etype;
                                        break;
                                    } else {
                                        break;
                                    }
                                case -1231260410:
                                    if (!etype.equals("socket-closed")) {
                                        str = "联机错误：" + etype;
                                        break;
                                    }
                                    str = "网络连接异常，请重试";
                                    break;
                                case -1146068178:
                                    if (!etype.equals("socket-error")) {
                                        str = "联机错误：" + etype;
                                        break;
                                    }
                                    str = "网络连接异常，请重试";
                                    break;
                                case 769670072:
                                    if (!etype.equals("unavailable-id")) {
                                        str = "联机错误：" + etype;
                                        break;
                                    } else {
                                        str = "房间号已被占用，请重新创建";
                                        break;
                                    }
                                case 1843485230:
                                    if (!etype.equals("network")) {
                                        str = "联机错误：" + etype;
                                        break;
                                    }
                                    str = "网络连接异常，请重试";
                                    break;
                                default:
                                    str = "联机错误：" + etype;
                                    break;
                            }
                            fail(str);
                            return;
                        }
                        return;
                    case 75113020:
                        if (optString.equals("OFFER") && (payload2 = msg.optJSONObject("payload")) != null) {
                            String optString2 = msg.optString("src");
                            Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
                            String optString3 = msg.optString("connectionId");
                            Intrinsics.checkNotNullExpressionValue(optString3, "optString(...)");
                            String optString4 = payload2.optString("sdp");
                            Intrinsics.checkNotNullExpressionValue(optString4, "optString(...)");
                            handleOffer(optString2, optString3, optString4);
                            return;
                        }
                        return;
                    case 1935487934:
                        if (optString.equals("ANSWER") && (payload3 = msg.optJSONObject("payload")) != null) {
                            post(new Function0<Unit>() { // from class: com.xiaofan.bangfan.PeerJsClient$handleSignaling$3
                                /* JADX INFO: Access modifiers changed from: package-private */
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    PeerJsClient peerJsClient = PeerJsClient.this;
                                    String optString5 = payload3.optString("sdp");
                                    Intrinsics.checkNotNullExpressionValue(optString5, "optString(...)");
                                    peerJsClient.handleAnswer(optString5);
                                }
                            });
                            return;
                        }
                        return;
                    case 2059137311:
                        if (optString.equals("EXPIRE") && Intrinsics.areEqual(msg.optString("dst"), this.selfId)) {
                            fail("房间不存在，或房间号/密码不正确");
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        } catch (Throwable th) {
            Log.w(TAG, "bad signaling msg: " + text, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startHeartbeat() {
        stopHeartbeat();
        Runnable runnable = new Runnable() { // from class: com.xiaofan.bangfan.PeerJsClient$startHeartbeat$r$1
            @Override // java.lang.Runnable
            public void run() {
                Handler handler;
                WebSocket webSocket;
                try {
                    webSocket = PeerJsClient.this.webSocket;
                    if (webSocket != null) {
                        String jSONObject = new JSONObject().put("type", "HEARTBEAT").toString();
                        Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
                        webSocket.send(jSONObject);
                    }
                } catch (Throwable th) {
                }
                handler = PeerJsClient.this.main;
                handler.postDelayed(this, 5000L);
            }
        };
        this.heartbeat = runnable;
        this.main.postDelayed(runnable, 5000L);
    }

    private final void stopHeartbeat() {
        Runnable it = this.heartbeat;
        if (it != null) {
            this.main.removeCallbacks(it);
        }
        this.heartbeat = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendSignaling(JSONObject obj) {
        try {
            WebSocket ws = this.webSocket;
            if (ws == null) {
                Log.w(TAG, "tx skipped, socket null type=" + obj.optString("type"));
                return;
            }
            String jSONObject = obj.toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
            ws.send(jSONObject);
            String optString = obj.optString("type");
            Log.i(TAG, "tx signaling type=" + optString + " dst=" + obj.optString("dst"));
        } catch (Throwable t) {
            Log.e(TAG, "tx error", t);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void bindChannel(final DataChannel dc) {
        this.channel = dc;
        dc.registerObserver(new DataChannel.Observer() { // from class: com.xiaofan.bangfan.PeerJsClient$bindChannel$1
            @Override // org.webrtc.DataChannel.Observer
            public void onBufferedAmountChange(long previousAmount) {
            }

            @Override // org.webrtc.DataChannel.Observer
            public void onStateChange() {
                DataChannel.State st = DataChannel.this.state();
                Log.i("GomokuPeer", "data channel state=" + st);
                if (st == DataChannel.State.OPEN) {
                    PeerJsClient peerJsClient = this;
                    final PeerJsClient peerJsClient2 = this;
                    peerJsClient.post(new Function0<Unit>() { // from class: com.xiaofan.bangfan.PeerJsClient$bindChannel$1$onStateChange$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke  reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            PeerJsClient.Listener listener;
                            listener = PeerJsClient.this.listener;
                            listener.onChannelOpen();
                        }
                    });
                } else if (st == DataChannel.State.CLOSED || st == DataChannel.State.CLOSING) {
                    PeerJsClient peerJsClient3 = this;
                    final PeerJsClient peerJsClient4 = this;
                    peerJsClient3.post(new Function0<Unit>() { // from class: com.xiaofan.bangfan.PeerJsClient$bindChannel$1$onStateChange$2
                        /* JADX INFO: Access modifiers changed from: package-private */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke  reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            PeerJsClient.Listener listener;
                            listener = PeerJsClient.this.listener;
                            listener.onClosed("对方已离开房间");
                        }
                    });
                }
            }

            @Override // org.webrtc.DataChannel.Observer
            public void onMessage(DataChannel.Buffer buffer) {
                ByteBuffer buf;
                if (buffer == null) {
                    buf = null;
                } else {
                    try {
                        buf = buffer.data;
                    } catch (Throwable th) {
                        return;
                    }
                }
                if (buf == null) {
                    return;
                }
                byte[] bytes = new byte[buf.remaining()];
                buf.get(bytes);
                final String text = new String(bytes, Charsets.UTF_8);
                PeerJsClient peerJsClient = this;
                final PeerJsClient peerJsClient2 = this;
                peerJsClient.post(new Function0<Unit>() { // from class: com.xiaofan.bangfan.PeerJsClient$bindChannel$1$onMessage$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke  reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        PeerJsClient.Listener listener;
                        listener = PeerJsClient.this.listener;
                        listener.onMessage(text);
                    }
                });
            }
        });
    }

    public final boolean send(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        DataChannel dc = this.channel;
        if (dc == null) {
            return false;
        }
        try {
            if (dc.state() != DataChannel.State.OPEN) {
                return false;
            }
            byte[] bytes = text.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            return dc.send(new DataChannel.Buffer(ByteBuffer.wrap(bytes), false));
        } catch (Throwable th) {
            return false;
        }
    }

    public final void close() {
        if (this.closed.compareAndSet(false, true)) {
            stopHeartbeat();
            try {
                DataChannel dataChannel = this.channel;
                if (dataChannel != null) {
                    dataChannel.close();
                }
            } catch (Throwable th) {
            }
            try {
                PeerConnection peerConnection = this.pc;
                if (peerConnection != null) {
                    peerConnection.close();
                }
            } catch (Throwable th2) {
            }
            try {
                WebSocket webSocket = this.webSocket;
                if (webSocket != null) {
                    webSocket.close(1000, "bye");
                }
            } catch (Throwable th3) {
            }
            this.channel = null;
            this.pc = null;
            this.webSocket = null;
            this.offerStarted = false;
            this.signalingOpen = false;
            this.remoteDescSet = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: PeerJsClient.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0012\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\t\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\n\u001a\u00020\u0004H\u0016¨\u0006\u000b"}, d2 = {"Lcom/xiaofan/bangfan/PeerJsClient$SimpleSdpObserver;", "Lorg/webrtc/SdpObserver;", "()V", "onCreateFailure", "", "p0", "", "onCreateSuccess", "Lorg/webrtc/SessionDescription;", "onSetFailure", "onSetSuccess", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static class SimpleSdpObserver implements SdpObserver {
        @Override // org.webrtc.SdpObserver
        public void onCreateSuccess(SessionDescription p0) {
        }

        @Override // org.webrtc.SdpObserver
        public void onSetSuccess() {
        }

        @Override // org.webrtc.SdpObserver
        public void onCreateFailure(String p0) {
        }

        @Override // org.webrtc.SdpObserver
        public void onSetFailure(String p0) {
        }
    }
}
