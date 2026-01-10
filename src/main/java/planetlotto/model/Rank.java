package planetlotto.model;

public enum Rank {
    FIRST(1, 5, false),
    SECOND(2, 4, true),
    THIRD(3, 4, false),
    FOURTH(4, 3, true),
    FIFTH(5, 2, true),
    MISS(0, 0, false);

    Rank(int ranking, int count, boolean bonus) {
        this.ranking = ranking;
        this.count = count;
        this.bonus = bonus;
    }

    private int ranking;
    private int count;
    private boolean bonus;

    public static Rank rank(int matchCount, boolean bonus){
        if (matchCount == 5) {
            return FIRST;
        }
        if (matchCount == 4 && bonus) {
            return SECOND;
        }
        if (matchCount == 4 && !bonus) {
            return THIRD;
        }
        if (matchCount == 3 && bonus) {
            return FOURTH;
        }
        if (matchCount == 2 && bonus) {
            return FIFTH;
        }
        return MISS;
    }

    public int getRanking() {
        return ranking;
    }
}
