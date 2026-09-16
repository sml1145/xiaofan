package com.xiaofan.bangfan.ai;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: VoiceCommandRouter.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0011B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J)\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\u000b\"\u00020\tH\u0002¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\tJ\u0010\u0010\u000f\u001a\u00020\u00102\b\u0010\u000e\u001a\u0004\u0018\u00010\tR\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/xiaofan/bangfan/ai/VoiceCommandRouter;", "", "()V", "t2s", "", "", "has", "", "s", "", "kws", "", "(Ljava/lang/String;[Ljava/lang/String;)Z", "normalize", "raw", "route", "Lcom/xiaofan/bangfan/ai/VoiceCommandRouter$Command;", "Command", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class VoiceCommandRouter {
    public static final VoiceCommandRouter INSTANCE = new VoiceCommandRouter();
    private static final Map<Character, Character> t2s = MapsKt.mapOf(TuplesKt.to((char) 38913, (char) 39029), TuplesKt.to((char) 32380, (char) 32487), TuplesKt.to((char) 32396, (char) 32493), TuplesKt.to((char) 35695, (char) 35793), TuplesKt.to((char) 36984, (char) 36873), TuplesKt.to((char) 36628, (char) 36741), TuplesKt.to((char) 26283, (char) 26242), TuplesKt.to((char) 38364, (char) 20851), TuplesKt.to((char) 38281, (char) 38381), TuplesKt.to((char) 39023, (char) 26174), TuplesKt.to((char) 38577, (char) 38544), TuplesKt.to((char) 26178, (char) 26102), TuplesKt.to((char) 38291, (char) 38388), TuplesKt.to((char) 40670, (char) 28857), TuplesKt.to((char) 30906, (char) 30830), TuplesKt.to((char) 35469, (char) 35748), TuplesKt.to((char) 20491, (char) 20010), TuplesKt.to((char) 40636, (char) 20040), TuplesKt.to((char) 38283, (char) 24320), TuplesKt.to((char) 35222, (char) 35270), TuplesKt.to((char) 38971, (char) 39057));

    private VoiceCommandRouter() {
    }

    /* compiled from: VoiceCommandRouter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013¨\u0006\u0014"}, d2 = {"Lcom/xiaofan/bangfan/ai/VoiceCommandRouter$Command;", "", "label", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getLabel", "()Ljava/lang/String;", "NEXT_PAGE", "PREV_PAGE", "AUTO_START", "PAUSE", "FASTER", "SLOWER", "BALL_SHOW", "BALL_HIDE", "TELL_TIME", "WEATHER", "CONFIRM", "CANCEL", "NONE", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes3.dex */
    public enum Command {
        NEXT_PAGE("下一页"),
        PREV_PAGE("上一页"),
        AUTO_START("开始/继续自动翻页"),
        PAUSE("暂停"),
        FASTER("加快"),
        SLOWER("减慢"),
        BALL_SHOW("打开悬浮球"),
        BALL_HIDE("关闭悬浮球"),
        TELL_TIME("报时"),
        WEATHER("天气"),
        CONFIRM("确认"),
        CANCEL("取消"),
        NONE("未识别");
        
        private final String label;
        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<Command> getEntries() {
            return $ENTRIES;
        }

        Command(String label) {
            this.label = label;
        }

        public final String getLabel() {
            return this.label;
        }
    }

    public final String normalize(String raw) {
        String str = raw;
        if (str == null || str.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(raw.length());
        for (int i = 0; i < raw.length(); i++) {
            char c = raw.charAt(i);
            if (t2s.containsKey(Character.valueOf(c))) {
                sb.append(t2s.get(Character.valueOf(c)));
            } else {
                if (!Character.isLetterOrDigit(c)) {
                    if (!(19968 <= c && c < 40960)) {
                    }
                }
                sb.append(c);
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        String lowerCase = sb2.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }

    private final boolean has(String s, String... kws) {
        for (String str : kws) {
            if (StringsKt.contains$default((CharSequence) s, (CharSequence) str, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    public final Command route(String raw) {
        String s = normalize(raw);
        if (s.length() == 0) {
            return Command.NONE;
        }
        if (has(s, "悬浮球", "悬浮", "浮球", "球") && has(s, "打开", "显示", "开启", "调出")) {
            return Command.BALL_SHOW;
        }
        if (has(s, "悬浮球", "悬浮", "浮球", "球") && has(s, "关闭", "隐藏", "收起", "关掉")) {
            return Command.BALL_HIDE;
        }
        if (has(s, "自动翻页", "开始翻页", "继续翻", "继续", "开始自动", "继续自动")) {
            return Command.AUTO_START;
        }
        if (has(s, "快一点", "快点", "加速", "快些", "再快")) {
            return Command.FASTER;
        }
        if (has(s, "慢一点", "慢点", "减速", "慢些", "再慢")) {
            return Command.SLOWER;
        }
        if (has(s, "下一页", "下页", "往后翻", "向后翻", "翻下一页", "下一张", "往后")) {
            return Command.NEXT_PAGE;
        }
        if (has(s, "上一页", "上页", "往前翻", "向前翻", "翻上一页", "上一张", "往前", "回退", "退回")) {
            return Command.PREV_PAGE;
        }
        if (has(s, "翻页", "翻一", "翻書", "翻书")) {
            return Command.NEXT_PAGE;
        }
        if (has(s, "暂停", "停下", "停止", "先别翻", "别翻了", "停一下") || StringsKt.endsWith$default(s, "停", false, 2, (Object) null)) {
            return Command.PAUSE;
        }
        return has(s, "几点", "报时", "现在时间", "什么时间", "多少点") ? Command.TELL_TIME : has(s, "天气", "气温", "温度") ? Command.WEATHER : has(s, "确认", "确定", "好的", "没问题", "可以") ? Command.CONFIRM : has(s, "取消", "算了", "不用了") ? Command.CANCEL : Command.NONE;
    }
}
