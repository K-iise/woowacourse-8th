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

    public void checkDuplicateName(List<String> name) {
        if (name.stream().distinct().count() != name.size()) {
            throw new IllegalArgumentException("이름은 중복될 수 없습니다.");
        }
    }

    public void checkEmptyName(List<String> name) {
        for (String checkName : name) {
            if (checkName.isBlank()) {
                throw new IllegalArgumentException("이름은 공백이 될 수 없습니다,");
            }
        }
    }
}
