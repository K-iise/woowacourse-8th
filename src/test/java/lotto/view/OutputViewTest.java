package lotto.view;

import lotto.domain.LottoPurchase;
import lotto.domain.Lottos;
import lotto.service.LottoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OutputViewTest {
    @Test
    public void 발행한_로또_수량_출력() {
        // given
        OutputView outputView = new OutputView();
        LottoPurchase lottoPurchase = new LottoPurchase(15000);
        // when
        Assertions.assertThatCode(() -> outputView.printLottoPurchase(lottoPurchase))
                .doesNotThrowAnyException();

    }

    @Test
    public void 발행한_로또_번호_출력() {
        // given
        OutputView outputView = new OutputView();
        LottoPurchase lottoPurchase = new LottoPurchase(8000);
        LottoService lottoService = new LottoService();

        // when
        Lottos lottos = new Lottos(lottoService.generateLottos(lottoPurchase.getLottoCount()));

        // then
        Assertions.assertThatCode(() -> outputView.printLottos(lottos))
                .doesNotThrowAnyException();
        
    }

}
