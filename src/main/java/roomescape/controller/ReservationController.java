package roomescape.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import roomescape.dto.ReservationRequest;
import roomescape.dto.ReservationResponse;
import roomescape.dto.TimeRequest;
import roomescape.dto.TimeResponse;
import roomescape.service.ReservationService;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations")
    @ResponseBody
    public ResponseEntity<List<ReservationResponse>> getReservation() {
        List<ReservationResponse> reservations = reservationService.readReservation();
        return ResponseEntity.ok().body(reservations);
    }

    @PostMapping("/reservations")
    @ResponseBody
    public ResponseEntity<ReservationResponse> addReservation(@RequestBody ReservationRequest reservationRequest) {
        ReservationResponse response = reservationService.registerReservation(reservationRequest);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.removeReservation(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/times")
    @ResponseBody
    public ResponseEntity<List<TimeResponse>> getReservationTime() {
        List<TimeResponse> reservationTimes = reservationService.readTime();
        return ResponseEntity.ok().body(reservationTimes);
    }

    @PostMapping("/times")
    @ResponseBody
    public ResponseEntity<TimeResponse> addReservationTime(@RequestBody TimeRequest timeRequest) {
        TimeResponse newTimeResponse = reservationService.registerTime(timeRequest);
        return ResponseEntity.ok().body(newTimeResponse);
    }

    @DeleteMapping("/times/{id}")
    public ResponseEntity<Void> deleteReservationTime(@PathVariable Long id) {
        reservationService.removeTime(id);
        return ResponseEntity.ok().build();
    }
}
