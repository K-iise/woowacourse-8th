package planetlotto.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class LottoResult {
    private Map<Integer, Integer> result;

    public LottoResult(){
        result = new LinkedHashMap<>();
        for (int i = 0; i < 6; i++) {
            result.put(i, 0);
        }
    }

    public void JudgeRank(Lottos lottos, WinningLotto winningLotto) {
        for (Lotto lotto : lottos.getLottos()) {
            int matchCount = winningLotto.matchLotto(lotto);
            boolean matchBonus = winningLotto.matchBonusNumber(lotto);

            Rank rank = Rank.rank(matchCount, matchBonus);
            int ranking = rank.getRanking();
            result.put(ranking, result.getOrDefault(ranking, 0) + 1);
        }
    }
    
}
