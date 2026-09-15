package com.xiaofan.bangfan;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.xiaofan.bangfan.WeatherManager;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: FunctionPanelActivity.kt */
@Metadata(d1 = {"\u00001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J0\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH\u0016¨\u0006\u000f"}, d2 = {"com/xiaofan/bangfan/FunctionPanelActivity$refreshWeather$1", "Lcom/xiaofan/bangfan/WeatherManager$Callback;", "onError", "", NotificationCompat.CATEGORY_MESSAGE, "", "onResult", "days", "", "Lcom/xiaofan/bangfan/WeatherManager$Day;", "current", "Lcom/xiaofan/bangfan/WeatherManager$Current;", "place", "cached", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class FunctionPanelActivity$refreshWeather$1 implements WeatherManager.Callback {
    final /* synthetic */ FunctionPanelActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FunctionPanelActivity$refreshWeather$1(FunctionPanelActivity $receiver) {
        this.this$0 = $receiver;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResult$lambda$0(FunctionPanelActivity this$0, List days, WeatherManager.Current $current, String place, boolean $cached) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(days, "$days");
        Intrinsics.checkNotNullParameter(place, "$place");
        this$0.renderWeather(days, $current, place, $cached);
    }

    @Override // com.xiaofan.bangfan.WeatherManager.Callback
    public void onResult(final List<WeatherManager.Day> days, final WeatherManager.Current current, final String place, final boolean cached) {
        Intrinsics.checkNotNullParameter(days, "days");
        Intrinsics.checkNotNullParameter(place, "place");
        FunctionPanelActivity functionPanelActivity = this.this$0;
        final FunctionPanelActivity functionPanelActivity2 = this.this$0;
        functionPanelActivity.runOnUiThread(new Runnable() { // from class: com.xiaofan.bangfan.FunctionPanelActivity$refreshWeather$1$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FunctionPanelActivity$refreshWeather$1.onResult$lambda$0(FunctionPanelActivity.this, days, current, place, cached);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onError$lambda$1(FunctionPanelActivity this$0, String msg) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(msg, "$msg");
        this$0.renderWeatherError(msg);
    }

    @Override // com.xiaofan.bangfan.WeatherManager.Callback
    public void onError(final String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        FunctionPanelActivity functionPanelActivity = this.this$0;
        final FunctionPanelActivity functionPanelActivity2 = this.this$0;
        functionPanelActivity.runOnUiThread(new Runnable() { // from class: com.xiaofan.bangfan.FunctionPanelActivity$refreshWeather$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FunctionPanelActivity$refreshWeather$1.onError$lambda$1(FunctionPanelActivity.this, msg);
            }
        });
    }
}
