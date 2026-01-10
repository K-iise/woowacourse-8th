package planetlotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class LottoGenerator {

    public static List<List<Integer>> createLotto(PurchaseInfo purchaseInfo){
        List<List<Integer>> lottoList = new ArrayList<>();
        for (int i = 0; i < purchaseInfo.getQuantity(); i++) {
            lottoList.add(generateLotto());
        }
        return lottoList;
    }

    private static List<Integer> generateLotto() {
        return Randoms.pickUniqueNumbersInRange(1, 30, 5);
    };
}
