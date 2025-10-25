package racingcar.domain;

import java.util.ArrayList;
import java.util.List;

public class WinnerFinder {
    public List<Car> findWinners(List<Car> cars) {

        int max = 0;
        for (Car car : cars) {
            if (car.getWeight() > max) {
                max = car.getWeight();
            }
        }
        List<Car> winners = new ArrayList<>();

        for (Car car : cars) {
            if (car.getWeight() == max) {
                winners.add(car);
            }
        }

        return winners;
    }
}
