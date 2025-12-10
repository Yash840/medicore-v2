package org.cross.medicore.orchestration.orchestrator;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.scheduling.api.ProviderApi;
import org.cross.medicore.scheduling.api.ProviderScheduleApi;
import org.cross.medicore.scheduling.api.dto.CreateProviderScheduleDto;
import org.cross.medicore.scheduling.api.dto.ProviderScheduleDetailsDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ProviderScheduleOrchestrator {
    private final ProviderScheduleApi providerScheduleApi;
    private final ProviderApi providerApi;

    public ProviderScheduleDetailsDto
    createProviderSchedule(CreateProviderScheduleDto dto){
        return providerScheduleApi.createProviderSchedule(dto);
    }

    public ProviderScheduleDetailsDto
    getProviderScheduleById(long id){
        return providerScheduleApi.getProviderScheduleById(id);
    }

    public ProviderScheduleDetailsDto
    getProviderScheduleForProviderAtDay(long id, LocalDate day){
        return providerScheduleApi.getProviderScheduleByProviderForDay(id, day);
    }
}
