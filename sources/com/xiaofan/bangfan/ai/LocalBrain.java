package com.xiaofan.bangfan.ai;

import android.content.Context;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.ai.NativeLlm;
import com.xiaofan.bangfan.ai.OfflineModels;
import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
/* compiled from: LocalBrain.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0002J$\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000e2\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00100\u0013J&\u0010\u0014\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00100\u0013J\u000e\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000eJ\u0018\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000e2\b\b\u0002\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u000bJ\u0006\u0010\u001c\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/xiaofan/bangfan/ai/LocalBrain;", "", "()V", "SYSTEM_PROMPT", "", "executor", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "handle", "Ljava/util/concurrent/atomic/AtomicLong;", "loading", "", "doLoad", "app", "Landroid/content/Context;", "ensureLoaded", "", "ctx", "onState", "Lkotlin/Function1;", "generate", "user", "onPiece", "isReady", "loadBlocking", "timeoutMs", "", "loaded", "release", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class LocalBrain {
    public static final String SYSTEM_PROMPT = "你是小翻，一个温暖、机灵、简洁的中文陪伴与阅读助手。用简体中文回答，口语化，一般不超过三句话，不使用 Markdown 符号，不编造事实，不知道就坦诚说不知道。";
    private static volatile boolean loading;
    public static final LocalBrain INSTANCE = new LocalBrain();
    private static final ExecutorService executor = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.xiaofan.bangfan.ai.LocalBrain$$ExternalSyntheticLambda1
        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            Thread executor$lambda$0;
            executor$lambda$0 = LocalBrain.executor$lambda$0(runnable);
            return executor$lambda$0;
        }
    });
    private static final AtomicLong handle = new AtomicLong(0);

    private LocalBrain() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Thread executor$lambda$0(Runnable r) {
        return new Thread(r, "xf-local-llm");
    }

    public final boolean isReady(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        return OfflineModels.INSTANCE.isReady(ctx, OfflineModels.Kind.LLM);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void ensureLoaded$default(LocalBrain localBrain, Context context, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<String, Unit>() { // from class: com.xiaofan.bangfan.ai.LocalBrain$ensureLoaded$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(String it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }
            };
        }
        localBrain.ensureLoaded(context, function1);
    }

    public final void ensureLoaded(Context ctx, final Function1<? super String, Unit> onState) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(onState, "onState");
        final Context app = ctx.getApplicationContext();
        executor.execute(new Runnable() { // from class: com.xiaofan.bangfan.ai.LocalBrain$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                LocalBrain.ensureLoaded$lambda$1(app, onState);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ensureLoaded$lambda$1(Context $app, Function1 onState) {
        Intrinsics.checkNotNullParameter(onState, "$onState");
        if (handle.get() != 0 || loading) {
            return;
        }
        OfflineModels offlineModels = OfflineModels.INSTANCE;
        Intrinsics.checkNotNull($app);
        if (offlineModels.isReady($app, OfflineModels.Kind.LLM)) {
            loading = true;
            try {
                NativeLlm.INSTANCE.ensureLoaded();
                onState.invoke("正在载入离线大脑…");
                int threads = RangesKt.coerceIn(Runtime.getRuntime().availableProcessors(), 2, 8);
                NativeLlm nativeLlm = NativeLlm.INSTANCE;
                String absolutePath = OfflineModels.INSTANCE.file($app, OfflineModels.Kind.LLM).getAbsolutePath();
                Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
                long h = nativeLlm.nativeLoad(absolutePath, 2048, threads);
                handle.set(h);
                onState.invoke(h != 0 ? "离线大脑已就绪" : "离线大脑载入失败");
            } finally {
                try {
                } finally {
                }
            }
        }
    }

    public final boolean loaded() {
        return handle.get() != 0;
    }

    public static /* synthetic */ boolean loadBlocking$default(LocalBrain localBrain, Context context, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 30000;
        }
        return localBrain.loadBlocking(context, j);
    }

    public final boolean loadBlocking(Context ctx, long timeoutMs) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        final Context app = ctx.getApplicationContext();
        if (handle.get() != 0) {
            return true;
        }
        OfflineModels offlineModels = OfflineModels.INSTANCE;
        Intrinsics.checkNotNull(app);
        if (offlineModels.isReady(app, OfflineModels.Kind.LLM)) {
            FutureTask task = new FutureTask(new Callable() { // from class: com.xiaofan.bangfan.ai.LocalBrain$$ExternalSyntheticLambda4
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    Boolean loadBlocking$lambda$2;
                    loadBlocking$lambda$2 = LocalBrain.loadBlocking$lambda$2(app);
                    return loadBlocking$lambda$2;
                }
            });
            executor.execute(task);
            try {
                Object obj = task.get(timeoutMs, TimeUnit.MILLISECONDS);
                Intrinsics.checkNotNull(obj);
                return ((Boolean) obj).booleanValue();
            } catch (Throwable th) {
                return handle.get() != 0;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Boolean loadBlocking$lambda$2(Context $app) {
        LocalBrain localBrain = INSTANCE;
        Intrinsics.checkNotNull($app);
        return Boolean.valueOf(localBrain.doLoad($app));
    }

    private final boolean doLoad(Context app) {
        if (handle.get() != 0) {
            return true;
        }
        if (loading) {
            long deadline = System.currentTimeMillis() + 30000;
            while (loading && System.currentTimeMillis() < deadline) {
                Thread.sleep(50L);
            }
            return handle.get() != 0;
        }
        loading = true;
        try {
            NativeLlm.INSTANCE.ensureLoaded();
            int threads = RangesKt.coerceIn(Runtime.getRuntime().availableProcessors(), 2, 8);
            File mf = OfflineModels.INSTANCE.file(app, OfflineModels.Kind.LLM);
            String absolutePath = mf.getAbsolutePath();
            Log.i("XFLLM", "loading model path=" + absolutePath + " exists=" + mf.exists() + " len=" + mf.length() + " threads=" + threads);
            NativeLlm nativeLlm = NativeLlm.INSTANCE;
            String absolutePath2 = mf.getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath2, "getAbsolutePath(...)");
            long h = nativeLlm.nativeLoad(absolutePath2, 2048, threads);
            Log.i("XFLLM", "nativeLoad handle=" + h);
            handle.set(h);
            boolean z = h != 0;
            loading = false;
            return z;
        } catch (Throwable t) {
            try {
                Log.e("XFLLM", "doLoad failed", t);
                return false;
            } finally {
                loading = false;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ String generate$default(LocalBrain localBrain, String str, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<String, Unit>() { // from class: com.xiaofan.bangfan.ai.LocalBrain$generate$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str2) {
                    invoke2(str2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(String it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }
            };
        }
        return localBrain.generate(str, function1);
    }

    public final String generate(final String user, final Function1<? super String, Unit> onPiece) {
        Intrinsics.checkNotNullParameter(user, "user");
        Intrinsics.checkNotNullParameter(onPiece, "onPiece");
        final long h = handle.get();
        if (h == 0) {
            Log.w("XFLLM", "generate skipped: handle=0");
            return null;
        }
        FutureTask task = new FutureTask(new Callable() { // from class: com.xiaofan.bangfan.ai.LocalBrain$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                String generate$lambda$3;
                generate$lambda$3 = LocalBrain.generate$lambda$3(h, user, onPiece);
                return generate$lambda$3;
            }
        });
        executor.execute(task);
        try {
            String r = (String) task.get();
            Log.i("XFLLM", "generate result len=" + (r != null ? r.length() : -1) + " text=" + (r != null ? StringsKt.take(r, 60) : null));
            return r;
        } catch (Throwable t) {
            Log.e("XFLLM", "generate failed", t);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String generate$lambda$3(long $h, String user, final Function1 onPiece) {
        Intrinsics.checkNotNullParameter(user, "$user");
        Intrinsics.checkNotNullParameter(onPiece, "$onPiece");
        return NativeLlm.INSTANCE.nativeChat($h, SYSTEM_PROMPT, user, 220, 0.7f, 0.9f, new NativeLlm.PieceCallback() { // from class: com.xiaofan.bangfan.ai.LocalBrain$generate$task$1$1
            @Override // com.xiaofan.bangfan.ai.NativeLlm.PieceCallback
            public boolean onPiece(String piece) {
                Intrinsics.checkNotNullParameter(piece, "piece");
                onPiece.invoke(piece);
                return true;
            }
        });
    }

    public final void release() {
        final long h = handle.getAndSet(0L);
        if (h != 0) {
            executor.execute(new Runnable() { // from class: com.xiaofan.bangfan.ai.LocalBrain$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    LocalBrain.release$lambda$4(h);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void release$lambda$4(long $h) {
        try {
            NativeLlm.INSTANCE.nativeUnload($h);
        } catch (Throwable th) {
        }
    }
}
