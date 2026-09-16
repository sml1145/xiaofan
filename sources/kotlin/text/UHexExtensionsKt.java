package kotlin.text;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: UHexExtensions.kt */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\f\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b¢\u0006\u0002\u0010\u0005\u001a\u001c\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b¢\u0006\u0002\u0010\b\u001a\u001c\u0010\t\u001a\u00020\n*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b¢\u0006\u0002\u0010\u000b\u001a\u001c\u0010\f\u001a\u00020\r*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b¢\u0006\u0002\u0010\u000e\u001a\u001c\u0010\u000f\u001a\u00020\u0010*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b¢\u0006\u0002\u0010\u0011\u001a\u001e\u0010\u0012\u001a\u00020\u0002*\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b¢\u0006\u0004\b\u0013\u0010\u0014\u001a2\u0010\u0012\u001a\u00020\u0002*\u00020\u00072\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u001e\u0010\u0012\u001a\u00020\u0002*\u00020\u00072\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u001e\u0010\u0012\u001a\u00020\u0002*\u00020\n2\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u001e\u0010\u0012\u001a\u00020\u0002*\u00020\r2\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u001e\u0010\u0012\u001a\u00020\u0002*\u00020\u00102\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b¢\u0006\u0004\b \u0010!¨\u0006\""}, d2 = {"hexToUByte", "Lkotlin/UByte;", "", "format", "Lkotlin/text/HexFormat;", "(Ljava/lang/String;Lkotlin/text/HexFormat;)B", "hexToUByteArray", "Lkotlin/UByteArray;", "(Ljava/lang/String;Lkotlin/text/HexFormat;)[B", "hexToUInt", "Lkotlin/UInt;", "(Ljava/lang/String;Lkotlin/text/HexFormat;)I", "hexToULong", "Lkotlin/ULong;", "(Ljava/lang/String;Lkotlin/text/HexFormat;)J", "hexToUShort", "Lkotlin/UShort;", "(Ljava/lang/String;Lkotlin/text/HexFormat;)S", "toHexString", "toHexString-ZQbaR00", "(BLkotlin/text/HexFormat;)Ljava/lang/String;", "startIndex", "", "endIndex", "toHexString-lZCiFrA", "([BIILkotlin/text/HexFormat;)Ljava/lang/String;", "toHexString-zHuV2wU", "([BLkotlin/text/HexFormat;)Ljava/lang/String;", "toHexString-8M7LxHw", "(ILkotlin/text/HexFormat;)Ljava/lang/String;", "toHexString-8UJCm-I", "(JLkotlin/text/HexFormat;)Ljava/lang/String;", "toHexString-r3ox_E0", "(SLkotlin/text/HexFormat;)Ljava/lang/String;", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class UHexExtensionsKt {
    /* renamed from: toHexString-zHuV2wU  reason: not valid java name */
    private static final String m1580toHexStringzHuV2wU(byte[] toHexString, HexFormat format) {
        Intrinsics.checkNotNullParameter(toHexString, "$this$toHexString");
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString(toHexString, format);
    }

    /* renamed from: toHexString-zHuV2wU$default  reason: not valid java name */
    static /* synthetic */ String m1581toHexStringzHuV2wU$default(byte[] toHexString, HexFormat format, int i, Object obj) {
        if ((i & 1) != 0) {
            format = HexFormat.Companion.getDefault();
        }
        Intrinsics.checkNotNullParameter(toHexString, "$this$toHexString");
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString(toHexString, format);
    }

    /* renamed from: toHexString-lZCiFrA$default  reason: not valid java name */
    static /* synthetic */ String m1577toHexStringlZCiFrA$default(byte[] toHexString, int startIndex, int endIndex, HexFormat format, int i, Object obj) {
        if ((i & 1) != 0) {
            startIndex = 0;
        }
        if ((i & 2) != 0) {
            endIndex = UByteArray.m347getSizeimpl(toHexString);
        }
        if ((i & 4) != 0) {
            format = HexFormat.Companion.getDefault();
        }
        Intrinsics.checkNotNullParameter(toHexString, "$this$toHexString");
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString(toHexString, startIndex, endIndex, format);
    }

    /* renamed from: toHexString-lZCiFrA  reason: not valid java name */
    private static final String m1576toHexStringlZCiFrA(byte[] toHexString, int startIndex, int endIndex, HexFormat format) {
        Intrinsics.checkNotNullParameter(toHexString, "$this$toHexString");
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString(toHexString, startIndex, endIndex, format);
    }

    static /* synthetic */ byte[] hexToUByteArray$default(String $this$hexToUByteArray_u24default, HexFormat format, int i, Object obj) {
        if ((i & 1) != 0) {
            format = HexFormat.Companion.getDefault();
        }
        Intrinsics.checkNotNullParameter($this$hexToUByteArray_u24default, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return UByteArray.m341constructorimpl(HexExtensionsKt.hexToByteArray($this$hexToUByteArray_u24default, format));
    }

    private static final byte[] hexToUByteArray(String $this$hexToUByteArray, HexFormat format) {
        Intrinsics.checkNotNullParameter($this$hexToUByteArray, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return UByteArray.m341constructorimpl(HexExtensionsKt.hexToByteArray($this$hexToUByteArray, format));
    }

    /* renamed from: toHexString-ZQbaR00  reason: not valid java name */
    private static final String m1574toHexStringZQbaR00(byte $this$toHexString_u2dZQbaR00, HexFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString($this$toHexString_u2dZQbaR00, format);
    }

    /* renamed from: toHexString-ZQbaR00$default  reason: not valid java name */
    static /* synthetic */ String m1575toHexStringZQbaR00$default(byte $this$toHexString_u2dZQbaR00_u24default, HexFormat format, int i, Object obj) {
        if ((i & 1) != 0) {
            format = HexFormat.Companion.getDefault();
        }
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString($this$toHexString_u2dZQbaR00_u24default, format);
    }

    private static final byte hexToUByte(String $this$hexToUByte, HexFormat format) {
        Intrinsics.checkNotNullParameter($this$hexToUByte, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return UByte.m288constructorimpl(HexExtensionsKt.hexToByte($this$hexToUByte, format));
    }

    static /* synthetic */ byte hexToUByte$default(String $this$hexToUByte_u24default, HexFormat format, int i, Object obj) {
        if ((i & 1) != 0) {
            format = HexFormat.Companion.getDefault();
        }
        Intrinsics.checkNotNullParameter($this$hexToUByte_u24default, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return UByte.m288constructorimpl(HexExtensionsKt.hexToByte($this$hexToUByte_u24default, format));
    }

    /* renamed from: toHexString-r3ox_E0  reason: not valid java name */
    private static final String m1578toHexStringr3ox_E0(short $this$toHexString_u2dr3ox_E0, HexFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString($this$toHexString_u2dr3ox_E0, format);
    }

    /* renamed from: toHexString-r3ox_E0$default  reason: not valid java name */
    static /* synthetic */ String m1579toHexStringr3ox_E0$default(short $this$toHexString_u2dr3ox_E0_u24default, HexFormat format, int i, Object obj) {
        if ((i & 1) != 0) {
            format = HexFormat.Companion.getDefault();
        }
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString($this$toHexString_u2dr3ox_E0_u24default, format);
    }

    private static final short hexToUShort(String $this$hexToUShort, HexFormat format) {
        Intrinsics.checkNotNullParameter($this$hexToUShort, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return UShort.m551constructorimpl(HexExtensionsKt.hexToShort($this$hexToUShort, format));
    }

    static /* synthetic */ short hexToUShort$default(String $this$hexToUShort_u24default, HexFormat format, int i, Object obj) {
        if ((i & 1) != 0) {
            format = HexFormat.Companion.getDefault();
        }
        Intrinsics.checkNotNullParameter($this$hexToUShort_u24default, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return UShort.m551constructorimpl(HexExtensionsKt.hexToShort($this$hexToUShort_u24default, format));
    }

    /* renamed from: toHexString-8M7LxHw  reason: not valid java name */
    private static final String m1570toHexString8M7LxHw(int $this$toHexString_u2d8M7LxHw, HexFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString($this$toHexString_u2d8M7LxHw, format);
    }

    /* renamed from: toHexString-8M7LxHw$default  reason: not valid java name */
    static /* synthetic */ String m1571toHexString8M7LxHw$default(int $this$toHexString_u2d8M7LxHw_u24default, HexFormat format, int i, Object obj) {
        if ((i & 1) != 0) {
            format = HexFormat.Companion.getDefault();
        }
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString($this$toHexString_u2d8M7LxHw_u24default, format);
    }

    private static final int hexToUInt(String $this$hexToUInt, HexFormat format) {
        Intrinsics.checkNotNullParameter($this$hexToUInt, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return UInt.m365constructorimpl(HexExtensionsKt.hexToInt($this$hexToUInt, format));
    }

    static /* synthetic */ int hexToUInt$default(String $this$hexToUInt_u24default, HexFormat format, int i, Object obj) {
        if ((i & 1) != 0) {
            format = HexFormat.Companion.getDefault();
        }
        Intrinsics.checkNotNullParameter($this$hexToUInt_u24default, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return UInt.m365constructorimpl(HexExtensionsKt.hexToInt($this$hexToUInt_u24default, format));
    }

    /* renamed from: toHexString-8UJCm-I  reason: not valid java name */
    private static final String m1572toHexString8UJCmI(long $this$toHexString_u2d8UJCm_u2dI, HexFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString($this$toHexString_u2d8UJCm_u2dI, format);
    }

    /* renamed from: toHexString-8UJCm-I$default  reason: not valid java name */
    static /* synthetic */ String m1573toHexString8UJCmI$default(long $this$toHexString_u2d8UJCm_u2dI_u24default, HexFormat format, int i, Object obj) {
        if ((i & 1) != 0) {
            format = HexFormat.Companion.getDefault();
        }
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString($this$toHexString_u2d8UJCm_u2dI_u24default, format);
    }

    private static final long hexToULong(String $this$hexToULong, HexFormat format) {
        Intrinsics.checkNotNullParameter($this$hexToULong, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return ULong.m444constructorimpl(HexExtensionsKt.hexToLong($this$hexToULong, format));
    }

    static /* synthetic */ long hexToULong$default(String $this$hexToULong_u24default, HexFormat format, int i, Object obj) {
        if ((i & 1) != 0) {
            format = HexFormat.Companion.getDefault();
        }
        Intrinsics.checkNotNullParameter($this$hexToULong_u24default, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return ULong.m444constructorimpl(HexExtensionsKt.hexToLong($this$hexToULong_u24default, format));
    }
}
