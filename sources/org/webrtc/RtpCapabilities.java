package org.webrtc;

import java.util.List;
import java.util.Map;
import org.webrtc.MediaStreamTrack;
/* loaded from: classes5.dex */
public class RtpCapabilities {
    public List<CodecCapability> codecs;
    public List<HeaderExtensionCapability> headerExtensions;

    /* loaded from: classes5.dex */
    public static class CodecCapability {
        public Integer clockRate;
        public MediaStreamTrack.MediaType kind;
        public String mimeType;
        public String name;
        public Integer numChannels;
        public Map<String, String> parameters;
        public int preferredPayloadType;

        public CodecCapability() {
        }

        CodecCapability(int preferredPayloadType, String name, MediaStreamTrack.MediaType kind, Integer clockRate, Integer numChannels, String mimeType, Map<String, String> parameters) {
            this.preferredPayloadType = preferredPayloadType;
            this.name = name;
            this.kind = kind;
            this.clockRate = clockRate;
            this.numChannels = numChannels;
            this.parameters = parameters;
            this.mimeType = mimeType;
        }

        int getPreferredPayloadType() {
            return this.preferredPayloadType;
        }

        String getName() {
            return this.name;
        }

        MediaStreamTrack.MediaType getKind() {
            return this.kind;
        }

        Integer getClockRate() {
            return this.clockRate;
        }

        Integer getNumChannels() {
            return this.numChannels;
        }

        Map getParameters() {
            return this.parameters;
        }
    }

    /* loaded from: classes5.dex */
    public static class HeaderExtensionCapability {
        private final boolean preferredEncrypted;
        private final int preferredId;
        private final String uri;

        HeaderExtensionCapability(String uri, int preferredId, boolean preferredEncrypted) {
            this.uri = uri;
            this.preferredId = preferredId;
            this.preferredEncrypted = preferredEncrypted;
        }

        public String getUri() {
            return this.uri;
        }

        public int getPreferredId() {
            return this.preferredId;
        }

        public boolean getPreferredEncrypted() {
            return this.preferredEncrypted;
        }
    }

    RtpCapabilities(List<CodecCapability> codecs, List<HeaderExtensionCapability> headerExtensions) {
        this.headerExtensions = headerExtensions;
        this.codecs = codecs;
    }

    public List<HeaderExtensionCapability> getHeaderExtensions() {
        return this.headerExtensions;
    }

    List<CodecCapability> getCodecs() {
        return this.codecs;
    }
}
