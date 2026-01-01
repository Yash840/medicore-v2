package org.cross.medicore.scheduling.internals.service;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.auditing.annotation.Auditable;
import org.cross.medicore.exception.AppointmentNotFoundException;
import org.cross.medicore.exception.InvalidAppointmentException;
import org.cross.medicore.scheduling.api.constants.AppointmentStatus;
import org.cross.medicore.scheduling.api.constants.SlotStatus;
import org.cross.medicore.scheduling.api.dto.AppointmentDetailsDto;
import org.cross.medicore.scheduling.api.dto.ScheduleAppointmentDto;
import org.cross.medicore.scheduling.internals.entity.Appointment;
import org.cross.medicore.scheduling.internals.entity.Slot;
import org.cross.medicore.scheduling.internals.mapper.SchedulingMapper;
import org.cross.medicore.scheduling.internals.persistence.AppointmentRepository;
import org.cross.medicore.shared.Action;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulingServiceImpl implements SchedulingService{
    private final AppointmentRepository appointmentRepository;
    private final SlotService slotService;

    @Auditable(action = Action.SCHEDULE_APPOINTMENT, message = "Scheduled appointment for patientId: ?1.patientId with providerId: ?1.providerId on slotId: ?1.slotId")
    @Override
    @Transactional
    public AppointmentDetailsDto scheduleAppointment(ScheduleAppointmentDto dto) {
        Appointment appointment = SchedulingMapper.toEntity(dto);
        Slot slot = slotService.getSlotById(dto.slotId());
        appointment.setSlot(slot);
        slot.setSlotStatus(SlotStatus.ENGAGED);

        Appointment saved = appointmentRepository.save(appointment);

        return SchedulingMapper.toDto(saved);
    }
    @Auditable(action = Action.READ_APPOINTMENT_DETAILS, message = "Fetched appointment details for appointmentId: ?1")

    @Override
    @Transactional(readOnly = true)
    public AppointmentDetailsDto getAppointmentDetails(long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentNotFoundException(appointmentId));

        return SchedulingMapper .toDto(appointment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentDetailsDto> getAllAppointmentDetailsForDay(LocalDate date) {
        List<Appointment> appointments = appointmentRepository.findBySlot_ProviderSchedule_ScheduleDateOrderBySlot_StartAsc(date);

        return appointments.stream()
                .map(SchedulingMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentDetailsDto> getAllAppointmentDetailsForPatient(long patientId) {
        List<Appointment> appointments = appointmentRepository.findByPatientIdOrderBySlot_ProviderSchedule_ScheduleDateAsc(patientId);

        return appointments.stream()
                .map(SchedulingMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentDetailsDto> getAllAppointmentDetailsForProvider(long providerId) {
        List<Appointment> appointments = appointmentRepository.findByProviderIdOrderBySlot_ProviderSchedule_ScheduleDateAsc(providerId);

        return appointments.stream()
                .map(SchedulingMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<AppointmentDetailsDto> getAllAppointmentDetailsForPatient(long patientId, LocalDate date) {
        List<Appointment> appointments = appointmentRepository.findByPatientIdAndSlot_ProviderSchedule_ScheduleDateOrderBySlot_StartAsc(patientId, date);

        return appointments.stream()
                .map(SchedulingMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentDetailsDto> getAllAppointmentDetailsForProvider(long providerId, LocalDate date) {
        List<Appointment> appointments = appointmentRepository.findByProviderIdAndSlot_ProviderSchedule_ScheduleDateOrderBySlot_StartAsc(providerId, date);

        return appointments.stream()
                .map(SchedulingMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    @Auditable(action = Action.CONSUME_APPOINTMENT, message = "Consumed appointment with appointmentId: ?1")
    public void consumeAppointment(long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentNotFoundException(appointmentId));

        if(!appointment.getAppointmentStatus().equals(AppointmentStatus.SCHEDULED))
            throw new InvalidAppointmentException(appointmentId);

        appointment.setAppointmentStatus(AppointmentStatus.COMPLETED);

        appointmentRepository.save(appointment);
    }
}
