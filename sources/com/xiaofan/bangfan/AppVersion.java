package com.xiaofan.bangfan;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: AppVersion.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u001a\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0004¨\u0006\r"}, d2 = {"Lcom/xiaofan/bangfan/AppVersion;", "", "()V", "bumpMinor", "", "current", "isNewer", "", "a", "b", "segments", "", "raw", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class AppVersion {
    public static final AppVersion INSTANCE = new AppVersion();

    private AppVersion() {
    }

    public final int[] segments(String raw) {
        String clean = StringsKt.trim((CharSequence) StringsKt.trimStart(StringsKt.trim((CharSequence) (raw == null ? "" : raw)).toString(), 'v', 'V')).toString();
        if (clean.length() == 0) {
            return new int[0];
        }
        Iterable $this$map$iv = StringsKt.split$default((CharSequence) clean, new char[]{'.', '-', '_'}, false, 0, 6, (Object) null);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            CharSequence p = (String) item$iv$iv;
            CharSequence $this$filterTo$iv$iv = p;
            Appendable destination$iv$iv2 = new StringBuilder();
            int length = $this$filterTo$iv$iv.length();
            String clean2 = clean;
            int index$iv$iv = 0;
            while (index$iv$iv < length) {
                Iterable $this$map$iv2 = $this$map$iv;
                char element$iv$iv = $this$filterTo$iv$iv.charAt(index$iv$iv);
                if (Character.isDigit(element$iv$iv)) {
                    destination$iv$iv2.append(element$iv$iv);
                }
                index$iv$iv++;
                $this$map$iv = $this$map$iv2;
            }
            Iterable $this$map$iv3 = $this$map$iv;
            String sb = ((StringBuilder) destination$iv$iv2).toString();
            Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
            Integer intOrNull = StringsKt.toIntOrNull(sb);
            destination$iv$iv.add(Integer.valueOf(intOrNull != null ? intOrNull.intValue() : 0));
            $this$map$iv = $this$map$iv3;
            clean = clean2;
        }
        return CollectionsKt.toIntArray((List) destination$iv$iv);
    }

    public final boolean isNewer(String a, String b) {
        int[] x = segments(a);
        int[] y = segments(b);
        int n = Math.max(x.length, y.length);
        int i = 0;
        while (i < n) {
            int xa = i < x.length ? x[i] : 0;
            int yb = i < y.length ? y[i] : 0;
            if (xa != yb) {
                return xa > yb;
            }
            i++;
        }
        return false;
    }

    public final String bumpMinor(String current) {
        int[] s = segments(current);
        if (s.length == 0) {
            return "2.2";
        }
        int major = s[0];
        int minor = s.length > 1 ? s[1] : 0;
        return (minor >= 9 ? new StringBuilder().append(major + 1).append(".0") : new StringBuilder().append(major).append(".").append(minor + 1)).toString();
    }
}
