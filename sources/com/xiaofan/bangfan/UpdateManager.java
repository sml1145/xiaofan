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
import com.xiaofan.bangfan.UpdateDownloadCore;
import com.xiaofan.bangfan.UpdateManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.DebugKt;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: UpdateManager.kt */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0012\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001LB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\fJ*\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u0004H\u0002J\u000e\u0010!\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015J\u0018\u0010\"\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\u0011H\u0002JN\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\u00112\u0006\u0010'\u001a\u00020\u00062\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00170)2\u0018\u0010*\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00170+H\u0002JF\u0010,\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\u00112\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00170)2\u0018\u0010*\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00170+H\u0002J0\u0010-\u001a\u00020\u00172\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\n2\u0006\u00101\u001a\u00020\u00062\u0006\u00102\u001a\u00020\u00062\u0006\u00103\u001a\u000204H\u0002J\u0012\u00105\u001a\u0004\u0018\u0001062\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0012\u00107\u001a\u0004\u0018\u0001062\u0006\u00108\u001a\u00020\nH\u0002J\u0018\u00109\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010#\u001a\u00020\u0011H\u0002J\u000e\u0010:\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u001e\u0010;\u001a\b\u0012\u0004\u0012\u00020=0<2\u0006\u00100\u001a\u00020\n2\u0006\u0010'\u001a\u00020\u0006H\u0002J \u0010>\u001a\u00020=2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\n2\u0006\u0010'\u001a\u00020\u0006H\u0002J\u000e\u0010?\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J \u0010@\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0010\b\u0002\u0010A\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010BJ(\u0010C\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010D\u001a\u0002062\u0006\u0010E\u001a\u00020\n2\u0006\u0010F\u001a\u00020\fH\u0002J\u000e\u0010G\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015J\u0018\u0010H\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010D\u001a\u000206H\u0002J\u0014\u0010I\u001a\u00020\u0017*\u00020\u00112\u0006\u0010J\u001a\u00020KH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006M"}, d2 = {"Lcom/xiaofan/bangfan/UpdateManager;", "", "()V", "CONNECT_TIMEOUT", "", "MIN_AUTO_INTERVAL_MS", "", "READ_TIMEOUT", "REQ_UNKNOWN_INSTALL", "TAG", "", "checking", "", "lastAutoAt", "main", "Landroid/os/Handler;", "pendingInstall", "Ljava/io/File;", "updateDialogShowing", "canInstall", "ctx", "Landroid/content/Context;", "checkAndPrompt", "", "activity", "Landroid/app/Activity;", "silent", "connectFollowing", "Ljava/net/HttpURLConnection;", "url0", "rangeHeader", "connectMs", "readMs", "currentVersion", "doInstall", "file", "downloadFile", "urlStr", "target", "expectedSize", "onStatus", "Lkotlin/Function1;", "onProgress", "Lkotlin/Function2;", "downloadFileSingle", "fetchChunk", "node", "Lcom/xiaofan/bangfan/UpdateDownloadCore$Node;", "assetUrl", "start", "end", "raf", "Ljava/io/RandomAccessFile;", "fetchLatest", "Lcom/xiaofan/bangfan/UpdateManager$Release;", "fetchReleaseOnce", "apiUrl", "installOrRequestPermission", "onEnterApp", "probeAllNodes", "", "Lcom/xiaofan/bangfan/UpdateDownloadCore$NodeProbe;", "probeNode", "resumePendingInstall", "showSourceDialog", "onSaved", "Lkotlin/Function0;", "showUpdateDialog", "rel", "cur", DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "sourceSlug", "startDownload", "writeBytesSafe", "bits", "", "Release", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
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
        if (now - lastAutoAt < 15000) {
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
                d.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda10
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
        new Thread(new Runnable() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda11
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
            main.post(new Runnable() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda4
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
        main.post(new Runnable() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda5
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
        AlertDialog dialog = new AlertDialog.Builder(activity).setTitle("发现新版本 V" + rel.getVersion()).setMessage(msg).setPositiveButton("立即更新", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda14
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                UpdateManager.showUpdateDialog$lambda$5(activity, rel, dialogInterface, i);
            }
        }).setNegativeButton("暂不更新", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda15
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                UpdateManager.showUpdateDialog$lambda$6(activity, rel, dialogInterface, i);
            }
        }).setCancelable(true).show();
        if (auto) {
            dialog.setCanceledOnTouchOutside(true);
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnCancelListener
                public final void onCancel(DialogInterface dialogInterface) {
                    UpdateManager.showUpdateDialog$lambda$7(activity, rel, dialogInterface);
                }
            });
        }
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda2
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

    private final Release fetchLatest(Context ctx) {
        Release r;
        String slug = sourceSlug(ctx);
        String owner = StringsKt.trim((CharSequence) StringsKt.substringBefore$default(slug, '/', (String) null, 2, (Object) null)).toString();
        String repo = StringsKt.trim((CharSequence) StringsKt.substringAfter$default(slug, '/', (String) null, 2, (Object) null)).toString();
        String api = "https://api.github.com/repos/" + owner + "/" + repo + "/releases/latest";
        List candidates = CollectionsKt.mutableListOf(api);
        Iterable $this$filter$iv = UpdateDownloadCore.INSTANCE.getNodes();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            UpdateDownloadCore.Node it = (UpdateDownloadCore.Node) element$iv$iv;
            if (!it.isDirect()) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$forEach$iv = (List) destination$iv$iv;
        for (Object element$iv : $this$forEach$iv) {
            UpdateDownloadCore.Node it2 = (UpdateDownloadCore.Node) element$iv;
            candidates.add(it2.wrap(api));
        }
        ExecutorService pool = Executors.newCachedThreadPool();
        try {
            ExecutorCompletionService cs = new ExecutorCompletionService(pool);
            List $this$map$iv = candidates;
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                final String u = (String) item$iv$iv;
                destination$iv$iv2.add(cs.submit(new Callable() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda12
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        UpdateManager.Release fetchLatest$lambda$12$lambda$11;
                        fetchLatest$lambda$12$lambda$11 = UpdateManager.fetchLatest$lambda$12$lambda$11(u);
                        return fetchLatest$lambda$12$lambda$11;
                    }
                }));
            }
            List tasks = (List) destination$iv$iv2;
            long deadline = System.currentTimeMillis() + UpdateDownloadCore.PROBE_BUDGET_MS + AppPrefs.FALLBACK_READ_MS;
            int remaining = tasks.size();
            while (remaining > 0) {
                if (System.currentTimeMillis() >= deadline) {
                    break;
                }
                Future done = cs.poll(2L, TimeUnit.SECONDS);
                if (done != null) {
                    int remaining2 = remaining - 1;
                    try {
                        r = (Release) done.get();
                    } catch (Throwable th) {
                        r = null;
                    }
                    if (r != null) {
                        pool.shutdownNow();
                        return r;
                    }
                    remaining = remaining2;
                }
            }
            pool.shutdownNow();
            throw new RuntimeException("无法连接更新服务器（GitHub 与加速节点均不可达）");
        } catch (Throwable th2) {
            pool.shutdownNow();
            throw th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Release fetchLatest$lambda$12$lambda$11(String u) {
        Intrinsics.checkNotNullParameter(u, "$u");
        try {
            return INSTANCE.fetchReleaseOnce(u);
        } catch (Throwable th) {
            Log.w(TAG, "api candidate failed: " + u + " : " + th.getMessage());
            return null;
        }
    }

    private final Release fetchReleaseOnce(String apiUrl) {
        int code;
        JSONObject json;
        JSONArray arr;
        String bestUrl;
        String bestName;
        long bestSize;
        int i;
        String str;
        String bestUrl2;
        String str2;
        String str3;
        char c;
        String bestUrl3 = "toLowerCase(...)";
        String str4 = "";
        URLConnection openConnection = new URL(apiUrl).openConnection();
        Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
        HttpURLConnection conn = (HttpURLConnection) openConnection;
        conn.setConnectTimeout(UpdateDownloadCore.PROBE_CONNECT_MS);
        conn.setReadTimeout(UpdateDownloadCore.PROBE_READ_MS);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "xiaofan-bangfan-update");
        conn.setRequestProperty("Accept", "application/vnd.github+json");
        try {
            int code2 = conn.getResponseCode();
            if (code2 != 200) {
                throw new RuntimeException("HTTP " + code2);
            }
            InputStream inputStream = conn.getInputStream();
            Intrinsics.checkNotNullExpressionValue(inputStream, "getInputStream(...)");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charsets.UTF_8);
            BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
            BufferedReader it = bufferedReader;
            String body = TextStreamsKt.readText(it);
            CloseableKt.closeFinally(bufferedReader, null);
            char c2 = 2;
            if (StringsKt.contains$default((CharSequence) body, (CharSequence) "tag_name", false, 2, (Object) null)) {
                JSONObject json2 = new JSONObject(body);
                String optString = json2.optString("tag_name", "");
                String str5 = "name";
                if (StringsKt.isBlank(optString)) {
                    optString = json2.optString("name", "");
                }
                String tag = optString;
                Intrinsics.checkNotNull(tag);
                String obj = StringsKt.trim((CharSequence) StringsKt.trimStart(StringsKt.trim((CharSequence) tag).toString(), 'v', 'V')).toString();
                if (StringsKt.isBlank(obj)) {
                    obj = "0";
                }
                String version = obj;
                String optString2 = json2.optString("body", "");
                Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
                String notes = StringsKt.trim((CharSequence) optString2).toString();
                JSONArray arr2 = json2.optJSONArray("assets");
                String bestUrl4 = "";
                String bestName2 = "";
                long bestSize2 = 0;
                if (arr2 == null) {
                    code = code2;
                    json = json2;
                    arr = arr2;
                    bestUrl = bestUrl4;
                    bestName = bestName2;
                    bestSize = 0;
                } else {
                    String firstUrl = "";
                    String firstName = "";
                    long firstSize = 0;
                    int length = arr2.length();
                    int i2 = 0;
                    while (i2 < length) {
                        JSONObject a = arr2.optJSONObject(i2);
                        if (a == null) {
                            str2 = bestUrl3;
                            str3 = str4;
                            code = code2;
                            i = length;
                            json = json2;
                            arr = arr2;
                            str = str5;
                            bestUrl2 = bestUrl4;
                            c = c2;
                        } else {
                            String name = a.optString(str5, str4);
                            Intrinsics.checkNotNull(name);
                            i = length;
                            String lowerCase = name.toLowerCase(Locale.ROOT);
                            Intrinsics.checkNotNullExpressionValue(lowerCase, bestUrl3);
                            str = str5;
                            code = code2;
                            json = json2;
                            bestUrl2 = bestUrl4;
                            if (StringsKt.endsWith$default(lowerCase, ".apk", false, 2, (Object) null)) {
                                String url = a.optString("browser_download_url", str4);
                                Intrinsics.checkNotNull(url);
                                if (StringsKt.isBlank(url)) {
                                    str2 = bestUrl3;
                                    str3 = str4;
                                    arr = arr2;
                                    c = 2;
                                } else {
                                    if (StringsKt.isBlank(firstUrl)) {
                                        firstSize = a.optLong("size", 0L);
                                        firstName = name;
                                        firstUrl = url;
                                    }
                                    String low = name.toLowerCase(Locale.ROOT);
                                    Intrinsics.checkNotNullExpressionValue(low, bestUrl3);
                                    str2 = bestUrl3;
                                    str3 = str4;
                                    arr = arr2;
                                    if (!StringsKt.contains$default((CharSequence) low, (CharSequence) AppPrefs.DEFAULT_UPDATE_REPO, false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) low, (CharSequence) "bangfan", false, 2, (Object) null)) {
                                        c = 2;
                                        if (StringsKt.contains$default((CharSequence) name, (CharSequence) "小翻", false, 2, (Object) null)) {
                                        }
                                    }
                                    bestUrl4 = url;
                                    bestName2 = name;
                                    bestSize2 = a.optLong("size", 0L);
                                    break;
                                }
                            } else {
                                str2 = bestUrl3;
                                str3 = str4;
                                arr = arr2;
                                c = 2;
                            }
                        }
                        i2++;
                        c2 = c;
                        length = i;
                        str5 = str;
                        bestUrl4 = bestUrl2;
                        json2 = json;
                        code2 = code;
                        bestUrl3 = str2;
                        str4 = str3;
                        arr2 = arr;
                    }
                    code = code2;
                    json = json2;
                    arr = arr2;
                    if (StringsKt.isBlank(bestUrl4)) {
                        bestUrl4 = firstUrl;
                        bestName2 = firstName;
                        bestSize2 = firstSize;
                    }
                    bestUrl = bestUrl4;
                    bestName = bestName2;
                    bestSize = bestSize2;
                }
                if (StringsKt.isBlank(bestUrl)) {
                    throw new RuntimeException("最新 Release 没有上传 .apk 安装包资产");
                }
                Release release = new Release(version, notes, bestUrl, tag, bestName, bestSize);
                try {
                    conn.disconnect();
                } catch (Throwable th) {
                }
                return release;
            }
            throw new RuntimeException("不是有效的 Release 信息");
        } catch (Throwable th2) {
            try {
                conn.disconnect();
            } catch (Throwable th3) {
            }
            throw th2;
        }
    }

    private final void startDownload(final Activity activity, final Release rel) {
        LinearLayout box = new LinearLayout(activity);
        box.setOrientation(1);
        box.setPadding(UiKit.INSTANCE.dp(activity, 20.0f), UiKit.INSTANCE.dp(activity, 12.0f), UiKit.INSTANCE.dp(activity, 20.0f), 0);
        final ProgressBar $this$startDownload_u24lambda_u2418 = new ProgressBar(activity, null, 16842872);
        $this$startDownload_u24lambda_u2418.setMax(100);
        $this$startDownload_u24lambda_u2418.setProgress(0);
        final TextView $this$startDownload_u24lambda_u2419 = new TextView(activity);
        $this$startDownload_u24lambda_u2419.setGravity(17);
        $this$startDownload_u24lambda_u2419.setText("准备下载…");
        box.addView($this$startDownload_u24lambda_u2418);
        LinearLayout.LayoutParams it = new LinearLayout.LayoutParams(-1, -2);
        it.topMargin = UiKit.INSTANCE.dp(activity, 8.0f);
        Unit unit = Unit.INSTANCE;
        box.addView($this$startDownload_u24lambda_u2419, it);
        final AlertDialog dialog = new AlertDialog.Builder(activity).setTitle("正在下载 V" + rel.getVersion()).setView(box).setCancelable(false).show();
        new Thread(new Runnable() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                UpdateManager.startDownload$lambda$24(activity, rel, $this$startDownload_u24lambda_u2419, $this$startDownload_u24lambda_u2418, dialog);
            }
        }, "xf-update-download").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00b0 A[Catch: all -> 0x00ab, TRY_LEAVE, TryCatch #3 {all -> 0x00ab, blocks: (B:23:0x00a4, B:30:0x00b0), top: B:63:0x00a4 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00d4 A[Catch: all -> 0x00cf, TRY_LEAVE, TryCatch #5 {all -> 0x00cf, blocks: (B:35:0x00c8, B:42:0x00d4), top: B:67:0x00c8 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00e9 A[Catch: all -> 0x00e4, TRY_LEAVE, TryCatch #0 {all -> 0x00e4, blocks: (B:48:0x00dd, B:55:0x00e9), top: B:57:0x00dd }] */
    /* JADX WARN: Removed duplicated region for block: B:70:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void startDownload$lambda$24(final android.app.Activity r18, final com.xiaofan.bangfan.UpdateManager.Release r19, android.widget.TextView r20, android.widget.ProgressBar r21, final android.app.AlertDialog r22) {
        /*
            Method dump skipped, instructions count: 237
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.UpdateManager.startDownload$lambda$24(android.app.Activity, com.xiaofan.bangfan.UpdateManager$Release, android.widget.TextView, android.widget.ProgressBar, android.app.AlertDialog):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startDownload$lambda$24$lambda$21(AlertDialog $dialog, File target, Activity activity) {
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
    public static final void startDownload$lambda$24$lambda$23(AlertDialog $dialog, final Activity activity, Throwable th, final Release rel) {
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
        title.setMessage(message + "\n已并行测速官方直连与多个国内加速节点、自动切换最快线路并支持断点续传，可换 WiFi/移动数据后点重试继续下载。").setPositiveButton("重试", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda9
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                UpdateManager.startDownload$lambda$24$lambda$23$lambda$22(activity, rel, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startDownload$lambda$24$lambda$23$lambda$22(Activity activity, Release rel, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(rel, "$rel");
        INSTANCE.startDownload(activity, rel);
    }

    private final void downloadFile(final String urlStr, File target, final long expectedSize, final Function1<? super String, Unit> function1, final Function2<? super Long, ? super Long, Unit> function2) {
        String str;
        boolean[] done;
        File part;
        if (expectedSize <= 0) {
            downloadFileSingle(urlStr, target, function1, function2);
            return;
        }
        File part2 = new File(target.getParentFile(), target.getName() + ".part");
        final File idxFile = new File(target.getParentFile(), target.getName() + ".idx");
        int chunkCount = UpdateDownloadCore.chunkCount$default(UpdateDownloadCore.INSTANCE, expectedSize, 0L, 2, null);
        function1.invoke("正在测速选择最快线路…");
        List probes = probeAllNodes(urlStr, expectedSize);
        Iterable $this$filter$iv = UpdateDownloadCore.INSTANCE.rankProbes(probes);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Iterable $this$filter$iv2 = $this$filter$iv;
            if (((UpdateDownloadCore.NodeProbe) element$iv$iv).getSupportsRange()) {
                destination$iv$iv.add(element$iv$iv);
            }
            $this$filter$iv = $this$filter$iv2;
        }
        final List ranked = (List) destination$iv$iv;
        if (ranked.isEmpty()) {
            Log.w(TAG, "no range-capable node from probes, fallback to single stream");
            downloadFileSingle(urlStr, target, function1, function2);
            return;
        }
        Log.i(TAG, "ranked nodes: " + CollectionsKt.joinToString$default(ranked, null, null, null, 0, null, new Function1<UpdateDownloadCore.NodeProbe, CharSequence>() { // from class: com.xiaofan.bangfan.UpdateManager$downloadFile$1
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(UpdateDownloadCore.NodeProbe it) {
                Intrinsics.checkNotNullParameter(it, "it");
                String label = it.getNode().getLabel();
                return label + "=" + (it.getBps() / 1024) + "KB/s";
            }
        }, 31, null));
        function1.invoke("已连接 " + RangesKt.coerceAtMost(ranked.size(), 4) + " 条加速线路…");
        if (!part2.exists() || part2.length() != expectedSize) {
            RandomAccessFile it = new RandomAccessFile(part2, "rw");
            try {
                it.setLength(expectedSize);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(it, null);
            } finally {
            }
        }
        boolean[] done2 = new boolean[chunkCount];
        if (idxFile.exists()) {
            str = "rw";
            if (idxFile.length() == (chunkCount + 7) / 8) {
                byte[] bits = FilesKt.readBytes(idxFile);
                for (int i = 0; i < chunkCount; i++) {
                    if (UpdateDownloadCore.INSTANCE.bitGet(bits, i)) {
                        done2[i] = true;
                    }
                }
            }
        } else {
            str = "rw";
        }
        final AtomicInteger cursor = new AtomicInteger(0);
        int i2 = 0;
        long initBytes = 0;
        while (i2 < chunkCount) {
            if (done2[i2]) {
                part = part2;
                initBytes += UpdateDownloadCore.chunkLen$default(UpdateDownloadCore.INSTANCE, i2, expectedSize, 0L, 4, null);
            } else {
                part = part2;
            }
            i2++;
            part2 = part;
        }
        File part3 = part2;
        final AtomicLong doneBytes = new AtomicLong(initBytes);
        final Object bitsLock = new Object();
        final byte[] bits2 = new byte[(chunkCount + 7) / 8];
        int i3 = 0;
        while (i3 < chunkCount) {
            if (done2[i3]) {
                done = done2;
                UpdateDownloadCore.INSTANCE.bitSet(bits2, i3);
            } else {
                done = done2;
            }
            i3++;
            done2 = done;
        }
        boolean[] done3 = done2;
        final RandomAccessFile raf = new RandomAccessFile(part3, str);
        final Throwable[] firstErr = {null};
        String str2 = TAG;
        final AtomicLong lastReport = new AtomicLong(0L);
        long startAt = System.currentTimeMillis();
        final AtomicLong lastTickBytes = new AtomicLong(initBytes);
        final AtomicLong lastTickAt = new AtomicLong(startAt);
        long initBytes2 = initBytes;
        Iterable $this$map$iv = RangesKt.until(0, 3);
        Iterable destination$iv$iv2 = (Collection) new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        Iterator<Integer> it2 = $this$map$iv.iterator();
        while (it2.hasNext()) {
            final int item$iv$iv = ((IntIterator) it2).nextInt();
            Collection destination$iv$iv3 = destination$iv$iv2;
            long startAt2 = startAt;
            final boolean[] done4 = done3;
            final int i4 = chunkCount;
            destination$iv$iv3.add(new Thread(new Runnable() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    UpdateManager.downloadFile$lambda$29$lambda$28(item$iv$iv, cursor, i4, done4, expectedSize, ranked, urlStr, raf, firstErr, bitsLock, doneBytes, lastReport, function2, lastTickAt, lastTickBytes, function1, bits2, idxFile);
                }
            }));
            destination$iv$iv2 = destination$iv$iv3;
            $this$map$iv = $this$map$iv;
            part3 = part3;
            initBytes2 = initBytes2;
            done3 = done4;
            startAt = startAt2;
            str2 = str2;
            chunkCount = chunkCount;
            probes = probes;
        }
        File part4 = part3;
        String str3 = str2;
        Iterable workerTasks = (List) destination$iv$iv2;
        Iterable $this$forEach$iv = workerTasks;
        for (Object element$iv : $this$forEach$iv) {
            ((Thread) element$iv).start();
        }
        Iterable $this$forEach$iv2 = workerTasks;
        for (Object element$iv2 : $this$forEach$iv2) {
            ((Thread) element$iv2).join();
        }
        try {
            raf.close();
        } catch (Throwable th) {
        }
        Throwable it3 = firstErr[0];
        if (it3 != null) {
            throw it3;
        }
        if (part4.length() != expectedSize) {
            throw new RuntimeException("下载不完整（" + part4.length() + "/" + expectedSize + " 字节），请重试（支持断点续传）");
        }
        if (target.exists()) {
            target.delete();
        }
        if (!part4.renameTo(target)) {
            FilesKt.copyTo$default(part4, target, true, 0, 4, null);
            part4.delete();
        }
        idxFile.delete();
        downloadFile$report(lastReport, doneBytes, function2, expectedSize, lastTickAt, lastTickBytes, function1, true);
        Log.i(str3, "chunked download ok size=" + target.length());
    }

    private static final void downloadFile$report(AtomicLong lastReport, AtomicLong doneBytes, Function2<? super Long, ? super Long, Unit> function2, long $expectedSize, AtomicLong lastTickAt, AtomicLong lastTickBytes, Function1<? super String, Unit> function1, boolean force) {
        long now = System.currentTimeMillis();
        if (force || now - lastReport.get() >= 250) {
            lastReport.set(now);
            long db = doneBytes.get();
            function2.invoke(Long.valueOf(db), Long.valueOf($expectedSize));
            long dt = now - lastTickAt.get();
            if (dt >= 1000) {
                long speed = ((db - lastTickBytes.get()) * 1000) / RangesKt.coerceAtLeast(dt, 1L);
                lastTickBytes.set(db);
                lastTickAt.set(now);
                function1.invoke("多线路并行下载 · " + UpdateDownloadCore.INSTANCE.formatSpeed(speed));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void downloadFile$lambda$29$lambda$28(int $workerId, AtomicInteger cursor, int $chunkCount, boolean[] done, long $expectedSize, List ranked, String urlStr, RandomAccessFile raf, Throwable[] firstErr, Object bitsLock, AtomicLong doneBytes, AtomicLong lastReport, Function2 onProgress, AtomicLong lastTickAt, AtomicLong lastTickBytes, Function1 onStatus, byte[] bits, File idxFile) {
        UpdateDownloadCore.NodeProbe probe;
        UpdateDownloadCore.NodeProbe probe2;
        int t;
        Intrinsics.checkNotNullParameter(cursor, "$cursor");
        Intrinsics.checkNotNullParameter(done, "$done");
        Intrinsics.checkNotNullParameter(ranked, "$ranked");
        Intrinsics.checkNotNullParameter(urlStr, "$urlStr");
        Intrinsics.checkNotNullParameter(raf, "$raf");
        Intrinsics.checkNotNullParameter(firstErr, "$firstErr");
        Intrinsics.checkNotNullParameter(bitsLock, "$bitsLock");
        Intrinsics.checkNotNullParameter(doneBytes, "$doneBytes");
        Intrinsics.checkNotNullParameter(lastReport, "$lastReport");
        Intrinsics.checkNotNullParameter(onProgress, "$onProgress");
        Intrinsics.checkNotNullParameter(lastTickAt, "$lastTickAt");
        Intrinsics.checkNotNullParameter(lastTickBytes, "$lastTickBytes");
        Intrinsics.checkNotNullParameter(onStatus, "$onStatus");
        Intrinsics.checkNotNullParameter(bits, "$bits");
        Intrinsics.checkNotNullParameter(idxFile, "$idxFile");
        int nodeOffset = $workerId;
        while (true) {
            int ci = cursor.getAndIncrement();
            if (ci >= $chunkCount) {
                return;
            }
            if (done[ci]) {
                downloadFile$report(lastReport, doneBytes, onProgress, $expectedSize, lastTickAt, lastTickBytes, onStatus, false);
            } else {
                long start = ci * UpdateDownloadCore.CHUNK_SIZE;
                long end = (start + UpdateDownloadCore.chunkLen$default(UpdateDownloadCore.INSTANCE, ci, $expectedSize, 0L, 4, null)) - 1;
                int nodeOffset2 = nodeOffset;
                int t2 = 0;
                while (true) {
                    if (t2 >= 12) {
                        probe = null;
                        break;
                    }
                    probe2 = (UpdateDownloadCore.NodeProbe) ranked.get((nodeOffset2 + t2) % ranked.size());
                    try {
                        t = t2;
                        try {
                            INSTANCE.fetchChunk(probe2.getNode(), urlStr, start, end, raf);
                            probe = 1;
                            break;
                        } catch (Throwable th) {
                            th = th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        t = t2;
                    }
                    Log.w(TAG, "chunk " + ci + " via " + probe2.getNode().getLabel() + " failed: " + th.getMessage());
                    nodeOffset2++;
                    t2 = t + 1;
                }
                if (probe != null) {
                    done[ci] = true;
                    synchronized (bitsLock) {
                        try {
                            UpdateDownloadCore.INSTANCE.bitSet(bits, ci);
                            try {
                                INSTANCE.writeBytesSafe(idxFile, bits);
                                Unit unit = Unit.INSTANCE;
                            } catch (Throwable th3) {
                                th = th3;
                                throw th;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                        }
                    }
                    doneBytes.addAndGet((end - start) + 1);
                    downloadFile$report(lastReport, doneBytes, onProgress, $expectedSize, lastTickAt, lastTickBytes, onStatus, false);
                    nodeOffset = nodeOffset2;
                } else {
                    firstErr[0] = new RuntimeException("分块 " + ci + " 在所有加速节点均下载失败，网络不稳定，请重试（支持断点续传）");
                    cursor.set($chunkCount);
                    return;
                }
            }
        }
    }

    private final List<UpdateDownloadCore.NodeProbe> probeAllNodes(final String assetUrl, final long expectedSize) {
        ExecutorService pool = Executors.newCachedThreadPool();
        try {
            Iterable $this$map$iv = UpdateDownloadCore.INSTANCE.getNodes();
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                final UpdateDownloadCore.Node n = (UpdateDownloadCore.Node) item$iv$iv;
                try {
                    destination$iv$iv.add(new Callable() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda13
                        @Override // java.util.concurrent.Callable
                        public final Object call() {
                            UpdateDownloadCore.NodeProbe probeAllNodes$lambda$34$lambda$33;
                            probeAllNodes$lambda$34$lambda$33 = UpdateManager.probeAllNodes$lambda$34$lambda$33(UpdateDownloadCore.Node.this, assetUrl, expectedSize);
                            return probeAllNodes$lambda$34$lambda$33;
                        }
                    });
                } catch (Throwable th) {
                    th = th;
                    pool.shutdownNow();
                    throw th;
                }
            }
            List tasks = (List) destination$iv$iv;
            Iterable futures = pool.invokeAll(tasks, UpdateDownloadCore.PROBE_BUDGET_MS, TimeUnit.MILLISECONDS);
            Intrinsics.checkNotNull(futures);
            Iterable $this$mapNotNull$iv = futures;
            Collection destination$iv$iv2 = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                Future f = (Future) element$iv$iv$iv;
                UpdateDownloadCore.NodeProbe nodeProbe = null;
                try {
                    if (f.isDone()) {
                        nodeProbe = (UpdateDownloadCore.NodeProbe) f.get();
                    }
                } catch (Throwable th2) {
                }
                if (nodeProbe != null) {
                    destination$iv$iv2.add(nodeProbe);
                }
            }
            ArrayList arrayList = (List) destination$iv$iv2;
            pool.shutdownNow();
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final UpdateDownloadCore.NodeProbe probeAllNodes$lambda$34$lambda$33(UpdateDownloadCore.Node n, String assetUrl, long $expectedSize) {
        Intrinsics.checkNotNullParameter(n, "$n");
        Intrinsics.checkNotNullParameter(assetUrl, "$assetUrl");
        try {
            return INSTANCE.probeNode(n, assetUrl, $expectedSize);
        } catch (Throwable th) {
            String label = n.getLabel();
            Log.w(TAG, "probe " + label + " failed: " + th.getMessage());
            return new UpdateDownloadCore.NodeProbe(n, 0L, false, -1L, false);
        }
    }

    private final UpdateDownloadCore.NodeProbe probeNode(UpdateDownloadCore.Node node, String assetUrl, long expectedSize) {
        String str;
        long total;
        boolean supportsRange;
        Throwable th;
        long t0 = System.currentTimeMillis();
        HttpURLConnection conn = connectFollowing(node.wrap(assetUrl), "bytes=0-262143", UpdateDownloadCore.PROBE_CONNECT_MS, UpdateDownloadCore.PROBE_READ_MS);
        try {
            int code = conn.getResponseCode();
            if (!(200 <= code && code < 300)) {
                throw new RuntimeException(String.valueOf(code));
            }
            String contentType = conn.getContentType();
            if (contentType != null) {
                str = contentType.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(str, "toLowerCase(...)");
            } else {
                str = null;
            }
            if (str == null) {
                str = "";
            }
            String ctype = str;
            if (StringsKt.contains$default((CharSequence) ctype, (CharSequence) "text/html", false, 2, (Object) null)) {
                throw new RuntimeException("返回网页而非安装包");
            }
            if (code == 206) {
                long total2 = UpdateDownloadCore.INSTANCE.parseContentRangeTotal(conn.getHeaderField("Content-Range"));
                total = total2;
                supportsRange = true;
            } else {
                long total3 = RangesKt.coerceAtLeast(conn.getContentLengthLong(), -1L);
                total = total3;
                supportsRange = false;
            }
            if (expectedSize > 0 && total > 0 && total != expectedSize) {
                throw new RuntimeException("资产大小不一致（节点缓存可能过期）");
            }
            byte[] buf = new byte[16384];
            Ref.LongRef got = new Ref.LongRef();
            InputStream inputStream = conn.getInputStream();
            try {
                InputStream input = inputStream;
                while (got.element <= UpdateDownloadCore.PROBE_BYTES) {
                    try {
                        int n = input.read(buf);
                        if (n <= 0) {
                            break;
                        }
                        got.element += n;
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(inputStream, null);
                long ms = RangesKt.coerceAtLeast(System.currentTimeMillis() - t0, 1L);
                long bps = (got.element * 1000) / ms;
                UpdateDownloadCore.NodeProbe nodeProbe = new UpdateDownloadCore.NodeProbe(node, bps, supportsRange, total, got.element > 0);
                try {
                    conn.disconnect();
                } catch (Throwable th3) {
                }
                return nodeProbe;
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Throwable th5) {
            try {
                conn.disconnect();
            } catch (Throwable th6) {
            }
            throw th5;
        }
    }

    private final void fetchChunk(UpdateDownloadCore.Node node, String assetUrl, long start, long end, RandomAccessFile raf) {
        String str;
        String ctype;
        long j = start;
        HttpURLConnection conn = connectFollowing(node.wrap(assetUrl), "bytes=" + j + "-" + end, 12000, UpdateDownloadCore.DL_READ_MS);
        try {
            int code = conn.getResponseCode();
            if (code == 200) {
                throw new RuntimeException("节点不支持断点续传");
            }
            if (code != 206) {
                throw new RuntimeException("HTTP " + code);
            }
            String contentType = conn.getContentType();
            if (contentType != null) {
                str = contentType.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(str, "toLowerCase(...)");
            } else {
                str = null;
            }
            if (str == null) {
                str = "";
            }
            String ctype2 = str;
            if (StringsKt.contains$default((CharSequence) ctype2, (CharSequence) "text/html", false, 2, (Object) null)) {
                throw new RuntimeException("返回网页而非安装包");
            }
            long expect = (end - j) + 1;
            byte[] buf = new byte[131072];
            long windowAt = System.currentTimeMillis();
            long windowBytes = 0;
            InputStream inputStream = conn.getInputStream();
            try {
                InputStream input = inputStream;
                byte[] buf2 = buf;
                long written = 0;
                while (written < expect) {
                    InputStream input2 = input;
                    byte[] buf3 = buf2;
                    try {
                        int n = input2.read(buf3);
                        if (n <= 0) {
                            break;
                        }
                        synchronized (raf) {
                            ctype = ctype2;
                            try {
                                raf.seek(j + written);
                                raf.write(buf3, 0, n);
                                Unit unit = Unit.INSTANCE;
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                        written += n;
                        windowBytes += n;
                        long now = System.currentTimeMillis();
                        if (now - windowAt < UpdateDownloadCore.STALL_WINDOW_MS) {
                            j = start;
                            buf2 = buf3;
                            ctype2 = ctype;
                            input = input2;
                        } else if (windowBytes < UpdateDownloadCore.STALL_MIN_BYTES) {
                            throw new RuntimeException(node.getLabel() + "速度过慢（" + ((1000 * windowBytes) / (now - windowAt)) + "B/s 级），切换线路");
                        } else {
                            windowAt = now;
                            windowBytes = 0;
                            buf2 = buf3;
                            ctype2 = ctype;
                            input = input2;
                        }
                    } catch (Throwable th2) {
                        throw th2;
                    }
                }
                Unit unit2 = Unit.INSTANCE;
                CloseableKt.closeFinally(inputStream, null);
                if (written != expect) {
                    throw new RuntimeException("分块不完整 " + written + "/" + expect);
                }
                try {
                    conn.disconnect();
                } catch (Throwable th3) {
                }
            } catch (Throwable th4) {
                throw th4;
            }
        } catch (Throwable th5) {
            try {
                conn.disconnect();
            } catch (Throwable th6) {
            }
            throw th5;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0077, code lost:
        throw new java.lang.RuntimeException("下载重定向异常");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.net.HttpURLConnection connectFollowing(java.lang.String r9, java.lang.String r10, int r11, int r12) {
        /*
            r8 = this;
            r0 = r9
            r1 = 0
        L2:
            java.net.URL r2 = new java.net.URL
            r2.<init>(r0)
            java.net.URLConnection r2 = r2.openConnection()
            java.lang.String r3 = "null cannot be cast to non-null type java.net.HttpURLConnection"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2, r3)
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2
            r3 = r2
            r4 = 0
            r3.setConnectTimeout(r11)
            r3.setReadTimeout(r12)
            java.lang.String r5 = "GET"
            r3.setRequestMethod(r5)
            r5 = 0
            r3.setInstanceFollowRedirects(r5)
            java.lang.String r6 = "User-Agent"
            java.lang.String r7 = "xiaofan-bangfan-update"
            r3.setRequestProperty(r6, r7)
            java.lang.String r6 = "Accept"
        */
        //  java.lang.String r7 = "application/vnd.android.package-archive, application/octet-stream, */*"
        /*
            r3.setRequestProperty(r6, r7)
            if (r10 == 0) goto L39
            java.lang.String r6 = "Range"
            r3.setRequestProperty(r6, r10)
        L39:
            int r3 = r2.getResponseCode()
            r4 = 300(0x12c, float:4.2E-43)
            r6 = 1
            if (r4 > r3) goto L4a
            r4 = 400(0x190, float:5.6E-43)
            if (r3 >= r4) goto L4a
            r4 = r6
            goto L4b
        L4a:
            r4 = r5
        L4b:
            if (r4 == 0) goto L78
            java.lang.String r4 = "Location"
            java.lang.String r4 = r2.getHeaderField(r4)
            r2.disconnect()     // Catch: java.lang.Throwable -> L57
            goto L58
        L57:
            r7 = move-exception
        L58:
            r7 = r4
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            if (r7 == 0) goto L63
            boolean r7 = kotlin.text.StringsKt.isBlank(r7)
            if (r7 == 0) goto L64
        L63:
            r5 = r6
        L64:
            if (r5 != 0) goto L70
            r5 = 6
            if (r1 >= r5) goto L70
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            r0 = r4
            int r1 = r1 + 1
            goto L2
        L70:
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            java.lang.String r6 = "下载重定向异常"
            r5.<init>(r6)
            throw r5
        L78:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.UpdateManager.connectFollowing(java.lang.String, java.lang.String, int, int):java.net.HttpURLConnection");
    }

    /* JADX WARN: Code restructure failed: missing block: B:109:0x02fc, code lost:
        r36 = r3;
        r35 = r13;
        r20 = r15;
        r38 = r24;
        r24 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0308, code lost:
        r38.flush();
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x030f, code lost:
        kotlin.io.CloseableKt.closeFinally(r7, null);
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0315, code lost:
        kotlin.io.CloseableKt.closeFinally(r4, null);
        r45.invoke(java.lang.Long.valueOf(r0.element), java.lang.Long.valueOf(r5));
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0329, code lost:
        if (r5 <= 0) goto L135;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0331, code lost:
        if (r36.length() != r5) goto L232;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x0360, code lost:
        throw new java.lang.RuntimeException("下载不完整（" + r36.length() + "/" + r5 + " 字节），将换节点续传");
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x0361, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x0362, code lost:
        r2 = r0;
        r5 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x0371, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x0372, code lost:
        r5 = r20;
        r6 = r24;
        r12 = r36;
        r2 = false;
        r8 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x0382, code lost:
        if (r43.exists() == false) goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x0384, code lost:
        r43.delete();
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x0387, code lost:
        r12 = r36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x038f, code lost:
        if (r12.renameTo(r43) != false) goto L224;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x0391, code lost:
        r21 = r5;
        r15 = r0;
        r23 = r0;
        r2 = false;
        r2 = null;
        r31 = r0;
        r19 = r0;
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x03a7, code lost:
        kotlin.io.FilesKt.copyTo$default(r12, r43, true, 0, 4, null);
        r12.delete();
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x03ae, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x03af, code lost:
        r8 = true;
        r5 = r20;
        r6 = r24;
        r12 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x03b6, code lost:
        r21 = r5;
        r23 = r0;
        r19 = r0;
        r15 = r0;
        r2 = null;
        r4 = true;
        r31 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x03e7, code lost:
        r6 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x03e9, code lost:
        android.util.Log.i(r6, "single-stream download ok via " + r14.getLabel() + " size=" + r43.length());
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x03ed, code lost:
        if (r20 == 0) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x03ef, code lost:
        r20.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x0402, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0404, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x0405, code lost:
        r6 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x0408, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x0409, code lost:
        r6 = r24;
        r2 = null;
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x040d, code lost:
        r8 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x0410, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x0411, code lost:
        r2 = r0;
        r5 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x0420, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0421, code lost:
        r6 = r24;
        r12 = r36;
        r2 = false;
        r8 = true;
        r5 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x042e, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x042f, code lost:
        r5 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x0444, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x0445, code lost:
        r5 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:194:0x056d, code lost:
        r5 = r20;
        r2 = r2;
        r8 = r8;
        r12 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:213:0x0619, code lost:
        r0 = r4.getMessage();
     */
    /* JADX WARN: Code restructure failed: missing block: B:214:0x061d, code lost:
        if (r0 != null) goto L205;
     */
    /* JADX WARN: Code restructure failed: missing block: B:217:0x062d, code lost:
        r2 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:218:0x062e, code lost:
        if (r2 != false) goto L209;
     */
    /* JADX WARN: Code restructure failed: missing block: B:219:0x0630, code lost:
        r12.delete();
     */
    /* JADX WARN: Code restructure failed: missing block: B:221:0x0634, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:222:0x0635, code lost:
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:227:0x0643, code lost:
        r0.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:234:0x0658, code lost:
        throw r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x027a, code lost:
        throw new java.lang.RuntimeException(r14.getLabel() + "速度过慢，切换线路");
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0643 A[Catch: all -> 0x0647, TRY_LEAVE, TryCatch #28 {all -> 0x0647, blocks: (B:225:0x063e, B:227:0x0643), top: B:283:0x063e }] */
    /* JADX WARN: Removed duplicated region for block: B:232:0x064d A[LOOP:0: B:3:0x002d->B:232:0x064d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:248:0x067b A[Catch: all -> 0x067f, TRY_LEAVE, TryCatch #23 {all -> 0x067f, blocks: (B:246:0x0676, B:248:0x067b), top: B:277:0x0676 }] */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0619 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:324:0x0655 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v1, types: [int] */
    /* JADX WARN: Type inference failed for: r12v14 */
    /* JADX WARN: Type inference failed for: r12v35, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r20v0, types: [long] */
    /* JADX WARN: Type inference failed for: r20v14, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r20v4 */
    /* JADX WARN: Type inference failed for: r2v65 */
    /* JADX WARN: Type inference failed for: r4v25 */
    /* JADX WARN: Type inference failed for: r4v26 */
    /* JADX WARN: Type inference failed for: r4v27 */
    /* JADX WARN: Type inference failed for: r4v28 */
    /* JADX WARN: Type inference failed for: r4v31 */
    /* JADX WARN: Type inference failed for: r8v46 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void downloadFileSingle(java.lang.String r42, java.io.File r43, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r44, kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> r45) {
        /*
            Method dump skipped, instructions count: 1665
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.UpdateManager.downloadFileSingle(java.lang.String, java.io.File, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function2):void");
    }

    private final void writeBytesSafe(File $this$writeBytesSafe, byte[] bits) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream($this$writeBytesSafe);
            FileOutputStream it = fileOutputStream;
            it.write(bits);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(fileOutputStream, null);
        } catch (Throwable th) {
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
        TextView $this$showSourceDialog_u24lambda_u2445 = new TextView(activity);
        $this$showSourceDialog_u24lambda_u2445.setText("填写 GitHub 仓库，格式：用户名/仓库名\n例如：your-name/XiaoFan");
        final EditText $this$showSourceDialog_u24lambda_u2446 = new EditText(activity);
        $this$showSourceDialog_u24lambda_u2446.setHint("用户名/仓库名");
        $this$showSourceDialog_u24lambda_u2446.setText(INSTANCE.sourceSlug(activity));
        container.addView($this$showSourceDialog_u24lambda_u2445);
        container.addView($this$showSourceDialog_u24lambda_u2446);
        new AlertDialog.Builder(activity).setTitle("更新源（GitHub 仓库）").setView(container).setPositiveButton("保存", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.UpdateManager$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                UpdateManager.showSourceDialog$lambda$47($this$showSourceDialog_u24lambda_u2446, activity, function0, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showSourceDialog$lambda$47(EditText input, Activity activity, Function0 $onSaved, DialogInterface dialogInterface, int i) {
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
