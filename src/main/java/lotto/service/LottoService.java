package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoPurchase;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;

public class LottoService {

    private final LottoGenerator lottoGenerator;
    private final Parser parser;

    public LottoService(LottoGenerator lottoGenerator, Parser parser) {
        this.lottoGenerator = lottoGenerator;
        this.parser = parser;
    }

    public Lottos buyLottos(LottoPurchase purchase) {
        int lottoCount = purchase.getLottoCount();
        List<Lotto> purchasedLottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {
            purchasedLottos.add(lottoGenerator.generateLotto());
        }
        return new Lottos(purchasedLottos);
    }

    public LottoPurchase createPurchase(String input) {
        return new LottoPurchase(parser.parsePurchaseAmount(input));
    }

    public Lotto createWinningLottoNumbers(String input){
        return new Lotto(parser.parseLotteryNumber(input));
    }


    public LottoResult calculateResult(Lottos lottos, WinningLotto winningLotto){
        LottoResult lottoResult = new LottoResult();

        for (Lotto lotto : lottos.getLottos()){
            Rank rank = winningLotto.judgeRank(lotto);
            lottoResult.addWinningCount(rank);
        }
        return lottoResult;
    }

    public double getProfit(LottoPurchase lottoPurchase, LottoResult lottoResult) {
        int cost = lottoPurchase.getAmount();
        int reward = lottoResult.getReward();
        return (double) reward / cost * 100;

    }
}
