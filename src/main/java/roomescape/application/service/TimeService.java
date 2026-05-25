package roomescape.application.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.application.dto.TimeCommand;
import roomescape.application.dto.TimeResult;
import roomescape.domain.exception.ConflictException;
import roomescape.domain.exception.ErrorCode;
import roomescape.domain.exception.NotFoundException;
import roomescape.domain.exception.UnprocessableEntityException;
import roomescape.domain.model.ReservationTime;
import roomescape.infrastructure.persistence.repository.ReservationRepository;
import roomescape.infrastructure.persistence.repository.ThemeRepository;
import roomescape.infrastructure.persistence.repository.TimeRepository;

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

    public List<TimeResult> readAll() {
        List<ReservationTime> times = timeRepository.findAll();
        return times.stream()
                .map(TimeResult::from)
                .collect(Collectors.toList());
    }

    public List<TimeResult> readAllByThemeIdAndDate(Long themeId, LocalDate date) {
        themeRepository.findById(themeId).orElseThrow(
                () -> new NotFoundException(ErrorCode.THEME_NOT_FOUND)
        );
        List<ReservationTime> times = timeRepository.findAllByThemeIdAndDate(themeId, date);
        return times.stream()
                .filter(time -> !isPastTime(date, time))
                .map(TimeResult::from)
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
    public TimeResult register(TimeCommand command) {
        ReservationTime time = new ReservationTime(null, command.startAt());
        if (timeRepository.existsByStartAt(time.getStartAt())) {
            throw new ConflictException(ErrorCode.TIME_DUPLICATED);
        }
        ReservationTime saved = timeRepository.save(time.getStartAt());
        return TimeResult.from(saved);
    }

    private boolean isPastTime(LocalDate date, ReservationTime time) {
        return LocalDateTime.now().isAfter(LocalDateTime.of(date, time.getStartAt()));
    }
}
