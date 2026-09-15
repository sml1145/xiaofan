package org.webrtc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes5.dex */
public class VideoCodecInfo {
    public static final String H264_CONSTRAINED_BASELINE_3_1 = "42e01f";
    public static final String H264_CONSTRAINED_HIGH_3_1 = "640c1f";
    public static final String H264_FMTP_LEVEL_ASYMMETRY_ALLOWED = "level-asymmetry-allowed";
    public static final String H264_FMTP_PACKETIZATION_MODE = "packetization-mode";
    public static final String H264_FMTP_PROFILE_LEVEL_ID = "profile-level-id";
    public static final String H264_LEVEL_3_1 = "1f";
    public static final String H264_PROFILE_CONSTRAINED_BASELINE = "42e0";
    public static final String H264_PROFILE_CONSTRAINED_HIGH = "640c";
    public final String name;
    public final Map<String, String> params;
    @Deprecated
    public final int payload;
    public final List<String> scalabilityModes;

    public VideoCodecInfo(String name, Map<String, String> params, List<String> scalabilityModes) {
        this.payload = 0;
        this.name = name;
        this.params = params;
        this.scalabilityModes = scalabilityModes;
    }

    @Deprecated
    public VideoCodecInfo(int payload, String name, Map<String, String> params) {
        this.payload = payload;
        this.name = name;
        this.params = params;
        this.scalabilityModes = new ArrayList();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VideoCodecInfo)) {
            return false;
        }
        VideoCodecInfo otherInfo = (VideoCodecInfo) obj;
        if (!this.name.equalsIgnoreCase(otherInfo.name) || !this.params.equals(otherInfo.params)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        Object[] values = {this.name.toUpperCase(Locale.ROOT), this.params};
        return Arrays.hashCode(values);
    }

    public String toString() {
        return "VideoCodec{" + this.name + " " + this.params + "}";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getName() {
        return this.name;
    }

    Map getParams() {
        return this.params;
    }

    List<String> getScalabilityModes() {
        return this.scalabilityModes;
    }
}
