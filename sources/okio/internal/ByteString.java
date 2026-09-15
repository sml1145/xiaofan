package okio.internal;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okio.Base64;
import okio.Buffer;
import okio.SegmentedByteString;
import okio._JvmPlatformKt;
/* compiled from: ByteString.kt */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0002\u001a\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tH\u0080\b\u001a\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0002\u001a\r\u0010\u0011\u001a\u00020\u0012*\u00020\fH\u0080\b\u001a\r\u0010\u0013\u001a\u00020\u0012*\u00020\fH\u0080\b\u001a\u0015\u0010\u0014\u001a\u00020\u0007*\u00020\f2\u0006\u0010\u0015\u001a\u00020\fH\u0080\b\u001a-\u0010\u0016\u001a\u00020\u0017*\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0007H\u0080\b\u001a\u000f\u0010\u001c\u001a\u0004\u0018\u00010\f*\u00020\u0012H\u0080\b\u001a\r\u0010\u001d\u001a\u00020\f*\u00020\u0012H\u0080\b\u001a\r\u0010\u001e\u001a\u00020\f*\u00020\u0012H\u0080\b\u001a\u0015\u0010\u001f\u001a\u00020 *\u00020\f2\u0006\u0010!\u001a\u00020\tH\u0080\b\u001a\u0015\u0010\u001f\u001a\u00020 *\u00020\f2\u0006\u0010!\u001a\u00020\fH\u0080\b\u001a\u0017\u0010\"\u001a\u00020 *\u00020\f2\b\u0010\u0015\u001a\u0004\u0018\u00010#H\u0080\b\u001a\u0015\u0010$\u001a\u00020%*\u00020\f2\u0006\u0010&\u001a\u00020\u0007H\u0080\b\u001a\r\u0010'\u001a\u00020\u0007*\u00020\fH\u0080\b\u001a\r\u0010(\u001a\u00020\u0007*\u00020\fH\u0080\b\u001a\r\u0010)\u001a\u00020\u0012*\u00020\fH\u0080\b\u001a\u001d\u0010*\u001a\u00020\u0007*\u00020\f2\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010+\u001a\u00020\u0007H\u0080\b\u001a\r\u0010,\u001a\u00020\t*\u00020\fH\u0080\b\u001a\u001d\u0010-\u001a\u00020\u0007*\u00020\f2\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010+\u001a\u00020\u0007H\u0080\b\u001a\u001d\u0010-\u001a\u00020\u0007*\u00020\f2\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010+\u001a\u00020\u0007H\u0080\b\u001a-\u0010.\u001a\u00020 *\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010/\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0007H\u0080\b\u001a-\u0010.\u001a\u00020 *\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010/\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0007H\u0080\b\u001a\u0015\u00100\u001a\u00020 *\u00020\f2\u0006\u00101\u001a\u00020\tH\u0080\b\u001a\u0015\u00100\u001a\u00020 *\u00020\f2\u0006\u00101\u001a\u00020\fH\u0080\b\u001a\u001d\u00102\u001a\u00020\f*\u00020\f2\u0006\u00103\u001a\u00020\u00072\u0006\u00104\u001a\u00020\u0007H\u0080\b\u001a\r\u00105\u001a\u00020\f*\u00020\fH\u0080\b\u001a\r\u00106\u001a\u00020\f*\u00020\fH\u0080\b\u001a\r\u00107\u001a\u00020\t*\u00020\fH\u0080\b\u001a\u001d\u00108\u001a\u00020\f*\u00020\t2\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0007H\u0080\b\u001a\r\u00109\u001a\u00020\u0012*\u00020\fH\u0080\b\u001a\r\u0010:\u001a\u00020\u0012*\u00020\fH\u0080\b\u001a$\u0010;\u001a\u00020\u0017*\u00020\f2\u0006\u0010<\u001a\u00020=2\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0007H\u0000\"\u001c\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005¨\u0006>"}, d2 = {"HEX_DIGIT_CHARS", "", "getHEX_DIGIT_CHARS$annotations", "()V", "getHEX_DIGIT_CHARS", "()[C", "codePointIndexToCharIndex", "", "s", "", "codePointCount", "commonOf", "Lokio/ByteString;", "data", "decodeHexDigit", "c", "", "commonBase64", "", "commonBase64Url", "commonCompareTo", "other", "commonCopyInto", "", "offset", "target", "targetOffset", "byteCount", "commonDecodeBase64", "commonDecodeHex", "commonEncodeUtf8", "commonEndsWith", "", "suffix", "commonEquals", "", "commonGetByte", "", "pos", "commonGetSize", "commonHashCode", "commonHex", "commonIndexOf", "fromIndex", "commonInternalArray", "commonLastIndexOf", "commonRangeEquals", "otherOffset", "commonStartsWith", "prefix", "commonSubstring", "beginIndex", "endIndex", "commonToAsciiLowercase", "commonToAsciiUppercase", "commonToByteArray", "commonToByteString", "commonToString", "commonUtf8", "commonWrite", "buffer", "Lokio/Buffer;", "okio"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: okio.internal.-ByteString */
/* loaded from: classes.dex */
public final class ByteString {
    private static final char[] HEX_DIGIT_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static /* synthetic */ void getHEX_DIGIT_CHARS$annotations() {
    }

    public static final String commonUtf8(okio.ByteString $this$commonUtf8) {
        Intrinsics.checkNotNullParameter($this$commonUtf8, "<this>");
        String result = $this$commonUtf8.getUtf8$okio();
        if (result == null) {
            String result2 = _JvmPlatformKt.toUtf8String($this$commonUtf8.internalArray$okio());
            $this$commonUtf8.setUtf8$okio(result2);
            return result2;
        }
        return result;
    }

    public static final String commonBase64(okio.ByteString $this$commonBase64) {
        Intrinsics.checkNotNullParameter($this$commonBase64, "<this>");
        return Base64.encodeBase64$default($this$commonBase64.getData$okio(), null, 1, null);
    }

    public static final String commonBase64Url(okio.ByteString $this$commonBase64Url) {
        Intrinsics.checkNotNullParameter($this$commonBase64Url, "<this>");
        return Base64.encodeBase64($this$commonBase64Url.getData$okio(), Base64.getBASE64_URL_SAFE());
    }

    public static final char[] getHEX_DIGIT_CHARS() {
        return HEX_DIGIT_CHARS;
    }

    public static final String commonHex(okio.ByteString $this$commonHex) {
        byte[] data$okio;
        Intrinsics.checkNotNullParameter($this$commonHex, "<this>");
        char[] result = new char[$this$commonHex.getData$okio().length * 2];
        int c = 0;
        for (byte b : $this$commonHex.getData$okio()) {
            int c2 = c + 1;
            int other$iv = b >> 4;
            result[c] = getHEX_DIGIT_CHARS()[other$iv & 15];
            c = c2 + 1;
            int other$iv2 = 15 & b;
            result[c2] = getHEX_DIGIT_CHARS()[other$iv2];
        }
        return StringsKt.concatToString(result);
    }

    public static final okio.ByteString commonToAsciiLowercase(okio.ByteString $this$commonToAsciiLowercase) {
        Intrinsics.checkNotNullParameter($this$commonToAsciiLowercase, "<this>");
        for (int i = 0; i < $this$commonToAsciiLowercase.getData$okio().length; i++) {
            byte c = $this$commonToAsciiLowercase.getData$okio()[i];
            if (c >= 65 && c <= 90) {
                byte[] data$okio = $this$commonToAsciiLowercase.getData$okio();
                byte[] lowercase = Arrays.copyOf(data$okio, data$okio.length);
                Intrinsics.checkNotNullExpressionValue(lowercase, "copyOf(this, size)");
                int i2 = i + 1;
                lowercase[i] = (byte) (c + 32);
                while (i2 < lowercase.length) {
                    byte c2 = lowercase[i2];
                    if (c2 < 65 || c2 > 90) {
                        i2++;
                    } else {
                        lowercase[i2] = (byte) (c2 + 32);
                        i2++;
                    }
                }
                return new okio.ByteString(lowercase);
            }
        }
        return $this$commonToAsciiLowercase;
    }

    public static final okio.ByteString commonToAsciiUppercase(okio.ByteString $this$commonToAsciiUppercase) {
        Intrinsics.checkNotNullParameter($this$commonToAsciiUppercase, "<this>");
        for (int i = 0; i < $this$commonToAsciiUppercase.getData$okio().length; i++) {
            byte c = $this$commonToAsciiUppercase.getData$okio()[i];
            if (c >= 97 && c <= 122) {
                byte[] data$okio = $this$commonToAsciiUppercase.getData$okio();
                byte[] lowercase = Arrays.copyOf(data$okio, data$okio.length);
                Intrinsics.checkNotNullExpressionValue(lowercase, "copyOf(this, size)");
                int i2 = i + 1;
                lowercase[i] = (byte) (c - 32);
                while (i2 < lowercase.length) {
                    byte c2 = lowercase[i2];
                    if (c2 < 97 || c2 > 122) {
                        i2++;
                    } else {
                        lowercase[i2] = (byte) (c2 - 32);
                        i2++;
                    }
                }
                return new okio.ByteString(lowercase);
            }
        }
        return $this$commonToAsciiUppercase;
    }

    public static final okio.ByteString commonSubstring(okio.ByteString $this$commonSubstring, int beginIndex, int endIndex) {
        Intrinsics.checkNotNullParameter($this$commonSubstring, "<this>");
        int endIndex2 = SegmentedByteString.resolveDefaultParameter($this$commonSubstring, endIndex);
        if (beginIndex >= 0) {
            if (!(endIndex2 <= $this$commonSubstring.getData$okio().length)) {
                throw new IllegalArgumentException(("endIndex > length(" + $this$commonSubstring.getData$okio().length + ')').toString());
            }
            int subLen = endIndex2 - beginIndex;
            if (subLen >= 0) {
                if (beginIndex == 0 && endIndex2 == $this$commonSubstring.getData$okio().length) {
                    return $this$commonSubstring;
                }
                return new okio.ByteString(ArraysKt.copyOfRange($this$commonSubstring.getData$okio(), beginIndex, endIndex2));
            }
            throw new IllegalArgumentException("endIndex < beginIndex".toString());
        }
        throw new IllegalArgumentException("beginIndex < 0".toString());
    }

    public static final byte commonGetByte(okio.ByteString $this$commonGetByte, int pos) {
        Intrinsics.checkNotNullParameter($this$commonGetByte, "<this>");
        return $this$commonGetByte.getData$okio()[pos];
    }

    public static final int commonGetSize(okio.ByteString $this$commonGetSize) {
        Intrinsics.checkNotNullParameter($this$commonGetSize, "<this>");
        return $this$commonGetSize.getData$okio().length;
    }

    public static final byte[] commonToByteArray(okio.ByteString $this$commonToByteArray) {
        Intrinsics.checkNotNullParameter($this$commonToByteArray, "<this>");
        byte[] data$okio = $this$commonToByteArray.getData$okio();
        byte[] copyOf = Arrays.copyOf(data$okio, data$okio.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        return copyOf;
    }

    public static final byte[] commonInternalArray(okio.ByteString $this$commonInternalArray) {
        Intrinsics.checkNotNullParameter($this$commonInternalArray, "<this>");
        return $this$commonInternalArray.getData$okio();
    }

    public static final boolean commonRangeEquals(okio.ByteString $this$commonRangeEquals, int offset, okio.ByteString other, int otherOffset, int byteCount) {
        Intrinsics.checkNotNullParameter($this$commonRangeEquals, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        return other.rangeEquals(otherOffset, $this$commonRangeEquals.getData$okio(), offset, byteCount);
    }

    public static final boolean commonRangeEquals(okio.ByteString $this$commonRangeEquals, int offset, byte[] other, int otherOffset, int byteCount) {
        Intrinsics.checkNotNullParameter($this$commonRangeEquals, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        return offset >= 0 && offset <= $this$commonRangeEquals.getData$okio().length - byteCount && otherOffset >= 0 && otherOffset <= other.length - byteCount && SegmentedByteString.arrayRangeEquals($this$commonRangeEquals.getData$okio(), offset, other, otherOffset, byteCount);
    }

    public static final void commonCopyInto(okio.ByteString $this$commonCopyInto, int offset, byte[] target, int targetOffset, int byteCount) {
        Intrinsics.checkNotNullParameter($this$commonCopyInto, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        ArraysKt.copyInto($this$commonCopyInto.getData$okio(), target, targetOffset, offset, offset + byteCount);
    }

    public static final boolean commonStartsWith(okio.ByteString $this$commonStartsWith, okio.ByteString prefix) {
        Intrinsics.checkNotNullParameter($this$commonStartsWith, "<this>");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        return $this$commonStartsWith.rangeEquals(0, prefix, 0, prefix.size());
    }

    public static final boolean commonStartsWith(okio.ByteString $this$commonStartsWith, byte[] prefix) {
        Intrinsics.checkNotNullParameter($this$commonStartsWith, "<this>");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        return $this$commonStartsWith.rangeEquals(0, prefix, 0, prefix.length);
    }

    public static final boolean commonEndsWith(okio.ByteString $this$commonEndsWith, okio.ByteString suffix) {
        Intrinsics.checkNotNullParameter($this$commonEndsWith, "<this>");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        return $this$commonEndsWith.rangeEquals($this$commonEndsWith.size() - suffix.size(), suffix, 0, suffix.size());
    }

    public static final boolean commonEndsWith(okio.ByteString $this$commonEndsWith, byte[] suffix) {
        Intrinsics.checkNotNullParameter($this$commonEndsWith, "<this>");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        return $this$commonEndsWith.rangeEquals($this$commonEndsWith.size() - suffix.length, suffix, 0, suffix.length);
    }

    public static final int commonIndexOf(okio.ByteString $this$commonIndexOf, byte[] other, int fromIndex) {
        Intrinsics.checkNotNullParameter($this$commonIndexOf, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int limit = $this$commonIndexOf.getData$okio().length - other.length;
        int i = Math.max(fromIndex, 0);
        if (i <= limit) {
            while (!SegmentedByteString.arrayRangeEquals($this$commonIndexOf.getData$okio(), i, other, 0, other.length)) {
                if (i == limit) {
                    return -1;
                }
                i++;
            }
            return i;
        }
        return -1;
    }

    public static final int commonLastIndexOf(okio.ByteString $this$commonLastIndexOf, okio.ByteString other, int fromIndex) {
        Intrinsics.checkNotNullParameter($this$commonLastIndexOf, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        return $this$commonLastIndexOf.lastIndexOf(other.internalArray$okio(), fromIndex);
    }

    public static final int commonLastIndexOf(okio.ByteString $this$commonLastIndexOf, byte[] other, int fromIndex) {
        Intrinsics.checkNotNullParameter($this$commonLastIndexOf, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int fromIndex2 = SegmentedByteString.resolveDefaultParameter($this$commonLastIndexOf, fromIndex);
        int limit = $this$commonLastIndexOf.getData$okio().length - other.length;
        for (int i = Math.min(fromIndex2, limit); -1 < i; i--) {
            if (SegmentedByteString.arrayRangeEquals($this$commonLastIndexOf.getData$okio(), i, other, 0, other.length)) {
                return i;
            }
        }
        return -1;
    }

    public static final boolean commonEquals(okio.ByteString $this$commonEquals, Object other) {
        Intrinsics.checkNotNullParameter($this$commonEquals, "<this>");
        if (other == $this$commonEquals) {
            return true;
        }
        if (other instanceof okio.ByteString) {
            return ((okio.ByteString) other).size() == $this$commonEquals.getData$okio().length && ((okio.ByteString) other).rangeEquals(0, $this$commonEquals.getData$okio(), 0, $this$commonEquals.getData$okio().length);
        }
        return false;
    }

    public static final int commonHashCode(okio.ByteString $this$commonHashCode) {
        Intrinsics.checkNotNullParameter($this$commonHashCode, "<this>");
        int result = $this$commonHashCode.getHashCode$okio();
        if (result != 0) {
            return result;
        }
        int it = Arrays.hashCode($this$commonHashCode.getData$okio());
        $this$commonHashCode.setHashCode$okio(it);
        return it;
    }

    public static final int commonCompareTo(okio.ByteString $this$commonCompareTo, okio.ByteString other) {
        Intrinsics.checkNotNullParameter($this$commonCompareTo, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int sizeA = $this$commonCompareTo.size();
        int sizeB = other.size();
        int size = Math.min(sizeA, sizeB);
        for (int i = 0; i < size; i++) {
            byte $this$and$iv = $this$commonCompareTo.getByte(i);
            int byteA = $this$and$iv & UByte.MAX_VALUE;
            byte $this$and$iv2 = other.getByte(i);
            int byteB = $this$and$iv2 & UByte.MAX_VALUE;
            if (byteA != byteB) {
                return byteA < byteB ? -1 : 1;
            }
        }
        if (sizeA == sizeB) {
            return 0;
        }
        return sizeA < sizeB ? -1 : 1;
    }

    public static final okio.ByteString commonOf(byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        byte[] copyOf = Arrays.copyOf(data, data.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        return new okio.ByteString(copyOf);
    }

    public static final okio.ByteString commonToByteString(byte[] $this$commonToByteString, int offset, int byteCount) {
        Intrinsics.checkNotNullParameter($this$commonToByteString, "<this>");
        int byteCount2 = SegmentedByteString.resolveDefaultParameter($this$commonToByteString, byteCount);
        SegmentedByteString.checkOffsetAndCount($this$commonToByteString.length, offset, byteCount2);
        return new okio.ByteString(ArraysKt.copyOfRange($this$commonToByteString, offset, offset + byteCount2));
    }

    public static final okio.ByteString commonEncodeUtf8(String $this$commonEncodeUtf8) {
        Intrinsics.checkNotNullParameter($this$commonEncodeUtf8, "<this>");
        okio.ByteString byteString = new okio.ByteString(_JvmPlatformKt.asUtf8ToByteArray($this$commonEncodeUtf8));
        byteString.setUtf8$okio($this$commonEncodeUtf8);
        return byteString;
    }

    public static final okio.ByteString commonDecodeBase64(String $this$commonDecodeBase64) {
        Intrinsics.checkNotNullParameter($this$commonDecodeBase64, "<this>");
        byte[] decoded = Base64.decodeBase64ToArray($this$commonDecodeBase64);
        if (decoded != null) {
            return new okio.ByteString(decoded);
        }
        return null;
    }

    public static final okio.ByteString commonDecodeHex(String $this$commonDecodeHex) {
        Intrinsics.checkNotNullParameter($this$commonDecodeHex, "<this>");
        if (!($this$commonDecodeHex.length() % 2 == 0)) {
            throw new IllegalArgumentException(("Unexpected hex string: " + $this$commonDecodeHex).toString());
        }
        byte[] result = new byte[$this$commonDecodeHex.length() / 2];
        int length = result.length;
        for (int i = 0; i < length; i++) {
            int d1 = decodeHexDigit($this$commonDecodeHex.charAt(i * 2)) << 4;
            int d2 = decodeHexDigit($this$commonDecodeHex.charAt((i * 2) + 1));
            result[i] = (byte) (d1 + d2);
        }
        return new okio.ByteString(result);
    }

    public static final void commonWrite(okio.ByteString $this$commonWrite, Buffer buffer, int offset, int byteCount) {
        Intrinsics.checkNotNullParameter($this$commonWrite, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        buffer.write($this$commonWrite.getData$okio(), offset, byteCount);
    }

    public static final int decodeHexDigit(char c) {
        boolean z = true;
        if ('0' <= c && c < ':') {
            return c - '0';
        }
        if ('a' <= c && c < 'g') {
            return (c - 'a') + 10;
        }
        if ('A' > c || c >= 'G') {
            z = false;
        }
        if (z) {
            return (c - 'A') + 10;
        }
        throw new IllegalArgumentException("Unexpected hex digit: " + c);
    }

    public static final String commonToString(okio.ByteString $this$commonToString) {
        Intrinsics.checkNotNullParameter($this$commonToString, "<this>");
        if ($this$commonToString.getData$okio().length == 0) {
            return "[size=0]";
        }
        int i = codePointIndexToCharIndex($this$commonToString.getData$okio(), 64);
        if (i == -1) {
            if ($this$commonToString.getData$okio().length <= 64) {
                return "[hex=" + $this$commonToString.hex() + ']';
            }
            StringBuilder append = new StringBuilder().append("[size=").append($this$commonToString.getData$okio().length).append(" hex=");
            okio.ByteString $this$commonSubstring$iv = $this$commonToString;
            int endIndex$iv = SegmentedByteString.resolveDefaultParameter($this$commonSubstring$iv, 64);
            if (!(endIndex$iv <= $this$commonSubstring$iv.getData$okio().length)) {
                throw new IllegalArgumentException(("endIndex > length(" + $this$commonSubstring$iv.getData$okio().length + ')').toString());
            }
            int subLen$iv = endIndex$iv - 0;
            if (subLen$iv >= 0) {
                if (endIndex$iv != $this$commonSubstring$iv.getData$okio().length) {
                    $this$commonSubstring$iv = new okio.ByteString(ArraysKt.copyOfRange($this$commonSubstring$iv.getData$okio(), 0, endIndex$iv));
                }
                return append.append($this$commonSubstring$iv.hex()).append("…]").toString();
            }
            throw new IllegalArgumentException("endIndex < beginIndex".toString());
        }
        String text = $this$commonToString.utf8();
        String substring = text.substring(0, i);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        String safeText = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(substring, "\\", "\\\\", false, 4, (Object) null), "\n", "\\n", false, 4, (Object) null), "\r", "\\r", false, 4, (Object) null);
        if (i < text.length()) {
            return "[size=" + $this$commonToString.getData$okio().length + " text=" + safeText + "…]";
        }
        return "[text=" + safeText + ']';
    }

    /* JADX WARN: Code restructure failed: missing block: B:1004:0x0166, code lost:
        if ((127(0x7f, float:1.78E-43) <= 65533(0xfffd, float:9.1831E-41) && 65533(0xfffd, float:9.1831E-41) < 160(0xa0, float:2.24E-43)) != false) goto L686;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1038:0x01ca, code lost:
        if ((127(0x7f, float:1.78E-43) <= 65533(0xfffd, float:9.1831E-41) && 65533(0xfffd, float:9.1831E-41) < 160(0xa0, float:2.24E-43)) != false) goto L723;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1071:0x0216, code lost:
        if ((127 <= r3 && r3 < 160) != false) goto L759;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1157:0x0303, code lost:
        if ((127(0x7f, float:1.78E-43) <= 65533(0xfffd, float:9.1831E-41) && 65533(0xfffd, float:9.1831E-41) < 160(0xa0, float:2.24E-43)) != false) goto L454;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1194:0x036e, code lost:
        if ((127(0x7f, float:1.78E-43) <= 65533(0xfffd, float:9.1831E-41) && 65533(0xfffd, float:9.1831E-41) < 160(0xa0, float:2.24E-43)) != false) goto L493;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1229:0x03d7, code lost:
        if ((127(0x7f, float:1.78E-43) <= 65533(0xfffd, float:9.1831E-41) && 65533(0xfffd, float:9.1831E-41) < 160(0xa0, float:2.24E-43)) != false) goto L530;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1268:0x0438, code lost:
        if ((127(0x7f, float:1.78E-43) <= 65533(0xfffd, float:9.1831E-41) && 65533(0xfffd, float:9.1831E-41) < 160(0xa0, float:2.24E-43)) != false) goto L572;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1300:0x0483, code lost:
        if ((127 <= r10 && r10 < 160) != false) goto L607;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1394:0x058b, code lost:
        if ((127(0x7f, float:1.78E-43) <= 65533(0xfffd, float:9.1831E-41) && 65533(0xfffd, float:9.1831E-41) < 160(0xa0, float:2.24E-43)) != false) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1431:0x05f4, code lost:
        if ((127(0x7f, float:1.78E-43) <= 65533(0xfffd, float:9.1831E-41) && 65533(0xfffd, float:9.1831E-41) < 160(0xa0, float:2.24E-43)) != false) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1469:0x0660, code lost:
        if ((127(0x7f, float:1.78E-43) <= 65533(0xfffd, float:9.1831E-41) && 65533(0xfffd, float:9.1831E-41) < 160(0xa0, float:2.24E-43)) != false) goto L212;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1503:0x06ce, code lost:
        if ((127(0x7f, float:1.78E-43) <= 65533(0xfffd, float:9.1831E-41) && 65533(0xfffd, float:9.1831E-41) < 160(0xa0, float:2.24E-43)) != false) goto L249;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1542:0x072f, code lost:
        if ((127(0x7f, float:1.78E-43) <= 65533(0xfffd, float:9.1831E-41) && 65533(0xfffd, float:9.1831E-41) < 160(0xa0, float:2.24E-43)) != false) goto L291;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1576:0x0781, code lost:
        if ((127(0x7f, float:1.78E-43) <= 65533(0xfffd, float:9.1831E-41) && 65533(0xfffd, float:9.1831E-41) < 160(0xa0, float:2.24E-43)) != false) goto L328;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1608:0x07cc, code lost:
        if ((127 <= r3 && r3 < 160) != false) goto L363;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1641:0x081e, code lost:
        if ((127(0x7f, float:1.78E-43) <= 65533(0xfffd, float:9.1831E-41) && 65533(0xfffd, float:9.1831E-41) < 160(0xa0, float:2.24E-43)) != false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:967:0x00f1, code lost:
        if ((127(0x7f, float:1.78E-43) <= 65533(0xfffd, float:9.1831E-41) && 65533(0xfffd, float:9.1831E-41) < 160(0xa0, float:2.24E-43)) != false) goto L646;
     */
    /* JADX WARN: Removed duplicated region for block: B:1032:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:1065:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:1151:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:1188:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:1223:0x03ca  */
    /* JADX WARN: Removed duplicated region for block: B:1262:0x042b  */
    /* JADX WARN: Removed duplicated region for block: B:1294:0x0476  */
    /* JADX WARN: Removed duplicated region for block: B:1388:0x057e  */
    /* JADX WARN: Removed duplicated region for block: B:1425:0x05e7  */
    /* JADX WARN: Removed duplicated region for block: B:1463:0x0653  */
    /* JADX WARN: Removed duplicated region for block: B:1497:0x06c1  */
    /* JADX WARN: Removed duplicated region for block: B:1536:0x0722  */
    /* JADX WARN: Removed duplicated region for block: B:1570:0x0774  */
    /* JADX WARN: Removed duplicated region for block: B:1602:0x07bf  */
    /* JADX WARN: Removed duplicated region for block: B:1655:0x00a4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1659:0x0059 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1662:0x0221 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1666:0x0171 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1670:0x01d5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1677:0x0443 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1682:0x0287 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1684:0x048e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1688:0x030e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1691:0x0379 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1694:0x03e2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1698:0x066b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1703:0x06d9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1705:0x04f3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1708:0x073a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1712:0x078c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1714:0x0596 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1718:0x07d7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1720:0x05ff A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:998:0x0159  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int codePointIndexToCharIndex(byte[] r30, int r31) {
        /*
            Method dump skipped, instructions count: 2112
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.ByteString.codePointIndexToCharIndex(byte[], int):int");
    }
}
