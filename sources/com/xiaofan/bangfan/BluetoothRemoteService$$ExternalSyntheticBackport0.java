package com.xiaofan.bangfan;
/* compiled from: D8$$SyntheticClass */
/* loaded from: classes4.dex */
public final /* synthetic */ class BluetoothRemoteService$$ExternalSyntheticBackport0 {
    public static /* synthetic */ long m(CharSequence charSequence, int i, int i2, int i3) {
        int i4;
        int i5 = i2 - i;
        if (i5 != 0) {
            if (i3 < 2 || i3 > 36) {
                throw new NumberFormatException("illegal radix: ".concat(String.valueOf(i3)));
            }
            long j = i3;
            long m = BluetoothRemoteService$$ExternalSyntheticBackport2.m(-1L, j);
            int i6 = (charSequence.charAt(i) != '+' || i5 <= 1) ? i : i + 1;
            long j2 = 0;
            long j3 = 0;
            while (i6 < i2) {
                int digit = Character.digit(charSequence.charAt(i6), i3);
                if (digit == -1) {
                    throw new NumberFormatException(charSequence.toString());
                }
                if (j3 < j2 || j3 > m || (i4 == 0 && digit > ((int) BluetoothRemoteService$$ExternalSyntheticBackport3.m(-1L, j)))) {
                    throw new NumberFormatException("Too large for unsigned long: ".concat(charSequence.toString()));
                }
                j3 = (j3 * j) + digit;
                i6++;
                j2 = 0;
            }
            return j3;
        }
        throw new NumberFormatException("empty string");
    }
}
