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
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.DebugKt;
/* compiled from: UpdateManager.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u00018B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\u00112\u0006\u0010\u0016\u001a\u00020\nH\u0002J\u0010\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\fJ\u000e\u0010\u001f\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0019J\u0018\u0010 \u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u0013H\u0002JN\u0010\"\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020\n2\u0006\u0010$\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u00062\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001b0'2\u0018\u0010(\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u001b0)H\u0002J\u0012\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u0010,\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\u0013H\u0002J\u000e\u0010-\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010.\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ \u0010/\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0010\b\u0002\u00100\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u000101J(\u00102\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u00103\u001a\u00020+2\u0006\u00104\u001a\u00020\n2\u0006\u00105\u001a\u00020\fH\u0002J\u000e\u00106\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0019J\u0018\u00107\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u00103\u001a\u00020+H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/xiaofan/bangfan/UpdateManager;", "", "()V", "CONNECT_TIMEOUT", "", "MIN_AUTO_INTERVAL_MS", "", "READ_TIMEOUT", "REQ_UNKNOWN_INSTALL", "TAG", "", "checking", "", "lastAutoAt", "main", "Landroid/os/Handler;", "mirrorPrefixes", "", "pendingInstall", "Ljava/io/File;", "updateDialogShowing", "buildCandidates", "assetUrl", "canInstall", "ctx", "Landroid/content/Context;", "checkAndPrompt", "", "activity", "Landroid/app/Activity;", "silent", "currentVersion", "doInstall", "file", "downloadFile", "urlStr", "target", "expectedSize", "onStatus", "Lkotlin/Function1;", "onProgress", "Lkotlin/Function2;", "fetchLatest", "Lcom/xiaofan/bangfan/UpdateManager$Release;", "installOrRequestPermission", "onEnterApp", "resumePendingInstall", "showSourceDialog", "onSaved", "Lkotlin/Function0;", "showUpdateDialog", "rel", "cur", DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "sourceSlug", "startDownload", "Release", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
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
    private static final List<String> mirrorPrefixes = CollectionsKt.listOf((Object[]) new String[]{"https://gh-proxy.com/", "https://ghfast.top/", "https://ghproxy.net/", "https://mirror.ghproxy.com/", "https://gh.llkk.cc/", "https://github.moeyy.xyz/"});

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
                d.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda5
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
        new Thread(new Runnable() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda6
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
                UpdateManager.startDownload$lambda$20(activity, rel, $this$startDownload_u24lambda_u2415, $this$startDownload_u24lambda_u2414, dialog);
            }
        }, "xf-update-download").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startDownload$lambda$20(final Activity activity, final Release rel, TextView pct, ProgressBar bar, final AlertDialog $dialog) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(rel, "$rel");
        Intrinsics.checkNotNullParameter(pct, "$pct");
        Intrinsics.checkNotNullParameter(bar, "$bar");
        try {
            File dir = activity.getExternalFilesDir("updates");
            if (dir == null) {
                dir = activity.getFilesDir();
            }
            if (!dir.exists()) {
                dir.mkdirs();
            }
            final File target = new File(dir, "xiaofan_v" + rel.getVersion() + ".apk");
            INSTANCE.downloadFile(rel.getApkUrl(), target, rel.getSize(), new UpdateManager$startDownload$2$1(pct), new UpdateManager$startDownload$2$2(bar, pct));
            main.post(new Runnable() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    UpdateManager.startDownload$lambda$20$lambda$17($dialog, target, activity);
                }
            });
        } catch (Throwable th) {
            Log.w(TAG, "download failed", th);
            main.post(new Runnable() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda8
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
        title.setMessage(message + "\n已自动尝试官方直连与多个国内加速节点并支持断点续传，可换 WiFi/移动数据后点重试继续下载。").setPositiveButton("重试", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda4
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

    private final List<String> buildCandidates(String assetUrl) {
        ArrayList out = new ArrayList();
        for (String p : mirrorPrefixes) {
            out.add(p + assetUrl);
        }
        out.add(assetUrl);
        return out;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x028e, code lost:
        if (r8 <= 0) goto L309;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0290, code lost:
        r5.delete();
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0294, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0295, code lost:
        r6 = r0;
        r7 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x02a8, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x02a9, code lost:
        r8 = r4;
        r20 = r5;
        r1 = r18;
        r4 = r10;
        r7 = r0;
        r2 = 1;
        r21 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x02bf, code lost:
        r14 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x02c1, code lost:
        r0.element = kotlin.ranges.RangesKt.coerceAtLeast(r0.getContentLengthLong(), 0L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x02c7, code lost:
        r11 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x02cb, code lost:
        if (r1 <= r14) goto L145;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x02cd, code lost:
        r0.element = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x02cf, code lost:
        r7 = new kotlin.jvm.internal.Ref.LongRef();
        r7.element = r11;
        r7 = new kotlin.jvm.internal.Ref.LongRef();
        r11 = r32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x02e3, code lost:
        if (r11 != 206) goto L289;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x02e5, code lost:
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x02e7, code lost:
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x02e8, code lost:
        r7 = new java.io.FileOutputStream(r5, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x02ed, code lost:
        r12 = r7;
        r10 = r0.getInputStream();
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x02fa, code lost:
        r0 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x0303, code lost:
        r8 = new byte[65536];
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x0305, code lost:
        r9 = r0.read(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x030a, code lost:
        if (r9 <= 0) goto L204;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x030c, code lost:
        r39 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x030f, code lost:
        r12.write(r8, 0, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x0312, code lost:
        r25 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x0316, code lost:
        r40 = r4;
        r41 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x031c, code lost:
        r7.element += r9;
        r4 = java.lang.System.currentTimeMillis();
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x032a, code lost:
        if ((r4 - r7.element) <= 150) goto L169;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x032c, code lost:
        r12 = r8;
        r50.invoke(java.lang.Long.valueOf(r7.element), java.lang.Long.valueOf(r0.element));
        r7.element = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x0340, code lost:
        r8 = r12;
        r12 = r25;
        r11 = r39;
        r4 = r40;
        r5 = r41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x034a, code lost:
        r12 = r25;
        r11 = r39;
        r4 = r40;
        r5 = r41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x0356, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x0357, code lost:
        r9 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x036f, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x0370, code lost:
        r9 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x038c, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x038d, code lost:
        r9 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x03ab, code lost:
        r40 = r4;
        r41 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x03b6, code lost:
        r12.flush();
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x03bd, code lost:
        kotlin.io.CloseableKt.closeFinally(r10, null);
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x03c3, code lost:
        kotlin.io.CloseableKt.closeFinally(r7, null);
        r50.invoke(java.lang.Long.valueOf(r7.element), java.lang.Long.valueOf(r0.element));
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x03d9, code lost:
        if (r1 <= 0) goto L269;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x03db, code lost:
        r7 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x03dd, code lost:
        r7 = r0.element;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x03df, code lost:
        r11 = r7;
        r21 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x03e4, code lost:
        if (r11 <= 0) goto L218;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x03ec, code lost:
        if (r41.length() != r11) goto L261;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x041b, code lost:
        throw new java.lang.RuntimeException("下载不完整（" + r41.length() + "/" + r11 + " 字节），将换节点续传");
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x041c, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x041d, code lost:
        r6 = r0;
        r7 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x0430, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x0431, code lost:
        r1 = r18;
        r4 = r10;
        r7 = r0;
        r8 = r40;
        r20 = r41;
        r2 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x0444, code lost:
        if (r46.exists() == false) goto L222;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x0446, code lost:
        r46.delete();
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0451, code lost:
        if (r41.renameTo(r46) != false) goto L247;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x0453, code lost:
        r36 = r8;
        r35 = r20;
        r4 = r10;
        r20 = r41;
        r38 = r11;
        r2 = 1;
        r1 = r18;
        r18 = r29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x0473, code lost:
        kotlin.io.FilesKt.copyTo$default(r41, r46, true, 0, 4, null);
        r20.delete();
        r1 = r1;
        r20 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x047a, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x047b, code lost:
        r7 = r0;
        r8 = r40;
        r1 = r1;
        r2 = r2;
        r20 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x0481, code lost:
        r1 = r18;
        r35 = r20;
        r4 = r10;
        r18 = r29;
        r36 = r8;
        r2 = 1;
        r20 = r41;
        r38 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x04b5, code lost:
        android.util.Log.i(r40, "download ok via " + r4 + " size=" + r46.length());
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x04b9, code lost:
        r0.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x04ce, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x04cf, code lost:
        r8 = r40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x04d3, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x04d4, code lost:
        r6 = r0;
        r7 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x04e5, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x04e6, code lost:
        r20 = r41;
        r1 = r18;
        r4 = r10;
        r8 = r40;
        r2 = 1;
        r7 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x04f3, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x04f4, code lost:
        r1 = r18;
        r4 = r10;
        r8 = r40;
        r20 = r41;
        r2 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x0500, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:190:0x0501, code lost:
        r6 = r0;
        r7 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:191:0x0514, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x0515, code lost:
        r1 = r18;
        r4 = r10;
        r8 = r40;
        r20 = r41;
        r2 = 1;
        r21 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x0522, code lost:
        r7 = r0;
        r1 = r1;
        r2 = r2;
        r8 = r8;
        r20 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:194:0x0526, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x0527, code lost:
        r9 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x053f, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:197:0x0540, code lost:
        r9 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x0557, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:199:0x0558, code lost:
        r9 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:200:0x0570, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:201:0x0571, code lost:
        r9 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x0588, code lost:
        throw r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:203:0x0589, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:205:0x058b, code lost:
        kotlin.io.CloseableKt.closeFinally(r10, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:206:0x058e, code lost:
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x058f, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:208:0x0590, code lost:
        r9 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:209:0x0592, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:210:0x0593, code lost:
        r9 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:211:0x05a8, code lost:
        throw r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:212:0x05a9, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:214:0x05ab, code lost:
        kotlin.io.CloseableKt.closeFinally(r7, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:215:0x05ae, code lost:
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x05af, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:217:0x05b0, code lost:
        r8 = r4;
        r20 = r5;
        r21 = 0;
        r1 = r18;
        r4 = r10;
        r2 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:218:0x05bb, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:219:0x05bc, code lost:
        r6 = r0;
        r7 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:220:0x05cf, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:221:0x05d0, code lost:
        r8 = r4;
        r20 = r5;
        r1 = r18;
        r4 = r10;
        r2 = 1;
        r21 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:222:0x05da, code lost:
        r7 = r0;
        r1 = r1;
        r2 = r2;
        r8 = r8;
        r20 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:224:0x0610, code lost:
        throw new java.lang.RuntimeException(r10 + " 返回了网页而非安装包");
     */
    /* JADX WARN: Code restructure failed: missing block: B:226:0x0647, code lost:
        throw new java.lang.RuntimeException(r10 + " 返回 " + r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:227:0x0648, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:228:0x0649, code lost:
        r6 = r0;
        r7 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:229:0x0654, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:230:0x0655, code lost:
        r7 = r0;
        r1 = r1;
        r2 = r2;
        r8 = r8;
        r20 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:231:0x0659, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:232:0x065a, code lost:
        r6 = r0;
        r7 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:233:0x066d, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:234:0x066e, code lost:
        r8 = r4;
        r20 = r5;
        r33 = r14;
        r34 = r15;
        r1 = r18;
        r4 = r10;
        r2 = true;
        r21 = 0;
        r7 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:258:0x0778, code lost:
        if (r20.length() > r21) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:259:0x077a, code lost:
        r0 = r6.getMessage();
     */
    /* JADX WARN: Code restructure failed: missing block: B:260:0x077e, code lost:
        if (r0 != null) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:261:0x0780, code lost:
        r12 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:262:0x078d, code lost:
        if (kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r0, (java.lang.CharSequence) "416", false, 2, (java.lang.Object) null) == r2) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:263:0x078f, code lost:
        r11 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:264:0x0791, code lost:
        r12 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:265:0x0792, code lost:
        r11 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:266:0x0793, code lost:
        if (r11 != false) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:267:0x0795, code lost:
        r20.delete();
     */
    /* JADX WARN: Code restructure failed: missing block: B:269:0x0799, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:270:0x079a, code lost:
        r6 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:274:0x07a9, code lost:
        r7.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:281:0x07c2, code lost:
        throw r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x01d3, code lost:
        r7 = r0.getResponseCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x01da, code lost:
        if (200 > r7) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x01dc, code lost:
        if (r7 >= 300) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x01de, code lost:
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x01e0, code lost:
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01e1, code lost:
        if (r0 == false) goto L328;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01e3, code lost:
        r0 = r0.getContentType();
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x01e7, code lost:
        if (r0 == null) goto L127;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x01e9, code lost:
        r0 = r0.toLowerCase(java.util.Locale.ROOT);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, "toLowerCase(...)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x01f5, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01f6, code lost:
        r6 = r0;
        r7 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0205, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0206, code lost:
        r8 = r4;
        r20 = r5;
        r33 = r14;
        r34 = r15;
        r1 = r18;
        r4 = r10;
        r7 = r0;
        r2 = 1;
        r21 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x021a, code lost:
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x021b, code lost:
        if (r0 != null) goto L130;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x021d, code lost:
        r0 = "";
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x021f, code lost:
        r28 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0221, code lost:
        r20 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0232, code lost:
        if (kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r28, (java.lang.CharSequence) "text/html", false, 2, (java.lang.Object) null) != false) goto L318;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0234, code lost:
        r0 = new kotlin.jvm.internal.Ref.LongRef();
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x023e, code lost:
        if (r7 != 206) goto L306;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0240, code lost:
        r32 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0248, code lost:
        r33 = r14;
        r34 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x024e, code lost:
        r0.element = kotlin.ranges.RangesKt.coerceAtLeast(r0.getContentLengthLong(), 0L) + r8;
        r11 = r8;
        r14 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x025b, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x025c, code lost:
        r6 = r0;
        r7 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x026f, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0270, code lost:
        r33 = r14;
        r34 = r15;
        r8 = r4;
        r20 = r5;
        r1 = r18;
        r4 = r10;
        r7 = r0;
        r2 = 1;
        r21 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0284, code lost:
        r32 = r7;
        r33 = r14;
        r34 = r15;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:267:0x0795 A[Catch: all -> 0x0799, TRY_LEAVE, TryCatch #53 {all -> 0x0799, blocks: (B:257:0x0772, B:259:0x077a, B:261:0x0780, B:267:0x0795), top: B:349:0x0772 }] */
    /* JADX WARN: Removed duplicated region for block: B:279:0x07b3 A[LOOP:0: B:3:0x0033->B:279:0x07b3, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:302:0x07a9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:329:0x07d9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:349:0x0772 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:378:0x07bf A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v44 */
    /* JADX WARN: Type inference failed for: r1v58 */
    /* JADX WARN: Type inference failed for: r20v20 */
    /* JADX WARN: Type inference failed for: r20v44 */
    /* JADX WARN: Type inference failed for: r20v59 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v43 */
    /* JADX WARN: Type inference failed for: r2v46 */
    /* JADX WARN: Type inference failed for: r2v55 */
    /* JADX WARN: Type inference failed for: r2v57 */
    /* JADX WARN: Type inference failed for: r2v58 */
    /* JADX WARN: Type inference failed for: r2v59 */
    /* JADX WARN: Type inference failed for: r2v60 */
    /* JADX WARN: Type inference failed for: r2v61 */
    /* JADX WARN: Type inference failed for: r2v62 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v35 */
    /* JADX WARN: Type inference failed for: r8v7, types: [long] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void downloadFile(java.lang.String r45, java.io.File r46, long r47, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r49, kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> r50) {
        /*
            Method dump skipped, instructions count: 2015
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.UpdateManager.downloadFile(java.lang.String, java.io.File, long, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function2):void");
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
