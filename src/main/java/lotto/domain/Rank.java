package lotto.domain;

public enum Rank {
    FIFTH(3, false, 5000),
    FOURTH(4, false, 50000),
    THIRD(5, false, 1500000),
    SECOND(5, true, 30000000),
    FIRST(6, false, 2000000000),
    MISS(0, false, 0);

    private final int count;
    private final boolean bonus;
    private final int value;

    Rank(int count, boolean bonus, int value) {
        this.count = count;
        this.bonus = bonus;
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public int getValue() {
        return value;
    }

    public static Rank rank(int matchCount, boolean bonus) {
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
            return FIFTH;
        }
        return MISS;
    }
}
