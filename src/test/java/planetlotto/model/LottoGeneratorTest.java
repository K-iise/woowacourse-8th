package planetlotto.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class LottoGeneratorTest {

    @Test
    public void 로또_생성_정상_테스트(){
        PurchaseInfo purchaseInfo = PurchaseInfo.FromPrice(2000);
        Assertions.assertDoesNotThrow((Executable) LottoGenerator.createLotto(purchaseInfo));
    }
}
