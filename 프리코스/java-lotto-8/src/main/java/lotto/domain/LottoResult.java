package lotto.domain;

import java.util.EnumMap;

public class LottoResult {
    private final EnumMap<Rank, Integer> winningCount;
    private final int reward;

    public LottoResult(EnumMap<Rank, Integer> winningCount, int reward){
        this.winningCount = initializeEnumMap(winningCount);
        this.reward = reward;
    }

    public EnumMap<Rank, Integer> getWinningEnumMap() {
        return new EnumMap<>(winningCount);
    }

    public int getReward() {
        return reward;
    }


    private EnumMap<Rank, Integer> initializeEnumMap(EnumMap<Rank, Integer> enumMap) {

        for (Rank rank : Rank.values()){
            int count = enumMap.getOrDefault(rank, 0);
            enumMap.put(rank, count);
        }
        return new EnumMap<>(enumMap);
    }

}
