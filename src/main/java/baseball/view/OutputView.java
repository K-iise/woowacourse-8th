package baseball.view;

import baseball.domain.Number;
import baseball.domain.Result;
import java.util.List;

public class OutputView {

    public void printResult(Result result){
        System.out.println(result.getBall() + "볼" + " " + result.getStrike() +"스트라이크");
    }

    public void printComputer(Number number){
        List<Integer> computer = number.getNumbers();

        System.out.print("컴퓨터 숫자 : ");
        for (Integer integer : computer) {
            System.out.print(integer);
        }
        System.out.println();
    }
}
