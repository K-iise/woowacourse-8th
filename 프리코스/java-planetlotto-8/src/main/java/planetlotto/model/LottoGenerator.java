package planetlotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LottoGenerator {

    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 30;
    private static final int COUNT_NUMBER = 5;

    public static List<List<Integer>> createLotto(PurchaseInfo purchaseInfo){
        List<List<Integer>> lottoList = new ArrayList<>();
        for (int i = 0; i < purchaseInfo.getQuantity(); i++) {
            lottoList.add(sortNumber(generateLotto()));
        }
        return lottoList;
    }

    private static List<Integer> sortNumber(List<Integer> numbers) {
        List<Integer> sorted = new ArrayList<>(numbers);
        sorted.sort(Comparator.naturalOrder());
        return sorted;
    }

    private static List<Integer> generateLotto() {
        return Randoms.pickUniqueNumbersInRange(START_NUMBER, END_NUMBER, COUNT_NUMBER);
    };
}
