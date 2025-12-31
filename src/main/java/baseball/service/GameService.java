package baseball.service;

import baseball.domain.Number;
import baseball.domain.Result;

public class GameService {
    private final NumberGenerator numberGenerator;
    private final Separator separator;
    private final Validator validator;

    public GameService(NumberGenerator numberGenerator, Separator separator, Validator validator) {
        this.numberGenerator = numberGenerator;
        this.separator = separator;
        this.validator = validator;
    }

    public Number initializeComputer(){
        return numberGenerator.generateNumber();
    }

    private Number getNumber(String numbers){
        validator.validateNumber(numbers);
        return new Number(separator.getNumberList(numbers));
    }

    public Result gameProcess(Number computer, String numbers){
        Number number = getNumber(numbers);
        return number.compare(computer);
    }

    public boolean gameJudge(Result result){
        return result.getStrike() == 3;
    }

}
