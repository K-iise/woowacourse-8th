package planetlotto.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WinningLotto {
    private final Lotto winningNumber;
    private final int bonusNumber;

    private WinningLotto(Lotto winningNumber, int bonusNumber) {
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto fromWinningNumberAndBonus(List<Integer> winningNumber, int bonusNumber){
        Lotto fake = new Lotto(winningNumber);
        return new WinningLotto(fake, bonusNumber);
    }

    public Map<Integer, Integer> JudgeRank(Lottos lottos) {
        Map<Integer, Integer> result = new LinkedHashMap<>();

        for (Lotto lotto : lottos.getLottos()) {
            int matchCount = lotto.matchCount(winningNumber);
            boolean matchBonus = lotto.contains(bonusNumber);

            Rank rank = Rank.rank(matchCount, matchBonus);
            int ranking = rank.getRanking();
            result.put(ranking, result.getOrDefault(ranking, 0) + 1);
        }
        return result;
    }

}
