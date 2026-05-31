package lotto.view;

import java.util.EnumMap;
import lotto.domain.Lotto;
import lotto.domain.LottoPurchase;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Rank;

public class OutputView {

    public static final String PURCHASE_MESSAGE = "개를 구매했습니다.";
    public static final String STAT_HEADER = "당첨 통계\n---";
    private static final String STAT_FORMAT = "%d개 일치%s (%s원) - %d개";
    private static final String PROFIT_FORMAT = "총 수익률은 %.1f%%입니다.";
    private static final String BONUS_MATCH_SUFFIX = ", 보너스 볼 일치";

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

        System.out.println(STAT_HEADER);

        for (Rank rank : Rank.values()) {
            if (rank == Rank.MISS) {
                continue;
            }

            String description = getDescription(rank);
            int count = stat.getOrDefault(rank, 0);

            System.out.printf((STAT_FORMAT) + "\n",
                    rank.getCount(),
                    description,
                    formatValue(rank.getValue()),
                    count);
        }
    }

    private String getDescription(Rank rank) {
        if (rank == Rank.SECOND) {
            return BONUS_MATCH_SUFFIX;
        }
        return "";
    }

    private String formatValue(int value) {
        return String.format("%,d", value);
    }

    public void printProfitRate(double prate) {
        System.out.printf(PROFIT_FORMAT, prate);
    }

    public void printError(String message) {
        System.out.println(message);
    }
}
