package org.cross.medicore.scheduling.internals.service;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.scheduling.api.dto.CreateProviderScheduleDto;
import org.cross.medicore.scheduling.api.dto.ProviderScheduleDetailsDto;
import org.cross.medicore.scheduling.internals.entity.Break;
import org.cross.medicore.scheduling.internals.entity.ProviderSchedule;
import org.cross.medicore.scheduling.internals.entity.Slot;
import org.cross.medicore.scheduling.internals.mapper.ProviderScheduleMapper;
import org.cross.medicore.scheduling.internals.persistence.ProviderScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProviderScheduleServiceImpl implements ProviderScheduleService{
    private final ProviderScheduleRepository providerScheduleRepository;
    private final SlotService slotService;

    @Override
    public ProviderScheduleDetailsDto createProviderSchedule(CreateProviderScheduleDto dto) {
        ProviderSchedule schedule = ProviderScheduleMapper.toProviderSchedule(dto);

        List<Break> originalBreaks = schedule.getBreaks();
        List<Break> sanitizedBreaks = Objects.isNull(originalBreaks) ?
                List.of() : this.mergeOverlappingBreaks(originalBreaks);

        schedule.setBreaks(sanitizedBreaks);

        List<Slot> generatedSlots = generateSlots(schedule, 20, 5, 5);
        schedule.setSlots(generatedSlots);

        ProviderSchedule saved = providerScheduleRepository.save(schedule);

        return ProviderScheduleMapper.toProviderScheduleDetailsDto(saved);
    }

    @Override
    public ProviderScheduleDetailsDto getProviderScheduleById(long id) {
        ProviderSchedule schedule = providerScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid Id"));
        return ProviderScheduleMapper.toProviderScheduleDetailsDto(schedule);
    }

    @Override
    public ProviderScheduleDetailsDto getProviderScheduleByProviderForDay(long providerId, LocalDate schDate) {
        ProviderSchedule schedule = providerScheduleRepository.findByProviderIdAndScheduleDate(providerId, schDate)
                .orElseThrow(() -> new RuntimeException("Invalid Id or No schedule for given date"));

        return ProviderScheduleMapper.toProviderScheduleDetailsDto(schedule);
    }

    private List<Break> mergeOverlappingBreaks(List<Break> breaks ){
        List<Break> sorted = breaks.stream()
                .sorted(Comparator.comparing(Break::getStart))
                .toList();

        List<Break> merged = new ArrayList<>();
        merged.add(sorted.getFirst());

        for(Break brk:sorted){
            Break prevBrk = merged.getLast();

            if(brk.getStart().isBefore(prevBrk.getEnd()) || brk.getStart().isEqual(prevBrk.getEnd())){
                LocalDateTime newEndTime = brk.getEnd().isAfter(prevBrk.getEnd()) ? brk.getEnd() : prevBrk.getEnd();
                String newBreakName = prevBrk.getBreakName() + " + " + brk.getBreakName();
                prevBrk.setEnd(newEndTime);
                prevBrk.setBreakName(newBreakName);
            }else {
                merged.add(brk);
            }
        }

        return merged;
    }

    private List<Slot> generateSlots(ProviderSchedule schedule, int durationMinutes, int prepMinutes, int cleanUpMinutes){
        List<Break> breaks = schedule.getBreaks();
        List<Slot> slots = new ArrayList<>();
        LocalDateTime scheduleEnd = schedule.getScheduleEndTime();

        LocalDateTime pointer = schedule.getScheduleStartTime();

        while(pointer.isBefore(scheduleEnd)){
            LocalDateTime proposedStart = pointer;
            LocalDateTime proposedEnd = pointer.plusMinutes(durationMinutes);

            if(proposedEnd.isAfter(scheduleEnd)){
                break;
            }

            boolean isBlocked = false;
            LocalDateTime nextAvailableTime = proposedEnd;

            for(var brk:breaks){
                if(proposedStart.isBefore(brk.getEnd()) && proposedEnd.isAfter(brk.getStart())){
                    isBlocked = true;
                    nextAvailableTime = brk.getEnd();
                    break;
                }
            }

            if(isBlocked){
                pointer = nextAvailableTime.plusMinutes(prepMinutes);
                continue;
            }

            Slot slot = new Slot(schedule, schedule.getProviderId(), proposedStart, proposedEnd);
            slots.add(slot);

            pointer = proposedEnd.plusMinutes(cleanUpMinutes + prepMinutes);
        }

        return slots;
    }
}
