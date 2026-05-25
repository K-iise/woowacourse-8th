package roomescape.presentation.web.dto;

import java.time.LocalTime;
import roomescape.application.dto.TimeResult;

public record TimeResponse(Long id, LocalTime startAt) {

    public static TimeResponse from(TimeResult result) {
        return new TimeResponse(result.id(), result.startAt());
    }
}
