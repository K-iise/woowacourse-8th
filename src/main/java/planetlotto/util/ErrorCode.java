package planetlotto.util;

public enum ErrorCode {

    PRICE_ERROR("로또 1장의 구입 단위는 500원 입니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
