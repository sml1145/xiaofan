package org.webrtc;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Arrays;
import java.util.LinkedHashSet;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.webrtc.EglBase;
/* compiled from: DefaultAlignedVideoEncoderFactory.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B+\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0012\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0013\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011H\u0016¢\u0006\u0002\u0010\u0012R\u000e\u0010\n\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/webrtc/DefaultAlignedVideoEncoderFactory;", "Lorg/webrtc/VideoEncoderFactory;", "eglContext", "Lorg/webrtc/EglBase$Context;", "enableIntelVp8Encoder", "", "enableH264HighProfile", "resolutionAdjustment", "Lorg/webrtc/ResolutionAdjustment;", "(Lorg/webrtc/EglBase$Context;ZZLorg/webrtc/ResolutionAdjustment;)V", "hardwareVideoEncoderFactory", "softwareVideoEncoderFactory", "createEncoder", "Lorg/webrtc/VideoEncoder;", "info", "Lorg/webrtc/VideoCodecInfo;", "getSupportedCodecs", "", "()[Lorg/webrtc/VideoCodecInfo;", "stream-webrtc-android_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class DefaultAlignedVideoEncoderFactory implements VideoEncoderFactory {
    private final VideoEncoderFactory hardwareVideoEncoderFactory;
    private final VideoEncoderFactory softwareVideoEncoderFactory;

    public DefaultAlignedVideoEncoderFactory(EglBase.Context eglContext, boolean enableIntelVp8Encoder, boolean enableH264HighProfile, ResolutionAdjustment resolutionAdjustment) {
        HardwareVideoEncoderWrapperFactory hardwareVideoEncoderWrapperFactory;
        Intrinsics.checkNotNullParameter(resolutionAdjustment, "resolutionAdjustment");
        this.softwareVideoEncoderFactory = new SoftwareVideoEncoderFactory();
        HardwareVideoEncoderFactory defaultFactory = new HardwareVideoEncoderFactory(eglContext, enableIntelVp8Encoder, enableH264HighProfile);
        if (resolutionAdjustment == ResolutionAdjustment.NONE) {
            hardwareVideoEncoderWrapperFactory = defaultFactory;
        } else {
            hardwareVideoEncoderWrapperFactory = new HardwareVideoEncoderWrapperFactory(defaultFactory, resolutionAdjustment.getValue());
        }
        this.hardwareVideoEncoderFactory = hardwareVideoEncoderWrapperFactory;
    }

    public /* synthetic */ DefaultAlignedVideoEncoderFactory(EglBase.Context context, boolean z, boolean z2, ResolutionAdjustment resolutionAdjustment, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? true : z, (i & 4) != 0 ? false : z2, resolutionAdjustment);
    }

    @Override // org.webrtc.VideoEncoderFactory
    public VideoEncoder createEncoder(VideoCodecInfo info) {
        Intrinsics.checkNotNullParameter(info, "info");
        VideoEncoder softwareEncoder = this.softwareVideoEncoderFactory.createEncoder(info);
        VideoEncoder hardwareEncoder = this.hardwareVideoEncoderFactory.createEncoder(info);
        if (hardwareEncoder == null || softwareEncoder == null) {
            return hardwareEncoder == null ? softwareEncoder : hardwareEncoder;
        }
        return new VideoEncoderFallback(softwareEncoder, hardwareEncoder);
    }

    @Override // org.webrtc.VideoEncoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        LinkedHashSet supportedCodecInfos = new LinkedHashSet();
        VideoCodecInfo[] supportedCodecs = this.softwareVideoEncoderFactory.getSupportedCodecs();
        Intrinsics.checkNotNullExpressionValue(supportedCodecs, "getSupportedCodecs(...)");
        supportedCodecInfos.addAll(CollectionsKt.listOf(Arrays.copyOf(supportedCodecs, supportedCodecs.length)));
        VideoCodecInfo[] supportedCodecs2 = this.hardwareVideoEncoderFactory.getSupportedCodecs();
        Intrinsics.checkNotNullExpressionValue(supportedCodecs2, "getSupportedCodecs(...)");
        supportedCodecInfos.addAll(CollectionsKt.listOf(Arrays.copyOf(supportedCodecs2, supportedCodecs2.length)));
        LinkedHashSet $this$toTypedArray$iv = supportedCodecInfos;
        return (VideoCodecInfo[]) $this$toTypedArray$iv.toArray(new VideoCodecInfo[0]);
    }
}
