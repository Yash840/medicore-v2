package org.cross.medicore.scheduling.internals.mapper;

import org.cross.medicore.scheduling.api.dto.AppointmentDetailsDto;
import org.cross.medicore.scheduling.api.dto.ScheduleAppointmentDto;
import org.cross.medicore.scheduling.internals.entity.Appointment;
import org.cross.medicore.scheduling.internals.entity.Slot;

public class SchedulingMapper {

    public static Appointment toEntity(ScheduleAppointmentDto dto) {
        Appointment appointment = new Appointment();
        appointment.setPatientId(dto.patientId());
        appointment.setProviderId(dto.providerId());
        appointment.setAppointmentType(dto.appointmentType());
        appointment.setAppointmentStatus(org.cross.medicore.scheduling.api.constants.AppointmentStatus.SCHEDULED);
        return appointment;
    }

    public static AppointmentDetailsDto toDto(Appointment appointment) {
        AppointmentDetailsDto.SlotDetailsDto slotDetails = new AppointmentDetailsDto.SlotDetailsDto(
                appointment.getSlot().getSlotId(),
                appointment.getSlot().getProviderSchedule().getProviderId(),
                appointment.getSlot().getStart(),
                appointment.getSlot().getEnd()
        );

        return new AppointmentDetailsDto(
                appointment.getAppointmentId(),
                appointment.getPatientId(),
                appointment.getProviderId(),
                appointment.getAppointmentType(),
                appointment.getAppointmentStatus(),
                slotDetails
        );
    }
}
