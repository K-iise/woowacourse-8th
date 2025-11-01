package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import lotto.service.LottoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoResultTest {
    @Test
    public void 각_등수별_당첨_횟수를_카운트한다() {
        // given
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 33, 23, 44));

        Lotto winLotto = new Lotto(List.of(1, 2, 3, 4, 22, 45));
        int bonus = 7;
        WinningLotto winningLotto = new WinningLotto(winLotto, bonus);

        LottoService lottoService = new LottoService();
        LottoResult lottoResult = new LottoResult();
        // when
        lottoService.judgeRank(winningLotto, lotto1);
        lottoService.judgeRank(winningLotto, lotto2);

        // when
        EnumMap<LottoReward, Integer> expected = new EnumMap<>(LottoReward.class);
        expected.put(lottoService.judgeRank(winningLotto, lotto1), 1);
        expected.put(lottoService.judgeRank(winningLotto, lotto1), 1);

        Assertions.assertThat(lottoResult.getWinningMap())
                .isEqualTo(expected);
    }
}
