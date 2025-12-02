package org.cross.medicore.scheduling.internals.service;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.exception.SlotNotFoundException;
import org.cross.medicore.scheduling.api.dto.SlotDetails;
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
    public List<SlotDetails> getAvailableSlotsByProviderAndTime(long providerId, LocalDateTime requiredTime) {
        return List.of();
    }

    @Override
    public Slot getSlotById(long slotId) {
        return slotRepository.findById(slotId).orElseThrow(() -> new SlotNotFoundException(slotId));
    }
}
