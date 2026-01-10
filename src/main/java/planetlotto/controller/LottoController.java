package planetlotto.controller;

import java.util.List;
import planetlotto.model.LottoGenerator;
import planetlotto.model.Lottos;
import planetlotto.model.PurchaseInfo;
import planetlotto.model.WinningLotto;
import planetlotto.util.Validator;
import planetlotto.view.InputView;
import planetlotto.view.OutputView;

public class LottoController {


    public void run(){
        // 로또 구입
        PurchaseInfo lottoPurchase = loopPurchaseInfo();

        // 로또 생성 및 발행
        List<List<Integer>> buyLotto = LottoGenerator.createLotto(lottoPurchase);
        Lottos lottoList = Lottos.fromLottoList(buyLotto);
        
        // 생성된 로또 출력
        OutputView.printPurchasedLottos(buyLotto);

        // 당첨 로또 생성
        WinningLotto winningLotto = loopWinningLotto();
        winningLotto.JudgeRank(lottoList);

        // 결과 출력
        OutputView.printResult(winningLotto.JudgeRank(lottoList));
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
                int bonus = loopBonus(winningNum);
                return WinningLotto.fromWinningNumberAndBonus(winningNum, bonus);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
    private int loopBonus(List<Integer> winningNumber){
        while (true){
            try {
                int bonus = InputView.askBonusNumber();
                Validator.validateBonusNumber(winningNumber, bonus);
                return bonus;
            } catch (IllegalArgumentException e){
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
