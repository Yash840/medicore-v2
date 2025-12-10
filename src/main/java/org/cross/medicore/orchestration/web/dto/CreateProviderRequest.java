package org.cross.medicore.orchestration.web.dto;

import org.cross.medicore.scheduling.api.dto.CreateProviderDto;
import org.cross.medicore.security.api.dto.UserCredsDto;

public record CreateProviderRequest(
        CreateProviderDto createProviderDto,
        UserCredsDto creds
) {
}
