package lotto.view;

import java.util.EnumMap;
import lotto.domain.Lotto;
import lotto.domain.LottoPurchase;
import lotto.domain.LottoResult;
import lotto.domain.LottoReward;
import lotto.domain.Lottos;

public class OutputView {

    public static final String PURCHASE_MESSAGE = "를 구매했습니다.";

    public void printLottoPurchase(LottoPurchase lottoPurchase) {
        System.out.println(lottoPurchase.getLottoCount() + PURCHASE_MESSAGE);
    }

    public void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.toString());
        }
    }

    public void printWinningStat(LottoResult lottoResult) {
        EnumMap<LottoReward, Integer> stat = lottoResult.getWinningEnumMap();
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000원) - " + stat.get(LottoReward.FIFTH));
        System.out.println("4개 일치 (50,000원) - " + stat.get(LottoReward.FOURTH));
        System.out.println("5개 일치 (1,500,000원) - " + stat.get(LottoReward.THIRD));
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + stat.get(LottoReward.SECOND));
        System.out.println("6개 일치 (2,000,000,000원) - " + stat.get(LottoReward.FIRST));
    }

    public void printProfitRate(double prate) {
        System.out.printf("총 수익률은 %3.1f%%입니다.", prate);
    }
}
