package lotto.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    @Test
    public void 정상_생성_테스트() {
        Lotto lotto = new Lotto(List.of(1, 3, 12, 44, 23, 34));
        int bonusNumber = 4;
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);

        Assertions.assertThat(winningLotto.getWinningNumbers()).isEqualTo(lotto);
        Assertions.assertThat(winningLotto.getBonusNumber()).isEqualTo(bonusNumber);
    }

    @Test
    public void 보너스_번호_중복_예외_테스트() {
        Lotto lotto = new Lotto(List.of(1, 3, 12, 44, 23, 34));
        int bonusNumber = 3;
        Assertions.assertThatThrownBy(() -> new WinningLotto(lotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 당첨_비교_테스트() {
        // given
        Lotto lotto = new Lotto(List.of(1, 3, 12, 44, 23, 34));
        int bonusNumber = 4;
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);
        Lotto testLotto = new Lotto(List.of(1, 10, 12, 33, 35, 42));

        // when
        int count = winningLotto.matchLotto(testLotto);

        // then
        Assertions.assertThat(count).isEqualTo(2);
    }

    @Test
    public void 보너스_번호_비교_테스트() {
        // given
        Lotto lotto = new Lotto(List.of(1, 3, 12, 44, 23, 34));
        int bonusNumber = 4;
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);
        Lotto testLotto = new Lotto(List.of(1, 10, 12, 4, 35, 42));

        // when & then
        org.junit.jupiter.api.Assertions.assertTrue(winningLotto.matchBonusNumber(testLotto));
    }

}
