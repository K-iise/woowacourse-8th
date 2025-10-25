package racingcar.view;

import java.util.List;
import racingcar.domain.Car;

public class OutputView {

    private final String STEP = "-";

    public void printProcess(List<Car> cars) {
        cars.forEach(car -> System.out.println(car.getName() + " : " + STEP.repeat(car.getWeight())));
    }
}
