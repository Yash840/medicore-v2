package org.cross.medicore.scheduling.internals.service;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.auditing.annotation.Auditable;
import org.cross.medicore.exception.ProviderNotFoundException;
import org.cross.medicore.scheduling.api.dto.CreateProviderDto;
import org.cross.medicore.scheduling.api.dto.ProviderBriefProfile;
import org.cross.medicore.scheduling.api.dto.ProviderDetails;
import org.cross.medicore.scheduling.api.dto.UpdateProviderDetailsDto;
import org.cross.medicore.scheduling.internals.entity.Provider;
import org.cross.medicore.scheduling.internals.mapper.ProviderMapper;
import org.cross.medicore.scheduling.internals.persistence.ProviderRepository;
import org.cross.medicore.shared.Action;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {
    private final ProviderRepository providerRepository;

    @Override
    @Transactional
    @Auditable(action = Action.CREATE_PROVIDER, message = "Created new provider with userId: ?2")
    public ProviderBriefProfile createProvider(CreateProviderDto dto, long userId) {
        Provider provider = ProviderMapper.toProvider(dto);
        provider.setUserId(userId);

        Provider saved = providerRepository.save(provider);

        return ProviderMapper.toProviderBriefProfile(saved);
    }

    @Override
    @Transactional
    @Auditable(action = Action.DELETE_PROVIDER, message = "Removed provider with providerId: ?1")
    public ProviderDetails removeProvider(long providerId) {
        Provider provider = fetchProvider(providerId);
        ProviderDetails providerDetails = ProviderMapper.toProviderDetails(provider);

        providerRepository.delete(provider);

        return providerDetails;
    }

    @Override
    @Transactional
    @Auditable(action = Action.MODIFY_PROVIDER_DETAILS, message = "Updated provider details for providerId: ?1")
    public ProviderDetails updateProviderDetails(long providerId, UpdateProviderDetailsDto dto) {
        Provider provider = fetchProvider(providerId);

        ProviderMapper.updateProviderFromDto(dto, provider);

        return ProviderMapper.toProviderDetails(provider);
    }

    @Override
    @Transactional(readOnly = true)
    @Auditable(action = Action.READ_PROVIDER_DETAILS, message = "Fetched provider details for providerId: ?1")
    public ProviderDetails getProviderById(long providerId) {
        return ProviderMapper.toProviderDetails(fetchProvider(providerId));
    }

    @Transactional(readOnly = true)
    private Provider fetchProvider(long providerId){
        return providerRepository.findById(providerId)
                .orElseThrow(() -> new ProviderNotFoundException(providerId));
    }
}