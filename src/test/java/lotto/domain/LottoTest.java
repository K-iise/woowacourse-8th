package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호는_오름차순으로_정렬된다() {
        Lotto lotto = new Lotto(List.of(8, 3, 15, 1, 42, 7));
        Assertions.assertThat(lotto.getNumbers())
                .containsExactly(1, 3, 7, 8, 15, 42);
    }

    @Test
    void 로또_번호의_범위가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 46, -1, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_발행_정상_테스트() {
        Assertions.assertThatCode(() -> new Lotto()).doesNotThrowAnyException();
    }


}
