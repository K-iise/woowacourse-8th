package baseball.service;

public class Validator {
    public void checkNumberLength(String numbers){
        if (numbers.length() != 3){
            throw new IllegalArgumentException("[Error] 입력된 숫자의 길이가 3이 아닙니다.");
        }
    }
    
}
