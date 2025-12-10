# Patient DTOs

This document contains all the Data Transfer Objects (DTOs) related to patient management.

## PatientPublicDto

**Description**: DTO for public patient information including all patient details and address.

**Schema**:
```json
{
  "patientId": "number",
  "firstName": "string",
  "lastName": "string",
  "middleName": "string",
  "email": "string",
  "phoneNumber": "string",
  "birthDate": "string (ISO 8601 date)",
  "ageYears": "number",
  "height": "number",
  "weight": "number",
  "aadhaar": "string",
  "bloodGroup": "string (enum: A_POSITIVE, A_NEGATIVE, B_POSITIVE, B_NEGATIVE, AB_POSITIVE, AB_NEGATIVE, O_POSITIVE, O_NEGATIVE, UNKNOWN)",
  "sex": "string (enum: MALE, FEMALE, TRANSGENDER, PREFER_NOT_TO_SAY)",
  "status": "string (enum: ACTIVE, INACTIVE)",
  "address": {
    "street": "string",
    "building": "string",
    "landmark": "string",
    "flat": "string",
    "town": "string",
    "city": "string",
    "state": "string",
    "country": "string",
    "zipCode": "string"
  },
  "userId": "number"
}
```

**Example**:
```json
{
  "patientId": 1001,
  "firstName": "John",
  "lastName": "Doe",
  "middleName": "Michael",
  "email": "john.doe@example.com",
  "phoneNumber": "+1-555-0123",
  "birthDate": "1985-06-15",
  "ageYears": 39,
  "height": 175,
  "weight": 70,
  "aadhaar": "123456789012",
  "bloodGroup": "O_POSITIVE",
  "sex": "MALE",
  "status": "ACTIVE",
  "address": {
    "street": "123 Main Street",
    "building": "Building A",
    "landmark": "Near City Hospital",
    "flat": "Apt 4B",
    "town": "Downtown",
    "city": "New York",
    "state": "NY",
    "country": "USA",
    "zipCode": "10001"
  },
  "userId": 5001
}
```

---

## CreatePatientDto

**Description**: DTO for creating a new patient with basic information.

**Schema**:
```json
{
  "firstName": "string",
  "lastName": "string",
  "middleName": "string",
  "phoneNumber": "string",
  "email": "string",
  "birthDate": "string (ISO 8601 date)"
}
```

**Example**:
```json
{
  "firstName": "Jane",
  "lastName": "Smith",
  "middleName": "Elizabeth",
  "phoneNumber": "+1-555-0456",
  "email": "jane.smith@example.com",
  "birthDate": "1990-03-22"
}
```

---

## UpdatePatientInfoDto

**Description**: DTO for updating comprehensive patient information including personal details and address.

**Schema**:
```json
{
  "patientId": "number",
  "firstName": "string",
  "lastName": "string",
  "middleName": "string",
  "email": "string",
  "phoneNumber": "string",
  "birthDate": "string (ISO 8601 date)",
  "ageYears": "number",
  "height": "number",
  "weight": "number",
  "aadhaar": "string",
  "bloodGroup": "string (enum: A_POSITIVE, A_NEGATIVE, B_POSITIVE, B_NEGATIVE, AB_POSITIVE, AB_NEGATIVE, O_POSITIVE, O_NEGATIVE, UNKNOWN)",
  "sex": "string (enum: MALE, FEMALE, TRANSGENDER, PREFER_NOT_TO_SAY)",
  "address": {
    "street": "string",
    "building": "string",
    "landmark": "string",
    "flat": "string",
    "town": "string",
    "city": "string",
    "state": "string",
    "country": "string",
    "zipCode": "string"
  }
}
```

**Example**:
```json
{
  "patientId": 1001,
  "firstName": "John",
  "lastName": "Doe",
  "middleName": "Michael",
  "email": "john.doe.updated@example.com",
  "phoneNumber": "+1-555-0789",
  "birthDate": "1985-06-15",
  "ageYears": 39,
  "height": 176,
  "weight": 72,
  "aadhaar": "123456789012",
  "bloodGroup": "O_POSITIVE",
  "sex": "MALE",
  "address": {
    "street": "456 Oak Avenue",
    "building": "Building B",
    "landmark": "Near Central Park",
    "flat": "Suite 12C",
    "town": "Midtown",
    "city": "New York",
    "state": "NY",
    "country": "USA",
    "zipCode": "10019"
  }
}
```

