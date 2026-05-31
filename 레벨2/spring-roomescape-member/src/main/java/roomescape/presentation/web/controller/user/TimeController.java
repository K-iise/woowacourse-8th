package roomescape.presentation.web.controller.user;

import java.time.LocalDate;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import roomescape.application.service.TimeService;
import roomescape.presentation.web.dto.TimeResponse;

@RestController
@RequestMapping("/times")
public class TimeController {

    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping
    public ResponseEntity<List<TimeResponse>> readAllByThemeIdAndDate(@RequestParam Long themeId,
                                                                      @RequestParam LocalDate date) {
        List<TimeResponse> reservationTimes = timeService.readAllByThemeIdAndDate(themeId, date).stream()
                .map(TimeResponse::from)
                .toList();
        return ResponseEntity.ok().body(reservationTimes);
    }
}
