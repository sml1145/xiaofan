package com.xiaofan.bangfan;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GamesHubActivity.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J,\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0002J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014¨\u0006\u000f"}, d2 = {"Lcom/xiaofan/bangfan/GamesHubActivity;", "Lcom/xiaofan/bangfan/BaseActivity;", "()V", "gameRow", "Landroid/widget/LinearLayout;", "emoji", "", "title", "desc", "target", "Ljava/lang/Class;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class GamesHubActivity extends BaseActivity {
    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scaffold = UiKit.INSTANCE.scaffold(this, "休闲小游戏", "每款游戏都支持人机对战、同屏对战与加密联机（房间号+密码，真实 ID 哈希隐藏）。");
        LinearLayout content = UiKit.INSTANCE.contentOf(scaffold);
        content.addView(gameRow("●", "五子棋", "15 路棋盘，五连即胜；支持人机、同屏、联机", GomokuActivity.class));
        content.addView(gameRow("車", "中国象棋", "完整中国象棋规则，将死即胜；AI 对手、同屏、联机", XiangqiActivity.class));
        content.addView(gameRow("圍", "围棋（9 路）", "提子/禁着/数子点目；AI、同屏、联机", GoActivity.class));
        content.addView(gameRow("♠", "斗地主", "经典牌型全识别；人机 1 打 2、三人同屏、两人联机+AI 补位", DdzActivity.class));
        setContentView(scaffold);
    }

    private final LinearLayout gameRow(String emoji, String title, String desc, final Class<?> cls) {
        LinearLayout cell = new LinearLayout(this);
        cell.setOrientation(0);
        cell.setGravity(16);
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(UiKit.INSTANCE.color(this, R.color.card));
        bg.setCornerRadius(UiKit.INSTANCE.dp(this, 16.0f));
        bg.setStroke(1, UiKit.INSTANCE.color(this, R.color.line));
        cell.setBackground(bg);
        cell.setPadding(UiKit.INSTANCE.dp(this, 14.0f), UiKit.INSTANCE.dp(this, 13.0f), UiKit.INSTANCE.dp(this, 14.0f), UiKit.INSTANCE.dp(this, 13.0f));
        TextView em = new TextView(this);
        em.setText(emoji);
        em.setTextSize(24.0f);
        cell.addView(em, new LinearLayout.LayoutParams(UiKit.INSTANCE.dp(this, 38.0f), -2));
        LinearLayout col = new LinearLayout(this);
        col.setOrientation(1);
        TextView t = new TextView(this);
        t.setText(title);
        t.setTextSize(16.0f);
        t.getPaint().setFakeBoldText(true);
        t.setTextColor(UiKit.INSTANCE.color(this, R.color.ink));
        col.addView(t);
        TextView d = new TextView(this);
        d.setText(desc);
        d.setTextSize(11.5f);
        d.setTextColor(UiKit.INSTANCE.color(this, R.color.muted));
        col.addView(d);
        TextView enter = UiKit.INSTANCE.button(this, "进入", true, new View.OnClickListener() { // from class: com.xiaofan.bangfan.GamesHubActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GamesHubActivity.gameRow$lambda$0(GamesHubActivity.this, cls, view);
            }
        });
        col.addView(enter, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 8.0f), 0, 0));
        cell.addView(col, new LinearLayout.LayoutParams(0, -2, 1.0f));
        LinearLayout.LayoutParams lp = UiKit.INSTANCE.matchWrap();
        lp.bottomMargin = UiKit.INSTANCE.dp(this, 12.0f);
        cell.setLayoutParams(lp);
        return cell;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void gameRow$lambda$0(GamesHubActivity this$0, Class target, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(target, "$target");
        this$0.startActivity(new Intent(this$0, target));
    }
}
