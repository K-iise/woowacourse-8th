package lotto.domain;

import java.util.TreeSet;

public class LotteryNumber {
    private final TreeSet<Integer> lotteryNumber;
    private final int bonusNumber;

    public LotteryNumber(TreeSet<Integer> lotteryNumber, int bonusNumber) {
        this.lotteryNumber = validateLotteryNumber(lotteryNumber);
        this.bonusNumber = validateBonusNumber(bonusNumber);
    }

    private TreeSet<Integer> validateLotteryNumber(TreeSet<Integer> lotteryNumber) {
        validateRange(lotteryNumber);
        validateDuplication(lotteryNumber);
        return new TreeSet<>(lotteryNumber);
    }

    private int validateBonusNumber(int bonusNumber) {
        validateRangeBonus(bonusNumber);
        validateDuplicationBonus(bonusNumber);
        return bonusNumber;
    }

    private void validateDuplication(TreeSet<Integer> lotteryNumber) {
        if (lotteryNumber.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호 6개는 중복 될 수 없습니다.");
        }
    }

    private void validateRange(TreeSet<Integer> lotteryNumber) {
        for (int number : lotteryNumber) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 1 ~ 45 범위에 속해야 합니다.");
            }
        }
    }

    private void validateRangeBonus(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1 ~ 45 범위에 속해야 합니다.");
        }
    }

    private void validateDuplicationBonus(int bonusNumber) {
        if (lotteryNumber.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복 될 수 없습니다.");
        }
    }

}
