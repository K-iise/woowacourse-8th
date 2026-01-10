package planetlotto.controller;

import java.util.List;
import planetlotto.model.LottoGenerator;
import planetlotto.model.LottoResult;
import planetlotto.model.Lottos;
import planetlotto.model.PurchaseInfo;
import planetlotto.model.WinningLotto;
import planetlotto.util.Validator;
import planetlotto.view.InputView;
import planetlotto.view.OutputView;

public class LottoController {


    public void run(){
        PurchaseInfo lottoPurchase = loopPurchaseInfo();

        List<List<Integer>> lottos = LottoGenerator.createLotto(lottoPurchase);
        Lottos lottoList = Lottos.fromLottoList(lottos);
        OutputView.printPurchasedLottos(lottos);

        WinningLotto winningLotto = loopWinningLotto();
        LottoResult lottoResult = new LottoResult();
        lottoResult.JudgeRank(lottoList, winningLotto);
    }

    private PurchaseInfo loopPurchaseInfo(){
        while (true) {
            try {
                int lottoPrice = InputView.askAmount();
                return PurchaseInfo.FromPrice(lottoPrice);
            } catch (IllegalArgumentException e){
                OutputView.printErrorMessage(e.getMessage());
            }

        }
    }

    private WinningLotto loopWinningLotto(){
        while (true) {
            try {
                List<Integer> winningNum = InputView.askWinningLotto();
                Validator.validateWinningNumber(winningNum);
                int bonus = InputView.askBonusNumber();
                Validator.validateBonusNumber(winningNum, bonus);
                return WinningLotto.fromWinningNumberAndBonus(winningNum, bonus);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
