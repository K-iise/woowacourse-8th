package lotto.domain;

import java.util.EnumMap;

public class LottoResult {
    private final EnumMap<LottoReward, Integer> winningCount;
    private int reward;

    public LottoResult() {
        this.winningCount = initializeEnumMap();
        this.reward = 0;
    }

    public EnumMap<LottoReward, Integer> getWinningEnumMap() {
        return winningCount;
    }

    public int getReward() {
        return reward;
    }

    public void addWinningCount(LottoReward lottoReward) {
        winningCount.put(lottoReward, winningCount.getOrDefault(lottoReward, 0) + 1);
        reward += lottoReward.getValue();
    }

    private EnumMap<LottoReward, Integer> initializeEnumMap() {
        EnumMap<LottoReward, Integer> map = new EnumMap<>(LottoReward.class);
        map.put(LottoReward.FIFTH, 0);
        map.put(LottoReward.FOURTH, 0);
        map.put(LottoReward.THIRD, 0);
        map.put(LottoReward.SECOND, 0);
        map.put(LottoReward.FIRST, 0);
        return map;
    }

}
