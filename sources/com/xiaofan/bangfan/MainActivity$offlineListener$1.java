package com.xiaofan.bangfan;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.ai.OfflineBrain;
import com.xiaofan.bangfan.ai.OfflineModels;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/xiaofan/bangfan/MainActivity$offlineListener$1", "Lcom/xiaofan/bangfan/ai/OfflineBrain$Listener;", "onState", "", "kind", "Lcom/xiaofan/bangfan/ai/OfflineModels$Kind;", "state", "Lcom/xiaofan/bangfan/ai/OfflineBrain$State;", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class MainActivity$offlineListener$1 implements OfflineBrain.Listener {
    final /* synthetic */ MainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MainActivity$offlineListener$1(MainActivity $receiver) {
        this.this$0 = $receiver;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onState$lambda$0(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.refreshPage3Status();
    }

    @Override // com.xiaofan.bangfan.ai.OfflineBrain.Listener
    public void onState(OfflineModels.Kind kind, OfflineBrain.State state) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(state, "state");
        MainActivity mainActivity = this.this$0;
        final MainActivity mainActivity2 = this.this$0;
        mainActivity.runOnUiThread(new Runnable() { // from class: com.xiaofan.bangfan.MainActivity$offlineListener$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity$offlineListener$1.onState$lambda$0(MainActivity.this);
            }
        });
    }
}
