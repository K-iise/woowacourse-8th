package racingcar;

import racingcar.controller.RacingController;
import racingcar.domain.CarGenerator;
import racingcar.domain.RacingGame;
import racingcar.domain.WinnerFinder;
import racingcar.service.RandomGenerator;
import racingcar.service.Separator;
import racingcar.service.Validator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        RacingController racingController = new RacingController(
                new InputView(),
                new OutputView(),
                new CarGenerator(new Validator(), new Separator()),
                new RacingGame(new RandomGenerator()),
                new WinnerFinder()
        );
        racingController.run();
    }
}
