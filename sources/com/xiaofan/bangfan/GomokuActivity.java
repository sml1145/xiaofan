package com.xiaofan.bangfan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.GomokuAi;
import com.xiaofan.bangfan.GomokuGame;
import com.xiaofan.bangfan.GomokuNet;
import com.xiaofan.bangfan.GomokuView;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: GomokuActivity.kt */
@Metadata(d1 = {"\u0000q\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0013*\u0001\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010%\u001a\u00020&H\u0002J\b\u0010'\u001a\u00020&H\u0002J\b\u0010(\u001a\u00020&H\u0002J\b\u0010)\u001a\u00020&H\u0002J\b\u0010*\u001a\u00020&H\u0002J\u0018\u0010+\u001a\u00020&2\u0006\u0010,\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\u0010H\u0002J\u001e\u0010.\u001a\u00020\u000b2\u0006\u0010/\u001a\u00020\u001a2\f\u00100\u001a\b\u0012\u0004\u0012\u00020&01H\u0002J\b\u00102\u001a\u00020&H\u0002J\u0010\u00103\u001a\u00020&2\u0006\u00104\u001a\u00020\u001aH\u0002J\u0018\u00105\u001a\u00020&2\u0006\u00106\u001a\u00020\u001a2\u0006\u00104\u001a\u00020\u001aH\u0002J\b\u00107\u001a\u00020&H\u0002J\u001a\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\u001a2\b\b\u0002\u0010;\u001a\u00020\u0004H\u0002J\u0018\u0010<\u001a\u00020\u000b2\u0006\u0010/\u001a\u00020\u001a2\u0006\u0010=\u001a\u00020\u0004H\u0002J\u0018\u0010>\u001a\u00020&2\u0006\u0010,\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\u0010H\u0002J\b\u0010?\u001a\u00020&H\u0002J\u0012\u0010@\u001a\u00020&2\b\u0010A\u001a\u0004\u0018\u00010BH\u0014J\b\u0010C\u001a\u00020&H\u0014J\b\u0010D\u001a\u00020&H\u0002J\b\u0010E\u001a\u00020&H\u0002J\b\u0010F\u001a\u00020&H\u0002J\b\u0010G\u001a\u00020&H\u0002J\b\u0010H\u001a\u00020&H\u0002J\b\u0010I\u001a\u00020&H\u0002J\b\u0010J\u001a\u00020&H\u0002J\b\u0010K\u001a\u00020&H\u0002J\u0010\u0010L\u001a\u00020&2\u0006\u0010M\u001a\u000209H\u0002J\u0010\u0010N\u001a\u00020&2\u0006\u0010/\u001a\u00020\u001aH\u0002J\u0018\u0010O\u001a\u00020&2\u0006\u0010P\u001a\u00020\u000b2\u0006\u0010Q\u001a\u00020\u0004H\u0002J\u0010\u0010R\u001a\u00020&2\u0006\u0010S\u001a\u00020\u0010H\u0002J\b\u0010T\u001a\u00020&H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006U"}, d2 = {"Lcom/xiaofan/bangfan/GomokuActivity;", "Lcom/xiaofan/bangfan/BaseActivity;", "()V", "aiThinking", "", "aiWorker", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "boardView", "Lcom/xiaofan/bangfan/GomokuView;", "confirmBtn", "Landroid/widget/TextView;", "game", "Lcom/xiaofan/bangfan/GomokuGame;", "leaveBtn", "mode", "", "modeAiBtn", "modeLocalBtn", "net", "Lcom/xiaofan/bangfan/GomokuNet;", "netListener", "com/xiaofan/bangfan/GomokuActivity$netListener$1", "Lcom/xiaofan/bangfan/GomokuActivity$netListener$1;", "netStatusTv", "opponentName", "", "pendingX", "pendingY", "recordTv", "resignBtn", "resultRecorded", "serverBtn", "statusTv", "undoBtn", "waitingDialog", "Landroid/app/AlertDialog;", "afterMove", "", "askCreatePassword", "askJoinInfo", "askServerUrl", "clearPendingStone", "commitStone", "x", "y", "ctrlButton", "text", "onClick", "Lkotlin/Function0;", "dismissWaiting", "doCreate", "pwd", "doJoin", "code", "exitOnline", "inputBox", "Landroid/widget/EditText;", "hint", "numeric", "modeButton", "primary", "onBoardTap", "onConfirm", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResign", "onUndo", "recordResultOnce", "refreshModeButtons", "refreshRecord", "refreshStatus", "requestRestart", "restartLocal", "showKeyboard", "et", "showWaiting", "styleModeBtn", "tv", "active", "switchMode", "toMode", "triggerAi", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class GomokuActivity extends BaseActivity {
    private boolean aiThinking;
    private GomokuView boardView;
    private TextView confirmBtn;
    private TextView leaveBtn;
    private int mode;
    private TextView modeAiBtn;
    private TextView modeLocalBtn;
    private TextView netStatusTv;
    private TextView recordTv;
    private TextView resignBtn;
    private boolean resultRecorded;
    private TextView serverBtn;
    private TextView statusTv;
    private TextView undoBtn;
    private AlertDialog waitingDialog;
    private final GomokuGame game = new GomokuGame();
    private int pendingX = -1;
    private int pendingY = -1;
    private final ExecutorService aiWorker = Executors.newSingleThreadExecutor();
    private final GomokuNet net = new GomokuNet(this);
    private String opponentName = "棋友";
    private final GomokuActivity$netListener$1 netListener = new GomokuNet.Listener() { // from class: com.xiaofan.bangfan.GomokuActivity$netListener$1
        /* JADX WARN: Code restructure failed: missing block: B:10:0x0025, code lost:
            r0 = r3.this$0.waitingDialog;
         */
        @Override // com.xiaofan.bangfan.GomokuNet.Listener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onStateChanged(com.xiaofan.bangfan.GomokuNet.State r4, java.lang.String r5) {
            /*
                r3 = this;
                java.lang.String r0 = "s"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                java.lang.String r0 = "info"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                com.xiaofan.bangfan.GomokuActivity r0 = com.xiaofan.bangfan.GomokuActivity.this
                android.widget.TextView r0 = com.xiaofan.bangfan.GomokuActivity.access$getNetStatusTv$p(r0)
                if (r0 != 0) goto L13
                goto L19
            L13:
                r1 = r5
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                r0.setText(r1)
            L19:
                com.xiaofan.bangfan.GomokuNet$State r0 = com.xiaofan.bangfan.GomokuNet.State.WAITING_OPPONENT
                if (r4 != r0) goto L3f
                com.xiaofan.bangfan.GomokuActivity r0 = com.xiaofan.bangfan.GomokuActivity.this
                android.app.AlertDialog r0 = com.xiaofan.bangfan.GomokuActivity.access$getWaitingDialog$p(r0)
                if (r0 == 0) goto L3f
                com.xiaofan.bangfan.GomokuActivity r0 = com.xiaofan.bangfan.GomokuActivity.this
                android.app.AlertDialog r0 = com.xiaofan.bangfan.GomokuActivity.access$getWaitingDialog$p(r0)
                if (r0 == 0) goto L3f
                r1 = r5
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                boolean r2 = kotlin.text.StringsKt.isBlank(r1)
                if (r2 == 0) goto L3a
                r1 = 0
                java.lang.String r1 = "等待棋友加入…"
            L3a:
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                r0.setMessage(r1)
            L3f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.GomokuActivity$netListener$1.onStateChanged(com.xiaofan.bangfan.GomokuNet$State, java.lang.String):void");
        }

        @Override // com.xiaofan.bangfan.GomokuNet.Listener
        public void onAuthorized(int myColor, String opponentName) {
            TextView textView;
            TextView textView2;
            TextView textView3;
            Intrinsics.checkNotNullParameter(opponentName, "opponentName");
            GomokuActivity.this.opponentName = opponentName;
            GomokuActivity.this.dismissWaiting();
            GomokuActivity.this.mode = 2;
            GomokuActivity.this.refreshModeButtons();
            GomokuActivity.this.restartLocal();
            textView = GomokuActivity.this.leaveBtn;
            if (textView != null) {
                textView.setVisibility(0);
            }
            textView2 = GomokuActivity.this.serverBtn;
            if (textView2 != null) {
                textView2.setVisibility(8);
            }
            String colorText = myColor == 1 ? "你执黑先手" : "你执白后手";
            textView3 = GomokuActivity.this.netStatusTv;
            if (textView3 != null) {
                textView3.setText("已与 " + opponentName + " 联机 · " + colorText);
            }
            Toast.makeText(GomokuActivity.this, "联机成功，" + colorText, 1).show();
        }

        @Override // com.xiaofan.bangfan.GomokuNet.Listener
        public void onRemoteMove(int x, int y, int player) {
            GomokuGame gomokuGame;
            gomokuGame = GomokuActivity.this.game;
            if (gomokuGame.placeStone(x, y, player)) {
                GomokuActivity.this.afterMove();
            }
        }

        @Override // com.xiaofan.bangfan.GomokuNet.Listener
        public void onRemoteReset() {
            GomokuActivity.this.restartLocal();
        }

        @Override // com.xiaofan.bangfan.GomokuNet.Listener
        public void onRemoteUndo(int steps) {
            GomokuView gomokuView;
            GomokuGame gomokuGame;
            GomokuActivity gomokuActivity = GomokuActivity.this;
            for (int i = 0; i < steps; i++) {
                gomokuGame = gomokuActivity.game;
                gomokuGame.undo();
            }
            gomokuView = GomokuActivity.this.boardView;
            if (gomokuView != null) {
                gomokuView.invalidate();
            }
            GomokuActivity.this.refreshStatus();
        }

        @Override // com.xiaofan.bangfan.GomokuNet.Listener
        public void onRemoteSurrender(int byPlayer) {
            GomokuGame gomokuGame;
            GomokuView gomokuView;
            gomokuGame = GomokuActivity.this.game;
            gomokuGame.resign(byPlayer);
            gomokuView = GomokuActivity.this.boardView;
            if (gomokuView != null) {
                gomokuView.invalidate();
            }
            GomokuActivity.this.afterMove();
        }

        @Override // com.xiaofan.bangfan.GomokuNet.Listener
        public void onPeerLeft() {
            Toast.makeText(GomokuActivity.this, "对方已离开房间", 1).show();
            GomokuActivity.this.exitOnline();
        }

        @Override // com.xiaofan.bangfan.GomokuNet.Listener
        public void onError(String message) {
            GomokuNet gomokuNet;
            TextView textView;
            Intrinsics.checkNotNullParameter(message, "message");
            Toast.makeText(GomokuActivity.this, message, 1).show();
            gomokuNet = GomokuActivity.this.net;
            if (!gomokuNet.isConnected()) {
                GomokuActivity.this.exitOnline();
                textView = GomokuActivity.this.netStatusTv;
                if (textView == null) {
                    return;
                }
                textView.setText("联机失败：" + message);
            }
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scaffold = UiKit.INSTANCE.scaffold(this, "五子棋", "人机 / 同屏 / 联机对战，五连即胜");
        LinearLayout content = UiKit.INSTANCE.contentOf(scaffold);
        LinearLayout modeRow = new LinearLayout(this);
        modeRow.setOrientation(0);
        this.modeAiBtn = modeButton("人机对战", true);
        this.modeLocalBtn = modeButton("同屏对战", false);
        TextView textView = this.modeAiBtn;
        Intrinsics.checkNotNull(textView);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.GomokuActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GomokuActivity.onCreate$lambda$0(GomokuActivity.this, view);
            }
        });
        TextView textView2 = this.modeLocalBtn;
        Intrinsics.checkNotNull(textView2);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.GomokuActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GomokuActivity.onCreate$lambda$1(GomokuActivity.this, view);
            }
        });
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(0, -2, 1.0f);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
        lp2.leftMargin = UiKit.INSTANCE.dp(this, 10.0f);
        modeRow.addView(this.modeAiBtn, lp1);
        modeRow.addView(this.modeLocalBtn, lp2);
        content.addView(modeRow, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 4.0f), 0, UiKit.INSTANCE.dp(this, 10.0f)));
        LinearLayout onlineCard = UiKit.INSTANCE.card(this);
        TextView onlineTitle = UiKit.INSTANCE.cardTitle(this, "联机对战");
        onlineCard.addView(onlineTitle);
        TextView onlineDesc = UiKit.INSTANCE.bodyText(this, "创建房间生成 6 位房间号，好友输入房间号与密码即可联机；真实 ID 经哈希隐藏。");
        onlineCard.addView(onlineDesc);
        this.serverBtn = new TextView(this);
        TextView textView3 = this.serverBtn;
        Intrinsics.checkNotNull(textView3);
        textView3.setText("联机服务器设置（默认官方云，可改自建 PeerJS）");
        TextView textView4 = this.serverBtn;
        Intrinsics.checkNotNull(textView4);
        textView4.setTextColor(-13063992);
        TextView textView5 = this.serverBtn;
        Intrinsics.checkNotNull(textView5);
        textView5.setTextSize(12.0f);
        TextView textView6 = this.serverBtn;
        Intrinsics.checkNotNull(textView6);
        textView6.setGravity(17);
        TextView textView7 = this.serverBtn;
        Intrinsics.checkNotNull(textView7);
        textView7.setPadding(0, UiKit.INSTANCE.dp(this, 6.0f), 0, 0);
        TextView textView8 = this.serverBtn;
        Intrinsics.checkNotNull(textView8);
        textView8.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.GomokuActivity$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GomokuActivity.onCreate$lambda$2(GomokuActivity.this, view);
            }
        });
        onlineCard.addView(this.serverBtn);
        LinearLayout onlineRow = new LinearLayout(this);
        onlineRow.setOrientation(0);
        TextView createBtn = UiKit.INSTANCE.button(this, "创建房间", true, new View.OnClickListener() { // from class: com.xiaofan.bangfan.GomokuActivity$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GomokuActivity.onCreate$lambda$3(GomokuActivity.this, view);
            }
        });
        TextView joinBtn = UiKit.INSTANCE.button(this, "加入房间", false, new View.OnClickListener() { // from class: com.xiaofan.bangfan.GomokuActivity$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GomokuActivity.onCreate$lambda$4(GomokuActivity.this, view);
            }
        });
        LinearLayout.LayoutParams o1 = new LinearLayout.LayoutParams(0, -2, 1.0f);
        LinearLayout.LayoutParams o2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
        o2.leftMargin = UiKit.INSTANCE.dp(this, 10.0f);
        onlineRow.addView(createBtn, o1);
        onlineRow.addView(joinBtn, o2);
        onlineCard.addView(onlineRow, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 10.0f), 0, 0));
        this.netStatusTv = UiKit.INSTANCE.bodyText(this, "尚未联机");
        TextView textView9 = this.netStatusTv;
        Intrinsics.checkNotNull(textView9);
        textView9.setGravity(17);
        onlineCard.addView(this.netStatusTv, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 8.0f), 0, 0));
        this.leaveBtn = UiKit.INSTANCE.button(this, "离开房间", false, new View.OnClickListener() { // from class: com.xiaofan.bangfan.GomokuActivity$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GomokuActivity.onCreate$lambda$5(GomokuActivity.this, view);
            }
        });
        TextView textView10 = this.leaveBtn;
        Intrinsics.checkNotNull(textView10);
        textView10.setVisibility(8);
        onlineCard.addView(this.leaveBtn, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 8.0f), 0, 0));
        content.addView(onlineCard, UiKit.INSTANCE.margin(this, 0, 0, 0, UiKit.INSTANCE.dp(this, 10.0f)));
        this.statusTv = UiKit.INSTANCE.cardTitle(this, "");
        TextView textView11 = this.statusTv;
        Intrinsics.checkNotNull(textView11);
        textView11.setGravity(17);
        content.addView(this.statusTv, UiKit.INSTANCE.margin(this, 0, 0, 0, UiKit.INSTANCE.dp(this, 8.0f)));
        LinearLayout boardCard = UiKit.INSTANCE.card(this);
        this.boardView = new GomokuView(this, null, 2, null);
        GomokuView gomokuView = this.boardView;
        Intrinsics.checkNotNull(gomokuView);
        gomokuView.setGame(this.game);
        int boardSize = getResources().getDisplayMetrics().widthPixels - UiKit.INSTANCE.dp(this, 64.0f);
        GomokuView gomokuView2 = this.boardView;
        Intrinsics.checkNotNull(gomokuView2);
        gomokuView2.setListener(new GomokuView.Listener() { // from class: com.xiaofan.bangfan.GomokuActivity$onCreate$5
            @Override // com.xiaofan.bangfan.GomokuView.Listener
            public void onCellTap(int x, int y) {
                GomokuActivity.this.onBoardTap(x, y);
            }
        });
        boardCard.addView(this.boardView, new LinearLayout.LayoutParams(boardSize, boardSize));
        content.addView(boardCard, UiKit.INSTANCE.margin(this, 0, 0, 0, UiKit.INSTANCE.dp(this, 10.0f)));
        LinearLayout ctrlRow = new LinearLayout(this);
        ctrlRow.setOrientation(0);
        this.undoBtn = ctrlButton("悔棋", new Function0<Unit>() { // from class: com.xiaofan.bangfan.GomokuActivity$onCreate$6
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
                GomokuActivity.this.onUndo();
            }
        });
        this.confirmBtn = ctrlButton("确认落子", new Function0<Unit>() { // from class: com.xiaofan.bangfan.GomokuActivity$onCreate$7
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
                GomokuActivity.this.onConfirm();
            }
        });
        TextView textView12 = this.confirmBtn;
        Intrinsics.checkNotNull(textView12);
        textView12.setEnabled(false);
        TextView textView13 = this.confirmBtn;
        Intrinsics.checkNotNull(textView13);
        textView13.setAlpha(0.5f);
        TextView restartBtn = ctrlButton("重开", new Function0<Unit>() { // from class: com.xiaofan.bangfan.GomokuActivity$onCreate$restartBtn$1
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
                GomokuActivity.this.requestRestart();
            }
        });
        this.resignBtn = ctrlButton("认输", new Function0<Unit>() { // from class: com.xiaofan.bangfan.GomokuActivity$onCreate$8
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
                GomokuActivity.this.onResign();
            }
        });
        LinearLayout.LayoutParams c1 = new LinearLayout.LayoutParams(0, -2, 1.0f);
        LinearLayout.LayoutParams c2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
        c2.leftMargin = UiKit.INSTANCE.dp(this, 8.0f);
        LinearLayout.LayoutParams c3 = new LinearLayout.LayoutParams(0, -2, 1.0f);
        c3.leftMargin = UiKit.INSTANCE.dp(this, 8.0f);
        LinearLayout.LayoutParams c4 = new LinearLayout.LayoutParams(0, -2, 1.0f);
        c4.leftMargin = UiKit.INSTANCE.dp(this, 8.0f);
        ctrlRow.addView(this.undoBtn, c1);
        ctrlRow.addView(this.confirmBtn, c2);
        ctrlRow.addView(restartBtn, c3);
        ctrlRow.addView(this.resignBtn, c4);
        content.addView(ctrlRow, UiKit.INSTANCE.margin(this, 0, 0, 0, UiKit.INSTANCE.dp(this, 10.0f)));
        this.recordTv = UiKit.INSTANCE.bodyText(this, "");
        TextView textView14 = this.recordTv;
        Intrinsics.checkNotNull(textView14);
        textView14.setGravity(17);
        content.addView(this.recordTv);
        setContentView(scaffold);
        this.game.reset();
        refreshModeButtons();
        refreshStatus();
        refreshRecord();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(GomokuActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.mode == 2) {
            return;
        }
        this$0.switchMode(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(GomokuActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.mode == 2) {
            return;
        }
        this$0.switchMode(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$2(GomokuActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.askServerUrl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$3(GomokuActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.askCreatePassword();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$4(GomokuActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.askJoinInfo();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$5(GomokuActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.exitOnline();
    }

    private final TextView modeButton(String text, boolean primary) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setGravity(17);
        tv.setTextSize(15.0f);
        tv.getPaint().setFakeBoldText(true);
        tv.setPadding(0, UiKit.INSTANCE.dp(this, 12.0f), 0, UiKit.INSTANCE.dp(this, 12.0f));
        styleModeBtn(tv, primary);
        return tv;
    }

    private final void styleModeBtn(TextView tv, boolean active) {
        GradientDrawable bg = new GradientDrawable();
        bg.setCornerRadius(UiKit.INSTANCE.dp(this, 14.0f));
        if (active) {
            bg.setColor(UiKit.INSTANCE.color(this, R.color.brand));
            tv.setTextColor(-1);
        } else {
            bg.setColor(UiKit.INSTANCE.color(this, R.color.card));
            bg.setStroke(1, UiKit.INSTANCE.color(this, R.color.line));
            tv.setTextColor(UiKit.INSTANCE.color(this, R.color.ink));
        }
        tv.setBackground(bg);
    }

    private final TextView ctrlButton(String text, final Function0<Unit> function0) {
        return UiKit.INSTANCE.button(this, text, false, new View.OnClickListener() { // from class: com.xiaofan.bangfan.GomokuActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GomokuActivity.ctrlButton$lambda$6(Function0.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ctrlButton$lambda$6(Function0 onClick, View it) {
        Intrinsics.checkNotNullParameter(onClick, "$onClick");
        onClick.invoke();
    }

    private final void switchMode(int toMode) {
        if (this.mode != toMode || this.mode == 2) {
            this.mode = toMode;
            this.aiThinking = false;
            refreshModeButtons();
            restartLocal();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshModeButtons() {
        TextView textView = this.modeAiBtn;
        Intrinsics.checkNotNull(textView);
        styleModeBtn(textView, this.mode == 0);
        TextView textView2 = this.modeLocalBtn;
        Intrinsics.checkNotNull(textView2);
        styleModeBtn(textView2, this.mode == 1);
        boolean dim = this.mode == 2;
        TextView textView3 = this.modeAiBtn;
        Intrinsics.checkNotNull(textView3);
        textView3.setEnabled(!dim);
        TextView textView4 = this.modeLocalBtn;
        Intrinsics.checkNotNull(textView4);
        textView4.setEnabled(dim ? false : true);
        TextView textView5 = this.modeAiBtn;
        Intrinsics.checkNotNull(textView5);
        textView5.setAlpha(dim ? 0.5f : 1.0f);
        TextView textView6 = this.modeLocalBtn;
        Intrinsics.checkNotNull(textView6);
        textView6.setAlpha(dim ? 0.5f : 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onBoardTap(int x, int y) {
        if (this.game.getGameOver() || this.aiThinking) {
            return;
        }
        if (this.mode != 0 || this.game.getCurrentPlayer() == 1) {
            if (this.mode == 2) {
                if (!this.net.isConnected()) {
                    Toast.makeText(this, "联机尚未建立", 0).show();
                    return;
                } else if (this.game.getCurrentPlayer() != this.net.getMyColor()) {
                    Toast.makeText(this, "请等待对方落子", 0).show();
                    return;
                }
            }
            if (this.game.getBoard()[y][x] != 0) {
                Toast.makeText(this, "此处已有棋子", 0).show();
                return;
            }
            this.pendingX = x;
            this.pendingY = y;
            GomokuView gomokuView = this.boardView;
            if (gomokuView != null) {
                gomokuView.setPending(x, y, this.game.getCurrentPlayer());
            }
            TextView textView = this.confirmBtn;
            if (textView != null) {
                textView.setEnabled(true);
            }
            TextView textView2 = this.confirmBtn;
            if (textView2 == null) {
                return;
            }
            textView2.setAlpha(1.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onConfirm() {
        if (this.pendingX < 0 || this.pendingY < 0 || this.game.getGameOver() || this.aiThinking) {
            return;
        }
        int x = this.pendingX;
        int y = this.pendingY;
        if (this.game.getBoard()[y][x] != 0) {
            clearPendingStone();
            return;
        }
        commitStone(x, y);
        clearPendingStone();
    }

    private final void clearPendingStone() {
        this.pendingX = -1;
        this.pendingY = -1;
        GomokuView gomokuView = this.boardView;
        if (gomokuView != null) {
            gomokuView.clearPending();
        }
        TextView textView = this.confirmBtn;
        if (textView != null) {
            textView.setEnabled(false);
        }
        TextView textView2 = this.confirmBtn;
        if (textView2 == null) {
            return;
        }
        textView2.setAlpha(0.5f);
    }

    private final void commitStone(int x, int y) {
        boolean moved = this.game.placeStone(x, y, this.game.getCurrentPlayer());
        if (moved) {
            if (this.mode == 2) {
                GomokuNet gomokuNet = this.net;
                GomokuGame.Move lastMove = this.game.getLastMove();
                Intrinsics.checkNotNull(lastMove);
                gomokuNet.sendMove(x, y, lastMove.getPlayer());
            }
            afterMove();
            if (this.mode != 0 || this.game.getGameOver() || this.game.getCurrentPlayer() != 2) {
                return;
            }
            triggerAi();
        }
    }

    private final void triggerAi() {
        this.aiThinking = true;
        refreshStatus();
        final int[][] snapshot = this.game.copyBoard();
        this.aiWorker.execute(new Runnable() { // from class: com.xiaofan.bangfan.GomokuActivity$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                GomokuActivity.triggerAi$lambda$8(snapshot, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void triggerAi$lambda$8(int[][] snapshot, final GomokuActivity this$0) {
        final GomokuAi.Candidate mv;
        Intrinsics.checkNotNullParameter(snapshot, "$snapshot");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            mv = GomokuAi.INSTANCE.bestMove(snapshot, 2);
        } catch (Throwable th) {
            mv = null;
        }
        this$0.runOnUiThread(new Runnable() { // from class: com.xiaofan.bangfan.GomokuActivity$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                GomokuActivity.triggerAi$lambda$8$lambda$7(GomokuActivity.this, mv);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void triggerAi$lambda$8$lambda$7(GomokuActivity this$0, GomokuAi.Candidate $mv) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.aiThinking = false;
        if ($mv != null && !this$0.game.getGameOver() && this$0.game.getCurrentPlayer() == 2) {
            this$0.game.placeStone($mv.getX(), $mv.getY(), 2);
        }
        this$0.afterMove();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void afterMove() {
        GomokuView gomokuView = this.boardView;
        if (gomokuView != null) {
            gomokuView.invalidate();
        }
        if (this.game.getGameOver() && this.mode == 0) {
            recordResultOnce();
        }
        refreshStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void onUndo() {
        int i = 0;
        if (this.aiThinking) {
            Toast.makeText(this, "AI 思考中，请稍候", 0).show();
        } else if (this.game.getGameOver()) {
            Toast.makeText(this, "本局已结束，请重开", 0).show();
        } else if (this.game.moveCount() == 0) {
            Toast.makeText(this, "还没有落子", 0).show();
        } else {
            switch (this.mode) {
                case 0:
                    while (i < 2) {
                        this.game.undo();
                        i++;
                    }
                    break;
                case 1:
                    this.game.undo();
                    break;
                case 2:
                    if (this.net.isConnected()) {
                        if (this.game.moveCount() >= 2) {
                            while (i < 2) {
                                this.game.undo();
                                i++;
                            }
                            this.net.sendUndo(2);
                            break;
                        } else {
                            Toast.makeText(this, "落子不足，无法悔棋", 0).show();
                            return;
                        }
                    } else {
                        return;
                    }
            }
            GomokuView gomokuView = this.boardView;
            if (gomokuView != null) {
                gomokuView.invalidate();
            }
            refreshStatus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onResign() {
        if (this.game.getGameOver() || this.game.moveCount() == 0) {
            Toast.makeText(this, "当前无需认输", 0).show();
            return;
        }
        if (this.mode == 2) {
            int p = this.net.getMyColor();
            this.game.resign(p);
            this.net.sendSurrender(p);
        } else {
            this.game.resign(this.game.getCurrentPlayer());
        }
        GomokuView gomokuView = this.boardView;
        if (gomokuView != null) {
            gomokuView.invalidate();
        }
        afterMove();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestRestart() {
        if (this.mode == 2 && this.net.isConnected()) {
            restartLocal();
            this.net.sendReset();
            return;
        }
        restartLocal();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void restartLocal() {
        this.game.reset();
        this.resultRecorded = false;
        this.aiThinking = false;
        clearPendingStone();
        GomokuView gomokuView = this.boardView;
        if (gomokuView != null) {
            gomokuView.invalidate();
        }
        refreshStatus();
    }

    private final void recordResultOnce() {
        if (this.resultRecorded) {
            return;
        }
        this.resultRecorded = true;
        AppPrefs.INSTANCE.addGomokuResult(this, this.game.getWinner() == 1, this.game.getWinner() == 0);
        refreshRecord();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("第 ").append(this.game.moveCount()).append(" 手 · ");
        boolean z = false;
        if (this.game.getGameOver()) {
            if (this.game.getWinner() == 0) {
                sb.append("平局");
            } else {
                sb.append(this.game.getWinner() != 1 ? "白棋" : "黑棋").append("获胜");
            }
        } else if (!this.aiThinking) {
            sb.append(this.game.getCurrentPlayer() != 1 ? "白棋" : "黑棋").append("落子");
            switch (this.mode) {
                case 0:
                    sb.append("（你执黑）");
                    break;
                case 2:
                    boolean mine = this.net.getMyColor() == this.game.getCurrentPlayer();
                    sb.append(mine ? "（轮到你）" : "（等待 " + this.opponentName + "）");
                    break;
            }
        } else {
            sb.append("AI 思考中…");
        }
        TextView textView = this.statusTv;
        if (textView != null) {
            textView.setText(sb.toString());
        }
        if (!this.game.getGameOver() && !this.aiThinking) {
            z = true;
        }
        boolean canAct = z;
        TextView textView2 = this.undoBtn;
        if (textView2 != null) {
            textView2.setEnabled(canAct);
        }
        TextView textView3 = this.resignBtn;
        if (textView3 != null) {
            textView3.setEnabled(canAct);
        }
        TextView textView4 = this.undoBtn;
        if (textView4 != null) {
            textView4.setAlpha(canAct ? 1.0f : 0.5f);
        }
        TextView textView5 = this.resignBtn;
        if (textView5 == null) {
            return;
        }
        textView5.setAlpha(canAct ? 1.0f : 0.5f);
    }

    private final void refreshRecord() {
        int w = AppPrefs.INSTANCE.gomokuWins(this);
        int l = AppPrefs.INSTANCE.gomokuLosses(this);
        int d = AppPrefs.INSTANCE.gomokuDraws(this);
        int total = w + l + d;
        int rate = total > 0 ? (w * 100) / total : 0;
        TextView textView = this.recordTv;
        if (textView == null) {
            return;
        }
        textView.setText("人机战绩：胜 " + w + " · 负 " + l + " · 平 " + d + " · 胜率 " + rate + "%");
    }

    static /* synthetic */ EditText inputBox$default(GomokuActivity gomokuActivity, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return gomokuActivity.inputBox(str, z);
    }

    private final EditText inputBox(String hint, boolean numeric) {
        EditText et = new EditText(this);
        et.setHint(hint);
        et.setSingleLine(true);
        if (numeric) {
            et.setInputType(2);
        }
        int pad = UiKit.INSTANCE.dp(this, 20.0f);
        et.setPadding(pad, UiKit.INSTANCE.dp(this, 12.0f), pad, UiKit.INSTANCE.dp(this, 12.0f));
        return et;
    }

    private final void askServerUrl() {
        final EditText et = inputBox$default(this, "wss:// 或 ws:// 开头的 PeerJS 信令地址", false, 2, null);
        et.setText(AppPrefs.INSTANCE.gomokuServer(this));
        new AlertDialog.Builder(this).setTitle("联机服务器").setMessage("默认使用官方公共云；若公共云不稳定，可填写自建 PeerJS 服务器地址（双方需使用同一服务器）。").setView(et).setPositiveButton("保存", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.GomokuActivity$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                GomokuActivity.askServerUrl$lambda$11(et, this, dialogInterface, i);
            }
        }).setNeutralButton("恢复默认", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.GomokuActivity$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                GomokuActivity.askServerUrl$lambda$12(GomokuActivity.this, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
        showKeyboard(et);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void askServerUrl$lambda$11(EditText et, GomokuActivity this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(et, "$et");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String v = StringsKt.trimEnd(StringsKt.trim((CharSequence) et.getText().toString()).toString(), '/');
        if (v.length() == 0) {
            v = AppPrefs.DEFAULT_GOMOKU_SERVER;
        }
        if (!StringsKt.startsWith$default(v, "ws://", false, 2, (Object) null) && !StringsKt.startsWith$default(v, "wss://", false, 2, (Object) null)) {
            Toast.makeText(this$0, "地址需以 ws:// 或 wss:// 开头", 0).show();
            return;
        }
        AppPrefs.INSTANCE.setGomokuServer(this$0, v);
        Toast.makeText(this$0, "已保存，重新创建/加入房间后生效", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void askServerUrl$lambda$12(GomokuActivity this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppPrefs.INSTANCE.setGomokuServer(this$0, AppPrefs.DEFAULT_GOMOKU_SERVER);
        Toast.makeText(this$0, "已恢复官方公共云", 0).show();
    }

    private final void askCreatePassword() {
        final EditText et = inputBox$default(this, "设置房间密码（好友加入需输入）", false, 2, null);
        new AlertDialog.Builder(this).setTitle("创建房间").setView(et).setPositiveButton("创建", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.GomokuActivity$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                GomokuActivity.askCreatePassword$lambda$13(et, this, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
        showKeyboard(et);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void askCreatePassword$lambda$13(EditText et, GomokuActivity this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(et, "$et");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String pwd = StringsKt.trim((CharSequence) et.getText().toString()).toString();
        if (pwd.length() == 0) {
            Toast.makeText(this$0, "请设置房间密码", 0).show();
        } else {
            this$0.doCreate(pwd);
        }
    }

    private final void askJoinInfo() {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(1);
        UiKit.INSTANCE.dp(this, 20.0f);
        final EditText codeEt = inputBox("输入 6 位数字房间号", true);
        final EditText pwdEt = inputBox$default(this, "输入房间密码", false, 2, null);
        box.addView(codeEt);
        box.addView(pwdEt);
        new AlertDialog.Builder(this).setTitle("加入房间").setView(box).setPositiveButton("加入", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.GomokuActivity$$ExternalSyntheticLambda6
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                GomokuActivity.askJoinInfo$lambda$15(codeEt, pwdEt, this, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
        showKeyboard(codeEt);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void askJoinInfo$lambda$15(EditText codeEt, EditText pwdEt, GomokuActivity this$0, DialogInterface dialogInterface, int i) {
        CharSequence $this$all$iv;
        Intrinsics.checkNotNullParameter(codeEt, "$codeEt");
        Intrinsics.checkNotNullParameter(pwdEt, "$pwdEt");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String code = StringsKt.trim((CharSequence) codeEt.getText().toString()).toString();
        String pwd = StringsKt.trim((CharSequence) pwdEt.getText().toString()).toString();
        if (code.length() == 6) {
            String $this$all$iv2 = code;
            int i2 = 0;
            while (true) {
                if (i2 >= $this$all$iv2.length()) {
                    $this$all$iv = 1;
                    break;
                }
                char element$iv = $this$all$iv2.charAt(i2);
                if (!Character.isDigit(element$iv)) {
                    $this$all$iv = null;
                    break;
                }
                i2++;
            }
            if ($this$all$iv != null) {
                if (pwd.length() == 0) {
                    Toast.makeText(this$0, "请输入房间密码", 0).show();
                    return;
                } else {
                    this$0.doJoin(code, pwd);
                    return;
                }
            }
        }
        Toast.makeText(this$0, "房间号应为 6 位数字", 0).show();
    }

    private final void showKeyboard(EditText et) {
        et.requestFocus();
        try {
            Object systemService = getSystemService("input_method");
            InputMethodManager imm = systemService instanceof InputMethodManager ? (InputMethodManager) systemService : null;
            if (imm != null) {
                imm.showSoftInput(et, 0);
            }
        } catch (Throwable th) {
        }
    }

    private final void doCreate(String pwd) {
        this.mode = 2;
        refreshModeButtons();
        this.game.reset();
        GomokuView gomokuView = this.boardView;
        if (gomokuView != null) {
            gomokuView.invalidate();
        }
        refreshStatus();
        String code = this.net.createRoom(pwd, AppPrefs.INSTANCE.nickname(this), this.netListener);
        showWaiting("房间号 " + code + "\n\n请把 6 位房间号和密码告诉好友，等待加入…");
    }

    private final void doJoin(String code, String pwd) {
        this.mode = 2;
        refreshModeButtons();
        this.game.reset();
        GomokuView gomokuView = this.boardView;
        if (gomokuView != null) {
            gomokuView.invalidate();
        }
        refreshStatus();
        this.net.joinRoom(code, pwd, AppPrefs.INSTANCE.nickname(this), this.netListener);
        showWaiting("正在加入房间 " + code + " …");
    }

    private final void showWaiting(String text) {
        AlertDialog alertDialog = this.waitingDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        this.waitingDialog = new AlertDialog.Builder(this).setTitle("联机中").setMessage(text).setCancelable(false).setNegativeButton("取消", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.GomokuActivity$$ExternalSyntheticLambda7
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                GomokuActivity.showWaiting$lambda$16(GomokuActivity.this, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showWaiting$lambda$16(GomokuActivity this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.exitOnline();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void dismissWaiting() {
        try {
            AlertDialog alertDialog = this.waitingDialog;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
        } catch (Throwable th) {
        }
        this.waitingDialog = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void exitOnline() {
        this.net.leave();
        this.mode = 0;
        this.opponentName = "棋友";
        TextView textView = this.leaveBtn;
        if (textView != null) {
            textView.setVisibility(8);
        }
        TextView textView2 = this.serverBtn;
        if (textView2 != null) {
            textView2.setVisibility(0);
        }
        TextView textView3 = this.netStatusTv;
        if (textView3 != null) {
            textView3.setText("尚未联机");
        }
        dismissWaiting();
        refreshModeButtons();
        restartLocal();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.net.leave();
        this.aiWorker.shutdownNow();
    }
}
