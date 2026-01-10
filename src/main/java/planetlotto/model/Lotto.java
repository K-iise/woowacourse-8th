package planetlotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> number) {
        this.numbers = sortNumber(number);
    }

    private List<Integer> sortNumber(List<Integer> numbers) {
        List<Integer> sorted = new ArrayList<>(numbers);
        sorted.sort(Comparator.naturalOrder());
        return sorted;
    }

    public int matchCount(Lotto otherLotto){
        return (int) numbers.stream().filter(otherLotto::contains).count();
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

}
