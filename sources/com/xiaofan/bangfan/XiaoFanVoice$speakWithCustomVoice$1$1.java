package com.xiaofan.bangfan;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.xiaofan.bangfan.CustomVoice;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: XiaoFanVoice.kt */
@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016¨\u0006\b"}, d2 = {"com/xiaofan/bangfan/XiaoFanVoice$speakWithCustomVoice$1$1", "Lcom/xiaofan/bangfan/CustomVoice$SpeakCallback;", "onEnd", "", "onFallback", NotificationCompat.CATEGORY_MESSAGE, "", "onStart", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class XiaoFanVoice$speakWithCustomVoice$1$1 implements CustomVoice.SpeakCallback {
    @Override // com.xiaofan.bangfan.CustomVoice.SpeakCallback
    public void onStart() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onEnd$lambda$0() {
        XiaoFanVoice.INSTANCE.endSpeaking();
    }

    @Override // com.xiaofan.bangfan.CustomVoice.SpeakCallback
    public void onEnd() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.xiaofan.bangfan.XiaoFanVoice$speakWithCustomVoice$1$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                XiaoFanVoice$speakWithCustomVoice$1$1.onEnd$lambda$0();
            }
        });
    }

    @Override // com.xiaofan.bangfan.CustomVoice.SpeakCallback
    public void onFallback(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Log.w("XiaoFanVoice", "custom voice fallback: " + msg);
    }
}
