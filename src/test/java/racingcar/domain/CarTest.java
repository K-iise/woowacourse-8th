package racingcar.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CarTest {
    @Test
    public void 랜덤값_4이상_전진() {
        // given
        Car car = new Car("yun");

        // when
        car.addWeight(4);

        // then
        Assertions.assertThat(car.getWeight()).isEqualTo(1);
    }

    @Test
    public void 랜덤값_4미만_정지() {
        // given
        Car car = new Car("yun");

        // when
        car.addWeight(3); // 4 이상이면 이동

        // then
        Assertions.assertThat(car.getWeight()).isEqualTo(1);
    }
}
