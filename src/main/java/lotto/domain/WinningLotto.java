package lotto.domain;

import lotto.exception.ErrorMessage;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final int bonusNumber;

    public WinningLotto(Lotto winningNumbers, int bonusNumber) {
        validateBonus(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public Lotto getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    private void validateBonus(Lotto winningNumbers, int bonusNumber) {
        if (winningNumbers.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_MUST_BE_UNIQUE.getMessage());
        }
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_NUMBER_OUT_OF_RANGE.getMessage());
        }
    }

    public int matchLotto(Lotto lotto) {
        return (int) lotto.getNumbers().stream()
                .filter(number -> winningNumbers.getNumbers().contains(number))
                .count();
    }

    public boolean matchBonusNumber(Lotto lotto) {
        return lotto.getNumbers().contains(getBonusNumber());
    }

    public Rank judgeRank(Lotto lotto){
        int matchCount = matchLotto(lotto);
        boolean matchBonus = matchBonusNumber(lotto);

        return Rank.rank(matchCount, matchBonus);
    }
}
