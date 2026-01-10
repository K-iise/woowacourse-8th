package planetlotto.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoGeneratorTest {

    @Test
    public void 로또_생성_정상_테스트(){
        PurchaseInfo purchaseInfo = PurchaseInfo.FromPrice(2000);
        Assertions.assertDoesNotThrow(LottoGenerator.createLotto(purchaseInfo));
    }
}
