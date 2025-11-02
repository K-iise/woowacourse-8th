package lotto.view;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoPurchase;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;
import lotto.service.LottoService;
import lotto.service.Parser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OutputViewTest {
    @Test
    public void 발행한_로또_수량_출력_테스트() {
        // given
        OutputView outputView = new OutputView();
        LottoPurchase lottoPurchase = new LottoPurchase(15000);
        // when
        Assertions.assertThatCode(() -> outputView.printLottoPurchase(lottoPurchase))
                .doesNotThrowAnyException();

    }

    @Test
    public void 발행한_로또_번호_출력_테스트() {
        // given
        OutputView outputView = new OutputView();
        LottoPurchase lottoPurchase = new LottoPurchase(8000);
        LottoService lottoService = new LottoService(new Parser());

        // when
        Lottos lottos = new Lottos(lottoService.generateLottos(lottoPurchase.getLottoCount()));

        // then
        Assertions.assertThatCode(() -> outputView.printLottos(lottos))
                .doesNotThrowAnyException();

    }

    @Test
    public void 당첨_통계_출력_테스트() {
        // given
        OutputView outputView = new OutputView();
        // given
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 33, 23, 44));

        Lotto winLotto = new Lotto(List.of(1, 2, 3, 4, 22, 45));
        int bonus = 7;
        WinningLotto winningLotto = new WinningLotto(winLotto, bonus);

        LottoService lottoService = new LottoService(new Parser());
        LottoResult lottoResult = new LottoResult();

        // when
        lottoResult.addWinningCount(lottoService.judgeRank(winningLotto, lotto1));
        lottoResult.addWinningCount(lottoService.judgeRank(winningLotto, lotto2));

        // then
        Assertions.assertThatCode(() -> outputView.printWinningStat(lottoResult))
                .doesNotThrowAnyException();
        ;
    }

    @Test
    public void 수익률_출력_테스트() {
        // given
        OutputView outputView = new OutputView();
        double profitRate = 62.5;

        // when & then
        Assertions.assertThatCode(() -> outputView.printProfitRate(profitRate))
                .doesNotThrowAnyException();

    }

}
