package planetlotto.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WinningLotto {
    private final Lotto winningNumber;
    private final int bonusNumber;

    private WinningLotto(Lotto winningNumber, int bonusNumber) {
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto fromWinningNumberAndBonus(List<Integer> winningNumber, int bonusNumber){
        Lotto fake = new Lotto(winningNumber);
        return new WinningLotto(fake, bonusNumber);
    }

    public int matchLotto(Lotto lotto) {
        int count = 0;
        for (int number : lotto.getNumbers()){
            if (winningNumber.getNumbers().contains(number)){
                count++;
            }
        }
        return count;
    }

    public boolean matchBonusNumber(Lotto lotto) {
        for (int number : lotto.getNumbers()){
            if (number == bonusNumber){
                return true;
            }
        }
        return false;
    }

}
