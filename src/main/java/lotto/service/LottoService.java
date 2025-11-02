package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoPurchase;
import lotto.domain.LottoResult;
import lotto.domain.LottoReward;
import lotto.domain.WinningLotto;

public class LottoService {

    public List<Lotto> generateLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto());
        }
        return List.copyOf(lottos);
    }

    public LottoReward judgeRank(WinningLotto winningLotto, Lotto lotto) {
        int matchCount = winningLotto.matchLotto(lotto);
        boolean bonus = winningLotto.matchBonusNumber(lotto);
        return LottoReward.rank(matchCount, bonus);
    }

    public double getProfit(LottoPurchase lottoPurchase, LottoResult lottoResult) {
        int cost = lottoPurchase.getAmount();
        int reward = lottoResult.getReward();
        return (double) reward / cost * 100;

    }
}
