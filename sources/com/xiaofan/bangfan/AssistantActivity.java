package com.xiaofan.bangfan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.UiKit;
import com.xiaofan.bangfan.VoicePack;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
/* compiled from: AssistantActivity.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 (2\u00020\u0001:\u0001(B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\u0012\u0010\r\u001a\u00020\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J-\u0010\u0010\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u000e\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0016¢\u0006\u0002\u0010\u0016J\b\u0010\u0017\u001a\u00020\u0007H\u0014J\b\u0010\u0018\u001a\u00020\u0007H\u0002J\b\u0010\u0019\u001a\u00020\u0007H\u0002J\u0018\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u0007H\u0002J\b\u0010\u001f\u001a\u00020\u0007H\u0002J\b\u0010 \u001a\u00020\u0007H\u0002J\b\u0010!\u001a\u00020\u0007H\u0002J\b\u0010\"\u001a\u00020\u0007H\u0002J\b\u0010#\u001a\u00020\u0007H\u0002J\b\u0010$\u001a\u00020\u0007H\u0002J\u0010\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020'H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/xiaofan/bangfan/AssistantActivity;", "Lcom/xiaofan/bangfan/BaseActivity;", "()V", "cvStatus", "Landroid/widget/TextView;", "statusLine", "onActivityResult", "", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onRequestPermissionsResult", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "pickVoiceSample", "refresh", "setCvStatus", "text", "ok", "", "showEndpointDialog", "showKeyDialog", "showPitchDialog", "showSystemVoiceDialog", "showTtsEndpointDialog", "showTtsKeyDialog", "showVoicePackDialog", "startClone", "uri", "Landroid/net/Uri;", "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class AssistantActivity extends BaseActivity {
    public static final Companion Companion = new Companion(null);
    private static final int REQ_PICK_AUDIO = 4301;
    private TextView cvStatus;
    private TextView statusLine;

    /* compiled from: AssistantActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/xiaofan/bangfan/AssistantActivity$Companion;", "", "()V", "REQ_PICK_AUDIO", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
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
        ScrollView scaffold = UiKit.INSTANCE.scaffold(this, "小翻助手", "设置小翻的声音，或配置云端大模型让小翻更聪明。");
        LinearLayout content = UiKit.INSTANCE.contentOf(scaffold);
        this.statusLine = UiKit.INSTANCE.bodyText(this, "");
        TextView textView = this.statusLine;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("statusLine");
            textView = null;
        }
        content.addView(textView, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 4.0f), 0, UiKit.INSTANCE.dp(this, 10.0f)));
        LinearLayout voiceCard = UiKit.INSTANCE.card(this);
        voiceCard.addView(UiKit.INSTANCE.cardTitle(this, "小翻的声音"));
        voiceCard.addView(UiKit.INSTANCE.switchRow(this, "让小翻说话", "精灵儿童音播报", new UiKit.Getter() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda38
            @Override // com.xiaofan.bangfan.UiKit.Getter
            public final Object get() {
                boolean onCreate$lambda$0;
                onCreate$lambda$0 = AssistantActivity.onCreate$lambda$0(AssistantActivity.this);
                return Boolean.valueOf(onCreate$lambda$0);
            }
        }, new UiKit.Setter() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda12
            @Override // com.xiaofan.bangfan.UiKit.Setter
            public final void set(boolean z) {
                AssistantActivity.onCreate$lambda$1(AssistantActivity.this, z);
            }
        }), UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 4.0f), 0, 0));
        voiceCard.addView(UiKit.INSTANCE.divider(this));
        voiceCard.addView(UiKit.INSTANCE.switchRow(this, "翻页时也说话", "关闭可减少打扰（默认关）", new UiKit.Getter() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda21
            @Override // com.xiaofan.bangfan.UiKit.Getter
            public final Object get() {
                boolean onCreate$lambda$2;
                onCreate$lambda$2 = AssistantActivity.onCreate$lambda$2(AssistantActivity.this);
                return Boolean.valueOf(onCreate$lambda$2);
            }
        }, new UiKit.Setter() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda23
            @Override // com.xiaofan.bangfan.UiKit.Setter
            public final void set(boolean z) {
                AssistantActivity.onCreate$lambda$3(AssistantActivity.this, z);
            }
        }));
        voiceCard.addView(UiKit.INSTANCE.divider(this));
        voiceCard.addView(UiKit.INSTANCE.switchRow(this, "按键音效", "点击按钮/开关时响起轻反馈音（默认开）", new UiKit.Getter() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda24
            @Override // com.xiaofan.bangfan.UiKit.Getter
            public final Object get() {
                boolean onCreate$lambda$4;
                onCreate$lambda$4 = AssistantActivity.onCreate$lambda$4(AssistantActivity.this);
                return Boolean.valueOf(onCreate$lambda$4);
            }
        }, new UiKit.Setter() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda25
            @Override // com.xiaofan.bangfan.UiKit.Setter
            public final void set(boolean z) {
                AssistantActivity.onCreate$lambda$5(AssistantActivity.this, z);
            }
        }));
        voiceCard.addView(UiKit.INSTANCE.divider(this));
        voiceCard.addView(UiKit.INSTANCE.valueRow(this, "音调（越高越像小朋友）", new UiKit.Getter() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda26
            @Override // com.xiaofan.bangfan.UiKit.Getter
            public final Object get() {
                String onCreate$lambda$6;
                onCreate$lambda$6 = AssistantActivity.onCreate$lambda$6(AssistantActivity.this);
                return onCreate$lambda$6;
            }
        }, new View.OnClickListener() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda27
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AssistantActivity.onCreate$lambda$7(AssistantActivity.this, view);
            }
        }));
        voiceCard.addView(UiKit.INSTANCE.divider(this));
        voiceCard.addView(UiKit.INSTANCE.valueRow(this, "音色包", new UiKit.Getter() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda28
            @Override // com.xiaofan.bangfan.UiKit.Getter
            public final Object get() {
                String onCreate$lambda$8;
                onCreate$lambda$8 = AssistantActivity.onCreate$lambda$8(AssistantActivity.this);
                return onCreate$lambda$8;
            }
        }, new View.OnClickListener() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda29
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AssistantActivity.onCreate$lambda$9(AssistantActivity.this, view);
            }
        }));
        voiceCard.addView(UiKit.INSTANCE.divider(this));
        voiceCard.addView(UiKit.INSTANCE.valueRow(this, "系统发音人（更自然）", new UiKit.Getter() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda1
            @Override // com.xiaofan.bangfan.UiKit.Getter
            public final Object get() {
                String onCreate$lambda$10;
                onCreate$lambda$10 = AssistantActivity.onCreate$lambda$10(AssistantActivity.this);
                return onCreate$lambda$10;
            }
        }, new View.OnClickListener() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AssistantActivity.onCreate$lambda$11(AssistantActivity.this, view);
            }
        }));
        voiceCard.addView(UiKit.INSTANCE.button(this, "试听小翻的声音", false, new View.OnClickListener() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AssistantActivity.onCreate$lambda$12(AssistantActivity.this, view);
            }
        }), UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 10.0f), 0, 0));
        content.addView(voiceCard, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 12.0f), 0, 0));
        LinearLayout cvCard = UiKit.INSTANCE.card(this);
        cvCard.addView(UiKit.INSTANCE.cardTitle(this, "自定义音色（用你的声音克隆）"));
        cvCard.addView(UiKit.INSTANCE.bodyText(this, "选一段你自己的音频或视频（MP3 / MP4 / WAV / M4A 都行，十几秒即可），上传到你配置的云端音色服务克隆出专属音色，之后小翻就用这个声音说话。\n\n为什么必须配云端服务：安卓系统 TTS 不允许 APP 注入第三方音色文件，音色克隆也需要服务端的模型训练，手机本地做不到；另外克隆他人声音涉及版权与人格权授权，所以小翻不内置任何现成音色库。\n\n没有云端服务也完全不影响使用——上面的「音色包」和「系统发音人」都是离线的。"), UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 6.0f), 0, UiKit.INSTANCE.dp(this, 10.0f)));
        this.cvStatus = UiKit.INSTANCE.bodyText(this, CustomVoice.INSTANCE.statusText(this));
        TextView textView3 = this.cvStatus;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cvStatus");
        } else {
            textView2 = textView3;
        }
        cvCard.addView(textView2, UiKit.INSTANCE.margin(this, 0, 0, 0, UiKit.INSTANCE.dp(this, 10.0f)));
        cvCard.addView(UiKit.INSTANCE.valueRow(this, "音色服务地址", new UiKit.Getter() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda4
            @Override // com.xiaofan.bangfan.UiKit.Getter
            public final Object get() {
                String onCreate$lambda$13;
                onCreate$lambda$13 = AssistantActivity.onCreate$lambda$13(AssistantActivity.this);
                return onCreate$lambda$13;
            }
        }, new View.OnClickListener() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AssistantActivity.onCreate$lambda$14(AssistantActivity.this, view);
            }
        }));
        cvCard.addView(UiKit.INSTANCE.divider(this));
        cvCard.addView(UiKit.INSTANCE.valueRow(this, "音色服务密钥", new UiKit.Getter() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda6
            @Override // com.xiaofan.bangfan.UiKit.Getter
            public final Object get() {
                String onCreate$lambda$15;
                onCreate$lambda$15 = AssistantActivity.onCreate$lambda$15(AssistantActivity.this);
                return onCreate$lambda$15;
            }
        }, new View.OnClickListener() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AssistantActivity.onCreate$lambda$16(AssistantActivity.this, view);
            }
        }));
        cvCard.addView(UiKit.INSTANCE.divider(this));
        cvCard.addView(UiKit.INSTANCE.switchRow(this, "使用自定义音色", "关闭则用系统音色", new UiKit.Getter() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda8
            @Override // com.xiaofan.bangfan.UiKit.Getter
            public final Object get() {
                boolean onCreate$lambda$17;
                onCreate$lambda$17 = AssistantActivity.onCreate$lambda$17(AssistantActivity.this);
                return Boolean.valueOf(onCreate$lambda$17);
            }
        }, new UiKit.Setter() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda9
            @Override // com.xiaofan.bangfan.UiKit.Setter
            public final void set(boolean z) {
                AssistantActivity.onCreate$lambda$18(AssistantActivity.this, z);
            }
        }));
        cvCard.addView(UiKit.INSTANCE.button(this, "选择音频并克隆音色", true, new View.OnClickListener() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AssistantActivity.onCreate$lambda$19(AssistantActivity.this, view);
            }
        }), UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 10.0f), 0, UiKit.INSTANCE.dp(this, 6.0f)));
        cvCard.addView(UiKit.INSTANCE.button(this, "试听自定义音色", false, new View.OnClickListener() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AssistantActivity.onCreate$lambda$20(AssistantActivity.this, view);
            }
        }), UiKit.INSTANCE.margin(this, 0, 0, 0, UiKit.INSTANCE.dp(this, 6.0f)));
        cvCard.addView(UiKit.INSTANCE.button(this, "清除自定义音色", false, new View.OnClickListener() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AssistantActivity.onCreate$lambda$21(AssistantActivity.this, view);
            }
        }));
        content.addView(cvCard, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 12.0f), 0, 0));
        LinearLayout aiCard = UiKit.INSTANCE.card(this);
        aiCard.addView(UiKit.INSTANCE.cardTitle(this, "更聪明的小翻（可选）"));
        aiCard.addView(UiKit.INSTANCE.bodyText(this, "默认全离线，能答天气/时间/闹钟/翻页。若你有自己的大模型接口，填进来后小翻还能回答开放性问题。不填则保持纯离线，绝不联网发问。"), UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 6.0f), 0, UiKit.INSTANCE.dp(this, 8.0f)));
        aiCard.addView(UiKit.INSTANCE.switchRow(this, "启用云端增强", "默认关闭", new UiKit.Getter() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda15
            @Override // com.xiaofan.bangfan.UiKit.Getter
            public final Object get() {
                boolean onCreate$lambda$22;
                onCreate$lambda$22 = AssistantActivity.onCreate$lambda$22(AssistantActivity.this);
                return Boolean.valueOf(onCreate$lambda$22);
            }
        }, new UiKit.Setter() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda16
            @Override // com.xiaofan.bangfan.UiKit.Setter
            public final void set(boolean z) {
                AssistantActivity.onCreate$lambda$23(AssistantActivity.this, z);
            }
        }), UiKit.INSTANCE.margin(this, 0, 0, 0, UiKit.INSTANCE.dp(this, 4.0f)));
        aiCard.addView(UiKit.INSTANCE.valueRow(this, "API 地址", new UiKit.Getter() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda17
            @Override // com.xiaofan.bangfan.UiKit.Getter
            public final Object get() {
                String onCreate$lambda$24;
                onCreate$lambda$24 = AssistantActivity.onCreate$lambda$24(AssistantActivity.this);
                return onCreate$lambda$24;
            }
        }, new View.OnClickListener() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda18
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AssistantActivity.onCreate$lambda$25(AssistantActivity.this, view);
            }
        }));
        aiCard.addView(UiKit.INSTANCE.divider(this));
        aiCard.addView(UiKit.INSTANCE.valueRow(this, "API 密钥", new UiKit.Getter() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda19
            @Override // com.xiaofan.bangfan.UiKit.Getter
            public final Object get() {
                String onCreate$lambda$26;
                onCreate$lambda$26 = AssistantActivity.onCreate$lambda$26(AssistantActivity.this);
                return onCreate$lambda$26;
            }
        }, new View.OnClickListener() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda20
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AssistantActivity.onCreate$lambda$27(AssistantActivity.this, view);
            }
        }));
        content.addView(aiCard, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 12.0f), 0, 0));
        setContentView(scaffold);
        refresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$0(AssistantActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return AppPrefs.INSTANCE.speakOn(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(AssistantActivity this$0, boolean v) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppPrefs.INSTANCE.setSpeakOn(this$0, v);
        if (!v) {
            XiaoFanVoice.INSTANCE.stopSpeaking();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$2(AssistantActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return AppPrefs.INSTANCE.speakTurn(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$3(AssistantActivity this$0, boolean v) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppPrefs.INSTANCE.setSpeakTurn(this$0, v);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$4(AssistantActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return AppPrefs.INSTANCE.clickSound(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$5(AssistantActivity this$0, boolean v) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppPrefs.INSTANCE.setClickSound(this$0, v);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String onCreate$lambda$6(AssistantActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.CHINA, "%.2f", Arrays.copyOf(new Object[]{Float.valueOf(AppPrefs.INSTANCE.speakPitch(this$0))}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$7(AssistantActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showPitchDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String onCreate$lambda$8(AssistantActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return VoicePack.INSTANCE.current(this$0).getName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$9(AssistantActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showVoicePackDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String onCreate$lambda$10(AssistantActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String locale = AppPrefs.INSTANCE.voiceLocale(this$0);
        return locale.length() == 0 ? "默认" : locale;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$11(AssistantActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showSystemVoiceDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$12(AssistantActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XiaoFanVoice.INSTANCE.preview(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String onCreate$lambda$13(AssistantActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String ep = AppPrefs.INSTANCE.ttsEndpoint(this$0);
        return ep.length() == 0 ? "未配置" : ep;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$14(AssistantActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showTtsEndpointDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String onCreate$lambda$15(AssistantActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String key = AppPrefs.INSTANCE.ttsApiKey(this$0);
        if (key.length() == 0) {
            return "未配置";
        }
        return "已配置（" + key.length() + " 位）";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$16(AssistantActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showTtsKeyDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$17(AssistantActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return AppPrefs.INSTANCE.customVoiceOn(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$18(AssistantActivity this$0, boolean v) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (v) {
            if (AppPrefs.INSTANCE.ttsVoiceId(this$0).length() == 0) {
                Toast.makeText(this$0, "还没克隆成功，先完成下面的克隆", 0).show();
                return;
            }
        }
        AppPrefs.INSTANCE.setCustomVoiceOn(this$0, v);
        this$0.refresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$19(AssistantActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.pickVoiceSample();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$20(AssistantActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!CustomVoice.INSTANCE.isReady(this$0)) {
            Toast.makeText(this$0, "还没克隆成功，先完成克隆", 0).show();
        } else {
            XiaoFanVoice.INSTANCE.preview(this$0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$21(AssistantActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppPrefs.INSTANCE.setTtsVoiceId(this$0, "");
        AppPrefs.INSTANCE.setCustomVoiceName(this$0, "");
        AppPrefs.INSTANCE.setCustomVoiceSample(this$0, "");
        AppPrefs.INSTANCE.setCustomVoiceOn(this$0, false);
        this$0.refresh();
        Toast.makeText(this$0, "已清除，回到系统音色", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$22(AssistantActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return AppPrefs.INSTANCE.aiEnabled(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$23(AssistantActivity this$0, boolean v) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppPrefs.INSTANCE.setAiEnabled(this$0, v);
        this$0.refresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String onCreate$lambda$24(AssistantActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String ep = AppPrefs.INSTANCE.aiEndpointRaw(this$0);
        return ep.length() == 0 ? "未设置" : ep;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$25(AssistantActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showEndpointDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String onCreate$lambda$26(AssistantActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String key = AppPrefs.INSTANCE.aiApiKeyRaw(this$0);
        if (key.length() == 0) {
            return "未设置";
        }
        return "已设置（" + key.length() + " 位）";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$27(AssistantActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showKeyDialog();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaofan.bangfan.BaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        refresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void refresh() {
        /*
            r8 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            com.xiaofan.bangfan.AppPrefs r1 = com.xiaofan.bangfan.AppPrefs.INSTANCE
            r2 = r8
            android.content.Context r2 = (android.content.Context) r2
            boolean r1 = r1.speakOn(r2)
            if (r1 == 0) goto L13
            java.lang.String r1 = "会说话"
            goto L15
        L13:
            java.lang.String r1 = "静音"
        L15:
            r0.append(r1)
            java.lang.String r1 = " · "
            r0.append(r1)
            com.xiaofan.bangfan.AppPrefs r1 = com.xiaofan.bangfan.AppPrefs.INSTANCE
            r2 = r8
            android.content.Context r2 = (android.content.Context) r2
            boolean r1 = r1.aiEnabled(r2)
            if (r1 == 0) goto L42
            com.xiaofan.bangfan.AppPrefs r1 = com.xiaofan.bangfan.AppPrefs.INSTANCE
            r2 = r8
            android.content.Context r2 = (android.content.Context) r2
            java.lang.String r1 = r1.aiEndpointRaw(r2)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            int r1 = r1.length()
            if (r1 != 0) goto L3b
            r1 = 1
            goto L3c
        L3b:
            r1 = 0
        L3c:
            if (r1 == 0) goto L3f
            goto L42
        L3f:
            java.lang.String r1 = "云端增强：已配置"
            goto L44
        L42:
            java.lang.String r1 = "云端增强：未启用"
        L44:
            r0.append(r1)
            android.widget.TextView r1 = r8.statusLine
            r2 = 0
            if (r1 != 0) goto L52
            java.lang.String r1 = "statusLine"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r1 = r2
        L52:
            java.lang.String r3 = r0.toString()
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            r1.setText(r3)
            com.xiaofan.bangfan.CustomVoice r1 = com.xiaofan.bangfan.CustomVoice.INSTANCE
            r3 = r8
            android.content.Context r3 = (android.content.Context) r3
            java.lang.String r1 = r1.statusText(r3)
            com.xiaofan.bangfan.CustomVoice r3 = com.xiaofan.bangfan.CustomVoice.INSTANCE
            r4 = r8
            android.content.Context r4 = (android.content.Context) r4
            boolean r3 = r3.isReady(r4)
            android.widget.TextView r4 = r8.cvStatus
            java.lang.String r5 = "cvStatus"
            if (r4 != 0) goto L77
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r4 = r2
        L77:
            if (r3 == 0) goto L8f
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.StringBuilder r6 = r6.append(r1)
            java.lang.String r7 = "（已启用）"
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            goto L92
        L8f:
            r6 = r1
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
        L92:
            r4.setText(r6)
            android.widget.TextView r4 = r8.cvStatus
            if (r4 != 0) goto L9d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            goto L9e
        L9d:
            r2 = r4
        L9e:
            com.xiaofan.bangfan.UiKit r4 = com.xiaofan.bangfan.UiKit.INSTANCE
            r5 = r8
            android.app.Activity r5 = (android.app.Activity) r5
            if (r3 == 0) goto La8
            int r6 = com.xiaofan.bangfan.R.color.ok
            goto Laa
        La8:
            int r6 = com.xiaofan.bangfan.R.color.muted
        Laa:
            int r4 = r4.color(r5, r6)
            r2.setTextColor(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.AssistantActivity.refresh():void");
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        FloatBallService companion;
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int length = grantResults.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            int element$iv = grantResults[i];
            int it = element$iv == 0 ? 1 : 0;
            if (it != 0) {
                z = true;
                break;
            }
            i++;
        }
        if (z && FloatBallService.Companion.isRunning() && (companion = FloatBallService.Companion.getInstance()) != null) {
            companion.onPermissionGranted();
        }
        refresh();
    }

    private final void showPitchDialog() {
        final float[] pitches = {1.2f, 1.5f, 1.75f, 2.0f};
        new AlertDialog.Builder(this).setTitle("小翻音调").setItems(new String[]{"1.20（偏低）", "1.50（默认·自然）", "1.75（偏亮）", "2.00（精灵儿童音）"}, new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda35
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AssistantActivity.showPitchDialog$lambda$29(AssistantActivity.this, pitches, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showPitchDialog$lambda$29(AssistantActivity this$0, float[] pitches, DialogInterface dialogInterface, int which) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(pitches, "$pitches");
        AppPrefs.INSTANCE.setSpeakPitch(this$0, pitches[which]);
        XiaoFanVoice.INSTANCE.applyVoiceParams(this$0);
        XiaoFanVoice.INSTANCE.preview(this$0);
    }

    private final void showVoicePackDialog() {
        int count = VoicePack.INSTANCE.presetCount();
        String[] items = new String[count];
        for (int i = 0; i < count; i++) {
            VoicePack.Preset p = VoicePack.INSTANCE.preset(i);
            String name = p.getName();
            items[i] = name + " — " + p.getDesc();
        }
        new AlertDialog.Builder(this).setTitle("选择音色包（离线即用）").setSingleChoiceItems(items, AppPrefs.INSTANCE.voicePack(this), new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda37
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                AssistantActivity.showVoicePackDialog$lambda$30(AssistantActivity.this, dialogInterface, i2);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showVoicePackDialog$lambda$30(AssistantActivity this$0, DialogInterface dialog, int which) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        VoicePack.INSTANCE.applyPresetToPrefs(this$0, which);
        XiaoFanVoice.INSTANCE.applyVoiceParams(this$0);
        XiaoFanVoice.INSTANCE.preview(this$0);
        this$0.refresh();
        dialog.dismiss();
    }

    private final void showSystemVoiceDialog() {
        Toast.makeText(this, "正在读取本机已安装的语音…", 0).show();
        new Thread(new Runnable() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AssistantActivity.showSystemVoiceDialog$lambda$33(AssistantActivity.this);
            }
        }, "list-voices").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showSystemVoiceDialog$lambda$33(final AssistantActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        final List voices = VoicePack.INSTANCE.listSystemVoices(this$0);
        this$0.runOnUiThread(new Runnable() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda36
            @Override // java.lang.Runnable
            public final void run() {
                AssistantActivity.showSystemVoiceDialog$lambda$33$lambda$32(voices, this$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showSystemVoiceDialog$lambda$33$lambda$32(List voices, final AssistantActivity this$0) {
        Intrinsics.checkNotNullParameter(voices, "$voices");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (voices.isEmpty()) {
            new AlertDialog.Builder(this$0).setTitle("系统发音人").setMessage("本机 TTS 引擎没有可单独选择的中文发音人。\n\n这不影响使用——你可以直接用上面的「音色包」，它是离线参数合成，任何手机都能切换。").setPositiveButton("知道了", (DialogInterface.OnClickListener) null).show();
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
        new AlertDialog.Builder(this$0).setTitle("选择系统发音人").setItems(items, new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda33
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i3) {
                AssistantActivity.showSystemVoiceDialog$lambda$33$lambda$32$lambda$31(AssistantActivity.this, locales, dialogInterface, i3);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showSystemVoiceDialog$lambda$33$lambda$32$lambda$31(AssistantActivity this$0, String[] locales, DialogInterface dialogInterface, int which) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(locales, "$locales");
        AppPrefs.INSTANCE.setVoiceLocale(this$0, locales[which]);
        XiaoFanVoice.INSTANCE.applyVoiceParams(this$0);
        XiaoFanVoice.INSTANCE.preview(this$0);
        this$0.refresh();
    }

    private final void showEndpointDialog() {
        final EditText input = new EditText(this);
        input.setHint("https://你的接口/v1/chat/completions");
        input.setInputType(16);
        input.setText(AppPrefs.INSTANCE.aiEndpointRaw(this));
        input.setSelection(input.getText().length());
        input.setPadding(UiKit.INSTANCE.dp(this, 16.0f), UiKit.INSTANCE.dp(this, 12.0f), UiKit.INSTANCE.dp(this, 16.0f), UiKit.INSTANCE.dp(this, 12.0f));
        new AlertDialog.Builder(this).setTitle("API 地址（OpenAI 兼容）").setView(input).setPositiveButton("保存", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda34
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AssistantActivity.showEndpointDialog$lambda$34(AssistantActivity.this, input, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showEndpointDialog$lambda$34(AssistantActivity this$0, EditText input, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(input, "$input");
        AppPrefs.INSTANCE.setAiEndpoint(this$0, StringsKt.trim((CharSequence) input.getText().toString()).toString());
        this$0.refresh();
    }

    private final void showKeyDialog() {
        final EditText input = new EditText(this);
        input.setHint("sk-... 留空表示无需密钥");
        input.setInputType(128);
        input.setText(AppPrefs.INSTANCE.aiApiKeyRaw(this));
        input.setPadding(UiKit.INSTANCE.dp(this, 16.0f), UiKit.INSTANCE.dp(this, 12.0f), UiKit.INSTANCE.dp(this, 16.0f), UiKit.INSTANCE.dp(this, 12.0f));
        new AlertDialog.Builder(this).setTitle("API 密钥").setView(input).setPositiveButton("保存", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda22
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AssistantActivity.showKeyDialog$lambda$35(AssistantActivity.this, input, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showKeyDialog$lambda$35(AssistantActivity this$0, EditText input, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(input, "$input");
        AppPrefs.INSTANCE.setAiApiKey(this$0, StringsKt.trim((CharSequence) input.getText().toString()).toString());
        this$0.refresh();
    }

    private final void showTtsEndpointDialog() {
        final EditText input = new EditText(this);
        input.setHint("https://你的接口/v1/audio/speech");
        input.setInputType(16);
        input.setText(AppPrefs.INSTANCE.ttsEndpoint(this));
        input.setSelection(input.getText().length());
        input.setPadding(UiKit.INSTANCE.dp(this, 16.0f), UiKit.INSTANCE.dp(this, 12.0f), UiKit.INSTANCE.dp(this, 16.0f), UiKit.INSTANCE.dp(this, 12.0f));
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(1);
        container.setPadding(UiKit.INSTANCE.dp(this, 20.0f), UiKit.INSTANCE.dp(this, 8.0f), UiKit.INSTANCE.dp(this, 20.0f), 0);
        container.addView(UiKit.INSTANCE.bodyText(this, "填支持音色克隆的 TTS 接口（OpenAI 风格）。\n克隆会 POST 到该地址的 /voices，合成会 POST 到该地址本身。"));
        container.addView(input, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 12.0f), 0, 0));
        new AlertDialog.Builder(this).setTitle("音色服务地址").setView(container).setPositiveButton("保存", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda32
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AssistantActivity.showTtsEndpointDialog$lambda$36(AssistantActivity.this, input, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showTtsEndpointDialog$lambda$36(AssistantActivity this$0, EditText input, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(input, "$input");
        AppPrefs.INSTANCE.setTtsEndpoint(this$0, StringsKt.trim((CharSequence) input.getText().toString()).toString());
        this$0.refresh();
    }

    private final void showTtsKeyDialog() {
        final EditText input = new EditText(this);
        input.setHint("sk-... 留空表示无需密钥");
        input.setInputType(128);
        input.setText(AppPrefs.INSTANCE.ttsApiKey(this));
        input.setPadding(UiKit.INSTANCE.dp(this, 16.0f), UiKit.INSTANCE.dp(this, 12.0f), UiKit.INSTANCE.dp(this, 16.0f), UiKit.INSTANCE.dp(this, 12.0f));
        new AlertDialog.Builder(this).setTitle("音色服务密钥").setView(input).setPositiveButton("保存", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda11
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AssistantActivity.showTtsKeyDialog$lambda$37(AssistantActivity.this, input, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showTtsKeyDialog$lambda$37(AssistantActivity this$0, EditText input, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(input, "$input");
        AppPrefs.INSTANCE.setTtsApiKey(this$0, StringsKt.trim((CharSequence) input.getText().toString()).toString());
        this$0.refresh();
    }

    private final void pickVoiceSample() {
        if (AppPrefs.INSTANCE.ttsEndpoint(this).length() == 0) {
            Toast.makeText(this, "请先填音色服务地址，否则克隆没有地方可传", 1).show();
            showTtsEndpointDialog();
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.setType("*/*");
            intent.putExtra("android.intent.extra.MIME_TYPES", new String[]{"audio/*", "video/*"});
            intent.addCategory("android.intent.category.OPENABLE");
            startActivityForResult(intent, REQ_PICK_AUDIO);
        } catch (Throwable th) {
            Toast.makeText(this, "这台设备没有可用的文件选择器", 0).show();
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri uri;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != REQ_PICK_AUDIO || resultCode != -1 || data == null || (uri = data.getData()) == null) {
            return;
        }
        try {
            getContentResolver().takePersistableUriPermission(uri, 1);
        } catch (Throwable th) {
        }
        startClone(uri);
    }

    private final void startClone(final Uri uri) {
        final EditText input = new EditText(this);
        input.setHint("给这个音色起个名字");
        input.setInputType(1);
        input.setSingleLine(true);
        input.setText("我的声音");
        input.setSelection(input.getText().length());
        input.setPadding(UiKit.INSTANCE.dp(this, 16.0f), UiKit.INSTANCE.dp(this, 12.0f), UiKit.INSTANCE.dp(this, 16.0f), UiKit.INSTANCE.dp(this, 12.0f));
        new AlertDialog.Builder(this).setTitle("开始克隆").setMessage("已选好参考音频。给这个音色起个名字：").setView(input).setPositiveButton("开始克隆", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda30
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AssistantActivity.startClone$lambda$39(input, this, uri, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startClone$lambda$39(EditText input, final AssistantActivity this$0, final Uri uri, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(input, "$input");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(uri, "$uri");
        final String name = StringsKt.trim((CharSequence) input.getText().toString()).toString();
        this$0.setCvStatus("正在上传音频并克隆，可能需要十几秒…", false);
        Toast.makeText(this$0, "正在克隆，请不要退出页面", 1).show();
        new Thread(new Runnable() { // from class: com.xiaofan.bangfan.AssistantActivity$$ExternalSyntheticLambda31
            @Override // java.lang.Runnable
            public final void run() {
                AssistantActivity.startClone$lambda$39$lambda$38(AssistantActivity.this, uri, name);
            }
        }, "voice-clone").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startClone$lambda$39$lambda$38(AssistantActivity this$0, Uri uri, String name) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(uri, "$uri");
        Intrinsics.checkNotNullParameter(name, "$name");
        CustomVoice.INSTANCE.clone(this$0, uri, name, new AssistantActivity$startClone$1$1$1(this$0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCvStatus(String text, boolean ok) {
        TextView textView = this.cvStatus;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cvStatus");
            textView = null;
        }
        textView.setText(text);
        TextView textView3 = this.cvStatus;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cvStatus");
        } else {
            textView2 = textView3;
        }
        textView2.setTextColor(UiKit.INSTANCE.color(this, ok ? R.color.ok : R.color.muted));
    }
}
