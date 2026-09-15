package com.xiaofan.bangfan;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
/* compiled from: VoicePack.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u001a\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\nJ\u000e\u0010\u0016\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eJ\u001a\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00040\u00182\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u001a\u001a\u00020\u0010R\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/xiaofan/bangfan/VoicePack;", "", "()V", "PRESETS", "", "Lcom/xiaofan/bangfan/VoicePack$Preset;", "getPRESETS", "()[Lcom/xiaofan/bangfan/VoicePack$Preset;", "[Lcom/xiaofan/bangfan/VoicePack$Preset;", "TAG", "", "applyPresetToPrefs", "", "context", "Landroid/content/Context;", "index", "", "applySystemVoice", "", "tts", "Landroid/speech/tts/TextToSpeech;", "localeTag", "current", "listSystemVoices", "", "preset", "presetCount", "Preset", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class VoicePack {
    public static final VoicePack INSTANCE = new VoicePack();
    private static final Preset[] PRESETS = {new Preset("精灵儿童音", "默认音色，清亮稚嫩像小朋友", 2.0f, 1.15f), new Preset("元气少女", "明亮活泼，语速略快", 1.75f, 1.2f), new Preset("温柔小姐姐", "柔和亲切，语速舒缓", 1.35f, 0.95f), new Preset("清亮少年", "干净自然，中性偏年轻", 1.2f, 1.05f), new Preset("沉稳男声", "低沉稳重，适合长时间听", 0.85f, 0.95f), new Preset("磁性低音", "浑厚有磁性", 0.7f, 0.9f), new Preset("标准播音", "字正腔圆，接近新闻播报", 1.0f, 1.0f), new Preset("俏皮灵动", "跳跃可爱，语速偏快", 1.85f, 1.3f), new Preset("慵懒慢语", "慢悠悠，放松助眠", 1.1f, 0.8f), new Preset("清脆童声", "比精灵音更幼态", 2.0f, 1.0f), new Preset("阳光青年", "爽朗有活力", 1.05f, 1.15f), new Preset("知性女声", "成熟知性，语速平稳", 1.25f, 1.0f)};
    private static final String TAG = "VoicePack";

    /* compiled from: VoicePack.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u001a"}, d2 = {"Lcom/xiaofan/bangfan/VoicePack$Preset;", "", "name", "", "desc", "pitch", "", "rate", "(Ljava/lang/String;Ljava/lang/String;FF)V", "getDesc", "()Ljava/lang/String;", "getName", "getPitch", "()F", "getRate", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Preset {
        private final String desc;
        private final String name;
        private final float pitch;
        private final float rate;

        public static /* synthetic */ Preset copy$default(Preset preset, String str, String str2, float f, float f2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = preset.name;
            }
            if ((i & 2) != 0) {
                str2 = preset.desc;
            }
            if ((i & 4) != 0) {
                f = preset.pitch;
            }
            if ((i & 8) != 0) {
                f2 = preset.rate;
            }
            return preset.copy(str, str2, f, f2);
        }

        public final String component1() {
            return this.name;
        }

        public final String component2() {
            return this.desc;
        }

        public final float component3() {
            return this.pitch;
        }

        public final float component4() {
            return this.rate;
        }

        public final Preset copy(String name, String desc, float f, float f2) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(desc, "desc");
            return new Preset(name, desc, f, f2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Preset) {
                Preset preset = (Preset) obj;
                return Intrinsics.areEqual(this.name, preset.name) && Intrinsics.areEqual(this.desc, preset.desc) && Float.compare(this.pitch, preset.pitch) == 0 && Float.compare(this.rate, preset.rate) == 0;
            }
            return false;
        }

        public int hashCode() {
            return (((((this.name.hashCode() * 31) + this.desc.hashCode()) * 31) + Float.hashCode(this.pitch)) * 31) + Float.hashCode(this.rate);
        }

        public String toString() {
            String str = this.name;
            String str2 = this.desc;
            float f = this.pitch;
            return "Preset(name=" + str + ", desc=" + str2 + ", pitch=" + f + ", rate=" + this.rate + ")";
        }

        public Preset(String name, String desc, float pitch, float rate) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(desc, "desc");
            this.name = name;
            this.desc = desc;
            this.pitch = pitch;
            this.rate = rate;
        }

        public final String getName() {
            return this.name;
        }

        public final String getDesc() {
            return this.desc;
        }

        public final float getPitch() {
            return this.pitch;
        }

        public final float getRate() {
            return this.rate;
        }
    }

    private VoicePack() {
    }

    public final Preset[] getPRESETS() {
        return PRESETS;
    }

    public final int presetCount() {
        return PRESETS.length;
    }

    public final Preset preset(int index) {
        return PRESETS[RangesKt.coerceIn(index, 0, PRESETS.length - 1)];
    }

    public final Preset current(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return preset(AppPrefs.INSTANCE.voicePack(context));
    }

    public final void applyPresetToPrefs(Context context, int index) {
        Intrinsics.checkNotNullParameter(context, "context");
        Preset p = preset(index);
        AppPrefs.INSTANCE.setVoicePack(context, index);
        AppPrefs.INSTANCE.setSpeakPitch(context, p.getPitch());
        AppPrefs.INSTANCE.setSpeakRate(context, p.getRate());
    }

    public final List<String[]> listSystemVoices(Context context) {
        Set voices;
        Intrinsics.checkNotNullParameter(context, "context");
        List result = new ArrayList();
        TextToSpeech tts = null;
        try {
            tts = new TextToSpeech(context.getApplicationContext(), new TextToSpeech.OnInitListener() { // from class: com.xiaofan.bangfan.VoicePack$$ExternalSyntheticLambda0
                @Override // android.speech.tts.TextToSpeech.OnInitListener
                public final void onInit(int i) {
                    VoicePack.listSystemVoices$lambda$0(i);
                }
            });
            for (int i = 0; i < 31; i++) {
                if (tts.getVoices() != null) {
                    break;
                }
                Thread.sleep(50L);
            }
            voices = tts.getVoices();
        } catch (Throwable th) {
            try {
                Log.w(TAG, "listSystemVoices failed", th);
            } finally {
                if (tts != null) {
                    try {
                        tts.shutdown();
                    } catch (Throwable th2) {
                    }
                }
            }
        }
        if (voices == null) {
            try {
                tts.shutdown();
            } catch (Throwable th3) {
            }
            return result;
        }
        Iterator<Voice> it = voices.iterator();
        while (it.hasNext()) {
            Voice voice = it.next();
            if ((voice != null ? voice.getLocale() : null) != null) {
                String lang = voice.getLocale().getLanguage();
                if (StringsKt.equals("zh", lang, true) || StringsKt.equals("cmn", lang, true)) {
                    String name = voice.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                    String languageTag = voice.getLocale().toLanguageTag();
                    Intrinsics.checkNotNullExpressionValue(languageTag, "toLanguageTag(...)");
                    result.add(new String[]{name, languageTag});
                }
            }
        }
        try {
            tts.shutdown();
        } catch (Throwable th4) {
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void listSystemVoices$lambda$0(int it) {
    }

    public final boolean applySystemVoice(TextToSpeech tts, String localeTag) {
        if (tts != null) {
            String str = localeTag;
            if (!(str == null || str.length() == 0)) {
                try {
                    Locale locale = Locale.forLanguageTag(localeTag);
                    Set voices = tts.getVoices();
                    if (voices == null) {
                        return false;
                    }
                    Iterator<Voice> it = voices.iterator();
                    while (it.hasNext()) {
                        Voice voice = it.next();
                        if ((voice != null ? voice.getLocale() : null) != null && Intrinsics.areEqual(voice.getLocale().toLanguageTag(), localeTag)) {
                            return tts.setVoice(voice) == 0;
                        }
                    }
                    return tts.setLanguage(locale) >= 0;
                } catch (Throwable th) {
                    Log.w(TAG, "applySystemVoice failed", th);
                    return false;
                }
            }
        }
        return false;
    }
}
