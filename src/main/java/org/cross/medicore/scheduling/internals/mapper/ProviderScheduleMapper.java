package org.cross.medicore.scheduling.internals.mapper;

import org.cross.medicore.scheduling.api.dto.CreateProviderScheduleDto;
import org.cross.medicore.scheduling.api.dto.ProviderScheduleDetailsDto;
import org.cross.medicore.scheduling.internals.entity.Break;
import org.cross.medicore.scheduling.internals.entity.ProviderSchedule;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProviderScheduleMapper {

    public static ProviderSchedule toProviderSchedule(CreateProviderScheduleDto dto) {
        if (dto == null) {
            return null;
        }

        ProviderSchedule schedule = new ProviderSchedule();
        schedule.setProvider_id(dto.providerId());
        schedule.setScheduleDate(dto.scheduleDate());
        schedule.setScheduleStartTime(dto.scheduleStartTime());
        schedule.setScheduleEndTime(dto.scheduleEndTime());

        // Convert breaks
        if (dto.breaks() != null && !dto.breaks().isEmpty()) {
            List<Break> breaks = toBreaks(dto.breaks(), schedule);
            schedule.setBreaks(breaks);
        }

        return schedule;
    }

    public static List<Break> toBreaks(List<CreateProviderScheduleDto.BreakDto> breakDtos, ProviderSchedule providerSchedule) {
        if (breakDtos == null || breakDtos.isEmpty()) {
            return new ArrayList<>();
        }

        return breakDtos.stream()
                .map(dto -> toBreak(dto, providerSchedule))
                .toList();
    }

    public static Break toBreak(CreateProviderScheduleDto.BreakDto dto, ProviderSchedule providerSchedule) {
        if (dto == null) {
            return null;
        }

        return new Break(providerSchedule, dto.start(), dto.end(), dto.breakName());
    }

    public static CreateProviderScheduleDto.BreakDto toBreakDto(Break breakEntity) {
        if (breakEntity == null) {
            return null;
        }

        return new CreateProviderScheduleDto.BreakDto(
                breakEntity.getStart(),
                breakEntity.getEnd(),
                breakEntity.getBreakName()
        );
    }

    public static List<CreateProviderScheduleDto.BreakDto> toBreakDtos(List<Break> breaks) {
        if (breaks == null || breaks.isEmpty()) {
            return new ArrayList<>();
        }

        return breaks.stream()
                .map(ProviderScheduleMapper::toBreakDto)
                .toList();
    }

    public static CreateProviderScheduleDto toCreateProviderScheduleDto(ProviderSchedule schedule) {
        if (schedule == null) {
            return null;
        }

        return new CreateProviderScheduleDto(
                schedule.getProvider_id(),
                schedule.getScheduleDate(),
                schedule.getScheduleStartTime(),
                schedule.getScheduleEndTime(),
                toBreakDtos(schedule.getBreaks())
        );
    }

    public static ProviderScheduleDetailsDto toProviderScheduleDetailsDto(ProviderSchedule schedule) {
        if (schedule == null) {
            return null;
        }

        return new ProviderScheduleDetailsDto(
                schedule.getId(),
                schedule.getProvider_id(),
                schedule.getScheduleDate(),
                schedule.getScheduleStartTime(),
                schedule.getScheduleEndTime(),
                toBreakDetailsDtos(schedule.getBreaks())
        );
    }

    public static List<ProviderScheduleDetailsDto.BreakDetailsDto> toBreakDetailsDtos(List<Break> breaks) {
        if (breaks == null || breaks.isEmpty()) {
            return new ArrayList<>();
        }

        return breaks.stream()
                .map(ProviderScheduleMapper::toBreakDetailsDto)
                .toList();
    }

    public static ProviderScheduleDetailsDto.BreakDetailsDto toBreakDetailsDto(Break breakEntity) {
        if (breakEntity == null) {
            return null;
        }

        return new ProviderScheduleDetailsDto.BreakDetailsDto(
                breakEntity.getBreakId(),
                breakEntity.getStart(),
                breakEntity.getEnd(),
                breakEntity.getBreakName()
        );
    }
}
