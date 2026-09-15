package com.xiaofan.bangfan;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.BookStore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: BookStore.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001eB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\n\u001a\u00020\u000bJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00102\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\u0011\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0004J\u001e\u0010\u0013\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0015H\u0002J\u0018\u0010\u0016\u001a\n \u0018*\u0004\u0018\u00010\u00170\u00172\u0006\u0010\n\u001a\u00020\u000bH\u0002J4\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u00042\b\u0010\u001c\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001d\u001a\u00020\u001aR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/xiaofan/bangfan/BookStore;", "", "()V", "FILE", "", "KEY_LIST", "MAX_RECORDS", "", "bumpTurnOnLatest", "", "context", "Landroid/content/Context;", "clear", "latest", "Lcom/xiaofan/bangfan/BookStore$Record;", "load", "", "remove", "title", "save", "list", "", "sp", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "upsert", "", "chapter", "pkg", "countTurn", "Record", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class BookStore {
    private static final String FILE = "xiaofan_books";
    public static final BookStore INSTANCE = new BookStore();
    private static final String KEY_LIST = "books";
    private static final int MAX_RECORDS = 300;

    private BookStore() {
    }

    /* compiled from: BookStore.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0006\u0010\u001b\u001a\u00020\u001cR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\nR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006\u001e"}, d2 = {"Lcom/xiaofan/bangfan/BookStore$Record;", "", "bookTitle", "", "chapterTitle", "packageName", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBookTitle", "()Ljava/lang/String;", "setBookTitle", "(Ljava/lang/String;)V", "getChapterTitle", "setChapterTitle", "getPackageName", "setPackageName", "turnCount", "", "getTurnCount", "()I", "setTurnCount", "(I)V", "updatedAt", "", "getUpdatedAt", "()J", "setUpdatedAt", "(J)V", "toJson", "Lorg/json/JSONObject;", "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Record {
        public static final Companion Companion = new Companion(null);
        private String bookTitle;
        private String chapterTitle;
        private String packageName;
        private int turnCount;
        private long updatedAt;

        public Record(String bookTitle, String chapterTitle, String packageName) {
            Intrinsics.checkNotNullParameter(bookTitle, "bookTitle");
            Intrinsics.checkNotNullParameter(chapterTitle, "chapterTitle");
            Intrinsics.checkNotNullParameter(packageName, "packageName");
            this.bookTitle = bookTitle;
            this.chapterTitle = chapterTitle;
            this.packageName = packageName;
            this.updatedAt = System.currentTimeMillis();
        }

        public final String getBookTitle() {
            return this.bookTitle;
        }

        public final void setBookTitle(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.bookTitle = str;
        }

        public final String getChapterTitle() {
            return this.chapterTitle;
        }

        public final void setChapterTitle(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.chapterTitle = str;
        }

        public final String getPackageName() {
            return this.packageName;
        }

        public final void setPackageName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.packageName = str;
        }

        public final long getUpdatedAt() {
            return this.updatedAt;
        }

        public final void setUpdatedAt(long j) {
            this.updatedAt = j;
        }

        public final int getTurnCount() {
            return this.turnCount;
        }

        public final void setTurnCount(int i) {
            this.turnCount = i;
        }

        public final JSONObject toJson() {
            JSONObject obj = new JSONObject();
            obj.put("bookTitle", this.bookTitle);
            obj.put("chapterTitle", this.chapterTitle);
            obj.put("packageName", this.packageName);
            obj.put("updatedAt", this.updatedAt);
            obj.put("turnCount", this.turnCount);
            return obj;
        }

        /* compiled from: BookStore.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/xiaofan/bangfan/BookStore$Record$Companion;", "", "()V", "fromJson", "Lcom/xiaofan/bangfan/BookStore$Record;", "obj", "Lorg/json/JSONObject;", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        /* loaded from: classes4.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final Record fromJson(JSONObject obj) {
                Intrinsics.checkNotNullParameter(obj, "obj");
                String optString = obj.optString("bookTitle", "未知书籍");
                Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
                String optString2 = obj.optString("chapterTitle", "未知章节");
                Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
                String optString3 = obj.optString("packageName", "");
                Intrinsics.checkNotNullExpressionValue(optString3, "optString(...)");
                Record record = new Record(optString, optString2, optString3);
                record.setUpdatedAt(obj.optLong("updatedAt", System.currentTimeMillis()));
                record.setTurnCount(obj.optInt("turnCount", 0));
                return record;
            }
        }
    }

    private final SharedPreferences sp(Context context) {
        return context.getApplicationContext().getSharedPreferences(FILE, 0);
    }

    public final List<Record> load(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        List list = new ArrayList();
        String string = sp(context).getString(KEY_LIST, null);
        String str = string;
        if (str == null || str.length() == 0) {
            return list;
        }
        try {
            JSONArray arr = new JSONArray(string);
            int length = arr.length();
            for (int i = 0; i < length; i++) {
                Record.Companion companion = Record.Companion;
                JSONObject jSONObject = arr.getJSONObject(i);
                Intrinsics.checkNotNullExpressionValue(jSONObject, "getJSONObject(...)");
                list.add(companion.fromJson(jSONObject));
            }
            if (list.size() > 1) {
                CollectionsKt.sortWith(list, new Comparator() { // from class: com.xiaofan.bangfan.BookStore$load$$inlined$sortByDescending$1
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        BookStore.Record it = (BookStore.Record) t2;
                        BookStore.Record it2 = (BookStore.Record) t;
                        return ComparisonsKt.compareValues(Long.valueOf(it.getUpdatedAt()), Long.valueOf(it2.getUpdatedAt()));
                    }
                });
            }
            return list;
        } catch (Exception e) {
            return new ArrayList();
        }
    }

    private final void save(Context context, List<Record> list) {
        JSONArray arr = new JSONArray();
        for (Record record : list) {
            arr.put(record.toJson());
        }
        sp(context).edit().putString(KEY_LIST, arr.toString()).apply();
    }

    public final boolean upsert(Context context, String title, String chapter, String pkg, boolean countTurn) {
        Object obj;
        boolean chapterChanged;
        boolean chapterChanged2;
        Intrinsics.checkNotNullParameter(context, "context");
        String str = title;
        boolean z = true;
        if (((str == null || StringsKt.isBlank(str)) ? 1 : null) != null) {
            return false;
        }
        String trimTitle = StringsKt.trim((CharSequence) title).toString();
        String trimChapter = (chapter == null || (trimChapter = StringsKt.trim((CharSequence) chapter).toString()) == null) ? "" : "";
        List list = load(context);
        Iterator<T> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            Record it2 = (Record) obj;
            if (Intrinsics.areEqual(it2.getBookTitle(), trimTitle)) {
                break;
            }
        }
        Record existing = (Record) obj;
        if (existing == null) {
            Record record = new Record(trimTitle, trimChapter, pkg != null ? pkg : "");
            record.setTurnCount(countTurn ? 1 : 0);
            list.add(0, record);
            chapterChanged2 = true;
        } else {
            if ((trimChapter.length() == 0 ? 1 : null) != null || Intrinsics.areEqual(trimChapter, existing.getChapterTitle())) {
                chapterChanged = false;
            } else {
                existing.setChapterTitle(trimChapter);
                chapterChanged = true;
            }
            if (countTurn) {
                existing.setTurnCount(existing.getTurnCount() + 1);
            }
            existing.setUpdatedAt(System.currentTimeMillis());
            String str2 = pkg;
            if (str2 != null && str2.length() != 0) {
                z = false;
            }
            if (!z) {
                existing.setPackageName(pkg);
            }
            chapterChanged2 = chapterChanged;
        }
        List trimmed = list.size() > 300 ? CollectionsKt.toMutableList((Collection) CollectionsKt.take(list, 300)) : list;
        save(context, trimmed);
        return chapterChanged2;
    }

    public final void bumpTurnOnLatest(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        List list = load(context);
        if (list.isEmpty()) {
            return;
        }
        Record record = list.get(0);
        record.setTurnCount(record.getTurnCount() + 1);
        record.setUpdatedAt(System.currentTimeMillis());
        save(context, list);
    }

    public final void remove(Context context, String title) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(title, "title");
        Iterable $this$filter$iv = load(context);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Record it = (Record) element$iv$iv;
            if (!Intrinsics.areEqual(it.getBookTitle(), title)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List list = CollectionsKt.toMutableList((List) destination$iv$iv);
        save(context, list);
    }

    public final void clear(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        sp(context).edit().remove(KEY_LIST).apply();
    }

    public final Record latest(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        List list = load(context);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
