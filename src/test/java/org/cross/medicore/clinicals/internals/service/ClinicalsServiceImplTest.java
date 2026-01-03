package org.cross.medicore.clinicals.internals.service;

import org.cross.medicore.clinicals.api.constants.EncounterStatus;
import org.cross.medicore.clinicals.api.dto.AddMedicationDto;
import org.cross.medicore.clinicals.api.dto.EncounterDetails;
import org.cross.medicore.clinicals.api.dto.UpdateVitalsDto;
import org.cross.medicore.clinicals.internals.entity.Encounter;
import org.cross.medicore.clinicals.internals.persistence.EncounterRepository;
import org.cross.medicore.scheduling.api.SchedulingApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClinicalsServiceImplTest {

    @Mock
    private SchedulingApi schedulingApi;

    @Mock
    private EncounterRepository encounterRepository;

    @InjectMocks
    private ClinicalsServiceImpl clinicalsService;

    private final Encounter mockEncounterFromDB = new Encounter(1L, 1L);
    private final EncounterDetails mockEncounterDetails = new EncounterDetails(1L, 1L, null, null, null);

    @Test
    @DisplayName("Encounter Creation Test")
    void beginEncounter() {
        when(encounterRepository.save(any(Encounter.class))).thenReturn(mockEncounterFromDB);

        long encId = clinicalsService.beginEncounter(1L);

        assertEquals(1L, encId);

        verify(schedulingApi, times(1)).consumeAppointment(1L);
        verify(encounterRepository, times(1)).save(any(Encounter.class));
    }

    @Test
    void getEncounterDetailsByEncounterId_WhenEncounterExist() {
        when(encounterRepository.findById(1L)).thenReturn(Optional.of(mockEncounterFromDB));

        EncounterDetails encounterDetails = clinicalsService.getEncounterDetailsByEncounterId(1L);

        assertEquals(1L, encounterDetails.encounterId());
        assertEquals(1L, encounterDetails.associatedAppointmentId());
    }

    @Test
    void getEncounterDetailsByAppointmentId() {
        when(encounterRepository.findByAppointmentId(1L)).thenReturn(Optional.of(mockEncounterFromDB));

        EncounterDetails encounterDetails = clinicalsService.getEncounterDetailsByAppointmentId(1L);

        assertEquals(1L, encounterDetails.encounterId());
        assertEquals(1L, encounterDetails.associatedAppointmentId());
    }

    @Test
    void endEncounter() {
        Encounter spyEncounter = spy(new Encounter(1L, 1L));

        when(encounterRepository.findById(1L)).thenReturn(Optional.of(spyEncounter));
        when(encounterRepository.save(any(Encounter.class))).thenAnswer(invocation -> invocation.getArgument(0));

        EncounterDetails  encounterDetails = clinicalsService.endEncounter(1L);

        assertEquals(1L, encounterDetails.encounterId());
        assertEquals(EncounterStatus.ENDED, encounterDetails.encounterStatus());

        verify(encounterRepository, times(1)).findById(1L);
        verify(spyEncounter, times(1)).endEncounter();
        verify(encounterRepository, times(1)).save(any(Encounter.class));
    }

    @Test
    void addMedications() {
        Encounter spyEncounter = spy(new Encounter(1L, 1L));

        when(encounterRepository.findById(1L)).thenReturn(Optional.of(spyEncounter));
        when(encounterRepository.save(any(Encounter.class))).thenAnswer(invocation -> invocation.getArgument(0));

        AddMedicationDto dto = mock(AddMedicationDto.class);
        List<AddMedicationDto> dtos = List.of(dto);

        EncounterDetails encounterDetails = clinicalsService.addMedications(1L, dtos);

        assertEquals(1, encounterDetails.prescription().medications().size());

        verify(encounterRepository, times(1)).findById(1L);
        verify(encounterRepository, times(1)).save(any(Encounter.class));
    }

    @Test
    void addVitals() {
        Encounter spyEncounter = spy(new Encounter(1L, 1L));

        when(encounterRepository.findById(1L)).thenReturn(Optional.of(spyEncounter));
        when(encounterRepository.save(any(Encounter.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UpdateVitalsDto dto = UpdateVitalsDto.builder()
                .pulseRateBpm(100)
                .weightKg(100.00)
                .build();

        EncounterDetails encounterDetails = clinicalsService.addVitals(1L, dto);

        assertNotEquals(0, encounterDetails.vitals().pulseRateBpm());
        assertNotEquals(0.0, encounterDetails.vitals().weightKg());

        verify(encounterRepository, times(1)).findById(1L);
        verify(encounterRepository, times(1)).save(any(Encounter.class));
    }
}