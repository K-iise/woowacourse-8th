package racingcar.service;

import java.util.List;

public class Separator {
    public List<String> parseCarNames(String input) {
        List<String> carNames = List.of(input.split(","));
        return carNames;
    }
}
