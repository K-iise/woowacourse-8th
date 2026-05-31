package roomescape.application.dto;

import java.time.LocalDate;
import roomescape.domain.model.Reservation;

public record ReservationResult(Long id, String name, LocalDate date, TimeResult time, ThemeResult theme) {

    public static ReservationResult from(Reservation reservation) {
        return new ReservationResult(
                reservation.getId(),
                reservation.getName(),
                reservation.getDate(),
                TimeResult.from(reservation.getTime()),
                ThemeResult.from(reservation.getTheme())
        );
    }
}
