package com.xiaofan.bangfan;

import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: ScreenSnapshot.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\u0018\u0000 '2\u00020\u0001:\u0003'()B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001d\u001a\u00020\tJ\u0006\u0010\u001e\u001a\u00020\u000fJ!\u0010\u001f\u001a\u0004\u0018\u00010 2\u0012\u0010!\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\"\"\u00020\t¢\u0006\u0002\u0010#J\u0006\u0010$\u001a\u00020%J\u0006\u0010&\u001a\u00020%R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0007R\u001a\u0010\u001a\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u000b\"\u0004\b\u001c\u0010\r¨\u0006*"}, d2 = {"Lcom/xiaofan/bangfan/ScreenSnapshot;", "", "()V", "images", "", "Lcom/xiaofan/bangfan/ScreenSnapshot$ImageItem;", "getImages", "()Ljava/util/List;", "packageName", "", "getPackageName", "()Ljava/lang/String;", "setPackageName", "(Ljava/lang/String;)V", "screenHeight", "", "getScreenHeight", "()I", "setScreenHeight", "(I)V", "screenWidth", "getScreenWidth", "setScreenWidth", "texts", "Lcom/xiaofan/bangfan/ScreenSnapshot$TextItem;", "getTexts", "windowTitle", "getWindowTitle", "setWindowTitle", "allText", "bodyCharCount", "findNode", "Landroid/graphics/Rect;", "keywords", "", "([Ljava/lang/String;)Landroid/graphics/Rect;", "imageCoverageRatio", "", "textCoverageRatio", "Companion", "ImageItem", "TextItem", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class ScreenSnapshot {
    public static final Companion Companion = new Companion(null);
    private int screenHeight;
    private int screenWidth;
    private final List<TextItem> texts = new ArrayList();
    private final List<ImageItem> images = new ArrayList();
    private String packageName = "";
    private String windowTitle = "";

    public final int getScreenWidth() {
        return this.screenWidth;
    }

    public final void setScreenWidth(int i) {
        this.screenWidth = i;
    }

    public final int getScreenHeight() {
        return this.screenHeight;
    }

    public final void setScreenHeight(int i) {
        this.screenHeight = i;
    }

    public final List<TextItem> getTexts() {
        return this.texts;
    }

    public final List<ImageItem> getImages() {
        return this.images;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final void setPackageName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.packageName = str;
    }

    public final String getWindowTitle() {
        return this.windowTitle;
    }

    public final void setWindowTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.windowTitle = str;
    }

    /* compiled from: ScreenSnapshot.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tJ\u0006\u0010\u0018\u001a\u00020\u0019R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015¨\u0006\u001a"}, d2 = {"Lcom/xiaofan/bangfan/ScreenSnapshot$TextItem;", "", "text", "", "bounds", "Landroid/graphics/Rect;", "clickable", "", "viewId", "(Ljava/lang/String;Landroid/graphics/Rect;ZLjava/lang/String;)V", "getBounds", "()Landroid/graphics/Rect;", "setBounds", "(Landroid/graphics/Rect;)V", "getClickable", "()Z", "setClickable", "(Z)V", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "getViewId", "setViewId", "area", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class TextItem {
        private Rect bounds;
        private boolean clickable;
        private String text;
        private String viewId;

        public TextItem(String text, Rect bounds, boolean clickable, String viewId) {
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(bounds, "bounds");
            this.text = text;
            this.bounds = bounds;
            this.clickable = clickable;
            this.viewId = viewId;
        }

        public final String getText() {
            return this.text;
        }

        public final void setText(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.text = str;
        }

        public final Rect getBounds() {
            return this.bounds;
        }

        public final void setBounds(Rect rect) {
            Intrinsics.checkNotNullParameter(rect, "<set-?>");
            this.bounds = rect;
        }

        public final boolean getClickable() {
            return this.clickable;
        }

        public final void setClickable(boolean z) {
            this.clickable = z;
        }

        public final String getViewId() {
            return this.viewId;
        }

        public final void setViewId(String str) {
            this.viewId = str;
        }

        public final int area() {
            return Math.max(0, this.bounds.width()) * Math.max(0, this.bounds.height());
        }
    }

    /* compiled from: ScreenSnapshot.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\u0006\u0010\u0018\u001a\u00020\u0019R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015¨\u0006\u001a"}, d2 = {"Lcom/xiaofan/bangfan/ScreenSnapshot$ImageItem;", "", "bounds", "Landroid/graphics/Rect;", "viewId", "", "clickable", "", "desc", "(Landroid/graphics/Rect;Ljava/lang/String;ZLjava/lang/String;)V", "getBounds", "()Landroid/graphics/Rect;", "setBounds", "(Landroid/graphics/Rect;)V", "getClickable", "()Z", "setClickable", "(Z)V", "getDesc", "()Ljava/lang/String;", "setDesc", "(Ljava/lang/String;)V", "getViewId", "setViewId", "area", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class ImageItem {
        private Rect bounds;
        private boolean clickable;
        private String desc;
        private String viewId;

        public ImageItem(Rect bounds, String viewId, boolean clickable, String desc) {
            Intrinsics.checkNotNullParameter(bounds, "bounds");
            Intrinsics.checkNotNullParameter(desc, "desc");
            this.bounds = bounds;
            this.viewId = viewId;
            this.clickable = clickable;
            this.desc = desc;
        }

        public final Rect getBounds() {
            return this.bounds;
        }

        public final void setBounds(Rect rect) {
            Intrinsics.checkNotNullParameter(rect, "<set-?>");
            this.bounds = rect;
        }

        public final String getViewId() {
            return this.viewId;
        }

        public final void setViewId(String str) {
            this.viewId = str;
        }

        public final boolean getClickable() {
            return this.clickable;
        }

        public final void setClickable(boolean z) {
            this.clickable = z;
        }

        public final String getDesc() {
            return this.desc;
        }

        public final void setDesc(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.desc = str;
        }

        public final int area() {
            return Math.max(0, this.bounds.width()) * Math.max(0, this.bounds.height());
        }
    }

    public final float textCoverageRatio() {
        if (this.screenWidth <= 0 || this.screenHeight <= 0) {
            return 0.0f;
        }
        long total = this.screenWidth * this.screenHeight;
        long covered = 0;
        for (TextItem t : this.texts) {
            covered += t.area();
        }
        if (total == 0) {
            return 0.0f;
        }
        return ((float) covered) / ((float) total);
    }

    public final float imageCoverageRatio() {
        if (this.screenWidth <= 0 || this.screenHeight <= 0) {
            return 0.0f;
        }
        long total = this.screenWidth * this.screenHeight;
        long covered = 0;
        for (ImageItem img : this.images) {
            covered += img.area();
        }
        if (total == 0) {
            return 0.0f;
        }
        return ((float) covered) / ((float) total);
    }

    public final int bodyCharCount() {
        int count = 0;
        for (TextItem t : this.texts) {
            if (t.getText().length() >= 2) {
                count += t.getText().length();
            }
        }
        return count;
    }

    public final String allText() {
        StringBuilder sb = new StringBuilder();
        for (TextItem t : this.texts) {
            sb.append(t.getText()).append('\n');
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    public final Rect findNode(String... keywords) {
        Intrinsics.checkNotNullParameter(keywords, "keywords");
        for (TextItem t : this.texts) {
            for (String kw : keywords) {
                if ((kw.length() > 0) && StringsKt.contains$default((CharSequence) t.getText(), (CharSequence) kw, false, 2, (Object) null)) {
                    return t.getBounds();
                }
            }
        }
        for (ImageItem img : this.images) {
            for (String kw2 : keywords) {
                if ((kw2.length() > 0) && StringsKt.contains$default((CharSequence) img.getDesc(), (CharSequence) kw2, false, 2, (Object) null)) {
                    return img.getBounds();
                }
            }
        }
        return null;
    }

    /* compiled from: ScreenSnapshot.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\"\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\bH\u0002¨\u0006\u0011"}, d2 = {"Lcom/xiaofan/bangfan/ScreenSnapshot$Companion;", "", "()V", "capture", "Lcom/xiaofan/bangfan/ScreenSnapshot;", "root", "Landroid/view/accessibility/AccessibilityNodeInfo;", "width", "", "height", "pkg", "", "walk", "", "node", "snapshot", "depth", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ScreenSnapshot capture(AccessibilityNodeInfo root, int width, int height, String pkg) {
            ScreenSnapshot snapshot = new ScreenSnapshot();
            snapshot.setScreenWidth(width);
            snapshot.setScreenHeight(height);
            snapshot.setPackageName(pkg == null ? "" : pkg);
            if (root != null) {
                walk(root, snapshot, 0);
                CharSequence text = root.getText();
                if (text != null) {
                    snapshot.setWindowTitle(text.toString());
                }
            }
            return snapshot;
        }

        private final void walk(AccessibilityNodeInfo node, ScreenSnapshot snapshot, int depth) {
            String str;
            boolean z;
            String str2;
            AccessibilityNodeInfo accessibilityNodeInfo;
            if (node != null && depth <= 40) {
                Rect bounds = new Rect();
                node.getBoundsInScreen(bounds);
                try {
                    str = node.getViewIdResourceName();
                } catch (Exception e) {
                    str = null;
                }
                String viewId = str;
                try {
                    z = node.isClickable();
                } catch (Exception e2) {
                    z = false;
                }
                boolean clickable = z;
                CharSequence text = node.getText();
                CharSequence contentDesc = node.getContentDescription();
                boolean isZeroWidth = bounds.width() <= 0 && bounds.height() > 0;
                if (text != null) {
                    if (text.length() > 0) {
                        snapshot.getTexts().add(new TextItem(text.toString(), bounds, clickable, viewId));
                        if ((snapshot.getWindowTitle().length() == 0) && bounds.top <= snapshot.getScreenHeight() * 0.15f && bounds.height() < snapshot.getScreenHeight() * 0.12f) {
                            snapshot.setWindowTitle(text.toString());
                        }
                    }
                }
                try {
                    CharSequence className = node.getClassName();
                    str2 = className != null ? className.toString() : null;
                } catch (Exception e3) {
                    str2 = null;
                }
                String className2 = str2;
                if (className2 != null) {
                    boolean isImage = (!StringsKt.contains$default((CharSequence) className2, (CharSequence) "ImageView", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) className2, (CharSequence) "TextureView", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) className2, (CharSequence) "SurfaceView", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) className2, (CharSequence) "VideoView", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) className2, (CharSequence) "WebView", false, 2, (Object) null)) ? false : true;
                    if (isImage && isZeroWidth) {
                        snapshot.getImages().add(new ImageItem(bounds, viewId, clickable, (contentDesc == null || (r14 = contentDesc.toString()) == null) ? "" : ""));
                    } else if (contentDesc != null) {
                        if ((contentDesc.length() > 0) && isZeroWidth && text == null) {
                            snapshot.getImages().add(new ImageItem(bounds, viewId, clickable, contentDesc.toString()));
                        }
                    }
                }
                int childCount = node.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    try {
                        accessibilityNodeInfo = node.getChild(i);
                    } catch (Exception e4) {
                        accessibilityNodeInfo = null;
                    }
                    AccessibilityNodeInfo child = accessibilityNodeInfo;
                    if (child != null) {
                        walk(child, snapshot, depth + 1);
                        try {
                            child.recycle();
                        } catch (Exception e5) {
                        }
                    }
                }
            }
        }
    }
}
