package org.cross.medicore.security.api.dto;

import org.cross.medicore.security.internals.constants.RoleName;

public record AuthenticatedUserDetails(
        long userId,
        String userName,
        RoleName role
) { }