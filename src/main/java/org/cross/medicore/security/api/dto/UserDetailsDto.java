package org.cross.medicore.security.api.dto;

import java.time.LocalDate;
import java.util.Set;

public record UserDetailsDto(
        long userId,
        String username,
        String roleName,
        Set<String> permissions,
        LocalDate createdAt,
        LocalDate updatedAt
) { }
