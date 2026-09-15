package com.xiaofan.bangfan;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.xiaofan.bangfan.CustomVoice;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AssistantActivity.kt */
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, d2 = {"com/xiaofan/bangfan/AssistantActivity$startClone$1$1$1", "Lcom/xiaofan/bangfan/CustomVoice$Callback;", "onError", "", NotificationCompat.CATEGORY_MESSAGE, "", "onSuccess", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class AssistantActivity$startClone$1$1$1 implements CustomVoice.Callback {
    final /* synthetic */ AssistantActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AssistantActivity$startClone$1$1$1(AssistantActivity $receiver) {
        this.this$0 = $receiver;
    }

    @Override // com.xiaofan.bangfan.CustomVoice.Callback
    public void onSuccess(final String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        AssistantActivity assistantActivity = this.this$0;
        final AssistantActivity assistantActivity2 = this.this$0;
        assistantActivity.runOnUiThread(new Runnable() { // from class: com.xiaofan.bangfan.AssistantActivity$startClone$1$1$1$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AssistantActivity$startClone$1$1$1.onSuccess$lambda$0(AssistantActivity.this, msg);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onSuccess$lambda$0(AssistantActivity this$0, String msg) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(msg, "$msg");
        this$0.setCvStatus(msg, true);
        this$0.refresh();
        XiaoFanVoice.INSTANCE.preview(this$0);
    }

    @Override // com.xiaofan.bangfan.CustomVoice.Callback
    public void onError(final String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        AssistantActivity assistantActivity = this.this$0;
        final AssistantActivity assistantActivity2 = this.this$0;
        assistantActivity.runOnUiThread(new Runnable() { // from class: com.xiaofan.bangfan.AssistantActivity$startClone$1$1$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AssistantActivity$startClone$1$1$1.onError$lambda$1(AssistantActivity.this, msg);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onError$lambda$1(AssistantActivity this$0, String msg) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(msg, "$msg");
        this$0.setCvStatus(msg, false);
        this$0.refresh();
    }
}
