package com.xiaofan.bangfan;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: TurnManager.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002+,B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u000f\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\tJ\u001e\u0010\u0014\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0011J\u000e\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\tJ\u0016\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0016\u0010\u001d\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u0011J\u0016\u0010\u001f\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u0011J\u0016\u0010 \u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u0011J\u0016\u0010!\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u0011J'\u0010\"\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010#\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110$\"\u00020\u0011¢\u0006\u0002\u0010%J\u0016\u0010&\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u0011J\u0016\u0010'\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u0011J\u0016\u0010(\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u0011J\u0016\u0010)\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\tJ\u0010\u0010*\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/xiaofan/bangfan/TurnManager;", "", "()V", "listener", "Lcom/xiaofan/bangfan/TurnManager$Listener;", "clearListener", "", "l", "isAutoTurnEnabled", "", "context", "Landroid/content/Context;", "notifyAdCountdown", "seconds", "", "notifyAdDetected", "ad", "", "notifyPresenceChanged", "present", "notifyProgress", "book", "chapter", "notifyServiceReady", "ready", "notifyTimedTurnChanged", "enabled", "intervalMs", "", "notifyTurnFailed", "reason", "notifyTurned", "requestFastForward", "requestPrev", "requestReaderOption", "options", "", "(Landroid/content/Context;[Ljava/lang/String;)Z", "requestSwipeDown", "requestSwipeUp", "requestTurn", "setAutoTurn", "setListener", "Listener", "SimpleListener", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class TurnManager {
    public static final TurnManager INSTANCE = new TurnManager();
    private static volatile Listener listener;

    /* compiled from: TurnManager.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000bH\u0016J\u0018\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH\u0016J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u000bH\u0016J\u0018\u0010\u0013\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\bH\u0016J\u0010\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\bH\u0016¨\u0006\u0019"}, d2 = {"Lcom/xiaofan/bangfan/TurnManager$Listener;", "", "onAdCountdown", "", "seconds", "", "onAdDetected", "ad", "", "onAutoTurnChanged", "enabled", "", "onPresenceChanged", "present", "onProgress", "book", "chapter", "onServiceReady", "ready", "onTimedTurnChanged", "intervalMs", "", "onTurnFailed", "reason", "onTurned", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public interface Listener {
        void onAdCountdown(int i);

        void onAdDetected(String str);

        void onAutoTurnChanged(boolean z);

        void onPresenceChanged(boolean z);

        void onProgress(String str, String str2);

        void onServiceReady(boolean z);

        void onTimedTurnChanged(boolean z, long j);

        void onTurnFailed(String str);

        void onTurned(String str);

        /* compiled from: TurnManager.kt */
        @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        /* loaded from: classes4.dex */
        public static final class DefaultImpls {
            public static void onAdCountdown(Listener $this, int seconds) {
            }

            public static void onAdDetected(Listener $this, String ad) {
                Intrinsics.checkNotNullParameter(ad, "ad");
            }

            public static void onAutoTurnChanged(Listener $this, boolean enabled) {
            }

            public static void onPresenceChanged(Listener $this, boolean present) {
            }

            public static void onProgress(Listener $this, String book, String chapter) {
                Intrinsics.checkNotNullParameter(book, "book");
                Intrinsics.checkNotNullParameter(chapter, "chapter");
            }

            public static void onServiceReady(Listener $this, boolean ready) {
            }

            public static void onTimedTurnChanged(Listener $this, boolean enabled, long intervalMs) {
            }

            public static void onTurnFailed(Listener $this, String reason) {
                Intrinsics.checkNotNullParameter(reason, "reason");
            }

            public static void onTurned(Listener $this, String reason) {
                Intrinsics.checkNotNullParameter(reason, "reason");
            }
        }
    }

    private TurnManager() {
    }

    /* compiled from: TurnManager.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/xiaofan/bangfan/TurnManager$SimpleListener;", "Lcom/xiaofan/bangfan/TurnManager$Listener;", "()V", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static class SimpleListener implements Listener {
        @Override // com.xiaofan.bangfan.TurnManager.Listener
        public void onAdCountdown(int seconds) {
            Listener.DefaultImpls.onAdCountdown(this, seconds);
        }

        @Override // com.xiaofan.bangfan.TurnManager.Listener
        public void onAdDetected(String ad) {
            Listener.DefaultImpls.onAdDetected(this, ad);
        }

        @Override // com.xiaofan.bangfan.TurnManager.Listener
        public void onAutoTurnChanged(boolean enabled) {
            Listener.DefaultImpls.onAutoTurnChanged(this, enabled);
        }

        @Override // com.xiaofan.bangfan.TurnManager.Listener
        public void onPresenceChanged(boolean present) {
            Listener.DefaultImpls.onPresenceChanged(this, present);
        }

        @Override // com.xiaofan.bangfan.TurnManager.Listener
        public void onProgress(String book, String chapter) {
            Listener.DefaultImpls.onProgress(this, book, chapter);
        }

        @Override // com.xiaofan.bangfan.TurnManager.Listener
        public void onServiceReady(boolean ready) {
            Listener.DefaultImpls.onServiceReady(this, ready);
        }

        @Override // com.xiaofan.bangfan.TurnManager.Listener
        public void onTimedTurnChanged(boolean enabled, long intervalMs) {
            Listener.DefaultImpls.onTimedTurnChanged(this, enabled, intervalMs);
        }

        @Override // com.xiaofan.bangfan.TurnManager.Listener
        public void onTurnFailed(String reason) {
            Listener.DefaultImpls.onTurnFailed(this, reason);
        }

        @Override // com.xiaofan.bangfan.TurnManager.Listener
        public void onTurned(String reason) {
            Listener.DefaultImpls.onTurned(this, reason);
        }
    }

    public final void setListener(Listener l) {
        listener = l;
    }

    public final void clearListener(Listener l) {
        if (listener == l) {
            listener = null;
        }
    }

    public final boolean isAutoTurnEnabled(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return AppPrefs.INSTANCE.autoTurn(context);
    }

    public final void setAutoTurn(Context context, boolean enabled) {
        Intrinsics.checkNotNullParameter(context, "context");
        AppPrefs.INSTANCE.setAutoTurn(context, enabled);
        Listener listener2 = listener;
        if (listener2 != null) {
            listener2.onAutoTurnChanged(enabled);
        }
    }

    public final void notifyTurned(Context context, String reason) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(reason, "reason");
        Listener listener2 = listener;
        if (listener2 != null) {
            listener2.onTurned(reason);
        }
    }

    public final void notifyTurnFailed(Context context, String reason) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(reason, "reason");
        Listener listener2 = listener;
        if (listener2 != null) {
            listener2.onTurnFailed(reason);
        }
    }

    public final void notifyAdDetected(Context context, String ad) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(ad, "ad");
        Listener listener2 = listener;
        if (listener2 != null) {
            listener2.onAdDetected(ad);
        }
    }

    public final void notifyAdCountdown(Context context, int seconds) {
        Intrinsics.checkNotNullParameter(context, "context");
        Listener listener2 = listener;
        if (listener2 != null) {
            listener2.onAdCountdown(seconds);
        }
    }

    public final void notifyProgress(Context context, String book, String chapter) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(book, "book");
        Intrinsics.checkNotNullParameter(chapter, "chapter");
        Listener listener2 = listener;
        if (listener2 != null) {
            listener2.onProgress(book, chapter);
        }
    }

    public final void notifyServiceReady(boolean ready) {
        Listener listener2 = listener;
        if (listener2 != null) {
            listener2.onServiceReady(ready);
        }
    }

    public final void notifyPresenceChanged(boolean present) {
        Listener listener2 = listener;
        if (listener2 != null) {
            listener2.onPresenceChanged(present);
        }
    }

    public final void notifyTimedTurnChanged(boolean enabled, long intervalMs) {
        Listener listener2 = listener;
        if (listener2 != null) {
            listener2.onTimedTurnChanged(enabled, intervalMs);
        }
    }

    public final boolean requestTurn(Context context, String reason) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(reason, "reason");
        PageTurnAccessibilityService service = PageTurnAccessibilityService.Companion.getInstance();
        if (service == null) {
            return false;
        }
        return service.performPageTurn(reason);
    }

    public final boolean requestPrev(Context context, String reason) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(reason, "reason");
        PageTurnAccessibilityService service = PageTurnAccessibilityService.Companion.getInstance();
        if (service == null) {
            return false;
        }
        return service.performPrevPage(reason);
    }

    public final boolean requestSwipeUp(Context context, String reason) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(reason, "reason");
        PageTurnAccessibilityService service = PageTurnAccessibilityService.Companion.getInstance();
        if (service == null) {
            return false;
        }
        return service.performVerticalSwipe(true, reason);
    }

    public final boolean requestSwipeDown(Context context, String reason) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(reason, "reason");
        PageTurnAccessibilityService service = PageTurnAccessibilityService.Companion.getInstance();
        if (service == null) {
            return false;
        }
        return service.performVerticalSwipe(false, reason);
    }

    public final boolean requestFastForward(Context context, String reason) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(reason, "reason");
        PageTurnAccessibilityService service = PageTurnAccessibilityService.Companion.getInstance();
        if (service == null) {
            return false;
        }
        return service.performFastForward(reason);
    }

    public final boolean requestReaderOption(Context context, String... options) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(options, "options");
        PageTurnAccessibilityService service = PageTurnAccessibilityService.Companion.getInstance();
        if (service == null) {
            return false;
        }
        return service.toggleReaderOption((String[]) Arrays.copyOf(options, options.length));
    }
}
