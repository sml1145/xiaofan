package com.xiaofan.bangfan;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
/* compiled from: ClickFx.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u000e\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/xiaofan/bangfan/ClickFx;", "", "()V", "loaded", "", "pool", "Landroid/media/SoundPool;", "soundId", "", "tried", "ensure", "", "ctx", "Landroid/content/Context;", "play", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class ClickFx {
    public static final ClickFx INSTANCE = new ClickFx();
    private static volatile boolean loaded;
    private static SoundPool pool;
    private static int soundId;
    private static volatile boolean tried;

    private ClickFx() {
    }

    private final void ensure(Context ctx) {
        if (tried) {
            return;
        }
        tried = true;
        try {
            AudioAttributes attrs = new AudioAttributes.Builder().setUsage(13).setContentType(4).build();
            SoundPool sp = new SoundPool.Builder().setMaxStreams(4).setAudioAttributes(attrs).build();
            sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() { // from class: com.xiaofan.bangfan.ClickFx$$ExternalSyntheticLambda0
                @Override // android.media.SoundPool.OnLoadCompleteListener
                public final void onLoadComplete(SoundPool soundPool, int i, int i2) {
                    ClickFx.ensure$lambda$0(soundPool, i, i2);
                }
            });
            soundId = sp.load(ctx.getApplicationContext(), R.raw.click, 1);
            pool = sp;
        } catch (Throwable th) {
            pool = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ensure$lambda$0(SoundPool soundPool, int i, int status) {
        if (status == 0) {
            loaded = true;
        }
    }

    public final void play(Context ctx) {
        if (ctx == null) {
            return;
        }
        try {
            if (AppPrefs.INSTANCE.clickSound(ctx)) {
                try {
                    ensure(ctx);
                    SoundPool sp = pool;
                    if (sp != null && loaded && soundId != 0) {
                        sp.play(soundId, 0.5f, 0.5f, 1, 0, 1.0f);
                    }
                } catch (Throwable th) {
                }
            }
        } catch (Throwable th2) {
        }
    }
}
