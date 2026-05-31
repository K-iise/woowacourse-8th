package racingcar.service;

import java.util.List;

public class Separator {
    public List<String> parseCarNames(String input) {
        return List.of(input.split(","));
    }
}
