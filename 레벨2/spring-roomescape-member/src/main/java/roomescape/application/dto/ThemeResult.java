package roomescape.application.dto;

import roomescape.domain.model.Theme;

public record ThemeResult(Long id, String name, String description, String url) {

    public static ThemeResult from(Theme theme) {
        return new ThemeResult(theme.getId(), theme.getName(), theme.getDescription(), theme.getUrl());
    }
}
