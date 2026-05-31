package roomescape.presentation.web.controller.admin;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roomescape.application.service.TimeService;
import roomescape.presentation.web.dto.TimeRequest;
import roomescape.presentation.web.dto.TimeResponse;

@RestController
@RequestMapping("/admin/times")
public class AdminTimeController {

    private final TimeService timeService;

    public AdminTimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @PostMapping
    public ResponseEntity<TimeResponse> register(@Valid @RequestBody TimeRequest timeRequest) {
        TimeResponse timeResponse = TimeResponse.from(timeService.register(timeRequest.toCommand()));
        return ResponseEntity.created(URI.create("/times/" + timeResponse.id())).body(timeResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeById(@PathVariable Long id) {
        timeService.removeById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TimeResponse>> readAll() {
        List<TimeResponse> reservationTimes = timeService.readAll().stream()
                .map(TimeResponse::from)
                .toList();
        return ResponseEntity.ok().body(reservationTimes);
    }
}
