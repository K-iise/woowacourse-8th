package roomescape.application.dto;

import java.time.LocalDate;

public record ReservationCommand(String name, LocalDate date, Long timeId, Long themeId) {
}
