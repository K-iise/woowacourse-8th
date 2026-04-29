package roomescape.dto;

import java.time.LocalTime;

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
}
