package com.xiaofan.bangfan;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.xiaofan.bangfan.WeatherManager;
import com.xiaofan.bangfan.XiaoFanAlarm;
import com.xiaofan.bangfan.XiaoFanBrain;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.DebugKt;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: XiaoFanBrain.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\f\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003ABCB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0017\u001a\u00020\u0015H\u0002J \u0010\u0018\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0004J\u0012\u0010\u001c\u001a\u00020\u00152\b\u0010\u001d\u001a\u0004\u0018\u00010\u0004H\u0002J)\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001d\u001a\u00020\u00042\u0012\u0010 \u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040!\"\u00020\u0004H\u0002¢\u0006\u0002\u0010\"J\u0012\u0010#\u001a\u0004\u0018\u00010$2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0004J\u000e\u0010%\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0004J\u0010\u0010&\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0004H\u0002J\b\u0010'\u001a\u00020\u0004H\u0002J\"\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u0010.\u001a\u0004\u0018\u00010/J \u00100\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010.\u001a\u00020/2\u0006\u00101\u001a\u000202H\u0002J\u0018\u00103\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010.\u001a\u00020/H\u0002J \u00104\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0002J(\u00105\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00101\u001a\u000202H\u0002J(\u00106\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00101\u001a\u000202H\u0002J\u0010\u00107\u001a\u00020\u001f2\u0006\u00108\u001a\u000209H\u0002J\u0010\u0010:\u001a\u00020\u001f2\b\u0010;\u001a\u0004\u0018\u00010\u0004J\u0012\u0010<\u001a\u00020\u00042\b\u0010\u001d\u001a\u0004\u0018\u00010\u0004H\u0002J\u0010\u0010=\u001a\u00020-2\b\u0010;\u001a\u0004\u0018\u00010\u0004J\u0012\u0010>\u001a\u00020\u00152\b\u0010\u001d\u001a\u0004\u0018\u00010\u0004H\u0002J\u0006\u0010?\u001a\u00020\u0004J\u0006\u0010@\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n \u0013*\u0004\u0018\u00010\u00120\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006D"}, d2 = {"Lcom/xiaofan/bangfan/XiaoFanBrain;", "", "()V", "INTENT_ALARM_CANCEL", "", "INTENT_ALARM_QUERY", "INTENT_ALARM_SET", "INTENT_DATE", "INTENT_GREET", "INTENT_THANKS", "INTENT_TIME", "INTENT_TURN", "INTENT_UNKNOWN", "INTENT_WAKE", "INTENT_WEATHER", "INTENT_WHO", "TAG", "UTF8", "Ljava/nio/charset/Charset;", "kotlin.jvm.PlatformType", "applyPeriod", "", "period", "hour", "askCloud", "endpoint", "apiKey", "question", "cnNum", "str", "containsAny", "", "keywords", "", "(Ljava/lang/String;[Ljava/lang/String;)Z", "extractAlarmTime", "Lcom/xiaofan/bangfan/XiaoFanBrain$AlarmTime;", "extractCity", "extractLabel", "greetingByHour", "handle", "", "context", "Landroid/content/Context;", "intent", "Lcom/xiaofan/bangfan/XiaoFanBrain$Intent;", "callback", "Lcom/xiaofan/bangfan/XiaoFanBrain$AnswerCallback;", "handleAlarmCancel", "handler", "Landroid/os/Handler;", "handleAlarmQuery", "handleAlarmSet", "handleUnknown", "handleWeather", "isCjk", "c", "", "isWakeWord", "text", "normalize", "parse", "parseIntSafe", "speakDateNow", "speakTimeNow", "AlarmTime", "AnswerCallback", "Intent", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class XiaoFanBrain {
    public static final String INTENT_ALARM_CANCEL = "alarm_cancel";
    public static final String INTENT_ALARM_QUERY = "alarm_query";
    public static final String INTENT_ALARM_SET = "alarm_set";
    public static final String INTENT_DATE = "date";
    public static final String INTENT_GREET = "greet";
    public static final String INTENT_THANKS = "thanks";
    public static final String INTENT_TIME = "time";
    public static final String INTENT_TURN = "turn";
    public static final String INTENT_UNKNOWN = "unknown";
    public static final String INTENT_WAKE = "wake";
    public static final String INTENT_WEATHER = "weather";
    public static final String INTENT_WHO = "who";
    private static final String TAG = "XiaoFanBrain";
    public static final XiaoFanBrain INSTANCE = new XiaoFanBrain();
    private static final Charset UTF8 = Charset.forName("UTF-8");

    /* compiled from: XiaoFanBrain.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H&¨\u0006\b"}, d2 = {"Lcom/xiaofan/bangfan/XiaoFanBrain$AnswerCallback;", "", "onAction", "", "action", "", "onSpeak", "text", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public interface AnswerCallback {
        void onAction(String str);

        void onSpeak(String str);
    }

    private XiaoFanBrain() {
    }

    /* compiled from: XiaoFanBrain.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0015\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010-\u001a\u00020\u0019R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR\u001a\u0010!\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0006\"\u0004\b#\u0010\bR\u001a\u0010$\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\f\"\u0004\b&\u0010\u000eR\u001a\u0010'\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0006\"\u0004\b)\u0010\bR\u001a\u0010*\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\f\"\u0004\b,\u0010\u000e¨\u0006."}, d2 = {"Lcom/xiaofan/bangfan/XiaoFanBrain$Intent;", "", "()V", "afterMinutes", "", "getAfterMinutes", "()I", "setAfterMinutes", "(I)V", "alarmLabel", "", "getAlarmLabel", "()Ljava/lang/String;", "setAlarmLabel", "(Ljava/lang/String;)V", "city", "getCity", "setCity", "confidence", "", "getConfidence", "()F", "setConfidence", "(F)V", XiaoFanAlarm.REPEAT_DAILY, "", "getDaily", "()Z", "setDaily", "(Z)V", "hour", "getHour", "setHour", "minute", "getMinute", "setMinute", "rawText", "getRawText", "setRawText", "second", "getSecond", "setSecond", "type", "getType", "setType", "isAlarmSet", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Intent {
        private float confidence;
        private boolean daily;
        private int minute;
        private int second;
        private String type = "unknown";
        private String rawText = "";
        private int hour = -1;
        private int afterMinutes = -1;
        private String alarmLabel = "";
        private String city = "";

        public final String getType() {
            return this.type;
        }

        public final void setType(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.type = str;
        }

        public final String getRawText() {
            return this.rawText;
        }

        public final void setRawText(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.rawText = str;
        }

        public final int getHour() {
            return this.hour;
        }

        public final void setHour(int i) {
            this.hour = i;
        }

        public final int getMinute() {
            return this.minute;
        }

        public final void setMinute(int i) {
            this.minute = i;
        }

        public final int getSecond() {
            return this.second;
        }

        public final void setSecond(int i) {
            this.second = i;
        }

        public final boolean getDaily() {
            return this.daily;
        }

        public final void setDaily(boolean z) {
            this.daily = z;
        }

        public final int getAfterMinutes() {
            return this.afterMinutes;
        }

        public final void setAfterMinutes(int i) {
            this.afterMinutes = i;
        }

        public final String getAlarmLabel() {
            return this.alarmLabel;
        }

        public final void setAlarmLabel(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.alarmLabel = str;
        }

        public final String getCity() {
            return this.city;
        }

        public final void setCity(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.city = str;
        }

        public final float getConfidence() {
            return this.confidence;
        }

        public final void setConfidence(float f) {
            this.confidence = f;
        }

        public final boolean isAlarmSet() {
            return Intrinsics.areEqual(XiaoFanBrain.INTENT_ALARM_SET, this.type) && (this.hour >= 0 || this.afterMinutes > 0);
        }
    }

    /* compiled from: XiaoFanBrain.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001a\u0010\u001b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\b¨\u0006\u001e"}, d2 = {"Lcom/xiaofan/bangfan/XiaoFanBrain$AlarmTime;", "", "()V", "afterMinutes", "", "getAfterMinutes", "()I", "setAfterMinutes", "(I)V", XiaoFanAlarm.REPEAT_DAILY, "", "getDaily", "()Z", "setDaily", "(Z)V", "hour", "getHour", "setHour", "label", "", "getLabel", "()Ljava/lang/String;", "setLabel", "(Ljava/lang/String;)V", "minute", "getMinute", "setMinute", "second", "getSecond", "setSecond", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class AlarmTime {
        private boolean daily;
        private int minute;
        private int second;
        private int hour = -1;
        private int afterMinutes = -1;
        private String label = "";

        public final int getHour() {
            return this.hour;
        }

        public final void setHour(int i) {
            this.hour = i;
        }

        public final int getMinute() {
            return this.minute;
        }

        public final void setMinute(int i) {
            this.minute = i;
        }

        public final int getSecond() {
            return this.second;
        }

        public final void setSecond(int i) {
            this.second = i;
        }

        public final boolean getDaily() {
            return this.daily;
        }

        public final void setDaily(boolean z) {
            this.daily = z;
        }

        public final int getAfterMinutes() {
            return this.afterMinutes;
        }

        public final void setAfterMinutes(int i) {
            this.afterMinutes = i;
        }

        public final String getLabel() {
            return this.label;
        }

        public final void setLabel(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.label = str;
        }
    }

    private final boolean isCjk(char c) {
        return 19968 <= c && c < 40960;
    }

    public final boolean isWakeWord(String text) {
        if (text == null) {
            return false;
        }
        String normalized = normalize(text);
        if ((normalized.length() == 0) || normalized.length() > 8) {
            return false;
        }
        return StringsKt.contains$default((CharSequence) normalized, (CharSequence) "小翻小翻", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) normalized, (CharSequence) "嘿小翻", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) normalized, (CharSequence) "你好小翻", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) normalized, (CharSequence) "哈喽小翻", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) normalized, (CharSequence) "嗨小翻", false, 2, (Object) null) || Intrinsics.areEqual(normalized, "小翻") || Intrinsics.areEqual(normalized, "小翻啊") || Intrinsics.areEqual(normalized, "小翻呢") || Intrinsics.areEqual(normalized, "小翻在吗");
    }

    public final Intent parse(String text) {
        Intent intent = new Intent();
        String raw = text == null ? "" : text;
        intent.setRawText(raw);
        String normalized = normalize(raw);
        if (normalized.length() == 0) {
            return intent;
        }
        if (!containsAny(normalized, "取消闹钟", "删除闹钟", "去掉闹钟", "不要闹钟", "删掉闹钟", "清空闹钟") && (!StringsKt.contains$default((CharSequence) normalized, (CharSequence) "闹钟", false, 2, (Object) null) || !containsAny(normalized, "取消", "删", "去掉", "清空"))) {
            if (containsAny(normalized, "有哪些闹钟", "几个闹钟", "闹钟列表", "定了什么闹钟", "下个闹钟", "下一个闹钟", "最近闹钟", "闹钟是几点", "闹钟几点") || (StringsKt.contains$default((CharSequence) normalized, (CharSequence) "闹钟", false, 2, (Object) null) && containsAny(normalized, "多少", "哪些", "什么", "有没有", "几点"))) {
                intent.setType(INTENT_ALARM_QUERY);
                intent.setConfidence(0.9f);
                return intent;
            }
            AlarmTime alarmTime = extractAlarmTime(normalized);
            if (!containsAny(normalized, "闹钟", "提醒我", "叫我", "定个", "订个", "设个", "定时") || alarmTime == null) {
                if (alarmTime == null || (alarmTime.getAfterMinutes() <= 0 && !containsAny(normalized, "提醒", "叫我", "闹"))) {
                    if (!containsAny(normalized, "几点", "什么时间", "现在时间", "报时", "多少点", "点钟了吗", "报一下时", "说下时间", "告诉我时间")) {
                        if (!containsAny(normalized, "几号", "什么日期", "今天日期", "星期几", "礼拜几", "周几")) {
                            if (!containsAny(normalized, "天气", "气温", "温度", "下雨", "下雪", "冷不冷", "热不热", "穿什么", "要带伞")) {
                                if (!containsAny(normalized, "你是谁", "你叫什么", "你是什么")) {
                                    if (!containsAny(normalized, "谢谢", "多谢", "辛苦")) {
                                        if (containsAny(normalized, "你好", "早上好", "中午好", "晚上好", "在吗", "嗨", "哈喽")) {
                                            intent.setType(INTENT_GREET);
                                            intent.setConfidence(0.85f);
                                            return intent;
                                        }
                                        intent.setType("unknown");
                                        intent.setConfidence(0.2f);
                                        return intent;
                                    }
                                    intent.setType(INTENT_THANKS);
                                    intent.setConfidence(0.9f);
                                    return intent;
                                }
                                intent.setType(INTENT_WHO);
                                intent.setConfidence(0.9f);
                                return intent;
                            }
                            intent.setType(INTENT_WEATHER);
                            intent.setConfidence(0.93f);
                            intent.setCity(extractCity(normalized));
                            return intent;
                        }
                        intent.setType(INTENT_DATE);
                        intent.setConfidence(0.95f);
                        return intent;
                    }
                    intent.setType("time");
                    intent.setConfidence(0.95f);
                    return intent;
                }
                intent.setType(INTENT_ALARM_SET);
                intent.setHour(alarmTime.getHour());
                intent.setMinute(alarmTime.getMinute());
                intent.setSecond(alarmTime.getSecond());
                intent.setDaily(alarmTime.getDaily());
                intent.setAfterMinutes(alarmTime.getAfterMinutes());
                intent.setAlarmLabel(alarmTime.getLabel());
                intent.setConfidence(0.85f);
                return intent;
            }
            intent.setType(INTENT_ALARM_SET);
            intent.setHour(alarmTime.getHour());
            intent.setMinute(alarmTime.getMinute());
            intent.setSecond(alarmTime.getSecond());
            intent.setDaily(alarmTime.getDaily());
            intent.setAfterMinutes(alarmTime.getAfterMinutes());
            intent.setAlarmLabel(alarmTime.getLabel());
            intent.setConfidence(0.92f);
            return intent;
        }
        intent.setType(INTENT_ALARM_CANCEL);
        intent.setConfidence(0.9f);
        return intent;
    }

    public final void handle(Context context, Intent intent, AnswerCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Handler handler = new Handler(Looper.getMainLooper());
        if (intent == null || callback == null) {
            return;
        }
        String type = intent.getType();
        switch (type.hashCode()) {
            case -874940727:
                if (type.equals(INTENT_THANKS)) {
                    callback.onSpeak("不客气，有事儿随时叫我");
                    return;
                }
                break;
            case -723593868:
                if (type.equals(INTENT_ALARM_SET)) {
                    handleAlarmSet(context, intent, callback);
                    return;
                }
                break;
            case -605903832:
                if (type.equals(INTENT_ALARM_CANCEL)) {
                    handleAlarmCancel(context, callback, handler);
                    return;
                }
                break;
            case 117694:
                if (type.equals(INTENT_WHO)) {
                    callback.onSpeak("我是小翻呀，帮你翻页、看时间、报天气、定闹钟的小助手，" + AppPrefs.INSTANCE.nickname(context) + "的好朋友");
                    return;
                }
                break;
            case 3076014:
                if (type.equals(INTENT_DATE)) {
                    callback.onSpeak(speakDateNow());
                    return;
                }
                break;
            case 3560141:
                if (type.equals("time")) {
                    callback.onSpeak(speakTimeNow());
                    return;
                }
                break;
            case 3571837:
                if (type.equals(INTENT_TURN)) {
                    callback.onSpeak(TurnManager.INSTANCE.requestTurn(context, "小翻智能脑翻页") ? "好嘞，翻了" : "翻页没成功，检查一下无障碍服务哦");
                    callback.onAction(INTENT_TURN);
                    return;
                }
                break;
            case 3641764:
                if (type.equals(INTENT_WAKE)) {
                    callback.onSpeak("我在");
                    return;
                }
                break;
            case 98619145:
                if (type.equals(INTENT_GREET)) {
                    callback.onSpeak(greetingByHour() + "！需要我帮你做点什么吗？你可以问我时间、天气，或者让我定个闹钟");
                    return;
                }
                break;
            case 409613658:
                if (type.equals(INTENT_ALARM_QUERY)) {
                    handleAlarmQuery(context, callback);
                    return;
                }
                break;
            case 1223440372:
                if (type.equals(INTENT_WEATHER)) {
                    handleWeather(context, intent, callback, handler);
                    return;
                }
                break;
        }
        handleUnknown(context, intent, callback, handler);
    }

    public final String speakTimeNow() {
        String period;
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
        int hour = cal.get(11);
        int minute = cal.get(12);
        int second = cal.get(13);
        if (hour < 6) {
            period = "凌晨";
        } else if (hour < 9) {
            period = "早上";
        } else if (hour < 12) {
            period = "上午";
        } else if (hour < 14) {
            period = "中午";
        } else {
            period = hour < 18 ? "下午" : "晚上";
        }
        int displayHour = hour % 12;
        return "现在北京时间" + period + (displayHour != 0 ? displayHour : 12) + "点" + minute + "分" + second + "秒";
    }

    public final String speakDateNow() {
        return "今天是" + new SimpleDateFormat("yyyy年M月d日 EEEE", Locale.CHINA).format(new Date());
    }

    private final String greetingByHour() {
        int hour = Calendar.getInstance().get(11);
        return hour < 6 ? "夜深了" : hour < 9 ? "早上好" : hour < 12 ? "上午好" : hour < 14 ? "中午好" : hour < 18 ? "下午好" : "晚上好";
    }

    private final void handleWeather(Context context, Intent intent, final AnswerCallback callback, Handler handler) {
        if ((intent.getCity().length() > 0) && !Intrinsics.areEqual(intent.getCity(), WeatherManager.INSTANCE.manualCity(context))) {
            WeatherManager.INSTANCE.setManualCity(context, intent.getCity());
        }
        callback.onSpeak("我查一下天气，稍等");
        WeatherManager.INSTANCE.fetchWeek(context, new WeatherManager.Callback() { // from class: com.xiaofan.bangfan.XiaoFanBrain$handleWeather$1
            @Override // com.xiaofan.bangfan.WeatherManager.Callback
            public void onResult(List<WeatherManager.Day> days, WeatherManager.Current current, String city, boolean fromCache) {
                Intrinsics.checkNotNullParameter(days, "days");
                Intrinsics.checkNotNullParameter(city, "city");
                XiaoFanBrain.AnswerCallback.this.onSpeak(WeatherManager.INSTANCE.speakSummary(days, current, city));
                XiaoFanBrain.AnswerCallback.this.onAction("weather_refreshed");
            }

            @Override // com.xiaofan.bangfan.WeatherManager.Callback
            public void onError(String msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                XiaoFanBrain.AnswerCallback.this.onSpeak(msg);
            }
        });
    }

    private final void handleAlarmSet(Context context, Intent intent, AnswerCallback callback) {
        String label;
        if (intent.getAfterMinutes() > 0) {
            XiaoFanAlarm.Item item = XiaoFanAlarm.INSTANCE.addAfterMinutes(context, intent.getAlarmLabel().length() > 0 ? intent.getAlarmLabel() : "提醒", intent.getAfterMinutes());
            label = intent.getAlarmLabel().length() > 0 ? "：" + intent.getAlarmLabel() : "";
            callback.onSpeak("好嘞，" + intent.getAfterMinutes() + "分钟后提醒你" + label + "，也就是" + item.timeText());
        } else {
            XiaoFanAlarm.Item item2 = XiaoFanAlarm.INSTANCE.add(context, intent.getAlarmLabel().length() > 0 ? intent.getAlarmLabel() : "闹钟", intent.getHour(), intent.getMinute(), intent.getSecond(), intent.getDaily());
            label = intent.getDaily() ? "每天" : "";
            callback.onSpeak("闹钟定好啦，" + label + item2.timeText() + "，" + XiaoFanAlarm.INSTANCE.remainingText(item2.getNextAt()) + "响");
        }
        if (!XiaoFanAlarm.INSTANCE.canScheduleExact(context)) {
            callback.onSpeak("不过系统限制了精确闹钟，可能会晚一点点响，去设置里给我开一下精确闹钟权限吧");
        }
        callback.onAction("alarm_added");
    }

    private final void handleAlarmQuery(Context context, AnswerCallback callback) {
        Iterable $this$filter$iv = XiaoFanAlarm.INSTANCE.load(context);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            XiaoFanAlarm.Item it = (XiaoFanAlarm.Item) element$iv$iv;
            if (it.getEnabled()) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List alarms = (List) destination$iv$iv;
        if (alarms.isEmpty()) {
            callback.onSpeak("现在还没有闹钟哦，要定一个吗？说：定个七点半的闹钟");
            return;
        }
        StringBuilder sb = new StringBuilder("你有" + alarms.size() + "个闹钟");
        XiaoFanAlarm.Item next = XiaoFanAlarm.INSTANCE.nextUpcoming(context);
        if (next != null) {
            sb.append("，最近的是" + next.timeText());
            if (next.getLabel().length() > 0) {
                sb.append("，" + next.getLabel());
            }
            sb.append("，" + XiaoFanAlarm.INSTANCE.remainingText(next.getNextAt()) + "响");
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        callback.onSpeak(sb2);
        callback.onAction("alarm_refreshed");
    }

    private final void handleAlarmCancel(Context context, AnswerCallback callback, Handler handler) {
        Iterable $this$filter$iv = XiaoFanAlarm.INSTANCE.load(context);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            XiaoFanAlarm.Item it = (XiaoFanAlarm.Item) element$iv$iv;
            if (it.getEnabled()) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List alarms = (List) destination$iv$iv;
        if (alarms.isEmpty()) {
            callback.onSpeak("没有正在生效的闹钟可以取消");
        } else if (alarms.size() == 1) {
            XiaoFanAlarm.Item item = (XiaoFanAlarm.Item) alarms.get(0);
            XiaoFanAlarm.INSTANCE.remove(context, item.getId());
            callback.onSpeak("已经把" + item.timeText() + "的闹钟取消了");
            callback.onAction("alarm_removed");
        } else {
            XiaoFanAlarm.Item next = XiaoFanAlarm.INSTANCE.nextUpcoming(context);
            if (next != null) {
                XiaoFanAlarm.INSTANCE.remove(context, next.getId());
                callback.onSpeak("取消了最近的" + next.timeText() + "那个闹钟，还有" + (alarms.size() - 1) + "个在生效，要全删就说清空闹钟");
            } else {
                callback.onSpeak("闹钟有点多，到功能表里手动删吧");
            }
            callback.onAction("alarm_removed");
        }
    }

    private final void handleUnknown(Context context, final Intent intent, final AnswerCallback callback, final Handler handler) {
        final String endpoint = AppPrefs.INSTANCE.aiEndpoint(context);
        final String apiKey = AppPrefs.INSTANCE.aiApiKey(context);
        if (endpoint.length() == 0) {
            callback.onSpeak("这个问题我还不太会。我能帮你翻页、报时间、查天气、定闹钟；想让我更聪明的话，去设置里配置你自己的大模型接口吧");
            return;
        }
        callback.onSpeak("我想一想");
        new Thread(new Runnable() { // from class: com.xiaofan.bangfan.XiaoFanBrain$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                XiaoFanBrain.handleUnknown$lambda$3(endpoint, apiKey, intent, handler, callback);
            }
        }, "brain-cloud").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void handleUnknown$lambda$3(String endpoint, String apiKey, Intent intent, Handler handler, final AnswerCallback callback) {
        Intrinsics.checkNotNullParameter(endpoint, "$endpoint");
        Intrinsics.checkNotNullParameter(apiKey, "$apiKey");
        Intrinsics.checkNotNullParameter(intent, "$intent");
        Intrinsics.checkNotNullParameter(handler, "$handler");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        final String answer = INSTANCE.askCloud(endpoint, apiKey, intent.getRawText());
        if (answer == null) {
            answer = "云端没有回答我，网络可能不太好，等会儿再问我一次吧";
        }
        handler.post(new Runnable() { // from class: com.xiaofan.bangfan.XiaoFanBrain$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                XiaoFanBrain.handleUnknown$lambda$3$lambda$2(XiaoFanBrain.AnswerCallback.this, answer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void handleUnknown$lambda$3$lambda$2(AnswerCallback callback, String answer) {
        Intrinsics.checkNotNullParameter(callback, "$callback");
        Intrinsics.checkNotNullParameter(answer, "$answer");
        callback.onSpeak(answer);
    }

    public final String askCloud(String endpoint, String apiKey, String question) {
        String str;
        String raw;
        String str2;
        String obj;
        Intrinsics.checkNotNullParameter(endpoint, "endpoint");
        Intrinsics.checkNotNullParameter(apiKey, "apiKey");
        Intrinsics.checkNotNullParameter(question, "question");
        HttpURLConnection conn = null;
        try {
            URLConnection openConnection = new URL(endpoint).openConnection();
            Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
            conn = (HttpURLConnection) openConnection;
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            if (apiKey.length() > 0) {
                conn.setRequestProperty("Authorization", "Bearer " + apiKey);
            }
            JSONObject userMsg = new JSONObject().put(AppPrefs.KEY_ROLE, "user").put("content", question);
            JSONObject sysMsg = new JSONObject().put(AppPrefs.KEY_ROLE, "system").put("content", "你叫小翻，是用户手机里一个贴心、机灵的中文阅读陪伴助手，性格像一位温柔又活泼的小伙伴。你能陪用户聊天、解答常识与学习问题、给建议、讲故事、做简单计算与文字润色；你也知道自己还能帮用户自动翻页、报时间、查天气、定闹钟。要求：1）一律用简体中文口语回答，亲切自然，像在跟人说话；2）回答简短清楚，一般不超过80个字，适合语音播报，不要用Markdown、标题符号或代码块；3）不确定的事不要编造，可如实说明并给出稳妥建议；4）不讨论敏感、违法内容，必要时礼貌带过；5）直接给出回答本身，不要复述用户问题，不要自称AI大模型。");
            JSONArray messages = new JSONArray().put(sysMsg).put(userMsg);
            JSONObject body = new JSONObject().put("model", DebugKt.DEBUG_PROPERTY_VALUE_AUTO).put("messages", messages).put("max_tokens", 320).put("temperature", 0.7d).put("stream", false);
            OutputStream out = conn.getOutputStream();
            Intrinsics.checkNotNullExpressionValue(out, "getOutputStream(...)");
            String jSONObject = body.toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
            Charset UTF82 = UTF8;
            Intrinsics.checkNotNullExpressionValue(UTF82, "UTF8");
            byte[] bytes = jSONObject.getBytes(UTF82);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            out.write(bytes);
            out.flush();
            out.close();
        } catch (Throwable th) {
            th = th;
            str = null;
        }
        if (conn.getResponseCode() != 200) {
            Log.w(TAG, "cloud http " + conn.getResponseCode());
            try {
                conn.disconnect();
            } catch (Throwable th2) {
            }
            return null;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), UTF8));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            str = null;
            try {
                sb.append(line);
            } catch (Throwable th3) {
                th = th3;
            }
            th = th3;
            try {
                Log.w(TAG, "askCloud failed", th);
                if (conn != null) {
                    try {
                        conn.disconnect();
                    } catch (Throwable th4) {
                    }
                }
                return str;
            } catch (Throwable th5) {
                if (conn != null) {
                    try {
                        conn.disconnect();
                    } catch (Throwable th6) {
                    }
                }
                throw th5;
            }
        }
        reader.close();
        JSONArray optJSONArray = new JSONObject(sb.toString()).optJSONArray("choices");
        if (optJSONArray == null) {
            try {
                conn.disconnect();
            } catch (Throwable th7) {
            }
            return null;
        } else if (optJSONArray.length() <= 0) {
            try {
                conn.disconnect();
            } catch (Throwable th8) {
            }
            return null;
        } else {
            JSONObject first = optJSONArray.getJSONObject(0);
            JSONObject message = first.optJSONObject("message");
            if (message != null) {
                str = null;
                raw = message.optString("content", null);
            } else {
                str = null;
                raw = first.optString("text", null);
            }
            if (raw == null || (obj = StringsKt.trim((CharSequence) raw).toString()) == null) {
                str2 = str;
            } else {
                String str3 = obj;
                str2 = str3.length() == 0 ? str : str3;
            }
            try {
                conn.disconnect();
                return str2;
            } catch (Throwable th9) {
                return str2;
            }
        }
    }

    private final int cnNum(String str) {
        int i;
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return -1;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            HashMap map = new HashMap();
            char[] digits = {38646, 19968, 20108, 19977, 22235, 20116, 20845, 19971, 20843, 20061};
            int length = digits.length;
            for (int i2 = 0; i2 < length; i2++) {
                map.put(Character.valueOf(digits[i2]), Integer.valueOf(i2));
            }
            map.put((char) 20004, 2);
            map.put((char) 12295, 0);
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) "十", false, 2, (Object) null)) {
                List parts = StringsKt.split$default((CharSequence) str, new String[]{"十"}, false, 0, 6, (Object) null);
                if (parts.isEmpty()) {
                    return 10;
                }
                if (((CharSequence) parts.get(0)).length() == 0) {
                    i = 1;
                } else {
                    i = (Integer) map.get(Character.valueOf(((String) parts.get(0)).charAt(0)));
                    if (i == null) {
                        i = -1;
                    }
                }
                int tens = i.intValue();
                int ones = 0;
                if (parts.size() > 1) {
                    if (((CharSequence) parts.get(1)).length() > 0) {
                        Integer num = (Integer) map.get(Character.valueOf(((String) parts.get(1)).charAt(0)));
                        ones = num == null ? -1 : num.intValue();
                    }
                }
                if (tens < 0 || ones < 0) {
                    return -1;
                }
                return (tens * 10) + ones;
            } else if (str.length() == 1 && map.containsKey(Character.valueOf(str.charAt(0)))) {
                Object obj = map.get(Character.valueOf(str.charAt(0)));
                Intrinsics.checkNotNull(obj);
                return ((Number) obj).intValue();
            } else if (str.length() == 2 && map.containsKey(Character.valueOf(str.charAt(0))) && map.containsKey(Character.valueOf(str.charAt(1)))) {
                Object obj2 = map.get(Character.valueOf(str.charAt(0)));
                Intrinsics.checkNotNull(obj2);
                Object obj3 = map.get(Character.valueOf(str.charAt(1)));
                Intrinsics.checkNotNull(obj3);
                return (((Number) obj2).intValue() * 10) + ((Number) obj3).intValue();
            } else {
                return -1;
            }
        }
    }

    public final AlarmTime extractAlarmTime(String str) {
        int m;
        int num;
        String str2 = str;
        boolean z = false;
        if (str2 == null || str2.length() == 0) {
            return null;
        }
        AlarmTime time = new AlarmTime();
        time.setDaily(containsAny(str, "每天", "天天", "每日", "每个早上", "每个晚上"));
        Matcher afterMatcher = Pattern.compile("(\\d+|[一二两三四五六七八九十半]+)\\s*(分钟|分|小时|钟头|个小时)(以后|之后|后)").matcher(str);
        if (afterMatcher.find() && (num = cnNum(afterMatcher.group(1))) > 0) {
            String unit = afterMatcher.group(2);
            int minutes = num;
            Intrinsics.checkNotNull(unit);
            if (StringsKt.contains$default((CharSequence) unit, (CharSequence) "小时", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) unit, (CharSequence) "钟头", false, 2, (Object) null)) {
                minutes *= 60;
            }
            time.setAfterMinutes(minutes);
            time.setLabel(extractLabel(str));
            return time;
        } else if (StringsKt.contains$default((CharSequence) str, (CharSequence) "半小时后", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "半个小时后", false, 2, (Object) null)) {
            time.setAfterMinutes(30);
            time.setLabel(extractLabel(str));
            return time;
        } else {
            Matcher digitMatcher = Pattern.compile("(凌晨|早上|上午|中午|下午|晚上|夜里)?\\s*(\\d{1,2})\\s*[点:：时]\\s*(\\d{1,2})?\\s*(分|:：?\\s*(\\d{1,2}))?\\s*(半)?").matcher(str);
            if (digitMatcher.find()) {
                int hour = parseIntSafe(digitMatcher.group(2));
                int minute = digitMatcher.group(3) != null ? parseIntSafe(digitMatcher.group(3)) : 0;
                if (digitMatcher.group(6) != null) {
                    minute = 30;
                }
                if (hour >= 0 && hour < 24) {
                    if (minute >= 0 && minute < 60) {
                        time.setHour(applyPeriod(digitMatcher.group(1), hour));
                        time.setMinute(minute);
                        time.setLabel(extractLabel(str));
                        return time;
                    }
                }
            }
            Matcher cnMatcher = Pattern.compile("(凌晨|早上|上午|中午|下午|晚上|夜里)?\\s*([零一二两三四五六七八九十]+)\\s*[点时]\\s*([零一二三四五六七八九十]+)?\\s*(分)?\\s*(半)?").matcher(str);
            if (cnMatcher.find()) {
                int hour2 = cnNum(cnMatcher.group(2));
                int minute2 = 0;
                if (cnMatcher.group(3) != null) {
                    String group = cnMatcher.group(3);
                    Intrinsics.checkNotNull(group);
                    if ((group.length() > 0) && (m = cnNum(cnMatcher.group(3))) >= 0) {
                        minute2 = m;
                    }
                }
                if (cnMatcher.group(5) != null) {
                    minute2 = 30;
                }
                if (hour2 >= 0 && hour2 < 24) {
                    if (minute2 >= 0 && minute2 < 60) {
                        z = true;
                    }
                    if (z) {
                        time.setHour(applyPeriod(cnMatcher.group(1), hour2));
                        time.setMinute(minute2);
                        time.setLabel(extractLabel(str));
                        return time;
                    }
                }
            }
            return null;
        }
    }

    private final int applyPeriod(String period, int hour) {
        if (period == null) {
            return hour;
        }
        if (!StringsKt.contains$default((CharSequence) period, (CharSequence) "下午", false, 2, (Object) null) || hour >= 12) {
            if (StringsKt.contains$default((CharSequence) period, (CharSequence) "晚上", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) period, (CharSequence) "夜里", false, 2, (Object) null)) {
                if (5 <= hour && hour < 12) {
                    return hour + 12;
                }
                if (hour == 12) {
                    return 0;
                }
            }
            if (!StringsKt.contains$default((CharSequence) period, (CharSequence) "中午", false, 2, (Object) null) || hour >= 11) {
                if (StringsKt.contains$default((CharSequence) period, (CharSequence) "凌晨", false, 2, (Object) null) && hour == 12) {
                    return 0;
                }
                return hour;
            }
            return hour + 12;
        }
        return hour + 12;
    }

    private final String extractLabel(String str) {
        Matcher m1 = Pattern.compile("提醒我([^，。,\\.]{1,12})").matcher(str);
        if (m1.find()) {
            String group = m1.group(1);
            String label = (group == null || (label = StringsKt.trim((CharSequence) group).toString()) == null) ? "" : "";
            if (label.length() > 0) {
                return label;
            }
        }
        Matcher m2 = Pattern.compile("叫我([^，。,\\.]{1,12})").matcher(str);
        if (m2.find()) {
            String group2 = m2.group(1);
            String label2 = (group2 == null || (label2 = StringsKt.trim((CharSequence) group2).toString()) == null) ? "" : "";
            if (label2.length() > 0) {
                return label2;
            }
        }
        return (StringsKt.contains$default((CharSequence) str, (CharSequence) "起床", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "叫我起", false, 2, (Object) null)) ? "起床" : StringsKt.contains$default((CharSequence) str, (CharSequence) "睡觉", false, 2, (Object) null) ? "睡觉" : StringsKt.contains$default((CharSequence) str, (CharSequence) "吃药", false, 2, (Object) null) ? "吃药" : StringsKt.contains$default((CharSequence) str, (CharSequence) "开会", false, 2, (Object) null) ? "开会" : "";
    }

    public final String extractCity(String str) {
        String group;
        Intrinsics.checkNotNullParameter(str, "str");
        Matcher matcher = Pattern.compile("([\\u4e00-\\u9fa5]{2,6}?(?:今天|明天|后天|周[一二三四五六日天])?)?(?:的)?(?:天气|气温|温度|会?下雨|会?下雪|冷不冷|热不热|要带伞)").matcher(str);
        if (matcher.find()) {
            String group2 = matcher.group(1);
            if (group2 == null) {
                group2 = "";
            }
            String[] junk = {"今天", "明天", "后天", "这里", "本地", "现在", "我们", "外面", "请问", "帮我查", "那边"};
            for (String j : junk) {
                group2 = StringsKt.replace$default(group2, j, "", false, 4, (Object) null);
            }
            group = StringsKt.trim((CharSequence) group2).toString();
        } else {
            group = "";
        }
        return group.length() >= 2 ? group : "";
    }

    private final String normalize(String str) {
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char[] charArray = str.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "toCharArray(...)");
        int length = charArray.length;
        for (int i = 0; i < length; i++) {
            char c = charArray[i];
            if (Character.isLetterOrDigit(c) || isCjk(c) || c == ':' || c == 65306) {
                sb.append(c != 65306 ? c : ':');
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        Locale CHINA = Locale.CHINA;
        Intrinsics.checkNotNullExpressionValue(CHINA, "CHINA");
        String lowerCase = sb2.toLowerCase(CHINA);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }

    private final boolean containsAny(String str, String... keywords) {
        for (String kw : keywords) {
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) kw, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    private final int parseIntSafe(String str) {
        if (str != null) {
            try {
                String obj = StringsKt.trim((CharSequence) str).toString();
                if (obj != null) {
                    return Integer.parseInt(obj);
                }
                return -1;
            } catch (Exception e) {
                return -1;
            }
        }
        return -1;
    }
}
