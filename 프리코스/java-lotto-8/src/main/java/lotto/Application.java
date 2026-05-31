package lotto;

import lotto.controller.LottoController;
import lotto.service.LottoGenerator;
import lotto.service.RandomLottoGenerator;
import lotto.service.LottoService;
import lotto.service.Parser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoGenerator lottoGenerator = new RandomLottoGenerator();
        Parser parser = new Parser();
        LottoService lottoService =  new LottoService(lottoGenerator, parser);
        LottoController lottoController = new LottoController(inputView, outputView, lottoService);

        lottoController.run();
    }
}
