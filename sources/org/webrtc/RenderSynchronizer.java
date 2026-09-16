package org.webrtc;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Trace;
import android.view.Choreographer;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
/* loaded from: classes5.dex */
public final class RenderSynchronizer {
    private static final float DEFAULT_TARGET_FPS = 30.0f;
    private static final String TAG = "RenderSynchronizer";
    private Choreographer choreographer;
    private boolean isListening;
    private long lastOpenedTimeNanos;
    private long lastRefreshTimeNanos;
    private final List<Listener> listeners;
    private final Object lock;
    private final Handler mainThreadHandler;
    private boolean renderWindowOpen;
    private final long targetFrameIntervalNanos;

    /* loaded from: classes5.dex */
    public interface Listener {
        void onRenderWindowClose();

        void onRenderWindowOpen();
    }

    public RenderSynchronizer(float targetFrameRateFps) {
        this.lock = new Object();
        this.listeners = new CopyOnWriteArrayList();
        this.targetFrameIntervalNanos = Math.round(((float) TimeUnit.SECONDS.toNanos(1L)) / targetFrameRateFps);
        this.mainThreadHandler = new Handler(Looper.getMainLooper());
        this.mainThreadHandler.post(new Runnable() { // from class: org.webrtc.RenderSynchronizer$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                RenderSynchronizer.this.m2128lambda$new$0$orgwebrtcRenderSynchronizer();
            }
        });
        Logging.d(TAG, "Created");
    }

    /* renamed from: lambda$new$0$org-webrtc-RenderSynchronizer */
    public /* synthetic */ void m2128lambda$new$0$orgwebrtcRenderSynchronizer() {
        this.choreographer = Choreographer.getInstance();
    }

    public RenderSynchronizer() {
        this(DEFAULT_TARGET_FPS);
    }

    public void registerListener(Listener listener) {
        this.listeners.add(listener);
        synchronized (this.lock) {
            if (!this.isListening) {
                Logging.d(TAG, "First listener, subscribing to frame callbacks");
                this.isListening = true;
                this.mainThreadHandler.post(new Runnable() { // from class: org.webrtc.RenderSynchronizer$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        RenderSynchronizer.this.m2129lambda$registerListener$1$orgwebrtcRenderSynchronizer();
                    }
                });
            }
        }
    }

    /* renamed from: lambda$registerListener$1$org-webrtc-RenderSynchronizer */
    public /* synthetic */ void m2129lambda$registerListener$1$orgwebrtcRenderSynchronizer() {
        this.choreographer.postFrameCallback(new RenderSynchronizer$$ExternalSyntheticLambda0(this));
    }

    public void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }

    public void onDisplayRefreshCycleBegin(long refreshTimeNanos) {
        synchronized (this.lock) {
            if (this.listeners.isEmpty()) {
                Logging.d(TAG, "No listeners, unsubscribing to frame callbacks");
                this.isListening = false;
                return;
            }
            this.choreographer.postFrameCallback(new RenderSynchronizer$$ExternalSyntheticLambda0(this));
            long lastOpenDeltaNanos = refreshTimeNanos - this.lastOpenedTimeNanos;
            long refreshDeltaNanos = refreshTimeNanos - this.lastRefreshTimeNanos;
            this.lastRefreshTimeNanos = refreshTimeNanos;
            if (Math.abs(lastOpenDeltaNanos - this.targetFrameIntervalNanos) < Math.abs((lastOpenDeltaNanos - this.targetFrameIntervalNanos) + refreshDeltaNanos)) {
                this.lastOpenedTimeNanos = refreshTimeNanos;
                openRenderWindow();
            } else if (this.renderWindowOpen) {
                closeRenderWindow();
            }
        }
    }

    private void traceRenderWindowChange() {
        if (Build.VERSION.SDK_INT >= 29) {
            Trace.setCounter("RenderWindow", this.renderWindowOpen ? 1L : 0L);
        }
    }

    private void openRenderWindow() {
        this.renderWindowOpen = true;
        traceRenderWindowChange();
        for (Listener listener : this.listeners) {
            listener.onRenderWindowOpen();
        }
    }

    private void closeRenderWindow() {
        this.renderWindowOpen = false;
        traceRenderWindowChange();
        for (Listener listener : this.listeners) {
            listener.onRenderWindowClose();
        }
    }
}
