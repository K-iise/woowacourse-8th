package racingcar.domain;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import racingcar.service.Validator;

public class CarGeneratorTest {
    @Test
    public void 자동차_생성() {
        // given
        CarGenerator carGenerator = new CarGenerator(new Validator());
        String cars = "cobi,james,top";
        List<Car> testList = List.of(new Car("cobi"), new Car("james"), new Car("top"));

        // when
        List<Car> carList = carGenerator.createCars(cars);

        // then
        Assertions.assertEquals(testList, carList);
    }

    @Test
    public void 자동차_생성_예외_발생() {
        // given
        CarGenerator carGenerator = new CarGenerator(new Validator());
        String cars = "cobias,james,top";
        List<Car> testList = List.of(new Car("cobias"), new Car("james"), new Car("top"));

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> carGenerator.createCars(cars));
    }
}

