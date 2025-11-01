package lotto.view;

import lotto.domain.LottoPurchase;

public class OutputView {

    public static final String PURCHASE_MESSAGE = "를 구매했습니다.";

    public void printLottoPurchase(LottoPurchase lottoPurchase) {
        System.out.println(lottoPurchase.getLottoCount() + PURCHASE_MESSAGE);
    }
}
