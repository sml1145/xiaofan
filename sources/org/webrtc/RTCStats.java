package org.webrtc;

import java.util.Map;
import kotlin.text.Typography;
/* loaded from: classes5.dex */
public class RTCStats {
    private final String id;
    private final Map<String, Object> members;
    private final long timestampUs;
    private final String type;

    public RTCStats(long timestampUs, String type, String id, Map<String, Object> members) {
        this.timestampUs = timestampUs;
        this.type = type;
        this.id = id;
        this.members = members;
    }

    public double getTimestampUs() {
        return this.timestampUs;
    }

    public String getType() {
        return this.type;
    }

    public String getId() {
        return this.id;
    }

    public Map<String, Object> getMembers() {
        return this.members;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{ timestampUs: ").append(this.timestampUs).append(", type: ").append(this.type).append(", id: ").append(this.id);
        for (Map.Entry<String, Object> entry : this.members.entrySet()) {
            builder.append(", ").append(entry.getKey()).append(": ");
            appendValue(builder, entry.getValue());
        }
        builder.append(" }");
        return builder.toString();
    }

    private static void appendValue(StringBuilder builder, Object value) {
        if (!(value instanceof Object[])) {
            if (value instanceof String) {
                builder.append(Typography.quote).append(value).append(Typography.quote);
                return;
            } else {
                builder.append(value);
                return;
            }
        }
        Object[] arrayValue = (Object[]) value;
        builder.append('[');
        for (int i = 0; i < arrayValue.length; i++) {
            if (i != 0) {
                builder.append(", ");
            }
            appendValue(builder, arrayValue[i]);
        }
        builder.append(']');
    }

    static RTCStats create(long timestampUs, String type, String id, Map members) {
        return new RTCStats(timestampUs, type, id, members);
    }
}
