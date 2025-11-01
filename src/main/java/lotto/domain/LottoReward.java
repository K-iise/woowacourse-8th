package lotto.domain;

public enum LottoReward {
    FIRST(6, false, 200000000),
    SECOND(5, true, 30000000),
    THIRD(5, false, 1500000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000);

    private final int rank;
    private final boolean bonus;
    private final int value;

    LottoReward(int rank, boolean bonus, int value) {
        this.rank = rank;
        this.bonus = bonus;
        this.value = value;
    }

    public int getRank() {
        return rank;
    }

    public boolean isBonus() {
        return bonus;
    }

    public int getValue() {
        return value;
    }
}
