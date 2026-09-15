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
import com.xiaofan.bangfan.GameNet;
import com.xiaofan.bangfan.GoView;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: GoActivity.kt */
@Metadata(d1 = {"\u0000i\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0012*\u0001\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020!H\u0002J\b\u0010#\u001a\u00020!H\u0002J\b\u0010$\u001a\u00020!H\u0002J\u0018\u0010%\u001a\u00020!2\u0006\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u0010H\u0002J\b\u0010(\u001a\u00020!H\u0002J\b\u0010)\u001a\u00020!H\u0002J\b\u0010*\u001a\u00020!H\u0002J\u001a\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u001a2\b\b\u0002\u0010.\u001a\u00020\u0004H\u0002J\u0010\u0010/\u001a\u00020!2\u0006\u00100\u001a\u00020,H\u0002J\u0018\u00101\u001a\u00020\u000b2\u0006\u00102\u001a\u00020\u001a2\u0006\u00103\u001a\u00020\u0004H\u0002J\b\u00104\u001a\u00020!H\u0002J\u0012\u00105\u001a\u00020!2\b\u00106\u001a\u0004\u0018\u000107H\u0014J\b\u00108\u001a\u00020!H\u0014J\u0018\u00109\u001a\u00020!2\u0006\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u0010H\u0002J\b\u0010:\u001a\u00020!H\u0002J\b\u0010;\u001a\u00020!H\u0002J\b\u0010<\u001a\u00020!H\u0002J\b\u0010=\u001a\u00020!H\u0002J\b\u0010>\u001a\u00020!H\u0002J\b\u0010?\u001a\u00020!H\u0002J\u0010\u0010@\u001a\u00020!2\u0006\u0010A\u001a\u00020\u001aH\u0002J\u0018\u0010B\u001a\u00020!2\u0006\u0010C\u001a\u00020\u000b2\u0006\u0010D\u001a\u00020\u0004H\u0002J\u0010\u0010E\u001a\u00020!2\u0006\u0010F\u001a\u00020\u0010H\u0002J\u0010\u0010G\u001a\u00020!2\u0006\u0010F\u001a\u00020\u001aH\u0002J\b\u0010H\u001a\u00020!H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006I"}, d2 = {"Lcom/xiaofan/bangfan/GoActivity;", "Lcom/xiaofan/bangfan/BaseActivity;", "()V", "aiThinking", "", "aiWorker", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "boardView", "Lcom/xiaofan/bangfan/GoView;", "confirmBtn", "Landroid/widget/TextView;", "game", "Lcom/xiaofan/bangfan/GoGame;", "leaveBtn", "mode", "", "modeAiBtn", "modeLocalBtn", "net", "Lcom/xiaofan/bangfan/GameNet;", "netListener", "com/xiaofan/bangfan/GoActivity$netListener$1", "Lcom/xiaofan/bangfan/GoActivity$netListener$1;", "netStatusTv", "opponentName", "", "pendingC", "pendingR", "statusTv", "waitingDialog", "Landroid/app/AlertDialog;", "afterMove", "", "askCreate", "askJoin", "clearPending", "commitPlace", "r", "c", "dismissWaiting", "doPass", "exitOnline", "input", "Landroid/widget/EditText;", "hint", "numeric", "keyboard", "et", "modeButton", "text", "primary", "onConfirm", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onTap", "onUndo", "refreshModeButtons", "refreshStatus", "requestRestart", "restartLocal", "showScore", "showWaiting", "t", "styleBtn", "tv", "active", "switchMode", "m", "toast", "triggerAi", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class GoActivity extends BaseActivity {
    private boolean aiThinking;
    private GoView boardView;
    private TextView confirmBtn;
    private TextView leaveBtn;
    private int mode;
    private TextView modeAiBtn;
    private TextView modeLocalBtn;
    private TextView netStatusTv;
    private TextView statusTv;
    private AlertDialog waitingDialog;
    private final GoGame game = new GoGame(9);
    private int pendingR = -1;
    private int pendingC = -1;
    private final ExecutorService aiWorker = Executors.newSingleThreadExecutor();
    private final GameNet net = new GameNet(this, "go");
    private String opponentName = "棋友";
    private final GoActivity$netListener$1 netListener = new GameNet.Listener() { // from class: com.xiaofan.bangfan.GoActivity$netListener$1
        /* JADX WARN: Code restructure failed: missing block: B:10:0x0025, code lost:
            r0 = r2.this$0.waitingDialog;
         */
        @Override // com.xiaofan.bangfan.GameNet.Listener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onStateChanged(com.xiaofan.bangfan.GomokuNet.State r3, java.lang.String r4) {
            /*
                r2 = this;
                java.lang.String r0 = "s"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                java.lang.String r0 = "info"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                com.xiaofan.bangfan.GoActivity r0 = com.xiaofan.bangfan.GoActivity.this
                android.widget.TextView r0 = com.xiaofan.bangfan.GoActivity.access$getNetStatusTv$p(r0)
                if (r0 != 0) goto L13
                goto L19
            L13:
                r1 = r4
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                r0.setText(r1)
            L19:
                com.xiaofan.bangfan.GomokuNet$State r0 = com.xiaofan.bangfan.GomokuNet.State.WAITING_OPPONENT
                if (r3 != r0) goto L33
                com.xiaofan.bangfan.GoActivity r0 = com.xiaofan.bangfan.GoActivity.this
                android.app.AlertDialog r0 = com.xiaofan.bangfan.GoActivity.access$getWaitingDialog$p(r0)
                if (r0 == 0) goto L33
                com.xiaofan.bangfan.GoActivity r0 = com.xiaofan.bangfan.GoActivity.this
                android.app.AlertDialog r0 = com.xiaofan.bangfan.GoActivity.access$getWaitingDialog$p(r0)
                if (r0 == 0) goto L33
                r1 = r4
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                r0.setMessage(r1)
            L33:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.GoActivity$netListener$1.onStateChanged(com.xiaofan.bangfan.GomokuNet$State, java.lang.String):void");
        }

        @Override // com.xiaofan.bangfan.GameNet.Listener
        public void onAuthorized(int mySeat, String opponentName) {
            TextView textView;
            TextView textView2;
            Intrinsics.checkNotNullParameter(opponentName, "opponentName");
            GoActivity.this.opponentName = opponentName;
            GoActivity.this.dismissWaiting();
            GoActivity.this.mode = 2;
            GoActivity.this.refreshModeButtons();
            GoActivity.this.restartLocal();
            textView = GoActivity.this.leaveBtn;
            if (textView != null) {
                textView.setVisibility(0);
            }
            String side = mySeat == 0 ? "你执黑先手" : "你执白后手";
            textView2 = GoActivity.this.netStatusTv;
            if (textView2 != null) {
                textView2.setText("已与 " + opponentName + " 联机 · " + side);
            }
            GoActivity.this.toast("联机成功，" + side);
        }

        @Override // com.xiaofan.bangfan.GameNet.Listener
        public void onRemotePayload(String payload) {
            GoGame goGame;
            GoGame goGame2;
            GoGame goGame3;
            Intrinsics.checkNotNullParameter(payload, "payload");
            List p = StringsKt.split$default((CharSequence) payload, new String[]{","}, false, 0, 6, (Object) null);
            try {
                String str = (String) p.get(0);
                if (Intrinsics.areEqual(str, "P")) {
                    goGame3 = GoActivity.this.game;
                    goGame3.pass();
                } else if (Intrinsics.areEqual(str, "M") && p.size() == 3) {
                    goGame = GoActivity.this.game;
                    goGame.place(Integer.parseInt((String) p.get(1)), Integer.parseInt((String) p.get(2)));
                }
                GoActivity.this.afterMove();
                goGame2 = GoActivity.this.game;
                if (goGame2.getGameOver()) {
                    GoActivity.this.showScore();
                }
            } catch (Throwable th) {
            }
        }

        @Override // com.xiaofan.bangfan.GameNet.Listener
        public void onRemoteReset() {
            GoActivity.this.restartLocal();
        }

        @Override // com.xiaofan.bangfan.GameNet.Listener
        public void onRemoteSurrender(int bySeat) {
            GoGame goGame;
            GoActivity.this.toast("对方认输，你赢了");
            goGame = GoActivity.this.game;
            goGame.endGame();
            GoActivity.this.refreshStatus();
        }

        @Override // com.xiaofan.bangfan.GameNet.Listener
        public void onPeerLeft() {
            GoActivity.this.toast("对方已离开房间");
            GoActivity.this.exitOnline();
        }

        @Override // com.xiaofan.bangfan.GameNet.Listener
        public void onError(String message) {
            GameNet gameNet;
            TextView textView;
            Intrinsics.checkNotNullParameter(message, "message");
            GoActivity.this.toast(message);
            gameNet = GoActivity.this.net;
            if (!gameNet.isConnected()) {
                GoActivity.this.exitOnline();
                textView = GoActivity.this.netStatusTv;
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
        ScrollView scaffold = UiKit.INSTANCE.scaffold(this, "围棋（9 路）", "人机 / 同屏 / 联机，围空多者胜");
        LinearLayout content = UiKit.INSTANCE.contentOf(scaffold);
        LinearLayout modeRow = new LinearLayout(this);
        modeRow.setOrientation(0);
        TextView aiBtn = modeButton("人机对战", true);
        TextView localBtn = modeButton("同屏对战", false);
        aiBtn.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.GoActivity$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GoActivity.onCreate$lambda$0(GoActivity.this, view);
            }
        });
        localBtn.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.GoActivity$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GoActivity.onCreate$lambda$1(GoActivity.this, view);
            }
        });
        this.modeAiBtn = aiBtn;
        this.modeLocalBtn = localBtn;
        LinearLayout.LayoutParams l1 = new LinearLayout.LayoutParams(0, -2, 1.0f);
        LinearLayout.LayoutParams l2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
        l2.leftMargin = UiKit.INSTANCE.dp(this, 10.0f);
        modeRow.addView(aiBtn, l1);
        modeRow.addView(localBtn, l2);
        content.addView(modeRow, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 4.0f), 0, UiKit.INSTANCE.dp(this, 10.0f)));
        LinearLayout onlineCard = UiKit.INSTANCE.card(this);
        onlineCard.addView(UiKit.INSTANCE.cardTitle(this, "联机对战"));
        onlineCard.addView(UiKit.INSTANCE.bodyText(this, "创建房间生成 6 位房间号，好友输入房间号与密码联机；真实 ID 经哈希隐藏。"));
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(0);
        TextView create = UiKit.INSTANCE.button(this, "创建房间", true, new View.OnClickListener() { // from class: com.xiaofan.bangfan.GoActivity$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GoActivity.onCreate$lambda$2(GoActivity.this, view);
            }
        });
        TextView join = UiKit.INSTANCE.button(this, "加入房间", false, new View.OnClickListener() { // from class: com.xiaofan.bangfan.GoActivity$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GoActivity.onCreate$lambda$3(GoActivity.this, view);
            }
        });
        LinearLayout.LayoutParams o1 = new LinearLayout.LayoutParams(0, -2, 1.0f);
        LinearLayout.LayoutParams o2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
        o2.leftMargin = UiKit.INSTANCE.dp(this, 10.0f);
        row.addView(create, o1);
        row.addView(join, o2);
        onlineCard.addView(row, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 10.0f), 0, 0));
        this.netStatusTv = UiKit.INSTANCE.bodyText(this, "尚未联机");
        TextView textView = this.netStatusTv;
        Intrinsics.checkNotNull(textView);
        textView.setGravity(17);
        onlineCard.addView(this.netStatusTv, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 8.0f), 0, 0));
        this.leaveBtn = UiKit.INSTANCE.button(this, "离开房间", false, new View.OnClickListener() { // from class: com.xiaofan.bangfan.GoActivity$$ExternalSyntheticLambda15
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GoActivity.onCreate$lambda$4(GoActivity.this, view);
            }
        });
        TextView textView2 = this.leaveBtn;
        Intrinsics.checkNotNull(textView2);
        textView2.setVisibility(8);
        onlineCard.addView(this.leaveBtn, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 8.0f), 0, 0));
        content.addView(onlineCard, UiKit.INSTANCE.margin(this, 0, 0, 0, UiKit.INSTANCE.dp(this, 10.0f)));
        this.statusTv = UiKit.INSTANCE.cardTitle(this, "");
        TextView textView3 = this.statusTv;
        Intrinsics.checkNotNull(textView3);
        textView3.setGravity(17);
        content.addView(this.statusTv, UiKit.INSTANCE.margin(this, 0, 0, 0, UiKit.INSTANCE.dp(this, 8.0f)));
        LinearLayout boardCard = UiKit.INSTANCE.card(this);
        this.boardView = new GoView(this);
        GoView goView = this.boardView;
        Intrinsics.checkNotNull(goView);
        goView.setGame(this.game);
        GoView goView2 = this.boardView;
        Intrinsics.checkNotNull(goView2);
        goView2.setListener(new GoView.Listener() { // from class: com.xiaofan.bangfan.GoActivity$onCreate$4
            @Override // com.xiaofan.bangfan.GoView.Listener
            public void onCellTap(int r, int c) {
                GoActivity.this.onTap(r, c);
            }
        });
        int w = getResources().getDisplayMetrics().widthPixels - UiKit.INSTANCE.dp(this, 64.0f);
        boardCard.addView(this.boardView, new LinearLayout.LayoutParams(w, w));
        content.addView(boardCard, UiKit.INSTANCE.margin(this, 0, 0, 0, UiKit.INSTANCE.dp(this, 10.0f)));
        LinearLayout ctrl = new LinearLayout(this);
        ctrl.setOrientation(0);
        TextView pass = UiKit.INSTANCE.button(this, "虚着", false, new View.OnClickListener() { // from class: com.xiaofan.bangfan.GoActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GoActivity.onCreate$lambda$5(GoActivity.this, view);
            }
        });
        this.confirmBtn = UiKit.INSTANCE.button(this, "确认落子", true, new View.OnClickListener() { // from class: com.xiaofan.bangfan.GoActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GoActivity.onCreate$lambda$6(GoActivity.this, view);
            }
        });
        TextView textView4 = this.confirmBtn;
        Intrinsics.checkNotNull(textView4);
        textView4.setEnabled(false);
        TextView textView5 = this.confirmBtn;
        Intrinsics.checkNotNull(textView5);
        textView5.setAlpha(0.5f);
        TextView undo = UiKit.INSTANCE.button(this, "悔棋", false, new View.OnClickListener() { // from class: com.xiaofan.bangfan.GoActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GoActivity.onCreate$lambda$7(GoActivity.this, view);
            }
        });
        TextView score = UiKit.INSTANCE.button(this, "点目", false, new View.OnClickListener() { // from class: com.xiaofan.bangfan.GoActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GoActivity.onCreate$lambda$8(GoActivity.this, view);
            }
        });
        TextView restart = UiKit.INSTANCE.button(this, "重开", false, new View.OnClickListener() { // from class: com.xiaofan.bangfan.GoActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GoActivity.onCreate$lambda$9(GoActivity.this, view);
            }
        });
        TextView textView6 = this.confirmBtn;
        Intrinsics.checkNotNull(textView6);
        TextView[] cs = {pass, textView6, undo, score, restart};
        int i = 0;
        int length = cs.length;
        while (i < length) {
            TextView pass2 = pass;
            TextView undo2 = undo;
            TextView score2 = score;
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, -2, 1.0f);
            if (i > 0) {
                lp.leftMargin = UiKit.INSTANCE.dp(this, 5.0f);
            }
            ctrl.addView(cs[i], lp);
            i++;
            pass = pass2;
            undo = undo2;
            score = score2;
        }
        content.addView(ctrl);
        setContentView(scaffold);
        restartLocal();
        refreshModeButtons();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(GoActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.mode != 2) {
            this$0.switchMode(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(GoActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.mode != 2) {
            this$0.switchMode(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$2(GoActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.askCreate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$3(GoActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.askJoin();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$4(GoActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.exitOnline();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$5(GoActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.doPass();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$6(GoActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onConfirm();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$7(GoActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onUndo();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$8(GoActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showScore();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$9(GoActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requestRestart();
    }

    private final TextView modeButton(String text, boolean primary) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setGravity(17);
        tv.setTextSize(15.0f);
        tv.getPaint().setFakeBoldText(true);
        tv.setPadding(0, UiKit.INSTANCE.dp(this, 12.0f), 0, UiKit.INSTANCE.dp(this, 12.0f));
        styleBtn(tv, primary);
        return tv;
    }

    private final void styleBtn(TextView tv, boolean active) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshModeButtons() {
        TextView it = this.modeAiBtn;
        if (it != null) {
            styleBtn(it, this.mode == 0);
        }
        TextView it2 = this.modeLocalBtn;
        if (it2 != null) {
            styleBtn(it2, this.mode == 1);
        }
        boolean dim = this.mode == 2;
        TextView textView = this.modeAiBtn;
        if (textView != null) {
            textView.setEnabled(!dim);
        }
        TextView textView2 = this.modeLocalBtn;
        if (textView2 != null) {
            textView2.setEnabled(dim ? false : true);
        }
        TextView textView3 = this.modeAiBtn;
        if (textView3 != null) {
            textView3.setAlpha(dim ? 0.5f : 1.0f);
        }
        TextView textView4 = this.modeLocalBtn;
        if (textView4 == null) {
            return;
        }
        textView4.setAlpha(dim ? 0.5f : 1.0f);
    }

    private final void switchMode(int m) {
        if (this.mode == m) {
            return;
        }
        this.mode = m;
        refreshModeButtons();
        restartLocal();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onTap(int r, int c) {
        if (this.game.getGameOver() || this.aiThinking) {
            return;
        }
        if (this.mode != 0 || this.game.getTurn() == 1) {
            if (this.mode == 2) {
                if (!this.net.isConnected()) {
                    toast("联机尚未建立");
                    return;
                }
                int myColor = this.net.getMySeat() == 0 ? 1 : 2;
                if (this.game.getTurn() != myColor) {
                    toast("请等待对方落子");
                    return;
                }
            }
            if (!this.game.canPlace(r, c, this.game.getTurn())) {
                toast("此处不能落子（自杀/劫/重复）");
                return;
            }
            this.pendingR = r;
            this.pendingC = c;
            GoView goView = this.boardView;
            if (goView != null) {
                goView.setPending(r, c, this.game.getTurn());
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

    private final void onConfirm() {
        if (this.pendingR < 0 || this.game.getGameOver() || this.aiThinking) {
            return;
        }
        int r = this.pendingR;
        int c = this.pendingC;
        commitPlace(r, c);
        clearPending();
    }

    private final void clearPending() {
        this.pendingR = -1;
        this.pendingC = -1;
        GoView goView = this.boardView;
        if (goView != null) {
            goView.clearPending();
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

    private final void commitPlace(int r, int c) {
        if (!this.game.place(r, c)) {
            toast("此处不能落子（自杀/劫/重复）");
            return;
        }
        if (this.mode == 2) {
            this.net.sendPayload("M," + r + "," + c);
        }
        afterMove();
        if (this.mode != 0 || this.game.getGameOver() || this.game.getTurn() != 2) {
            return;
        }
        triggerAi();
    }

    private final void doPass() {
        if (this.game.getGameOver() || this.aiThinking) {
            return;
        }
        if (this.mode == 2) {
            if (!this.net.isConnected()) {
                return;
            }
            int myColor = this.net.getMySeat() == 0 ? 1 : 2;
            if (this.game.getTurn() != myColor) {
                toast("还没轮到你");
                return;
            }
            this.net.sendPayload("P");
        }
        this.game.pass();
        afterMove();
        if (this.mode != 0 || this.game.getGameOver() || this.game.getTurn() != 2) {
            if (this.game.getGameOver()) {
                showScore();
                return;
            }
            return;
        }
        triggerAi();
    }

    private final void triggerAi() {
        this.aiThinking = true;
        refreshStatus();
        this.aiWorker.execute(new Runnable() { // from class: com.xiaofan.bangfan.GoActivity$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                GoActivity.triggerAi$lambda$13(GoActivity.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void triggerAi$lambda$13(final GoActivity this$0) {
        final int[] mv;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            mv = GoAi.INSTANCE.bestMove(this$0.game, 2);
        } catch (Throwable th) {
            mv = null;
        }
        this$0.runOnUiThread(new Runnable() { // from class: com.xiaofan.bangfan.GoActivity$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                GoActivity.triggerAi$lambda$13$lambda$12(GoActivity.this, mv);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void triggerAi$lambda$13$lambda$12(GoActivity this$0, int[] $mv) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.aiThinking = false;
        if (!this$0.game.getGameOver() && this$0.game.getTurn() == 2) {
            if ($mv != null) {
                this$0.game.place($mv[0], $mv[1]);
            } else {
                this$0.game.pass();
            }
        }
        this$0.afterMove();
        if (this$0.game.getGameOver()) {
            this$0.showScore();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void afterMove() {
        GoView goView = this.boardView;
        if (goView != null) {
            goView.invalidate();
        }
        refreshStatus();
    }

    private final void onUndo() {
        if (this.aiThinking) {
            toast("AI 思考中");
            return;
        }
        switch (this.mode) {
            case 0:
                this.game.undoTwo();
                break;
            case 1:
                this.game.undoLast();
                break;
            case 2:
                toast("联机对局不支持悔棋");
                break;
        }
        GoView goView = this.boardView;
        if (goView != null) {
            goView.invalidate();
        }
        refreshStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showScore() {
        String str;
        StringBuilder append;
        this.game.endGame();
        int[] s = this.game.score();
        int capB = this.game.getCaptures()[2];
        int capW = this.game.getCaptures()[1];
        int i = s[0];
        int i2 = s[1];
        if (s[0] > s[1]) {
            append = new StringBuilder().append("黑棋领先 ").append(s[0] - s[1]);
        } else if (s[1] > s[0]) {
            append = new StringBuilder().append("白棋领先 ").append(s[1] - s[0]);
        } else {
            str = "和棋";
            String msg = "黑：地 " + i + "（提白 " + capB + "）\n白：地 " + i2 + "（提黑 " + capW + "，未计贴目）\n" + str;
            new AlertDialog.Builder(this).setTitle("点目结果").setMessage(msg).setPositiveButton("再来一局", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.GoActivity$$ExternalSyntheticLambda9
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i3) {
                    GoActivity.showScore$lambda$14(GoActivity.this, dialogInterface, i3);
                }
            }).setNegativeButton("看看棋盘", (DialogInterface.OnClickListener) null).show();
            refreshStatus();
        }
        str = append.append(" 子").toString();
        String msg2 = "黑：地 " + i + "（提白 " + capB + "）\n白：地 " + i2 + "（提黑 " + capW + "，未计贴目）\n" + str;
        new AlertDialog.Builder(this).setTitle("点目结果").setMessage(msg2).setPositiveButton("再来一局", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.GoActivity$$ExternalSyntheticLambda9
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i3) {
                GoActivity.showScore$lambda$14(GoActivity.this, dialogInterface, i3);
            }
        }).setNegativeButton("看看棋盘", (DialogInterface.OnClickListener) null).show();
        refreshStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showScore$lambda$14(GoActivity this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requestRestart();
    }

    private final void requestRestart() {
        if (this.mode != 2 || !this.net.isConnected()) {
            restartLocal();
            return;
        }
        restartLocal();
        this.net.sendReset();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void restartLocal() {
        this.game.reset();
        this.aiThinking = false;
        clearPending();
        GoView goView = this.boardView;
        if (goView != null) {
            goView.invalidate();
        }
        refreshStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshStatus() {
        StringBuilder sb = new StringBuilder();
        if (!this.game.getGameOver()) {
            if (!this.aiThinking) {
                sb.append(this.game.getTurn() == 1 ? "黑方" : "白方").append("落子");
                switch (this.mode) {
                    case 0:
                        sb.append("（你执黑）");
                        break;
                    case 2:
                        int myColor = this.net.getMySeat() == 0 ? 1 : 2;
                        sb.append(this.game.getTurn() == myColor ? "（轮到你）" : "（等待 " + this.opponentName + "）");
                        break;
                }
            } else {
                sb.append("AI 思考中…");
            }
        } else {
            sb.append("对局结束，点目看结果");
        }
        sb.append(" · 黑提 " + this.game.getCaptures()[2] + " / 白提 " + this.game.getCaptures()[1]);
        TextView textView = this.statusTv;
        if (textView == null) {
            return;
        }
        textView.setText(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toast(String m) {
        Toast.makeText(this, m, 0).show();
    }

    static /* synthetic */ EditText input$default(GoActivity goActivity, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return goActivity.input(str, z);
    }

    private final EditText input(String hint, boolean numeric) {
        EditText et = new EditText(this);
        et.setHint(hint);
        et.setSingleLine(true);
        if (numeric) {
            et.setInputType(2);
        }
        int p = UiKit.INSTANCE.dp(this, 20.0f);
        et.setPadding(p, UiKit.INSTANCE.dp(this, 12.0f), p, UiKit.INSTANCE.dp(this, 12.0f));
        return et;
    }

    private final void keyboard(EditText et) {
        et.requestFocus();
        try {
            Object systemService = getSystemService("input_method");
            InputMethodManager inputMethodManager = systemService instanceof InputMethodManager ? (InputMethodManager) systemService : null;
            if (inputMethodManager != null) {
                inputMethodManager.showSoftInput(et, 0);
            }
        } catch (Throwable th) {
        }
    }

    private final void askCreate() {
        final EditText et = input$default(this, "设置房间密码（好友加入需输入）", false, 2, null);
        new AlertDialog.Builder(this).setTitle("创建房间").setView(et).setPositiveButton("创建", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.GoActivity$$ExternalSyntheticLambda10
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                GoActivity.askCreate$lambda$15(et, this, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
        keyboard(et);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void askCreate$lambda$15(EditText et, GoActivity this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(et, "$et");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String pwd = StringsKt.trim((CharSequence) et.getText().toString()).toString();
        if (pwd.length() == 0) {
            this$0.toast("请设置房间密码");
            return;
        }
        this$0.mode = 2;
        this$0.refreshModeButtons();
        this$0.restartLocal();
        String code = this$0.net.createRoom(pwd, AppPrefs.INSTANCE.nickname(this$0), this$0.netListener);
        this$0.showWaiting("房间号 " + code + "\n\n把 6 位房间号和密码告诉好友，黑方先手…");
    }

    private final void askJoin() {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(1);
        final EditText code = input("输入 6 位数字房间号", true);
        final EditText pwd = input$default(this, "输入房间密码", false, 2, null);
        box.addView(code);
        box.addView(pwd);
        new AlertDialog.Builder(this).setTitle("加入房间").setView(box).setPositiveButton("加入", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.GoActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                GoActivity.askJoin$lambda$18(code, pwd, this, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
        keyboard(code);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void askJoin$lambda$18(EditText code, EditText pwd, GoActivity this$0, DialogInterface dialogInterface, int i) {
        CharSequence $this$all$iv;
        Intrinsics.checkNotNullParameter(code, "$code");
        Intrinsics.checkNotNullParameter(pwd, "$pwd");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String c = StringsKt.trim((CharSequence) code.getText().toString()).toString();
        String p = StringsKt.trim((CharSequence) pwd.getText().toString()).toString();
        if (c.length() == 6) {
            String $this$all$iv2 = c;
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
                if (p.length() == 0) {
                    this$0.toast("请输入房间密码");
                    return;
                }
                this$0.mode = 2;
                this$0.refreshModeButtons();
                this$0.restartLocal();
                this$0.net.joinRoom(c, p, AppPrefs.INSTANCE.nickname(this$0), this$0.netListener);
                this$0.showWaiting("正在加入房间 " + c + " …");
                return;
            }
        }
        this$0.toast("房间号应为 6 位数字");
    }

    private final void showWaiting(String t) {
        AlertDialog alertDialog = this.waitingDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        this.waitingDialog = new AlertDialog.Builder(this).setTitle("联机中").setMessage(t).setCancelable(false).setNegativeButton("取消", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.GoActivity$$ExternalSyntheticLambda7
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                GoActivity.showWaiting$lambda$20(GoActivity.this, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showWaiting$lambda$20(GoActivity this$0, DialogInterface dialogInterface, int i) {
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
        TextView textView2 = this.netStatusTv;
        if (textView2 != null) {
            textView2.setText("尚未联机");
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
