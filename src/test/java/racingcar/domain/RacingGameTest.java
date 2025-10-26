package racingcar.domain;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import racingcar.service.Separator;
import racingcar.service.Validator;

public class RacingGameTest {
    
    public void 레이싱_게임_시작() {
        // given
        CarGenerator carGenerator = new CarGenerator(new Validator(), new Separator());
        RacingGame racingGame = new RacingGame();
        List<Car> CarList = carGenerator.createCars("joni,cobi,yun");
        int attempt = 5;

        // when & then
        racingGame.startRacing(carList, attempt);
        for (Car car : CarList) {
            Assertions.assertTrue(car.getWeight() >= 0);
            Assertions.assertTrue(car.getWeight() <= attempt);
        }

    }
}
