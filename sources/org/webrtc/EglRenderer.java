package org.webrtc;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.view.Surface;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.webrtc.EglBase;
import org.webrtc.EglThread;
import org.webrtc.GlUtil;
import org.webrtc.RendererCommon;
/* loaded from: classes5.dex */
public class EglRenderer implements VideoSink {
    private static final long LOG_INTERVAL_SEC = 4;
    private static final String TAG = "EglRenderer";
    private final GlTextureFrameBuffer bitmapTextureFramebuffer;
    private final Matrix drawMatrix;
    private RendererCommon.GlDrawer drawer;
    private EglBase eglBase;
    private final Runnable eglExceptionCallback;
    private final EglSurfaceCreation eglSurfaceCreationRunnable;
    private EglThread eglThread;
    private volatile ErrorCallback errorCallback;
    private final Object fpsReductionLock;
    private final VideoFrameDrawer frameDrawer;
    private final ArrayList<FrameListenerAndParams> frameListeners;
    private final Object frameLock;
    private int framesDropped;
    private int framesReceived;
    private int framesRendered;
    private float layoutAspectRatio;
    private final Object layoutLock;
    private final Runnable logStatisticsRunnable;
    private long minRenderPeriodNs;
    private boolean mirrorHorizontally;
    private boolean mirrorVertically;
    protected final String name;
    private long nextFrameTimeNs;
    private VideoFrame pendingFrame;
    private long renderSwapBufferTimeNs;
    private long renderTimeNs;
    private final Object statisticsLock;
    private long statisticsStartTimeNs;
    private final Object threadLock;
    private boolean usePresentationTimeStamp;

    /* loaded from: classes5.dex */
    public interface ErrorCallback {
        void onGlOutOfMemory();
    }

