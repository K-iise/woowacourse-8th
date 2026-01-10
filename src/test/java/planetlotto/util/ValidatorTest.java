package planetlotto.util;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidatorTest {

    @Test
    public void 당첨_번호_중복_예외_테스트(){
        List<Integer> winningNumber = new ArrayList<>(List.of(1,2,3,4,4));
        Assertions.assertThrows(IllegalArgumentException.class, ()->Validator.validateWinningNumber(winningNumber));
    }

    @Test
    public void 당첨_번호_개수_초과_예외_테스트(){
        List<Integer> winningNumber = new ArrayList<>(List.of(1,2,3,4,4,5));
        Assertions.assertThrows(IllegalArgumentException.class, ()->Validator.validateWinningNumber(winningNumber));
    }

    @Test
    public void 당첨_번호_보너스_번호_중복_예외_테스트(){
        List<Integer> winningNumber = new ArrayList<>(List.of(1,2,3,4,5));
        int bonusNumber = 5;
        Assertions.assertThrows(IllegalArgumentException.class, ()->Validator.validateBonusNumber(winningNumber, bonusNumber));

    }
}
