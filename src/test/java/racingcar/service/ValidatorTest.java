package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidatorTest {

    @Test
    public void 이름_5자_초과_예외_발생() {
        // given
        List<String> names = new ArrayList<>(List.of("pobias", "woniss", "junjss"));
        Validator validator = new Validator();

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            validator.checkOverLength(names);
        });
    }

    @Test
    public void 이름_5자_이하_정상_통과() {
        // given
        List<String> names = new ArrayList<>(List.of("pobi", "woni", "jun"));
        Validator validator = new Validator();

        // when & then
        Assertions.assertDoesNotThrow(() -> {
            validator.checkOverLength(names);
        });
    }
}
