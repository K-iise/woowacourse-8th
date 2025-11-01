package lotto.service;

import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.Collectors;
import lotto.domain.ErrorMessage;

public class Parser {


    public int parsePurchaseAmount(String purchaseAmount) {
        try {
            return Integer.parseInt(purchaseAmount);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.AMOUNT_MUST_BE_NUMBER.getMessage());
        }
    }

    public TreeSet<Integer> parseLotteryNumber(String input) {
        TreeSet<Integer> lotteryNumber = parseToNumbers(input);
        return new TreeSet<>(lotteryNumber);
    }

    public int parseBonusNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_MUST_BE_NUMBER.getMessage());
        }
    }

    private TreeSet<Integer> parseToNumbers(String input) {
        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(TreeSet::new));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_MUST_BE_NUMBER.getMessage());
        }
    }


}
