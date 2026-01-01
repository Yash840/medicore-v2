package org.cross.medicore.orchestration.orchestrator;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.auditing.annotation.Auditable;
import org.cross.medicore.clinicals.api.ClinicalsApi;
import org.cross.medicore.clinicals.api.dto.AddMedicationDto;
import org.cross.medicore.clinicals.api.dto.EncounterDetails;
import org.cross.medicore.clinicals.api.dto.UpdateVitalsDto;
import org.cross.medicore.shared.Action;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClinicalsOrchestrator {
    private final ClinicalsApi clinicalsApi;

    @Auditable(action = Action.BEGIN_ENCOUNTER, message = "Orchestrated begin encounter for appointmentId: ?1")
    public long beginEncounter(long appointmentId){
        return clinicalsApi.beginEncounter(appointmentId);
    }

    @Auditable(action = Action.READ_ENCOUNTER_DETAILS, message = "Orchestrated fetch encounter details for encounterId: ?1")
    public EncounterDetails getEncounterDetailsByEncounterId(long encounterId) {
        return clinicalsApi.getEncounterDetailsByEncounterId(encounterId);
    }

    @Auditable(action = Action.READ_ENCOUNTER_DETAILS, message = "Orchestrated fetch encounter details for appointmentId: ?1")
    public EncounterDetails getEncounterDetailsByAppointmentId(long appointmentId) {
        return clinicalsApi.getEncounterDetailsByAppointmentId(appointmentId);
    }

    @Auditable(action = Action.END_ENCOUNTER, message = "Orchestrated end encounter for encounterId: ?1")
    public EncounterDetails endEncounter(long encounterId) {
        return clinicalsApi.endEncounter(encounterId);
    }

    @Auditable(action = Action.ADD_MEDICATIONS, message = "Orchestrated add medications for encounterId: ?1")
    public EncounterDetails addMedications(long encounterId, List<AddMedicationDto> medications) {
        return clinicalsApi.addMedications(encounterId, medications);
    }

    @Auditable(action = Action.ADD_VITALS, message = "Orchestrated add vitals for encounterId: ?1")
    public EncounterDetails addVitals(long encounterId, UpdateVitalsDto vitals) {
        return clinicalsApi.addVitals(encounterId, vitals);
    }
}
