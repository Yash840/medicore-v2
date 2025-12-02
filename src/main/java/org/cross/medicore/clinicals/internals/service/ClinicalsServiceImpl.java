package org.cross.medicore.clinicals.internals.service;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.clinicals.api.dto.AddMedicationDto;
import org.cross.medicore.clinicals.api.dto.EncounterDetails;
import org.cross.medicore.clinicals.api.dto.UpdateVitalsDto;
import org.cross.medicore.clinicals.internals.entity.Encounter;
import org.cross.medicore.clinicals.internals.entity.Medication;
import org.cross.medicore.clinicals.internals.entity.Vitals;
import org.cross.medicore.clinicals.internals.mapper.ClinicalsMapper;
import org.cross.medicore.clinicals.internals.persistence.EncounterRepository;
import org.cross.medicore.exception.BeginEncounterFailedException;
import org.cross.medicore.exception.EncounterAlreadyEndedException;
import org.cross.medicore.exception.EncounterNotFoundException;
import org.cross.medicore.scheduling.api.SchedulingApi;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClinicalsServiceImpl implements ClinicalsService{
    private final SchedulingApi schedulingApi;
    private final EncounterRepository encounterRepository;

    @Override
    public long beginEncounter(long appointmentId) {
        try {
            schedulingApi.consumeAppointment(appointmentId);

            Encounter encounter = new Encounter(appointmentId);
            Encounter saved = encounterRepository.save(encounter);

            return saved.getEncounterId();
        } catch (Exception e) {
            throw new BeginEncounterFailedException(e.getMessage());
        }
    }

    @Override
    public EncounterDetails getEncounterDetailsByEncounterId(long encounterId) {
        return ClinicalsMapper.toEncounterDetails(getEncounterByEncounterId(encounterId));
    }

    @Override
    public EncounterDetails getEncounterDetailsByAppointmentId(long appointmentId) {
        Encounter encounter = encounterRepository.findById(appointmentId)
                .orElseThrow(() -> new EncounterNotFoundException("Encounter not found with Appointment ID: " + appointmentId));

        return ClinicalsMapper.toEncounterDetails(encounter);
    }

    @Override
    public EncounterDetails endEncounter(long encounterId) {
        Encounter encounter = getEncounterByEncounterId(encounterId);
        EncounterDetails encounterDetails = ClinicalsMapper.toEncounterDetails(encounter);

        encounter.endEncounter();

        return encounterDetails;
    }

    @Override
    public EncounterDetails addMedications(long encounterId, List<AddMedicationDto> dtos) {
        Encounter encounter = getEncounterByEncounterId(encounterId);
        ensureEncounterIsUpdatable(encounter);

        dtos.forEach((dto) -> {
            Medication medication = ClinicalsMapper.toMedication(dto, encounter.getPrescription());
            encounter.getPrescription().addMedication(medication);
        });

        Encounter updated = encounterRepository.save(encounter);

        return ClinicalsMapper.toEncounterDetails(updated);
    }

    @Override
    public EncounterDetails addVitals(long encounterId, UpdateVitalsDto dto) {
        Encounter encounter = getEncounterByEncounterId(encounterId);
        ensureEncounterIsUpdatable(encounter);

        ClinicalsMapper.updateVitals(encounter.getVitals(), dto);

        Encounter updated = encounterRepository.save(encounter);

        return ClinicalsMapper.toEncounterDetails(updated);
    }

    private Encounter getEncounterByEncounterId(long encounterId){
        return encounterRepository.findById(encounterId)
                .orElseThrow(() -> new EncounterNotFoundException(encounterId));
    }

    private void ensureEncounterIsUpdatable(Encounter encounter){
        if(encounter.isOngoing())
            return;

        throw new EncounterAlreadyEndedException();
    }
}
