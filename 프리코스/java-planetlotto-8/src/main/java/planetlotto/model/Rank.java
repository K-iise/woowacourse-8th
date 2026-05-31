package planetlotto.model;

import java.util.Arrays;

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
       return Arrays.stream(Rank.values()).filter(
                rank -> rank.count==matchCount && rank.bonus ==bonus
        ).findFirst().orElse(MISS);
    }

    public int getRanking() {
        return ranking;
    }
}
