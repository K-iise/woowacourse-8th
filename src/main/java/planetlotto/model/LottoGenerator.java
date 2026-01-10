package planetlotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoGenerator {

    public static List<List<Integer>> createLotto(PurchaseInfo purchaseInfo){
        List<List<Integer>> lottoList = new ArrayList<>();
        for (int i = 0; i < purchaseInfo.getQuantity(); i++) {
            lottoList.add(Lotto.generateLotto());
        }
        return lottoList;

    }
}
