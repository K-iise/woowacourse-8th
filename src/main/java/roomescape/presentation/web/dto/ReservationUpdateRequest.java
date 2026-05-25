package roomescape.presentation.web.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import roomescape.application.dto.ReservationUpdateCommand;

public record ReservationUpdateRequest(
        @NotNull(message = "예약 날짜는 필수입니다.")
        LocalDate date,

        @NotNull(message = "예약 시간은 필수입니다.")
        Long timeId) {

    public ReservationUpdateCommand toCommand() {
        return new ReservationUpdateCommand(date, timeId);
    }
}