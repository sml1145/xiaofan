package kotlinx.coroutines.channels;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import kotlin.time.DurationKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import kotlinx.coroutines.internal.InlineList;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectClause1Impl;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectClause2Impl;
import kotlinx.coroutines.selects.SelectImplementation;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.TrySelectDetailedResult;
/* compiled from: BufferedChannel.kt */
@Metadata(d1 = {"\u0000À\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\"\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0004Þ\u0001ß\u0001B1\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\"\b\u0002\u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\b¢\u0006\u0002\u0010\tJ\u0010\u0010P\u001a\u00020\u001c2\u0006\u0010Q\u001a\u00020\u0010H\u0002J\u0006\u0010R\u001a\u00020\u0007J\u0010\u0010R\u001a\u00020\u001c2\b\u0010S\u001a\u0004\u0018\u00010\u0016J\u0016\u0010R\u001a\u00020\u00072\u000e\u0010S\u001a\n\u0018\u00010Tj\u0004\u0018\u0001`UJ\u0017\u0010V\u001a\u00020\u001c2\b\u0010S\u001a\u0004\u0018\u00010\u0016H\u0010¢\u0006\u0002\bWJ\u001e\u0010X\u001a\u00020\u00072\f\u0010Y\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010K\u001a\u00020\u0010H\u0002J\u0006\u0010Z\u001a\u00020\u0007J\u0012\u0010[\u001a\u00020\u001c2\b\u0010S\u001a\u0004\u0018\u00010\u0016H\u0016J\u000e\u0010\\\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0002J\u001a\u0010]\u001a\u00020\u001c2\b\u0010S\u001a\u0004\u0018\u00010\u00162\u0006\u0010R\u001a\u00020\u001cH\u0014J\u0010\u0010^\u001a\u00020\u00072\u0006\u0010_\u001a\u00020\u0010H\u0002J\u0016\u0010`\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010_\u001a\u00020\u0010H\u0002J\b\u0010a\u001a\u00020\u0007H\u0002J\u0010\u0010b\u001a\u00020\u00072\u0006\u0010c\u001a\u00020\u0010H\u0004J\b\u0010d\u001a\u00020\u0007H\u0002J.\u0010e\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00142\u0006\u0010f\u001a\u00020\u00102\f\u0010g\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010h\u001a\u00020\u0010H\u0002J&\u0010i\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00142\u0006\u0010f\u001a\u00020\u00102\f\u0010g\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0002J&\u0010j\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00142\u0006\u0010f\u001a\u00020\u00102\f\u0010g\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0002J\r\u0010k\u001a\u00020\u001cH\u0000¢\u0006\u0002\blJ\u0012\u0010m\u001a\u00020\u00072\b\b\u0002\u0010n\u001a\u00020\u0010H\u0002J\b\u0010o\u001a\u00020\u0007H\u0002J-\u0010p\u001a\u00020\u00072#\u0010q\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0016¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(S\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J&\u0010r\u001a\u00020\u001c2\f\u0010s\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001a\u00020\u00042\u0006\u0010u\u001a\u00020\u0010H\u0002J\u0018\u0010v\u001a\u00020\u001c2\u0006\u0010w\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u000f\u0010x\u001a\b\u0012\u0004\u0012\u00028\u00000yH\u0096\u0002J\u0016\u0010z\u001a\u00020\u00102\f\u0010Y\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0002J\b\u0010{\u001a\u00020\u0007H\u0002J\b\u0010|\u001a\u00020\u0007H\u0002J\b\u0010}\u001a\u00020\u0007H\u0002J\u001e\u0010~\u001a\u00020\u00072\u0006\u0010f\u001a\u00020\u00102\f\u0010g\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0002J\b\u0010\u007f\u001a\u00020\u0007H\u0014J\"\u0010\u0080\u0001\u001a\u00020\u00072\u0014\u0010\u0081\u0001\u001a\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000,0\u0082\u0001H\u0002ø\u0001\u0000J\u0019\u0010\u0083\u0001\u001a\u00020\u00072\u000e\u0010\u0081\u0001\u001a\t\u0012\u0004\u0012\u00028\u00000\u0082\u0001H\u0002J\u0015\u0010\u0084\u0001\u001a\u00020\u00072\n\u0010<\u001a\u0006\u0012\u0002\b\u000309H\u0002J$\u0010\u0085\u0001\u001a\u00020\u00072\u0007\u0010\u0086\u0001\u001a\u00028\u00002\n\u0010<\u001a\u0006\u0012\u0002\b\u000309H\u0002¢\u0006\u0003\u0010\u0087\u0001J\u001c\u0010\u0088\u0001\u001a\u00020\u00072\u0007\u0010\u0086\u0001\u001a\u00028\u0000H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u0089\u0001J(\u0010\u008a\u0001\u001a\u00020\u00072\u0007\u0010\u0086\u0001\u001a\u00028\u00002\u000e\u0010\u0081\u0001\u001a\t\u0012\u0004\u0012\u00020\u00070\u0082\u0001H\u0002¢\u0006\u0003\u0010\u008b\u0001J\t\u0010\u008c\u0001\u001a\u00020\u0007H\u0014J\t\u0010\u008d\u0001\u001a\u00020\u0007H\u0014J!\u0010\u008e\u0001\u001a\u0004\u0018\u00010\f2\t\u0010\u008f\u0001\u001a\u0004\u0018\u00010\f2\t\u0010\u0090\u0001\u001a\u0004\u0018\u00010\fH\u0002J!\u0010\u0091\u0001\u001a\u0004\u0018\u00010\f2\t\u0010\u008f\u0001\u001a\u0004\u0018\u00010\f2\t\u0010\u0090\u0001\u001a\u0004\u0018\u00010\fH\u0002J!\u0010\u0092\u0001\u001a\u0004\u0018\u00010\f2\t\u0010\u008f\u0001\u001a\u0004\u0018\u00010\f2\t\u0010\u0090\u0001\u001a\u0004\u0018\u00010\fH\u0002J!\u0010\u0093\u0001\u001a\u0004\u0018\u00010\f2\t\u0010\u008f\u0001\u001a\u0004\u0018\u00010\f2\t\u0010\u0090\u0001\u001a\u0004\u0018\u00010\fH\u0002J\u0013\u0010\u0094\u0001\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0003\u0010\u0095\u0001J%\u0010\u0096\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000,H\u0096@ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000ø\u0001\u0000¢\u0006\u0006\b\u0097\u0001\u0010\u0095\u0001JD\u0010\u0098\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000,2\f\u0010s\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001a\u00020\u00042\u0007\u0010\u0099\u0001\u001a\u00020\u0010H\u0082@ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000ø\u0001\u0000¢\u0006\u0006\b\u009a\u0001\u0010\u009b\u0001J\u008c\u0002\u0010\u009c\u0001\u001a\u0003H\u009d\u0001\"\u0005\b\u0001\u0010\u009d\u00012\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010\f2$\u0010\u009f\u0001\u001a\u001f\u0012\u0014\u0012\u00128\u0000¢\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(\u0086\u0001\u0012\u0005\u0012\u0003H\u009d\u00010\u00062V\u0010 \u0001\u001aQ\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00028\u00000\u0014¢\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(¡\u0001\u0012\u0014\u0012\u00120\u0004¢\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(¢\u0001\u0012\u0014\u0012\u00120\u0010¢\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(\u0099\u0001\u0012\u0005\u0012\u0003H\u009d\u0001082\u000f\u0010£\u0001\u001a\n\u0012\u0005\u0012\u0003H\u009d\u00010¤\u00012X\b\u0002\u0010¥\u0001\u001aQ\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00028\u00000\u0014¢\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(¡\u0001\u0012\u0014\u0012\u00120\u0004¢\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(¢\u0001\u0012\u0014\u0012\u00120\u0010¢\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(\u0099\u0001\u0012\u0005\u0012\u0003H\u009d\u000108H\u0082\b¢\u0006\u0003\u0010¦\u0001Jh\u0010§\u0001\u001a\u00020\u00072\f\u0010s\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001a\u00020\u00042\u0007\u0010\u0099\u0001\u001a\u00020\u00102\b\u0010\u009e\u0001\u001a\u00030¨\u00012#\u0010\u009f\u0001\u001a\u001e\u0012\u0014\u0012\u00128\u0000¢\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(\u0086\u0001\u0012\u0004\u0012\u00020\u00070\u00062\u000e\u0010£\u0001\u001a\t\u0012\u0004\u0012\u00020\u00070¤\u0001H\u0082\bJ2\u0010©\u0001\u001a\u00028\u00002\f\u0010s\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001a\u00020\u00042\u0007\u0010\u0099\u0001\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u009b\u0001J \u0010ª\u0001\u001a\u00020\u00072\n\u0010<\u001a\u0006\u0012\u0002\b\u0003092\t\u0010\u008f\u0001\u001a\u0004\u0018\u00010\fH\u0002J \u0010«\u0001\u001a\u00020\u00072\n\u0010<\u001a\u0006\u0012\u0002\b\u0003092\t\u0010\u0086\u0001\u001a\u0004\u0018\u00010\fH\u0014J\u0017\u0010¬\u0001\u001a\u00020\u00072\f\u0010Y\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0002J\u001c\u0010\u00ad\u0001\u001a\u00020\u00072\u0007\u0010\u0086\u0001\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0003\u0010\u0089\u0001J\u001f\u0010®\u0001\u001a\u00020\u001c2\u0007\u0010\u0086\u0001\u001a\u00028\u0000H\u0090@ø\u0001\u0000¢\u0006\u0006\b¯\u0001\u0010\u0089\u0001J\u0082\u0002\u0010°\u0001\u001a\u0003H\u009d\u0001\"\u0005\b\u0001\u0010\u009d\u00012\u0007\u0010\u0086\u0001\u001a\u00028\u00002\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010\f2\u000f\u0010±\u0001\u001a\n\u0012\u0005\u0012\u0003H\u009d\u00010¤\u00012A\u0010 \u0001\u001a<\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00028\u00000\u0014¢\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(¡\u0001\u0012\u0014\u0012\u00120\u0004¢\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(¢\u0001\u0012\u0005\u0012\u0003H\u009d\u00010²\u00012\u000f\u0010£\u0001\u001a\n\u0012\u0005\u0012\u0003H\u009d\u00010¤\u00012o\b\u0002\u0010¥\u0001\u001ah\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00028\u00000\u0014¢\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(¡\u0001\u0012\u0014\u0012\u00120\u0004¢\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(¢\u0001\u0012\u0014\u0012\u00128\u0000¢\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(\u0086\u0001\u0012\u0014\u0012\u00120\u0010¢\u0006\r\b:\u0012\t\b;\u0012\u0005\b\b(´\u0001\u0012\u0005\u0012\u0003H\u009d\u00010³\u0001H\u0084\b¢\u0006\u0003\u0010µ\u0001Jb\u0010¶\u0001\u001a\u00020\u00072\f\u0010s\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001a\u00020\u00042\u0007\u0010\u0086\u0001\u001a\u00028\u00002\u0007\u0010´\u0001\u001a\u00020\u00102\b\u0010\u009e\u0001\u001a\u00030¨\u00012\u000e\u0010±\u0001\u001a\t\u0012\u0004\u0012\u00020\u00070¤\u00012\u000e\u0010£\u0001\u001a\t\u0012\u0004\u0012\u00020\u00070¤\u0001H\u0082\b¢\u0006\u0003\u0010·\u0001J;\u0010¸\u0001\u001a\u00020\u00072\f\u0010s\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001a\u00020\u00042\u0007\u0010\u0086\u0001\u001a\u00028\u00002\u0007\u0010´\u0001\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010¹\u0001J\u000f\u0010º\u0001\u001a\u00020\u001cH\u0010¢\u0006\u0003\b»\u0001J\u0012\u0010º\u0001\u001a\u00020\u001c2\u0007\u0010¼\u0001\u001a\u00020\u0010H\u0003J\n\u0010½\u0001\u001a\u00030¾\u0001H\u0016J\u0010\u0010¿\u0001\u001a\u00030¾\u0001H\u0000¢\u0006\u0003\bÀ\u0001J!\u0010Á\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000,H\u0016ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000¢\u0006\u0006\bÂ\u0001\u0010Ã\u0001J*\u0010Ä\u0001\u001a\b\u0012\u0004\u0012\u00020\u00070,2\u0007\u0010\u0086\u0001\u001a\u00028\u0000H\u0016ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000¢\u0006\u0006\bÅ\u0001\u0010Æ\u0001J(\u0010Ç\u0001\u001a\u00020\u001c2\f\u0010s\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001a\u00020\u00042\u0007\u0010È\u0001\u001a\u00020\u0010H\u0002J(\u0010É\u0001\u001a\u00020\u001c2\f\u0010s\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001a\u00020\u00042\u0007\u0010È\u0001\u001a\u00020\u0010H\u0002J5\u0010Ê\u0001\u001a\u0004\u0018\u00010\f2\f\u0010s\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001a\u00020\u00042\u0007\u0010\u0099\u0001\u001a\u00020\u00102\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010\fH\u0002J5\u0010Ë\u0001\u001a\u0004\u0018\u00010\f2\f\u0010s\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001a\u00020\u00042\u0007\u0010\u0099\u0001\u001a\u00020\u00102\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010\fH\u0002JK\u0010Ì\u0001\u001a\u00020\u00042\f\u0010s\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001a\u00020\u00042\u0007\u0010\u0086\u0001\u001a\u00028\u00002\u0007\u0010´\u0001\u001a\u00020\u00102\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010\f2\u0007\u0010Í\u0001\u001a\u00020\u001cH\u0002¢\u0006\u0003\u0010Î\u0001JK\u0010Ï\u0001\u001a\u00020\u00042\f\u0010s\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001a\u00020\u00042\u0007\u0010\u0086\u0001\u001a\u00028\u00002\u0007\u0010´\u0001\u001a\u00020\u00102\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010\f2\u0007\u0010Í\u0001\u001a\u00020\u001cH\u0002¢\u0006\u0003\u0010Î\u0001J\u0012\u0010Ð\u0001\u001a\u00020\u00072\u0007\u0010Ñ\u0001\u001a\u00020\u0010H\u0002J\u0012\u0010Ò\u0001\u001a\u00020\u00072\u0007\u0010Ñ\u0001\u001a\u00020\u0010H\u0002J\u0017\u0010Ó\u0001\u001a\u00020\u00072\u0006\u0010u\u001a\u00020\u0010H\u0000¢\u0006\u0003\bÔ\u0001J$\u0010Õ\u0001\u001a\u00020\u0007*\u00030¨\u00012\f\u0010s\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001a\u00020\u0004H\u0002J$\u0010Ö\u0001\u001a\u00020\u0007*\u00030¨\u00012\f\u0010s\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001a\u00020\u0004H\u0002J\u000e\u0010×\u0001\u001a\u00020\u0007*\u00030¨\u0001H\u0002J\u000e\u0010Ø\u0001\u001a\u00020\u0007*\u00030¨\u0001H\u0002J\u0017\u0010Ù\u0001\u001a\u00020\u0007*\u00030¨\u00012\u0007\u0010Ú\u0001\u001a\u00020\u001cH\u0002J\u001c\u0010Û\u0001\u001a\u00020\u001c*\u00020\f2\u0007\u0010\u0086\u0001\u001a\u00028\u0000H\u0002¢\u0006\u0003\u0010Ü\u0001J#\u0010Ý\u0001\u001a\u00020\u001c*\u00020\f2\f\u0010s\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010t\u001a\u00020\u0004H\u0002R\u0011\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0082\u0004R\t\u0010\r\u001a\u00020\u000eX\u0082\u0004R\u0014\u0010\u000f\u001a\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00140\u000bX\u0082\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00168DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0082\u0004R\t\u0010\u001a\u001a\u00020\u000eX\u0082\u0004R\u001a\u0010\u001b\u001a\u00020\u001c8VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001b\u0010\u001fR\u001a\u0010 \u001a\u00020\u001c8VX\u0097\u0004¢\u0006\f\u0012\u0004\b!\u0010\u001e\u001a\u0004\b \u0010\u001fR\u0014\u0010\"\u001a\u00020\u001c8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u001fR\u001a\u0010#\u001a\u00020\u001c8VX\u0097\u0004¢\u0006\f\u0012\u0004\b$\u0010\u001e\u001a\u0004\b#\u0010\u001fR\u0014\u0010%\u001a\u00020\u001c8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010\u001fR \u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000'8VX\u0096\u0004¢\u0006\f\u0012\u0004\b(\u0010\u001e\u001a\u0004\b)\u0010*R)\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000,0'8VX\u0096\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b-\u0010\u001e\u001a\u0004\b.\u0010*R\"\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000'8VX\u0096\u0004¢\u0006\f\u0012\u0004\b0\u0010\u001e\u001a\u0004\b1\u0010*R,\u00102\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0000038VX\u0096\u0004¢\u0006\f\u0012\u0004\b4\u0010\u001e\u001a\u0004\b5\u00106R*\u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\b8\u0000X\u0081\u0004¢\u0006\u0002\n\u0000Ru\u00107\u001ac\u0012\u0017\u0012\u0015\u0012\u0002\b\u000309¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(<\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(=\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00070\u0006\u0018\u000108j\u0004\u0018\u0001`?X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b@\u0010\u001eR\u0014\u0010A\u001a\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bB\u0010\u0018R\u0015\u0010C\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00140\u000bX\u0082\u0004R\t\u0010D\u001a\u00020\u000eX\u0082\u0004R\u0014\u0010E\u001a\u00020\u00108@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bF\u0010\u0012R\u0014\u0010G\u001a\u00020\u00168DX\u0084\u0004¢\u0006\u0006\u001a\u0004\bH\u0010\u0018R\u0015\u0010I\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00140\u000bX\u0082\u0004R\t\u0010J\u001a\u00020\u000eX\u0082\u0004R\u0014\u0010K\u001a\u00020\u00108@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bL\u0010\u0012R\u0018\u0010M\u001a\u00020\u001c*\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bM\u0010NR\u0018\u0010O\u001a\u00020\u001c*\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bO\u0010N\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006à\u0001"}, d2 = {"Lkotlinx/coroutines/channels/BufferedChannel;", "E", "Lkotlinx/coroutines/channels/Channel;", "capacity", "", "onUndeliveredElement", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "(ILkotlin/jvm/functions/Function1;)V", "_closeCause", "Lkotlinx/atomicfu/AtomicRef;", "", "bufferEnd", "Lkotlinx/atomicfu/AtomicLong;", "bufferEndCounter", "", "getBufferEndCounter", "()J", "bufferEndSegment", "Lkotlinx/coroutines/channels/ChannelSegment;", "closeCause", "", "getCloseCause", "()Ljava/lang/Throwable;", "closeHandler", "completedExpandBuffersAndPauseFlag", "isClosedForReceive", "", "isClosedForReceive$annotations", "()V", "()Z", "isClosedForSend", "isClosedForSend$annotations", "isConflatedDropOldest", "isEmpty", "isEmpty$annotations", "isRendezvousOrUnlimited", "onReceive", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive$annotations", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceiveCatching", "Lkotlinx/coroutines/channels/ChannelResult;", "getOnReceiveCatching$annotations", "getOnReceiveCatching", "onReceiveOrNull", "getOnReceiveOrNull$annotations", "getOnReceiveOrNull", "onSend", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend$annotations", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "onUndeliveredElementReceiveCancellationConstructor", "Lkotlin/Function3;", "Lkotlinx/coroutines/selects/SelectInstance;", "Lkotlin/ParameterName;", "name", "select", "param", "internalResult", "Lkotlinx/coroutines/selects/OnCancellationConstructor;", "getOnUndeliveredElementReceiveCancellationConstructor$annotations", "receiveException", "getReceiveException", "receiveSegment", "receivers", "receiversCounter", "getReceiversCounter$kotlinx_coroutines_core", "sendException", "getSendException", "sendSegment", "sendersAndCloseStatus", "sendersCounter", "getSendersCounter$kotlinx_coroutines_core", "isClosedForReceive0", "(J)Z", "isClosedForSend0", "bufferOrRendezvousSend", "curSenders", "cancel", "cause", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "cancelImpl", "cancelImpl$kotlinx_coroutines_core", "cancelSuspendedReceiveRequests", "lastSegment", "checkSegmentStructureInvariants", "close", "closeLinkedList", "closeOrCancelImpl", "completeCancel", "sendersCur", "completeClose", "completeCloseOrCancel", "dropFirstElementUntilTheSpecifiedCellIsInTheBuffer", "globalCellIndex", "expandBuffer", "findSegmentBufferEnd", "id", "startFrom", "currentBufferEndCounter", "findSegmentReceive", "findSegmentSend", "hasElements", "hasElements$kotlinx_coroutines_core", "incCompletedExpandBufferAttempts", "nAttempts", "invokeCloseHandler", "invokeOnClose", "handler", "isCellNonEmpty", "segment", "index", "globalIndex", "isClosed", "sendersAndCloseStatusCur", "iterator", "Lkotlinx/coroutines/channels/ChannelIterator;", "markAllEmptyCellsAsClosed", "markCancellationStarted", "markCancelled", "markClosed", "moveSegmentBufferEndToSpecifiedOrLast", "onClosedIdempotent", "onClosedReceiveCatchingOnNoWaiterSuspend", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "onClosedReceiveOnNoWaiterSuspend", "onClosedSelectOnReceive", "onClosedSelectOnSend", "element", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)V", "onClosedSend", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onClosedSendOnNoWaiterSuspend", "(Ljava/lang/Object;Lkotlinx/coroutines/CancellableContinuation;)V", "onReceiveDequeued", "onReceiveEnqueued", "processResultSelectReceive", "ignoredParam", "selectResult", "processResultSelectReceiveCatching", "processResultSelectReceiveOrNull", "processResultSelectSend", "receive", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveCatching", "receiveCatching-JP2dKIU", "receiveCatchingOnNoWaiterSuspend", "r", "receiveCatchingOnNoWaiterSuspend-GKJJFZk", "(Lkotlinx/coroutines/channels/ChannelSegment;IJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveImpl", "R", "waiter", "onElementRetrieved", "onSuspend", "segm", "i", "onClosed", "Lkotlin/Function0;", "onNoWaiterSuspend", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "receiveImplOnNoWaiter", "Lkotlinx/coroutines/Waiter;", "receiveOnNoWaiterSuspend", "registerSelectForReceive", "registerSelectForSend", "removeUnprocessedElements", "send", "sendBroadcast", "sendBroadcast$kotlinx_coroutines_core", "sendImpl", "onRendezvousOrBuffered", "Lkotlin/Function2;", "Lkotlin/Function4;", "s", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "sendImplOnNoWaiter", "(Lkotlinx/coroutines/channels/ChannelSegment;ILjava/lang/Object;JLkotlinx/coroutines/Waiter;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "sendOnNoWaiterSuspend", "(Lkotlinx/coroutines/channels/ChannelSegment;ILjava/lang/Object;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shouldSendSuspend", "shouldSendSuspend$kotlinx_coroutines_core", "curSendersAndCloseStatus", "toString", "", "toStringDebug", "toStringDebug$kotlinx_coroutines_core", "tryReceive", "tryReceive-PtdJZtk", "()Ljava/lang/Object;", "trySend", "trySend-JP2dKIU", "(Ljava/lang/Object;)Ljava/lang/Object;", "updateCellExpandBuffer", "b", "updateCellExpandBufferSlow", "updateCellReceive", "updateCellReceiveSlow", "updateCellSend", "closed", "(Lkotlinx/coroutines/channels/ChannelSegment;ILjava/lang/Object;JLjava/lang/Object;Z)I", "updateCellSendSlow", "updateReceiversCounterIfLower", "value", "updateSendersCounterIfLower", "waitExpandBufferCompletion", "waitExpandBufferCompletion$kotlinx_coroutines_core", "prepareReceiverForSuspension", "prepareSenderForSuspension", "resumeReceiverOnClosedChannel", "resumeSenderOnCancelledChannel", "resumeWaiterOnClosedChannel", "receiver", "tryResumeReceiver", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "tryResumeSender", "BufferedChannelIterator", "SendBroadcast", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public class BufferedChannel<E> implements Channel<E> {
    @Volatile
    private volatile Object _closeCause;
    @Volatile
    private volatile long bufferEnd;
    @Volatile
    private volatile Object bufferEndSegment;
    private final int capacity;
    @Volatile
    private volatile Object closeHandler;
    @Volatile
    private volatile long completedExpandBuffersAndPauseFlag;
    public final Function1<E, Unit> onUndeliveredElement;
    private final Function3<SelectInstance<?>, Object, Object, Function1<Throwable, Unit>> onUndeliveredElementReceiveCancellationConstructor;
    @Volatile
    private volatile Object receiveSegment;
    @Volatile
    private volatile long receivers;
    @Volatile
    private volatile Object sendSegment;
    @Volatile
    private volatile long sendersAndCloseStatus;
    private static final AtomicLongFieldUpdater sendersAndCloseStatus$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "sendersAndCloseStatus");
    private static final AtomicLongFieldUpdater receivers$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "receivers");
    private static final AtomicLongFieldUpdater bufferEnd$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "bufferEnd");
    private static final AtomicLongFieldUpdater completedExpandBuffersAndPauseFlag$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "completedExpandBuffersAndPauseFlag");
    private static final AtomicReferenceFieldUpdater sendSegment$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "sendSegment");
    private static final AtomicReferenceFieldUpdater receiveSegment$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "receiveSegment");
    private static final AtomicReferenceFieldUpdater bufferEndSegment$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "bufferEndSegment");
    private static final AtomicReferenceFieldUpdater _closeCause$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "_closeCause");
    private static final AtomicReferenceFieldUpdater closeHandler$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "closeHandler");

    private final Object getAndUpdate$atomicfu(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, Function1<Object, ? extends Object> function1, Object obj) {
        Object obj2;
        do {
            obj2 = atomicReferenceFieldUpdater.get(obj);
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, obj, obj2, function1.invoke(obj2)));
        return obj2;
    }

    public static /* synthetic */ void getOnReceive$annotations() {
    }

    public static /* synthetic */ void getOnReceiveCatching$annotations() {
    }

    public static /* synthetic */ void getOnReceiveOrNull$annotations() {
    }

    public static /* synthetic */ void getOnSend$annotations() {
    }

    private static /* synthetic */ void getOnUndeliveredElementReceiveCancellationConstructor$annotations() {
    }

    public static /* synthetic */ void isClosedForReceive$annotations() {
    }

    public static /* synthetic */ void isClosedForSend$annotations() {
    }

    public static /* synthetic */ void isEmpty$annotations() {
    }

    private final void loop$atomicfu(AtomicLongFieldUpdater atomicLongFieldUpdater, Function1<? super Long, Unit> function1, Object obj) {
        while (true) {
            function1.invoke(Long.valueOf(atomicLongFieldUpdater.get(obj)));
        }
    }

    private final void loop$atomicfu(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, Function1<Object, Unit> function1, Object obj) {
        while (true) {
            function1.invoke(atomicReferenceFieldUpdater.get(obj));
        }
    }

    private final void update$atomicfu(AtomicLongFieldUpdater atomicLongFieldUpdater, Function1<? super Long, Long> function1, Object obj) {
        long j;
        do {
            j = atomicLongFieldUpdater.get(obj);
        } while (!atomicLongFieldUpdater.compareAndSet(obj, j, function1.invoke(Long.valueOf(j)).longValue()));
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public Object receive(Continuation<? super E> continuation) {
        return receive$suspendImpl(this, continuation);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* renamed from: receiveCatching-JP2dKIU  reason: not valid java name */
    public Object mo1763receiveCatchingJP2dKIU(Continuation<? super ChannelResult<? extends E>> continuation) {
        return m1761receiveCatchingJP2dKIU$suspendImpl(this, continuation);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public Object send(E e, Continuation<? super Unit> continuation) {
        return send$suspendImpl(this, e, continuation);
    }

    public Object sendBroadcast$kotlinx_coroutines_core(E e, Continuation<? super Boolean> continuation) {
        return sendBroadcast$suspendImpl(this, e, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BufferedChannel(int capacity, Function1<? super E, Unit> function1) {
        long initialBufferEnd;
        ChannelSegment channelSegment;
        Symbol symbol;
        this.capacity = capacity;
        this.onUndeliveredElement = function1;
        if (this.capacity >= 0) {
            initialBufferEnd = BufferedChannelKt.initialBufferEnd(this.capacity);
            this.bufferEnd = initialBufferEnd;
            this.completedExpandBuffersAndPauseFlag = getBufferEndCounter();
            ChannelSegment firstSegment = new ChannelSegment(0L, null, this, 3);
            this.sendSegment = firstSegment;
            this.receiveSegment = firstSegment;
            if (isRendezvousOrUnlimited()) {
                channelSegment = BufferedChannelKt.NULL_SEGMENT;
                Intrinsics.checkNotNull(channelSegment, "null cannot be cast to non-null type kotlinx.coroutines.channels.ChannelSegment<E of kotlinx.coroutines.channels.BufferedChannel>");
            } else {
                channelSegment = firstSegment;
            }
            this.bufferEndSegment = channelSegment;
            this.onUndeliveredElementReceiveCancellationConstructor = this.onUndeliveredElement != null ? (Function3) new Function3<SelectInstance<?>, Object, Object, Function1<? super Throwable, ? extends Unit>>(this) { // from class: kotlinx.coroutines.channels.BufferedChannel$onUndeliveredElementReceiveCancellationConstructor$1$1
                final /* synthetic */ BufferedChannel<E> this$0;

                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                    this.this$0 = this;
                }

                @Override // kotlin.jvm.functions.Function3
                public final Function1<Throwable, Unit> invoke(final SelectInstance<?> selectInstance, Object obj, final Object element) {
                    final BufferedChannel<E> bufferedChannel = this.this$0;
                    return new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.channels.BufferedChannel$onUndeliveredElementReceiveCancellationConstructor$1$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                            invoke2(th);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke  reason: avoid collision after fix types in other method */
                        public final void invoke2(Throwable it) {
                            if (element != BufferedChannelKt.getCHANNEL_CLOSED()) {
                                OnUndeliveredElementKt.callUndeliveredElement(bufferedChannel.onUndeliveredElement, element, selectInstance.getContext());
                            }
                        }
                    };
                }
            } : null;
            symbol = BufferedChannelKt.NO_CLOSE_CAUSE;
            this._closeCause = symbol;
            return;
        }
        throw new IllegalArgumentException(("Invalid channel capacity: " + this.capacity + ", should be >=0").toString());
    }

    public /* synthetic */ BufferedChannel(int i, Function1 function1, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? null : function1);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
    public boolean offer(E e) {
        return Channel.DefaultImpls.offer(this, e);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'tryReceive'. Please note that the provided replacement does not rethrow channel's close cause as 'poll' did, for the precise replacement please refer to the 'poll' documentation", replaceWith = @ReplaceWith(expression = "tryReceive().getOrNull()", imports = {}))
    public E poll() {
        return (E) Channel.DefaultImpls.poll(this);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in favor of 'receiveCatching'. Please note that the provided replacement does not rethrow channel's close cause as 'receiveOrNull' did, for the detailed replacement please refer to the 'receiveOrNull' documentation", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}))
    public Object receiveOrNull(Continuation<? super E> continuation) {
        return Channel.DefaultImpls.receiveOrNull(this, continuation);
    }

    public final long getSendersCounter$kotlinx_coroutines_core() {
        long $this$sendersCounter$iv = sendersAndCloseStatus$FU.get(this);
        return $this$sendersCounter$iv & 1152921504606846975L;
    }

    public final long getReceiversCounter$kotlinx_coroutines_core() {
        return receivers$FU.get(this);
    }

    private final long getBufferEndCounter() {
        return bufferEnd$FU.get(this);
    }

    private final boolean isRendezvousOrUnlimited() {
        long it = getBufferEndCounter();
        return it == 0 || it == Long.MAX_VALUE;
    }

    static /* synthetic */ <E> Object send$suspendImpl(BufferedChannel<E> bufferedChannel, E e, Continuation<? super Unit> continuation) {
        ChannelSegment segment$iv;
        ChannelSegment segment$iv2 = (ChannelSegment) sendSegment$FU.get(bufferedChannel);
        while (true) {
            long sendersAndCloseStatusCur$iv = sendersAndCloseStatus$FU.getAndIncrement(bufferedChannel);
            long $this$sendersCounter$iv$iv = sendersAndCloseStatusCur$iv & 1152921504606846975L;
            boolean closed$iv = bufferedChannel.isClosedForSend0(sendersAndCloseStatusCur$iv);
            long id$iv = $this$sendersCounter$iv$iv / BufferedChannelKt.SEGMENT_SIZE;
            int i$iv = (int) ($this$sendersCounter$iv$iv % BufferedChannelKt.SEGMENT_SIZE);
            if (segment$iv2.id != id$iv) {
                ChannelSegment segment$iv3 = bufferedChannel.findSegmentSend(id$iv, segment$iv2);
                if (segment$iv3 != null) {
                    segment$iv = segment$iv3;
                } else if (closed$iv) {
                    Object onClosedSend = bufferedChannel.onClosedSend(e, continuation);
                    if (onClosedSend == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return onClosedSend;
                    }
                }
            } else {
                segment$iv = segment$iv2;
            }
            switch (bufferedChannel.updateCellSend(segment$iv, i$iv, e, $this$sendersCounter$iv$iv, null, closed$iv)) {
                case 0:
                    segment$iv.cleanPrev();
                    break;
                case 2:
                    if (!closed$iv) {
                        if (DebugKt.getASSERTIONS_ENABLED()) {
                            throw new AssertionError();
                        }
                    } else {
                        segment$iv.onSlotCleaned();
                        Object onClosedSend2 = bufferedChannel.onClosedSend(e, continuation);
                        if (onClosedSend2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                            return onClosedSend2;
                        }
                    }
                    break;
                case 3:
                    ChannelSegment segm = segment$iv;
                    Object sendOnNoWaiterSuspend = bufferedChannel.sendOnNoWaiterSuspend(segm, i$iv, e, $this$sendersCounter$iv$iv, continuation);
                    if (sendOnNoWaiterSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return sendOnNoWaiterSuspend;
                    }
                    break;
                case 4:
                    if ($this$sendersCounter$iv$iv < bufferedChannel.getReceiversCounter$kotlinx_coroutines_core()) {
                        segment$iv.cleanPrev();
                    }
                    Object onClosedSend3 = bufferedChannel.onClosedSend(e, continuation);
                    if (onClosedSend3 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return onClosedSend3;
                    }
                    break;
                case 5:
                    segment$iv.cleanPrev();
                    break;
            }
            segment$iv2 = segment$iv;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object onClosedSend(E e, Continuation<? super Unit> continuation) {
        Throwable th;
        UndeliveredElementException it;
        UndeliveredElementException undeliveredElementException;
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellable$iv.initCancellability();
        CancellableContinuationImpl continuation2 = cancellable$iv;
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        if (function1 != null && (it = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, e, null, 2, null)) != null) {
            ExceptionsKt.addSuppressed(it, getSendException());
            CancellableContinuationImpl $this$resumeWithStackTrace$iv = continuation2;
            Result.Companion companion = Result.Companion;
            if (DebugKt.getRECOVER_STACK_TRACES() && ($this$resumeWithStackTrace$iv instanceof CoroutineStackFrame)) {
                undeliveredElementException = StackTraceRecoveryKt.recoverFromStackFrame(it, $this$resumeWithStackTrace$iv);
            } else {
                undeliveredElementException = it;
            }
            $this$resumeWithStackTrace$iv.resumeWith(Result.m256constructorimpl(ResultKt.createFailure(undeliveredElementException)));
        } else {
            CancellableContinuationImpl $this$resumeWithStackTrace$iv2 = continuation2;
            Throwable exception$iv = getSendException();
            Result.Companion companion2 = Result.Companion;
            if (DebugKt.getRECOVER_STACK_TRACES() && ($this$resumeWithStackTrace$iv2 instanceof CoroutineStackFrame)) {
                th = StackTraceRecoveryKt.recoverFromStackFrame(exception$iv, $this$resumeWithStackTrace$iv2);
            } else {
                th = exception$iv;
            }
            $this$resumeWithStackTrace$iv2.resumeWith(Result.m256constructorimpl(ResultKt.createFailure(th)));
        }
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object sendOnNoWaiterSuspend(ChannelSegment<E> channelSegment, int index, E e, long s, Continuation<? super Unit> continuation) {
        ChannelSegment segment$iv$iv;
        ChannelSegment segment$iv$iv2;
        Continuation<? super Unit> continuation2 = continuation;
        CancellableContinuationImpl cancellable$iv = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation2));
        try {
            try {
                try {
                    try {
                        switch (updateCellSend(channelSegment, index, e, s, cancellable$iv, false)) {
                            case 0:
                                channelSegment.cleanPrev();
                                Result.Companion companion = Result.Companion;
                                cancellable$iv.resumeWith(Result.m256constructorimpl(Unit.INSTANCE));
                                break;
                            case 1:
                                Result.Companion companion2 = Result.Companion;
                                cancellable$iv.resumeWith(Result.m256constructorimpl(Unit.INSTANCE));
                                break;
                            case 2:
                                prepareSenderForSuspension(cancellable$iv, channelSegment, index);
                                break;
                            case 3:
                            default:
                                throw new IllegalStateException("unexpected".toString());
                            case 4:
                                if (s < getReceiversCounter$kotlinx_coroutines_core()) {
                                    channelSegment.cleanPrev();
                                }
                                onClosedSendOnNoWaiterSuspend(e, cancellable$iv);
                                break;
                            case 5:
                                try {
                                    channelSegment.cleanPrev();
                                    ChannelSegment segment$iv$iv3 = (ChannelSegment) sendSegment$FU.get(this);
                                    while (true) {
                                        long $this$sendersCounter$iv$iv$iv = sendersAndCloseStatus$FU.getAndIncrement(this);
                                        long $this$sendersCounter$iv$iv$iv2 = $this$sendersCounter$iv$iv$iv & 1152921504606846975L;
                                        boolean closed$iv$iv = isClosedForSend0($this$sendersCounter$iv$iv$iv);
                                        long id$iv$iv = $this$sendersCounter$iv$iv$iv2 / BufferedChannelKt.SEGMENT_SIZE;
                                        int i$iv$iv = (int) ($this$sendersCounter$iv$iv$iv2 % BufferedChannelKt.SEGMENT_SIZE);
                                        if (segment$iv$iv3.id != id$iv$iv) {
                                            try {
                                                ChannelSegment segment$iv$iv4 = findSegmentSend(id$iv$iv, segment$iv$iv3);
                                                if (segment$iv$iv4 != null) {
                                                    segment$iv$iv = segment$iv$iv4;
                                                } else if (closed$iv$iv) {
                                                    onClosedSendOnNoWaiterSuspend(e, cancellable$iv);
                                                    break;
                                                }
                                            } catch (Throwable th) {
                                                e$iv = th;
                                                cancellable$iv.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                                                throw e$iv;
                                            }
                                        } else {
                                            segment$iv$iv = segment$iv$iv3;
                                        }
                                        ChannelSegment segment$iv$iv5 = segment$iv$iv;
                                        Continuation<? super Unit> continuation3 = continuation2;
                                        switch (updateCellSend(segment$iv$iv, i$iv$iv, e, $this$sendersCounter$iv$iv$iv2, cancellable$iv, closed$iv$iv)) {
                                            case 0:
                                                segment$iv$iv5.cleanPrev();
                                                Result.Companion companion3 = Result.Companion;
                                                cancellable$iv.resumeWith(Result.m256constructorimpl(Unit.INSTANCE));
                                                break;
                                            case 1:
                                                Result.Companion companion4 = Result.Companion;
                                                cancellable$iv.resumeWith(Result.m256constructorimpl(Unit.INSTANCE));
                                                break;
                                            case 2:
                                                if (!closed$iv$iv) {
                                                    CancellableContinuationImpl cancellableContinuationImpl = cancellable$iv instanceof Waiter ? cancellable$iv : null;
                                                    if (cancellableContinuationImpl != null) {
                                                        prepareSenderForSuspension(cancellableContinuationImpl, segment$iv$iv5, i$iv$iv);
                                                    }
                                                    break;
                                                } else {
                                                    segment$iv$iv5.onSlotCleaned();
                                                    onClosedSendOnNoWaiterSuspend(e, cancellable$iv);
                                                    break;
                                                }
                                            case 3:
                                                throw new IllegalStateException("unexpected".toString());
                                            case 4:
                                                if ($this$sendersCounter$iv$iv$iv2 < getReceiversCounter$kotlinx_coroutines_core()) {
                                                    segment$iv$iv5.cleanPrev();
                                                }
                                                onClosedSendOnNoWaiterSuspend(e, cancellable$iv);
                                                break;
                                            case 5:
                                                segment$iv$iv5.cleanPrev();
                                                segment$iv$iv2 = segment$iv$iv5;
                                                break;
                                            default:
                                                segment$iv$iv2 = segment$iv$iv5;
                                                break;
                                        }
                                        segment$iv$iv3 = segment$iv$iv2;
                                        continuation2 = continuation3;
                                    }
                                } catch (Throwable th2) {
                                    e$iv = th2;
                                }
                        }
                        Object result = cancellable$iv.getResult();
                        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                            DebugProbesKt.probeCoroutineSuspended(continuation);
                        }
                        return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
                    } catch (Throwable th3) {
                        e$iv = th3;
                    }
                } catch (Throwable th4) {
                    e$iv = th4;
                }
            } catch (Throwable th5) {
                e$iv = th5;
            }
        } catch (Throwable th6) {
            e$iv = th6;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void prepareSenderForSuspension(Waiter $this$prepareSenderForSuspension, ChannelSegment<E> channelSegment, int index) {
        $this$prepareSenderForSuspension.invokeOnCancellation(channelSegment, BufferedChannelKt.SEGMENT_SIZE + index);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onClosedSendOnNoWaiterSuspend(E e, CancellableContinuation<? super Unit> cancellableContinuation) {
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        if (function1 != null) {
            OnUndeliveredElementKt.callUndeliveredElement(function1, e, cancellableContinuation.getContext());
        }
        CancellableContinuation<? super Unit> cancellableContinuation2 = cancellableContinuation;
        Throwable exception$iv = getSendException();
        if (DebugKt.getRECOVER_STACK_TRACES() && (cancellableContinuation instanceof CoroutineStackFrame)) {
            exception$iv = StackTraceRecoveryKt.recoverFromStackFrame(exception$iv, (CoroutineStackFrame) cancellableContinuation);
        }
        Result.Companion companion = Result.Companion;
        cancellableContinuation2.resumeWith(Result.m256constructorimpl(ResultKt.createFailure(exception$iv)));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // kotlinx.coroutines.channels.SendChannel
    /* renamed from: trySend-JP2dKIU */
    public Object mo1759trySendJP2dKIU(E e) {
        Object waiter$iv;
        ChannelSegment segment$iv;
        ChannelSegment segment$iv2;
        if (shouldSendSuspend(sendersAndCloseStatus$FU.get(this))) {
            return ChannelResult.Companion.m1783failurePtdJZtk();
        }
        waiter$iv = BufferedChannelKt.INTERRUPTED_SEND;
        int $i$f$sendImpl = 0;
        ChannelSegment segment$iv3 = (ChannelSegment) sendSegment$FU.get(this);
        while (true) {
            long sendersAndCloseStatusCur$iv = sendersAndCloseStatus$FU.getAndIncrement(this);
            long $this$sendersCounter$iv$iv = sendersAndCloseStatusCur$iv & 1152921504606846975L;
            boolean closed$iv = isClosedForSend0(sendersAndCloseStatusCur$iv);
            long id$iv = $this$sendersCounter$iv$iv / BufferedChannelKt.SEGMENT_SIZE;
            int i$iv = (int) ($this$sendersCounter$iv$iv % BufferedChannelKt.SEGMENT_SIZE);
            if (segment$iv3.id != id$iv) {
                ChannelSegment segment$iv4 = findSegmentSend(id$iv, segment$iv3);
                if (segment$iv4 != null) {
                    segment$iv = segment$iv4;
                } else if (closed$iv) {
                    return ChannelResult.Companion.m1782closedJP2dKIU(getSendException());
                }
            } else {
                segment$iv = segment$iv3;
            }
            ChannelSegment segment$iv5 = segment$iv;
            int $i$f$sendImpl2 = $i$f$sendImpl;
            switch (updateCellSend(segment$iv, i$iv, e, $this$sendersCounter$iv$iv, waiter$iv, closed$iv)) {
                case 0:
                    segment$iv5.cleanPrev();
                    return ChannelResult.Companion.m1784successJP2dKIU(Unit.INSTANCE);
                case 1:
                    return ChannelResult.Companion.m1784successJP2dKIU(Unit.INSTANCE);
                case 2:
                    if (closed$iv) {
                        segment$iv5.onSlotCleaned();
                        return ChannelResult.Companion.m1782closedJP2dKIU(getSendException());
                    }
                    Waiter waiter = waiter$iv instanceof Waiter ? (Waiter) waiter$iv : null;
                    if (waiter != null) {
                        segment$iv2 = segment$iv5;
                        prepareSenderForSuspension(waiter, segment$iv2, i$iv);
                    } else {
                        segment$iv2 = segment$iv5;
                    }
                    ChannelSegment segm = segment$iv2;
                    segm.onSlotCleaned();
                    return ChannelResult.Companion.m1783failurePtdJZtk();
                case 3:
                    throw new IllegalStateException("unexpected".toString());
                case 4:
                    if ($this$sendersCounter$iv$iv < getReceiversCounter$kotlinx_coroutines_core()) {
                        segment$iv5.cleanPrev();
                    }
                    return ChannelResult.Companion.m1782closedJP2dKIU(getSendException());
                case 5:
                    segment$iv5.cleanPrev();
                    break;
            }
            segment$iv3 = segment$iv5;
            $i$f$sendImpl = $i$f$sendImpl2;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    static /* synthetic */ <E> Object sendBroadcast$suspendImpl(BufferedChannel<E> bufferedChannel, E e, Continuation<? super Boolean> continuation) {
        CancellableContinuationImpl cancellable$iv;
        ChannelSegment segment$iv;
        boolean z = false;
        CancellableContinuationImpl cancellable$iv2 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellable$iv2.initCancellability();
        CancellableContinuation cont = cancellable$iv2;
        if (!(bufferedChannel.onUndeliveredElement == null)) {
            throw new IllegalStateException("the `onUndeliveredElement` feature is unsupported for `sendBroadcast(e)`".toString());
        }
        Object waiter$iv = new SendBroadcast(cont);
        ChannelSegment segment$iv2 = (ChannelSegment) sendSegment$FU.get(bufferedChannel);
        while (true) {
            long sendersAndCloseStatusCur$iv = sendersAndCloseStatus$FU.getAndIncrement(bufferedChannel);
            long $this$sendersCounter$iv$iv = sendersAndCloseStatusCur$iv & 1152921504606846975L;
            boolean closed$iv = bufferedChannel.isClosedForSend0(sendersAndCloseStatusCur$iv);
            long id$iv = $this$sendersCounter$iv$iv / BufferedChannelKt.SEGMENT_SIZE;
            cancellable$iv = cancellable$iv2;
            int i$iv = (int) ($this$sendersCounter$iv$iv % BufferedChannelKt.SEGMENT_SIZE);
            if (segment$iv2.id != id$iv) {
                ChannelSegment segment$iv3 = bufferedChannel.findSegmentSend(id$iv, segment$iv2);
                if (segment$iv3 != null) {
                    segment$iv = segment$iv3;
                } else if (closed$iv) {
                    Result.Companion companion = Result.Companion;
                    cont.resumeWith(Result.m256constructorimpl(Boxing.boxBoolean(false)));
                } else {
                    cancellable$iv2 = cancellable$iv;
                }
            } else {
                segment$iv = segment$iv2;
            }
            boolean z2 = z;
            Object waiter$iv2 = waiter$iv;
            switch (bufferedChannel.updateCellSend(segment$iv, i$iv, e, $this$sendersCounter$iv$iv, waiter$iv, closed$iv)) {
                case 0:
                    segment$iv.cleanPrev();
                    Result.Companion companion2 = Result.Companion;
                    cont.resumeWith(Result.m256constructorimpl(Boxing.boxBoolean(true)));
                    break;
                case 1:
                    Result.Companion companion3 = Result.Companion;
                    cont.resumeWith(Result.m256constructorimpl(Boxing.boxBoolean(true)));
                    break;
                case 2:
                    if (closed$iv) {
                        segment$iv.onSlotCleaned();
                        Result.Companion companion4 = Result.Companion;
                        cont.resumeWith(Result.m256constructorimpl(Boxing.boxBoolean(false)));
                        break;
                    } else {
                        SendBroadcast sendBroadcast = waiter$iv2 instanceof Waiter ? (Waiter) waiter$iv2 : null;
                        if (sendBroadcast != null) {
                            bufferedChannel.prepareSenderForSuspension(sendBroadcast, segment$iv, i$iv);
                        }
                        break;
                    }
                case 3:
                    throw new IllegalStateException("unexpected".toString());
                case 4:
                    if ($this$sendersCounter$iv$iv < bufferedChannel.getReceiversCounter$kotlinx_coroutines_core()) {
                        segment$iv.cleanPrev();
                    }
                    Result.Companion companion5 = Result.Companion;
                    cont.resumeWith(Result.m256constructorimpl(Boxing.boxBoolean(false)));
                    break;
                case 5:
                    segment$iv.cleanPrev();
                    break;
            }
            waiter$iv = waiter$iv2;
            segment$iv2 = segment$iv;
            cancellable$iv2 = cancellable$iv;
            z = z2;
        }
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: BufferedChannel.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u001d\u0010\b\u001a\u00020\t2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\f\u001a\u00020\rH\u0096\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/channels/BufferedChannel$SendBroadcast;", "Lkotlinx/coroutines/Waiter;", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Lkotlinx/coroutines/CancellableContinuation;)V", "getCont", "()Lkotlinx/coroutines/CancellableContinuation;", "invokeOnCancellation", "", "segment", "Lkotlinx/coroutines/internal/Segment;", "index", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class SendBroadcast implements Waiter {
        private final /* synthetic */ CancellableContinuationImpl<Boolean> $$delegate_0;
        private final CancellableContinuation<Boolean> cont;

        @Override // kotlinx.coroutines.Waiter
        public void invokeOnCancellation(Segment<?> segment, int i) {
            this.$$delegate_0.invokeOnCancellation(segment, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public SendBroadcast(CancellableContinuation<? super Boolean> cancellableContinuation) {
            this.cont = cancellableContinuation;
            Intrinsics.checkNotNull(cancellableContinuation, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuationImpl<kotlin.Boolean>");
            this.$$delegate_0 = (CancellableContinuationImpl) cancellableContinuation;
        }

        public final CancellableContinuation<Boolean> getCont() {
            return this.cont;
        }
    }

    public static /* synthetic */ Object sendImpl$default(BufferedChannel $this, Object element, Object waiter, Function0 onRendezvousOrBuffered, Function2 onSuspend, Function0 onClosed, Function4 onNoWaiterSuspend, int i, Object obj) {
        Function4 onNoWaiterSuspend2;
        ChannelSegment segment;
        int i2;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendImpl");
        }
        if ((i & 32) == 0) {
            onNoWaiterSuspend2 = onNoWaiterSuspend;
        } else {
            onNoWaiterSuspend2 = new Function4() { // from class: kotlinx.coroutines.channels.BufferedChannel$sendImpl$1
                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3, Object p4) {
                    return invoke((ChannelSegment<int>) p1, ((Number) p2).intValue(), (int) p3, ((Number) p4).longValue());
                }

                public final Void invoke(ChannelSegment<E> channelSegment, int i3, E e, long j) {
                    throw new IllegalStateException("unexpected".toString());
                }
            };
        }
        ChannelSegment segment2 = (ChannelSegment) sendSegment$FU.get($this);
        while (true) {
            long sendersAndCloseStatusCur = sendersAndCloseStatus$FU.getAndIncrement($this);
            long $this$sendersCounter$iv = sendersAndCloseStatusCur & 1152921504606846975L;
            boolean closed = $this.isClosedForSend0(sendersAndCloseStatusCur);
            long id = $this$sendersCounter$iv / BufferedChannelKt.SEGMENT_SIZE;
            int i3 = (int) ($this$sendersCounter$iv % BufferedChannelKt.SEGMENT_SIZE);
            if (segment2.id != id) {
                ChannelSegment segment3 = $this.findSegmentSend(id, segment2);
                if (segment3 != null) {
                    segment = segment3;
                } else if (closed) {
                    return onClosed.invoke();
                }
            } else {
                segment = segment2;
            }
            ChannelSegment segment4 = segment;
            switch ($this.updateCellSend(segment, i3, element, $this$sendersCounter$iv, waiter, closed)) {
                case 0:
                    segment4.cleanPrev();
                    return onRendezvousOrBuffered.invoke();
                case 1:
                    return onRendezvousOrBuffered.invoke();
                case 2:
                    if (closed) {
                        segment4.onSlotCleaned();
                        return onClosed.invoke();
                    }
                    Waiter waiter2 = waiter instanceof Waiter ? (Waiter) waiter : null;
                    if (waiter2 != null) {
                        i2 = i3;
                        $this.prepareSenderForSuspension(waiter2, segment4, i2);
                    } else {
                        i2 = i3;
                    }
                    return onSuspend.invoke(segment4, Integer.valueOf(i2));
                case 3:
                    return onNoWaiterSuspend2.invoke(segment4, Integer.valueOf(i3), element, Long.valueOf($this$sendersCounter$iv));
                case 4:
                    if ($this$sendersCounter$iv < $this.getReceiversCounter$kotlinx_coroutines_core()) {
                        segment4.cleanPrev();
                    }
                    return onClosed.invoke();
                case 5:
                    segment4.cleanPrev();
                    break;
            }
            segment2 = segment4;
        }
    }

    protected final <R> R sendImpl(E e, Object waiter, Function0<? extends R> function0, Function2<? super ChannelSegment<E>, ? super Integer, ? extends R> function2, Function0<? extends R> function02, Function4<? super ChannelSegment<E>, ? super Integer, ? super E, ? super Long, ? extends R> function4) {
        ChannelSegment segment;
        int i;
        ChannelSegment segment2 = (ChannelSegment) sendSegment$FU.get(this);
        while (true) {
            long sendersAndCloseStatusCur = sendersAndCloseStatus$FU.getAndIncrement(this);
            long $this$sendersCounter$iv = sendersAndCloseStatusCur & 1152921504606846975L;
            boolean closed = isClosedForSend0(sendersAndCloseStatusCur);
            long id = $this$sendersCounter$iv / BufferedChannelKt.SEGMENT_SIZE;
            int i2 = (int) ($this$sendersCounter$iv % BufferedChannelKt.SEGMENT_SIZE);
            if (segment2.id != id) {
                ChannelSegment segment3 = findSegmentSend(id, segment2);
                if (segment3 != null) {
                    segment = segment3;
                } else if (closed) {
                    return function02.invoke();
                }
            } else {
                segment = segment2;
            }
            ChannelSegment segment4 = segment;
            switch (updateCellSend(segment, i2, e, $this$sendersCounter$iv, waiter, closed)) {
                case 0:
                    segment4.cleanPrev();
                    return function0.invoke();
                case 1:
                    return function0.invoke();
                case 2:
                    if (closed) {
                        segment4.onSlotCleaned();
                        return function02.invoke();
                    }
                    Waiter waiter2 = waiter instanceof Waiter ? (Waiter) waiter : null;
                    if (waiter2 != null) {
                        i = i2;
                        prepareSenderForSuspension(waiter2, segment4, i);
                    } else {
                        i = i2;
                    }
                    return function2.invoke(segment4, Integer.valueOf(i));
                case 3:
                    return function4.invoke(segment4, Integer.valueOf(i2), e, Long.valueOf($this$sendersCounter$iv));
                case 4:
                    if ($this$sendersCounter$iv < getReceiversCounter$kotlinx_coroutines_core()) {
                        segment4.cleanPrev();
                    }
                    return function02.invoke();
                case 5:
                    segment4.cleanPrev();
                    break;
            }
            segment2 = segment4;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final void sendImplOnNoWaiter(ChannelSegment<E> channelSegment, int index, E e, long s, Waiter waiter, Function0<Unit> function0, Function0<Unit> function02) {
        ChannelSegment segment$iv;
        boolean z = false;
        switch (updateCellSend(channelSegment, index, e, s, waiter, false)) {
            case 0:
                channelSegment.cleanPrev();
                function0.invoke();
                return;
            case 1:
                function0.invoke();
                return;
            case 2:
                prepareSenderForSuspension(waiter, channelSegment, index);
                return;
            case 3:
            default:
                throw new IllegalStateException("unexpected".toString());
            case 4:
                if (s < getReceiversCounter$kotlinx_coroutines_core()) {
                    channelSegment.cleanPrev();
                }
                function02.invoke();
                return;
            case 5:
                channelSegment.cleanPrev();
                ChannelSegment segment$iv2 = (ChannelSegment) sendSegment$FU.get(this);
                while (true) {
                    long sendersAndCloseStatusCur$iv = sendersAndCloseStatus$FU.getAndIncrement(this);
                    long $this$sendersCounter$iv$iv = sendersAndCloseStatusCur$iv & 1152921504606846975L;
                    boolean closed$iv = isClosedForSend0(sendersAndCloseStatusCur$iv);
                    long id$iv = $this$sendersCounter$iv$iv / BufferedChannelKt.SEGMENT_SIZE;
                    int i$iv = (int) ($this$sendersCounter$iv$iv % BufferedChannelKt.SEGMENT_SIZE);
                    if (segment$iv2.id != id$iv) {
                        ChannelSegment segment$iv3 = findSegmentSend(id$iv, segment$iv2);
                        if (segment$iv3 != null) {
                            segment$iv = segment$iv3;
                        } else if (closed$iv) {
                            function02.invoke();
                        }
                    } else {
                        segment$iv = segment$iv2;
                    }
                    ChannelSegment segment$iv4 = segment$iv;
                    boolean z2 = z;
                    switch (updateCellSend(segment$iv, i$iv, e, $this$sendersCounter$iv$iv, waiter, closed$iv)) {
                        case 0:
                            segment$iv4.cleanPrev();
                            function0.invoke();
                            break;
                        case 1:
                            function0.invoke();
                            break;
                        case 2:
                            if (!closed$iv) {
                                Waiter waiter2 = waiter instanceof Waiter ? waiter : null;
                                if (waiter2 != null) {
                                    prepareSenderForSuspension(waiter2, segment$iv4, i$iv);
                                }
                                Unit unit = Unit.INSTANCE;
                                break;
                            } else {
                                segment$iv4.onSlotCleaned();
                                function02.invoke();
                                break;
                            }
                        case 3:
                            throw new IllegalStateException("unexpected".toString());
                        case 4:
                            if ($this$sendersCounter$iv$iv < getReceiversCounter$kotlinx_coroutines_core()) {
                                segment$iv4.cleanPrev();
                            }
                            function02.invoke();
                            break;
                        case 5:
                            segment$iv4.cleanPrev();
                            break;
                    }
                    segment$iv2 = segment$iv4;
                    z = z2;
                }
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int updateCellSend(ChannelSegment<E> channelSegment, int index, E e, long s, Object waiter, boolean closed) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        channelSegment.storeElement$kotlinx_coroutines_core(index, e);
        if (closed) {
            return updateCellSendSlow(channelSegment, index, e, s, waiter, closed);
        }
        Object state = channelSegment.getState$kotlinx_coroutines_core(index);
        if (state == null) {
            if (bufferOrRendezvousSend(s)) {
                if (channelSegment.casState$kotlinx_coroutines_core(index, null, BufferedChannelKt.BUFFERED)) {
                    return 1;
                }
            } else if (waiter == null) {
                return 3;
            } else {
                if (channelSegment.casState$kotlinx_coroutines_core(index, null, waiter)) {
                    return 2;
                }
            }
        } else if (state instanceof Waiter) {
            channelSegment.cleanElement$kotlinx_coroutines_core(index);
            if (tryResumeReceiver(state, e)) {
                symbol3 = BufferedChannelKt.DONE_RCV;
                channelSegment.setState$kotlinx_coroutines_core(index, symbol3);
                onReceiveDequeued();
                return 0;
            }
            symbol = BufferedChannelKt.INTERRUPTED_RCV;
            Object andSetState$kotlinx_coroutines_core = channelSegment.getAndSetState$kotlinx_coroutines_core(index, symbol);
            symbol2 = BufferedChannelKt.INTERRUPTED_RCV;
            if (andSetState$kotlinx_coroutines_core != symbol2) {
                channelSegment.onCancelledRequest(index, true);
            }
            return 5;
        }
        return updateCellSendSlow(channelSegment, index, e, s, waiter, closed);
    }

    private final int updateCellSendSlow(ChannelSegment<E> channelSegment, int index, E e, long s, Object waiter, boolean closed) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        Symbol symbol6;
        Symbol symbol7;
        while (true) {
            Object state = channelSegment.getState$kotlinx_coroutines_core(index);
            if (state != null) {
                symbol2 = BufferedChannelKt.IN_BUFFER;
                if (state != symbol2) {
                    symbol3 = BufferedChannelKt.INTERRUPTED_RCV;
                    if (state != symbol3) {
                        symbol4 = BufferedChannelKt.POISONED;
                        if (state == symbol4) {
                            channelSegment.cleanElement$kotlinx_coroutines_core(index);
                            return 5;
                        } else if (state == BufferedChannelKt.getCHANNEL_CLOSED()) {
                            channelSegment.cleanElement$kotlinx_coroutines_core(index);
                            completeCloseOrCancel();
                            return 4;
                        } else {
                            if (DebugKt.getASSERTIONS_ENABLED()) {
                                if ((((state instanceof Waiter) || (state instanceof WaiterEB)) ? 1 : 0) == 0) {
                                    throw new AssertionError();
                                }
                            }
                            channelSegment.cleanElement$kotlinx_coroutines_core(index);
                            Object receiver = state instanceof WaiterEB ? ((WaiterEB) state).waiter : state;
                            if (tryResumeReceiver(receiver, e)) {
                                symbol7 = BufferedChannelKt.DONE_RCV;
                                channelSegment.setState$kotlinx_coroutines_core(index, symbol7);
                                onReceiveDequeued();
                                return 0;
                            }
                            symbol5 = BufferedChannelKt.INTERRUPTED_RCV;
                            Object andSetState$kotlinx_coroutines_core = channelSegment.getAndSetState$kotlinx_coroutines_core(index, symbol5);
                            symbol6 = BufferedChannelKt.INTERRUPTED_RCV;
                            if (andSetState$kotlinx_coroutines_core != symbol6) {
                                channelSegment.onCancelledRequest(index, true);
                            }
                            return 5;
                        }
                    }
                    channelSegment.cleanElement$kotlinx_coroutines_core(index);
                    return 5;
                } else if (channelSegment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.BUFFERED)) {
                    return 1;
                }
            } else if (bufferOrRendezvousSend(s) && !closed) {
                if (channelSegment.casState$kotlinx_coroutines_core(index, null, BufferedChannelKt.BUFFERED)) {
                    return 1;
                }
            } else if (closed) {
                symbol = BufferedChannelKt.INTERRUPTED_SEND;
                if (channelSegment.casState$kotlinx_coroutines_core(index, null, symbol)) {
                    channelSegment.onCancelledRequest(index, false);
                    return 4;
                }
            } else if (waiter == null) {
                return 3;
            } else {
                if (channelSegment.casState$kotlinx_coroutines_core(index, null, waiter)) {
                    return 2;
                }
            }
        }
    }

    private final boolean shouldSendSuspend(long curSendersAndCloseStatus) {
        if (isClosedForSend0(curSendersAndCloseStatus)) {
            return false;
        }
        long $this$sendersCounter$iv = curSendersAndCloseStatus & 1152921504606846975L;
        return !bufferOrRendezvousSend($this$sendersCounter$iv);
    }

    private final boolean bufferOrRendezvousSend(long curSenders) {
        return curSenders < getBufferEndCounter() || curSenders < getReceiversCounter$kotlinx_coroutines_core() + ((long) this.capacity);
    }

    public boolean shouldSendSuspend$kotlinx_coroutines_core() {
        return shouldSendSuspend(sendersAndCloseStatus$FU.get(this));
    }

    private final boolean tryResumeReceiver(Object $this$tryResumeReceiver, E e) {
        boolean tryResume0;
        boolean tryResume02;
        if ($this$tryResumeReceiver instanceof SelectInstance) {
            return ((SelectInstance) $this$tryResumeReceiver).trySelect(this, e);
        }
        if ($this$tryResumeReceiver instanceof ReceiveCatching) {
            Intrinsics.checkNotNull($this$tryResumeReceiver, "null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveCatching<E of kotlinx.coroutines.channels.BufferedChannel>");
            ReceiveCatching receiveCatching = (ReceiveCatching) $this$tryResumeReceiver;
            CancellableContinuationImpl<ChannelResult<? extends E>> cancellableContinuationImpl = ((ReceiveCatching) $this$tryResumeReceiver).cont;
            ChannelResult m1769boximpl = ChannelResult.m1769boximpl(ChannelResult.Companion.m1784successJP2dKIU(e));
            Function1<E, Unit> function1 = this.onUndeliveredElement;
            tryResume02 = BufferedChannelKt.tryResume0(cancellableContinuationImpl, m1769boximpl, function1 != null ? OnUndeliveredElementKt.bindCancellationFun(function1, e, ((ReceiveCatching) $this$tryResumeReceiver).cont.getContext()) : null);
            return tryResume02;
        } else if ($this$tryResumeReceiver instanceof BufferedChannelIterator) {
            Intrinsics.checkNotNull($this$tryResumeReceiver, "null cannot be cast to non-null type kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator<E of kotlinx.coroutines.channels.BufferedChannel>");
            BufferedChannelIterator bufferedChannelIterator = (BufferedChannelIterator) $this$tryResumeReceiver;
            return ((BufferedChannelIterator) $this$tryResumeReceiver).tryResumeHasNext(e);
        } else if ($this$tryResumeReceiver instanceof CancellableContinuation) {
            Intrinsics.checkNotNull($this$tryResumeReceiver, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<E of kotlinx.coroutines.channels.BufferedChannel>");
            CancellableContinuation cancellableContinuation = (CancellableContinuation) $this$tryResumeReceiver;
            CancellableContinuation cancellableContinuation2 = (CancellableContinuation) $this$tryResumeReceiver;
            Function1<E, Unit> function12 = this.onUndeliveredElement;
            tryResume0 = BufferedChannelKt.tryResume0(cancellableContinuation2, e, function12 != null ? OnUndeliveredElementKt.bindCancellationFun(function12, e, ((CancellableContinuation) $this$tryResumeReceiver).getContext()) : null);
            return tryResume0;
        } else {
            throw new IllegalStateException(("Unexpected receiver type: " + $this$tryResumeReceiver).toString());
        }
    }

    protected void onReceiveEnqueued() {
    }

    protected void onReceiveDequeued() {
    }

    static /* synthetic */ <E> Object receive$suspendImpl(BufferedChannel<E> bufferedChannel, Continuation<? super E> continuation) {
        ChannelSegment segment$iv;
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        ChannelSegment segment$iv2 = (ChannelSegment) receiveSegment$FU.get(bufferedChannel);
        while (!bufferedChannel.isClosedForReceive()) {
            long r$iv = receivers$FU.getAndIncrement(bufferedChannel);
            long id$iv = r$iv / BufferedChannelKt.SEGMENT_SIZE;
            int i$iv = (int) (r$iv % BufferedChannelKt.SEGMENT_SIZE);
            if (segment$iv2.id != id$iv) {
                ChannelSegment segment$iv3 = bufferedChannel.findSegmentReceive(id$iv, segment$iv2);
                if (segment$iv3 == null) {
                    continue;
                } else {
                    segment$iv = segment$iv3;
                }
            } else {
                segment$iv = segment$iv2;
            }
            Object updCellResult$iv = bufferedChannel.updateCellReceive(segment$iv, i$iv, r$iv, null);
            symbol = BufferedChannelKt.SUSPEND;
            if (updCellResult$iv != symbol) {
                symbol2 = BufferedChannelKt.FAILED;
                if (updCellResult$iv != symbol2) {
                    symbol3 = BufferedChannelKt.SUSPEND_NO_WAITER;
                    if (updCellResult$iv == symbol3) {
                        ChannelSegment segm = segment$iv;
                        return bufferedChannel.receiveOnNoWaiterSuspend(segm, i$iv, r$iv, continuation);
                    }
                    segment$iv.cleanPrev();
                    return updCellResult$iv;
                }
                if (r$iv < bufferedChannel.getSendersCounter$kotlinx_coroutines_core()) {
                    segment$iv.cleanPrev();
                }
                segment$iv2 = segment$iv;
            } else {
                throw new IllegalStateException("unexpected".toString());
            }
        }
        throw StackTraceRecoveryKt.recoverStackTrace(bufferedChannel.getReceiveException());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object receiveOnNoWaiterSuspend(ChannelSegment<E> channelSegment, int index, long r, Continuation<? super E> continuation) {
        Symbol symbol;
        Symbol symbol2;
        ChannelSegment segment$iv$iv;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        boolean z = false;
        Continuation<? super E> continuation2 = continuation;
        boolean z2 = false;
        CancellableContinuationImpl cancellable$iv = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation2));
        try {
            Object updCellResult$iv = updateCellReceive(channelSegment, index, r, cancellable$iv);
            symbol = BufferedChannelKt.SUSPEND;
            try {
                if (updCellResult$iv != symbol) {
                    symbol2 = BufferedChannelKt.FAILED;
                    try {
                        if (updCellResult$iv != symbol2) {
                            channelSegment.cleanPrev();
                            Function1<E, Unit> function1 = this.onUndeliveredElement;
                            Function1 onCancellation = function1 != null ? OnUndeliveredElementKt.bindCancellationFun(function1, updCellResult$iv, cancellable$iv.getContext()) : null;
                            cancellable$iv.resume(updCellResult$iv, onCancellation);
                        } else {
                            if (r < getSendersCounter$kotlinx_coroutines_core()) {
                                channelSegment.cleanPrev();
                            }
                            ChannelSegment segment$iv$iv2 = (ChannelSegment) receiveSegment$FU.get(this);
                            while (!isClosedForReceive()) {
                                long r$iv$iv = receivers$FU.getAndIncrement(this);
                                long id$iv$iv = r$iv$iv / BufferedChannelKt.SEGMENT_SIZE;
                                boolean z3 = z;
                                Continuation<? super E> continuation3 = continuation2;
                                try {
                                    int i$iv$iv = (int) (r$iv$iv % BufferedChannelKt.SEGMENT_SIZE);
                                    boolean z4 = z2;
                                    if (segment$iv$iv2.id != id$iv$iv) {
                                        segment$iv$iv = findSegmentReceive(id$iv$iv, segment$iv$iv2);
                                        if (segment$iv$iv == null) {
                                            z = z3;
                                            continuation2 = continuation3;
                                            z2 = z4;
                                        }
                                    } else {
                                        segment$iv$iv = segment$iv$iv2;
                                    }
                                    Object updCellResult$iv$iv = updateCellReceive(segment$iv$iv, i$iv$iv, r$iv$iv, cancellable$iv);
                                    symbol3 = BufferedChannelKt.SUSPEND;
                                    if (updCellResult$iv$iv != symbol3) {
                                        symbol4 = BufferedChannelKt.FAILED;
                                        if (updCellResult$iv$iv != symbol4) {
                                            symbol5 = BufferedChannelKt.SUSPEND_NO_WAITER;
                                            if (updCellResult$iv$iv == symbol5) {
                                                throw new IllegalStateException("unexpected".toString());
                                            }
                                            segment$iv$iv.cleanPrev();
                                            Function1<E, Unit> function12 = this.onUndeliveredElement;
                                            Function1 onCancellation2 = function12 != null ? OnUndeliveredElementKt.bindCancellationFun(function12, updCellResult$iv$iv, cancellable$iv.getContext()) : null;
                                            cancellable$iv.resume(updCellResult$iv$iv, onCancellation2);
                                        } else {
                                            if (r$iv$iv < getSendersCounter$kotlinx_coroutines_core()) {
                                                segment$iv$iv.cleanPrev();
                                            }
                                            segment$iv$iv2 = segment$iv$iv;
                                            z = z3;
                                            continuation2 = continuation3;
                                            z2 = z4;
                                        }
                                    } else {
                                        CancellableContinuationImpl cancellableContinuationImpl = cancellable$iv instanceof Waiter ? cancellable$iv : null;
                                        if (cancellableContinuationImpl != null) {
                                            prepareReceiverForSuspension(cancellableContinuationImpl, segment$iv$iv, i$iv$iv);
                                        }
                                    }
                                } catch (Throwable th) {
                                    e$iv = th;
                                    cancellable$iv.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                                    throw e$iv;
                                }
                            }
                            onClosedReceiveOnNoWaiterSuspend(cancellable$iv);
                        }
                    } catch (Throwable th2) {
                        e$iv = th2;
                    }
                } else {
                    try {
                        prepareReceiverForSuspension(cancellable$iv, channelSegment, index);
                    } catch (Throwable th3) {
                        e$iv = th3;
                        cancellable$iv.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                        throw e$iv;
                    }
                }
                Object result = cancellable$iv.getResult();
                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(continuation);
                }
                return result;
            } catch (Throwable th4) {
                e$iv = th4;
            }
        } catch (Throwable th5) {
            e$iv = th5;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void prepareReceiverForSuspension(Waiter $this$prepareReceiverForSuspension, ChannelSegment<E> channelSegment, int index) {
        onReceiveEnqueued();
        $this$prepareReceiverForSuspension.invokeOnCancellation(channelSegment, index);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onClosedReceiveOnNoWaiterSuspend(CancellableContinuation<? super E> cancellableContinuation) {
        Result.Companion companion = Result.Companion;
        cancellableContinuation.resumeWith(Result.m256constructorimpl(ResultKt.createFailure(getReceiveException())));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0042  */
    /* renamed from: receiveCatching-JP2dKIU$suspendImpl  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ <E> java.lang.Object m1761receiveCatchingJP2dKIU$suspendImpl(kotlinx.coroutines.channels.BufferedChannel<E> r17, kotlin.coroutines.Continuation<? super kotlinx.coroutines.channels.ChannelResult<? extends E>> r18) {
        /*
            Method dump skipped, instructions count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.m1761receiveCatchingJP2dKIU$suspendImpl(kotlinx.coroutines.channels.BufferedChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x004a  */
    /* renamed from: receiveCatchingOnNoWaiterSuspend-GKJJFZk  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m1762receiveCatchingOnNoWaiterSuspendGKJJFZk(kotlinx.coroutines.channels.ChannelSegment<E> r28, int r29, long r30, kotlin.coroutines.Continuation<? super kotlinx.coroutines.channels.ChannelResult<? extends E>> r32) {
        /*
            Method dump skipped, instructions count: 444
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.m1762receiveCatchingOnNoWaiterSuspendGKJJFZk(kotlinx.coroutines.channels.ChannelSegment, int, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onClosedReceiveCatchingOnNoWaiterSuspend(CancellableContinuation<? super ChannelResult<? extends E>> cancellableContinuation) {
        Result.Companion companion = Result.Companion;
        cancellableContinuation.resumeWith(Result.m256constructorimpl(ChannelResult.m1769boximpl(ChannelResult.Companion.m1782closedJP2dKIU(getCloseCause()))));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* renamed from: tryReceive-PtdJZtk  reason: not valid java name */
    public Object mo1764tryReceivePtdJZtk() {
        Object waiter$iv;
        ChannelSegment segment$iv;
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        long r = receivers$FU.get(this);
        long sendersAndCloseStatusCur = sendersAndCloseStatus$FU.get(this);
        if (isClosedForReceive0(sendersAndCloseStatusCur)) {
            return ChannelResult.Companion.m1782closedJP2dKIU(getCloseCause());
        }
        long $this$sendersCounter$iv = sendersAndCloseStatusCur & 1152921504606846975L;
        if (r >= $this$sendersCounter$iv) {
            return ChannelResult.Companion.m1783failurePtdJZtk();
        }
        waiter$iv = BufferedChannelKt.INTERRUPTED_RCV;
        ChannelSegment segment$iv2 = (ChannelSegment) receiveSegment$FU.get(this);
        while (!isClosedForReceive()) {
            long r$iv = receivers$FU.getAndIncrement(this);
            long id$iv = r$iv / BufferedChannelKt.SEGMENT_SIZE;
            int i$iv = (int) (r$iv % BufferedChannelKt.SEGMENT_SIZE);
            if (segment$iv2.id != id$iv) {
                ChannelSegment segment$iv3 = findSegmentReceive(id$iv, segment$iv2);
                if (segment$iv3 == null) {
                    continue;
                } else {
                    segment$iv = segment$iv3;
                }
            } else {
                segment$iv = segment$iv2;
            }
            long r2 = r;
            ChannelSegment segment$iv4 = segment$iv;
            Object updCellResult$iv = updateCellReceive(segment$iv, i$iv, r$iv, waiter$iv);
            symbol = BufferedChannelKt.SUSPEND;
            if (updCellResult$iv != symbol) {
                symbol2 = BufferedChannelKt.FAILED;
                if (updCellResult$iv != symbol2) {
                    symbol3 = BufferedChannelKt.SUSPEND_NO_WAITER;
                    if (updCellResult$iv == symbol3) {
                        throw new IllegalStateException("unexpected".toString());
                    }
                    segment$iv4.cleanPrev();
                    return ChannelResult.Companion.m1784successJP2dKIU(updCellResult$iv);
                }
                if (r$iv < getSendersCounter$kotlinx_coroutines_core()) {
                    segment$iv4.cleanPrev();
                }
                segment$iv2 = segment$iv4;
                r = r2;
            } else {
                Waiter waiter = waiter$iv instanceof Waiter ? (Waiter) waiter$iv : null;
                if (waiter != null) {
                    prepareReceiverForSuspension(waiter, segment$iv4, i$iv);
                }
                waitExpandBufferCompletion$kotlinx_coroutines_core(r$iv);
                segment$iv4.onSlotCleaned();
                return ChannelResult.Companion.m1783failurePtdJZtk();
            }
        }
        return ChannelResult.Companion.m1782closedJP2dKIU(getCloseCause());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void dropFirstElementUntilTheSpecifiedCellIsInTheBuffer(long globalCellIndex) {
        Symbol symbol;
        UndeliveredElementException it;
        if (DebugKt.getASSERTIONS_ENABLED() && !isConflatedDropOldest()) {
            throw new AssertionError();
        }
        ChannelSegment segment = (ChannelSegment) receiveSegment$FU.get(this);
        while (true) {
            long r = receivers$FU.get(this);
            if (globalCellIndex < Math.max(this.capacity + r, getBufferEndCounter())) {
                return;
            }
            if (receivers$FU.compareAndSet(this, r, r + 1)) {
                long id = r / BufferedChannelKt.SEGMENT_SIZE;
                int i = (int) (r % BufferedChannelKt.SEGMENT_SIZE);
                if (segment.id != id) {
                    ChannelSegment findSegmentReceive = findSegmentReceive(id, segment);
                    if (findSegmentReceive == null) {
                        continue;
                    } else {
                        segment = findSegmentReceive;
                    }
                }
                Object updCellResult = updateCellReceive(segment, i, r, null);
                symbol = BufferedChannelKt.FAILED;
                if (updCellResult == symbol) {
                    if (r < getSendersCounter$kotlinx_coroutines_core()) {
                        segment.cleanPrev();
                    }
                } else {
                    segment.cleanPrev();
                    Function1<E, Unit> function1 = this.onUndeliveredElement;
                    if (function1 != null && (it = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, updCellResult, null, 2, null)) != null) {
                        throw it;
                    }
                }
            }
        }
    }

    static /* synthetic */ Object receiveImpl$default(BufferedChannel $this, Object waiter, Function1 onElementRetrieved, Function3 onSuspend, Function0 onClosed, Function3 onNoWaiterSuspend, int i, Object obj) {
        Function3 onNoWaiterSuspend2;
        ChannelSegment segment;
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        if (obj == null) {
            if ((i & 16) == 0) {
                onNoWaiterSuspend2 = onNoWaiterSuspend;
            } else {
                onNoWaiterSuspend2 = new Function3() { // from class: kotlinx.coroutines.channels.BufferedChannel$receiveImpl$1
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3) {
                        return invoke((ChannelSegment) p1, ((Number) p2).intValue(), ((Number) p3).longValue());
                    }

                    public final Void invoke(ChannelSegment<E> channelSegment, int i2, long j) {
                        throw new IllegalStateException("unexpected".toString());
                    }
                };
            }
            ChannelSegment segment2 = (ChannelSegment) receiveSegment$FU.get($this);
            while (!$this.isClosedForReceive()) {
                long r = receivers$FU.getAndIncrement($this);
                long id = r / BufferedChannelKt.SEGMENT_SIZE;
                int i2 = (int) (r % BufferedChannelKt.SEGMENT_SIZE);
                if (segment2.id != id) {
                    ChannelSegment segment3 = $this.findSegmentReceive(id, segment2);
                    if (segment3 == null) {
                        continue;
                    } else {
                        segment = segment3;
                    }
                } else {
                    segment = segment2;
                }
                Object updCellResult = $this.updateCellReceive(segment, i2, r, waiter);
                symbol = BufferedChannelKt.SUSPEND;
                if (updCellResult != symbol) {
                    symbol2 = BufferedChannelKt.FAILED;
                    if (updCellResult != symbol2) {
                        symbol3 = BufferedChannelKt.SUSPEND_NO_WAITER;
                        if (updCellResult == symbol3) {
                            return onNoWaiterSuspend2.invoke(segment, Integer.valueOf(i2), Long.valueOf(r));
                        }
                        segment.cleanPrev();
                        return onElementRetrieved.invoke(updCellResult);
                    }
                    if (r < $this.getSendersCounter$kotlinx_coroutines_core()) {
                        segment.cleanPrev();
                    }
                    segment2 = segment;
                } else {
                    Waiter waiter2 = waiter instanceof Waiter ? (Waiter) waiter : null;
                    if (waiter2 != null) {
                        $this.prepareReceiverForSuspension(waiter2, segment, i2);
                    }
                    return onSuspend.invoke(segment, Integer.valueOf(i2), Long.valueOf(r));
                }
            }
            return onClosed.invoke();
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: receiveImpl");
    }

    private final <R> R receiveImpl(Object waiter, Function1<? super E, ? extends R> function1, Function3<? super ChannelSegment<E>, ? super Integer, ? super Long, ? extends R> function3, Function0<? extends R> function0, Function3<? super ChannelSegment<E>, ? super Integer, ? super Long, ? extends R> function32) {
        ChannelSegment segment;
        Object obj;
        Object obj2;
        Object obj3;
        ChannelSegment segment2 = (ChannelSegment) receiveSegment$FU.get(this);
        while (!isClosedForReceive()) {
            long r = receivers$FU.getAndIncrement(this);
            long id = r / BufferedChannelKt.SEGMENT_SIZE;
            int i = (int) (r % BufferedChannelKt.SEGMENT_SIZE);
            if (segment2.id != id) {
                ChannelSegment segment3 = findSegmentReceive(id, segment2);
                if (segment3 == null) {
                    continue;
                } else {
                    segment = segment3;
                }
            } else {
                segment = segment2;
            }
            Object updCellResult = (Object) updateCellReceive(segment, i, r, waiter);
            obj = BufferedChannelKt.SUSPEND;
            if (updCellResult != obj) {
                obj2 = BufferedChannelKt.FAILED;
                if (updCellResult != obj2) {
                    obj3 = BufferedChannelKt.SUSPEND_NO_WAITER;
                    if (updCellResult == obj3) {
                        return function32.invoke(segment, Integer.valueOf(i), Long.valueOf(r));
                    }
                    segment.cleanPrev();
                    return function1.invoke(updCellResult);
                }
                if (r < getSendersCounter$kotlinx_coroutines_core()) {
                    segment.cleanPrev();
                }
                segment2 = segment;
            } else {
                Waiter waiter2 = waiter instanceof Waiter ? (Waiter) waiter : null;
                if (waiter2 != null) {
                    prepareReceiverForSuspension(waiter2, segment, i);
                }
                return function3.invoke(segment, Integer.valueOf(i), Long.valueOf(r));
            }
        }
        return function0.invoke();
    }

    private final void receiveImplOnNoWaiter(ChannelSegment<E> channelSegment, int index, long r, Waiter waiter, Function1<? super E, Unit> function1, Function0<Unit> function0) {
        Object obj;
        Object obj2;
        ChannelSegment segment$iv;
        Object obj3;
        Object obj4;
        Object obj5;
        boolean z = false;
        Object updCellResult = (Object) updateCellReceive(channelSegment, index, r, waiter);
        obj = BufferedChannelKt.SUSPEND;
        if (updCellResult != obj) {
            obj2 = BufferedChannelKt.FAILED;
            if (updCellResult == obj2) {
                if (r < getSendersCounter$kotlinx_coroutines_core()) {
                    channelSegment.cleanPrev();
                }
                ChannelSegment segment$iv2 = (ChannelSegment) receiveSegment$FU.get(this);
                while (!isClosedForReceive()) {
                    long r$iv = receivers$FU.getAndIncrement(this);
                    long id$iv = r$iv / BufferedChannelKt.SEGMENT_SIZE;
                    int i$iv = (int) (r$iv % BufferedChannelKt.SEGMENT_SIZE);
                    if (segment$iv2.id != id$iv) {
                        ChannelSegment segment$iv3 = findSegmentReceive(id$iv, segment$iv2);
                        if (segment$iv3 == null) {
                            continue;
                        } else {
                            segment$iv = segment$iv3;
                        }
                    } else {
                        segment$iv = segment$iv2;
                    }
                    boolean z2 = z;
                    ChannelSegment segment$iv4 = segment$iv;
                    Object updCellResult$iv = (Object) updateCellReceive(segment$iv, i$iv, r$iv, waiter);
                    obj3 = BufferedChannelKt.SUSPEND;
                    if (updCellResult$iv != obj3) {
                        obj4 = BufferedChannelKt.FAILED;
                        if (updCellResult$iv != obj4) {
                            obj5 = BufferedChannelKt.SUSPEND_NO_WAITER;
                            if (updCellResult$iv == obj5) {
                                throw new IllegalStateException("unexpected".toString());
                            }
                            segment$iv4.cleanPrev();
                            function1.invoke(updCellResult$iv);
                            return;
                        }
                        if (r$iv < getSendersCounter$kotlinx_coroutines_core()) {
                            segment$iv4.cleanPrev();
                        }
                        segment$iv2 = segment$iv4;
                        z = z2;
                    } else {
                        Waiter waiter2 = waiter instanceof Waiter ? waiter : null;
                        if (waiter2 != null) {
                            prepareReceiverForSuspension(waiter2, segment$iv4, i$iv);
                        }
                        Unit unit = Unit.INSTANCE;
                        return;
                    }
                }
                function0.invoke();
                return;
            }
            channelSegment.cleanPrev();
            function1.invoke(updCellResult);
            return;
        }
        prepareReceiverForSuspension(waiter, channelSegment, index);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object updateCellReceive(ChannelSegment<E> channelSegment, int index, long r, Object waiter) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Object state = channelSegment.getState$kotlinx_coroutines_core(index);
        if (state == null) {
            long $this$sendersCounter$iv = sendersAndCloseStatus$FU.get(this);
            long senders = $this$sendersCounter$iv & 1152921504606846975L;
            if (r >= senders) {
                if (waiter == null) {
                    symbol3 = BufferedChannelKt.SUSPEND_NO_WAITER;
                    return symbol3;
                } else if (channelSegment.casState$kotlinx_coroutines_core(index, state, waiter)) {
                    expandBuffer();
                    symbol2 = BufferedChannelKt.SUSPEND;
                    return symbol2;
                }
            }
        } else if (state == BufferedChannelKt.BUFFERED) {
            symbol = BufferedChannelKt.DONE_RCV;
            if (channelSegment.casState$kotlinx_coroutines_core(index, state, symbol)) {
                expandBuffer();
                return channelSegment.retrieveElement$kotlinx_coroutines_core(index);
            }
        }
        return updateCellReceiveSlow(channelSegment, index, r, waiter);
    }

    private final Object updateCellReceiveSlow(ChannelSegment<E> channelSegment, int index, long r, Object waiter) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        Symbol symbol6;
        Symbol symbol7;
        Symbol symbol8;
        Symbol symbol9;
        Symbol symbol10;
        Symbol symbol11;
        Symbol symbol12;
        Symbol symbol13;
        Symbol symbol14;
        Symbol symbol15;
        Symbol symbol16;
        while (true) {
            Object state = channelSegment.getState$kotlinx_coroutines_core(index);
            if (state != null) {
                symbol5 = BufferedChannelKt.IN_BUFFER;
                if (state != symbol5) {
                    if (state == BufferedChannelKt.BUFFERED) {
                        symbol6 = BufferedChannelKt.DONE_RCV;
                        if (channelSegment.casState$kotlinx_coroutines_core(index, state, symbol6)) {
                            expandBuffer();
                            return channelSegment.retrieveElement$kotlinx_coroutines_core(index);
                        }
                    } else {
                        symbol7 = BufferedChannelKt.INTERRUPTED_SEND;
                        if (state == symbol7) {
                            symbol8 = BufferedChannelKt.FAILED;
                            return symbol8;
                        }
                        symbol9 = BufferedChannelKt.POISONED;
                        if (state == symbol9) {
                            symbol10 = BufferedChannelKt.FAILED;
                            return symbol10;
                        } else if (state != BufferedChannelKt.getCHANNEL_CLOSED()) {
                            symbol12 = BufferedChannelKt.RESUMING_BY_EB;
                            if (state != symbol12) {
                                symbol13 = BufferedChannelKt.RESUMING_BY_RCV;
                                if (channelSegment.casState$kotlinx_coroutines_core(index, state, symbol13)) {
                                    boolean helpExpandBuffer = state instanceof WaiterEB;
                                    Object sender = state instanceof WaiterEB ? ((WaiterEB) state).waiter : state;
                                    if (tryResumeSender(sender, channelSegment, index)) {
                                        symbol16 = BufferedChannelKt.DONE_RCV;
                                        channelSegment.setState$kotlinx_coroutines_core(index, symbol16);
                                        expandBuffer();
                                        return channelSegment.retrieveElement$kotlinx_coroutines_core(index);
                                    }
                                    symbol14 = BufferedChannelKt.INTERRUPTED_SEND;
                                    channelSegment.setState$kotlinx_coroutines_core(index, symbol14);
                                    channelSegment.onCancelledRequest(index, false);
                                    if (helpExpandBuffer) {
                                        expandBuffer();
                                    }
                                    symbol15 = BufferedChannelKt.FAILED;
                                    return symbol15;
                                }
                            } else {
                                continue;
                            }
                        } else {
                            expandBuffer();
                            symbol11 = BufferedChannelKt.FAILED;
                            return symbol11;
                        }
                    }
                }
            }
            long $this$sendersCounter$iv = sendersAndCloseStatus$FU.get(this);
            long senders = $this$sendersCounter$iv & 1152921504606846975L;
            if (r < senders) {
                symbol = BufferedChannelKt.POISONED;
                if (channelSegment.casState$kotlinx_coroutines_core(index, state, symbol)) {
                    expandBuffer();
                    symbol2 = BufferedChannelKt.FAILED;
                    return symbol2;
                }
            } else if (waiter == null) {
                symbol3 = BufferedChannelKt.SUSPEND_NO_WAITER;
                return symbol3;
            } else if (channelSegment.casState$kotlinx_coroutines_core(index, state, waiter)) {
                expandBuffer();
                symbol4 = BufferedChannelKt.SUSPEND;
                return symbol4;
            }
        }
    }

    private final boolean tryResumeSender(Object $this$tryResumeSender, ChannelSegment<E> channelSegment, int index) {
        if ($this$tryResumeSender instanceof CancellableContinuation) {
            Intrinsics.checkNotNull($this$tryResumeSender, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
            CancellableContinuation cancellableContinuation = (CancellableContinuation) $this$tryResumeSender;
            return BufferedChannelKt.tryResume0$default((CancellableContinuation) $this$tryResumeSender, Unit.INSTANCE, null, 2, null);
        } else if ($this$tryResumeSender instanceof SelectInstance) {
            Intrinsics.checkNotNull($this$tryResumeSender, "null cannot be cast to non-null type kotlinx.coroutines.selects.SelectImplementation<*>");
            SelectImplementation selectImplementation = (SelectImplementation) $this$tryResumeSender;
            TrySelectDetailedResult trySelectResult = ((SelectImplementation) $this$tryResumeSender).trySelectDetailed(this, Unit.INSTANCE);
            if (trySelectResult == TrySelectDetailedResult.REREGISTER) {
                channelSegment.cleanElement$kotlinx_coroutines_core(index);
            }
            return trySelectResult == TrySelectDetailedResult.SUCCESSFUL;
        } else if ($this$tryResumeSender instanceof SendBroadcast) {
            return BufferedChannelKt.tryResume0$default(((SendBroadcast) $this$tryResumeSender).getCont(), true, null, 2, null);
        } else {
            throw new IllegalStateException(("Unexpected waiter: " + $this$tryResumeSender).toString());
        }
    }

    private final void expandBuffer() {
        long id;
        if (isRendezvousOrUnlimited()) {
            return;
        }
        ChannelSegment segment = (ChannelSegment) bufferEndSegment$FU.get(this);
        while (true) {
            long b = bufferEnd$FU.getAndIncrement(this);
            long id2 = b / BufferedChannelKt.SEGMENT_SIZE;
            long s = getSendersCounter$kotlinx_coroutines_core();
            if (s <= b) {
                if (segment.id < id2 && segment.getNext() != 0) {
                    moveSegmentBufferEndToSpecifiedOrLast(id2, segment);
                }
                incCompletedExpandBufferAttempts$default(this, 0L, 1, null);
                return;
            }
            if (segment.id != id2) {
                id = 0;
                ChannelSegment findSegmentBufferEnd = findSegmentBufferEnd(id2, segment, b);
                if (findSegmentBufferEnd == null) {
                    continue;
                } else {
                    segment = findSegmentBufferEnd;
                }
            } else {
                id = 0;
            }
            int i = (int) (b % BufferedChannelKt.SEGMENT_SIZE);
            if (updateCellExpandBuffer(segment, i, b)) {
                incCompletedExpandBufferAttempts$default(this, id, 1, null);
                return;
            }
            incCompletedExpandBufferAttempts$default(this, id, 1, null);
        }
    }

    private final boolean updateCellExpandBuffer(ChannelSegment<E> channelSegment, int index, long b) {
        Symbol symbol;
        Symbol symbol2;
        Object state = channelSegment.getState$kotlinx_coroutines_core(index);
        if ((state instanceof Waiter) && b >= receivers$FU.get(this)) {
            symbol = BufferedChannelKt.RESUMING_BY_EB;
            if (channelSegment.casState$kotlinx_coroutines_core(index, state, symbol)) {
                if (!tryResumeSender(state, channelSegment, index)) {
                    symbol2 = BufferedChannelKt.INTERRUPTED_SEND;
                    channelSegment.setState$kotlinx_coroutines_core(index, symbol2);
                    channelSegment.onCancelledRequest(index, false);
                    return false;
                }
                channelSegment.setState$kotlinx_coroutines_core(index, BufferedChannelKt.BUFFERED);
                return true;
            }
        }
        return updateCellExpandBufferSlow(channelSegment, index, b);
    }

    private final boolean updateCellExpandBufferSlow(ChannelSegment<E> channelSegment, int index, long b) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        Symbol symbol6;
        Symbol symbol7;
        Symbol symbol8;
        while (true) {
            Object state = channelSegment.getState$kotlinx_coroutines_core(index);
            if (!(state instanceof Waiter)) {
                symbol3 = BufferedChannelKt.INTERRUPTED_SEND;
                if (state != symbol3) {
                    if (state == null) {
                        symbol4 = BufferedChannelKt.IN_BUFFER;
                        if (channelSegment.casState$kotlinx_coroutines_core(index, state, symbol4)) {
                            return true;
                        }
                    } else if (state != BufferedChannelKt.BUFFERED) {
                        symbol5 = BufferedChannelKt.POISONED;
                        if (state == symbol5) {
                            break;
                        }
                        symbol6 = BufferedChannelKt.DONE_RCV;
                        if (state == symbol6) {
                            break;
                        }
                        symbol7 = BufferedChannelKt.INTERRUPTED_RCV;
                        if (state == symbol7 || state == BufferedChannelKt.getCHANNEL_CLOSED()) {
                            return true;
                        }
                        symbol8 = BufferedChannelKt.RESUMING_BY_RCV;
                        if (state != symbol8) {
                            throw new IllegalStateException(("Unexpected cell state: " + state).toString());
                        }
                    } else {
                        return true;
                    }
                } else {
                    return false;
                }
            } else if (b >= receivers$FU.get(this)) {
                symbol = BufferedChannelKt.RESUMING_BY_EB;
                if (channelSegment.casState$kotlinx_coroutines_core(index, state, symbol)) {
                    if (!tryResumeSender(state, channelSegment, index)) {
                        symbol2 = BufferedChannelKt.INTERRUPTED_SEND;
                        channelSegment.setState$kotlinx_coroutines_core(index, symbol2);
                        channelSegment.onCancelledRequest(index, false);
                        return false;
                    }
                    channelSegment.setState$kotlinx_coroutines_core(index, BufferedChannelKt.BUFFERED);
                    return true;
                }
            } else if (channelSegment.casState$kotlinx_coroutines_core(index, state, new WaiterEB((Waiter) state))) {
                return true;
            }
        }
    }

    static /* synthetic */ void incCompletedExpandBufferAttempts$default(BufferedChannel bufferedChannel, long j, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: incCompletedExpandBufferAttempts");
        }
        if ((i & 1) != 0) {
            j = 1;
        }
        bufferedChannel.incCompletedExpandBufferAttempts(j);
    }

    private final void incCompletedExpandBufferAttempts(long nAttempts) {
        boolean z;
        long it = completedExpandBuffersAndPauseFlag$FU.addAndGet(this, nAttempts);
        if ((it & 4611686018427387904L) != 0) {
            do {
                long $this$ebPauseExpandBuffers$iv = completedExpandBuffersAndPauseFlag$FU.get(this);
                if (($this$ebPauseExpandBuffers$iv & 4611686018427387904L) != 0) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
            } while (z);
        }
    }

    public final void waitExpandBufferCompletion$kotlinx_coroutines_core(long globalIndex) {
        int i;
        long constructEBCompletedAndPauseFlag;
        long ebCompleted;
        long constructEBCompletedAndPauseFlag2;
        long constructEBCompletedAndPauseFlag3;
        if (isRendezvousOrUnlimited()) {
            return;
        }
        do {
        } while (getBufferEndCounter() <= globalIndex);
        i = BufferedChannelKt.EXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS;
        boolean z = false;
        int i2 = 0;
        while (true) {
            long j = DurationKt.MAX_MILLIS;
            if (i2 < i) {
                long b = getBufferEndCounter();
                long $this$ebCompletedCounter$iv = completedExpandBuffersAndPauseFlag$FU.get(this);
                if (b == (DurationKt.MAX_MILLIS & $this$ebCompletedCounter$iv) && b == getBufferEndCounter()) {
                    return;
                }
                i2++;
            } else {
                AtomicLongFieldUpdater atomicfu$handler$iv = completedExpandBuffersAndPauseFlag$FU;
                while (true) {
                    long it = atomicfu$handler$iv.get(this);
                    long $this$ebCompletedCounter$iv2 = it & j;
                    constructEBCompletedAndPauseFlag = BufferedChannelKt.constructEBCompletedAndPauseFlag($this$ebCompletedCounter$iv2, true);
                    if (atomicfu$handler$iv.compareAndSet(this, it, constructEBCompletedAndPauseFlag)) {
                        break;
                    }
                    z = false;
                    j = DurationKt.MAX_MILLIS;
                }
                while (true) {
                    long b2 = getBufferEndCounter();
                    long ebCompletedAndBit = completedExpandBuffersAndPauseFlag$FU.get(this);
                    long $this$ebCompletedCounter$iv3 = ebCompletedAndBit & j;
                    ebCompleted = $this$ebCompletedCounter$iv3;
                    boolean pauseExpandBuffers = (ebCompletedAndBit & 4611686018427387904L) != 0 ? true : z;
                    if (b2 == ebCompleted && b2 == getBufferEndCounter()) {
                        break;
                    } else if (!pauseExpandBuffers) {
                        AtomicLongFieldUpdater atomicLongFieldUpdater = completedExpandBuffersAndPauseFlag$FU;
                        constructEBCompletedAndPauseFlag2 = BufferedChannelKt.constructEBCompletedAndPauseFlag(ebCompleted, true);
                        atomicLongFieldUpdater.compareAndSet(this, ebCompletedAndBit, constructEBCompletedAndPauseFlag2);
                        z = false;
                        j = DurationKt.MAX_MILLIS;
                    } else {
                        z = false;
                        j = DurationKt.MAX_MILLIS;
                    }
                }
                AtomicLongFieldUpdater atomicfu$handler$iv2 = completedExpandBuffersAndPauseFlag$FU;
                while (true) {
                    long it2 = atomicfu$handler$iv2.get(this);
                    constructEBCompletedAndPauseFlag3 = BufferedChannelKt.constructEBCompletedAndPauseFlag(it2 & j, z);
                    long ebCompleted2 = ebCompleted;
                    if (!atomicfu$handler$iv2.compareAndSet(this, it2, constructEBCompletedAndPauseFlag3)) {
                        ebCompleted = ebCompleted2;
                        z = false;
                        j = DurationKt.MAX_MILLIS;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public SelectClause2<E, BufferedChannel<E>> getOnSend() {
        BufferedChannel$onSend$1 bufferedChannel$onSend$1 = BufferedChannel$onSend$1.INSTANCE;
        Intrinsics.checkNotNull(bufferedChannel$onSend$1, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = 'clauseObject')] kotlin.Any, @[ParameterName(name = 'select')] kotlinx.coroutines.selects.SelectInstance<*>, @[ParameterName(name = 'param')] kotlin.Any?, kotlin.Unit>{ kotlinx.coroutines.selects.SelectKt.RegistrationFunction }");
        Function3 function3 = (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(bufferedChannel$onSend$1, 3);
        BufferedChannel$onSend$2 bufferedChannel$onSend$2 = BufferedChannel$onSend$2.INSTANCE;
        Intrinsics.checkNotNull(bufferedChannel$onSend$2, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = 'clauseObject')] kotlin.Any, @[ParameterName(name = 'param')] kotlin.Any?, @[ParameterName(name = 'clauseResult')] kotlin.Any?, kotlin.Any?>{ kotlinx.coroutines.selects.SelectKt.ProcessResultFunction }");
        return new SelectClause2Impl(this, function3, (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(bufferedChannel$onSend$2, 3), null, 8, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    public void registerSelectForSend(SelectInstance<?> selectInstance, Object element) {
        ChannelSegment segment$iv;
        int $i$f$sendImpl = 0;
        ChannelSegment segment$iv2 = (ChannelSegment) sendSegment$FU.get(this);
        while (true) {
            long sendersAndCloseStatusCur$iv = sendersAndCloseStatus$FU.getAndIncrement(this);
            long $this$sendersCounter$iv$iv = sendersAndCloseStatusCur$iv & 1152921504606846975L;
            boolean closed$iv = isClosedForSend0(sendersAndCloseStatusCur$iv);
            long id$iv = $this$sendersCounter$iv$iv / BufferedChannelKt.SEGMENT_SIZE;
            int i$iv = (int) ($this$sendersCounter$iv$iv % BufferedChannelKt.SEGMENT_SIZE);
            if (segment$iv2.id != id$iv) {
                ChannelSegment segment$iv3 = findSegmentSend(id$iv, segment$iv2);
                if (segment$iv3 != null) {
                    segment$iv = segment$iv3;
                } else if (closed$iv) {
                    onClosedSelectOnSend(element, selectInstance);
                    return;
                }
            } else {
                segment$iv = segment$iv2;
            }
            ChannelSegment segment$iv4 = segment$iv;
            int $i$f$sendImpl2 = $i$f$sendImpl;
            switch (updateCellSend(segment$iv, i$iv, element, $this$sendersCounter$iv$iv, selectInstance, closed$iv)) {
                case 0:
                    segment$iv4.cleanPrev();
                    selectInstance.selectInRegistrationPhase(Unit.INSTANCE);
                    return;
                case 1:
                    selectInstance.selectInRegistrationPhase(Unit.INSTANCE);
                    return;
                case 2:
                    if (closed$iv) {
                        segment$iv4.onSlotCleaned();
                        onClosedSelectOnSend(element, selectInstance);
                        return;
                    }
                    Waiter waiter = selectInstance instanceof Waiter ? (Waiter) selectInstance : null;
                    if (waiter != null) {
                        prepareSenderForSuspension(waiter, segment$iv4, i$iv);
                    }
                    return;
                case 3:
                    throw new IllegalStateException("unexpected".toString());
                case 4:
                    if ($this$sendersCounter$iv$iv < getReceiversCounter$kotlinx_coroutines_core()) {
                        segment$iv4.cleanPrev();
                    }
                    onClosedSelectOnSend(element, selectInstance);
                    return;
                case 5:
                    segment$iv4.cleanPrev();
                    break;
            }
            segment$iv2 = segment$iv4;
            $i$f$sendImpl = $i$f$sendImpl2;
        }
    }

    private final void onClosedSelectOnSend(E e, SelectInstance<?> selectInstance) {
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        if (function1 != null) {
            OnUndeliveredElementKt.callUndeliveredElement(function1, e, selectInstance.getContext());
        }
        selectInstance.selectInRegistrationPhase(BufferedChannelKt.getCHANNEL_CLOSED());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object processResultSelectSend(Object ignoredParam, Object selectResult) {
        if (selectResult == BufferedChannelKt.getCHANNEL_CLOSED()) {
            throw getSendException();
        }
        return this;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public SelectClause1<E> getOnReceive() {
        BufferedChannel$onReceive$1 bufferedChannel$onReceive$1 = BufferedChannel$onReceive$1.INSTANCE;
        Intrinsics.checkNotNull(bufferedChannel$onReceive$1, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = 'clauseObject')] kotlin.Any, @[ParameterName(name = 'select')] kotlinx.coroutines.selects.SelectInstance<*>, @[ParameterName(name = 'param')] kotlin.Any?, kotlin.Unit>{ kotlinx.coroutines.selects.SelectKt.RegistrationFunction }");
        BufferedChannel$onReceive$2 bufferedChannel$onReceive$2 = BufferedChannel$onReceive$2.INSTANCE;
        Intrinsics.checkNotNull(bufferedChannel$onReceive$2, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = 'clauseObject')] kotlin.Any, @[ParameterName(name = 'param')] kotlin.Any?, @[ParameterName(name = 'clauseResult')] kotlin.Any?, kotlin.Any?>{ kotlinx.coroutines.selects.SelectKt.ProcessResultFunction }");
        return new SelectClause1Impl(this, (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(bufferedChannel$onReceive$1, 3), (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(bufferedChannel$onReceive$2, 3), this.onUndeliveredElementReceiveCancellationConstructor);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public SelectClause1<ChannelResult<E>> getOnReceiveCatching() {
        BufferedChannel$onReceiveCatching$1 bufferedChannel$onReceiveCatching$1 = BufferedChannel$onReceiveCatching$1.INSTANCE;
        Intrinsics.checkNotNull(bufferedChannel$onReceiveCatching$1, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = 'clauseObject')] kotlin.Any, @[ParameterName(name = 'select')] kotlinx.coroutines.selects.SelectInstance<*>, @[ParameterName(name = 'param')] kotlin.Any?, kotlin.Unit>{ kotlinx.coroutines.selects.SelectKt.RegistrationFunction }");
        BufferedChannel$onReceiveCatching$2 bufferedChannel$onReceiveCatching$2 = BufferedChannel$onReceiveCatching$2.INSTANCE;
        Intrinsics.checkNotNull(bufferedChannel$onReceiveCatching$2, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = 'clauseObject')] kotlin.Any, @[ParameterName(name = 'param')] kotlin.Any?, @[ParameterName(name = 'clauseResult')] kotlin.Any?, kotlin.Any?>{ kotlinx.coroutines.selects.SelectKt.ProcessResultFunction }");
        return new SelectClause1Impl(this, (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(bufferedChannel$onReceiveCatching$1, 3), (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(bufferedChannel$onReceiveCatching$2, 3), this.onUndeliveredElementReceiveCancellationConstructor);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public SelectClause1<E> getOnReceiveOrNull() {
        BufferedChannel$onReceiveOrNull$1 bufferedChannel$onReceiveOrNull$1 = BufferedChannel$onReceiveOrNull$1.INSTANCE;
        Intrinsics.checkNotNull(bufferedChannel$onReceiveOrNull$1, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = 'clauseObject')] kotlin.Any, @[ParameterName(name = 'select')] kotlinx.coroutines.selects.SelectInstance<*>, @[ParameterName(name = 'param')] kotlin.Any?, kotlin.Unit>{ kotlinx.coroutines.selects.SelectKt.RegistrationFunction }");
        BufferedChannel$onReceiveOrNull$2 bufferedChannel$onReceiveOrNull$2 = BufferedChannel$onReceiveOrNull$2.INSTANCE;
        Intrinsics.checkNotNull(bufferedChannel$onReceiveOrNull$2, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = 'clauseObject')] kotlin.Any, @[ParameterName(name = 'param')] kotlin.Any?, @[ParameterName(name = 'clauseResult')] kotlin.Any?, kotlin.Any?>{ kotlinx.coroutines.selects.SelectKt.ProcessResultFunction }");
        return new SelectClause1Impl(this, (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(bufferedChannel$onReceiveOrNull$1, 3), (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(bufferedChannel$onReceiveOrNull$2, 3), this.onUndeliveredElementReceiveCancellationConstructor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void registerSelectForReceive(SelectInstance<?> selectInstance, Object ignoredParam) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        ChannelSegment segment$iv = (ChannelSegment) receiveSegment$FU.get(this);
        while (!isClosedForReceive()) {
            long r$iv = receivers$FU.getAndIncrement(this);
            long id$iv = r$iv / BufferedChannelKt.SEGMENT_SIZE;
            int i$iv = (int) (r$iv % BufferedChannelKt.SEGMENT_SIZE);
            if (segment$iv.id != id$iv) {
                ChannelSegment findSegmentReceive = findSegmentReceive(id$iv, segment$iv);
                if (findSegmentReceive == null) {
                    continue;
                } else {
                    segment$iv = findSegmentReceive;
                }
            }
            Object updCellResult$iv = updateCellReceive(segment$iv, i$iv, r$iv, selectInstance);
            symbol = BufferedChannelKt.SUSPEND;
            if (updCellResult$iv != symbol) {
                symbol2 = BufferedChannelKt.FAILED;
                if (updCellResult$iv != symbol2) {
                    symbol3 = BufferedChannelKt.SUSPEND_NO_WAITER;
                    if (updCellResult$iv == symbol3) {
                        throw new IllegalStateException("unexpected".toString());
                    }
                    segment$iv.cleanPrev();
                    selectInstance.selectInRegistrationPhase(updCellResult$iv);
                    return;
                } else if (r$iv < getSendersCounter$kotlinx_coroutines_core()) {
                    segment$iv.cleanPrev();
                }
            } else {
                Waiter waiter = selectInstance instanceof Waiter ? (Waiter) selectInstance : null;
                if (waiter != null) {
                    prepareReceiverForSuspension(waiter, segment$iv, i$iv);
                }
                return;
            }
        }
        onClosedSelectOnReceive(selectInstance);
    }

    private final void onClosedSelectOnReceive(SelectInstance<?> selectInstance) {
        selectInstance.selectInRegistrationPhase(BufferedChannelKt.getCHANNEL_CLOSED());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object processResultSelectReceive(Object ignoredParam, Object selectResult) {
        if (selectResult == BufferedChannelKt.getCHANNEL_CLOSED()) {
            throw getReceiveException();
        }
        return selectResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object processResultSelectReceiveOrNull(Object ignoredParam, Object selectResult) {
        if (selectResult == BufferedChannelKt.getCHANNEL_CLOSED()) {
            if (getCloseCause() == null) {
                return null;
            }
            throw getReceiveException();
        }
        return selectResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object processResultSelectReceiveCatching(Object ignoredParam, Object selectResult) {
        return ChannelResult.m1769boximpl(selectResult == BufferedChannelKt.getCHANNEL_CLOSED() ? ChannelResult.Companion.m1782closedJP2dKIU(getCloseCause()) : ChannelResult.Companion.m1784successJP2dKIU(selectResult));
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public ChannelIterator<E> iterator() {
        return new BufferedChannelIterator();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: BufferedChannel.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0011\u0010\t\u001a\u00020\u0006H\u0096Bø\u0001\u0000¢\u0006\u0002\u0010\nJ/\u0010\u000b\u001a\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J\u001c\u0010\u0013\u001a\u00020\u00142\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u000e\u0010\u0016\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0006H\u0002J\b\u0010\u0019\u001a\u00020\u0014H\u0002J\u0013\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00028\u0000¢\u0006\u0002\u0010\u001cJ\u0006\u0010\u001d\u001a\u00020\u0014R\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"Lkotlinx/coroutines/channels/BufferedChannel$BufferedChannelIterator;", "Lkotlinx/coroutines/channels/ChannelIterator;", "Lkotlinx/coroutines/Waiter;", "(Lkotlinx/coroutines/channels/BufferedChannel;)V", "continuation", "Lkotlinx/coroutines/CancellableContinuationImpl;", "", "receiveResult", "", "hasNext", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasNextOnNoWaiterSuspend", "segment", "Lkotlinx/coroutines/channels/ChannelSegment;", "index", "", "r", "", "(Lkotlinx/coroutines/channels/ChannelSegment;IJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invokeOnCancellation", "", "Lkotlinx/coroutines/internal/Segment;", "next", "()Ljava/lang/Object;", "onClosedHasNext", "onClosedHasNextNoWaiterSuspend", "tryResumeHasNext", "element", "(Ljava/lang/Object;)Z", "tryResumeHasNextOnClosedChannel", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public final class BufferedChannelIterator implements ChannelIterator<E>, Waiter {
        private CancellableContinuationImpl<? super Boolean> continuation;
        private Object receiveResult;

        public BufferedChannelIterator() {
            Symbol symbol;
            symbol = BufferedChannelKt.NO_RECEIVE_RESULT;
            this.receiveResult = symbol;
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.3.0, binary compatibility with versions <= 1.2.x")
        public /* synthetic */ Object next(Continuation $completion) {
            return ChannelIterator.DefaultImpls.next(this, $completion);
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        public Object hasNext(Continuation<? super Boolean> continuation) {
            ChannelSegment segment$iv;
            Symbol symbol;
            Symbol symbol2;
            Symbol symbol3;
            BufferedChannel this_$iv = BufferedChannel.this;
            ChannelSegment segment$iv2 = (ChannelSegment) BufferedChannel.receiveSegment$FU.get(this_$iv);
            while (!this_$iv.isClosedForReceive()) {
                long r$iv = BufferedChannel.receivers$FU.getAndIncrement(this_$iv);
                long id$iv = r$iv / BufferedChannelKt.SEGMENT_SIZE;
                int i$iv = (int) (r$iv % BufferedChannelKt.SEGMENT_SIZE);
                if (segment$iv2.id != id$iv) {
                    ChannelSegment segment$iv3 = this_$iv.findSegmentReceive(id$iv, segment$iv2);
                    if (segment$iv3 == null) {
                        continue;
                    } else {
                        segment$iv = segment$iv3;
                    }
                } else {
                    segment$iv = segment$iv2;
                }
                Object updCellResult$iv = this_$iv.updateCellReceive(segment$iv, i$iv, r$iv, null);
                symbol = BufferedChannelKt.SUSPEND;
                if (updCellResult$iv != symbol) {
                    symbol2 = BufferedChannelKt.FAILED;
                    if (updCellResult$iv != symbol2) {
                        symbol3 = BufferedChannelKt.SUSPEND_NO_WAITER;
                        if (updCellResult$iv != symbol3) {
                            segment$iv.cleanPrev();
                            this.receiveResult = updCellResult$iv;
                            return Boxing.boxBoolean(true);
                        }
                        ChannelSegment segm = segment$iv;
                        return hasNextOnNoWaiterSuspend(segm, i$iv, r$iv, continuation);
                    }
                    if (r$iv < this_$iv.getSendersCounter$kotlinx_coroutines_core()) {
                        segment$iv.cleanPrev();
                    }
                    segment$iv2 = segment$iv;
                } else {
                    throw new IllegalStateException("unreachable".toString());
                }
            }
            return Boxing.boxBoolean(onClosedHasNext());
        }

        private final boolean onClosedHasNext() {
            this.receiveResult = BufferedChannelKt.getCHANNEL_CLOSED();
            Throwable cause = BufferedChannel.this.getCloseCause();
            if (cause == null) {
                return false;
            }
            throw StackTraceRecoveryKt.recoverStackTrace(cause);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Object hasNextOnNoWaiterSuspend(ChannelSegment<E> channelSegment, int index, long r, Continuation<? super Boolean> continuation) {
            Symbol symbol;
            Symbol symbol2;
            ChannelSegment segment$iv$iv;
            Symbol symbol3;
            Symbol symbol4;
            Symbol symbol5;
            BufferedChannel this_$iv = BufferedChannel.this;
            boolean z = false;
            Continuation<? super Boolean> continuation2 = continuation;
            boolean z2 = false;
            CancellableContinuationImpl cancellable$iv = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation2));
            boolean z3 = false;
            try {
                this.continuation = cancellable$iv;
                Object updCellResult$iv = this_$iv.updateCellReceive(channelSegment, index, r, this);
                symbol = BufferedChannelKt.SUSPEND;
                try {
                    if (updCellResult$iv != symbol) {
                        symbol2 = BufferedChannelKt.FAILED;
                        try {
                            if (updCellResult$iv != symbol2) {
                                channelSegment.cleanPrev();
                                this.receiveResult = updCellResult$iv;
                                this.continuation = null;
                                Boolean boxBoolean = Boxing.boxBoolean(true);
                                Function1<E, Unit> function1 = this_$iv.onUndeliveredElement;
                                cancellable$iv.resume(boxBoolean, function1 != null ? OnUndeliveredElementKt.bindCancellationFun(function1, updCellResult$iv, cancellable$iv.getContext()) : null);
                            } else {
                                if (r < this_$iv.getSendersCounter$kotlinx_coroutines_core()) {
                                    channelSegment.cleanPrev();
                                }
                                ChannelSegment segment$iv$iv2 = (ChannelSegment) BufferedChannel.receiveSegment$FU.get(this_$iv);
                                while (!this_$iv.isClosedForReceive()) {
                                    long r$iv$iv = BufferedChannel.receivers$FU.getAndIncrement(this_$iv);
                                    long id$iv$iv = r$iv$iv / BufferedChannelKt.SEGMENT_SIZE;
                                    boolean z4 = z;
                                    Continuation<? super Boolean> continuation3 = continuation2;
                                    try {
                                        int i$iv$iv = (int) (r$iv$iv % BufferedChannelKt.SEGMENT_SIZE);
                                        boolean z5 = z2;
                                        if (segment$iv$iv2.id != id$iv$iv) {
                                            segment$iv$iv = this_$iv.findSegmentReceive(id$iv$iv, segment$iv$iv2);
                                            if (segment$iv$iv == null) {
                                                z = z4;
                                                continuation2 = continuation3;
                                                z2 = z5;
                                            }
                                        } else {
                                            segment$iv$iv = segment$iv$iv2;
                                        }
                                        boolean z6 = z3;
                                        Object updCellResult$iv2 = updCellResult$iv;
                                        Object updCellResult$iv$iv = this_$iv.updateCellReceive(segment$iv$iv, i$iv$iv, r$iv$iv, this);
                                        symbol3 = BufferedChannelKt.SUSPEND;
                                        if (updCellResult$iv$iv != symbol3) {
                                            symbol4 = BufferedChannelKt.FAILED;
                                            if (updCellResult$iv$iv != symbol4) {
                                                symbol5 = BufferedChannelKt.SUSPEND_NO_WAITER;
                                                if (updCellResult$iv$iv == symbol5) {
                                                    throw new IllegalStateException("unexpected".toString());
                                                }
                                                segment$iv$iv.cleanPrev();
                                                this.receiveResult = updCellResult$iv$iv;
                                                this.continuation = null;
                                                Boolean boxBoolean2 = Boxing.boxBoolean(true);
                                                Function1<E, Unit> function12 = this_$iv.onUndeliveredElement;
                                                cancellable$iv.resume(boxBoolean2, function12 != null ? OnUndeliveredElementKt.bindCancellationFun(function12, updCellResult$iv$iv, cancellable$iv.getContext()) : null);
                                            } else {
                                                if (r$iv$iv < this_$iv.getSendersCounter$kotlinx_coroutines_core()) {
                                                    segment$iv$iv.cleanPrev();
                                                }
                                                segment$iv$iv2 = segment$iv$iv;
                                                z3 = z6;
                                                z = z4;
                                                continuation2 = continuation3;
                                                z2 = z5;
                                                updCellResult$iv = updCellResult$iv2;
                                            }
                                        } else {
                                            BufferedChannelIterator bufferedChannelIterator = this instanceof Waiter ? this : null;
                                            if (bufferedChannelIterator != null) {
                                                this_$iv.prepareReceiverForSuspension(bufferedChannelIterator, segment$iv$iv, i$iv$iv);
                                            }
                                        }
                                    } catch (Throwable th) {
                                        e$iv = th;
                                        cancellable$iv.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                                        throw e$iv;
                                    }
                                }
                                onClosedHasNextNoWaiterSuspend();
                            }
                        } catch (Throwable th2) {
                            e$iv = th2;
                        }
                    } else {
                        try {
                            this_$iv.prepareReceiverForSuspension(this, channelSegment, index);
                        } catch (Throwable th3) {
                            e$iv = th3;
                            cancellable$iv.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                            throw e$iv;
                        }
                    }
                    Object result = cancellable$iv.getResult();
                    if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        DebugProbesKt.probeCoroutineSuspended(continuation);
                    }
                    return result;
                } catch (Throwable th4) {
                    e$iv = th4;
                }
            } catch (Throwable th5) {
                e$iv = th5;
            }
        }

        @Override // kotlinx.coroutines.Waiter
        public void invokeOnCancellation(Segment<?> segment, int index) {
            CancellableContinuationImpl<? super Boolean> cancellableContinuationImpl = this.continuation;
            if (cancellableContinuationImpl != null) {
                cancellableContinuationImpl.invokeOnCancellation(segment, index);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void onClosedHasNextNoWaiterSuspend() {
            Throwable th;
            CancellableContinuationImpl cont = this.continuation;
            Intrinsics.checkNotNull(cont);
            this.continuation = null;
            this.receiveResult = BufferedChannelKt.getCHANNEL_CLOSED();
            Throwable cause = BufferedChannel.this.getCloseCause();
            if (cause == null) {
                Result.Companion companion = Result.Companion;
                cont.resumeWith(Result.m256constructorimpl(false));
                return;
            }
            CancellableContinuationImpl cancellableContinuationImpl = cont;
            if (DebugKt.getRECOVER_STACK_TRACES() && (cont instanceof CoroutineStackFrame)) {
                th = StackTraceRecoveryKt.recoverFromStackFrame(cause, cont);
            } else {
                th = cause;
            }
            Result.Companion companion2 = Result.Companion;
            cancellableContinuationImpl.resumeWith(Result.m256constructorimpl(ResultKt.createFailure(th)));
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        public E next() {
            Symbol symbol;
            Symbol symbol2;
            E e = (E) this.receiveResult;
            symbol = BufferedChannelKt.NO_RECEIVE_RESULT;
            if (e != symbol) {
                symbol2 = BufferedChannelKt.NO_RECEIVE_RESULT;
                this.receiveResult = symbol2;
                if (e == BufferedChannelKt.getCHANNEL_CLOSED()) {
                    throw StackTraceRecoveryKt.recoverStackTrace(BufferedChannel.this.getReceiveException());
                }
                return e;
            }
            throw new IllegalStateException("`hasNext()` has not been invoked".toString());
        }

        public final boolean tryResumeHasNext(E e) {
            boolean tryResume0;
            CancellableContinuationImpl cont = this.continuation;
            Intrinsics.checkNotNull(cont);
            this.continuation = null;
            this.receiveResult = e;
            CancellableContinuationImpl cancellableContinuationImpl = cont;
            Function1<E, Unit> function1 = BufferedChannel.this.onUndeliveredElement;
            tryResume0 = BufferedChannelKt.tryResume0(cancellableContinuationImpl, true, function1 != null ? OnUndeliveredElementKt.bindCancellationFun(function1, e, cont.getContext()) : null);
            return tryResume0;
        }

        public final void tryResumeHasNextOnClosedChannel() {
            Throwable th;
            CancellableContinuationImpl cont = this.continuation;
            Intrinsics.checkNotNull(cont);
            this.continuation = null;
            this.receiveResult = BufferedChannelKt.getCHANNEL_CLOSED();
            Throwable cause = BufferedChannel.this.getCloseCause();
            if (cause == null) {
                Result.Companion companion = Result.Companion;
                cont.resumeWith(Result.m256constructorimpl(false));
                return;
            }
            CancellableContinuationImpl cancellableContinuationImpl = cont;
            if (DebugKt.getRECOVER_STACK_TRACES() && (cont instanceof CoroutineStackFrame)) {
                th = StackTraceRecoveryKt.recoverFromStackFrame(cause, cont);
            } else {
                th = cause;
            }
            Result.Companion companion2 = Result.Companion;
            cancellableContinuationImpl.resumeWith(Result.m256constructorimpl(ResultKt.createFailure(th)));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Throwable getCloseCause() {
        return (Throwable) _closeCause$FU.get(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Throwable getSendException() {
        Throwable closeCause = getCloseCause();
        return closeCause == null ? new ClosedSendChannelException(ChannelsKt.DEFAULT_CLOSE_MESSAGE) : closeCause;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Throwable getReceiveException() {
        Throwable closeCause = getCloseCause();
        return closeCause == null ? new ClosedReceiveChannelException(ChannelsKt.DEFAULT_CLOSE_MESSAGE) : closeCause;
    }

    protected void onClosedIdempotent() {
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean close(Throwable cause) {
        return closeOrCancelImpl(cause, false);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final boolean cancel(Throwable cause) {
        return cancelImpl$kotlinx_coroutines_core(cause);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final void cancel() {
        cancelImpl$kotlinx_coroutines_core(null);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final void cancel(CancellationException cause) {
        cancelImpl$kotlinx_coroutines_core(cause);
    }

    public boolean cancelImpl$kotlinx_coroutines_core(Throwable cause) {
        return closeOrCancelImpl(cause == null ? new CancellationException("Channel was cancelled") : cause, true);
    }

    protected boolean closeOrCancelImpl(Throwable cause, boolean cancel) {
        Symbol symbol;
        if (cancel) {
            markCancellationStarted();
        }
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _closeCause$FU;
        symbol = BufferedChannelKt.NO_CLOSE_CAUSE;
        boolean closedByThisOperation = AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, symbol, cause);
        if (cancel) {
            markCancelled();
        } else {
            markClosed();
        }
        completeCloseOrCancel();
        onClosedIdempotent();
        if (closedByThisOperation) {
            invokeCloseHandler();
        }
        return closedByThisOperation;
    }

    private final void invokeCloseHandler() {
        Object it;
        AtomicReferenceFieldUpdater atomicfu$handler$iv = closeHandler$FU;
        do {
            it = atomicfu$handler$iv.get(this);
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicfu$handler$iv, this, it, it == null ? BufferedChannelKt.CLOSE_HANDLER_CLOSED : BufferedChannelKt.CLOSE_HANDLER_INVOKED));
        if (it != null) {
            Function1 function1 = (Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(it, 1);
            ((Function1) it).invoke(getCloseCause());
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public void invokeOnClose(Function1<? super Throwable, Unit> function1) {
        Symbol symbol;
        Symbol symbol2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        Symbol symbol3;
        Symbol symbol4;
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(closeHandler$FU, this, null, function1)) {
            return;
        }
        AtomicReferenceFieldUpdater atomicfu$handler$iv = closeHandler$FU;
        do {
            Object cur = atomicfu$handler$iv.get(this);
            symbol = BufferedChannelKt.CLOSE_HANDLER_CLOSED;
            if (cur != symbol) {
                symbol2 = BufferedChannelKt.CLOSE_HANDLER_INVOKED;
                if (cur != symbol2) {
                    throw new IllegalStateException(("Another handler is already registered: " + cur).toString());
                }
                throw new IllegalStateException("Another handler was already registered and successfully invoked".toString());
            }
            atomicReferenceFieldUpdater = closeHandler$FU;
            symbol3 = BufferedChannelKt.CLOSE_HANDLER_CLOSED;
            symbol4 = BufferedChannelKt.CLOSE_HANDLER_INVOKED;
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, symbol3, symbol4));
        function1.invoke(getCloseCause());
    }

    private final void markClosed() {
        long cur;
        long constructSendersAndCloseStatus;
        AtomicLongFieldUpdater atomicfu$handler$iv = sendersAndCloseStatus$FU;
        do {
            cur = atomicfu$handler$iv.get(this);
            int $i$f$getSendersCloseStatus = (int) (cur >> 60);
            switch ($i$f$getSendersCloseStatus) {
                case 0:
                    constructSendersAndCloseStatus = BufferedChannelKt.constructSendersAndCloseStatus(1152921504606846975L & cur, 2);
                    break;
                case 1:
                    constructSendersAndCloseStatus = BufferedChannelKt.constructSendersAndCloseStatus(1152921504606846975L & cur, 3);
                    break;
                default:
                    return;
            }
        } while (!atomicfu$handler$iv.compareAndSet(this, cur, constructSendersAndCloseStatus));
    }

    private final void markCancelled() {
        long cur;
        long constructSendersAndCloseStatus;
        AtomicLongFieldUpdater atomicfu$handler$iv = sendersAndCloseStatus$FU;
        do {
            cur = atomicfu$handler$iv.get(this);
            long $this$sendersCounter$iv = cur & 1152921504606846975L;
            constructSendersAndCloseStatus = BufferedChannelKt.constructSendersAndCloseStatus($this$sendersCounter$iv, 3);
        } while (!atomicfu$handler$iv.compareAndSet(this, cur, constructSendersAndCloseStatus));
    }

    private final void markCancellationStarted() {
        long cur;
        long constructSendersAndCloseStatus;
        AtomicLongFieldUpdater atomicfu$handler$iv = sendersAndCloseStatus$FU;
        do {
            cur = atomicfu$handler$iv.get(this);
            int $i$f$getSendersCloseStatus = (int) (cur >> 60);
            if ($i$f$getSendersCloseStatus == 0) {
                long $this$sendersCounter$iv = cur & 1152921504606846975L;
                constructSendersAndCloseStatus = BufferedChannelKt.constructSendersAndCloseStatus($this$sendersCounter$iv, 1);
            } else {
                return;
            }
        } while (!atomicfu$handler$iv.compareAndSet(this, cur, constructSendersAndCloseStatus));
    }

    private final void completeCloseOrCancel() {
        isClosedForSend();
    }

    protected boolean isConflatedDropOldest() {
        return false;
    }

    private final ChannelSegment<E> completeClose(long sendersCur) {
        ChannelSegment lastSegment = closeLinkedList();
        if (isConflatedDropOldest()) {
            long lastBufferedCellGlobalIndex = markAllEmptyCellsAsClosed(lastSegment);
            if (lastBufferedCellGlobalIndex != -1) {
                dropFirstElementUntilTheSpecifiedCellIsInTheBuffer(lastBufferedCellGlobalIndex);
            }
        }
        cancelSuspendedReceiveRequests(lastSegment, sendersCur);
        return lastSegment;
    }

    private final void completeCancel(long sendersCur) {
        ChannelSegment lastSegment = completeClose(sendersCur);
        removeUnprocessedElements(lastSegment);
    }

    private final ChannelSegment<E> closeLinkedList() {
        Object lastSegment = bufferEndSegment$FU.get(this);
        ChannelSegment it = (ChannelSegment) sendSegment$FU.get(this);
        if (it.id > ((ChannelSegment) lastSegment).id) {
            lastSegment = it;
        }
        ChannelSegment it2 = (ChannelSegment) receiveSegment$FU.get(this);
        if (it2.id > ((ChannelSegment) lastSegment).id) {
            lastSegment = it2;
        }
        return (ChannelSegment) ConcurrentLinkedListKt.close((ConcurrentLinkedListNode) lastSegment);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0042, code lost:
        r1 = (kotlinx.coroutines.channels.ChannelSegment) r0.getPrev();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0048, code lost:
        if (r1 != null) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004a, code lost:
        return -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final long markAllEmptyCellsAsClosed(kotlinx.coroutines.channels.ChannelSegment<E> r9) {
        /*
            r8 = this;
            r0 = r9
        L1:
            int r1 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            int r1 = r1 + (-1)
        L6:
            r2 = -1
            r4 = -1
            if (r4 >= r1) goto L42
            long r4 = r0.id
            int r6 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r6 = (long) r6
            long r4 = r4 * r6
            long r6 = (long) r1
            long r4 = r4 + r6
            long r6 = r8.getReceiversCounter$kotlinx_coroutines_core()
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 >= 0) goto L1c
            return r2
        L1c:
            java.lang.Object r2 = r0.getState$kotlinx_coroutines_core(r1)
            if (r2 == 0) goto L31
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.access$getIN_BUFFER$p()
            if (r2 != r3) goto L2b
            goto L31
        L2b:
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.BUFFERED
            if (r2 != r3) goto L30
            return r4
        L30:
            goto L3f
        L31:
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.getCHANNEL_CLOSED()
            boolean r3 = r0.casState$kotlinx_coroutines_core(r1, r2, r3)
            if (r3 == 0) goto L1c
            r0.onSlotCleaned()
        L3f:
            int r1 = r1 + (-1)
            goto L6
        L42:
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r1 = r0.getPrev()
            kotlinx.coroutines.channels.ChannelSegment r1 = (kotlinx.coroutines.channels.ChannelSegment) r1
            if (r1 != 0) goto L4b
            return r2
        L4b:
            r0 = r1
            goto L1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.markAllEmptyCellsAsClosed(kotlinx.coroutines.channels.ChannelSegment):long");
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00ba, code lost:
        r5 = (kotlinx.coroutines.channels.ChannelSegment) r4.getPrev();
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00c0, code lost:
        if (r5 != null) goto L87;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void removeUnprocessedElements(kotlinx.coroutines.channels.ChannelSegment<E> r13) {
        /*
            Method dump skipped, instructions count: 249
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.removeUnprocessedElements(kotlinx.coroutines.channels.ChannelSegment):void");
    }

    private final void cancelSuspendedReceiveRequests(ChannelSegment<E> channelSegment, long sendersCounter) {
        Symbol symbol;
        Object suspendedReceivers = InlineList.m1806constructorimpl$default(null, 1, null);
        loop0: for (ChannelSegment segment = channelSegment; segment != null; segment = (ChannelSegment) segment.getPrev()) {
            for (int index = BufferedChannelKt.SEGMENT_SIZE - 1; -1 < index; index--) {
                if ((segment.id * BufferedChannelKt.SEGMENT_SIZE) + index < sendersCounter) {
                    break loop0;
                }
                while (true) {
                    Object state = segment.getState$kotlinx_coroutines_core(index);
                    if (state != null) {
                        symbol = BufferedChannelKt.IN_BUFFER;
                        if (state != symbol) {
                            if (state instanceof WaiterEB) {
                                if (segment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.getCHANNEL_CLOSED())) {
                                    suspendedReceivers = InlineList.m1811plusFjFbRPM(suspendedReceivers, ((WaiterEB) state).waiter);
                                    segment.onCancelledRequest(index, true);
                                    break;
                                }
                            } else if (!(state instanceof Waiter)) {
                                break;
                            } else if (segment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.getCHANNEL_CLOSED())) {
                                suspendedReceivers = InlineList.m1811plusFjFbRPM(suspendedReceivers, state);
                                segment.onCancelledRequest(index, true);
                                break;
                            }
                        }
                    }
                    if (segment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.getCHANNEL_CLOSED())) {
                        segment.onSlotCleaned();
                        break;
                    }
                }
            }
        }
        if (suspendedReceivers == null) {
            return;
        }
        if (!(suspendedReceivers instanceof ArrayList)) {
            Waiter it = (Waiter) suspendedReceivers;
            resumeReceiverOnClosedChannel(it);
            return;
        }
        Intrinsics.checkNotNull(suspendedReceivers, "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>{ kotlin.collections.TypeAliasesKt.ArrayList<E of kotlinx.coroutines.internal.InlineList> }");
        ArrayList list$iv = (ArrayList) suspendedReceivers;
        for (int i$iv = list$iv.size() - 1; -1 < i$iv; i$iv--) {
            Waiter it2 = (Waiter) list$iv.get(i$iv);
            resumeReceiverOnClosedChannel(it2);
        }
    }

    private final void resumeReceiverOnClosedChannel(Waiter $this$resumeReceiverOnClosedChannel) {
        resumeWaiterOnClosedChannel($this$resumeReceiverOnClosedChannel, true);
    }

    private final void resumeSenderOnCancelledChannel(Waiter $this$resumeSenderOnCancelledChannel) {
        resumeWaiterOnClosedChannel($this$resumeSenderOnCancelledChannel, false);
    }

    private final void resumeWaiterOnClosedChannel(Waiter $this$resumeWaiterOnClosedChannel, boolean receiver) {
        if (!($this$resumeWaiterOnClosedChannel instanceof SendBroadcast)) {
            if (!($this$resumeWaiterOnClosedChannel instanceof CancellableContinuation)) {
                if (!($this$resumeWaiterOnClosedChannel instanceof ReceiveCatching)) {
                    if (!($this$resumeWaiterOnClosedChannel instanceof BufferedChannelIterator)) {
                        if (!($this$resumeWaiterOnClosedChannel instanceof SelectInstance)) {
                            throw new IllegalStateException(("Unexpected waiter: " + $this$resumeWaiterOnClosedChannel).toString());
                        }
                        ((SelectInstance) $this$resumeWaiterOnClosedChannel).trySelect(this, BufferedChannelKt.getCHANNEL_CLOSED());
                        return;
                    }
                    ((BufferedChannelIterator) $this$resumeWaiterOnClosedChannel).tryResumeHasNextOnClosedChannel();
                    return;
                }
                Result.Companion companion = Result.Companion;
                ((ReceiveCatching) $this$resumeWaiterOnClosedChannel).cont.resumeWith(Result.m256constructorimpl(ChannelResult.m1769boximpl(ChannelResult.Companion.m1782closedJP2dKIU(getCloseCause()))));
                return;
            }
            Continuation continuation = (Continuation) $this$resumeWaiterOnClosedChannel;
            Result.Companion companion2 = Result.Companion;
            continuation.resumeWith(Result.m256constructorimpl(ResultKt.createFailure(receiver ? getReceiveException() : getSendException())));
            return;
        }
        Result.Companion companion3 = Result.Companion;
        ((SendBroadcast) $this$resumeWaiterOnClosedChannel).getCont().resumeWith(Result.m256constructorimpl(false));
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean isClosedForSend() {
        return isClosedForSend0(sendersAndCloseStatus$FU.get(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isClosedForSend0(long $this$isClosedForSend0) {
        return isClosed($this$isClosedForSend0, false);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        return isClosedForReceive0(sendersAndCloseStatus$FU.get(this));
    }

    private final boolean isClosedForReceive0(long $this$isClosedForReceive0) {
        return isClosed($this$isClosedForReceive0, true);
    }

    private final boolean isClosed(long sendersAndCloseStatusCur, boolean isClosedForReceive) {
        long $this$sendersCloseStatus$iv = (int) (sendersAndCloseStatusCur >> 60);
        switch ($this$sendersCloseStatus$iv) {
            case 0:
                return false;
            case 1:
                return false;
            case 2:
                completeClose(sendersAndCloseStatusCur & 1152921504606846975L);
                return (isClosedForReceive && hasElements$kotlinx_coroutines_core()) ? false : true;
            case 3:
                completeCancel(sendersAndCloseStatusCur & 1152921504606846975L);
                return true;
            default:
                throw new IllegalStateException(("unexpected close status: " + ((int) (sendersAndCloseStatusCur >> 60))).toString());
        }
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        if (isClosedForReceive() || hasElements$kotlinx_coroutines_core()) {
            return false;
        }
        return !isClosedForReceive();
    }

    public final boolean hasElements$kotlinx_coroutines_core() {
        while (true) {
            ChannelSegment segment = (ChannelSegment) receiveSegment$FU.get(this);
            long r = getReceiversCounter$kotlinx_coroutines_core();
            long s = getSendersCounter$kotlinx_coroutines_core();
            if (s <= r) {
                return false;
            }
            long id = r / BufferedChannelKt.SEGMENT_SIZE;
            if (segment.id != id) {
                ChannelSegment findSegmentReceive = findSegmentReceive(id, segment);
                if (findSegmentReceive != null) {
                    segment = findSegmentReceive;
                } else if (((ChannelSegment) receiveSegment$FU.get(this)).id < id) {
                    return false;
                }
            }
            segment.cleanPrev();
            int i = (int) (r % BufferedChannelKt.SEGMENT_SIZE);
            if (isCellNonEmpty(segment, i, r)) {
                return true;
            }
            receivers$FU.compareAndSet(this, r, r + 1);
        }
    }

    private final boolean isCellNonEmpty(ChannelSegment<E> channelSegment, int index, long globalIndex) {
        Object state;
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        Symbol symbol6;
        Symbol symbol7;
        do {
            state = channelSegment.getState$kotlinx_coroutines_core(index);
            if (state != null) {
                symbol2 = BufferedChannelKt.IN_BUFFER;
                if (state != symbol2) {
                    if (state == BufferedChannelKt.BUFFERED) {
                        return true;
                    }
                    symbol3 = BufferedChannelKt.INTERRUPTED_SEND;
                    if (state != symbol3 && state != BufferedChannelKt.getCHANNEL_CLOSED()) {
                        symbol4 = BufferedChannelKt.DONE_RCV;
                        if (state != symbol4) {
                            symbol5 = BufferedChannelKt.POISONED;
                            if (state != symbol5) {
                                symbol6 = BufferedChannelKt.RESUMING_BY_EB;
                                if (state == symbol6) {
                                    return true;
                                }
                                symbol7 = BufferedChannelKt.RESUMING_BY_RCV;
                                return state != symbol7 && globalIndex == getReceiversCounter$kotlinx_coroutines_core();
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
            }
            symbol = BufferedChannelKt.POISONED;
        } while (!channelSegment.casState$kotlinx_coroutines_core(index, state, symbol));
        expandBuffer();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ChannelSegment<E> findSegmentSend(long id, ChannelSegment<E> channelSegment) {
        Object s$iv;
        Function2 createNewSegment$iv;
        boolean z;
        boolean z2;
        AtomicReferenceFieldUpdater atomicfu$handler$iv = sendSegment$FU;
        Function2 createNewSegment$iv2 = (Function2) BufferedChannelKt.createSegmentFunction();
        boolean z3 = false;
        while (true) {
            s$iv = ConcurrentLinkedListKt.findSegmentInternal(channelSegment, id, createNewSegment$iv2);
            if (SegmentOrClosed.m1823isClosedimpl(s$iv)) {
                break;
            }
            Segment to$iv$iv = SegmentOrClosed.m1821getSegmentimpl(s$iv);
            while (true) {
                Segment cur$iv$iv = (Segment) atomicfu$handler$iv.get(this);
                createNewSegment$iv = createNewSegment$iv2;
                z = z3;
                if (cur$iv$iv.id >= to$iv$iv.id) {
                    z2 = true;
                    break;
                } else if (!to$iv$iv.tryIncPointers$kotlinx_coroutines_core()) {
                    z2 = false;
                    break;
                } else if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicfu$handler$iv, this, cur$iv$iv, to$iv$iv)) {
                    if (cur$iv$iv.decPointers$kotlinx_coroutines_core()) {
                        cur$iv$iv.remove();
                    }
                    z2 = true;
                } else {
                    if (to$iv$iv.decPointers$kotlinx_coroutines_core()) {
                        to$iv$iv.remove();
                    }
                    createNewSegment$iv2 = createNewSegment$iv;
                    z3 = z;
                }
            }
            if (z2) {
                break;
            }
            createNewSegment$iv2 = createNewSegment$iv;
            z3 = z;
        }
        if (SegmentOrClosed.m1823isClosedimpl(s$iv)) {
            completeCloseOrCancel();
            if (channelSegment.id * BufferedChannelKt.SEGMENT_SIZE < getReceiversCounter$kotlinx_coroutines_core()) {
                channelSegment.cleanPrev();
                return null;
            }
            return null;
        }
        ChannelSegment segment = (ChannelSegment) SegmentOrClosed.m1821getSegmentimpl(s$iv);
        if (segment.id > id) {
            updateSendersCounterIfLower(segment.id * BufferedChannelKt.SEGMENT_SIZE);
            if (segment.id * BufferedChannelKt.SEGMENT_SIZE < getReceiversCounter$kotlinx_coroutines_core()) {
                segment.cleanPrev();
                return null;
            }
            return null;
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(segment.id == id)) {
                throw new AssertionError();
            }
        }
        return segment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ChannelSegment<E> findSegmentReceive(long id, ChannelSegment<E> channelSegment) {
        Object s$iv;
        Function2 createNewSegment$iv;
        boolean z;
        boolean z2;
        AtomicReferenceFieldUpdater atomicfu$handler$iv = receiveSegment$FU;
        Function2 createNewSegment$iv2 = (Function2) BufferedChannelKt.createSegmentFunction();
        boolean z3 = false;
        while (true) {
            s$iv = ConcurrentLinkedListKt.findSegmentInternal(channelSegment, id, createNewSegment$iv2);
            if (SegmentOrClosed.m1823isClosedimpl(s$iv)) {
                break;
            }
            Segment to$iv$iv = SegmentOrClosed.m1821getSegmentimpl(s$iv);
            while (true) {
                Segment cur$iv$iv = (Segment) atomicfu$handler$iv.get(this);
                createNewSegment$iv = createNewSegment$iv2;
                z = z3;
                if (cur$iv$iv.id >= to$iv$iv.id) {
                    z2 = true;
                    break;
                } else if (!to$iv$iv.tryIncPointers$kotlinx_coroutines_core()) {
                    z2 = false;
                    break;
                } else if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicfu$handler$iv, this, cur$iv$iv, to$iv$iv)) {
                    if (cur$iv$iv.decPointers$kotlinx_coroutines_core()) {
                        cur$iv$iv.remove();
                    }
                    z2 = true;
                } else {
                    if (to$iv$iv.decPointers$kotlinx_coroutines_core()) {
                        to$iv$iv.remove();
                    }
                    createNewSegment$iv2 = createNewSegment$iv;
                    z3 = z;
                }
            }
            if (z2) {
                break;
            }
            createNewSegment$iv2 = createNewSegment$iv;
            z3 = z;
        }
        Object it = s$iv;
        if (SegmentOrClosed.m1823isClosedimpl(it)) {
            completeCloseOrCancel();
            if (channelSegment.id * BufferedChannelKt.SEGMENT_SIZE < getSendersCounter$kotlinx_coroutines_core()) {
                channelSegment.cleanPrev();
            }
            return null;
        }
        ChannelSegment segment = (ChannelSegment) SegmentOrClosed.m1821getSegmentimpl(it);
        if (!isRendezvousOrUnlimited() && id <= getBufferEndCounter() / BufferedChannelKt.SEGMENT_SIZE) {
            AtomicReferenceFieldUpdater atomicfu$handler$iv2 = bufferEndSegment$FU;
            while (true) {
                Segment cur$iv = (Segment) atomicfu$handler$iv2.get(this);
                Object it2 = it;
                if (cur$iv.id >= segment.id || !segment.tryIncPointers$kotlinx_coroutines_core()) {
                    break;
                } else if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicfu$handler$iv2, this, cur$iv, segment)) {
                    if (cur$iv.decPointers$kotlinx_coroutines_core()) {
                        cur$iv.remove();
                    }
                } else {
                    if (segment.decPointers$kotlinx_coroutines_core()) {
                        segment.remove();
                    }
                    it = it2;
                }
            }
        }
        if (segment.id > id) {
            updateReceiversCounterIfLower(segment.id * BufferedChannelKt.SEGMENT_SIZE);
            if (segment.id * BufferedChannelKt.SEGMENT_SIZE < getSendersCounter$kotlinx_coroutines_core()) {
                segment.cleanPrev();
            }
            return null;
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(segment.id == id)) {
                throw new AssertionError();
            }
        }
        return segment;
    }

    private final ChannelSegment<E> findSegmentBufferEnd(long id, ChannelSegment<E> channelSegment, long currentBufferEndCounter) {
        Object s$iv;
        boolean z;
        AtomicReferenceFieldUpdater atomicfu$handler$iv = bufferEndSegment$FU;
        Function2 createNewSegment$iv = (Function2) BufferedChannelKt.createSegmentFunction();
        do {
            s$iv = ConcurrentLinkedListKt.findSegmentInternal(channelSegment, id, createNewSegment$iv);
            if (SegmentOrClosed.m1823isClosedimpl(s$iv)) {
                break;
            }
            Segment to$iv$iv = SegmentOrClosed.m1821getSegmentimpl(s$iv);
            int $i$f$moveForward$atomicfu = 0;
            while (true) {
                Segment cur$iv$iv = (Segment) atomicfu$handler$iv.get(this);
                int $i$f$moveForward$atomicfu2 = $i$f$moveForward$atomicfu;
                if (cur$iv$iv.id >= to$iv$iv.id) {
                    z = true;
                    continue;
                    break;
                } else if (!to$iv$iv.tryIncPointers$kotlinx_coroutines_core()) {
                    z = false;
                    continue;
                    break;
                } else if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicfu$handler$iv, this, cur$iv$iv, to$iv$iv)) {
                    if (cur$iv$iv.decPointers$kotlinx_coroutines_core()) {
                        cur$iv$iv.remove();
                    }
                    z = true;
                    continue;
                } else {
                    if (to$iv$iv.decPointers$kotlinx_coroutines_core()) {
                        to$iv$iv.remove();
                    }
                    $i$f$moveForward$atomicfu = $i$f$moveForward$atomicfu2;
                }
            }
        } while (!z);
        if (SegmentOrClosed.m1823isClosedimpl(s$iv)) {
            completeCloseOrCancel();
            moveSegmentBufferEndToSpecifiedOrLast(id, channelSegment);
            incCompletedExpandBufferAttempts$default(this, 0L, 1, null);
            return null;
        }
        ChannelSegment segment = (ChannelSegment) SegmentOrClosed.m1821getSegmentimpl(s$iv);
        if (segment.id > id) {
            if (bufferEnd$FU.compareAndSet(this, currentBufferEndCounter + 1, segment.id * BufferedChannelKt.SEGMENT_SIZE)) {
                incCompletedExpandBufferAttempts((segment.id * BufferedChannelKt.SEGMENT_SIZE) - currentBufferEndCounter);
                return null;
            }
            incCompletedExpandBufferAttempts$default(this, 0L, 1, null);
            return null;
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(segment.id == id)) {
                throw new AssertionError();
            }
        }
        return segment;
    }

    private final void moveSegmentBufferEndToSpecifiedOrLast(long id, ChannelSegment<E> channelSegment) {
        boolean z;
        ChannelSegment channelSegment2;
        ChannelSegment channelSegment3;
        ChannelSegment segment = channelSegment;
        while (segment.id < id && (channelSegment3 = (ChannelSegment) segment.getNext()) != null) {
            segment = channelSegment3;
        }
        while (true) {
            if (!segment.isRemoved() || (channelSegment2 = (ChannelSegment) segment.getNext()) == null) {
                AtomicReferenceFieldUpdater atomicfu$handler$iv = bufferEndSegment$FU;
                while (true) {
                    Segment cur$iv = (Segment) atomicfu$handler$iv.get(this);
                    z = true;
                    if (cur$iv.id >= segment.id) {
                        break;
                    } else if (!segment.tryIncPointers$kotlinx_coroutines_core()) {
                        z = false;
                        break;
                    } else if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicfu$handler$iv, this, cur$iv, segment)) {
                        if (cur$iv.decPointers$kotlinx_coroutines_core()) {
                            cur$iv.remove();
                        }
                    } else if (segment.decPointers$kotlinx_coroutines_core()) {
                        segment.remove();
                    }
                }
                if (z) {
                    return;
                }
            } else {
                segment = channelSegment2;
            }
        }
    }

    private final void updateSendersCounterIfLower(long value) {
        long cur;
        long update;
        AtomicLongFieldUpdater atomicfu$handler$iv = sendersAndCloseStatus$FU;
        do {
            cur = atomicfu$handler$iv.get(this);
            long $this$sendersCounter$iv = cur & 1152921504606846975L;
            if ($this$sendersCounter$iv >= value) {
                return;
            }
            update = BufferedChannelKt.constructSendersAndCloseStatus($this$sendersCounter$iv, (int) (cur >> 60));
        } while (!sendersAndCloseStatus$FU.compareAndSet(this, cur, update));
    }

    private final void updateReceiversCounterIfLower(long value) {
        long cur;
        AtomicLongFieldUpdater atomicfu$handler$iv = receivers$FU;
        do {
            cur = atomicfu$handler$iv.get(this);
            if (cur >= value) {
                return;
            }
        } while (!receivers$FU.compareAndSet(this, cur, value));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String toString() {
        Symbol symbol;
        Symbol symbol2;
        boolean areEqual;
        Symbol symbol3;
        boolean areEqual2;
        Symbol symbol4;
        boolean areEqual3;
        Symbol symbol5;
        boolean areEqual4;
        Symbol symbol6;
        boolean areEqual5;
        Symbol symbol7;
        boolean areEqual6;
        String obj;
        ChannelSegment channelSegment;
        StringBuilder sb = new StringBuilder();
        long $this$sendersCloseStatus$iv = sendersAndCloseStatus$FU.get(this);
        switch ((int) ($this$sendersCloseStatus$iv >> 60)) {
            case 2:
                sb.append("closed,");
                break;
            case 3:
                sb.append("cancelled,");
                break;
        }
        sb.append("capacity=" + this.capacity + ',');
        sb.append("data=[");
        Iterable $this$filter$iv = CollectionsKt.listOf((Object[]) new ChannelSegment[]{receiveSegment$FU.get(this), sendSegment$FU.get(this), bufferEndSegment$FU.get(this)});
        ArrayList arrayList = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            ChannelSegment it = (ChannelSegment) element$iv$iv;
            channelSegment = BufferedChannelKt.NULL_SEGMENT;
            ChannelSegment it2 = it != channelSegment ? 1 : null;
            if (it2 != null) {
                arrayList.add(element$iv$iv);
            }
        }
        ArrayList $this$minBy$iv = arrayList;
        Iterator iterator$iv = $this$minBy$iv.iterator();
        if (iterator$iv.hasNext()) {
            Object minElem$iv = iterator$iv.next();
            if (iterator$iv.hasNext()) {
                ChannelSegment it3 = (ChannelSegment) minElem$iv;
                long minValue$iv = it3.id;
                while (true) {
                    Object e$iv = iterator$iv.next();
                    ChannelSegment it4 = (ChannelSegment) e$iv;
                    long v$iv = it4.id;
                    if (minValue$iv > v$iv) {
                        minElem$iv = e$iv;
                        minValue$iv = v$iv;
                    }
                    if (!iterator$iv.hasNext()) {
                    }
                }
            }
            ChannelSegment firstSegment = (ChannelSegment) minElem$iv;
            long r = getReceiversCounter$kotlinx_coroutines_core();
            long s = getSendersCounter$kotlinx_coroutines_core();
            ChannelSegment segment = firstSegment;
            while (true) {
                int i = 0;
                int i2 = BufferedChannelKt.SEGMENT_SIZE;
                while (true) {
                    if (i < i2) {
                        ChannelSegment firstSegment2 = firstSegment;
                        long globalCellIndex = (segment.id * BufferedChannelKt.SEGMENT_SIZE) + i;
                        if (globalCellIndex < s || globalCellIndex < r) {
                            Object cellState = segment.getState$kotlinx_coroutines_core(i);
                            Object element = segment.getElement$kotlinx_coroutines_core(i);
                            if (cellState instanceof CancellableContinuation) {
                                obj = (globalCellIndex >= r || globalCellIndex < s) ? (globalCellIndex >= s || globalCellIndex < r) ? "cont" : "send" : "receive";
                            } else if (cellState instanceof SelectInstance) {
                                obj = (globalCellIndex >= r || globalCellIndex < s) ? (globalCellIndex >= s || globalCellIndex < r) ? "select" : "onSend" : "onReceive";
                            } else if (cellState instanceof ReceiveCatching) {
                                obj = "receiveCatching";
                            } else if (cellState instanceof SendBroadcast) {
                                obj = "sendBroadcast";
                            } else if (cellState instanceof WaiterEB) {
                                obj = "EB(" + cellState + ')';
                            } else {
                                symbol = BufferedChannelKt.RESUMING_BY_RCV;
                                if (Intrinsics.areEqual(cellState, symbol)) {
                                    areEqual = true;
                                } else {
                                    symbol2 = BufferedChannelKt.RESUMING_BY_EB;
                                    areEqual = Intrinsics.areEqual(cellState, symbol2);
                                }
                                if (areEqual) {
                                    obj = "resuming_sender";
                                } else {
                                    if (cellState == null) {
                                        areEqual2 = true;
                                    } else {
                                        symbol3 = BufferedChannelKt.IN_BUFFER;
                                        areEqual2 = Intrinsics.areEqual(cellState, symbol3);
                                    }
                                    if (areEqual2) {
                                        areEqual3 = true;
                                    } else {
                                        symbol4 = BufferedChannelKt.DONE_RCV;
                                        areEqual3 = Intrinsics.areEqual(cellState, symbol4);
                                    }
                                    if (areEqual3) {
                                        areEqual4 = true;
                                    } else {
                                        symbol5 = BufferedChannelKt.POISONED;
                                        areEqual4 = Intrinsics.areEqual(cellState, symbol5);
                                    }
                                    if (areEqual4) {
                                        areEqual5 = true;
                                    } else {
                                        symbol6 = BufferedChannelKt.INTERRUPTED_RCV;
                                        areEqual5 = Intrinsics.areEqual(cellState, symbol6);
                                    }
                                    if (areEqual5) {
                                        areEqual6 = true;
                                    } else {
                                        symbol7 = BufferedChannelKt.INTERRUPTED_SEND;
                                        areEqual6 = Intrinsics.areEqual(cellState, symbol7);
                                    }
                                    if (areEqual6 ? true : Intrinsics.areEqual(cellState, BufferedChannelKt.getCHANNEL_CLOSED())) {
                                        i++;
                                        firstSegment = firstSegment2;
                                    } else {
                                        obj = cellState.toString();
                                    }
                                }
                            }
                            String cellStateString = obj;
                            if (element != null) {
                                sb.append('(' + cellStateString + ',' + element + "),");
                            } else {
                                sb.append(cellStateString + ',');
                            }
                            i++;
                            firstSegment = firstSegment2;
                        }
                    } else {
                        ChannelSegment firstSegment3 = firstSegment;
                        ChannelSegment channelSegment2 = (ChannelSegment) segment.getNext();
                        if (channelSegment2 != null) {
                            segment = channelSegment2;
                            firstSegment = firstSegment3;
                        }
                    }
                }
            }
            if (StringsKt.last(sb) == ',') {
                Intrinsics.checkNotNullExpressionValue(sb.deleteCharAt(sb.length() - 1), "this.deleteCharAt(index)");
            }
            sb.append("]");
            return sb.toString();
        }
        throw new NoSuchElementException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x027c A[LOOP:2: B:32:0x0177->B:66:0x027c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0277 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String toStringDebug$kotlinx_coroutines_core() {
        /*
            Method dump skipped, instructions count: 656
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.toStringDebug$kotlinx_coroutines_core():java.lang.String");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:111:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x020f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0119  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void checkSegmentStructureInvariants() {
        /*
            Method dump skipped, instructions count: 627
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.checkSegmentStructureInvariants():void");
    }
}
