package com.xiaofan.bangfan;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.BluetoothRemoteService;
import com.xiaofan.bangfan.UiKit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: BtRemoteActivity.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 42\u00020\u00012\u00020\u0002:\u00014B\u0005¢\u0006\u0002\u0010\u0003J\n\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002J\u0013\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002¢\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00020\t2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\u0016\u0010\u0015\u001a\u00020\t2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\"\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\u001a\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\u001a2\b\u0010 \u001a\u0004\u0018\u00010\u0011H\u0016J\u0012\u0010!\u001a\u00020\t2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0014J\b\u0010$\u001a\u00020\tH\u0014J-\u0010%\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u001a2\u000e\u0010&\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110\u00102\u0006\u0010'\u001a\u00020(H\u0016¢\u0006\u0002\u0010)J\b\u0010*\u001a\u00020\tH\u0014J\b\u0010+\u001a\u00020\tH\u0002J\u001a\u0010,\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\u001a2\b\u0010-\u001a\u0004\u0018\u00010\u0011H\u0002J\b\u0010.\u001a\u00020\tH\u0002J\u0010\u0010/\u001a\u00020\t2\u0006\u00100\u001a\u000201H\u0002J\u0010\u00102\u001a\u00020\t2\u0006\u00103\u001a\u00020\u0011H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/xiaofan/bangfan/BtRemoteActivity;", "Lcom/xiaofan/bangfan/BaseActivity;", "Lcom/xiaofan/bangfan/BluetoothRemoteService$Listener;", "()V", "disconnectBtn", "Landroid/widget/TextView;", "pairBtn", "pendingAfterEnable", "Lkotlin/Function0;", "", "scaffold", "Landroid/widget/ScrollView;", "statusLine", "adapter", "Landroid/bluetooth/BluetoothAdapter;", "btPerms", "", "", "()[Ljava/lang/String;", "ensureBtThen", "action", "ensurePermsThen", "hasBtPerms", "", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onBtState", "state", "peerName", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onRequestPermissionsResult", "permissions", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "pickAndPair", "render", "peer", "requestIgnoreBatteryOnce", "startPair", "dev", "Landroid/bluetooth/BluetoothDevice;", "toast", "t", "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class BtRemoteActivity extends BaseActivity implements BluetoothRemoteService.Listener {
    public static final Companion Companion = new Companion(null);
    private static final int REQ_BT_ENABLE = 4214;
    private static final int REQ_BT_PERMS = 4213;
    private TextView disconnectBtn;
    private TextView pairBtn;
    private Function0<Unit> pendingAfterEnable;
    private ScrollView scaffold;
    private TextView statusLine;

    /* compiled from: BtRemoteActivity.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/xiaofan/bangfan/BtRemoteActivity$Companion;", "", "()V", "REQ_BT_ENABLE", "", "REQ_BT_PERMS", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView sc = UiKit.INSTANCE.scaffold(this, "蓝牙遥控", "两台手机先在系统蓝牙里互相配对（只需一次），然后两台都点下面同一个“连接设备”按钮、选中对方，小翻会自动建立连接，无需区分主机/从机。连上后用一台的音量键遥控另一台翻页，熄屏也能用，且两台可互相控制。");
        this.scaffold = sc;
        LinearLayout content = UiKit.INSTANCE.contentOf(sc);
        this.statusLine = UiKit.INSTANCE.bodyText(this, "");
        content.addView(this.statusLine, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 4.0f), 0, UiKit.INSTANCE.dp(this, 10.0f)));
        LinearLayout conn = UiKit.INSTANCE.card(this);
        conn.addView(UiKit.INSTANCE.cardTitle(this, "建立连接"));
        this.pairBtn = UiKit.INSTANCE.button(this, "连接设备", true, new View.OnClickListener() { // from class: com.xiaofan.bangfan.BtRemoteActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BtRemoteActivity.onCreate$lambda$0(BtRemoteActivity.this, view);
            }
        });
        conn.addView(this.pairBtn, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 12.0f), 0, UiKit.INSTANCE.dp(this, 8.0f)));
        this.disconnectBtn = UiKit.INSTANCE.button(this, "断开连接", false, new View.OnClickListener() { // from class: com.xiaofan.bangfan.BtRemoteActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BtRemoteActivity.onCreate$lambda$1(BtRemoteActivity.this, view);
            }
        });
        conn.addView(this.disconnectBtn, UiKit.INSTANCE.margin(this, 0, 0, 0, UiKit.INSTANCE.dp(this, 8.0f)));
        TextView sysBtn = UiKit.INSTANCE.button(this, "打开系统蓝牙（配对/开关）", false, new View.OnClickListener() { // from class: com.xiaofan.bangfan.BtRemoteActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BtRemoteActivity.onCreate$lambda$2(BtRemoteActivity.this, view);
            }
        });
        conn.addView(sysBtn, UiKit.INSTANCE.margin(this, 0, 0, 0, 0));
        content.addView(conn, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 10.0f), 0, 0));
        LinearLayout set = UiKit.INSTANCE.card(this);
        set.addView(UiKit.INSTANCE.cardTitle(this, "遥控设置"));
        set.addView(UiKit.INSTANCE.switchRow(this, "用音量键遥控对方", "连接后本机音量-下一页、音量+上一页，熄屏/锁屏也能遥控", new UiKit.Getter() { // from class: com.xiaofan.bangfan.BtRemoteActivity$$ExternalSyntheticLambda5
            @Override // com.xiaofan.bangfan.UiKit.Getter
            public final Object get() {
                boolean onCreate$lambda$3;
                onCreate$lambda$3 = BtRemoteActivity.onCreate$lambda$3(BtRemoteActivity.this);
                return Boolean.valueOf(onCreate$lambda$3);
            }
        }, new UiKit.Setter() { // from class: com.xiaofan.bangfan.BtRemoteActivity$$ExternalSyntheticLambda6
            @Override // com.xiaofan.bangfan.UiKit.Setter
            public final void set(boolean z) {
                BtRemoteActivity.onCreate$lambda$4(BtRemoteActivity.this, z);
            }
        }), UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 6.0f), 0, 0));
        set.addView(UiKit.INSTANCE.divider(this));
        set.addView(UiKit.INSTANCE.switchRow(this, "被控时语音播报", "本机被对方遥控翻页时，小翻念一句提示（默认关）", new UiKit.Getter() { // from class: com.xiaofan.bangfan.BtRemoteActivity$$ExternalSyntheticLambda7
            @Override // com.xiaofan.bangfan.UiKit.Getter
            public final Object get() {
                boolean onCreate$lambda$5;
                onCreate$lambda$5 = BtRemoteActivity.onCreate$lambda$5(BtRemoteActivity.this);
                return Boolean.valueOf(onCreate$lambda$5);
            }
        }, new UiKit.Setter() { // from class: com.xiaofan.bangfan.BtRemoteActivity$$ExternalSyntheticLambda8
            @Override // com.xiaofan.bangfan.UiKit.Setter
            public final void set(boolean z) {
                BtRemoteActivity.onCreate$lambda$6(BtRemoteActivity.this, z);
            }
        }));
        content.addView(set, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 10.0f), 0, 0));
        LinearLayout tip = UiKit.INSTANCE.card(this);
        tip.addView(UiKit.INSTANCE.cardTitle(this, "使用步骤"));
        tip.addView(UiKit.INSTANCE.bodyText(this, "1. 两台手机在系统设置→蓝牙里先完成配对（只需一次）。\n2. 两台都打开本页，都点“连接设备”，在弹出列表里选中对方（只有一台已配对设备会自动选择）。\n3. 稍等 1~3 秒，状态变成“已连接”即成功；两台会自动分工，不用区分谁主谁从。\n4. 手持端按音量-翻下一页、音量+回上一页，熄屏也能用；通道双向，反过来也能控制。\n5. 被控端需要开启小翻的无障碍服务才能真正翻页（和自动翻页同一套，在“前置准备”里开）。\n6. 不用时点“断开连接”，音量键即恢复本机原功能。"));
        content.addView(tip, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 10.0f), 0, 0));
        setContentView(sc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(final BtRemoteActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.ensurePermsThen(new Function0<Unit>() { // from class: com.xiaofan.bangfan.BtRemoteActivity$onCreate$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                BtRemoteActivity btRemoteActivity = BtRemoteActivity.this;
                final BtRemoteActivity btRemoteActivity2 = BtRemoteActivity.this;
                btRemoteActivity.ensureBtThen(new Function0<Unit>() { // from class: com.xiaofan.bangfan.BtRemoteActivity$onCreate$1$1.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke  reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        BtRemoteActivity.this.pickAndPair();
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(BtRemoteActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BluetoothRemoteService.Companion.disconnect(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$2(BtRemoteActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            this$0.startActivity(new Intent("android.settings.BLUETOOTH_SETTINGS"));
        } catch (Throwable th) {
            try {
                this$0.startActivity(new Intent("android.settings.WIRELESS_SETTINGS"));
            } catch (Throwable th2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$3(BtRemoteActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return AppPrefs.INSTANCE.btVolumeRemote(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$4(BtRemoteActivity this$0, boolean v) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppPrefs.INSTANCE.setBtVolumeRemote(this$0, v);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$5(BtRemoteActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return AppPrefs.INSTANCE.btRecvTip(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$6(BtRemoteActivity this$0, boolean v) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppPrefs.INSTANCE.setBtRecvTip(this$0, v);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaofan.bangfan.BaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        BluetoothRemoteService.Companion.setListener(this);
        render(BluetoothRemoteService.Companion.stateNow(), BluetoothRemoteService.Companion.peerNow());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaofan.bangfan.BaseActivity, android.app.Activity
    public void onPause() {
        BluetoothRemoteService.Companion.setListener(null);
        super.onPause();
    }

    @Override // com.xiaofan.bangfan.BluetoothRemoteService.Listener
    public void onBtState(int state, String peerName) {
        render(state, peerName);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x004f, code lost:
        r1 = "状态：正在和另一台小翻建立连接…两台都点了“连接设备”后稍候";
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x000a, code lost:
        r1 = "状态：未连接";
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void render(int r5, java.lang.String r6) {
        /*
            r4 = this;
            android.widget.TextView r0 = r4.statusLine
            if (r0 != 0) goto L5
            return
        L5:
            java.lang.String r1 = "对方"
            switch(r5) {
                case 1: goto L4f;
                case 2: goto L2f;
                case 3: goto Lf;
                default: goto La;
            }
        La:
            java.lang.String r1 = "状态：未连接"
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            goto L53
        Lf:
            if (r6 != 0) goto L12
            goto L13
        L12:
            r1 = r6
        L13:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "状态：已连接 "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r2 = " ✓ 音量-下一页 / 音量+上一页，熄屏可用"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            goto L53
        L2f:
            if (r6 != 0) goto L32
            goto L33
        L32:
            r1 = r6
        L33:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "状态：正在连接 "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r2 = "…"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            goto L53
        L4f:
            java.lang.String r1 = "状态：正在和另一台小翻建立连接…两台都点了“连接设备”后稍候"
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
        L53:
            r0.setText(r1)
            com.xiaofan.bangfan.UiKit r1 = com.xiaofan.bangfan.UiKit.INSTANCE
            r2 = r4
            android.app.Activity r2 = (android.app.Activity) r2
            r3 = 3
            if (r5 != r3) goto L61
            int r3 = com.xiaofan.bangfan.R.color.ok
            goto L63
        L61:
            int r3 = com.xiaofan.bangfan.R.color.muted
        L63:
            int r1 = r1.color(r2, r3)
            r0.setTextColor(r1)
            android.widget.TextView r1 = r4.disconnectBtn
            if (r1 != 0) goto L6f
            goto L78
        L6f:
            if (r5 == 0) goto L73
            r2 = 0
            goto L75
        L73:
            r2 = 8
        L75:
            r1.setVisibility(r2)
        L78:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.BtRemoteActivity.render(int, java.lang.String):void");
    }

    private final BluetoothAdapter adapter() {
        Object systemService = getSystemService("bluetooth");
        BluetoothManager bluetoothManager = systemService instanceof BluetoothManager ? (BluetoothManager) systemService : null;
        if (bluetoothManager != null) {
            return bluetoothManager.getAdapter();
        }
        return null;
    }

    private final String[] btPerms() {
        if (Build.VERSION.SDK_INT >= 31) {
            return new String[]{"android.permission.BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_SCAN"};
        }
        return new String[0];
    }

    private final boolean hasBtPerms() {
        String[] ps = btPerms();
        int length = ps.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                return true;
            }
            if (!(checkSelfPermission(ps[i]) == 0)) {
                return false;
            }
            i++;
        }
    }

    private final void ensurePermsThen(Function0<Unit> function0) {
        if (hasBtPerms()) {
            function0.invoke();
            return;
        }
        requestPermissions(btPerms(), REQ_BT_PERMS);
        this.pendingAfterEnable = function0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void ensureBtThen(Function0<Unit> function0) {
        BluetoothAdapter a = adapter();
        if (a == null) {
            toast("这台设备不支持蓝牙");
        } else if (a.isEnabled()) {
            function0.invoke();
        } else {
            this.pendingAfterEnable = function0;
            try {
                startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), REQ_BT_ENABLE);
            } catch (Throwable th) {
                try {
                    startActivity(new Intent("android.settings.BLUETOOTH_SETTINGS"));
                } catch (Throwable th2) {
                }
            }
        }
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_BT_PERMS) {
            boolean z = false;
            if (!(grantResults.length == 0)) {
                int length = grantResults.length;
                int i = 0;
                while (true) {
                    if (i < length) {
                        int element$iv = grantResults[i];
                        int it = element$iv == 0 ? 1 : 0;
                        if (it == 0) {
                            break;
                        }
                        i++;
                    } else {
                        z = true;
                        break;
                    }
                }
                if (z) {
                    Function0<Unit> function0 = this.pendingAfterEnable;
                    if (function0 != null) {
                        function0.invoke();
                    }
                    this.pendingAfterEnable = null;
                }
            }
            toast("需要蓝牙权限才能双机遥控");
            this.pendingAfterEnable = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaofan.bangfan.BaseActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_BT_ENABLE) {
            if (resultCode == -1) {
                Function0<Unit> function0 = this.pendingAfterEnable;
                if (function0 != null) {
                    function0.invoke();
                }
            } else {
                toast("没打开蓝牙，无法连接");
            }
            this.pendingAfterEnable = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void pickAndPair() {
        List bonded;
        BluetoothAdapter a = adapter();
        if (a == null) {
            return;
        }
        try {
            Set<BluetoothDevice> bondedDevices = a.getBondedDevices();
            if (bondedDevices == null || (bonded = CollectionsKt.toList(bondedDevices)) == null) {
                bonded = CollectionsKt.emptyList();
            }
        } catch (Throwable th) {
            bonded = CollectionsKt.emptyList();
        }
        if (bonded.isEmpty()) {
            new AlertDialog.Builder(this).setTitle("没有已配对设备").setMessage("先在系统蓝牙里把两台手机配对，再回来点“连接设备”。现在去配对吗？").setPositiveButton("去配对", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.BtRemoteActivity$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    BtRemoteActivity.pickAndPair$lambda$9(BtRemoteActivity.this, dialogInterface, i);
                }
            }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
            return;
        }
        Iterable $this$sortedBy$iv = bonded;
        final List sorted = CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: com.xiaofan.bangfan.BtRemoteActivity$pickAndPair$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                BluetoothDevice it = (BluetoothDevice) t;
                String name = it.getName();
                if (name == null) {
                    name = it.getAddress();
                }
                String str = name;
                BluetoothDevice it2 = (BluetoothDevice) t2;
                String name2 = it2.getName();
                if (name2 == null) {
                    name2 = it2.getAddress();
                }
                return ComparisonsKt.compareValues(str, name2);
            }
        });
        if (sorted.size() == 1) {
            startPair((BluetoothDevice) sorted.get(0));
            return;
        }
        List $this$map$iv = sorted;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            BluetoothDevice it = (BluetoothDevice) item$iv$iv;
            String name = it.getName();
            if (name == null) {
                name = "未命名设备";
            } else {
                Intrinsics.checkNotNull(name);
            }
            destination$iv$iv.add(name + "\n" + it.getAddress());
            bonded = bonded;
        }
        Collection $this$toTypedArray$iv = (List) destination$iv$iv;
        String[] labels = (String[]) $this$toTypedArray$iv.toArray(new String[0]);
        new AlertDialog.Builder(this).setTitle("选择要连接的另一台小翻（两台都要点连接设备）").setItems(labels, new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.BtRemoteActivity$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                BtRemoteActivity.pickAndPair$lambda$12(BtRemoteActivity.this, sorted, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void pickAndPair$lambda$9(BtRemoteActivity this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            this$0.startActivity(new Intent("android.settings.BLUETOOTH_SETTINGS"));
        } catch (Throwable th) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void pickAndPair$lambda$12(BtRemoteActivity this$0, List sorted, DialogInterface dialogInterface, int which) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(sorted, "$sorted");
        this$0.startPair((BluetoothDevice) sorted.get(which));
    }

    private final void startPair(BluetoothDevice dev) {
        AppPrefs.INSTANCE.setBtLastPeer(this, dev.getAddress());
        requestIgnoreBatteryOnce();
        String address = dev.getAddress();
        Intrinsics.checkNotNullExpressionValue(address, "getAddress(...)");
        BluetoothRemoteService.Companion.pair(this, address);
    }

    private final void requestIgnoreBatteryOnce() {
        try {
            if (AppPrefs.INSTANCE.btBatteryHintShown(this)) {
                return;
            }
            AppPrefs.INSTANCE.setBtBatteryHintShown(this, true);
            Object systemService = getSystemService("power");
            PowerManager pm = systemService instanceof PowerManager ? (PowerManager) systemService : null;
            if (pm == null || pm.isIgnoringBatteryOptimizations(getPackageName())) {
                return;
            }
            Intent intent = new Intent("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        } catch (Throwable th) {
        }
    }

    private final void toast(String t) {
        Toast.makeText(this, t, 0).show();
    }
}
