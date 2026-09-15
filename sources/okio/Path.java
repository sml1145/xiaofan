package okio;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Path.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 .2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001.B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0000H\u0096\u0002J\u0016\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\rH\u0087\u0002¢\u0006\u0002\b\"J\u0016\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0003H\u0087\u0002¢\u0006\u0002\b\"J\u0016\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0000H\u0087\u0002¢\u0006\u0002\b\"J\u0013\u0010#\u001a\u00020\b2\b\u0010\u001f\u001a\u0004\u0018\u00010$H\u0096\u0002J\b\u0010%\u001a\u00020\u001eH\u0016J\u0006\u0010&\u001a\u00020\u0000J\u000e\u0010'\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u0000J\u0018\u0010\"\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\r2\b\b\u0002\u0010(\u001a\u00020\bJ\u0018\u0010\"\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u00032\b\b\u0002\u0010(\u001a\u00020\bJ\u0018\u0010\"\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u00002\b\b\u0002\u0010(\u001a\u00020\bJ\u0006\u0010)\u001a\u00020*J\u0006\u0010+\u001a\u00020,J\b\u0010-\u001a\u00020\rH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\tR\u0011\u0010\n\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\n\u0010\tR\u0011\u0010\u000b\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\r8G¢\u0006\u0006\u001a\u0004\b\f\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u00038G¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0006R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u00008G¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u00008F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u00158F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0017R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u001b8G¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001c¨\u0006/"}, d2 = {"Lokio/Path;", "", "bytes", "Lokio/ByteString;", "(Lokio/ByteString;)V", "getBytes$okio", "()Lokio/ByteString;", "isAbsolute", "", "()Z", "isRelative", "isRoot", "name", "", "()Ljava/lang/String;", "nameBytes", "parent", "()Lokio/Path;", "root", "getRoot", "segments", "", "getSegments", "()Ljava/util/List;", "segmentsBytes", "getSegmentsBytes", "volumeLetter", "", "()Ljava/lang/Character;", "compareTo", "", "other", "div", "child", "resolve", "equals", "", "hashCode", "normalized", "relativeTo", "normalize", "toFile", "Ljava/io/File;", "toNioPath", "Ljava/nio/file/Path;", "toString", "Companion", "okio"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class Path implements Comparable<Path> {
    public static final Companion Companion = new Companion(null);
    public static final String DIRECTORY_SEPARATOR;
    private final ByteString bytes;

    @JvmStatic
    public static final Path get(File file) {
        return Companion.get(file);
    }

    @JvmStatic
    public static final Path get(File file, boolean z) {
        return Companion.get(file, z);
    }

    @JvmStatic
    public static final Path get(String str) {
        return Companion.get(str);
    }

    @JvmStatic
    public static final Path get(String str, boolean z) {
        return Companion.get(str, z);
    }

    @JvmStatic
    public static final Path get(java.nio.file.Path path) {
        return Companion.get(path);
    }

    @JvmStatic
    public static final Path get(java.nio.file.Path path, boolean z) {
        return Companion.get(path, z);
    }

    public Path(ByteString bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        this.bytes = bytes;
    }

    public final ByteString getBytes$okio() {
        return this.bytes;
    }

    public final Path getRoot() {
        int rootLength$iv = okio.internal.Path.access$rootLength(this);
        if (rootLength$iv == -1) {
            return null;
        }
        return new Path(getBytes$okio().substring(0, rootLength$iv));
    }

    public final List<String> getSegments() {
        Iterable result$iv$iv = (List) new ArrayList();
        int segmentStart$iv$iv = okio.internal.Path.access$rootLength(this);
        if (segmentStart$iv$iv == -1) {
            segmentStart$iv$iv = 0;
        } else if (segmentStart$iv$iv < getBytes$okio().size() && getBytes$okio().getByte(segmentStart$iv$iv) == 92) {
            segmentStart$iv$iv++;
        }
        int size = getBytes$okio().size();
        for (int i$iv$iv = segmentStart$iv$iv; i$iv$iv < size; i$iv$iv++) {
            if (getBytes$okio().getByte(i$iv$iv) == 47 || getBytes$okio().getByte(i$iv$iv) == 92) {
                ((Collection) result$iv$iv).add(getBytes$okio().substring(segmentStart$iv$iv, i$iv$iv));
                segmentStart$iv$iv = i$iv$iv + 1;
            }
        }
        if (segmentStart$iv$iv < getBytes$okio().size()) {
            ((Collection) result$iv$iv).add(getBytes$okio().substring(segmentStart$iv$iv, getBytes$okio().size()));
        }
        Iterable $this$map$iv$iv = result$iv$iv;
        Collection destination$iv$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv$iv, 10));
        for (Object item$iv$iv$iv : $this$map$iv$iv) {
            ByteString it$iv = (ByteString) item$iv$iv$iv;
            destination$iv$iv$iv.add(it$iv.utf8());
        }
        return (List) destination$iv$iv$iv;
    }

    public final List<ByteString> getSegmentsBytes() {
        List result$iv = new ArrayList();
        int segmentStart$iv = okio.internal.Path.access$rootLength(this);
        if (segmentStart$iv == -1) {
            segmentStart$iv = 0;
        } else if (segmentStart$iv < getBytes$okio().size() && getBytes$okio().getByte(segmentStart$iv) == 92) {
            segmentStart$iv++;
        }
        int size = getBytes$okio().size();
        for (int i$iv = segmentStart$iv; i$iv < size; i$iv++) {
            if (getBytes$okio().getByte(i$iv) == 47 || getBytes$okio().getByte(i$iv) == 92) {
                result$iv.add(getBytes$okio().substring(segmentStart$iv, i$iv));
                segmentStart$iv = i$iv + 1;
            }
        }
        if (segmentStart$iv < getBytes$okio().size()) {
            result$iv.add(getBytes$okio().substring(segmentStart$iv, getBytes$okio().size()));
        }
        return result$iv;
    }

    public final boolean isAbsolute() {
        return okio.internal.Path.access$rootLength(this) != -1;
    }

    public final boolean isRelative() {
        return okio.internal.Path.access$rootLength(this) == -1;
    }

    public final Character volumeLetter() {
        boolean z = false;
        if (ByteString.indexOf$default(getBytes$okio(), okio.internal.Path.access$getSLASH$p(), 0, 2, (Object) null) == -1 && getBytes$okio().size() >= 2 && getBytes$okio().getByte(1) == 58) {
            char c$iv = (char) getBytes$okio().getByte(0);
            if (!('a' <= c$iv && c$iv < '{')) {
                if ('A' <= c$iv && c$iv < '[') {
                    z = true;
                }
                if (!z) {
                    return null;
                }
            }
            return Character.valueOf(c$iv);
        }
        return null;
    }

    public final ByteString nameBytes() {
        int lastSlash$iv = okio.internal.Path.access$getIndexOfLastSlash(this);
        if (lastSlash$iv != -1) {
            return ByteString.substring$default(getBytes$okio(), lastSlash$iv + 1, 0, 2, null);
        }
        return (volumeLetter() == null || getBytes$okio().size() != 2) ? getBytes$okio() : ByteString.EMPTY;
    }

    public final String name() {
        return nameBytes().utf8();
    }

    public final Path parent() {
        if (Intrinsics.areEqual(getBytes$okio(), okio.internal.Path.access$getDOT$p()) || Intrinsics.areEqual(getBytes$okio(), okio.internal.Path.access$getSLASH$p()) || Intrinsics.areEqual(getBytes$okio(), okio.internal.Path.access$getBACKSLASH$p()) || okio.internal.Path.access$lastSegmentIsDotDot(this)) {
            return null;
        }
        int lastSlash$iv = okio.internal.Path.access$getIndexOfLastSlash(this);
        if (lastSlash$iv == 2 && volumeLetter() != null) {
            if (getBytes$okio().size() == 3) {
                return null;
            }
            return new Path(ByteString.substring$default(getBytes$okio(), 0, 3, 1, null));
        } else if (lastSlash$iv == 1 && getBytes$okio().startsWith(okio.internal.Path.access$getBACKSLASH$p())) {
            return null;
        } else {
            if (lastSlash$iv == -1 && volumeLetter() != null) {
                if (getBytes$okio().size() == 2) {
                    return null;
                }
                return new Path(ByteString.substring$default(getBytes$okio(), 0, 2, 1, null));
            } else if (lastSlash$iv == -1) {
                return new Path(okio.internal.Path.access$getDOT$p());
            } else {
                if (lastSlash$iv == 0) {
                    return new Path(ByteString.substring$default(getBytes$okio(), 0, 1, 1, null));
                }
                return new Path(ByteString.substring$default(getBytes$okio(), 0, lastSlash$iv, 1, null));
            }
        }
    }

    public final boolean isRoot() {
        return okio.internal.Path.access$rootLength(this) == getBytes$okio().size();
    }

    public final Path resolve(String child) {
        Intrinsics.checkNotNullParameter(child, "child");
        Buffer child$iv$iv = new Buffer().writeUtf8(child);
        return okio.internal.Path.commonResolve(this, okio.internal.Path.toPath(child$iv$iv, false), false);
    }

    public final Path resolve(ByteString child) {
        Intrinsics.checkNotNullParameter(child, "child");
        Buffer child$iv$iv = new Buffer().write(child);
        return okio.internal.Path.commonResolve(this, okio.internal.Path.toPath(child$iv$iv, false), false);
    }

    public final Path resolve(Path child) {
        Intrinsics.checkNotNullParameter(child, "child");
        return okio.internal.Path.commonResolve(this, child, false);
    }

    public static /* synthetic */ Path resolve$default(Path path, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return path.resolve(str, z);
    }

    public final Path resolve(String child, boolean normalize) {
        Intrinsics.checkNotNullParameter(child, "child");
        Buffer child$iv$iv = new Buffer().writeUtf8(child);
        return okio.internal.Path.commonResolve(this, okio.internal.Path.toPath(child$iv$iv, false), normalize);
    }

    public static /* synthetic */ Path resolve$default(Path path, ByteString byteString, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return path.resolve(byteString, z);
    }

    public final Path resolve(ByteString child, boolean normalize) {
        Intrinsics.checkNotNullParameter(child, "child");
        Buffer child$iv$iv = new Buffer().write(child);
        return okio.internal.Path.commonResolve(this, okio.internal.Path.toPath(child$iv$iv, false), normalize);
    }

    public static /* synthetic */ Path resolve$default(Path path, Path path2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return path.resolve(path2, z);
    }

    public final Path resolve(Path child, boolean normalize) {
        Intrinsics.checkNotNullParameter(child, "child");
        return okio.internal.Path.commonResolve(this, child, normalize);
    }

    public final Path relativeTo(Path other) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (!Intrinsics.areEqual(getRoot(), other.getRoot())) {
            throw new IllegalArgumentException(("Paths of different roots cannot be relative to each other: " + this + " and " + other).toString());
        }
        List thisSegments$iv = getSegmentsBytes();
        List otherSegments$iv = other.getSegmentsBytes();
        int firstNewSegmentIndex$iv = 0;
        int minSegmentsSize$iv = Math.min(thisSegments$iv.size(), otherSegments$iv.size());
        while (firstNewSegmentIndex$iv < minSegmentsSize$iv && Intrinsics.areEqual(thisSegments$iv.get(firstNewSegmentIndex$iv), otherSegments$iv.get(firstNewSegmentIndex$iv))) {
            firstNewSegmentIndex$iv++;
        }
        if (firstNewSegmentIndex$iv == minSegmentsSize$iv && getBytes$okio().size() == other.getBytes$okio().size()) {
            return Companion.get$default(Companion, ".", false, 1, (Object) null);
        }
        if (!(otherSegments$iv.subList(firstNewSegmentIndex$iv, otherSegments$iv.size()).indexOf(okio.internal.Path.access$getDOT_DOT$p()) == -1)) {
            throw new IllegalArgumentException(("Impossible relative path to resolve: " + this + " and " + other).toString());
        }
        Buffer buffer$iv = new Buffer();
        ByteString slash$iv = okio.internal.Path.access$getSlash(other);
        if (slash$iv == null && (slash$iv = okio.internal.Path.access$getSlash(this)) == null) {
            slash$iv = okio.internal.Path.access$toSlash(DIRECTORY_SEPARATOR);
        }
        int size = otherSegments$iv.size();
        for (int i$iv = firstNewSegmentIndex$iv; i$iv < size; i$iv++) {
            buffer$iv.write(okio.internal.Path.access$getDOT_DOT$p());
            buffer$iv.write(slash$iv);
        }
        int size2 = thisSegments$iv.size();
        for (int i$iv2 = firstNewSegmentIndex$iv; i$iv2 < size2; i$iv2++) {
            buffer$iv.write(thisSegments$iv.get(i$iv2));
            buffer$iv.write(slash$iv);
        }
        return okio.internal.Path.toPath(buffer$iv, false);
    }

    public final Path normalized() {
        Path $this$commonNormalized$iv = Companion.get(toString(), true);
        return $this$commonNormalized$iv;
    }

    public final File toFile() {
        return new File(toString());
    }

    public final java.nio.file.Path toNioPath() {
        java.nio.file.Path path = Paths.get(toString(), new String[0]);
        Intrinsics.checkNotNullExpressionValue(path, "get(...)");
        return path;
    }

    @Override // java.lang.Comparable
    public int compareTo(Path other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return getBytes$okio().compareTo(other.getBytes$okio());
    }

    public boolean equals(Object other) {
        return (other instanceof Path) && Intrinsics.areEqual(((Path) other).getBytes$okio(), getBytes$okio());
    }

    public int hashCode() {
        return getBytes$okio().hashCode();
    }

    public String toString() {
        return getBytes$okio().utf8();
    }

    /* compiled from: Path.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001b\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\b\nJ\u001b\u0010\u0005\u001a\u00020\u0006*\u00020\u000b2\b\b\u0002\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\b\nJ\u001b\u0010\f\u001a\u00020\u0006*\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\b\nR\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lokio/Path$Companion;", "", "()V", "DIRECTORY_SEPARATOR", "", "toOkioPath", "Lokio/Path;", "Ljava/io/File;", "normalize", "", "get", "Ljava/nio/file/Path;", "toPath", "okio"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final Path get(File file) {
            Intrinsics.checkNotNullParameter(file, "<this>");
            return get$default(this, file, false, 1, (Object) null);
        }

        @JvmStatic
        public final Path get(String str) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            return get$default(this, str, false, 1, (Object) null);
        }

        @JvmStatic
        public final Path get(java.nio.file.Path path) {
            Intrinsics.checkNotNullParameter(path, "<this>");
            return get$default(this, path, false, 1, (Object) null);
        }

        private Companion() {
        }

        public static /* synthetic */ Path get$default(Companion companion, String str, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return companion.get(str, z);
        }

        @JvmStatic
        public final Path get(String $this$toPath, boolean normalize) {
            Intrinsics.checkNotNullParameter($this$toPath, "<this>");
            return okio.internal.Path.commonToPath($this$toPath, normalize);
        }

        public static /* synthetic */ Path get$default(Companion companion, File file, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return companion.get(file, z);
        }

        @JvmStatic
        public final Path get(File $this$toOkioPath, boolean normalize) {
            Intrinsics.checkNotNullParameter($this$toOkioPath, "<this>");
            String file = $this$toOkioPath.toString();
            Intrinsics.checkNotNullExpressionValue(file, "toString(...)");
            return get(file, normalize);
        }

        public static /* synthetic */ Path get$default(Companion companion, java.nio.file.Path path, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return companion.get(path, z);
        }

        @JvmStatic
        public final Path get(java.nio.file.Path $this$toOkioPath, boolean normalize) {
            Intrinsics.checkNotNullParameter($this$toOkioPath, "<this>");
            return get($this$toOkioPath.toString(), normalize);
        }
    }

    static {
        String separator = File.separator;
        Intrinsics.checkNotNullExpressionValue(separator, "separator");
        DIRECTORY_SEPARATOR = separator;
    }
}
