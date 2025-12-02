package org.cross.medicore.scheduling.api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record CreateProviderScheduleDto(
        long providerId,
        LocalDate scheduleDate,
        LocalDateTime scheduleStartTime,
        LocalDateTime scheduleEndTime,
        List<BreakDto> breaks
) {
    public record BreakDto(
            LocalDateTime start,
            LocalDateTime end,
            String breakName
    ) {}
}
