package kotlin.text;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.ULong;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.HexFormat;
/* compiled from: HexExtensions.kt */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\n\n\u0002\b\u0004\u001a \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002\u001a@\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\tH\u0000\u001a@\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\tH\u0000\u001a \u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0002\u001a,\u0010\u0016\u001a\u00020\t*\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u0003H\u0002\u001a,\u0010\u001b\u001a\u00020\u001c*\u00020\u00032\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020 H\u0002\u001a\u001c\u0010!\u001a\u00020\t*\u00020\u00032\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\tH\u0002\u001a\u0014\u0010\"\u001a\u00020\t*\u00020\u00032\u0006\u0010\u0018\u001a\u00020\tH\u0002\u001a*\u0010#\u001a\u00020$*\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\t2\b\b\u0002\u0010\u0019\u001a\u00020\t2\b\b\u0002\u0010%\u001a\u00020&H\u0003\u001a\u0016\u0010#\u001a\u00020$*\u00020\u00032\b\b\u0002\u0010%\u001a\u00020&H\u0007\u001a*\u0010'\u001a\u00020(*\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\t2\b\b\u0002\u0010\u0019\u001a\u00020\t2\b\b\u0002\u0010%\u001a\u00020&H\u0003\u001a\u0016\u0010'\u001a\u00020(*\u00020\u00032\b\b\u0002\u0010%\u001a\u00020&H\u0007\u001a*\u0010)\u001a\u00020\t*\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\t2\b\b\u0002\u0010\u0019\u001a\u00020\t2\b\b\u0002\u0010%\u001a\u00020&H\u0003\u001a\u0016\u0010)\u001a\u00020\t*\u00020\u00032\b\b\u0002\u0010%\u001a\u00020&H\u0007\u001a*\u0010*\u001a\u00020\u0006*\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\t2\b\b\u0002\u0010\u0019\u001a\u00020\t2\b\b\u0002\u0010%\u001a\u00020&H\u0003\u001a\u0016\u0010*\u001a\u00020\u0006*\u00020\u00032\b\b\u0002\u0010%\u001a\u00020&H\u0007\u001a0\u0010+\u001a\u00020\u0006*\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\t2\b\b\u0002\u0010\u0019\u001a\u00020\t2\u0006\u0010%\u001a\u00020&2\u0006\u0010\u001e\u001a\u00020\tH\u0003\u001a*\u0010,\u001a\u00020-*\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\t2\b\b\u0002\u0010\u0019\u001a\u00020\t2\b\b\u0002\u0010%\u001a\u00020&H\u0003\u001a\u0016\u0010,\u001a\u00020-*\u00020\u00032\b\b\u0002\u0010%\u001a\u00020&H\u0007\u001a\u0016\u0010.\u001a\u00020\u0003*\u00020$2\b\b\u0002\u0010%\u001a\u00020&H\u0007\u001a*\u0010.\u001a\u00020\u0003*\u00020(2\b\b\u0002\u0010\u001d\u001a\u00020\t2\b\b\u0002\u0010\u0019\u001a\u00020\t2\b\b\u0002\u0010%\u001a\u00020&H\u0007\u001a\u0016\u0010.\u001a\u00020\u0003*\u00020(2\b\b\u0002\u0010%\u001a\u00020&H\u0007\u001a\u0016\u0010.\u001a\u00020\u0003*\u00020\t2\b\b\u0002\u0010%\u001a\u00020&H\u0007\u001a\u0016\u0010.\u001a\u00020\u0003*\u00020\u00062\b\b\u0002\u0010%\u001a\u00020&H\u0007\u001a\u0016\u0010.\u001a\u00020\u0003*\u00020-2\b\b\u0002\u0010%\u001a\u00020&H\u0007\u001a\u001c\u0010/\u001a\u00020\u0003*\u00020\u00062\u0006\u0010%\u001a\u00020&2\u0006\u00100\u001a\u00020\tH\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"HEX_DIGITS_TO_DECIMAL", "", "LOWER_CASE_HEX_DIGITS", "", "UPPER_CASE_HEX_DIGITS", "charsPerSet", "", "charsPerElement", "elementsPerSet", "", "elementSeparatorLength", "formattedStringLength", "totalBytes", "bytesPerLine", "bytesPerGroup", "groupSeparatorLength", "byteSeparatorLength", "bytePrefixLength", "byteSuffixLength", "parsedByteArrayMaxSize", "stringLength", "wholeElementsPerSet", "checkContainsAt", "part", "index", "endIndex", "partName", "checkHexLength", "", "startIndex", "maxDigits", "requireMaxLength", "", "checkNewLineAt", "decimalFromHexDigitAt", "hexToByte", "", "format", "Lkotlin/text/HexFormat;", "hexToByteArray", "", "hexToInt", "hexToLong", "hexToLongImpl", "hexToShort", "", "toHexString", "toHexStringImpl", "bits", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class HexExtensionsKt {
    private static final int[] HEX_DIGITS_TO_DECIMAL;
    private static final String LOWER_CASE_HEX_DIGITS = "0123456789abcdef";
    private static final String UPPER_CASE_HEX_DIGITS = "0123456789ABCDEF";

    static {
        int[] $this$HEX_DIGITS_TO_DECIMAL_u24lambda_u242 = new int[128];
        int i = 0;
        for (int i2 = 0; i2 < 128; i2++) {
            $this$HEX_DIGITS_TO_DECIMAL_u24lambda_u242[i2] = -1;
        }
        int index$iv = 0;
        int i3 = 0;
        while (i3 < $this$forEachIndexed$iv.length()) {
            char item$iv = $this$forEachIndexed$iv.charAt(i3);
            $this$HEX_DIGITS_TO_DECIMAL_u24lambda_u242[item$iv] = index$iv;
            i3++;
            index$iv++;
        }
        int index$iv2 = 0;
        while (i < $this$forEachIndexed$iv.length()) {
            char item$iv2 = $this$forEachIndexed$iv.charAt(i);
            $this$HEX_DIGITS_TO_DECIMAL_u24lambda_u242[item$iv2] = index$iv2;
            i++;
            index$iv2++;
        }
        HEX_DIGITS_TO_DECIMAL = $this$HEX_DIGITS_TO_DECIMAL_u24lambda_u242;
    }

    public static final String toHexString(byte[] $this$toHexString, HexFormat format) {
        Intrinsics.checkNotNullParameter($this$toHexString, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return toHexString($this$toHexString, 0, $this$toHexString.length, format);
    }

    public static /* synthetic */ String toHexString$default(byte[] bArr, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return toHexString(bArr, hexFormat);
    }

    public static /* synthetic */ String toHexString$default(byte[] bArr, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = bArr.length;
        }
        if ((i3 & 4) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return toHexString(bArr, i, i2, hexFormat);
    }

    public static final String toHexString(byte[] $this$toHexString, int startIndex, int endIndex, HexFormat format) {
        byte[] bArr = $this$toHexString;
        int i = endIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, i, bArr.length);
        if (startIndex == i) {
            return "";
        }
        String digits = format.getUpperCase() ? UPPER_CASE_HEX_DIGITS : LOWER_CASE_HEX_DIGITS;
        HexFormat.BytesHexFormat bytesFormat = format.getBytes();
        int bytesPerLine = bytesFormat.getBytesPerLine();
        int bytesPerGroup = bytesFormat.getBytesPerGroup();
        String bytePrefix = bytesFormat.getBytePrefix();
        String byteSuffix = bytesFormat.getByteSuffix();
        String byteSeparator = bytesFormat.getByteSeparator();
        String groupSeparator = bytesFormat.getGroupSeparator();
        int formatLength = formattedStringLength(i - startIndex, bytesPerLine, bytesPerGroup, groupSeparator.length(), byteSeparator.length(), bytePrefix.length(), byteSuffix.length());
        int indexInLine = 0;
        int indexInGroup = 0;
        StringBuilder $this$toHexString_u24lambda_u243 = new StringBuilder(formatLength);
        int i2 = startIndex;
        while (true) {
            if (i2 >= i) {
                break;
            }
            int i3 = bArr[i2] & UByte.MAX_VALUE;
            if (indexInLine == bytesPerLine) {
                $this$toHexString_u24lambda_u243.append('\n');
                indexInLine = 0;
                indexInGroup = 0;
            } else if (indexInGroup == bytesPerGroup) {
                $this$toHexString_u24lambda_u243.append(groupSeparator);
                indexInGroup = 0;
            }
            if (indexInGroup != 0) {
                $this$toHexString_u24lambda_u243.append(byteSeparator);
            }
            $this$toHexString_u24lambda_u243.append(bytePrefix);
            $this$toHexString_u24lambda_u243.append(digits.charAt(i3 >> 4));
            $this$toHexString_u24lambda_u243.append(digits.charAt(i3 & 15));
            $this$toHexString_u24lambda_u243.append(byteSuffix);
            indexInGroup++;
            indexInLine++;
            i2++;
            bArr = $this$toHexString;
            i = endIndex;
        }
        if (formatLength == $this$toHexString_u24lambda_u243.length()) {
            String sb = $this$toHexString_u24lambda_u243.toString();
            Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
            return sb;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public static final int formattedStringLength(int totalBytes, int bytesPerLine, int bytesPerGroup, int groupSeparatorLength, int byteSeparatorLength, int bytePrefixLength, int byteSuffixLength) {
        if (totalBytes > 0) {
            int lineSeparators = (totalBytes - 1) / bytesPerLine;
            int groupSeparatorsPerLine = (bytesPerLine - 1) / bytesPerGroup;
            int it = totalBytes % bytesPerLine;
            if (it == 0) {
                it = bytesPerLine;
            }
            int groupSeparatorsInLastLine = (it - 1) / bytesPerGroup;
            int groupSeparators = (lineSeparators * groupSeparatorsPerLine) + groupSeparatorsInLastLine;
            int byteSeparators = ((totalBytes - 1) - lineSeparators) - groupSeparators;
            long totalLength = lineSeparators + (groupSeparators * groupSeparatorLength) + (byteSeparators * byteSeparatorLength) + (totalBytes * (bytePrefixLength + 2 + byteSuffixLength));
            if (RangesKt.intRangeContains((ClosedRange<Integer>) new IntRange(0, Integer.MAX_VALUE), totalLength)) {
                return (int) totalLength;
            }
            throw new IllegalArgumentException("The resulting string length is too big: " + ((Object) ULong.m495toStringimpl(ULong.m449constructorimpl(totalLength))));
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    public static final byte[] hexToByteArray(String $this$hexToByteArray, HexFormat format) {
        Intrinsics.checkNotNullParameter($this$hexToByteArray, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return hexToByteArray($this$hexToByteArray, 0, $this$hexToByteArray.length(), format);
    }

    public static /* synthetic */ byte[] hexToByteArray$default(String str, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToByteArray(str, hexFormat);
    }

    static /* synthetic */ byte[] hexToByteArray$default(String str, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToByteArray(str, i, i2, hexFormat);
    }

    private static final byte[] hexToByteArray(String $this$hexToByteArray, int startIndex, int endIndex, HexFormat format) {
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, $this$hexToByteArray.length());
        if (startIndex == endIndex) {
            return new byte[0];
        }
        HexFormat.BytesHexFormat bytesFormat = format.getBytes();
        int bytesPerLine = bytesFormat.getBytesPerLine();
        int bytesPerGroup = bytesFormat.getBytesPerGroup();
        String bytePrefix = bytesFormat.getBytePrefix();
        String byteSuffix = bytesFormat.getByteSuffix();
        String byteSeparator = bytesFormat.getByteSeparator();
        String groupSeparator = bytesFormat.getGroupSeparator();
        String groupSeparator2 = groupSeparator;
        int resultCapacity = parsedByteArrayMaxSize(endIndex - startIndex, bytesPerLine, bytesPerGroup, groupSeparator.length(), byteSeparator.length(), bytePrefix.length(), byteSuffix.length());
        byte[] result = new byte[resultCapacity];
        int i = startIndex;
        int byteIndex = 0;
        int indexInLine = 0;
        int indexInGroup = 0;
        while (i < endIndex) {
            if (indexInLine == bytesPerLine) {
                i = checkNewLineAt($this$hexToByteArray, i, endIndex);
                indexInLine = 0;
                indexInGroup = 0;
            } else if (indexInGroup == bytesPerGroup) {
                i = checkContainsAt($this$hexToByteArray, groupSeparator2, i, endIndex, "group separator");
                indexInGroup = 0;
            } else if (indexInGroup != 0) {
                i = checkContainsAt($this$hexToByteArray, byteSeparator, i, endIndex, "byte separator");
            }
            indexInLine++;
            indexInGroup++;
            int i2 = checkContainsAt($this$hexToByteArray, bytePrefix, i, endIndex, "byte prefix");
            HexFormat.BytesHexFormat bytesFormat2 = bytesFormat;
            checkHexLength($this$hexToByteArray, i2, RangesKt.coerceAtMost(i2 + 2, endIndex), 2, true);
            int i3 = i2 + 1;
            int i4 = i3 + 1;
            result[byteIndex] = (byte) (decimalFromHexDigitAt($this$hexToByteArray, i3) | (decimalFromHexDigitAt($this$hexToByteArray, i2) << 4));
            i = checkContainsAt($this$hexToByteArray, byteSuffix, i4, endIndex, "byte suffix");
            byteIndex++;
            groupSeparator2 = groupSeparator2;
            bytesFormat = bytesFormat2;
        }
        if (byteIndex == result.length) {
            return result;
        }
        byte[] copyOf = Arrays.copyOf(result, byteIndex);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(...)");
        return copyOf;
    }

    public static final int parsedByteArrayMaxSize(int stringLength, int bytesPerLine, int bytesPerGroup, int groupSeparatorLength, int byteSeparatorLength, int bytePrefixLength, int byteSuffixLength) {
        long j;
        if (!(stringLength > 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        long charsPerByte = bytePrefixLength + 2 + byteSuffixLength;
        long charsPerGroup = charsPerSet(charsPerByte, bytesPerGroup, byteSeparatorLength);
        if (bytesPerLine <= bytesPerGroup) {
            j = charsPerSet(charsPerByte, bytesPerLine, byteSeparatorLength);
        } else {
            int groupsPerLine = bytesPerLine / bytesPerGroup;
            long result = charsPerSet(charsPerGroup, groupsPerLine, groupSeparatorLength);
            int bytesPerLastGroupInLine = bytesPerLine % bytesPerGroup;
            if (bytesPerLastGroupInLine != 0) {
                result = result + groupSeparatorLength + charsPerSet(charsPerByte, bytesPerLastGroupInLine, byteSeparatorLength);
            }
            j = result;
        }
        long charsPerLine = j;
        long numberOfChars = stringLength;
        long wholeLines = wholeElementsPerSet(numberOfChars, charsPerLine, 1);
        long numberOfChars2 = numberOfChars - ((charsPerLine + 1) * wholeLines);
        long wholeGroupsInLastLine = wholeElementsPerSet(numberOfChars2, charsPerGroup, groupSeparatorLength);
        long numberOfChars3 = numberOfChars2 - ((groupSeparatorLength + charsPerGroup) * wholeGroupsInLastLine);
        long wholeBytesInLastGroup = wholeElementsPerSet(numberOfChars3, charsPerByte, byteSeparatorLength);
        int spare = numberOfChars3 - ((((long) byteSeparatorLength) + charsPerByte) * wholeBytesInLastGroup) > 0 ? 1 : 0;
        return (int) ((bytesPerLine * wholeLines) + (bytesPerGroup * wholeGroupsInLastLine) + wholeBytesInLastGroup + spare);
    }

    private static final long charsPerSet(long charsPerElement, int elementsPerSet, int elementSeparatorLength) {
        if (!(elementsPerSet > 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        return (elementsPerSet * charsPerElement) + (elementSeparatorLength * (elementsPerSet - 1));
    }

    private static final long wholeElementsPerSet(long charsPerSet, long charsPerElement, int elementSeparatorLength) {
        if (charsPerSet <= 0 || charsPerElement <= 0) {
            return 0L;
        }
        return (elementSeparatorLength + charsPerSet) / (elementSeparatorLength + charsPerElement);
    }

    private static final int checkNewLineAt(String $this$checkNewLineAt, int index, int endIndex) {
        if ($this$checkNewLineAt.charAt(index) == '\r') {
            return (index + 1 >= endIndex || $this$checkNewLineAt.charAt(index + 1) != '\n') ? index + 1 : index + 2;
        } else if ($this$checkNewLineAt.charAt(index) == '\n') {
            return index + 1;
        } else {
            throw new NumberFormatException("Expected a new line at index " + index + ", but was " + $this$checkNewLineAt.charAt(index));
        }
    }

    public static final String toHexString(byte $this$toHexString, HexFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        return toHexStringImpl($this$toHexString, format, 8);
    }

    public static /* synthetic */ String toHexString$default(byte b, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return toHexString(b, hexFormat);
    }

    public static final byte hexToByte(String $this$hexToByte, HexFormat format) {
        Intrinsics.checkNotNullParameter($this$hexToByte, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return hexToByte($this$hexToByte, 0, $this$hexToByte.length(), format);
    }

    public static /* synthetic */ byte hexToByte$default(String str, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToByte(str, hexFormat);
    }

    static /* synthetic */ byte hexToByte$default(String str, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToByte(str, i, i2, hexFormat);
    }

    private static final byte hexToByte(String $this$hexToByte, int startIndex, int endIndex, HexFormat format) {
        return (byte) hexToLongImpl($this$hexToByte, startIndex, endIndex, format, 2);
    }

    public static final String toHexString(short $this$toHexString, HexFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        return toHexStringImpl($this$toHexString, format, 16);
    }

    public static /* synthetic */ String toHexString$default(short s, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return toHexString(s, hexFormat);
    }

    public static final short hexToShort(String $this$hexToShort, HexFormat format) {
        Intrinsics.checkNotNullParameter($this$hexToShort, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return hexToShort($this$hexToShort, 0, $this$hexToShort.length(), format);
    }

    public static /* synthetic */ short hexToShort$default(String str, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToShort(str, hexFormat);
    }

    static /* synthetic */ short hexToShort$default(String str, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToShort(str, i, i2, hexFormat);
    }

    private static final short hexToShort(String $this$hexToShort, int startIndex, int endIndex, HexFormat format) {
        return (short) hexToLongImpl($this$hexToShort, startIndex, endIndex, format, 4);
    }

    public static final String toHexString(int $this$toHexString, HexFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        return toHexStringImpl($this$toHexString, format, 32);
    }

    public static /* synthetic */ String toHexString$default(int i, HexFormat hexFormat, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return toHexString(i, hexFormat);
    }

    public static final int hexToInt(String $this$hexToInt, HexFormat format) {
        Intrinsics.checkNotNullParameter($this$hexToInt, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return hexToInt($this$hexToInt, 0, $this$hexToInt.length(), format);
    }

    public static /* synthetic */ int hexToInt$default(String str, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToInt(str, hexFormat);
    }

    static /* synthetic */ int hexToInt$default(String str, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToInt(str, i, i2, hexFormat);
    }

    private static final int hexToInt(String $this$hexToInt, int startIndex, int endIndex, HexFormat format) {
        return (int) hexToLongImpl($this$hexToInt, startIndex, endIndex, format, 8);
    }

    public static final String toHexString(long $this$toHexString, HexFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        return toHexStringImpl($this$toHexString, format, 64);
    }

    public static /* synthetic */ String toHexString$default(long j, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return toHexString(j, hexFormat);
    }

    public static final long hexToLong(String $this$hexToLong, HexFormat format) {
        Intrinsics.checkNotNullParameter($this$hexToLong, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return hexToLong($this$hexToLong, 0, $this$hexToLong.length(), format);
    }

    public static /* synthetic */ long hexToLong$default(String str, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToLong(str, hexFormat);
    }

    static /* synthetic */ long hexToLong$default(String str, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            hexFormat = HexFormat.Companion.getDefault();
        }
        return hexToLong(str, i, i2, hexFormat);
    }

    private static final long hexToLong(String $this$hexToLong, int startIndex, int endIndex, HexFormat format) {
        return hexToLongImpl($this$hexToLong, startIndex, endIndex, format, 16);
    }

    private static final String toHexStringImpl(long $this$toHexStringImpl, HexFormat format, int bits) {
        if (!((bits & 3) == 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        String digits = format.getUpperCase() ? UPPER_CASE_HEX_DIGITS : LOWER_CASE_HEX_DIGITS;
        String prefix = format.getNumber().getPrefix();
        String suffix = format.getNumber().getSuffix();
        int formatLength = prefix.length() + (bits >> 2) + suffix.length();
        boolean removeZeros = format.getNumber().getRemoveLeadingZeros();
        StringBuilder $this$toHexStringImpl_u24lambda_u246 = new StringBuilder(formatLength);
        $this$toHexStringImpl_u24lambda_u246.append(prefix);
        int shift = bits;
        while (shift > 0) {
            shift -= 4;
            int decimal = (int) (($this$toHexStringImpl >> shift) & 15);
            removeZeros = removeZeros && decimal == 0 && shift > 0;
            if (!removeZeros) {
                $this$toHexStringImpl_u24lambda_u246.append(digits.charAt(decimal));
            }
        }
        $this$toHexStringImpl_u24lambda_u246.append(suffix);
        String sb = $this$toHexStringImpl_u24lambda_u246.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
        return sb;
    }

    static /* synthetic */ long hexToLongImpl$default(String str, int i, int i2, HexFormat hexFormat, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = 0;
        }
        if ((i4 & 2) != 0) {
            i2 = str.length();
        }
        return hexToLongImpl(str, i, i2, hexFormat, i3);
    }

    private static final long hexToLongImpl(String $this$hexToLongImpl, int startIndex, int endIndex, HexFormat format, int maxDigits) {
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, $this$hexToLongImpl.length());
        String prefix = format.getNumber().getPrefix();
        String suffix = format.getNumber().getSuffix();
        if (prefix.length() + suffix.length() >= endIndex - startIndex) {
            StringBuilder append = new StringBuilder().append("Expected a hexadecimal number with prefix \"").append(prefix).append("\" and suffix \"").append(suffix).append("\", but was ");
            Intrinsics.checkNotNull($this$hexToLongImpl, "null cannot be cast to non-null type java.lang.String");
            String substring = $this$hexToLongImpl.substring(startIndex, endIndex);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            throw new NumberFormatException(append.append(substring).toString());
        }
        int digitsStartIndex = checkContainsAt($this$hexToLongImpl, prefix, startIndex, endIndex, "prefix");
        int digitsEndIndex = endIndex - suffix.length();
        checkContainsAt($this$hexToLongImpl, suffix, digitsEndIndex, endIndex, "suffix");
        checkHexLength($this$hexToLongImpl, digitsStartIndex, digitsEndIndex, maxDigits, false);
        long result = 0;
        for (int i = digitsStartIndex; i < digitsEndIndex; i++) {
            result = (result << 4) | decimalFromHexDigitAt($this$hexToLongImpl, i);
        }
        return result;
    }

    private static final int checkContainsAt(String $this$checkContainsAt, String part, int index, int endIndex, String partName) {
        int end = part.length() + index;
        if (end > endIndex || !StringsKt.regionMatches($this$checkContainsAt, index, part, 0, part.length(), true)) {
            StringBuilder append = new StringBuilder().append("Expected ").append(partName).append(" \"").append(part).append("\" at index ").append(index).append(", but was ");
            int coerceAtMost = RangesKt.coerceAtMost(end, endIndex);
            Intrinsics.checkNotNull($this$checkContainsAt, "null cannot be cast to non-null type java.lang.String");
            String substring = $this$checkContainsAt.substring(index, coerceAtMost);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            throw new NumberFormatException(append.append(substring).toString());
        }
        return end;
    }

    private static final void checkHexLength(String $this$checkHexLength, int startIndex, int endIndex, int maxDigits, boolean requireMaxLength) {
        int digitsLength = endIndex - startIndex;
        boolean isCorrectLength = true;
        if (!requireMaxLength ? digitsLength > maxDigits : digitsLength != maxDigits) {
            isCorrectLength = false;
        }
        if (!isCorrectLength) {
            String specifier = requireMaxLength ? "exactly" : "at most";
            Intrinsics.checkNotNull($this$checkHexLength, "null cannot be cast to non-null type java.lang.String");
            String substring = $this$checkHexLength.substring(startIndex, endIndex);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            throw new NumberFormatException("Expected " + specifier + ' ' + maxDigits + " hexadecimal digits at index " + startIndex + ", but was " + substring + " of length " + digitsLength);
        }
    }

    private static final int decimalFromHexDigitAt(String $this$decimalFromHexDigitAt, int index) {
        int code = $this$decimalFromHexDigitAt.charAt(index);
        if (code > 127 || HEX_DIGITS_TO_DECIMAL[code] < 0) {
            throw new NumberFormatException("Expected a hexadecimal digit at index " + index + ", but was " + $this$decimalFromHexDigitAt.charAt(index));
        }
        return HEX_DIGITS_TO_DECIMAL[code];
    }
}
