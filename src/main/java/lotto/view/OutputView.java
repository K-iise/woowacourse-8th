package lotto.view;

import java.util.EnumMap;
import lotto.domain.Lotto;
import lotto.domain.LottoPurchase;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Rank;

public class OutputView {

    public static final String PURCHASE_MESSAGE = "개를 구매했습니다.";

    public void printLottoPurchase(LottoPurchase lottoPurchase) {
        System.out.println("\n" + lottoPurchase.getLottoCount() + PURCHASE_MESSAGE);
    }

    public void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.toString());
        }
        System.out.println();
    }

    public void printWinningStat(LottoResult lottoResult) {
        EnumMap<Rank, Integer> stat = lottoResult.getWinningEnumMap();
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000원) - " + stat.get(Rank.FIFTH) + "개");
        System.out.println("4개 일치 (50,000원) - " + stat.get(Rank.FOURTH) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + stat.get(Rank.THIRD) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + stat.get(Rank.SECOND) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + stat.get(Rank.FIRST) + "개");
    }

    public void printProfitRate(double prate) {
        System.out.printf("총 수익률은 %3.1f%%입니다.", prate);
    }

    public void printError(String message) {
        System.out.println(message);
    }
}
