package baseball.view;

import baseball.domain.Number;
import baseball.domain.Result;
import java.util.List;

public class OutputView {

    public void printProcessResult(Result result){
        if (result.getBall() == 0 && result.getStrike() == 0){
            System.out.println("낫싱");
        }
        if (result.getBall() != 0){
            System.out.print(result.getBall() + "볼 ");
        }
        if (result.getStrike() != 0){
            System.out.print(result.getStrike() + "스트라이크");
        }
    }

    public void printFinalResult(){
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }

}
