package racingcar.domain;

import java.util.List;
import racingcar.service.RandomGenerator;

public class RacingGame {
    RandomGenerator randomGenerator;

    public RacingGame(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public void startRacing(List<Car> carList) {
        carList.forEach(car -> car.addWeight(randomGenerator.getRandomNumber()));
    }
}
