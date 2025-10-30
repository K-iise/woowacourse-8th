package lotto.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoPurchaseTest {
    @Test
    public void 로또_구입_정상_테스트() {
        // given
        int lottoPurchaseAmount = 15000;

        // when
        LottoPurchase amount = new LottoPurchase(lottoPurchaseAmount);

        // then
        Assertions.assertEquals(15000, amount.getAmount());
    }

    @Test
    public void 로또_구입_금액_단위_예외_테스트() {
        // given
        int amount = 15500;

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new LottoPurchase(amount));

    }

    @Test
    public void 로또_구입_0원_이하_예외테스트() {
        // given
        int amount = 0;

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new LottoPurchase(amount));
    }


}
