package org.cross.medicore.scheduling.internals.service;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.auditing.annotation.Auditable;
import org.cross.medicore.exception.SlotNotFoundException;
import org.cross.medicore.scheduling.api.constants.SlotStatus;
import org.cross.medicore.scheduling.api.dto.SlotInfo;
import org.cross.medicore.scheduling.internals.entity.Slot;
import org.cross.medicore.scheduling.internals.persistence.SlotRepository;
import org.cross.medicore.shared.Action;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SlotServiceImpl implements SlotService{
    private final SlotRepository slotRepository;

    @Override
    @Transactional
    public void addSlots(List<Slot> slots) {
        List<Slot> savedSlots = slotRepository.saveAll(slots);
    }

    @Override
    @Transactional(readOnly = true)
    @Auditable(action = Action.READ_SLOT_DETAILS, message = "Fetched available slots for providerId: ?1 near time: ?2")
    @PreAuthorize("hasAuthority('MANAGE_APPOINTMENT')")
    public List<SlotInfo> getAvailableSlotsByProviderAndTime(long providerId, LocalDateTime requiredTime) {
        LocalDateTime rangeStart = requiredTime.minusMinutes(10);
        LocalDateTime rangeEnd = requiredTime.plusMinutes(10);
        return slotRepository.findByProviderIdAndSlotStatusAndStartBetweenOrderByStartAsc(providerId, SlotStatus.FREE, rangeStart, rangeEnd);
    }

    @Override
    @Transactional(readOnly = true)
    @Auditable(action = Action.READ_SLOT_DETAILS, message = "Fetched details for Slot ID: ?1")
    public Slot getSlotById(long slotId) {
        return slotRepository.findById(slotId).orElseThrow(() -> new SlotNotFoundException(slotId));
    }
}
