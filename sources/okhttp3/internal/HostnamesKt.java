package okhttp3.internal;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.net.IDN;
import java.net.InetAddress;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okio.Buffer;
/* compiled from: hostnames.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0002\u001a\"\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u001a\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\f\u0010\r\u001a\u00020\u0001*\u00020\u0003H\u0002\u001a\f\u0010\u000e\u001a\u0004\u0018\u00010\u0003*\u00020\u0003¨\u0006\u000f"}, d2 = {"decodeIpv4Suffix", "", "input", "", "pos", "", "limit", "address", "", "addressOffset", "decodeIpv6", "Ljava/net/InetAddress;", "inet6AddressToAscii", "containsInvalidHostnameAsciiCodes", "toCanonicalHost", "okhttp"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class HostnamesKt {
    public static final String toCanonicalHost(String $this$toCanonicalHost) {
        Intrinsics.checkNotNullParameter($this$toCanonicalHost, "<this>");
        if (StringsKt.contains$default((CharSequence) $this$toCanonicalHost, (CharSequence) ":", false, 2, (Object) null)) {
            InetAddress inetAddress = (StringsKt.startsWith$default($this$toCanonicalHost, "[", false, 2, (Object) null) && StringsKt.endsWith$default($this$toCanonicalHost, "]", false, 2, (Object) null)) ? decodeIpv6($this$toCanonicalHost, 1, $this$toCanonicalHost.length() - 1) : decodeIpv6($this$toCanonicalHost, 0, $this$toCanonicalHost.length());
            if (inetAddress == null) {
                return null;
            }
            byte[] address = inetAddress.getAddress();
            if (address.length == 16) {
                Intrinsics.checkNotNullExpressionValue(address, "address");
                return inet6AddressToAscii(address);
            } else if (address.length == 4) {
                return inetAddress.getHostAddress();
            } else {
                throw new AssertionError("Invalid IPv6 address: '" + $this$toCanonicalHost + '\'');
            }
        }
        try {
            String ascii = IDN.toASCII($this$toCanonicalHost);
            Intrinsics.checkNotNullExpressionValue(ascii, "toASCII(host)");
            Locale US = Locale.US;
            Intrinsics.checkNotNullExpressionValue(US, "US");
            String result = ascii.toLowerCase(US);
            Intrinsics.checkNotNullExpressionValue(result, "this as java.lang.String).toLowerCase(locale)");
            if (result.length() == 0) {
                return null;
            }
            if (containsInvalidHostnameAsciiCodes(result)) {
                return null;
            }
            return result;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private static final boolean containsInvalidHostnameAsciiCodes(String $this$containsInvalidHostnameAsciiCodes) {
        int length = $this$containsInvalidHostnameAsciiCodes.length();
        for (int i = 0; i < length; i++) {
            char c = $this$containsInvalidHostnameAsciiCodes.charAt(i);
            if (Intrinsics.compare((int) c, 31) <= 0 || Intrinsics.compare((int) c, 127) >= 0 || StringsKt.indexOf$default((CharSequence) " #%/:?@[\\]", c, 0, false, 6, (Object) null) != -1) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x008b, code lost:
        return null;
     */
    /* JADX WARN: Removed duplicated region for block: B:30:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final java.net.InetAddress decodeIpv6(java.lang.String r13, int r14, int r15) {
        /*
            r0 = 16
            byte[] r0 = new byte[r0]
            r1 = 0
            r2 = -1
            r3 = -1
            r4 = r14
            r10 = r4
        L9:
            r11 = -1
            r12 = 0
            if (r10 >= r15) goto L8c
            int r4 = r0.length
            if (r1 != r4) goto L11
            return r12
        L11:
            int r4 = r10 + 2
            if (r4 > r15) goto L2e
            java.lang.String r5 = "::"
            r7 = 0
            r8 = 4
            r9 = 0
            r4 = r13
            r6 = r10
            boolean r4 = kotlin.text.StringsKt.startsWith$default(r4, r5, r6, r7, r8, r9)
            if (r4 == 0) goto L2e
            if (r2 == r11) goto L25
            return r12
        L25:
            int r10 = r10 + 2
            int r1 = r1 + 2
            r2 = r1
            if (r10 != r15) goto L5b
            goto L8c
        L2e:
            if (r1 == 0) goto L5b
            java.lang.String r5 = ":"
            r7 = 0
            r8 = 4
            r9 = 0
            r4 = r13
            r6 = r10
            boolean r4 = kotlin.text.StringsKt.startsWith$default(r4, r5, r6, r7, r8, r9)
            if (r4 == 0) goto L40
            int r10 = r10 + 1
            goto L5b
        L40:
            java.lang.String r5 = "."
            r7 = 0
            r8 = 4
            r9 = 0
            r4 = r13
            r6 = r10
            boolean r4 = kotlin.text.StringsKt.startsWith$default(r4, r5, r6, r7, r8, r9)
            if (r4 == 0) goto L5a
            int r4 = r1 + (-2)
            boolean r4 = decodeIpv4Suffix(r13, r3, r15, r0, r4)
            if (r4 != 0) goto L56
            return r12
        L56:
            int r1 = r1 + 2
            goto L8c
        L5a:
            return r12
        L5b:
            r4 = 0
            r3 = r10
        L5d:
            if (r10 >= r15) goto L71
            char r5 = r13.charAt(r10)
            int r5 = okhttp3.internal.Util.parseHexDigit(r5)
            if (r5 == r11) goto L71
            int r6 = r4 << 4
            int r4 = r6 + r5
            int r10 = r10 + 1
            goto L5d
        L71:
            int r5 = r10 - r3
            if (r5 == 0) goto L8b
            r6 = 4
            if (r5 <= r6) goto L79
            goto L8b
        L79:
            int r6 = r1 + 1
            int r7 = r4 >>> 8
            r7 = r7 & 255(0xff, float:3.57E-43)
            byte r7 = (byte) r7
            r0[r1] = r7
            int r1 = r6 + 1
            r7 = r4 & 255(0xff, float:3.57E-43)
            byte r7 = (byte) r7
            r0[r6] = r7
            goto L9
        L8b:
            return r12
        L8c:
            int r4 = r0.length
            if (r1 == r4) goto La2
            if (r2 != r11) goto L92
            return r12
        L92:
            int r4 = r0.length
            int r5 = r1 - r2
            int r4 = r4 - r5
            int r5 = r1 - r2
            java.lang.System.arraycopy(r0, r2, r0, r4, r5)
            int r4 = r0.length
            int r4 = r4 - r1
            int r4 = r4 + r2
            r5 = 0
            java.util.Arrays.fill(r0, r2, r4, r5)
        La2:
            java.net.InetAddress r4 = java.net.InetAddress.getByAddress(r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.HostnamesKt.decodeIpv6(java.lang.String, int, int):java.net.InetAddress");
    }

    private static final boolean decodeIpv4Suffix(String input, int pos, int limit, byte[] address, int addressOffset) {
        int b = addressOffset;
        int i = pos;
        while (i < limit) {
            if (b == address.length) {
                return false;
            }
            if (b != addressOffset) {
                if (input.charAt(i) != '.') {
                    return false;
                }
                i++;
            }
            int value = 0;
            int groupOffset = i;
            while (i < limit) {
                char c = input.charAt(i);
                if (Intrinsics.compare((int) c, 48) < 0 || Intrinsics.compare((int) c, 57) > 0) {
                    break;
                } else if ((value == 0 && groupOffset != i) || ((value * 10) + c) - 48 > 255) {
                    return false;
                } else {
                    i++;
                }
            }
            int groupLength = i - groupOffset;
            if (groupLength == 0) {
                return false;
            }
            address[b] = (byte) value;
            b++;
        }
        return b == addressOffset + 4;
    }

    private static final String inet6AddressToAscii(byte[] address) {
        int longestRunOffset = -1;
        int longestRunLength = 0;
        int i = 0;
        while (i < address.length) {
            int currentRunOffset = i;
            while (i < 16 && address[i] == 0 && address[i + 1] == 0) {
                i += 2;
            }
            int currentRunLength = i - currentRunOffset;
            if (currentRunLength > longestRunLength && currentRunLength >= 4) {
                longestRunOffset = currentRunOffset;
                longestRunLength = currentRunLength;
            }
            i += 2;
        }
        Buffer result = new Buffer();
        int i2 = 0;
        while (i2 < address.length) {
            if (i2 == longestRunOffset) {
                result.writeByte(58);
                i2 += longestRunLength;
                if (i2 == 16) {
                    result.writeByte(58);
                }
            } else {
                if (i2 > 0) {
                    result.writeByte(58);
                }
                int group = (Util.and(address[i2], 255) << 8) | Util.and(address[i2 + 1], 255);
                result.writeHexadecimalUnsignedLong(group);
                i2 += 2;
            }
        }
        return result.readUtf8();
    }
}
