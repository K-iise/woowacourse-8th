package lotto.service;

import lotto.domain.LottoPurchase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void 로또_구입_금액_정수형_변경_정상_테스트(){
        // given
        String purchaseAmount = "15000";
        Parser parser = new Parser();

        // when
        int amount = parser.changeInteger(purchaseAmount);

        // then
        Assertions.assertEquals(15000, amount);
    }

    @Test
    public void 로또_구입_금액_정수형_변경_예외_테스트(){
        // given
        String purchaseAmount = "asdf";
        Parser parser = new Parser();

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> parser.changeInteger(purchaseAmount));
    }
}
