package org.cross.medicore.orchestration.orchestrator;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.clinicals.api.ClinicalsApi;
import org.cross.medicore.clinicals.api.dto.AddMedicationDto;
import org.cross.medicore.clinicals.api.dto.EncounterDetails;
import org.cross.medicore.clinicals.api.dto.UpdateVitalsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClinicalsOrchestrator {
    private final ClinicalsApi clinicalsApi;

    public long beginEncounter(long appointmentId){
        return clinicalsApi.beginEncounter(appointmentId);
    }

    public EncounterDetails getEncounterDetailsByEncounterId(long encounterId) {
        return clinicalsApi.getEncounterDetailsByEncounterId(encounterId);
    }

    public EncounterDetails getEncounterDetailsByAppointmentId(long appointmentId) {
        return clinicalsApi.getEncounterDetailsByAppointmentId(appointmentId);
    }

    public EncounterDetails endEncounter(long encounterId) {
        return clinicalsApi.endEncounter(encounterId);
    }

    public EncounterDetails addMedications(long encounterId, List<AddMedicationDto> medications) {
        return clinicalsApi.addMedications(encounterId, medications);
    }

    public EncounterDetails addVitals(long encounterId, UpdateVitalsDto vitals) {
        return clinicalsApi.addVitals(encounterId, vitals);
    }
}
