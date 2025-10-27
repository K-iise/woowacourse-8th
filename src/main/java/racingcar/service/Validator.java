package racingcar.service;

import java.util.List;

public class Validator {

    private final String InVALID_NAME_LENGTH_MESSAGE = "이름은 5자를 초과할 수 없습니다.";

    public void checkOverLength(List<String> names) {
        if (names.stream().anyMatch(name -> name.length() > 5)) {
            throw new IllegalArgumentException(InVALID_NAME_LENGTH_MESSAGE);
        }
    }

    public void checkDuplicateName(List<String> names) {
        if (names.stream().distinct().count() != names.size()) {
            throw new IllegalArgumentException("이름은 중복될 수 없습니다.");
        }
    }

    public void checkEmptyName(List<String> names) {
        if (names.stream().anyMatch(String::isBlank)) {
            throw new IllegalArgumentException("이름은 공백이 될 수 없습니다,");
        }
    }

    public void checkInteger(String attempt) {
        try {
            Integer.parseInt(attempt);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("시도 횟수는 숫자를 입력해야 합니다.");
        }
    }

    public void checkPositive(String attempt) {
        if (Integer.parseInt(attempt) <= 0) {
            throw new IllegalArgumentException("시도 횟수는 양수여야 합니다.");
        }
    }


    public void validateAll(List<String> names) {
        checkEmptyName(names);
        checkDuplicateName(names);
        checkOverLength(names);
    }
}
