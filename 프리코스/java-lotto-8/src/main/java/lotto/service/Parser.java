package lotto.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.exception.ErrorMessage;

public class Parser {

    public int parsePurchaseAmount(String purchaseAmount) {
        try {
            return Integer.parseInt(purchaseAmount);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.AMOUNT_MUST_BE_NUMBER.getMessage());
        }
    }

    public List<Integer> parseLotteryNumber(String input) {
        List<Integer> lotteryNumber = parseToNumbers(input);
        return new ArrayList<>(lotteryNumber);
    }

    public int parseBonusNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_MUST_BE_NUMBER.getMessage());
        }
    }

    private List<Integer> parseToNumbers(String input) {
        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_MUST_BE_NUMBER.getMessage());
        }
    }


}
