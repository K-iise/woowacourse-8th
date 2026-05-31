package roomescape.domain.exception;

public class ConflictException extends RoomescapeException {
    public ConflictException(ErrorCode errorCode) {
        super(errorCode);
    }
}
