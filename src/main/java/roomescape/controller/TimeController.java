package roomescape.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import roomescape.dto.TimeRequest;
import roomescape.dto.TimeResponse;
import roomescape.service.TimeService;

@Controller
public class TimeController {

    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping("/times")
    @ResponseBody
    public ResponseEntity<List<TimeResponse>> getReservationTime() {
        List<TimeResponse> reservationTimes = timeService.readTimeAll();
        return ResponseEntity.ok().body(reservationTimes);
    }

    @PostMapping("/times")
    @ResponseBody
    public ResponseEntity<TimeResponse> addReservationTime(@RequestBody TimeRequest timeRequest) {
        TimeResponse newTimeResponse = timeService.registerTime(timeRequest);
        return ResponseEntity.ok().body(newTimeResponse);
    }

    @DeleteMapping("/times/{id}")
    public ResponseEntity<Void> deleteReservationTime(@PathVariable Long id) {
        timeService.removeTime(id);
        return ResponseEntity.ok().build();
    }
}
