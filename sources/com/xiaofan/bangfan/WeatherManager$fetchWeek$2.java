package com.xiaofan.bangfan;

import android.content.Context;
import android.location.Location;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.WeatherManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: WeatherManager.kt */
@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006"}, d2 = {"com/xiaofan/bangfan/WeatherManager$fetchWeek$2", "Lcom/xiaofan/bangfan/WeatherManager$Callback2;", "Landroid/location/Location;", "onValue", "", "location", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class WeatherManager$fetchWeek$2 implements WeatherManager.Callback2<Location> {
    final /* synthetic */ WeatherManager.Callback $callback;
    final /* synthetic */ Context $context;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WeatherManager$fetchWeek$2(Context $context, WeatherManager.Callback $callback) {
        this.$context = $context;
        this.$callback = $callback;
    }

    @Override // com.xiaofan.bangfan.WeatherManager.Callback2
    public void onValue(final Location location) {
        final String gk;
        boolean returnCached;
        if (location != null) {
            gk = WeatherManager.INSTANCE.gridKey(location.getLatitude(), location.getLongitude());
            returnCached = WeatherManager.INSTANCE.returnCached(this.$context, gk, this.$callback);
            if (returnCached) {
                return;
            }
            final Context context = this.$context;
            final WeatherManager.Callback callback = this.$callback;
            new Thread(new Runnable() { // from class: com.xiaofan.bangfan.WeatherManager$fetchWeek$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    WeatherManager$fetchWeek$2.onValue$lambda$0(location, context, gk, callback);
                }
            }, "weather-fetch-loc").start();
            return;
        }
        this.$callback.onError("还没有位置信息：请在功能表里允许定位，或点「设置城市」手动填写城市名");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onValue$lambda$0(Location $location, Context context, String gk, WeatherManager.Callback callback) {
        String place;
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(gk, "$gk");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        try {
            place = WeatherManager.INSTANCE.reverseGeocode($location.getLatitude(), $location.getLongitude());
            if (place.length() == 0) {
                place = "当前位置";
            }
            WeatherManager.INSTANCE.fetchByCoord(context, $location.getLatitude(), $location.getLongitude(), place, gk, callback);
        } catch (Throwable th) {
            Log.e("Weather", "fetch failed", th);
            callback.onError("天气获取失败：" + th.getMessage() + "（请检查网络后重试）");
        }
    }
}
