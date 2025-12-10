# Scheduling DTOs

This document contains all the Data Transfer Objects (DTOs) related to appointment scheduling and provider schedule management.

## CreateProviderScheduleDto

**Description**: DTO for creating a provider's schedule for a specific date with time slots and breaks.

**Schema**:
```json
{
  "providerId": "number",
  "scheduleDate": "string (ISO 8601 date)",
  "scheduleStartTime": "string (ISO 8601 date-time)",
  "scheduleEndTime": "string (ISO 8601 date-time)",
  "breaks": [
    {
      "start": "string (ISO 8601 date-time)",
      "end": "string (ISO 8601 date-time)",
      "breakName": "string"
    }
  ]
}
```

**Example**:
```json
{
  "providerId": 2001,
  "scheduleDate": "2025-12-15",
  "scheduleStartTime": "2025-12-15T09:00:00",
  "scheduleEndTime": "2025-12-15T17:00:00",
  "breaks": [
    {
      "start": "2025-12-15T12:00:00",
      "end": "2025-12-15T13:00:00",
      "breakName": "Lunch Break"
    },
    {
      "start": "2025-12-15T15:00:00",
      "end": "2025-12-15T15:15:00",
      "breakName": "Coffee Break"
    }
  ]
}
```

---

## ProviderScheduleDetailsDto

**Description**: DTO containing complete provider schedule details including all breaks with their IDs.

**Schema**:
```json
{
  "id": "number",
  "providerId": "number",
  "scheduleDate": "string (ISO 8601 date)",
  "scheduleStartTime": "string (ISO 8601 date-time)",
  "scheduleEndTime": "string (ISO 8601 date-time)",
  "breaks": [
    {
      "breakId": "number",
      "start": "string (ISO 8601 date-time)",
      "end": "string (ISO 8601 date-time)",
      "breakName": "string"
    }
  ]
}
```

**Example**:
```json
{
  "id": 501,
  "providerId": 2001,
  "scheduleDate": "2025-12-15",
  "scheduleStartTime": "2025-12-15T09:00:00",
  "scheduleEndTime": "2025-12-15T17:00:00",
  "breaks": [
    {
      "breakId": 701,
      "start": "2025-12-15T12:00:00",
      "end": "2025-12-15T13:00:00",
      "breakName": "Lunch Break"
    },
    {
      "breakId": 702,
      "start": "2025-12-15T15:00:00",
      "end": "2025-12-15T15:15:00",
      "breakName": "Coffee Break"
    }
  ]
}
```

---

## ScheduleAppointmentDto

**Description**: DTO for scheduling a new appointment between a patient and provider for a specific time slot.

**Schema**:
```json
{
  "patientId": "number",
  "providerId": "number",
  "slotId": "number",
  "appointmentType": "string (enum: CONSULTATION, FOLLOW_UP, ROUTINE, UNKNOWN)"
}
```

**Example**:
```json
{
  "patientId": 1001,
  "providerId": 2001,
  "slotId": 3001,
  "appointmentType": "CONSULTATION"
}
```

---

## AppointmentDetailsDto

**Description**: DTO containing complete appointment details including patient, provider, slot information, and status.

**Schema**:
```json
{
  "appointmentId": "number",
  "patientId": "number",
  "providerId": "number",
  "appointmentType": "string (enum: CONSULTATION, FOLLOW_UP, ROUTINE, UNKNOWN)",
  "appointmentStatus": "string (enum: SCHEDULED, CANCELLED, COMPLETED, UNKNOWN)",
  "slot": {
    "slotId": "number",
    "providerScheduleId": "number",
    "start": "string (ISO 8601 date-time)",
    "end": "string (ISO 8601 date-time)"
  }
}
```

**Example**:
```json
{
  "appointmentId": 4001,
  "patientId": 1001,
  "providerId": 2001,
  "appointmentType": "CONSULTATION",
  "appointmentStatus": "SCHEDULED",
  "slot": {
    "slotId": 3001,
    "providerScheduleId": 501,
    "start": "2025-12-15T10:00:00",
    "end": "2025-12-15T10:30:00"
  }
}
```

