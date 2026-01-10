package planetlotto.model;

import java.util.List;

public class WinningLotto {
    private final List<Integer> winningNumber;
    private final int bonusNumber;

    private WinningLotto(List<Integer> winningNumber, int bonusNumber) {
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto fromWinningNumberAndBonus(List<Integer> winningNumber, int bonusNumber){
        return new WinningLotto(winningNumber, bonusNumber);
    }
}
