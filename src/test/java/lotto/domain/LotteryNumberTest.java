package lotto.domain;

import java.util.List;
import java.util.TreeSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LotteryNumberTest {
    @Test
    public void 당첨_번호_생성_정상_테스트() {
        // given
        TreeSet<Integer> lotteryNumber = new TreeSet<>(List.of(11, 13, 15, 33, 44, 32));
        int bonusNumber = 14;

        // when & then
        Assertions.assertDoesNotThrow(() -> new LotteryNumber(lotteryNumber, bonusNumber));
    }

    @Test
    public void 당첨_번호_생성_범위_예외_테스트() {
        // given
        TreeSet<Integer> lotteryNumber = new TreeSet<>(List.of(12, 32, 44, 46, 57, 2));
        int bonusNumber = 13;

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new LotteryNumber(lotteryNumber, bonusNumber));
    }

    @Test
    public void 당청_번호_생성_중복_예외_테스트() {
        // given
        TreeSet<Integer> lotteryNumber = new TreeSet<>(List.of(2, 11, 22, 33, 33, 44));
        int bonusNumber = 3;

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new LotteryNumber(lotteryNumber, bonusNumber));
    }

    @Test
    public void 보너스_번호_범위_예외_테스트() {
        // given
        TreeSet<Integer> lotteryNumber = new TreeSet<>(List.of(11, 13, 15, 33, 44, 32));
        int bonusNumber = -2;

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new LotteryNumber(lotteryNumber, bonusNumber));
    }

    @Test
    public void 보너스_번호_중복_예외_테스트() {
        // given
        TreeSet<Integer> lotteryNumber = new TreeSet<>(List.of(11, 13, 15, 33, 44, 32));
        int bonusNumber = 11;

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new LotteryNumber(lotteryNumber, bonusNumber));
    }
}
