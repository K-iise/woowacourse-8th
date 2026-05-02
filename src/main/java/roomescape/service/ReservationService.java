package roomescape.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.dto.ReservationRequest;
import roomescape.dto.ReservationResponse;
import roomescape.dto.TimeRequest;
import roomescape.dto.TimeResponse;
import roomescape.model.Reservation;
import roomescape.model.ReservationTime;
import roomescape.repository.ReservationRepository;
import roomescape.repository.TimeRepository;

@Service
@Transactional
public class ReservationService {

    private ReservationRepository reservationRepository;

    private TimeRepository timeRepository;

    public ReservationService(ReservationRepository reservationRepository, TimeRepository timeRepository) {
        this.reservationRepository = reservationRepository;
        this.timeRepository = timeRepository;
    }

    public List<ReservationResponse> readReservation() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(ReservationResponse::from)
                .collect(Collectors.toList());
    }

    public void removeReservation(Long id) {
        reservationRepository.remove(id);
    }

    public ReservationResponse registerReservation(ReservationRequest reservationRequest) {
        Reservation reservation = reservationRepository.register(reservationRequest.getName(),
                reservationRequest.getDate(), reservationRequest.getTimeId());
        return ReservationResponse.from(reservation);
    }

    public List<TimeResponse> readTime() {
        List<ReservationTime> times = timeRepository.findAll();
        return times.stream()
                .map(TimeResponse::from)
                .collect(Collectors.toList());
    }

    public void removeTime(Long id) {
        timeRepository.remove(id);
    }

    public TimeResponse registerTime(TimeRequest timeRequest) {
        ReservationTime reservationTime = timeRepository.saveTime(timeRequest.getStartAt());
        return TimeResponse.from(reservationTime);
    }

}
