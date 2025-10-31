package lotto.domain;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class LotteryNumber {
    private final SortedSet<Integer> lotteryNumber;
    private final int bonusNumber;

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_COUNT = 6;

    private static final String RANGE_ERROR_MESSAGE = "[ERROR] 당첨 번호는 1 ~ 45 범위에 속해야 합니다.";
    private static final String BONUS_RANGE_ERROR_MESSAGE = "[ERROR] 보너스 번호는 1 ~ 45 범위에 속해야 합니다.";
    private static final String COUNT_ERROR_MESSAGE = "[ERROR] 당첨 번호 6개는 중복 될 수 없습니다.";
    private static final String BONUS_DUPLICATION_ERROR_MESSAGE = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.";

    public LotteryNumber(SortedSet<Integer> lotteryNumber, int bonusNumber) {
        this.lotteryNumber = validateLotteryNumber(lotteryNumber);
        this.bonusNumber = validateBonusNumber(bonusNumber);
    }

    private SortedSet<Integer> validateLotteryNumber(SortedSet<Integer> lotteryNumber) {
        validateRange(lotteryNumber);
        validateCount(lotteryNumber);
        return Collections.unmodifiableSortedSet(new TreeSet<>(lotteryNumber));
    }

    private int validateBonusNumber(int bonusNumber) {
        validateSingleRange(bonusNumber, BONUS_RANGE_ERROR_MESSAGE);
        validateBonusDuplication(bonusNumber);
        return bonusNumber;
    }

    private void validateCount(SortedSet<Integer> lotteryNumber) {
        if (lotteryNumber.size() != LOTTO_COUNT) {
            throw new IllegalArgumentException(COUNT_ERROR_MESSAGE);
        }
    }

    private void validateRange(SortedSet<Integer> lotteryNumber) {
        lotteryNumber.forEach(n -> validateSingleRange(n, RANGE_ERROR_MESSAGE));
    }

    private void validateSingleRange(int number, String message) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException(message);
        }
    }

    private void validateBonusDuplication(int bonusNumber) {
        if (lotteryNumber.contains(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_DUPLICATION_ERROR_MESSAGE);
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    public SortedSet<Integer> getLotteryNumber() {
        return Collections.unmodifiableSortedSet(new TreeSet<>(lotteryNumber));
    }
}
