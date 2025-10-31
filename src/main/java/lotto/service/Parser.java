package lotto.service;

import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Parser {
    public int changeInteger(String purchaseAmount) {
        try {
            return Integer.parseInt(purchaseAmount);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.");
        }
    }

    public TreeSet<Integer> separateNumber(String input) {
        TreeSet<Integer> lotteryNumber = parseToNumbers(input);
        validateNumbers(lotteryNumber);
        return new TreeSet<>(lotteryNumber);
    }

    private TreeSet<Integer> parseToNumbers(String input) {
        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(TreeSet::new));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 합니다.");
        }
    }

    private void validateNumbers(TreeSet<Integer> numbers) {
        validateCount(numbers);
        numbers.forEach(this::validateRange);
    }

    private void validateRange(int number) {
        if (number > 45 || number < 1) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 1 ~ 45 범위에 속해야 합니다.");
        }
    }

    private void validateCount(TreeSet<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 하며 중복될 수 없습니다.");
        }
    }
}
