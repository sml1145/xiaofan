package org.webrtc;

import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.opengl.GLException;
import android.view.Surface;
import org.webrtc.EglBase14;
import org.webrtc.EglBase14Impl;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class EglBase14Impl implements EglBase14 {
    private static final EglConnection EGL_NO_CONNECTION = new EglConnection();
    private static final String TAG = "EglBase14Impl";
    private EglConnection eglConnection;
    private EGLSurface eglSurface = EGL14.EGL_NO_SURFACE;

    /* renamed from: -$$Nest$smgetEglDisplay  reason: not valid java name */
    static /* bridge */ /* synthetic */ EGLDisplay m2082$$Nest$smgetEglDisplay() {
        return getEglDisplay();
    }

    /* loaded from: classes5.dex */
    public static class Context implements EglBase14.Context {
        private final EGLContext egl14Context;

        @Override // org.webrtc.EglBase14.Context
        public EGLContext getRawContext() {
            return this.egl14Context;
        }

        @Override // org.webrtc.EglBase.Context
        public long getNativeEglContext() {
            return this.egl14Context.getNativeHandle();
        }

        public Context(EGLContext eglContext) {
            this.egl14Context = eglContext;
        }
    }

    /* loaded from: classes5.dex */
    public static class EglConnection implements EglBase14.EglConnection {
        private EGLSurface currentSurface;
        private final EGLConfig eglConfig;
        private final EGLContext eglContext;
        private final EGLDisplay eglDisplay;
        private final RefCountDelegate refCountDelegate;

        public EglConnection(EGLContext sharedContext, int[] configAttributes) {
            this.currentSurface = EGL14.EGL_NO_SURFACE;
            this.eglDisplay = EglBase14Impl.m2082$$Nest$smgetEglDisplay();
            this.eglConfig = EglBase14Impl.getEglConfig(this.eglDisplay, configAttributes);
            int openGlesVersion = EglBase.getOpenGlesVersionFromConfig(configAttributes);
            Logging.d(EglBase14Impl.TAG, "Using OpenGL ES version " + openGlesVersion);
            this.eglContext = EglBase14Impl.createEglContext(sharedContext, this.eglDisplay, this.eglConfig, openGlesVersion);
            this.refCountDelegate = new RefCountDelegate(new Runnable() { // from class: org.webrtc.EglBase14Impl$EglConnection$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    EglBase14Impl.EglConnection.this.m2083lambda$new$0$orgwebrtcEglBase14Impl$EglConnection();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$0$org-webrtc-EglBase14Impl$EglConnection  reason: not valid java name */
        public /* synthetic */ void m2083lambda$new$0$orgwebrtcEglBase14Impl$EglConnection() {
            synchronized (EglBase.lock) {
                EGL14.eglMakeCurrent(this.eglDisplay, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
                EGL14.eglDestroyContext(this.eglDisplay, this.eglContext);
            }
            EGL14.eglReleaseThread();
            EGL14.eglTerminate(this.eglDisplay);
            this.currentSurface = EGL14.EGL_NO_SURFACE;
        }

        private EglConnection() {
            this.currentSurface = EGL14.EGL_NO_SURFACE;
            this.eglContext = EGL14.EGL_NO_CONTEXT;
            this.eglDisplay = EGL14.EGL_NO_DISPLAY;
            this.eglConfig = null;
            this.refCountDelegate = new RefCountDelegate(new Runnable() { // from class: org.webrtc.EglBase14Impl$EglConnection$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    EglBase14Impl.EglConnection.lambda$new$1();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$new$1() {
        }

        @Override // org.webrtc.RefCounted
        public void retain() {
            this.refCountDelegate.retain();
        }

        @Override // org.webrtc.RefCounted
        public void release() {
            this.refCountDelegate.release();
        }

        @Override // org.webrtc.EglBase14.EglConnection
        public EGLContext getContext() {
            return this.eglContext;
        }

        @Override // org.webrtc.EglBase14.EglConnection
        public EGLDisplay getDisplay() {
            return this.eglDisplay;
        }

        @Override // org.webrtc.EglBase14.EglConnection
        public EGLConfig getConfig() {
            return this.eglConfig;
        }

        public void makeCurrent(EGLSurface eglSurface) {
            if (EGL14.eglGetCurrentContext() == this.eglContext && this.currentSurface == eglSurface) {
                return;
            }
            synchronized (EglBase.lock) {
                if (!EGL14.eglMakeCurrent(this.eglDisplay, eglSurface, eglSurface, this.eglContext)) {
                    throw new GLException(EGL14.eglGetError(), "eglMakeCurrent failed: 0x" + Integer.toHexString(EGL14.eglGetError()));
                }
            }
            this.currentSurface = eglSurface;
        }

        public void detachCurrent() {
            synchronized (EglBase.lock) {
                if (!EGL14.eglMakeCurrent(this.eglDisplay, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT)) {
                    throw new GLException(EGL14.eglGetError(), "eglDetachCurrent failed: 0x" + Integer.toHexString(EGL14.eglGetError()));
                }
            }
            this.currentSurface = EGL14.EGL_NO_SURFACE;
        }
    }

    public EglBase14Impl(EGLContext sharedContext, int[] configAttributes) {
        this.eglConnection = new EglConnection(sharedContext, configAttributes);
    }

    public EglBase14Impl(EglConnection eglConnection) {
        this.eglConnection = eglConnection;
        this.eglConnection.retain();
    }

    @Override // org.webrtc.EglBase
    public void createSurface(Surface surface) {
        createSurfaceInternal(surface);
    }

    @Override // org.webrtc.EglBase
    public void createSurface(SurfaceTexture surfaceTexture) {
        createSurfaceInternal(surfaceTexture);
    }

    private void createSurfaceInternal(Object surface) {
        if (!(surface instanceof Surface) && !(surface instanceof SurfaceTexture)) {
            throw new IllegalStateException("Input must be either a Surface or SurfaceTexture");
        }
        checkIsNotReleased();
        if (this.eglSurface != EGL14.EGL_NO_SURFACE) {
            throw new RuntimeException("Already has an EGLSurface");
        }
        int[] surfaceAttribs = {12344};
        this.eglSurface = EGL14.eglCreateWindowSurface(this.eglConnection.getDisplay(), this.eglConnection.getConfig(), surface, surfaceAttribs, 0);
        if (this.eglSurface == EGL14.EGL_NO_SURFACE) {
            throw new GLException(EGL14.eglGetError(), "Failed to create window surface: 0x" + Integer.toHexString(EGL14.eglGetError()));
        }
    }

    @Override // org.webrtc.EglBase
    public void createDummyPbufferSurface() {
        createPbufferSurface(1, 1);
    }

    @Override // org.webrtc.EglBase
    public void createPbufferSurface(int width, int height) {
        checkIsNotReleased();
        if (this.eglSurface != EGL14.EGL_NO_SURFACE) {
            throw new RuntimeException("Already has an EGLSurface");
        }
        int[] surfaceAttribs = {12375, width, 12374, height, 12344};
        this.eglSurface = EGL14.eglCreatePbufferSurface(this.eglConnection.getDisplay(), this.eglConnection.getConfig(), surfaceAttribs, 0);
        if (this.eglSurface == EGL14.EGL_NO_SURFACE) {
            throw new GLException(EGL14.eglGetError(), "Failed to create pixel buffer surface with size " + width + "x" + height + ": 0x" + Integer.toHexString(EGL14.eglGetError()));
        }
    }

    @Override // org.webrtc.EglBase
    public Context getEglBaseContext() {
        return new Context(this.eglConnection.getContext());
    }

    @Override // org.webrtc.EglBase
    public boolean hasSurface() {
        return this.eglSurface != EGL14.EGL_NO_SURFACE;
    }

    @Override // org.webrtc.EglBase
    public int surfaceWidth() {
        int[] widthArray = new int[1];
        EGL14.eglQuerySurface(this.eglConnection.getDisplay(), this.eglSurface, 12375, widthArray, 0);
        return widthArray[0];
    }

    @Override // org.webrtc.EglBase
    public int surfaceHeight() {
        int[] heightArray = new int[1];
        EGL14.eglQuerySurface(this.eglConnection.getDisplay(), this.eglSurface, 12374, heightArray, 0);
        return heightArray[0];
    }

    @Override // org.webrtc.EglBase
    public void releaseSurface() {
        if (this.eglSurface != EGL14.EGL_NO_SURFACE) {
            EGL14.eglDestroySurface(this.eglConnection.getDisplay(), this.eglSurface);
            this.eglSurface = EGL14.EGL_NO_SURFACE;
        }
    }

    private void checkIsNotReleased() {
        if (this.eglConnection == EGL_NO_CONNECTION) {
            throw new RuntimeException("This object has been released");
        }
    }

    @Override // org.webrtc.EglBase
    public void release() {
        checkIsNotReleased();
        releaseSurface();
        this.eglConnection.release();
        this.eglConnection = EGL_NO_CONNECTION;
    }

    @Override // org.webrtc.EglBase
    public void makeCurrent() {
        checkIsNotReleased();
        if (this.eglSurface == EGL14.EGL_NO_SURFACE) {
            throw new RuntimeException("No EGLSurface - can't make current");
        }
        this.eglConnection.makeCurrent(this.eglSurface);
    }

    @Override // org.webrtc.EglBase
    public void detachCurrent() {
        this.eglConnection.detachCurrent();
    }

    @Override // org.webrtc.EglBase
    public void swapBuffers() {
        checkIsNotReleased();
        if (this.eglSurface == EGL14.EGL_NO_SURFACE) {
            throw new RuntimeException("No EGLSurface - can't swap buffers");
        }
        synchronized (EglBase.lock) {
            EGL14.eglSwapBuffers(this.eglConnection.getDisplay(), this.eglSurface);
        }
    }

    @Override // org.webrtc.EglBase
    public void swapBuffers(long timeStampNs) {
        checkIsNotReleased();
        if (this.eglSurface == EGL14.EGL_NO_SURFACE) {
            throw new RuntimeException("No EGLSurface - can't swap buffers");
        }
        synchronized (EglBase.lock) {
            EGLExt.eglPresentationTimeANDROID(this.eglConnection.getDisplay(), this.eglSurface, timeStampNs);
            EGL14.eglSwapBuffers(this.eglConnection.getDisplay(), this.eglSurface);
        }
    }

    private static EGLDisplay getEglDisplay() {
        EGLDisplay eglDisplay = EGL14.eglGetDisplay(0);
        if (eglDisplay == EGL14.EGL_NO_DISPLAY) {
            throw new GLException(EGL14.eglGetError(), "Unable to get EGL14 display: 0x" + Integer.toHexString(EGL14.eglGetError()));
        }
        int[] version = new int[2];
        if (!EGL14.eglInitialize(eglDisplay, version, 0, version, 1)) {
            throw new GLException(EGL14.eglGetError(), "Unable to initialize EGL14: 0x" + Integer.toHexString(EGL14.eglGetError()));
        }
        return eglDisplay;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static EGLConfig getEglConfig(EGLDisplay eglDisplay, int[] configAttributes) {
        EGLConfig[] configs = new EGLConfig[1];
        int[] numConfigs = new int[1];
        if (!EGL14.eglChooseConfig(eglDisplay, configAttributes, 0, configs, 0, configs.length, numConfigs, 0)) {
            throw new GLException(EGL14.eglGetError(), "eglChooseConfig failed: 0x" + Integer.toHexString(EGL14.eglGetError()));
        }
        if (numConfigs[0] <= 0) {
            throw new RuntimeException("Unable to find any matching EGL config");
        }
        EGLConfig eglConfig = configs[0];
        if (eglConfig == null) {
            throw new RuntimeException("eglChooseConfig returned null");
        }
        return eglConfig;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static EGLContext createEglContext(EGLContext sharedContext, EGLDisplay eglDisplay, EGLConfig eglConfig, int openGlesVersion) {
        EGLContext eglContext;
        if (sharedContext != null && sharedContext == EGL14.EGL_NO_CONTEXT) {
            throw new RuntimeException("Invalid sharedContext");
        }
        int[] contextAttributes = {12440, openGlesVersion, 12344};
        EGLContext rootContext = sharedContext == null ? EGL14.EGL_NO_CONTEXT : sharedContext;
        synchronized (EglBase.lock) {
            eglContext = EGL14.eglCreateContext(eglDisplay, eglConfig, rootContext, contextAttributes, 0);
        }
        if (eglContext == EGL14.EGL_NO_CONTEXT) {
            throw new GLException(EGL14.eglGetError(), "Failed to create EGL context: 0x" + Integer.toHexString(EGL14.eglGetError()));
        }
        return eglContext;
    }
}
