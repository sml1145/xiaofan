package com.xiaofan.bangfan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.Face;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.FaceDetector;
import android.media.Image;
import android.media.ImageReader;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import android.util.Size;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.xiaofan.bangfan.PresenceDetector;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: PresenceDetector.kt */
@Metadata(d1 = {"\u0000\u0083\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0017\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n*\u0001\f\u0018\u0000 Q2\u00020\u0001:\u0002PQB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u0011H\u0002J\u001d\u0010+\u001a\u00020,2\u000e\u0010-\u001a\n\u0012\u0004\u0012\u00020,\u0018\u00010.H\u0002¢\u0006\u0002\u0010/J\b\u00100\u001a\u00020'H\u0002J\u0006\u00101\u001a\u00020'J \u00102\u001a\u00020'2\u0006\u0010*\u001a\u00020\u00112\u0006\u00103\u001a\u00020\u00162\u0006\u00104\u001a\u00020\u0016H\u0002J\u0006\u00105\u001a\u00020\u0016J\u0006\u00106\u001a\u00020\u0016J\u0006\u00107\u001a\u00020\u0016J\b\u00108\u001a\u00020'H\u0002J\u0010\u00109\u001a\u00020'2\u0006\u0010:\u001a\u00020\u0014H\u0002J\u0010\u0010;\u001a\u00020'2\u0006\u0010<\u001a\u00020\u0016H\u0002J\u0006\u0010=\u001a\u00020'J\u0006\u0010>\u001a\u00020'J\u0010\u0010?\u001a\u00020'2\b\u0010@\u001a\u0004\u0018\u00010\bJ\u0017\u0010A\u001a\u0004\u0018\u00010\u00162\u0006\u0010(\u001a\u00020)H\u0002¢\u0006\u0002\u0010BJ\u0006\u0010C\u001a\u00020'J\u0006\u0010D\u001a\u00020'JH\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020H2\u0006\u0010J\u001a\u00020H2\u0006\u0010K\u001a\u00020!2\u0006\u0010L\u001a\u00020!2\u0006\u0010M\u001a\u00020!2\u0006\u0010N\u001a\u00020!2\u0006\u0010O\u001a\u00020!H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u0016\u0010\u000e\u001a\n \u000f*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006R"}, d2 = {"Lcom/xiaofan/bangfan/PresenceDetector;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "bg", "Landroid/os/Handler;", "callback", "Lcom/xiaofan/bangfan/PresenceDetector$Callback;", "cameraDevice", "Landroid/hardware/camera2/CameraDevice;", "captureCallback", "com/xiaofan/bangfan/PresenceDetector$captureCallback$1", "Lcom/xiaofan/bangfan/PresenceDetector$captureCallback$1;", "ctx", "kotlin.jvm.PlatformType", "firstAbsentAt", "", "firstPresentAt", "frontCameraId", "", "hardwareFaceDetect", "", "imageReader", "Landroid/media/ImageReader;", "lastFrameAt", "lastSampleAt", "latestHwAt", "latestHwFound", "main", "present", "running", "sensorOrientation", "", "session", "Landroid/hardware/camera2/CameraCaptureSession;", "thread", "Landroid/os/HandlerThread;", "analyzeFrame", "", "image", "Landroid/media/Image;", "now", "chooseSize", "Landroid/util/Size;", "sizes", "", "([Landroid/util/Size;)Landroid/util/Size;", "closeCamera", "createSession", "feed", "found", "bySoftware", "isHardwareFaceDetect", "isPresent", "isRunning", "openFrontCamera", "postError", NotificationCompat.CATEGORY_MESSAGE, "postPresence", "p", "release", "resetToPresent", "setCallback", "cb", "softFaceDetect", "(Landroid/media/Image;)Ljava/lang/Boolean;", "start", "stop", "yuv420ToNv21", "", "yBuffer", "Ljava/nio/ByteBuffer;", "uBuffer", "vBuffer", "width", "height", "yRowStride", "uvRowStride", "uvPixelStride", "Callback", "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class PresenceDetector {
    private static final long BACK_MS = 700;
    public static final Companion Companion = new Companion(null);
    private static final long HW_FRESH_MS = 1500;
    private static final long LOST_MS = 1200;
    private static final long SAMPLE_INTERVAL_MS = 500;
    private static final String TAG = "PresenceDetector";
    private final Handler bg;
    private volatile Callback callback;
    private CameraDevice cameraDevice;
    private final PresenceDetector$captureCallback$1 captureCallback;
    private final Context ctx;
    private long firstAbsentAt;
    private long firstPresentAt;
    private String frontCameraId;
    private volatile boolean hardwareFaceDetect;
    private ImageReader imageReader;
    private long lastFrameAt;
    private long lastSampleAt;
    private volatile long latestHwAt;
    private volatile boolean latestHwFound;
    private final Handler main;
    private volatile boolean present;
    private volatile boolean running;
    private int sensorOrientation;
    private CameraCaptureSession session;
    private final HandlerThread thread;

    /* compiled from: PresenceDetector.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH&¨\u0006\u000b"}, d2 = {"Lcom/xiaofan/bangfan/PresenceDetector$Callback;", "", "onError", "", NotificationCompat.CATEGORY_MESSAGE, "", "onPresenceChanged", "present", "", "onSample", "hardware", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public interface Callback {
        void onError(String str);

        void onPresenceChanged(boolean z);

        void onSample(boolean z, boolean z2);
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [com.xiaofan.bangfan.PresenceDetector$captureCallback$1] */
    public PresenceDetector(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.ctx = context.getApplicationContext();
        HandlerThread it = new HandlerThread("presence-thread");
        it.start();
        this.thread = it;
        this.bg = new Handler(this.thread.getLooper());
        this.main = new Handler(Looper.getMainLooper());
        this.sensorOrientation = 270;
        this.present = true;
        this.captureCallback = new CameraCaptureSession.CaptureCallback() { // from class: com.xiaofan.bangfan.PresenceDetector$captureCallback$1
            @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
            public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
                boolean z;
                boolean z2;
                Intrinsics.checkNotNullParameter(session, "session");
                Intrinsics.checkNotNullParameter(request, "request");
                Intrinsics.checkNotNullParameter(result, "result");
                z = PresenceDetector.this.running;
                if (z) {
                    z2 = PresenceDetector.this.hardwareFaceDetect;
                    if (!z2) {
                        return;
                    }
                    boolean found = false;
                    try {
                        Object obj = result.get(CaptureResult.STATISTICS_FACES);
                        Face[] faces = obj instanceof Face[] ? (Face[]) obj : null;
                        boolean z3 = true;
                        if (faces != null) {
                            if (!(faces.length == 0)) {
                                z3 = false;
                            }
                        }
                        if (!z3) {
                            Iterator it2 = ArrayIteratorKt.iterator(faces);
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                }
                                Face face = (Face) it2.next();
                                Rect bounds = face.getBounds();
                                if (bounds != null && bounds.width() > 0 && bounds.height() > 0) {
                                    found = true;
                                    break;
                                }
                            }
                        }
                    } catch (Throwable th) {
                    }
                    PresenceDetector.this.latestHwFound = found;
                    PresenceDetector.this.latestHwAt = System.currentTimeMillis();
                }
            }
        };
    }

    /* compiled from: PresenceDetector.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/xiaofan/bangfan/PresenceDetector$Companion;", "", "()V", "BACK_MS", "", "HW_FRESH_MS", "LOST_MS", "SAMPLE_INTERVAL_MS", "TAG", "", "hasFrontCamera", "", "context", "Landroid/content/Context;", "isSupportedDevice", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isSupportedDevice() {
            return true;
        }

        public final boolean hasFrontCamera(Context context) {
            String id;
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                Object systemService = context.getSystemService("camera");
                CameraManager cm = systemService instanceof CameraManager ? (CameraManager) systemService : null;
                if (cm == null) {
                    return false;
                }
                Object[] cameraIdList = cm.getCameraIdList();
                Intrinsics.checkNotNullExpressionValue(cameraIdList, "getCameraIdList(...)");
                Object[] $this$any$iv = cameraIdList;
                for (Object element$iv : $this$any$iv) {
                    String id2 = (String) element$iv;
                    Integer facing = (Integer) cm.getCameraCharacteristics(id2).get(CameraCharacteristics.LENS_FACING);
                    if (facing == null || facing.intValue() != 0) {
                        id = null;
                    } else {
                        id = 1;
                    }
                    if (id != null) {
                        return true;
                    }
                }
                return false;
            } catch (Throwable th) {
                return false;
            }
        }
    }

    public final void setCallback(Callback cb) {
        this.callback = cb;
    }

    public final boolean isRunning() {
        return this.running;
    }

    public final boolean isPresent() {
        return this.present;
    }

    public final boolean isHardwareFaceDetect() {
        return this.hardwareFaceDetect;
    }

    public final void resetToPresent() {
        this.present = true;
        this.firstAbsentAt = 0L;
        this.firstPresentAt = 0L;
    }

    public final void start() {
        if (this.running) {
            return;
        }
        this.running = true;
        resetToPresent();
        this.bg.post(new Runnable() { // from class: com.xiaofan.bangfan.PresenceDetector$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                PresenceDetector.start$lambda$1(PresenceDetector.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void start$lambda$1(PresenceDetector this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            this$0.openFrontCamera();
        } catch (Throwable th) {
            Log.e(TAG, "start failed", th);
            this$0.postError("前置摄像头启动失败：" + th.getMessage());
        }
    }

    public final void stop() {
        this.running = false;
        this.bg.post(new Runnable() { // from class: com.xiaofan.bangfan.PresenceDetector$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                PresenceDetector.stop$lambda$2(PresenceDetector.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void stop$lambda$2(PresenceDetector this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.closeCamera();
    }

    public final void release() {
        stop();
        try {
            this.thread.quitSafely();
        } catch (Throwable th) {
        }
    }

    private final void openFrontCamera() {
        Object systemService = this.ctx.getSystemService("camera");
        CameraManager cm = systemService instanceof CameraManager ? (CameraManager) systemService : null;
        if (cm == null) {
            postError("设备无摄像头服务");
            return;
        }
        String[] cameraIdList = cm.getCameraIdList();
        Intrinsics.checkNotNullExpressionValue(cameraIdList, "getCameraIdList(...)");
        int length = cameraIdList.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String id = cameraIdList[i];
            CameraCharacteristics characteristics = cm.getCameraCharacteristics(id);
            Intrinsics.checkNotNullExpressionValue(characteristics, "getCameraCharacteristics(...)");
            Integer facing = (Integer) characteristics.get(CameraCharacteristics.LENS_FACING);
            if (facing == null || facing.intValue() != 0) {
                i++;
            } else {
                this.frontCameraId = id;
                Object obj = characteristics.get(CameraCharacteristics.STATISTICS_INFO_AVAILABLE_FACE_DETECT_MODES);
                int[] modes = obj instanceof int[] ? (int[]) obj : null;
                if (modes != null) {
                    int[] $this$any$iv = modes;
                    int length2 = $this$any$iv.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length2) {
                            break;
                        }
                        int element$iv = $this$any$iv[i2];
                        if (element$iv == 1 || element$iv == 2) {
                            z = true;
                            break;
                        }
                        i2++;
                    }
                }
                this.hardwareFaceDetect = z;
                Integer num = (Integer) characteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
                this.sensorOrientation = num == null ? 270 : num.intValue();
            }
        }
        String id2 = this.frontCameraId;
        if (id2 == null) {
            postError("未找到前置摄像头");
            return;
        }
        Log.i(TAG, "front camera id=" + id2 + " hwFace=" + this.hardwareFaceDetect + " sensorOrientation=" + this.sensorOrientation);
        Size[] sizes = null;
        try {
            Object obj2 = cm.getCameraCharacteristics(id2).get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            StreamConfigurationMap map = obj2 instanceof StreamConfigurationMap ? (StreamConfigurationMap) obj2 : null;
            sizes = map != null ? map.getOutputSizes(35) : null;
        } catch (Throwable th) {
        }
        Size chosen = chooseSize(sizes);
        ImageReader reader = ImageReader.newInstance(chosen.getWidth(), chosen.getHeight(), 35, 3);
        Intrinsics.checkNotNullExpressionValue(reader, "newInstance(...)");
        this.imageReader = reader;
        reader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() { // from class: com.xiaofan.bangfan.PresenceDetector$$ExternalSyntheticLambda4
            @Override // android.media.ImageReader.OnImageAvailableListener
            public final void onImageAvailable(ImageReader imageReader) {
                PresenceDetector.openFrontCamera$lambda$4(PresenceDetector.this, imageReader);
            }
        }, this.bg);
        try {
            cm.openCamera(id2, new CameraDevice.StateCallback() { // from class: com.xiaofan.bangfan.PresenceDetector$openFrontCamera$3
                @Override // android.hardware.camera2.CameraDevice.StateCallback
                public void onOpened(CameraDevice camera) {
                    Intrinsics.checkNotNullParameter(camera, "camera");
                    PresenceDetector.this.cameraDevice = camera;
                    PresenceDetector.this.createSession();
                }

                @Override // android.hardware.camera2.CameraDevice.StateCallback
                public void onDisconnected(CameraDevice camera) {
                    Intrinsics.checkNotNullParameter(camera, "camera");
                    camera.close();
                    PresenceDetector.this.cameraDevice = null;
                }

                @Override // android.hardware.camera2.CameraDevice.StateCallback
                public void onError(CameraDevice camera, int error) {
                    Intrinsics.checkNotNullParameter(camera, "camera");
                    camera.close();
                    PresenceDetector.this.cameraDevice = null;
                    PresenceDetector.this.postError("摄像头错误码 " + error + "（可能未授予相机权限）");
                }
            }, this.bg);
        } catch (SecurityException e) {
            postError("未授予前置摄像头权限");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openFrontCamera$lambda$4(PresenceDetector this$0, ImageReader r) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.running) {
            long now = System.currentTimeMillis();
            this$0.lastFrameAt = now;
            if (now - this$0.lastSampleAt < SAMPLE_INTERVAL_MS) {
                return;
            }
            this$0.lastSampleAt = now;
            Image image = null;
            try {
                image = r.acquireLatestImage();
                if (image == null) {
                    return;
                }
                this$0.analyzeFrame(image, now);
                try {
                    image.close();
                } catch (Throwable th) {
                }
            } catch (Throwable th2) {
                if (image != null) {
                    try {
                        image.close();
                    } catch (Throwable th3) {
                    }
                }
            }
        }
    }

    private final Size chooseSize(Size[] sizes) {
        boolean z = true;
        if (sizes != null) {
            if (!(sizes.length == 0)) {
                z = false;
            }
        }
        if (z) {
            return new Size(320, 240);
        }
        Size best = sizes[0];
        long bestDiff = Long.MAX_VALUE;
        Iterator it = ArrayIteratorKt.iterator(sizes);
        while (it.hasNext()) {
            Size size = (Size) it.next();
            long diff = Math.abs((size.getWidth() * size.getHeight()) - 76800);
            if (diff < bestDiff) {
                best = size;
                bestDiff = diff;
            }
        }
        return best;
    }

    public final void createSession() {
        CameraDevice camera = this.cameraDevice;
        if (camera == null) {
            return;
        }
        try {
            ImageReader imageReader = this.imageReader;
            Intrinsics.checkNotNull(imageReader);
            List surfaces = CollectionsKt.listOf(imageReader.getSurface());
            final CaptureRequest.Builder builder = camera.createCaptureRequest(1);
            Intrinsics.checkNotNullExpressionValue(builder, "createCaptureRequest(...)");
            ImageReader imageReader2 = this.imageReader;
            Intrinsics.checkNotNull(imageReader2);
            builder.addTarget(imageReader2.getSurface());
            builder.set(CaptureRequest.CONTROL_MODE, 1);
            if (this.hardwareFaceDetect) {
                builder.set(CaptureRequest.STATISTICS_FACE_DETECT_MODE, 1);
            }
            camera.createCaptureSession(surfaces, new CameraCaptureSession.StateCallback() { // from class: com.xiaofan.bangfan.PresenceDetector$createSession$1
                @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
                public void onConfigured(CameraCaptureSession s) {
                    CameraDevice cameraDevice;
                    PresenceDetector$captureCallback$1 presenceDetector$captureCallback$1;
                    Handler handler;
                    Intrinsics.checkNotNullParameter(s, "s");
                    cameraDevice = PresenceDetector.this.cameraDevice;
                    if (cameraDevice == null) {
                        return;
                    }
                    PresenceDetector.this.session = s;
                    try {
                        CaptureRequest build = builder.build();
                        presenceDetector$captureCallback$1 = PresenceDetector.this.captureCallback;
                        handler = PresenceDetector.this.bg;
                        s.setRepeatingRequest(build, presenceDetector$captureCallback$1, handler);
                        Log.i("PresenceDetector", "capture session configured, presence detection running");
                    } catch (Throwable th) {
                        PresenceDetector.this.postError("预览请求失败：" + th.getMessage());
                    }
                }

                @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
                public void onConfigureFailed(CameraCaptureSession s) {
                    Intrinsics.checkNotNullParameter(s, "s");
                    PresenceDetector.this.postError("摄像头会话配置失败");
                }
            }, this.bg);
        } catch (Throwable th) {
            postError("创建摄像头会话失败：" + th.getMessage());
        }
    }

    private final void analyzeFrame(Image image, long now) {
        boolean decisive;
        Boolean soft = softFaceDetect(image);
        if (soft != null) {
            decisive = soft.booleanValue();
        } else if (now - this.latestHwAt > HW_FRESH_MS) {
            return;
        } else {
            decisive = this.latestHwFound;
        }
        feed(now, decisive, soft != null);
    }

    private final Boolean softFaceDetect(Image image) {
        int width;
        int height;
        Image.Plane[] planes;
        Bitmap bitmap;
        Bitmap bitmap2 = null;
        Bitmap rotated = null;
        Boolean bool = null;
        try {
            width = image.getWidth();
            height = image.getHeight();
            planes = image.getPlanes();
        } catch (Throwable th) {
        }
        if (planes.length < 3) {
            return null;
        }
        ByteBuffer buffer = planes[0].getBuffer();
        Intrinsics.checkNotNullExpressionValue(buffer, "getBuffer(...)");
        ByteBuffer buffer2 = planes[1].getBuffer();
        Intrinsics.checkNotNullExpressionValue(buffer2, "getBuffer(...)");
        ByteBuffer buffer3 = planes[2].getBuffer();
        Intrinsics.checkNotNullExpressionValue(buffer3, "getBuffer(...)");
        byte[] nv21 = yuv420ToNv21(buffer, buffer2, buffer3, width, height, planes[0].getRowStride(), planes[1].getRowStride(), planes[1].getPixelStride());
        YuvImage yuv = new YuvImage(nv21, 17, width, height, null);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        yuv.compressToJpeg(new Rect(0, 0, width, height), 55, out);
        byte[] data = out.toByteArray();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        if (decodeByteArray == null) {
            return null;
        }
        bitmap2 = decodeByteArray;
        if (this.sensorOrientation != 0) {
            Matrix m = new Matrix();
            m.postRotate(this.sensorOrientation);
            bitmap = Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), bitmap2.getHeight(), m, true);
        } else {
            bitmap = bitmap2;
        }
        rotated = bitmap;
        int w = rotated.getWidth() - (rotated.getWidth() % 2);
        int h = rotated.getHeight();
        if (w <= 0 || h <= 0) {
            if (rotated != null && rotated != bitmap2) {
                try {
                    rotated.recycle();
                } catch (Throwable th2) {
                }
            }
            try {
                bitmap2.recycle();
            } catch (Throwable th3) {
            }
            return null;
        }
        try {
            FaceDetector detector = new FaceDetector(w, h, 1);
            FaceDetector.Face[] faces = new FaceDetector.Face[1];
            bool = Boolean.valueOf(detector.findFaces(rotated, faces) > 0 && faces[0] != null);
            if (rotated != null && rotated != bitmap2) {
                try {
                    rotated.recycle();
                } catch (Throwable th4) {
                }
            }
            try {
                bitmap2.recycle();
            } catch (Throwable th5) {
            }
        } catch (Throwable th6) {
            bool = null;
            if (rotated != null && rotated != bitmap2) {
                try {
                    rotated.recycle();
                } catch (Throwable th7) {
                }
            }
            if (bitmap2 != null) {
                try {
                    bitmap2.recycle();
                } catch (Throwable th8) {
                }
            }
            return bool;
        }
        return bool;
    }

    private final byte[] yuv420ToNv21(ByteBuffer yBuffer, ByteBuffer uBuffer, ByteBuffer vBuffer, int width, int height, int yRowStride, int uvRowStride, int uvPixelStride) {
        int ySize = width * height;
        int total = (ySize * 3) / 2;
        byte[] out = new byte[total];
        if (yRowStride == width) {
            yBuffer.get(out, 0, ySize);
        } else {
            ySize = 0;
            for (int row = 0; row < height; row++) {
                yBuffer.position(row * yRowStride);
                yBuffer.get(out, ySize, width);
                ySize += width;
            }
        }
        if (uvPixelStride == 2) {
            int i = height / 2;
            for (int row2 = 0; row2 < i; row2++) {
                int remaining = total - ySize;
                if (remaining <= 0) {
                    break;
                }
                vBuffer.position(row2 * uvRowStride);
                int len = Math.min(width, remaining);
                vBuffer.get(out, ySize, len);
                ySize += len;
            }
        } else {
            int i2 = height / 2;
            for (int row3 = 0; row3 < i2; row3++) {
                int i3 = width / 2;
                for (int col = 0; col < i3 && ySize + 1 < total; col++) {
                    int idx = (row3 * uvRowStride) + (col * uvPixelStride);
                    out[ySize] = vBuffer.get(idx);
                    out[ySize + 1] = uBuffer.get(idx);
                    ySize += 2;
                }
            }
        }
        return out;
    }

    private final void feed(long now, final boolean found, final boolean bySoftware) {
        if (found) {
            this.firstAbsentAt = 0L;
            if (this.firstPresentAt == 0) {
                this.firstPresentAt = now;
            }
            if (!this.present && now - this.firstPresentAt >= 700) {
                this.present = true;
                this.firstPresentAt = 0L;
                Log.i(TAG, "presence -> PRESENT (bySoftware=" + bySoftware + ")");
                postPresence(true);
            }
        } else {
            this.firstPresentAt = 0L;
            if (this.firstAbsentAt == 0) {
                this.firstAbsentAt = now;
            }
            if (this.present && now - this.firstAbsentAt >= LOST_MS) {
                this.present = false;
                this.firstAbsentAt = 0L;
                Log.i(TAG, "presence -> ABSENT (bySoftware=" + bySoftware + ")");
                postPresence(false);
            }
        }
        final Callback cb = this.callback;
        if (cb != null) {
            this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.PresenceDetector$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    PresenceDetector.feed$lambda$5(PresenceDetector.Callback.this, found, bySoftware);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void feed$lambda$5(Callback $cb, boolean $value, boolean $bySoftware) {
        $cb.onSample($value, !$bySoftware);
    }

    private final void postPresence(final boolean p) {
        AppPrefs appPrefs = AppPrefs.INSTANCE;
        Context ctx = this.ctx;
        Intrinsics.checkNotNullExpressionValue(ctx, "ctx");
        appPrefs.setPresence(ctx, p);
        TurnManager.INSTANCE.notifyPresenceChanged(p);
        final Callback cb = this.callback;
        if (cb != null) {
            this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.PresenceDetector$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    PresenceDetector.Callback.this.onPresenceChanged(p);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void postError(final String msg) {
        Log.w(TAG, "error: " + msg);
        final Callback cb = this.callback;
        if (cb != null) {
            this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.PresenceDetector$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    PresenceDetector.postError$lambda$7(PresenceDetector.Callback.this, msg);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void postError$lambda$7(Callback $cb, String msg) {
        Intrinsics.checkNotNullParameter(msg, "$msg");
        $cb.onError(msg);
    }

    private final void closeCamera() {
        try {
            CameraCaptureSession it = this.session;
            if (it != null) {
                it.stopRepeating();
                it.close();
            }
        } catch (Throwable th) {
        }
        this.session = null;
        try {
            CameraDevice cameraDevice = this.cameraDevice;
            if (cameraDevice != null) {
                cameraDevice.close();
            }
        } catch (Throwable th2) {
        }
        this.cameraDevice = null;
        try {
            ImageReader imageReader = this.imageReader;
            if (imageReader != null) {
                imageReader.close();
            }
        } catch (Throwable th3) {
        }
        this.imageReader = null;
    }
}
