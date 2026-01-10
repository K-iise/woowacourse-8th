package planetlotto.controller;

import java.util.List;
import planetlotto.model.LottoGenerator;
import planetlotto.model.Lottos;
import planetlotto.model.PurchaseInfo;
import planetlotto.view.InputView;
import planetlotto.view.OutputView;

public class LottoController {


    public void run(){
        PurchaseInfo lottoPurchase = loopPurchaseInfo();

        List<List<Integer>> lottos = LottoGenerator.createLotto(lottoPurchase);
        OutputView.printPurchasedLottos(lottos);
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
}
