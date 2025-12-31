package baseball.service;

public class Validator {

    private final String range = "^[0-9]*$";

    public void checkNumberLength(String numbers){
        if (numbers.length() != 3){
            throw new IllegalArgumentException("[Error] 입력된 숫자의 길이가 3이 아닙니다.");
        }
    }

    public void checkNumberInteger(String numbers){
        for (int i = 0; i < numbers.length(); i++) {
            int number = numbers.charAt(i) - '0';
            if (!(number > 0 && number < 9)){
                throw new IllegalArgumentException("[Error] 입력된 숫자의 범위는 1~9여야 합니다.");
            }
        }
    }
}
