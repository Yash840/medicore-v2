package org.cross.medicore.clinicals.api.constants;

import org.cross.medicore.clinicals.api.dto.AddMedicationDto;
import org.cross.medicore.clinicals.api.dto.EncounterDetails;
import org.cross.medicore.clinicals.api.dto.UpdateVitalsDto;
import org.cross.medicore.clinicals.internals.entity.Medication;

import java.util.List;

public interface ClinicalsApi {

    long beginEncounter(long appointmentId);

    EncounterDetails getEncounterDetailsByEncounterId(long encounterId);

    EncounterDetails getEncounterDetailsByAppointmentId(long appointmentId);

    EncounterDetails endEncounter(long encounterId);

    EncounterDetails addMedications(long encounterId, List<AddMedicationDto> dto);

    EncounterDetails addVitals(long encounterId, UpdateVitalsDto dto);
}
