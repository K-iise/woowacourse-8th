package baseball;

import baseball.controller.GameController;
import baseball.domain.Number;
import baseball.domain.Result;
import baseball.service.GameService;
import baseball.service.RandomNumberGenerator;
import baseball.service.Separator;
import baseball.service.Validator;
import baseball.view.InputView;
import baseball.view.OutputView;

public class Application {
    public static void main(String[] args) {
        GameController gameController = new GameController(new InputView(), new OutputView(), new GameService(
                new RandomNumberGenerator(), new Separator(), new Validator()
        ));

        gameController.run();

    }
}
