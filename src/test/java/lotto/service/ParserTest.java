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
        int amount = parser.changeInteger(purchaseAmount);

        // then
        Assertions.assertEquals(15000, amount);
    }

    @Test
    public void 로또_구입_금액_정수형_변경_예외_테스트() {
        // given
        String purchaseAmount = "asdf";
        Parser parser = new Parser();

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> parser.changeInteger(purchaseAmount));
    }

    @Test
    public void 당첨_번호_구분_정상_테스트() {
        // given
        String winningNumbers = "6,10,22,35,38,44";
        Parser parser = new Parser();
        TreeSet<Integer> lotteryNumber = new TreeSet<>();

        // when
        lotteryNumber = parser.separateNumber(winningNumbers);

        // then
        TreeSet<Integer> expected = new TreeSet<>(List.of(6, 10, 22, 35, 38, 44));
        Assertions.assertEquals(expected, lotteryNumber);

    }

    @Test
    public void 당첨_번호_구분_범위_예외_테스트() {
        // given
        String errorNumbers = "6,12,33,55,27,48";
        Parser parser = new Parser();
        TreeSet<Integer> lotteryNumber = new TreeSet<>();

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> parser.separateNumber(errorNumbers));
    }

    @Test
    public void 당첨_번호_구분_중복_예외_테스트() {
        // given
        String errorNumbers = "5,12,44,12,33,4";
        Parser parser = new Parser();
        TreeSet<Integer> lotteryNumber = new TreeSet<>();

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> parser.separateNumber(errorNumbers));
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
