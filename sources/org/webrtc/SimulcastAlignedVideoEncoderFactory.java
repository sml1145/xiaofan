package org.webrtc;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.webrtc.EglBase;
import org.webrtc.SimulcastAlignedVideoEncoderFactory;
import org.webrtc.VideoEncoder;
import org.webrtc.VideoFrame;
/* compiled from: SimulcastAlignedVideoEncoderFactory.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0015\u0016B+\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0013\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013H\u0016¢\u0006\u0002\u0010\u0014R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/webrtc/SimulcastAlignedVideoEncoderFactory;", "Lorg/webrtc/VideoEncoderFactory;", "sharedContext", "Lorg/webrtc/EglBase$Context;", "enableIntelVp8Encoder", "", "enableH264HighProfile", "resolutionAdjustment", "Lorg/webrtc/ResolutionAdjustment;", "(Lorg/webrtc/EglBase$Context;ZZLorg/webrtc/ResolutionAdjustment;)V", "fallback", "native", "Lorg/webrtc/SimulcastVideoEncoderFactory;", "primary", "createEncoder", "Lorg/webrtc/VideoEncoder;", "info", "Lorg/webrtc/VideoCodecInfo;", "getSupportedCodecs", "", "()[Lorg/webrtc/VideoCodecInfo;", "StreamEncoderWrapper", "StreamEncoderWrapperFactory", "stream-webrtc-android_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class SimulcastAlignedVideoEncoderFactory implements VideoEncoderFactory {
    private final VideoEncoderFactory fallback;

    /* renamed from: native  reason: not valid java name */
    private final SimulcastVideoEncoderFactory f5native;
    private final VideoEncoderFactory primary;

    public SimulcastAlignedVideoEncoderFactory(EglBase.Context sharedContext, boolean enableIntelVp8Encoder, boolean enableH264HighProfile, ResolutionAdjustment resolutionAdjustment) {
        HardwareVideoEncoderWrapperFactory encoderFactory;
        Intrinsics.checkNotNullParameter(resolutionAdjustment, "resolutionAdjustment");
        HardwareVideoEncoderFactory hardwareVideoEncoderFactory = new HardwareVideoEncoderFactory(sharedContext, enableIntelVp8Encoder, enableH264HighProfile);
        if (resolutionAdjustment == ResolutionAdjustment.NONE) {
            encoderFactory = hardwareVideoEncoderFactory;
        } else {
            encoderFactory = new HardwareVideoEncoderWrapperFactory(hardwareVideoEncoderFactory, resolutionAdjustment.getValue());
        }
        this.primary = new StreamEncoderWrapperFactory(encoderFactory);
        this.fallback = new SoftwareVideoEncoderFactory();
        this.f5native = new SimulcastVideoEncoderFactory(this.primary, this.fallback);
    }

    public /* synthetic */ SimulcastAlignedVideoEncoderFactory(EglBase.Context context, boolean z, boolean z2, ResolutionAdjustment resolutionAdjustment, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? true : z, (i & 4) != 0 ? false : z2, resolutionAdjustment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SimulcastAlignedVideoEncoderFactory.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u001a\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u001a\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0016\u001a\u00020\tH\u0016J\u001a\u0010\u0017\u001a\u00020\t2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lorg/webrtc/SimulcastAlignedVideoEncoderFactory$StreamEncoderWrapper;", "Lorg/webrtc/VideoEncoder;", "encoder", "(Lorg/webrtc/VideoEncoder;)V", "executor", "Ljava/util/concurrent/ExecutorService;", "streamSettings", "Lorg/webrtc/VideoEncoder$Settings;", "encode", "Lorg/webrtc/VideoCodecStatus;", "frame", "Lorg/webrtc/VideoFrame;", "encodeInfo", "Lorg/webrtc/VideoEncoder$EncodeInfo;", "getImplementationName", "", "getScalingSettings", "Lorg/webrtc/VideoEncoder$ScalingSettings;", "initEncode", "settings", "callback", "Lorg/webrtc/VideoEncoder$Callback;", "release", "setRateAllocation", "allocation", "Lorg/webrtc/VideoEncoder$BitrateAllocation;", "frameRate", "", "Companion", "stream-webrtc-android_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes5.dex */
    public static final class StreamEncoderWrapper implements VideoEncoder {
        public static final Companion Companion = new Companion(null);
        private static final String TAG = Reflection.getOrCreateKotlinClass(StreamEncoderWrapper.class).getSimpleName();
        private final VideoEncoder encoder;
        private final ExecutorService executor;
        private VideoEncoder.Settings streamSettings;

        public StreamEncoderWrapper(VideoEncoder encoder) {
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            this.encoder = encoder;
            ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
            Intrinsics.checkNotNullExpressionValue(newSingleThreadExecutor, "newSingleThreadExecutor(...)");
            this.executor = newSingleThreadExecutor;
        }

        /* compiled from: SimulcastAlignedVideoEncoderFactory.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/webrtc/SimulcastAlignedVideoEncoderFactory$StreamEncoderWrapper$Companion;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "stream-webrtc-android_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        /* loaded from: classes5.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final String getTAG() {
                return StreamEncoderWrapper.TAG;
            }
        }

        @Override // org.webrtc.VideoEncoder
        public VideoCodecStatus initEncode(final VideoEncoder.Settings settings, final VideoEncoder.Callback callback) {
            Intrinsics.checkNotNullParameter(settings, "settings");
            this.streamSettings = settings;
            Future future = this.executor.submit(new Callable() { // from class: org.webrtc.SimulcastAlignedVideoEncoderFactory$StreamEncoderWrapper$$ExternalSyntheticLambda4
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    VideoCodecStatus initEncode$lambda$0;
                    initEncode$lambda$0 = SimulcastAlignedVideoEncoderFactory.StreamEncoderWrapper.initEncode$lambda$0(SimulcastAlignedVideoEncoderFactory.StreamEncoderWrapper.this, settings, callback);
                    return initEncode$lambda$0;
                }
            });
            Object obj = future.get();
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return (VideoCodecStatus) obj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final VideoCodecStatus initEncode$lambda$0(StreamEncoderWrapper this$0, VideoEncoder.Settings settings, VideoEncoder.Callback $callback) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(settings, "$settings");
            String str = TAG;
            String name = Thread.currentThread().getName();
            long id = Thread.currentThread().getId();
            String implementationName = this$0.encoder.getImplementationName();
            int i = settings.numberOfCores;
            int i2 = settings.width;
            int i3 = settings.height;
            int i4 = settings.startBitrate;
            int i5 = settings.maxFramerate;
            boolean z = settings.automaticResizeOn;
            int i6 = settings.numberOfSimulcastStreams;
            Logging.v(str, StringsKt.trimMargin$default("initEncode() thread=" + name + " [" + id + "]\n                        |  encoder=" + implementationName + "\n                        |  streamSettings:\n                        |    numberOfCores=" + i + "\n                        |    width=" + i2 + "\n                        |    height=" + i3 + "\n                        |    startBitrate=" + i4 + "\n                        |    maxFramerate=" + i5 + "\n                        |    automaticResizeOn=" + z + "\n                        |    numberOfSimulcastStreams=" + i6 + "\n                        |    lossNotification=" + settings.capabilities.lossNotification + "\n            ", null, 1, null));
            return this$0.encoder.initEncode(settings, $callback);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final VideoCodecStatus release$lambda$1(StreamEncoderWrapper this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            return this$0.encoder.release();
        }

        @Override // org.webrtc.VideoEncoder
        public VideoCodecStatus release() {
            Future future = this.executor.submit(new Callable() { // from class: org.webrtc.SimulcastAlignedVideoEncoderFactory$StreamEncoderWrapper$$ExternalSyntheticLambda3
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    VideoCodecStatus release$lambda$1;
                    release$lambda$1 = SimulcastAlignedVideoEncoderFactory.StreamEncoderWrapper.release$lambda$1(SimulcastAlignedVideoEncoderFactory.StreamEncoderWrapper.this);
                    return release$lambda$1;
                }
            });
            Object obj = future.get();
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return (VideoCodecStatus) obj;
        }

        @Override // org.webrtc.VideoEncoder
        public VideoCodecStatus encode(final VideoFrame frame, final VideoEncoder.EncodeInfo encodeInfo) {
            Intrinsics.checkNotNullParameter(frame, "frame");
            Future future = this.executor.submit(new Callable() { // from class: org.webrtc.SimulcastAlignedVideoEncoderFactory$StreamEncoderWrapper$$ExternalSyntheticLambda1
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    VideoCodecStatus encode$lambda$4;
                    encode$lambda$4 = SimulcastAlignedVideoEncoderFactory.StreamEncoderWrapper.encode$lambda$4(SimulcastAlignedVideoEncoderFactory.StreamEncoderWrapper.this, frame, encodeInfo);
                    return encode$lambda$4;
                }
            });
            Object obj = future.get();
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return (VideoCodecStatus) obj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final VideoCodecStatus encode$lambda$4(StreamEncoderWrapper this$0, VideoFrame frame, VideoEncoder.EncodeInfo $encodeInfo) {
            VideoCodecStatus videoCodecStatus;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(frame, "$frame");
            VideoEncoder.Settings it = this$0.streamSettings;
            if (it == null) {
                videoCodecStatus = null;
            } else if (frame.getBuffer().getWidth() == it.width) {
                videoCodecStatus = this$0.encoder.encode(frame, $encodeInfo);
            } else {
                int originalWidth = frame.getBuffer().getWidth();
                int originalHeight = frame.getBuffer().getHeight();
                VideoFrame.Buffer scaledBuffer = frame.getBuffer().cropAndScale(0, 0, originalWidth, originalHeight, it.width, it.height);
                VideoFrame scaledFrame = new VideoFrame(scaledBuffer, frame.getRotation(), frame.getTimestampNs());
                VideoCodecStatus result = this$0.encoder.encode(scaledFrame, $encodeInfo);
                scaledBuffer.release();
                videoCodecStatus = result;
            }
            if (videoCodecStatus != null) {
                return videoCodecStatus;
            }
            return VideoCodecStatus.ERROR;
        }

        @Override // org.webrtc.VideoEncoder
        public VideoCodecStatus setRateAllocation(final VideoEncoder.BitrateAllocation allocation, final int frameRate) {
            Future future = this.executor.submit(new Callable() { // from class: org.webrtc.SimulcastAlignedVideoEncoderFactory$StreamEncoderWrapper$$ExternalSyntheticLambda5
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    VideoCodecStatus rateAllocation$lambda$5;
                    rateAllocation$lambda$5 = SimulcastAlignedVideoEncoderFactory.StreamEncoderWrapper.setRateAllocation$lambda$5(SimulcastAlignedVideoEncoderFactory.StreamEncoderWrapper.this, allocation, frameRate);
                    return rateAllocation$lambda$5;
                }
            });
            Object obj = future.get();
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return (VideoCodecStatus) obj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final VideoCodecStatus setRateAllocation$lambda$5(StreamEncoderWrapper this$0, VideoEncoder.BitrateAllocation $allocation, int $frameRate) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            return this$0.encoder.setRateAllocation($allocation, $frameRate);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final VideoEncoder.ScalingSettings getScalingSettings$lambda$6(StreamEncoderWrapper this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            return this$0.encoder.getScalingSettings();
        }

        @Override // org.webrtc.VideoEncoder
        public VideoEncoder.ScalingSettings getScalingSettings() {
            Future future = this.executor.submit(new Callable() { // from class: org.webrtc.SimulcastAlignedVideoEncoderFactory$StreamEncoderWrapper$$ExternalSyntheticLambda2
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    VideoEncoder.ScalingSettings scalingSettings$lambda$6;
                    scalingSettings$lambda$6 = SimulcastAlignedVideoEncoderFactory.StreamEncoderWrapper.getScalingSettings$lambda$6(SimulcastAlignedVideoEncoderFactory.StreamEncoderWrapper.this);
                    return scalingSettings$lambda$6;
                }
            });
            Object obj = future.get();
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return (VideoEncoder.ScalingSettings) obj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String getImplementationName$lambda$7(StreamEncoderWrapper this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            return this$0.encoder.getImplementationName();
        }

        @Override // org.webrtc.VideoEncoder
        public String getImplementationName() {
            Future future = this.executor.submit(new Callable() { // from class: org.webrtc.SimulcastAlignedVideoEncoderFactory$StreamEncoderWrapper$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    String implementationName$lambda$7;
                    implementationName$lambda$7 = SimulcastAlignedVideoEncoderFactory.StreamEncoderWrapper.getImplementationName$lambda$7(SimulcastAlignedVideoEncoderFactory.StreamEncoderWrapper.this);
                    return implementationName$lambda$7;
                }
            });
            Object obj = future.get();
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return (String) obj;
        }
    }

    /* compiled from: SimulcastAlignedVideoEncoderFactory.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0013\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0016¢\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/webrtc/SimulcastAlignedVideoEncoderFactory$StreamEncoderWrapperFactory;", "Lorg/webrtc/VideoEncoderFactory;", "factory", "(Lorg/webrtc/VideoEncoderFactory;)V", "createEncoder", "Lorg/webrtc/VideoEncoder;", "videoCodecInfo", "Lorg/webrtc/VideoCodecInfo;", "getSupportedCodecs", "", "()[Lorg/webrtc/VideoCodecInfo;", "stream-webrtc-android_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes5.dex */
    private static final class StreamEncoderWrapperFactory implements VideoEncoderFactory {
        private final VideoEncoderFactory factory;

        public StreamEncoderWrapperFactory(VideoEncoderFactory factory) {
            Intrinsics.checkNotNullParameter(factory, "factory");
            this.factory = factory;
        }

        @Override // org.webrtc.VideoEncoderFactory
        public VideoEncoder createEncoder(VideoCodecInfo videoCodecInfo) {
            VideoEncoder encoder = this.factory.createEncoder(videoCodecInfo);
            if (encoder == null) {
                return null;
            }
            return new StreamEncoderWrapper(encoder);
        }

        @Override // org.webrtc.VideoEncoderFactory
        public VideoCodecInfo[] getSupportedCodecs() {
            VideoCodecInfo[] supportedCodecs = this.factory.getSupportedCodecs();
            Intrinsics.checkNotNullExpressionValue(supportedCodecs, "getSupportedCodecs(...)");
            return supportedCodecs;
        }
    }

    @Override // org.webrtc.VideoEncoderFactory
    public VideoEncoder createEncoder(VideoCodecInfo info) {
        return this.f5native.createEncoder(info);
    }

    @Override // org.webrtc.VideoEncoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        VideoCodecInfo[] supportedCodecs = this.f5native.getSupportedCodecs();
        Intrinsics.checkNotNullExpressionValue(supportedCodecs, "getSupportedCodecs(...)");
        return supportedCodecs;
    }
}
