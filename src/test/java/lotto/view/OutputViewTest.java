package lotto.view;

import lotto.domain.LottoPurchase;
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

}
