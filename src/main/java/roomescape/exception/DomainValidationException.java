package roomescape.exception;

public class DomainValidationException extends RoomescapeException {
    public DomainValidationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
