package com.xiaofan.bangfan.ai;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import java.io.File;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import okhttp3.OkHttpClient;
/* compiled from: OfflineModels.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fJ2\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n0\u00142\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012J\u0016\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012J\u0016\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u001c"}, d2 = {"Lcom/xiaofan/bangfan/ai/OfflineModels;", "", "()V", "http", "Lokhttp3/OkHttpClient;", "getHttp", "()Lokhttp3/OkHttpClient;", "http$delegate", "Lkotlin/Lazy;", "cleanupLegacy", "", "ctx", "Landroid/content/Context;", "dir", "Ljava/io/File;", "download", "", "kind", "Lcom/xiaofan/bangfan/ai/OfflineModels$Kind;", "onProgress", "Lkotlin/Function1;", "", "cancel", "Ljava/util/concurrent/atomic/AtomicBoolean;", "file", "isReady", NotificationCompat.CATEGORY_PROGRESS, "Kind", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class OfflineModels {
    public static final OfflineModels INSTANCE = new OfflineModels();
    private static final Lazy http$delegate = LazyKt.lazy(new Function0<OkHttpClient>() { // from class: com.xiaofan.bangfan.ai.OfflineModels$http$2
        @Override // kotlin.jvm.functions.Function0
        public final OkHttpClient invoke() {
            return new OkHttpClient.Builder().connectTimeout(20L, TimeUnit.SECONDS).readTimeout(60L, TimeUnit.SECONDS).retryOnConnectionFailure(true).build();
        }
    });

    /* compiled from: OfflineModels.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B'\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fj\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/xiaofan/bangfan/ai/OfflineModels$Kind;", "", "fileName", "", "url", "expectSize", "", "label", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;JLjava/lang/String;)V", "getExpectSize", "()J", "getFileName", "()Ljava/lang/String;", "getLabel", "getUrl", "LLM", "ASR", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes3.dex */
    public enum Kind {
        LLM("qwen25-0.5b-instruct-q4km.gguf", "https://hf-mirror.com/Qwen/Qwen2.5-0.5B-Instruct-GGUF/resolve/main/qwen2.5-0.5b-instruct-q4_k_m.gguf", 491400032, "离线对话大脑"),
        ASR("whisper-base-q8_0.bin", "https://hf-mirror.com/ggerganov/whisper.cpp/resolve/main/ggml-base-q8_0.bin", 81768585, "离线声控识别");
        
        private final long expectSize;
        private final String fileName;
        private final String label;
        private final String url;
        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<Kind> getEntries() {
            return $ENTRIES;
        }

        Kind(String fileName, String url, long expectSize, String label) {
            this.fileName = fileName;
            this.url = url;
            this.expectSize = expectSize;
            this.label = label;
        }

        public final long getExpectSize() {
            return this.expectSize;
        }

        public final String getFileName() {
            return this.fileName;
        }

        public final String getLabel() {
            return this.label;
        }

        public final String getUrl() {
            return this.url;
        }
    }

    private OfflineModels() {
    }

    public final void cleanupLegacy(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        try {
            File legacy = new File(dir(ctx), "whisper-base.bin");
            if (legacy.exists()) {
                legacy.delete();
            }
        } catch (Throwable th) {
        }
    }

    public final File dir(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        File d = new File(ctx.getApplicationContext().getFilesDir(), "models");
        if (!d.exists()) {
            d.mkdirs();
        }
        return d;
    }

    public final File file(Context ctx, Kind kind) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(kind, "kind");
        return new File(dir(ctx), kind.getFileName());
    }

    public final boolean isReady(Context ctx, Kind kind) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(kind, "kind");
        File f = file(ctx, kind);
        return f.exists() && f.length() == kind.getExpectSize();
    }

    public final int progress(Context ctx, Kind kind) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(kind, "kind");
        File f = file(ctx, kind);
        if (f.exists() && kind.getExpectSize() > 0) {
            return RangesKt.coerceIn((int) ((f.length() * 100) / kind.getExpectSize()), 0, 99);
        }
        return 0;
    }

    private final OkHttpClient getHttp() {
        return (OkHttpClient) http$delegate.getValue();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:6|(11:7|8|9|(2:153|154)(1:11)|12|13|(1:15)|16|17|18|19)|(3:144|145|(7:147|148|24|25|26|27|(2:32|33)(2:35|36)))|21|(1:23)(23:41|(1:143)(1:45)|46|(2:48|49)(3:138|139|(1:141)(1:142))|50|51|52|53|54|55|(2:127|128)(2:57|58)|59|(1:61)(1:126)|62|(4:65|66|(8:68|69|70|71|(4:73|74|75|(4:81|82|83|80)(1:77))(2:96|97)|78|79|80)(7:101|102|103|104|105|106|107)|63)|108|109|110|111|112|113|117|118)|24|25|26|27|(2:29|31)(1:37)|32|33|2) */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean download(android.content.Context r30, com.xiaofan.bangfan.ai.OfflineModels.Kind r31, kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> r32, java.util.concurrent.atomic.AtomicBoolean r33) {
        /*
            Method dump skipped, instructions count: 545
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.ai.OfflineModels.download(android.content.Context, com.xiaofan.bangfan.ai.OfflineModels$Kind, kotlin.jvm.functions.Function1, java.util.concurrent.atomic.AtomicBoolean):boolean");
    }
}
