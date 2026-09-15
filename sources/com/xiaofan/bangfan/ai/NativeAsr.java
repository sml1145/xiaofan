package com.xiaofan.bangfan.ai;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
/* compiled from: NativeAsr.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0017\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086 J!\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\nH\u0086 J\u0011\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\bH\u0086 R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/xiaofan/bangfan/ai/NativeAsr;", "", "()V", "loaded", "", "ensureLoaded", "", "nativeLoad", "", "modelPath", "", "threads", "", "nativeTranscribe", "handle", "pcm16", "", "lang", "nativeUnload", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class NativeAsr {
    public static final NativeAsr INSTANCE = new NativeAsr();
    private static volatile boolean loaded;

    public final native long nativeLoad(String str, int i);

    public final native String nativeTranscribe(long j, short[] sArr, String str);

    public final native void nativeUnload(long j);

    private NativeAsr() {
    }

    public final void ensureLoaded() {
        if (loaded) {
            return;
        }
        synchronized (this) {
            if (loaded) {
                return;
            }
            System.loadLibrary("xfwhisper");
            loaded = true;
            Unit unit = Unit.INSTANCE;
        }
    }
}
