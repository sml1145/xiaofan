package com.xiaofan.bangfan.ai;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
/* compiled from: NativeLlm.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001aB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\t\u0010\u0007\u001a\u00020\u0006H\u0082 JC\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0086 J!\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u000fH\u0086 J\u0011\u0010\u0019\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0086 R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/xiaofan/bangfan/ai/NativeLlm;", "", "()V", "loaded", "", "ensureLoaded", "", "nativeBackendInit", "nativeChat", "", "handle", "", "system", "user", "maxTokens", "", "temperature", "", "topP", "callback", "Lcom/xiaofan/bangfan/ai/NativeLlm$PieceCallback;", "nativeLoad", "modelPath", "nCtx", "threads", "nativeUnload", "PieceCallback", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class NativeLlm {
    public static final NativeLlm INSTANCE = new NativeLlm();
    private static volatile boolean loaded;

    /* compiled from: NativeLlm.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/xiaofan/bangfan/ai/NativeLlm$PieceCallback;", "", "onPiece", "", "piece", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes3.dex */
    public interface PieceCallback {
        boolean onPiece(String str);
    }

    private final native void nativeBackendInit();

    public final native String nativeChat(long j, String str, String str2, int i, float f, float f2, PieceCallback pieceCallback);

    public final native long nativeLoad(String str, int i, int i2);

    public final native void nativeUnload(long j);

    private NativeLlm() {
    }

    public final void ensureLoaded() {
        if (loaded) {
            return;
        }
        synchronized (this) {
            if (loaded) {
                return;
            }
            System.loadLibrary("xfllama");
            INSTANCE.nativeBackendInit();
            loaded = true;
            Unit unit = Unit.INSTANCE;
        }
    }
}
