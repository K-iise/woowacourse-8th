package baseball.controller;

import baseball.domain.Number;
import baseball.domain.Result;
import baseball.service.GameService;
import baseball.service.Separator;
import baseball.service.Validator;
import baseball.view.InputView;
import baseball.view.OutputView;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final GameService gameService;

    public GameController(InputView inputView, OutputView outputView, GameService gameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameService = gameService;
    }

    public void run(){
        // 게임 초기화
        Number computer = gameService.initializeComputer();

        while (true){
            // 게임 시작 판단
            Result result = gameService.gameProcess(computer, inputView.getNumbers());

            outputView.printProcessResult(result);

            // 게임 종료 판단
            if (gameService.gameJudge(result)){
                outputView.printFinalResult();
                break;
            }
        }

        // 재시작 판단
        int start = Integer.parseInt(inputView.getStart());
        if (start == 1){
            run();
        }
    }


}
