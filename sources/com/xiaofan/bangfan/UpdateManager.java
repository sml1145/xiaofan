package com.xiaofan.bangfan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
import com.xiaofan.bangfan.NetState;
import com.xiaofan.bangfan.UpdateManager;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.DebugKt;
/* compiled from: UpdateManager.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u00012B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\fJ\u000e\u0010\u001b\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015J\u0018\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u0011H\u0002J:\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u00062\u0018\u0010\"\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00170#H\u0002J\u0012\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0018\u0010&\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u0011H\u0002J\u000e\u0010'\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010(\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J \u0010)\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0010\b\u0002\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010+J(\u0010,\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010-\u001a\u00020%2\u0006\u0010.\u001a\u00020\n2\u0006\u0010/\u001a\u00020\fH\u0002J\u000e\u00100\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015J\u0018\u00101\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010-\u001a\u00020%H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/xiaofan/bangfan/UpdateManager;", "", "()V", "CONNECT_TIMEOUT", "", "MIN_AUTO_INTERVAL_MS", "", "READ_TIMEOUT", "REQ_UNKNOWN_INSTALL", "TAG", "", "checking", "", "lastAutoAt", "main", "Landroid/os/Handler;", "pendingInstall", "Ljava/io/File;", "updateDialogShowing", "canInstall", "ctx", "Landroid/content/Context;", "checkAndPrompt", "", "activity", "Landroid/app/Activity;", "silent", "currentVersion", "doInstall", "file", "downloadFile", "urlStr", "target", "expectedSize", "onProgress", "Lkotlin/Function2;", "fetchLatest", "Lcom/xiaofan/bangfan/UpdateManager$Release;", "installOrRequestPermission", "onEnterApp", "resumePendingInstall", "showSourceDialog", "onSaved", "Lkotlin/Function0;", "showUpdateDialog", "rel", "cur", DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "sourceSlug", "startDownload", "Release", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class UpdateManager {
    private static final int CONNECT_TIMEOUT = 12000;
    private static final long MIN_AUTO_INTERVAL_MS = 15000;
    private static final int READ_TIMEOUT = 30000;
    private static final int REQ_UNKNOWN_INSTALL = 4310;
    private static final String TAG = "XiaoFanUpdate";
    private static volatile boolean checking;
    private static volatile long lastAutoAt;
    private static volatile File pendingInstall;
    private static volatile boolean updateDialogShowing;
    public static final UpdateManager INSTANCE = new UpdateManager();
    private static final Handler main = new Handler(Looper.getMainLooper());

    private UpdateManager() {
    }

    /* compiled from: UpdateManager.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\tHÆ\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006 "}, d2 = {"Lcom/xiaofan/bangfan/UpdateManager$Release;", "", "version", "", "notes", "apkUrl", "tagName", "assetName", "size", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getApkUrl", "()Ljava/lang/String;", "getAssetName", "getNotes", "getSize", "()J", "getTagName", "getVersion", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Release {
        private final String apkUrl;
        private final String assetName;
        private final String notes;
        private final long size;
        private final String tagName;
        private final String version;

        public static /* synthetic */ Release copy$default(Release release, String str, String str2, String str3, String str4, String str5, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                str = release.version;
            }
            if ((i & 2) != 0) {
                str2 = release.notes;
            }
            String str6 = str2;
            if ((i & 4) != 0) {
                str3 = release.apkUrl;
            }
            String str7 = str3;
            if ((i & 8) != 0) {
                str4 = release.tagName;
            }
            String str8 = str4;
            if ((i & 16) != 0) {
                str5 = release.assetName;
            }
            String str9 = str5;
            if ((i & 32) != 0) {
                j = release.size;
            }
            return release.copy(str, str6, str7, str8, str9, j);
        }

        public final String component1() {
            return this.version;
        }

        public final String component2() {
            return this.notes;
        }

        public final String component3() {
            return this.apkUrl;
        }

        public final String component4() {
            return this.tagName;
        }

        public final String component5() {
            return this.assetName;
        }

        public final long component6() {
            return this.size;
        }

        public final Release copy(String version, String notes, String apkUrl, String tagName, String assetName, long j) {
            Intrinsics.checkNotNullParameter(version, "version");
            Intrinsics.checkNotNullParameter(notes, "notes");
            Intrinsics.checkNotNullParameter(apkUrl, "apkUrl");
            Intrinsics.checkNotNullParameter(tagName, "tagName");
            Intrinsics.checkNotNullParameter(assetName, "assetName");
            return new Release(version, notes, apkUrl, tagName, assetName, j);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Release) {
                Release release = (Release) obj;
                return Intrinsics.areEqual(this.version, release.version) && Intrinsics.areEqual(this.notes, release.notes) && Intrinsics.areEqual(this.apkUrl, release.apkUrl) && Intrinsics.areEqual(this.tagName, release.tagName) && Intrinsics.areEqual(this.assetName, release.assetName) && this.size == release.size;
            }
            return false;
        }

        public int hashCode() {
            return (((((((((this.version.hashCode() * 31) + this.notes.hashCode()) * 31) + this.apkUrl.hashCode()) * 31) + this.tagName.hashCode()) * 31) + this.assetName.hashCode()) * 31) + Long.hashCode(this.size);
        }

        public String toString() {
            String str = this.version;
            String str2 = this.notes;
            String str3 = this.apkUrl;
            String str4 = this.tagName;
            String str5 = this.assetName;
            return "Release(version=" + str + ", notes=" + str2 + ", apkUrl=" + str3 + ", tagName=" + str4 + ", assetName=" + str5 + ", size=" + this.size + ")";
        }

        public Release(String version, String notes, String apkUrl, String tagName, String assetName, long size) {
            Intrinsics.checkNotNullParameter(version, "version");
            Intrinsics.checkNotNullParameter(notes, "notes");
            Intrinsics.checkNotNullParameter(apkUrl, "apkUrl");
            Intrinsics.checkNotNullParameter(tagName, "tagName");
            Intrinsics.checkNotNullParameter(assetName, "assetName");
            this.version = version;
            this.notes = notes;
            this.apkUrl = apkUrl;
            this.tagName = tagName;
            this.assetName = assetName;
            this.size = size;
        }

        public final String getApkUrl() {
            return this.apkUrl;
        }

        public final String getAssetName() {
            return this.assetName;
        }

        public final String getNotes() {
            return this.notes;
        }

        public final long getSize() {
            return this.size;
        }

        public final String getTagName() {
            return this.tagName;
        }

        public final String getVersion() {
            return this.version;
        }
    }

    public final String currentVersion(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        try {
            String str = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0).versionName;
            return str == null ? "0" : str;
        } catch (Throwable th) {
            return "0";
        }
    }

    public final String sourceSlug(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        String owner = AppPrefs.INSTANCE.updateOwner(ctx);
        String repo = AppPrefs.INSTANCE.updateRepo(ctx);
        return StringsKt.isBlank(owner) ? "" : owner + "/" + repo;
    }

    public final void onEnterApp(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (checking || updateDialogShowing || !AppPrefs.INSTANCE.updateAutoCheck(activity) || StringsKt.isBlank(sourceSlug(activity)) || !NetState.INSTANCE.isOnline(activity)) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now - lastAutoAt < MIN_AUTO_INTERVAL_MS) {
            return;
        }
        lastAutoAt = now;
        checkAndPrompt(activity, true);
    }

    public final void checkAndPrompt(final Activity activity, final boolean silent) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (checking) {
            return;
        }
        String slug = sourceSlug(activity);
        if (StringsKt.isBlank(slug)) {
            if (!silent) {
                final AlertDialog d = new AlertDialog.Builder(activity).setTitle("还没设置更新源").setMessage("应用内更新从 GitHub Releases 拉取，请先填写你的 GitHub 仓库（格式：用户名/仓库名），并把每个版本的 APK 传到该仓库的 Releases。").setPositiveButton("去设置", (DialogInterface.OnClickListener) null).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
                d.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda7
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        UpdateManager.checkAndPrompt$lambda$1$lambda$0(d, activity, view);
                    }
                });
                return;
            }
            return;
        }
        checking = true;
        if (!silent) {
            Toast.makeText(activity, "正在检查更新…", 0).show();
        }
        final String cur = currentVersion(activity);
        new Thread(new Runnable() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                UpdateManager.checkAndPrompt$lambda$4(activity, silent, cur);
            }
        }, "xf-update-check").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkAndPrompt$lambda$1$lambda$0(AlertDialog $d, final Activity activity, View it) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        $d.dismiss();
        INSTANCE.showSourceDialog(activity, new Function0<Unit>() { // from class: com.xiaofan.bangfan.UpdateManager$checkAndPrompt$1$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                UpdateManager.INSTANCE.checkAndPrompt(activity, false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkAndPrompt$lambda$4(final Activity activity, final boolean $silent, final String cur) {
        final Release rel;
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(cur, "$cur");
        try {
            rel = INSTANCE.fetchLatest(activity);
        } catch (Throwable th) {
            Log.w(TAG, "fetch latest failed", th);
            main.post(new Runnable() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    UpdateManager.checkAndPrompt$lambda$4$lambda$2($silent, activity, th);
                }
            });
            rel = null;
        }
        if (rel == null) {
            return;
        }
        main.post(new Runnable() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                UpdateManager.checkAndPrompt$lambda$4$lambda$3(UpdateManager.Release.this, cur, $silent, activity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkAndPrompt$lambda$4$lambda$2(boolean $silent, Activity activity, Throwable th) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(th, "$th");
        checking = false;
        if (!$silent) {
            Activity activity2 = activity;
            String message = th.getMessage();
            if (message == null) {
                message = "网络不通";
            }
            Toast.makeText(activity2, "检查更新失败：" + message, 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkAndPrompt$lambda$4$lambda$3(Release $rel, String cur, boolean $silent, Activity activity) {
        Intrinsics.checkNotNullParameter(cur, "$cur");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        checking = false;
        if (!AppVersion.INSTANCE.isNewer($rel.getVersion(), cur)) {
            if (!$silent) {
                new AlertDialog.Builder(activity).setTitle("已是最新版本").setMessage("当前版本 V" + cur + "，GitHub 最新版本 V" + $rel.getVersion() + "。").setPositiveButton("好的", (DialogInterface.OnClickListener) null).show();
            }
        } else if ($silent && Intrinsics.areEqual($rel.getVersion(), AppPrefs.INSTANCE.updateSkipped(activity))) {
            Log.i(TAG, "auto prompt skipped for ignored version " + $rel.getVersion());
        } else {
            INSTANCE.showUpdateDialog(activity, $rel, cur, $silent);
        }
    }

    private final void showUpdateDialog(final Activity activity, final Release rel, String cur, boolean auto) {
        if (updateDialogShowing) {
            return;
        }
        NetState.Kind netKind = NetState.INSTANCE.kind(activity);
        String msg = NetState.INSTANCE.composeUpdateMessage(rel.getVersion(), cur, rel.getSize(), netKind, rel.getNotes(), rel.getTagName(), rel.getAssetName());
        updateDialogShowing = true;
        AlertDialog dialog = new AlertDialog.Builder(activity).setTitle("发现新版本 V" + rel.getVersion()).setMessage(msg).setPositiveButton("立即更新", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda9
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                UpdateManager.showUpdateDialog$lambda$5(activity, rel, dialogInterface, i);
            }
        }).setNegativeButton("暂不更新", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda10
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                UpdateManager.showUpdateDialog$lambda$6(activity, rel, dialogInterface, i);
            }
        }).setCancelable(true).show();
        if (auto) {
            dialog.setCanceledOnTouchOutside(true);
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda11
                @Override // android.content.DialogInterface.OnCancelListener
                public final void onCancel(DialogInterface dialogInterface) {
                    UpdateManager.showUpdateDialog$lambda$7(activity, rel, dialogInterface);
                }
            });
        }
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda12
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                UpdateManager.updateDialogShowing = false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showUpdateDialog$lambda$5(Activity activity, Release rel, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(rel, "$rel");
        AppPrefs.INSTANCE.setUpdateSkipped(activity, "");
        INSTANCE.startDownload(activity, rel);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showUpdateDialog$lambda$6(Activity activity, Release rel, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(rel, "$rel");
        AppPrefs.INSTANCE.setUpdateSkipped(activity, rel.getVersion());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showUpdateDialog$lambda$7(Activity activity, Release rel, DialogInterface it) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(rel, "$rel");
        AppPrefs.INSTANCE.setUpdateSkipped(activity, rel.getVersion());
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:60:0x0237
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    private final com.xiaofan.bangfan.UpdateManager.Release fetchLatest(android.content.Context r39) {
        /*
            Method dump skipped, instructions count: 790
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.UpdateManager.fetchLatest(android.content.Context):com.xiaofan.bangfan.UpdateManager$Release");
    }

    private final void startDownload(final Activity activity, final Release rel) {
        LinearLayout box = new LinearLayout(activity);
        box.setOrientation(1);
        box.setPadding(UiKit.INSTANCE.dp(activity, 20.0f), UiKit.INSTANCE.dp(activity, 12.0f), UiKit.INSTANCE.dp(activity, 20.0f), 0);
        final ProgressBar $this$startDownload_u24lambda_u2414 = new ProgressBar(activity, null, 16842872);
        $this$startDownload_u24lambda_u2414.setMax(100);
        $this$startDownload_u24lambda_u2414.setProgress(0);
        final TextView $this$startDownload_u24lambda_u2415 = new TextView(activity);
        $this$startDownload_u24lambda_u2415.setGravity(17);
        $this$startDownload_u24lambda_u2415.setText("准备下载…");
        box.addView($this$startDownload_u24lambda_u2414);
        LinearLayout.LayoutParams it = new LinearLayout.LayoutParams(-1, -2);
        it.topMargin = UiKit.INSTANCE.dp(activity, 8.0f);
        Unit unit = Unit.INSTANCE;
        box.addView($this$startDownload_u24lambda_u2415, it);
        final AlertDialog dialog = new AlertDialog.Builder(activity).setTitle("正在下载 V" + rel.getVersion()).setView(box).setCancelable(false).show();
        new Thread(new Runnable() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                UpdateManager.startDownload$lambda$20(activity, rel, $this$startDownload_u24lambda_u2414, $this$startDownload_u24lambda_u2415, dialog);
            }
        }, "xf-update-download").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startDownload$lambda$20(final Activity activity, final Release rel, ProgressBar bar, TextView pct, final AlertDialog $dialog) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(rel, "$rel");
        Intrinsics.checkNotNullParameter(bar, "$bar");
        Intrinsics.checkNotNullParameter(pct, "$pct");
        try {
            File dir = activity.getExternalFilesDir("updates");
            if (dir == null) {
                dir = activity.getFilesDir();
            }
            if (!dir.exists()) {
                dir.mkdirs();
            }
            final File target = new File(dir, "xiaofan_v" + rel.getVersion() + ".apk");
            INSTANCE.downloadFile(rel.getApkUrl(), target, rel.getSize(), new UpdateManager$startDownload$2$1(bar, pct));
            main.post(new Runnable() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    UpdateManager.startDownload$lambda$20$lambda$17($dialog, target, activity);
                }
            });
        } catch (Throwable th) {
            Log.w(TAG, "download failed", th);
            main.post(new Runnable() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    UpdateManager.startDownload$lambda$20$lambda$19($dialog, activity, th, rel);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startDownload$lambda$20$lambda$17(AlertDialog $dialog, File target, Activity activity) {
        Intrinsics.checkNotNullParameter(target, "$target");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        try {
            $dialog.dismiss();
        } catch (Throwable th) {
        }
        pendingInstall = target;
        INSTANCE.installOrRequestPermission(activity, target);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startDownload$lambda$20$lambda$19(AlertDialog $dialog, final Activity activity, Throwable th, final Release rel) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(th, "$th");
        Intrinsics.checkNotNullParameter(rel, "$rel");
        try {
            $dialog.dismiss();
        } catch (Throwable th2) {
        }
        AlertDialog.Builder title = new AlertDialog.Builder(activity).setTitle("下载失败");
        String message = th.getMessage();
        if (message == null) {
            message = "网络中断";
        }
        title.setMessage(message + "\n已支持断点续传，可换网络后点重试继续下载。").setPositiveButton("重试", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                UpdateManager.startDownload$lambda$20$lambda$19$lambda$18(activity, rel, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startDownload$lambda$20$lambda$19$lambda$18(Activity activity, Release rel, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(rel, "$rel");
        INSTANCE.startDownload(activity, rel);
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x0237, code lost:
        if (r0.renameTo(r33) != false) goto L142;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0239, code lost:
        r24 = r27;
        r25 = r9;
        r26 = r29;
        kotlin.io.FilesKt.copyTo$default(r0, r33, true, 0, 4, null);
        r0.delete();
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0254, code lost:
        r25 = r9;
        r24 = r27;
        r26 = r29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x025b, code lost:
        r0.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x026e, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x026f, code lost:
        r4 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0277, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0278, code lost:
        r5 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0280, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x0281, code lost:
        r5 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0288, code lost:
        throw r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0289, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x028b, code lost:
        kotlin.io.CloseableKt.closeFinally(r4, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x028e, code lost:
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x028f, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x0290, code lost:
        r4 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x0292, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x0293, code lost:
        r4 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x029b, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x029c, code lost:
        r4 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x02a5, code lost:
        throw r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x02a6, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x02a8, code lost:
        kotlin.io.CloseableKt.closeFinally(r13, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x02ab, code lost:
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x02ca, code lost:
        throw new java.lang.RuntimeException("下载返回 " + r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x02cb, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x02cc, code lost:
        r1 = r0;
        r4 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x02d0, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x02d1, code lost:
        r4 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x02d8, code lost:
        r1 = r0;
        r3 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x02da, code lost:
        android.util.Log.w(com.xiaofan.bangfan.UpdateManager.TAG, "download attempt " + r10 + " failed: " + r1.getMessage());
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x0304, code lost:
        if (r0.exists() != false) goto L172;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x0310, code lost:
        r0 = r1.getMessage();
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0314, code lost:
        if (r0 != null) goto L176;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x0316, code lost:
        r8 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x0324, code lost:
        if (kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r0, (java.lang.CharSequence) "416", false, 2, (java.lang.Object) null) == true) goto L178;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x0326, code lost:
        r12 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x0328, code lost:
        r8 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x0329, code lost:
        r12 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x032a, code lost:
        if (r12 != false) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x032c, code lost:
        r0.delete();
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x0333, code lost:
        java.lang.Thread.sleep(r10 * 800);
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x0336, code lost:
        if (r4 != null) goto L187;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x0338, code lost:
        r4.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x033d, code lost:
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x0347, code lost:
        throw r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00dc, code lost:
        throw new java.lang.RuntimeException("下载重定向异常");
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00e0, code lost:
        r4 = r0.getResponseCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00e7, code lost:
        if (200 > r4) goto L166;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00e9, code lost:
        if (r4 >= 300) goto L166;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00eb, code lost:
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00ed, code lost:
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00ee, code lost:
        if (r0 == false) goto L164;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00f6, code lost:
        if (r4 != 206) goto L160;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00f8, code lost:
        r4 = r8;
        r19 = kotlin.ranges.RangesKt.coerceAtLeast(r0.getContentLengthLong(), 0L) + r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0107, code lost:
        if (r8 <= 0) goto L163;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0109, code lost:
        r0.delete();
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x010c, code lost:
        r4 = 0;
        r19 = kotlin.ranges.RangesKt.coerceAtLeast(r0.getContentLengthLong(), 0L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0118, code lost:
        r0 = new kotlin.jvm.internal.Ref.LongRef();
        r0.element = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0124, code lost:
        if (r4 != 206) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0126, code lost:
        r13 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0128, code lost:
        r13 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0129, code lost:
        r13 = new java.io.FileOutputStream(r0, r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x012f, code lost:
        r0 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x013b, code lost:
        r4 = r0.getInputStream();
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x013e, code lost:
        r0 = r4;
        r5 = new byte[65536];
        r24 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x014a, code lost:
        r26 = r0.read(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x014f, code lost:
        r26 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0155, code lost:
        if (r26 <= 0) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0157, code lost:
        r27 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x015a, code lost:
        r0.write(r5, 0, r26);
        r28 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0161, code lost:
        r29 = r8;
        r9 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0166, code lost:
        r0.element += r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0172, code lost:
        if ((java.lang.System.currentTimeMillis() - r24) <= 150) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0180, code lost:
        r36.invoke(java.lang.Long.valueOf(r0.element), java.lang.Long.valueOf(r19));
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0187, code lost:
        r24 = java.lang.System.currentTimeMillis();
        r7 = r9;
        r0 = r26;
        r6 = r27;
        r5 = r28;
        r8 = r29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0193, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0195, code lost:
        r7 = r9;
        r0 = r26;
        r6 = r27;
        r5 = r28;
        r8 = r29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x01a1, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01a4, code lost:
        r5 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01ad, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01ae, code lost:
        r5 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01bc, code lost:
        r27 = r6;
        r29 = r8;
        r9 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01c5, code lost:
        r0.flush();
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x01cc, code lost:
        kotlin.io.CloseableKt.closeFinally(r4, null);
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x01d2, code lost:
        kotlin.io.CloseableKt.closeFinally(r13, null);
        r36.invoke(java.lang.Long.valueOf(r0.element), java.lang.Long.valueOf(r19));
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x01e6, code lost:
        if (r34 <= 0) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x01e8, code lost:
        r6 = r34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x01eb, code lost:
        r6 = r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01ed, code lost:
        r13 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01f0, code lost:
        if (r13 <= 0) goto L130;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01f8, code lost:
        if (r0.length() != r13) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0227, code lost:
        throw new java.lang.RuntimeException("下载不完整（" + r0.length() + "/" + r13 + " 字节），将续传重试");
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x022c, code lost:
        if (r33.exists() == false) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x022e, code lost:
        r33.delete();
     */
    /* JADX WARN: Removed duplicated region for block: B:155:0x032c A[Catch: all -> 0x02d3, TRY_LEAVE, TryCatch #15 {all -> 0x02d3, blocks: (B:6:0x002f, B:8:0x0035, B:11:0x0041, B:13:0x007a, B:14:0x0098, B:21:0x00ac, B:26:0x00b8, B:28:0x00bd, B:36:0x00cc, B:37:0x00d5, B:38:0x00dc, B:143:0x02da, B:145:0x0306, B:147:0x0310, B:149:0x0316, B:155:0x032c, B:157:0x0333, B:165:0x0347), top: B:185:0x002f, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:191:0x034a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void downloadFile(java.lang.String r32, java.io.File r33, long r34, kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> r36) {
        /*
            Method dump skipped, instructions count: 864
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.UpdateManager.downloadFile(java.lang.String, java.io.File, long, kotlin.jvm.functions.Function2):void");
    }

    private final boolean canInstall(Context ctx) {
        return Build.VERSION.SDK_INT < 26 || ctx.getPackageManager().canRequestPackageInstalls();
    }

    private final void installOrRequestPermission(Activity activity, File file) {
        if (!file.exists() || file.length() <= 0) {
            Toast.makeText(activity, "安装包损坏，请重新下载", 1).show();
        } else if (!canInstall(activity)) {
            Toast.makeText(activity, "请先允许小翻安装应用，返回后会自动继续", 1).show();
            try {
                Uri uri = Uri.parse("package:" + activity.getPackageName());
                activity.startActivityForResult(new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES", uri), REQ_UNKNOWN_INSTALL);
            } catch (Throwable th) {
                try {
                    activity.startActivity(new Intent("android.settings.SECURITY_SETTINGS"));
                } catch (Throwable th2) {
                }
            }
        } else {
            doInstall(activity, file);
        }
    }

    private final void doInstall(Context ctx, File file) {
        try {
            Uri uri = FileProvider.getUriForFile(ctx, ctx.getPackageName() + ".fileprovider", file);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            intent.addFlags(268435457);
            ctx.startActivity(intent);
        } catch (Throwable th) {
            Log.w(TAG, "install failed", th);
            Toast.makeText(ctx, "无法调起安装：" + th.getMessage(), 1).show();
        }
    }

    public final void resumePendingInstall(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        File f = pendingInstall;
        if (f != null && canInstall(activity)) {
            doInstall(activity, f);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void showSourceDialog$default(UpdateManager updateManager, Activity activity, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = null;
        }
        updateManager.showSourceDialog(activity, function0);
    }

    public final void showSourceDialog(final Activity activity, final Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        LinearLayout container = new LinearLayout(activity);
        container.setOrientation(1);
        container.setPadding(UiKit.INSTANCE.dp(activity, 20.0f), UiKit.INSTANCE.dp(activity, 8.0f), UiKit.INSTANCE.dp(activity, 20.0f), 0);
        TextView $this$showSourceDialog_u24lambda_u2426 = new TextView(activity);
        $this$showSourceDialog_u24lambda_u2426.setText("填写 GitHub 仓库，格式：用户名/仓库名\n例如：your-name/XiaoFan");
        final EditText $this$showSourceDialog_u24lambda_u2427 = new EditText(activity);
        $this$showSourceDialog_u24lambda_u2427.setHint("用户名/仓库名");
        $this$showSourceDialog_u24lambda_u2427.setText(INSTANCE.sourceSlug(activity));
        container.addView($this$showSourceDialog_u24lambda_u2426);
        container.addView($this$showSourceDialog_u24lambda_u2427);
        new AlertDialog.Builder(activity).setTitle("更新源（GitHub 仓库）").setView(container).setPositiveButton("保存", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                UpdateManager.showSourceDialog$lambda$28($this$showSourceDialog_u24lambda_u2427, activity, function0, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showSourceDialog$lambda$28(EditText input, Activity activity, Function0 $onSaved, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(input, "$input");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        String raw = StringsKt.trimEnd(StringsKt.trim((CharSequence) input.getText().toString()).toString(), '/');
        if (StringsKt.contains$default((CharSequence) raw, '/', false, 2, (Object) null)) {
            String owner = StringsKt.trim((CharSequence) StringsKt.substringBefore$default(raw, '/', (String) null, 2, (Object) null)).toString();
            String repo = StringsKt.substringBefore$default(StringsKt.trim((CharSequence) StringsKt.substringAfter$default(raw, '/', (String) null, 2, (Object) null)).toString(), '/', (String) null, 2, (Object) null);
            AppPrefs.INSTANCE.setUpdateSource(activity, owner, repo);
            lastAutoAt = 0L;
            Toast.makeText(activity, "已保存更新源 " + owner + "/" + repo, 0).show();
            if ($onSaved != null) {
                $onSaved.invoke();
                return;
            }
            return;
        }
        Toast.makeText(activity, "格式应为：用户名/仓库名", 1).show();
    }
}
