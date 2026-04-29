package roomescape.dto;

import java.time.LocalTime;
import roomescape.model.ReservationTime;

public class TimeResponse {
    private Long id;
    private LocalTime timeAt;

    public Long getId() {
        return id;
    }

    public LocalTime getTimeAt() {
        return timeAt;
    }

    public TimeResponse(Long id, LocalTime timeAt) {
        this.id = id;
        this.timeAt = timeAt;
    }

    public static TimeResponse from(ReservationTime reservationTime) {
        return new TimeResponse(reservationTime.getId(), reservationTime.getStartAt());
    }
}
