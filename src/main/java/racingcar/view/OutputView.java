package racingcar.view;

import java.util.List;
import racingcar.domain.Car;

public class OutputView {

    private final String STEP = "-";

    public void printProcess(List<Car> cars) {
        cars.forEach(car -> System.out.println(car.getName() + " : " + STEP.repeat(car.getWeight())));
    }

    public void printWinner(List<Car> cars) {
        String result = "최종우승자 : ";
        for (int i = 0; i < cars.size(); i++) {
            if (cars.size() - 1 == i) {
                result += cars.get(i).getName();
            } else {
                result += cars.get(i).getName() + ", ";
            }
        }

        System.out.print(result);
    }
}
