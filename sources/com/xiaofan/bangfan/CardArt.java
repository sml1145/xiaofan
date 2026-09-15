package com.xiaofan.bangfan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
/* compiled from: PokerCardView.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005R*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/xiaofan/bangfan/CardArt;", "", "()V", "cache", "Ljava/util/HashMap;", "", "Landroid/graphics/Bitmap;", "Lkotlin/collections/HashMap;", "bitmap", "ctx", "Landroid/content/Context;", "res", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class CardArt {
    public static final CardArt INSTANCE = new CardArt();
    private static final HashMap<Integer, Bitmap> cache = new HashMap<>();

    private CardArt() {
    }

    public final Bitmap bitmap(Context ctx, int res) {
        Bitmap it;
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Bitmap it2 = cache.get(Integer.valueOf(res));
        if (it2 != null) {
            return it2;
        }
        try {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bmp = BitmapFactory.decodeResource(ctx.getResources(), res, opts);
            if (bmp != null && (bmp.getWidth() > 420 || bmp.getHeight() > 420)) {
                float k = 420 / Math.max(bmp.getWidth(), bmp.getHeight());
                it = Bitmap.createScaledBitmap(bmp, RangesKt.coerceAtLeast((int) (bmp.getWidth() * k), 1), RangesKt.coerceAtLeast((int) (bmp.getHeight() * k), 1), true);
                if (!Intrinsics.areEqual(it, bmp)) {
                    bmp.recycle();
                }
            } else {
                it = bmp;
            }
            Bitmap scaled = it;
            if (scaled != null) {
                cache.put(Integer.valueOf(res), scaled);
                return scaled;
            }
            return scaled;
        } catch (Throwable th) {
            return null;
        }
    }
}
