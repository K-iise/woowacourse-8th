package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;

public class LottoService {

    public List<Lotto> generateLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto());
        }
        return List.copyOf(lottos);
    }
}
