package roomescape.presentation.web.dto;

import java.time.LocalDate;
import roomescape.domain.model.Reservation;

public record ReservationResponse(Long id, String name, LocalDate date, TimeResponse time, ThemeResponse theme) {

    public static ReservationResponse from(Reservation reservation) {
        return new ReservationResponse(reservation.getId(), reservation.getName(), reservation.getDate(),
                TimeResponse.from(reservation.getTime()), ThemeResponse.from(reservation.getTheme()));
    }
}
