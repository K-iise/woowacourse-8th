package roomescape.presentation.web.dto;

import roomescape.domain.model.Theme;

public record ThemeResponse(Long id, String name, String description, String url) {

    public static ThemeResponse from(Theme theme) {
        return new ThemeResponse(theme.getId(), theme.getName(), theme.getDescription(), theme.getUrl());
    }

}
