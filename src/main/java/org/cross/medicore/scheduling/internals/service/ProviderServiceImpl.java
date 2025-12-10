package org.cross.medicore.scheduling.internals.service;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.exception.ProviderNotFoundException;
import org.cross.medicore.scheduling.api.dto.CreateProviderDto;
import org.cross.medicore.scheduling.api.dto.ProviderBriefProfile;
import org.cross.medicore.scheduling.api.dto.ProviderDetails;
import org.cross.medicore.scheduling.api.dto.UpdateProviderDetailsDto;
import org.cross.medicore.scheduling.internals.entity.Provider;
import org.cross.medicore.scheduling.internals.mapper.ProviderMapper;
import org.cross.medicore.scheduling.internals.persistence.ProviderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {
    private final ProviderRepository providerRepository;

    @Override
    @Transactional
    public ProviderBriefProfile createProvider(CreateProviderDto dto, long userId) {
        Provider provider = ProviderMapper.toProvider(dto);
        provider.setUserId(userId);

        Provider saved = providerRepository.save(provider);

        return ProviderMapper.toProviderBriefProfile(saved);
    }

    @Override
    @Transactional
    public ProviderDetails removeProvider(long providerId) {
        Provider provider = fetchProvider(providerId);
        ProviderDetails providerDetails = ProviderMapper.toProviderDetails(provider);

        providerRepository.delete(provider);

        return providerDetails;
    }

    @Override
    @Transactional
    public ProviderDetails updateProviderDetails(long providerId, UpdateProviderDetailsDto dto) {
        Provider provider = fetchProvider(providerId);

        ProviderMapper.updateProviderFromDto(dto, provider);

        return ProviderMapper.toProviderDetails(provider);
    }

    @Override
    @Transactional(readOnly = true)
    public ProviderDetails getProviderById(long providerId) {
        return ProviderMapper.toProviderDetails(fetchProvider(providerId));
    }

    @Transactional(readOnly = true)
    private Provider fetchProvider(long providerId){
        return providerRepository.findById(providerId)
                .orElseThrow(() -> new ProviderNotFoundException(providerId));
    }
}