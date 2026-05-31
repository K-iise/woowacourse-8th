package roomescape.presentation.web.dto;

import java.time.LocalDate;
import roomescape.application.dto.ReservationResult;

public record ReservationResponse(Long id, String name, LocalDate date, TimeResponse time, ThemeResponse theme) {

    public static ReservationResponse from(ReservationResult result) {
        return new ReservationResponse(result.id(), result.name(), result.date(),
                TimeResponse.from(result.time()), ThemeResponse.from(result.theme()));
    }
}
