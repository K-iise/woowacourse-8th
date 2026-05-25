package roomescape.application.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.application.dto.ReservationCommand;
import roomescape.application.dto.ReservationResult;
import roomescape.application.dto.ReservationUpdateCommand;
import roomescape.domain.exception.ConflictException;
import roomescape.domain.exception.ErrorCode;
import roomescape.domain.exception.NotFoundException;
import roomescape.domain.exception.UnprocessableEntityException;
import roomescape.domain.model.Reservation;
import roomescape.domain.model.ReservationTime;
import roomescape.domain.model.Theme;
import roomescape.infrastructure.persistence.repository.ReservationRepository;
import roomescape.infrastructure.persistence.repository.ThemeRepository;
import roomescape.infrastructure.persistence.repository.TimeRepository;

@Service
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final TimeRepository timeRepository;
    private final ThemeRepository themeRepository;

    public ReservationService(ReservationRepository reservationRepository, TimeRepository timeRepository,
                              ThemeRepository themeRepository) {
        this.reservationRepository = reservationRepository;
        this.timeRepository = timeRepository;
        this.themeRepository = themeRepository;
    }

    public List<ReservationResult> read() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(ReservationResult::from)
                .collect(Collectors.toList());
    }

    public List<ReservationResult> readAllByName(String name) {
        List<Reservation> reservations = reservationRepository.findAllByName(name);
        return reservations.stream()
                .map(ReservationResult::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void removeById(Long id) {
        Reservation reservation = getReservation(id);
        validatePastUpdate(reservation.getDate(), reservation.getTime());
        reservationRepository.deleteById(id);
    }

    @Transactional
    public void cancelByIdAndName(Long id, String username) {
        Reservation reservation = getReservation(id);
        validateOwner(username, reservation);
        validatePastUpdate(reservation.getDate(), reservation.getTime());
        reservationRepository.deleteById(id);
    }

    private void validateOwner(String username, Reservation reservation) {
        if (!reservation.getName().equals(username)) {
            throw new UnprocessableEntityException(ErrorCode.RESERVATION_NOT_OWNER);
        }
    }

    @Transactional
    public ReservationResult register(ReservationCommand command) {
        ReservationTime reservationTime = getReservationTime(command.timeId());
        Theme theme = getTheme(command.themeId());

        validatePastRegister(command.date(), reservationTime);
        validateDuplicate(command.date(), command.timeId(), command.themeId());

        Reservation savedReservation = reservationRepository.save(command.name(), command.date(),
                command.timeId(),
                command.themeId(), reservationTime, theme);
        return ReservationResult.from(savedReservation);
    }

    @Transactional
    public ReservationResult update(Long id, String username, ReservationUpdateCommand command) {
        Reservation reservation = getReservation(id);
        validateOwner(username, reservation);
        validatePastUpdate(reservation.getDate(), reservation.getTime());

        ReservationTime reservationTime = getReservationTime(command.timeId());
        validatePastUpdate(command.date(), reservationTime);
        validateDuplicateExceptSelf(command.date(), command.timeId(),
                reservation.getTheme().getId(), reservation.getId());

        Reservation updated = reservationRepository.update(id, command.date(), command.timeId());
        return ReservationResult.from(updated);
    }

    private Reservation getReservation(Long id) {
        return reservationRepository.findById(id).orElseThrow(
                () -> new NotFoundException(ErrorCode.RESERVATION_NOT_FOUND)
        );
    }

    private ReservationTime getReservationTime(Long timeId) {
        return timeRepository.findById(timeId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.TIME_NOT_FOUND));
    }

    private Theme getTheme(Long themeId) {
        return themeRepository.findById(themeId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.THEME_NOT_FOUND));
    }

    private boolean isOverDateAndTime(LocalDate date, ReservationTime time) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime reservation = LocalDateTime.of(date, time.getStartAt());

        return now.isAfter(reservation);
    }

    private void validatePastUpdate(LocalDate date, ReservationTime time) {
        if (isOverDateAndTime(date, time)) {
            throw new UnprocessableEntityException(ErrorCode.RESERVATION_PAST_UPDATE);
        }
    }

    private void validatePastRegister(LocalDate date, ReservationTime time) {
        if (isOverDateAndTime(date, time)) {
            throw new UnprocessableEntityException(ErrorCode.RESERVATION_PAST_DATE);
        }
    }

    private void validateDuplicate(LocalDate date, Long timeId, Long themeId) {
        if (reservationRepository.existsByDateAndTimeIdAndThemeId(date,
                timeId,
                themeId)) {
            throw new ConflictException(ErrorCode.RESERVATION_DUPLICATED);
        }
    }

    private void validateDuplicateExceptSelf(LocalDate date, Long timeId, Long themeId, Long reservationId) {
        if (reservationRepository.existsByDateAndTimeIdAndThemeIdAndReservationIdNot(date, timeId, themeId,
                reservationId)) {
            throw new ConflictException(ErrorCode.RESERVATION_TIME_ALREADY_BOOKED);
        }
    }
}
