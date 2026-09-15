package com.xiaofan.bangfan;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: SettingsBackup.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u000e\u0010\u000f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u0012\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0011J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0004H\u0002J \u0010\u0017\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0018\u0010\u0019\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0011J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00040\u001d2\u0006\u0010\r\u001a\u00020\u000eH\u0002J \u0010\u001e\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u001f\u001a\u00020\u001bJ*\u0010 \u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00042\b\u0010!\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001f\u001a\u00020\u001bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/xiaofan/bangfan/SettingsBackup;", "", "()V", "BOOKS_FILE", "", "FORMAT_VERSION", "", "PREFS_FILES", "PREFS_PREFIX", "UTF8", "Ljava/nio/charset/Charset;", "kotlin.jvm.PlatformType", "appVersionName", "context", "Landroid/content/Context;", "bookCount", "defaultFile", "Ljava/io/File;", "describe", "file", "dumpPrefs", "Lorg/json/JSONObject;", "name", "dumpRaw", "key", "export", "hasBackup", "", "listPrefsFiles", "", "restore", "merge", "restorePrefsFile", "prefs", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class SettingsBackup {
    private static final String BOOKS_FILE = "xiaofan_books";
    private static final int FORMAT_VERSION = 3;
    private static final String PREFS_FILES = "xiaofan_prefs";
    private static final String PREFS_PREFIX = "xiaofan_";
    public static final SettingsBackup INSTANCE = new SettingsBackup();
    private static final Charset UTF8 = Charset.forName("UTF-8");

    private SettingsBackup() {
    }

    public final File defaultFile(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        File dir = context.getExternalFilesDir(null);
        if (dir == null) {
            dir = context.getFilesDir();
        }
        return new File(dir, "xiaofan-settings-backup.json");
    }

    private final List<String> listPrefsFiles(Context context) {
        String[] strArr;
        ArrayList list = new ArrayList();
        try {
            File prefsDir = new File(context.getApplicationInfo().dataDir, "shared_prefs");
            File[] listFiles = prefsDir.listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    String name = file.getName();
                    Intrinsics.checkNotNull(name);
                    if (StringsKt.endsWith$default(name, ".xml", false, 2, (Object) null)) {
                        String key = name.substring(0, name.length() - 4);
                        Intrinsics.checkNotNullExpressionValue(key, "substring(...)");
                        if (StringsKt.startsWith$default(key, PREFS_PREFIX, false, 2, (Object) null) && !list.contains(key)) {
                            list.add(key);
                        }
                    }
                }
            }
        } catch (Throwable th) {
        }
        for (String str : new String[]{PREFS_FILES, BOOKS_FILE}) {
            if (!list.contains(str)) {
                list.add(str);
            }
        }
        Collections.sort(list);
        return list;
    }

    public final File export(Context context, File file) {
        File target;
        Intrinsics.checkNotNullParameter(context, "context");
        if (file == null) {
            target = defaultFile(context);
            Intrinsics.checkNotNull(target);
        } else {
            target = file;
        }
        File it = target.getParentFile();
        if (it != null && !it.exists()) {
            it.mkdirs();
        }
        JSONObject root = new JSONObject();
        root.put("formatVersion", 3);
        root.put("appVersion", appVersionName(context));
        root.put("exportedAt", System.currentTimeMillis());
        root.put("exportedAtText", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date()));
        root.put(AppPrefs.KEY_NICKNAME, AppPrefs.INSTANCE.nickname(context));
        root.put("avatarIndex", AppPrefs.INSTANCE.avatarIndex(context));
        JSONObject allPrefs = new JSONObject();
        List files = listPrefsFiles(context);
        for (String name : files) {
            allPrefs.put(name, dumpPrefs(context, name));
        }
        root.put("allPrefs", allPrefs);
        root.put("prefsFileCount", files.size());
        root.put("prefs", dumpPrefs(context, PREFS_FILES));
        root.put("books", dumpRaw(context, BOOKS_FILE, "books"));
        FileOutputStream out = new FileOutputStream(target);
        try {
            String jSONObject = root.toString(2);
            Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
            Charset UTF82 = UTF8;
            Intrinsics.checkNotNullExpressionValue(UTF82, "UTF8");
            byte[] bytes = jSONObject.getBytes(UTF82);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            out.write(bytes);
            out.flush();
            AppPrefs.INSTANCE.markBackupAt(context, System.currentTimeMillis());
            return target;
        } finally {
            try {
                out.close();
            } catch (Exception e) {
            }
        }
    }

    private final String appVersionName(Context context) {
        try {
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            return str == null ? "?" : str;
        } catch (Throwable th) {
            return "?";
        }
    }

    private final JSONObject dumpPrefs(Context context, String name) {
        JSONObject obj = new JSONObject();
        try {
            Map<String, ?> all = context.getSharedPreferences(name, 0).getAll();
            Intrinsics.checkNotNullExpressionValue(all, "getAll(...)");
            for (Map.Entry<String, ?> entry : all.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value != null) {
                    if (value instanceof Boolean) {
                        obj.put(key, ((Boolean) value).booleanValue());
                    } else if (value instanceof Integer) {
                        obj.put(key, ((Number) value).intValue());
                    } else if (value instanceof Long) {
                        obj.put(key, ((Number) value).longValue());
                    } else if (value instanceof Float) {
                        obj.put(key, ((Number) value).floatValue());
                    } else {
                        obj.put(key, value.toString());
                    }
                }
            }
        } catch (Exception e) {
        }
        return obj;
    }

    private final String dumpRaw(Context context, String name, String key) {
        try {
            String string = context.getSharedPreferences(name, 0).getString(key, "");
            return string == null ? "" : string;
        } catch (Exception e) {
            return "";
        }
    }

    public final int restore(Context context, File file, boolean merge) {
        Throwable th;
        Intrinsics.checkNotNullParameter(context, "context");
        if (file == null || !file.exists()) {
            throw new Exception("存档文件不存在");
        }
        byte[] data = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        int read = 0;
        while (true) {
            try {
                int read2 = data.length;
                if (read < read2) {
                    try {
                        int n = fis.read(data, read, data.length - read);
                        if (n >= 0) {
                            read += n;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            fis.close();
                        } catch (Exception e) {
                        }
                        throw th;
                    }
                }
                try {
                    fis.close();
                } catch (Exception e2) {
                }
                Charset UTF82 = UTF8;
                Intrinsics.checkNotNullExpressionValue(UTF82, "UTF8");
                JSONObject root = new JSONObject(new String(data, UTF82));
                if (root.optInt("formatVersion", 1) > 3) {
                    throw new Exception("存档版本高于当前 APP，请升级后再恢复");
                }
                int count = 0;
                JSONObject allPrefs = root.optJSONObject("allPrefs");
                boolean hasAll = allPrefs != null && allPrefs.length() > 0;
                if (!hasAll) {
                    count = 0 + restorePrefsFile(context, PREFS_FILES, root.optJSONObject("prefs"), merge);
                    String books = root.optString("books", "");
                    Intrinsics.checkNotNull(books);
                    if (books.length() > 0) {
                        context.getSharedPreferences(BOOKS_FILE, 0).edit().putString("books", books).apply();
                        count++;
                    }
                } else {
                    Intrinsics.checkNotNull(allPrefs);
                    Iterator keys = allPrefs.keys();
                    while (keys.hasNext()) {
                        String name = keys.next();
                        Intrinsics.checkNotNull(name);
                        byte[] data2 = data;
                        if (StringsKt.startsWith$default(name, PREFS_PREFIX, false, 2, (Object) null)) {
                            count += restorePrefsFile(context, name, allPrefs.optJSONObject(name), merge);
                            data = data2;
                        } else {
                            data = data2;
                        }
                    }
                }
                String nickname = root.optString(AppPrefs.KEY_NICKNAME, "");
                Intrinsics.checkNotNull(nickname);
                if (nickname.length() > 0) {
                    AppPrefs.INSTANCE.setNickname(context, nickname);
                    count++;
                }
                int avatar = root.optInt("avatarIndex", 0);
                if (avatar > 0) {
                    AppPrefs.INSTANCE.setAvatarIndex(context, avatar);
                    count++;
                }
                int count2 = count;
                try {
                    XiaoFanAlarm.INSTANCE.rescheduleAll(context);
                } catch (Throwable th3) {
                }
                return count2;
            } catch (Throwable th4) {
                th = th4;
            }
        }
    }

    private final int restorePrefsFile(Context context, String name, JSONObject prefs, boolean merge) {
        if (prefs == null) {
            return 0;
        }
        SharedPreferences sp = context.getSharedPreferences(name, 0);
        Map existing = sp.getAll();
        SharedPreferences.Editor editor = sp.edit();
        int count = 0;
        Iterator keys = prefs.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (!merge || !existing.containsKey(key)) {
                Object value = prefs.opt(key);
                if (value instanceof Boolean) {
                    editor.putBoolean(key, ((Boolean) value).booleanValue());
                } else if (value instanceof Integer) {
                    editor.putInt(key, ((Number) value).intValue());
                } else if (value instanceof Long) {
                    editor.putLong(key, ((Number) value).longValue());
                } else {
                    if (value instanceof Double ? true : value instanceof Float) {
                        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Number");
                        editor.putFloat(key, ((Number) value).floatValue());
                    } else {
                        editor.putString(key, value.toString());
                    }
                }
                count++;
            }
        }
        editor.apply();
        return count;
    }

    public final String describe(Context context, File file) {
        int bookCount;
        JSONObject alarms;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(file, "file");
        try {
            byte[] data = new byte[(int) Math.min(file.length(), 400000L)];
            FileInputStream fis = new FileInputStream(file);
            int read = fis.read(data);
            fis.close();
            if (read <= 0) {
                return "空存档";
            }
            Charset UTF82 = UTF8;
            Intrinsics.checkNotNullExpressionValue(UTF82, "UTF8");
            JSONObject root = new JSONObject(new String(data, 0, read, UTF82));
            String exportedAt = root.optString("exportedAtText", "");
            String nickname = root.optString(AppPrefs.KEY_NICKNAME, "");
            JSONObject prefs = root.optJSONObject("prefs");
            int prefsCount = prefs != null ? prefs.length() : 0;
            JSONObject allPrefs = root.optJSONObject("allPrefs");
            int fileCount = allPrefs != null ? allPrefs.length() : 0;
            int alarmCount = 0;
            if (allPrefs != null && (alarms = allPrefs.optJSONObject("xiaofan_alarms")) != null) {
                String alarmsStr = alarms.optString("alarms", "");
                Intrinsics.checkNotNull(alarmsStr);
                if (alarmsStr.length() > 0) {
                    alarmCount = new JSONArray(alarmsStr).length();
                }
            }
            StringBuilder sb = new StringBuilder("导出时间 ").append(exportedAt).append("\n称呼 ").append(nickname).append("\n设置项 ").append(prefsCount).append(" 条");
            if (fileCount > 0) {
                sb.append(" · 数据文件 ").append(fileCount).append(" 个");
            }
            if (alarmCount > 0) {
                sb.append(" · 闹钟 ").append(alarmCount).append(" 个");
            }
            String books = root.optString("books", "");
            Intrinsics.checkNotNull(books);
            if ((books.length() > 0) && (bookCount = new JSONArray(books).length()) > 0) {
                sb.append(" · 阅读记录 ").append(bookCount).append(" 条");
            }
            String sb2 = sb.toString();
            Intrinsics.checkNotNull(sb2);
            return sb2;
        } catch (Exception e) {
            return "存档读取失败：" + e.getMessage();
        }
    }

    public final boolean hasBackup(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        File file = defaultFile(context);
        return file != null && file.exists() && file.length() > 10;
    }

    public final int bookCount(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return BookStore.INSTANCE.load(context).size();
    }
}
