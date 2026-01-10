package planetlotto.controller;

import planetlotto.model.PurchaseInfo;
import planetlotto.view.InputView;

public class LottoController {


    public void run(){
        int lottoPrice = InputView.askAmount();
        PurchaseInfo lottoPurchase = PurchaseInfo.FromPrice(lottoPrice);
    }
}
