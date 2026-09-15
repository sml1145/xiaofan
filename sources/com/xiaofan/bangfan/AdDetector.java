package com.xiaofan.bangfan;

import android.content.Context;
import android.graphics.Rect;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.ScreenSnapshot;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
/* compiled from: AdDetector.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001:\u000267B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u001a\u001a\u00020\u00052\b\u0010\u001b\u001a\u0004\u0018\u00010\u0005H\u0002J\u0010\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fJ$\u0010 \u001a\u00020\u00052\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010#\u001a\u0004\u0018\u00010\u0005J\u0012\u0010$\u001a\u0004\u0018\u00010\u00052\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fJ\u0012\u0010%\u001a\u0004\u0018\u00010&2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fJ'\u0010'\u001a\u0004\u0018\u00010\u00052\b\u0010\u001b\u001a\u0004\u0018\u00010\u00052\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002¢\u0006\u0002\u0010)J\u0010\u0010*\u001a\u00020+2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010,\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u0005H\u0002J\u001c\u0010-\u001a\u00020+2\b\u0010.\u001a\u0004\u0018\u00010\u00052\b\u0010/\u001a\u0004\u0018\u00010\u0005H\u0002J\u0012\u00100\u001a\u00020\b2\b\u00101\u001a\u0004\u0018\u00010\u0005H\u0002J \u00102\u001a\u00020+2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u00103\u001a\u00020\b2\u0006\u00104\u001a\u00020\bJ\u0012\u00105\u001a\u00020\u00052\b\u0010.\u001a\u0004\u0018\u00010\u0005H\u0002R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0016\u0010\u0018\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lcom/xiaofan/bangfan/AdDetector;", "", "()V", "AD_KEYWORDS", "", "", "[Ljava/lang/String;", "AD_PAGE", "", "BOOK_END_PAGE", "CHAPTER_END_KEYWORDS", "COUNTDOWN_PATTERNS", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "ILLUSTRATION_PAGE", "ILLUSTRATION_SAFE_CHARS", "IMAGE_RATIO_AD", "", "IMAGE_RATIO_LOOSE", "MIN_BASELINE_SAMPLES", "MIN_BODY_CHARS_FOR_TEXT_PAGE", "NOT_AD", "STRICT_END_KEYWORDS", "TITLE_JUNK_KEYWORDS", "TITLE_JUNK_PATTERN", "UNKNOWN", "clean", "text", "detect", "Lcom/xiaofan/bangfan/AdDetector$Result;", "snapshot", "Lcom/xiaofan/bangfan/ScreenSnapshot;", "extractBookTitle", "context", "Landroid/content/Context;", "packageName", "extractChapter", "findCountdown", "Lcom/xiaofan/bangfan/AdDetector$CountdownHit;", "firstHit", "keywords", "(Ljava/lang/String;[Ljava/lang/String;)Ljava/lang/String;", "hasFullscreenMedia", "", "indexOfChapterMark", "isPlausibleTitle", "title", "appName", "parseIntSafe", "str", "shouldSpeedUp", "baselineChars", "baselineSamples", "stripChapterSuffix", "CountdownHit", "Result", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class AdDetector {
    public static final int AD_PAGE = 1;
    public static final int BOOK_END_PAGE = 2;
    public static final int ILLUSTRATION_PAGE = 3;
    private static final int ILLUSTRATION_SAFE_CHARS = 200;
    private static final float IMAGE_RATIO_AD = 0.8f;
    private static final float IMAGE_RATIO_LOOSE = 0.55f;
    public static final int MIN_BASELINE_SAMPLES = 5;
    private static final int MIN_BODY_CHARS_FOR_TEXT_PAGE = 120;
    public static final int NOT_AD = 0;
    public static final int UNKNOWN = 4;
    public static final AdDetector INSTANCE = new AdDetector();
    private static final String[] CHAPTER_END_KEYWORDS = {"本章完", "本章结束", "章节完", "本章终", "下一章", "下章预告", "下一章预告", "加入书签", "返回目录", "章节目录", "目 录", "本书目录", "投推荐票", "月票", "打赏", "催更", "作者的话", "作者感言", "本章说", "书友评论", "评论区"};
    private static final String[] STRICT_END_KEYWORDS = {"本章完", "本章结束", "章节完", "本章终", "本回完", "下章预告", "下一章预告", "作者的话", "作者感言", "作者有话说"};
    private static final String[] AD_KEYWORDS = {"跳过", "跳过广告", "关闭广告", "广告", "推广", "立即下载", "马上下载", "点击下载", "免费下载", "安装", "查看详情", "了解更多", "领取", "立即领取", "限时", "优惠券", "红包", "签到领", "点击参与", "去逛逛", "立即购买", "试玩", "首充", "充值返利", "开局", "上线送", "变态版"};
    private static final Pattern COUNTDOWN_PATTERNS = Pattern.compile("(?:跳过|skip|广告|剩余|还有)?\\s*(?:(\\d{1,3})\\s*(?:s|S|秒)|(\\d{1,2}):(\\d{2})|(\\d{1,3}))\\s*(?:s|S|秒)?");
    private static final String[] TITLE_JUNK_KEYWORDS = {"广告", "推广", "下载", "安装", "更新", "签到", "领取", "充值", "会员", "书架", "书城", "发现", "我的", "设置", "搜索", "返回", "目录", "书签", "夜间", "白天", "亮度", "音量", "电量", "电池", "信号", "WiFi", "wifi", "已连接", "未连接", "加载中", "正在加载", "网络", "无网络", "登录", "注册", "退出", "分享", "评论", "弹幕", "点赞", "关注", "订阅", "限时", "免费", "热门", "推荐", "排行", "榜单", "精选", "新书", "完本", "上午", "下午", "中午", "凌晨", "昨天", "今天", "明天", "星期", "通知", "消息", "活动", "福利", "红包", "优惠", "客服", "帮助", "关于"};
    private static final Pattern TITLE_JUNK_PATTERN = Pattern.compile("^\\s*(?:\\d{1,2}:\\d{2}(?::\\d{2})?|\\d{1,4}[-/年]\\d{1,2}([-/月]\\d{1,2}日?)?|\\d{1,3}\\s*%|第?\\s*\\d{1,5}\\s*/\\s*\\d{1,5}\\s*页?|\\d{1,6}\\s*[页章节话]|\\d+(\\.\\d+)*|[a-zA-Z0-9\\s\\.\\-_]{1,30})\\s*$");

    private AdDetector() {
    }

    /* compiled from: AdDetector.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\nJ\u0006\u0010\u0013\u001a\u00020\u0014R\u0013\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000e¨\u0006\u0015"}, d2 = {"Lcom/xiaofan/bangfan/AdDetector$Result;", "", "type", "", "reason", "", "countdownSeconds", "skipButtonBounds", "Landroid/graphics/Rect;", "countdownBounds", "(ILjava/lang/String;ILandroid/graphics/Rect;Landroid/graphics/Rect;)V", "getCountdownBounds", "()Landroid/graphics/Rect;", "getCountdownSeconds", "()I", "getReason", "()Ljava/lang/String;", "getSkipButtonBounds", "getType", "isAd", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Result {
        private final Rect countdownBounds;
        private final int countdownSeconds;
        private final String reason;
        private final Rect skipButtonBounds;
        private final int type;

        public Result(int type, String reason, int countdownSeconds, Rect skipButtonBounds, Rect countdownBounds) {
            Intrinsics.checkNotNullParameter(reason, "reason");
            this.type = type;
            this.reason = reason;
            this.countdownSeconds = countdownSeconds;
            this.skipButtonBounds = skipButtonBounds;
            this.countdownBounds = countdownBounds;
        }

        public final int getType() {
            return this.type;
        }

        public final String getReason() {
            return this.reason;
        }

        public final int getCountdownSeconds() {
            return this.countdownSeconds;
        }

        public final Rect getSkipButtonBounds() {
            return this.skipButtonBounds;
        }

        public final Rect getCountdownBounds() {
            return this.countdownBounds;
        }

        public final boolean isAd() {
            return this.type == 1;
        }
    }

    /* compiled from: AdDetector.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/xiaofan/bangfan/AdDetector$CountdownHit;", "", "seconds", "", "bounds", "Landroid/graphics/Rect;", "(ILandroid/graphics/Rect;)V", "getBounds", "()Landroid/graphics/Rect;", "getSeconds", "()I", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class CountdownHit {
        private final Rect bounds;
        private final int seconds;

        public CountdownHit(int seconds, Rect bounds) {
            Intrinsics.checkNotNullParameter(bounds, "bounds");
            this.seconds = seconds;
            this.bounds = bounds;
        }

        public final Rect getBounds() {
            return this.bounds;
        }

        public final int getSeconds() {
            return this.seconds;
        }
    }

    public final Result detect(ScreenSnapshot snapshot) {
        if (snapshot == null || snapshot.getScreenWidth() <= 0 || snapshot.getScreenHeight() <= 0) {
            return new Result(4, "屏幕信息不可用", -1, null, null);
        }
        String allText = snapshot.allText();
        int bodyChars = snapshot.bodyCharCount();
        float imageRatio = snapshot.imageCoverageRatio();
        float textRatio = snapshot.textCoverageRatio();
        String chapterHit = firstHit(allText, CHAPTER_END_KEYWORDS);
        if (chapterHit != null) {
            return new Result(2, "命中章节结构词「" + chapterHit + "」", -1, null, null);
        }
        if (snapshot.getImages().size() > 2 || bodyChars < 200) {
            String adHit = firstHit(allText, AD_KEYWORDS);
            Rect skipBounds = snapshot.findNode("跳过", "关闭广告", "关闭", "skip", "Skip", "SKIP");
            CountdownHit countdown = findCountdown(snapshot);
            boolean hasAdFeature = (adHit == null && skipBounds == null && countdown == null) ? false : true;
            if (imageRatio >= IMAGE_RATIO_AD && bodyChars < MIN_BODY_CHARS_FOR_TEXT_PAGE) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("图片占屏%.0f%%≥80%%，正文仅%d字", Arrays.copyOf(new Object[]{Float.valueOf(100 * imageRatio), Integer.valueOf(bodyChars)}, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                return new Result(1, format, countdown != null ? countdown.getSeconds() : -1, skipBounds, countdown != null ? countdown.getBounds() : null);
            } else if (imageRatio >= IMAGE_RATIO_LOOSE && bodyChars < MIN_BODY_CHARS_FOR_TEXT_PAGE && hasAdFeature) {
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                String format2 = String.format("图片占屏%.0f%%，正文%d字，命中广告特征", Arrays.copyOf(new Object[]{Float.valueOf(100 * imageRatio), Integer.valueOf(bodyChars)}, 2));
                Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
                return new Result(1, format2, countdown != null ? countdown.getSeconds() : -1, skipBounds, countdown != null ? countdown.getBounds() : null);
            } else if (bodyChars < 20 && hasFullscreenMedia(snapshot)) {
                return new Result(1, "全屏媒体且无正文文字", countdown != null ? countdown.getSeconds() : -1, skipBounds, countdown != null ? countdown.getBounds() : null);
            } else if (bodyChars >= MIN_BODY_CHARS_FOR_TEXT_PAGE) {
                return new Result(0, "正文" + bodyChars + "字，文字占屏" + Math.round(100 * textRatio) + "%", -1, null, null);
            } else {
                return new Result(4, "正文" + bodyChars + "字，图片占屏" + Math.round(100 * imageRatio) + "%，特征不足", countdown != null ? countdown.getSeconds() : -1, skipBounds, countdown != null ? countdown.getBounds() : null);
            }
        }
        return new Result(3, "正文" + bodyChars + "字，配图" + snapshot.getImages().size() + "张，判为插图页", -1, null, null);
    }

    public final boolean shouldSpeedUp(ScreenSnapshot snapshot, int baselineChars, int baselineSamples) {
        int bodyChars;
        if (snapshot == null || snapshot.getScreenWidth() <= 0 || snapshot.getScreenHeight() <= 0) {
            return false;
        }
        try {
            Result result = detect(snapshot);
            if (result.getType() == 1 || result.getType() == 3 || (bodyChars = snapshot.bodyCharCount()) <= 0) {
                return false;
            }
            boolean isStrictEnd = firstHit(snapshot.allText(), STRICT_END_KEYWORDS) != null;
            return isStrictEnd ? baselineChars > 0 && baselineSamples >= 5 && ((float) bodyChars) < ((float) baselineChars) * 0.4f : baselineChars <= 0 ? bodyChars < MIN_BODY_CHARS_FOR_TEXT_PAGE : ((float) bodyChars) < ((float) baselineChars) * 0.7f;
        } catch (Throwable th) {
            return false;
        }
    }

    private final boolean hasFullscreenMedia(ScreenSnapshot snapshot) {
        int w = snapshot.getScreenWidth();
        int h = snapshot.getScreenHeight();
        for (ScreenSnapshot.ImageItem img : snapshot.getImages()) {
            float widthRatio = w == 0 ? 0.0f : img.getBounds().width() / w;
            float heightRatio = h != 0 ? img.getBounds().height() / h : 0.0f;
            if (widthRatio >= 0.92f && heightRatio >= 0.75f) {
                return true;
            }
        }
        return false;
    }

    private final String firstHit(String text, String[] keywords) {
        if (text == null) {
            return null;
        }
        for (String kw : keywords) {
            if (StringsKt.contains$default((CharSequence) text, (CharSequence) kw, false, 2, (Object) null)) {
                return kw;
            }
        }
        return null;
    }

    public final CountdownHit findCountdown(ScreenSnapshot snapshot) {
        int seconds;
        if (snapshot == null) {
            return null;
        }
        CountdownHit result = null;
        Iterable $this$filter$iv = snapshot.getTexts();
        Collection destination$iv$iv = new ArrayList();
        Iterator<T> it = $this$filter$iv.iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                break;
            }
            Object element$iv$iv = it.next();
            ScreenSnapshot.TextItem it2 = (ScreenSnapshot.TextItem) element$iv$iv;
            if ((it2.getText() == null || it2.getText().length() > 24) ? false : false) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List<ScreenSnapshot.TextItem> shortTexts = (List) destination$iv$iv;
        for (ScreenSnapshot.TextItem item : shortTexts) {
            String trim = StringsKt.trim((CharSequence) item.getText()).toString();
            boolean hasContext = StringsKt.contains$default((CharSequence) trim, (CharSequence) "跳过", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) trim, (CharSequence) "广告", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) trim, (CharSequence) "s", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) trim, (CharSequence) "秒", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) trim, (CharSequence) "skip", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) trim, (CharSequence) "剩余", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) trim, (CharSequence) ":", false, 2, (Object) null);
            Matcher matcher = COUNTDOWN_PATTERNS.matcher(trim);
            while (matcher.find()) {
                if (matcher.group(1) != null) {
                    seconds = parseIntSafe(matcher.group(1));
                } else {
                    seconds = (matcher.group(2) == null || matcher.group(3) == null) ? (matcher.group(4) == null || !hasContext) ? -1 : parseIntSafe(matcher.group(4)) : parseIntSafe(matcher.group(3)) + (parseIntSafe(matcher.group(2)) * 60);
                }
                if (seconds > 0 && seconds <= MIN_BODY_CHARS_FOR_TEXT_PAGE && (result == null || seconds < result.getSeconds())) {
                    result = new CountdownHit(seconds, item.getBounds());
                }
            }
            if (result != null && hasContext) {
                break;
            }
        }
        return result;
    }

    private final int parseIntSafe(String str) {
        if (str != null) {
            try {
                String obj = StringsKt.trim((CharSequence) str).toString();
                if (obj != null) {
                    return Integer.parseInt(obj);
                }
                return -1;
            } catch (Exception e) {
                return -1;
            }
        }
        return -1;
    }

    public final String extractChapter(ScreenSnapshot snapshot) {
        if (snapshot == null || snapshot.getTexts().isEmpty()) {
            return null;
        }
        Pattern pattern = Pattern.compile("^\\s*(第\\s*[0-9一二三四五六七八九十百千万零两]{1,12}\\s*[章节回卷篇部]|第\\s*[0-9]{1,5}\\s*[章节回卷篇部]|Chapter\\s*\\d+|CHAPTER\\s*\\d+|chapter\\s*\\d+)\\s*[^\\n]{0,40}");
        for (ScreenSnapshot.TextItem item : snapshot.getTexts()) {
            String text = item.getText();
            if (text != null) {
                String trim = StringsKt.trim((CharSequence) text).toString();
                if (trim.length() <= 60 && pattern.matcher(trim).find()) {
                    return clean(trim);
                }
            }
        }
        String result = null;
        for (ScreenSnapshot.TextItem item2 : snapshot.getTexts()) {
            String text2 = item2.getText();
            if (text2 != null) {
                String trim2 = StringsKt.trim((CharSequence) text2).toString();
                int length = trim2.length();
                boolean z = false;
                if (2 <= length && length < 41) {
                    z = true;
                }
                if (z && item2.getBounds().top >= 0 && item2.getBounds().top <= snapshot.getScreenHeight() * 0.2f && (result == null || item2.getBounds().top < 0)) {
                    result = clean(trim2);
                }
            }
        }
        return result;
    }

    public final String extractBookTitle(Context context, ScreenSnapshot snapshot, String packageName) {
        String appName;
        if (context == null || packageName == null) {
            return "未知书籍";
        }
        try {
            appName = AppKnowledge.INSTANCE.appName(context, packageName);
        } catch (Throwable th) {
            appName = null;
        }
        if (snapshot == null) {
            return "未知书籍";
        }
        if (snapshot.getWindowTitle() != null) {
            String clean = clean(snapshot.getWindowTitle());
            if (isPlausibleTitle(clean, appName)) {
                return stripChapterSuffix(clean);
            }
        }
        String result = null;
        int minTop = Integer.MAX_VALUE;
        for (ScreenSnapshot.TextItem item : snapshot.getTexts()) {
            String text = item.getText();
            if (text != null) {
                String clean2 = clean(text);
                if (isPlausibleTitle(clean2, appName) && item.getBounds().top >= 0 && item.getBounds().top < minTop && item.getBounds().top <= snapshot.getScreenHeight() * 0.15f) {
                    minTop = item.getBounds().top;
                    result = stripChapterSuffix(clean2);
                }
            }
        }
        return result == null ? "未知书籍" : result;
    }

    private final boolean isPlausibleTitle(String title, String appName) {
        CharSequence $this$any$iv;
        String[] strArr;
        if (title == null) {
            return false;
        }
        String trim = StringsKt.trim((CharSequence) title).toString();
        if (trim.length() < 2 || trim.length() > 20) {
            return false;
        }
        String $this$any$iv2 = trim;
        int i = 0;
        while (true) {
            if (i < $this$any$iv2.length()) {
                char element$iv = $this$any$iv2.charAt(i);
                char it = (19968 > element$iv || element$iv >= 40960) ? (char) 0 : (char) 1;
                if (it != 0) {
                    $this$any$iv = 1;
                    break;
                }
                i++;
            } else {
                $this$any$iv = null;
                break;
            }
        }
        if ($this$any$iv == null) {
            return false;
        }
        if (!new Regex("^\\s*第\\s*[0-9一二三四五六七八九十百千万零两]{1,12}\\s*[章节回卷篇部].*$").matches(trim) && !TITLE_JUNK_PATTERN.matcher(trim).matches()) {
            if (appName != null && (Intrinsics.areEqual(trim, appName) || StringsKt.contains$default((CharSequence) appName, (CharSequence) trim, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) trim, (CharSequence) appName, false, 2, (Object) null))) {
                return false;
            }
            for (String kw : TITLE_JUNK_KEYWORDS) {
                if (StringsKt.contains$default((CharSequence) trim, (CharSequence) kw, false, 2, (Object) null)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private final String stripChapterSuffix(String title) {
        if (title == null) {
            return "";
        }
        String trim = StringsKt.trim((CharSequence) title).toString();
        int idx = indexOfChapterMark(trim);
        if (idx > 0) {
            String substring = trim.substring(0, idx);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            trim = StringsKt.trim((CharSequence) substring).toString();
        }
        while (true) {
            if (!StringsKt.endsWith$default(trim, "·", false, 2, (Object) null) && !StringsKt.endsWith$default(trim, "-", false, 2, (Object) null) && !StringsKt.endsWith$default(trim, "_", false, 2, (Object) null) && !StringsKt.endsWith$default(trim, "|", false, 2, (Object) null) && !StringsKt.endsWith$default(trim, " ", false, 2, (Object) null) && !StringsKt.endsWith$default(trim, "，", false, 2, (Object) null) && !StringsKt.endsWith$default(trim, ",", false, 2, (Object) null)) {
                break;
            }
            String substring2 = trim.substring(0, trim.length() - 1);
            Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
            trim = StringsKt.trim((CharSequence) substring2).toString();
        }
        return trim.length() >= 2 ? trim : StringsKt.trim((CharSequence) title).toString();
    }

    private final int indexOfChapterMark(String text) {
        Matcher matcher = Pattern.compile("第\\s*[0-9一二三四五六七八九十百千万零两]{1,12}\\s*[章节回卷篇部]").matcher(text);
        if (matcher.find()) {
            return matcher.start();
        }
        return -1;
    }

    private final String clean(String text) {
        if (text == null) {
            return "";
        }
        String trim = StringsKt.trim((CharSequence) StringsKt.replace$default(StringsKt.replace$default(text, '\r', ' ', false, 4, (Object) null), '\n', ' ', false, 4, (Object) null)).toString();
        while (StringsKt.contains$default((CharSequence) trim, (CharSequence) "  ", false, 2, (Object) null)) {
            trim = StringsKt.replace$default(trim, "  ", " ", false, 4, (Object) null);
        }
        return trim;
    }
}
