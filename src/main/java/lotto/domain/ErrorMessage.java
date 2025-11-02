package lotto.domain;

public enum ErrorMessage {
    AMOUNT_MUST_BE_NUMBER("[ERROR] 구입 금액은 숫자여야 합니다."),
    BONUS_MUST_BE_NUMBER("[ERROR] 보너스 번호는 숫자여야 합니다."),
    LOTTO_MUST_BE_NUMBER("[ERROR] 당첨 번호는 숫자여야 합니다."),
    AMOUNT_MUST_BE_POSITIVE("[ERROR] 구입 금액은 0원보다 커야 합니다."),
    AMOUNT_MUST_BE_DIVISIBLE("[ERROR] 구입 금액은 1,000원 단위로 입력받아야 합니다."),
    LOTTO_MUST_HAVE_SIX_NUMBERS("[ERROR] 로또 번호는 6개여야 합니다."),
    LOTTO_NUMBERS_MUST_BE_UNIQUE("[ERROR] 로또 번호는 중복될 수 없습니다."),
    LOTTO_NUMBER_OUT_OF_RANGE("[ERROR] 로또 번호는 1 ~ 45 범위에 속해야 합니다."),
    BONUS_NUMBER_OUT_OF_RANGE("[ERROR] 보너스 번호는 1 ~ 45 범위에 속해야 합니다."),
    BONUS_MUST_BE_UNIQUE("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
