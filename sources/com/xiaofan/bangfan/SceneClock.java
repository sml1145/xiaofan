package com.xiaofan.bangfan;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
/* compiled from: SceneClock.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/xiaofan/bangfan/SceneClock;", "", "()V", "SCENE_DAY", "", "SCENE_MOON", "SCENE_STARS", "SCENE_SUNSET", "sceneForMinutes", "minutes", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class SceneClock {
    public static final SceneClock INSTANCE = new SceneClock();
    public static final int SCENE_DAY = 0;
    public static final int SCENE_MOON = 2;
    public static final int SCENE_STARS = 3;
    public static final int SCENE_SUNSET = 1;

    private SceneClock() {
    }

    public final int sceneForMinutes(int minutes) {
        boolean z = false;
        if (360 <= minutes && minutes < 1020) {
            return 0;
        }
        if (1020 <= minutes && minutes < 1140) {
            z = true;
        }
        if (z) {
            return 1;
        }
        if (minutes >= 1140 || minutes <= 119) {
            return 2;
        }
        return 3;
    }
}
