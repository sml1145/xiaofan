package kotlinx.coroutines.internal;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CopyableThrowable;
/* compiled from: ExceptionsConstructor.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a2\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005j\u0002`\u0007\"\b\b\u0000\u0010\b*\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\nH\u0002\u001a.\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005j\u0002`\u00072\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005H\u0002\u001a!\u0010\r\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u00062\u0006\u0010\u000e\u001a\u0002H\bH\u0000¢\u0006\u0002\u0010\u000f\u001a\u001b\u0010\u0010\u001a\u00020\u0003*\u0006\u0012\u0002\b\u00030\n2\b\b\u0002\u0010\u0011\u001a\u00020\u0003H\u0082\u0010\u001a\u0018\u0010\u0012\u001a\u00020\u0003*\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u0013\u001a\u00020\u0003H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000*(\b\u0002\u0010\u0014\"\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00052\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005¨\u0006\u0015"}, d2 = {"ctorCache", "Lkotlinx/coroutines/internal/CtorCache;", "throwableFields", "", "createConstructor", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/Ctor;", "E", "clz", "Ljava/lang/Class;", "safeCtor", "block", "tryCopyException", "exception", "(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "fieldsCount", "accumulator", "fieldsCountOrDefault", "defaultValue", "Ctor", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class ExceptionsConstructorKt {
    private static final CtorCache ctorCache;
    private static final int throwableFields = fieldsCountOrDefault(Throwable.class, -1);

    public static final /* synthetic */ Function1 access$createConstructor(Class clz) {
        return createConstructor(clz);
    }

    static {
        CtorCache ctorCache2;
        try {
            ctorCache2 = FastServiceLoaderKt.getANDROID_DETECTED() ? WeakMapCtorCache.INSTANCE : ClassValueCtorCache.INSTANCE;
        } catch (Throwable th) {
            ctorCache2 = WeakMapCtorCache.INSTANCE;
        }
        ctorCache = ctorCache2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <E extends Throwable> E tryCopyException(E e) {
        Object m275constructorimpl;
        if (e instanceof CopyableThrowable) {
            try {
                Result.Companion companion = Result.Companion;
                m275constructorimpl = Result.m275constructorimpl(((CopyableThrowable) e).createCopy());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                m275constructorimpl = Result.m275constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m281isFailureimpl(m275constructorimpl)) {
                m275constructorimpl = null;
            }
            return (E) m275constructorimpl;
        }
        return (E) ctorCache.get(e.getClass()).invoke(e);
    }

    public static final <E extends Throwable> Function1<Throwable, Throwable> createConstructor(Class<E> cls) {
        Function1<Throwable, Throwable> function1;
        char c;
        Pair pair;
        Function1 nullResult = new Function1() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createConstructor$nullResult$1
            @Override // kotlin.jvm.functions.Function1
            public final Void invoke(Throwable it) {
                return null;
            }
        };
        char c2 = 0;
        if (throwableFields != fieldsCountOrDefault(cls, 0)) {
            return nullResult;
        }
        Constructor<?>[] constructors = cls.getConstructors();
        Collection destination$iv$iv = new ArrayList(constructors.length);
        int length = constructors.length;
        int i = 0;
        while (true) {
            Object maxElem$iv = null;
            if (i >= length) {
                Iterable $this$maxByOrNull$iv = (List) destination$iv$iv;
                Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
                if (iterator$iv.hasNext()) {
                    maxElem$iv = iterator$iv.next();
                    if (iterator$iv.hasNext()) {
                        Pair p0 = (Pair) maxElem$iv;
                        int maxValue$iv = ((Number) p0.getSecond()).intValue();
                        do {
                            Object e$iv = iterator$iv.next();
                            Pair p02 = (Pair) e$iv;
                            int v$iv = ((Number) p02.getSecond()).intValue();
                            if (maxValue$iv < v$iv) {
                                maxValue$iv = v$iv;
                                maxElem$iv = e$iv;
                            }
                        } while (iterator$iv.hasNext());
                    }
                }
                Pair pair2 = (Pair) maxElem$iv;
                return (pair2 == null || (function1 = (Function1) pair2.getFirst()) == null) ? nullResult : function1;
            }
            final Constructor<?> constructor = constructors[i];
            Class[] p = constructor.getParameterTypes();
            switch (p.length) {
                case 0:
                    c = 0;
                    pair = TuplesKt.to(safeCtor(new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createConstructor$1$4
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Throwable invoke(Throwable e) {
                            Object newInstance = constructor.newInstance(new Object[0]);
                            Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Throwable");
                            Throwable it = (Throwable) newInstance;
                            it.initCause(e);
                            return it;
                        }
                    }), 0);
                    break;
                case 1:
                    Class cls2 = p[0];
                    if (!Intrinsics.areEqual(cls2, String.class)) {
                        if (!Intrinsics.areEqual(cls2, Throwable.class)) {
                            pair = TuplesKt.to(null, -1);
                            c = 0;
                            break;
                        } else {
                            pair = TuplesKt.to(safeCtor(new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createConstructor$1$3
                                /* JADX INFO: Access modifiers changed from: package-private */
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final Throwable invoke(Throwable e) {
                                    Object newInstance = constructor.newInstance(e);
                                    Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Throwable");
                                    return (Throwable) newInstance;
                                }
                            }), 1);
                            c = 0;
                            break;
                        }
                    } else {
                        pair = TuplesKt.to(safeCtor(new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createConstructor$1$2
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Throwable invoke(Throwable e) {
                                Object newInstance = constructor.newInstance(e.getMessage());
                                Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Throwable");
                                Throwable it = (Throwable) newInstance;
                                it.initCause(e);
                                return it;
                            }
                        }), 2);
                        c = 0;
                        break;
                    }
                case 2:
                    if (!Intrinsics.areEqual(p[c2], String.class) || !Intrinsics.areEqual(p[1], Throwable.class)) {
                        pair = TuplesKt.to(null, -1);
                        c = 0;
                        break;
                    } else {
                        pair = TuplesKt.to(safeCtor(new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createConstructor$1$1
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Throwable invoke(Throwable e) {
                                Object newInstance = constructor.newInstance(e.getMessage(), e);
                                Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Throwable");
                                return (Throwable) newInstance;
                            }
                        }), 3);
                        c = 0;
                        break;
                    }
                default:
                    c = c2;
                    pair = TuplesKt.to(null, -1);
                    break;
            }
            destination$iv$iv.add(pair);
            i++;
            c2 = c;
        }
    }

    private static final Function1<Throwable, Throwable> safeCtor(final Function1<? super Throwable, ? extends Throwable> function1) {
        return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$safeCtor$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Throwable invoke(Throwable e) {
                Object m275constructorimpl;
                Function1<Throwable, Throwable> function12 = function1;
                try {
                    Result.Companion companion = Result.Companion;
                    Throwable result = function12.invoke(e);
                    m275constructorimpl = Result.m275constructorimpl((Intrinsics.areEqual(e.getMessage(), result.getMessage()) || Intrinsics.areEqual(result.getMessage(), e.toString())) ? result : null);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    m275constructorimpl = Result.m275constructorimpl(ResultKt.createFailure(th));
                }
                return Result.m281isFailureimpl(m275constructorimpl) ? null : m275constructorimpl;
            }
        };
    }

    private static final int fieldsCountOrDefault(Class<?> cls, int defaultValue) {
        Integer m275constructorimpl;
        JvmClassMappingKt.getKotlinClass(cls);
        try {
            Result.Companion companion = Result.Companion;
            m275constructorimpl = Result.m275constructorimpl(Integer.valueOf(fieldsCount$default(cls, 0, 1, null)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            m275constructorimpl = Result.m275constructorimpl(ResultKt.createFailure(th));
        }
        Integer valueOf = Integer.valueOf(defaultValue);
        if (Result.m281isFailureimpl(m275constructorimpl)) {
            m275constructorimpl = valueOf;
        }
        return ((Number) m275constructorimpl).intValue();
    }

    static /* synthetic */ int fieldsCount$default(Class cls, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return fieldsCount(cls, i);
    }

    private static final int fieldsCount(Class<?> cls, int accumulator) {
        while (true) {
            int count$iv = 0;
            for (Field field : cls.getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    count$iv++;
                }
            }
            int fieldsCount = count$iv;
            int totalFields = accumulator + fieldsCount;
            Class<? super Object> superclass = cls.getSuperclass();
            if (superclass == null) {
                return totalFields;
            }
            cls = superclass;
            accumulator = totalFields;
        }
    }
}
