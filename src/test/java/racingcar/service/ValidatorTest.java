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

    @Test
    public void 자동차_이름_중복_검사_예외_발생() {
        // given
        List<String> name = new ArrayList<>(List.of("pobi", "pobi", ""));
        Validator validator = new Validator();

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            validator.checkDuplicateName(names);
        });
    }

    @Test
    public void 자동자_이름_중복_검사_통과() {
        // given
        List<String> names = new ArrayList<>(List.of("pobi", "woni", "jun"));
        Validator validator = new Validator();

        // when & then
        Assertions.assertDoesNotThrow(() -> {
            validator.checkDuplicateName(name);
        });
    }
}
