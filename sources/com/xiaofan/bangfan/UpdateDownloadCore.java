package com.xiaofan.bangfan;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.UpdateDownloadCore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
/* compiled from: UpdateDownloadCore.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002*+B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0006J\u0016\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0006J\u0018\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u00042\b\b\u0002\u0010\u001f\u001a\u00020\u0004J \u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u00042\b\b\u0002\u0010\u001f\u001a\u00020\u0004J\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0004J\u0010\u0010%\u001a\u00020\u00042\b\u0010&\u001a\u0004\u0018\u00010#J\u001a\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\u00122\f\u0010)\u001a\b\u0012\u0004\u0012\u00020(0\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006,"}, d2 = {"Lcom/xiaofan/bangfan/UpdateDownloadCore;", "", "()V", "CHUNK_SIZE", "", "CHUNK_TRIES", "", "DL_CONNECT_MS", "DL_READ_MS", "MIN_PROBE_SPEED", "PROBE_BUDGET_MS", "PROBE_BYTES", "PROBE_CONNECT_MS", "PROBE_READ_MS", "STALL_MIN_BYTES", "STALL_WINDOW_MS", "WORKERS", "nodes", "", "Lcom/xiaofan/bangfan/UpdateDownloadCore$Node;", "getNodes", "()Ljava/util/List;", "bitGet", "", "bits", "", "i", "bitSet", "", "chunkCount", "total", "chunk", "chunkLen", "index", "formatSpeed", "", "bps", "parseContentRangeTotal", "v", "rankProbes", "Lcom/xiaofan/bangfan/UpdateDownloadCore$NodeProbe;", "probes", "Node", "NodeProbe", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class UpdateDownloadCore {
    public static final long CHUNK_SIZE = 1048576;
    public static final int CHUNK_TRIES = 12;
    public static final int DL_CONNECT_MS = 12000;
    public static final int DL_READ_MS = 15000;
    public static final long MIN_PROBE_SPEED = 15000;
    public static final long PROBE_BUDGET_MS = 14000;
    public static final long PROBE_BYTES = 262143;
    public static final int PROBE_CONNECT_MS = 6000;
    public static final int PROBE_READ_MS = 8000;
    public static final long STALL_MIN_BYTES = 200000;
    public static final long STALL_WINDOW_MS = 10000;
    public static final int WORKERS = 3;
    public static final UpdateDownloadCore INSTANCE = new UpdateDownloadCore();
    private static final List<Node> nodes = CollectionsKt.listOf((Object[]) new Node[]{new Node("https://gh.ddlc.top/", "加速节点1"), new Node("https://gh-proxy.com/", "加速节点2"), new Node("https://ghfast.top/", "加速节点3"), new Node("https://ghproxy.net/", "加速节点4"), new Node("https://mirror.ghproxy.com/", "加速节点5"), new Node("https://gh.llkk.cc/", "加速节点6"), new Node("https://github.moeyy.xyz/", "加速节点7"), new Node("https://ghproxy.homeboyc.cn/", "加速节点8"), new Node("https://gh.h233.eu.org/", "加速节点9"), new Node("", "官方直连")});

    private UpdateDownloadCore() {
    }

    /* compiled from: UpdateDownloadCore.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\u000e\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/xiaofan/bangfan/UpdateDownloadCore$Node;", "", "prefix", "", "label", "(Ljava/lang/String;Ljava/lang/String;)V", "isDirect", "", "()Z", "getLabel", "()Ljava/lang/String;", "getPrefix", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "wrap", "githubUrl", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Node {
        private final String label;
        private final String prefix;

        public static /* synthetic */ Node copy$default(Node node, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = node.prefix;
            }
            if ((i & 2) != 0) {
                str2 = node.label;
            }
            return node.copy(str, str2);
        }

        public final String component1() {
            return this.prefix;
        }

        public final String component2() {
            return this.label;
        }

        public final Node copy(String prefix, String label) {
            Intrinsics.checkNotNullParameter(prefix, "prefix");
            Intrinsics.checkNotNullParameter(label, "label");
            return new Node(prefix, label);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Node) {
                Node node = (Node) obj;
                return Intrinsics.areEqual(this.prefix, node.prefix) && Intrinsics.areEqual(this.label, node.label);
            }
            return false;
        }

        public int hashCode() {
            return (this.prefix.hashCode() * 31) + this.label.hashCode();
        }

        public String toString() {
            String str = this.prefix;
            return "Node(prefix=" + str + ", label=" + this.label + ")";
        }

        public Node(String prefix, String label) {
            Intrinsics.checkNotNullParameter(prefix, "prefix");
            Intrinsics.checkNotNullParameter(label, "label");
            this.prefix = prefix;
            this.label = label;
        }

        public final String getLabel() {
            return this.label;
        }

        public final String getPrefix() {
            return this.prefix;
        }

        public final String wrap(String githubUrl) {
            Intrinsics.checkNotNullParameter(githubUrl, "githubUrl");
            if (this.prefix.length() == 0) {
                return githubUrl;
            }
            return this.prefix + githubUrl;
        }

        public final boolean isDirect() {
            return this.prefix.length() == 0;
        }
    }

    /* compiled from: UpdateDownloadCore.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u00072\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/xiaofan/bangfan/UpdateDownloadCore$NodeProbe;", "", "node", "Lcom/xiaofan/bangfan/UpdateDownloadCore$Node;", "bps", "", "supportsRange", "", "total", "ok", "(Lcom/xiaofan/bangfan/UpdateDownloadCore$Node;JZJZ)V", "getBps", "()J", "getNode", "()Lcom/xiaofan/bangfan/UpdateDownloadCore$Node;", "getOk", "()Z", "getSupportsRange", "getTotal", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class NodeProbe {
        private final long bps;
        private final Node node;
        private final boolean ok;
        private final boolean supportsRange;
        private final long total;

        public static /* synthetic */ NodeProbe copy$default(NodeProbe nodeProbe, Node node, long j, boolean z, long j2, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                node = nodeProbe.node;
            }
            if ((i & 2) != 0) {
                j = nodeProbe.bps;
            }
            long j3 = j;
            if ((i & 4) != 0) {
                z = nodeProbe.supportsRange;
            }
            boolean z3 = z;
            if ((i & 8) != 0) {
                j2 = nodeProbe.total;
            }
            long j4 = j2;
            if ((i & 16) != 0) {
                z2 = nodeProbe.ok;
            }
            return nodeProbe.copy(node, j3, z3, j4, z2);
        }

        public final Node component1() {
            return this.node;
        }

        public final long component2() {
            return this.bps;
        }

        public final boolean component3() {
            return this.supportsRange;
        }

        public final long component4() {
            return this.total;
        }

        public final boolean component5() {
            return this.ok;
        }

        public final NodeProbe copy(Node node, long j, boolean z, long j2, boolean z2) {
            Intrinsics.checkNotNullParameter(node, "node");
            return new NodeProbe(node, j, z, j2, z2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof NodeProbe) {
                NodeProbe nodeProbe = (NodeProbe) obj;
                return Intrinsics.areEqual(this.node, nodeProbe.node) && this.bps == nodeProbe.bps && this.supportsRange == nodeProbe.supportsRange && this.total == nodeProbe.total && this.ok == nodeProbe.ok;
            }
            return false;
        }

        public int hashCode() {
            return (((((((this.node.hashCode() * 31) + Long.hashCode(this.bps)) * 31) + Boolean.hashCode(this.supportsRange)) * 31) + Long.hashCode(this.total)) * 31) + Boolean.hashCode(this.ok);
        }

        public String toString() {
            Node node = this.node;
            long j = this.bps;
            boolean z = this.supportsRange;
            long j2 = this.total;
            return "NodeProbe(node=" + node + ", bps=" + j + ", supportsRange=" + z + ", total=" + j2 + ", ok=" + this.ok + ")";
        }

        public NodeProbe(Node node, long bps, boolean supportsRange, long total, boolean ok) {
            Intrinsics.checkNotNullParameter(node, "node");
            this.node = node;
            this.bps = bps;
            this.supportsRange = supportsRange;
            this.total = total;
            this.ok = ok;
        }

        public final long getBps() {
            return this.bps;
        }

        public final Node getNode() {
            return this.node;
        }

        public final boolean getOk() {
            return this.ok;
        }

        public final boolean getSupportsRange() {
            return this.supportsRange;
        }

        public final long getTotal() {
            return this.total;
        }
    }

    public final List<Node> getNodes() {
        return nodes;
    }

    public static /* synthetic */ int chunkCount$default(UpdateDownloadCore updateDownloadCore, long j, long j2, int i, Object obj) {
        if ((i & 2) != 0) {
            j2 = CHUNK_SIZE;
        }
        return updateDownloadCore.chunkCount(j, j2);
    }

    public final int chunkCount(long total, long chunk) {
        return (int) (((total + chunk) - 1) / chunk);
    }

    public static /* synthetic */ long chunkLen$default(UpdateDownloadCore updateDownloadCore, int i, long j, long j2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            j2 = CHUNK_SIZE;
        }
        return updateDownloadCore.chunkLen(i, j, j2);
    }

    public final long chunkLen(int index, long total, long chunk) {
        long start = index * chunk;
        return RangesKt.coerceIn(total - start, 0L, chunk);
    }

    public final boolean bitGet(byte[] bits, int i) {
        Intrinsics.checkNotNullParameter(bits, "bits");
        return (bits[i / 8] & (1 << (i % 8))) != 0;
    }

    public final void bitSet(byte[] bits, int i) {
        Intrinsics.checkNotNullParameter(bits, "bits");
        bits[i / 8] = (byte) (bits[i / 8] | (1 << (i % 8)));
    }

    public final List<NodeProbe> rankProbes(List<NodeProbe> probes) {
        Intrinsics.checkNotNullParameter(probes, "probes");
        List<NodeProbe> $this$filter$iv = probes;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            NodeProbe it = (NodeProbe) element$iv$iv;
            if (it.getOk() && it.getBps() >= MIN_PROBE_SPEED) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        final Comparator comparator = new Comparator() { // from class: com.xiaofan.bangfan.UpdateDownloadCore$rankProbes$$inlined$compareByDescending$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                UpdateDownloadCore.NodeProbe it2 = (UpdateDownloadCore.NodeProbe) t2;
                Integer num = it2.getSupportsRange() ? (Comparable) 1 : (Comparable) 0;
                UpdateDownloadCore.NodeProbe it3 = (UpdateDownloadCore.NodeProbe) t;
                return ComparisonsKt.compareValues(num, it3.getSupportsRange() ? (Comparable) 1 : (Comparable) 0);
            }
        };
        return CollectionsKt.sortedWith((List) destination$iv$iv, new Comparator() { // from class: com.xiaofan.bangfan.UpdateDownloadCore$rankProbes$$inlined$thenByDescending$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int previousCompare = comparator.compare(t, t2);
                if (previousCompare != 0) {
                    return previousCompare;
                }
                UpdateDownloadCore.NodeProbe it2 = (UpdateDownloadCore.NodeProbe) t2;
                UpdateDownloadCore.NodeProbe it3 = (UpdateDownloadCore.NodeProbe) t;
                return ComparisonsKt.compareValues(Long.valueOf(it2.getBps()), Long.valueOf(it3.getBps()));
            }
        });
    }

    public final long parseContentRangeTotal(String v) {
        int slash;
        String str = v;
        if (!(str == null || StringsKt.isBlank(str)) && (slash = StringsKt.lastIndexOf$default((CharSequence) v, '/', 0, false, 6, (Object) null)) >= 0) {
            String substring = v.substring(slash + 1);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            Long longOrNull = StringsKt.toLongOrNull(StringsKt.trim((CharSequence) substring).toString());
            if (longOrNull != null) {
                return longOrNull.longValue();
            }
            return -1L;
        }
        return -1L;
    }

    public final String formatSpeed(long bps) {
        if (bps < 1000000) {
            if (bps >= 1000) {
                return (bps / 1000) + "KB/s";
            }
            return bps + "B/s";
        }
        String format = String.format("%.1fMB/s", Arrays.copyOf(new Object[]{Double.valueOf(bps / 1000000.0d)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }
}
