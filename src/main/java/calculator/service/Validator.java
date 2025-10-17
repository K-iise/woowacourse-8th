package calculator.service;

public class Validator {

    /**
     * 빈 문자열 및 공백 처리
     */
    public boolean IsBlank(String text){
        return text == null || text.isBlank();
    }

    /**
     * 숫자 판별
     */
    public boolean IsInteger(String candidate){
        if (!IsBlank(candidate))
            return candidate.matches("^\\d+$");
        return false;
    }

    public boolean IsException(String text){
        if (!IsInteger(text)) {
            throw new IllegalArgumentException("입력은 양의 정수여야 합니다.");
        }
        return false;
    }
}
