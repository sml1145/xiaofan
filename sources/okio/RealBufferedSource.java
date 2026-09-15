package okio;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.Typography;
/* compiled from: RealBufferedSource.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\rH\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0012H\u0016J \u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0012H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u0012H\u0016J\u0010\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u0012H\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\rH\u0016J\b\u0010\u001e\u001a\u00020\u0001H\u0016J\u0018\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J(\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0016J\u0010\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020&H\u0016J\u0010\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020'H\u0016J \u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020'2\u0006\u0010 \u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0016J\u0018\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u0012H\u0016J\u0010\u0010(\u001a\u00020\u00122\u0006\u0010%\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020\u0014H\u0016J\b\u0010+\u001a\u00020'H\u0016J\u0010\u0010+\u001a\u00020'2\u0006\u0010#\u001a\u00020\u0012H\u0016J\b\u0010,\u001a\u00020\u0018H\u0016J\u0010\u0010,\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u0012H\u0016J\b\u0010-\u001a\u00020\u0012H\u0016J\u0010\u0010.\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020'H\u0016J\u0018\u0010.\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u0012H\u0016J\b\u0010/\u001a\u00020\u0012H\u0016J\b\u00100\u001a\u00020\"H\u0016J\b\u00101\u001a\u00020\"H\u0016J\b\u00102\u001a\u00020\u0012H\u0016J\b\u00103\u001a\u00020\u0012H\u0016J\b\u00104\u001a\u000205H\u0016J\b\u00106\u001a\u000205H\u0016J\u0010\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0016J\u0018\u00107\u001a\u0002082\u0006\u0010#\u001a\u00020\u00122\u0006\u00109\u001a\u00020:H\u0016J\b\u0010;\u001a\u000208H\u0016J\u0010\u0010;\u001a\u0002082\u0006\u0010#\u001a\u00020\u0012H\u0016J\b\u0010<\u001a\u00020\"H\u0016J\n\u0010=\u001a\u0004\u0018\u000108H\u0016J\b\u0010>\u001a\u000208H\u0016J\u0010\u0010>\u001a\u0002082\u0006\u0010?\u001a\u00020\u0012H\u0016J\u0010\u0010@\u001a\u00020\r2\u0006\u0010#\u001a\u00020\u0012H\u0016J\u0010\u0010A\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u0012H\u0016J\u0010\u0010B\u001a\u00020\"2\u0006\u0010C\u001a\u00020DH\u0016J\u0010\u0010E\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u0012H\u0016J\b\u0010F\u001a\u00020GH\u0016J\b\u0010H\u001a\u000208H\u0016R\u001b\u0010\u0005\u001a\u00020\u00068Ö\u0002X\u0096\u0004¢\u0006\f\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00020\r8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006I"}, d2 = {"Lokio/RealBufferedSource;", "Lokio/BufferedSource;", "source", "Lokio/Source;", "(Lokio/Source;)V", "buffer", "Lokio/Buffer;", "getBuffer$annotations", "()V", "getBuffer", "()Lokio/Buffer;", "bufferField", "closed", "", "close", "", "exhausted", "indexOf", "", "b", "", "fromIndex", "toIndex", "bytes", "Lokio/ByteString;", "indexOfElement", "targetBytes", "inputStream", "Ljava/io/InputStream;", "isOpen", "peek", "rangeEquals", "offset", "bytesOffset", "", "byteCount", "read", "sink", "Ljava/nio/ByteBuffer;", "", "readAll", "Lokio/Sink;", "readByte", "readByteArray", "readByteString", "readDecimalLong", "readFully", "readHexadecimalUnsignedLong", "readInt", "readIntLe", "readLong", "readLongLe", "readShort", "", "readShortLe", "readString", "", "charset", "Ljava/nio/charset/Charset;", "readUtf8", "readUtf8CodePoint", "readUtf8Line", "readUtf8LineStrict", "limit", "request", "require", "select", "options", "Lokio/Options;", "skip", "timeout", "Lokio/Timeout;", "toString", "okio"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class RealBufferedSource implements BufferedSource {
    public final Buffer bufferField;
    public boolean closed;
    public final Source source;

    public static /* synthetic */ void getBuffer$annotations() {
    }

    public RealBufferedSource(Source source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.source = source;
        this.bufferField = new Buffer();
    }

    @Override // okio.BufferedSource, okio.BufferedSink
    public Buffer getBuffer() {
        return this.bufferField;
    }

    @Override // okio.BufferedSource, okio.BufferedSink
    public Buffer buffer() {
        return this.bufferField;
    }

    @Override // okio.Source
    public long read(Buffer sink, long byteCount) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (byteCount >= 0) {
            if (!this.closed) {
                if (this.bufferField.size() == 0) {
                    long read$iv = this.source.read(this.bufferField, 8192L);
                    if (read$iv == -1) {
                        return -1L;
                    }
                }
                long toRead$iv = Math.min(byteCount, this.bufferField.size());
                return this.bufferField.read(sink, toRead$iv);
            }
            throw new IllegalStateException("closed".toString());
        }
        throw new IllegalArgumentException(("byteCount < 0: " + byteCount).toString());
    }

    @Override // okio.BufferedSource
    public boolean exhausted() {
        if (!this.closed) {
            return this.bufferField.exhausted() && this.source.read(this.bufferField, 8192L) == -1;
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSource
    public void require(long byteCount) {
        if (request(byteCount)) {
            return;
        }
        throw new EOFException();
    }

    @Override // okio.BufferedSource
    public boolean request(long byteCount) {
        if (!(byteCount >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + byteCount).toString());
        }
        if (!this.closed) {
            while (this.bufferField.size() < byteCount) {
                if (this.source.read(this.bufferField, 8192L) == -1) {
                    return false;
                }
            }
            return true;
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSource
    public byte readByte() {
        require(1L);
        return this.bufferField.readByte();
    }

    @Override // okio.BufferedSource
    public ByteString readByteString() {
        this.bufferField.writeAll(this.source);
        return this.bufferField.readByteString();
    }

    @Override // okio.BufferedSource
    public ByteString readByteString(long byteCount) {
        require(byteCount);
        return this.bufferField.readByteString(byteCount);
    }

    @Override // okio.BufferedSource
    public int select(Options options) {
        Intrinsics.checkNotNullParameter(options, "options");
        if (!this.closed) {
            do {
                int index$iv = okio.internal.Buffer.selectPrefix(this.bufferField, options, true);
                switch (index$iv) {
                    case -2:
                        break;
                    case -1:
                        return -1;
                    default:
                        int selectedSize$iv = options.getByteStrings$okio()[index$iv].size();
                        this.bufferField.skip(selectedSize$iv);
                        return index$iv;
                }
            } while (this.source.read(this.bufferField, 8192L) != -1);
            return -1;
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray() {
        this.bufferField.writeAll(this.source);
        return this.bufferField.readByteArray();
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray(long byteCount) {
        require(byteCount);
        return this.bufferField.readByteArray(byteCount);
    }

    @Override // okio.BufferedSource
    public int read(byte[] sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        return read(sink, 0, sink.length);
    }

    @Override // okio.BufferedSource
    public void readFully(byte[] sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        try {
            require(sink.length);
            this.bufferField.readFully(sink);
        } catch (EOFException e$iv) {
            int offset$iv = 0;
            while (this.bufferField.size() > 0) {
                int read$iv = this.bufferField.read(sink, offset$iv, (int) this.bufferField.size());
                if (read$iv == -1) {
                    throw new AssertionError();
                }
                offset$iv += read$iv;
            }
            throw e$iv;
        }
    }

    @Override // okio.BufferedSource
    public int read(byte[] sink, int offset, int byteCount) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        SegmentedByteString.checkOffsetAndCount(sink.length, offset, byteCount);
        if (this.bufferField.size() == 0) {
            long read$iv = this.source.read(this.bufferField, 8192L);
            if (read$iv == -1) {
                return -1;
            }
        }
        long b$iv$iv = this.bufferField.size();
        int toRead$iv = (int) Math.min(byteCount, b$iv$iv);
        int $i$f$getBuffer = this.bufferField.read(sink, offset, toRead$iv);
        return $i$f$getBuffer;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (this.bufferField.size() == 0) {
            long read = this.source.read(this.bufferField, 8192L);
            if (read == -1) {
                return -1;
            }
        }
        return this.bufferField.read(sink);
    }

    @Override // okio.BufferedSource
    public void readFully(Buffer sink, long byteCount) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        try {
            require(byteCount);
            this.bufferField.readFully(sink, byteCount);
        } catch (EOFException e$iv) {
            sink.writeAll(this.bufferField);
            throw e$iv;
        }
    }

    @Override // okio.BufferedSource
    public long readAll(Sink sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        long totalBytesWritten$iv = 0;
        while (this.source.read(this.bufferField, 8192L) != -1) {
            long emitByteCount$iv = this.bufferField.completeSegmentByteCount();
            if (emitByteCount$iv > 0) {
                totalBytesWritten$iv += emitByteCount$iv;
                sink.write(this.bufferField, emitByteCount$iv);
            }
        }
        if (this.bufferField.size() > 0) {
            long totalBytesWritten$iv2 = totalBytesWritten$iv + this.bufferField.size();
            sink.write(this.bufferField, this.bufferField.size());
            return totalBytesWritten$iv2;
        }
        return totalBytesWritten$iv;
    }

    @Override // okio.BufferedSource
    public String readUtf8() {
        this.bufferField.writeAll(this.source);
        return this.bufferField.readUtf8();
    }

    @Override // okio.BufferedSource
    public String readUtf8(long byteCount) {
        require(byteCount);
        return this.bufferField.readUtf8(byteCount);
    }

    @Override // okio.BufferedSource
    public String readString(Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "charset");
        this.bufferField.writeAll(this.source);
        return this.bufferField.readString(charset);
    }

    @Override // okio.BufferedSource
    public String readString(long byteCount, Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "charset");
        require(byteCount);
        return this.bufferField.readString(byteCount, charset);
    }

    @Override // okio.BufferedSource
    public String readUtf8Line() {
        long newline$iv = indexOf((byte) 10);
        if (newline$iv == -1) {
            if (this.bufferField.size() != 0) {
                return readUtf8(this.bufferField.size());
            }
            return null;
        }
        return okio.internal.Buffer.readUtf8Line(this.bufferField, newline$iv);
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict() {
        return readUtf8LineStrict(Long.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict(long limit) {
        if (limit >= 0) {
            long scanLength$iv = limit == Long.MAX_VALUE ? Long.MAX_VALUE : limit + 1;
            long newline$iv = indexOf((byte) 10, 0L, scanLength$iv);
            if (newline$iv != -1) {
                return okio.internal.Buffer.readUtf8Line(this.bufferField, newline$iv);
            }
            if (scanLength$iv < Long.MAX_VALUE && request(scanLength$iv) && this.bufferField.getByte(scanLength$iv - 1) == 13 && request(1 + scanLength$iv) && this.bufferField.getByte(scanLength$iv) == 10) {
                return okio.internal.Buffer.readUtf8Line(this.bufferField, scanLength$iv);
            }
            Buffer data$iv = new Buffer();
            Buffer buffer = this.bufferField;
            long b$iv$iv = this.bufferField.size();
            buffer.copyTo(data$iv, 0L, Math.min(32, b$iv$iv));
            throw new EOFException("\\n not found: limit=" + Math.min(this.bufferField.size(), limit) + " content=" + data$iv.readByteString().hex() + Typography.ellipsis);
        }
        throw new IllegalArgumentException(("limit < 0: " + limit).toString());
    }

    @Override // okio.BufferedSource
    public int readUtf8CodePoint() {
        require(1L);
        int b0$iv = this.bufferField.getByte(0L);
        if ((b0$iv & 224) == 192) {
            require(2L);
        } else if ((b0$iv & 240) == 224) {
            require(3L);
        } else if ((b0$iv & 248) == 240) {
            require(4L);
        }
        return this.bufferField.readUtf8CodePoint();
    }

    @Override // okio.BufferedSource
    public short readShort() {
        require(2L);
        return this.bufferField.readShort();
    }

    @Override // okio.BufferedSource
    public short readShortLe() {
        require(2L);
        return this.bufferField.readShortLe();
    }

    @Override // okio.BufferedSource
    public int readInt() {
        require(4L);
        return this.bufferField.readInt();
    }

    @Override // okio.BufferedSource
    public int readIntLe() {
        require(4L);
        return this.bufferField.readIntLe();
    }

    @Override // okio.BufferedSource
    public long readLong() {
        require(8L);
        return this.bufferField.readLong();
    }

    @Override // okio.BufferedSource
    public long readLongLe() {
        require(8L);
        return this.bufferField.readLongLe();
    }

    @Override // okio.BufferedSource
    public long readDecimalLong() {
        require(1L);
        for (long pos$iv = 0; request(pos$iv + 1); pos$iv++) {
            byte b$iv = this.bufferField.getByte(pos$iv);
            if ((b$iv < 48 || b$iv > 57) && (pos$iv != 0 || b$iv != 45)) {
                if (pos$iv == 0) {
                    StringBuilder append = new StringBuilder().append("Expected a digit or '-' but was 0x");
                    String num = Integer.toString(b$iv, CharsKt.checkRadix(CharsKt.checkRadix(16)));
                    Intrinsics.checkNotNullExpressionValue(num, "toString(this, checkRadix(radix))");
                    throw new NumberFormatException(append.append(num).toString());
                }
                return this.bufferField.readDecimalLong();
            }
        }
        return this.bufferField.readDecimalLong();
    }

    @Override // okio.BufferedSource
    public long readHexadecimalUnsignedLong() {
        require(1L);
        for (int pos$iv = 0; request(pos$iv + 1); pos$iv++) {
            byte b$iv = this.bufferField.getByte(pos$iv);
            if ((b$iv < 48 || b$iv > 57) && ((b$iv < 97 || b$iv > 102) && (b$iv < 65 || b$iv > 70))) {
                if (pos$iv == 0) {
                    StringBuilder append = new StringBuilder().append("Expected leading [0-9a-fA-F] character but was 0x");
                    String num = Integer.toString(b$iv, CharsKt.checkRadix(CharsKt.checkRadix(16)));
                    Intrinsics.checkNotNullExpressionValue(num, "toString(this, checkRadix(radix))");
                    throw new NumberFormatException(append.append(num).toString());
                }
                return this.bufferField.readHexadecimalUnsignedLong();
            }
        }
        return this.bufferField.readHexadecimalUnsignedLong();
    }

    @Override // okio.BufferedSource
    public void skip(long byteCount) {
        long byteCount$iv = byteCount;
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        while (byteCount$iv > 0) {
            if (this.bufferField.size() == 0 && this.source.read(this.bufferField, 8192L) == -1) {
                throw new EOFException();
            }
            long toSkip$iv = Math.min(byteCount$iv, this.bufferField.size());
            this.bufferField.skip(toSkip$iv);
            byteCount$iv -= toSkip$iv;
        }
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b) {
        return indexOf(b, 0L, Long.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b, long fromIndex) {
        return indexOf(b, fromIndex, Long.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b, long fromIndex, long toIndex) {
        boolean z = true;
        if (!this.closed) {
            if (0 > fromIndex || fromIndex > toIndex) {
                z = false;
            }
            if (!z) {
                throw new IllegalArgumentException(("fromIndex=" + fromIndex + " toIndex=" + toIndex).toString());
            }
            long fromIndex$iv = fromIndex;
            while (fromIndex$iv < toIndex) {
                long result$iv = this.bufferField.indexOf(b, fromIndex$iv, toIndex);
                if (result$iv != -1) {
                    return result$iv;
                }
                long lastBufferSize$iv = this.bufferField.size();
                if (lastBufferSize$iv >= toIndex || this.source.read(this.bufferField, 8192L) == -1) {
                    return -1L;
                }
                fromIndex$iv = Math.max(fromIndex$iv, lastBufferSize$iv);
            }
            return -1L;
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSource
    public long indexOf(ByteString bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return indexOf(bytes, 0L);
    }

    @Override // okio.BufferedSource
    public long indexOf(ByteString bytes, long fromIndex) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        long fromIndex$iv = fromIndex;
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        while (true) {
            long result$iv = this.bufferField.indexOf(bytes, fromIndex$iv);
            if (result$iv != -1) {
                return result$iv;
            }
            long lastBufferSize$iv = this.bufferField.size();
            if (this.source.read(this.bufferField, 8192L) == -1) {
                return -1L;
            }
            fromIndex$iv = Math.max(fromIndex$iv, (lastBufferSize$iv - bytes.size()) + 1);
        }
    }

    @Override // okio.BufferedSource
    public long indexOfElement(ByteString targetBytes) {
        Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
        return indexOfElement(targetBytes, 0L);
    }

    @Override // okio.BufferedSource
    public long indexOfElement(ByteString targetBytes, long fromIndex) {
        Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
        long fromIndex$iv = fromIndex;
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        while (true) {
            long result$iv = this.bufferField.indexOfElement(targetBytes, fromIndex$iv);
            if (result$iv != -1) {
                return result$iv;
            }
            long lastBufferSize$iv = this.bufferField.size();
            if (this.source.read(this.bufferField, 8192L) == -1) {
                return -1L;
            }
            fromIndex$iv = Math.max(fromIndex$iv, lastBufferSize$iv);
        }
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long offset, ByteString bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return rangeEquals(offset, bytes, 0, bytes.size());
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long offset, ByteString bytes, int bytesOffset, int byteCount) {
        int i$iv;
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (!this.closed) {
            if (offset < 0 || bytesOffset < 0 || byteCount < 0 || bytes.size() - bytesOffset < byteCount) {
                return false;
            }
            while (i$iv < byteCount) {
                long bufferOffset$iv = i$iv + offset;
                i$iv = (request(1 + bufferOffset$iv) && this.bufferField.getByte(bufferOffset$iv) == bytes.getByte(bytesOffset + i$iv)) ? i$iv + 1 : 0;
                return false;
            }
            return true;
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSource
    public BufferedSource peek() {
        return Okio.buffer(new PeekSource(this));
    }

    @Override // okio.BufferedSource
    public InputStream inputStream() {
        return new InputStream() { // from class: okio.RealBufferedSource$inputStream$1
            @Override // java.io.InputStream
            public int read() {
                if (RealBufferedSource.this.closed) {
                    throw new IOException("closed");
                }
                RealBufferedSource this_$iv = RealBufferedSource.this;
                if (this_$iv.bufferField.size() == 0) {
                    Source source = RealBufferedSource.this.source;
                    RealBufferedSource this_$iv2 = RealBufferedSource.this;
                    long count = source.read(this_$iv2.bufferField, 8192L);
                    if (count == -1) {
                        return -1;
                    }
                }
                RealBufferedSource this_$iv3 = RealBufferedSource.this;
                byte $this$and$iv = this_$iv3.bufferField.readByte();
                return $this$and$iv & UByte.MAX_VALUE;
            }

            @Override // java.io.InputStream
            public int read(byte[] data, int offset, int byteCount) {
                Intrinsics.checkNotNullParameter(data, "data");
                if (RealBufferedSource.this.closed) {
                    throw new IOException("closed");
                }
                SegmentedByteString.checkOffsetAndCount(data.length, offset, byteCount);
                RealBufferedSource this_$iv = RealBufferedSource.this;
                if (this_$iv.bufferField.size() == 0) {
                    Source source = RealBufferedSource.this.source;
                    RealBufferedSource this_$iv2 = RealBufferedSource.this;
                    long count = source.read(this_$iv2.bufferField, 8192L);
                    if (count == -1) {
                        return -1;
                    }
                }
                RealBufferedSource this_$iv3 = RealBufferedSource.this;
                return this_$iv3.bufferField.read(data, offset, byteCount);
            }

            @Override // java.io.InputStream
            public int available() {
                if (RealBufferedSource.this.closed) {
                    throw new IOException("closed");
                }
                RealBufferedSource this_$iv = RealBufferedSource.this;
                long a$iv = this_$iv.bufferField.size();
                return (int) Math.min(a$iv, Integer.MAX_VALUE);
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                RealBufferedSource.this.close();
            }

            public String toString() {
                return RealBufferedSource.this + ".inputStream()";
            }
        };
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.closed;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (!this.closed) {
            this.closed = true;
            this.source.close();
            this.bufferField.clear();
        }
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }

    public String toString() {
        return "buffer(" + this.source + ')';
    }
}
