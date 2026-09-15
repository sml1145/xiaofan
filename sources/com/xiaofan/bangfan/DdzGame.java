package com.xiaofan.bangfan;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.DdzRules;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: DdzGame.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010%\u001a\u0004\u0018\u00010\u00182\u0006\u0010&\u001a\u00020\u0005J\u000e\u0010'\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\u0005J\u000e\u0010(\u001a\u00020\r2\u0006\u0010&\u001a\u00020\u0005J\u000e\u0010)\u001a\u00020\r2\u0006\u0010&\u001a\u00020\u0005J\u000e\u0010*\u001a\u00020\r2\u0006\u0010&\u001a\u00020\u0005J\u001c\u0010+\u001a\u00020\r2\u0006\u0010&\u001a\u00020\u00052\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00050-J\u000e\u0010.\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020\u0005J\u0016\u0010/\u001a\u0002002\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u000502H\u0002J\u001e\u00103\u001a\u0002002\u0006\u00104\u001a\u00020\u00052\u000e\b\u0002\u00105\u001a\b\u0012\u0004\u0012\u00020\u000502R!\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\n\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u000e\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\r@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R)\u0010\u0011\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u00060\u0012¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0016\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\fR\"\u0010\u0019\u001a\u0004\u0018\u00010\u00182\b\u0010\t\u001a\u0004\u0018\u00010\u0018@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001e\u0010\u001c\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\fR!\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020\u001f0\u0004j\b\u0012\u0004\u0012\u00020\u001f`\u0006¢\u0006\b\n\u0000\u001a\u0004\b \u0010\bR\u001e\u0010!\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\fR\u001e\u0010#\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\f¨\u00066"}, d2 = {"Lcom/xiaofan/bangfan/DdzGame;", "", "()V", "bottom", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getBottom", "()Ljava/util/ArrayList;", "<set-?>", "current", "getCurrent", "()I", "", "gameOver", "getGameOver", "()Z", "hands", "", "getHands", "()[Ljava/util/ArrayList;", "[Ljava/util/ArrayList;", "landlord", "getLandlord", "Lcom/xiaofan/bangfan/DdzRules$Combo;", "lastCombo", "getLastCombo", "()Lcom/xiaofan/bangfan/DdzRules$Combo;", "lastPlayer", "getLastPlayer", "log", "", "getLog", "passCount", "getPassCount", "winnerSide", "getWinnerSide", "aiDecide", "seat", "handSize", "isFarmer", "isLandlord", "pass", "play", "cards", "", "sideName", "sortInPlace", "", "l", "", "start", "landlordSeat", "deck", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class DdzGame {
    private int current;
    private boolean gameOver;
    private int landlord;
    private DdzRules.Combo lastCombo;
    private int passCount;
    private final ArrayList<Integer>[] hands = {new ArrayList<>(), new ArrayList<>(), new ArrayList<>()};
    private final ArrayList<Integer> bottom = new ArrayList<>();
    private int lastPlayer = -1;
    private int winnerSide = -1;
    private final ArrayList<String> log = new ArrayList<>();

    public final ArrayList<Integer>[] getHands() {
        return this.hands;
    }

    public final ArrayList<Integer> getBottom() {
        return this.bottom;
    }

    public final int getLandlord() {
        return this.landlord;
    }

    public final int getCurrent() {
        return this.current;
    }

    public final DdzRules.Combo getLastCombo() {
        return this.lastCombo;
    }

    public final int getLastPlayer() {
        return this.lastPlayer;
    }

    public final int getPassCount() {
        return this.passCount;
    }

    public final boolean getGameOver() {
        return this.gameOver;
    }

    public final int getWinnerSide() {
        return this.winnerSide;
    }

    public final ArrayList<String> getLog() {
        return this.log;
    }

    public final boolean isLandlord(int seat) {
        return seat == this.landlord;
    }

    public final boolean isFarmer(int seat) {
        return seat != this.landlord;
    }

    public final int handSize(int seat) {
        return this.hands[seat].size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void start$default(DdzGame ddzGame, int i, List list, int i2, Object obj) {
        List<Integer> list2 = list;
        if ((i2 & 2) != 0) {
            list2 = DdzRules.INSTANCE.shuffledDeck();
        }
        ddzGame.start(i, list2);
    }

    public final void start(int landlordSeat, List<Integer> deck) {
        Intrinsics.checkNotNullParameter(deck, "deck");
        for (int i = 0; i < 3; i++) {
            this.hands[i].clear();
        }
        this.bottom.clear();
        this.log.clear();
        for (int i2 = 0; i2 < 51; i2++) {
            this.hands[i2 % 3].add(deck.get(i2));
        }
        for (int i3 = 51; i3 < 54; i3++) {
            this.bottom.add(deck.get(i3));
        }
        this.landlord = landlordSeat;
        this.hands[this.landlord].addAll(this.bottom);
        for (int i4 = 0; i4 < 3; i4++) {
            sortInPlace(this.hands[i4]);
        }
        int i5 = this.landlord;
        this.current = i5;
        this.lastCombo = null;
        this.lastPlayer = -1;
        this.passCount = 0;
        this.gameOver = false;
        this.winnerSide = -1;
    }

    private final void sortInPlace(List<Integer> list) {
        List s = DdzRules.INSTANCE.sorted(list);
        list.clear();
        list.addAll(s);
    }

    public final boolean play(int seat, List<Integer> cards) {
        DdzRules.Combo combo;
        Intrinsics.checkNotNullParameter(cards, "cards");
        if (this.gameOver || seat != this.current || !this.hands[seat].containsAll(cards) || (combo = DdzRules.INSTANCE.recognize(cards)) == null) {
            return false;
        }
        boolean needBeat = this.lastPlayer >= 0 && this.lastPlayer != seat;
        if (!needBeat || DdzRules.INSTANCE.canBeat(combo, this.lastCombo)) {
            this.hands[seat].removeAll(CollectionsKt.toSet(cards));
            sortInPlace(this.hands[seat]);
            this.lastCombo = combo;
            this.lastPlayer = seat;
            this.passCount = 0;
            this.current = (this.current + 1) % 3;
            this.log.add("seat" + seat + " 出牌 " + CollectionsKt.joinToString$default(cards, ",", null, null, 0, null, new Function1<Integer, CharSequence>() { // from class: com.xiaofan.bangfan.DdzGame$play$1
                public final CharSequence invoke(int it) {
                    return DdzRules.INSTANCE.label(it);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ CharSequence invoke(Integer num) {
                    return invoke(num.intValue());
                }
            }, 30, null));
            if (this.hands[seat].isEmpty()) {
                this.gameOver = true;
                this.winnerSide = seat != this.landlord ? 1 : 0;
            }
            return true;
        }
        return false;
    }

    public final boolean pass(int seat) {
        if (this.gameOver || seat != this.current || this.lastPlayer < 0 || this.lastPlayer == seat) {
            return false;
        }
        this.passCount++;
        this.current = (this.current + 1) % 3;
        this.log.add("seat" + seat + " 不要");
        if (this.passCount >= 2) {
            this.current = this.lastPlayer;
            this.lastCombo = null;
            this.lastPlayer = -1;
            this.passCount = 0;
        }
        return true;
    }

    public final DdzRules.Combo aiDecide(int seat) {
        DdzRules.Combo bomb;
        if (seat != this.current || this.gameOver) {
            return null;
        }
        List hand = CollectionsKt.toList(this.hands[seat]);
        boolean urgentOpponent = true;
        boolean leading = this.lastPlayer < 0 || this.lastPlayer == seat;
        if (leading) {
            return DdzRules.INSTANCE.chooseLead(hand);
        }
        DdzRules ddzRules = DdzRules.INSTANCE;
        DdzRules.Combo combo = this.lastCombo;
        Intrinsics.checkNotNull(combo);
        DdzRules.Combo beat = ddzRules.findBeat(hand, combo);
        int leader = this.lastPlayer;
        boolean iAmLandlord = seat == this.landlord;
        boolean leaderIsPartner = (iAmLandlord || leader == this.landlord) ? false : true;
        if (!leaderIsPartner || this.hands[leader].size() > 2) {
            if (beat != null) {
                return beat;
            }
            int i = this.landlord;
            if (!iAmLandlord ? leader != i : leader == i) {
                urgentOpponent = false;
            }
            if (urgentOpponent && this.hands[leader].size() <= 3 && (bomb = DdzRules.findBomb$default(DdzRules.INSTANCE, hand, 0, 2, null)) != null) {
                DdzRules.Combo combo2 = this.lastCombo;
                Intrinsics.checkNotNull(combo2);
                if (combo2.getType() == 11 && bomb.getType() != 12) {
                    int mainRank = bomb.getMainRank();
                    DdzRules.Combo combo3 = this.lastCombo;
                    Intrinsics.checkNotNull(combo3);
                    if (mainRank > combo3.getMainRank()) {
                    }
                }
                return bomb;
            }
            return null;
        }
        return null;
    }

    public final String sideName(int seat) {
        return seat == this.landlord ? "地主" : "农民";
    }
}
