package org.cross.medicore.orchestration.web.dto;

import org.cross.medicore.patients.api.dto.CreatePatientDto;
import org.cross.medicore.security.api.dto.UserCredsDto;

public record CreatePatientRequest(
        CreatePatientDto createPatientDto,
        UserCredsDto creds
) {
}
