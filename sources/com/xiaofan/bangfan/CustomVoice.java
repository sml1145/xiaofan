package com.xiaofan.bangfan;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaDataSource;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.xiaofan.bangfan.CustomVoice;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;
import org.webrtc.MediaStreamTrack;
/* compiled from: CustomVoice.kt */
@Metadata(d1 = {"\u0000\u0085\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\r\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002IJB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001b\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122\u0006\u0010\u0013\u001a\u00020\u0004H\u0002¢\u0006\u0002\u0010\u0014J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0004H\u0002J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0016H\u0002J(\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u00042\u0006\u0010!\u001a\u00020\"J\u0010\u0010#\u001a\u00020\u00162\u0006\u0010$\u001a\u00020\u0004H\u0002J\u0018\u0010%\u001a\u00020\u00162\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u0004H\u0002J\u0012\u0010(\u001a\u0004\u0018\u00010\u00162\u0006\u0010)\u001a\u00020\u0004H\u0002J\u0012\u0010*\u001a\u0004\u0018\u00010\u00042\b\u0010$\u001a\u0004\u0018\u00010\u0004J\u0012\u0010+\u001a\u00020\u00042\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0002J\u0018\u0010,\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u000e\u0010-\u001a\u00020.2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010/\u001a\u00020.2\u0006\u0010\u001c\u001a\u00020\u001dJ \u00100\u001a\u0002012\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u0004H\u0002J\u001a\u00103\u001a\u00020.2\u0006\u0010\u001a\u001a\u00020\u00162\b\u0010!\u001a\u0004\u0018\u000104H\u0002J0\u00105\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u00106\u001a\u00020\u0004H\u0002J\u0010\u00107\u001a\u00020\u00042\u0006\u00108\u001a\u000209H\u0002J\u0010\u0010:\u001a\u00020\u00162\u0006\u00108\u001a\u000209H\u0002J\u0010\u0010;\u001a\u00020\u00042\u0006\u0010<\u001a\u000201H\u0002J\u001a\u0010=\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J \u0010>\u001a\u00020.2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010?\u001a\u00020\u00042\b\u0010!\u001a\u0004\u0018\u000104J\u000e\u0010@\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010A\u001a\u00020\u0019J\"\u0010B\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010?\u001a\u00020\u00042\u0006\u0010C\u001a\u00020\u0004H\u0002J\u0012\u0010D\u001a\u00020\u00042\b\u0010$\u001a\u0004\u0018\u00010\u0004H\u0002J \u0010E\u001a\u00020\u00192\u0006\u0010F\u001a\u00020G2\u0006\u0010 \u001a\u00020\u00042\u0006\u0010H\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006K"}, d2 = {"Lcom/xiaofan/bangfan/CustomVoice;", "", "()V", "BOUNDARY", "", "CACHE_MAX_ENTRIES", "", "MAX_SAMPLE_BYTES", "TAG", "UTF8", "Ljava/nio/charset/Charset;", "kotlin.jvm.PlatformType", "cache", "com/xiaofan/bangfan/CustomVoice$cache$1", "Lcom/xiaofan/bangfan/CustomVoice$cache$1;", "player", "Landroid/media/MediaPlayer;", "buildCloneUrls", "", "endpoint", "(Ljava/lang/String;)[Ljava/lang/String;", "cacheHit", "", "key", "cachePut", "", "data", "clone", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "name", "callback", "Lcom/xiaofan/bangfan/CustomVoice$Callback;", "decodeBase64", "str", "downloadBytes", "url", "apiKey", "extractAudioFromJson", "json", "extractVoiceId", "guessFromPath", "guessMime", "isConfigured", "", "isReady", "openJson", "Ljava/net/HttpURLConnection;", "method", "playAudio", "Lcom/xiaofan/bangfan/CustomVoice$SpeakCallback;", "postMultipart", "mime", "readAll", "input", "Ljava/io/InputStream;", "readAllBytes", "readErrorBody", "conn", "readUri", "speak", "text", "statusText", "stopPlayback", "synthesize", "voiceId", "truncate", "writeField", "out", "Ljava/io/DataOutputStream;", "value", "Callback", "SpeakCallback", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class CustomVoice {
    private static final String BOUNDARY = "----XiaoFanVoiceBoundary7d4a1b2c";
    private static final int CACHE_MAX_ENTRIES = 24;
    private static final int MAX_SAMPLE_BYTES = 20971520;
    private static final String TAG = "CustomVoice";
    private static volatile MediaPlayer player;
    public static final CustomVoice INSTANCE = new CustomVoice();
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final CustomVoice$cache$1 cache = new CustomVoice$cache$1();

    /* compiled from: CustomVoice.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/xiaofan/bangfan/CustomVoice$Callback;", "", "onError", "", NotificationCompat.CATEGORY_MESSAGE, "", "onSuccess", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public interface Callback {
        void onError(String str);

        void onSuccess(String str);
    }

    /* compiled from: CustomVoice.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\u0003H&¨\u0006\b"}, d2 = {"Lcom/xiaofan/bangfan/CustomVoice$SpeakCallback;", "", "onEnd", "", "onFallback", NotificationCompat.CATEGORY_MESSAGE, "", "onStart", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public interface SpeakCallback {
        void onEnd();

        void onFallback(String str);

        void onStart();
    }

    private CustomVoice() {
    }

    public final boolean isConfigured(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return AppPrefs.INSTANCE.ttsEndpoint(context).length() > 0;
    }

    public final boolean isReady(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return AppPrefs.INSTANCE.customVoiceReady(context);
    }

    public final String statusText(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (isConfigured(context)) {
            if (AppPrefs.INSTANCE.ttsVoiceId(context).length() == 0) {
                return "已配置服务，尚未克隆音色";
            }
            String name = AppPrefs.INSTANCE.customVoiceName(context);
            return name.length() > 0 ? "自定义音色已就绪：" + name : "自定义音色已就绪";
        }
        return "未配置云端音色服务（可选）";
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0081 A[Catch: all -> 0x01d3, TryCatch #4 {all -> 0x01d3, blocks: (B:10:0x0033, B:12:0x003e, B:18:0x0048, B:20:0x004d, B:22:0x006f, B:24:0x0075, B:30:0x0081, B:32:0x00a5, B:34:0x00b5, B:48:0x011c, B:50:0x0121, B:56:0x012d, B:59:0x015b, B:61:0x0160, B:67:0x016c, B:69:0x0189, B:70:0x01a3, B:73:0x01ac, B:74:0x01ad, B:57:0x0149, B:44:0x00f6, B:46:0x0114, B:31:0x0099, B:78:0x01cd, B:72:0x01a5), top: B:87:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0099 A[Catch: all -> 0x01d3, TryCatch #4 {all -> 0x01d3, blocks: (B:10:0x0033, B:12:0x003e, B:18:0x0048, B:20:0x004d, B:22:0x006f, B:24:0x0075, B:30:0x0081, B:32:0x00a5, B:34:0x00b5, B:48:0x011c, B:50:0x0121, B:56:0x012d, B:59:0x015b, B:61:0x0160, B:67:0x016c, B:69:0x0189, B:70:0x01a3, B:73:0x01ac, B:74:0x01ad, B:57:0x0149, B:44:0x00f6, B:46:0x0114, B:31:0x0099, B:78:0x01cd, B:72:0x01a5), top: B:87:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00b5 A[Catch: all -> 0x01d3, TRY_LEAVE, TryCatch #4 {all -> 0x01d3, blocks: (B:10:0x0033, B:12:0x003e, B:18:0x0048, B:20:0x004d, B:22:0x006f, B:24:0x0075, B:30:0x0081, B:32:0x00a5, B:34:0x00b5, B:48:0x011c, B:50:0x0121, B:56:0x012d, B:59:0x015b, B:61:0x0160, B:67:0x016c, B:69:0x0189, B:70:0x01a3, B:73:0x01ac, B:74:0x01ad, B:57:0x0149, B:44:0x00f6, B:46:0x0114, B:31:0x0099, B:78:0x01cd, B:72:0x01a5), top: B:87:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0149 A[Catch: all -> 0x01d3, LOOP:0: B:33:0x00b3->B:57:0x0149, LOOP_END, TryCatch #4 {all -> 0x01d3, blocks: (B:10:0x0033, B:12:0x003e, B:18:0x0048, B:20:0x004d, B:22:0x006f, B:24:0x0075, B:30:0x0081, B:32:0x00a5, B:34:0x00b5, B:48:0x011c, B:50:0x0121, B:56:0x012d, B:59:0x015b, B:61:0x0160, B:67:0x016c, B:69:0x0189, B:70:0x01a3, B:73:0x01ac, B:74:0x01ad, B:57:0x0149, B:44:0x00f6, B:46:0x0114, B:31:0x0099, B:78:0x01cd, B:72:0x01a5), top: B:87:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x016c A[Catch: all -> 0x01d3, TryCatch #4 {all -> 0x01d3, blocks: (B:10:0x0033, B:12:0x003e, B:18:0x0048, B:20:0x004d, B:22:0x006f, B:24:0x0075, B:30:0x0081, B:32:0x00a5, B:34:0x00b5, B:48:0x011c, B:50:0x0121, B:56:0x012d, B:59:0x015b, B:61:0x0160, B:67:0x016c, B:69:0x0189, B:70:0x01a3, B:73:0x01ac, B:74:0x01ad, B:57:0x0149, B:44:0x00f6, B:46:0x0114, B:31:0x0099, B:78:0x01cd, B:72:0x01a5), top: B:87:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0189 A[Catch: all -> 0x01d3, TryCatch #4 {all -> 0x01d3, blocks: (B:10:0x0033, B:12:0x003e, B:18:0x0048, B:20:0x004d, B:22:0x006f, B:24:0x0075, B:30:0x0081, B:32:0x00a5, B:34:0x00b5, B:48:0x011c, B:50:0x0121, B:56:0x012d, B:59:0x015b, B:61:0x0160, B:67:0x016c, B:69:0x0189, B:70:0x01a3, B:73:0x01ac, B:74:0x01ad, B:57:0x0149, B:44:0x00f6, B:46:0x0114, B:31:0x0099, B:78:0x01cd, B:72:0x01a5), top: B:87:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x012d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0157 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void clone(android.content.Context r22, android.net.Uri r23, java.lang.String r24, com.xiaofan.bangfan.CustomVoice.Callback r25) {
        /*
            Method dump skipped, instructions count: 502
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.CustomVoice.clone(android.content.Context, android.net.Uri, java.lang.String, com.xiaofan.bangfan.CustomVoice$Callback):void");
    }

    private final String[] buildCloneUrls(String endpoint) {
        String ep = endpoint;
        if (StringsKt.endsWith$default(ep, "/", false, 2, (Object) null)) {
            String substring = ep.substring(0, ep.length() - 1);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            ep = substring;
        }
        List list = new ArrayList();
        if (!StringsKt.endsWith$default(ep, "/voices", false, 2, (Object) null)) {
            list.add(ep + "/voices");
        }
        list.add(ep);
        List $this$toTypedArray$iv = list;
        return (String[]) $this$toTypedArray$iv.toArray(new String[0]);
    }

    public final boolean speak(Context context, String text, SpeakCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(text, "text");
        if (StringsKt.isBlank(text) || !isReady(context)) {
            return false;
        }
        String voiceId = AppPrefs.INSTANCE.ttsVoiceId(context);
        String cacheKey = voiceId + "|" + text;
        try {
            byte[] audio = cacheHit(cacheKey);
            if (audio == null) {
                if (callback != null) {
                    callback.onStart();
                }
                byte[] audio2 = synthesize(context, text, voiceId);
                if (audio2 != null) {
                    if (!(audio2.length == 0)) {
                        cachePut(cacheKey, audio2);
                    }
                }
                return false;
            }
            if (callback != null) {
                callback.onStart();
            }
            return playAudio(audio, callback);
        } catch (Throwable th) {
            Log.w(TAG, "speak failed", th);
            if (callback != null) {
                String message = th.getMessage();
                if (message == null) {
                    message = th.toString();
                }
                callback.onFallback(message);
                return false;
            }
            return false;
        }
    }

    private final byte[] cacheHit(String key) {
        byte[] bArr;
        synchronized (cache) {
            bArr = (byte[]) cache.get((Object) key);
        }
        return bArr;
    }

    private final void cachePut(String key, byte[] data) {
        synchronized (cache) {
            cache.put(key, data);
            Unit unit = Unit.INSTANCE;
        }
    }

    private final boolean playAudio(final byte[] data, final SpeakCallback callback) {
        try {
            stopPlayback();
            MediaPlayer mp = new MediaPlayer();
            player = mp;
            mp.setDataSource(new MediaDataSource() { // from class: com.xiaofan.bangfan.CustomVoice$playAudio$1
                @Override // java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                }

                @Override // android.media.MediaDataSource
                public int readAt(long position, byte[] buffer, int offset, int size) {
                    int len;
                    Intrinsics.checkNotNullParameter(buffer, "buffer");
                    if (position < data.length && (len = Math.min(size, data.length - ((int) position))) > 0) {
                        System.arraycopy(data, (int) position, buffer, offset, len);
                        return len;
                    }
                    return -1;
                }

                @Override // android.media.MediaDataSource
                public long getSize() {
                    return data.length;
                }
            });
            mp.setAudioAttributes(new AudioAttributes.Builder().setUsage(13).setContentType(1).build());
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.xiaofan.bangfan.CustomVoice$$ExternalSyntheticLambda0
                @Override // android.media.MediaPlayer.OnCompletionListener
                public final void onCompletion(MediaPlayer mediaPlayer) {
                    CustomVoice.playAudio$lambda$3(CustomVoice.SpeakCallback.this, mediaPlayer);
                }
            });
            mp.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.xiaofan.bangfan.CustomVoice$$ExternalSyntheticLambda1
                @Override // android.media.MediaPlayer.OnErrorListener
                public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                    boolean playAudio$lambda$4;
                    playAudio$lambda$4 = CustomVoice.playAudio$lambda$4(CustomVoice.SpeakCallback.this, mediaPlayer, i, i2);
                    return playAudio$lambda$4;
                }
            });
            mp.prepare();
            mp.start();
            Log.i(TAG, "playing custom voice audio " + data.length + " bytes");
            return true;
        } catch (Throwable th) {
            Log.w(TAG, "playAudio failed", th);
            stopPlayback();
            if (callback != null) {
                callback.onFallback("音频播放失败：" + th.getMessage());
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void playAudio$lambda$3(SpeakCallback $callback, MediaPlayer it) {
        INSTANCE.stopPlayback();
        if ($callback != null) {
            $callback.onEnd();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean playAudio$lambda$4(SpeakCallback $callback, MediaPlayer mediaPlayer, int what, int extra) {
        Log.w(TAG, "player error what=" + what + " extra=" + extra);
        INSTANCE.stopPlayback();
        if ($callback != null) {
            $callback.onEnd();
            return true;
        }
        return true;
    }

    public final void stopPlayback() {
        MediaPlayer mp = player;
        player = null;
        if (mp != null) {
            try {
                if (mp.isPlaying()) {
                    mp.stop();
                }
            } catch (Throwable th) {
            }
            try {
                mp.release();
            } catch (Throwable th2) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x009e A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final byte[] synthesize(android.content.Context r12, java.lang.String r13, java.lang.String r14) {
        /*
            r11 = this;
            com.xiaofan.bangfan.AppPrefs r0 = com.xiaofan.bangfan.AppPrefs.INSTANCE
            java.lang.String r0 = r0.ttsEndpoint(r12)
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            java.lang.String r2 = "model"
            java.lang.String r3 = "tts-1"
            r1.put(r2, r3)
            java.lang.String r2 = "voice"
            r1.put(r2, r14)
            java.lang.String r2 = "input"
            r1.put(r2, r13)
            java.lang.String r2 = "response_format"
            java.lang.String r3 = "mp3"
            r1.put(r2, r3)
            r2 = 0
            com.xiaofan.bangfan.AppPrefs r3 = com.xiaofan.bangfan.AppPrefs.INSTANCE     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r3 = r3.ttsApiKey(r12)     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r4 = "POST"
            java.net.HttpURLConnection r3 = r11.openJson(r0, r3, r4)     // Catch: java.lang.Throwable -> Ld2
            r2 = r3
            java.io.OutputStream r3 = r2.getOutputStream()     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r4 = r1.toString()     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r5 = "toString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)     // Catch: java.lang.Throwable -> Ld2
            java.nio.charset.Charset r5 = com.xiaofan.bangfan.CustomVoice.UTF8     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r6 = "UTF8"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)     // Catch: java.lang.Throwable -> Ld2
            byte[] r4 = r4.getBytes(r5)     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r5 = "getBytes(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)     // Catch: java.lang.Throwable -> Ld2
            r3.write(r4)     // Catch: java.lang.Throwable -> Ld2
            r3.flush()     // Catch: java.lang.Throwable -> Ld2
            r3.close()     // Catch: java.lang.Throwable -> Ld2
            int r4 = r2.getResponseCode()     // Catch: java.lang.Throwable -> Ld2
            r5 = 200(0xc8, float:2.8E-43)
            if (r4 != r5) goto La3
            java.lang.String r4 = r2.getContentType()     // Catch: java.lang.Throwable -> Ld2
            java.io.InputStream r5 = r2.getInputStream()     // Catch: java.lang.Throwable -> Ld2
            if (r4 == 0) goto L95
            java.util.Locale r6 = java.util.Locale.US     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r7 = "US"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r6 = r4.toLowerCase(r6)     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r7 = "toLowerCase(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)     // Catch: java.lang.Throwable -> Ld2
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r7 = "json"
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7     // Catch: java.lang.Throwable -> Ld2
            r8 = 2
            r9 = 0
            r10 = 0
            boolean r6 = kotlin.text.StringsKt.contains$default(r6, r7, r10, r8, r9)     // Catch: java.lang.Throwable -> Ld2
            if (r6 == 0) goto L95
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r6 = r11.readAll(r5)     // Catch: java.lang.Throwable -> Ld2
            byte[] r6 = r11.extractAudioFromJson(r6)     // Catch: java.lang.Throwable -> Ld2
            goto L9c
        L95:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)     // Catch: java.lang.Throwable -> Ld2
            byte[] r6 = r11.readAllBytes(r5)     // Catch: java.lang.Throwable -> Ld2
        L9c:
            if (r2 == 0) goto La1
            r2.disconnect()
        La1:
            return r6
        La3:
            java.lang.Exception r4 = new java.lang.Exception     // Catch: java.lang.Throwable -> Ld2
            int r5 = r2.getResponseCode()     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r6 = r11.readErrorBody(r2)     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r6 = r11.truncate(r6)     // Catch: java.lang.Throwable -> Ld2
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Ld2
            r7.<init>()     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r8 = "HTTP "
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch: java.lang.Throwable -> Ld2
            java.lang.StringBuilder r5 = r7.append(r5)     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r7 = " "
            java.lang.StringBuilder r5 = r5.append(r7)     // Catch: java.lang.Throwable -> Ld2
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> Ld2
            r4.<init>(r5)     // Catch: java.lang.Throwable -> Ld2
            throw r4     // Catch: java.lang.Throwable -> Ld2
        Ld2:
            r3 = move-exception
            if (r2 == 0) goto Ld8
            r2.disconnect()
        Ld8:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.CustomVoice.synthesize(android.content.Context, java.lang.String, java.lang.String):byte[]");
    }

    private final byte[] extractAudioFromJson(String json) {
        JSONObject first;
        JSONObject obj = new JSONObject(json);
        JSONArray data = obj.optJSONArray("data");
        if (data != null && data.length() > 0 && (first = data.optJSONObject(0)) != null) {
            String audio = first.optString(MediaStreamTrack.AUDIO_TRACK_KIND, "");
            Intrinsics.checkNotNull(audio);
            if (audio.length() > 0) {
                return decodeBase64(audio);
            }
            String url = first.optString("url", "");
            Intrinsics.checkNotNull(url);
            if (url.length() > 0) {
                return downloadBytes(url, "");
            }
        }
        String[] strArr = {"audio_base64", MediaStreamTrack.AUDIO_TRACK_KIND, "audioBase64"};
        for (int i = 0; i < 3; i++) {
            String key = strArr[i];
            String val_ = obj.optString(key, "");
            Intrinsics.checkNotNull(val_);
            if (val_.length() > 0) {
                return decodeBase64(val_);
            }
        }
        String[] strArr2 = {"url", "audio_url", "audioUrl"};
        for (int i2 = 0; i2 < 3; i2++) {
            String key2 = strArr2[i2];
            String val_2 = obj.optString(key2, "");
            Intrinsics.checkNotNull(val_2);
            if (val_2.length() > 0) {
                return downloadBytes(val_2, "");
            }
        }
        throw new Exception("响应里没有音频数据");
    }

    private final byte[] decodeBase64(String str) {
        String s = str;
        int idx = StringsKt.indexOf$default((CharSequence) s, ',', 0, false, 6, (Object) null);
        if (StringsKt.startsWith$default(s, "data:", false, 2, (Object) null) && idx > 0) {
            String substring = s.substring(idx + 1);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            s = substring;
        }
        byte[] decode = Base64.decode(new Regex("\\s").replace(s, ""), 0);
        Intrinsics.checkNotNullExpressionValue(decode, "decode(...)");
        return decode;
    }

    private final HttpURLConnection openJson(String url, String apiKey, String method) {
        URLConnection openConnection = new URL(url).openConnection();
        Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
        HttpURLConnection conn = (HttpURLConnection) openConnection;
        conn.setRequestMethod(method);
        conn.setConnectTimeout(UpdateDownloadCore.DL_CONNECT_MS);
        conn.setReadTimeout(30000);
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        conn.setRequestProperty("User-Agent", "xiaofan-assistant/3.0");
        if (apiKey.length() > 0) {
            conn.setRequestProperty("Authorization", "Bearer " + apiKey);
        }
        return conn;
    }

    private final String postMultipart(String url, String apiKey, String name, byte[] data, String mime) {
        HttpURLConnection conn = null;
        DataOutputStream out = null;
        try {
            URLConnection openConnection = new URL(url).openConnection();
            Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
            HttpURLConnection conn2 = (HttpURLConnection) openConnection;
            conn2.setRequestMethod("POST");
            conn2.setConnectTimeout(UpdateDownloadCore.DL_READ_MS);
            conn2.setReadTimeout(120000);
            boolean z = true;
            conn2.setDoOutput(true);
            conn2.setUseCaches(false);
            conn2.setRequestProperty("Connection", "Keep-Alive");
            conn2.setRequestProperty("Content-Type", "multipart/form-data; boundary=----XiaoFanVoiceBoundary7d4a1b2c");
            if (apiKey.length() <= 0) {
                z = false;
            }
            if (z) {
                conn2.setRequestProperty("Authorization", "Bearer " + apiKey);
            }
            DataOutputStream out2 = new DataOutputStream(conn2.getOutputStream());
            writeField(out2, "name", name);
            writeField(out2, "model", "tts-1");
            out2.writeBytes("----XiaoFanVoiceBoundary7d4a1b2c\r\n");
            out2.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"sample\"\r\n");
            out2.writeBytes("Content-Type: " + mime + "\r\n\r\n");
            out2.write(data);
            out2.writeBytes("\r\n");
            out2.writeBytes("----XiaoFanVoiceBoundary7d4a1b2c--\r\n");
            out2.flush();
            if (conn2.getResponseCode() != 200 && conn2.getResponseCode() != 201) {
                throw new Exception("HTTP " + conn2.getResponseCode() + " " + truncate(readErrorBody(conn2)));
            }
            InputStream inputStream = conn2.getInputStream();
            Intrinsics.checkNotNullExpressionValue(inputStream, "getInputStream(...)");
            String readAll = readAll(inputStream);
            try {
                out2.close();
            } catch (Throwable th) {
            }
            try {
                conn2.disconnect();
            } catch (Throwable th2) {
            }
            return readAll;
        } catch (Throwable th3) {
            if (0 != 0) {
                try {
                    out.close();
                } catch (Throwable th4) {
                }
            }
            if (0 != 0) {
                try {
                    conn.disconnect();
                } catch (Throwable th5) {
                }
            }
            throw th3;
        }
    }

    private final void writeField(DataOutputStream out, String name, String value) {
        out.writeBytes("----XiaoFanVoiceBoundary7d4a1b2c\r\n");
        out.writeBytes("Content-Disposition: form-data; name=\"" + name + "\"\r\n\r\n");
        Charset UTF82 = UTF8;
        Intrinsics.checkNotNullExpressionValue(UTF82, "UTF8");
        byte[] bytes = value.getBytes(UTF82);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        out.write(bytes);
        out.writeBytes("\r\n");
    }

    private final byte[] downloadBytes(String url, String apiKey) {
        HttpURLConnection conn = null;
        try {
            URLConnection openConnection = new URL(url).openConnection();
            Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
            HttpURLConnection conn2 = (HttpURLConnection) openConnection;
            conn2.setConnectTimeout(UpdateDownloadCore.DL_CONNECT_MS);
            conn2.setReadTimeout(60000);
            if (apiKey.length() > 0) {
                conn2.setRequestProperty("Authorization", "Bearer " + apiKey);
            }
            if (conn2.getResponseCode() != 200) {
                throw new Exception("下载音频失败 HTTP " + conn2.getResponseCode());
            }
            InputStream inputStream = conn2.getInputStream();
            Intrinsics.checkNotNullExpressionValue(inputStream, "getInputStream(...)");
            byte[] readAllBytes = readAllBytes(inputStream);
            conn2.disconnect();
            return readAllBytes;
        } catch (Throwable th) {
            if (0 != 0) {
                conn.disconnect();
            }
            throw th;
        }
    }

    private final String readErrorBody(HttpURLConnection conn) {
        try {
            InputStream err = conn.getErrorStream();
            if (err == null) {
                return "";
            }
            return readAll(err);
        } catch (Throwable th) {
            return "";
        }
    }

    private final String readAll(InputStream input) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, UTF8));
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                String it = reader.readLine();
                if (it == null) {
                    break;
                }
                sb.append(it);
            }
            String sb2 = sb.toString();
            Intrinsics.checkNotNull(sb2);
            return sb2;
        } finally {
            try {
                reader.close();
            } catch (Throwable th) {
            }
        }
    }

    private final byte[] readAllBytes(InputStream input) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[8192];
        while (true) {
            try {
                int read = input.read(buf);
                if (read <= 0) {
                    break;
                }
                out.write(buf, 0, read);
            } finally {
                try {
                    input.close();
                } catch (Throwable th) {
                }
            }
        }
        byte[] byteArray = out.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
        return byteArray;
    }

    private final byte[] readUri(Context context, Uri uri) {
        InputStream input = context.getContentResolver().openInputStream(uri);
        if (input == null) {
            throw new Exception("打不开这个文件");
        }
        return readAllBytes(input);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x001b A[Catch: all -> 0x0021, TRY_LEAVE, TryCatch #0 {all -> 0x0021, blocks: (B:2:0x0001, B:4:0x000e, B:11:0x001b), top: B:16:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.String guessMime(android.content.Context r3, android.net.Uri r4) {
        /*
            r2 = this;
            android.content.ContentResolver r0 = r3.getContentResolver()     // Catch: java.lang.Throwable -> L21
            java.lang.String r0 = r0.getType(r4)     // Catch: java.lang.Throwable -> L21
            r1 = r0
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch: java.lang.Throwable -> L21
            if (r1 == 0) goto L17
            int r1 = r1.length()     // Catch: java.lang.Throwable -> L21
            if (r1 != 0) goto L15
            goto L17
        L15:
            r1 = 0
            goto L18
        L17:
            r1 = 1
        L18:
            if (r1 != 0) goto L1b
            goto L27
        L1b:
            java.lang.String r1 = r2.guessFromPath(r4)     // Catch: java.lang.Throwable -> L21
            r0 = r1
            goto L27
        L21:
            r0 = move-exception
            java.lang.String r1 = r2.guessFromPath(r4)
            r0 = r1
        L27:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.CustomVoice.guessMime(android.content.Context, android.net.Uri):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0018, code lost:
        if (r0 == null) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.String guessFromPath(android.net.Uri r6) {
        /*
            r5 = this;
            if (r6 == 0) goto L1a
            java.lang.String r0 = r6.getPath()
            if (r0 == 0) goto L1a
            java.util.Locale r1 = java.util.Locale.US
            java.lang.String r2 = "US"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.String r0 = r0.toLowerCase(r1)
            java.lang.String r1 = "toLowerCase(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            if (r0 != 0) goto L1c
        L1a:
            java.lang.String r0 = ""
        L1c:
            java.lang.String r1 = ".mp3"
            r2 = 0
            r3 = 2
            r4 = 0
            boolean r1 = kotlin.text.StringsKt.endsWith$default(r0, r1, r2, r3, r4)
            if (r1 == 0) goto L2b
            java.lang.String r1 = "audio/mpeg"
            goto L62
        L2b:
            java.lang.String r1 = ".wav"
            boolean r1 = kotlin.text.StringsKt.endsWith$default(r0, r1, r2, r3, r4)
            if (r1 == 0) goto L36
            java.lang.String r1 = "audio/wav"
            goto L62
        L36:
            java.lang.String r1 = ".m4a"
            boolean r1 = kotlin.text.StringsKt.endsWith$default(r0, r1, r2, r3, r4)
            if (r1 != 0) goto L60
            java.lang.String r1 = ".mp4"
            boolean r1 = kotlin.text.StringsKt.endsWith$default(r0, r1, r2, r3, r4)
            if (r1 == 0) goto L47
            goto L60
        L47:
            java.lang.String r1 = ".ogg"
            boolean r1 = kotlin.text.StringsKt.endsWith$default(r0, r1, r2, r3, r4)
            if (r1 == 0) goto L52
            java.lang.String r1 = "audio/ogg"
            goto L62
        L52:
            java.lang.String r1 = ".aac"
            boolean r1 = kotlin.text.StringsKt.endsWith$default(r0, r1, r2, r3, r4)
            if (r1 == 0) goto L5d
            java.lang.String r1 = "audio/aac"
            goto L62
        L5d:
            java.lang.String r1 = "application/octet-stream"
            goto L62
        L60:
            java.lang.String r1 = "audio/mp4"
        L62:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.CustomVoice.guessFromPath(android.net.Uri):java.lang.String");
    }

    public final String extractVoiceId(String str) {
        JSONObject first;
        String str2 = str;
        if (str2 == null || StringsKt.isBlank(str2)) {
            return null;
        }
        try {
            JSONObject obj = new JSONObject(StringsKt.trim((CharSequence) str).toString());
            String[] strArr = {"id", "voice_id", "voiceId", "voice"};
            for (int i = 0; i < 4; i++) {
                String key = strArr[i];
                String val_ = obj.optString(key, "");
                Intrinsics.checkNotNull(val_);
                if ((val_.length() > 0) && !StringsKt.startsWith$default(val_, "{", false, 2, (Object) null)) {
                    return val_;
                }
            }
            JSONObject voice = obj.optJSONObject("voice");
            if (voice != null) {
                String id = voice.optString("id", voice.optString("voice_id", ""));
                Intrinsics.checkNotNull(id);
                if (id.length() > 0) {
                    return id;
                }
            }
            JSONArray data = obj.optJSONArray("data");
            if (data != null && data.length() > 0 && (first = data.optJSONObject(0)) != null) {
                String id2 = first.optString("id", first.optString("voice_id", ""));
                Intrinsics.checkNotNull(id2);
                if (id2.length() > 0) {
                    return id2;
                }
            }
        } catch (Throwable th) {
            Log.w(TAG, "extractVoiceId parse failed", th);
        }
        return null;
    }

    private final String truncate(String str) {
        if (str == null) {
            return "";
        }
        if (str.length() <= 200) {
            return str;
        }
        String substring = str.substring(0, ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        return substring + "…";
    }
}
