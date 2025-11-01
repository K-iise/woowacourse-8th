package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        validateDuplication(numbers);
        validateRange(numbers);
        this.numbers = sortNumber(numbers);
    }

    public Lotto() {
        this.numbers = generateLotto();
    }


    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_MUST_HAVE_SIX_NUMBERS.getMessage());
        }
    }

    private void validateDuplication(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != 6) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBERS_MUST_BE_UNIQUE.getMessage());
        }
    }

    private void validateRange(List<Integer> numbers) {
        numbers.forEach(this::validateSingleRange);
    }

    private void validateSingleRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBER_OUT_OF_RANGE.getMessage());
        }
    }

    private List<Integer> sortNumber(List<Integer> numbers) {
        List<Integer> sorted = new ArrayList<>(numbers);
        sorted.sort(Comparator.naturalOrder());
        return sorted;
    }

    private List<Integer> generateLotto() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    // TODO: 추가 기능 구현
}
