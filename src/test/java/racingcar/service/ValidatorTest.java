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
        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.checkOverLength(names));
    }

    @Test
    public void 이름_5자_이하_정상_통과() {
        // given
        List<String> names = new ArrayList<>(List.of("pobi", "woni", "jun"));
        Validator validator = new Validator();

        // when & then
        Assertions.assertDoesNotThrow(() -> validator.checkOverLength(names));
    }

    @Test
    public void 자동차_이름_중복_검사_예외_발생() {
        // given
        List<String> names = new ArrayList<>(List.of("pobi", "pobi", ""));
        Validator validator = new Validator();

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.checkDuplicateName(names));
    }

    @Test
    public void 자동자_이름_중복_검사_통과() {
        // given
        List<String> names = new ArrayList<>(List.of("pobi", "woni", "jun"));
        Validator validator = new Validator();

        // when & then
        Assertions.assertDoesNotThrow(() -> validator.checkDuplicateName(names));
    }

    @Test
    public void 자동차_이름_공백_검사_예외_발생() {
        // given
        List<String> names = new ArrayList<>(List.of("pobi", "", "jun"));
        Validator validator = new Validator();

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.checkEmptyName(names));
    }

    @Test
    public void 자동차_이름_공백_검사_통과() {
        // given
        List<String> names = new ArrayList<>(List.of("pobi", "fnd", "jun"));
        Validator validator = new Validator();

        // when & then
        Assertions.assertDoesNotThrow(() -> validator.checkEmptyName(names));
    }

    @Test
    public void 시도_횟수_숫자_여부_예외_발생() {
        // given
        String attempt = "a";
        Validator validator = new Validator();

        // when
        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.checkInteger(attempt));
    }

    @Test
    public void 시도_횟수_숫자_여부_통과() {
        // given
        String attempt = "5";
        Validator validator = new Validator();

        // when
        Assertions.assertDoesNotThrow(() -> validator.checkInteger(attempt));
    }

    @Test
    public void 시도_횟수_0이하_예외_발생() {
        // given
        String attempt = "0";
        Validator validator = new Validator();

        // when
        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.checkPositive(attempt));
    }
}
