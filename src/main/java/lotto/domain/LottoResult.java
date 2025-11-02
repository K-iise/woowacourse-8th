package lotto.domain;

import java.util.EnumMap;

public class LottoResult {
    private EnumMap<LottoReward, Integer> winningCount;

    public LottoResult() {
        this.winningCount = new EnumMap<>(LottoReward.class);
    }

    public EnumMap<LottoReward, Integer> getWinningEnumMap() {
        return winningCount;
    }

    public void addWinningCount(LottoReward lottoReward) {
        winningCount.put(lottoReward, winningCount.getOrDefault(lottoReward, 0) + 1);
    }
}