    /* loaded from: classes5.dex */
    public interface FrameListener {
        void onFrame(Bitmap bitmap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class FrameListenerAndParams {
        public final boolean applyFpsReduction;
        public final RendererCommon.GlDrawer drawer;
        public final FrameListener listener;
        public final float scale;

        public FrameListenerAndParams(FrameListener listener, float scale, RendererCommon.GlDrawer drawer, boolean applyFpsReduction) {
            this.listener = listener;
            this.scale = scale;
            this.drawer = drawer;
            this.applyFpsReduction = applyFpsReduction;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class EglSurfaceCreation implements Runnable {
        private Object surface;

        private EglSurfaceCreation() {
        }

        public synchronized void setSurface(Object surface) {
            this.surface = surface;
        }

        @Override // java.lang.Runnable
        public synchronized void run() {
            if (this.surface != null && EglRenderer.this.eglBase != null && !EglRenderer.this.eglBase.hasSurface()) {
                if (this.surface instanceof Surface) {
                    EglRenderer.this.eglBase.createSurface((Surface) this.surface);
                } else if (this.surface instanceof SurfaceTexture) {
                    EglRenderer.this.eglBase.createSurface((SurfaceTexture) this.surface);
                } else {
                    throw new IllegalStateException("Invalid surface: " + this.surface);
                }
                EglRenderer.this.eglBase.makeCurrent();
                GLES20.glPixelStorei(3317, 1);
            }
        }
    }

    public EglRenderer(String name) {
        this(name, new VideoFrameDrawer());
    }

    public EglRenderer(String name, VideoFrameDrawer videoFrameDrawer) {
        this.threadLock = new Object();
        this.eglExceptionCallback = new Runnable() { // from class: org.webrtc.EglRenderer.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (EglRenderer.this.threadLock) {
                    EglRenderer.this.eglThread = null;
                }
            }
        };
        this.frameListeners = new ArrayList<>();
        this.fpsReductionLock = new Object();
        this.drawMatrix = new Matrix();
        this.frameLock = new Object();
        this.layoutLock = new Object();
        this.statisticsLock = new Object();
        this.bitmapTextureFramebuffer = new GlTextureFrameBuffer(6408);
        this.logStatisticsRunnable = new Runnable() { // from class: org.webrtc.EglRenderer.2
            @Override // java.lang.Runnable
            public void run() {
                EglRenderer.this.logStatistics();
                synchronized (EglRenderer.this.threadLock) {
                    if (EglRenderer.this.eglThread != null) {
                        EglRenderer.this.eglThread.getHandler().removeCallbacks(EglRenderer.this.logStatisticsRunnable);
                        EglRenderer.this.eglThread.getHandler().postDelayed(EglRenderer.this.logStatisticsRunnable, TimeUnit.SECONDS.toMillis(EglRenderer.LOG_INTERVAL_SEC));
                    }
                }
            }
        };
        this.eglSurfaceCreationRunnable = new EglSurfaceCreation();
        this.name = name;
        this.frameDrawer = videoFrameDrawer;
    }

    public void init(EglThread eglThread, RendererCommon.GlDrawer drawer, boolean usePresentationTimeStamp) {
        synchronized (this.threadLock) {
            if (this.eglThread != null) {
                throw new IllegalStateException(this.name + "Already initialized");
            }
            logD("Initializing EglRenderer");
            this.eglThread = eglThread;
            this.drawer = drawer;
            this.usePresentationTimeStamp = usePresentationTimeStamp;
            eglThread.addExceptionCallback(this.eglExceptionCallback);
            this.eglBase = eglThread.createEglBaseWithSharedConnection();
            eglThread.getHandler().post(this.eglSurfaceCreationRunnable);
            long currentTimeNs = System.nanoTime();
            resetStatistics(currentTimeNs);
            eglThread.getHandler().postDelayed(this.logStatisticsRunnable, TimeUnit.SECONDS.toMillis(LOG_INTERVAL_SEC));
        }
    }

    public void init(EglBase.Context sharedContext, int[] configAttributes, RendererCommon.GlDrawer drawer, boolean usePresentationTimeStamp) {
        EglThread thread = EglThread.create(null, sharedContext, configAttributes);
        init(thread, drawer, usePresentationTimeStamp);
    }

    public void init(EglBase.Context sharedContext, int[] configAttributes, RendererCommon.GlDrawer drawer) {
        init(sharedContext, configAttributes, drawer, false);
    }

    public void createEglSurface(Surface surface) {
        createEglSurfaceInternal(surface);
    }

    public void createEglSurface(SurfaceTexture surfaceTexture) {
        createEglSurfaceInternal(surfaceTexture);
    }

    private void createEglSurfaceInternal(Object surface) {
        this.eglSurfaceCreationRunnable.setSurface(surface);
        postToRenderThread(this.eglSurfaceCreationRunnable);
    }

    public void release() {
        logD("Releasing.");
        final CountDownLatch eglCleanupBarrier = new CountDownLatch(1);
        synchronized (this.threadLock) {
            if (this.eglThread == null) {
                logD("Already released");
                return;
            }
            this.eglThread.getHandler().removeCallbacks(this.logStatisticsRunnable);
            this.eglThread.removeExceptionCallback(this.eglExceptionCallback);
            this.eglThread.getHandler().postAtFrontOfQueue(new Runnable() { // from class: org.webrtc.EglRenderer$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    EglRenderer.this.m2099lambda$release$0$orgwebrtcEglRenderer(eglCleanupBarrier);
                }
            });
            this.eglThread.release();
            this.eglThread = null;
            ThreadUtils.awaitUninterruptibly(eglCleanupBarrier);
            synchronized (this.frameLock) {
                if (this.pendingFrame != null) {
                    this.pendingFrame.release();
                    this.pendingFrame = null;
                }
            }
            logD("Releasing done.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$release$0$org-webrtc-EglRenderer  reason: not valid java name */
    public /* synthetic */ void m2099lambda$release$0$orgwebrtcEglRenderer(CountDownLatch eglCleanupBarrier) {
        synchronized (EglBase.lock) {
            GLES20.glUseProgram(0);
        }
        if (this.drawer != null) {
            this.drawer.release();
            this.drawer = null;
        }
        this.frameDrawer.release();
        this.bitmapTextureFramebuffer.release();
        if (this.eglBase != null) {
            logD("eglBase detach and release.");
            this.eglBase.detachCurrent();
            this.eglBase.release();
            this.eglBase = null;
        }
        this.frameListeners.clear();
        eglCleanupBarrier.countDown();
    }

    private void resetStatistics(long currentTimeNs) {
        synchronized (this.statisticsLock) {
            this.statisticsStartTimeNs = currentTimeNs;
            this.framesReceived = 0;
            this.framesDropped = 0;
            this.framesRendered = 0;
            this.renderTimeNs = 0L;
            this.renderSwapBufferTimeNs = 0L;
        }
    }

    public void printStackTrace() {
        synchronized (this.threadLock) {
            Thread renderThread = this.eglThread == null ? null : this.eglThread.getHandler().getLooper().getThread();
            if (renderThread != null) {
                StackTraceElement[] renderStackTrace = renderThread.getStackTrace();
                if (renderStackTrace.length > 0) {
                    logW("EglRenderer stack trace:");
                    for (StackTraceElement traceElem : renderStackTrace) {
                        logW(traceElem.toString());
                    }
                }
            }
        }
    }

    public void setMirror(boolean mirror) {
        synchronized (this.layoutLock) {
            this.mirrorHorizontally = mirror;
        }
    }

    public void setMirrorVertically(boolean mirrorVertically) {
        synchronized (this.layoutLock) {
            this.mirrorVertically = mirrorVertically;
        }
    }

    public void setLayoutAspectRatio(float layoutAspectRatio) {
        synchronized (this.layoutLock) {
            this.layoutAspectRatio = layoutAspectRatio;
        }
    }

    public void setFpsReduction(float fps) {
        synchronized (this.fpsReductionLock) {
            long previousRenderPeriodNs = this.minRenderPeriodNs;
            if (fps <= 0.0f) {
                this.minRenderPeriodNs = Long.MAX_VALUE;
            } else {
                this.minRenderPeriodNs = ((float) TimeUnit.SECONDS.toNanos(1L)) / fps;
            }
            if (this.minRenderPeriodNs != previousRenderPeriodNs) {
                this.nextFrameTimeNs = System.nanoTime();
            }
        }
    }

    public void disableFpsReduction() {
        setFpsReduction(Float.POSITIVE_INFINITY);
    }

    public void pauseVideo() {
        setFpsReduction(0.0f);
    }

    public void addFrameListener(FrameListener listener, float scale) {
        addFrameListener(listener, scale, null, false);
    }

    public void addFrameListener(FrameListener listener, float scale, RendererCommon.GlDrawer drawerParam) {
        addFrameListener(listener, scale, drawerParam, false);
    }

    public void addFrameListener(final FrameListener listener, final float scale, final RendererCommon.GlDrawer drawerParam, final boolean applyFpsReduction) {
        postToRenderThread(new Runnable() { // from class: org.webrtc.EglRenderer$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                EglRenderer.this.m2097lambda$addFrameListener$1$orgwebrtcEglRenderer(drawerParam, listener, scale, applyFpsReduction);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$addFrameListener$1$org-webrtc-EglRenderer  reason: not valid java name */
    public /* synthetic */ void m2097lambda$addFrameListener$1$orgwebrtcEglRenderer(RendererCommon.GlDrawer drawerParam, FrameListener listener, float scale, boolean applyFpsReduction) {
        RendererCommon.GlDrawer listenerDrawer = drawerParam == null ? this.drawer : drawerParam;
        this.frameListeners.add(new FrameListenerAndParams(listener, scale, listenerDrawer, applyFpsReduction));
    }

    public void removeFrameListener(final FrameListener listener) {
        final CountDownLatch latch = new CountDownLatch(1);
        synchronized (this.threadLock) {
            if (this.eglThread == null) {
                return;
            }
            if (Thread.currentThread() == this.eglThread.getHandler().getLooper().getThread()) {
                throw new RuntimeException("removeFrameListener must not be called on the render thread.");
            }
            postToRenderThread(new Runnable() { // from class: org.webrtc.EglRenderer$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    EglRenderer.this.m2101lambda$removeFrameListener$2$orgwebrtcEglRenderer(latch, listener);
                }
            });
            ThreadUtils.awaitUninterruptibly(latch);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$removeFrameListener$2$org-webrtc-EglRenderer  reason: not valid java name */
    public /* synthetic */ void m2101lambda$removeFrameListener$2$orgwebrtcEglRenderer(CountDownLatch latch, FrameListener listener) {
        latch.countDown();
        Iterator<FrameListenerAndParams> iter = this.frameListeners.iterator();
        while (iter.hasNext()) {
            if (iter.next().listener == listener) {
                iter.remove();
            }
        }
    }

    public void setErrorCallback(ErrorCallback errorCallback) {
        this.errorCallback = errorCallback;
    }

    @Override // org.webrtc.VideoSink
    public void onFrame(VideoFrame frame) {
        boolean dropOldFrame;
        synchronized (this.statisticsLock) {
            this.framesReceived++;
        }
        synchronized (this.threadLock) {
            if (this.eglThread == null) {
                logD("Dropping frame - Not initialized or already released.");
                return;
            }
            synchronized (this.frameLock) {
                dropOldFrame = this.pendingFrame != null;
                if (dropOldFrame) {
                    this.pendingFrame.release();
                }
                this.pendingFrame = frame;
                this.pendingFrame.retain();
                this.eglThread.getHandler().post(new Runnable() { // from class: org.webrtc.EglRenderer$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        EglRenderer.this.renderFrameOnRenderThread();
                    }
                });
            }
            if (dropOldFrame) {
                synchronized (this.statisticsLock) {
                    this.framesDropped++;
                }
            }
        }
    }

    public void releaseEglSurface(final Runnable completionCallback) {
        this.eglSurfaceCreationRunnable.setSurface(null);
        synchronized (this.threadLock) {
            if (this.eglThread != null) {
                this.eglThread.getHandler().removeCallbacks(this.eglSurfaceCreationRunnable);
                this.eglThread.getHandler().postAtFrontOfQueue(new Runnable() { // from class: org.webrtc.EglRenderer$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        EglRenderer.this.m2100lambda$releaseEglSurface$3$orgwebrtcEglRenderer(completionCallback);
                    }
                });
                return;
            }
            completionCallback.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$releaseEglSurface$3$org-webrtc-EglRenderer  reason: not valid java name */
    public /* synthetic */ void m2100lambda$releaseEglSurface$3$orgwebrtcEglRenderer(Runnable completionCallback) {
        if (this.eglBase != null) {
            this.eglBase.detachCurrent();
            this.eglBase.releaseSurface();
        }
        completionCallback.run();
    }

    private void postToRenderThread(Runnable runnable) {
        synchronized (this.threadLock) {
            if (this.eglThread != null) {
                this.eglThread.getHandler().post(runnable);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: clearSurfaceOnRenderThread */
    public void m2098lambda$clearImage$4$orgwebrtcEglRenderer(float r, float g, float b, float a) {
        if (this.eglBase != null && this.eglBase.hasSurface()) {
            logD("clearSurface");
            this.eglBase.makeCurrent();
            GLES20.glClearColor(r, g, b, a);
            GLES20.glClear(16384);
            this.eglBase.swapBuffers();
        }
    }

    public void clearImage() {
        clearImage(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public void clearImage(final float r, final float g, final float b, final float a) {
        synchronized (this.threadLock) {
            if (this.eglThread == null) {
                return;
            }
            this.eglThread.getHandler().postAtFrontOfQueue(new Runnable() { // from class: org.webrtc.EglRenderer$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    EglRenderer.this.m2098lambda$clearImage$4$orgwebrtcEglRenderer(r, g, b, a);
                }
            });
        }
    }

    private void swapBuffersOnRenderThread(final VideoFrame frame, final long swapBuffersStartTimeNs) {
        synchronized (this.threadLock) {
            if (this.eglThread != null) {
                this.eglThread.scheduleRenderUpdate(new EglThread.RenderUpdate() { // from class: org.webrtc.EglRenderer$$ExternalSyntheticLambda2
                    @Override // org.webrtc.EglThread.RenderUpdate
                    public final void update(boolean z) {
                        EglRenderer.this.m2102lambda$swapBuffersOnRenderThread$5$orgwebrtcEglRenderer(frame, swapBuffersStartTimeNs, z);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$swapBuffersOnRenderThread$5$org-webrtc-EglRenderer  reason: not valid java name */
    public /* synthetic */ void m2102lambda$swapBuffersOnRenderThread$5$orgwebrtcEglRenderer(VideoFrame frame, long swapBuffersStartTimeNs, boolean runsInline) {
        if (!runsInline) {
            if (this.eglBase == null || !this.eglBase.hasSurface()) {
                return;
            }
            this.eglBase.makeCurrent();
        }
        if (this.usePresentationTimeStamp) {
            this.eglBase.swapBuffers(frame.getTimestampNs());
        } else {
            this.eglBase.swapBuffers();
        }
        synchronized (this.statisticsLock) {
            this.renderSwapBufferTimeNs += System.nanoTime() - swapBuffersStartTimeNs;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void renderFrameOnRenderThread() {
        boolean shouldRenderFrame;
        float drawnAspectRatio;
        float scaleX;
        float scaleY;
        synchronized (this.frameLock) {
            if (this.pendingFrame == null) {
                return;
            }
            VideoFrame frame = this.pendingFrame;
            this.pendingFrame = null;
            if (this.eglBase == null || !this.eglBase.hasSurface()) {
                logD("Dropping frame - No surface");
                frame.release();
                return;
            }
            this.eglBase.makeCurrent();
            synchronized (this.fpsReductionLock) {
                if (this.minRenderPeriodNs == Long.MAX_VALUE) {
                    shouldRenderFrame = false;
                } else if (this.minRenderPeriodNs <= 0) {
                    shouldRenderFrame = true;
                } else {
                    long currentTimeNs = System.nanoTime();
                    if (currentTimeNs < this.nextFrameTimeNs) {
                        logD("Skipping frame rendering - fps reduction is active.");
                        shouldRenderFrame = false;
                    } else {
                        this.nextFrameTimeNs += this.minRenderPeriodNs;
                        this.nextFrameTimeNs = Math.max(this.nextFrameTimeNs, currentTimeNs);
                        shouldRenderFrame = true;
                    }
                }
            }
            long startTimeNs = System.nanoTime();
            float frameAspectRatio = frame.getRotatedWidth() / frame.getRotatedHeight();
            synchronized (this.layoutLock) {
                drawnAspectRatio = this.layoutAspectRatio != 0.0f ? this.layoutAspectRatio : frameAspectRatio;
            }
            if (frameAspectRatio > drawnAspectRatio) {
                float scaleX2 = drawnAspectRatio / frameAspectRatio;
                scaleX = scaleX2;
                scaleY = 1.0f;
            } else {
                scaleX = 1.0f;
                scaleY = frameAspectRatio / drawnAspectRatio;
            }
            this.drawMatrix.reset();
            this.drawMatrix.preTranslate(0.5f, 0.5f);
            this.drawMatrix.preScale(this.mirrorHorizontally ? -1.0f : 1.0f, this.mirrorVertically ? -1.0f : 1.0f);
            this.drawMatrix.preScale(scaleX, scaleY);
            this.drawMatrix.preTranslate(-0.5f, -0.5f);
            try {
                if (shouldRenderFrame) {
                    try {
                        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                        GLES20.glClear(16384);
                        try {
                            this.frameDrawer.drawFrame(frame, this.drawer, this.drawMatrix, 0, 0, this.eglBase.surfaceWidth(), this.eglBase.surfaceHeight());
                            long swapBuffersStartTimeNs = System.nanoTime();
                            swapBuffersOnRenderThread(frame, swapBuffersStartTimeNs);
                            synchronized (this.statisticsLock) {
                                this.framesRendered++;
                                this.renderTimeNs += swapBuffersStartTimeNs - startTimeNs;
                            }
                        } catch (GlUtil.GlOutOfMemoryException e) {
                            e = e;
                            logE("Error while drawing frame", e);
                            ErrorCallback errorCallback = this.errorCallback;
                            if (errorCallback != null) {
                                errorCallback.onGlOutOfMemory();
                            }
                            this.drawer.release();
                            this.frameDrawer.release();
                            this.bitmapTextureFramebuffer.release();
                            frame.release();
                        }
                    } catch (GlUtil.GlOutOfMemoryException e2) {
                        e = e2;
                    } catch (Throwable th) {
                        th = th;
                        frame.release();
                        throw th;
                    }
                }
                notifyCallbacks(frame, shouldRenderFrame);
                frame.release();
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    private void notifyCallbacks(VideoFrame frame, boolean wasRendered) {
        if (this.frameListeners.isEmpty()) {
            return;
        }
        this.drawMatrix.reset();
        this.drawMatrix.preTranslate(0.5f, 0.5f);
        this.drawMatrix.preScale(this.mirrorHorizontally ? -1.0f : 1.0f, this.mirrorVertically ? -1.0f : 1.0f);
        this.drawMatrix.preScale(1.0f, -1.0f);
        this.drawMatrix.preTranslate(-0.5f, -0.5f);
        Iterator<FrameListenerAndParams> it = this.frameListeners.iterator();
        while (it.hasNext()) {
            FrameListenerAndParams listenerAndParams = it.next();
            if (wasRendered || !listenerAndParams.applyFpsReduction) {
                it.remove();
                int scaledWidth = (int) (listenerAndParams.scale * frame.getRotatedWidth());
                int scaledHeight = (int) (listenerAndParams.scale * frame.getRotatedHeight());
                if (scaledWidth == 0 || scaledHeight == 0) {
                    listenerAndParams.listener.onFrame(null);
                } else {
                    this.bitmapTextureFramebuffer.setSize(scaledWidth, scaledHeight);
                    GLES20.glBindFramebuffer(36160, this.bitmapTextureFramebuffer.getFrameBufferId());
                    GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.bitmapTextureFramebuffer.getTextureId(), 0);
                    GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                    GLES20.glClear(16384);
                    this.frameDrawer.drawFrame(frame, listenerAndParams.drawer, this.drawMatrix, 0, 0, scaledWidth, scaledHeight);
                    ByteBuffer bitmapBuffer = ByteBuffer.allocateDirect(scaledWidth * scaledHeight * 4);
                    GLES20.glViewport(0, 0, scaledWidth, scaledHeight);
                    GLES20.glReadPixels(0, 0, scaledWidth, scaledHeight, 6408, 5121, bitmapBuffer);
                    GLES20.glBindFramebuffer(36160, 0);
                    GlUtil.checkNoGLES2Error("EglRenderer.notifyCallbacks");
                    Bitmap bitmap = Bitmap.createBitmap(scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888);
                    bitmap.copyPixelsFromBuffer(bitmapBuffer);
                    listenerAndParams.listener.onFrame(bitmap);
                }
            }
        }
    }

    private String averageTimeAsString(long sumTimeNs, int count) {
        return count <= 0 ? "NA" : TimeUnit.NANOSECONDS.toMicros(sumTimeNs / count) + " us";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logStatistics() {
        DecimalFormat fpsFormat = new DecimalFormat("#.0");
        long currentTimeNs = System.nanoTime();
        synchronized (this.statisticsLock) {
            long elapsedTimeNs = currentTimeNs - this.statisticsStartTimeNs;
            if (elapsedTimeNs > 0 && (this.minRenderPeriodNs != Long.MAX_VALUE || this.framesReceived != 0)) {
                float renderFps = ((float) (this.framesRendered * TimeUnit.SECONDS.toNanos(1L))) / ((float) elapsedTimeNs);
                logD("Duration: " + TimeUnit.NANOSECONDS.toMillis(elapsedTimeNs) + " ms. Frames received: " + this.framesReceived + ". Dropped: " + this.framesDropped + ". Rendered: " + this.framesRendered + ". Render fps: " + fpsFormat.format(renderFps) + ". Average render time: " + averageTimeAsString(this.renderTimeNs, this.framesRendered) + ". Average swapBuffer time: " + averageTimeAsString(this.renderSwapBufferTimeNs, this.framesRendered) + ".");
                resetStatistics(currentTimeNs);
            }
        }
    }

    private void logE(String string, Throwable e) {
        Logging.e(TAG, this.name + string, e);
    }

    private void logD(String string) {
        Logging.d(TAG, this.name + string);
    }

    private void logW(String string) {
        Logging.w(TAG, this.name + string);
    }
}
