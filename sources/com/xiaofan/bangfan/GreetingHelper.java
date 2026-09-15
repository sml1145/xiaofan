package com.xiaofan.bangfan;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Calendar;
import kotlin.Metadata;
/* compiled from: GreetingHelper.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u000eB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rR\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/xiaofan/bangfan/GreetingHelper;", "", "()V", "WEEK_CN", "", "", "[Ljava/lang/String;", "coldGreeting", "greetOnColdLaunch", "", "context", "Landroid/content/Context;", "callback", "Lcom/xiaofan/bangfan/GreetingHelper$Callback;", "Callback", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class GreetingHelper {
    public static final GreetingHelper INSTANCE = new GreetingHelper();
    private static final String[] WEEK_CN = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

    /* compiled from: GreetingHelper.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/xiaofan/bangfan/GreetingHelper$Callback;", "", "onGreeting", "", "greeting", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public interface Callback {
        void onGreeting(String str);
    }

    private GreetingHelper() {
    }

    public final String coldGreeting() {
        Calendar c = Calendar.getInstance();
        String date = (c.get(2) + 1) + "月" + c.get(5) + "日";
        String week = WEEK_CN[c.get(7) - 1];
        return "欢迎使用小翻，今天是" + date + week + "，小翻祝您天天开心。";
    }

    public final void greetOnColdLaunch(Context context, Callback callback) {
        if (callback == null) {
            return;
        }
        callback.onGreeting(coldGreeting());
    }
}
