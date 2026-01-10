package planetlotto.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PurchaseInfoTest {

    @Test
    public void 로또_구입_정상_테스트(){
        int price = 2000;
        Assertions.assertDoesNotThrow(()-> PurchaseInfo.FromPrice(price));
    }

    @Test
    public void 로또_구입_예외_테스트(){
        int price = 1400;
        Assertions.assertThrows(IllegalArgumentException.class, () -> PurchaseInfo.FromPrice(price));
    }


}
