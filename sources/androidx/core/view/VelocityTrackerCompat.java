package androidx.core.view;

import android.os.Build;
import android.view.VelocityTracker;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes.dex */
public final class VelocityTrackerCompat {

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface VelocityTrackableMotionEventAxis {
    }

    @Deprecated
    public static float getXVelocity(VelocityTracker tracker, int pointerId) {
        return tracker.getXVelocity(pointerId);
    }

    @Deprecated
    public static float getYVelocity(VelocityTracker tracker, int pointerId) {
        return tracker.getYVelocity(pointerId);
    }

    public static boolean isAxisSupported(VelocityTracker tracker, int axis) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api34Impl.isAxisSupported(tracker, axis);
        }
        return axis == 0 || axis == 1;
    }

    public static float getAxisVelocity(VelocityTracker tracker, int axis) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api34Impl.getAxisVelocity(tracker, axis);
        }
        if (axis == 0) {
            return tracker.getXVelocity();
        }
        if (axis == 1) {
            return tracker.getYVelocity();
        }
        return 0.0f;
    }

    public static float getAxisVelocity(VelocityTracker tracker, int axis, int pointerId) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api34Impl.getAxisVelocity(tracker, axis, pointerId);
        }
        if (axis == 0) {
            return tracker.getXVelocity(pointerId);
        }
        if (axis == 1) {
            return tracker.getYVelocity(pointerId);
        }
        return 0.0f;
    }

    /* loaded from: classes.dex */
    private static class Api34Impl {
        private Api34Impl() {
        }

        static boolean isAxisSupported(VelocityTracker velocityTracker, int axis) {
            return velocityTracker.isAxisSupported(axis);
        }

        static float getAxisVelocity(VelocityTracker velocityTracker, int axis, int id) {
            return velocityTracker.getAxisVelocity(axis, id);
        }

        static float getAxisVelocity(VelocityTracker velocityTracker, int axis) {
            return velocityTracker.getAxisVelocity(axis);
        }
    }

    private VelocityTrackerCompat() {
    }
}
