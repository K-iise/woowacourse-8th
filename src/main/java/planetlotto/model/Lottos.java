package planetlotto.model;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos fromLottoList(List<Lotto> lottoList){
        return new Lottos(lottoList);
    }

}
