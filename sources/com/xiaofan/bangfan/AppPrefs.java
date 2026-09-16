package com.xiaofan.bangfan;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.DebugKt;
/* compiled from: AppPrefs.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\bO\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b)\n\u0002\u0010 \n\u0002\ba\n\u0002\u0018\u0002\n\u0002\b\u001c\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000e\u0010b\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020aJ\u0016\u0010c\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020\u000fJ\u0016\u0010f\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020\u000fJ\u001e\u0010g\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010h\u001a\u00020_2\u0006\u0010i\u001a\u00020_J\u0016\u0010j\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020\u000fJ\u000e\u0010k\u001a\u00020\u00062\u0006\u0010`\u001a\u00020aJ\u000e\u0010l\u001a\u00020\u00062\u0006\u0010`\u001a\u00020aJ\u000e\u0010m\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000e\u0010n\u001a\u00020\u00062\u0006\u0010`\u001a\u00020aJ\u000e\u0010o\u001a\u00020\u00062\u0006\u0010`\u001a\u00020aJ\u000e\u0010p\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000e\u0010q\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000e\u0010r\u001a\u00020\u00042\u0006\u0010`\u001a\u00020aJ\u000e\u0010s\u001a\u00020\u00062\u0006\u0010`\u001a\u00020aJ\u000e\u0010s\u001a\u00020\u00062\u0006\u0010t\u001a\u00020\u0004J\u000e\u0010u\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020aJ\u000e\u0010v\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020aJ\u000e\u0010w\u001a\u00020\u00042\u0006\u0010`\u001a\u00020aJ\u000e\u0010x\u001a\u00020\u00062\u0006\u0010`\u001a\u00020aJ\u000e\u0010y\u001a\u00020\u00042\u0006\u0010`\u001a\u00020aJ\u000e\u0010z\u001a\u00020\u00042\u0006\u0010`\u001a\u00020aJ\u000e\u0010{\u001a\u00020\u00042\u0006\u0010`\u001a\u00020aJ\u000e\u0010|\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u0010\u0010}\u001a\u0004\u0018\u00010\u00062\u0006\u0010`\u001a\u00020aJ\u000e\u0010~\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000e\u0010\u007f\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0080\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0081\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0082\u0001\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0083\u0001\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0084\u0001\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0085\u0001\u001a\u00020\u00062\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0086\u0001\u001a\u00020\u00062\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0087\u0001\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0088\u0001\u001a\u00020\t2\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0089\u0001\u001a\u00020\u00062\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u008a\u0001\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u008b\u0001\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u008c\u0001\u001a\u00020\u00062\u0006\u0010`\u001a\u00020aJ\u0016\u0010\u008d\u0001\u001a\t\u0012\u0004\u0012\u00020\u00060\u008e\u00012\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u008f\u0001\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0090\u0001\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0091\u0001\u001a\u00020\u00042\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0092\u0001\u001a\u00020\u00042\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0093\u0001\u001a\u00020\u00062\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0094\u0001\u001a\u00020\u00042\u0006\u0010`\u001a\u00020aJ\u001a\u0010\u0095\u0001\u001a\u00020_2\u0006\u0010`\u001a\u00020a2\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u0006J\u000f\u0010\u0097\u0001\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0098\u0001\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u001a\u0010\u0099\u0001\u001a\u00020_2\u0006\u0010`\u001a\u00020a2\t\u0010\u009a\u0001\u001a\u0004\u0018\u00010\u0006J\u000f\u0010\u009b\u0001\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u009c\u0001\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u0017\u0010\u009d\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020\u000fJ\u001a\u0010\u009e\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u0006J\u000f\u0010\u009f\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020aJ\u000f\u0010 \u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020aJ\u000f\u0010¡\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020aJ\u000f\u0010¢\u0001\u001a\u00020\u00062\u0006\u0010`\u001a\u00020aJ\u000f\u0010£\u0001\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000f\u0010¤\u0001\u001a\u00020\u00042\u0006\u0010`\u001a\u00020aJ\u000f\u0010¥\u0001\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000f\u0010¦\u0001\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020aJ\u000f\u0010§\u0001\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000f\u0010¨\u0001\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000f\u0010©\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020aJ\u000f\u0010ª\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020aJ\u000f\u0010«\u0001\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000f\u0010¬\u0001\u001a\u00020\u00062\u0006\u0010`\u001a\u00020aJ,\u0010\u00ad\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0007\u0010®\u0001\u001a\u00020\u00042\u0007\u0010¯\u0001\u001a\u00020\u00042\t\u0010°\u0001\u001a\u0004\u0018\u00010\u0006J\u0017\u0010±\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020\u000fJ\u000f\u0010²\u0001\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u0017\u0010³\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0017\u0010´\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020\u000fJ\u0019\u0010µ\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\b\u0010e\u001a\u0004\u0018\u00010\u0006J\u0017\u0010¶\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0019\u0010·\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\b\u0010e\u001a\u0004\u0018\u00010\u0006J\u0017\u0010¸\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0017\u0010¹\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0017\u0010º\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020\u0004J\u0017\u0010»\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020\u0004J\u0017\u0010¼\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020\u0004J\u0017\u0010½\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u001a\u0010¾\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\t\u0010¿\u0001\u001a\u0004\u0018\u00010\u0006J\u0017\u0010À\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0017\u0010Á\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0017\u0010Â\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0019\u0010Ã\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\b\u0010e\u001a\u0004\u0018\u00010\u0006J\u0017\u0010Ä\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020\u000fJ\u0017\u0010Å\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020\tJ\u0019\u0010Æ\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\b\u0010e\u001a\u0004\u0018\u00010\u0006J\u0017\u0010Ç\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0019\u0010È\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\b\u0010e\u001a\u0004\u0018\u00010\u0006J!\u0010É\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0010\u0010Ê\u0001\u001a\u000b\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u008e\u0001J\u0017\u0010Ë\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0017\u0010Ì\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020\u0006J\u0019\u0010Í\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\b\u0010e\u001a\u0004\u0018\u00010\u0006J\u0017\u0010Î\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0017\u0010Ï\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020\u0004J\u0017\u0010Ð\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0017\u0010Ñ\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0017\u0010Ò\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0017\u0010Ó\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0019\u0010Ô\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\b\u0010e\u001a\u0004\u0018\u00010\u0006J\u0017\u0010Õ\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0017\u0010Ö\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0017\u0010×\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0017\u0010Ø\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020\tJ\u0017\u0010Ù\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020\tJ\u0017\u0010Ú\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0017\u0010Û\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0017\u0010Ü\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0017\u0010Ý\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0019\u0010Þ\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\b\u0010e\u001a\u0004\u0018\u00010\u0006J\u0019\u0010ß\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\b\u0010e\u001a\u0004\u0018\u00010\u0006J\u0019\u0010à\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\b\u0010e\u001a\u0004\u0018\u00010\u0006J\u0017\u0010á\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020\tJ\u0017\u0010â\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020\u0006J\u0017\u0010ã\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0017\u0010ä\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020\u0006J!\u0010å\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0007\u0010æ\u0001\u001a\u00020\u00062\u0007\u0010ç\u0001\u001a\u00020\u0006J\u0017\u0010è\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0017\u0010é\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0017\u0010ê\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u0019\u0010ë\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\b\u0010e\u001a\u0004\u0018\u00010\u0006J\u0017\u0010ì\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020\u0004J\u0017\u0010í\u0001\u001a\u00020d2\u0006\u0010`\u001a\u00020a2\u0006\u0010e\u001a\u00020_J\u000f\u0010î\u0001\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u0010\u0010ï\u0001\u001a\u00030ð\u00012\u0006\u0010`\u001a\u00020aJ\u000f\u0010ñ\u0001\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000f\u0010ò\u0001\u001a\u00020\t2\u0006\u0010`\u001a\u00020aJ\u000f\u0010ó\u0001\u001a\u00020\t2\u0006\u0010`\u001a\u00020aJ\u000f\u0010ô\u0001\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000f\u0010õ\u0001\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000f\u0010ö\u0001\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000f\u0010÷\u0001\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000f\u0010ø\u0001\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000f\u0010ù\u0001\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020aJ\u000f\u0010ú\u0001\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020aJ\u000f\u0010û\u0001\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020aJ\u000f\u0010ü\u0001\u001a\u00020\u00062\u0006\u0010`\u001a\u00020aJ\u000f\u0010ý\u0001\u001a\u00020\u00062\u0006\u0010`\u001a\u00020aJ\u000f\u0010þ\u0001\u001a\u00020\u00062\u0006\u0010`\u001a\u00020aJ\u000f\u0010ÿ\u0001\u001a\u00020\t2\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0080\u0002\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0081\u0002\u001a\u00020\u00062\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0082\u0002\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0083\u0002\u001a\u00020\u00062\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0084\u0002\u001a\u00020\u00062\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0085\u0002\u001a\u00020\u00062\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0086\u0002\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0087\u0002\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0088\u0002\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u0089\u0002\u001a\u00020\u00062\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u008a\u0002\u001a\u00020\u00042\u0006\u0010`\u001a\u00020aJ\u000f\u0010\u008b\u0002\u001a\u00020_2\u0006\u0010`\u001a\u00020aR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010K\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010L\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010U\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010W\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010Y\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010Z\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010[\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\\\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010]\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u008c\u0002"}, d2 = {"Lcom/xiaofan/bangfan/AppPrefs;", "", "()V", "AVATAR_COUNT", "", "DEFAULT_GOMOKU_SERVER", "", "DEFAULT_NICKNAME", "DEFAULT_SPEAK_PITCH", "", "DEFAULT_SPEAK_RATE", "DEFAULT_TURN_FACTOR", "DEFAULT_UPDATE_OWNER", "DEFAULT_UPDATE_REPO", "FALLBACK_READ_MS", "", "FILE", "KEY_AD_DETECT", "KEY_AD_TIMEOUT_MS", "KEY_AI_API_KEY", "KEY_AI_ENABLED", "KEY_AI_ENDPOINT", "KEY_AUTO_START_BALL", "KEY_AUTO_TURN", "KEY_AVATAR_INDEX", "KEY_AVG_READ_MS", "KEY_BACKUP_AT", "KEY_BALL_ALPHA", "KEY_BALL_EDGE", "KEY_BALL_SIZE_DP", "KEY_BALL_X", "KEY_BALL_Y", "KEY_BT_LAST_PEER", "KEY_BT_RECV_TIP", "KEY_BT_VOLUME_REMOTE", "KEY_COMPANION_MINUTES", "KEY_COMPANION_SECONDS", "KEY_CUSTOM_AVATAR", "KEY_CUSTOM_INTERVAL_MS", "KEY_CUSTOM_TURN_FACTOR", "KEY_CUSTOM_VOICE_NAME", "KEY_CUSTOM_VOICE_ON", "KEY_CUSTOM_VOICE_SAMPLE", "KEY_EXCLUDED_PKGS", "KEY_EYE_CARE", "KEY_FIRST_LAUNCH", "KEY_GOMOKU_SERVER", "KEY_GUIDE_SHOWN", "KEY_LAST_GREET_DATE", "KEY_NICKNAME", "KEY_NIGHT_MODE", "KEY_NIGHT_MODE_STATE", "KEY_PLANNER", "KEY_PRESENCE", "KEY_PRESENCE_LOST_AT", "KEY_PRESENCE_REQUIRED", "KEY_PROFILE_DONE", "KEY_RESTORE_AUTO_TURN", "KEY_ROLE", "KEY_SCREEN_OFF_OK", "KEY_SILENT", "KEY_SPEAK_ON", "KEY_SPEAK_PITCH", "KEY_SPEAK_RATE", "KEY_SPEAK_TIPS", "KEY_SPEAK_TURN", "KEY_SPEEDUP_BOOK_END", "KEY_SPEED_DONE", "KEY_TIMED_TURN", "KEY_TOTAL_AD_SKIPPED", "KEY_TOTAL_READ_MS", "KEY_TOTAL_TURNS", "KEY_TTS_API_KEY", "KEY_TTS_ENDPOINT", "KEY_TTS_VOICE_ID", "KEY_TURN_FACTOR", "KEY_TURN_MODE", "KEY_UPDATE_AUTO", "KEY_UPDATE_OWNER", "KEY_UPDATE_REPO", "KEY_UPDATE_SKIPPED", "KEY_USE_CUSTOM_INTERVAL", "KEY_VIBRATE_ON", "KEY_VOICE_CMD", "KEY_VOICE_LOCALE", "KEY_VOICE_PACK", "KEY_VOLUME_KEY_CTRL", "NIGHT_FOLLOW", "NIGHT_OFF", "NIGHT_ON", "PLANNER_CODE", "ROLE_CONTROLLER", "ROLE_NONE", "ROLE_READER", "adDetect", "", "context", "Landroid/content/Context;", "adTimeoutMs", "addCompanionMinutes", "", "v", "addCompanionSeconds", "addGomokuResult", "win", "draw", "addReadMs", "aiApiKey", "aiApiKeyRaw", "aiEnabled", "aiEndpoint", "aiEndpointRaw", "autoStartBall", "autoTurn", "avatarIndex", "avatarResName", "index", "avgReadMs", "backupAt", "ballAlpha", "ballEdge", "ballSizeDp", "ballX", "ballY", "btBatteryHintShown", "btLastPeer", "btRecvTip", "btVolumeRemote", "bumpAdSkipped", "bumpTotalTurns", "clickSound", "companionMinutes", "companionSeconds", "companionText", "customAvatarUri", "customIntervalMs", "customTurnFactor", "customVoiceName", "customVoiceOn", "customVoiceReady", "customVoiceSample", "excludedPkgs", "", "eyeCare", "firstLaunch", "gomokuDraws", "gomokuLosses", "gomokuServer", "gomokuWins", "greetedToday", XiaoFanBrain.INTENT_DATE, "guideShown", "isController", "isExcluded", "pkg", "isPlanner", "isReader", "markBackupAt", "markGreeted", "markGuideShown", "markLaunched", "markProfileDone", AppPrefs.KEY_NICKNAME, "nightMode", "nightModeState", AppPrefs.KEY_PRESENCE, "presenceLostAt", "presenceRequired", "profileDone", "resetSpeedResult", "resetStats", "restoreAutoTurn", AppPrefs.KEY_ROLE, "saveBallPosition", "x", "y", "edge", "saveSpeedResult", "screenOffOk", "setAdDetect", "setAdTimeoutMs", "setAiApiKey", "setAiEnabled", "setAiEndpoint", "setAutoStartBall", "setAutoTurn", "setAvatarIndex", "setBallAlpha", "setBallSizeDp", "setBtBatteryHintShown", "setBtLastPeer", "mac", "setBtRecvTip", "setBtVolumeRemote", "setClickSound", "setCustomAvatarUri", "setCustomIntervalMs", "setCustomTurnFactor", "setCustomVoiceName", "setCustomVoiceOn", "setCustomVoiceSample", "setExcludedPkgs", "list", "setEyeCare", "setGomokuServer", "setNickname", "setNightMode", "setNightModeState", "setPlanner", "setPresence", "setPresenceRequired", "setRestoreAutoTurn", "setRole", "setScreenOffOk", "setSilent", "setSpeakOn", "setSpeakPitch", "setSpeakRate", "setSpeakTips", "setSpeakTurn", "setSpeedUpOnBookEnd", "setTimedTurn", "setTtsApiKey", "setTtsEndpoint", "setTtsVoiceId", "setTurnFactor", "setTurnMode", "setUpdateAutoCheck", "setUpdateSkipped", "setUpdateSource", "owner", "repo", "setUseCustomInterval", "setVibrateOn", "setVoiceCmdOn", "setVoiceLocale", "setVoicePack", "setVolumeKeyCtrl", "silent", "sp", "Landroid/content/SharedPreferences;", "speakOn", "speakPitch", "speakRate", "speakTips", "speakTurn", "speedDone", "speedUpOnBookEnd", "timedTurn", "totalAdSkipped", "totalReadMs", "totalTurns", "ttsApiKey", "ttsEndpoint", "ttsVoiceId", "turnFactor", "turnIntervalMs", "turnMode", "updateAutoCheck", "updateOwner", "updateRepo", "updateSkipped", "useCustomInterval", "vibrateOn", "voiceCmdOn", "voiceLocale", "voicePack", "volumeKeyCtrl", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class AppPrefs {
    public static final int AVATAR_COUNT = 8;
    public static final String DEFAULT_GOMOKU_SERVER = "wss://0.peerjs.com/peerjs";
    public static final String DEFAULT_NICKNAME = "主人";
    public static final float DEFAULT_SPEAK_PITCH = 1.5f;
    public static final float DEFAULT_SPEAK_RATE = 1.15f;
    public static final float DEFAULT_TURN_FACTOR = 1.1f;
    public static final String DEFAULT_UPDATE_OWNER = "sml1145";
    public static final String DEFAULT_UPDATE_REPO = "xiaofan";
    public static final long FALLBACK_READ_MS = 6000;
    private static final String FILE = "xiaofan_prefs";
    public static final AppPrefs INSTANCE = new AppPrefs();
    public static final String KEY_AD_DETECT = "ad_detect";
    public static final String KEY_AD_TIMEOUT_MS = "ad_timeout_ms";
    public static final String KEY_AI_API_KEY = "ai_api_key";
    public static final String KEY_AI_ENABLED = "ai_enabled";
    public static final String KEY_AI_ENDPOINT = "ai_endpoint";
    public static final String KEY_AUTO_START_BALL = "auto_start_ball";
    public static final String KEY_AUTO_TURN = "auto_turn";
    public static final String KEY_AVATAR_INDEX = "avatar_index";
    public static final String KEY_AVG_READ_MS = "avg_read_ms";
    public static final String KEY_BACKUP_AT = "backup_at";
    public static final String KEY_BALL_ALPHA = "ball_alpha";
    public static final String KEY_BALL_EDGE = "ball_edge";
    public static final String KEY_BALL_SIZE_DP = "ball_size_dp";
    public static final String KEY_BALL_X = "ball_x";
    public static final String KEY_BALL_Y = "ball_y";
    public static final String KEY_BT_LAST_PEER = "bt_last_peer_mac";
    public static final String KEY_BT_RECV_TIP = "bt_recv_tip";
    public static final String KEY_BT_VOLUME_REMOTE = "bt_volume_remote";
    public static final String KEY_COMPANION_MINUTES = "companion_minutes";
    public static final String KEY_COMPANION_SECONDS = "companion_seconds";
    public static final String KEY_CUSTOM_AVATAR = "custom_avatar_uri";
    public static final String KEY_CUSTOM_INTERVAL_MS = "custom_interval_ms";
    public static final String KEY_CUSTOM_TURN_FACTOR = "custom_turn_factor";
    public static final String KEY_CUSTOM_VOICE_NAME = "custom_voice_name";
    public static final String KEY_CUSTOM_VOICE_ON = "custom_voice_on";
    public static final String KEY_CUSTOM_VOICE_SAMPLE = "custom_voice_sample";
    public static final String KEY_EXCLUDED_PKGS = "excluded_pkgs";
    public static final String KEY_EYE_CARE = "eye_care";
    public static final String KEY_FIRST_LAUNCH = "first_launch";
    public static final String KEY_GOMOKU_SERVER = "gomoku_server";
    public static final String KEY_GUIDE_SHOWN = "guide_shown";
    public static final String KEY_LAST_GREET_DATE = "last_greet_date";
    public static final String KEY_NICKNAME = "nickname";
    public static final String KEY_NIGHT_MODE = "night_mode";
    public static final String KEY_NIGHT_MODE_STATE = "night_mode_state";
    public static final String KEY_PLANNER = "planner_identity";
    public static final String KEY_PRESENCE = "presence";
    public static final String KEY_PRESENCE_LOST_AT = "presence_lost_at";
    public static final String KEY_PRESENCE_REQUIRED = "presence_required";
    public static final String KEY_PROFILE_DONE = "profile_done";
    public static final String KEY_RESTORE_AUTO_TURN = "restore_auto_turn";
    public static final String KEY_ROLE = "role";
    public static final String KEY_SCREEN_OFF_OK = "screen_off_ok";
    public static final String KEY_SILENT = "silent";
    public static final String KEY_SPEAK_ON = "speak_on";
    public static final String KEY_SPEAK_PITCH = "speak_pitch";
    public static final String KEY_SPEAK_RATE = "speak_rate";
    public static final String KEY_SPEAK_TIPS = "speak_tips";
    public static final String KEY_SPEAK_TURN = "speak_turn";
    public static final String KEY_SPEEDUP_BOOK_END = "speedup_book_end";
    public static final String KEY_SPEED_DONE = "speed_done";
    public static final String KEY_TIMED_TURN = "timed_turn";
    public static final String KEY_TOTAL_AD_SKIPPED = "total_ad_skipped";
    public static final String KEY_TOTAL_READ_MS = "total_read_ms";
    public static final String KEY_TOTAL_TURNS = "total_turns";
    public static final String KEY_TTS_API_KEY = "tts_api_key";
    public static final String KEY_TTS_ENDPOINT = "tts_endpoint";
    public static final String KEY_TTS_VOICE_ID = "tts_voice_id";
    public static final String KEY_TURN_FACTOR = "turn_factor";
    public static final String KEY_TURN_MODE = "turn_mode";
    public static final String KEY_UPDATE_AUTO = "update_auto_check";
    public static final String KEY_UPDATE_OWNER = "update_github_owner";
    public static final String KEY_UPDATE_REPO = "update_github_repo";
    public static final String KEY_UPDATE_SKIPPED = "update_skipped_version";
    public static final String KEY_USE_CUSTOM_INTERVAL = "use_custom_interval";
    public static final String KEY_VIBRATE_ON = "vibrate_on";
    public static final String KEY_VOICE_CMD = "voice_cmd_on";
    public static final String KEY_VOICE_LOCALE = "voice_locale";
    public static final String KEY_VOICE_PACK = "voice_pack";
    public static final String KEY_VOLUME_KEY_CTRL = "volume_key_ctrl";
    public static final int NIGHT_FOLLOW = 2;
    public static final int NIGHT_OFF = 0;
    public static final int NIGHT_ON = 1;
    public static final String PLANNER_CODE = "walmt";
    public static final String ROLE_CONTROLLER = "controller";
    public static final String ROLE_NONE = "";
    public static final String ROLE_READER = "reader";

    private AppPrefs() {
    }

    public final SharedPreferences sp(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(FILE, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
        return sharedPreferences;
    }

    public final String gomokuServer(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String it = sp(context).getString(KEY_GOMOKU_SERVER, DEFAULT_GOMOKU_SERVER);
        if (it == null || !(!StringsKt.isBlank(it))) {
            it = null;
        }
        return it == null ? DEFAULT_GOMOKU_SERVER : it;
    }

    public final void setGomokuServer(Context context, String v) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(v, "v");
        sp(context).edit().putString(KEY_GOMOKU_SERVER, StringsKt.trim((CharSequence) v).toString()).apply();
    }

    public final String updateOwner(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = sp(context).getString(KEY_UPDATE_OWNER, DEFAULT_UPDATE_OWNER);
        String obj = string != null ? StringsKt.trim((CharSequence) string).toString() : null;
        return obj == null ? "" : obj;
    }

    public final String updateRepo(Context context) {
        String obj;
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences sp = sp(context);
        String str = DEFAULT_UPDATE_REPO;
        String string = sp.getString(KEY_UPDATE_REPO, DEFAULT_UPDATE_REPO);
        if (string == null || (obj = StringsKt.trim((CharSequence) string).toString()) == null) {
            return DEFAULT_UPDATE_REPO;
        }
        String str2 = obj;
        if (!StringsKt.isBlank(str2)) {
            str = str2;
        }
        return str;
    }

    public final void setUpdateSource(Context context, String owner, String repo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(repo, "repo");
        sp(context).edit().putString(KEY_UPDATE_OWNER, StringsKt.trim((CharSequence) owner).toString()).putString(KEY_UPDATE_REPO, StringsKt.trim((CharSequence) repo).toString()).apply();
    }

    public final boolean updateAutoCheck(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_UPDATE_AUTO, true);
    }

    public final void setUpdateAutoCheck(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_UPDATE_AUTO, v).apply();
    }

    public final String updateSkipped(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = sp(context).getString(KEY_UPDATE_SKIPPED, "");
        return string == null ? "" : string;
    }

    public final void setUpdateSkipped(Context context, String v) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(v, "v");
        sp(context).edit().putString(KEY_UPDATE_SKIPPED, StringsKt.trim((CharSequence) v).toString()).apply();
    }

    public final boolean autoTurn(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_AUTO_TURN, false);
    }

    public final void setAutoTurn(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_AUTO_TURN, v).apply();
    }

    public final String turnMode(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = sp(context).getString(KEY_TURN_MODE, DebugKt.DEBUG_PROPERTY_VALUE_AUTO);
        return string == null ? DebugKt.DEBUG_PROPERTY_VALUE_AUTO : string;
    }

    public final void setTurnMode(Context context, String v) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(v, "v");
        sp(context).edit().putString(KEY_TURN_MODE, v).apply();
    }

    public final boolean adDetect(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_AD_DETECT, true);
    }

    public final void setAdDetect(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_AD_DETECT, v).apply();
    }

    public final long adTimeoutMs(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getLong(KEY_AD_TIMEOUT_MS, 2000L);
    }

    public final void setAdTimeoutMs(Context context, long v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putLong(KEY_AD_TIMEOUT_MS, v).apply();
    }

    public final boolean timedTurn(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_TIMED_TURN, true);
    }

    public final void setTimedTurn(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_TIMED_TURN, v).apply();
    }

    public final boolean speedDone(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_SPEED_DONE, false);
    }

    public final long avgReadMs(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getLong(KEY_AVG_READ_MS, FALLBACK_READ_MS);
    }

    public final void saveSpeedResult(Context context, long v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_SPEED_DONE, true).putLong(KEY_AVG_READ_MS, v).apply();
    }

    public final void resetSpeedResult(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_SPEED_DONE, false).remove(KEY_AVG_READ_MS).apply();
    }

    public final float turnFactor(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getFloat(KEY_TURN_FACTOR, 1.1f);
    }

    public final void setTurnFactor(Context context, float v) {
        Intrinsics.checkNotNullParameter(context, "context");
        float f = RangesKt.coerceAtLeast(v, 1.0f);
        sp(context).edit().putFloat(KEY_TURN_FACTOR, f).apply();
    }

    public final boolean useCustomInterval(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_USE_CUSTOM_INTERVAL, false);
    }

    public final void setUseCustomInterval(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_USE_CUSTOM_INTERVAL, v).apply();
    }

    public final long customIntervalMs(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getLong(KEY_CUSTOM_INTERVAL_MS, 5000L);
    }

    public final void setCustomIntervalMs(Context context, long v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putLong(KEY_CUSTOM_INTERVAL_MS, RangesKt.coerceAtLeast(v, 800L)).apply();
    }

    public final float customTurnFactor(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getFloat(KEY_CUSTOM_TURN_FACTOR, 1.0f);
    }

    public final void setCustomTurnFactor(Context context, float v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putFloat(KEY_CUSTOM_TURN_FACTOR, RangesKt.coerceIn(v, 0.5f, 3.0f)).apply();
    }

    public final long turnIntervalMs(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (useCustomInterval(context)) {
            return Math.max(800L, ((float) customIntervalMs(context)) * customTurnFactor(context));
        }
        return Math.max(800L, ((float) avgReadMs(context)) * turnFactor(context));
    }

    public final boolean presenceRequired(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_PRESENCE_REQUIRED, true);
    }

    public final void setPresenceRequired(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_PRESENCE_REQUIRED, v).apply();
    }

    public final boolean voiceCmdOn(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_VOICE_CMD, false);
    }

    public final void setVoiceCmdOn(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_VOICE_CMD, v).apply();
    }

    public final boolean speedUpOnBookEnd(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_SPEEDUP_BOOK_END, false);
    }

    public final void setSpeedUpOnBookEnd(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_SPEEDUP_BOOK_END, v).apply();
    }

    public final boolean presence(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_PRESENCE, true);
    }

    public final void setPresence(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences.Editor edit = sp(context).edit().putBoolean(KEY_PRESENCE, v);
        if (!v) {
            edit.putLong(KEY_PRESENCE_LOST_AT, System.currentTimeMillis());
        }
        edit.apply();
    }

    public final long presenceLostAt(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getLong(KEY_PRESENCE_LOST_AT, 0L);
    }

    public final String role(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = sp(context).getString(KEY_ROLE, "");
        return string == null ? "" : string;
    }

    public final void setRole(Context context, String v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putString(KEY_ROLE, v == null ? "" : v).apply();
    }

    public final boolean isReader(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return Intrinsics.areEqual(ROLE_READER, role(context));
    }

    public final boolean isController(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return Intrinsics.areEqual(ROLE_CONTROLLER, role(context));
    }

    public final boolean volumeKeyCtrl(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_VOLUME_KEY_CTRL, true);
    }

    public final void setVolumeKeyCtrl(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_VOLUME_KEY_CTRL, v).apply();
    }

    public final boolean btVolumeRemote(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_BT_VOLUME_REMOTE, true);
    }

    public final void setBtVolumeRemote(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_BT_VOLUME_REMOTE, v).apply();
    }

    public final boolean btRecvTip(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_BT_RECV_TIP, false);
    }

    public final void setBtRecvTip(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_BT_RECV_TIP, v).apply();
    }

    public final String btLastPeer(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getString(KEY_BT_LAST_PEER, null);
    }

    public final void setBtLastPeer(Context context, String mac) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putString(KEY_BT_LAST_PEER, mac).apply();
    }

    public final boolean btBatteryHintShown(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean("bt_battery_hint_shown", false);
    }

    public final void setBtBatteryHintShown(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean("bt_battery_hint_shown", v).apply();
    }

    public final boolean clickSound(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean("click_sound", true);
    }

    public final void setClickSound(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean("click_sound", v).apply();
    }

    public final boolean screenOffOk(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_SCREEN_OFF_OK, true);
    }

    public final void setScreenOffOk(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_SCREEN_OFF_OK, v).apply();
    }

    public final boolean isPlanner(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_PLANNER, false);
    }

    public final void setPlanner(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_PLANNER, v).apply();
    }

    public final String nickname(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = sp(context).getString(KEY_NICKNAME, DEFAULT_NICKNAME);
        return string == null ? DEFAULT_NICKNAME : string;
    }

    public final void setNickname(Context context, String v) {
        Intrinsics.checkNotNullParameter(context, "context");
        String trim = StringsKt.take(StringsKt.trim((CharSequence) (v == null ? "" : v)).toString(), 12);
        sp(context).edit().putString(KEY_NICKNAME, trim).apply();
    }

    public final int avatarIndex(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return RangesKt.coerceIn(sp(context).getInt(KEY_AVATAR_INDEX, 1), 1, 8);
    }

    public final void setAvatarIndex(Context context, int v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putInt(KEY_AVATAR_INDEX, RangesKt.coerceIn(v, 1, 8)).apply();
    }

    public final boolean profileDone(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_PROFILE_DONE, false);
    }

    public final String customAvatarUri(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = sp(context).getString(KEY_CUSTOM_AVATAR, "");
        return string == null ? "" : string;
    }

    public final void setCustomAvatarUri(Context context, String v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putString(KEY_CUSTOM_AVATAR, v == null ? "" : v).apply();
    }

    public final void markProfileDone(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_PROFILE_DONE, true).apply();
    }

    public final String avatarResName(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return avatarResName(avatarIndex(context));
    }

    public final String avatarResName(int index) {
        int i = RangesKt.coerceIn(index, 1, 8);
        return "avatar_" + (i < 10 ? "0" + i : String.valueOf(i));
    }

    public final boolean speakOn(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_SPEAK_ON, true);
    }

    public final void setSpeakOn(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_SPEAK_ON, v).apply();
    }

    public final float speakPitch(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getFloat(KEY_SPEAK_PITCH, 1.5f);
    }

    public final void setSpeakPitch(Context context, float v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putFloat(KEY_SPEAK_PITCH, RangesKt.coerceIn(v, 0.5f, 2.0f)).apply();
    }

    public final float speakRate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getFloat(KEY_SPEAK_RATE, 1.15f);
    }

    public final void setSpeakRate(Context context, float v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putFloat(KEY_SPEAK_RATE, RangesKt.coerceIn(v, 0.5f, 2.0f)).apply();
    }

    public final int voicePack(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getInt(KEY_VOICE_PACK, 2);
    }

    public final void setVoicePack(Context context, int v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putInt(KEY_VOICE_PACK, v).apply();
    }

    public final String voiceLocale(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = sp(context).getString(KEY_VOICE_LOCALE, "");
        return string == null ? "" : string;
    }

    public final void setVoiceLocale(Context context, String v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putString(KEY_VOICE_LOCALE, v == null ? "" : v).apply();
    }

    public final String ttsEndpoint(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = sp(context).getString(KEY_TTS_ENDPOINT, "");
        return StringsKt.trim((CharSequence) (string != null ? string : "")).toString();
    }

    public final void setTtsEndpoint(Context context, String v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putString(KEY_TTS_ENDPOINT, StringsKt.trim((CharSequence) (v == null ? "" : v)).toString()).apply();
    }

    public final String ttsApiKey(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = sp(context).getString(KEY_TTS_API_KEY, "");
        return StringsKt.trim((CharSequence) (string != null ? string : "")).toString();
    }

    public final void setTtsApiKey(Context context, String v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putString(KEY_TTS_API_KEY, StringsKt.trim((CharSequence) (v == null ? "" : v)).toString()).apply();
    }

    public final String ttsVoiceId(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = sp(context).getString(KEY_TTS_VOICE_ID, "");
        return string == null ? "" : string;
    }

    public final void setTtsVoiceId(Context context, String v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putString(KEY_TTS_VOICE_ID, v == null ? "" : v).apply();
    }

    public final String customVoiceName(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = sp(context).getString(KEY_CUSTOM_VOICE_NAME, "");
        return string == null ? "" : string;
    }

    public final void setCustomVoiceName(Context context, String v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putString(KEY_CUSTOM_VOICE_NAME, v == null ? "" : v).apply();
    }

    public final String customVoiceSample(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = sp(context).getString(KEY_CUSTOM_VOICE_SAMPLE, "");
        return string == null ? "" : string;
    }

    public final void setCustomVoiceSample(Context context, String v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putString(KEY_CUSTOM_VOICE_SAMPLE, v == null ? "" : v).apply();
    }

    public final boolean customVoiceOn(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_CUSTOM_VOICE_ON, false);
    }

    public final void setCustomVoiceOn(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_CUSTOM_VOICE_ON, v).apply();
    }

    public final boolean customVoiceReady(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (customVoiceOn(context)) {
            if (ttsEndpoint(context).length() > 0) {
                return ttsVoiceId(context).length() > 0;
            }
            return false;
        }
        return false;
    }

    public final boolean speakTips(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_SPEAK_TIPS, true);
    }

    public final void setSpeakTips(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_SPEAK_TIPS, v).apply();
    }

    public final boolean speakTurn(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_SPEAK_TURN, false);
    }

    public final void setSpeakTurn(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_SPEAK_TURN, v).apply();
    }

    public final int ballX(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getInt(KEY_BALL_X, -1);
    }

    public final int ballY(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getInt(KEY_BALL_Y, -1);
    }

    public final String ballEdge(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = sp(context).getString(KEY_BALL_EDGE, "right");
        return string == null ? "right" : string;
    }

    public final void saveBallPosition(Context context, int x, int y, String edge) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putInt(KEY_BALL_X, x).putInt(KEY_BALL_Y, y).putString(KEY_BALL_EDGE, edge == null ? "right" : edge).apply();
    }

    public final int ballSizeDp(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return RangesKt.coerceIn(sp(context).getInt(KEY_BALL_SIZE_DP, 0), 0, 96);
    }

    public final void setBallSizeDp(Context context, int v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putInt(KEY_BALL_SIZE_DP, RangesKt.coerceIn(v, 0, 96)).apply();
    }

    public final boolean autoStartBall(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_AUTO_START_BALL, true);
    }

    public final void setAutoStartBall(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_AUTO_START_BALL, v).apply();
    }

    public final boolean restoreAutoTurn(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_RESTORE_AUTO_TURN, true);
    }

    public final void setRestoreAutoTurn(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_RESTORE_AUTO_TURN, v).apply();
    }

    public final int ballAlpha(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getInt(KEY_BALL_ALPHA, 235);
    }

    public final void setBallAlpha(Context context, int v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putInt(KEY_BALL_ALPHA, v).apply();
    }

    public final List<String> excludedPkgs(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = sp(context).getString(KEY_EXCLUDED_PKGS, "");
        String raw = string != null ? string : "";
        if (raw.length() == 0) {
            return CollectionsKt.emptyList();
        }
        Iterable $this$map$iv = StringsKt.split$default((CharSequence) raw, new String[]{","}, false, 0, 6, (Object) null);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add(StringsKt.trim((CharSequence) it).toString());
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it2 = (String) element$iv$iv;
            if (it2.length() > 0) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv2;
    }

    public final void setExcludedPkgs(Context context, List<String> list) {
        String trim;
        Intrinsics.checkNotNullParameter(context, "context");
        StringBuilder sb = new StringBuilder();
        if (list != null) {
            List<String> $this$forEach$iv = list;
            for (Object element$iv : $this$forEach$iv) {
                String s = (String) element$iv;
                if (s != null && (trim = StringsKt.trim((CharSequence) s).toString()) != null) {
                    if (trim.length() > 0) {
                        if (sb.length() > 0) {
                            sb.append(',');
                        }
                        sb.append(trim);
                    }
                }
            }
        }
        sp(context).edit().putString(KEY_EXCLUDED_PKGS, sb.toString()).apply();
    }

    public final boolean isExcluded(Context context, String pkg) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = pkg;
        if (str == null || str.length() == 0) {
            return false;
        }
        Iterable $this$any$iv = excludedPkgs(context);
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            String it = (String) element$iv;
            if (Intrinsics.areEqual(it, pkg)) {
                return true;
            }
        }
        return false;
    }

    public final boolean silent(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean("silent", false);
    }

    public final void setSilent(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean("silent", v).apply();
    }

    public final boolean vibrateOn(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_VIBRATE_ON, true);
    }

    public final void setVibrateOn(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_VIBRATE_ON, v).apply();
    }

    public final long totalTurns(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getLong(KEY_TOTAL_TURNS, 0L);
    }

    public final long totalReadMs(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getLong(KEY_TOTAL_READ_MS, 0L);
    }

    public final long totalAdSkipped(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getLong(KEY_TOTAL_AD_SKIPPED, 0L);
    }

    public final void bumpTotalTurns(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putLong(KEY_TOTAL_TURNS, totalTurns(context) + 1).apply();
    }

    public final void addReadMs(Context context, long v) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (v <= 0) {
            return;
        }
        sp(context).edit().putLong(KEY_TOTAL_READ_MS, totalReadMs(context) + v).apply();
    }

    public final void bumpAdSkipped(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putLong(KEY_TOTAL_AD_SKIPPED, totalAdSkipped(context) + 1).apply();
    }

    public final void resetStats(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putLong(KEY_TOTAL_TURNS, 0L).putLong(KEY_TOTAL_READ_MS, 0L).putLong(KEY_TOTAL_AD_SKIPPED, 0L).apply();
    }

    public final long backupAt(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getLong(KEY_BACKUP_AT, 0L);
    }

    public final void markBackupAt(Context context, long v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putLong(KEY_BACKUP_AT, v).apply();
    }

    public final String aiEndpoint(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return !aiEnabled(context) ? "" : aiEndpointRaw(context);
    }

    public final void setAiEndpoint(Context context, String v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putString(KEY_AI_ENDPOINT, StringsKt.trim((CharSequence) (v == null ? "" : v)).toString()).apply();
    }

    public final String aiEndpointRaw(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = sp(context).getString(KEY_AI_ENDPOINT, "");
        return StringsKt.trim((CharSequence) (string != null ? string : "")).toString();
    }

    public final String aiApiKey(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return !aiEnabled(context) ? "" : aiApiKeyRaw(context);
    }

    public final void setAiApiKey(Context context, String v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putString(KEY_AI_API_KEY, StringsKt.trim((CharSequence) (v == null ? "" : v)).toString()).apply();
    }

    public final String aiApiKeyRaw(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = sp(context).getString(KEY_AI_API_KEY, "");
        return StringsKt.trim((CharSequence) (string != null ? string : "")).toString();
    }

    public final boolean aiEnabled(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_AI_ENABLED, false);
    }

    public final void setAiEnabled(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_AI_ENABLED, v).apply();
    }

    public final int nightModeState(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getInt(KEY_NIGHT_MODE_STATE, 2);
    }

    public final void setNightModeState(Context context, int v) {
        Intrinsics.checkNotNullParameter(context, "context");
        int state = v >= 0 && v < 3 ? v : 2;
        sp(context).edit().putInt(KEY_NIGHT_MODE_STATE, state).putBoolean(KEY_NIGHT_MODE, state == 1).apply();
    }

    public final boolean nightMode(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_NIGHT_MODE, false);
    }

    public final void setNightMode(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        setNightModeState(context, v ? 1 : 0);
    }

    public final boolean eyeCare(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_EYE_CARE, false);
    }

    public final void setEyeCare(Context context, boolean v) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_EYE_CARE, v).apply();
    }

    public final boolean firstLaunch(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_FIRST_LAUNCH, true);
    }

    public final void markLaunched(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_FIRST_LAUNCH, false).apply();
    }

    public final boolean guideShown(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getBoolean(KEY_GUIDE_SHOWN, false);
    }

    public final void markGuideShown(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putBoolean(KEY_GUIDE_SHOWN, true).apply();
    }

    public final long companionSeconds(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        long v = sp(context).getLong(KEY_COMPANION_SECONDS, -1L);
        if (v >= 0) {
            return v;
        }
        long migrated = sp(context).getLong(KEY_COMPANION_MINUTES, 0L) * 60;
        sp(context).edit().putLong(KEY_COMPANION_SECONDS, migrated).apply();
        return migrated;
    }

    public final void addCompanionSeconds(Context context, long v) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (v <= 0) {
            return;
        }
        sp(context).edit().putLong(KEY_COMPANION_SECONDS, companionSeconds(context) + v).apply();
    }

    public final long companionMinutes(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return companionSeconds(context) / 60;
    }

    public final void addCompanionMinutes(Context context, long v) {
        Intrinsics.checkNotNullParameter(context, "context");
        addCompanionSeconds(context, 60 * v);
    }

    public final int gomokuWins(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getInt("gomoku_wins", 0);
    }

    public final int gomokuLosses(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getInt("gomoku_losses", 0);
    }

    public final int gomokuDraws(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return sp(context).getInt("gomoku_draws", 0);
    }

    public final void addGomokuResult(Context context, boolean win, boolean draw) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences.Editor e = sp(context).edit();
        if (draw) {
            e.putInt("gomoku_draws", gomokuDraws(context) + 1);
        } else if (win) {
            e.putInt("gomoku_wins", gomokuWins(context) + 1);
        } else {
            e.putInt("gomoku_losses", gomokuLosses(context) + 1);
        }
        e.apply();
    }

    public final String companionText(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        long minutes = companionMinutes(context);
        long j = 1440;
        long days = minutes / j;
        long j2 = 60;
        long hours = (minutes % j) / j2;
        long mins = minutes % j2;
        StringBuilder sb = new StringBuilder();
        if (days > 0) {
            sb.append(days).append("天");
        }
        if (hours > 0) {
            sb.append(hours).append("小时");
        }
        sb.append(mins).append("分钟");
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    public final boolean greetedToday(Context context, String date) {
        Intrinsics.checkNotNullParameter(context, "context");
        return date != null && Intrinsics.areEqual(date, sp(context).getString(KEY_LAST_GREET_DATE, ""));
    }

    public final void markGreeted(Context context, String date) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().putString(KEY_LAST_GREET_DATE, date == null ? "" : date).apply();
    }
}
