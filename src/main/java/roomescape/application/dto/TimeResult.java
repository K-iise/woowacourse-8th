package roomescape.application.dto;

import java.time.LocalTime;
import roomescape.domain.model.ReservationTime;

public record TimeResult(Long id, LocalTime startAt) {

    public static TimeResult from(ReservationTime reservationTime) {
        return new TimeResult(reservationTime.getId(), reservationTime.getStartAt());
    }
}
