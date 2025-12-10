# Provider DTOs

This document contains all the Data Transfer Objects (DTOs) related to healthcare provider management.

## CreateProviderDto

**Description**: DTO for creating a new healthcare provider with basic registration information.

**Schema**:
```json
{
  "firstName": "string",
  "middleName": "string",
  "lastName": "string",
  "email": "string",
  "specialization": "string",
  "mmcRegNumber": "string"
}
```

**Example**:
```json
{
  "firstName": "Sarah",
  "middleName": "Ann",
  "lastName": "Johnson",
  "email": "dr.sarah.johnson@hospital.com",
  "specialization": "Cardiology",
  "mmcRegNumber": "MMC123456789"
}
```

---

## ProviderDetails

**Description**: DTO containing complete provider information including personal details, contact information, and address.

**Schema**:
```json
{
  "providerId": "number",
  "firstName": "string",
  "middleName": "string",
  "lastName": "string",
  "email": "string",
  "phoneNumber": "string",
  "birthDate": "string (ISO 8601 date)",
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
  },
  "specialization": "string",
  "mmcRegNumber": "string",
  "userId": "number"
}
```

**Example**:
```json
{
  "providerId": 2001,
  "firstName": "Sarah",
  "middleName": "Ann",
  "lastName": "Johnson",
  "email": "dr.sarah.johnson@hospital.com",
  "phoneNumber": "+1-555-1234",
  "birthDate": "1978-09-12",
  "bloodGroup": "A_POSITIVE",
  "sex": "FEMALE",
  "address": {
    "street": "789 Medical Plaza",
    "building": "Healthcare Center",
    "landmark": "Next to General Hospital",
    "flat": "Office 301",
    "town": "Medical District",
    "city": "Boston",
    "state": "MA",
    "country": "USA",
    "zipCode": "02115"
  },
  "specialization": "Cardiology",
  "mmcRegNumber": "MMC123456789",
  "userId": 6001
}
```

---

## UpdateProviderDetailsDto

**Description**: DTO for updating comprehensive provider information including personal and professional details.

**Schema**:
```json
{
  "firstName": "string",
  "middleName": "string",
  "lastName": "string",
  "email": "string",
  "phoneNumber": "string",
  "birthDate": "string (ISO 8601 date)",
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
  },
  "specialization": "string",
  "mmcRegNumber": "string"
}
```

**Example**:
```json
{
  "firstName": "Sarah",
  "middleName": "Ann",
  "lastName": "Johnson",
  "email": "dr.sarah.johnson.updated@hospital.com",
  "phoneNumber": "+1-555-5678",
  "birthDate": "1978-09-12",
  "bloodGroup": "A_POSITIVE",
  "sex": "FEMALE",
  "address": {
    "street": "1200 Healthcare Boulevard",
    "building": "Medical Tower",
    "landmark": "Opposite City Medical Center",
    "flat": "Suite 505",
    "town": "Medical District",
    "city": "Boston",
    "state": "MA",
    "country": "USA",
    "zipCode": "02116"
  },
  "specialization": "Interventional Cardiology",
  "mmcRegNumber": "MMC123456789"
}
```

