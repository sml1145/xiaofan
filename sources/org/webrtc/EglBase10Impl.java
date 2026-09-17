package org.webrtc;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.opengl.GLException;
import android.view.Surface;
import android.view.SurfaceHolder;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import org.webrtc.EglBase;
import org.webrtc.EglBase10;
import org.webrtc.EglBase10Impl;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class EglBase10Impl implements EglBase10 {
    private static final int EGL_CONTEXT_CLIENT_VERSION = 12440;
    private static final EglConnection EGL_NO_CONNECTION = new EglConnection();
    private static final String TAG = "EglBase10Impl";
    private EglConnection eglConnection;
    private EGLSurface eglSurface = EGL10.EGL_NO_SURFACE;

    /* renamed from: -$$Nest$smnativeGetCurrentNativeEGLContext  reason: not valid java name */
    static /* bridge */ /* synthetic */ long m2092$$Nest$smnativeGetCurrentNativeEGLContext() {
        return nativeGetCurrentNativeEGLContext();
    }

    private static native long nativeGetCurrentNativeEGLContext();

    /* loaded from: classes5.dex */
    private static class Context implements EglBase10.Context {
        private final EGL10 egl;
        private final EGLContext eglContext;
        private final EGLConfig eglContextConfig;

        @Override // org.webrtc.EglBase10.Context
        public EGLContext getRawContext() {
            return this.eglContext;
        }

        @Override // org.webrtc.EglBase.Context
        public long getNativeEglContext() {
            EGLContext previousContext = this.egl.eglGetCurrentContext();
            EGLDisplay currentDisplay = this.egl.eglGetCurrentDisplay();
            EGLSurface previousDrawSurface = this.egl.eglGetCurrentSurface(12377);
            EGLSurface previousReadSurface = this.egl.eglGetCurrentSurface(12378);
            EGLSurface tempEglSurface = null;
            if (currentDisplay == EGL10.EGL_NO_DISPLAY) {
                currentDisplay = this.egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            }
            try {
                if (previousContext != this.eglContext) {
                    int[] surfaceAttribs = {12375, 1, 12374, 1, 12344};
                    tempEglSurface = this.egl.eglCreatePbufferSurface(currentDisplay, this.eglContextConfig, surfaceAttribs);
                    if (!this.egl.eglMakeCurrent(currentDisplay, tempEglSurface, tempEglSurface, this.eglContext)) {
                        throw new GLException(this.egl.eglGetError(), "Failed to make temporary EGL surface active: " + this.egl.eglGetError());
                    }
                }
                return EglBase10Impl.m2092$$Nest$smnativeGetCurrentNativeEGLContext();
            } finally {
                if (0 != 0) {
                    this.egl.eglMakeCurrent(currentDisplay, previousDrawSurface, previousReadSurface, previousContext);
                    this.egl.eglDestroySurface(currentDisplay, null);
                }
            }
        }

        public Context(EGL10 egl, EGLContext eglContext, EGLConfig eglContextConfig) {
            this.egl = egl;
            this.eglContext = eglContext;
            this.eglContextConfig = eglContextConfig;
        }
    }

    /* loaded from: classes5.dex */
    public static class EglConnection implements EglBase10.EglConnection {
        private EGLSurface currentSurface;
        private final EGL10 egl;
        private final EGLConfig eglConfig;
        private final EGLContext eglContext;
        private final EGLDisplay eglDisplay;
        private final RefCountDelegate refCountDelegate;

        public EglConnection(EGLContext sharedContext, int[] configAttributes) {
            this.currentSurface = EGL10.EGL_NO_SURFACE;
            this.egl = (EGL10) EGLContext.getEGL();
            this.eglDisplay = EglBase10Impl.getEglDisplay(this.egl);
            this.eglConfig = EglBase10Impl.getEglConfig(this.egl, this.eglDisplay, configAttributes);
            int openGlesVersion = EglBase.getOpenGlesVersionFromConfig(configAttributes);
            Logging.d(EglBase10Impl.TAG, "Using OpenGL ES version " + openGlesVersion);
            this.eglContext = EglBase10Impl.createEglContext(this.egl, sharedContext, this.eglDisplay, this.eglConfig, openGlesVersion);
            this.refCountDelegate = new RefCountDelegate(new Runnable() { // from class: org.webrtc.EglBase10Impl$EglConnection$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    EglBase10Impl.EglConnection.this.m2093lambda$new$0$orgwebrtcEglBase10Impl$EglConnection();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$0$org-webrtc-EglBase10Impl$EglConnection  reason: not valid java name */
        public /* synthetic */ void m2093lambda$new$0$orgwebrtcEglBase10Impl$EglConnection() {
            synchronized (EglBase.lock) {
                this.egl.eglMakeCurrent(this.eglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
            }
            this.egl.eglDestroyContext(this.eglDisplay, this.eglContext);
            this.egl.eglTerminate(this.eglDisplay);
            this.currentSurface = EGL10.EGL_NO_SURFACE;
        }

        private EglConnection() {
            this.currentSurface = EGL10.EGL_NO_SURFACE;
            this.egl = (EGL10) EGLContext.getEGL();
            this.eglContext = EGL10.EGL_NO_CONTEXT;
            this.eglDisplay = EGL10.EGL_NO_DISPLAY;
            this.eglConfig = null;
            this.refCountDelegate = new RefCountDelegate(new Runnable() { // from class: org.webrtc.EglBase10Impl$EglConnection$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    EglBase10Impl.EglConnection.lambda$new$1();
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

        @Override // org.webrtc.EglBase10.EglConnection
        public EGL10 getEgl() {
            return this.egl;
        }

        @Override // org.webrtc.EglBase10.EglConnection
        public EGLContext getContext() {
            return this.eglContext;
        }

        @Override // org.webrtc.EglBase10.EglConnection
        public EGLDisplay getDisplay() {
            return this.eglDisplay;
        }

        @Override // org.webrtc.EglBase10.EglConnection
        public EGLConfig getConfig() {
            return this.eglConfig;
        }

        public void makeCurrent(EGLSurface eglSurface) {
            if (this.egl.eglGetCurrentContext() == this.eglContext && this.currentSurface == eglSurface) {
                return;
            }
            synchronized (EglBase.lock) {
                if (!this.egl.eglMakeCurrent(this.eglDisplay, eglSurface, eglSurface, this.eglContext)) {
                    throw new GLException(this.egl.eglGetError(), "eglMakeCurrent failed: 0x" + Integer.toHexString(this.egl.eglGetError()));
                }
            }
            this.currentSurface = eglSurface;
        }

        public void detachCurrent() {
            synchronized (EglBase.lock) {
                if (!this.egl.eglMakeCurrent(this.eglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT)) {
                    throw new GLException(this.egl.eglGetError(), "eglDetachCurrent failed: 0x" + Integer.toHexString(this.egl.eglGetError()));
                }
            }
            this.currentSurface = EGL10.EGL_NO_SURFACE;
        }
    }

    public EglBase10Impl(EGLContext sharedContext, int[] configAttributes) {
        this.eglConnection = new EglConnection(sharedContext, configAttributes);
    }

    public EglBase10Impl(EglConnection eglConnection) {
        this.eglConnection = eglConnection;
        this.eglConnection.retain();
    }

    @Override // org.webrtc.EglBase
    public void createSurface(Surface surface) {
        createSurfaceInternal(new SurfaceHolder(surface) { // from class: org.webrtc.EglBase10Impl.1FakeSurfaceHolder
            private final Surface surface;

            {
                this.surface = surface;
            }

            @Override // android.view.SurfaceHolder
            public void addCallback(SurfaceHolder.Callback callback) {
            }

            @Override // android.view.SurfaceHolder
            public void removeCallback(SurfaceHolder.Callback callback) {
            }

            @Override // android.view.SurfaceHolder
            public boolean isCreating() {
                return false;
            }

            @Override // android.view.SurfaceHolder
            @Deprecated
            public void setType(int i) {
            }

            @Override // android.view.SurfaceHolder
            public void setFixedSize(int i, int i2) {
            }

            @Override // android.view.SurfaceHolder
            public void setSizeFromLayout() {
            }

            @Override // android.view.SurfaceHolder
            public void setFormat(int i) {
            }

            @Override // android.view.SurfaceHolder
            public void setKeepScreenOn(boolean b) {
            }

            @Override // android.view.SurfaceHolder
            public Canvas lockCanvas() {
                return null;
            }

            @Override // android.view.SurfaceHolder
            public Canvas lockCanvas(Rect rect) {
                return null;
            }

            @Override // android.view.SurfaceHolder
            public void unlockCanvasAndPost(Canvas canvas) {
            }

            @Override // android.view.SurfaceHolder
            public Rect getSurfaceFrame() {
                return null;
            }

            @Override // android.view.SurfaceHolder
            public Surface getSurface() {
                return this.surface;
            }
        });
    }

    @Override // org.webrtc.EglBase
    public void createSurface(SurfaceTexture surfaceTexture) {
        createSurfaceInternal(surfaceTexture);
    }

    private void createSurfaceInternal(Object nativeWindow) {
        if (!(nativeWindow instanceof SurfaceHolder) && !(nativeWindow instanceof SurfaceTexture)) {
            throw new IllegalStateException("Input must be either a SurfaceHolder or SurfaceTexture");
        }
        checkIsNotReleased();
        if (this.eglSurface != EGL10.EGL_NO_SURFACE) {
            throw new RuntimeException("Already has an EGLSurface");
        }
        EGL10 egl = this.eglConnection.getEgl();
        int[] surfaceAttribs = {12344};
        this.eglSurface = egl.eglCreateWindowSurface(this.eglConnection.getDisplay(), this.eglConnection.getConfig(), nativeWindow, surfaceAttribs);
        if (this.eglSurface == EGL10.EGL_NO_SURFACE) {
            throw new GLException(egl.eglGetError(), "Failed to create window surface: 0x" + Integer.toHexString(egl.eglGetError()));
        }
    }

    @Override // org.webrtc.EglBase
    public void createDummyPbufferSurface() {
        createPbufferSurface(1, 1);
    }

    @Override // org.webrtc.EglBase
    public void createPbufferSurface(int width, int height) {
        checkIsNotReleased();
        if (this.eglSurface != EGL10.EGL_NO_SURFACE) {
            throw new RuntimeException("Already has an EGLSurface");
        }
        EGL10 egl = this.eglConnection.getEgl();
        int[] surfaceAttribs = {12375, width, 12374, height, 12344};
        this.eglSurface = egl.eglCreatePbufferSurface(this.eglConnection.getDisplay(), this.eglConnection.getConfig(), surfaceAttribs);
        if (this.eglSurface == EGL10.EGL_NO_SURFACE) {
            throw new GLException(egl.eglGetError(), "Failed to create pixel buffer surface with size " + width + "x" + height + ": 0x" + Integer.toHexString(egl.eglGetError()));
        }
    }

    @Override // org.webrtc.EglBase
    public EglBase.Context getEglBaseContext() {
        return new Context(this.eglConnection.getEgl(), this.eglConnection.getContext(), this.eglConnection.getConfig());
    }

    @Override // org.webrtc.EglBase
    public boolean hasSurface() {
        return this.eglSurface != EGL10.EGL_NO_SURFACE;
    }

    @Override // org.webrtc.EglBase
    public int surfaceWidth() {
        int[] widthArray = new int[1];
        this.eglConnection.getEgl().eglQuerySurface(this.eglConnection.getDisplay(), this.eglSurface, 12375, widthArray);
        return widthArray[0];
    }

    @Override // org.webrtc.EglBase
    public int surfaceHeight() {
        int[] heightArray = new int[1];
        this.eglConnection.getEgl().eglQuerySurface(this.eglConnection.getDisplay(), this.eglSurface, 12374, heightArray);
        return heightArray[0];
    }

    @Override // org.webrtc.EglBase
    public void releaseSurface() {
        if (this.eglSurface != EGL10.EGL_NO_SURFACE) {
            this.eglConnection.getEgl().eglDestroySurface(this.eglConnection.getDisplay(), this.eglSurface);
            this.eglSurface = EGL10.EGL_NO_SURFACE;
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
        if (this.eglSurface == EGL10.EGL_NO_SURFACE) {
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
        if (this.eglSurface == EGL10.EGL_NO_SURFACE) {
            throw new RuntimeException("No EGLSurface - can't swap buffers");
        }
        synchronized (EglBase.lock) {
            this.eglConnection.getEgl().eglSwapBuffers(this.eglConnection.getDisplay(), this.eglSurface);
        }
    }

    @Override // org.webrtc.EglBase
    public void swapBuffers(long timeStampNs) {
        swapBuffers();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static EGLDisplay getEglDisplay(EGL10 egl) {
        EGLDisplay eglDisplay = egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        if (eglDisplay == EGL10.EGL_NO_DISPLAY) {
            throw new GLException(egl.eglGetError(), "Unable to get EGL10 display: 0x" + Integer.toHexString(egl.eglGetError()));
        }
        int[] version = new int[2];
        if (!egl.eglInitialize(eglDisplay, version)) {
            throw new GLException(egl.eglGetError(), "Unable to initialize EGL10: 0x" + Integer.toHexString(egl.eglGetError()));
        }
        return eglDisplay;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static EGLConfig getEglConfig(EGL10 egl, EGLDisplay eglDisplay, int[] configAttributes) {
        EGLConfig[] configs = new EGLConfig[1];
        int[] numConfigs = new int[1];
        if (!egl.eglChooseConfig(eglDisplay, configAttributes, configs, configs.length, numConfigs)) {
            throw new GLException(egl.eglGetError(), "eglChooseConfig failed: 0x" + Integer.toHexString(egl.eglGetError()));
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
    public static EGLContext createEglContext(EGL10 egl, EGLContext sharedContext, EGLDisplay eglDisplay, EGLConfig eglConfig, int openGlesVersion) {
        EGLContext eglContext;
        if (sharedContext != null && sharedContext == EGL10.EGL_NO_CONTEXT) {
            throw new RuntimeException("Invalid sharedContext");
        }
        int[] contextAttributes = {EGL_CONTEXT_CLIENT_VERSION, openGlesVersion, 12344};
        EGLContext rootContext = sharedContext == null ? EGL10.EGL_NO_CONTEXT : sharedContext;
        synchronized (EglBase.lock) {
            eglContext = egl.eglCreateContext(eglDisplay, eglConfig, rootContext, contextAttributes);
        }
        if (eglContext == EGL10.EGL_NO_CONTEXT) {
            throw new GLException(egl.eglGetError(), "Failed to create EGL context: 0x" + Integer.toHexString(egl.eglGetError()));
        }
        return eglContext;
    }
}
