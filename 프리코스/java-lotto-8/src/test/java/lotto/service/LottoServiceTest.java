package lotto.service;

import java.util.EnumMap;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoPurchase;
import lotto.domain.LottoResult;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoServiceTest {
    @Test
    public void 로또_발행_정상_테스트() {
        // given
        LottoPurchase lottoPurchase = new LottoPurchase(5000);
        LottoService lottoService = new LottoService(new RandomLottoGenerator(), new Parser());

        // when & then
        Assertions.assertThatCode(() -> lottoService.buyLottos(lottoPurchase))
                .doesNotThrowAnyException();
    }

    @Test
    public void 로또_등수_판정_정상_테스트() {
        // given
        Lotto lotto = new Lotto(List.of(1, 3, 12, 44, 23, 34));
        int bonusNumber = 4;
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);
        Lotto test = new Lotto(List.of(1, 3, 12, 44, 4, 43));

        LottoService lottoService = new LottoService(new RandomLottoGenerator(), new Parser());

        // when & then
        Assertions.assertThatCode(() -> winningLotto.judgeRank(lotto))
                .doesNotThrowAnyException();
    }

    @Test
    public void 로또_수익률_계산_테스트_리팩토링_후() {
        // given
        LottoService lottoService = new LottoService(new RandomLottoGenerator(), new Parser());

        LottoPurchase lottoPurchase = new LottoPurchase(8000);

        EnumMap<Rank, Integer> fixedWinningCount = new EnumMap<>(Rank.class);
        fixedWinningCount.put(Rank.FIFTH, 1);

        int fixedReward = Rank.FIFTH.getValue();

        LottoResult lottoResult = new LottoResult(fixedWinningCount, fixedReward);

        // when
        double profit = lottoService.calculateProfitRate(lottoPurchase, lottoResult);

        // then
        Assertions.assertThat(profit).isEqualTo(62.5);
    }
}
