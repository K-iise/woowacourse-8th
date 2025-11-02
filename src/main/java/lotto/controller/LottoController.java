package lotto.controller;

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
        String purchase = inputView.readPurchaseAmount();
        LottoPurchase lottoPurchase = lottoService.createPurchase(purchase);
        Lottos lottos = lottoService.createLottos(lottoPurchase);

        outputView.printLottoPurchase(lottoPurchase);
        outputView.printLottos(lottos);

        String numbers = inputView.readWinningNumbers();
        System.out.println();
        String bonus = inputView.readBonusNumber();
        WinningLotto winningLotto = lottoService.createWinningLotto(numbers, bonus);

        LottoResult lottoResult = lottoService.createLottoResult(lottos, winningLotto);
        outputView.printWinningStat(lottoResult);
        outputView.printProfitRate(lottoService.getProfit(lottoPurchase, lottoResult));
    }
}
