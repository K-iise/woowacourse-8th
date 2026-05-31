package planetlotto.model;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    public void 로또_당첨_개수_정상_테스트(){
        Lotto lotto = new Lotto(List.of(1,2,3,4,5));
        Lotto winning = new Lotto(List.of(1,2,3,7,8));

        Assertions.assertThat(lotto.matchCount(winning)).isEqualTo(3);
    }
}
