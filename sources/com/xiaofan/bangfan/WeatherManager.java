package com.xiaofan.bangfan;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.text.HtmlCompat;
import com.xiaofan.bangfan.CityCoordDB;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: WeatherManager.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u0004=>?@B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006H\u0002J \u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J8\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0016\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0012\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\u0006H\u0002J\u0018\u0010\"\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0002J\u0010\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u0006H\u0002J\u001e\u0010%\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u000e\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010'0&J\u000e\u0010(\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0015J\u0014\u0010)\u001a\u0004\u0018\u00010*2\b\u0010+\u001a\u0004\u0018\u00010\u0006H\u0002J\u0016\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-2\u0006\u0010+\u001a\u00020\u0006H\u0002J \u0010/\u001a\u0002002\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0018\u00101\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0002J\u0018\u00102\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0011\u001a\u0004\u0018\u00010\u0006J\u0018\u00103\u001a\n \u000f*\u0004\u0018\u000104042\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J(\u00105\u001a\u00020\u00062\u000e\u00106\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010-2\b\u00107\u001a\u0004\u0018\u00010*2\u0006\u0010\u001c\u001a\u00020\u0006J\u000e\u00108\u001a\u00020\u00062\u0006\u00109\u001a\u00020:J\u0010\u0010;\u001a\u00020\u00062\u0006\u0010<\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006A"}, d2 = {"Lcom/xiaofan/bangfan/WeatherManager;", "", "()V", "CACHE_VALID_MS", "", "KEY_CACHE", "", "KEY_CACHE_AT", "KEY_CACHE_CITY", "KEY_CACHE_CURRENT", "KEY_CACHE_PLACE", "KEY_CITY", "TAG", "UTF8", "Ljava/nio/charset/Charset;", "kotlin.jvm.PlatformType", "cityKey", "city", "fetchByCity", "", "context", "Landroid/content/Context;", "callback", "Lcom/xiaofan/bangfan/WeatherManager$Callback;", "fetchByCoord", "lat", "", "lon", "place", "key", "fetchWeek", "geocodeSearch", "Lorg/json/JSONObject;", "name", "gridKey", "httpGet", "urlStr", "lastKnownLocation", "Lcom/xiaofan/bangfan/WeatherManager$Callback2;", "Landroid/location/Location;", "manualCity", "parseCurrent", "Lcom/xiaofan/bangfan/WeatherManager$Current;", "s", "parseDays", "", "Lcom/xiaofan/bangfan/WeatherManager$Day;", "returnCached", "", "reverseGeocode", "setManualCity", "sp", "Landroid/content/SharedPreferences;", "speakSummary", "days", "current", "weatherText", "code", "", "weekCn", "dateStr", "Callback", "Callback2", "Current", "Day", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class WeatherManager {
    private static final long CACHE_VALID_MS = 900000;
    private static final String KEY_CACHE = "weather_cache";
    private static final String KEY_CACHE_AT = "weather_cache_at";
    private static final String KEY_CACHE_CITY = "weather_cache_city";
    private static final String KEY_CACHE_CURRENT = "weather_cache_current";
    private static final String KEY_CACHE_PLACE = "weather_cache_place";
    private static final String KEY_CITY = "weather_city";
    private static final String TAG = "Weather";
    public static final WeatherManager INSTANCE = new WeatherManager();
    private static final Charset UTF8 = Charset.forName("UTF-8");

    /* compiled from: WeatherManager.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J0\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH&¨\u0006\u000f"}, d2 = {"Lcom/xiaofan/bangfan/WeatherManager$Callback;", "", "onError", "", NotificationCompat.CATEGORY_MESSAGE, "", "onResult", "days", "", "Lcom/xiaofan/bangfan/WeatherManager$Day;", "current", "Lcom/xiaofan/bangfan/WeatherManager$Current;", "place", "cached", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public interface Callback {
        void onError(String str);

        void onResult(List<Day> list, Current current, String str, boolean z);
    }

    /* compiled from: WeatherManager.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/xiaofan/bangfan/WeatherManager$Callback2;", "T", "", "onValue", "", "value", "(Ljava/lang/Object;)V", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public interface Callback2<T> {
        void onValue(T t);
    }

    private WeatherManager() {
    }

    public final String weatherText(int code) {
        switch (code) {
            case 0:
                return "晴";
            case 1:
                return "大部晴朗";
            case 2:
            default:
                return "多云";
            case 3:
                return "阴";
            case 45:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                return "有雾";
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                return "小毛毛雨";
            case DdzRules.BIG_JOKER /* 53 */:
                return "毛毛雨";
            case 55:
                return "大毛毛雨";
            case 56:
            case 57:
                return "冻毛毛雨";
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                return "小雨";
            case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                return "中雨";
            case 65:
                return "大雨";
            case 66:
            case 67:
                return "冻雨";
            case 71:
                return "小雪";
            case 73:
                return "中雪";
            case 75:
                return "大雪";
            case 77:
                return "雪粒";
            case 80:
                return "小阵雨";
            case 81:
                return "阵雨";
            case 82:
                return "强阵雨";
            case 85:
                return "小阵雪";
            case 86:
                return "强阵雪";
            case 95:
                return "雷阵雨";
            case 96:
            case 99:
                return "雷阵雨伴冰雹";
        }
    }

    /* compiled from: WeatherManager.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 '2\u00020\u0001:\u0001'B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010$\u001a\u00020\nJ\u0006\u0010%\u001a\u00020&R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R\u001a\u0010\u001b\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\f\"\u0004\b\u001d\u0010\u000eR\u001a\u0010\u001e\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\f\"\u0004\b \u0010\u000eR\u001a\u0010!\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0015\"\u0004\b#\u0010\u0017¨\u0006("}, d2 = {"Lcom/xiaofan/bangfan/WeatherManager$Day;", "", "()V", "code", "", "getCode", "()I", "setCode", "(I)V", XiaoFanBrain.INTENT_DATE, "", "getDate", "()Ljava/lang/String;", "setDate", "(Ljava/lang/String;)V", "precipProb", "getPrecipProb", "setPrecipProb", "tempMax", "", "getTempMax", "()F", "setTempMax", "(F)V", "tempMin", "getTempMin", "setTempMin", "text", "getText", "setText", "weekCn", "getWeekCn", "setWeekCn", "windMax", "getWindMax", "setWindMax", "line", "toJson", "Lorg/json/JSONObject;", "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Day {
        public static final Companion Companion = new Companion(null);
        private int code;
        private int precipProb;
        private float tempMax;
        private float tempMin;
        private float windMax;
        private String date = "";
        private String weekCn = "";
        private String text = "";

        public final String getDate() {
            return this.date;
        }

        public final void setDate(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.date = str;
        }

        public final String getWeekCn() {
            return this.weekCn;
        }

        public final void setWeekCn(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.weekCn = str;
        }

        public final float getTempMax() {
            return this.tempMax;
        }

        public final void setTempMax(float f) {
            this.tempMax = f;
        }

        public final float getTempMin() {
            return this.tempMin;
        }

        public final void setTempMin(float f) {
            this.tempMin = f;
        }

        public final int getCode() {
            return this.code;
        }

        public final void setCode(int i) {
            this.code = i;
        }

        public final String getText() {
            return this.text;
        }

        public final void setText(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.text = str;
        }

        public final int getPrecipProb() {
            return this.precipProb;
        }

        public final void setPrecipProb(int i) {
            this.precipProb = i;
        }

        public final float getWindMax() {
            return this.windMax;
        }

        public final void setWindMax(float f) {
            this.windMax = f;
        }

        public final String line() {
            String str = this.weekCn;
            String str2 = this.text;
            int round = Math.round(this.tempMin);
            return str + " " + str2 + "，" + round + "~" + Math.round(this.tempMax) + "℃";
        }

        public final JSONObject toJson() {
            JSONObject o = new JSONObject();
            try {
                o.put(XiaoFanBrain.INTENT_DATE, this.date);
                o.put("weekCn", this.weekCn);
                o.put("tempMax", Float.valueOf(this.tempMax));
                o.put("tempMin", Float.valueOf(this.tempMin));
                o.put("code", this.code);
                o.put("text", this.text);
                o.put("precipProb", this.precipProb);
                o.put("windMax", Float.valueOf(this.windMax));
            } catch (Exception e) {
            }
            return o;
        }

        /* compiled from: WeatherManager.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/xiaofan/bangfan/WeatherManager$Day$Companion;", "", "()V", "fromJson", "Lcom/xiaofan/bangfan/WeatherManager$Day;", "o", "Lorg/json/JSONObject;", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        /* loaded from: classes4.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final Day fromJson(JSONObject o) {
                Intrinsics.checkNotNullParameter(o, "o");
                Day d = new Day();
                String optString = o.optString(XiaoFanBrain.INTENT_DATE, "");
                Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
                d.setDate(optString);
                String optString2 = o.optString("weekCn", "");
                Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
                d.setWeekCn(optString2);
                d.setTempMax((float) o.optDouble("tempMax", 0.0d));
                d.setTempMin((float) o.optDouble("tempMin", 0.0d));
                d.setCode(o.optInt("code", 0));
                String optString3 = o.optString("text", WeatherManager.INSTANCE.weatherText(d.getCode()));
                Intrinsics.checkNotNullExpressionValue(optString3, "optString(...)");
                d.setText(optString3);
                d.setPrecipProb(o.optInt("precipProb", 0));
                d.setWindMax((float) o.optDouble("windMax", 0.0d));
                return d;
            }
        }
    }

    /* compiled from: WeatherManager.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 #2\u00020\u0001:\u0001#B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010!\u001a\u00020\"R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\f\"\u0004\b \u0010\u000e¨\u0006$"}, d2 = {"Lcom/xiaofan/bangfan/WeatherManager$Current;", "", "()V", "code", "", "getCode", "()I", "setCode", "(I)V", "feelsLike", "", "getFeelsLike", "()F", "setFeelsLike", "(F)V", "humidity", "getHumidity", "setHumidity", "precipProb", "getPrecipProb", "setPrecipProb", "temp", "getTemp", "setTemp", "text", "", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "wind", "getWind", "setWind", "toJson", "Lorg/json/JSONObject;", "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Current {
        public static final Companion Companion = new Companion(null);
        private int code;
        private float feelsLike;
        private int humidity;
        private int precipProb;
        private float temp;
        private String text = "";
        private float wind;

        public final float getTemp() {
            return this.temp;
        }

        public final void setTemp(float f) {
            this.temp = f;
        }

        public final float getFeelsLike() {
            return this.feelsLike;
        }

        public final void setFeelsLike(float f) {
            this.feelsLike = f;
        }

        public final int getCode() {
            return this.code;
        }

        public final void setCode(int i) {
            this.code = i;
        }

        public final String getText() {
            return this.text;
        }

        public final void setText(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.text = str;
        }

        public final float getWind() {
            return this.wind;
        }

        public final void setWind(float f) {
            this.wind = f;
        }

        public final int getHumidity() {
            return this.humidity;
        }

        public final void setHumidity(int i) {
            this.humidity = i;
        }

        public final int getPrecipProb() {
            return this.precipProb;
        }

        public final void setPrecipProb(int i) {
            this.precipProb = i;
        }

        public final JSONObject toJson() {
            JSONObject o = new JSONObject();
            try {
                o.put("temp", Float.valueOf(this.temp));
                o.put("feelsLike", Float.valueOf(this.feelsLike));
                o.put("code", this.code);
                o.put("text", this.text);
                o.put("wind", Float.valueOf(this.wind));
                o.put("humidity", this.humidity);
                o.put("precipProb", this.precipProb);
            } catch (Exception e) {
            }
            return o;
        }

        /* compiled from: WeatherManager.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/xiaofan/bangfan/WeatherManager$Current$Companion;", "", "()V", "fromJson", "Lcom/xiaofan/bangfan/WeatherManager$Current;", "o", "Lorg/json/JSONObject;", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        /* loaded from: classes4.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final Current fromJson(JSONObject o) {
                Intrinsics.checkNotNullParameter(o, "o");
                Current c = new Current();
                c.setTemp((float) o.optDouble("temp", 0.0d));
                c.setFeelsLike((float) o.optDouble("feelsLike", 0.0d));
                c.setCode(o.optInt("code", 0));
                String optString = o.optString("text", WeatherManager.INSTANCE.weatherText(c.getCode()));
                Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
                c.setText(optString);
                c.setWind((float) o.optDouble("wind", 0.0d));
                c.setHumidity(o.optInt("humidity", 0));
                c.setPrecipProb(o.optInt("precipProb", 0));
                return c;
            }
        }
    }

    private final SharedPreferences sp(Context context) {
        return context.getApplicationContext().getSharedPreferences("xiaofan_prefs", 0);
    }

    public final String manualCity(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = sp(context).getString(KEY_CITY, "");
        return string == null ? "" : string;
    }

    public final void setManualCity(Context context, String city) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putString(KEY_CITY, (city == null || (r1 = StringsKt.trim((CharSequence) city).toString()) == null) ? "" : "").apply();
        sp(context).edit().remove(KEY_CACHE).apply();
    }

    /* JADX WARN: Type inference failed for: r0v17, types: [com.xiaofan.bangfan.WeatherManager$lastKnownLocation$listener$1] */
    public final void lastKnownLocation(Context context, final Callback2<Location> callback) {
        Location best;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callback, "callback");
        try {
            boolean hasFine = context.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0;
            boolean hasCoarse = context.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0;
            if (!hasFine && !hasCoarse) {
                callback.onValue(null);
                return;
            }
            Object systemService = context.getSystemService("location");
            final LocationManager lm = systemService instanceof LocationManager ? (LocationManager) systemService : null;
            if (lm == null) {
                callback.onValue(null);
                return;
            }
            Location best2 = null;
            try {
                for (String provider : lm.getProviders(true)) {
                    try {
                        Location loc = lm.getLastKnownLocation(provider);
                        if (loc != null && (best2 == null || loc.getTime() > best2.getTime())) {
                            best2 = loc;
                        }
                    } catch (SecurityException e) {
                    }
                }
                best = best2;
            } catch (SecurityException e2) {
                best = best2;
            }
            if (best != null) {
                callback.onValue(best);
                return;
            }
            final Location[] holder = new Location[1];
            final Object lock = new Object();
            Looper mainLooper = Looper.getMainLooper();
            final ?? r0 = new LocationListener() { // from class: com.xiaofan.bangfan.WeatherManager$lastKnownLocation$listener$1
                @Override // android.location.LocationListener
                public void onLocationChanged(Location location) {
                    Intrinsics.checkNotNullParameter(location, "location");
                    Object obj = lock;
                    Location[] locationArr = holder;
                    Object obj2 = lock;
                    synchronized (obj) {
                        if (locationArr[0] == null) {
                            locationArr[0] = location;
                            obj2.notifyAll();
                        }
                        Unit unit = Unit.INSTANCE;
                    }
                }

                @Override // android.location.LocationListener
                public void onProviderDisabled(String provider2) {
                    Intrinsics.checkNotNullParameter(provider2, "provider");
                }

                @Override // android.location.LocationListener
                public void onProviderEnabled(String provider2) {
                    Intrinsics.checkNotNullParameter(provider2, "provider");
                }

                @Override // android.location.LocationListener
                public void onStatusChanged(String provider2, int status, Bundle extras) {
                    Intrinsics.checkNotNullParameter(provider2, "provider");
                }
            };
            String provider2 = null;
            if (hasFine && lm.getAllProviders().contains("gps") && lm.isProviderEnabled("gps")) {
                provider2 = "gps";
            }
            String provider3 = (provider2 == null && lm.getAllProviders().contains("network") && lm.isProviderEnabled("network")) ? "network" : provider2;
            if (provider3 == null) {
                callback.onValue(null);
                return;
            }
            try {
                lm.requestSingleUpdate(provider3, (LocationListener) r0, mainLooper);
                try {
                    new Thread(new Runnable() { // from class: com.xiaofan.bangfan.WeatherManager$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            WeatherManager.lastKnownLocation$lambda$1(lock, lm, r0, callback, holder);
                        }
                    }, "weather-loc-timeout").start();
                } catch (Throwable th) {
                    callback.onValue(null);
                }
            } catch (Throwable th2) {
            }
        } catch (Throwable th3) {
            callback.onValue(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lastKnownLocation$lambda$1(Object lock, LocationManager $lm, WeatherManager$lastKnownLocation$listener$1 listener, Callback2 callback, Location[] holder) {
        Intrinsics.checkNotNullParameter(lock, "$lock");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        Intrinsics.checkNotNullParameter(holder, "$holder");
        synchronized (lock) {
            try {
                lock.wait(AppPrefs.FALLBACK_READ_MS);
            } catch (InterruptedException e) {
            }
            Unit unit = Unit.INSTANCE;
        }
        try {
            $lm.removeUpdates(listener);
        } catch (Throwable th) {
        }
        callback.onValue(holder[0]);
    }

    public final void fetchWeek(final Context context, final Callback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callback, "callback");
        final String city = manualCity(context);
        if (city.length() > 0) {
            if (returnCached(context, cityKey(city), callback)) {
                return;
            }
            new Thread(new Runnable() { // from class: com.xiaofan.bangfan.WeatherManager$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    WeatherManager.fetchWeek$lambda$2(context, city, callback);
                }
            }, "weather-fetch-city").start();
            return;
        }
        lastKnownLocation(context, new WeatherManager$fetchWeek$2(context, callback));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fetchWeek$lambda$2(Context context, String city, Callback callback) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(city, "$city");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        try {
            INSTANCE.fetchByCity(context, city, callback);
        } catch (Throwable th) {
            Log.e(TAG, "manual city fetch failed", th);
            callback.onError("天气获取失败：" + th.getMessage() + "（请检查网络后重试）");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean returnCached(Context context, String key, Callback callback) {
        String cache = sp(context).getString(KEY_CACHE, null);
        long at = sp(context).getLong(KEY_CACHE_AT, 0L);
        String cacheCity = sp(context).getString(KEY_CACHE_CITY, "");
        if (cacheCity == null) {
            cacheCity = "";
        }
        if (cache == null || !Intrinsics.areEqual(key, cacheCity) || System.currentTimeMillis() - at >= CACHE_VALID_MS) {
            return false;
        }
        List days = parseDays(cache);
        if (days.isEmpty()) {
            return false;
        }
        Current current = parseCurrent(sp(context).getString(KEY_CACHE_CURRENT, null));
        String string = sp(context).getString(KEY_CACHE_PLACE, "");
        String place = string != null ? string : "";
        if (place.length() == 0) {
            place = "当前位置";
        }
        callback.onResult(days, current, place, true);
        return true;
    }

    private final String cityKey(String city) {
        return "c:" + StringsKt.trim((CharSequence) city).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String gridKey(double lat, double lon) {
        long round = Math.round(lat * 10.0d);
        return "g:" + round + ":" + Math.round(10.0d * lon);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String reverseGeocode(double lat, double lon) {
        try {
            JSONObject o = new JSONObject(httpGet("https://api.bigdatacloud.net/data/reverse-geocode-client?latitude=" + lat + "&longitude=" + lon + "&localityLanguage=zh"));
            String city = o.optString("city", "");
            Intrinsics.checkNotNull(city);
            boolean z = true;
            if (city.length() == 0) {
                city = o.optString("locality", "");
            }
            Intrinsics.checkNotNull(city);
            if (city.length() != 0) {
                z = false;
            }
            if (z) {
                city = o.optString("principalSubdivision", "");
            }
            Intrinsics.checkNotNull(city);
            return city;
        } catch (Throwable th) {
            Log.w(TAG, "reverse geocode failed", th);
            return "";
        }
    }

    private final void fetchByCity(Context context, String city, Callback callback) {
        JSONObject geo;
        String ck = cityKey(city);
        CityCoordDB.P lookup = CityCoordDB.Companion.lookup(city);
        if (lookup != null) {
            fetchByCoord(context, lookup.getLat(), lookup.getLon(), lookup.getDisplay(), ck, callback);
            return;
        }
        JSONObject geo2 = geocodeSearch(city);
        if (geo2 != null) {
            geo = geo2;
        } else {
            String trim = StringsKt.trim((CharSequence) city).toString();
            String[] strArr = {"市", "县", "区"};
            int i = 0;
            while (true) {
                if (i >= 3) {
                    geo = geo2;
                    break;
                }
                String suffix = strArr[i];
                if (StringsKt.endsWith$default(trim, suffix, false, 2, (Object) null) && trim.length() > suffix.length()) {
                    String substring = trim.substring(0, trim.length() - suffix.length());
                    Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                    geo2 = geocodeSearch(substring);
                    if (geo2 != null) {
                        geo = geo2;
                        break;
                    }
                }
                i++;
            }
        }
        if (geo == null) {
            callback.onError("没有找到城市「" + city + "」，请填写国内城市名，例如 泉州 / 杭州");
            return;
        }
        double lat = geo.getDouble("latitude");
        double lon = geo.getDouble("longitude");
        String name = geo.optString("name", city);
        String admin1 = geo.optString("admin1", "");
        Intrinsics.checkNotNull(admin1);
        String name2 = (!(admin1.length() > 0) || Intrinsics.areEqual(admin1, name)) ? name : admin1 + "·" + name;
        Intrinsics.checkNotNull(name2);
        fetchByCoord(context, lat, lon, name2, ck, callback);
    }

    private final JSONObject geocodeSearch(String name) {
        try {
            JSONArray results = new JSONObject(httpGet("https://geocoding-api.open-meteo.com/v1/search?count=10&language=zh&format=json&country=cn&name=" + URLEncoder.encode(name, "UTF-8"))).optJSONArray("results");
            if (results == null || results.length() == 0) {
                return null;
            }
            int length = results.length();
            for (int i = 0; i < length; i++) {
                JSONObject r = results.getJSONObject(i);
                if (StringsKt.equals("CN", r.optString("country_code"), true)) {
                    return r;
                }
            }
            return results.getJSONObject(0);
        } catch (Throwable th) {
            Log.w(TAG, "geocode failed for " + name, th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fetchByCoord(Context context, double lat, double lon, String place, String key, Callback callback) {
        JSONArray codes;
        JSONArray maxTemps;
        float f;
        JSONObject root = new JSONObject(httpGet("https://api.open-meteo.com/v1/forecast?latitude=" + lat + "&longitude=" + lon + "&current=temperature_2m,apparent_temperature,weather_code,wind_speed_10m,relative_humidity_2m&hourly=precipitation_probability&daily=weather_code,temperature_2m_max,temperature_2m_min,precipitation_probability_max,wind_speed_10m_max&timezone=auto&forecast_days=7"));
        JSONObject daily = root.optJSONObject(XiaoFanAlarm.REPEAT_DAILY);
        if (daily == null) {
            callback.onError("天气接口返回异常，请稍后重试");
            return;
        }
        Current current = new Current();
        JSONObject cur = root.optJSONObject("current");
        if (cur != null) {
            current.setTemp((float) cur.optDouble("temperature_2m", 0.0d));
            current.setFeelsLike((float) cur.optDouble("apparent_temperature", 0.0d));
            current.setCode(cur.optInt("weather_code", 0));
            current.setText(weatherText(current.getCode()));
            current.setWind((float) cur.optDouble("wind_speed_10m", 0.0d));
            current.setHumidity(cur.optInt("relative_humidity_2m", 0));
            JSONObject hourly = root.optJSONObject("hourly");
            if (hourly != null) {
                JSONArray probs = hourly.optJSONArray("precipitation_probability");
                JSONArray times = hourly.optJSONArray("time");
                if (probs != null && times != null) {
                    String prefix = new SimpleDateFormat("yyyy-MM-dd'T'HH", Locale.CHINA).format(new Date());
                    int i = 0;
                    int length = times.length();
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        JSONObject hourly2 = hourly;
                        String optString = times.optString(i, "");
                        Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
                        Intrinsics.checkNotNull(prefix);
                        JSONObject cur2 = cur;
                        if (StringsKt.startsWith$default(optString, prefix, false, 2, (Object) null)) {
                            current.setPrecipProb(probs.optInt(i, 0));
                            break;
                        }
                        i++;
                        hourly = hourly2;
                        cur = cur2;
                    }
                }
            }
        }
        JSONArray dates = daily.getJSONArray("time");
        JSONArray codes2 = daily.getJSONArray("weather_code");
        JSONArray maxTemps2 = daily.getJSONArray("temperature_2m_max");
        JSONArray minTemps = daily.getJSONArray("temperature_2m_min");
        JSONArray probs2 = daily.optJSONArray("precipitation_probability_max");
        JSONArray winds = daily.optJSONArray("wind_speed_10m_max");
        ArrayList days = new ArrayList();
        int i2 = 0;
        int length2 = dates.length();
        while (i2 < length2) {
            Day d = new Day();
            String string = dates.getString(i2);
            JSONArray dates2 = dates;
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            d.setDate(string);
            d.setCode(codes2.getInt(i2));
            JSONObject daily2 = daily;
            d.setTempMax((float) maxTemps2.getDouble(i2));
            d.setTempMin((float) minTemps.getDouble(i2));
            d.setText(weatherText(d.getCode()));
            d.setWeekCn(weekCn(d.getDate()));
            d.setPrecipProb(probs2 != null ? probs2.optInt(i2, 0) : 0);
            if (winds != null) {
                codes = codes2;
                maxTemps = maxTemps2;
                f = (float) winds.optDouble(i2, 0.0d);
            } else {
                codes = codes2;
                maxTemps = maxTemps2;
                f = 0.0f;
            }
            d.setWindMax(f);
            days.add(d);
            i2++;
            daily = daily2;
            codes2 = codes;
            maxTemps2 = maxTemps;
            dates = dates2;
        }
        if (days.isEmpty()) {
            callback.onError("天气数据为空");
            return;
        }
        JSONArray arr = new JSONArray();
        Iterator it = days.iterator();
        while (it.hasNext()) {
            arr.put(((Day) it.next()).toJson());
        }
        sp(context).edit().putString(KEY_CACHE, arr.toString()).putString(KEY_CACHE_CURRENT, current.toJson().toString()).putString(KEY_CACHE_PLACE, place).putLong(KEY_CACHE_AT, System.currentTimeMillis()).putString(KEY_CACHE_CITY, key).apply();
        callback.onResult(days, current, place, false);
    }

    private final Current parseCurrent(String s) {
        String str = s;
        if (!(str == null || str.length() == 0)) {
            try {
                return Current.Companion.fromJson(new JSONObject(s));
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    private final List<Day> parseDays(String s) {
        ArrayList list = new ArrayList();
        try {
            JSONArray arr = new JSONArray(s);
            int length = arr.length();
            for (int i = 0; i < length; i++) {
                Day.Companion companion = Day.Companion;
                JSONObject jSONObject = arr.getJSONObject(i);
                Intrinsics.checkNotNullExpressionValue(jSONObject, "getJSONObject(...)");
                list.add(companion.fromJson(jSONObject));
            }
        } catch (Exception e) {
        }
        return list;
    }

    private final String httpGet(String urlStr) {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        try {
            URLConnection openConnection = new URL(urlStr).openConnection();
            Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
            HttpURLConnection conn2 = (HttpURLConnection) openConnection;
            conn2.setConnectTimeout(UpdateDownloadCore.PROBE_READ_MS);
            conn2.setReadTimeout(UpdateDownloadCore.PROBE_READ_MS);
            conn2.setRequestProperty("User-Agent", "xiaofan-bangfan/2.0");
            if (conn2.getResponseCode() != 200) {
                throw new Exception("HTTP " + conn2.getResponseCode());
            }
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(conn2.getInputStream(), UTF8));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = reader2.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
            }
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
            try {
                reader2.close();
            } catch (Exception e) {
            }
            conn2.disconnect();
            return sb2;
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    reader.close();
                } catch (Exception e2) {
                }
            }
            if (0 != 0) {
                conn.disconnect();
            }
            throw th;
        }
    }

    private final String weekCn(String dateStr) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).parse(dateStr);
            if (date == null) {
                return "";
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"}[cal.get(7) - 1];
        } catch (Exception e) {
            return "";
        }
    }

    public final String speakSummary(List<Day> list, Current current, String place) {
        Intrinsics.checkNotNullParameter(place, "place");
        StringBuilder sb = new StringBuilder();
        sb.append(place);
        if (current != null) {
            sb.append("现在").append(current.getText()).append("，");
            sb.append(Math.round(current.getTemp())).append("度，体感");
            sb.append(Math.round(current.getFeelsLike())).append("度");
            if (current.getPrecipProb() >= 40) {
                sb.append("，降水概率").append(current.getPrecipProb()).append("%，记得带伞");
            }
            sb.append("。");
        }
        List<Day> list2 = list;
        if (!(list2 == null || list2.isEmpty())) {
            sb.append("今天").append(list.get(0).getText()).append("，");
            sb.append(Math.round(list.get(0).getTempMin())).append("到").append(Math.round(list.get(0).getTempMax())).append("度");
            if (list.get(0).getPrecipProb() >= 40) {
                sb.append("，降水概率").append(list.get(0).getPrecipProb()).append("%");
            }
            if (list.size() > 1) {
                sb.append("；明天").append(list.get(1).getText()).append("，");
                sb.append(Math.round(list.get(1).getTempMin())).append("到").append(Math.round(list.get(1).getTempMax())).append("度");
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }
}
