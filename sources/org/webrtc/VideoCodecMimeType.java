package org.webrtc;
/* loaded from: classes5.dex */
enum VideoCodecMimeType {
    VP8("video/x-vnd.on2.vp8"),
    VP9("video/x-vnd.on2.vp9"),
    H264("video/avc"),
    AV1("video/av01"),
    H265("video/hevc");
    
    private final String mimeType;

    VideoCodecMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String mimeType() {
        return this.mimeType;
    }
}
