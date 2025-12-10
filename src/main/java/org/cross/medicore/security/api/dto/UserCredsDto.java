package org.cross.medicore.security.api.dto;

public record UserCredsDto(
        String username,
        String password
) {
}
