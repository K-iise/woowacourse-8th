package lotto.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoRewardTest {
    @Test
    public void 로또_당첨_정상_테스트() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);

        Lotto test = new Lotto(List.of(1, 2, 3, 6, 7, 8));
        // then
        int count = winningLotto.matchLotto(test);
        boolean bonus = winningLotto.matchBonusNumber(test);

        // when
        Assertions.assertThat(count).isEqualTo(Rank.FOURTH.getCount());
    }
}
