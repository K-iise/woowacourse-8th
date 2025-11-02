package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import lotto.service.LottoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoResultTest {
    @Test
    public void 각_등수별_당첨_횟수를_추가한다() {
        // given
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 33, 23, 44));

        Lotto winLotto = new Lotto(List.of(1, 2, 3, 4, 22, 45));
        int bonus = 7;
        WinningLotto winningLotto = new WinningLotto(winLotto, bonus);

        LottoService lottoService = new LottoService();
        LottoResult lottoResult = new LottoResult();

        // when
        lottoResult.addWinningCount(lottoService.judgeRank(winningLotto, lotto1));
        lottoResult.addWinningCount(lottoService.judgeRank(winningLotto, lotto2));

        // then
        EnumMap<LottoReward, Integer> expected = new EnumMap<>(LottoReward.class);
        expected.put(LottoReward.FIRST, 0);
        expected.put(LottoReward.FIFTH, 1);
        expected.put(LottoReward.SECOND, 0);
        expected.put(LottoReward.THIRD, 0);
        expected.put(LottoReward.FOURTH, 1);

        Assertions.assertThat(lottoResult.getWinningEnumMap())
                .containsAllEntriesOf(expected);
    }

    @Test
    public void 로또_당첨_통계_초기값_0으로_초기화() {
        // given
        LottoResult lottoResult = new LottoResult();
        EnumMap<LottoReward, Integer> rewardMap = lottoResult.getWinningEnumMap();

        // when & then
        Assertions.assertThat(rewardMap.get(LottoReward.FIFTH)).isEqualTo(0);
        Assertions.assertThat(rewardMap.get(LottoReward.FOURTH)).isEqualTo(0);
        Assertions.assertThat(rewardMap.get(LottoReward.THIRD)).isEqualTo(0);
        Assertions.assertThat(rewardMap.get(LottoReward.SECOND)).isEqualTo(0);
        Assertions.assertThat(rewardMap.get(LottoReward.FIRST)).isEqualTo(0);

    }
}
