package racingcar.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WinnerFinderTest {

    @Test
    public void 가장_많이_전진한_자동차_목록() {
        // given
        Car james = new Car("james");
        Car cobi = new Car("cobi");
        Car jordan = new Car("jordan");

        james.addWeight(3);
        cobi.addWeight(5);
        jordan.addWeight(5);

        List<Car> cars = new ArrayList<>(List.of(james, cobi, jordan));
        WinnerFinder winnerFinder = new WinnerFinder();

        // when
        List<Car> winners = winnerFinder.findWinners(cars);

        // then
        Assertions.assertTrue(winners.contains(cobi));
        Assertions.assertTrue(winners.contains(jordan));
        Assertions.assertEquals(2, winners.size());
    }
}
