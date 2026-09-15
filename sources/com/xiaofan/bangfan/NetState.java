package com.xiaofan.bangfan;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
/* compiled from: NetState.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0018B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J>\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004J\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\bJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0013J\u0010\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002¨\u0006\u0019"}, d2 = {"Lcom/xiaofan/bangfan/NetState;", "", "()V", "composeUpdateMessage", "", "version", "cur", "sizeBytes", "", "kind", "Lcom/xiaofan/bangfan/NetState$Kind;", "notes", "tag", "asset", "formatSize", "bytes", "isOnline", "", "ctx", "Landroid/content/Context;", "label", "transportFallback", "cm", "Landroid/net/ConnectivityManager;", "Kind", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class NetState {
    public static final NetState INSTANCE = new NetState();

    /* compiled from: NetState.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/xiaofan/bangfan/NetState$Kind;", "", "(Ljava/lang/String;I)V", "WIFI", "CELLULAR", "OTHER", "OFFLINE", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public enum Kind {
        WIFI,
        CELLULAR,
        OTHER,
        OFFLINE;
        
        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<Kind> getEntries() {
            return $ENTRIES;
        }
    }

    /* compiled from: NetState.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Kind.values().length];
            try {
                iArr[Kind.WIFI.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Kind.CELLULAR.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Kind.OTHER.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[Kind.OFFLINE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private NetState() {
    }

    public final Kind kind(Context ctx) {
        Network network;
        NetworkCapabilities cap;
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Object systemService = ctx.getSystemService("connectivity");
        ConnectivityManager cm = systemService instanceof ConnectivityManager ? (ConnectivityManager) systemService : null;
        if (cm != null && (network = cm.getActiveNetwork()) != null && (cap = cm.getNetworkCapabilities(network)) != null) {
            return cap.hasTransport(1) ? Kind.WIFI : cap.hasTransport(0) ? Kind.CELLULAR : cap.hasTransport(3) ? Kind.OTHER : cap.hasTransport(4) ? transportFallback(cm) : Kind.OTHER;
        }
        return Kind.OFFLINE;
    }

    private final Kind transportFallback(ConnectivityManager cm) {
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            return Kind.OTHER;
        }
        switch (info.getType()) {
            case 0:
                return Kind.CELLULAR;
            case 1:
                return Kind.WIFI;
            default:
                return Kind.OTHER;
        }
    }

    public final boolean isOnline(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        return kind(ctx) != Kind.OFFLINE;
    }

    public final String label(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        switch (WhenMappings.$EnumSwitchMapping$0[kind(ctx).ordinal()]) {
            case 1:
                return "WiFi";
            case 2:
                return "移动数据";
            case 3:
                return "其它网络";
            case 4:
                return "无网络";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final String formatSize(long bytes) {
        if (bytes <= 0) {
            return "";
        }
        double mb = (bytes / 1024.0d) / 1024.0d;
        if (mb >= 1.0d) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%.1f", Arrays.copyOf(new Object[]{Double.valueOf(mb)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            return format + "MB";
        }
        double kb = bytes / 1024.0d;
        if (kb >= 1.0d) {
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String format2 = String.format("%.0f", Arrays.copyOf(new Object[]{Double.valueOf(kb)}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
            return format2 + "KB";
        }
        return bytes + "B";
    }

    public final String composeUpdateMessage(String version, String cur, long sizeBytes, Kind kind, String notes, String tag, String asset) {
        String netLine;
        String str;
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(cur, "cur");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(notes, "notes");
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(asset, "asset");
        String sizeText = formatSize(sizeBytes);
        switch (WhenMappings.$EnumSwitchMapping$0[kind.ordinal()]) {
            case 1:
                netLine = "当前网络：WiFi（不消耗移动流量）";
                break;
            case 2:
                netLine = "当前网络：移动数据";
                break;
            case 3:
                netLine = "当前网络：其它已连接网络";
                break;
            case 4:
                netLine = "当前网络：无网络";
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        if (!StringsKt.isBlank(sizeText)) {
            str = "本次更新需下载约 " + sizeText + (kind == Kind.CELLULAR ? "，将消耗等额移动流量，建议在 WiFi 下更新" : "");
        }
        String flowLine = str;
        StringBuilder sb = new StringBuilder();
        sb.append("发现新版本 V").append(version).append("（当前 V").append(cur).append("），是否更新？\n\n");
        sb.append(netLine).append('\n');
        if (!StringsKt.isBlank(flowLine)) {
            sb.append(flowLine).append('\n');
        }
        sb.append('\n');
        String str2 = notes;
        if (StringsKt.isBlank(str2)) {
            str2 = "建议更新到最新版本以获得修复与优化。";
        }
        sb.append(str2);
        sb.append("\n\n来源：GitHub Release ").append(tag).append("\n资产：").append(asset);
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }
}
