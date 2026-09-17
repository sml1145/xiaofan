package com.xiaofan.bangfan;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.GameNet;
import com.xiaofan.bangfan.GomokuNet;
import com.xiaofan.bangfan.PeerJsClient;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONObject;
/* compiled from: GameNet.kt */
@Metadata(d1 = {"\u0000K\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0010*\u0001\u0015\u0018\u00002\u00020\u0001:\u00016B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\nJ\b\u0010%\u001a\u00020\u0005H\u0002J\u0010\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u0005H\u0002J\u0006\u0010)\u001a\u00020\fJ&\u0010*\u001a\u00020'2\u0006\u0010+\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\nJ\u0006\u0010,\u001a\u00020'J\b\u0010-\u001a\u00020'H\u0002J\u000e\u0010.\u001a\u00020'2\u0006\u0010/\u001a\u00020\u0005J\u0006\u00100\u001a\u00020'J\u000e\u00101\u001a\u00020'2\u0006\u00102\u001a\u00020\u0010J\u001a\u00103\u001a\u00020'2\u0006\u00104\u001a\u00020\u001a2\b\b\u0002\u00105\u001a\u00020\u0005H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\u0010@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u000b\u001a\u00020\u001a@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001e\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u00067"}, d2 = {"Lcom/xiaofan/bangfan/GameNet;", "", "appContext", "Landroid/content/Context;", "gameType", "", "(Landroid/content/Context;Ljava/lang/String;)V", "client", "Lcom/xiaofan/bangfan/PeerJsClient;", "listener", "Lcom/xiaofan/bangfan/GameNet$Listener;", "<set-?>", "", "modeHost", "getModeHost", "()Z", "", "mySeat", "getMySeat", "()I", "peerListener", "com/xiaofan/bangfan/GameNet$peerListener$1", "Lcom/xiaofan/bangfan/GameNet$peerListener$1;", "pendingJoinTarget", "pwdHash", "selfName", "Lcom/xiaofan/bangfan/GomokuNet$State;", "state", "getState", "()Lcom/xiaofan/bangfan/GomokuNet$State;", "visibleCode", "getVisibleCode", "()Ljava/lang/String;", "createRoom", "password", "name", "l", "displayGameName", "handleMessage", "", "text", "isConnected", "joinRoom", "code", "leave", "sendLeave", "sendPayload", "payload", "sendReset", "sendSurrender", "seat", "setState", "s", "info", "Listener", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class GameNet {
    private final Context appContext;
    private PeerJsClient client;
    private final String gameType;
    private Listener listener;
    private boolean modeHost;
    private int mySeat;
    private final GameNet$peerListener$1 peerListener;
    private String pendingJoinTarget;
    private String pwdHash;
    private String selfName;
    private volatile GomokuNet.State state;
    private String visibleCode;

    /* compiled from: GameNet.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0007H&J\b\u0010\n\u001a\u00020\u0003H&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0007H&J\b\u0010\r\u001a\u00020\u0003H&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0005H&J\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0007H&¨\u0006\u0014"}, d2 = {"Lcom/xiaofan/bangfan/GameNet$Listener;", "", "onAuthorized", "", "mySeat", "", "opponentName", "", "onError", "message", "onPeerLeft", "onRemotePayload", "payload", "onRemoteReset", "onRemoteSurrender", "bySeat", "onStateChanged", "state", "Lcom/xiaofan/bangfan/GomokuNet$State;", "info", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public interface Listener {
        void onAuthorized(int i, String str);

        void onError(String str);

        void onPeerLeft();

        void onRemotePayload(String str);

        void onRemoteReset();

        void onRemoteSurrender(int i);

        void onStateChanged(GomokuNet.State state, String str);
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [com.xiaofan.bangfan.GameNet$peerListener$1] */
    public GameNet(Context appContext, String gameType) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(gameType, "gameType");
        this.appContext = appContext;
        this.gameType = gameType;
        this.mySeat = -1;
        this.visibleCode = "";
        this.pwdHash = "";
        this.selfName = "玩家";
        this.state = GomokuNet.State.IDLE;
        this.peerListener = new PeerJsClient.Listener() { // from class: com.xiaofan.bangfan.GameNet$peerListener$1
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
                    com.xiaofan.bangfan.GameNet r0 = com.xiaofan.bangfan.GameNet.this
                    boolean r0 = r0.getModeHost()
                    if (r0 == 0) goto L2f
                    com.xiaofan.bangfan.GameNet r0 = com.xiaofan.bangfan.GameNet.this
                    com.xiaofan.bangfan.GomokuNet$State r1 = com.xiaofan.bangfan.GomokuNet.State.WAITING_OPPONENT
                    com.xiaofan.bangfan.GameNet r2 = com.xiaofan.bangfan.GameNet.this
                    java.lang.String r2 = r2.getVisibleCode()
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder
                    r3.<init>()
                    java.lang.String r4 = "房间 "
                    java.lang.StringBuilder r3 = r3.append(r4)
                    java.lang.StringBuilder r2 = r3.append(r2)
                    java.lang.String r3 = " · 等待棋友加入"
                    java.lang.StringBuilder r2 = r2.append(r3)
                    java.lang.String r2 = r2.toString()
                    com.xiaofan.bangfan.GameNet.access$setState(r0, r1, r2)
                    goto L4c
                L2f:
                    com.xiaofan.bangfan.GameNet r0 = com.xiaofan.bangfan.GameNet.this
                    com.xiaofan.bangfan.GomokuNet$State r1 = com.xiaofan.bangfan.GomokuNet.State.CONNECTING
                    java.lang.String r2 = "正在连接房主…"
                    com.xiaofan.bangfan.GameNet.access$setState(r0, r1, r2)
                    com.xiaofan.bangfan.GameNet r0 = com.xiaofan.bangfan.GameNet.this
                    java.lang.String r0 = com.xiaofan.bangfan.GameNet.access$getPendingJoinTarget$p(r0)
                    if (r0 == 0) goto L4c
                    com.xiaofan.bangfan.GameNet r1 = com.xiaofan.bangfan.GameNet.this
                    r2 = 0
                    com.xiaofan.bangfan.PeerJsClient r1 = com.xiaofan.bangfan.GameNet.access$getClient$p(r1)
                    if (r1 == 0) goto L4c
                    r1.startOffer(r0)
                L4c:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.GameNet$peerListener$1.onSignalingOpen():void");
            }

            @Override // com.xiaofan.bangfan.PeerJsClient.Listener
            public void onChannelOpen() {
                String str;
                String str2;
                String str3;
                PeerJsClient peerJsClient;
                if (GameNet.this.getModeHost()) {
                    GameNet.this.setState(GomokuNet.State.WAITING_OPPONENT, "棋友正在验证密码…");
                    return;
                }
                JSONObject o = new JSONObject();
                JSONObject put = o.put("t", "AUTH");
                str = GameNet.this.pwdHash;
                JSONObject put2 = put.put("hash", str);
                str2 = GameNet.this.selfName;
                JSONObject put3 = put2.put("name", str2);
                str3 = GameNet.this.gameType;
                put3.put("g", str3);
                peerJsClient = GameNet.this.client;
                if (peerJsClient != null) {
                    String jSONObject = o.toString();
                    Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
                    peerJsClient.send(jSONObject);
                }
                GameNet.this.setState(GomokuNet.State.CONNECTING, "正在校验房间密码…");
            }

            @Override // com.xiaofan.bangfan.PeerJsClient.Listener
            public void onMessage(String text) {
                Intrinsics.checkNotNullParameter(text, "text");
                GameNet.this.handleMessage(text);
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
                    com.xiaofan.bangfan.GameNet r0 = com.xiaofan.bangfan.GameNet.this
                    com.xiaofan.bangfan.GomokuNet$State r0 = r0.getState()
                    com.xiaofan.bangfan.GomokuNet$State r1 = com.xiaofan.bangfan.GomokuNet.State.CONNECTED
                    if (r0 != r1) goto L1a
                    com.xiaofan.bangfan.GameNet r0 = com.xiaofan.bangfan.GameNet.this
                    com.xiaofan.bangfan.GameNet$Listener r0 = com.xiaofan.bangfan.GameNet.access$getListener$p(r0)
                    if (r0 == 0) goto L1a
                    r0.onPeerLeft()
                L1a:
                    com.xiaofan.bangfan.GameNet r0 = com.xiaofan.bangfan.GameNet.this
                    com.xiaofan.bangfan.GomokuNet$State r1 = com.xiaofan.bangfan.GomokuNet.State.CLOSED
                    com.xiaofan.bangfan.GameNet.access$setState(r0, r1, r3)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.GameNet$peerListener$1.onClosed(java.lang.String):void");
            }

            @Override // com.xiaofan.bangfan.PeerJsClient.Listener
            public void onError(String error) {
                GameNet.Listener listener;
                Intrinsics.checkNotNullParameter(error, "error");
                GameNet.this.setState(GomokuNet.State.ERROR, error);
                listener = GameNet.this.listener;
                if (listener != null) {
                    listener.onError(error);
                }
            }
        };
    }

    public final boolean getModeHost() {
        return this.modeHost;
    }

    public final int getMySeat() {
        return this.mySeat;
    }

    public final String getVisibleCode() {
        return this.visibleCode;
    }

    public final GomokuNet.State getState() {
        return this.state;
    }

    static /* synthetic */ void setState$default(GameNet gameNet, GomokuNet.State state, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "";
        }
        gameNet.setState(state, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setState(GomokuNet.State s, String info) {
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
        String realId = GomokuSecurity.INSTANCE.realPeerId(code, password, this.gameType);
        Log.i("GameNet", "host " + this.gameType + " room=" + code + " real=" + realId);
        setState(GomokuNet.State.CONNECTING, "正在创建房间…");
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
        String realId = GomokuSecurity.INSTANCE.realPeerId(code, password, this.gameType);
        String myId = GomokuSecurity.INSTANCE.randomClientId();
        Log.i("GameNet", "join " + this.gameType + " room=" + code + " real=" + realId + " client=" + myId);
        setState(GomokuNet.State.CONNECTING, "正在加入房间…");
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
        try {
            try {
                JSONObject o = new JSONObject(text);
                String optString = o.optString("t");
                if (optString != null) {
                    switch (optString.hashCode()) {
                        case -18351275:
                            if (optString.equals("AUTH_FAIL")) {
                                Listener listener5 = this.listener;
                                if (listener5 != null) {
                                    listener5.onError("房间密码错误或房间不存在，无法加入");
                                }
                                leave();
                                return;
                            }
                            return;
                        case 2278:
                            if (optString.equals("GM") && (listener = this.listener) != null) {
                                String optString2 = o.optString("d");
                                Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
                                listener.onRemotePayload(optString2);
                                return;
                            }
                            return;
                        case 2020776:
                            if (optString.equals("AUTH") && this.modeHost) {
                                String hash = o.optString("hash");
                                String g = o.optString("g");
                                String oppName = o.optString("name", "棋友");
                                boolean gameOk = StringsKt.equals(g, this.gameType, true);
                                if (!Intrinsics.areEqual(hash, this.pwdHash) || !gameOk) {
                                    PeerJsClient peerJsClient = this.client;
                                    if (peerJsClient != null) {
                                        String jSONObject = new JSONObject().put("t", "AUTH_FAIL").toString();
                                        Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
                                        peerJsClient.send(jSONObject);
                                    }
                                    Listener listener6 = this.listener;
                                    if (listener6 != null) {
                                        listener6.onError(!gameOk ? "该房间不是" + displayGameName() + "房间" : "对方密码错误，已拒绝");
                                    }
                                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.GameNet$$ExternalSyntheticLambda0
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            GameNet.handleMessage$lambda$2(GameNet.this);
                                        }
                                    }, 600L);
                                    return;
                                }
                                this.mySeat = 0;
                                JSONObject ok = new JSONObject();
                                ok.put("t", "AUTH_OK").put("you", 1).put("g", this.gameType).put("name", this.selfName);
                                PeerJsClient peerJsClient2 = this.client;
                                if (peerJsClient2 != null) {
                                    String jSONObject2 = ok.toString();
                                    Intrinsics.checkNotNullExpressionValue(jSONObject2, "toString(...)");
                                    peerJsClient2.send(jSONObject2);
                                }
                                setState(GomokuNet.State.CONNECTED, "已与 " + oppName + " 联机");
                                Listener listener7 = this.listener;
                                if (listener7 != null) {
                                    Intrinsics.checkNotNull(oppName);
                                    listener7.onAuthorized(0, oppName);
                                    return;
                                }
                                return;
                            }
                            return;
                        case 71489491:
                            if (optString.equals("AUTH_OK")) {
                                if (!StringsKt.equals(o.optString("g"), this.gameType, true)) {
                                    Listener listener8 = this.listener;
                                    if (listener8 != null) {
                                        listener8.onError("房间游戏类型不匹配");
                                    }
                                    leave();
                                    return;
                                }
                                this.mySeat = o.optInt("you", 1);
                                String oppName2 = o.optString("name", "房主");
                                setState(GomokuNet.State.CONNECTED, "已加入房间，与 " + oppName2 + " 联机");
                                Listener listener9 = this.listener;
                                if (listener9 != null) {
                                    int i = this.mySeat;
                                    Intrinsics.checkNotNull(oppName2);
                                    listener9.onAuthorized(i, oppName2);
                                    return;
                                }
                                return;
                            }
                            return;
                        case 72308375:
                            if (optString.equals("LEAVE") && (listener2 = this.listener) != null) {
                                listener2.onPeerLeft();
                                return;
                            }
                            return;
                        case 77866287:
                            if (optString.equals("RESET") && (listener3 = this.listener) != null) {
                                listener3.onRemoteReset();
                                return;
                            }
                            return;
                        case 294720454:
                            if (optString.equals("SURRENDER") && (listener4 = this.listener) != null) {
                                listener4.onRemoteSurrender(o.optInt("s"));
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
            } catch (Throwable th) {
            }
        } catch (Throwable th2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void handleMessage$lambda$2(GameNet this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            PeerJsClient peerJsClient = this$0.client;
            if (peerJsClient != null) {
                peerJsClient.close();
            }
        } catch (Throwable th) {
        }
    }

    private final String displayGameName() {
        String str = this.gameType;
        switch (str.hashCode()) {
            case -2069685983:
                if (str.equals("xiangqi")) {
                    return "中国象棋";
                }
                break;
            case 3304:
                if (str.equals("go")) {
                    return "围棋";
                }
                break;
            case 99322:
                if (str.equals("ddz")) {
                    return "斗地主";
                }
                break;
        }
        return this.gameType;
    }

    public final void sendPayload(String payload) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        PeerJsClient peerJsClient = this.client;
        if (peerJsClient != null) {
            String jSONObject = new JSONObject().put("t", "GM").put("d", payload).toString();
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

    public final void sendSurrender(int seat) {
        PeerJsClient peerJsClient = this.client;
        if (peerJsClient != null) {
            String jSONObject = new JSONObject().put("t", "SURRENDER").put("s", seat).toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
            peerJsClient.send(jSONObject);
        }
    }

    private final void sendLeave() {
        Boolean bool;
        try {
            Result.Companion companion = Result.Companion;
            GameNet $this$sendLeave_u24lambda_u243 = this;
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
        return this.state == GomokuNet.State.CONNECTED;
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
        this.state = GomokuNet.State.IDLE;
        this.mySeat = -1;
        this.visibleCode = "";
        this.pendingJoinTarget = null;
    }
}
