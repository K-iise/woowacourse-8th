package baseball;

import baseball.domain.Number;
import baseball.service.GameService;
import baseball.service.RandomNumberGenerator;
import baseball.service.Validator;
import baseball.view.InputView;

public class Application {
    public static void main(String[] args) {
        //TODO: 숫자 야구 게임 구현

        // 사용자 입력
        InputView inputView = new InputView();
        String numbers = inputView.getNumbers();

        // 입력 검증
        Validator validator = new Validator();
        validator.validateNumber(numbers);

        // 게임 초기화(컴퓨터 숫자 등록)
        GameService gameService = new GameService(new RandomNumberGenerator());
        Number computer = gameService.initializeComputer();

    }
}
