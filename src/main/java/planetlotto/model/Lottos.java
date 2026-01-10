package planetlotto.model;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos fromLottoList(List<List<Integer>> lottosList){
        List<Lotto> lottoList = new ArrayList<>();
        for (List<Integer> list : lottosList) {
            lottoList.add(new Lotto(list));
        }
        return new Lottos(lottoList);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
