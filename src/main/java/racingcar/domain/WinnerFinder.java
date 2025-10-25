package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;

public class WinnerFinder {
    public List<Car> findWinners(List<Car> cars) {
        int max = getMaxWeight(cars);
        return cars.stream().filter(car -> car.getWeight() == max).collect(Collectors.toList());
    }

    private int getMaxWeight(List<Car> cars) {
        return cars.stream().mapToInt(Car::getWeight).max().orElse(0);
    }
}
