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
/* compiled from: DoubaoBridge.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0004J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0018\u0010\u000f\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u0004J\u0010\u0010\u0010\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u001a\u0010\u0011\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/xiaofan/bangfan/DoubaoBridge;", "", "()V", "DOUBAO_WEB", "", "PACKAGE_CANDIDATES", "", "[Ljava/lang/String;", "TAG", "copyToClipboard", "", "context", "Landroid/content/Context;", "text", "getPackage", "handoff", "isInstalled", "open", "openWeb", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class DoubaoBridge {
    private static final String DOUBAO_WEB = "https://www.doubao.com/";
    public static final DoubaoBridge INSTANCE = new DoubaoBridge();
    private static final String[] PACKAGE_CANDIDATES = {"com.doubao.ai", "com.larus.nova", "com.ss.android.ugc.aweme"};
    private static final String TAG = "DoubaoBridge";

    private DoubaoBridge() {
    }

    public final boolean isInstalled(Context context) {
        String[] strArr;
        Exception exc;
        Exception _;
        if (context == null) {
            return false;
        }
        try {
            PackageManager pm = context.getPackageManager();
            for (String str : PACKAGE_CANDIDATES) {
                try {
                    if (pm.getPackageInfo(str, 0) == null) {
                        _ = null;
                    } else {
                        _ = 1;
                    }
                } catch (Exception e) {
                    if (pm.getLaunchIntentForPackage(str) == null) {
                        exc = null;
                    } else {
                        exc = 1;
                    }
                    _ = exc;
                }
                if (_ != null) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            Log.w(TAG, "isInstalled check failed", th);
            return false;
        }
    }

    private final String getPackage(Context context) {
        String[] strArr;
        PackageManager pm = context.getPackageManager();
        for (String str : PACKAGE_CANDIDATES) {
            boolean z = true;
            try {
                if (pm.getPackageInfo(str, 0) == null) {
                    z = false;
                }
            } catch (Exception e) {
                if (pm.getLaunchIntentForPackage(str) == null) {
                    z = false;
                }
            }
            if (z) {
                return str;
            }
        }
        return null;
    }

    public final boolean open(Context context, String text) {
        Intent intent;
        if (context == null) {
            return false;
        }
        String str = text;
        if (!(str == null || StringsKt.isBlank(str))) {
            copyToClipboard(context, StringsKt.trim((CharSequence) text).toString());
        }
        try {
            String pkg = getPackage(context);
            if (pkg == null || (intent = context.getPackageManager().getLaunchIntentForPackage(pkg)) == null) {
                return false;
            }
            intent.addFlags(268435456);
            context.startActivity(intent);
            Log.i(TAG, "opened doubao app: " + pkg);
            return true;
        } catch (Throwable th) {
            Log.w(TAG, "open doubao failed", th);
            return openWeb(context);
        }
    }

    public final boolean openWeb(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(DOUBAO_WEB));
            intent.addFlags(268435456);
            context.startActivity(intent);
            Log.i(TAG, "opened doubao web");
            return true;
        } catch (Throwable th) {
            Log.w(TAG, "open doubao web failed", th);
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
        return context == null ? "" : isInstalled(context) ? open(context, text) ? "这个问题我帮你转到豆包了，问题已复制，长按输入框粘贴就能问" : "豆包打不开，你可以自己打开豆包 APP 问一下" : openWeb(context) ? "本机没装豆包 APP，我帮你打开了豆包网页版，问题已复制到剪贴板" : "本机没装豆包 APP，建议你装一个，或到应用商店搜索「豆包」";
    }
}
