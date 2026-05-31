package roomescape.domain.model;

import java.time.LocalDate;
import roomescape.domain.exception.DomainValidationException;
import roomescape.domain.exception.ErrorCode;

public class Reservation {

    private static final int MAX_RESERVATION_NAME_LENGTH = 20;

    private final Long id;
    private final String name;
    private final LocalDate date;
    private final ReservationTime time;
    private final Theme theme;

    public Reservation(Long id, String name, LocalDate date, ReservationTime time, Theme theme) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.theme = theme;
        validateAll();
    }

    private void validateAll() {
        validateName();
        validateDate();
        validateTime();
        validateTheme();
    }

    private void validateName() {
        if (name == null || name.isBlank()) {
            throw new DomainValidationException(ErrorCode.RESERVATION_NAME_BLANK);
        }
        if (name.length() > MAX_RESERVATION_NAME_LENGTH) {
            throw new DomainValidationException(ErrorCode.RESERVATION_NAME_LENGTH_INVALID);
        }
    }

    private void validateDate() {
        if (date == null) {
            throw new DomainValidationException(ErrorCode.RESERVATION_DATE_NULL);
        }
    }

    private void validateTime() {
        if (time == null) {
            throw new DomainValidationException(ErrorCode.RESERVATION_TIME_NULL);
        }
    }

    private void validateTheme() {
        if (theme == null) {
            throw new DomainValidationException(ErrorCode.RESERVATION_THEME_NULL);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public ReservationTime getTime() {
        return time;
    }

    public Theme getTheme() {
        return theme;
    }

}
