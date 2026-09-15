package com.xiaofan.bangfan;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.webrtc.MediaStreamTrack;
/* compiled from: AlarmRingService.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 )2\u00020\u0001:\u0001)B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\b\u0010\u0016\u001a\u00020\u0014H\u0002J\u0012\u0010\u0017\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0002J\u0014\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0014H\u0016J\b\u0010\u001f\u001a\u00020\u0014H\u0016J\"\u0010 \u001a\u00020\u00042\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u0004H\u0016J\b\u0010#\u001a\u00020\u0014H\u0002J\b\u0010$\u001a\u00020\u0014H\u0002J\u0012\u0010%\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0002J\b\u0010&\u001a\u00020\u0014H\u0002J\b\u0010'\u001a\u00020\u0014H\u0002J\b\u0010(\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0018\u00010\u0011R\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/xiaofan/bangfan/AlarmRingService;", "Landroid/app/Service;", "()V", "alarmId", "", "audioManager", "Landroid/media/AudioManager;", "main", "Landroid/os/Handler;", "player", "Landroid/media/MediaPlayer;", "savedAlarmVolume", "stopTask", "Ljava/lang/Runnable;", "vibrator", "Landroid/os/Vibrator;", "wakeLock", "Landroid/os/PowerManager$WakeLock;", "Landroid/os/PowerManager;", "acquireScreenWakeLock", "", "cancelAutoStop", "createChannel", "launchAlertActivity", "label", "", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "flags", "startId", "releasePlayer", "scheduleAutoStop", "startForegroundSafe", "startRingtone", "startVibration", "stopEverything", "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class AlarmRingService extends Service {
    public static final String ACTION_RING = "com.xiaofan.bangfan.action.ALARM_RING";
    public static final String ACTION_STOP = "com.xiaofan.bangfan.action.ALARM_RING_STOP";
    private static final String CHANNEL_ID = "xiaofan_alarm_ring";
    public static final Companion Companion = new Companion(null);
    public static final String EXTRA_ID = "alarm_id";
    public static final String EXTRA_LABEL = "alarm_label";
    private static final String HARD_STOP_ACTION = "com.xiaofan.bangfan.action.ALARM_HARD_STOP";
    private static final long MAX_RING_MS = 120000;
    private static final int NOTIF_ID = 7100;
    private static final String TAG = "AlarmRing";
    private int alarmId;
    private AudioManager audioManager;
    private MediaPlayer player;
    private Runnable stopTask;
    private Vibrator vibrator;
    private PowerManager.WakeLock wakeLock;
    private final Handler main = new Handler(Looper.getMainLooper());
    private int savedAlarmVolume = -1;

    /* compiled from: AlarmRingService.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/xiaofan/bangfan/AlarmRingService$Companion;", "", "()V", "ACTION_RING", "", "ACTION_STOP", "CHANNEL_ID", "EXTRA_ID", "EXTRA_LABEL", "HARD_STOP_ACTION", "MAX_RING_MS", "", "NOTIF_ID", "", "TAG", "start", "", "context", "Landroid/content/Context;", "id", "label", "stop", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, int id, String label) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, AlarmRingService.class);
            intent.setAction(AlarmRingService.ACTION_RING);
            intent.putExtra("alarm_id", id);
            intent.putExtra(AlarmRingService.EXTRA_LABEL, label == null ? "" : label);
            try {
                if (Build.VERSION.SDK_INT >= 26) {
                    context.startForegroundService(intent);
                } else {
                    context.startService(intent);
                }
            } catch (Throwable th) {
                Log.e(AlarmRingService.TAG, "start failed", th);
            }
        }

        public final void stop(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                Intent intent = new Intent(context, AlarmRingService.class);
                intent.setAction(AlarmRingService.ACTION_STOP);
                context.startService(intent);
            } catch (Throwable th) {
                Log.w(AlarmRingService.TAG, "stop request failed", th);
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        createChannel();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        String str;
        if (intent == null || (str = intent.getAction()) == null) {
            str = ACTION_RING;
        }
        if (Intrinsics.areEqual(ACTION_STOP, str)) {
            Log.i(TAG, "stop requested");
            stopEverything();
            stopSelf();
            return 2;
        }
        this.alarmId = intent != null ? intent.getIntExtra("alarm_id", 0) : 0;
        String label = intent != null ? intent.getStringExtra(EXTRA_LABEL) : null;
        startForegroundSafe(label);
        acquireScreenWakeLock();
        startRingtone();
        startVibration();
        launchAlertActivity(label);
        scheduleAutoStop();
        Log.i(TAG, "ring started id=" + this.alarmId);
        return 2;
    }

    private final void createChannel() {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        try {
            Object systemService = getSystemService("notification");
            NotificationManager nm = systemService instanceof NotificationManager ? (NotificationManager) systemService : null;
            if (nm == null) {
                return;
            }
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "小翻闹钟响铃", 4);
            channel.setDescription("闹钟响铃时的前台通知");
            channel.setSound(null, null);
            channel.enableVibration(false);
            channel.setBypassDnd(true);
            channel.setLockscreenVisibility(1);
            nm.createNotificationChannel(channel);
        } catch (Throwable th) {
            Log.w(TAG, "createChannel failed", th);
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:1|2|(1:4)(1:28)|5|(1:7)(1:27)|8|(7:13|(1:15)(1:25)|16|17|18|19|21)|26|(0)(0)|16|17|18|19|21|(1:(0))) */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void startForegroundSafe(java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 217
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.AlarmRingService.startForegroundSafe(java.lang.String):void");
    }

    private final void launchAlertActivity(String label) {
        try {
            Intent i = new Intent(this, AlarmAlertActivity.class);
            i.addFlags(335544320);
            i.putExtra("alarm_id", this.alarmId);
            i.putExtra(EXTRA_LABEL, label == null ? "" : label);
            startActivity(i);
            Log.i(TAG, "alarm alert activity launched");
        } catch (Throwable th) {
            Log.e(TAG, "launch alert activity failed", th);
        }
    }

    private final void acquireScreenWakeLock() {
        try {
            Object systemService = getSystemService("power");
            PowerManager pm = systemService instanceof PowerManager ? (PowerManager) systemService : null;
            if (pm == null) {
                return;
            }
            PowerManager.WakeLock wl = pm.newWakeLock(805306394, "xiaofan:alarmring");
            this.wakeLock = wl;
            wl.setReferenceCounted(false);
            wl.acquire(MAX_RING_MS);
        } catch (Throwable th) {
            Log.w(TAG, "wakelock failed", th);
        }
    }

    private final void startRingtone() {
        try {
            if (AppPrefs.INSTANCE.silent(this)) {
                Log.i(TAG, "silent mode: skip ringtone (vibration still runs)");
                return;
            }
            Object systemService = getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
            AudioManager am = systemService instanceof AudioManager ? (AudioManager) systemService : null;
            this.audioManager = am;
            if (am != null) {
                int maxVol = am.getStreamMaxVolume(4);
                int curVol = am.getStreamVolume(4);
                this.savedAlarmVolume = curVol;
                int target = Math.max(curVol, (int) Math.ceil(maxVol * 0.7d));
                if (target > curVol) {
                    try {
                        am.setStreamVolume(4, target, 0);
                    } catch (Throwable th) {
                    }
                }
            }
            Uri uri = RingtoneManager.getDefaultUri(4);
            if (uri == null) {
                uri = RingtoneManager.getDefaultUri(1);
            }
            if (uri == null) {
                uri = RingtoneManager.getDefaultUri(2);
            }
            if (uri == null) {
                Log.w(TAG, "no ringtone uri available on this device");
                return;
            }
            MediaPlayer mp = new MediaPlayer();
            this.player = mp;
            mp.setDataSource(this, uri);
            mp.setAudioStreamType(4);
            mp.setAudioAttributes(new AudioAttributes.Builder().setUsage(4).setContentType(4).build());
            mp.setLooping(true);
            mp.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.xiaofan.bangfan.AlarmRingService$$ExternalSyntheticLambda1
                @Override // android.media.MediaPlayer.OnErrorListener
                public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                    boolean startRingtone$lambda$0;
                    startRingtone$lambda$0 = AlarmRingService.startRingtone$lambda$0(mediaPlayer, i, i2);
                    return startRingtone$lambda$0;
                }
            });
            mp.prepare();
            mp.start();
            AudioManager audioManager = this.audioManager;
            Log.i(TAG, "ringtone started, alarm volume=" + (audioManager != null ? Integer.valueOf(audioManager.getStreamVolume(4)) : "?"));
        } catch (Throwable th2) {
            Log.e(TAG, "ringtone failed", th2);
            releasePlayer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean startRingtone$lambda$0(MediaPlayer mediaPlayer, int what, int extra) {
        Log.w(TAG, "player error what=" + what + " extra=" + extra);
        return true;
    }

    private final void startVibration() {
        try {
            if (!AppPrefs.INSTANCE.vibrateOn(this)) {
                Log.i(TAG, "vibration disabled by user");
                return;
            }
            Object systemService = getSystemService("vibrator");
            Vibrator v = systemService instanceof Vibrator ? (Vibrator) systemService : null;
            this.vibrator = v;
            if (v == null || !v.hasVibrator()) {
                Log.w(TAG, "device has no vibrator");
                return;
            }
            long[] pattern = {0, 900, 500};
            if (Build.VERSION.SDK_INT >= 26) {
                v.vibrate(VibrationEffect.createWaveform(pattern, 0));
            } else {
                v.vibrate(pattern, 0);
            }
            Log.i(TAG, "vibration started");
        } catch (Throwable th) {
            Log.e(TAG, "vibration failed", th);
        }
    }

    private final void scheduleAutoStop() {
        cancelAutoStop();
        Runnable task = new Runnable() { // from class: com.xiaofan.bangfan.AlarmRingService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AlarmRingService.scheduleAutoStop$lambda$1(AlarmRingService.this);
            }
        };
        this.stopTask = task;
        this.main.postDelayed(task, MAX_RING_MS);
        try {
            Object systemService = getSystemService(NotificationCompat.CATEGORY_ALARM);
            AlarmManager am = systemService instanceof AlarmManager ? (AlarmManager) systemService : null;
            if (am != null) {
                Intent intent = new Intent(this, AlarmRingService.class);
                intent.setAction(ACTION_STOP);
                PendingIntent pi = PendingIntent.getService(this, 99, intent, 201326592);
                am.set(0, System.currentTimeMillis() + MAX_RING_MS + 5000, pi);
                Log.i(TAG, "hard stop scheduled via AlarmManager");
            }
        } catch (Throwable th) {
            Log.w(TAG, "hard stop schedule failed", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void scheduleAutoStop$lambda$1(AlarmRingService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.i(TAG, "auto stop after max ring time (handler)");
        this$0.stopEverything();
        this$0.stopSelf();
    }

    private final void cancelAutoStop() {
        Runnable it = this.stopTask;
        if (it != null) {
            this.main.removeCallbacks(it);
        }
        this.stopTask = null;
        try {
            Object systemService = getSystemService(NotificationCompat.CATEGORY_ALARM);
            AlarmManager am = systemService instanceof AlarmManager ? (AlarmManager) systemService : null;
            if (am != null) {
                Intent intent = new Intent(this, AlarmRingService.class);
                intent.setAction(ACTION_STOP);
                PendingIntent pi = PendingIntent.getService(this, 99, intent, 201326592);
                am.cancel(pi);
            }
        } catch (Throwable th) {
        }
    }

    private final void releasePlayer() {
        try {
            MediaPlayer it = this.player;
            if (it != null) {
                if (it.isPlaying()) {
                    it.stop();
                }
                it.release();
            }
        } catch (Throwable th) {
        }
        this.player = null;
    }

    private final void stopEverything() {
        cancelAutoStop();
        releasePlayer();
        try {
            Vibrator vibrator = this.vibrator;
            if (vibrator != null) {
                vibrator.cancel();
            }
        } catch (Throwable th) {
        }
        this.vibrator = null;
        try {
            AudioManager am = this.audioManager;
            if (am != null && this.savedAlarmVolume >= 0) {
                int cur = am.getStreamVolume(4);
                int max = am.getStreamMaxVolume(4);
                int target = Math.max(cur, (int) Math.ceil(max * 0.7d));
                if (cur == target && this.savedAlarmVolume < target) {
                    am.setStreamVolume(4, this.savedAlarmVolume, 0);
                }
            }
        } catch (Throwable th2) {
        }
        this.savedAlarmVolume = -1;
        this.audioManager = null;
        try {
            PowerManager.WakeLock it = this.wakeLock;
            if (it != null && it.isHeld()) {
                it.release();
            }
        } catch (Throwable th3) {
        }
        this.wakeLock = null;
        try {
            stopForeground(true);
        } catch (Throwable th4) {
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        stopEverything();
        Log.i(TAG, "ring service destroyed");
        super.onDestroy();
    }
}
