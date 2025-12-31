package baseball.service;

import baseball.domain.Number;

public class GameService {
    private final NumberGenerator numberGenerator;

    public GameService(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public Number initializeComputer(){
        return numberGenerator.generateNumber();
    }

}
