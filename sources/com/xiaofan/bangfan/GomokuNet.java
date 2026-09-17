package com.xiaofan.bangfan;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.GomokuNet;
import com.xiaofan.bangfan.PeerJsClient;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONObject;
/* compiled from: GomokuNet.kt */
@Metadata(d1 = {"\u0000M\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0014*\u0001\u0013\u0018\u00002\u00020\u0001:\u000278B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010 \u001a\u00020\u00162\u0006\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020\bJ\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0016H\u0002J\u0006\u0010'\u001a\u00020\nJ&\u0010(\u001a\u00020%2\u0006\u0010)\u001a\u00020\u00162\u0006\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020\bJ\u0006\u0010*\u001a\u00020%J\b\u0010+\u001a\u00020%H\u0002J\u001e\u0010,\u001a\u00020%2\u0006\u0010-\u001a\u00020\u000e2\u0006\u0010.\u001a\u00020\u000e2\u0006\u0010/\u001a\u00020\u000eJ\u0006\u00100\u001a\u00020%J\u000e\u00101\u001a\u00020%2\u0006\u0010/\u001a\u00020\u000eJ\u000e\u00102\u001a\u00020%2\u0006\u00103\u001a\u00020\u000eJ\u001a\u00104\u001a\u00020%2\u0006\u00105\u001a\u00020\u00192\b\b\u0002\u00106\u001a\u00020\u0016H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001a\u001a\u00020\u00192\u0006\u0010\t\u001a\u00020\u0019@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001e\u0010\u001d\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\u0016@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u00069"}, d2 = {"Lcom/xiaofan/bangfan/GomokuNet;", "", "appContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "client", "Lcom/xiaofan/bangfan/PeerJsClient;", "listener", "Lcom/xiaofan/bangfan/GomokuNet$Listener;", "<set-?>", "", "modeHost", "getModeHost", "()Z", "", "myColor", "getMyColor", "()I", "peerListener", "com/xiaofan/bangfan/GomokuNet$peerListener$1", "Lcom/xiaofan/bangfan/GomokuNet$peerListener$1;", "pendingJoinTarget", "", "pwdHash", "selfName", "Lcom/xiaofan/bangfan/GomokuNet$State;", "state", "getState", "()Lcom/xiaofan/bangfan/GomokuNet$State;", "visibleCode", "getVisibleCode", "()Ljava/lang/String;", "createRoom", "password", "name", "l", "handleMessage", "", "text", "isConnected", "joinRoom", "code", "leave", "sendLeave", "sendMove", "x", "y", "player", "sendReset", "sendSurrender", "sendUndo", "steps", "setState", "s", "info", "Listener", "State", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class GomokuNet {
    private final Context appContext;
    private PeerJsClient client;
    private Listener listener;
    private boolean modeHost;
    private int myColor;
    private final GomokuNet$peerListener$1 peerListener;
    private String pendingJoinTarget;
    private String pwdHash;
    private String selfName;
    private volatile State state;
    private String visibleCode;

    /* compiled from: GomokuNet.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0007H&J\b\u0010\n\u001a\u00020\u0003H&J \u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005H&J\b\u0010\u000f\u001a\u00020\u0003H&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0005H&J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0005H&J\u0018\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0007H&¨\u0006\u0018"}, d2 = {"Lcom/xiaofan/bangfan/GomokuNet$Listener;", "", "onAuthorized", "", "myColor", "", "opponentName", "", "onError", "message", "onPeerLeft", "onRemoteMove", "x", "y", "player", "onRemoteReset", "onRemoteSurrender", "byPlayer", "onRemoteUndo", "steps", "onStateChanged", "state", "Lcom/xiaofan/bangfan/GomokuNet$State;", "info", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public interface Listener {
        void onAuthorized(int i, String str);

        void onError(String str);

        void onPeerLeft();

        void onRemoteMove(int i, int i2, int i3);

        void onRemoteReset();

        void onRemoteSurrender(int i);

        void onRemoteUndo(int i);

        void onStateChanged(State state, String str);
    }

    /* compiled from: GomokuNet.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/xiaofan/bangfan/GomokuNet$State;", "", "(Ljava/lang/String;I)V", "IDLE", "CONNECTING", "WAITING_OPPONENT", "CONNECTED", "CLOSED", "ERROR", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public enum State {
        IDLE,
        CONNECTING,
        WAITING_OPPONENT,
        CONNECTED,
        CLOSED,
        ERROR;
        
        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<State> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [com.xiaofan.bangfan.GomokuNet$peerListener$1] */
    public GomokuNet(Context appContext) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        this.appContext = appContext;
        this.myColor = -1;
        this.visibleCode = "";
        this.pwdHash = "";
        this.selfName = "玩家";
        this.state = State.IDLE;
        this.peerListener = new PeerJsClient.Listener() { // from class: com.xiaofan.bangfan.GomokuNet$peerListener$1
            /* JADX WARN: Code restructure failed: missing block: B:7:0x0040, code lost:
                r1 = r5.this$0.client;
             */
            @Override // com.xiaofan.bangfan.PeerJsClient.Listener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onSignalingOpen() {
                /*
                    r5 = this;
                    com.xiaofan.bangfan.GomokuNet r0 = com.xiaofan.bangfan.GomokuNet.this
                    boolean r0 = r0.getModeHost()
                    if (r0 == 0) goto L2f
                    com.xiaofan.bangfan.GomokuNet r0 = com.xiaofan.bangfan.GomokuNet.this
                    com.xiaofan.bangfan.GomokuNet$State r1 = com.xiaofan.bangfan.GomokuNet.State.WAITING_OPPONENT
                    com.xiaofan.bangfan.GomokuNet r2 = com.xiaofan.bangfan.GomokuNet.this
                    java.lang.String r2 = r2.getVisibleCode()
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder
                    r3.<init>()
                    java.lang.String r4 = "房间 "
                    java.lang.StringBuilder r3 = r3.append(r4)
                    java.lang.StringBuilder r2 = r3.append(r2)
                    java.lang.String r3 = " · 等待棋友加入"
                    java.lang.StringBuilder r2 = r2.append(r3)
                    java.lang.String r2 = r2.toString()
                    com.xiaofan.bangfan.GomokuNet.access$setState(r0, r1, r2)
                    goto L4c
                L2f:
                    com.xiaofan.bangfan.GomokuNet r0 = com.xiaofan.bangfan.GomokuNet.this
                    com.xiaofan.bangfan.GomokuNet$State r1 = com.xiaofan.bangfan.GomokuNet.State.CONNECTING
                    java.lang.String r2 = "正在连接房主…"
                    com.xiaofan.bangfan.GomokuNet.access$setState(r0, r1, r2)
                    com.xiaofan.bangfan.GomokuNet r0 = com.xiaofan.bangfan.GomokuNet.this
                    java.lang.String r0 = com.xiaofan.bangfan.GomokuNet.access$getPendingJoinTarget$p(r0)
                    if (r0 == 0) goto L4c
                    com.xiaofan.bangfan.GomokuNet r1 = com.xiaofan.bangfan.GomokuNet.this
                    r2 = 0
                    com.xiaofan.bangfan.PeerJsClient r1 = com.xiaofan.bangfan.GomokuNet.access$getClient$p(r1)
                    if (r1 == 0) goto L4c
                    r1.startOffer(r0)
                L4c:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.GomokuNet$peerListener$1.onSignalingOpen():void");
            }

            @Override // com.xiaofan.bangfan.PeerJsClient.Listener
            public void onChannelOpen() {
                String str;
                String str2;
                PeerJsClient peerJsClient;
                if (GomokuNet.this.getModeHost()) {
                    GomokuNet.this.setState(GomokuNet.State.WAITING_OPPONENT, "棋友正在验证密码…");
                    return;
                }
                JSONObject o = new JSONObject();
                JSONObject put = o.put("t", "AUTH");
                str = GomokuNet.this.pwdHash;
                JSONObject put2 = put.put("hash", str);
                str2 = GomokuNet.this.selfName;
                put2.put("name", str2);
                peerJsClient = GomokuNet.this.client;
                if (peerJsClient != null) {
                    String jSONObject = o.toString();
                    Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
                    peerJsClient.send(jSONObject);
                }
                GomokuNet.this.setState(GomokuNet.State.CONNECTING, "正在校验房间密码…");
            }

            @Override // com.xiaofan.bangfan.PeerJsClient.Listener
            public void onMessage(String text) {
                Intrinsics.checkNotNullParameter(text, "text");
                GomokuNet.this.handleMessage(text);
            }

            /* JADX WARN: Code restructure failed: missing block: B:4:0x000f, code lost:
                r0 = r2.this$0.listener;
             */
            @Override // com.xiaofan.bangfan.PeerJsClient.Listener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onClosed(java.lang.String r3) {
                /*
                    r2 = this;
                    java.lang.String r0 = "reason"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                    com.xiaofan.bangfan.GomokuNet r0 = com.xiaofan.bangfan.GomokuNet.this
                    com.xiaofan.bangfan.GomokuNet$State r0 = r0.getState()
                    com.xiaofan.bangfan.GomokuNet$State r1 = com.xiaofan.bangfan.GomokuNet.State.CONNECTED
                    if (r0 != r1) goto L1a
                    com.xiaofan.bangfan.GomokuNet r0 = com.xiaofan.bangfan.GomokuNet.this
                    com.xiaofan.bangfan.GomokuNet$Listener r0 = com.xiaofan.bangfan.GomokuNet.access$getListener$p(r0)
                    if (r0 == 0) goto L1a
                    r0.onPeerLeft()
                L1a:
                    com.xiaofan.bangfan.GomokuNet r0 = com.xiaofan.bangfan.GomokuNet.this
                    com.xiaofan.bangfan.GomokuNet$State r1 = com.xiaofan.bangfan.GomokuNet.State.CLOSED
                    com.xiaofan.bangfan.GomokuNet.access$setState(r0, r1, r3)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.GomokuNet$peerListener$1.onClosed(java.lang.String):void");
            }

            @Override // com.xiaofan.bangfan.PeerJsClient.Listener
            public void onError(String error) {
                GomokuNet.Listener listener;
                Intrinsics.checkNotNullParameter(error, "error");
                GomokuNet.this.setState(GomokuNet.State.ERROR, error);
                listener = GomokuNet.this.listener;
                if (listener != null) {
                    listener.onError(error);
                }
            }
        };
    }

    public final boolean getModeHost() {
        return this.modeHost;
    }

    public final int getMyColor() {
        return this.myColor;
    }

    public final String getVisibleCode() {
        return this.visibleCode;
    }

    public final State getState() {
        return this.state;
    }

    static /* synthetic */ void setState$default(GomokuNet gomokuNet, State state, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "";
        }
        gomokuNet.setState(state, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setState(State s, String info) {
        this.state = s;
        Listener listener = this.listener;
        if (listener != null) {
            listener.onStateChanged(s, info);
        }
    }

    public final String createRoom(String password, String name, Listener l) {
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(l, "l");
        leave();
        this.listener = l;
        this.modeHost = true;
        String str = name;
        if (StringsKt.isBlank(str)) {
            str = "房主";
        }
        this.selfName = str;
        this.pwdHash = GomokuSecurity.INSTANCE.passwordHash(password);
        String code = GomokuSecurity.INSTANCE.generateVisibleRoomCode();
        this.visibleCode = code;
        String realId = GomokuSecurity.INSTANCE.realPeerId(code, password);
        Log.i("GomokuNet", "host room=" + code + " real=" + realId);
        setState(State.CONNECTING, "正在创建房间…");
        PeerJsClient c = new PeerJsClient(this.appContext, realId, this.peerListener, AppPrefs.INSTANCE.gomokuServer(this.appContext));
        this.client = c;
        c.connectSignaling();
        return code;
    }

    public final void joinRoom(String code, String password, String name, Listener l) {
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(l, "l");
        leave();
        this.listener = l;
        this.modeHost = false;
        String str = name;
        if (StringsKt.isBlank(str)) {
            str = "棋友";
        }
        this.selfName = str;
        this.pwdHash = GomokuSecurity.INSTANCE.passwordHash(password);
        this.visibleCode = StringsKt.trim((CharSequence) code).toString();
        String realId = GomokuSecurity.INSTANCE.realPeerId(code, password);
        String myId = GomokuSecurity.INSTANCE.randomClientId();
        Log.i("GomokuNet", "join room=" + code + " real=" + realId + " client=" + myId);
        setState(State.CONNECTING, "正在加入房间…");
        PeerJsClient c = new PeerJsClient(this.appContext, myId, this.peerListener, AppPrefs.INSTANCE.gomokuServer(this.appContext));
        this.client = c;
        c.connectSignaling();
        this.pendingJoinTarget = realId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleMessage(String text) {
        Listener listener;
        Listener listener2;
        Listener listener3;
        Listener listener4;
        Listener listener5;
        try {
            JSONObject o = new JSONObject(text);
            String hash = o.optString("t");
            if (hash != null) {
                switch (hash.hashCode()) {
                    case -18351275:
                        if (hash.equals("AUTH_FAIL")) {
                            Listener listener6 = this.listener;
                            if (listener6 != null) {
                                listener6.onError("房间密码错误，无法加入");
                            }
                            leave();
                            return;
                        }
                        return;
                    case 2020776:
                        if (hash.equals("AUTH") && this.modeHost) {
                            String hash2 = o.optString("hash");
                            String oppName = o.optString("name", "棋友");
                            if (!Intrinsics.areEqual(hash2, this.pwdHash)) {
                                JSONObject fail = new JSONObject();
                                fail.put("t", "AUTH_FAIL");
                                PeerJsClient peerJsClient = this.client;
                                if (peerJsClient != null) {
                                    String jSONObject = fail.toString();
                                    Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
                                    peerJsClient.send(jSONObject);
                                }
                                Listener listener7 = this.listener;
                                if (listener7 != null) {
                                    listener7.onError("对方密码错误，已拒绝");
                                }
                                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.GomokuNet$$ExternalSyntheticLambda0
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        GomokuNet.handleMessage$lambda$2(GomokuNet.this);
                                    }
                                }, 600L);
                                return;
                            }
                            this.myColor = 1;
                            JSONObject ok = new JSONObject();
                            ok.put("t", "AUTH_OK").put("you", 2).put("name", this.selfName);
                            PeerJsClient peerJsClient2 = this.client;
                            if (peerJsClient2 != null) {
                                String jSONObject2 = ok.toString();
                                Intrinsics.checkNotNullExpressionValue(jSONObject2, "toString(...)");
                                peerJsClient2.send(jSONObject2);
                            }
                            setState(State.CONNECTED, "已与 " + oppName + " 联机");
                            Listener listener8 = this.listener;
                            if (listener8 != null) {
                                Intrinsics.checkNotNull(oppName);
                                listener8.onAuthorized(1, oppName);
                                return;
                            }
                            return;
                        }
                        return;
                    case 2372561:
                        if (hash.equals("MOVE") && (listener = this.listener) != null) {
                            listener.onRemoteMove(o.optInt("x"), o.optInt("y"), o.optInt("p"));
                            return;
                        }
                        return;
                    case 2609380:
                        if (hash.equals("UNDO") && (listener2 = this.listener) != null) {
                            listener2.onRemoteUndo(o.optInt("n", 1));
                            return;
                        }
                        return;
                    case 71489491:
                        if (hash.equals("AUTH_OK")) {
                            this.myColor = o.optInt("you", 2);
                            String oppName2 = o.optString("name", "房主");
                            setState(State.CONNECTED, "已加入房间，与 " + oppName2 + " 联机");
                            Listener listener9 = this.listener;
                            if (listener9 != null) {
                                int i = this.myColor;
                                Intrinsics.checkNotNull(oppName2);
                                listener9.onAuthorized(i, oppName2);
                                return;
                            }
                            return;
                        }
                        return;
                    case 72308375:
                        if (hash.equals("LEAVE") && (listener3 = this.listener) != null) {
                            listener3.onPeerLeft();
                            return;
                        }
                        return;
                    case 77866287:
                        if (hash.equals("RESET") && (listener4 = this.listener) != null) {
                            listener4.onRemoteReset();
                            return;
                        }
                        return;
                    case 294720454:
                        if (hash.equals("SURRENDER") && (listener5 = this.listener) != null) {
                            listener5.onRemoteSurrender(o.optInt("p"));
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        } catch (Throwable th) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void handleMessage$lambda$2(GomokuNet this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            PeerJsClient peerJsClient = this$0.client;
            if (peerJsClient != null) {
                peerJsClient.close();
            }
        } catch (Throwable th) {
        }
    }

    public final void sendMove(int x, int y, int player) {
        JSONObject o = new JSONObject();
        o.put("t", "MOVE").put("x", x).put("y", y).put("p", player);
        PeerJsClient peerJsClient = this.client;
        if (peerJsClient != null) {
            String jSONObject = o.toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
            peerJsClient.send(jSONObject);
        }
    }

    public final void sendReset() {
        PeerJsClient peerJsClient = this.client;
        if (peerJsClient != null) {
            String jSONObject = new JSONObject().put("t", "RESET").toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
            peerJsClient.send(jSONObject);
        }
    }

    public final void sendUndo(int steps) {
        PeerJsClient peerJsClient = this.client;
        if (peerJsClient != null) {
            String jSONObject = new JSONObject().put("t", "UNDO").put("n", steps).toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
            peerJsClient.send(jSONObject);
        }
    }

    public final void sendSurrender(int player) {
        PeerJsClient peerJsClient = this.client;
        if (peerJsClient != null) {
            String jSONObject = new JSONObject().put("t", "SURRENDER").put("p", player).toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
            peerJsClient.send(jSONObject);
        }
    }

    private final void sendLeave() {
        Boolean bool;
        try {
            Result.Companion companion = Result.Companion;
            GomokuNet $this$sendLeave_u24lambda_u243 = this;
            PeerJsClient peerJsClient = $this$sendLeave_u24lambda_u243.client;
            if (peerJsClient != null) {
                String jSONObject = new JSONObject().put("t", "LEAVE").toString();
                Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
                bool = Boolean.valueOf(peerJsClient.send(jSONObject));
            } else {
                bool = null;
            }
            Result.m275constructorimpl(bool);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            Result.m275constructorimpl(ResultKt.createFailure(th));
        }
    }

    public final boolean isConnected() {
        return this.state == State.CONNECTED;
    }

    public final void leave() {
        sendLeave();
        try {
            PeerJsClient peerJsClient = this.client;
            if (peerJsClient != null) {
                peerJsClient.close();
            }
        } catch (Throwable th) {
        }
        this.client = null;
        this.state = State.IDLE;
        this.myColor = -1;
        this.visibleCode = "";
        this.pendingJoinTarget = null;
    }
}
