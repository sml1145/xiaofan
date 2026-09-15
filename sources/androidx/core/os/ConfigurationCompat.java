package androidx.core.os;

import android.content.res.Configuration;
import android.os.LocaleList;
/* loaded from: classes.dex */
public final class ConfigurationCompat {
    private ConfigurationCompat() {
    }

    public static LocaleListCompat getLocales(Configuration configuration) {
        return LocaleListCompat.wrap(Api24Impl.getLocales(configuration));
    }

    public static void setLocales(Configuration configuration, LocaleListCompat locales) {
        Api24Impl.setLocales(configuration, locales);
    }

    /* loaded from: classes.dex */
    static class Api17Impl {
        private Api17Impl() {
        }

        static void setLocale(Configuration configuration, LocaleListCompat locales) {
            if (!locales.isEmpty()) {
                configuration.setLocale(locales.get(0));
            }
        }
    }

    /* loaded from: classes.dex */
    static class Api24Impl {
        private Api24Impl() {
        }

        static LocaleList getLocales(Configuration configuration) {
            return configuration.getLocales();
        }

        static void setLocales(Configuration configuration, LocaleListCompat locales) {
            configuration.setLocales((LocaleList) locales.unwrap());
        }
    }
}
