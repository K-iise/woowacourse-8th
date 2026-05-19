package roomescape.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.dto.TimeRequest;
import roomescape.dto.TimeResponse;
import roomescape.exception.ConflictException;
import roomescape.exception.ErrorCode;
import roomescape.exception.NotFoundException;
import roomescape.exception.UnprocessableEntityException;
import roomescape.model.ReservationTime;
import roomescape.repository.ReservationRepository;
import roomescape.repository.ThemeRepository;
import roomescape.repository.TimeRepository;

@Service
@Transactional(readOnly = true)
public class TimeService {

    private final TimeRepository timeRepository;
    private final ThemeRepository themeRepository;
    private final ReservationRepository reservationRepository;

    public TimeService(TimeRepository timeRepository, ThemeRepository themeRepository,
                       ReservationRepository reservationRepository) {
        this.timeRepository = timeRepository;
        this.themeRepository = themeRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<TimeResponse> readAll() {
        List<ReservationTime> times = timeRepository.findAll();
        return times.stream()
                .map(TimeResponse::from)
                .collect(Collectors.toList());
    }

    public List<TimeResponse> readAllByThemeIdAndDate(Long themeId, LocalDate date) {
        themeRepository.findById(themeId).orElseThrow(
                () -> new NotFoundException(ErrorCode.THEME_NOT_FOUND)
        );
        List<ReservationTime> times = timeRepository.findAllByThemeIdAndDate(themeId, date);
        return times.stream()
                .filter(time -> !isPastTime(date, time))
                .map(TimeResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void removeById(Long id) {
        timeRepository.findById(id).orElseThrow(
                () -> new NotFoundException(ErrorCode.TIME_NOT_FOUND)
        );
        if (reservationRepository.existsByTimeId(id)) {
            throw new UnprocessableEntityException(ErrorCode.TIME_HAS_RESERVATIONS);
        }
        timeRepository.deleteById(id);
    }

    @Transactional
    public TimeResponse register(TimeRequest timeRequest) {
        ReservationTime time = new ReservationTime(null, timeRequest.startAt());
        if (timeRepository.existsByStartAt(time.getStartAt())) {
            throw new ConflictException(ErrorCode.TIME_DUPLICATED);
        }
        ReservationTime saved = timeRepository.save(time.getStartAt());
        return TimeResponse.from(saved);
    }

    private boolean isPastTime(LocalDate date, ReservationTime time) {
        return LocalDateTime.now().isAfter(LocalDateTime.of(date, time.getStartAt()));
    }
}
