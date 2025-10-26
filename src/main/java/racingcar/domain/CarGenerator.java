package racingcar.domain;

import java.util.ArrayList;
import java.util.List;
import racingcar.service.Validator;

public class CarGenerator {

    private Validator validator;

    public CarGenerator(Validator validator) {
        this.validator = validator;
    }

    public List<Car> createCars(String input) {
        List<String> carNames = List.of(input.split(","));
        validator.checkDuplicateName(carNames);
        validator.checkOverLength(carNames);
        validator.checkEmptyName(carNames);

        List<Car> carList = new ArrayList<>();

        for (String carName : carNames) {
            carList.add(new Car(carName));
        }

        return carList;
    }
}
