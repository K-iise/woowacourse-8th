package baseball.domain;

import java.util.List;

public class Number {
    private final List<Integer> numbers;

    public Number(List<Integer> numbers){
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public Result compare(Number computer){
        List<Integer> comNumbers = computer.getNumbers();
        Result result = new Result();

        for (int i = 0; i < 3; i++) {
            int num = numbers.get(i);
            if (comNumbers.get(i) == num) {
                result.addStrike();
                continue;
            }
            if (numbers.contains(comNumbers.get(i))){
                result.addBall();
            }
        }
        return result;
    }
}
