# Clinical DTOs

This document contains all the Data Transfer Objects (DTOs) related to clinical operations such as patient vitals and medications.

## UpdateVitalsDto

**Description**: DTO for updating patient vital signs and measurements during a clinical encounter.

**Schema**:
```json
{
  "bodyTemperatureCelsius": "number (double)",
  "pulseRateBpm": "number (integer)",
  "respiratoryRateBrPm": "number (integer)",
  "bloodPressureMmhg": "number (double)",
  "oxygenSaturationPercent": "number (double)",
  "heightMtr": "number (double)",
  "weightKg": "number (double)"
}
```

**Example**:
```json
{
  "bodyTemperatureCelsius": 37.2,
  "pulseRateBpm": 72,
  "respiratoryRateBrPm": 16,
  "bloodPressureMmhg": 120.0,
  "oxygenSaturationPercent": 98.5,
  "heightMtr": 1.75,
  "weightKg": 70.5
}
```

---

## AddMedicationDto

**Description**: DTO for adding medication prescriptions to a patient's treatment plan.

**Schema**:
```json
{
  "name": "string",
  "dosage": "string",
  "pattern": "string",
  "quantity": "number (integer)",
  "guidelines": "string"
}
```

**Example**:
```json
{
  "name": "Amoxicillin",
  "dosage": "500mg",
  "pattern": "Three times daily",
  "quantity": 21,
  "guidelines": "Take with food. Complete the full course of antibiotics even if symptoms improve."
}
```

