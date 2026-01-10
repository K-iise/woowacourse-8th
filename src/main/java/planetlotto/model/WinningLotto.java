package planetlotto.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WinningLotto {
    private final List<Integer> winningNumber;
    private final int bonusNumber;

    private WinningLotto(List<Integer> winningNumber, int bonusNumber) {
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto fromWinningNumberAndBonus(List<Integer> winningNumber, int bonusNumber){
        List<Integer> fake = sortNumber(winningNumber);
        return new WinningLotto(fake, bonusNumber);
    }

    public int matchLotto(Lotto lotto) {
        int count = 0;
        for (int number : lotto.getNumbers()){
            if (winningNumber.contains(number)){
                count++;
            }
        }
        return count;
    }

    private static List<Integer> sortNumber(List<Integer> numbers) {
        List<Integer> sorted = new ArrayList<>(numbers);
        sorted.sort(Comparator.naturalOrder());
        return sorted;
    }
}
