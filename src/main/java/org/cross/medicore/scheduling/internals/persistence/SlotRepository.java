package org.cross.medicore.scheduling.internals.persistence;

import org.cross.medicore.scheduling.internals.SlotInfo;
import org.cross.medicore.scheduling.internals.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Long> {

    /**
     * Finds all available slots for provider between given time range
     * @param providerId ID of provider
     * @param rangeStart Start of required time range
     * @param rangeEnd End of required time range
     * @return List of SlotInfo
     */
    @Query(value = "SELECT s.*, p.first_name as providerFirstName, p.last_name as providerLastName, p.specialization FROM slots s INNER JOIN providers p ON p.provider_id = s.provider_id WHERE s.slot_status = 'FREE' AND s.start IS BETWEEN(?2, ?3) AND s.provider_id = ?1", nativeQuery = true)
    List<SlotInfo> findAllAvailableSlotsByProviderAndStart(long providerId, LocalDateTime rangeStart, LocalDateTime rangeEnd);
}