package racingcar.domain;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import racingcar.service.RandomGenerator;
import racingcar.service.Separator;
import racingcar.service.Validator;

public class RacingGameTest {

    @Test
    public void 레이싱_게임_시작() {
        // given
        CarGenerator carGenerator = new CarGenerator(new Validator(), new Separator());
        RacingGame racingGame = new RacingGame(new RandomGenerator());
        List<Car> CarList = carGenerator.createCars("joni,cobi,yun");

        // when & then
        racingGame.startRacing(CarList);
        for (Car car : CarList) {
            Assertions.assertTrue(car.getWeight() >= 0);
        }

    }
}
