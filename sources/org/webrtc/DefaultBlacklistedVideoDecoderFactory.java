package org.webrtc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.webrtc.EglBase;
/* loaded from: classes5.dex */
public class DefaultBlacklistedVideoDecoderFactory implements VideoDecoderFactory {
    private static final String TAG = "DefaultBlacklistedVideoDecoderFactory";
    private static final java.util.function.Predicate<VideoDecoder> defaultBlacklistedPredicate = new java.util.function.Predicate() { // from class: org.webrtc.DefaultBlacklistedVideoDecoderFactory$$ExternalSyntheticLambda0
        @Override // java.util.function.Predicate
        public final boolean test(Object obj) {
            boolean isExynosVP9;
            isExynosVP9 = DefaultBlacklistedVideoDecoderFactory.isExynosVP9((VideoDecoder) obj);
            return isExynosVP9;
        }
    };
    private final VideoDecoderFactory hardwareVideoDecoderFactory;
    private final java.util.function.Predicate<VideoDecoder> isHardwareDecoderBlacklisted;
    private final VideoDecoderFactory platformSoftwareVideoDecoderFactory;
    private final VideoDecoderFactory softwareVideoDecoderFactory;

    public DefaultBlacklistedVideoDecoderFactory(EglBase.Context eglContext) {
        this(eglContext, null);
    }

    public DefaultBlacklistedVideoDecoderFactory(EglBase.Context eglContext, java.util.function.Predicate<VideoDecoder> decoderBlacklistedPredicate) {
        java.util.function.Predicate<VideoDecoder> or;
        this.hardwareVideoDecoderFactory = new HardwareVideoDecoderFactory(eglContext);
        this.softwareVideoDecoderFactory = new SoftwareVideoDecoderFactory();
        this.platformSoftwareVideoDecoderFactory = new PlatformSoftwareVideoDecoderFactory(eglContext);
        if (decoderBlacklistedPredicate == null) {
            or = defaultBlacklistedPredicate;
        } else {
            or = decoderBlacklistedPredicate.or(defaultBlacklistedPredicate);
        }
        this.isHardwareDecoderBlacklisted = or;
    }

    @Override // org.webrtc.VideoDecoderFactory
    public VideoDecoder createDecoder(VideoCodecInfo codecType) {
        VideoDecoder softwareDecoder = this.softwareVideoDecoderFactory.createDecoder(codecType);
        VideoDecoder hardwareDecoder = this.hardwareVideoDecoderFactory.createDecoder(codecType);
        if (softwareDecoder == null) {
            softwareDecoder = this.platformSoftwareVideoDecoderFactory.createDecoder(codecType);
        }
        if (this.isHardwareDecoderBlacklisted.test(hardwareDecoder)) {
            Logging.d(TAG, "Hardware decoder is blacklisted: " + hardwareDecoder.getImplementationName());
            return softwareDecoder;
        } else if (hardwareDecoder == null || softwareDecoder == null) {
            return hardwareDecoder != null ? hardwareDecoder : softwareDecoder;
        } else {
            return new VideoDecoderFallback(softwareDecoder, hardwareDecoder);
        }
    }

    @Override // org.webrtc.VideoDecoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        Set<VideoCodecInfo> supportedCodecInfos = new HashSet<>();
        supportedCodecInfos.addAll(Arrays.asList(this.softwareVideoDecoderFactory.getSupportedCodecs()));
        supportedCodecInfos.addAll(Arrays.asList(this.hardwareVideoDecoderFactory.getSupportedCodecs()));
        supportedCodecInfos.addAll(Arrays.asList(this.platformSoftwareVideoDecoderFactory.getSupportedCodecs()));
        return (VideoCodecInfo[]) supportedCodecInfos.toArray(new VideoCodecInfo[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isExynosVP9(VideoDecoder decoder) {
        if (decoder == null) {
            return false;
        }
        String name = decoder.getImplementationName().toLowerCase();
        return name.contains("exynos") && name.contains("vp9");
    }
}
