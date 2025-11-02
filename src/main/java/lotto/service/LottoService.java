package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoPurchase;
import lotto.domain.LottoResult;
import lotto.domain.LottoReward;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;

public class LottoService {

    private final Parser parser;

    public LottoService(Parser parser) {
        this.parser = parser;
    }

    public List<Lotto> generateLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto());
        }
        return List.copyOf(lottos);
    }

    public Lottos createLottos(LottoPurchase lottoPurchase) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoPurchase.getLottoCount(); i++) {
            lottos.add(new Lotto());
        }
        return new Lottos(lottos);
    }

    public Lotto createLotto(String lotto) {
        return new Lotto(parser.parseLotteryNumber(lotto));
    }

    public LottoPurchase createPurchase(String input) {
        return new LottoPurchase(parser.parsePurchaseAmount(input));
    }

    public WinningLotto createWinningLotto(Lotto winningInput, int bonusInput) {
        return new WinningLotto(winningInput, bonusInput);
    }

    public LottoResult createLottoResult(Lottos lottos, WinningLotto winningLotto) {
        LottoResult lottoResult = new LottoResult();
        for (Lotto lotto : lottos.getLottos()) {
            lottoResult.addWinningCount(judgeRank(winningLotto, lotto));
        }
        return lottoResult;
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
