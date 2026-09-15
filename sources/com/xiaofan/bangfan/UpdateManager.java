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
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
/* compiled from: UpdateManager.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u00010B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\fJ\u000e\u0010\u001b\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015J\u0018\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u0011H\u0002J2\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020\u00112\u0018\u0010!\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00170\"H\u0002J\u0012\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0018\u0010%\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u0011H\u0002J\u000e\u0010&\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010'\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J \u0010(\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0010\b\u0002\u0010)\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010*J \u0010+\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020\nH\u0002J\u000e\u0010.\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015J\u0018\u0010/\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010,\u001a\u00020$H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/xiaofan/bangfan/UpdateManager;", "", "()V", "CONNECT_TIMEOUT", "", "MIN_AUTO_INTERVAL_MS", "", "READ_TIMEOUT", "REQ_UNKNOWN_INSTALL", "TAG", "", "checking", "", "lastAutoAt", "main", "Landroid/os/Handler;", "pendingInstall", "Ljava/io/File;", "updateDialogShowing", "canInstall", "ctx", "Landroid/content/Context;", "checkAndPrompt", "", "activity", "Landroid/app/Activity;", "silent", "currentVersion", "doInstall", "file", "downloadFile", "urlStr", "target", "onProgress", "Lkotlin/Function2;", "fetchLatest", "Lcom/xiaofan/bangfan/UpdateManager$Release;", "installOrRequestPermission", "onEnterApp", "resumePendingInstall", "showSourceDialog", "onSaved", "Lkotlin/Function0;", "showUpdateDialog", "rel", "cur", "sourceSlug", "startDownload", "Release", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
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
                d.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda3
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
        new Thread(new Runnable() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda4
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
            main.post(new Runnable() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda8
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
        main.post(new Runnable() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda9
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
                return;
            }
            return;
        }
        INSTANCE.showUpdateDialog(activity, $rel, cur);
    }

    private final void showUpdateDialog(final Activity activity, final Release rel, String cur) {
        if (updateDialogShowing) {
            return;
        }
        NetState.Kind netKind = NetState.INSTANCE.kind(activity);
        String msg = NetState.INSTANCE.composeUpdateMessage(rel.getVersion(), cur, rel.getSize(), netKind, rel.getNotes(), rel.getTagName(), rel.getAssetName());
        updateDialogShowing = true;
        AlertDialog dialog = new AlertDialog.Builder(activity).setTitle("发现新版本 V" + rel.getVersion()).setMessage(msg).setPositiveButton("立即更新", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda10
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                UpdateManager.showUpdateDialog$lambda$5(activity, rel, dialogInterface, i);
            }
        }).setNegativeButton("暂不更新", (DialogInterface.OnClickListener) null).setCancelable(true).show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda1
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
        INSTANCE.startDownload(activity, rel);
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
        final ProgressBar $this$startDownload_u24lambda_u2412 = new ProgressBar(activity, null, 16842872);
        $this$startDownload_u24lambda_u2412.setMax(100);
        $this$startDownload_u24lambda_u2412.setProgress(0);
        final TextView $this$startDownload_u24lambda_u2413 = new TextView(activity);
        $this$startDownload_u24lambda_u2413.setGravity(17);
        $this$startDownload_u24lambda_u2413.setText("准备下载…");
        box.addView($this$startDownload_u24lambda_u2412);
        LinearLayout.LayoutParams it = new LinearLayout.LayoutParams(-1, -2);
        it.topMargin = UiKit.INSTANCE.dp(activity, 8.0f);
        Unit unit = Unit.INSTANCE;
        box.addView($this$startDownload_u24lambda_u2413, it);
        final AlertDialog dialog = new AlertDialog.Builder(activity).setTitle("正在下载 V" + rel.getVersion()).setView(box).setCancelable(false).show();
        new Thread(new Runnable() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                UpdateManager.startDownload$lambda$18(activity, rel, $this$startDownload_u24lambda_u2412, $this$startDownload_u24lambda_u2413, dialog);
            }
        }, "xf-update-download").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startDownload$lambda$18(final Activity activity, final Release rel, ProgressBar bar, TextView pct, final AlertDialog $dialog) {
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
            if (target.exists()) {
                target.delete();
            }
            INSTANCE.downloadFile(rel.getApkUrl(), target, new UpdateManager$startDownload$2$1(bar, pct));
            main.post(new Runnable() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    UpdateManager.startDownload$lambda$18$lambda$15($dialog, target, activity);
                }
            });
        } catch (Throwable th) {
            Log.w(TAG, "download failed", th);
            main.post(new Runnable() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    UpdateManager.startDownload$lambda$18$lambda$17($dialog, activity, th, rel);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startDownload$lambda$18$lambda$15(AlertDialog $dialog, File target, Activity activity) {
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
    public static final void startDownload$lambda$18$lambda$17(AlertDialog $dialog, final Activity activity, Throwable th, final Release rel) {
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
        title.setMessage(message + "\n可换网络后重试。").setPositiveButton("重试", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                UpdateManager.startDownload$lambda$18$lambda$17$lambda$16(activity, rel, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startDownload$lambda$18$lambda$17$lambda$16(Activity activity, Release rel, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(rel, "$rel");
        INSTANCE.startDownload(activity, rel);
    }

    private final void downloadFile(String urlStr, File target, Function2<? super Long, ? super Long, Unit> function2) {
        int code;
        URLConnection openConnection = new URL(urlStr).openConnection();
        Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
        HttpURLConnection $this$downloadFile_u24lambda_u2419 = (HttpURLConnection) openConnection;
        $this$downloadFile_u24lambda_u2419.setConnectTimeout(CONNECT_TIMEOUT);
        $this$downloadFile_u24lambda_u2419.setReadTimeout(READ_TIMEOUT);
        boolean z = true;
        $this$downloadFile_u24lambda_u2419.setInstanceFollowRedirects(true);
        $this$downloadFile_u24lambda_u2419.setRequestProperty("User-Agent", "xiaofan-bangfan-update");
        try {
            code = $this$downloadFile_u24lambda_u2419.getResponseCode();
            if (200 > code || code >= 300) {
                z = false;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            if (!z) {
                throw new RuntimeException("下载返回 " + code);
            }
            long total = RangesKt.coerceAtLeast($this$downloadFile_u24lambda_u2419.getContentLengthLong(), 0L);
            long done = 0;
            InputStream inputStream = $this$downloadFile_u24lambda_u2419.getInputStream();
            try {
                InputStream input = inputStream;
                boolean z2 = false;
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(target);
                    try {
                        FileOutputStream out = fileOutputStream;
                        byte[] buf = new byte[65536];
                        long lastCb = 0;
                        while (true) {
                            int n = input.read(buf);
                            if (n <= 0) {
                                out.flush();
                                Unit unit = Unit.INSTANCE;
                                CloseableKt.closeFinally(fileOutputStream, null);
                                Unit unit2 = Unit.INSTANCE;
                                CloseableKt.closeFinally(inputStream, null);
                                function2.invoke(Long.valueOf(done), Long.valueOf(total));
                                try {
                                    $this$downloadFile_u24lambda_u2419.disconnect();
                                    return;
                                } catch (Throwable th2) {
                                    return;
                                }
                            }
                            InputStream input2 = input;
                            try {
                                out.write(buf, 0, n);
                                boolean z3 = z2;
                                done += n;
                                try {
                                    if (System.currentTimeMillis() - lastCb > 120) {
                                        function2.invoke(Long.valueOf(done), Long.valueOf(total));
                                        lastCb = System.currentTimeMillis();
                                        z2 = z3;
                                        input = input2;
                                    } else {
                                        z2 = z3;
                                        input = input2;
                                    }
                                } catch (Throwable th3) {
                                    throw th3;
                                }
                            } catch (Throwable th4) {
                                throw th4;
                            }
                            throw th3;
                        }
                    } catch (Throwable th5) {
                        throw th5;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    throw th;
                }
            } catch (Throwable th7) {
                th = th7;
            }
        } catch (Throwable th8) {
            th = th8;
            Throwable th9 = th;
            try {
                $this$downloadFile_u24lambda_u2419.disconnect();
            } catch (Throwable th10) {
            }
            throw th9;
        }
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
        TextView $this$showSourceDialog_u24lambda_u2424 = new TextView(activity);
        $this$showSourceDialog_u24lambda_u2424.setText("填写 GitHub 仓库，格式：用户名/仓库名\n例如：your-name/XiaoFan");
        final EditText $this$showSourceDialog_u24lambda_u2425 = new EditText(activity);
        $this$showSourceDialog_u24lambda_u2425.setHint("用户名/仓库名");
        $this$showSourceDialog_u24lambda_u2425.setText(INSTANCE.sourceSlug(activity));
        container.addView($this$showSourceDialog_u24lambda_u2424);
        container.addView($this$showSourceDialog_u24lambda_u2425);
        new AlertDialog.Builder(activity).setTitle("更新源（GitHub 仓库）").setView(container).setPositiveButton("保存", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda5
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                UpdateManager.showSourceDialog$lambda$26($this$showSourceDialog_u24lambda_u2425, activity, function0, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showSourceDialog$lambda$26(EditText input, Activity activity, Function0 $onSaved, DialogInterface dialogInterface, int i) {
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
