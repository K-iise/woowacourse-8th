package roomescape.presentation.web.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import roomescape.application.dto.TimeCommand;

public record TimeRequest(
        @NotNull(message = "예약 시작 시간은 필수입니다.")
        LocalTime startAt) {

    public TimeCommand toCommand() {
        return new TimeCommand(startAt);
    }
}
