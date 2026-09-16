package com.xiaofan.bangfan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import com.xiaofan.bangfan.DictationController;
import com.xiaofan.bangfan.HorizontalPager;
import com.xiaofan.bangfan.TurnManager;
import com.xiaofan.bangfan.XiaoFanBrain;
import com.xiaofan.bangfan.ai.LocalBrain;
import com.xiaofan.bangfan.ai.OfflineBrain;
import com.xiaofan.bangfan.ai.OfflineModels;
import java.io.InputStream;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.DebugKt;
/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0015\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u008e\u00012\u00020\u00012\u00020\u0002:\u0002\u008e\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010)\u001a\u00020*H\u0002J\u0018\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020-H\u0002J\u0010\u0010/\u001a\u00020*2\u0006\u0010,\u001a\u00020-H\u0002J\u0010\u00100\u001a\u00020*2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u00101\u001a\u00020*2\u0006\u00102\u001a\u00020-2\u0006\u00103\u001a\u00020-H\u0002J\u0012\u00104\u001a\u00020*2\b\u00105\u001a\u0004\u0018\u00010\u001fH\u0002J\u0010\u00106\u001a\u00020*2\u0006\u00107\u001a\u00020-H\u0002J\b\u00108\u001a\u00020*H\u0002J\u0010\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020:H\u0002J\u0010\u0010<\u001a\u00020*2\u0006\u0010=\u001a\u00020\u0005H\u0002J\u0018\u0010>\u001a\u00020*2\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020-H\u0002J\b\u0010B\u001a\u00020@H\u0002J\b\u0010C\u001a\u00020@H\u0002J\b\u0010D\u001a\u00020-H\u0002J\b\u0010E\u001a\u00020@H\u0002J\u0012\u0010F\u001a\u0004\u0018\u00010-2\u0006\u0010,\u001a\u00020-H\u0002J\b\u0010G\u001a\u00020:H\u0002J\u0010\u0010H\u001a\u00020*2\u0006\u0010,\u001a\u00020-H\u0002J\u0010\u0010I\u001a\u00020-2\u0006\u0010J\u001a\u00020KH\u0002J(\u0010L\u001a\u00020@2\u0006\u0010M\u001a\u00020-2\u0006\u0010N\u001a\u00020-2\u0006\u0010O\u001a\u00020-2\u0006\u0010A\u001a\u00020-H\u0002J\b\u0010P\u001a\u00020*H\u0002J\u0012\u0010Q\u001a\u00020\u00112\b\u00107\u001a\u0004\u0018\u00010-H\u0002J(\u0010R\u001a\u00020@2\u0006\u0010M\u001a\u00020-2\u0006\u0010N\u001a\u00020-2\u0006\u0010O\u001a\u00020-2\u0006\u0010A\u001a\u00020-H\u0002J\b\u0010S\u001a\u00020\u000bH\u0002J\"\u0010T\u001a\u00020*2\u0006\u0010U\u001a\u00020:2\u0006\u0010V\u001a\u00020:2\b\u0010W\u001a\u0004\u0018\u00010XH\u0014J\u0010\u0010Y\u001a\u00020*2\u0006\u0010J\u001a\u00020:H\u0016J\u0010\u0010Z\u001a\u00020*2\u0006\u0010[\u001a\u00020-H\u0016J\u0010\u0010\\\u001a\u00020*2\u0006\u0010]\u001a\u00020\u0011H\u0016J\b\u0010^\u001a\u00020*H\u0016J\u0012\u0010_\u001a\u00020*2\b\u0010`\u001a\u0004\u0018\u00010aH\u0014J\b\u0010b\u001a\u00020*H\u0014J\b\u0010c\u001a\u00020*H\u0014J\u0010\u0010d\u001a\u00020*2\u0006\u0010e\u001a\u00020\u0011H\u0016J\u0018\u0010f\u001a\u00020*2\u0006\u0010g\u001a\u00020-2\u0006\u0010h\u001a\u00020-H\u0016J-\u0010i\u001a\u00020*2\u0006\u0010U\u001a\u00020:2\u000e\u0010j\u001a\n\u0012\u0006\b\u0001\u0012\u00020-0\u001a2\u0006\u0010k\u001a\u00020lH\u0016¢\u0006\u0002\u0010mJ\b\u0010n\u001a\u00020*H\u0014J\u0010\u0010o\u001a\u00020*2\u0006\u0010p\u001a\u00020\u0011H\u0016J\b\u0010q\u001a\u00020*H\u0014J\u0018\u0010r\u001a\u00020*2\u0006\u0010s\u001a\u00020\u00112\u0006\u0010t\u001a\u00020KH\u0016J\u0010\u0010u\u001a\u00020*2\u0006\u0010[\u001a\u00020-H\u0016J\u0010\u0010v\u001a\u00020*2\u0006\u0010[\u001a\u00020-H\u0016J\u0010\u0010w\u001a\u00020*2\u0006\u0010,\u001a\u00020-H\u0002J\b\u0010x\u001a\u00020-H\u0002J\b\u0010y\u001a\u00020*H\u0002J\b\u0010z\u001a\u00020*H\u0002J\b\u0010{\u001a\u00020*H\u0002J\u0010\u0010|\u001a\u00020*2\u0006\u0010}\u001a\u00020:H\u0002J\u0010\u0010~\u001a\u00020*2\u0006\u00103\u001a\u00020-H\u0002J\u0010\u0010\u007f\u001a\u00020*2\u0006\u00103\u001a\u00020-H\u0002J\t\u0010\u0080\u0001\u001a\u00020*H\u0002J\t\u0010\u0081\u0001\u001a\u00020*H\u0002J\u0012\u0010\u0082\u0001\u001a\u00020*2\u0007\u0010\u0083\u0001\u001a\u00020\u0011H\u0002J\t\u0010\u0084\u0001\u001a\u00020*H\u0002J\t\u0010\u0085\u0001\u001a\u00020*H\u0002J\t\u0010\u0086\u0001\u001a\u00020*H\u0002J\u0011\u0010\u0087\u0001\u001a\u00020*2\u0006\u0010,\u001a\u00020-H\u0002J\t\u0010\u0088\u0001\u001a\u00020*H\u0002J\t\u0010\u0089\u0001\u001a\u00020*H\u0002J\t\u0010\u008a\u0001\u001a\u00020*H\u0002J\t\u0010\u008b\u0001\u001a\u00020*H\u0002J\u0012\u0010\u008c\u0001\u001a\u00030\u008d\u00012\u0006\u0010?\u001a\u00020@H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001bR\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u008f\u0001"}, d2 = {"Lcom/xiaofan/bangfan/MainActivity;", "Lcom/xiaofan/bangfan/BaseActivity;", "Lcom/xiaofan/bangfan/TurnManager$Listener;", "()V", "ballBtn", "Landroid/widget/TextView;", "bubbleHideTask", "Ljava/lang/Runnable;", "chatInput", "Landroid/widget/EditText;", "chatLog", "Landroid/widget/LinearLayout;", "companionText", "companionTimeText", "dictate", "Lcom/xiaofan/bangfan/DictationController;", "dictating", "", "identityText", "main", "Landroid/os/Handler;", "mascotAvatarBtn", "mascotView", "Lcom/xiaofan/bangfan/MascotView;", "nightBtn", "pageDots", "", "[Landroid/widget/TextView;", "pager", "Lcom/xiaofan/bangfan/HorizontalPager;", "profileAvatar", "Landroid/widget/ImageView;", "profileNameText", "profileSubText", "quickStatus", "refreshTask", "roleBadge", "sendBtnRef", "speechBubble", "updateCheckPosted", "wentBackground", "afterProfileChanged", "", "answerByCloudThenLocal", "text", "", "endpoint", "answerByLocalBrain", "answerOpenQuestion", "appendChat", "sender", NotificationCompat.CATEGORY_MESSAGE, "applyAvatarTo", "imageView", "applyNickname", "name", "autoStartBallIfNeeded", "avatarRes", "", "index", "beginDictation", "btn", "bindModuleClick", "view", "Landroid/view/View;", "target", "buildPage1", "buildPage2", "buildQuickStatus", "buildUi", "builtinChatReply", "countMissingPerms", "externalFallback", "fmtSeconds", "seconds", "", "fullWidthCell", "emoji", "title", "desc", "greetOnce", "isReservedPlannerName", "moduleCell", "newPage", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onAdCountdown", "onAdDetected", "reason", "onAutoTurnChanged", DebugKt.DEBUG_PROPERTY_VALUE_ON, "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onPresenceChanged", "present", "onProgress", "book", "chapter", "onRequestPermissionsResult", "permissions", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "onServiceReady", "ready", "onStop", "onTimedTurnChanged", "running", "interval", "onTurnFailed", "onTurned", "openQwen", "pickGreeting", "pickImageFromGallery", "postRefresh", "refreshAll", "refreshDots", "page", "replaceLastXiaoFan", "reply", "sendChat", "showAvatarChooser", "showIdentityDialog", "first", "showMascotAvatarDialog", "showNicknameDialog", "showNightModeDialog", "showSpeechBubble", "startBallService", "startPeriodicRefresh", "stopPeriodicRefresh", "toggleBall", "wrapScroll", "Landroid/widget/ScrollView;", "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class MainActivity extends BaseActivity implements TurnManager.Listener {
    public static final Companion Companion = new Companion(null);
    private static final int REQ_DICTATE = 4211;
    private static final int REQ_PICK_IMAGE = 4201;
    private static volatile boolean autoStartTried;
    private static volatile boolean greetedThisProcess;
    private TextView ballBtn;
    private Runnable bubbleHideTask;
    private EditText chatInput;
    private LinearLayout chatLog;
    private TextView companionText;
    private TextView companionTimeText;
    private DictationController dictate;
    private boolean dictating;
    private TextView identityText;
    private TextView mascotAvatarBtn;
    private MascotView mascotView;
    private TextView nightBtn;
    private TextView[] pageDots;
    private HorizontalPager pager;
    private ImageView profileAvatar;
    private TextView profileNameText;
    private TextView profileSubText;
    private TextView quickStatus;
    private Runnable refreshTask;
    private TextView roleBadge;
    private TextView sendBtnRef;
    private TextView speechBubble;
    private boolean updateCheckPosted;
    private final Handler main = new Handler(Looper.getMainLooper());
    private boolean wentBackground = true;

    /* compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/xiaofan/bangfan/MainActivity$Companion;", "", "()V", "REQ_DICTATE", "", "REQ_PICK_IMAGE", "autoStartTried", "", "greetedThisProcess", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Override // com.xiaofan.bangfan.TurnManager.Listener
    public void onAdCountdown(int seconds) {
    }

    @Override // com.xiaofan.bangfan.TurnManager.Listener
    public void onAdDetected(String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
    }

    @Override // com.xiaofan.bangfan.TurnManager.Listener
    public void onProgress(String book, String chapter) {
        Intrinsics.checkNotNullParameter(book, "book");
        Intrinsics.checkNotNullParameter(chapter, "chapter");
    }

    @Override // com.xiaofan.bangfan.TurnManager.Listener
    public void onTurnFailed(String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
    }

    @Override // com.xiaofan.bangfan.TurnManager.Listener
    public void onTurned(String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(buildUi());
        TurnManager.INSTANCE.setListener(this);
        XiaoFanVoice.INSTANCE.init(this);
        OfflineBrain offlineBrain = OfflineBrain.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        offlineBrain.bootstrap(applicationContext);
        XiaoFanAlarm.INSTANCE.rescheduleAll(this);
        if (AppPrefs.INSTANCE.firstLaunch(this)) {
            AppPrefs.INSTANCE.markLaunched(this);
            showIdentityDialog(true);
            return;
        }
        greetOnce();
        autoStartBallIfNeeded();
    }

    private final void greetOnce() {
        if (greetedThisProcess) {
            return;
        }
        greetedThisProcess = true;
        if (AppPrefs.INSTANCE.speakOn(this)) {
            final String msg = GreetingHelper.INSTANCE.coldGreeting();
            appendChat("小翻", msg);
            this.main.postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.greetOnce$lambda$1(MainActivity.this, msg);
                }
            }, 900L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void greetOnce$lambda$1(final MainActivity this$0, String msg) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(msg, "$msg");
        XiaoFanVoice.INSTANCE.tip(this$0, msg);
        MascotView mascotView = this$0.mascotView;
        if (mascotView != null) {
            mascotView.setSpeaking(true);
        }
        this$0.main.postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda26
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.greetOnce$lambda$1$lambda$0(MainActivity.this);
            }
        }, Math.min((long) AppPrefs.FALLBACK_READ_MS, msg.length() * 240));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void greetOnce$lambda$1$lambda$0(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MascotView mascotView = this$0.mascotView;
        if (mascotView != null) {
            mascotView.setSpeaking(false);
        }
    }

    private final void autoStartBallIfNeeded() {
        if (!FloatBallService.Companion.isRunning() && AppPrefs.INSTANCE.profileDone(this) && UiKit.INSTANCE.canOverlay(this) && UiKit.INSTANCE.isA11yEnabled(this)) {
            startBallService();
        }
    }

    private final void startBallService() {
        Intent intent = new Intent(this, FloatBallService.class);
        intent.setAction(FloatBallService.ACTION_START);
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                startForegroundService(intent);
            } else {
                startService(intent);
            }
        } catch (Throwable th) {
            Toast.makeText(this, "悬浮球启动失败：" + th.getMessage(), 1).show();
        }
        this.main.postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.startBallService$lambda$2(MainActivity.this);
            }
        }, 400L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startBallService$lambda$2(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.refreshAll();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaofan.bangfan.BaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        MascotView mascotView = this.mascotView;
        if (mascotView != null) {
            mascotView.applySceneByTime();
        }
        refreshAll();
        startPeriodicRefresh();
        if (this.wentBackground && !this.updateCheckPosted) {
            this.wentBackground = false;
            this.updateCheckPosted = true;
            this.main.postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda14
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.onResume$lambda$3(MainActivity.this);
                }
            }, 1500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResume$lambda$3(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.updateCheckPosted = false;
        UpdateManager.INSTANCE.onEnterApp(this$0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaofan.bangfan.BaseActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        stopPeriodicRefresh();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        this.wentBackground = true;
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        TurnManager.INSTANCE.clearListener(this);
        stopPeriodicRefresh();
        DictationController dictationController = this.dictate;
        if (dictationController != null) {
            dictationController.release();
        }
        this.dictate = null;
        super.onDestroy();
    }

    private final View buildUi() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(1);
        root.setBackgroundColor(UiKit.INSTANCE.color(this, R.color.bg));
        this.pager = new HorizontalPager(this, null, 2, null);
        HorizontalPager horizontalPager = this.pager;
        Intrinsics.checkNotNull(horizontalPager);
        horizontalPager.addView(wrapScroll(buildPage1()));
        HorizontalPager horizontalPager2 = this.pager;
        Intrinsics.checkNotNull(horizontalPager2);
        horizontalPager2.addView(wrapScroll(buildPage2()));
        root.addView(this.pager, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        LinearLayout dotsRow = new LinearLayout(this);
        dotsRow.setOrientation(0);
        dotsRow.setGravity(17);
        dotsRow.setPadding(0, UiKit.INSTANCE.dp(this, 6.0f), 0, UiKit.INSTANCE.dp(this, 10.0f));
        this.pageDots = new TextView[2];
        for (int i = 0; i < 2; i++) {
            TextView[] textViewArr = this.pageDots;
            Intrinsics.checkNotNull(textViewArr);
            textViewArr[i] = new TextView(this);
            TextView[] textViewArr2 = this.pageDots;
            Intrinsics.checkNotNull(textViewArr2);
            TextView textView = textViewArr2[i];
            Intrinsics.checkNotNull(textView);
            textView.setText("●");
            TextView[] textViewArr3 = this.pageDots;
            Intrinsics.checkNotNull(textViewArr3);
            TextView textView2 = textViewArr3[i];
            Intrinsics.checkNotNull(textView2);
            textView2.setTextSize(11.0f);
            TextView[] textViewArr4 = this.pageDots;
            Intrinsics.checkNotNull(textViewArr4);
            TextView textView3 = textViewArr4[i];
            Intrinsics.checkNotNull(textView3);
            textView3.setPadding(UiKit.INSTANCE.dp(this, 10.0f), UiKit.INSTANCE.dp(this, 4.0f), UiKit.INSTANCE.dp(this, 10.0f), UiKit.INSTANCE.dp(this, 4.0f));
            final int target = i;
            TextView[] textViewArr5 = this.pageDots;
            Intrinsics.checkNotNull(textViewArr5);
            TextView textView4 = textViewArr5[i];
            Intrinsics.checkNotNull(textView4);
            textView4.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda32
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MainActivity.buildUi$lambda$4(MainActivity.this, target, view);
                }
            });
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, -2);
            lp.leftMargin = UiKit.INSTANCE.dp(this, 3.0f);
            lp.rightMargin = UiKit.INSTANCE.dp(this, 3.0f);
            TextView[] textViewArr6 = this.pageDots;
            Intrinsics.checkNotNull(textViewArr6);
            dotsRow.addView(textViewArr6[i], lp);
        }
        root.addView(dotsRow, UiKit.INSTANCE.matchWrap());
        HorizontalPager horizontalPager3 = this.pager;
        Intrinsics.checkNotNull(horizontalPager3);
        horizontalPager3.setOnPageChangedListener(new HorizontalPager.OnPageChangedListener() { // from class: com.xiaofan.bangfan.MainActivity$buildUi$2
            @Override // com.xiaofan.bangfan.HorizontalPager.OnPageChangedListener
            public void onPageChanged(int page) {
                MainActivity.this.refreshDots(page);
            }
        });
        refreshDots(0);
        return root;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void buildUi$lambda$4(MainActivity this$0, int $target, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HorizontalPager horizontalPager = this$0.pager;
        if (horizontalPager != null) {
            horizontalPager.scrollToPage($target, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshDots(int page) {
        TextView[] dots = this.pageDots;
        if (dots == null) {
            return;
        }
        int i = 0;
        int length = dots.length;
        while (i < length) {
            TextView textView = dots[i];
            if (textView != null) {
                textView.setTextColor(UiKit.INSTANCE.color(this, i == page ? R.color.brand : R.color.line));
            }
            i++;
        }
    }

    private final ScrollView wrapScroll(View view) {
        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(UiKit.INSTANCE.color(this, R.color.bg));
        scroll.addView(view, new FrameLayout.LayoutParams(-1, -2));
        return scroll;
    }

    private final View buildPage1() {
        LinearLayout page = newPage();
        FrameLayout topFrame = new FrameLayout(this);
        topFrame.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, 1.0f));
        this.mascotView = new MascotView(this, null, 2, null);
        MascotView mascotView = this.mascotView;
        Intrinsics.checkNotNull(mascotView);
        mascotView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        MascotView mascotView2 = this.mascotView;
        Intrinsics.checkNotNull(mascotView2);
        mascotView2.setBlinkEnabled(false);
        MascotView mascotView3 = this.mascotView;
        Intrinsics.checkNotNull(mascotView3);
        mascotView3.setMascotListener(new MainActivity$buildPage1$1(this));
        topFrame.addView(this.mascotView);
        this.speechBubble = new TextView(this);
        TextView textView = this.speechBubble;
        Intrinsics.checkNotNull(textView);
        textView.setTextColor(UiKit.INSTANCE.color(this, R.color.ink));
        TextView textView2 = this.speechBubble;
        Intrinsics.checkNotNull(textView2);
        textView2.setTextSize(13.0f);
        TextView textView3 = this.speechBubble;
        Intrinsics.checkNotNull(textView3);
        textView3.setPadding(UiKit.INSTANCE.dp(this, 14.0f), UiKit.INSTANCE.dp(this, 9.0f), UiKit.INSTANCE.dp(this, 14.0f), UiKit.INSTANCE.dp(this, 9.0f));
        TextView textView4 = this.speechBubble;
        Intrinsics.checkNotNull(textView4);
        textView4.setVisibility(8);
        GradientDrawable bubbleBg = new GradientDrawable();
        bubbleBg.setColor(Color.argb(235, 255, 255, 255));
        bubbleBg.setCornerRadius(UiKit.INSTANCE.dp(this, 18.0f));
        bubbleBg.setStroke(1, UiKit.INSTANCE.color(this, R.color.line));
        TextView textView5 = this.speechBubble;
        Intrinsics.checkNotNull(textView5);
        textView5.setBackground(bubbleBg);
        TextView textView6 = this.speechBubble;
        Intrinsics.checkNotNull(textView6);
        textView6.setMaxWidth(UiKit.INSTANCE.dp(this, 240.0f));
        FrameLayout.LayoutParams bubbleLp = new FrameLayout.LayoutParams(-2, -2);
        bubbleLp.gravity = 49;
        bubbleLp.topMargin = UiKit.INSTANCE.dp(this, 14.0f);
        topFrame.addView(this.speechBubble, bubbleLp);
        this.companionTimeText = new TextView(this);
        TextView textView7 = this.companionTimeText;
        Intrinsics.checkNotNull(textView7);
        textView7.setTextColor(Color.argb(235, 255, 255, 255));
        TextView textView8 = this.companionTimeText;
        Intrinsics.checkNotNull(textView8);
        textView8.setTextSize(12.5f);
        TextView textView9 = this.companionTimeText;
        Intrinsics.checkNotNull(textView9);
        textView9.getPaint().setFakeBoldText(true);
        TextView textView10 = this.companionTimeText;
        Intrinsics.checkNotNull(textView10);
        textView10.setGravity(17);
        TextView textView11 = this.companionTimeText;
        Intrinsics.checkNotNull(textView11);
        textView11.setPadding(UiKit.INSTANCE.dp(this, 14.0f), UiKit.INSTANCE.dp(this, 5.0f), UiKit.INSTANCE.dp(this, 14.0f), UiKit.INSTANCE.dp(this, 5.0f));
        TextView textView12 = this.companionTimeText;
        Intrinsics.checkNotNull(textView12);
        textView12.setShadowLayer(3.0f, 0.0f, 1.0f, Color.argb(140, 0, 0, 0));
        GradientDrawable timeTagBg = new GradientDrawable();
        timeTagBg.setColor(Color.argb(70, 0, 0, 0));
        timeTagBg.setCornerRadius(UiKit.INSTANCE.dp(this, 14.0f));
        timeTagBg.setStroke(1, Color.argb(150, 255, 255, 255));
        TextView textView13 = this.companionTimeText;
        Intrinsics.checkNotNull(textView13);
        textView13.setBackground(timeTagBg);
        FrameLayout.LayoutParams timeLp = new FrameLayout.LayoutParams(-2, -2);
        timeLp.gravity = 81;
        timeLp.bottomMargin = UiKit.INSTANCE.dp(this, 10.0f);
        topFrame.addView(this.companionTimeText, timeLp);
        LinearLayout.LayoutParams topLp = new LinearLayout.LayoutParams(-1, 0, 1.0f);
        topLp.bottomMargin = UiKit.INSTANCE.dp(this, 10.0f);
        page.addView(topFrame, topLp);
        LinearLayout chatCard = UiKit.INSTANCE.card(this);
        LinearLayout inputRow = new LinearLayout(this);
        inputRow.setOrientation(0);
        inputRow.setGravity(16);
        this.chatInput = new EditText(this);
        EditText editText = this.chatInput;
        Intrinsics.checkNotNull(editText);
        editText.setHint("和小翻聊聊，例如：今天天气怎么样");
        EditText editText2 = this.chatInput;
        Intrinsics.checkNotNull(editText2);
        editText2.setInputType(1);
        EditText editText3 = this.chatInput;
        Intrinsics.checkNotNull(editText3);
        editText3.setSingleLine(true);
        EditText editText4 = this.chatInput;
        Intrinsics.checkNotNull(editText4);
        editText4.setTextSize(14.0f);
        EditText editText5 = this.chatInput;
        Intrinsics.checkNotNull(editText5);
        editText5.setTextColor(UiKit.INSTANCE.color(this, R.color.ink));
        EditText editText6 = this.chatInput;
        Intrinsics.checkNotNull(editText6);
        editText6.setHintTextColor(UiKit.INSTANCE.color(this, R.color.muted));
        EditText editText7 = this.chatInput;
        Intrinsics.checkNotNull(editText7);
        editText7.setPadding(UiKit.INSTANCE.dp(this, 14.0f), UiKit.INSTANCE.dp(this, 11.0f), UiKit.INSTANCE.dp(this, 14.0f), UiKit.INSTANCE.dp(this, 11.0f));
        GradientDrawable inputBg = new GradientDrawable();
        inputBg.setColor(UiKit.INSTANCE.color(this, R.color.bg));
        inputBg.setCornerRadius(UiKit.INSTANCE.dp(this, 22.0f));
        inputBg.setStroke(1, UiKit.INSTANCE.color(this, R.color.line));
        EditText editText8 = this.chatInput;
        Intrinsics.checkNotNull(editText8);
        editText8.setBackground(inputBg);
        inputRow.addView(this.chatInput, new LinearLayout.LayoutParams(0, -2, 1.0f));
        final TextView sendBtn = new TextView(this);
        sendBtn.setText("发送");
        sendBtn.setTextColor(-1);
        sendBtn.setTextSize(14.0f);
        sendBtn.getPaint().setFakeBoldText(true);
        sendBtn.setGravity(17);
        sendBtn.setPadding(UiKit.INSTANCE.dp(this, 18.0f), UiKit.INSTANCE.dp(this, 11.0f), UiKit.INSTANCE.dp(this, 18.0f), UiKit.INSTANCE.dp(this, 11.0f));
        GradientDrawable sendBg = new GradientDrawable();
        sendBg.setColor(UiKit.INSTANCE.color(this, R.color.brand));
        sendBg.setCornerRadius(UiKit.INSTANCE.dp(this, 22.0f));
        sendBtn.setBackground(sendBg);
        LinearLayout.LayoutParams sendLp = new LinearLayout.LayoutParams(-2, -2);
        sendLp.leftMargin = UiKit.INSTANCE.dp(this, 8.0f);
        sendBtn.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.buildPage1$lambda$5(MainActivity.this, view);
            }
        });
        sendBtn.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                boolean buildPage1$lambda$6;
                buildPage1$lambda$6 = MainActivity.buildPage1$lambda$6(MainActivity.this, sendBtn, view);
                return buildPage1$lambda$6;
            }
        });
        sendBtn.setOnTouchListener(new View.OnTouchListener() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean buildPage1$lambda$7;
                buildPage1$lambda$7 = MainActivity.buildPage1$lambda$7(MainActivity.this, view, motionEvent);
                return buildPage1$lambda$7;
            }
        });
        this.sendBtnRef = sendBtn;
        inputRow.addView(sendBtn, sendLp);
        chatCard.addView(inputRow);
        TextView dictateHint = UiKit.INSTANCE.bodyText(this, "长按“发送”按钮可语音转文字，识别后可在输入框修改，再短按发送给小翻。");
        dictateHint.setTextSize(11.5f);
        dictateHint.setPadding(UiKit.INSTANCE.dp(this, 4.0f), UiKit.INSTANCE.dp(this, 6.0f), UiKit.INSTANCE.dp(this, 4.0f), 0);
        chatCard.addView(dictateHint);
        page.addView(chatCard, UiKit.INSTANCE.margin(this, 0, 0, 0, UiKit.INSTANCE.dp(this, 10.0f)));
        LinearLayout profileStrip = UiKit.INSTANCE.card(this);
        profileStrip.setOrientation(0);
        profileStrip.setGravity(16);
        this.profileAvatar = new ImageView(this);
        ImageView imageView = this.profileAvatar;
        Intrinsics.checkNotNull(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageView imageView2 = this.profileAvatar;
        Intrinsics.checkNotNull(imageView2);
        applyAvatarTo(imageView2);
        GradientDrawable avatarBg = new GradientDrawable();
        avatarBg.setShape(1);
        avatarBg.setStroke(UiKit.INSTANCE.dp(this, 2.0f), UiKit.INSTANCE.color(this, R.color.brand));
        ImageView imageView3 = this.profileAvatar;
        Intrinsics.checkNotNull(imageView3);
        imageView3.setBackground(avatarBg);
        ImageView imageView4 = this.profileAvatar;
        Intrinsics.checkNotNull(imageView4);
        imageView4.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.buildPage1$lambda$8(MainActivity.this, view);
            }
        });
        ImageView imageView5 = this.profileAvatar;
        Intrinsics.checkNotNull(imageView5);
        imageView5.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                boolean buildPage1$lambda$9;
                buildPage1$lambda$9 = MainActivity.buildPage1$lambda$9(MainActivity.this, view);
                return buildPage1$lambda$9;
            }
        });
        profileStrip.addView(this.profileAvatar, new LinearLayout.LayoutParams(UiKit.INSTANCE.dp(this, 44.0f), UiKit.INSTANCE.dp(this, 44.0f)));
        LinearLayout nameCol = new LinearLayout(this);
        nameCol.setOrientation(1);
        nameCol.setPadding(UiKit.INSTANCE.dp(this, 12.0f), 0, 0, 0);
        this.profileNameText = new TextView(this);
        TextView textView14 = this.profileNameText;
        Intrinsics.checkNotNull(textView14);
        textView14.setTextColor(UiKit.INSTANCE.color(this, R.color.ink));
        TextView textView15 = this.profileNameText;
        Intrinsics.checkNotNull(textView15);
        textView15.setTextSize(15.5f);
        TextView textView16 = this.profileNameText;
        Intrinsics.checkNotNull(textView16);
        textView16.getPaint().setFakeBoldText(true);
        nameCol.addView(this.profileNameText);
        this.profileSubText = new TextView(this);
        TextView textView17 = this.profileSubText;
        Intrinsics.checkNotNull(textView17);
        textView17.setTextColor(UiKit.INSTANCE.color(this, R.color.muted));
        TextView textView18 = this.profileSubText;
        Intrinsics.checkNotNull(textView18);
        textView18.setTextSize(10.5f);
        TextView textView19 = this.profileSubText;
        Intrinsics.checkNotNull(textView19);
        textView19.setPadding(0, UiKit.INSTANCE.dp(this, 2.0f), 0, 0);
        nameCol.addView(this.profileSubText);
        profileStrip.addView(nameCol, new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView editName = new TextView(this);
        editName.setText("改称呼");
        editName.setTextColor(UiKit.INSTANCE.color(this, R.color.brand));
        editName.setTextSize(12.0f);
        editName.setPadding(UiKit.INSTANCE.dp(this, 10.0f), UiKit.INSTANCE.dp(this, 6.0f), UiKit.INSTANCE.dp(this, 4.0f), UiKit.INSTANCE.dp(this, 6.0f));
        editName.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.buildPage1$lambda$10(MainActivity.this, view);
            }
        });
        profileStrip.addView(editName);
        TextView dataEntry = new TextView(this);
        dataEntry.setText("数据存档 ›");
        dataEntry.setTextColor(UiKit.INSTANCE.color(this, R.color.muted));
        dataEntry.setTextSize(12.0f);
        dataEntry.setPadding(UiKit.INSTANCE.dp(this, 4.0f), UiKit.INSTANCE.dp(this, 6.0f), 0, UiKit.INSTANCE.dp(this, 6.0f));
        dataEntry.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.buildPage1$lambda$11(MainActivity.this, view);
            }
        });
        profileStrip.addView(dataEntry);
        page.addView(profileStrip);
        return page;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void buildPage1$lambda$5(MainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ClickFx.INSTANCE.play(this$0);
        this$0.sendChat();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean buildPage1$lambda$6(MainActivity this$0, TextView sendBtn, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(sendBtn, "$sendBtn");
        if (!it.hasFocus()) {
            it.requestFocus();
        }
        this$0.beginDictation(sendBtn);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean buildPage1$lambda$7(MainActivity this$0, View v, MotionEvent ev) {
        DictationController dictationController;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.dictating) {
            if ((ev.getActionMasked() == 1 || ev.getActionMasked() == 3) && (dictationController = this$0.dictate) != null) {
                dictationController.stop();
                return false;
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void buildPage1$lambda$8(MainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showAvatarChooser();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean buildPage1$lambda$9(MainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppPrefs.INSTANCE.setCustomAvatarUri(this$0, "");
        this$0.refreshAll();
        Toast.makeText(this$0, "已恢复默认头像", 0).show();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void buildPage1$lambda$10(MainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showNicknameDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void buildPage1$lambda$11(MainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, DataActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSpeechBubble(String text) {
        final TextView bubble = this.speechBubble;
        if (bubble == null) {
            return;
        }
        Runnable it = this.bubbleHideTask;
        if (it != null) {
            this.main.removeCallbacks(it);
        }
        bubble.setText(text);
        bubble.setAlpha(1.0f);
        bubble.setVisibility(0);
        Runnable fadeTask = new Runnable() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda27
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.showSpeechBubble$lambda$14(bubble);
            }
        };
        this.bubbleHideTask = fadeTask;
        this.main.postDelayed(fadeTask, 30000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showSpeechBubble$lambda$14(final TextView bubble) {
        Intrinsics.checkNotNullParameter(bubble, "$bubble");
        bubble.animate().alpha(0.0f).setDuration(800L).withEndAction(new Runnable() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda35
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.showSpeechBubble$lambda$14$lambda$13(bubble);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showSpeechBubble$lambda$14$lambda$13(TextView bubble) {
        Intrinsics.checkNotNullParameter(bubble, "$bubble");
        bubble.setVisibility(8);
    }

    private final View buildPage2() {
        LinearLayout page = newPage();
        LinearLayout header = new LinearLayout(this);
        header.setOrientation(0);
        header.setGravity(16);
        TextView title = new TextView(this);
        title.setText("功能");
        title.setTextColor(UiKit.INSTANCE.color(this, R.color.ink));
        title.setTextSize(24.0f);
        title.getPaint().setFakeBoldText(true);
        header.addView(title, new LinearLayout.LayoutParams(0, -2, 1.0f));
        this.nightBtn = new TextView(this);
        TextView textView = this.nightBtn;
        Intrinsics.checkNotNull(textView);
        textView.setTextColor(UiKit.INSTANCE.color(this, R.color.brand));
        TextView textView2 = this.nightBtn;
        Intrinsics.checkNotNull(textView2);
        textView2.setTextSize(18.0f);
        TextView textView3 = this.nightBtn;
        Intrinsics.checkNotNull(textView3);
        textView3.setPadding(UiKit.INSTANCE.dp(this, 8.0f), UiKit.INSTANCE.dp(this, 4.0f), UiKit.INSTANCE.dp(this, 8.0f), UiKit.INSTANCE.dp(this, 4.0f));
        TextView textView4 = this.nightBtn;
        Intrinsics.checkNotNull(textView4);
        textView4.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda29
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.buildPage2$lambda$15(MainActivity.this, view);
            }
        });
        header.addView(this.nightBtn);
        this.mascotAvatarBtn = new TextView(this);
        TextView textView5 = this.mascotAvatarBtn;
        Intrinsics.checkNotNull(textView5);
        textView5.setText("小翻形象");
        TextView textView6 = this.mascotAvatarBtn;
        Intrinsics.checkNotNull(textView6);
        textView6.setTextColor(UiKit.INSTANCE.color(this, R.color.brand));
        TextView textView7 = this.mascotAvatarBtn;
        Intrinsics.checkNotNull(textView7);
        textView7.setTextSize(13.0f);
        TextView textView8 = this.mascotAvatarBtn;
        Intrinsics.checkNotNull(textView8);
        textView8.setPadding(UiKit.INSTANCE.dp(this, 8.0f), UiKit.INSTANCE.dp(this, 6.0f), 0, UiKit.INSTANCE.dp(this, 6.0f));
        TextView textView9 = this.mascotAvatarBtn;
        Intrinsics.checkNotNull(textView9);
        textView9.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda30
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.buildPage2$lambda$16(MainActivity.this, view);
            }
        });
        header.addView(this.mascotAvatarBtn);
        page.addView(header, UiKit.INSTANCE.matchWrap());
        this.ballBtn = new TextView(this);
        TextView textView10 = this.ballBtn;
        Intrinsics.checkNotNull(textView10);
        textView10.setGravity(17);
        TextView textView11 = this.ballBtn;
        Intrinsics.checkNotNull(textView11);
        textView11.setTextColor(-1);
        TextView textView12 = this.ballBtn;
        Intrinsics.checkNotNull(textView12);
        textView12.setTextSize(17.0f);
        TextView textView13 = this.ballBtn;
        Intrinsics.checkNotNull(textView13);
        textView13.getPaint().setFakeBoldText(true);
        TextView textView14 = this.ballBtn;
        Intrinsics.checkNotNull(textView14);
        textView14.setPadding(UiKit.INSTANCE.dp(this, 16.0f), UiKit.INSTANCE.dp(this, 15.0f), UiKit.INSTANCE.dp(this, 16.0f), UiKit.INSTANCE.dp(this, 15.0f));
        GradientDrawable ballBg = new GradientDrawable();
        ballBg.setColor(UiKit.INSTANCE.color(this, R.color.brand));
        ballBg.setCornerRadius(UiKit.INSTANCE.dp(this, 16.0f));
        TextView textView15 = this.ballBtn;
        Intrinsics.checkNotNull(textView15);
        textView15.setBackground(ballBg);
        TextView textView16 = this.ballBtn;
        Intrinsics.checkNotNull(textView16);
        textView16.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda31
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.buildPage2$lambda$17(MainActivity.this, view);
            }
        });
        page.addView(this.ballBtn, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 14.0f), 0, UiKit.INSTANCE.dp(this, 12.0f)));
        String name = PrepActivity.class.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        page.addView(fullWidthCell("🛠", "前置准备", "悬浮窗 / 无障碍 / 麦克风 / 定位 / 后台白名单，全部权限一处开齐", name));
        String name2 = ReaderActivity.class.getName();
        Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
        page.addView(fullWidthCell("📖", "阅读翻页", "测速、翻页间隔、翻页方式、广告处理、书末页加速", name2));
        String name3 = BtRemoteActivity.class.getName();
        Intrinsics.checkNotNullExpressionValue(name3, "getName(...)");
        page.addView(fullWidthCell("📶", "蓝牙遥控", "两台设备一键蓝牙互联，用一台的音量键（熄屏可用）遥控另一台翻页，可互控", name3));
        String name4 = FunctionPanelActivity.class.getName();
        Intrinsics.checkNotNullExpressionValue(name4, "getName(...)");
        page.addView(fullWidthCell("⏰", "功能表", "一周天气、北京时间、闹钟", name4));
        String name5 = AssistantActivity.class.getName();
        Intrinsics.checkNotNullExpressionValue(name5, "getName(...)");
        page.addView(fullWidthCell("🎙", "小翻助手", "音色、AI 接口设置、按键音效", name5));
        String name6 = VoiceBrainActivity.class.getName();
        Intrinsics.checkNotNullExpressionValue(name6, "getName(...)");
        page.addView(fullWidthCell("🧠", "声控与大脑", "离线声控开关与口令、离线对话/识别模型、离线音色", name6));
        String name7 = GamesHubActivity.class.getName();
        Intrinsics.checkNotNullExpressionValue(name7, "getName(...)");
        page.addView(fullWidthCell("🎲", "休闲小游戏", "五子棋 / 中国象棋 / 围棋 / 斗地主，支持人机、同屏与安全联机", name7));
        String name8 = DataActivity.class.getName();
        Intrinsics.checkNotNullExpressionValue(name8, "getName(...)");
        page.addView(fullWidthCell("💾", "数据与存档", "使用统计、导出/恢复全部个性化数据，换机一键恢复", name8));
        return page;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void buildPage2$lambda$15(MainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showNightModeDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void buildPage2$lambda$16(MainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showMascotAvatarDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void buildPage2$lambda$17(MainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.toggleBall();
    }

    private final LinearLayout newPage() {
        LinearLayout page = new LinearLayout(this);
        page.setOrientation(1);
        int pad = UiKit.INSTANCE.dp(this, 18.0f);
        page.setPadding(pad, UiKit.INSTANCE.dp(this, 16.0f), pad, UiKit.INSTANCE.dp(this, 16.0f));
        return page;
    }

    private final View fullWidthCell(String emoji, String title, String desc, String target) {
        LinearLayout cell = new LinearLayout(this);
        cell.setOrientation(0);
        cell.setGravity(16);
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(UiKit.INSTANCE.color(this, R.color.card));
        bg.setCornerRadius(UiKit.INSTANCE.dp(this, 16.0f));
        bg.setStroke(1, UiKit.INSTANCE.color(this, R.color.line));
        cell.setBackground(bg);
        cell.setPadding(UiKit.INSTANCE.dp(this, 14.0f), UiKit.INSTANCE.dp(this, 13.0f), UiKit.INSTANCE.dp(this, 14.0f), UiKit.INSTANCE.dp(this, 13.0f));
        TextView emojiTv = new TextView(this);
        emojiTv.setText(emoji);
        emojiTv.setTextSize(24.0f);
        cell.addView(emojiTv, new LinearLayout.LayoutParams(UiKit.INSTANCE.dp(this, 38.0f), -2));
        LinearLayout col = new LinearLayout(this);
        col.setOrientation(1);
        TextView titleTv = new TextView(this);
        titleTv.setText(title);
        titleTv.setTextColor(UiKit.INSTANCE.color(this, R.color.ink));
        titleTv.setTextSize(16.0f);
        titleTv.getPaint().setFakeBoldText(true);
        col.addView(titleTv);
        TextView descTv = new TextView(this);
        descTv.setText(desc);
        descTv.setTextColor(UiKit.INSTANCE.color(this, R.color.muted));
        descTv.setTextSize(11.0f);
        descTv.setLineSpacing(0.0f, 1.3f);
        descTv.setPadding(0, UiKit.INSTANCE.dp(this, 3.0f), 0, 0);
        col.addView(descTv);
        cell.addView(col, new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView arrow = new TextView(this);
        arrow.setText("›");
        arrow.setTextColor(UiKit.INSTANCE.color(this, R.color.muted));
        arrow.setTextSize(22.0f);
        cell.addView(arrow);
        bindModuleClick(cell, target);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
        lp.bottomMargin = UiKit.INSTANCE.dp(this, 10.0f);
        cell.setLayoutParams(lp);
        return cell;
    }

    private final View moduleCell(String emoji, String title, String desc, String target) {
        LinearLayout cell = new LinearLayout(this);
        cell.setOrientation(1);
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(UiKit.INSTANCE.color(this, R.color.card));
        bg.setCornerRadius(UiKit.INSTANCE.dp(this, 16.0f));
        bg.setStroke(1, UiKit.INSTANCE.color(this, R.color.line));
        cell.setBackground(bg);
        cell.setPadding(UiKit.INSTANCE.dp(this, 14.0f), UiKit.INSTANCE.dp(this, 13.0f), UiKit.INSTANCE.dp(this, 14.0f), UiKit.INSTANCE.dp(this, 13.0f));
        TextView emojiTv = new TextView(this);
        emojiTv.setText(emoji);
        emojiTv.setTextSize(22.0f);
        cell.addView(emojiTv);
        TextView titleTv = new TextView(this);
        titleTv.setText(title);
        titleTv.setTextColor(UiKit.INSTANCE.color(this, R.color.ink));
        titleTv.setTextSize(15.5f);
        titleTv.getPaint().setFakeBoldText(true);
        titleTv.setPadding(0, UiKit.INSTANCE.dp(this, 6.0f), 0, 0);
        cell.addView(titleTv);
        TextView descTv = new TextView(this);
        descTv.setText(desc);
        descTv.setTextColor(UiKit.INSTANCE.color(this, R.color.muted));
        descTv.setTextSize(10.5f);
        descTv.setLineSpacing(0.0f, 1.25f);
        descTv.setPadding(0, UiKit.INSTANCE.dp(this, 3.0f), 0, 0);
        cell.addView(descTv);
        bindModuleClick(cell, target);
        return cell;
    }

    private final void bindModuleClick(View view, final String target) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MainActivity.bindModuleClick$lambda$18(MainActivity.this, target, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindModuleClick$lambda$18(MainActivity this$0, String target, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(target, "$target");
        ClickFx.INSTANCE.play(this$0);
        try {
            this$0.startActivity(new Intent(this$0, Class.forName(target)));
        } catch (Throwable th) {
            Toast.makeText(this$0, "模块打开失败", 0).show();
        }
    }

    private final void beginDictation(TextView btn) {
        if (this.dictating) {
            return;
        }
        if (checkSelfPermission("android.permission.RECORD_AUDIO") != 0) {
            requestPermissions(new String[]{"android.permission.RECORD_AUDIO"}, REQ_DICTATE);
        } else if (!OfflineModels.INSTANCE.isReady(this, OfflineModels.Kind.ASR)) {
            Toast.makeText(this, "语音识别模型还没下好，先到第三页下载离线模型", 1).show();
        } else {
            if (this.dictate == null) {
                Context applicationContext = getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                this.dictate = new DictationController(applicationContext);
                DictationController dictationController = this.dictate;
                Intrinsics.checkNotNull(dictationController);
                dictationController.setOnState(new Function1<DictationController.State, Unit>() { // from class: com.xiaofan.bangfan.MainActivity$beginDictation$1

                    /* compiled from: MainActivity.kt */
                    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                    /* loaded from: classes4.dex */
                    public /* synthetic */ class WhenMappings {
                        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                        static {
                            int[] iArr = new int[DictationController.State.values().length];
                            try {
                                iArr[DictationController.State.LISTENING.ordinal()] = 1;
                            } catch (NoSuchFieldError e) {
                            }
                            try {
                                iArr[DictationController.State.RECOGNIZING.ordinal()] = 2;
                            } catch (NoSuchFieldError e2) {
                            }
                            $EnumSwitchMapping$0 = iArr;
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DictationController.State state) {
                        invoke2(state);
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Code restructure failed: missing block: B:7:0x0019, code lost:
                        r1 = "发送";
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
                        r1 = "正在转文字…";
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:9:0x0023, code lost:
                        r1 = "松开发送文字";
                     */
                    /* renamed from: invoke  reason: avoid collision after fix types in other method */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final void invoke2(com.xiaofan.bangfan.DictationController.State r4) {
                        /*
                            r3 = this;
                            java.lang.String r0 = "st"
                            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                            com.xiaofan.bangfan.MainActivity r0 = com.xiaofan.bangfan.MainActivity.this
                            android.widget.TextView r0 = com.xiaofan.bangfan.MainActivity.access$getSendBtnRef$p(r0)
                            if (r0 != 0) goto Le
                            goto L2a
                        Le:
                            int[] r1 = com.xiaofan.bangfan.MainActivity$beginDictation$1.WhenMappings.$EnumSwitchMapping$0
                            int r2 = r4.ordinal()
                            r1 = r1[r2]
                            switch(r1) {
                                case 1: goto L23;
                                case 2: goto L1e;
                                default: goto L19;
                            }
                        L19:
                            java.lang.String r1 = "发送"
                            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                            goto L27
                        L1e:
                            java.lang.String r1 = "正在转文字…"
                            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                            goto L27
                        L23:
                            java.lang.String r1 = "松开发送文字"
                            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                        L27:
                            r0.setText(r1)
                        L2a:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.MainActivity$beginDictation$1.invoke2(com.xiaofan.bangfan.DictationController$State):void");
                    }
                });
                DictationController dictationController2 = this.dictate;
                Intrinsics.checkNotNull(dictationController2);
                dictationController2.setOnResult(new Function1<String, Unit>() { // from class: com.xiaofan.bangfan.MainActivity$beginDictation$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(String str) {
                        invoke2(str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke  reason: avoid collision after fix types in other method */
                    public final void invoke2(String txt) {
                        TextView textView;
                        EditText editText;
                        EditText editText2;
                        EditText editText3;
                        EditText editText4;
                        Editable text;
                        String obj;
                        MainActivity.this.dictating = false;
                        textView = MainActivity.this.sendBtnRef;
                        if (textView != null) {
                            textView.setText("发送");
                        }
                        String str = txt;
                        if (!(str == null || str.length() == 0)) {
                            editText = MainActivity.this.chatInput;
                            String cur = (editText == null || (text = editText.getText()) == null || (obj = text.toString()) == null) ? null : StringsKt.trim((CharSequence) obj).toString();
                            if (cur == null) {
                                cur = "";
                            }
                            editText2 = MainActivity.this.chatInput;
                            if (editText2 != null) {
                                editText2.setText(cur.length() == 0 ? txt : cur + " " + txt);
                            }
                            editText3 = MainActivity.this.chatInput;
                            if (editText3 != null) {
                                editText4 = MainActivity.this.chatInput;
                                Intrinsics.checkNotNull(editText4);
                                editText3.setSelection(editText4.getText().length());
                            }
                            Toast.makeText(MainActivity.this, "已转成文字，可修改后短按发送", 0).show();
                            return;
                        }
                        Toast.makeText(MainActivity.this, "没太听清，长按发送按钮再说一次", 0).show();
                    }
                });
            }
            DictationController dictationController3 = this.dictate;
            Intrinsics.checkNotNull(dictationController3);
            boolean ok = dictationController3.start();
            if (ok) {
                this.dictating = true;
                btn.setText("正在聆听…");
            }
        }
    }

    private final void sendChat() {
        EditText input = this.chatInput;
        if (input == null) {
            return;
        }
        String text = StringsKt.trim((CharSequence) input.getText().toString()).toString();
        if (text.length() == 0) {
            return;
        }
        input.setText("");
        appendChat("我", text);
        if (XiaoFanBrain.INSTANCE.isWakeWord(text)) {
            reply("我在，想问什么？天气、时间、定闹钟都可以。");
            return;
        }
        appendChat("小翻", "我想一想…");
        XiaoFanBrain.Intent parsed = XiaoFanBrain.INSTANCE.parse(text);
        if (parsed != null && !Intrinsics.areEqual("unknown", parsed.getType())) {
            XiaoFanBrain.INSTANCE.handle(this, parsed, new XiaoFanBrain.AnswerCallback() { // from class: com.xiaofan.bangfan.MainActivity$sendChat$1
                @Override // com.xiaofan.bangfan.XiaoFanBrain.AnswerCallback
                public void onSpeak(String msg) {
                    Intrinsics.checkNotNullParameter(msg, "msg");
                    if (msg.length() > 0) {
                        MainActivity.this.replaceLastXiaoFan(msg);
                        XiaoFanVoice.INSTANCE.tip(MainActivity.this, msg);
                    }
                }

                @Override // com.xiaofan.bangfan.XiaoFanBrain.AnswerCallback
                public void onAction(String action) {
                    Intrinsics.checkNotNullParameter(action, "action");
                    MainActivity.this.refreshAll();
                }
            });
        } else {
            answerOpenQuestion(text);
        }
    }

    private final void answerOpenQuestion(String text) {
        String endpoint = AppPrefs.INSTANCE.aiEndpoint(this);
        if (AppPrefs.INSTANCE.aiEnabled(this)) {
            if (endpoint.length() > 0) {
                answerByCloudThenLocal(text, endpoint);
                return;
            }
        }
        if (LocalBrain.INSTANCE.isReady(this)) {
            answerByLocalBrain(text);
        } else {
            externalFallback(text);
        }
    }

    private final void answerByCloudThenLocal(final String text, final String endpoint) {
        new Thread(new Runnable() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda34
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.answerByCloudThenLocal$lambda$21(endpoint, this, text);
            }
        }, "xf-chat-cloud").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void answerByCloudThenLocal$lambda$21(String endpoint, final MainActivity this$0, final String text) {
        final String cloud = "";
        Intrinsics.checkNotNullParameter(endpoint, "$endpoint");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(text, "$text");
        try {
            String askCloud = XiaoFanBrain.INSTANCE.askCloud(endpoint, AppPrefs.INSTANCE.aiApiKey(this$0), text);
            String obj = askCloud != null ? StringsKt.trim((CharSequence) askCloud).toString() : null;
            if (obj != null) {
                cloud = obj;
            }
        } catch (Throwable th) {
            Log.w("XFCHAT", "cloud failed, fallback local", th);
        }
        this$0.main.post(new Runnable() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda33
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.answerByCloudThenLocal$lambda$21$lambda$20(cloud, this$0, text);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void answerByCloudThenLocal$lambda$21$lambda$20(String cloud, final MainActivity this$0, String text) {
        Intrinsics.checkNotNullParameter(cloud, "$cloud");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(text, "$text");
        if (cloud.length() > 0) {
            this$0.replaceLastXiaoFan(cloud);
            XiaoFanVoice.INSTANCE.tip(this$0, cloud);
            MascotView mascotView = this$0.mascotView;
            if (mascotView != null) {
                mascotView.setSpeaking(true);
            }
            this$0.main.postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda25
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.answerByCloudThenLocal$lambda$21$lambda$20$lambda$19(MainActivity.this);
                }
            }, Math.min((long) AppPrefs.FALLBACK_READ_MS, cloud.length() * 220));
        } else if (LocalBrain.INSTANCE.isReady(this$0)) {
            this$0.answerByLocalBrain(text);
        } else {
            this$0.externalFallback(text);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void answerByCloudThenLocal$lambda$21$lambda$20$lambda$19(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MascotView mascotView = this$0.mascotView;
        if (mascotView != null) {
            mascotView.setSpeaking(false);
        }
    }

    private final void answerByLocalBrain(final String text) {
        new Thread(new Runnable() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.answerByLocalBrain$lambda$27(MainActivity.this, text);
            }
        }, "xf-chat-local").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void answerByLocalBrain$lambda$27(final MainActivity this$0, final String text) {
        final String ans;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(text, "$text");
        Log.i("XFCHAT", "answerByLocalBrain start ready=" + LocalBrain.INSTANCE.isReady(this$0));
        this$0.main.post(new Runnable() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda18
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.answerByLocalBrain$lambda$27$lambda$22(MainActivity.this);
            }
        });
        try {
            boolean warmed = LocalBrain.INSTANCE.loadBlocking(this$0, 30000L);
            Log.i("XFCHAT", "warmed=" + warmed);
            if (!warmed) {
                this$0.main.post(new Runnable() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda19
                    @Override // java.lang.Runnable
                    public final void run() {
                        MainActivity.answerByLocalBrain$lambda$27$lambda$23(MainActivity.this, text);
                    }
                });
                return;
            }
            StringBuilder sb = new StringBuilder();
            String generate = LocalBrain.INSTANCE.generate(text, new MainActivity$answerByLocalBrain$1$ans$1(sb, this$0));
            if (generate != null) {
                ans = StringsKt.trim((CharSequence) generate).toString();
            } else {
                ans = null;
            }
            if (ans == null) {
                ans = "";
            }
            this$0.main.post(new Runnable() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda21
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.answerByLocalBrain$lambda$27$lambda$25(ans, this$0, text);
                }
            });
        } finally {
            this$0.main.post(new Runnable() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda20
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.answerByLocalBrain$lambda$27$lambda$26(MainActivity.this);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void answerByLocalBrain$lambda$27$lambda$22(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.replaceLastXiaoFan("我想一想…");
        MascotView mascotView = this$0.mascotView;
        if (mascotView != null) {
            mascotView.setCpuRenderPaused(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void answerByLocalBrain$lambda$27$lambda$23(MainActivity this$0, String text) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(text, "$text");
        this$0.externalFallback(text);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void answerByLocalBrain$lambda$27$lambda$25(String ans, final MainActivity this$0, String text) {
        Intrinsics.checkNotNullParameter(ans, "$ans");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(text, "$text");
        if (ans.length() == 0) {
            this$0.externalFallback(text);
            return;
        }
        this$0.replaceLastXiaoFan(ans);
        XiaoFanVoice.INSTANCE.tip(this$0, ans);
        MascotView mascotView = this$0.mascotView;
        if (mascotView != null) {
            mascotView.setSpeaking(true);
        }
        this$0.main.postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda15
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.answerByLocalBrain$lambda$27$lambda$25$lambda$24(MainActivity.this);
            }
        }, Math.min((long) AppPrefs.FALLBACK_READ_MS, ans.length() * 220));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void answerByLocalBrain$lambda$27$lambda$25$lambda$24(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MascotView mascotView = this$0.mascotView;
        if (mascotView != null) {
            mascotView.setSpeaking(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void answerByLocalBrain$lambda$27$lambda$26(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MascotView mascotView = this$0.mascotView;
        if (mascotView != null) {
            mascotView.setCpuRenderPaused(false);
        }
    }

    private final void externalFallback(String text) {
        if (AppPrefs.INSTANCE.aiEndpoint(this).length() > 0) {
            XiaoFanBrain.Intent unknown = new XiaoFanBrain.Intent();
            unknown.setType("unknown");
            unknown.setRawText(text);
            XiaoFanBrain.INSTANCE.handle(this, unknown, new XiaoFanBrain.AnswerCallback() { // from class: com.xiaofan.bangfan.MainActivity$externalFallback$1
                @Override // com.xiaofan.bangfan.XiaoFanBrain.AnswerCallback
                public void onAction(String action) {
                    Intrinsics.checkNotNullParameter(action, "action");
                }

                @Override // com.xiaofan.bangfan.XiaoFanBrain.AnswerCallback
                public void onSpeak(String msg) {
                    Intrinsics.checkNotNullParameter(msg, "msg");
                    if (msg.length() > 0) {
                        MainActivity.this.replaceLastXiaoFan(msg);
                        XiaoFanVoice.INSTANCE.tip(MainActivity.this, msg);
                    }
                }
            });
        } else if (DoubaoBridge.INSTANCE.isInstalled(this)) {
            String handoff = DoubaoBridge.INSTANCE.handoff(this, text);
            replaceLastXiaoFan(handoff);
            XiaoFanVoice.INSTANCE.tip(this, "这个我帮你转到豆包了");
        } else if (QwenBridge.INSTANCE.isInstalled(this)) {
            String handoff2 = QwenBridge.INSTANCE.handoff(this, text);
            replaceLastXiaoFan(handoff2);
            XiaoFanVoice.INSTANCE.tip(this, "这个我帮你转到千问了");
        } else {
            String builtin = builtinChatReply(text);
            if (builtin != null) {
                replaceLastXiaoFan(builtin);
                XiaoFanVoice.INSTANCE.tip(this, builtin);
                return;
            }
            replaceLastXiaoFan("这个我还答不了。你可以装个豆包或千问 APP，我帮你把问题转过去；或者到「小翻助手」里填你自己的大模型接口，我就能直接答了。");
            XiaoFanVoice.INSTANCE.tip(this, "这个我还答不了。你可以装个豆包或千问 APP，我帮你把问题转过去；或者到「小翻助手」里填你自己的大模型接口，我就能直接答了。");
        }
    }

    private final String builtinChatReply(String text) {
        String obj = StringsKt.trim((CharSequence) text).toString();
        Locale CHINA = Locale.CHINA;
        Intrinsics.checkNotNullExpressionValue(CHINA, "CHINA");
        String t = obj.toLowerCase(CHINA);
        Intrinsics.checkNotNullExpressionValue(t, "toLowerCase(...)");
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) "你好", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "您好", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "hi", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "hello", false, 2, (Object) null)) {
            return "你好呀！我是小翻，你的阅读小助手。有什么我能帮你的吗？";
        }
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) "谢谢", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "感谢", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) XiaoFanBrain.INTENT_THANKS, false, 2, (Object) null)) {
            return "不客气！能帮到你我很开心，有需要随时叫我。";
        }
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) "你是谁", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "你叫什么", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "介绍", false, 2, (Object) null)) {
            return "我是小翻助手，专门帮你自动翻页、报天气、定闹钟。我还能陪你聊天哦！";
        }
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) "你会什么", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "功能", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "能做什么", false, 2, (Object) null)) {
            return "我会：自动翻页（按你的阅读速度）、广告跳过、天气查询、闹钟提醒、语音播报。你还可以在设置里配置大模型接口，让我更聪明。";
        }
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) "笑话", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "讲个", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "搞笑", false, 2, (Object) null)) {
            return "有一天小明问妈妈：妈妈，我是不是傻孩子？妈妈说：傻孩子，你怎么会是傻孩子呢～";
        }
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) "天气", false, 2, (Object) null)) {
            return "你可以直接问我「今天天气怎么样」或者「北京天气」，我会帮你查的！";
        }
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) "时间", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "几点", false, 2, (Object) null)) {
            return "现在是" + XiaoFanBrain.INSTANCE.speakTimeNow();
        } else if (StringsKt.contains$default((CharSequence) t, (CharSequence) "再见", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "拜拜", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "bye", false, 2, (Object) null)) {
            return "再见啦！需要翻页随时叫我哦～";
        } else {
            return null;
        }
    }

    private final void openQwen(String text) {
        if (!QwenBridge.INSTANCE.open(this, text)) {
            Toast.makeText(this, "打不开千问，请确认已安装或用浏览器访问 qianwen.com", 1).show();
            return;
        }
        if (text.length() > 0) {
            Toast.makeText(this, "问题已复制，进千问后长按输入框粘贴即可", 1).show();
        }
    }

    private final void appendChat(String sender, String msg) {
        LinearLayout log = this.chatLog;
        if (log == null) {
            return;
        }
        boolean isMe = Intrinsics.areEqual("我", sender);
        LinearLayout bubble = new LinearLayout(this);
        bubble.setOrientation(1);
        bubble.setTag("chat");
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(UiKit.INSTANCE.color(this, isMe ? R.color.brand : R.color.bg));
        bg.setCornerRadius(UiKit.INSTANCE.dp(this, 14.0f));
        bubble.setBackground(bg);
        int pad = UiKit.INSTANCE.dp(this, 12.0f);
        bubble.setPadding(pad, UiKit.INSTANCE.dp(this, 9.0f), pad, UiKit.INSTANCE.dp(this, 9.0f));
        TextView msgTv = new TextView(this);
        msgTv.setText(msg);
        msgTv.setTextColor(isMe ? -1 : UiKit.INSTANCE.color(this, R.color.ink));
        msgTv.setTextSize(13.5f);
        msgTv.setLineSpacing(0.0f, 1.35f);
        bubble.addView(msgTv);
        TextView senderTv = new TextView(this);
        senderTv.setText(sender);
        senderTv.setTextColor(isMe ? -855638017 : UiKit.INSTANCE.color(this, R.color.muted));
        senderTv.setTextSize(10.0f);
        senderTv.setPadding(0, UiKit.INSTANCE.dp(this, 3.0f), 0, 0);
        bubble.addView(senderTv);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, -2);
        lp.gravity = isMe ? GravityCompat.END : GravityCompat.START;
        lp.bottomMargin = UiKit.INSTANCE.dp(this, 7.0f);
        msgTv.setMaxWidth((int) (getResources().getDisplayMetrics().widthPixels * 0.78f));
        log.addView(bubble, lp);
        while (log.getChildCount() > 40) {
            log.removeViewAt(0);
        }
    }

    private final void reply(String msg) {
        appendChat("小翻", msg);
        XiaoFanVoice.INSTANCE.tip(this, msg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void replaceLastXiaoFan(String msg) {
        showSpeechBubble(msg);
        LinearLayout log = this.chatLog;
        if (log == null) {
            appendChat("小翻", msg);
            return;
        }
        for (int i = log.getChildCount() - 1; -1 < i; i--) {
            View child = log.getChildAt(i);
            if ((child instanceof LinearLayout) && ((LinearLayout) child).getChildCount() >= 2) {
                View sender = ((LinearLayout) child).getChildAt(1);
                if ((sender instanceof TextView) && Intrinsics.areEqual("小翻", ((TextView) sender).getText())) {
                    View msgView = ((LinearLayout) child).getChildAt(0);
                    if (msgView instanceof TextView) {
                        ((TextView) msgView).setText(msg);
                        return;
                    }
                }
            }
        }
        appendChat("小翻", msg);
    }

    private final void showNicknameDialog() {
        final EditText input = new EditText(this);
        input.setHint("例如：主人");
        input.setInputType(1);
        input.setSingleLine(true);
        input.setText(AppPrefs.INSTANCE.nickname(this));
        input.setSelection(input.getText().length());
        input.setPadding(UiKit.INSTANCE.dp(this, 16.0f), UiKit.INSTANCE.dp(this, 12.0f), UiKit.INSTANCE.dp(this, 16.0f), UiKit.INSTANCE.dp(this, 12.0f));
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(1);
        container.setPadding(UiKit.INSTANCE.dp(this, 20.0f), UiKit.INSTANCE.dp(this, 8.0f), UiKit.INSTANCE.dp(this, 20.0f), 0);
        container.addView(UiKit.INSTANCE.bodyText(this, "我会用这个称呼跟你说话。"));
        container.addView(input, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 12.0f), 0, 0));
        new AlertDialog.Builder(this).setTitle("我的称呼").setView(container).setPositiveButton("保存", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.showNicknameDialog$lambda$28(MainActivity.this, input, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showNicknameDialog$lambda$28(MainActivity this$0, EditText input, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(input, "$input");
        this$0.applyNickname(StringsKt.trim((CharSequence) input.getText().toString()).toString());
    }

    private final void applyNickname(String name) {
        String n = name;
        if (StringsKt.equals(AppPrefs.PLANNER_CODE, n, true)) {
            AppPrefs.INSTANCE.setPlanner(this, true);
            AppPrefs.INSTANCE.setNickname(this, "策划");
            AppPrefs.INSTANCE.markProfileDone(this);
            afterProfileChanged();
            XiaoFanVoice.INSTANCE.tip(this, "策划身份已激活，欢迎回来");
            return;
        }
        if (n.length() == 0) {
            n = AppPrefs.DEFAULT_NICKNAME;
        }
        if (isReservedPlannerName(n)) {
            new AlertDialog.Builder(this).setTitle("无法使用").setMessage(R.string.planner_locked).setPositiveButton("知道了", (DialogInterface.OnClickListener) null).show();
            return;
        }
        if (AppPrefs.INSTANCE.isPlanner(this)) {
            AppPrefs.INSTANCE.setPlanner(this, false);
        }
        AppPrefs.INSTANCE.setNickname(this, n);
        AppPrefs.INSTANCE.markProfileDone(this);
        afterProfileChanged();
        XiaoFanVoice.INSTANCE.tip(this, "好啦，以后我就叫你" + AppPrefs.INSTANCE.nickname(this));
    }

    private final boolean isReservedPlannerName(String name) {
        String str = name;
        if (str == null || str.length() == 0) {
            return false;
        }
        String obj = StringsKt.trim((CharSequence) name).toString();
        Locale US = Locale.US;
        Intrinsics.checkNotNullExpressionValue(US, "US");
        String lowerCase = obj.toLowerCase(US);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String n = StringsKt.replace$default(lowerCase, " ", "", false, 4, (Object) null);
        if (n.length() == 0) {
            return false;
        }
        return StringsKt.contains$default((CharSequence) n, (CharSequence) "策划", false, 2, (Object) null) || Intrinsics.areEqual(n, "cehua") || Intrinsics.areEqual(n, "cehuar") || Intrinsics.areEqual(n, "planner");
    }

    private final void showIdentityDialog(boolean first) {
        final EditText input = new EditText(this);
        input.setHint("例如：主人");
        input.setInputType(1);
        input.setSingleLine(true);
        input.setText(AppPrefs.INSTANCE.nickname(this));
        input.setSelection(input.getText().length());
        input.setPadding(UiKit.INSTANCE.dp(this, 16.0f), UiKit.INSTANCE.dp(this, 12.0f), UiKit.INSTANCE.dp(this, 16.0f), UiKit.INSTANCE.dp(this, 12.0f));
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(1);
        container.setPadding(UiKit.INSTANCE.dp(this, 20.0f), UiKit.INSTANCE.dp(this, 8.0f), UiKit.INSTANCE.dp(this, 20.0f), 0);
        container.addView(UiKit.INSTANCE.bodyText(this, first ? "先给自己设个称呼吧，之后我就这么叫你。\n头像和小翻形象稍后都能在「我的」里改。" : "修改称呼"));
        container.addView(input, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 12.0f), 0, 0));
        new AlertDialog.Builder(this).setTitle(first ? "认识一下" : "我的称呼").setView(container).setPositiveButton("保存", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda16
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.showIdentityDialog$lambda$30(MainActivity.this, input, dialogInterface, i);
            }
        }).setNegativeButton(first ? "稍后再说" : "取消", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showIdentityDialog$lambda$30(final MainActivity this$0, EditText input, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(input, "$input");
        this$0.applyNickname(StringsKt.trim((CharSequence) input.getText().toString()).toString());
        if (TextUtils.isEmpty(AppPrefs.INSTANCE.role(this$0))) {
            this$0.main.postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda17
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.showIdentityDialog$lambda$30$lambda$29(MainActivity.this);
                }
            }, 500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showIdentityDialog$lambda$30$lambda$29(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppPrefs.INSTANCE.setRole(this$0, AppPrefs.ROLE_READER);
        this$0.refreshAll();
    }

    private final void showMascotAvatarDialog() {
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(1);
        char c = 0;
        container.setPadding(UiKit.INSTANCE.dp(this, 20.0f), UiKit.INSTANCE.dp(this, 8.0f), UiKit.INSTANCE.dp(this, 20.0f), 0);
        container.addView(UiKit.INSTANCE.bodyText(this, "选一个小翻形象，它会用在悬浮球与开屏页上。"));
        final ImageView preview = new ImageView(this);
        preview.setImageResource(avatarRes(AppPrefs.INSTANCE.avatarIndex(this)));
        LinearLayout.LayoutParams previewLp = new LinearLayout.LayoutParams(UiKit.INSTANCE.dp(this, 96.0f), UiKit.INSTANCE.dp(this, 96.0f));
        previewLp.gravity = 17;
        previewLp.topMargin = UiKit.INSTANCE.dp(this, 10.0f);
        container.addView(preview, previewLp);
        final int[] selected = {AppPrefs.INSTANCE.avatarIndex(this)};
        final LinearLayout thumbs = new LinearLayout(this);
        thumbs.setOrientation(0);
        float f = 4.0f;
        thumbs.setPadding(0, UiKit.INSTANCE.dp(this, 12.0f), 0, UiKit.INSTANCE.dp(this, 4.0f));
        int i = 1;
        while (i < 9) {
            ImageView thumb = new ImageView(this);
            thumb.setImageResource(avatarRes(i));
            thumb.setPadding(UiKit.INSTANCE.dp(this, f), UiKit.INSTANCE.dp(this, f), UiKit.INSTANCE.dp(this, f), UiKit.INSTANCE.dp(this, f));
            thumb.setAlpha(selected[c] == i ? 1.0f : 0.45f);
            final int i2 = i;
            thumb.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda11
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MainActivity.showMascotAvatarDialog$lambda$31(selected, i2, preview, this, thumbs, view);
                }
            });
            LinearLayout.LayoutParams thumbLp = new LinearLayout.LayoutParams(UiKit.INSTANCE.dp(this, 56.0f), UiKit.INSTANCE.dp(this, 56.0f));
            thumbLp.rightMargin = UiKit.INSTANCE.dp(this, 8.0f);
            thumbs.addView(thumb, thumbLp);
            i++;
            c = 0;
            f = 4.0f;
        }
        HorizontalScrollView hScroll = new HorizontalScrollView(this);
        hScroll.setHorizontalScrollBarEnabled(false);
        hScroll.addView(thumbs);
        container.addView(hScroll, UiKit.INSTANCE.matchWrap());
        ScrollView scroll = new ScrollView(this);
        scroll.addView(container);
        new AlertDialog.Builder(this).setTitle("小翻形象").setView(scroll).setPositiveButton("保存", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda22
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i3) {
                MainActivity.showMascotAvatarDialog$lambda$32(MainActivity.this, selected, dialogInterface, i3);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMascotAvatarDialog$lambda$31(int[] selected, int $i, ImageView preview, MainActivity this$0, LinearLayout thumbs, View it) {
        Intrinsics.checkNotNullParameter(selected, "$selected");
        Intrinsics.checkNotNullParameter(preview, "$preview");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(thumbs, "$thumbs");
        selected[0] = $i;
        preview.setImageResource(this$0.avatarRes($i));
        int childCount = thumbs.getChildCount();
        for (int j = 0; j < childCount; j++) {
            thumbs.getChildAt(j).setAlpha(j + 1 == $i ? 1.0f : 0.45f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMascotAvatarDialog$lambda$32(MainActivity this$0, int[] selected, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(selected, "$selected");
        AppPrefs.INSTANCE.setAvatarIndex(this$0, selected[0]);
        this$0.afterProfileChanged();
    }

    private final void showAvatarChooser() {
        if (!UiKit.INSTANCE.hasPerm(this, "android.permission.READ_EXTERNAL_STORAGE") && Build.VERSION.SDK_INT < 33) {
            requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, REQ_PICK_IMAGE);
        } else {
            pickImageFromGallery();
        }
    }

    private final void pickImageFromGallery() {
        try {
            Intent intent = new Intent("android.intent.action.PICK");
            intent.setType("image/*");
            intent.addFlags(1);
            startActivityForResult(intent, REQ_PICK_IMAGE);
        } catch (Throwable th) {
            try {
                Intent intent2 = new Intent("android.intent.action.GET_CONTENT");
                intent2.setType("image/*");
                intent2.addCategory("android.intent.category.OPENABLE");
                startActivityForResult(intent2, REQ_PICK_IMAGE);
            } catch (Throwable th2) {
                Toast.makeText(this, "这台设备没有可用的相册应用", 0).show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaofan.bangfan.BaseActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri uri;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != REQ_PICK_IMAGE || resultCode != -1 || data == null || (uri = data.getData()) == null) {
            return;
        }
        try {
            getContentResolver().takePersistableUriPermission(uri, 1);
        } catch (Throwable th) {
        }
        AppPrefs.INSTANCE.setCustomAvatarUri(this, uri.toString());
        refreshAll();
        Toast.makeText(this, "头像已更新", 0).show();
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean z = true;
        int[] iArr = null;
        if (requestCode != REQ_DICTATE) {
            if (requestCode != REQ_PICK_IMAGE) {
                return;
            }
            int length = grantResults.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    int element$iv = grantResults[i];
                    if ((element$iv == 0 ? 1 : 0) != 0) {
                        break;
                    }
                    i++;
                } else {
                    z = false;
                    break;
                }
            }
            boolean granted = z;
            if (granted) {
                pickImageFromGallery();
                return;
            } else {
                Toast.makeText(this, "没有相册权限，会继续用默认头像", 0).show();
                return;
            }
        }
        int length2 = grantResults.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length2) {
                break;
            }
            int element$iv2 = grantResults[i2];
            if ((element$iv2 == 0 ? 1 : 0) != 0) {
                iArr = 1;
                break;
            }
            i2++;
        }
        int[] $this$any$iv = iArr;
        if ($this$any$iv == null) {
            Toast.makeText(this, "未授予麦克风权限，无法语音转文字", 1).show();
            return;
        }
        TextView it = this.sendBtnRef;
        if (it != null) {
            beginDictation(it);
        }
    }

    private final void applyAvatarTo(ImageView imageView) {
        if (imageView == null) {
            return;
        }
        String uri = AppPrefs.INSTANCE.customAvatarUri(this);
        if (uri.length() > 0) {
            try {
                InputStream stream = getContentResolver().openInputStream(Uri.parse(uri));
                if (stream != null) {
                    Bitmap bitmap = BitmapFactory.decodeStream(stream);
                    stream.close();
                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                        return;
                    }
                }
            } catch (Throwable th) {
            }
        }
        imageView.setImageResource(R.mipmap.ic_launcher);
    }

    private final void afterProfileChanged() {
        FloatBallService companion;
        refreshAll();
        if (!FloatBallService.Companion.isRunning() || (companion = FloatBallService.Companion.getInstance()) == null) {
            return;
        }
        companion.refreshFromPrefs();
    }

    private final int avatarRes(int index) {
        int resId = getResources().getIdentifier(AppPrefs.INSTANCE.avatarResName(index), "drawable", getPackageName());
        return resId != 0 ? resId : R.mipmap.ic_launcher;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String pickGreeting() {
        return new String[]{"我在呀", "需要帮忙吗", "问天气还是定闹钟？", "翻页交给我", "长按我可以打开功能表哦", "打字问我也可以"}[(int) (Math.random() * 6)];
    }

    private final void toggleBall() {
        if (FloatBallService.Companion.isRunning()) {
            stopService(new Intent(this, FloatBallService.class));
            this.main.postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda23
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.toggleBall$lambda$36(MainActivity.this);
                }
            }, 350L);
        } else if (!UiKit.INSTANCE.canOverlay(this)) {
            Toast.makeText(this, "请先在「前置准备」里开启悬浮窗权限", 1).show();
            UiKit.INSTANCE.openOverlaySettings(this);
        } else if (!UiKit.INSTANCE.isA11yEnabled(this)) {
            new AlertDialog.Builder(this).setTitle("需要无障碍服务").setMessage("小翻靠无障碍服务来帮你翻页、读屏、拦截音量键。\n开启后系统会提示「可访问你的屏幕内容」——这是对所有无障碍服务的标准提示，小翻只在本机识别页面，不上传任何内容。").setPositiveButton("去开启", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda24
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity.toggleBall$lambda$37(MainActivity.this, dialogInterface, i);
                }
            }).setNegativeButton("先不开", (DialogInterface.OnClickListener) null).show();
        } else if (!AppPrefs.INSTANCE.profileDone(this)) {
            showIdentityDialog(true);
        } else {
            startBallService();
            Toast.makeText(this, "悬浮球已启动：3 秒不碰会自动缩到边框", 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void toggleBall$lambda$36(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.refreshAll();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void toggleBall$lambda$37(MainActivity this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UiKit.INSTANCE.openA11ySettings(this$0);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        HorizontalPager p = this.pager;
        if (p != null && p.getCurrentPage() > 0) {
            p.showPage(p.getCurrentPage() - 1);
        } else {
            super.onBackPressed();
        }
    }

    private final void startPeriodicRefresh() {
        stopPeriodicRefresh();
        Runnable runnable = new Runnable() { // from class: com.xiaofan.bangfan.MainActivity$startPeriodicRefresh$task$1
            @Override // java.lang.Runnable
            public void run() {
                Handler handler;
                MainActivity.this.refreshAll();
                handler = MainActivity.this.main;
                handler.postDelayed(this, 2500L);
            }
        };
        this.refreshTask = runnable;
        this.main.postDelayed(runnable, 2500L);
    }

    private final void stopPeriodicRefresh() {
        Runnable it = this.refreshTask;
        if (it != null) {
            this.main.removeCallbacks(it);
        }
        this.refreshTask = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshAll() {
        FloatBallService companion;
        boolean isPlanner = AppPrefs.INSTANCE.isPlanner(this);
        TextView textView = this.identityText;
        if (textView != null) {
            textView.setText(isPlanner ? "策划 · 小翻" : "用户" + AppPrefs.INSTANCE.nickname(this) + "的小翻");
        }
        MascotView mascotView = this.mascotView;
        if (mascotView != null) {
            mascotView.applyVisualState(this);
        }
        MascotView mascotView2 = this.mascotView;
        if (mascotView2 != null) {
            mascotView2.setNightGlow(isNightUi());
        }
        long j = 0;
        if (FloatBallService.Companion.isRunning() && (companion = FloatBallService.Companion.getInstance()) != null) {
            j = companion.liveCompanionSeconds();
        }
        long liveBall = j;
        long totalCompanion = AppPrefs.INSTANCE.companionSeconds(this) + BaseActivity.Companion.appSessionSeconds() + liveBall;
        TextView textView2 = this.companionText;
        if (textView2 != null) {
            textView2.setText(getString(R.string.companion_prefix) + " " + fmtSeconds(totalCompanion));
        }
        TextView textView3 = this.companionTimeText;
        if (textView3 != null) {
            textView3.setText("陪伴 " + fmtSeconds(totalCompanion));
        }
        TextView textView4 = this.ballBtn;
        if (textView4 != null) {
            textView4.setText(getString(FloatBallService.Companion.isRunning() ? R.string.btn_stop_ball : R.string.btn_start_ball));
        }
        TextView textView5 = this.quickStatus;
        if (textView5 != null) {
            textView5.setText(buildQuickStatus());
        }
        TextView textView6 = this.nightBtn;
        if (textView6 != null) {
            textView6.setText(isNightUi() ? "☀" : "☾");
        }
        applyAvatarTo(this.profileAvatar);
        TextView textView7 = this.profileNameText;
        if (textView7 != null) {
            textView7.setText(AppPrefs.INSTANCE.nickname(this));
        }
        TextView textView8 = this.profileSubText;
        if (textView8 == null) {
            return;
        }
        textView8.setText(isPlanner ? "策划身份 · 金色胸灯标识" : "点头像换照片 · 长按恢复默认");
    }

    private final void showNightModeDialog() {
        int state = AppPrefs.INSTANCE.nightModeState(this);
        new AlertDialog.Builder(this).setTitle("夜间模式").setSingleChoiceItems(new String[]{"日间模式", "夜间模式", "跟随系统（默认）"}, state, new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda28
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.showNightModeDialog$lambda$39(MainActivity.this, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showNightModeDialog$lambda$39(MainActivity this$0, DialogInterface dialogInterface, int which) {
        int i;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppPrefs appPrefs = AppPrefs.INSTANCE;
        MainActivity mainActivity = this$0;
        switch (which) {
            case 0:
                i = 0;
                break;
            case 1:
                i = 1;
                break;
            default:
                i = 2;
                break;
        }
        appPrefs.setNightModeState(mainActivity, i);
        this$0.refreshAll();
        this$0.recreate();
    }

    private final String fmtSeconds(long seconds) {
        long s = seconds < 0 ? 0L : seconds;
        long j = 60;
        long minutes = s / j;
        long j2 = 1440;
        long days = minutes / j2;
        long hours = (minutes % j2) / j;
        long mins = minutes % j;
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

    private final String buildQuickStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append(FloatBallService.Companion.isRunning() ? "● 悬浮球运行中" : "○ 悬浮球未启动");
        sb.append(AppPrefs.INSTANCE.autoTurn(this) ? " · 自动翻页开" : " · 自动翻页关");
        int missing = countMissingPerms();
        if (missing > 0) {
            sb.append(" · " + missing + " 项权限待开");
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    private final int countMissingPerms() {
        int count = !UiKit.INSTANCE.canOverlay(this);
        if (!UiKit.INSTANCE.isA11yEnabled(this)) {
            count++;
        }
        return !UiKit.INSTANCE.hasPerm(this, "android.permission.RECORD_AUDIO") ? count + 1 : count;
    }

    @Override // com.xiaofan.bangfan.TurnManager.Listener
    public void onAutoTurnChanged(boolean on) {
        postRefresh();
    }

    @Override // com.xiaofan.bangfan.TurnManager.Listener
    public void onPresenceChanged(boolean present) {
        postRefresh();
    }

    @Override // com.xiaofan.bangfan.TurnManager.Listener
    public void onTimedTurnChanged(boolean running, long interval) {
        postRefresh();
    }

    @Override // com.xiaofan.bangfan.TurnManager.Listener
    public void onServiceReady(boolean ready) {
        postRefresh();
    }

    private final void postRefresh() {
        this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.MainActivity$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.postRefresh$lambda$40(MainActivity.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void postRefresh$lambda$40(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isFinishing()) {
            return;
        }
        this$0.refreshAll();
    }
}
