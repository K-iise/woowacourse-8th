package baseball.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validator {

    private final String range = "^[0-9]*$";

    private void checkNumberLength(String numbers){
        if (numbers.length() != 3){
            throw new IllegalArgumentException("[Error] 입력된 숫자의 길이가 3이 아닙니다.");
        }
    }

    private void checkNumberInteger(String numbers){
        try{
            int number = Integer.parseInt(numbers);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[Error] 입력된 문자는 숫자여야 합니다.");
        }
    }

    private void checkNumberRange(String numbers){
        for (int i = 0; i < numbers.length(); i++) {
            int number = numbers.charAt(i) - '0';
            if (!(number > 0 && number < 9)){
                throw new IllegalArgumentException("[Error] 입력된 숫자의 범위는 1~9여야 합니다.");
            }
        }
    }

    private void checkNumberDuplication(String numbers){
        Set<Integer> number = new HashSet<>();
        for (int i = 0; i < numbers.length(); i++) {
            int num = numbers.charAt(i) - '0';
            number.add(num);
        }
        if (number.size() != 3){
            throw new IllegalArgumentException("[Error] 입력된 수는 중복될 수 없습니다.");
        }
    }

    public void validateNumber(String numbers){
        checkNumberLength(numbers);
        checkNumberInteger(numbers);
        checkNumberRange(numbers);
        checkNumberDuplication(numbers);
    }
}
