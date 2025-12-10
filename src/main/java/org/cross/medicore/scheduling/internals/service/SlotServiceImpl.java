package org.cross.medicore.scheduling.internals.service;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.exception.SlotNotFoundException;
import org.cross.medicore.scheduling.api.constants.SlotStatus;
import org.cross.medicore.scheduling.api.dto.SlotDetails;
import org.cross.medicore.scheduling.api.dto.SlotInfo;
import org.cross.medicore.scheduling.internals.entity.Slot;
import org.cross.medicore.scheduling.internals.persistence.SlotRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SlotServiceImpl implements SlotService{
    private final SlotRepository slotRepository;

    @Override
    public void addSlots(List<Slot> slots) {
        List<Slot> savedSlots = slotRepository.saveAll(slots);
    }

    @Override
    public List<SlotInfo> getAvailableSlotsByProviderAndTime(long providerId, LocalDateTime requiredTime) {
        LocalDateTime rangeStart = requiredTime.minusMinutes(10);
        LocalDateTime rangeEnd = requiredTime.plusMinutes(10);
        return slotRepository.findByProviderIdAndSlotStatusAndStartBetweenOrderByStartAsc(providerId, SlotStatus.FREE, rangeStart, rangeEnd);
    }

    @Override
    public Slot getSlotById(long slotId) {
        return slotRepository.findById(slotId).orElseThrow(() -> new SlotNotFoundException(slotId));
    }
}
