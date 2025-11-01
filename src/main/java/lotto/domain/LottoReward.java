package lotto.domain;

public enum LottoReward {
    FIRST(6, false, 200000000),
    SECOND(5, true, 30000000),
    THIRD(5, false, 1500000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000),
    NONE(0, false, 0);

    private final int count;
    private final boolean bonus;
    private final int value;

    LottoReward(int count, boolean bonus, int value) {
        this.count = count;
        this.bonus = bonus;
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public boolean isBonus() {
        return bonus;
    }

    public int getValue() {
        return value;
    }

    public static LottoReward rank(int matchCount, boolean bonus) {
        if (matchCount == 6) {
            return FIRST;
        }
        if (matchCount == 5 && bonus) {
            return SECOND;
        }
        if (matchCount == 5) {
            return THIRD;
        }
        if (matchCount == 4) {
            return FOURTH;
        }
        if (matchCount == 3) {
            return THIRD;
        }
        return NONE;
    }
}
