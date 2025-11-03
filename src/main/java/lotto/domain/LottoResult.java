package lotto.domain;

import java.util.EnumMap;

public class LottoResult {
    private final EnumMap<Rank, Integer> winningCount;
    private int reward;

    public LottoResult() {
        this.winningCount = initializeEnumMap();
        this.reward = 0;
    }

    public EnumMap<Rank, Integer> getWinningEnumMap() {
        return winningCount;
    }

    public int getReward() {
        return reward;
    }

    public void addWinningCount(Rank lottoReward) {
        winningCount.put(lottoReward, winningCount.getOrDefault(lottoReward, 0) + 1);
        reward += lottoReward.getValue();
    }

    private EnumMap<Rank, Integer> initializeEnumMap() {
        EnumMap<Rank, Integer> map = new EnumMap<>(Rank.class);
        map.put(Rank.FIFTH, 0);
        map.put(Rank.FOURTH, 0);
        map.put(Rank.THIRD, 0);
        map.put(Rank.SECOND, 0);
        map.put(Rank.FIRST, 0);
        map.put(Rank.MISS, 0);
        return map;
    }

}
