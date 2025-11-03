package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import lotto.service.RandomLottoGenerator;
import lotto.service.LottoService;
import lotto.service.Parser;
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

        LottoService lottoService = new LottoService(new RandomLottoGenerator(), new Parser());
        LottoResult lottoResult = new LottoResult();

        // when
        lottoResult.addWinningCount(winningLotto.judgeRank(lotto1));
        lottoResult.addWinningCount(winningLotto.judgeRank(lotto2));

        // then
        EnumMap<Rank, Integer> expected = new EnumMap<>(Rank.class);
        expected.put(Rank.FIRST, 0);
        expected.put(Rank.FIFTH, 1);
        expected.put(Rank.SECOND, 0);
        expected.put(Rank.THIRD, 0);
        expected.put(Rank.FOURTH, 1);

        Assertions.assertThat(lottoResult.getWinningEnumMap())
                .containsAllEntriesOf(expected);
    }

    @Test
    public void 로또_당첨_통계_초기값_0으로_초기화() {
        // given
        LottoResult lottoResult = new LottoResult();
        EnumMap<Rank, Integer> rewardMap = lottoResult.getWinningEnumMap();

        // when & then
        Assertions.assertThat(rewardMap.get(Rank.FIFTH)).isEqualTo(0);
        Assertions.assertThat(rewardMap.get(Rank.FOURTH)).isEqualTo(0);
        Assertions.assertThat(rewardMap.get(Rank.THIRD)).isEqualTo(0);
        Assertions.assertThat(rewardMap.get(Rank.SECOND)).isEqualTo(0);
        Assertions.assertThat(rewardMap.get(Rank.FIRST)).isEqualTo(0);

    }
}
