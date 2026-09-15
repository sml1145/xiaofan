package com.xiaofan.bangfan;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.xiaofan.bangfan.XiaoFanAlarm;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;
import org.webrtc.MediaStreamTrack;
/* compiled from: XiaoFanAlarm.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002HIB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J8\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020 J \u0010!\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\"\u001a\u00020\u000bJ\u000e\u0010#\u001a\u00020 2\u0006\u0010\u0019\u001a\u00020\u001aJ\u0018\u0010$\u001a\u00020%2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u0018H\u0002J\u000e\u0010'\u001a\u00020%2\u0006\u0010\u0019\u001a\u00020\u001aJ&\u0010(\u001a\u00020)2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u000bJ\u000e\u0010*\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u001aJ\u0016\u0010+\u001a\u00020%2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020\u000bJ\u0016\u0010-\u001a\u00020%2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020\u000bJ\u000e\u0010.\u001a\u00020\u00042\u0006\u0010/\u001a\u00020)J\u0014\u00100\u001a\b\u0012\u0004\u0012\u00020\u0018012\u0006\u0010\u0019\u001a\u00020\u001aJ\u0010\u00102\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u00103\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u00104\u001a\u00020%2\u0006\u0010\u0019\u001a\u00020\u001aJ\u0018\u00105\u001a\u0002062\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u0018H\u0002J\u0010\u00107\u001a\u00020%2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u000e\u00108\u001a\u00020\u00042\u0006\u00109\u001a\u00020)J\u0016\u0010:\u001a\u00020%2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020\u000bJ\u000e\u0010;\u001a\u00020%2\u0006\u0010\u0019\u001a\u00020\u001aJ\u001e\u0010<\u001a\u00020%2\u0006\u0010\u0019\u001a\u00020\u001a2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u001801H\u0002J\u0016\u0010>\u001a\u00020 2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u0018J\u001e\u0010?\u001a\u00020%2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020\u000b2\u0006\u0010@\u001a\u00020 J\u0018\u0010A\u001a\u00020%2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u0018H\u0002J\u0010\u0010B\u001a\u00020C2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010D\u001a\u00020%2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010E\u001a\u00020%H\u0002J\b\u0010F\u001a\u00020%H\u0002J\u0010\u0010G\u001a\u00020%2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Lcom/xiaofan/bangfan/XiaoFanAlarm;", "", "()V", "ACTION_DISMISS", "", "ACTION_FIRE", "CHANNEL_ID", "EXTRA_ID", "FILE", "KEY_LIST", "MAX_ALARMS", "", "NOTIF_BASE", "REPEAT_DAILY", "REPEAT_ONCE", "TAG", "activePlayer", "Landroid/media/MediaPlayer;", "activeVibrator", "Landroid/os/Vibrator;", "savedAlarmVolume", "savedAudioManager", "Landroid/media/AudioManager;", "add", "Lcom/xiaofan/bangfan/XiaoFanAlarm$Item;", "context", "Landroid/content/Context;", "label", "hour", "minute", "second", XiaoFanAlarm.REPEAT_DAILY, "", "addAfterMinutes", "minutes", "canScheduleExact", "cancel", "", "item", "clearAll", "computeNextAt", "", "count", "dismiss", "id", "fire", "formatAt", "at", "load", "", "nextId", "nextUpcoming", "openExactAlarmSettings", "pending", "Landroid/app/PendingIntent;", "playRingtone", "remainingText", "nextAt", "remove", "rescheduleAll", "save", "list", "schedule", "setEnabled", "enabled", "showNotification", "sp", "Landroid/content/SharedPreferences;", "startVibration", "stopRingtone", "stopVibration", "wakeScreen", "AlarmReceiver", "Item", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class XiaoFanAlarm {
    public static final String ACTION_DISMISS = "com.xiaofan.bangfan.action.ALARM_DISMISS";
    public static final String ACTION_FIRE = "com.xiaofan.bangfan.action.ALARM_FIRE";
    private static final String CHANNEL_ID = "xiaofan_alarm";
    public static final String EXTRA_ID = "alarm_id";
    private static final String FILE = "xiaofan_alarms";
    private static final String KEY_LIST = "alarms";
    private static final int MAX_ALARMS = 60;
    private static final int NOTIF_BASE = 5000;
    public static final String REPEAT_DAILY = "daily";
    public static final String REPEAT_ONCE = "once";
    private static final String TAG = "XiaoFanAlarm";
    private static MediaPlayer activePlayer;
    private static Vibrator activeVibrator;
    private static AudioManager savedAudioManager;
    public static final XiaoFanAlarm INSTANCE = new XiaoFanAlarm();
    private static int savedAlarmVolume = -1;

    private XiaoFanAlarm() {
    }

    /* compiled from: XiaoFanAlarm.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 /2\u00020\u0001:\u0001/B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010*\u001a\u00020\u0019J\u0006\u0010+\u001a\u00020\u0019J\u0006\u0010,\u001a\u00020\u0019J\u0006\u0010-\u001a\u00020.R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0012\"\u0004\b \u0010\u0014R\u001a\u0010!\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0006\"\u0004\b#\u0010\bR\u001a\u0010$\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001b\"\u0004\b&\u0010\u001dR\u001a\u0010'\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0012\"\u0004\b)\u0010\u0014¨\u00060"}, d2 = {"Lcom/xiaofan/bangfan/XiaoFanAlarm$Item;", "", "()V", "createdAt", "", "getCreatedAt", "()J", "setCreatedAt", "(J)V", "enabled", "", "getEnabled", "()Z", "setEnabled", "(Z)V", "hour", "", "getHour", "()I", "setHour", "(I)V", "id", "getId", "setId", "label", "", "getLabel", "()Ljava/lang/String;", "setLabel", "(Ljava/lang/String;)V", "minute", "getMinute", "setMinute", "nextAt", "getNextAt", "setNextAt", "repeat", "getRepeat", "setRepeat", "second", "getSecond", "setSecond", "display", "repeatText", "timeText", "toJson", "Lorg/json/JSONObject;", "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Item {
        public static final Companion Companion = new Companion(null);
        private int id;
        private int minute;
        private long nextAt;
        private int second;
        private String label = "";
        private int hour = 7;
        private String repeat = XiaoFanAlarm.REPEAT_ONCE;
        private boolean enabled = true;
        private long createdAt = System.currentTimeMillis();

        public final int getId() {
            return this.id;
        }

        public final void setId(int i) {
            this.id = i;
        }

        public final String getLabel() {
            return this.label;
        }

        public final void setLabel(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.label = str;
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

        public final String getRepeat() {
            return this.repeat;
        }

        public final void setRepeat(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.repeat = str;
        }

        public final boolean getEnabled() {
            return this.enabled;
        }

        public final void setEnabled(boolean z) {
            this.enabled = z;
        }

        public final long getCreatedAt() {
            return this.createdAt;
        }

        public final void setCreatedAt(long j) {
            this.createdAt = j;
        }

        public final long getNextAt() {
            return this.nextAt;
        }

        public final void setNextAt(long j) {
            this.nextAt = j;
        }

        public final String timeText() {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format(Locale.CHINA, "%02d:%02d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.hour), Integer.valueOf(this.minute), Integer.valueOf(this.second)}, 3));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            return format;
        }

        public final String repeatText() {
            return Intrinsics.areEqual(XiaoFanAlarm.REPEAT_DAILY, this.repeat) ? "每天" : "仅一次";
        }

        public final String display() {
            return timeText() + (this.label.length() > 0 ? " · " + this.label : "") + " · " + repeatText();
        }

        public final JSONObject toJson() {
            JSONObject o = new JSONObject();
            try {
                o.put("id", this.id);
                o.put("label", this.label);
                o.put("hour", this.hour);
                o.put("minute", this.minute);
                o.put("second", this.second);
                o.put("repeat", this.repeat);
                o.put("enabled", this.enabled);
                o.put("createdAt", this.createdAt);
                o.put("nextAt", this.nextAt);
            } catch (Exception e) {
            }
            return o;
        }

        /* compiled from: XiaoFanAlarm.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/xiaofan/bangfan/XiaoFanAlarm$Item$Companion;", "", "()V", "fromJson", "Lcom/xiaofan/bangfan/XiaoFanAlarm$Item;", "o", "Lorg/json/JSONObject;", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        /* loaded from: classes4.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final Item fromJson(JSONObject o) {
                Intrinsics.checkNotNullParameter(o, "o");
                Item item = new Item();
                item.setId(o.optInt("id", 0));
                String optString = o.optString("label", "");
                Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
                item.setLabel(optString);
                item.setHour(o.optInt("hour", 7));
                item.setMinute(o.optInt("minute", 0));
                item.setSecond(o.optInt("second", 0));
                String optString2 = o.optString("repeat", XiaoFanAlarm.REPEAT_ONCE);
                Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
                item.setRepeat(optString2);
                item.setEnabled(o.optBoolean("enabled", true));
                item.setCreatedAt(o.optLong("createdAt", System.currentTimeMillis()));
                item.setNextAt(o.optLong("nextAt", 0L));
                return item;
            }
        }
    }

    private final SharedPreferences sp(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(FILE, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
        return sharedPreferences;
    }

    public final List<Item> load(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ArrayList list = new ArrayList();
        String string = sp(context).getString(KEY_LIST, "");
        String s = string != null ? string : "";
        if (s.length() == 0) {
            return list;
        }
        try {
            JSONArray arr = new JSONArray(s);
            int length = arr.length();
            for (int i = 0; i < length; i++) {
                Item.Companion companion = Item.Companion;
                JSONObject jSONObject = arr.getJSONObject(i);
                Intrinsics.checkNotNullExpressionValue(jSONObject, "getJSONObject(...)");
                list.add(companion.fromJson(jSONObject));
            }
            final XiaoFanAlarm$load$1 xiaoFanAlarm$load$1 = new Function2<Item, Item, Integer>() { // from class: com.xiaofan.bangfan.XiaoFanAlarm$load$1
                @Override // kotlin.jvm.functions.Function2
                public final Integer invoke(XiaoFanAlarm.Item a, XiaoFanAlarm.Item b) {
                    int h = Integer.compare(a.getHour(), b.getHour());
                    if (h != 0) {
                        return Integer.valueOf(h);
                    }
                    int m = Integer.compare(a.getMinute(), b.getMinute());
                    return Integer.valueOf(m != 0 ? m : Integer.compare(a.getSecond(), b.getSecond()));
                }
            };
            Collections.sort(list, new Comparator() { // from class: com.xiaofan.bangfan.XiaoFanAlarm$$ExternalSyntheticLambda3
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int load$lambda$0;
                    load$lambda$0 = XiaoFanAlarm.load$lambda$0(Function2.this, obj, obj2);
                    return load$lambda$0;
                }
            });
            return list;
        } catch (Exception e) {
            return new ArrayList();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int load$lambda$0(Function2 tmp0, Object p0, Object p1) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        return ((Number) tmp0.invoke(p0, p1)).intValue();
    }

    private final void save(Context context, List<Item> list) {
        JSONArray arr = new JSONArray();
        for (Item item : list) {
            arr.put(item.toJson());
        }
        sp(context).edit().putString(KEY_LIST, arr.toString()).apply();
    }

    private final int nextId(Context context) {
        int max = 0;
        for (Item item : load(context)) {
            if (item.getId() > max) {
                max = item.getId();
            }
        }
        return max + 1;
    }

    public final long computeNextAt(Context context, int hour, int minute, int second) {
        Intrinsics.checkNotNullParameter(context, "context");
        Calendar now = Calendar.getInstance();
        Calendar target = Calendar.getInstance();
        target.set(11, hour);
        target.set(12, minute);
        target.set(13, second);
        target.set(14, 0);
        if (!target.after(now)) {
            target.add(5, 1);
        }
        return target.getTimeInMillis();
    }

    public final Item add(Context context, String label, int hour, int minute, int second, boolean daily) {
        Intrinsics.checkNotNullParameter(context, "context");
        int h = ((hour % 24) + 24) % 24;
        int m = Math.max(0, Math.min(59, minute));
        int s = Math.max(0, Math.min(59, second));
        List list = CollectionsKt.toMutableList((Collection) load(context));
        Item item = new Item();
        item.setId(nextId(context));
        item.setLabel((label == null || (r9 = StringsKt.trim((CharSequence) label).toString()) == null) ? "" : "");
        item.setHour(h);
        item.setMinute(m);
        item.setSecond(s);
        item.setRepeat(daily ? REPEAT_DAILY : REPEAT_ONCE);
        item.setEnabled(true);
        item.setCreatedAt(System.currentTimeMillis());
        item.setNextAt(computeNextAt(context, h, m, s));
        list.add(item);
        List trimmed = list.size() > 60 ? CollectionsKt.toMutableList((Collection) list.subList(list.size() - 60, list.size())) : list;
        save(context, trimmed);
        schedule(context, item);
        Log.i(TAG, "alarm added id=" + item.getId() + " at " + item.timeText() + " next=" + item.getNextAt());
        return item;
    }

    public final Item addAfterMinutes(Context context, String label, int minutes) {
        Intrinsics.checkNotNullParameter(context, "context");
        Calendar cal = Calendar.getInstance();
        cal.add(12, Math.max(1, minutes));
        return add(context, label, cal.get(11), cal.get(12), cal.get(13), false);
    }

    public final void setEnabled(Context context, int id, boolean enabled) {
        Intrinsics.checkNotNullParameter(context, "context");
        List list = load(context);
        for (Item item : list) {
            if (item.getId() == id) {
                item.setEnabled(enabled);
                if (enabled) {
                    item.setNextAt(computeNextAt(context, item.getHour(), item.getMinute(), item.getSecond()));
                    schedule(context, item);
                } else {
                    item.setNextAt(0L);
                    cancel(context, item);
                }
            }
        }
        save(context, list);
    }

    public final void remove(Context context, int id) {
        Intrinsics.checkNotNullParameter(context, "context");
        List list = load(context);
        ArrayList remaining = new ArrayList();
        for (Item item : list) {
            if (item.getId() == id) {
                cancel(context, item);
            } else {
                remaining.add(item);
            }
        }
        save(context, remaining);
    }

    public final void clearAll(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        for (Item item : load(context)) {
            cancel(context, item);
        }
        save(context, new ArrayList());
    }

    public final void rescheduleAll(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        int count = 0;
        for (Item item : load(context)) {
            if (item.getEnabled()) {
                item.setNextAt(computeNextAt(context, item.getHour(), item.getMinute(), item.getSecond()));
                schedule(context, item);
                count++;
            }
        }
        save(context, load(context));
        Log.i(TAG, "rescheduled " + count + " alarms");
    }

    public final int count(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Iterable $this$count$iv = load(context);
        if (($this$count$iv instanceof Collection) && ((Collection) $this$count$iv).isEmpty()) {
            return 0;
        }
        int count$iv = 0;
        for (Object element$iv : $this$count$iv) {
            Item it = (Item) element$iv;
            if (it.getEnabled() && (count$iv = count$iv + 1) < 0) {
                CollectionsKt.throwCountOverflow();
            }
        }
        return count$iv;
    }

    public final Item nextUpcoming(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        long now = System.currentTimeMillis();
        Item best = null;
        for (Item item : load(context)) {
            if (item.getEnabled()) {
                long next = item.getNextAt() > 0 ? item.getNextAt() : computeNextAt(context, item.getHour(), item.getMinute(), item.getSecond());
                if (next < now) {
                    next = computeNextAt(context, item.getHour(), item.getMinute(), item.getSecond());
                }
                if (best == null || next < best.getNextAt()) {
                    item.setNextAt(next);
                    best = item;
                }
            }
        }
        return best;
    }

    private final PendingIntent pending(Context context, Item item) {
        PendingIntent broadcast = PendingIntent.getBroadcast(context, item.getId() + NOTIF_BASE, new AlarmReceiver().intentFor(context, item.getId()), AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        Intrinsics.checkNotNullExpressionValue(broadcast, "getBroadcast(...)");
        return broadcast;
    }

    public final boolean schedule(Context context, Item item) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getEnabled()) {
            try {
                Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
                AlarmManager am = systemService instanceof AlarmManager ? (AlarmManager) systemService : null;
                if (am == null) {
                    return false;
                }
                long next = item.getNextAt() > 0 ? item.getNextAt() : computeNextAt(context, item.getHour(), item.getMinute(), item.getSecond());
                item.setNextAt(next);
                PendingIntent pi = pending(context, item);
                if (Build.VERSION.SDK_INT >= 31 && !am.canScheduleExactAlarms()) {
                    am.setWindow(0, next, 60000L, pi);
                    Log.w(TAG, "exact alarm not allowed, fallback to window alarm");
                    return false;
                }
                am.setExactAndAllowWhileIdle(0, next, pi);
                return true;
            } catch (Throwable th) {
                Log.e(TAG, "schedule failed", th);
                return false;
            }
        }
        return false;
    }

    private final void cancel(Context context, Item item) {
        try {
            Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            AlarmManager am = systemService instanceof AlarmManager ? (AlarmManager) systemService : null;
            if (am != null) {
                am.cancel(pending(context, item));
            }
        } catch (Throwable th) {
        }
    }

    public final boolean canScheduleExact(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (Build.VERSION.SDK_INT < 31) {
            return true;
        }
        try {
            Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            AlarmManager am = systemService instanceof AlarmManager ? (AlarmManager) systemService : null;
            if (am != null) {
                if (am.canScheduleExactAlarms()) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            return true;
        }
    }

    public final void openExactAlarmSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            if (Build.VERSION.SDK_INT >= 31) {
                Intent intent = new Intent("android.settings.REQUEST_SCHEDULE_EXACT_ALARM", Uri.parse("package:" + context.getPackageName()));
                intent.addFlags(268435456);
                context.startActivity(intent);
                return;
            }
        } catch (Throwable th) {
        }
        try {
            Intent intent2 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + context.getPackageName()));
            intent2.addFlags(268435456);
            context.startActivity(intent2);
        } catch (Throwable th2) {
        }
    }

    public final void fire(Context context, int id) {
        Object element$iv;
        StringBuilder append;
        String str;
        Item it;
        Intrinsics.checkNotNullParameter(context, "context");
        Iterable $this$firstOrNull$iv = load(context);
        Iterator<T> it2 = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it2.hasNext()) {
                element$iv = it2.next();
                if (((Item) element$iv).getId() == id) {
                    it = 1;
                    continue;
                } else {
                    it = null;
                    continue;
                }
                if (it != null) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        Item item = (Item) element$iv;
        if (item == null) {
            return;
        }
        Log.i(TAG, "alarm fired id=" + id + " label=" + item.getLabel());
        wakeScreen(context);
        showNotification(context, item);
        AlarmRingService.Companion.start(context, item.getId(), item.getLabel().length() > 0 ? item.getLabel() : item.timeText());
        XiaoFanVoice xiaoFanVoice = XiaoFanVoice.INSTANCE;
        if (item.getLabel().length() > 0) {
            str = item.getLabel();
            append = new StringBuilder().append("时间到啦，");
        } else {
            append = new StringBuilder().append("时间到啦，").append(item.timeText());
            str = " 的闹钟响了";
        }
        xiaoFanVoice.tip(context, append.append(str).toString());
        if (Intrinsics.areEqual(REPEAT_DAILY, item.getRepeat())) {
            item.setNextAt(computeNextAt(context, item.getHour(), item.getMinute(), item.getSecond()));
            schedule(context, item);
            List list = load(context);
            for (Item it3 : list) {
                if (it3.getId() == id) {
                    it3.setNextAt(item.getNextAt());
                }
            }
            save(context, list);
            return;
        }
        setEnabled(context, id, false);
    }

    private final void wakeScreen(Context context) {
        try {
            Object systemService = context.getSystemService("power");
            PowerManager pm = systemService instanceof PowerManager ? (PowerManager) systemService : null;
            if (pm == null) {
                return;
            }
            PowerManager.WakeLock wl = pm.newWakeLock(805306378, "xiaofan:alarm");
            wl.setReferenceCounted(false);
            wl.acquire(30000L);
        } catch (Throwable th) {
        }
    }

    private final void playRingtone(Context context) {
        if (AppPrefs.INSTANCE.silent(context)) {
            return;
        }
        stopRingtone();
        Object systemService = context.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        AudioManager audioManager = systemService instanceof AudioManager ? (AudioManager) systemService : null;
        if (audioManager != null) {
            int maxVol = audioManager.getStreamMaxVolume(4);
            int curVol = audioManager.getStreamVolume(4);
            savedAudioManager = audioManager;
            savedAlarmVolume = curVol;
            int target = Math.max(curVol, (int) Math.ceil(maxVol * 0.7d));
            if (target > curVol) {
                try {
                    audioManager.setStreamVolume(4, target, 0);
                } catch (Throwable th) {
                }
            }
        }
        Uri uri = RingtoneManager.getDefaultUri(4);
        if (uri == null) {
            uri = RingtoneManager.getDefaultUri(2);
        }
        if (uri == null) {
            uri = RingtoneManager.getDefaultUri(1);
        }
        MediaPlayer player = new MediaPlayer();
        activePlayer = player;
        if (uri != null) {
            player.setDataSource(context, uri);
        } else {
            player.setDataSource(context, RingtoneManager.getDefaultUri(2));
        }
        player.setAudioStreamType(4);
        player.setAudioAttributes(new AudioAttributes.Builder().setUsage(4).setContentType(4).build());
        player.setLooping(true);
        player.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.xiaofan.bangfan.XiaoFanAlarm$$ExternalSyntheticLambda0
            @Override // android.media.MediaPlayer.OnErrorListener
            public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                boolean playRingtone$lambda$3;
                playRingtone$lambda$3 = XiaoFanAlarm.playRingtone$lambda$3(mediaPlayer, i, i2);
                return playRingtone$lambda$3;
            }
        });
        player.prepare();
        player.start();
        Log.i(TAG, "alarm ringtone started");
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.XiaoFanAlarm$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                XiaoFanAlarm.playRingtone$lambda$4();
            }
        }, 60000L);
        startVibration(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean playRingtone$lambda$3(MediaPlayer mediaPlayer, int what, int extra) {
        Log.w(TAG, "alarm player error what=" + what + " extra=" + extra);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void playRingtone$lambda$4() {
        INSTANCE.stopRingtone();
    }

    private final void startVibration(Context context) {
        try {
            if (AppPrefs.INSTANCE.vibrateOn(context)) {
                Object systemService = context.getSystemService("vibrator");
                Vibrator vibrator = systemService instanceof Vibrator ? (Vibrator) systemService : null;
                if (vibrator != null && vibrator.hasVibrator()) {
                    activeVibrator = vibrator;
                    long[] pattern = {0, 800, 500};
                    if (Build.VERSION.SDK_INT >= 26) {
                        vibrator.vibrate(VibrationEffect.createWaveform(pattern, 0));
                    } else {
                        vibrator.vibrate(pattern, 0);
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.XiaoFanAlarm$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            XiaoFanAlarm.startVibration$lambda$5();
                        }
                    }, 60000L);
                }
            }
        } catch (Throwable th) {
            Log.w(TAG, "startVibration failed", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startVibration$lambda$5() {
        INSTANCE.stopVibration();
    }

    private final void stopVibration() {
        try {
            Vibrator vibrator = activeVibrator;
            if (vibrator != null) {
                vibrator.cancel();
            }
        } catch (Throwable th) {
        }
        activeVibrator = null;
    }

    private final void stopRingtone() {
        int cur;
        int max;
        try {
            MediaPlayer it = activePlayer;
            if (it != null) {
                if (it.isPlaying()) {
                    it.stop();
                }
                it.release();
            }
        } catch (Throwable th) {
        }
        activePlayer = null;
        stopVibration();
        try {
            AudioManager am = savedAudioManager;
            if (am != null && savedAlarmVolume >= 0 && cur == (max = Math.max((cur = am.getStreamVolume(4)), (int) Math.ceil(am.getStreamMaxVolume(4) * 0.7d))) && savedAlarmVolume < max) {
                am.setStreamVolume(4, savedAlarmVolume, 0);
            }
        } catch (Throwable th2) {
        }
        savedAlarmVolume = -1;
        savedAudioManager = null;
    }

    private final void showNotification(Context context, Item item) {
        try {
            Object systemService = context.getSystemService("notification");
            NotificationManager nm = systemService instanceof NotificationManager ? (NotificationManager) systemService : null;
            if (nm == null) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 26) {
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "小翻闹钟", 4);
                channel.setDescription("闹钟提醒");
                channel.setSound(null, null);
                channel.enableVibration(false);
                channel.setBypassDnd(true);
                channel.setLockscreenVisibility(1);
                try {
                    nm.createNotificationChannel(channel);
                } catch (Throwable th) {
                }
            }
            Intent intent = new Intent(context, FunctionPanelActivity.class);
            intent.addFlags(268435456);
            intent.putExtra("alarm_id", item.getId());
            PendingIntent pi = PendingIntent.getActivity(context, item.getId(), intent, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
            Intent dismissIntent = new AlarmReceiver().intentFor(context, item.getId());
            dismissIntent.setAction(ACTION_DISMISS);
            PendingIntent dismissPi = PendingIntent.getBroadcast(context, item.getId() + NOTIF_BASE + 1000, dismissIntent, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
            Notification.Builder builder = Build.VERSION.SDK_INT >= 26 ? new Notification.Builder(context, CHANNEL_ID) : new Notification.Builder(context);
            builder.setContentTitle("小翻闹钟 · " + item.timeText()).setContentText(item.getLabel().length() > 0 ? item.getLabel() : "时间到了").setSmallIcon(17301550).setContentIntent(pi).setAutoCancel(true).setPriority(2).setCategory(NotificationCompat.CATEGORY_ALARM);
            builder.addAction(new Notification.Action.Builder((Icon) null, "关闭", dismissPi).build());
            nm.notify(item.getId() + NOTIF_BASE, builder.build());
        } catch (Throwable th2) {
            Log.w(TAG, "notify failed", th2);
        }
    }

    public final void dismiss(Context context, int id) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            AlarmRingService.Companion.stop(context);
        } catch (Throwable th) {
            Log.w(TAG, "stop ring service failed", th);
        }
        stopRingtone();
        try {
            Object systemService = context.getSystemService("notification");
            NotificationManager nm = systemService instanceof NotificationManager ? (NotificationManager) systemService : null;
            if (nm != null) {
                nm.cancel(id + NOTIF_BASE);
            }
            if (nm != null) {
                nm.cancel(7100);
            }
        } catch (Throwable th2) {
        }
        Log.i(TAG, "alarm dismissed id=" + id);
    }

    public final String remainingText(long nextAt) {
        long j;
        long diff = nextAt - System.currentTimeMillis();
        if (diff <= 0) {
            return "马上就要响了";
        }
        long minutes = diff / 60000;
        long hours = minutes / 60;
        if (hours >= 24) {
            long j2 = 24;
            return "还有 " + (hours / j2) + " 天 " + (hours % j2) + " 小时";
        } else if (hours >= 1) {
            return "还有 " + hours + " 小时 " + (minutes % j) + " 分钟";
        } else if (minutes >= 1) {
            return "还有 " + minutes + " 分钟";
        } else {
            return "还有 " + (diff / 1000) + " 秒";
        }
    }

    public final String formatAt(long at) {
        String format = new SimpleDateFormat("MM月dd日 HH:mm:ss", Locale.CHINA).format(new Date(at));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    /* compiled from: XiaoFanAlarm.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u001a\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004H\u0016¨\u0006\f"}, d2 = {"Lcom/xiaofan/bangfan/XiaoFanAlarm$AlarmReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "intentFor", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "id", "", "onReceive", "", "intent", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class AlarmReceiver extends BroadcastReceiver {
        public final Intent intentFor(Context context, int id) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, AlarmReceiver.class);
            intent.setAction(XiaoFanAlarm.ACTION_FIRE);
            intent.putExtra("alarm_id", id);
            return intent;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (intent == null) {
                return;
            }
            String action = intent.getAction();
            int id = intent.getIntExtra("alarm_id", 0);
            if (action != null) {
                switch (action.hashCode()) {
                    case -1675391454:
                        if (action.equals(XiaoFanAlarm.ACTION_FIRE)) {
                            XiaoFanAlarm.INSTANCE.fire(context, id);
                            return;
                        }
                        return;
                    case -1545632002:
                        if (action.equals(XiaoFanAlarm.ACTION_DISMISS)) {
                            XiaoFanAlarm.INSTANCE.dismiss(context, id);
                            return;
                        }
                        return;
                    case 502473491:
                        if (!action.equals("android.intent.action.TIMEZONE_CHANGED")) {
                            return;
                        }
                        break;
                    case 505380757:
                        if (!action.equals("android.intent.action.TIME_SET")) {
                            return;
                        }
                        break;
                    case 798292259:
                        if (!action.equals("android.intent.action.BOOT_COMPLETED")) {
                            return;
                        }
                        break;
                    case 1737074039:
                        if (!action.equals("android.intent.action.MY_PACKAGE_REPLACED")) {
                            return;
                        }
                        break;
                    default:
                        return;
                }
                XiaoFanAlarm.INSTANCE.rescheduleAll(context);
            }
        }
    }
}
