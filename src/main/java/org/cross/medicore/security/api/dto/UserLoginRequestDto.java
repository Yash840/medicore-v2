package org.cross.medicore.security.api.dto;

public record UserLoginRequestDto(
        String username,
        String password
) {
}
