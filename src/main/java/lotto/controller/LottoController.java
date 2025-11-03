package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoPurchase;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;
import lotto.service.LottoService;
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
        LottoPurchase lottoPurchase = getValidPurchase();
        Lottos lottos = lottoService.buyLottos(lottoPurchase);

        outputView.printLottoPurchase(lottoPurchase);
        outputView.printLottos(lottos);

        // 2. 당첨 로또 생성
        WinningLotto winningLotto = getValidWinningLotto();

        // 3. 당첨 결과 계산
        LottoResult lottoResult = lottoService.calculateResult(lottos, winningLotto);
        outputView.printWinningStat(lottoResult);
        outputView.printProfitRate(lottoService.calculateProfitRate(lottoPurchase, lottoResult));
    }

    private LottoPurchase getValidPurchase() {
        while (true) {
            try {
                String input = inputView.readPurchaseAmount();
                return lottoService.parsePurchase(input);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private Lotto getValidWinningLottoNumbers() {
        while (true) {
            try {
                String winNumbers = inputView.readWinningNumbers();
                System.out.println();
                return lottoService.parseWinningLotto(winNumbers);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private WinningLotto getValidWinningLotto() {
        Lotto lotto = getValidWinningLottoNumbers();
        while (true) {
            try {
                String bonusInput = inputView.readBonusNumber();
                System.out.println();
                return lottoService.createWinningLotto(lotto, bonusInput);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
