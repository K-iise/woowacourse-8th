package racingcar.domain;

import java.util.List;
import racingcar.service.Separator;
import racingcar.service.Validator;

public class CarGenerator {

    private final Validator validator;
    private final Separator separator;

    public CarGenerator(Validator validator, Separator separator) {
        this.validator = validator;
        this.separator = separator;
    }

    public List<Car> createCars(String input) {
        List<String> carNames = separator.parseCarNames(input);
        validator.validateAll(carNames);

        return carNames.stream().map(Car::new).toList();
    }
}
