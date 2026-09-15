package com.xiaofan.bangfan;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: AppKnowledge.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005J\u0006\u0010\u0016\u001a\u00020\u000bJ\u0018\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005J\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0005J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0005J\u0016\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0005J\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00050\u001e2\u0006\u0010\u0013\u001a\u00020\u0014J\u0018\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u0005H\u0002J\u001e\u0010\"\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u000bJ\u001e\u0010$\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u0005J\u001a\u0010&\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0005H\u0002J\u0018\u0010'\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0005H\u0002J\u0018\u0010(\u001a\n **\u0004\u0018\u00010)0)2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010+\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005H\u0002R\u001c\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u001c\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\r¨\u0006,"}, d2 = {"Lcom/xiaofan/bangfan/AppKnowledge;", "", "()V", "BUILTIN_READERS", "", "", "[[Ljava/lang/String;", "BUILTIN_VIDEOS", "FILE", "KEY_KNOWN", "MAX_KNOWN", "", "READER_MARKERS", "[Ljava/lang/String;", "TYPE_READER", "TYPE_UNKNOWN", "TYPE_VIDEO", "VIDEO_MARKERS", "appName", "context", "Landroid/content/Context;", "pkg", "builtinCount", "classify", "forget", "", "isKnownReader", "", "isKnownVideoApp", "knownList", "", "matchEntry", "lower", "target", "remember", "type", "rememberName", "name", "rememberedName", "rememberedType", "sp", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "typeKey", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class AppKnowledge {
    private static final String FILE = "xiaofan_app_knowledge";
    private static final String KEY_KNOWN = "known_pkgs";
    private static final int MAX_KNOWN = 200;
    public static final int TYPE_READER = 1;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_VIDEO = 2;
    public static final AppKnowledge INSTANCE = new AppKnowledge();
    private static final String[][] BUILTIN_READERS = {new String[]{"com.dragon.read", "番茄小说"}, new String[]{"com.dragon.read.lite", "番茄小说极速版"}, new String[]{"com.kmxs.reader", "七猫免费小说"}, new String[]{"com.kmxs.reader.lite", "七猫小说极速版"}, new String[]{"com.qidian.QDReader", "起点读书"}, new String[]{"com.qidian.QDReader.mm", "起点读书"}, new String[]{"com.qidian.QDReader.honor", "起点读书"}, new String[]{"com.qq.reader", "QQ阅读"}, new String[]{"com.tencent.qqreader", "QQ阅读"}, new String[]{"com.flyersoft.seekbooks", "追书神器"}, new String[]{"com.ushaqi.zhuishushenqi", "追书神器"}, new String[]{"com.wtzh.zhuishushenqi", "追书神器"}, new String[]{"com.moonshot.shuqireader", "书旗小说"}, new String[]{"com.shuqi.controller", "书旗小说"}, new String[]{"io.legado.app.release", "阅读（Legado）"}, new String[]{"io.legado.app", "阅读（Legado）"}, new String[]{"com.chaozh.iReaderFree", "掌阅"}, new String[]{"com.chaozh.iReader", "掌阅"}, new String[]{"com.zhangyue.read", "掌阅精选"}, new String[]{"com.tencent.weread", "微信读书"}, new String[]{"com.duokan.reader", "多看阅读"}, new String[]{"com.miui.notes", "小米笔记"}, new String[]{"com.iflytek.read", "讯飞阅读"}, new String[]{"com.mianfeia.lite", "免费小说大全"}, new String[]{"com.readnovel.app", "小说阅读"}, new String[]{"com.hongxiu.reader", "红袖读书"}, new String[]{"com.jjwxc.reader", "晋江小说阅读"}, new String[]{"com.lofter.android", "LOFTER"}, new String[]{"com.zhihu.daily.android", "知乎日报"}, new String[]{"com.netease.snailread", "网易蜗牛读书"}, new String[]{"com.ximalaya.ting.android", "喜马拉雅（听书）"}, new String[]{"com.lanren.tingbook", "懒人听书"}};
    private static final String[][] BUILTIN_VIDEOS = {new String[]{"com.ss.android.ugc.aweme", "抖音"}, new String[]{"com.ss.android.ugc.aweme.lite", "抖音极速版"}, new String[]{"com.ss.android.ugc.aweme.hotsoon", "抖音火山版"}, new String[]{"com.ss.android.ugc.aweme.tv", "抖音TV"}, new String[]{"com.smile.gifmaker", "快手"}, new String[]{"com.kuaishou.nebula", "快手极速版"}, new String[]{"com.yxcorp.gifshow", "快手"}, new String[]{"com.tencent.weishi", "微视"}, new String[]{"com.bilibili.app.in", "哔哩哔哩"}, new String[]{"tv.danmaku.bili", "哔哩哔哩"}, new String[]{"com.zhiliaoapp.musically", "TikTok"}, new String[]{"com.ss.android.ugc.trill", "TikTok"}};
    private static final String[] READER_MARKERS = {"novel", AppPrefs.ROLE_READER, "readbook", "bookread", "ireader", "ebook", "readfree", "bookstore", "story", "zhuishu", "shuqi"};
    private static final String[] VIDEO_MARKERS = {"douyin", "aweme", "kuaishou", "gifmaker", "shortvideo", "musically", "nebula", "hotsoon"};

    private AppKnowledge() {
    }

    private final SharedPreferences sp(Context context) {
        return context.getApplicationContext().getSharedPreferences(FILE, 0);
    }

    public final int classify(Context context, String pkg) {
        String[][] strArr;
        String[][] strArr2;
        String[] strArr3;
        String[] strArr4;
        Intrinsics.checkNotNullParameter(context, "context");
        String str = pkg;
        if (str == null || str.length() == 0) {
            return 0;
        }
        Locale ENGLISH = Locale.ENGLISH;
        Intrinsics.checkNotNullExpressionValue(ENGLISH, "ENGLISH");
        String lower = pkg.toLowerCase(ENGLISH);
        Intrinsics.checkNotNullExpressionValue(lower, "toLowerCase(...)");
        for (String[] entry : BUILTIN_READERS) {
            if (matchEntry(lower, entry[0])) {
                return 1;
            }
        }
        for (String[] entry2 : BUILTIN_VIDEOS) {
            if (matchEntry(lower, entry2[0])) {
                return 2;
            }
        }
        for (String marker : VIDEO_MARKERS) {
            if (StringsKt.contains$default((CharSequence) lower, (CharSequence) marker, false, 2, (Object) null)) {
                return 2;
            }
        }
        for (String marker2 : READER_MARKERS) {
            if (StringsKt.contains$default((CharSequence) lower, (CharSequence) marker2, false, 2, (Object) null)) {
                return 1;
            }
        }
        int remembered = rememberedType(context, pkg);
        if (remembered != 0) {
            return remembered;
        }
        return 0;
    }

    private final boolean matchEntry(String lower, String target) {
        Locale ENGLISH = Locale.ENGLISH;
        Intrinsics.checkNotNullExpressionValue(ENGLISH, "ENGLISH");
        String t = target.toLowerCase(ENGLISH);
        Intrinsics.checkNotNullExpressionValue(t, "toLowerCase(...)");
        return Intrinsics.areEqual(lower, t) || StringsKt.startsWith$default(lower, new StringBuilder().append(t).append(".").toString(), false, 2, (Object) null);
    }

    public final boolean isKnownReader(Context context, String pkg) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pkg, "pkg");
        return classify(context, pkg) == 1;
    }

    public final boolean isKnownVideoApp(Context context, String pkg) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pkg, "pkg");
        return classify(context, pkg) == 2;
    }

    public final String appName(Context context, String pkg) {
        String[][] strArr;
        String[][] strArr2;
        Intrinsics.checkNotNullParameter(context, "context");
        if (pkg == null) {
            return null;
        }
        Locale ENGLISH = Locale.ENGLISH;
        Intrinsics.checkNotNullExpressionValue(ENGLISH, "ENGLISH");
        String lower = pkg.toLowerCase(ENGLISH);
        Intrinsics.checkNotNullExpressionValue(lower, "toLowerCase(...)");
        boolean z = false;
        for (String[] entry : BUILTIN_READERS) {
            if (matchEntry(lower, entry[0])) {
                return entry[1];
            }
        }
        for (String[] entry2 : BUILTIN_VIDEOS) {
            if (matchEntry(lower, entry2[0])) {
                return entry2[1];
            }
        }
        String name = rememberedName(context, pkg);
        String str = name;
        if ((str == null || str.length() == 0) ? true : true) {
            return null;
        }
        return name;
    }

    public final void remember(Context context, String pkg, int type) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pkg, "pkg");
        if ((pkg.length() == 0) || type == 0 || classify(context, pkg) != 0) {
            return;
        }
        sp(context).edit().putInt(typeKey(pkg), type).apply();
        List known = CollectionsKt.toMutableList((Collection) knownList(context));
        if (known.contains(pkg)) {
            return;
        }
        known.add(0, pkg);
        List trimmed = known.size() > 200 ? CollectionsKt.take(known, 200) : known;
        sp(context).edit().putString(KEY_KNOWN, CollectionsKt.joinToString$default(trimmed, ",", null, null, 0, null, null, 62, null)).apply();
    }

    public final void rememberName(Context context, String pkg, String name) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pkg, "pkg");
        Intrinsics.checkNotNullParameter(name, "name");
        if (pkg.length() == 0) {
            return;
        }
        if ((name.length() == 0) || name.length() > 30) {
            return;
        }
        sp(context).edit().putString("name_" + pkg, name).apply();
    }

    private final int rememberedType(Context context, String pkg) {
        return sp(context).getInt(typeKey(pkg), 0);
    }

    private final String rememberedName(Context context, String pkg) {
        return sp(context).getString("name_" + pkg, null);
    }

    private final String typeKey(String pkg) {
        return "type_" + pkg;
    }

    public final List<String> knownList(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = sp(context).getString(KEY_KNOWN, "");
        String raw = string != null ? string : "";
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
        return CollectionsKt.toMutableList((List) destination$iv$iv2);
    }

    public final void forget(Context context, String pkg) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pkg, "pkg");
        List known = CollectionsKt.toMutableList((Collection) knownList(context));
        known.remove(pkg);
        sp(context).edit().putString(KEY_KNOWN, CollectionsKt.joinToString$default(known, ",", null, null, 0, null, null, 62, null)).remove(typeKey(pkg)).remove("name_" + pkg).apply();
    }

    public final int builtinCount() {
        return BUILTIN_READERS.length + BUILTIN_VIDEOS.length;
    }
}
