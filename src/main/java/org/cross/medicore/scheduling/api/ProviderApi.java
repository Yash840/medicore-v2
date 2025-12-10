package org.cross.medicore.scheduling.api;

import org.cross.medicore.scheduling.api.dto.CreateProviderDto;
import org.cross.medicore.scheduling.api.dto.ProviderBriefProfile;
import org.cross.medicore.scheduling.api.dto.ProviderDetails;
import org.cross.medicore.scheduling.api.dto.UpdateProviderDetailsDto;

public interface ProviderApi {
    ProviderBriefProfile createProvider(CreateProviderDto dto, long userId);
    ProviderDetails getProviderById(long providerId);
    ProviderDetails updateProviderDetails(long providerId, UpdateProviderDetailsDto dto);
    ProviderDetails removeProvider(long providerId);
}
