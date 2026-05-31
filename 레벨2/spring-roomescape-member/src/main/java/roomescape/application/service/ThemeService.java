package roomescape.application.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.application.dto.ThemeCommand;
import roomescape.application.dto.ThemeResult;
import roomescape.domain.exception.ConflictException;
import roomescape.domain.exception.ErrorCode;
import roomescape.domain.exception.NotFoundException;
import roomescape.domain.exception.UnprocessableEntityException;
import roomescape.domain.model.Theme;
import roomescape.infrastructure.persistence.repository.ReservationRepository;
import roomescape.infrastructure.persistence.repository.ThemeRepository;

@Service
@Transactional(readOnly = true)
public class ThemeService {

    private static final int RANKS_LIMIT_COUNT = 10;

    private final ThemeRepository themeRepository;
    private final ReservationRepository reservationRepository;

    public ThemeService(ThemeRepository themeRepository, ReservationRepository reservationRepository) {
        this.themeRepository = themeRepository;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public ThemeResult register(ThemeCommand command) {
        Theme theme = new Theme(null, command.name(), command.description(), command.url());
        if (themeRepository.existsByName(theme.getName())) {
            throw new ConflictException(ErrorCode.THEME_DUPLICATED);
        }
        Theme saved = themeRepository.save(theme.getName(), theme.getDescription(), theme.getUrl());
        return ThemeResult.from(saved);
    }

    @Transactional
    public void removeById(Long id) {
        themeRepository.findById(id).orElseThrow(
                () -> new NotFoundException(ErrorCode.THEME_NOT_FOUND)
        );
        if (reservationRepository.existsByThemeId(id)) {
            throw new UnprocessableEntityException(ErrorCode.THEME_HAS_RESERVATIONS);
        }
        themeRepository.deleteById(id);
    }

    public List<ThemeResult> readAll() {
        List<Theme> themes = themeRepository.findAll();
        return themes.stream()
                .map(ThemeResult::from)
                .collect(Collectors.toList());
    }

    public List<ThemeResult> readRanks(LocalDate today) {
        LocalDate endDate = today.minusDays(1);
        LocalDate startDate = today.minusDays(7);
        List<Theme> themes = themeRepository.findByCurrentDateAndLastWeekDateAndLimit(endDate,
                startDate,
                RANKS_LIMIT_COUNT);
        return themes.stream()
                .map(ThemeResult::from)
                .collect(Collectors.toList());
    }
}
