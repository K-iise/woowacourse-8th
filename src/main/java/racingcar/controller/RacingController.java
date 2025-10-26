package racingcar.controller;

import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.CarGenerator;
import racingcar.domain.RacingGame;
import racingcar.domain.WinnerFinder;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingController {

    private InputView inputView;
    private OutputView outputView;
    private CarGenerator carGenerator;
    private RacingGame racingGame;
    private WinnerFinder winnerFinder;

    public RacingController(InputView inputView, OutputView outputView, CarGenerator carGenerator,
                            RacingGame racingGame,
                            WinnerFinder winnerFinder) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.carGenerator = carGenerator;
        this.racingGame = racingGame;
        this.winnerFinder = winnerFinder;
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
        System.out.println();
        System.out.println("실행 결과");
        for (int i = 0; i < attempt; i++) {
            racingGame.startRacing(carList);
            outputView.printProcess(carList);
            System.out.println();
        }
    }

    private int getAttempt() {
        return Integer.parseInt(inputView.getTryCount());
    }

    private List<Car> createCars() {
        return carGenerator.createCars(inputView.getCarName());
    }
}
