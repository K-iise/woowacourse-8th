package planetlotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Lotto {
    private List<Integer> numbers;

    public Lotto(){
        List<Integer> copy = generateLotto();
        this.numbers = sortNumber(copy);
    }

    private List<Integer> sortNumber(List<Integer> numbers) {
        List<Integer> sorted = new ArrayList<>(numbers);
        sorted.sort(Comparator.naturalOrder());
        return sorted;
    }

    public List<Integer> generateLotto() {
        return Randoms.pickUniqueNumbersInRange(1, 30, 5);
    }
}
