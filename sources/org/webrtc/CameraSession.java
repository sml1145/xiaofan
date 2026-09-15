package org.webrtc;

import android.content.Context;
import android.graphics.Matrix;
import android.view.WindowManager;
import org.webrtc.VideoFrame;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public interface CameraSession {

    /* loaded from: classes5.dex */
    public interface CreateSessionCallback {
        void onDone(CameraSession cameraSession);

        void onFailure(FailureType failureType, String str);
    }

    /* loaded from: classes5.dex */
    public interface Events {
        void onCameraClosed(CameraSession cameraSession);

        void onCameraDisconnected(CameraSession cameraSession);

        void onCameraError(CameraSession cameraSession, String str);

        void onCameraOpening();

        void onFrameCaptured(CameraSession cameraSession, VideoFrame videoFrame);
    }

    /* loaded from: classes5.dex */
    public enum FailureType {
        ERROR,
        DISCONNECTED
    }

    void stop();

    static int getDeviceOrientation(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService("window");
        switch (wm.getDefaultDisplay().getRotation()) {
            case 1:
                return 90;
            case 2:
                return 180;
            case 3:
                return 270;
            default:
                return 0;
        }
    }

    static VideoFrame.TextureBuffer createTextureBufferWithModifiedTransformMatrix(TextureBufferImpl buffer, boolean mirror, int rotation) {
        Matrix transformMatrix = new Matrix();
        transformMatrix.preTranslate(0.5f, 0.5f);
        if (mirror) {
            transformMatrix.preScale(-1.0f, 1.0f);
        }
        transformMatrix.preRotate(rotation);
        transformMatrix.preTranslate(-0.5f, -0.5f);
        return buffer.applyTransformMatrix(transformMatrix, buffer.getWidth(), buffer.getHeight());
    }
}
