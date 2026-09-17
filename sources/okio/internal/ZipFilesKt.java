package okio.internal;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.XiaoFanBrain;
import java.io.IOException;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.UByte;
import kotlin.UShort;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import okhttp3.internal.ws.WebSocketProtocol;
import okio.BufferedSource;
import okio.FileMetadata;
import okio.FileSystem;
import okio.Path;
import okio.ZipFileSystem;
/* compiled from: ZipFiles.kt */
@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\"\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u00132\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0017H\u0002\u001a\u001f\u0010\u0018\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u0001H\u0002¢\u0006\u0002\u0010\u001b\u001a.\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020 2\u0014\b\u0002\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020#0\"H\u0000\u001a\f\u0010$\u001a\u00020\u0015*\u00020%H\u0000\u001a\f\u0010&\u001a\u00020'*\u00020%H\u0002\u001a.\u0010(\u001a\u00020)*\u00020%2\u0006\u0010*\u001a\u00020\u00012\u0018\u0010+\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020)0,H\u0002\u001a\u0014\u0010-\u001a\u00020.*\u00020%2\u0006\u0010/\u001a\u00020.H\u0000\u001a\u0018\u00100\u001a\u0004\u0018\u00010.*\u00020%2\b\u0010/\u001a\u0004\u0018\u00010.H\u0002\u001a\u0014\u00101\u001a\u00020'*\u00020%2\u0006\u00102\u001a\u00020'H\u0002\u001a\f\u00103\u001a\u00020)*\u00020%H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0018\u0010\u000e\u001a\u00020\u000f*\u00020\u00018BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u00064"}, d2 = {"BIT_FLAG_ENCRYPTED", "", "BIT_FLAG_UNSUPPORTED_MASK", "CENTRAL_FILE_HEADER_SIGNATURE", "COMPRESSION_METHOD_DEFLATED", "COMPRESSION_METHOD_STORED", "END_OF_CENTRAL_DIRECTORY_SIGNATURE", "HEADER_ID_EXTENDED_TIMESTAMP", "HEADER_ID_ZIP64_EXTENDED_INFO", "LOCAL_FILE_HEADER_SIGNATURE", "MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE", "", "ZIP64_EOCD_RECORD_SIGNATURE", "ZIP64_LOCATOR_SIGNATURE", "hex", "", "getHex", "(I)Ljava/lang/String;", "buildIndex", "", "Lokio/Path;", "Lokio/internal/ZipEntry;", "entries", "", "dosDateTimeToEpochMillis", XiaoFanBrain.INTENT_DATE, "time", "(II)Ljava/lang/Long;", "openZip", "Lokio/ZipFileSystem;", "zipPath", "fileSystem", "Lokio/FileSystem;", "predicate", "Lkotlin/Function1;", "", "readEntry", "Lokio/BufferedSource;", "readEocdRecord", "Lokio/internal/EocdRecord;", "readExtra", "", "extraSize", "block", "Lkotlin/Function2;", "readLocalHeader", "Lokio/FileMetadata;", "basicMetadata", "readOrSkipLocalHeader", "readZip64EocdRecord", "regularRecord", "skipLocalHeader", "okio"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class ZipFilesKt {
    private static final int BIT_FLAG_ENCRYPTED = 1;
    private static final int BIT_FLAG_UNSUPPORTED_MASK = 1;
    private static final int CENTRAL_FILE_HEADER_SIGNATURE = 33639248;
    public static final int COMPRESSION_METHOD_DEFLATED = 8;
    public static final int COMPRESSION_METHOD_STORED = 0;
    private static final int END_OF_CENTRAL_DIRECTORY_SIGNATURE = 101010256;
    private static final int HEADER_ID_EXTENDED_TIMESTAMP = 21589;
    private static final int HEADER_ID_ZIP64_EXTENDED_INFO = 1;
    private static final int LOCAL_FILE_HEADER_SIGNATURE = 67324752;
    private static final long MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE = 4294967295L;
    private static final int ZIP64_EOCD_RECORD_SIGNATURE = 101075792;
    private static final int ZIP64_LOCATOR_SIGNATURE = 117853008;

    public static /* synthetic */ ZipFileSystem openZip$default(Path path, FileSystem fileSystem, Function1 function1, int i, Object obj) throws IOException {
        if ((i & 4) != 0) {
            function1 = new Function1<ZipEntry, Boolean>() { // from class: okio.internal.ZipFilesKt$openZip$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(ZipEntry it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return true;
                }
            };
        }
        return openZip(path, fileSystem, function1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:159:0x0054, code lost:
        r13 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x0055, code lost:
        r15 = readEocdRecord(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x005e, code lost:
        r6 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x0063, code lost:
        r0 = r6.readUtf8(r15.getCommentByteCount());
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x0069, code lost:
        r6.close();
        r10 = r13 - 20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x0077, code lost:
        if (r10 <= 0) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0079, code lost:
        r12 = okio.Okio.buffer(r0.source(r10));
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x0084, code lost:
        r0 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x0092, code lost:
        if (r0.readIntLe() != okio.internal.ZipFilesKt.ZIP64_LOCATOR_SIGNATURE) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x0094, code lost:
        r0 = r0.readIntLe();
        r23 = r0.readLongLe();
        r0 = r0.readIntLe();
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x00aa, code lost:
        if (r0 != 1) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x00ac, code lost:
        if (r0 != 0) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x00b2, code lost:
        r6 = okio.Okio.buffer(r0.source(r23));
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x00bf, code lost:
        r0 = r6;
        r27 = r0.readIntLe();
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x00d1, code lost:
        if (r27 != okio.internal.ZipFilesKt.ZIP64_EOCD_RECORD_SIGNATURE) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x00d3, code lost:
        r15 = readZip64EocdRecord(r0, r15);
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x00dc, code lost:
        kotlin.io.CloseableKt.closeFinally(r6, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x00e3, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x00e4, code lost:
        r6 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x00e9, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x00ea, code lost:
        r10 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x011f, code lost:
        throw new java.io.IOException("bad zip: expected " + getHex(okio.internal.ZipFilesKt.ZIP64_EOCD_RECORD_SIGNATURE) + " but was " + getHex(r27));
     */
    /* JADX WARN: Code restructure failed: missing block: B:190:0x0120, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:191:0x0121, code lost:
        r10 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x0123, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x0124, code lost:
        r10 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:194:0x0128, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x0129, code lost:
        r10 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x012e, code lost:
        throw r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:201:0x0135, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x0136, code lost:
        r6 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:204:0x014a, code lost:
        throw new java.io.IOException("unsupported zip: spanned");
     */
    /* JADX WARN: Code restructure failed: missing block: B:206:0x014f, code lost:
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:208:0x0153, code lost:
        kotlin.io.CloseableKt.closeFinally(r12, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:212:0x015a, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:213:0x015b, code lost:
        r6 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:214:0x0160, code lost:
        throw r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:220:0x016b, code lost:
        r0 = new java.util.ArrayList();
        r10 = okio.Okio.buffer(r0.source(r15.getCentralDirectoryOffset()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:221:0x0182, code lost:
        r0 = r10;
        r12 = 0;
        r16 = r15.getEntryCount();
     */
    /* JADX WARN: Code restructure failed: missing block: B:223:0x018e, code lost:
        if (r12 >= r16) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:224:0x0190, code lost:
        r14 = readEntry(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:225:0x019e, code lost:
        if (r14.getOffset() >= r15.getCentralDirectoryOffset()) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:227:0x01aa, code lost:
        if (r3.invoke(r14).booleanValue() == false) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:228:0x01ac, code lost:
        r19 = r0;
        r0.add(r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:229:0x01b5, code lost:
        r19 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:230:0x01b7, code lost:
        r12 = r12 + 1;
        r0 = r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:232:0x01c7, code lost:
        throw new java.io.IOException("bad zip: local file header offset >= central directory offset");
     */
    /* JADX WARN: Code restructure failed: missing block: B:233:0x01c8, code lost:
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:235:0x01ce, code lost:
        kotlin.io.CloseableKt.closeFinally(r10, null);
        r0 = buildIndex(r0);
        r3 = new okio.ZipFileSystem(r33, r34, r0, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:236:0x01da, code lost:
        kotlin.io.CloseableKt.closeFinally(r4, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:237:0x01de, code lost:
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:245:0x01e8, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:247:0x01ee, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:0x01ef, code lost:
        r6 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:256:0x0224, code lost:
        r6.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:257:0x0227, code lost:
        throw r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final okio.ZipFileSystem openZip(okio.Path r33, okio.FileSystem r34, kotlin.jvm.functions.Function1<? super okio.internal.ZipEntry, java.lang.Boolean> r35) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 592
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.ZipFilesKt.openZip(okio.Path, okio.FileSystem, kotlin.jvm.functions.Function1):okio.ZipFileSystem");
    }

    private static final Map<Path, ZipEntry> buildIndex(List<ZipEntry> list) {
        Path root = Path.Companion.get$default(Path.Companion, "/", false, 1, (Object) null);
        Map result = MapsKt.mutableMapOf(TuplesKt.to(root, new ZipEntry(root, true, null, 0L, 0L, 0L, 0, null, 0L, 508, null)));
        List<ZipEntry> $this$sortedBy$iv = list;
        for (ZipEntry entry : CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: okio.internal.ZipFilesKt$buildIndex$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                ZipEntry it = (ZipEntry) t;
                ZipEntry it2 = (ZipEntry) t2;
                return ComparisonsKt.compareValues(it.getCanonicalPath(), it2.getCanonicalPath());
            }
        })) {
            ZipEntry replaced = result.put(entry.getCanonicalPath(), entry);
            if (replaced == null) {
                ZipEntry child = entry;
                while (true) {
                    Path parentPath = child.getCanonicalPath().parent();
                    if (parentPath != null) {
                        ZipEntry parentEntry = result.get(parentPath);
                        if (parentEntry != null) {
                            parentEntry.getChildren().add(child.getCanonicalPath());
                            break;
                        }
                        ZipEntry parentEntry2 = new ZipEntry(parentPath, true, null, 0L, 0L, 0L, 0, null, 0L, 508, null);
                        result.put(parentPath, parentEntry2);
                        parentEntry2.getChildren().add(child.getCanonicalPath());
                        child = parentEntry2;
                    }
                }
            }
        }
        return result;
    }

    public static final ZipEntry readEntry(final BufferedSource $this$readEntry) throws IOException {
        Ref.LongRef offset;
        Intrinsics.checkNotNullParameter($this$readEntry, "<this>");
        int signature = $this$readEntry.readIntLe();
        if (signature != CENTRAL_FILE_HEADER_SIGNATURE) {
            throw new IOException("bad zip: expected " + getHex(CENTRAL_FILE_HEADER_SIGNATURE) + " but was " + getHex(signature));
        }
        $this$readEntry.skip(4L);
        int bitFlag = $this$readEntry.readShortLe() & UShort.MAX_VALUE;
        if ((bitFlag & 1) != 0) {
            throw new IOException("unsupported zip: general purpose bit flag=" + getHex(bitFlag));
        }
        int compressionMethod = $this$readEntry.readShortLe() & UShort.MAX_VALUE;
        int time = $this$readEntry.readShortLe() & UShort.MAX_VALUE;
        int date = $this$readEntry.readShortLe() & UShort.MAX_VALUE;
        Long lastModifiedAtMillis = dosDateTimeToEpochMillis(date, time);
        long crc = $this$readEntry.readIntLe() & MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE;
        final Ref.LongRef compressedSize = new Ref.LongRef();
        compressedSize.element = $this$readEntry.readIntLe() & MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE;
        final Ref.LongRef size = new Ref.LongRef();
        size.element = $this$readEntry.readIntLe() & MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE;
        int nameSize = $this$readEntry.readShortLe() & UShort.MAX_VALUE;
        int extraSize = $this$readEntry.readShortLe() & UShort.MAX_VALUE;
        int commentByteCount = $this$readEntry.readShortLe() & UShort.MAX_VALUE;
        $this$readEntry.skip(8L);
        Ref.LongRef offset2 = new Ref.LongRef();
        offset2.element = $this$readEntry.readIntLe() & MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE;
        String name = $this$readEntry.readUtf8(nameSize);
        if (StringsKt.contains$default((CharSequence) name, (char) 0, false, 2, (Object) null)) {
            throw new IOException("bad zip: filename contains 0x00");
        }
        long result = 0;
        if (size.element == MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE) {
            offset = offset2;
            result = 0 + 8;
        } else {
            offset = offset2;
        }
        if (compressedSize.element == MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE) {
            result += 8;
        }
        if (offset.element == MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE) {
            result += 8;
        }
        final long requiredZip64ExtraSize = result;
        final Ref.BooleanRef hasZip64Extra = new Ref.BooleanRef();
        final Ref.LongRef offset3 = offset;
        readExtra($this$readEntry, extraSize, new Function2<Integer, Long, Unit>() { // from class: okio.internal.ZipFilesKt$readEntry$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, Long l) {
                invoke(num.intValue(), l.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int headerId, long dataSize) {
                if (headerId == 1) {
                    if (!Ref.BooleanRef.this.element) {
                        Ref.BooleanRef.this.element = true;
                        if (dataSize < requiredZip64ExtraSize) {
                            throw new IOException("bad zip: zip64 extra too short");
                        }
                        size.element = size.element == 4294967295L ? $this$readEntry.readLongLe() : size.element;
                        compressedSize.element = compressedSize.element == 4294967295L ? $this$readEntry.readLongLe() : 0L;
                        offset3.element = offset3.element == 4294967295L ? $this$readEntry.readLongLe() : 0L;
                        return;
                    }
                    throw new IOException("bad zip: zip64 extra repeated");
                }
            }
        });
        if (requiredZip64ExtraSize > 0 && !hasZip64Extra.element) {
            throw new IOException("bad zip: zip64 extra required but absent");
        }
        String comment = $this$readEntry.readUtf8(commentByteCount);
        Path canonicalPath = Path.Companion.get$default(Path.Companion, "/", false, 1, (Object) null).resolve(name);
        boolean isDirectory = StringsKt.endsWith$default(name, "/", false, 2, (Object) null);
        return new ZipEntry(canonicalPath, isDirectory, comment, crc, compressedSize.element, size.element, compressionMethod, lastModifiedAtMillis, offset3.element);
    }

    private static final EocdRecord readEocdRecord(BufferedSource $this$readEocdRecord) throws IOException {
        int diskNumber = $this$readEocdRecord.readShortLe() & UShort.MAX_VALUE;
        int diskWithCentralDir = $this$readEocdRecord.readShortLe() & UShort.MAX_VALUE;
        long entryCount = $this$readEocdRecord.readShortLe() & UShort.MAX_VALUE;
        long totalEntryCount = $this$readEocdRecord.readShortLe() & UShort.MAX_VALUE;
        if (entryCount != totalEntryCount || diskNumber != 0 || diskWithCentralDir != 0) {
            throw new IOException("unsupported zip: spanned");
        }
        $this$readEocdRecord.skip(4L);
        long centralDirectoryOffset = $this$readEocdRecord.readIntLe() & MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE;
        int commentByteCount = 65535 & $this$readEocdRecord.readShortLe();
        return new EocdRecord(entryCount, centralDirectoryOffset, commentByteCount);
    }

    private static final EocdRecord readZip64EocdRecord(BufferedSource $this$readZip64EocdRecord, EocdRecord regularRecord) throws IOException {
        $this$readZip64EocdRecord.skip(12L);
        int diskNumber = $this$readZip64EocdRecord.readIntLe();
        int diskWithCentralDirStart = $this$readZip64EocdRecord.readIntLe();
        long entryCount = $this$readZip64EocdRecord.readLongLe();
        long totalEntryCount = $this$readZip64EocdRecord.readLongLe();
        if (entryCount == totalEntryCount && diskNumber == 0 && diskWithCentralDirStart == 0) {
            $this$readZip64EocdRecord.skip(8L);
            long centralDirectoryOffset = $this$readZip64EocdRecord.readLongLe();
            return new EocdRecord(entryCount, centralDirectoryOffset, regularRecord.getCommentByteCount());
        }
        throw new IOException("unsupported zip: spanned");
    }

    private static final void readExtra(BufferedSource $this$readExtra, int extraSize, Function2<? super Integer, ? super Long, Unit> function2) {
        long remaining = extraSize;
        while (remaining != 0) {
            if (remaining < 4) {
                throw new IOException("bad zip: truncated header in extra field");
            }
            int headerId = $this$readExtra.readShortLe() & UShort.MAX_VALUE;
            long dataSize = $this$readExtra.readShortLe() & WebSocketProtocol.PAYLOAD_SHORT_MAX;
            long remaining2 = remaining - 4;
            if (remaining2 < dataSize) {
                throw new IOException("bad zip: truncated value in extra field");
            }
            $this$readExtra.require(dataSize);
            long sizeBefore = $this$readExtra.getBuffer().size();
            function2.invoke(Integer.valueOf(headerId), Long.valueOf(dataSize));
            long fieldRemaining = ($this$readExtra.getBuffer().size() + dataSize) - sizeBefore;
            if (fieldRemaining < 0) {
                throw new IOException("unsupported zip: too many bytes processed for " + headerId);
            }
            if (fieldRemaining > 0) {
                $this$readExtra.getBuffer().skip(fieldRemaining);
            }
            remaining = remaining2 - dataSize;
        }
    }

    public static final void skipLocalHeader(BufferedSource $this$skipLocalHeader) {
        Intrinsics.checkNotNullParameter($this$skipLocalHeader, "<this>");
        readOrSkipLocalHeader($this$skipLocalHeader, null);
    }

    public static final FileMetadata readLocalHeader(BufferedSource $this$readLocalHeader, FileMetadata basicMetadata) {
        Intrinsics.checkNotNullParameter($this$readLocalHeader, "<this>");
        Intrinsics.checkNotNullParameter(basicMetadata, "basicMetadata");
        FileMetadata readOrSkipLocalHeader = readOrSkipLocalHeader($this$readLocalHeader, basicMetadata);
        Intrinsics.checkNotNull(readOrSkipLocalHeader);
        return readOrSkipLocalHeader;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final FileMetadata readOrSkipLocalHeader(final BufferedSource $this$readOrSkipLocalHeader, FileMetadata basicMetadata) {
        final Ref.ObjectRef lastModifiedAtMillis = new Ref.ObjectRef();
        lastModifiedAtMillis.element = basicMetadata != null ? basicMetadata.getLastModifiedAtMillis() : 0;
        final Ref.ObjectRef lastAccessedAtMillis = new Ref.ObjectRef();
        final Ref.ObjectRef createdAtMillis = new Ref.ObjectRef();
        int signature = $this$readOrSkipLocalHeader.readIntLe();
        if (signature == LOCAL_FILE_HEADER_SIGNATURE) {
            $this$readOrSkipLocalHeader.skip(2L);
            int bitFlag = $this$readOrSkipLocalHeader.readShortLe() & UShort.MAX_VALUE;
            if ((bitFlag & 1) == 0) {
                $this$readOrSkipLocalHeader.skip(18L);
                long fileNameLength = $this$readOrSkipLocalHeader.readShortLe() & WebSocketProtocol.PAYLOAD_SHORT_MAX;
                int extraSize = 65535 & $this$readOrSkipLocalHeader.readShortLe();
                $this$readOrSkipLocalHeader.skip(fileNameLength);
                if (basicMetadata == null) {
                    $this$readOrSkipLocalHeader.skip(extraSize);
                    return null;
                }
                readExtra($this$readOrSkipLocalHeader, extraSize, new Function2<Integer, Long, Unit>() { // from class: okio.internal.ZipFilesKt$readOrSkipLocalHeader$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, Long l) {
                        invoke(num.intValue(), l.longValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Type inference failed for: r4v12, types: [T, java.lang.Long] */
                    /* JADX WARN: Type inference failed for: r4v6, types: [T, java.lang.Long] */
                    /* JADX WARN: Type inference failed for: r4v9, types: [T, java.lang.Long] */
                    public final void invoke(int headerId, long dataSize) {
                        if (headerId == 21589) {
                            if (dataSize < 1) {
                                throw new IOException("bad zip: extended timestamp extra too short");
                            }
                            int flags = BufferedSource.this.readByte() & UByte.MAX_VALUE;
                            boolean hasLastModifiedAtMillis = (flags & 1) == 1;
                            boolean hasLastAccessedAtMillis = (flags & 2) == 2;
                            boolean hasCreatedAtMillis = (flags & 4) == 4;
                            BufferedSource bufferedSource = BufferedSource.this;
                            long result = hasLastModifiedAtMillis ? 1 + 4 : 1L;
                            if (hasLastAccessedAtMillis) {
                                result += 4;
                            }
                            if (hasCreatedAtMillis) {
                                result += 4;
                            }
                            long requiredSize = result;
                            if (dataSize < requiredSize) {
                                throw new IOException("bad zip: extended timestamp extra too short");
                            }
                            if (hasLastModifiedAtMillis) {
                                lastModifiedAtMillis.element = Long.valueOf(BufferedSource.this.readIntLe() * 1000);
                            }
                            if (hasLastAccessedAtMillis) {
                                lastAccessedAtMillis.element = Long.valueOf(BufferedSource.this.readIntLe() * 1000);
                            }
                            if (hasCreatedAtMillis) {
                                createdAtMillis.element = Long.valueOf(BufferedSource.this.readIntLe() * 1000);
                            }
                        }
                    }
                });
                return new FileMetadata(basicMetadata.isRegularFile(), basicMetadata.isDirectory(), null, basicMetadata.getSize(), (Long) createdAtMillis.element, (Long) lastModifiedAtMillis.element, (Long) lastAccessedAtMillis.element, null, 128, null);
            }
            throw new IOException("unsupported zip: general purpose bit flag=" + getHex(bitFlag));
        }
        throw new IOException("bad zip: expected " + getHex(LOCAL_FILE_HEADER_SIGNATURE) + " but was " + getHex(signature));
    }

    private static final Long dosDateTimeToEpochMillis(int date, int time) {
        if (time == -1) {
            return null;
        }
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(14, 0);
        int year = ((date >> 9) & 127) + 1980;
        int month = (date >> 5) & 15;
        int day = date & 31;
        int hour = (time >> 11) & 31;
        int minute = (time >> 5) & 63;
        int second = (time & 31) << 1;
        cal.set(year, month - 1, day, hour, minute, second);
        return Long.valueOf(cal.getTime().getTime());
    }

    private static final String getHex(int $this$hex) {
        StringBuilder append = new StringBuilder().append("0x");
        String num = Integer.toString($this$hex, CharsKt.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(num, "toString(this, checkRadix(radix))");
        return append.append(num).toString();
    }
}
