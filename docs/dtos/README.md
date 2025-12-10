# Medicore DTOs Documentation

This directory contains comprehensive documentation for all Data Transfer Objects (DTOs) used in the Medicore application.

## Overview

DTOs are used to transfer data between different layers of the application, particularly between the API layer and clients. Each DTO is documented with its schema and JSON examples.

## DTO Categories

### 1. [Patient DTOs](PatientDtos.md)
Documentation for patient-related data transfer objects.

**DTOs included:**
- `PatientPublicDto` - Complete patient information
- `CreatePatientDto` - Create new patient
- `UpdatePatientInfoDto` - Update patient information

---

### 2. [Provider DTOs](ProviderDtos.md)
Documentation for healthcare provider-related data transfer objects.

**DTOs included:**
- `CreateProviderDto` - Create new provider
- `ProviderDetails` - Complete provider information
- `UpdateProviderDetailsDto` - Update provider details

---

### 3. [Scheduling DTOs](SchedulingDtos.md)
Documentation for appointment scheduling and provider schedule management.

**DTOs included:**
- `CreateProviderScheduleDto` - Create provider schedule
- `ProviderScheduleDetailsDto` - Provider schedule details
- `ScheduleAppointmentDto` - Schedule new appointment
- `AppointmentDetailsDto` - Appointment details

---

### 4. [Security DTOs](SecurityDtos.md)
Documentation for authentication and authorization-related data transfer objects.

**DTOs included:**
- `UserCredsDto` - User credentials for authentication
- `UserDetailsDto` - Complete user information

---

### 5. [Clinical DTOs](ClinicalDtos.md)
Documentation for clinical operations such as vitals and medications.

**DTOs included:**
- `UpdateVitalsDto` - Update patient vitals
- `AddMedicationDto` - Add medication prescription

---

## Common Enumerations

### BloodGroup
```
A_POSITIVE, A_NEGATIVE, B_POSITIVE, B_NEGATIVE, 
AB_POSITIVE, AB_NEGATIVE, O_POSITIVE, O_NEGATIVE, UNKNOWN
```

### Sex
```
MALE, FEMALE, TRANSGENDER, PREFER_NOT_TO_SAY
```

### PatientStatus
```
ACTIVE, INACTIVE
```

### AppointmentType
```
CONSULTATION, FOLLOW_UP, ROUTINE, UNKNOWN
```

### AppointmentStatus
```
SCHEDULED, CANCELLED, COMPLETED, UNKNOWN
```

---

## Date and Time Formats

- **Date**: ISO 8601 format - `YYYY-MM-DD` (e.g., `2025-12-10`)
- **DateTime**: ISO 8601 format - `YYYY-MM-DDTHH:mm:ss` (e.g., `2025-12-15T10:00:00`)

---

## Document Conventions

Each DTO documentation includes:
1. **Description**: Purpose and usage of the DTO
2. **Schema**: JSON schema showing field names, types, and constraints
3. **Example**: A complete, realistic JSON example

---

*Last Updated: December 10, 2025*

