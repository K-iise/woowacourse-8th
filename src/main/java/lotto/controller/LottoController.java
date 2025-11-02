package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoPurchase;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;
import lotto.service.LottoService;
import lotto.service.Parser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        // 1. 로또 구매
        LottoPurchase lottoPurchase = loopPurchase();
        Lottos lottos = lottoService.createLottos(lottoPurchase);

        outputView.printLottoPurchase(lottoPurchase);
        outputView.printLottos(lottos);

        // 2. 당첨 로또 생성
        WinningLotto winningLotto = loopWinning();

        // 3. 당첨 결과 계산
        LottoResult lottoResult = lottoService.createLottoResult(lottos, winningLotto);
        outputView.printWinningStat(lottoResult);
        outputView.printProfitRate(lottoService.getProfit(lottoPurchase, lottoResult));
    }

    private LottoPurchase loopPurchase() {
        while (true) {
            try {
                String input = inputView.readPurchaseAmount();
                return lottoService.createPurchase(input);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private Lotto loopLotto() {
        while (true) {
            try {
                String winNumbers = inputView.readWinningNumbers();
                System.out.println();
                return lottoService.createLotto(winNumbers);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private WinningLotto loopWinning() {
        Lotto lotto = loopLotto();
        while (true) {
            try {
                String bonusInput = inputView.readBonusNumber();
                System.out.println();
                Parser parser = new Parser();
                int bonusNumber = parser.parseBonusNumber(bonusInput);
                return lottoService.createWinningLotto(lotto, bonusNumber);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
