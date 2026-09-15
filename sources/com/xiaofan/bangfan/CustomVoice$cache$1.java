package com.xiaofan.bangfan;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
/* compiled from: CustomVoice.kt */
@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010'\n\u0000*\u0001\u0000\b\n\u0018\u00002\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bH\u0014¨\u0006\t"}, d2 = {"com/xiaofan/bangfan/CustomVoice$cache$1", "Ljava/util/LinkedHashMap;", "", "", "Lkotlin/collections/LinkedHashMap;", "removeEldestEntry", "", "eldest", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class CustomVoice$cache$1 extends LinkedHashMap<String, byte[]> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public CustomVoice$cache$1() {
        super(16, 0.75f, true);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ boolean containsKey(Object key) {
        if (key instanceof String) {
            return containsKey((String) key);
        }
        return false;
    }

    public /* bridge */ boolean containsKey(String key) {
        return super.containsKey((Object) key);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ boolean containsValue(Object value) {
        if (value instanceof byte[]) {
            return containsValue((byte[]) value);
        }
        return false;
    }

    public /* bridge */ boolean containsValue(byte[] value) {
        return super.containsValue((Object) value);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Set<Map.Entry<String, byte[]>> entrySet() {
        return getEntries();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object get(Object key) {
        if (key instanceof String) {
            return get((String) key);
        }
        return null;
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ byte[] get(Object key) {
        if (key instanceof String) {
            return get((String) key);
        }
        return null;
    }

    public /* bridge */ byte[] get(String key) {
        return (byte[]) super.get((Object) key);
    }

    public /* bridge */ Set<Map.Entry<String, byte[]>> getEntries() {
        return super.entrySet();
    }

    public /* bridge */ Set<String> getKeys() {
        return super.keySet();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object getOrDefault(Object key, Object defaultValue) {
        return !(key instanceof String) ? defaultValue : getOrDefault((String) key, (byte[]) defaultValue);
    }

    public final /* bridge */ byte[] getOrDefault(Object key, byte[] defaultValue) {
        return !(key instanceof String) ? defaultValue : getOrDefault((String) key, defaultValue);
    }

    public /* bridge */ byte[] getOrDefault(String key, byte[] defaultValue) {
        return (byte[]) super.getOrDefault((Object) key, (String) defaultValue);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ Collection<byte[]> getValues() {
        return super.values();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Set<String> keySet() {
        return getKeys();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object remove(Object key) {
        if (key instanceof String) {
            return remove((String) key);
        }
        return null;
    }

    @Override // java.util.HashMap, java.util.Map
    public final /* bridge */ boolean remove(Object key, Object value) {
        if ((key instanceof String) && (value instanceof byte[])) {
            return remove((String) key, (byte[]) value);
        }
        return false;
    }

    public /* bridge */ boolean remove(String key, byte[] value) {
        return super.remove((Object) key, (Object) value);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ byte[] remove(Object key) {
        if (key instanceof String) {
            return remove((String) key);
        }
        return null;
    }

    public /* bridge */ byte[] remove(String key) {
        return (byte[]) super.remove((Object) key);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Collection<byte[]> values() {
        return getValues();
    }

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(Map.Entry<String, byte[]> entry) {
        return size() > 24;
    }
}
