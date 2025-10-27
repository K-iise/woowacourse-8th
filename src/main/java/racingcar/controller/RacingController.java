package racingcar.controller;

import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.CarGenerator;
import racingcar.domain.RacingGame;
import racingcar.domain.WinnerFinder;
import racingcar.service.Validator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingController {

    private InputView inputView;
    private OutputView outputView;
    private CarGenerator carGenerator;
    private RacingGame racingGame;
    private WinnerFinder winnerFinder;
    private Validator validator;

    public RacingController(InputView inputView, OutputView outputView, CarGenerator carGenerator,
                            RacingGame racingGame,
                            WinnerFinder winnerFinder, Validator validator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.carGenerator = carGenerator;
        this.racingGame = racingGame;
        this.winnerFinder = winnerFinder;
        this.validator = validator;
    }

    public void run() {
        List<Car> carList = createCars();
        int attempt = getAttempt();

        playGame(attempt, carList);
        printWinner(carList);
    }

    private void printWinner(List<Car> carList) {
        List<Car> winnerList = winnerFinder.findWinners(carList);
        outputView.printWinner(winnerList);
    }

    private void playGame(int attempt, List<Car> carList) {
        outputView.printGameStart();
        for (int i = 0; i < attempt; i++) {
            racingGame.startRacing(carList);
            outputView.printProcess(carList);
        }
    }

    private int getAttempt() {
        String attemptInput = inputView.getTryCount();
        validator.validateNumberAll(attemptInput);
        return Integer.parseInt(attemptInput);
    }

    private List<Car> createCars() {
        return carGenerator.createCars(inputView.getCarName());
    }
}
