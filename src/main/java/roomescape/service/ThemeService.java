package roomescape.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.dto.ThemeRequest;
import roomescape.dto.ThemeResponse;
import roomescape.exception.ConflictException;
import roomescape.exception.ErrorCode;
import roomescape.exception.NotFoundException;
import roomescape.exception.UnprocessableEntityException;
import roomescape.model.Theme;
import roomescape.repository.ReservationRepository;
import roomescape.repository.ThemeRepository;

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
    public ThemeResponse register(ThemeRequest themeRequest) {
        Theme theme = new Theme(null, themeRequest.name(), themeRequest.description(), themeRequest.url());
        if (themeRepository.existsByName(theme.getName())) {
            throw new ConflictException(ErrorCode.THEME_DUPLICATED);
        }
        Theme saved = themeRepository.save(theme.getName(), theme.getDescription(), theme.getUrl());
        return ThemeResponse.from(saved);
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

    public List<ThemeResponse> readAll() {
        List<Theme> themes = themeRepository.findAll();
        return themes.stream()
                .map(ThemeResponse::from)
                .collect(Collectors.toList());
    }

    public List<ThemeResponse> readRanks(LocalDate today) {
        LocalDate endDate = today.minusDays(1);
        LocalDate startDate = today.minusDays(7);
        List<Theme> themes = themeRepository.findByCurrentDateAndLastWeekDateAndLimit(endDate,
                startDate,
                RANKS_LIMIT_COUNT);
        return themes.stream()
                .map(ThemeResponse::from)
                .collect(Collectors.toList());
    }
}
