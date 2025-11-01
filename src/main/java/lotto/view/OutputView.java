package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoPurchase;
import lotto.domain.Lottos;

public class OutputView {

    public static final String PURCHASE_MESSAGE = "를 구매했습니다.";

    public void printLottoPurchase(LottoPurchase lottoPurchase) {
        System.out.println(lottoPurchase.getLottoCount() + PURCHASE_MESSAGE);
    }

    public void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.toString());
        }
    }
}
