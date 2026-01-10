package planetlotto.util;

public enum ErrorCode {

    PRICE_ERROR("로또 1장의 구입 단위는 500원 입니다."),
    RANGE_ERROR("로또 번호는 1부터 30 사이의 숫자여야 합니다."),
    WINNING_COUNT_ERROR("당첨 번호 5개를 입력해주세요."),
    DUPLICATION_WINNING_ERROR("당첨 번호는 중복될 수 없습니다."),
    DUPLICATION_BONUS_ERROR("보너스 번호는 중복될 수 없습니다.");
    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
