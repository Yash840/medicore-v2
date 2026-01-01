package org.cross.medicore.orchestration.orchestrator;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.auditing.annotation.Auditable;
import org.cross.medicore.scheduling.api.ProviderApi;
import org.cross.medicore.scheduling.api.ProviderScheduleApi;
import org.cross.medicore.scheduling.api.dto.CreateProviderScheduleDto;
import org.cross.medicore.scheduling.api.dto.ProviderScheduleDetailsDto;
import org.cross.medicore.shared.Action;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ProviderScheduleOrchestrator {
    private final ProviderScheduleApi providerScheduleApi;
    private final ProviderApi providerApi;

    @Auditable(action = Action.CREATE_PROVIDER_SCHEDULE, message = "Orchestrated create provider schedule for providerId: ?1.providerId")
    public ProviderScheduleDetailsDto
    createProviderSchedule(CreateProviderScheduleDto dto){
        return providerScheduleApi.createProviderSchedule(dto);
    }

    @Auditable(action = Action.READ_PROVIDER_SCHEDULE, message = "Orchestrated fetch provider schedule by id: ?1")
    public ProviderScheduleDetailsDto
    getProviderScheduleById(long id){
        return providerScheduleApi.getProviderScheduleById(id);
    }

    @Auditable(action = Action.READ_PROVIDER_SCHEDULE, message = "Orchestrated fetch provider schedule for providerId: ?1 on date: ?2")
    public ProviderScheduleDetailsDto
    getProviderScheduleForProviderAtDay(long id, LocalDate day){
        return providerScheduleApi.getProviderScheduleByProviderForDay(id, day);
    }
}
