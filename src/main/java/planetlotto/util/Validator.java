package planetlotto.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validator {

    public static void validateWinningNumber(List<Integer> winningNumber){
        validateNumberCount(winningNumber);
        validateRange(winningNumber);
        validateDuplicationNumbers(winningNumber);
    }

    private static void validateNumberCount(List<Integer> winningNumber){
        if (winningNumber.size() != 5){
            throw new IllegalArgumentException(ErrorCode.WINNING_COUNT_ERROR.getMessage());
        }
    }

    private static void validateDuplicationNumbers(List<Integer> winningNumber) {
        Set<Integer> fake = new HashSet<>(winningNumber);
        if (fake.size() != 5) {
            throw new IllegalArgumentException(ErrorCode.DUPLICATION_WINNING_ERROR.getMessage());
        }
    }

    public static void validateBonusNumber(List<Integer> winningNumber, int bonus){
        validateSingleRange(bonus);
        Set<Integer> fake = new HashSet<>(winningNumber);
        fake.add(bonus);
        if (fake.size() != 6) {
            throw new IllegalArgumentException(ErrorCode.DUPLICATION_BONUS_ERROR.getMessage());
        }
    }

    public static void validateSingleRange(int number){
        if (!(number >= 1 && number <= 30)){
            throw new IllegalArgumentException(ErrorCode.RANGE_ERROR.getMessage());
        }
    }

    public static void validateRange(List<Integer> numbers) {
        for (int number : numbers){
            validateSingleRange(number);
        }
    }


}
