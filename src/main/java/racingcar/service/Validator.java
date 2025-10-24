package racingcar.service;

import java.util.List;

public class Validator {

    private final String InVALID_NAME_LENGTH_MESSAGE = "이름은 5자를 초과할 수 없습니다.";

    public void checkOverLength(List<String> names) {
        for (String name : names) {
            validateNameLength(name);
        }
    }

    private void validateNameLength(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException(InVALID_NAME_LENGTH_MESSAGE);
        }
    }
}
