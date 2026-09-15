package com.xiaofan.bangfan;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: QwenBridge.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004J\u0018\u0010\f\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u0004J\u0010\u0010\r\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u001a\u0010\u000e\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/xiaofan/bangfan/QwenBridge;", "", "()V", "QWEN_PACKAGE", "", "QWEN_WEB", "TAG", "copyToClipboard", "", "context", "Landroid/content/Context;", "text", "handoff", "isInstalled", "open", "openWeb", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class QwenBridge {
    public static final QwenBridge INSTANCE = new QwenBridge();
    public static final String QWEN_PACKAGE = "com.aliyun.tongyi";
    private static final String QWEN_WEB = "https://www.qianwen.com/";
    private static final String TAG = "QwenBridge";

    private QwenBridge() {
    }

    public final boolean isInstalled(Context context) {
        boolean z = false;
        if (context == null) {
            return false;
        }
        try {
            PackageManager pm = context.getPackageManager();
            try {
                pm.getPackageInfo(QWEN_PACKAGE, 0);
                z = true;
            } catch (Exception e) {
                if (pm.getLaunchIntentForPackage(QWEN_PACKAGE) != null) {
                    z = true;
                }
            }
        } catch (Throwable th) {
            Log.w(TAG, "isInstalled check failed", th);
        }
        return z;
    }

    public final boolean open(Context context, String text) {
        if (context == null) {
            return false;
        }
        String str = text;
        if (!(str == null || StringsKt.isBlank(str))) {
            copyToClipboard(context, StringsKt.trim((CharSequence) text).toString());
        }
        try {
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(QWEN_PACKAGE);
            if (intent != null) {
                intent.addFlags(268435456);
                context.startActivity(intent);
                Log.i(TAG, "opened qwen app");
                return true;
            }
            return false;
        } catch (Throwable th) {
            Log.w(TAG, "open qwen failed", th);
            return openWeb(context);
        }
    }

    public final boolean openWeb(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(QWEN_WEB));
            intent.addFlags(268435456);
            context.startActivity(intent);
            Log.i(TAG, "opened qwen web");
            return true;
        } catch (Throwable th) {
            Log.w(TAG, "open qwen web failed", th);
            return false;
        }
    }

    public final boolean copyToClipboard(Context context, String text) {
        if (context == null || text == null) {
            return false;
        }
        try {
            Object systemService = context.getSystemService("clipboard");
            ClipboardManager clipboard = systemService instanceof ClipboardManager ? (ClipboardManager) systemService : null;
            if (clipboard == null) {
                return false;
            }
            clipboard.setPrimaryClip(ClipData.newPlainText("question", text));
            return true;
        } catch (Throwable th) {
            Log.w(TAG, "copy to clipboard failed", th);
            return false;
        }
    }

    public final String handoff(Context context, String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (context != null) {
            if (AppPrefs.INSTANCE.aiEndpoint(context).length() > 0) {
                return "";
            }
            return isInstalled(context) ? open(context, text) ? "这个问题我帮你转到千问了，问题已复制，长按输入框粘贴就能问" : "千问打不开，你可以自己打开千问 APP 问一下" : openWeb(context) ? "本机没装千问 APP，我帮你打开了千问网页版，问题已复制到剪贴板" : "本机没装千问 APP，建议你装一个，或到应用商店搜索「千问」";
        }
        return "";
    }
}
