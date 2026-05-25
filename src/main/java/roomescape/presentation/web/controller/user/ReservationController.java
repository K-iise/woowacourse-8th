package roomescape.presentation.web.controller.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import roomescape.application.service.ReservationService;
import roomescape.presentation.web.dto.ReservationRequest;
import roomescape.presentation.web.dto.ReservationResponse;
import roomescape.presentation.web.dto.ReservationUpdateRequest;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> register(@Valid @RequestBody ReservationRequest reservationRequest) {
        ReservationResponse reservationResponse = ReservationResponse.from(
                reservationService.register(reservationRequest.toCommand()));
        return ResponseEntity.created(URI.create("/reservations/" + reservationResponse.id()))
                .body(reservationResponse);
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponse>> readAllByName(@NotBlank @RequestParam String username) {
        List<ReservationResponse> reservationResponses = reservationService.readAllByName(username).stream()
                .map(ReservationResponse::from)
                .toList();
        return ResponseEntity.ok(reservationResponses);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> cancelByIdAndName(@PathVariable Long id, @NotBlank @RequestParam String username) {
        reservationService.cancelByIdAndName(id, username);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<ReservationResponse> update(@PathVariable Long id,
                                                      @NotBlank @RequestParam String username,
                                                      @Valid @RequestBody ReservationUpdateRequest reservationUpdateRequest) {
        ReservationResponse reservationResponse = ReservationResponse.from(
                reservationService.update(id, username, reservationUpdateRequest.toCommand()));
        return ResponseEntity.ok().body(reservationResponse);
    }
}
