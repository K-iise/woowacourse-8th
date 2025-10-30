package lotto.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoPurchaseTest {
    @Test
    public void 로또_구입금액_정상_생성(){
        // given
        String lottoPurchaseAmount = "15000";

        // when
        LottoPurchase amount = new LottoPurchase(lottoPurchaseAmount);

        // then
        Assertions.assertEquals(15000, amount.getAmount());
    }

}
