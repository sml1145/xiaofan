package com.xiaofan.bangfan;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlin.time.DurationKt;
/* compiled from: GomokuSecurity.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0002J\u0006\u0010\u000e\u001a\u00020\u0006J\u0018\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0006J\u0006\u0010\u0013\u001a\u00020\u0006J\u0006\u0010\u0014\u001a\u00020\u0006J\u0016\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0006J\u001e\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006J\u0010\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/xiaofan/bangfan/GomokuSecurity;", "", "()V", "HEX", "", "ID_PREFIX", "", "RNG", "Ljava/security/SecureRandom;", "SALT", "constantTimeEquals", "", "a", "b", "generateVisibleRoomCode", "matches", "password", "expectedHash", "passwordHash", "randomClientId", "randomConnectionId", "realPeerId", "visibleCode", "gameType", "sha256", "input", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class GomokuSecurity {
    private static final char[] HEX;
    private static final String ID_PREFIX = "xf";
    public static final GomokuSecurity INSTANCE = new GomokuSecurity();
    private static final SecureRandom RNG = new SecureRandom();
    private static final String SALT = "xiaofan_gomoku_secure_2026_v9_q7z3m9";

    private GomokuSecurity() {
    }

    static {
        char[] charArray = "0123456789abcdef".toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "toCharArray(...)");
        HEX = charArray;
    }

    private final String sha256(String input) {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = input.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] digest = messageDigest.digest(bytes);
        StringBuilder sb = new StringBuilder(digest.length * 2);
        Intrinsics.checkNotNull(digest);
        for (byte b : digest) {
            int v = b & UByte.MAX_VALUE;
            sb.append(HEX[v >>> 4]).append(HEX[v & 15]);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    public final String generateVisibleRoomCode() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%06d", Arrays.copyOf(new Object[]{Integer.valueOf(RNG.nextInt(DurationKt.NANOS_IN_MILLIS))}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    public final String realPeerId(String visibleCode, String password) {
        Intrinsics.checkNotNullParameter(visibleCode, "visibleCode");
        Intrinsics.checkNotNullParameter(password, "password");
        return realPeerId(visibleCode, password, "gomoku");
    }

    public final String realPeerId(String visibleCode, String password, String gameType) {
        Intrinsics.checkNotNullParameter(visibleCode, "visibleCode");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(gameType, "gameType");
        String raw = StringsKt.trim((CharSequence) visibleCode).toString() + "|" + StringsKt.trim((CharSequence) password).toString() + "|" + StringsKt.trim((CharSequence) gameType).toString() + "|xiaofan_gomoku_secure_2026_v9_q7z3m9";
        String substring = sha256(raw).substring(0, 18);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        return ID_PREFIX + substring;
    }

    public final String randomClientId() {
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            sb.append("abcdefghijklmnopqrstuvwxyz0123456789".charAt(RNG.nextInt("abcdefghijklmnopqrstuvwxyz0123456789".length())));
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    public final String randomConnectionId() {
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            sb.append("abcdefghijklmnopqrstuvwxyz0123456789".charAt(RNG.nextInt("abcdefghijklmnopqrstuvwxyz0123456789".length())));
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    public final String passwordHash(String password) {
        Intrinsics.checkNotNullParameter(password, "password");
        return sha256(StringsKt.trim((CharSequence) password).toString() + "|xiaofan_gomoku_secure_2026_v9_q7z3m9");
    }

    public final boolean matches(String password, String expectedHash) {
        Intrinsics.checkNotNullParameter(password, "password");
        String str = expectedHash;
        if (str == null || str.length() == 0) {
            return false;
        }
        return constantTimeEquals(passwordHash(password), expectedHash);
    }

    private final boolean constantTimeEquals(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        int r = 0;
        int length = a.length();
        for (int i = 0; i < length; i++) {
            r |= a.charAt(i) ^ b.charAt(i);
        }
        return r == 0;
    }
}
