package com.xiaofan.bangfan;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.location.LocationRequestCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
/* compiled from: MascotView.kt */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\bF\u0018\u0000 £\u00012\u00020\u0001:\u0004£\u0001¤\u0001B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010Q\u001a\u00020RJ\u0010\u0010S\u001a\u00020R2\u0006\u0010T\u001a\u00020UH\u0002J\u000e\u0010V\u001a\u00020R2\u0006\u0010\u0002\u001a\u00020\u0003J\b\u0010W\u001a\u00020RH\u0002J \u0010X\u001a\u00020\u00122\u0006\u0010Y\u001a\u00020\u00122\u0006\u0010Z\u001a\u00020\u00122\u0006\u0010[\u001a\u00020\u0012H\u0002J\b\u0010\\\u001a\u00020%H\u0002J\u0018\u0010]\u001a\u00020R2\u0006\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020\u001bH\u0002J(\u0010a\u001a\u00020R2\u0006\u0010^\u001a\u00020_2\u0006\u0010b\u001a\u00020\f2\u0006\u0010c\u001a\u00020%2\u0006\u0010d\u001a\u00020%H\u0002J \u0010e\u001a\u00020R2\u0006\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020\u001b2\u0006\u0010f\u001a\u00020\u0012H\u0002J \u0010g\u001a\u00020R2\u0006\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020\u001b2\u0006\u0010f\u001a\u00020\u0012H\u0002J \u0010h\u001a\u00020R2\u0006\u0010^\u001a\u00020_2\u0006\u0010c\u001a\u00020%2\u0006\u0010d\u001a\u00020%H\u0002J\u0018\u0010i\u001a\u00020R2\u0006\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020\u001bH\u0002J(\u0010j\u001a\u00020R2\u0006\u0010^\u001a\u00020_2\u0006\u0010c\u001a\u00020%2\u0006\u0010d\u001a\u00020%2\u0006\u0010k\u001a\u00020%H\u0002J \u0010l\u001a\u00020R2\u0006\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020\u001b2\u0006\u0010m\u001a\u00020\u0012H\u0002J\u0018\u0010n\u001a\u00020R2\u0006\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020\u001bH\u0002J \u0010o\u001a\u00020R2\u0006\u0010^\u001a\u00020_2\u0006\u0010c\u001a\u00020%2\u0006\u0010d\u001a\u00020%H\u0002J \u0010p\u001a\u00020R2\u0006\u0010^\u001a\u00020_2\u0006\u0010c\u001a\u00020%2\u0006\u0010d\u001a\u00020%H\u0002J\u0018\u0010q\u001a\u00020R2\u0006\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020\u001bH\u0002J0\u0010r\u001a\u00020R2\u0006\u0010^\u001a\u00020_2\u0006\u0010c\u001a\u00020%2\u0006\u0010d\u001a\u00020%2\u0006\u0010s\u001a\u00020%2\u0006\u0010t\u001a\u00020\u0012H\u0002J(\u0010u\u001a\u00020R2\u0006\u0010^\u001a\u00020_2\u0006\u0010v\u001a\u00020\u00122\u0006\u0010w\u001a\u00020\u00122\u0006\u0010x\u001a\u00020\u0012H\u0002J8\u0010y\u001a\u00020R2\u0006\u0010^\u001a\u00020_2\u0006\u0010z\u001a\u00020\u00122\u0006\u0010{\u001a\u00020\u00122\u0006\u0010|\u001a\u00020\u00122\u0006\u0010}\u001a\u00020\u00122\u0006\u0010s\u001a\u00020%H\u0002J8\u0010~\u001a\u00020R2\u0006\u0010^\u001a\u00020_2\u0006\u0010z\u001a\u00020\u00122\u0006\u0010{\u001a\u00020\u00122\u0006\u0010\u007f\u001a\u00020%2\u0006\u0010}\u001a\u00020\u00122\u0006\u0010s\u001a\u00020%H\u0002J\u0019\u0010\u0080\u0001\u001a\u00020R2\u0006\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020\u001bH\u0002JC\u0010\u0081\u0001\u001a\u00020R2\u0006\u0010^\u001a\u00020_2\u0006\u0010z\u001a\u00020\u00122\u0006\u0010{\u001a\u00020\u00122\u0007\u0010\u0082\u0001\u001a\u00020%2\u0007\u0010\u0083\u0001\u001a\u00020%2\u0006\u0010}\u001a\u00020\u00122\u0006\u0010s\u001a\u00020%H\u0002J!\u0010\u0084\u0001\u001a\u00020R2\u0006\u0010^\u001a\u00020_2\u0006\u0010c\u001a\u00020%2\u0006\u0010d\u001a\u00020%H\u0002J!\u0010\u0085\u0001\u001a\u00020R2\u0006\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020\u001b2\u0006\u0010f\u001a\u00020\u0012H\u0002J!\u0010\u0086\u0001\u001a\u00020R2\u0006\u0010^\u001a\u00020_2\u0006\u0010c\u001a\u00020%2\u0006\u0010d\u001a\u00020%H\u0002J!\u0010\u0087\u0001\u001a\u00020R2\u0006\u0010^\u001a\u00020_2\u0006\u0010c\u001a\u00020%2\u0006\u0010d\u001a\u00020%H\u0002J*\u0010\u0088\u0001\u001a\u00020R2\u0006\u0010^\u001a\u00020_2\u0006\u0010c\u001a\u00020%2\u0006\u0010d\u001a\u00020%2\u0007\u0010\u0089\u0001\u001a\u00020%H\u0002J\u0007\u0010\u008a\u0001\u001a\u00020RJ\t\u0010\u008b\u0001\u001a\u00020RH\u0014J\u0011\u0010\u008c\u0001\u001a\u00020R2\u0006\u0010^\u001a\u00020_H\u0014J\u0011\u0010\u008d\u0001\u001a\u00020\u00142\u0006\u0010T\u001a\u00020UH\u0016J\u001b\u0010\u008e\u0001\u001a\u00020R2\u0007\u0010\u008f\u0001\u001a\u00020\u00012\u0007\u0010\u0090\u0001\u001a\u00020%H\u0014J\u0012\u0010\u0091\u0001\u001a\u00020R2\u0007\u0010\u0090\u0001\u001a\u00020%H\u0014J\t\u0010\u0092\u0001\u001a\u00020RH\u0002J\u0011\u0010\u0093\u0001\u001a\u00020\u00122\u0006\u0010c\u001a\u00020%H\u0002J\t\u0010\u0094\u0001\u001a\u00020RH\u0002J\u000f\u0010\u0095\u0001\u001a\u00020R2\u0006\u0010Y\u001a\u00020\u0014J\u0010\u0010\u0096\u0001\u001a\u00020R2\u0007\u0010\u0097\u0001\u001a\u00020\u0014J\u0012\u0010\u0098\u0001\u001a\u00020R2\t\u0010\u0099\u0001\u001a\u0004\u0018\u000102J\u000f\u0010\u009a\u0001\u001a\u00020R2\u0006\u0010Y\u001a\u00020\u0014J\u000f\u0010\u009b\u0001\u001a\u00020R2\u0006\u0010Y\u001a\u00020\u0014J\u000f\u0010\u009c\u0001\u001a\u00020R2\u0006\u0010Y\u001a\u00020\u0014J\t\u0010\u009d\u0001\u001a\u00020RH\u0002J\t\u0010\u009e\u0001\u001a\u00020RH\u0002J\t\u0010\u009f\u0001\u001a\u00020RH\u0002J\t\u0010 \u0001\u001a\u00020RH\u0002J\t\u0010¡\u0001\u001a\u00020RH\u0002J\t\u0010¢\u0001\u001a\u00020RH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u0018\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010B\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010D\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010K\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010L\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020PX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006¥\u0001"}, d2 = {"Lcom/xiaofan/bangfan/MascotView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "BG_TRANS_MS", "", "SCENE_TICK_MS", "bgBitmaps", "", "Landroid/graphics/Bitmap;", "[Landroid/graphics/Bitmap;", "bgPaint", "Landroid/graphics/Paint;", "bgTransStartAt", "bgTransition", "", "bgTransitioning", "", "blinkAmount", "blinkEnabled", "blinking", "body", "bodyPaint", "bodyRect", "Landroid/graphics/RectF;", "camera", "Landroid/graphics/Camera;", "chestFlash", "cloudOffset", "cpuRenderPaused", "crownPaint", "curRotX", "curRotY", "currentBg", "", "eyePatchPaint", "frameTask", "Ljava/lang/Runnable;", "gazeX", "gazeY", "glowPaint", "highlightPaint", "idleBobY", "idleBreath", "idleSway", "lastSceneCheckAt", "listener", "Lcom/xiaofan/bangfan/MascotView$MascotListener;", "longPressFired", "longPressTask", "main", "Landroid/os/Handler;", "mouthOpen", "mouthPaint", "mouthPhaseStart", "nextBg", "nextBlinkAt", "nightGlow", "planner", "pressOffsetY", "scale", "scene", "sceneEnabled", "sceneFinalizeTask", "scenePaint", "sceneTickTask", "shadowPaint", "shimmerPhase", "speaking", "starTwinkle", "targetGazeX", "targetGazeY", "targetPressOffsetY", "targetRotX", "targetRotY", "targetScale", "tiltMatrix", "Landroid/graphics/Matrix;", "applySceneByTime", "", "applyTiltFromTouch", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "applyVisualState", "bounce", "clamp", "v", "min", "max", "computeSceneByTime", "drawAura", "canvas", "Landroid/graphics/Canvas;", "rect", "drawBgBitmap", "bmp", "w", "h", "drawBlink", "amount", "drawChestGlow", "drawDynamicElements", "drawGazeHighlights", "drawGradientFallback", "s", "drawGroundShadow", "rotY", "drawLightOverlay", "drawMeteor", "drawMoonFace", "drawMouth", "drawMovingClouds", "color", "speedFactor", "drawPixelCloud", "x", "y", "u", "drawPixelDisc", "cx", "cy", "r", "cell", "drawPixelSmile", "halfCells", "drawPlannerMarks", "drawPxRect", "cols", "rows", "drawScene", "drawSpeakGlow", "drawSunFace", "drawSunsetFace", "drawTwinklingStars", "count", "flashChest", "onDetachedFromWindow", "onDraw", "onTouchEvent", "onVisibilityChanged", "changedView", "visibility", "onWindowVisibilityChanged", "openEyes", "pxCell", "scheduleNextBlink", "setBlinkEnabled", "setCpuRenderPaused", "paused", "setMascotListener", "l", "setNightGlow", "setSceneEnabled", "setSpeaking", "startRenderLoop", "startSceneTick", "stopRenderLoop", "stopSceneTick", "triggerBlink", "updateFrame", "Companion", "MascotListener", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class MascotView extends View {
    private static final float CHEST_Y = 0.682f;
    public static final Companion Companion = new Companion(null);
    private static final float EYE_L_X = 0.37f;
    private static final float EYE_RX = 0.06f;
    private static final float EYE_RY = 0.045f;
    private static final float EYE_R_X = 0.63f;
    private static final float EYE_Y = 0.4f;
    private static final float MAX_TILT = 14.0f;
    private static final float MOUTH_Y = 0.5f;
    private final long BG_TRANS_MS;
    private final long SCENE_TICK_MS;
    private final Bitmap[] bgBitmaps;
    private final Paint bgPaint;
    private long bgTransStartAt;
    private float bgTransition;
    private boolean bgTransitioning;
    private float blinkAmount;
    private boolean blinkEnabled;
    private boolean blinking;
    private Bitmap body;
    private final Paint bodyPaint;
    private final RectF bodyRect;
    private final Camera camera;
    private float chestFlash;
    private float cloudOffset;
    private boolean cpuRenderPaused;
    private final Paint crownPaint;
    private float curRotX;
    private float curRotY;
    private int currentBg;
    private final Paint eyePatchPaint;
    private Runnable frameTask;
    private float gazeX;
    private float gazeY;
    private final Paint glowPaint;
    private final Paint highlightPaint;
    private float idleBobY;
    private float idleBreath;
    private float idleSway;
    private long lastSceneCheckAt;
    private MascotListener listener;
    private boolean longPressFired;
    private Runnable longPressTask;
    private final Handler main;
    private float mouthOpen;
    private final Paint mouthPaint;
    private long mouthPhaseStart;
    private int nextBg;
    private long nextBlinkAt;
    private volatile boolean nightGlow;
    private volatile boolean planner;
    private float pressOffsetY;
    private float scale;
    private volatile int scene;
    private volatile boolean sceneEnabled;
    private Runnable sceneFinalizeTask;
    private final Paint scenePaint;
    private Runnable sceneTickTask;
    private final Paint shadowPaint;
    private float shimmerPhase;
    private volatile boolean speaking;
    private float starTwinkle;
    private float targetGazeX;
    private float targetGazeY;
    private float targetPressOffsetY;
    private float targetRotX;
    private float targetRotY;
    private float targetScale;
    private final Matrix tiltMatrix;

    /* compiled from: MascotView.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/xiaofan/bangfan/MascotView$MascotListener;", "", "onMascotLongPressed", "", "onMascotTapped", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public interface MascotListener {
        void onMascotLongPressed();

        void onMascotTapped();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MascotView(Context context) {
        this(context, null, 2, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ MascotView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MascotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        this.bgBitmaps = new Bitmap[4];
        this.nextBg = -1;
        this.BG_TRANS_MS = 800L;
        this.bodyRect = new RectF();
        this.bodyPaint = new Paint(3);
        this.bgPaint = new Paint(3);
        this.camera = new Camera();
        this.blinkEnabled = true;
        this.eyePatchPaint = new Paint(1);
        this.glowPaint = new Paint(1);
        this.highlightPaint = new Paint(1);
        this.main = new Handler(Looper.getMainLooper());
        this.mouthPaint = new Paint(1);
        this.scale = 1.0f;
        this.sceneEnabled = true;
        this.scenePaint = new Paint(1);
        this.shadowPaint = new Paint(1);
        this.crownPaint = new Paint(1);
        this.targetScale = 1.0f;
        this.tiltMatrix = new Matrix();
        this.SCENE_TICK_MS = 10000L;
        this.idleBreath = 1.0f;
        setLayerType(2, null);
        int bodyRes = context.getResources().getIdentifier("mascot_body", "drawable", context.getPackageName());
        if (bodyRes != 0) {
            this.body = BitmapFactory.decodeResource(context.getResources(), bodyRes);
        }
        int[] bgResIds = {context.getResources().getIdentifier("bg_day", "drawable", context.getPackageName()), context.getResources().getIdentifier("bg_sunset", "drawable", context.getPackageName()), context.getResources().getIdentifier("bg_night", "drawable", context.getPackageName()), context.getResources().getIdentifier("bg_stars", "drawable", context.getPackageName())};
        int length = bgResIds.length;
        for (int i = 0; i < length; i++) {
            if (bgResIds[i] != 0) {
                try {
                    this.bgBitmaps[i] = BitmapFactory.decodeResource(context.getResources(), bgResIds[i]);
                } catch (Throwable th) {
                }
            }
        }
        int i2 = computeSceneByTime();
        this.scene = i2;
        this.currentBg = this.scene;
        this.lastSceneCheckAt = System.currentTimeMillis();
        this.eyePatchPaint.setColor(-722950);
        this.eyePatchPaint.setStyle(Paint.Style.FILL);
        this.mouthPaint.setColor(-12958124);
        this.mouthPaint.setStyle(Paint.Style.STROKE);
        this.mouthPaint.setStrokeCap(Paint.Cap.ROUND);
        this.shadowPaint.setColor(857747520);
        scheduleNextBlink();
        startRenderLoop();
        startSceneTick();
    }

    /* compiled from: MascotView.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/xiaofan/bangfan/MascotView$Companion;", "", "()V", "CHEST_Y", "", "EYE_L_X", "EYE_RX", "EYE_RY", "EYE_R_X", "EYE_Y", "MAX_TILT", "MOUTH_Y", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final void setMascotListener(MascotListener l) {
        this.listener = l;
    }

    public final void setSpeaking(boolean v) {
        this.speaking = v;
        if (v) {
            this.mouthPhaseStart = System.currentTimeMillis();
        }
    }

    public final void setCpuRenderPaused(boolean paused) {
        if (this.cpuRenderPaused == paused) {
            return;
        }
        this.cpuRenderPaused = paused;
        if (paused) {
            stopRenderLoop();
        } else {
            startRenderLoop();
        }
        invalidate();
    }

    public final void flashChest() {
        this.chestFlash = 1.0f;
    }

    public final void setNightGlow(boolean v) {
        this.nightGlow = v;
    }

    public final void setBlinkEnabled(boolean v) {
        this.blinkEnabled = v;
        if (!v) {
            this.blinking = false;
            this.blinkAmount = 0.0f;
        }
    }

    public final void setSceneEnabled(boolean v) {
        this.sceneEnabled = v;
        invalidate();
    }

    public final void applyVisualState(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.planner = AppPrefs.INSTANCE.isPlanner(context);
    }

    public final void applySceneByTime() {
        final int s = computeSceneByTime();
        if (s != this.scene) {
            this.scene = s;
        }
        if (s == this.currentBg || s == this.nextBg) {
            return;
        }
        this.nextBg = s;
        this.bgTransitioning = true;
        this.bgTransStartAt = System.currentTimeMillis();
        Runnable it = this.sceneFinalizeTask;
        if (it != null) {
            this.main.removeCallbacks(it);
        }
        Runnable fin = new Runnable() { // from class: com.xiaofan.bangfan.MascotView$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                MascotView.applySceneByTime$lambda$1(MascotView.this, s);
            }
        };
        this.sceneFinalizeTask = fin;
        this.main.postDelayed(fin, this.BG_TRANS_MS + 60);
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void applySceneByTime$lambda$1(MascotView this$0, int $s) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.bgTransitioning && this$0.nextBg == $s) {
            this$0.invalidate();
        }
    }

    private final int computeSceneByTime() {
        Calendar cal = Calendar.getInstance();
        int minutes = (cal.get(11) * 60) + cal.get(12);
        return SceneClock.INSTANCE.sceneForMinutes(minutes);
    }

    private final void openEyes() {
    }

    private final void scheduleNextBlink() {
        this.nextBlinkAt = System.currentTimeMillis() + 2200 + ((long) (Math.random() * 2600));
    }

    private final void triggerBlink() {
        this.blinking = true;
        this.blinkAmount = 0.0f;
        scheduleNextBlink();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Pair pair;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        int w = getWidth();
        int h = getHeight();
        if (w != 0 && h != 0) {
            if (this.sceneEnabled) {
                drawScene(canvas, w, h);
            }
            float minDim = Math.min(w, h) - ((Math.min(w, h) * EYE_RX) * 2.0f);
            Bitmap drawBody = this.body;
            if (drawBody != null) {
                float ratio = drawBody.getWidth() / drawBody.getHeight();
                pair = ratio >= 1.0f ? TuplesKt.to(Float.valueOf(minDim), Float.valueOf(minDim / ratio)) : TuplesKt.to(Float.valueOf(ratio * minDim), Float.valueOf(minDim));
            } else {
                pair = TuplesKt.to(Float.valueOf(minDim), Float.valueOf(minDim));
            }
            float bodyW = ((Number) pair.component1()).floatValue();
            float bodyH = ((Number) pair.component2()).floatValue();
            float bobPx = this.idleBobY * bodyH * 0.022f;
            float bodyOffsetY = this.pressOffsetY + bobPx;
            this.bodyRect.set((w - bodyW) / 2.0f, ((h - bodyH) / 2.0f) + bodyOffsetY, (w + bodyW) / 2.0f, ((h + bodyH) / 2.0f) + bodyOffsetY);
            canvas.save();
            float cx = w / 2.0f;
            float cy = (h / 2.0f) + bodyOffsetY;
            canvas.scale(this.scale * this.idleBreath, this.scale * this.idleBreath, cx, cy);
            this.camera.save();
            this.camera.rotateX(-this.curRotX);
            this.camera.rotateY(this.curRotY + (this.idleSway * 1.4f));
            this.camera.getMatrix(this.tiltMatrix);
            this.camera.restore();
            this.tiltMatrix.preTranslate(-cx, -cy);
            this.tiltMatrix.postTranslate(cx, cy);
            canvas.concat(this.tiltMatrix);
            drawGroundShadow(canvas, this.bodyRect, this.curRotY);
            drawAura(canvas, this.bodyRect);
            if (drawBody != null) {
                canvas.drawBitmap(drawBody, (Rect) null, this.bodyRect, this.bodyPaint);
            }
            if (this.blinkAmount > 0.02f) {
                drawBlink(canvas, this.bodyRect, this.blinkAmount);
            }
            drawSpeakGlow(canvas, this.bodyRect, this.mouthOpen);
            drawChestGlow(canvas, this.bodyRect, this.chestFlash);
            drawLightOverlay(canvas, this.bodyRect);
            if (this.planner) {
                drawPlannerMarks(canvas, this.bodyRect);
            }
            canvas.restore();
        }
    }

    private final void drawLightOverlay(Canvas canvas, RectF rect) {
        float cx = rect.centerX();
        float cy = rect.top + (rect.height() * 0.3f);
        float r = rect.width() * 0.8f;
        switch (this.currentBg) {
            case 0:
            case 1:
                this.glowPaint.setShader(new RadialGradient(cx - (rect.width() * 0.2f), cy - (rect.height() * 0.15f), r, new int[]{Color.argb(35, 255, 220, 140), Color.argb(0, 255, 220, 140)}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
                canvas.drawCircle(cx, cy, r, this.glowPaint);
                break;
            case 2:
            case 3:
                this.glowPaint.setShader(new RadialGradient(cx + (rect.width() * 0.15f), cy - (rect.height() * 0.1f), r, new int[]{Color.argb(28, 140, 180, 255), Color.argb(0, 140, 180, 255)}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
                canvas.drawCircle(cx, cy, r, this.glowPaint);
                break;
        }
        this.glowPaint.setShader(null);
    }

    private final void drawGroundShadow(Canvas canvas, RectF rect, float rotY) {
        float cx = rect.centerX() + (rect.width() * rotY * 0.02f);
        float cy = rect.bottom - (rect.height() * 0.02f);
        float w = rect.width() * EYE_Y;
        float h = rect.height() * EYE_RY;
        this.shadowPaint.setShader(new RadialGradient(cx, cy, w, new int[]{1428172864, 2109504}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.scale(1.0f, h / w, cx, cy);
        canvas.drawCircle(cx, cy, w, this.shadowPaint);
        canvas.restore();
        this.shadowPaint.setShader(null);
    }

    private final void drawGazeHighlights(Canvas canvas, RectF rect) {
        float f;
        RectF rectF = rect;
        float w = rect.width() * EYE_RX;
        float h = rect.height() * EYE_RY;
        float dx = this.gazeX * w * 0.5f;
        float dy = this.gazeY * h * 0.5f;
        Paint eyeBgPaint = new Paint(1);
        eyeBgPaint.setColor(Color.argb(230, 30, 40, 60));
        float eyeR = 0.42f * w;
        int i = 2;
        float[] fArr = {EYE_L_X, EYE_R_X};
        int i2 = 0;
        while (true) {
            f = EYE_Y;
            if (i2 >= i) {
                break;
            }
            float ex = fArr[i2];
            float ecx = rectF.left + (rect.width() * ex);
            float ecy = rectF.top + (rect.height() * EYE_Y);
            canvas.drawCircle(ecx, ecy, eyeR, eyeBgPaint);
            i2++;
            i = 2;
        }
        this.highlightPaint.setColor(Color.argb(255, 140, 230, 255));
        float r = 0.28f * w;
        int i3 = 2;
        float[] fArr2 = {EYE_L_X, EYE_R_X};
        int i4 = 0;
        while (i4 < i3) {
            float ex2 = fArr2[i4];
            canvas.drawCircle(((rectF.left + (rect.width() * ex2)) - (0.12f * w)) + dx, ((rectF.top + (rect.height() * f)) - (0.08f * h)) + dy, r, this.highlightPaint);
            i4++;
            i3 = 2;
            f = EYE_Y;
        }
        this.highlightPaint.setColor(Color.argb((int) ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION, 255, 255, 255));
        float[] fArr3 = {EYE_L_X, EYE_R_X};
        int i5 = 0;
        for (int i6 = 2; i5 < i6; i6 = 2) {
            float ex3 = fArr3[i5];
            canvas.drawCircle(((rectF.left + (rect.width() * ex3)) - (0.2f * w)) + dx, ((rectF.top + (rect.height() * EYE_Y)) - (0.18f * h)) + dy, r * 0.35f, this.highlightPaint);
            i5++;
            rectF = rect;
        }
    }

    private final void drawBlink(Canvas canvas, RectF rect, float amount) {
        float w = rect.width() * EYE_RX;
        float h = rect.height() * EYE_RY;
        float fh = h * amount;
        Paint lidPaint = new Paint(1);
        lidPaint.setColor(Color.argb((int) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 235, 240, 248));
        float[] fArr = {EYE_L_X, EYE_R_X};
        int i = 0;
        while (i < 2) {
            float ex = fArr[i];
            float cx = rect.left + (rect.width() * ex);
            float cy = rect.top + (rect.height() * EYE_Y);
            RectF top = new RectF(cx - (w * 1.3f), cy - (h * 1.3f), cx + (w * 1.3f), (cy - (h * 1.3f)) + (fh * 1.1f));
            RectF bottom = new RectF(cx - (w * 1.3f), (cy + (h * 1.3f)) - (1.1f * fh), cx + (w * 1.3f), cy + (1.3f * h));
            canvas.drawArc(top, 0.0f, 180.0f, true, lidPaint);
            canvas.drawArc(bottom, 180.0f, 180.0f, true, lidPaint);
            i++;
            fArr = fArr;
        }
    }

    private final void drawMouth(Canvas canvas, RectF rect) {
        float cx = rect.left + (rect.width() * 0.5f);
        float cy = rect.top + (rect.height() * 0.5f);
        float hw = rect.width() * 0.13f;
        this.mouthPaint.setStrokeWidth(rect.width() * 0.02f);
        this.mouthPaint.setStyle(Paint.Style.STROKE);
        this.mouthPaint.setColor(Color.argb(220, 80, 90, 110));
        Path path = new Path();
        path.moveTo(cx - hw, cy);
        path.quadTo(cx, (0.55f * hw) + cy, hw + cx, cy);
        canvas.drawPath(path, this.mouthPaint);
    }

    private final void drawSpeakGlow(Canvas canvas, RectF rect, float amount) {
        RectF rectF = rect;
        if (amount <= 0.02f) {
            return;
        }
        float w = rect.width() * EYE_RX;
        float r = ((1.6f * amount) + 1.8f) * w;
        int alpha = (int) ((175.0f * amount) + 60.0f);
        int i = 2;
        float[] fArr = {EYE_L_X, EYE_R_X};
        int i2 = 0;
        int i3 = 0;
        while (i3 < i) {
            float ex = fArr[i3];
            float cx = rectF.left + (rect.width() * ex);
            float cy = rectF.top + (rect.height() * EYE_Y);
            this.glowPaint.setShader(new RadialGradient(cx, cy, r, new int[]{Color.argb(alpha, 120, 235, 255), Color.argb(alpha / 2, 90, 210, (int) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION), Color.argb(i2, 90, 210, (int) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION)}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP));
            canvas.drawCircle(cx, cy, r, this.glowPaint);
            this.glowPaint.setShader(null);
            i3++;
            rectF = rect;
            i = 2;
            i2 = 0;
        }
    }

    private final void drawChestGlow(Canvas canvas, RectF rect, float amount) {
        float cx = rect.left + (rect.width() * 0.5f);
        float cy = rect.top + (rect.height() * CHEST_Y);
        float r = rect.width() * 0.05f * ((2.2f * amount) + 1.6f);
        int[] colors = this.planner ? new int[]{Color.argb((int) ((160.0f * amount) + 70.0f), 255, 205, 96), Color.argb(0, 255, 184, 60)} : new int[]{Color.argb((int) ((160.0f * amount) + 70.0f), 90, 220, (int) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION), Color.argb(0, 90, 220, (int) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION)};
        this.glowPaint.setShader(new RadialGradient(cx, cy, r, colors, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
        canvas.drawCircle(cx, cy, r, this.glowPaint);
        this.glowPaint.setShader(null);
    }

    private final void drawScene(Canvas canvas, int w, int h) {
        if (this.bgTransitioning && this.nextBg >= 0) {
            this.bgTransition = RangesKt.coerceIn(((float) (System.currentTimeMillis() - this.bgTransStartAt)) / ((float) this.BG_TRANS_MS), 0.0f, 1.0f);
            if (this.bgTransition >= 1.0f) {
                this.currentBg = this.nextBg;
                this.nextBg = -1;
                this.bgTransitioning = false;
            }
        }
        Bitmap curBmp = this.bgBitmaps[this.currentBg];
        if (curBmp != null) {
            this.bgPaint.setAlpha(this.bgTransitioning ? (int) ((1.0f - this.bgTransition) * 255) : 255);
            drawBgBitmap(canvas, curBmp, w, h);
        } else {
            drawGradientFallback(canvas, w, h, this.currentBg);
        }
        if (this.bgTransitioning && this.nextBg >= 0) {
            Bitmap nextBmp = this.bgBitmaps[this.nextBg];
            if (nextBmp != null) {
                this.bgPaint.setAlpha((int) (this.bgTransition * 255));
                drawBgBitmap(canvas, nextBmp, w, h);
            } else {
                drawGradientFallback(canvas, w, h, this.nextBg);
            }
        }
        this.bgPaint.setAlpha(255);
        drawDynamicElements(canvas, w, h);
    }

    private final void drawBgBitmap(Canvas canvas, Bitmap bmp, int w, int h) {
        float drawW;
        float drawH;
        float drawX;
        float drawX2;
        float bmpRatio = bmp.getWidth() / bmp.getHeight();
        float viewRatio = w / h;
        if (bmpRatio > viewRatio) {
            drawH = h;
            drawW = h * bmpRatio;
            float drawW2 = w;
            drawX = (drawW2 - drawW) / 2.0f;
            drawX2 = 0.0f;
        } else {
            drawW = w;
            float drawW3 = w;
            drawH = drawW3 / bmpRatio;
            drawX = 0.0f;
            float drawX3 = h;
            drawX2 = (drawX3 - drawH) / 2.0f;
        }
        canvas.drawBitmap(bmp, (Rect) null, new RectF(drawX, drawX2, drawX + drawW, drawX2 + drawH), this.bgPaint);
    }

    private final void drawGradientFallback(Canvas canvas, int w, int h, int s) {
        Pair pair;
        switch (s) {
            case 1:
                pair = TuplesKt.to(-24981, -9824);
                break;
            case 2:
                pair = TuplesKt.to(-15852742, -14272930);
                break;
            case 3:
                pair = TuplesKt.to(-16381414, -15064256);
                break;
            default:
                pair = TuplesKt.to(-8468238, -2691333);
                break;
        }
        int topColor = ((Number) pair.component1()).intValue();
        int bottomColor = ((Number) pair.component2()).intValue();
        this.scenePaint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, h, topColor, bottomColor, Shader.TileMode.CLAMP));
        this.scenePaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0.0f, 0.0f, w, h, this.scenePaint);
        this.scenePaint.setShader(null);
    }

    private final void drawDynamicElements(Canvas canvas, int w, int h) {
        this.cloudOffset += 0.3f;
        if (this.cloudOffset > w * 2.0f) {
            this.cloudOffset = 0.0f;
        }
        this.starTwinkle += 0.03f;
        if (this.starTwinkle > 1.0f) {
            this.starTwinkle -= 1.0f;
        }
        switch (this.currentBg) {
            case 0:
                drawSunFace(canvas, w, h);
                drawMovingClouds(canvas, w, h, Color.argb((int) ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION, 255, 255, 255), 1.0f);
                return;
            case 1:
                drawSunsetFace(canvas, w, h);
                drawMovingClouds(canvas, w, h, Color.argb(170, 255, 160, 80), 0.6f);
                return;
            case 2:
                drawMoonFace(canvas, w, h);
                drawMovingClouds(canvas, w, h, Color.argb(60, (int) ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION, 210, 240), 0.3f);
                drawTwinklingStars(canvas, w, h, 20);
                return;
            case 3:
                drawTwinklingStars(canvas, w, h, 100);
                drawMeteor(canvas, w, h);
                return;
            default:
                return;
        }
    }

    private final float pxCell(int w) {
        return w / 110.0f;
    }

    private final void drawPixelDisc(Canvas canvas, float cx, float cy, float r, float cell, int color) {
        this.scenePaint.setStyle(Paint.Style.FILL);
        this.scenePaint.setShader(null);
        this.scenePaint.setColor(color);
        int n = (int) Math.ceil(r / cell);
        float r2 = r * r;
        for (int gy = -n; gy <= n; gy++) {
            for (int gx = -n; gx <= n; gx++) {
                float px = (gx * cell) + (cell * 0.5f);
                float py = (gy * cell) + (0.5f * cell);
                if ((px * px) + (py * py) <= r2) {
                    canvas.drawRect(cx + (gx * cell), cy + (gy * cell), cx + ((gx + 1) * cell), cy + ((gy + 1) * cell), this.scenePaint);
                }
            }
        }
    }

    private final void drawPxRect(Canvas canvas, float cx, float cy, int cols, int rows, float cell, int color) {
        this.scenePaint.setStyle(Paint.Style.FILL);
        this.scenePaint.setShader(null);
        this.scenePaint.setColor(color);
        float w = cols * cell;
        float h = rows * cell;
        canvas.drawRect(cx - (w / 2.0f), cy - (h / 2.0f), cx + (w / 2.0f), cy + (h / 2.0f), this.scenePaint);
    }

    private final void drawPixelSmile(Canvas canvas, float cx, float cy, int halfCells, float cell, int color) {
        this.scenePaint.setStyle(Paint.Style.FILL);
        this.scenePaint.setShader(null);
        this.scenePaint.setColor(color);
        int i = -halfCells;
        if (i > halfCells) {
            return;
        }
        while (true) {
            float t = Math.abs(i) / halfCells;
            float yy = cy + ((1.0f - t) * cell * 1.7f);
            canvas.drawRect((cx + (i * cell)) - (cell * EYE_Y), yy - (cell * EYE_Y), cx + (i * cell) + (cell * EYE_Y), yy + (cell * EYE_Y), this.scenePaint);
            if (i == halfCells) {
                return;
            }
            i++;
        }
    }

    private final void drawSunFace(Canvas canvas, int w, int h) {
        float cx = w * 0.78f;
        float cy = h * 0.16f;
        float r = w * 0.085f;
        float cell = pxCell(w);
        float sweatPhase = (this.starTwinkle * 2.0f) % 1.0f;
        canvas.save();
        canvas.translate(cx, cy);
        this.scenePaint.setStyle(Paint.Style.FILL);
        this.scenePaint.setShader(null);
        int rayLen = (int) ((0.55f * r) / cell);
        int i = 0;
        while (i < 12) {
            this.scenePaint.setColor(Color.argb(110, 255, 214, 70));
            canvas.save();
            canvas.rotate(i * 30.0f);
            canvas.drawRect(r * 1.02f, (-cell) * 0.9f, (rayLen * cell) + (1.02f * r), cell * 0.9f, this.scenePaint);
            canvas.restore();
            i++;
            rayLen = rayLen;
        }
        drawPixelDisc(canvas, 0.0f, 0.0f, r, cell, Color.argb(255, 255, 168, 32));
        drawPixelDisc(canvas, 0.0f, 0.0f, r * 0.78f, cell, Color.argb(255, 255, 202, 62));
        drawPixelDisc(canvas, (-r) * 0.12f, (-r) * 0.14f, r * 0.5f, cell, Color.argb(255, 255, 232, 140));
        drawPxRect(canvas, (-r) * 0.32f, (-r) * 0.1f, 2, 3, cell, Color.argb(220, 120, 78, 18));
        drawPxRect(canvas, r * 0.32f, (-r) * 0.1f, 2, 3, cell, Color.argb(220, 120, 78, 18));
        drawPixelSmile(canvas, 0.0f, r * 0.16f, 3, cell, Color.argb(220, 120, 78, 18));
        float sweatY = ((-r) * 0.5f) + (sweatPhase * r * 0.8f);
        int sweatAlpha = (int) (255 * (1.0f - (0.5f * sweatPhase)));
        drawPxRect(canvas, r * 0.62f, sweatY, 2, 3, cell, Color.argb(sweatAlpha, 120, (int) ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION, 255));
        canvas.restore();
    }

    private final void drawSunsetFace(Canvas canvas, int w, int h) {
        MascotView mascotView = this;
        float cx = w * 0.78f;
        float cy = h * 0.16f;
        float r = w * 0.095f;
        float cell = mascotView.pxCell(w);
        canvas.save();
        canvas.translate(cx, cy);
        drawPixelDisc(canvas, 0.0f, 0.0f, r * 1.7f, cell * 1.6f, Color.argb(90, 255, 140, 50));
        drawPixelDisc(canvas, 0.0f, 0.0f, r, cell, Color.argb(255, (int) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 92, 24));
        drawPixelDisc(canvas, 0.0f, 0.0f, r * 0.8f, cell, Color.argb(255, 255, 140, 52));
        drawPixelDisc(canvas, (-r) * 0.1f, (-r) * 0.12f, r * 0.5f, cell, Color.argb(255, 255, 186, 92));
        int i = 150;
        int i2 = 8;
        int i3 = 52;
        int eyeColor = Color.argb(230, 150, 52, 8);
        int[] iArr = {-1, 1};
        int i4 = 0;
        while (i4 < 2) {
            int sx = iArr[i4];
            float ex = 0.24f * sx * r;
            int t = 0;
            while (t < 3) {
                int ww = 3 - t;
                float yy = (t * cell * 1.3f) + ((-r) * 0.16f);
                float xx = ex + (sx * t * cell * 0.7f);
                mascotView.scenePaint.setStyle(Paint.Style.FILL);
                mascotView.scenePaint.setShader(null);
                mascotView.scenePaint.setColor(eyeColor);
                canvas.drawRect(xx - ((ww * cell) * 0.5f), yy - (cell * EYE_Y), xx + (ww * cell * 0.5f), yy + (EYE_Y * cell), mascotView.scenePaint);
                t++;
                mascotView = this;
                i = 150;
                cx = cx;
                sx = sx;
                i4 = i4;
                eyeColor = eyeColor;
            }
            i4++;
            i2 = 8;
            i3 = 52;
            cx = cx;
            mascotView = this;
        }
        drawPixelSmile(canvas, 0.0f, r * 0.12f, 3, cell, Color.argb(230, i, i3, i2));
        drawPixelDisc(canvas, (-r) * 0.46f, r * 0.16f, r * 0.13f, cell, Color.argb(110, 255, 100, 60));
        drawPixelDisc(canvas, r * 0.46f, r * 0.16f, r * 0.13f, cell, Color.argb(110, 255, 100, 60));
        canvas.restore();
    }

    private final void drawMoonFace(Canvas canvas, int w, int h) {
        float cx = w * 0.76f;
        float cy = h * 0.18f;
        float r = w * 0.09f;
        float cell = pxCell(w);
        float breathe = (float) ((Math.sin(this.starTwinkle * 2.0d * 3.141592653589793d) * 0.5d) + 0.5d);
        canvas.save();
        canvas.translate(cx, cy);
        drawPixelDisc(canvas, 0.0f, 0.0f, r * 2.0f, cell * 1.8f, Color.argb((int) ((26.0f * breathe) + 46.0f), (int) ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION, 220, 255));
        drawPixelDisc(canvas, 0.0f, 0.0f, r, cell, Color.argb(255, 214, 222, 214));
        drawPixelDisc(canvas, (-r) * 0.14f, (-r) * 0.16f, r * 0.72f, cell, Color.argb(255, 244, 246, 240));
        drawPixelDisc(canvas, (-r) * 0.3f, r * 0.16f, r * 0.11f, cell, Color.argb(150, 178, 188, 178));
        drawPixelDisc(canvas, r * 0.26f, (-r) * 0.2f, r * 0.08f, cell, Color.argb(140, 180, 190, 180));
        float f = EYE_RX;
        drawPixelDisc(canvas, r * 0.12f, r * 0.3f, r * EYE_RX, cell, Color.argb(130, 180, 190, 180));
        int eyeColor = Color.argb(210, (int) LocationRequestCompat.QUALITY_LOW_POWER, 114, 124);
        boolean z = true;
        int[] iArr = {-1, 1};
        boolean z2 = false;
        int i = 0;
        while (i < 2) {
            int sx = iArr[i];
            float ex = sx * r * 0.25f;
            this.scenePaint.setStyle(Paint.Style.FILL);
            this.scenePaint.setShader(null);
            this.scenePaint.setColor(eyeColor);
            canvas.drawRect(ex - (r * 0.12f), (-r) * f, ex + (r * 0.12f), ((-r) * f) + (1.4f * cell), this.scenePaint);
            i++;
            z = z;
            z2 = false;
            f = EYE_RX;
        }
        drawPixelSmile(canvas, 0.0f, r * 0.2f, 2, cell, Color.argb(190, 120, 130, 140));
        float zzzY = ((-r) * 0.5f) - ((breathe * r) * 0.15f);
        int zzzAlpha = (int) (180 - (80 * breathe));
        this.scenePaint.setColor(Color.argb(zzzAlpha, 180, (int) ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION, 240));
        this.scenePaint.setTextSize(0.34f * r);
        this.scenePaint.setFakeBoldText(z);
        canvas.drawText("Z", 0.5f * r, zzzY, this.scenePaint);
        canvas.drawText("z", 0.78f * r, zzzY - (0.16f * r), this.scenePaint);
        this.scenePaint.setFakeBoldText(false);
        canvas.restore();
    }

    private final void drawMovingClouds(Canvas canvas, int w, int h, int color, float speedFactor) {
        this.scenePaint.setStyle(Paint.Style.FILL);
        this.scenePaint.setShader(null);
        this.scenePaint.setColor(color);
        float cloudY1 = h * 0.15f;
        float cloudY2 = h * 0.28f;
        float c1 = w * 0.05f;
        float c2 = w * 0.038f;
        float x1 = ((this.cloudOffset * speedFactor) % (w * 1.5f)) - (w * 0.25f);
        float x2 = ((((this.cloudOffset * speedFactor) * 0.7f) + (w * 0.5f)) % (w * 1.5f)) - (w * 0.25f);
        drawPixelCloud(canvas, x1, cloudY1, c1);
        drawPixelCloud(canvas, x2, cloudY2, c2);
    }

    private final void drawPixelCloud(Canvas canvas, float x, float y, float u) {
        this.scenePaint.setStyle(Paint.Style.FILL);
        this.scenePaint.setShader(null);
        canvas.drawRect(x - (u * 2.4f), y, x + (2.4f * u), y + (u * 1.4f), this.scenePaint);
        canvas.drawRect(x - (1.4f * u), y - u, x + (1.6f * u), y, this.scenePaint);
        canvas.drawRect(x - (EYE_Y * u), y - (1.7f * u), x + (1.2f * u), y - u, this.scenePaint);
    }

    private final void drawTwinklingStars(Canvas canvas, int w, int h, int count) {
        this.scenePaint.setStyle(Paint.Style.FILL);
        this.scenePaint.setShader(null);
        float s = pxCell(w) * 1.1f;
        long seed = 20240910;
        int i = 0;
        while (i < count) {
            long j = 1103515245;
            long j2 = 12345;
            long seed2 = ((seed * j) + j2) & 2147483647L;
            long j3 = 1000;
            float fx = ((float) (seed2 % j3)) / 1000.0f;
            long seed3 = ((seed2 * j) + j2) & 2147483647L;
            int i2 = i;
            float fy = ((float) (seed3 % j3)) / 1000.0f;
            seed = ((j * seed3) + j2) & 2147483647L;
            float fz = ((float) (seed % j3)) / 1000.0f;
            float phase = (this.starTwinkle + fz) % 1.0f;
            float twinkle = (float) ((Math.sin(phase * 2.0d * 3.141592653589793d) * 0.5d) + 0.5d);
            int alpha = Math.min(255, (int) ((120.0f * twinkle * ((0.6f * fz) + EYE_Y)) + 80.0f));
            this.scenePaint.setColor(Color.argb(alpha, 255, 255, 240));
            float sx = w * fx;
            float sy = h * fy * 0.7f;
            boolean big = fz > 0.78f;
            canvas.drawRect(sx - (s / 2.0f), sy - (s / 2.0f), sx + (s / 2.0f), sy + (s / 2.0f), this.scenePaint);
            if (big) {
                canvas.drawRect(sx - (s * 1.6f), sy - (s * 0.2f), sx + (s * 1.6f), sy + (s * 0.2f), this.scenePaint);
                canvas.drawRect(sx - (s * 0.2f), sy - (s * 1.6f), sx + (0.2f * s), sy + (1.6f * s), this.scenePaint);
            }
            i = i2 + 1;
        }
    }

    private final void drawMeteor(Canvas canvas, int w, int h) {
        float phase = (this.starTwinkle * 1.5f) % 1.0f;
        if (phase > 0.7f) {
            return;
        }
        float startX = w * 0.8f;
        float startY = h * 0.1f;
        float len = w * 0.25f;
        float progress = phase / 0.7f;
        float cx = startX - (len * progress);
        float cy = (EYE_Y * len * progress) + startY;
        float cell = pxCell(w);
        this.scenePaint.setStyle(Paint.Style.FILL);
        this.scenePaint.setShader(null);
        this.scenePaint.setColor(Color.argb((int) ((1.0f - progress) * 200.0f), (int) ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION, 230, 255));
        for (int t = 0; t < 7; t++) {
            float tx = (t * cell * 1.4f) + cx;
            float ty = cy - ((t * cell) * 0.6f);
            float a = 1.0f - (t / 7.0f);
            this.scenePaint.setAlpha((int) ((1.0f - progress) * 200.0f * a));
            canvas.drawRect(tx - (cell * 0.5f), ty - (cell * 0.5f), tx + (cell * 0.5f), ty + (0.5f * cell), this.scenePaint);
        }
        this.scenePaint.setAlpha(255);
    }

    private final void drawAura(Canvas canvas, RectF rect) {
        float cx = rect.centerX();
        float cy = rect.centerY();
        float r = rect.width() * 0.78f;
        if (this.planner) {
            this.glowPaint.setShader(new RadialGradient(cx, cy, r, new int[]{Color.argb(120, 255, 213, 110), Color.argb(54, 255, 196, 70), Color.argb(0, 255, 196, 70)}, new float[]{0.0f, 0.55f, 1.0f}, Shader.TileMode.CLAMP));
            canvas.drawCircle(cx, cy, r, this.glowPaint);
            this.glowPaint.setShader(null);
            return;
        }
        this.glowPaint.setShader(new RadialGradient(cx, cy, r, new int[]{Color.argb(72, 120, 190, 255), Color.argb(30, 120, 190, 255), Color.argb(0, 120, 190, 255)}, new float[]{0.0f, 0.55f, 1.0f}, Shader.TileMode.CLAMP));
        canvas.drawCircle(cx, cy, r, this.glowPaint);
        this.glowPaint.setShader(null);
    }

    private final void drawPlannerMarks(Canvas canvas, RectF rect) {
        float w = rect.width();
        float cx = rect.centerX();
        float chestY = rect.top + (rect.height() * CHEST_Y);
        this.glowPaint.setShader(new RadialGradient(cx, chestY, w * 0.16f, new int[]{Color.argb(150, 255, 206, 92), Color.argb(60, 255, 184, 60), Color.argb(0, 255, 184, 60)}, new float[]{0.0f, 0.55f, 1.0f}, Shader.TileMode.CLAMP));
        canvas.drawCircle(cx, chestY, 0.16f * w, this.glowPaint);
        this.glowPaint.setShader(null);
        float gemW = 0.152f * w;
        float gemH = 0.052f * w;
        int outline = Color.argb(255, 120, 78, 16);
        int gold = Color.argb(255, 255, 199, 74);
        int goldLight = Color.argb(255, 255, 231, 150);
        this.scenePaint.setStyle(Paint.Style.FILL);
        this.scenePaint.setShader(null);
        this.scenePaint.setColor(outline);
        canvas.drawRoundRect(new RectF((cx - (gemW / 2.0f)) - (w * 0.006f), (chestY - (gemH / 2.0f)) - (w * 0.006f), cx + (gemW / 2.0f) + (w * 0.006f), chestY + (gemH / 2.0f) + (0.006f * w)), w * 0.022f, 0.022f * w, this.scenePaint);
        this.scenePaint.setColor(gold);
        canvas.drawRoundRect(new RectF(cx - (gemW / 2.0f), chestY - (gemH / 2.0f), (gemW / 2.0f) + cx, chestY + (gemH / 2.0f)), w * 0.02f, w * 0.02f, this.scenePaint);
        this.scenePaint.setColor(goldLight);
        canvas.drawRoundRect(new RectF(cx - (0.36f * gemW), chestY - (0.3f * gemH), (0.12f * gemW) + cx, chestY - (0.02f * gemH)), w * 0.014f, 0.014f * w, this.scenePaint);
        float labelSize = 0.0385f * w;
        this.scenePaint.setShader(null);
        this.scenePaint.setTextSize(labelSize);
        this.scenePaint.setTypeface(Typeface.create(Typeface.MONOSPACE, 1));
        this.scenePaint.setFakeBoldText(true);
        this.scenePaint.setAntiAlias(false);
        this.scenePaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fm = this.scenePaint.getFontMetrics();
        float labelBaseline = chestY - ((fm.ascent + fm.descent) / 2.0f);
        this.scenePaint.setColor(Color.argb(255, 120, 12, 12));
        canvas.drawText("策划", (w * 0.0018f) + cx, (0.0018f * w) + labelBaseline, this.scenePaint);
        this.scenePaint.setColor(Color.argb(255, 222, 38, 38));
        canvas.drawText("策划", cx, labelBaseline, this.scenePaint);
        this.scenePaint.setTextAlign(Paint.Align.LEFT);
        this.scenePaint.setFakeBoldText(false);
        this.scenePaint.setAntiAlias(true);
        this.scenePaint.setTypeface(Typeface.DEFAULT);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        switch (event.getActionMasked()) {
            case 0:
                this.targetScale = 0.92f;
                this.targetPressOffsetY = getHeight() * 0.015f;
                this.longPressFired = false;
                applyTiltFromTouch(event);
                Runnable task = new Runnable() { // from class: com.xiaofan.bangfan.MascotView$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        MascotView.onTouchEvent$lambda$3(MascotView.this);
                    }
                };
                this.longPressTask = task;
                this.main.postDelayed(task, 650L);
                return true;
            case 1:
            case 3:
                Runnable it = this.longPressTask;
                if (it != null) {
                    this.main.removeCallbacks(it);
                }
                this.targetScale = 1.0f;
                this.targetPressOffsetY = 0.0f;
                this.targetRotX = 0.0f;
                this.targetRotY = 0.0f;
                if (!this.longPressFired) {
                    bounce();
                    this.chestFlash = 1.0f;
                    MascotListener mascotListener = this.listener;
                    if (mascotListener != null) {
                        mascotListener.onMascotTapped();
                    }
                }
                return true;
            case 2:
                applyTiltFromTouch(event);
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTouchEvent$lambda$3(MascotView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!this$0.longPressFired) {
            this$0.longPressFired = true;
            this$0.bounce();
            MascotListener mascotListener = this$0.listener;
            if (mascotListener != null) {
                mascotListener.onMascotLongPressed();
            }
        }
    }

    private final void applyTiltFromTouch(MotionEvent event) {
        float cx = getWidth() / 2.0f;
        float cy = getHeight() / 2.0f;
        float x = (event.getX() - cx) / Math.max(1.0f, cx);
        float y = (event.getY() - cy) / Math.max(1.0f, cy);
        this.targetRotY = clamp(x * MAX_TILT, -14.0f, MAX_TILT);
        this.targetRotX = clamp(y * MAX_TILT, -14.0f, MAX_TILT);
        this.targetGazeX = clamp(x, -1.0f, 1.0f);
        this.targetGazeY = clamp(y, -1.0f, 1.0f);
    }

    private final void bounce() {
        ValueAnimator anim = ValueAnimator.ofFloat(1.12f, 1.0f);
        anim.setDuration(280L);
        anim.setInterpolator(new OvershootInterpolator(2.2f));
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.xiaofan.bangfan.MascotView$$ExternalSyntheticLambda1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                MascotView.bounce$lambda$5(MascotView.this, valueAnimator);
            }
        });
        anim.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bounce$lambda$5(MascotView this$0, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        Object animatedValue = it.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        this$0.targetScale = ((Float) animatedValue).floatValue();
    }

    private final void startRenderLoop() {
        stopRenderLoop();
        Runnable runnable = new Runnable() { // from class: com.xiaofan.bangfan.MascotView$startRenderLoop$task$1
            @Override // java.lang.Runnable
            public void run() {
                Handler handler;
                MascotView.this.updateFrame();
                MascotView.this.invalidate();
                handler = MascotView.this.main;
                handler.postDelayed(this, 16L);
            }
        };
        this.frameTask = runnable;
        this.main.post(runnable);
    }

    private final void stopRenderLoop() {
        Runnable it = this.frameTask;
        if (it != null) {
            this.main.removeCallbacks(it);
        }
        this.frameTask = null;
    }

    private final void startSceneTick() {
        stopSceneTick();
        Runnable runnable = new Runnable() { // from class: com.xiaofan.bangfan.MascotView$startSceneTick$task$1
            @Override // java.lang.Runnable
            public void run() {
                Handler handler;
                long j;
                try {
                    if (MascotView.this.isAttachedToWindow() && MascotView.this.getVisibility() == 0) {
                        MascotView.this.applySceneByTime();
                        MascotView.this.invalidate();
                    }
                } finally {
                    handler = MascotView.this.main;
                    j = MascotView.this.SCENE_TICK_MS;
                    handler.postDelayed(this, j);
                }
            }
        };
        this.sceneTickTask = runnable;
        this.main.postDelayed(runnable, this.SCENE_TICK_MS);
    }

    private final void stopSceneTick() {
        Runnable it = this.sceneTickTask;
        if (it != null) {
            this.main.removeCallbacks(it);
        }
        this.sceneTickTask = null;
        Runnable it2 = this.sceneFinalizeTask;
        if (it2 != null) {
            this.main.removeCallbacks(it2);
        }
        this.sceneFinalizeTask = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateFrame() {
        long sceneNowMs = System.currentTimeMillis();
        if (sceneNowMs - this.lastSceneCheckAt > 20000) {
            this.lastSceneCheckAt = sceneNowMs;
            applySceneByTime();
        }
        this.curRotX += (this.targetRotX - this.curRotX) * 0.12f;
        this.curRotY += (this.targetRotY - this.curRotY) * 0.12f;
        this.gazeX += (this.targetGazeX - this.gazeX) * 0.15f;
        this.gazeY += (this.targetGazeY - this.gazeY) * 0.15f;
        this.pressOffsetY += (this.targetPressOffsetY - this.pressOffsetY) * 0.2f;
        this.scale += (this.targetScale - this.scale) * 0.18f;
        if (this.blinking) {
            this.blinkAmount += 0.12f;
            if (this.blinkAmount >= 1.0f) {
                this.blinkAmount = 1.0f;
                this.blinking = false;
            }
        } else if (this.blinkAmount > 0.0f) {
            this.blinkAmount -= 0.08f;
            if (this.blinkAmount <= 0.0f) {
                this.blinkAmount = 0.0f;
            }
        }
        if (this.chestFlash > 0.0f) {
            this.chestFlash -= 0.04f;
            if (this.chestFlash < 0.0f) {
                this.chestFlash = 0.0f;
            }
        }
        if (this.speaking) {
            long elapsed = System.currentTimeMillis() - this.mouthPhaseStart;
            this.mouthOpen = ((float) ((Math.sin(elapsed * 0.012d) * 0.5d) + 0.5d)) * 0.8f;
        } else {
            this.mouthOpen *= 0.85f;
            if (this.mouthOpen < 0.01f) {
                this.mouthOpen = 0.0f;
            }
        }
        this.shimmerPhase += 0.02f;
        if (this.shimmerPhase > 1.0f) {
            this.shimmerPhase -= 1.0f;
        }
        long nowMs = System.currentTimeMillis();
        this.idleBobY = (float) Math.sin(nowMs / 900.0d);
        this.idleSway = (float) Math.sin(nowMs / 1500.0d);
        this.idleBreath = (((((float) Math.sin(nowMs / 1300.0d)) * 0.5f) + 0.5f) * 0.018f) + 1.0f;
        if (this.blinking || !this.blinkEnabled || this.blinkAmount > 0.0f || System.currentTimeMillis() < this.nextBlinkAt) {
            return;
        }
        triggerBlink();
    }

    private final float clamp(float v, float min, float max) {
        return Math.min(Math.max(v, min), max);
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View changedView, int visibility) {
        Intrinsics.checkNotNullParameter(changedView, "changedView");
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == 0) {
            invalidate();
        }
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility == 0) {
            invalidate();
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        XiaoFanVoice.INSTANCE.detachMascot(this);
        stopRenderLoop();
        stopSceneTick();
        Runnable it = this.longPressTask;
        if (it != null) {
            this.main.removeCallbacks(it);
        }
        Bitmap it2 = this.body;
        if (it2 != null && !it2.isRecycled()) {
            it2.recycle();
        }
        this.body = null;
        int length = this.bgBitmaps.length;
        for (int i = 0; i < length; i++) {
            Bitmap it3 = this.bgBitmaps[i];
            if (it3 != null && !it3.isRecycled()) {
                it3.recycle();
            }
            this.bgBitmaps[i] = null;
        }
    }
}
