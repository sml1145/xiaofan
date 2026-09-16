package com.xiaofan.bangfan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.UiKit;
import com.xiaofan.bangfan.VoiceControlService;
import com.xiaofan.bangfan.ai.OfflineBrain;
import com.xiaofan.bangfan.ai.OfflineModels;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: VoiceBrainActivity.kt */
@Metadata(d1 = {"\u0000S\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0007\u0018\u0000 #2\u00020\u0001:\u0001#B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\nH\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\u0012\u0010\u000e\u001a\u00020\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\b\u0010\u0011\u001a\u00020\nH\u0014J-\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00142\u000e\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170\u00162\u0006\u0010\u0018\u001a\u00020\u0019H\u0016¢\u0006\u0002\u0010\u001aJ\b\u0010\u001b\u001a\u00020\nH\u0014J\b\u0010\u001c\u001a\u00020\nH\u0002J\b\u0010\u001d\u001a\u00020\nH\u0002J\b\u0010\u001e\u001a\u00020\nH\u0002J\b\u0010\u001f\u001a\u00020\nH\u0002J\u0010\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\"H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b¨\u0006$"}, d2 = {"Lcom/xiaofan/bangfan/VoiceBrainActivity;", "Lcom/xiaofan/bangfan/BaseActivity;", "()V", "asrStatus", "Landroid/widget/TextView;", "llmStatus", "offlineListener", "com/xiaofan/bangfan/VoiceBrainActivity$offlineListener$1", "Lcom/xiaofan/bangfan/VoiceBrainActivity$offlineListener$1;", "disableVoiceCmd", "", "enableVoiceCmd", "hasMic", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onRequestPermissionsResult", "requestCode", "", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "refreshStatus", "requestVoiceCmd", "showOfflineModelsDialog", "showOfflineVoiceDialog", "stateText", "kind", "Lcom/xiaofan/bangfan/ai/OfflineModels$Kind;", "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class VoiceBrainActivity extends BaseActivity {
    public static final Companion Companion = new Companion(null);
    private static final int REQ_MIC = 4217;
    private TextView asrStatus;
    private TextView llmStatus;
    private final VoiceBrainActivity$offlineListener$1 offlineListener = new VoiceBrainActivity$offlineListener$1(this);

    /* compiled from: VoiceBrainActivity.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[OfflineBrain.Phase.values().length];
            try {
                iArr[OfflineBrain.Phase.DOWNLOADING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[OfflineBrain.Phase.FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[OfflineBrain.Phase.READY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[OfflineBrain.Phase.NOT_STARTED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* compiled from: VoiceBrainActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/xiaofan/bangfan/VoiceBrainActivity$Companion;", "", "()V", "REQ_MIC", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView sc = UiKit.INSTANCE.scaffold(this, "声控与大脑", "离线声控让你长按悬浮球即可用口令控制翻页；离线大脑让开放性问题直接在手机内回答。模型只保存在本机，断网也能用。");
        LinearLayout content = UiKit.INSTANCE.contentOf(sc);
        LinearLayout voiceCard = UiKit.INSTANCE.card(this);
        voiceCard.addView(UiKit.INSTANCE.cardTitle(this, "离线声控"));
        voiceCard.addView(UiKit.INSTANCE.switchRow(this, "启用离线声控", "开启后长按悬浮球即持续聆听，再次长按关闭（完全本地，不联网）", new UiKit.Getter() { // from class: com.xiaofan.bangfan.VoiceBrainActivity$$ExternalSyntheticLambda0
            @Override // com.xiaofan.bangfan.UiKit.Getter
            public final Object get() {
                boolean onCreate$lambda$0;
                onCreate$lambda$0 = VoiceBrainActivity.onCreate$lambda$0(VoiceBrainActivity.this);
                return Boolean.valueOf(onCreate$lambda$0);
            }
        }, new UiKit.Setter() { // from class: com.xiaofan.bangfan.VoiceBrainActivity$$ExternalSyntheticLambda1
            @Override // com.xiaofan.bangfan.UiKit.Setter
            public final void set(boolean z) {
                VoiceBrainActivity.onCreate$lambda$1(VoiceBrainActivity.this, z);
            }
        }), UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 4.0f), 0, 0));
        voiceCard.addView(UiKit.INSTANCE.divider(this));
        voiceCard.addView(UiKit.INSTANCE.bodyText(this, "使用方法：先开启本开关并下载识别模型 → 显示悬浮球 → 长按悬浮球，小翻亮起并一直开着麦克风聆听，你说一句它识别执行一句；再次长按悬浮球即关闭聆听。\n可识别口令：\n· 翻页：下一页 / 上一页 / 翻页\n· 自动翻书：开始自动翻页 / 继续翻页 / 暂停翻书 / 暂停\n· 节奏：快一点 / 慢一点\n· 悬浮球：打开悬浮球 / 关闭悬浮球\n· 其它：现在几点 / 天气 / 好的 / 取消"), UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 2.0f), 0, 0));
        content.addView(voiceCard, UiKit.INSTANCE.margin(this, 0, 0, 0, UiKit.INSTANCE.dp(this, 12.0f)));
        LinearLayout brainCard = UiKit.INSTANCE.card(this);
        brainCard.addView(UiKit.INSTANCE.cardTitle(this, "离线大脑与声控（完全本地推理）"));
        brainCard.addView(UiKit.INSTANCE.bodyText(this, "对话大脑让开放性问题直接在手机内回答，不再跳转豆包/千问；声控模型用来本地听懂口令。模型只保存在本机，断网也能用，下载源为国内可直连镜像 hf-mirror.com。"), UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 6.0f), 0, UiKit.INSTANCE.dp(this, 8.0f)));
        this.llmStatus = UiKit.INSTANCE.bodyText(this, "");
        this.asrStatus = UiKit.INSTANCE.bodyText(this, "");
        brainCard.addView(this.llmStatus);
        brainCard.addView(UiKit.INSTANCE.divider(this));
        brainCard.addView(this.asrStatus, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 8.0f), 0, 0));
        brainCard.addView(UiKit.INSTANCE.button(this, "管理 / 下载离线模型", true, new View.OnClickListener() { // from class: com.xiaofan.bangfan.VoiceBrainActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VoiceBrainActivity.onCreate$lambda$2(VoiceBrainActivity.this, view);
            }
        }), UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 10.0f), 0, UiKit.INSTANCE.dp(this, 6.0f)));
        brainCard.addView(UiKit.INSTANCE.divider(this));
        brainCard.addView(UiKit.INSTANCE.valueRow(this, "离线音色（本机发音人）", new UiKit.Getter() { // from class: com.xiaofan.bangfan.VoiceBrainActivity$$ExternalSyntheticLambda3
            @Override // com.xiaofan.bangfan.UiKit.Getter
            public final Object get() {
                String onCreate$lambda$3;
                onCreate$lambda$3 = VoiceBrainActivity.onCreate$lambda$3(VoiceBrainActivity.this);
                return onCreate$lambda$3;
            }
        }, new View.OnClickListener() { // from class: com.xiaofan.bangfan.VoiceBrainActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VoiceBrainActivity.onCreate$lambda$4(VoiceBrainActivity.this, view);
            }
        }));
        brainCard.addView(UiKit.INSTANCE.bodyText(this, "说明：对话/识别模型本身不包含发音人音色，小翻的声音来自手机本地 TTS，离线可用；点上面可挑选本机已装的中文发音人并试听。"), UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 2.0f), 0, 0));
        content.addView(brainCard, UiKit.INSTANCE.margin(this, 0, 0, 0, UiKit.INSTANCE.dp(this, 12.0f)));
        setContentView(sc);
        OfflineBrain offlineBrain = OfflineBrain.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        offlineBrain.bootstrap(applicationContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$0(VoiceBrainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return AppPrefs.INSTANCE.voiceCmdOn(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(VoiceBrainActivity this$0, boolean v) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (v) {
            this$0.requestVoiceCmd();
        } else {
            this$0.disableVoiceCmd();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$2(VoiceBrainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showOfflineModelsDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String onCreate$lambda$3(VoiceBrainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String locale = AppPrefs.INSTANCE.voiceLocale(this$0);
        return locale.length() == 0 ? "默认（离线合成）" : locale;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$4(VoiceBrainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showOfflineVoiceDialog();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaofan.bangfan.BaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        OfflineBrain.INSTANCE.addListener(this.offlineListener);
        refreshStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaofan.bangfan.BaseActivity, android.app.Activity
    public void onPause() {
        OfflineBrain.INSTANCE.removeListener(this.offlineListener);
        super.onPause();
    }

    private final String stateText(OfflineModels.Kind kind) {
        if (OfflineModels.INSTANCE.isReady(this, kind)) {
            return "已就绪（本地可用）";
        }
        switch (WhenMappings.$EnumSwitchMapping$0[OfflineBrain.INSTANCE.state(kind).getPhase().ordinal()]) {
            case 1:
                return "下载中 " + OfflineBrain.INSTANCE.state(kind).getProgress() + "%";
            case 2:
                return "下载未完成（点下方按钮重试）";
            case 3:
                return "已就绪（本地可用）";
            case 4:
                return "未下载";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshStatus() {
        TextView asr;
        TextView llm = this.llmStatus;
        if (llm == null || (asr = this.asrStatus) == null) {
            return;
        }
        llm.setText("对话大脑：" + stateText(OfflineModels.Kind.LLM));
        asr.setText("声控识别：" + stateText(OfflineModels.Kind.ASR));
        llm.setTextColor(UiKit.INSTANCE.color(this, OfflineModels.INSTANCE.isReady(this, OfflineModels.Kind.LLM) ? R.color.ok : R.color.muted));
        asr.setTextColor(UiKit.INSTANCE.color(this, OfflineModels.INSTANCE.isReady(this, OfflineModels.Kind.ASR) ? R.color.ok : R.color.muted));
    }

    private final void showOfflineModelsDialog() {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(1);
        box.setPadding(UiKit.INSTANCE.dp(this, 20.0f), UiKit.INSTANCE.dp(this, 8.0f), UiKit.INSTANCE.dp(this, 20.0f), 0);
        box.addView(UiKit.INSTANCE.bodyText(this, "对话大脑用于机内回答开放性问题；声控识别用于本地听懂翻页口令。下载源为国内可直连镜像 hf-mirror.com。"));
        box.addView(UiKit.INSTANCE.button(this, "下载 / 重试对话大脑（约0.47GB）", true, new View.OnClickListener() { // from class: com.xiaofan.bangfan.VoiceBrainActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VoiceBrainActivity.showOfflineModelsDialog$lambda$5(VoiceBrainActivity.this, view);
            }
        }), UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 12.0f), 0, 0));
        box.addView(UiKit.INSTANCE.button(this, "下载 / 重试声控识别（约0.14GB）", false, new View.OnClickListener() { // from class: com.xiaofan.bangfan.VoiceBrainActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VoiceBrainActivity.showOfflineModelsDialog$lambda$6(VoiceBrainActivity.this, view);
            }
        }), UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 10.0f), 0, 0));
        new AlertDialog.Builder(this).setTitle("管理离线模型").setView(box).setNegativeButton("关闭", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showOfflineModelsDialog$lambda$5(VoiceBrainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OfflineBrain.INSTANCE.download(this$0, OfflineModels.Kind.LLM);
        Toast.makeText(this$0, "开始下载对话大脑，进度可在本页查看", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showOfflineModelsDialog$lambda$6(VoiceBrainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OfflineBrain.INSTANCE.download(this$0, OfflineModels.Kind.ASR);
        Toast.makeText(this$0, "开始下载声控模型，进度可在本页查看", 0).show();
    }

    private final void showOfflineVoiceDialog() {
        Toast.makeText(this, "正在读取本机已装语音…", 0).show();
        new Thread(new Runnable() { // from class: com.xiaofan.bangfan.VoiceBrainActivity$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                VoiceBrainActivity.showOfflineVoiceDialog$lambda$9(VoiceBrainActivity.this);
            }
        }, "vb-list-voices").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showOfflineVoiceDialog$lambda$9(final VoiceBrainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        final List voices = VoicePack.INSTANCE.listSystemVoices(this$0);
        this$0.runOnUiThread(new Runnable() { // from class: com.xiaofan.bangfan.VoiceBrainActivity$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                VoiceBrainActivity.showOfflineVoiceDialog$lambda$9$lambda$8(voices, this$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showOfflineVoiceDialog$lambda$9$lambda$8(List voices, final VoiceBrainActivity this$0) {
        Intrinsics.checkNotNullParameter(voices, "$voices");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (voices.isEmpty()) {
            new AlertDialog.Builder(this$0).setTitle("离线音色").setMessage("本机还没有可单独选择的中文发音人。可在系统设置里安装离线中文语音数据；不装也能用——小翻默认就是本地离线合成。").setPositiveButton("知道了", (DialogInterface.OnClickListener) null).show();
            return;
        }
        int size = voices.size() + 1;
        String[] items = new String[size];
        int i = 0;
        int i2 = 0;
        while (i2 < size) {
            items[i2] = i2 == 0 ? "默认（引擎自带）" : ((String[]) voices.get(i2 - 1))[0];
            i2++;
        }
        int size2 = voices.size() + 1;
        final String[] locales = new String[size2];
        while (i < size2) {
            locales[i] = i == 0 ? "" : ((String[]) voices.get(i - 1))[1];
            i++;
        }
        new AlertDialog.Builder(this$0).setTitle("选择离线音色").setItems(items, new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.VoiceBrainActivity$$ExternalSyntheticLambda9
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i3) {
                VoiceBrainActivity.showOfflineVoiceDialog$lambda$9$lambda$8$lambda$7(VoiceBrainActivity.this, locales, dialogInterface, i3);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showOfflineVoiceDialog$lambda$9$lambda$8$lambda$7(VoiceBrainActivity this$0, String[] locales, DialogInterface dialogInterface, int which) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(locales, "$locales");
        AppPrefs.INSTANCE.setVoiceLocale(this$0, locales[which]);
        XiaoFanVoice.INSTANCE.applyVoiceParams(this$0);
        XiaoFanVoice.INSTANCE.preview(this$0);
    }

    private final boolean hasMic() {
        return checkSelfPermission("android.permission.RECORD_AUDIO") == 0;
    }

    private final void requestVoiceCmd() {
        if (!hasMic()) {
            requestPermissions(new String[]{"android.permission.RECORD_AUDIO"}, REQ_MIC);
        } else {
            enableVoiceCmd();
        }
    }

    private final void enableVoiceCmd() {
        if (OfflineModels.INSTANCE.isReady(this, OfflineModels.Kind.ASR)) {
            AppPrefs.INSTANCE.setVoiceCmdOn(this, true);
            Toast.makeText(this, "离线声控已启用：长按悬浮球开始持续聆听，再长按关闭", 1).show();
            refreshStatus();
            return;
        }
        AppPrefs.INSTANCE.setVoiceCmdOn(this, true);
        Toast.makeText(this, "正在下载声控识别模型，下好后长按悬浮球即可说话", 1).show();
        OfflineBrain.INSTANCE.download(this, OfflineModels.Kind.ASR);
        refreshStatus();
    }

    private final void disableVoiceCmd() {
        AppPrefs.INSTANCE.setVoiceCmdOn(this, false);
        VoiceControlService.Companion companion = VoiceControlService.Companion;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        companion.stop(applicationContext);
        refreshStatus();
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        int[] $this$any$iv;
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != REQ_MIC) {
            return;
        }
        int length = grantResults.length;
        int i = 0;
        while (true) {
            if (i < length) {
                int element$iv = grantResults[i];
                int it = element$iv == 0 ? 1 : 0;
                if (it != 0) {
                    $this$any$iv = 1;
                    break;
                }
                i++;
            } else {
                $this$any$iv = null;
                break;
            }
        }
        if ($this$any$iv == null) {
            AppPrefs.INSTANCE.setVoiceCmdOn(this, false);
            Toast.makeText(this, "未授予麦克风权限，离线声控无法开启", 1).show();
            refreshStatus();
            return;
        }
        enableVoiceCmd();
    }
}
