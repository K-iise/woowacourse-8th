package lotto.service;

import java.util.List;
import java.util.TreeSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void 로또_구입_금액_정수형_변경_정상_테스트() {
        // given
        String purchaseAmount = "15000";
        Parser parser = new Parser();

        // when
        int amount = parser.parsePurchaseAmount(purchaseAmount);

        // then
        Assertions.assertEquals(15000, amount);
    }

    @Test
    public void 로또_구입_금액_정수형_변경_예외_테스트() {
        // given
        String purchaseAmount = "asdf";
        Parser parser = new Parser();

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> parser.parsePurchaseAmount(purchaseAmount));
    }

    @Test
    public void 당첨_번호_구분_정상_테스트() {
        // given
        String winningNumbers = "6,10,22,35,38,44";
        Parser parser = new Parser();

        // when
        TreeSet<Integer> lotteryNumber = parser.parseLotteryNumber(winningNumbers);

        // then
        TreeSet<Integer> expected = new TreeSet<>(List.of(6, 10, 22, 35, 38, 44));
        Assertions.assertEquals(expected, lotteryNumber);

    }

    @Test
    public void 당첨_번호_구분_예외_테스트() {
        // given
        String lotteryNumbers = "5,a,12,33,44,13";
        Parser parser = new Parser();

        // when & when
        Assertions.assertThrows(IllegalArgumentException.class, () -> parser.parseLotteryNumber(lotteryNumbers));
    }


    @Test
    public void 보너스_번호_변환_정상_테스트() {
        // given
        String bonusNumber = "12";
        Parser parser = new Parser();

        // when
        int transNumber = parser.parseBonusNumber(bonusNumber);

        // then
        Assertions.assertEquals(12, transNumber);
    }

    @Test
    public void 보너스_번호_변환_예외_테스트() {
        // given
        String bonusNumber = "asb";
        Parser parser = new Parser();

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> parser.parseBonusNumber(bonusNumber));
    }
}
