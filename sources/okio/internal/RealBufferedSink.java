package okio.internal;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.EOFException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Source;
import okio.Timeout;
/* compiled from: RealBufferedSink.kt */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0080\b\u001a\r\u0010\u0003\u001a\u00020\u0004*\u00020\u0002H\u0080\b\u001a\r\u0010\u0005\u001a\u00020\u0004*\u00020\u0002H\u0080\b\u001a\r\u0010\u0006\u001a\u00020\u0001*\u00020\u0002H\u0080\b\u001a\r\u0010\u0007\u001a\u00020\b*\u00020\u0002H\u0080\b\u001a\r\u0010\t\u001a\u00020\n*\u00020\u0002H\u0080\b\u001a\u0015\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0080\b\u001a%\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0080\b\u001a\u001d\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0080\b\u001a%\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0080\b\u001a\u001d\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010\u0016\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\f\u001a\u00020\u0015H\u0080\b\u001a\u0015\u0010\u0017\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010\u0019\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010\u001b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010\u001c\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010\u001e\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010\u001f\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010 \u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010!\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\"\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010#\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\"\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010$\u001a\u00020\u0004*\u00020\u00022\u0006\u0010%\u001a\u00020\nH\u0080\b\u001a%\u0010$\u001a\u00020\u0004*\u00020\u00022\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\u000f2\u0006\u0010'\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010(\u001a\u00020\u0004*\u00020\u00022\u0006\u0010)\u001a\u00020\u000fH\u0080\b¨\u0006*"}, d2 = {"commonClose", "", "Lokio/RealBufferedSink;", "commonEmit", "Lokio/BufferedSink;", "commonEmitCompleteSegments", "commonFlush", "commonTimeout", "Lokio/Timeout;", "commonToString", "", "commonWrite", "source", "", "offset", "", "byteCount", "Lokio/Buffer;", "", "byteString", "Lokio/ByteString;", "Lokio/Source;", "commonWriteAll", "commonWriteByte", "b", "commonWriteDecimalLong", "v", "commonWriteHexadecimalUnsignedLong", "commonWriteInt", "i", "commonWriteIntLe", "commonWriteLong", "commonWriteLongLe", "commonWriteShort", "s", "commonWriteShortLe", "commonWriteUtf8", "string", "beginIndex", "endIndex", "commonWriteUtf8CodePoint", "codePoint", "okio"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: okio.internal.-RealBufferedSink  reason: invalid class name */
/* loaded from: classes.dex */
public final class RealBufferedSink {
    public static final void commonWrite(okio.RealBufferedSink $this$commonWrite, Buffer source, long byteCount) {
        Intrinsics.checkNotNullParameter($this$commonWrite, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        if (!(!$this$commonWrite.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        $this$commonWrite.bufferField.write(source, byteCount);
        $this$commonWrite.emitCompleteSegments();
    }

    public static final BufferedSink commonWrite(okio.RealBufferedSink $this$commonWrite, ByteString byteString) {
        Intrinsics.checkNotNullParameter($this$commonWrite, "<this>");
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        if (!$this$commonWrite.closed) {
            $this$commonWrite.bufferField.write(byteString);
            return $this$commonWrite.emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public static final BufferedSink commonWrite(okio.RealBufferedSink $this$commonWrite, ByteString byteString, int offset, int byteCount) {
        Intrinsics.checkNotNullParameter($this$commonWrite, "<this>");
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        if (!$this$commonWrite.closed) {
            $this$commonWrite.bufferField.write(byteString, offset, byteCount);
            return $this$commonWrite.emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public static final BufferedSink commonWriteUtf8(okio.RealBufferedSink $this$commonWriteUtf8, String string) {
        Intrinsics.checkNotNullParameter($this$commonWriteUtf8, "<this>");
        Intrinsics.checkNotNullParameter(string, "string");
        if (!$this$commonWriteUtf8.closed) {
            $this$commonWriteUtf8.bufferField.writeUtf8(string);
            return $this$commonWriteUtf8.emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public static final BufferedSink commonWriteUtf8(okio.RealBufferedSink $this$commonWriteUtf8, String string, int beginIndex, int endIndex) {
        Intrinsics.checkNotNullParameter($this$commonWriteUtf8, "<this>");
        Intrinsics.checkNotNullParameter(string, "string");
        if (!$this$commonWriteUtf8.closed) {
            $this$commonWriteUtf8.bufferField.writeUtf8(string, beginIndex, endIndex);
            return $this$commonWriteUtf8.emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public static final BufferedSink commonWriteUtf8CodePoint(okio.RealBufferedSink $this$commonWriteUtf8CodePoint, int codePoint) {
        Intrinsics.checkNotNullParameter($this$commonWriteUtf8CodePoint, "<this>");
        if (!$this$commonWriteUtf8CodePoint.closed) {
            $this$commonWriteUtf8CodePoint.bufferField.writeUtf8CodePoint(codePoint);
            return $this$commonWriteUtf8CodePoint.emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public static final BufferedSink commonWrite(okio.RealBufferedSink $this$commonWrite, byte[] source) {
        Intrinsics.checkNotNullParameter($this$commonWrite, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        if (!$this$commonWrite.closed) {
            $this$commonWrite.bufferField.write(source);
            return $this$commonWrite.emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public static final BufferedSink commonWrite(okio.RealBufferedSink $this$commonWrite, byte[] source, int offset, int byteCount) {
        Intrinsics.checkNotNullParameter($this$commonWrite, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        if (!$this$commonWrite.closed) {
            $this$commonWrite.bufferField.write(source, offset, byteCount);
            return $this$commonWrite.emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public static final long commonWriteAll(okio.RealBufferedSink $this$commonWriteAll, Source source) {
        Intrinsics.checkNotNullParameter($this$commonWriteAll, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        long totalBytesRead = 0;
        while (true) {
            long readCount = source.read($this$commonWriteAll.bufferField, 8192L);
            if (readCount != -1) {
                totalBytesRead += readCount;
                $this$commonWriteAll.emitCompleteSegments();
            } else {
                return totalBytesRead;
            }
        }
    }

    public static final BufferedSink commonWrite(okio.RealBufferedSink $this$commonWrite, Source source, long byteCount) {
        Intrinsics.checkNotNullParameter($this$commonWrite, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        long byteCount2 = byteCount;
        while (byteCount2 > 0) {
            long read = source.read($this$commonWrite.bufferField, byteCount2);
            if (read == -1) {
                throw new EOFException();
            }
            byteCount2 -= read;
            $this$commonWrite.emitCompleteSegments();
        }
        return $this$commonWrite;
    }

    public static final BufferedSink commonWriteByte(okio.RealBufferedSink $this$commonWriteByte, int b) {
        Intrinsics.checkNotNullParameter($this$commonWriteByte, "<this>");
        if (!$this$commonWriteByte.closed) {
            $this$commonWriteByte.bufferField.writeByte(b);
            return $this$commonWriteByte.emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public static final BufferedSink commonWriteShort(okio.RealBufferedSink $this$commonWriteShort, int s) {
        Intrinsics.checkNotNullParameter($this$commonWriteShort, "<this>");
        if (!$this$commonWriteShort.closed) {
            $this$commonWriteShort.bufferField.writeShort(s);
            return $this$commonWriteShort.emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public static final BufferedSink commonWriteShortLe(okio.RealBufferedSink $this$commonWriteShortLe, int s) {
        Intrinsics.checkNotNullParameter($this$commonWriteShortLe, "<this>");
        if (!$this$commonWriteShortLe.closed) {
            $this$commonWriteShortLe.bufferField.writeShortLe(s);
            return $this$commonWriteShortLe.emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public static final BufferedSink commonWriteInt(okio.RealBufferedSink $this$commonWriteInt, int i) {
        Intrinsics.checkNotNullParameter($this$commonWriteInt, "<this>");
        if (!$this$commonWriteInt.closed) {
            $this$commonWriteInt.bufferField.writeInt(i);
            return $this$commonWriteInt.emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public static final BufferedSink commonWriteIntLe(okio.RealBufferedSink $this$commonWriteIntLe, int i) {
        Intrinsics.checkNotNullParameter($this$commonWriteIntLe, "<this>");
        if (!$this$commonWriteIntLe.closed) {
            $this$commonWriteIntLe.bufferField.writeIntLe(i);
            return $this$commonWriteIntLe.emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public static final BufferedSink commonWriteLong(okio.RealBufferedSink $this$commonWriteLong, long v) {
        Intrinsics.checkNotNullParameter($this$commonWriteLong, "<this>");
        if (!$this$commonWriteLong.closed) {
            $this$commonWriteLong.bufferField.writeLong(v);
            return $this$commonWriteLong.emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public static final BufferedSink commonWriteLongLe(okio.RealBufferedSink $this$commonWriteLongLe, long v) {
        Intrinsics.checkNotNullParameter($this$commonWriteLongLe, "<this>");
        if (!$this$commonWriteLongLe.closed) {
            $this$commonWriteLongLe.bufferField.writeLongLe(v);
            return $this$commonWriteLongLe.emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public static final BufferedSink commonWriteDecimalLong(okio.RealBufferedSink $this$commonWriteDecimalLong, long v) {
        Intrinsics.checkNotNullParameter($this$commonWriteDecimalLong, "<this>");
        if (!$this$commonWriteDecimalLong.closed) {
            $this$commonWriteDecimalLong.bufferField.writeDecimalLong(v);
            return $this$commonWriteDecimalLong.emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public static final BufferedSink commonWriteHexadecimalUnsignedLong(okio.RealBufferedSink $this$commonWriteHexadecimalUnsignedLong, long v) {
        Intrinsics.checkNotNullParameter($this$commonWriteHexadecimalUnsignedLong, "<this>");
        if (!$this$commonWriteHexadecimalUnsignedLong.closed) {
            $this$commonWriteHexadecimalUnsignedLong.bufferField.writeHexadecimalUnsignedLong(v);
            return $this$commonWriteHexadecimalUnsignedLong.emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public static final BufferedSink commonEmitCompleteSegments(okio.RealBufferedSink $this$commonEmitCompleteSegments) {
        Intrinsics.checkNotNullParameter($this$commonEmitCompleteSegments, "<this>");
        if (!$this$commonEmitCompleteSegments.closed) {
            long byteCount = $this$commonEmitCompleteSegments.bufferField.completeSegmentByteCount();
            if (byteCount > 0) {
                $this$commonEmitCompleteSegments.sink.write($this$commonEmitCompleteSegments.bufferField, byteCount);
            }
            return $this$commonEmitCompleteSegments;
        }
        throw new IllegalStateException("closed".toString());
    }

    public static final BufferedSink commonEmit(okio.RealBufferedSink $this$commonEmit) {
        Intrinsics.checkNotNullParameter($this$commonEmit, "<this>");
        if (!$this$commonEmit.closed) {
            long byteCount = $this$commonEmit.bufferField.size();
            if (byteCount > 0) {
                $this$commonEmit.sink.write($this$commonEmit.bufferField, byteCount);
            }
            return $this$commonEmit;
        }
        throw new IllegalStateException("closed".toString());
    }

    public static final void commonFlush(okio.RealBufferedSink $this$commonFlush) {
        Intrinsics.checkNotNullParameter($this$commonFlush, "<this>");
        if (!(!$this$commonFlush.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        if ($this$commonFlush.bufferField.size() > 0) {
            $this$commonFlush.sink.write($this$commonFlush.bufferField, $this$commonFlush.bufferField.size());
        }
        $this$commonFlush.sink.flush();
    }

    public static final void commonClose(okio.RealBufferedSink $this$commonClose) {
        Intrinsics.checkNotNullParameter($this$commonClose, "<this>");
        if ($this$commonClose.closed) {
            return;
        }
        Throwable thrown = null;
        try {
            if ($this$commonClose.bufferField.size() > 0) {
                $this$commonClose.sink.write($this$commonClose.bufferField, $this$commonClose.bufferField.size());
            }
        } catch (Throwable e) {
            thrown = e;
        }
        try {
            $this$commonClose.sink.close();
        } catch (Throwable e2) {
            if (thrown == null) {
                thrown = e2;
            }
        }
        $this$commonClose.closed = true;
        if (thrown != null) {
            throw thrown;
        }
    }

    public static final Timeout commonTimeout(okio.RealBufferedSink $this$commonTimeout) {
        Intrinsics.checkNotNullParameter($this$commonTimeout, "<this>");
        return $this$commonTimeout.sink.timeout();
    }

    public static final String commonToString(okio.RealBufferedSink $this$commonToString) {
        Intrinsics.checkNotNullParameter($this$commonToString, "<this>");
        return "buffer(" + $this$commonToString.sink + ')';
    }
}
