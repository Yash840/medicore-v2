package org.cross.medicore.orchestration.orchestrator;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.auditing.annotation.Auditable;
import org.cross.medicore.scheduling.api.ProviderApi;
import org.cross.medicore.scheduling.api.dto.CreateProviderDto;
import org.cross.medicore.scheduling.api.dto.ProviderBriefProfile;
import org.cross.medicore.scheduling.api.dto.ProviderDetails;
import org.cross.medicore.scheduling.api.dto.UpdateProviderDetailsDto;
import org.cross.medicore.security.api.UserApi;
import org.cross.medicore.shared.Action;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProviderOrchestrator {
    private final ProviderApi providerApi;
    private final UserApi userApi;

    @Transactional
    @Auditable(action = Action.CREATE_PROVIDER, message = "Orchestrated provider creation with username: ?2")
    public ProviderBriefProfile createProvider(CreateProviderDto dto, String username, String password){
        long userId = userApi.createProviderUser(username, password).userId();

        return providerApi.createProvider(dto, userId);
    }

    @Auditable(action = Action.MODIFY_PROVIDER_DETAILS, message = "Orchestrated provider update for providerId: ?1")
    public ProviderDetails updateProviderDetails(long providerId, UpdateProviderDetailsDto dto){
        return providerApi.updateProviderDetails(providerId, dto);
    }

    @Auditable(action = Action.READ_PROVIDER_DETAILS, message = "Orchestrated fetch provider details for providerId: ?1")
    public ProviderDetails getProviderDetails(long providerId){
        return providerApi.getProviderById(providerId);
    }

    @Transactional
    @Auditable(action = Action.DELETE_PROVIDER, message = "Orchestrated provider deletion for providerId: ?1")
    public ProviderDetails deleteProvider(long providerId){
        ProviderDetails providerDetails = getProviderDetails(providerId);
        long associatedUserId = providerDetails.userId();

        userApi.deleteUser(associatedUserId);
        providerApi.removeProvider(providerId);

        return providerDetails;
    }
}
