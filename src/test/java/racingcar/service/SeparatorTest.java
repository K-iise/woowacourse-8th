package racingcar.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class SeparatorTest {

    @Test
    public void 자동차_이름_구분() {
        // given
        String input = "pobi,woni,jun";
        List<String> testNames = new ArrayList<>(Arrays.asList("pobi", "woni", "jun"));
        Separator separator = new Separator();

        // when
        List<String> CarNames = separator.parseCarNames(input);

        // then
        Assertions.assertThat(CarNames).isEqualTo(testNames);
    }
}
