package org.cross.medicore.scheduling.api;

import org.cross.medicore.scheduling.api.dto.CreateProviderScheduleDto;
import org.cross.medicore.scheduling.api.dto.ProviderScheduleDetailsDto;

import java.time.LocalDate;

public interface ProviderScheduleApi {
    ProviderScheduleDetailsDto createProviderSchedule(CreateProviderScheduleDto dto);

    ProviderScheduleDetailsDto getProviderScheduleById(long id);

    ProviderScheduleDetailsDto getProviderScheduleByProviderForDay(long providerId, LocalDate schDate);
}
