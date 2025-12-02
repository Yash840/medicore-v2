package org.cross.medicore.scheduling.api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ProviderScheduleDetailsDto(
        long id,
        long providerId,
        LocalDate scheduleDate,
        LocalDateTime scheduleStartTime,
        LocalDateTime scheduleEndTime,
        List<BreakDetailsDto> breaks
) {
    public record BreakDetailsDto(
            long breakId,
            LocalDateTime start,
            LocalDateTime end,
            String breakName
    ) {}
}
