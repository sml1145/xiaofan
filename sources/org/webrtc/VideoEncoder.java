package org.webrtc;

import org.webrtc.EncodedImage;
/* loaded from: classes5.dex */
public interface VideoEncoder {

    /* loaded from: classes5.dex */
    public interface Callback {
        void onEncodedFrame(EncodedImage encodedImage, CodecSpecificInfo codecSpecificInfo);
    }

    /* loaded from: classes5.dex */
    public static class CodecSpecificInfo {
    }

    /* loaded from: classes5.dex */
    public static class CodecSpecificInfoAV1 extends CodecSpecificInfo {
    }

    /* loaded from: classes5.dex */
    public static class CodecSpecificInfoH264 extends CodecSpecificInfo {
    }

    /* loaded from: classes5.dex */
    public static class CodecSpecificInfoVP8 extends CodecSpecificInfo {
    }

    /* loaded from: classes5.dex */
    public static class CodecSpecificInfoVP9 extends CodecSpecificInfo {
    }

    VideoCodecStatus encode(VideoFrame videoFrame, EncodeInfo encodeInfo);

    String getImplementationName();

    ScalingSettings getScalingSettings();

    VideoCodecStatus initEncode(Settings settings, Callback callback);

    VideoCodecStatus release();

    VideoCodecStatus setRateAllocation(BitrateAllocation bitrateAllocation, int i);

    /* loaded from: classes5.dex */
    public static class Settings {
        public final boolean automaticResizeOn;
        public final Capabilities capabilities;
        public final int height;
        public final int maxFramerate;
        public final int numberOfCores;
        public final int numberOfSimulcastStreams;
        public final int startBitrate;
        public final int width;

        @Deprecated
        public Settings(int numberOfCores, int width, int height, int startBitrate, int maxFramerate, int numberOfSimulcastStreams, boolean automaticResizeOn) {
            this(numberOfCores, width, height, startBitrate, maxFramerate, numberOfSimulcastStreams, automaticResizeOn, new Capabilities(false));
        }

        public Settings(int numberOfCores, int width, int height, int startBitrate, int maxFramerate, int numberOfSimulcastStreams, boolean automaticResizeOn, Capabilities capabilities) {
            this.numberOfCores = numberOfCores;
            this.width = width;
            this.height = height;
            this.startBitrate = startBitrate;
            this.maxFramerate = maxFramerate;
            this.numberOfSimulcastStreams = numberOfSimulcastStreams;
            this.automaticResizeOn = automaticResizeOn;
            this.capabilities = capabilities;
        }
    }

    /* loaded from: classes5.dex */
    public static class Capabilities {
        public final boolean lossNotification;

        public Capabilities(boolean lossNotification) {
            this.lossNotification = lossNotification;
        }
    }

    /* loaded from: classes5.dex */
    public static class EncodeInfo {
        public final EncodedImage.FrameType[] frameTypes;

        public EncodeInfo(EncodedImage.FrameType[] frameTypes) {
            this.frameTypes = frameTypes;
        }
    }

    /* loaded from: classes5.dex */
    public static class BitrateAllocation {
        public final int[][] bitratesBbs;

        public BitrateAllocation(int[][] bitratesBbs) {
            this.bitratesBbs = bitratesBbs;
        }

        public int getSum() {
            int[][] iArr;
            int sum = 0;
            for (int[] spatialLayer : this.bitratesBbs) {
                for (int bitrate : spatialLayer) {
                    sum += bitrate;
                }
            }
            return sum;
        }
    }

    /* loaded from: classes5.dex */
    public static class ScalingSettings {
        public static final ScalingSettings OFF = new ScalingSettings();
        public final Integer high;
        public final Integer low;
        public final boolean on;

        public ScalingSettings(int low, int high) {
            this.on = true;
            this.low = Integer.valueOf(low);
            this.high = Integer.valueOf(high);
        }

        private ScalingSettings() {
            this.on = false;
            this.low = null;
            this.high = null;
        }

        @Deprecated
        public ScalingSettings(boolean on) {
            this.on = on;
            this.low = null;
            this.high = null;
        }

        @Deprecated
        public ScalingSettings(boolean on, int low, int high) {
            this.on = on;
            this.low = Integer.valueOf(low);
            this.high = Integer.valueOf(high);
        }

        public String toString() {
            return this.on ? "[ " + this.low + ", " + this.high + " ]" : "OFF";
        }
    }

    /* loaded from: classes5.dex */
    public static class ResolutionBitrateLimits {
        public final int frameSizePixels;
        public final int maxBitrateBps;
        public final int minBitrateBps;
        public final int minStartBitrateBps;

        public ResolutionBitrateLimits(int frameSizePixels, int minStartBitrateBps, int minBitrateBps, int maxBitrateBps) {
            this.frameSizePixels = frameSizePixels;
            this.minStartBitrateBps = minStartBitrateBps;
            this.minBitrateBps = minBitrateBps;
            this.maxBitrateBps = maxBitrateBps;
        }

        public int getFrameSizePixels() {
            return this.frameSizePixels;
        }

        public int getMinStartBitrateBps() {
            return this.minStartBitrateBps;
        }

        public int getMinBitrateBps() {
            return this.minBitrateBps;
        }

        public int getMaxBitrateBps() {
            return this.maxBitrateBps;
        }
    }

    /* loaded from: classes5.dex */
    public static class RateControlParameters {
        public final BitrateAllocation bitrate;
        public final double framerateFps;

        public RateControlParameters(BitrateAllocation bitrate, double framerateFps) {
            this.bitrate = bitrate;
            this.framerateFps = framerateFps;
        }
    }

    /* loaded from: classes5.dex */
    public static class EncoderInfo {
        public final boolean applyAlignmentToAllSimulcastLayers;
        public final int requestedResolutionAlignment;

        public EncoderInfo(int requestedResolutionAlignment, boolean applyAlignmentToAllSimulcastLayers) {
            this.requestedResolutionAlignment = requestedResolutionAlignment;
            this.applyAlignmentToAllSimulcastLayers = applyAlignmentToAllSimulcastLayers;
        }

        public int getRequestedResolutionAlignment() {
            return this.requestedResolutionAlignment;
        }

        public boolean getApplyAlignmentToAllSimulcastLayers() {
            return this.applyAlignmentToAllSimulcastLayers;
        }
    }

    default long createNative(long webrtcEnvRef) {
        return 0L;
    }

    default boolean isHardwareEncoder() {
        return true;
    }

    default VideoCodecStatus setRates(RateControlParameters rcParameters) {
        int framerateFps = (int) Math.ceil(rcParameters.framerateFps);
        return setRateAllocation(rcParameters.bitrate, framerateFps);
    }

    default ResolutionBitrateLimits[] getResolutionBitrateLimits() {
        ResolutionBitrateLimits[] bitrate_limits = new ResolutionBitrateLimits[0];
        return bitrate_limits;
    }

    default EncoderInfo getEncoderInfo() {
        return new EncoderInfo(1, false);
    }
}
