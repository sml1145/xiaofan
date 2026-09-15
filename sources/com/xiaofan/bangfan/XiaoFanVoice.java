package com.xiaofan.bangfan;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: XiaoFanVoice.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u001f\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001?B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0010\u0010\u001e\u001a\u00020\u001b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0011J\u0018\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u0006H\u0002J\u0010\u0010$\u001a\u00020\u001b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0011J\b\u0010%\u001a\u00020\u001bH\u0002J\u0016\u0010&\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020\u0006J\u000e\u0010(\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0018\u0010)\u001a\u00020\u000b2\u0006\u0010*\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u0006H\u0002J\u0010\u0010+\u001a\u00020\u000b2\b\u0010'\u001a\u0004\u0018\u00010\u0006J\u0006\u0010,\u001a\u00020\u000bJ\u0006\u0010-\u001a\u00020\u000bJ\u0006\u0010.\u001a\u00020\u000bJ\u0010\u0010/\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u0006H\u0002J\b\u00100\u001a\u00020\u001bH\u0002J\u000e\u00101\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u00102\u001a\u00020\u001bJ*\u00103\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020\u00062\b\u0010'\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u00104\u001a\u00020\u000bJ\u0010\u00105\u001a\u00020\u001b2\b\u00106\u001a\u0004\u0018\u00010\u0013J\u0006\u00107\u001a\u00020\u001bJ\u0018\u00108\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020\u0006H\u0002J\u0018\u00109\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020\u0006H\u0002J\u0006\u0010:\u001a\u00020\u001bJ\u0016\u0010;\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020\u0006J\u0016\u0010<\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020\u0006J\u0016\u0010=\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020\u0006J\u0018\u0010>\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Lcom/xiaofan/bangfan/XiaoFanVoice;", "", "()V", "MAX_GUARD_ENTRIES", "", "TAG", "", "TIP_REPEAT_COOLDOWN_MS", "", "WARN_REPEAT_COOLDOWN_MS", "failed", "", "lastSpokenAt", "Ljava/util/concurrent/ConcurrentHashMap;", "lastSpokenText", "mascotRef", "Ljava/lang/ref/WeakReference;", "Lcom/xiaofan/bangfan/MascotView;", "mouthCallback", "Lcom/xiaofan/bangfan/XiaoFanVoice$MouthCallback;", "ready", "speakingNow", "tts", "Landroid/speech/tts/TextToSpeech;", "utteranceId", "Ljava/util/concurrent/atomic/AtomicInteger;", "applyVoiceParams", "", "context", "Landroid/content/Context;", "attachMascot", "mascot", "charOverlap", "", "a", "b", "detachMascot", "endSpeaking", XiaoFanBrain.INTENT_GREET, "text", "init", "isDuplicate", "type", "isEchoOfSpeech", "isFailed", "isReady", "isSpeakingNow", "normalizeForCompare", "notifyMouthStart", "preview", "resetRepeatGuard", "say", "bypassRepeatGuard", "setMouthCallback", "cb", "shutdown", "speakWithCustomVoice", "speakWithSystemTts", "stopSpeaking", "tip", XiaoFanBrain.INTENT_TURN, "warn", "withNickname", "MouthCallback", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class XiaoFanVoice {
    private static final int MAX_GUARD_ENTRIES = 64;
    private static final String TAG = "XiaoFanVoice";
    private static final long TIP_REPEAT_COOLDOWN_MS = 30000;
    private static final long WARN_REPEAT_COOLDOWN_MS = 300000;
    private static volatile boolean failed;
    private static volatile WeakReference<MascotView> mascotRef;
    private static volatile MouthCallback mouthCallback;
    private static volatile boolean ready;
    private static volatile boolean speakingNow;
    private static volatile TextToSpeech tts;
    public static final XiaoFanVoice INSTANCE = new XiaoFanVoice();
    private static final AtomicInteger utteranceId = new AtomicInteger(0);
    private static volatile String lastSpokenText = "";
    private static final ConcurrentHashMap<String, Long> lastSpokenAt = new ConcurrentHashMap<>();

    /* compiled from: XiaoFanVoice.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/xiaofan/bangfan/XiaoFanVoice$MouthCallback;", "", "onSpeakEnd", "", "onSpeakStart", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public interface MouthCallback {
        void onSpeakEnd();

        void onSpeakStart();
    }

    private XiaoFanVoice() {
    }

    public final boolean isSpeakingNow() {
        return speakingNow;
    }

    public final boolean isEchoOfSpeech(String text) {
        if (speakingNow) {
            String str = text;
            if (!(str == null || StringsKt.isBlank(str))) {
                String last = lastSpokenText;
                return !(last.length() == 0) && charOverlap(normalizeForCompare(text), normalizeForCompare(last)) >= 0.62f;
            }
        }
        return false;
    }

    private final String normalizeForCompare(String text) {
        int i;
        StringBuilder sb = new StringBuilder();
        int length = text.length();
        for (i = 0; i < length; i = i + 1) {
            char c = text.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                i = 19968 <= c && c < 40960 ? 0 : i + 1;
            }
            sb.append(c);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    private final float charOverlap(String a, String b) {
        if (!(a.length() == 0)) {
            if (!(b.length() == 0)) {
                HashMap counts = new HashMap();
                int length = b.length();
                for (int i = 0; i < length; i++) {
                    char c = b.charAt(i);
                    HashMap hashMap = counts;
                    Character valueOf = Character.valueOf(c);
                    Integer num = (Integer) counts.get(Character.valueOf(c));
                    if (num == null) {
                        num = 0;
                    }
                    hashMap.put(valueOf, Integer.valueOf(num.intValue() + 1));
                }
                int matched = 0;
                int length2 = a.length();
                for (int i2 = 0; i2 < length2; i2++) {
                    char c2 = a.charAt(i2);
                    Integer num2 = (Integer) counts.get(Character.valueOf(c2));
                    if (num2 == null) {
                        num2 = 0;
                    }
                    int count = num2.intValue();
                    if (count > 0) {
                        counts.put(Character.valueOf(c2), Integer.valueOf(count - 1));
                        matched++;
                    }
                }
                int max = Math.max(a.length(), b.length());
                if (max == 0) {
                    return 0.0f;
                }
                return matched / max;
            }
        }
        return 0.0f;
    }

    public final void setMouthCallback(MouthCallback cb) {
        mouthCallback = cb;
    }

    public final void attachMascot(final MascotView mascot) {
        if (mascot == null) {
            return;
        }
        mascotRef = new WeakReference<>(mascot);
        mouthCallback = new MouthCallback() { // from class: com.xiaofan.bangfan.XiaoFanVoice$attachMascot$1
            @Override // com.xiaofan.bangfan.XiaoFanVoice.MouthCallback
            public void onSpeakStart() {
                MascotView.this.setSpeaking(true);
            }

            @Override // com.xiaofan.bangfan.XiaoFanVoice.MouthCallback
            public void onSpeakEnd() {
                MascotView.this.setSpeaking(false);
            }
        };
    }

    public final void detachMascot(MascotView mascot) {
        if (mascot != null) {
            WeakReference<MascotView> weakReference = mascotRef;
            if ((weakReference != null ? weakReference.get() : null) != mascot) {
                return;
            }
            mascotRef = null;
            mouthCallback = null;
        }
    }

    public final void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        final Context appContext = context.getApplicationContext();
        if (tts != null || failed) {
            return;
        }
        try {
            tts = new TextToSpeech(appContext, new TextToSpeech.OnInitListener() { // from class: com.xiaofan.bangfan.XiaoFanVoice$$ExternalSyntheticLambda1
                @Override // android.speech.tts.TextToSpeech.OnInitListener
                public final void onInit(int i) {
                    XiaoFanVoice.init$lambda$0(appContext, i);
                }
            });
        } catch (Throwable th) {
            failed = true;
            Log.e(TAG, "TTS create failed", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(Context $appContext, int status) {
        TextToSpeech t = tts;
        if (status != 0 || t == null) {
            ready = false;
            failed = true;
            Log.w(TAG, "TTS init failed status=" + status);
            return;
        }
        Locale locale = Locale.CHINA;
        int available = t.isLanguageAvailable(locale);
        switch (available) {
            case -2:
            case -1:
                ready = false;
                failed = true;
                Log.w(TAG, "TTS zh-CN not available: " + available);
                return;
            default:
                t.setLanguage(locale);
                XiaoFanVoice xiaoFanVoice = INSTANCE;
                Intrinsics.checkNotNull($appContext);
                xiaoFanVoice.applyVoiceParams($appContext);
                t.setOnUtteranceProgressListener(new UtteranceProgressListener() { // from class: com.xiaofan.bangfan.XiaoFanVoice$init$1$1
                    @Override // android.speech.tts.UtteranceProgressListener
                    public void onDone(String utteranceId2) {
                    }

                    @Override // android.speech.tts.UtteranceProgressListener
                    public void onStart(String utteranceId2) {
                    }

                    @Override // android.speech.tts.UtteranceProgressListener
                    public void onError(String utteranceId2) {
                        Log.w("XiaoFanVoice", "utterance error id=" + utteranceId2);
                    }
                });
                ready = true;
                failed = false;
                Log.i(TAG, "TTS ready, elf-child voice applied");
                return;
        }
    }

    public final void applyVoiceParams(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        TextToSpeech t = tts;
        if (t != null && ready) {
            try {
                String locale = AppPrefs.INSTANCE.voiceLocale(context);
                if (locale.length() > 0) {
                    VoicePack.INSTANCE.applySystemVoice(t, locale);
                }
                t.setPitch(AppPrefs.INSTANCE.speakPitch(context));
                t.setSpeechRate(AppPrefs.INSTANCE.speakRate(context));
            } catch (Throwable th) {
            }
        }
    }

    public final boolean isReady() {
        return ready;
    }

    public final boolean isFailed() {
        return failed;
    }

    private final boolean isDuplicate(String type, String text) {
        long now = System.currentTimeMillis();
        long cooldown = Intrinsics.areEqual(type, "warn") ? WARN_REPEAT_COOLDOWN_MS : TIP_REPEAT_COOLDOWN_MS;
        Long last = lastSpokenAt.get(type);
        if (last == null || now - last.longValue() >= cooldown) {
            if (lastSpokenAt.size() >= 64) {
                lastSpokenAt.clear();
            }
            lastSpokenAt.put(type, Long.valueOf(now));
            return false;
        }
        return true;
    }

    public final void resetRepeatGuard() {
        lastSpokenAt.clear();
    }

    public static /* synthetic */ void say$default(XiaoFanVoice xiaoFanVoice, Context context, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        xiaoFanVoice.say(context, str, str2, z);
    }

    public final void say(Context context, String type, String text, boolean bypassRepeatGuard) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        String str = text;
        if ((str == null || str.length() == 0) || !AppPrefs.INSTANCE.speakOn(context) || AppPrefs.INSTANCE.silent(context)) {
            return;
        }
        if (!Intrinsics.areEqual(type, "tip") || AppPrefs.INSTANCE.speakTips(context)) {
            if (!Intrinsics.areEqual(type, XiaoFanBrain.INTENT_TURN) || AppPrefs.INSTANCE.speakTurn(context)) {
                if (!bypassRepeatGuard && !Intrinsics.areEqual(type, XiaoFanBrain.INTENT_TURN) && isDuplicate(type, text)) {
                    Log.i(TAG, "skip duplicate speech within cooldown: " + text);
                    return;
                }
                init(context);
                if (!ready) {
                    Log.i(TAG, "TTS not ready, skip: " + text);
                    return;
                }
                String withNick = withNickname(context, text);
                lastSpokenText = text;
                speakingNow = true;
                notifyMouthStart();
                if (CustomVoice.INSTANCE.isReady(context)) {
                    speakWithCustomVoice(context, withNick);
                } else {
                    speakWithSystemTts(context, withNick);
                }
            }
        }
    }

    private final void notifyMouthStart() {
        try {
            MouthCallback mouthCallback2 = mouthCallback;
            if (mouthCallback2 != null) {
                mouthCallback2.onSpeakStart();
            }
        } catch (Throwable th) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void endSpeaking() {
        speakingNow = false;
        try {
            MouthCallback mouthCallback2 = mouthCallback;
            if (mouthCallback2 != null) {
                mouthCallback2.onSpeakEnd();
            }
        } catch (Throwable th) {
        }
    }

    private final void speakWithSystemTts(Context context, String text) {
        String id = "xf-" + utteranceId.incrementAndGet();
        long duration = Math.max(400L, (text.length() * 230.0f) / Math.max(0.5f, AppPrefs.INSTANCE.speakRate(context))) + 500;
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.XiaoFanVoice$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                XiaoFanVoice.speakWithSystemTts$lambda$1();
            }
        }, duration);
        try {
            TextToSpeech textToSpeech = tts;
            if (textToSpeech != null) {
                textToSpeech.speak(text, 0, null, id);
            }
        } catch (Throwable th) {
            Log.w(TAG, "speak failed", th);
            endSpeaking();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void speakWithSystemTts$lambda$1() {
        INSTANCE.endSpeaking();
    }

    private final void speakWithCustomVoice(Context context, final String text) {
        final Context appContext = context.getApplicationContext();
        new Thread(new Runnable() { // from class: com.xiaofan.bangfan.XiaoFanVoice$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                XiaoFanVoice.speakWithCustomVoice$lambda$3(appContext, text);
            }
        }, "custom-voice").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void speakWithCustomVoice$lambda$3(final Context $appContext, final String text) {
        Intrinsics.checkNotNullParameter(text, "$text");
        boolean success = false;
        try {
            CustomVoice customVoice = CustomVoice.INSTANCE;
            Intrinsics.checkNotNull($appContext);
            success = customVoice.speak($appContext, text, new XiaoFanVoice$speakWithCustomVoice$1$1());
        } catch (Throwable th) {
            Log.w(TAG, "custom voice speak threw", th);
        }
        if (!success) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.xiaofan.bangfan.XiaoFanVoice$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    XiaoFanVoice.speakWithCustomVoice$lambda$3$lambda$2($appContext, text);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void speakWithCustomVoice$lambda$3$lambda$2(Context $appContext, String text) {
        Intrinsics.checkNotNullParameter(text, "$text");
        XiaoFanVoice xiaoFanVoice = INSTANCE;
        Intrinsics.checkNotNull($appContext);
        xiaoFanVoice.speakWithSystemTts($appContext, text);
    }

    public final void tip(Context context, String text) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(text, "text");
        say$default(this, context, "tip", text, false, 8, null);
    }

    public final void greet(Context context, String text) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(text, "text");
        say(context, XiaoFanBrain.INTENT_GREET, text, true);
    }

    public final void turn(Context context, String text) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(text, "text");
        say$default(this, context, XiaoFanBrain.INTENT_TURN, text, false, 8, null);
    }

    public final void warn(Context context, String text) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(text, "text");
        say$default(this, context, "warn", text, false, 8, null);
    }

    private final String withNickname(Context context, String text) {
        String nickname = AppPrefs.INSTANCE.nickname(context);
        return ((nickname.length() == 0) || StringsKt.contains$default((CharSequence) text, (CharSequence) nickname, false, 2, (Object) null)) ? text : nickname + "，" + text;
    }

    public final void preview(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (!AppPrefs.INSTANCE.speakOn(context) || AppPrefs.INSTANCE.silent(context)) {
            return;
        }
        String text = AppPrefs.INSTANCE.nickname(context) + "，我是小翻，这个声音你喜欢吗？";
        if (CustomVoice.INSTANCE.isReady(context)) {
            lastSpokenText = text;
            speakingNow = true;
            notifyMouthStart();
            speakWithCustomVoice(context, text);
            return;
        }
        init(context);
        if (ready) {
            applyVoiceParams(context);
            try {
                lastSpokenText = text;
                speakingNow = true;
                notifyMouthStart();
                TextToSpeech textToSpeech = tts;
                if (textToSpeech != null) {
                    textToSpeech.speak(text, 0, null, "xf-preview");
                }
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.XiaoFanVoice$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        XiaoFanVoice.preview$lambda$4();
                    }
                }, Math.max(600L, text.length() * 260));
            } catch (Throwable th) {
                endSpeaking();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void preview$lambda$4() {
        INSTANCE.endSpeaking();
    }

    public final void stopSpeaking() {
        speakingNow = false;
        try {
            TextToSpeech textToSpeech = tts;
            if (textToSpeech != null) {
                textToSpeech.stop();
            }
        } catch (Throwable th) {
        }
        try {
            CustomVoice.INSTANCE.stopPlayback();
        } catch (Throwable th2) {
        }
        try {
            MouthCallback mouthCallback2 = mouthCallback;
            if (mouthCallback2 != null) {
                mouthCallback2.onSpeakEnd();
            }
        } catch (Throwable th3) {
        }
    }

    public final void shutdown() {
        speakingNow = false;
        try {
            CustomVoice.INSTANCE.stopPlayback();
        } catch (Throwable th) {
        }
        try {
            TextToSpeech it = tts;
            if (it != null) {
                it.stop();
                it.shutdown();
            }
        } catch (Throwable th2) {
        }
        tts = null;
        ready = false;
    }
}
