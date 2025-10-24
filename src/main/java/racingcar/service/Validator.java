package racingcar.service;

import java.util.List;

public class Validator {
    
    public void checkOverLength(List<String> names) {
        for (String name : names) {
            if (name.length() > 5) {
                throw new IllegalArgumentException("이름은 5자를 초과할 수 없습니다.");
            }
        }
    }
}
