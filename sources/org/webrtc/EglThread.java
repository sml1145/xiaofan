package org.webrtc;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import org.webrtc.EglBase;
import org.webrtc.RenderSynchronizer;
/* loaded from: classes5.dex */
public class EglThread implements RenderSynchronizer.Listener {
    private final EglBase.EglConnection eglConnection;
    private final HandlerWithExceptionCallbacks handler;
    private final ReleaseMonitor releaseMonitor;
    private final RenderSynchronizer renderSynchronizer;
    private final List<RenderUpdate> pendingRenderUpdates = new ArrayList();
    private boolean renderWindowOpen = true;

    /* loaded from: classes5.dex */
    public interface ReleaseMonitor {
        boolean onRelease(EglThread eglThread);
    }

    /* loaded from: classes5.dex */
    public interface RenderUpdate {
        void update(boolean z);
    }

    public static EglThread create(ReleaseMonitor releaseMonitor, final EglBase.Context sharedContext, final int[] configAttributes, RenderSynchronizer renderSynchronizer) {
        HandlerThread renderThread = new HandlerThread("EglThread");
        renderThread.start();
        HandlerWithExceptionCallbacks handler = new HandlerWithExceptionCallbacks(renderThread.getLooper());
        EglBase.EglConnection eglConnection = (EglBase.EglConnection) ThreadUtils.invokeAtFrontUninterruptibly(handler, new Callable() { // from class: org.webrtc.EglThread$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return EglThread.lambda$create$0(EglBase.Context.this, configAttributes);
            }
        });
        return new EglThread(releaseMonitor != null ? releaseMonitor : new ReleaseMonitor() { // from class: org.webrtc.EglThread$$ExternalSyntheticLambda3
            @Override // org.webrtc.EglThread.ReleaseMonitor
            public final boolean onRelease(EglThread eglThread) {
                return EglThread.lambda$create$1(eglThread);
            }
        }, handler, eglConnection, renderSynchronizer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ EglBase.EglConnection lambda$create$0(EglBase.Context sharedContext, int[] configAttributes) throws Exception {
        if (sharedContext == null) {
            return EglBase.EglConnection.createEgl10(configAttributes);
        }
        return EglBase.EglConnection.create(sharedContext, configAttributes);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$create$1(EglThread eglThread) {
        return true;
    }

    public static EglThread create(ReleaseMonitor releaseMonitor, EglBase.Context sharedContext, int[] configAttributes) {
        return create(releaseMonitor, sharedContext, configAttributes, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class HandlerWithExceptionCallbacks extends Handler {
        private final Object callbackLock;
        private final List<Runnable> exceptionCallbacks;

        public HandlerWithExceptionCallbacks(Looper looper) {
            super(looper);
            this.callbackLock = new Object();
            this.exceptionCallbacks = new ArrayList();
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message msg) {
            try {
                super.dispatchMessage(msg);
            } catch (Exception e) {
                Logging.e("EglThread", "Exception on EglThread", e);
                synchronized (this.callbackLock) {
                    for (Runnable callback : this.exceptionCallbacks) {
                        callback.run();
                    }
                    throw e;
                }
            }
        }

        public void addExceptionCallback(Runnable callback) {
            synchronized (this.callbackLock) {
                this.exceptionCallbacks.add(callback);
            }
        }

        public void removeExceptionCallback(Runnable callback) {
            synchronized (this.callbackLock) {
                this.exceptionCallbacks.remove(callback);
            }
        }
    }

    private EglThread(ReleaseMonitor releaseMonitor, HandlerWithExceptionCallbacks handler, EglBase.EglConnection eglConnection, RenderSynchronizer renderSynchronizer) {
        this.releaseMonitor = releaseMonitor;
        this.handler = handler;
        this.eglConnection = eglConnection;
        this.renderSynchronizer = renderSynchronizer;
        if (renderSynchronizer != null) {
            renderSynchronizer.registerListener(this);
        }
    }

    public void release() {
        if (!this.releaseMonitor.onRelease(this)) {
            return;
        }
        if (this.renderSynchronizer != null) {
            this.renderSynchronizer.removeListener(this);
        }
        HandlerWithExceptionCallbacks handlerWithExceptionCallbacks = this.handler;
        final EglBase.EglConnection eglConnection = this.eglConnection;
        Objects.requireNonNull(eglConnection);
        handlerWithExceptionCallbacks.post(new Runnable() { // from class: org.webrtc.EglThread$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                EglBase.EglConnection.this.release();
            }
        });
        this.handler.getLooper().quitSafely();
    }

    public EglBase createEglBaseWithSharedConnection() {
        return EglBase.create(this.eglConnection);
    }

    public Handler getHandler() {
        return this.handler;
    }

    public void addExceptionCallback(Runnable callback) {
        this.handler.addExceptionCallback(callback);
    }

    public void removeExceptionCallback(Runnable callback) {
        this.handler.removeExceptionCallback(callback);
    }

    public void scheduleRenderUpdate(RenderUpdate update) {
        if (this.renderWindowOpen) {
            update.update(true);
        } else {
            this.pendingRenderUpdates.add(update);
        }
    }

    @Override // org.webrtc.RenderSynchronizer.Listener
    public void onRenderWindowOpen() {
        this.handler.post(new Runnable() { // from class: org.webrtc.EglThread$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                EglThread.this.m2107lambda$onRenderWindowOpen$2$orgwebrtcEglThread();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onRenderWindowOpen$2$org-webrtc-EglThread  reason: not valid java name */
    public /* synthetic */ void m2107lambda$onRenderWindowOpen$2$orgwebrtcEglThread() {
        this.renderWindowOpen = true;
        for (RenderUpdate update : this.pendingRenderUpdates) {
            update.update(false);
        }
        this.pendingRenderUpdates.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onRenderWindowClose$3$org-webrtc-EglThread  reason: not valid java name */
    public /* synthetic */ void m2106lambda$onRenderWindowClose$3$orgwebrtcEglThread() {
        this.renderWindowOpen = false;
    }

    @Override // org.webrtc.RenderSynchronizer.Listener
    public void onRenderWindowClose() {
        this.handler.post(new Runnable() { // from class: org.webrtc.EglThread$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                EglThread.this.m2106lambda$onRenderWindowClose$3$orgwebrtcEglThread();
            }
        });
    }
}
