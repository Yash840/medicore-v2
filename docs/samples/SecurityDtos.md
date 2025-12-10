# Security DTOs

This document contains all the Data Transfer Objects (DTOs) related to user authentication and authorization.

## UserCredsDto

**Description**: DTO for user credentials used during authentication/login.

**Schema**:
```json
{
  "username": "string",
  "password": "string"
}
```

**Example**:
```json
{
  "username": "john.doe",
  "password": "SecurePassword123!"
}
```

---

## UserDetailsDto

**Description**: DTO containing complete user information including roles, permissions, and timestamps.

**Schema**:
```json
{
  "userId": "number",
  "username": "string",
  "roleName": "string",
  "permissions": ["string"],
  "createdAt": "string (ISO 8601 date)",
  "updatedAt": "string (ISO 8601 date)"
}
```

**Example**:
```json
{
  "userId": 5001,
  "username": "john.doe",
  "roleName": "PATIENT",
  "permissions": [
    "VIEW_OWN_APPOINTMENTS",
    "SCHEDULE_APPOINTMENT",
    "VIEW_OWN_MEDICAL_RECORDS",
    "UPDATE_OWN_PROFILE"
  ],
  "createdAt": "2024-01-15",
  "updatedAt": "2025-12-10"
}
```

