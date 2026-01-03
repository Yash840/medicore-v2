package org.cross.medicore.scheduling.internals.service;

import org.cross.medicore.exception.AppointmentNotFoundException;
import org.cross.medicore.scheduling.api.constants.AppointmentStatus;
import org.cross.medicore.scheduling.api.constants.AppointmentType;
import org.cross.medicore.scheduling.api.constants.SlotStatus;
import org.cross.medicore.scheduling.api.dto.AppointmentDetailsDto;
import org.cross.medicore.scheduling.api.dto.ScheduleAppointmentDto;
import org.cross.medicore.scheduling.internals.entity.Appointment;
import org.cross.medicore.scheduling.internals.entity.ProviderSchedule;
import org.cross.medicore.scheduling.internals.entity.Slot;
import org.cross.medicore.scheduling.internals.persistence.AppointmentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SchedulingServiceImplTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private SlotService slotService;

    @InjectMocks
    private SchedulingServiceImpl schedulingService;

    @Test
    @DisplayName("Schedule Appointment Test")
    void scheduleAppointment() {
        // Arrange
        ScheduleAppointmentDto dto = new ScheduleAppointmentDto(
                1L,
                2L,
                3L,
                AppointmentType.CONSULTATION
        );

        ProviderSchedule providerSchedule = mock(ProviderSchedule.class);
        when(providerSchedule.getProviderId()).thenReturn(2L);

        Slot mockSlot = spy(new Slot(
                providerSchedule,
                2L,
                LocalDateTime.of(2026, 1, 5, 10, 0),
                LocalDateTime.of(2026, 1, 5, 10, 30),
                SlotStatus.FREE
        ));

        Appointment savedAppointment = new Appointment();
        savedAppointment.setPatientId(1L);
        savedAppointment.setProviderId(2L);
        savedAppointment.setAppointmentType(AppointmentType.CONSULTATION);
        savedAppointment.setAppointmentStatus(AppointmentStatus.SCHEDULED);
        savedAppointment.setSlot(mockSlot);

        when(slotService.getSlotById(3L)).thenReturn(mockSlot);
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(savedAppointment);

        // Act
        AppointmentDetailsDto result = schedulingService.scheduleAppointment(dto);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.patientId());
        assertEquals(2L, result.providerId());
        assertEquals(AppointmentType.CONSULTATION, result.appointmentType());
        assertEquals(AppointmentStatus.SCHEDULED, result.appointmentStatus());
        assertNotNull(result.slot());

        verify(slotService, times(1)).getSlotById(3L);
        verify(mockSlot, times(1)).setSlotStatus(SlotStatus.ENGAGED);
        verify(appointmentRepository, times(1)).save(any(Appointment.class));
    }

    @Test
    @DisplayName("Get Appointment Details Test - Success")
    void getAppointmentDetails() {
        // Arrange
        ProviderSchedule providerSchedule = mock(ProviderSchedule.class);
        when(providerSchedule.getProviderId()).thenReturn(2L);

        Slot mockSlot = new Slot(
                providerSchedule,
                2L,
                LocalDateTime.of(2026, 1, 5, 10, 0),
                LocalDateTime.of(2026, 1, 5, 10, 30)
        );

        Appointment appointment = new Appointment();
        appointment.setPatientId(1L);
        appointment.setProviderId(2L);
        appointment.setAppointmentType(AppointmentType.CONSULTATION);
        appointment.setAppointmentStatus(AppointmentStatus.SCHEDULED);
        appointment.setSlot(mockSlot);

        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));

        // Act
        AppointmentDetailsDto result = schedulingService.getAppointmentDetails(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.patientId());
        assertEquals(2L, result.providerId());
        assertEquals(AppointmentType.CONSULTATION, result.appointmentType());
        assertEquals(AppointmentStatus.SCHEDULED, result.appointmentStatus());
        assertNotNull(result.slot());
        assertEquals(2L, result.slot().providerScheduleId());

        verify(appointmentRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Get Appointment Details Test - Not Found")
    void getAppointmentDetails_NotFound() {
        // Arrange
        when(appointmentRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(AppointmentNotFoundException.class, () -> {
            schedulingService.getAppointmentDetails(999L);
        });

        verify(appointmentRepository, times(1)).findById(999L);
    }
}