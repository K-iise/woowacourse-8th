package baseball.service;

import baseball.domain.Number;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class RandomNumberGenerator implements NumberGenerator{

    @Override
    public Number generateNumber() {
        List<Integer> list = new ArrayList<>();
        while (list.size() < 3){
            int number = Randoms.pickNumberInRange(1, 9);

            if (list.contains(number)){
                continue;
            }
            list.add(number);
        }

        return new Number(list);

    }
}
