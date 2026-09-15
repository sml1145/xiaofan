package com.xiaofan.bangfan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.DdzRules;
import com.xiaofan.bangfan.GameNet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;
/* compiled from: DdzActivity.kt */
@Metadata(d1 = {"\u0000\u008f\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0017*\u0001\u001d\u0018\u00002\u00020\u0001:\u0001lB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010-\u001a\u00020\u0016H\u0002J\u0010\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u0016H\u0002J\b\u00101\u001a\u00020\u0016H\u0002J\b\u00102\u001a\u00020\u0016H\u0002J\u0010\u00103\u001a\u00020/2\u0006\u00100\u001a\u00020\u0016H\u0002J\u0010\u00104\u001a\u00020/2\u0006\u00100\u001a\u00020\u0016H\u0002J\u001e\u00105\u001a\u00020/2\u0006\u00100\u001a\u00020\u00162\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u001607H\u0002J\u0016\u00108\u001a\u00020/2\f\u00109\u001a\b\u0012\u0004\u0012\u00020\u001007H\u0002J\b\u0010:\u001a\u00020/H\u0002J\b\u0010;\u001a\u00020/H\u0002J\u0018\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\u00162\u0006\u0010?\u001a\u00020\u0016H\u0002J\u0010\u0010@\u001a\u00020\u00162\u0006\u00100\u001a\u00020\u0016H\u0002J\u0016\u0010A\u001a\u00020/2\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u001607H\u0002J\u0016\u0010B\u001a\u00020/2\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u001607H\u0002J\b\u0010C\u001a\u00020\u0016H\u0002J\b\u0010D\u001a\u00020/H\u0002J\b\u0010E\u001a\u00020/H\u0002J\u001a\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020\u00102\b\b\u0002\u0010I\u001a\u00020\fH\u0002J\b\u0010J\u001a\u00020\fH\u0002J\u0010\u0010K\u001a\u00020/2\u0006\u0010L\u001a\u00020GH\u0002J\b\u0010M\u001a\u00020\u0016H\u0002J\u0010\u0010N\u001a\u00020\u00102\u0006\u00100\u001a\u00020\u0016H\u0002J\u0018\u0010O\u001a\u00020\n2\u0006\u0010P\u001a\u00020\u00102\u0006\u0010Q\u001a\u00020\fH\u0002J\u000e\u0010R\u001a\b\u0012\u0004\u0012\u00020\u001607H\u0002J\b\u0010S\u001a\u00020/H\u0002J\u0012\u0010T\u001a\u00020/2\b\u0010U\u001a\u0004\u0018\u00010VH\u0014J\b\u0010W\u001a\u00020/H\u0014J\b\u0010X\u001a\u00020/H\u0002J\b\u0010Y\u001a\u00020/H\u0002J\b\u0010Z\u001a\u00020/H\u0002J\b\u0010[\u001a\u00020/H\u0002J\b\u0010\\\u001a\u00020/H\u0002J\b\u0010]\u001a\u00020/H\u0002J\b\u0010^\u001a\u00020/H\u0002J\u0018\u0010_\u001a\u00020\u00102\u0006\u00100\u001a\u00020\u00162\u0006\u0010`\u001a\u00020\u0016H\u0002J\b\u0010a\u001a\u00020/H\u0002J\b\u0010b\u001a\u00020/H\u0002J\b\u0010c\u001a\u00020/H\u0002J\u0010\u0010d\u001a\u00020/2\u0006\u0010P\u001a\u00020\u0010H\u0002J\u0018\u0010e\u001a\u00020/2\u0006\u0010f\u001a\u00020\n2\u0006\u0010g\u001a\u00020\fH\u0002J\u0010\u0010h\u001a\u00020/2\u0006\u0010i\u001a\u00020\u0010H\u0002J\u0010\u0010j\u001a\u00020\u00102\u0006\u00100\u001a\u00020\u0016H\u0002J\b\u0010k\u001a\u00020\u0016H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\rR\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001eR\u0010\u0010\u001f\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010&\u001a\u0012\u0012\u0004\u0012\u00020\u00160'j\b\u0012\u0004\u0012\u00020\u0016`(X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010,X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006m"}, d2 = {"Lcom/xiaofan/bangfan/DdzActivity;", "Lcom/xiaofan/bangfan/BaseActivity;", "()V", "game", "Lcom/xiaofan/bangfan/DdzGame;", "gv", "Lcom/xiaofan/bangfan/DdzActivity$GView;", "handBox", "Landroid/widget/LinearLayout;", "hintBtn", "Landroid/widget/TextView;", "isGuest", "", "()Z", "lastShown", "", "", "[Ljava/lang/String;", "leaveBtn", "main", "Landroid/os/Handler;", "mode", "", "modeAiBtn", "modeLocalBtn", "mySeat", "net", "Lcom/xiaofan/bangfan/GameNet;", "netListener", "com/xiaofan/bangfan/DdzActivity$netListener$1", "Lcom/xiaofan/bangfan/DdzActivity$netListener$1;", "netStatusTv", "oppBox", "opponentName", "passBtn", "playBtn", "revealSeat", "seatPanel", "selected", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "statusTv", "tableTv", "waitingDialog", "Landroid/app/AlertDialog;", "actingSeat", "afterAction", "", "seat", "aiSeat", "aiSeatOfGuest", "aiStep", "applyPass", "applyPlay", "cards", "", "applyRemoteView", "p", "askCreate", "askJoin", "cardChip", "Landroid/view/View;", "card", "owner", "cntOf", "commitPlay", "confirmPlay", "curSeat", "dismissWaiting", "exitOnline", "input", "Landroid/widget/EditText;", "hint", "numeric", "isOver", "keyboard", "et", "landlordSeat", "lastOf", "modeBtn", "t", "primary", "myHand", "newGame", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onHint", "onPass", "onPlay", "pushView", "render", "renderOpponents", "requestRestart", "roleOf", "landlord", "sameScreenGate", "scheduleAi", "showResult", "showWaiting", "styleMode", "tv", "active", "toast", "m", "whoOf", "winnerSide", "GView", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class DdzActivity extends BaseActivity {
    private GView gv;
    private LinearLayout handBox;
    private TextView hintBtn;
    private TextView leaveBtn;
    private int mode;
    private TextView modeAiBtn;
    private TextView modeLocalBtn;
    private int mySeat;
    private TextView netStatusTv;
    private LinearLayout oppBox;
    private TextView passBtn;
    private TextView playBtn;
    private TextView seatPanel;
    private TextView statusTv;
    private TextView tableTv;
    private AlertDialog waitingDialog;
    private final DdzGame game = new DdzGame();
    private final Handler main = new Handler(Looper.getMainLooper());
    private final ArrayList<Integer> selected = new ArrayList<>();
    private int revealSeat = -1;
    private final String[] lastShown = {"", "", ""};
    private String opponentName = "牌友";
    private final GameNet net = new GameNet(this, "ddz");
    private final DdzActivity$netListener$1 netListener = new GameNet.Listener() { // from class: com.xiaofan.bangfan.DdzActivity$netListener$1
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
                com.xiaofan.bangfan.DdzActivity r0 = com.xiaofan.bangfan.DdzActivity.this
                android.widget.TextView r0 = com.xiaofan.bangfan.DdzActivity.access$getNetStatusTv$p(r0)
                if (r0 != 0) goto L13
                goto L19
            L13:
                r1 = r4
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                r0.setText(r1)
            L19:
                com.xiaofan.bangfan.GomokuNet$State r0 = com.xiaofan.bangfan.GomokuNet.State.WAITING_OPPONENT
                if (r3 != r0) goto L33
                com.xiaofan.bangfan.DdzActivity r0 = com.xiaofan.bangfan.DdzActivity.this
                android.app.AlertDialog r0 = com.xiaofan.bangfan.DdzActivity.access$getWaitingDialog$p(r0)
                if (r0 == 0) goto L33
                com.xiaofan.bangfan.DdzActivity r0 = com.xiaofan.bangfan.DdzActivity.this
                android.app.AlertDialog r0 = com.xiaofan.bangfan.DdzActivity.access$getWaitingDialog$p(r0)
                if (r0 == 0) goto L33
                r1 = r4
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                r0.setMessage(r1)
            L33:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.DdzActivity$netListener$1.onStateChanged(com.xiaofan.bangfan.GomokuNet$State, java.lang.String):void");
        }

        @Override // com.xiaofan.bangfan.GameNet.Listener
        public void onAuthorized(int mySeatNet, String opp) {
            TextView textView;
            TextView textView2;
            ArrayList arrayList;
            String[] strArr;
            DdzGame ddzGame;
            TextView textView3;
            Intrinsics.checkNotNullParameter(opp, "opp");
            DdzActivity.this.opponentName = opp;
            DdzActivity.this.dismissWaiting();
            textView = DdzActivity.this.leaveBtn;
            if (textView != null) {
                textView.setVisibility(0);
            }
            if (mySeatNet == 0) {
                DdzActivity.this.mySeat = 0;
                arrayList = DdzActivity.this.selected;
                arrayList.clear();
                strArr = DdzActivity.this.lastShown;
                ArraysKt.fill$default(strArr, "", 0, 0, 6, (Object) null);
                ddzGame = DdzActivity.this.game;
                DdzGame.start$default(ddzGame, 0, null, 2, null);
                DdzActivity.this.pushView();
                DdzActivity.this.render();
                textView3 = DdzActivity.this.netStatusTv;
                if (textView3 != null) {
                    textView3.setText("已与 " + opp + " 联机，你是地主");
                }
            } else {
                DdzActivity.this.mySeat = 1;
                textView2 = DdzActivity.this.netStatusTv;
                if (textView2 != null) {
                    textView2.setText("已与 " + opp + " 联机，你是农民，等待房主发牌");
                }
                DdzActivity.this.render();
            }
            DdzActivity.this.toast("联机成功");
        }

        @Override // com.xiaofan.bangfan.GameNet.Listener
        public void onRemotePayload(String payload) {
            int i;
            DdzGame ddzGame;
            int i2;
            DdzGame ddzGame2;
            Intrinsics.checkNotNullParameter(payload, "payload");
            List p = StringsKt.split$default((CharSequence) payload, new String[]{","}, false, 0, 6, (Object) null);
            String str = (String) p.get(0);
            switch (str.hashCode()) {
                case 86:
                    if (str.equals("V")) {
                        DdzActivity.this.applyRemoteView(p);
                        return;
                    }
                    return;
                case 2448401:
                    if (str.equals("PASS")) {
                        i = DdzActivity.this.mySeat;
                        if (i == 0) {
                            ddzGame = DdzActivity.this.game;
                            if (ddzGame.getCurrent() == 1) {
                                DdzActivity.this.applyPass(1);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                case 2458420:
                    if (str.equals("PLAY")) {
                        i2 = DdzActivity.this.mySeat;
                        if (i2 == 0) {
                            Iterable $this$filter$iv = CollectionsKt.drop(p, 1);
                            Collection destination$iv$iv = new ArrayList();
                            for (Object element$iv$iv : $this$filter$iv) {
                                String it = (String) element$iv$iv;
                                if (it.length() > 0) {
                                    destination$iv$iv.add(element$iv$iv);
                                }
                            }
                            Iterable $this$map$iv = (List) destination$iv$iv;
                            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                            for (Object item$iv$iv : $this$map$iv) {
                                String it2 = (String) item$iv$iv;
                                destination$iv$iv2.add(Integer.valueOf(Integer.parseInt(it2)));
                            }
                            List cards = (List) destination$iv$iv2;
                            ddzGame2 = DdzActivity.this.game;
                            if (ddzGame2.getCurrent() == 1) {
                                DdzActivity.this.applyPlay(1, cards);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        @Override // com.xiaofan.bangfan.GameNet.Listener
        public void onRemoteReset() {
            int i;
            i = DdzActivity.this.mySeat;
            if (i == 1) {
                DdzActivity.this.gv = null;
                DdzActivity.this.render();
                return;
            }
            DdzActivity.this.newGame();
        }

        @Override // com.xiaofan.bangfan.GameNet.Listener
        public void onRemoteSurrender(int bySeat) {
            DdzActivity.this.toast("对方离开/认输");
        }

        @Override // com.xiaofan.bangfan.GameNet.Listener
        public void onPeerLeft() {
            DdzActivity.this.toast("对方已离开房间");
            DdzActivity.this.exitOnline();
        }

        @Override // com.xiaofan.bangfan.GameNet.Listener
        public void onError(String message) {
            GameNet gameNet;
            TextView textView;
            Intrinsics.checkNotNullParameter(message, "message");
            DdzActivity.this.toast(message);
            gameNet = DdzActivity.this.net;
            if (!gameNet.isConnected()) {
                DdzActivity.this.exitOnline();
                textView = DdzActivity.this.netStatusTv;
                if (textView == null) {
                    return;
                }
                textView.setText("联机失败：" + message);
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DdzActivity.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001d\b\u0002\u0018\u00002\u00020\u0001BW\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003¢\u0006\u0002\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u000e\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010\r\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0015\"\u0004\b(\u0010\u0017¨\u0006)"}, d2 = {"Lcom/xiaofan/bangfan/DdzActivity$GView;", "", "cur", "", "cnt", "", "last", "", "", "hand", "", "over", "", "side", "landlord", "(I[I[Ljava/lang/String;Ljava/util/List;ZII)V", "getCnt", "()[I", "setCnt", "([I)V", "getCur", "()I", "setCur", "(I)V", "getHand", "()Ljava/util/List;", "setHand", "(Ljava/util/List;)V", "getLandlord", "setLandlord", "getLast", "()[Ljava/lang/String;", "setLast", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "getOver", "()Z", "setOver", "(Z)V", "getSide", "setSide", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class GView {
        private int[] cnt;
        private int cur;
        private List<Integer> hand;
        private int landlord;
        private String[] last;
        private boolean over;
        private int side;

        public GView() {
            this(0, null, null, null, false, 0, 0, 127, null);
        }

        public GView(int cur, int[] cnt, String[] last, List<Integer> hand, boolean over, int side, int landlord) {
            Intrinsics.checkNotNullParameter(cnt, "cnt");
            Intrinsics.checkNotNullParameter(last, "last");
            Intrinsics.checkNotNullParameter(hand, "hand");
            this.cur = cur;
            this.cnt = cnt;
            this.last = last;
            this.hand = hand;
            this.over = over;
            this.side = side;
            this.landlord = landlord;
        }

        public /* synthetic */ GView(int i, int[] iArr, String[] strArr, List list, boolean z, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this((i4 & 1) != 0 ? 0 : i, (i4 & 2) != 0 ? new int[]{17, 17, 17} : iArr, (i4 & 4) != 0 ? new String[]{"", "", ""} : strArr, (i4 & 8) != 0 ? CollectionsKt.emptyList() : list, (i4 & 16) != 0 ? false : z, (i4 & 32) != 0 ? -1 : i2, (i4 & 64) != 0 ? 0 : i3);
        }

        public final int[] getCnt() {
            return this.cnt;
        }

        public final int getCur() {
            return this.cur;
        }

        public final void setCnt(int[] iArr) {
            Intrinsics.checkNotNullParameter(iArr, "<set-?>");
            this.cnt = iArr;
        }

        public final void setCur(int i) {
            this.cur = i;
        }

        public final List<Integer> getHand() {
            return this.hand;
        }

        public final String[] getLast() {
            return this.last;
        }

        public final void setHand(List<Integer> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.hand = list;
        }

        public final void setLast(String[] strArr) {
            Intrinsics.checkNotNullParameter(strArr, "<set-?>");
            this.last = strArr;
        }

        public final int getLandlord() {
            return this.landlord;
        }

        public final boolean getOver() {
            return this.over;
        }

        public final int getSide() {
            return this.side;
        }

        public final void setLandlord(int i) {
            this.landlord = i;
        }

        public final void setOver(boolean z) {
            this.over = z;
        }

        public final void setSide(int i) {
            this.side = i;
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scaffold = UiKit.INSTANCE.scaffold(this, "斗地主", "人机 / 三人同屏 / 两人联机（小翻补位第三人）");
        LinearLayout content = UiKit.INSTANCE.contentOf(scaffold);
        LinearLayout modeRow = new LinearLayout(this);
        modeRow.setOrientation(0);
        TextView ai = modeBtn("人机对战", true);
        TextView local = modeBtn("三人同屏", false);
        ai.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.DdzActivity$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DdzActivity.onCreate$lambda$0(DdzActivity.this, view);
            }
        });
        local.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.DdzActivity$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DdzActivity.onCreate$lambda$1(DdzActivity.this, view);
            }
        });
        this.modeAiBtn = ai;
        this.modeLocalBtn = local;
        LinearLayout.LayoutParams l1 = new LinearLayout.LayoutParams(0, -2, 1.0f);
        LinearLayout.LayoutParams l2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
        l2.leftMargin = UiKit.INSTANCE.dp(this, 10.0f);
        modeRow.addView(ai, l1);
        modeRow.addView(local, l2);
        content.addView(modeRow, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 4.0f), 0, UiKit.INSTANCE.dp(this, 10.0f)));
        LinearLayout onlineCard = UiKit.INSTANCE.card(this);
        onlineCard.addView(UiKit.INSTANCE.cardTitle(this, "联机对战（两人·小翻补位）"));
        onlineCard.addView(UiKit.INSTANCE.bodyText(this, "两人各坐一位，第三位由小翻 AI 补位；房主为地主并做权威裁定，真实 ID 经哈希隐藏，加入方只收到自己的手牌。"));
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(0);
        TextView create = UiKit.INSTANCE.button(this, "创建房间", true, new View.OnClickListener() { // from class: com.xiaofan.bangfan.DdzActivity$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DdzActivity.onCreate$lambda$2(DdzActivity.this, view);
            }
        });
        TextView join = UiKit.INSTANCE.button(this, "加入房间", false, new View.OnClickListener() { // from class: com.xiaofan.bangfan.DdzActivity$$ExternalSyntheticLambda15
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DdzActivity.onCreate$lambda$3(DdzActivity.this, view);
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
        this.leaveBtn = UiKit.INSTANCE.button(this, "离开房间", false, new View.OnClickListener() { // from class: com.xiaofan.bangfan.DdzActivity$$ExternalSyntheticLambda16
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DdzActivity.onCreate$lambda$4(DdzActivity.this, view);
            }
        });
        TextView textView2 = this.leaveBtn;
        Intrinsics.checkNotNull(textView2);
        textView2.setVisibility(8);
        onlineCard.addView(this.leaveBtn, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 8.0f), 0, 0));
        content.addView(onlineCard, UiKit.INSTANCE.margin(this, 0, 0, 0, UiKit.INSTANCE.dp(this, 10.0f)));
        LinearLayout tableCard = UiKit.INSTANCE.card(this);
        this.seatPanel = UiKit.INSTANCE.bodyText(this, "");
        TextView textView3 = this.seatPanel;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("seatPanel");
            textView3 = null;
        }
        tableCard.addView(textView3);
        this.oppBox = new LinearLayout(this);
        LinearLayout linearLayout = this.oppBox;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("oppBox");
            linearLayout = null;
        }
        linearLayout.setOrientation(1);
        LinearLayout linearLayout2 = this.oppBox;
        if (linearLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("oppBox");
            linearLayout2 = null;
        }
        tableCard.addView(linearLayout2, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 4.0f), 0, UiKit.INSTANCE.dp(this, 4.0f)));
        this.tableTv = UiKit.INSTANCE.cardTitle(this, "");
        TextView textView4 = this.tableTv;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tableTv");
            textView4 = null;
        }
        textView4.setGravity(17);
        TextView textView5 = this.tableTv;
        if (textView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tableTv");
            textView5 = null;
        }
        textView5.setPadding(0, UiKit.INSTANCE.dp(this, 10.0f), 0, UiKit.INSTANCE.dp(this, 10.0f));
        TextView textView6 = this.tableTv;
        if (textView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tableTv");
            textView6 = null;
        }
        tableCard.addView(textView6);
        content.addView(tableCard, UiKit.INSTANCE.margin(this, 0, 0, 0, UiKit.INSTANCE.dp(this, 10.0f)));
        this.statusTv = UiKit.INSTANCE.cardTitle(this, "");
        TextView textView7 = this.statusTv;
        if (textView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("statusTv");
            textView7 = null;
        }
        textView7.setGravity(17);
        TextView textView8 = this.statusTv;
        if (textView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("statusTv");
            textView8 = null;
        }
        content.addView(textView8, UiKit.INSTANCE.margin(this, 0, 0, 0, UiKit.INSTANCE.dp(this, 6.0f)));
        HorizontalScrollView scroll = new HorizontalScrollView(this);
        this.handBox = new LinearLayout(this);
        LinearLayout linearLayout3 = this.handBox;
        if (linearLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("handBox");
            linearLayout3 = null;
        }
        linearLayout3.setOrientation(0);
        LinearLayout linearLayout4 = this.handBox;
        if (linearLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("handBox");
            linearLayout4 = null;
        }
        scroll.addView(linearLayout4);
        content.addView(scroll, UiKit.INSTANCE.margin(this, 0, 0, 0, UiKit.INSTANCE.dp(this, 8.0f)));
        LinearLayout ctrl = new LinearLayout(this);
        ctrl.setOrientation(0);
        this.hintBtn = UiKit.INSTANCE.button(this, "提示", false, new View.OnClickListener() { // from class: com.xiaofan.bangfan.DdzActivity$$ExternalSyntheticLambda17
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DdzActivity.onCreate$lambda$5(DdzActivity.this, view);
            }
        });
        this.playBtn = UiKit.INSTANCE.button(this, "出牌", true, new View.OnClickListener() { // from class: com.xiaofan.bangfan.DdzActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DdzActivity.onCreate$lambda$6(DdzActivity.this, view);
            }
        });
        this.passBtn = UiKit.INSTANCE.button(this, "不要", false, new View.OnClickListener() { // from class: com.xiaofan.bangfan.DdzActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DdzActivity.onCreate$lambda$7(DdzActivity.this, view);
            }
        });
        TextView restart = UiKit.INSTANCE.button(this, "重开", false, new View.OnClickListener() { // from class: com.xiaofan.bangfan.DdzActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DdzActivity.onCreate$lambda$8(DdzActivity.this, view);
            }
        });
        TextView textView9 = this.hintBtn;
        Intrinsics.checkNotNull(textView9);
        TextView textView10 = this.playBtn;
        Intrinsics.checkNotNull(textView10);
        TextView textView11 = this.passBtn;
        Intrinsics.checkNotNull(textView11);
        TextView[] cs = {textView9, textView10, textView11, restart};
        int i = 0;
        int length = cs.length;
        while (i < length) {
            LinearLayout tableCard2 = tableCard;
            HorizontalScrollView scroll2 = scroll;
            TextView restart2 = restart;
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, -2, 1.0f);
            if (i > 0) {
                lp.leftMargin = UiKit.INSTANCE.dp(this, 6.0f);
            }
            ctrl.addView(cs[i], lp);
            i++;
            tableCard = tableCard2;
            scroll = scroll2;
            restart = restart2;
        }
        content.addView(ctrl);
        setContentView(scaffold);
        newGame();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(DdzActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.mode != 2) {
            this$0.mode = 0;
            this$0.mySeat = 0;
            this$0.newGame();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(DdzActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.mode != 2) {
            this$0.mode = 1;
            this$0.newGame();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$2(DdzActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.askCreate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$3(DdzActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.askJoin();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$4(DdzActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.exitOnline();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$5(DdzActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onHint();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$6(DdzActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onPlay();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$7(DdzActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onPass();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$8(DdzActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requestRestart();
    }

    private final TextView modeBtn(String t, boolean primary) {
        TextView tv = new TextView(this);
        tv.setText(t);
        tv.setGravity(17);
        tv.setTextSize(15.0f);
        tv.getPaint().setFakeBoldText(true);
        tv.setPadding(0, UiKit.INSTANCE.dp(this, 12.0f), 0, UiKit.INSTANCE.dp(this, 12.0f));
        GradientDrawable bg = new GradientDrawable();
        bg.setCornerRadius(UiKit.INSTANCE.dp(this, 14.0f));
        if (primary) {
            bg.setColor(UiKit.INSTANCE.color(this, R.color.brand));
            tv.setTextColor(-1);
        } else {
            bg.setColor(UiKit.INSTANCE.color(this, R.color.card));
            bg.setStroke(1, UiKit.INSTANCE.color(this, R.color.line));
            tv.setTextColor(UiKit.INSTANCE.color(this, R.color.ink));
        }
        tv.setBackground(bg);
        return tv;
    }

    private final boolean isGuest() {
        return this.mode == 2 && this.mySeat == 1 && this.gv != null;
    }

    private final int aiSeat() {
        return (this.mode != 0 && this.mode == 2 && this.mySeat == 0) ? 2 : -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void newGame() {
        this.selected.clear();
        ArraysKt.fill$default(this.lastShown, "", 0, 0, 6, (Object) null);
        this.gv = null;
        DdzGame.start$default(this.game, 0, null, 2, null);
        this.revealSeat = this.mode == 1 ? this.game.getCurrent() : -1;
        render();
        scheduleAi();
        if (this.mode == 1) {
            this.main.postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.DdzActivity$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    DdzActivity.newGame$lambda$9(DdzActivity.this);
                }
            }, 120L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void newGame$lambda$9(DdzActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.sameScreenGate();
    }

    private final void requestRestart() {
        if (this.mode != 2 || !this.net.isConnected() || this.mySeat != 0) {
            newGame();
            return;
        }
        this.net.sendReset();
        newGame();
        pushView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String roleOf(int seat, int landlord) {
        return seat == landlord ? "地主" : "农民";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String whoOf(int seat) {
        if (this.mode == 0) {
            return seat == 0 ? "你" : "小翻AI";
        } else if (isGuest()) {
            return seat == this.mySeat ? "你" : seat == aiSeatOfGuest() ? "小翻AI" : this.opponentName;
        } else if (this.mode == 2) {
            return seat == this.mySeat ? "你" : seat == 2 ? "小翻AI" : this.opponentName;
        } else {
            return "座位" + (seat + 1);
        }
    }

    private final int aiSeatOfGuest() {
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int curSeat() {
        if (isGuest()) {
            GView gView = this.gv;
            Intrinsics.checkNotNull(gView);
            return gView.getCur();
        }
        return this.game.getCurrent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int cntOf(int seat) {
        if (isGuest()) {
            GView gView = this.gv;
            Intrinsics.checkNotNull(gView);
            return gView.getCnt()[seat];
        }
        return this.game.handSize(seat);
    }

    private final int landlordSeat() {
        if (isGuest()) {
            GView gView = this.gv;
            Intrinsics.checkNotNull(gView);
            return gView.getLandlord();
        }
        return this.game.getLandlord();
    }

    private final String lastOf(int seat) {
        if (isGuest()) {
            GView gView = this.gv;
            Intrinsics.checkNotNull(gView);
            return gView.getLast()[seat];
        }
        return this.lastShown[seat];
    }

    private final boolean isOver() {
        if (isGuest()) {
            GView gView = this.gv;
            Intrinsics.checkNotNull(gView);
            return gView.getOver();
        }
        return this.game.getGameOver();
    }

    private final List<Integer> myHand() {
        if (!isGuest()) {
            if (this.mode == 1) {
                return this.revealSeat >= 0 ? CollectionsKt.toList(this.game.getHands()[this.revealSeat]) : CollectionsKt.emptyList();
            }
            return CollectionsKt.toList(this.game.getHands()[0]);
        }
        GView gView = this.gv;
        Intrinsics.checkNotNull(gView);
        Iterable $this$sortedByDescending$iv = gView.getHand();
        return CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.xiaofan.bangfan.DdzActivity$myHand$$inlined$sortedByDescending$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int it = ((Number) t2).intValue();
                int it2 = ((Number) t).intValue();
                return ComparisonsKt.compareValues(Integer.valueOf(DdzRules.INSTANCE.rank(it)), Integer.valueOf(DdzRules.INSTANCE.rank(it2)));
            }
        });
    }

    private final int actingSeat() {
        switch (this.mode) {
            case 1:
                return this.revealSeat;
            case 2:
                return this.mySeat;
            default:
                return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void render() {
        String str;
        TextView it = this.modeAiBtn;
        boolean canPass = false;
        if (it != null) {
            styleMode(it, this.mode == 0);
        }
        TextView it2 = this.modeLocalBtn;
        if (it2 != null) {
            styleMode(it2, this.mode == 1);
        }
        final int ll = landlordSeat();
        TextView textView = this.seatPanel;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("seatPanel");
            textView = null;
        }
        textView.setText(CollectionsKt.joinToString$default(new IntRange(0, 2), "   ", null, null, 0, null, new Function1<Integer, CharSequence>() { // from class: com.xiaofan.bangfan.DdzActivity$render$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final CharSequence invoke(int it3) {
                String whoOf;
                String roleOf;
                int cntOf;
                whoOf = DdzActivity.this.whoOf(it3);
                roleOf = DdzActivity.this.roleOf(it3, ll);
                cntOf = DdzActivity.this.cntOf(it3);
                return whoOf + "(" + roleOf + " " + cntOf + ")";
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ CharSequence invoke(Integer num) {
                return invoke(num.intValue());
            }
        }, 30, null));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            String s = lastOf(i);
            if (s.length() > 0) {
                sb.append(whoOf(i) + "：" + s + "\n");
            }
        }
        TextView textView3 = this.tableTv;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tableTv");
            textView3 = null;
        }
        String sb2 = sb.toString();
        if (StringsKt.isBlank(sb2)) {
            sb2 = "（尚未出牌，地主先出）";
        }
        textView3.setText(sb2);
        renderOpponents();
        LinearLayout linearLayout = this.handBox;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("handBox");
            linearLayout = null;
        }
        linearLayout.removeAllViews();
        List<Integer> cards = myHand();
        int owner = actingSeat();
        for (Integer num : cards) {
            int card = num.intValue();
            LinearLayout linearLayout2 = this.handBox;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("handBox");
                linearLayout2 = null;
            }
            linearLayout2.addView(cardChip(card, owner));
        }
        boolean myTurn = !isOver() && curSeat() == actingSeat() && (this.mode != 1 || this.revealSeat == this.game.getCurrent());
        TextView textView4 = this.statusTv;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("statusTv");
        } else {
            textView2 = textView4;
        }
        if (isOver()) {
            str = winnerSide() == 0 ? "地主获胜！" : "农民获胜！";
        } else {
            str = myTurn ? "轮到你出牌" : "等待其他玩家…";
        }
        textView2.setText(str);
        boolean canAct = !isOver() && myTurn;
        TextView textView5 = this.playBtn;
        if (textView5 != null) {
            textView5.setEnabled(canAct);
        }
        TextView textView6 = this.hintBtn;
        if (textView6 != null) {
            textView6.setEnabled(canAct);
        }
        TextView textView7 = this.playBtn;
        if (textView7 != null) {
            textView7.setAlpha(canAct ? 1.0f : 0.5f);
        }
        TextView textView8 = this.hintBtn;
        if (textView8 != null) {
            textView8.setAlpha(canAct ? 1.0f : 0.5f);
        }
        boolean leading = !isGuest() && (this.game.getLastPlayer() < 0 || this.game.getLastPlayer() == this.game.getCurrent());
        if (canAct && !leading) {
            canPass = true;
        }
        TextView textView9 = this.passBtn;
        if (textView9 != null) {
            textView9.setEnabled(canPass);
        }
        TextView textView10 = this.passBtn;
        if (textView10 == null) {
            return;
        }
        textView10.setAlpha(canPass ? 1.0f : 0.4f);
    }

    private final int winnerSide() {
        if (isGuest()) {
            GView gView = this.gv;
            Intrinsics.checkNotNull(gView);
            return gView.getSide();
        }
        return this.game.getWinnerSide();
    }

    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v2, types: [android.util.AttributeSet, kotlin.jvm.internal.DefaultConstructorMarker] */
    private final void renderOpponents() {
        int cnt;
        LinearLayout linearLayout = this.oppBox;
        boolean z = false;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("oppBox");
            linearLayout = null;
        }
        linearLayout.removeAllViews();
        int ll = landlordSeat();
        int seat = 0;
        while (seat < 3) {
            int i = 2;
            if ((seat != actingSeat() || this.mode == 2) && ((!isGuest() || seat != this.mySeat) && (cnt = cntOf(seat)) > 0)) {
                LinearLayout row = new LinearLayout(this);
                row.setOrientation(0);
                row.setGravity(16);
                TextView name = UiKit.INSTANCE.bodyText(this, whoOf(seat) + "(" + roleOf(seat, ll) + ")  ");
                name.setTextSize(12.0f);
                row.addView(name);
                int shown = Math.min(cnt, 10);
                int i2 = 0;
                ?? r3 = z;
                while (i2 < shown) {
                    PokerCardView back = new PokerCardView(this, r3, i, r3);
                    back.setFaceDown(true);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(UiKit.INSTANCE.dp(this, 20.0f), UiKit.INSTANCE.dp(this, 28.0f));
                    lp.leftMargin = i2 == 0 ? 0 : -UiKit.INSTANCE.dp(this, 11.0f);
                    back.setLayoutParams(lp);
                    row.addView(back);
                    i2++;
                    r3 = 0;
                    i = 2;
                }
                if (cnt > shown) {
                    TextView more = UiKit.INSTANCE.bodyText(this, " +" + (cnt - shown));
                    more.setTextSize(12.0f);
                    row.addView(more);
                }
                LinearLayout linearLayout2 = this.oppBox;
                if (linearLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("oppBox");
                    linearLayout2 = null;
                }
                linearLayout2.addView(row);
            }
            seat++;
            z = false;
        }
    }

    private final void styleMode(TextView tv, boolean active) {
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

    private final View cardChip(final int card, final int owner) {
        PokerCardView v = new PokerCardView(this, null, 2, null);
        v.setCardId(card);
        boolean isSel = this.selected.contains(Integer.valueOf(card));
        v.setChosen(isSel);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(UiKit.INSTANCE.dp(this, 52.0f), UiKit.INSTANCE.dp(this, 74.0f));
        lp.rightMargin = UiKit.INSTANCE.dp(this, 5.0f);
        lp.topMargin = isSel ? 0 : UiKit.INSTANCE.dp(this, 12.0f);
        v.setLayoutParams(lp);
        v.setOnTap(new Function0<Unit>() { // from class: com.xiaofan.bangfan.DdzActivity$cardChip$1
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
                int curSeat;
                ArrayList arrayList;
                ArrayList arrayList2;
                ArrayList arrayList3;
                int i = owner;
                curSeat = this.curSeat();
                if (i == curSeat) {
                    arrayList = this.selected;
                    if (arrayList.contains(Integer.valueOf(card))) {
                        arrayList3 = this.selected;
                        arrayList3.remove(Integer.valueOf(card));
                    } else {
                        arrayList2 = this.selected;
                        arrayList2.add(Integer.valueOf(card));
                    }
                    this.render();
                }
            }
        });
        return v;
    }

    private final void onPlay() {
        if (isOver()) {
            return;
        }
        if (isGuest()) {
            if (curSeat() != this.mySeat) {
                return;
            }
            if (this.selected.isEmpty()) {
                toast("请选择要出的牌");
                return;
            } else if (DdzRules.INSTANCE.recognize(CollectionsKt.toList(this.selected)) == null) {
                toast("牌型不对");
                return;
            } else {
                confirmPlay(CollectionsKt.toList(this.selected));
                return;
            }
        }
        int seat = actingSeat();
        if (seat != this.game.getCurrent()) {
            return;
        }
        if (this.selected.isEmpty()) {
            toast("请选择要出的牌");
            return;
        }
        DdzRules.Combo combo = DdzRules.INSTANCE.recognize(CollectionsKt.toList(this.selected));
        if (combo == null) {
            toast("牌型不对，重新选");
            return;
        }
        boolean needBeat = this.game.getLastPlayer() >= 0 && this.game.getLastPlayer() != seat;
        if (!needBeat || DdzRules.INSTANCE.canBeat(combo, this.game.getLastCombo())) {
            confirmPlay(CollectionsKt.toList(this.selected));
        } else {
            toast("压不过上家");
        }
    }

    private final void confirmPlay(final List<Integer> list) {
        String text = CollectionsKt.joinToString$default(list, " ", null, null, 0, null, new Function1<Integer, CharSequence>() { // from class: com.xiaofan.bangfan.DdzActivity$confirmPlay$text$1
            public final CharSequence invoke(int it) {
                return DdzRules.INSTANCE.label(it);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ CharSequence invoke(Integer num) {
                return invoke(num.intValue());
            }
        }, 30, null);
        new AlertDialog.Builder(this).setTitle("确定出牌？").setMessage(text).setPositiveButton("确定出牌", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.DdzActivity$$ExternalSyntheticLambda5
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DdzActivity.confirmPlay$lambda$14(DdzActivity.this, list, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void confirmPlay$lambda$14(DdzActivity this$0, List cards, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(cards, "$cards");
        this$0.commitPlay(cards);
    }

    private final void commitPlay(List<Integer> list) {
        if (isGuest()) {
            if (curSeat() != this.mySeat) {
                return;
            }
            this.net.sendPayload("PLAY," + CollectionsKt.joinToString$default(list, ",", null, null, 0, null, null, 62, null));
            this.selected.clear();
            render();
            return;
        }
        int seat = actingSeat();
        if (seat != this.game.getCurrent()) {
            return;
        }
        applyPlay(seat, list);
    }

    private final void onPass() {
        if (isGuest()) {
            if (curSeat() == this.mySeat) {
                this.net.sendPayload("PASS");
                return;
            }
            return;
        }
        int seat = actingSeat();
        if (seat != this.game.getCurrent() || this.game.getGameOver()) {
            return;
        }
        if (this.game.getLastPlayer() < 0 || this.game.getLastPlayer() == seat) {
            toast("你是上手，需要出牌");
        } else {
            applyPass(seat);
        }
    }

    private final void onHint() {
        DdzRules.Combo c;
        if (isOver()) {
            return;
        }
        List hand = myHand();
        int seat = curSeat();
        if (isGuest()) {
            if (seat != this.mySeat) {
                return;
            }
        } else if (seat != actingSeat()) {
            return;
        }
        boolean leading = isGuest() || this.game.getLastPlayer() < 0 || this.game.getLastPlayer() == seat;
        if (leading) {
            c = DdzRules.INSTANCE.chooseLead(hand);
        } else {
            DdzRules ddzRules = DdzRules.INSTANCE;
            DdzRules.Combo lastCombo = this.game.getLastCombo();
            Intrinsics.checkNotNull(lastCombo);
            DdzRules.Combo findBeat = ddzRules.findBeat(hand, lastCombo);
            c = findBeat == null ? DdzRules.findBomb$default(DdzRules.INSTANCE, hand, 0, 2, null) : findBeat;
        }
        if (c == null) {
            toast("没有能压的牌，可点不要");
            return;
        }
        this.selected.clear();
        this.selected.addAll(ArraysKt.toList(c.getCards()));
        render();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void applyPlay(int seat, List<Integer> list) {
        if (!this.game.play(seat, list)) {
            toast("出牌不合法");
            return;
        }
        this.lastShown[seat] = CollectionsKt.joinToString$default(list, " ", null, null, 0, null, new Function1<Integer, CharSequence>() { // from class: com.xiaofan.bangfan.DdzActivity$applyPlay$1
            public final CharSequence invoke(int it) {
                return DdzRules.INSTANCE.label(it);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ CharSequence invoke(Integer num) {
                return invoke(num.intValue());
            }
        }, 30, null);
        this.selected.clear();
        afterAction(seat);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void applyPass(int seat) {
        if (!this.game.pass(seat)) {
            toast("现在不能过");
            return;
        }
        this.lastShown[seat] = "不要";
        afterAction(seat);
    }

    private final void afterAction(int seat) {
        if (this.mode == 2 && this.mySeat == 0 && this.net.isConnected()) {
            pushView();
        }
        if (this.mode == 1) {
            this.revealSeat = -1;
        }
        render();
        if (this.game.getGameOver()) {
            showResult();
            return;
        }
        scheduleAi();
        if (this.mode == 1) {
            this.main.postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.DdzActivity$$ExternalSyntheticLambda9
                @Override // java.lang.Runnable
                public final void run() {
                    DdzActivity.afterAction$lambda$15(DdzActivity.this);
                }
            }, 250L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void afterAction$lambda$15(DdzActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.sameScreenGate();
    }

    private final void scheduleAi() {
        final int ai;
        if (this.game.getGameOver()) {
            return;
        }
        if (this.mode != 0 || this.game.getCurrent() == 0) {
            ai = (this.mode == 2 && this.mySeat == 0 && this.game.getCurrent() == 2) ? 2 : -1;
        } else {
            ai = this.game.getCurrent();
        }
        if (ai >= 0) {
            this.main.postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.DdzActivity$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    DdzActivity.scheduleAi$lambda$16(DdzActivity.this, ai);
                }
            }, 850L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void scheduleAi$lambda$16(DdzActivity this$0, int $ai) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.aiStep($ai);
    }

    private final void aiStep(int seat) {
        if (this.game.getGameOver() || this.game.getCurrent() != seat) {
            return;
        }
        DdzRules.Combo c = this.game.aiDecide(seat);
        if (c == null) {
            applyPass(seat);
        } else {
            applyPlay(seat, ArraysKt.toList(c.getCards()));
        }
    }

    private final void sameScreenGate() {
        if (this.mode != 1 || this.game.getGameOver()) {
            return;
        }
        final int seat = this.game.getCurrent();
        new AlertDialog.Builder(this).setTitle("轮到 " + roleOf(seat, this.game.getLandlord()) + "（座位" + (seat + 1) + "）").setMessage("请其他玩家回避，点「查看手牌」开始操作").setCancelable(false).setPositiveButton("查看手牌", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.DdzActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DdzActivity.sameScreenGate$lambda$17(DdzActivity.this, seat, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sameScreenGate$lambda$17(DdzActivity this$0, int $seat, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.revealSeat = $seat;
        this$0.render();
    }

    private final void showResult() {
        String msg = this.game.getWinnerSide() == 0 ? "地主获胜！" : "农民获胜！";
        new AlertDialog.Builder(this).setTitle("本局结束").setMessage(msg).setPositiveButton("再来一局", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.DdzActivity$$ExternalSyntheticLambda10
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DdzActivity.showResult$lambda$18(DdzActivity.this, dialogInterface, i);
            }
        }).setNegativeButton("看看牌面", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showResult$lambda$18(DdzActivity this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requestRestart();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toast(String m) {
        Toast.makeText(this, m, 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void pushView() {
        DdzGame g = this.game;
        StringBuilder sb = new StringBuilder("V,");
        sb.append(g.getCurrent()).append(',');
        sb.append(g.handSize(0)).append(',').append(g.handSize(1)).append(',').append(g.handSize(2)).append(',');
        for (int i = 0; i < 3; i++) {
            sb.append(StringsKt.replace$default(this.lastShown[i], ",", "·", false, 4, (Object) null)).append(',');
        }
        sb.append(CollectionsKt.joinToString$default(g.getHands()[1], "~", null, null, 0, null, null, 62, null)).append(',');
        sb.append(g.getGameOver() ? 1 : 0).append(',').append(g.getWinnerSide()).append(',').append(g.getLandlord());
        GameNet gameNet = this.net;
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        gameNet.sendPayload(sb2);
    }

    static /* synthetic */ EditText input$default(DdzActivity ddzActivity, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return ddzActivity.input(str, z);
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
        new AlertDialog.Builder(this).setTitle("创建房间").setView(et).setPositiveButton("创建", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.DdzActivity$$ExternalSyntheticLambda6
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DdzActivity.askCreate$lambda$19(et, this, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
        keyboard(et);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void askCreate$lambda$19(EditText et, DdzActivity this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(et, "$et");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String pwd = StringsKt.trim((CharSequence) et.getText().toString()).toString();
        if (pwd.length() == 0) {
            this$0.toast("请设置房间密码");
            return;
        }
        this$0.mode = 2;
        this$0.mySeat = 0;
        String code = this$0.net.createRoom(pwd, AppPrefs.INSTANCE.nickname(this$0), this$0.netListener);
        this$0.selected.clear();
        ArraysKt.fill$default(this$0.lastShown, "", 0, 0, 6, (Object) null);
        DdzGame.start$default(this$0.game, 0, null, 2, null);
        this$0.render();
        this$0.showWaiting("房间号 " + code + "\n\n你是地主，把房号和密码告诉好友，好友以农民身份加入…");
    }

    private final void askJoin() {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(1);
        final EditText code = input("输入 6 位数字房间号", true);
        final EditText pwd = input$default(this, "输入房间密码", false, 2, null);
        box.addView(code);
        box.addView(pwd);
        new AlertDialog.Builder(this).setTitle("加入房间").setView(box).setPositiveButton("加入", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.DdzActivity$$ExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DdzActivity.askJoin$lambda$22(code, pwd, this, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
        keyboard(code);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void askJoin$lambda$22(EditText code, EditText pwd, DdzActivity this$0, DialogInterface dialogInterface, int i) {
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
                this$0.mySeat = 1;
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
        this.waitingDialog = new AlertDialog.Builder(this).setTitle("联机中").setMessage(t).setCancelable(false).setNegativeButton("取消", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.DdzActivity$$ExternalSyntheticLambda11
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DdzActivity.showWaiting$lambda$24(DdzActivity.this, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showWaiting$lambda$24(DdzActivity this$0, DialogInterface dialogInterface, int i) {
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
        this.mySeat = 0;
        this.gv = null;
        this.opponentName = "牌友";
        TextView textView = this.leaveBtn;
        if (textView != null) {
            textView.setVisibility(8);
        }
        TextView textView2 = this.netStatusTv;
        if (textView2 != null) {
            textView2.setText("尚未联机");
        }
        dismissWaiting();
        newGame();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void applyRemoteView(List<String> list) {
        List hand;
        boolean wasOver = true;
        try {
            int cur = Integer.parseInt(list.get(1));
            int[] cnt = {Integer.parseInt(list.get(2)), Integer.parseInt(list.get(3)), Integer.parseInt(list.get(4))};
            String[] last = {list.get(5), list.get(6), list.get(7)};
            if (list.get(8).length() == 0) {
                hand = CollectionsKt.emptyList();
            } else {
                Iterable $this$map$iv = StringsKt.split$default((CharSequence) list.get(8), new String[]{"~"}, false, 0, 6, (Object) null);
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    String it = (String) item$iv$iv;
                    destination$iv$iv.add(Integer.valueOf(Integer.parseInt(it)));
                }
                hand = (List) destination$iv$iv;
            }
            boolean over = Intrinsics.areEqual(list.get(9), "1");
            int side = Integer.parseInt(list.get(10));
            int landlord = Integer.parseInt(list.get(11));
            GView gView = this.gv;
            if (gView == null || !gView.getOver()) {
                wasOver = false;
            }
            this.gv = new GView(cur, cnt, last, hand, over, side, landlord);
            this.selected.clear();
            render();
            if (over && !wasOver) {
                new AlertDialog.Builder(this).setTitle("本局结束").setMessage(side == 0 ? "地主获胜！" : "农民获胜！").setPositiveButton("知道了", (DialogInterface.OnClickListener) null).show();
            }
        } catch (Throwable th) {
            Log.w("Ddz", "bad view", th);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.net.leave();
        this.main.removeCallbacksAndMessages(null);
    }
}
