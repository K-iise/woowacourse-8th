package calculator.service;

public class Validator {

    public boolean IsBlank(String text){
        return text == null || text.isBlank();
    }

    public boolean IsInteger(String candidate){
        if (!IsBlank(candidate))
            return candidate.matches("^\\d+$");
        return false;
    }

    public boolean IsCharacter(String candidate){
        if(!IsBlank(candidate))
            return candidate.matches("^[a-zA-Z가-힣]+$");
        return false;
    }

    public int getValidateValue(String text){
        if (IsBlank(text)) return 0;

        if(IsCharacter(text)) throw new IllegalArgumentException("숫자만 입력해야 합니다.");

        if (!IsInteger(text)) throw new IllegalArgumentException("입력은 양의 정수 형태여야 합니다.");
        return Integer.parseInt(text);
    }

}
