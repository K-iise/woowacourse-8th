package roomescape.presentation.web.dto;

import java.time.LocalTime;
import roomescape.domain.model.ReservationTime;

public record TimeResponse(Long id, LocalTime startAt) {

    public static TimeResponse from(ReservationTime reservationTime) {
        return new TimeResponse(reservationTime.getId(), reservationTime.getStartAt());
    }
}
