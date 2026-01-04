package org.cross.medicore.clinicals.internals.service;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.auditing.annotation.Auditable;
import org.cross.medicore.clinicals.api.dto.AddMedicationDto;
import org.cross.medicore.clinicals.api.dto.EncounterDetails;
import org.cross.medicore.clinicals.api.dto.UpdateVitalsDto;
import org.cross.medicore.clinicals.internals.entity.Encounter;
import org.cross.medicore.clinicals.internals.entity.Medication;
import org.cross.medicore.clinicals.internals.mapper.ClinicalsMapper;
import org.cross.medicore.clinicals.internals.persistence.EncounterRepository;
import org.cross.medicore.exception.BeginEncounterFailedException;
import org.cross.medicore.exception.EncounterAlreadyEndedException;
import org.cross.medicore.exception.EncounterNotFoundException;
import org.cross.medicore.scheduling.api.SchedulingApi;
import org.cross.medicore.shared.Action;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClinicalsServiceImpl implements ClinicalsService{
    private final SchedulingApi schedulingApi;
    private final EncounterRepository encounterRepository;

    @Override
    @Transactional
    @Auditable(action = Action.BEGIN_ENCOUNTER, message = "Encounter started for Appointment ID: ?1")
    @PreAuthorize("hasRole('PROVIDER')")
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
    @Transactional(readOnly = true)
    @Auditable(action = Action.READ_ENCOUNTER_DETAILS, message = "Fetched details for Encounter ID: ?1")
    @PreAuthorize("hasAuthority('READ_MEDICAL_DOCS')")
    public EncounterDetails getEncounterDetailsByEncounterId(long encounterId) {
        return ClinicalsMapper.toEncounterDetails(getEncounterByEncounterId(encounterId));
    }

    @Override
    @Transactional(readOnly = true)
    @Auditable(action = Action.READ_ENCOUNTER_DETAILS, message = "Fetched details for Encounter with AppointmentID: ?1")
    @PreAuthorize("hasAuthority('READ_MEDICAL_DOCS')")
    public EncounterDetails getEncounterDetailsByAppointmentId(long appointmentId) {
        Encounter encounter = encounterRepository.findByAppointmentId(appointmentId)
                .orElseThrow(() -> new EncounterNotFoundException("Encounter not found with Appointment ID: " + appointmentId));
        return ClinicalsMapper.toEncounterDetails(encounter);
    }

    @Override
    @Transactional
    @Auditable(action = Action.END_ENCOUNTER, message = "Ended Encounter ID: ?1")
    @PreAuthorize("hasAuthority('MANAGE_ENCOUNTER')")
    public EncounterDetails endEncounter(long encounterId) {
        Encounter encounter = getEncounterByEncounterId(encounterId);
        encounter.endEncounter();
        Encounter endedEncounter = encounterRepository.save(encounter);

        return ClinicalsMapper.toEncounterDetails(endedEncounter);
    }

    @Override
    @Transactional
    @Auditable(action = Action.MODIFY_ENCOUNTER_DETAILS, message = "Added Medications For Encounter ID: ?1")
    @PreAuthorize("hasAuthority('MANAGE_ENCOUNTER')")
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
    @Transactional
    @Auditable(action = Action.MODIFY_ENCOUNTER_DETAILS, message = "Added Vitals For Encounter ID: ?1")
    @PreAuthorize("hasAuthority('MANAGE_ENCOUNTER')")
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
