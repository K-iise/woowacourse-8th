package racingcar.view;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;

public class OutputView {

    private final String STEP = "-";

    public void printProcess(List<Car> cars) {
        cars.forEach(car -> System.out.println(car.getName() + " : " + STEP.repeat(car.getWeight())));
    }

    public void printWinner(List<Car> cars) {
        String winners = cars.stream().map(Car::getName).collect(Collectors.joining(", "));
        System.out.print("최종 우승자 : " + winners);
    }
}
