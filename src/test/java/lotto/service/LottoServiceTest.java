package lotto.service;

import lotto.domain.LottoPurchase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoServiceTest {
    @Test
    public void 로또_발행_정상_테스트() {
        // given
        LottoPurchase lottoPurchase = new LottoPurchase(5000);
        LottoService lottoService = new LottoService();

        // when & then
        Assertions.assertThatCode(() -> lottoService.generateLottos(lottoPurchase.getLottoCount()))
                .doesNotThrowAnyException();
    }
}
