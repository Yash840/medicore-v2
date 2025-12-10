# 🏥 Medicore - Electronic Health Records System

<div align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen?style=for-the-badge&logo=spring)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue?style=for-the-badge&logo=mysql)
![Maven](https://img.shields.io/badge/Maven-3.8+-red?style=for-the-badge&logo=apache-maven)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

**An Enterprise-Grade Healthcare Tool for Managing Medical Records Electronically**

[Features](#-features) • [Quick Start](#-quick-start) • [Documentation](#-documentation) • [API Reference](#-api-reference) • [Tech Stack](#-tech-stack)

</div>

---

## 📋 Table of Contents

- [About](#-about)
- [Features](#-features)
- [Architecture](#-architecture)
- [Tech Stack](#-tech-stack)
- [Prerequisites](#-prerequisites)
- [Quick Start](#-quick-start)
- [Configuration](#-configuration)
- [API Reference](#-api-reference)
- [Project Structure](#-project-structure)
- [Documentation](#-documentation)
- [Development](#-development)
- [Testing](#-testing)
- [Deployment](#-deployment)
- [Contributing](#-contributing)
- [License](#-license)

---

## 🎯 About

**Medicore** is a comprehensive Electronic Health Records (EHR) system designed to streamline healthcare operations. Built with modern technologies and best practices, it provides a robust platform for managing patients, healthcare providers, appointments, and clinical encounters.

The system follows a modular monolithic architecture using **Spring Modulith**, ensuring clean separation of concerns while maintaining the simplicity of deployment.

---

## ✨ Features

### 👥 Patient Management
- ✅ Complete patient registration and profile management
- ✅ Comprehensive patient demographics and medical history
- ✅ Blood group and vital health information tracking
- ✅ Patient search and filtering capabilities

### 👨‍⚕️ Provider Management
- ✅ Healthcare provider registration and management
- ✅ Provider profiles with specialization tracking
- ✅ Provider availability and schedule management
- ✅ Multi-provider support

### 📅 Scheduling System
- ✅ Intelligent appointment scheduling
- ✅ Provider availability slot management
- ✅ Time slot conflict prevention
- ✅ Appointment status tracking (scheduled, completed, cancelled)
- ✅ Patient appointment history

### 🏥 Clinical Management
- ✅ Clinical encounter documentation
- ✅ Vital signs recording (blood pressure, heart rate, temperature, etc.)
- ✅ Medication prescribing and tracking
- ✅ Clinical notes and observations
- ✅ Encounter lifecycle management (begin, in-progress, end)

### 🔐 Security & Authentication
- ✅ JWT-based authentication
- ✅ Role-based access control (RBAC)
- ✅ Secure password encryption
- ✅ Token-based session management
- ✅ API endpoint security

### 📊 Additional Features
- ✅ RESTful API architecture
- ✅ Comprehensive API documentation with Swagger/OpenAPI
- ✅ Database migration management with Flyway
- ✅ Audit logging with JPA Auditing
- ✅ Exception handling and error responses
- ✅ CORS configuration for frontend integration

---

## 🏗️ Architecture

Medicore follows a **Modular Monolithic Architecture** using Spring Modulith:

```
┌─────────────────────────────────────────┐
│          API Gateway Layer              │
│   (Spring Web Controllers + Security)   │
└──────────────┬──────────────────────────┘
               │
┌──────────────┴──────────────────────────┐
│        Orchestration Layer              │
│     (Cross-module coordination)         │
└──────────────┬──────────────────────────┘
               │
     ┌─────────┴─────────┐
     │                   │
┌────┴─────┐  ┌─────────┴────┐  ┌─────────────┐
│ Patients │  │  Scheduling  │  │  Clinicals  │
│  Module  │  │    Module    │  │   Module    │
└────┬─────┘  └──────┬───────┘  └──────┬──────┘
     │               │                  │
     └───────────────┴──────────────────┘
                     │
          ┌──────────┴──────────┐
          │   Shared Services   │
          │   (Security, etc.)  │
          └─────────────────────┘
                     │
          ┌──────────┴──────────┐
          │    Data Layer       │
          │  (JPA/Hibernate)    │
          └─────────────────────┘
                     │
          ┌──────────┴──────────┐
          │   MySQL Database    │
          └─────────────────────┘
```

### Module Overview

- **Security Module**: Handles authentication, authorization, and JWT token management
- **Patients Module**: Manages patient data and profiles
- **Scheduling Module**: Handles appointments, slots, and provider schedules
- **Clinicals Module**: Manages clinical encounters, vitals, and medications
- **Orchestration Layer**: Coordinates cross-module operations
- **Shared Module**: Common utilities, enums, and shared domain objects

---

## 🛠️ Tech Stack

### Core Framework
- **Java 21** - Modern Java LTS version
- **Spring Boot 3.5.7** - Application framework
- **Spring Modulith 1.4.4** - Modular monolithic architecture

### Data & Persistence
- **Spring Data JPA** - Data access abstraction
- **Hibernate** - ORM framework
- **MySQL** - Relational database
- **Flyway** - Database migration management

### Security
- **Spring Security** - Security framework
- **JJWT (0.13.0)** - JWT token generation and validation
- **BCrypt** - Password hashing

### API Documentation
- **SpringDoc OpenAPI 2.7.0** - API documentation generation
- **Swagger UI** - Interactive API documentation

### Development Tools
- **Lombok** - Boilerplate code reduction
- **Spring Boot DevTools** - Development productivity
- **Dotenv Java** - Environment variable management
- **Maven** - Build and dependency management

---

## 📦 Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK) 21** or higher
  - Download from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
- **Apache Maven 3.8+** (optional if using Maven Wrapper)
- **MySQL 8.0+** database server
- **Git** for version control
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code recommended)

---

## 🚀 Quick Start

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/Yash840/medicore-v2.git
cd medicore-v2
```

### 2️⃣ Setup Database

Create a MySQL database:

```sql
CREATE DATABASE medicore_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 3️⃣ Configure Environment Variables

Create a `.env` file in the project root:

```env
DB_URL=jdbc:mysql://localhost:3306/medicore_db
DB_USERNAME=your_username
DB_PASSWORD=your_password
JWT_SECRET=your_64_character_minimum_secret_key_here
JWT_EXPIRATION=86400000
```

> 💡 **Tip**: See [Environment Setup Guide](docs/ENV_SETUP.md) for detailed configuration instructions.

### 4️⃣ Build the Project

Using Maven Wrapper (Windows):
```cmd
mvnw.cmd clean install
```

Using Maven Wrapper (Linux/Mac):
```bash
./mvnw clean install
```

Or using Maven directly:
```bash
mvn clean install
```

### 5️⃣ Run the Application

Using Maven Wrapper (Windows):
```cmd
mvnw.cmd spring-boot:run
```

Using Maven Wrapper (Linux/Mac):
```bash
./mvnw spring-boot:run
```

Or using Maven directly:
```bash
mvn spring-boot:run
```

### 6️⃣ Access the Application

- **Application**: http://localhost:10001

---

## ⚙️ Configuration

### Application Profiles

The application supports multiple profiles:

- **local** - Local development environment
- **prod** - Production environment

Activate a profile:

```bash
# Windows
mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=local

# Linux/Mac
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

### Configuration Files

- `application.yaml` - Base configuration
- `application-local.yaml` - Local environment overrides
- `application-prod.yaml` - Production environment overrides

### Key Configuration Properties

```yaml
# Server Configuration
server:
  port: 10001

# Database Configuration
spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}

# JWT Configuration
application:
  security:
    jwt:
      secret-key: ${JWT_SECRET}
      expiration: ${JWT_EXPIRATION}
```

---

## 📚 API Reference

### Base URL
```
http://localhost:10001/api/v1
```

### Authentication

#### Login
```http
POST /api/v1/auth/login
Content-Type: application/json

{
  "username": "user@example.com",
  "password": "password"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIs...",
  "type": "Bearer",
  "expiresIn": 86400000
}
```

### Core Endpoints

#### Patient Management
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/services/patients/{patientId}` | Get patient by ID |
| `POST` | `/services/patients` | Create new patient |
| `PATCH` | `/services/patients/{patientId}` | Update patient |
| `DELETE` | `/services/patients/{patientId}` | Delete patient |

#### Provider Management
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/services/providers/{providerId}` | Get provider by ID |
| `POST` | `/services/providers` | Create new provider |
| `PATCH` | `/services/providers/{providerId}` | Update provider |
| `DELETE` | `/services/providers/{providerId}` | Delete provider |

#### Scheduling
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/services/scheduling/slots/provider/{providerId}` | Get available slots |
| `POST` | `/services/scheduling/appointments` | Schedule appointment |
| `GET` | `/services/scheduling/appointments/{appointmentId}` | Get appointment |
| `GET` | `/services/scheduling/appointments/patient/{patientId}` | Get patient appointments |
| `POST` | `/services/scheduling/provider-schedules` | Create provider schedule |

#### Clinical Management
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/services/clinicals/encounters/appointment/{appointmentId}` | Begin encounter |
| `GET` | `/services/clinicals/encounters/{encounterId}` | Get encounter details |
| `PATCH` | `/services/clinicals/encounters/{encounterId}/end` | End encounter |
| `POST` | `/services/clinicals/encounters/{encounterId}/medications` | Add medications |
| `POST` | `/services/clinicals/encounters/{encounterId}/vitals` | Add vitals |


## 📂 Project Structure

```
medicore/
├── docs/                           # Documentation
│   ├── API_README.md              # API documentation overview
│   ├── ENV_SETUP.md               # Environment setup guide
│   └── samples/                   # DTO samples and examples
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/cross/medicore/
│   │   │       ├── MedicoreApplication.java    # Main application class
│   │   │       ├── clinicals/                  # Clinical module
│   │   │       │   ├── api/                    # REST controllers
│   │   │       │   └── internals/              # Business logic & repositories
│   │   │       ├── exception/                  # Global exception handling
│   │   │       ├── orchestration/              # Cross-module orchestration
│   │   │       ├── patients/                   # Patient module
│   │   │       │   ├── api/                    # REST controllers
│   │   │       │   └── internals/              # Business logic & repositories
│   │   │       ├── scheduling/                 # Scheduling module
│   │   │       │   ├── api/                    # REST controllers
│   │   │       │   └── internals/              # Business logic & repositories
│   │   │       ├── security/                   # Security & authentication
│   │   │       │   ├── api/                    # Auth controllers
│   │   │       │   ├── auth/                   # Auth services
│   │   │       │   ├── config/                 # Security configuration
│   │   │       │   ├── internals/              # User management
│   │   │       │   └── jwt/                    # JWT utilities
│   │   │       ├── shared/                     # Shared utilities & enums
│   │   │       └── system/                     # System configuration
│   │   └── resources/
│   │       ├── application.yaml               # Base configuration
│   │       ├── application-local.yaml         # Local profile config
│   │       ├── application-prod.yaml          # Production profile config
│   │       ├── db/migration/                  # Flyway migration scripts
│   │       └── static/docs/
│   │           └── openapi-spec.yaml          # OpenAPI specification
│   └── test/                                  # Test files
├── target/                                    # Build output
├── .env.example                               # Environment variables template
├── .gitignore                                 # Git ignore rules
├── HELP.md                                    # Spring Boot help
├── mvnw                                       # Maven wrapper (Unix)
├── mvnw.cmd                                   # Maven wrapper (Windows)
├── pom.xml                                    # Maven configuration
└── README.md                                  # This file
```

### Module Responsibilities

#### 🏥 Clinicals Module
- Manages clinical encounters
- Records vital signs and observations
- Handles medication prescriptions
- Clinical documentation

#### 👥 Patients Module
- Patient registration and management
- Patient profile information
- Medical history tracking
- Patient search and retrieval

#### 📅 Scheduling Module
- Appointment scheduling
- Provider availability management
- Time slot management
- Schedule conflict prevention

#### 🔐 Security Module
- User authentication
- JWT token management
- Authorization and access control
- User management

#### 🎭 Orchestration Layer
- Coordinates cross-module operations
- Handles complex workflows
- Ensures data consistency across modules

---

## 📖 Documentation

Comprehensive documentation is available in the `docs/` directory:

- **[API Documentation](docs/API_README.md)** - Complete API reference
- **[Environment Setup](docs/ENV_SETUP.md)** - Environment configuration guide
- **[Sample DTOs](docs/samples/)** - Request/response examples

### Additional Resources

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Modulith Reference](https://docs.spring.io/spring-modulith/reference/)
- [Spring Security Guide](https://docs.spring.io/spring-security/reference/)
- [Flyway Documentation](https://documentation.red-gate.com/fd)

---

## 💻 Development

### Setting Up Development Environment

1. **Import Project**
   - Open your IDE (IntelliJ IDEA recommended)
   - Import as Maven project
   - Wait for dependencies to download

2. **Configure Lombok**
   - Install Lombok plugin in your IDE
   - Enable annotation processing

3. **Database Setup**
   - Ensure MySQL is running
   - Create database as per Quick Start guide
   - Flyway will handle schema migrations automatically

4. **Run Application**
   - Use IDE's Spring Boot run configuration
   - Or use Maven command: `mvnw.cmd spring-boot:run`

### Development Best Practices

- ✅ Follow the existing package structure
- ✅ Use Lombok annotations to reduce boilerplate
- ✅ Write meaningful commit messages
- ✅ Add appropriate exception handling
- ✅ Document public APIs with Javadoc
- ✅ Use DTOs for API requests/responses
- ✅ Keep modules loosely coupled
- ✅ Write unit tests for new features

### Code Style

- Follow Java naming conventions
- Use 4 spaces for indentation
- Maximum line length: 120 characters
- Add blank line between methods
- Use meaningful variable names

---

## 🧪 Testing

### Running Tests

Run all tests:
```bash
# Windows
mvnw.cmd test

# Linux/Mac
./mvnw test
```

Run specific test class:
```bash
mvnw.cmd test -Dtest=MedicoreApplicationTests
```

### Test Structure

```
src/test/java/
└── org/cross/medicore/
    ├── MedicoreApplicationTests.java
    └── shared/
```

### Testing Best Practices

- Write unit tests for business logic
- Use integration tests for API endpoints
- Mock external dependencies
- Maintain high test coverage
- Use descriptive test method names

---


## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Authors & Acknowledgments

### Development Team

- **Project Lead** - Initial work and architecture
- **Contributors** - See [CONTRIBUTORS.md](CONTRIBUTORS.md) for full list

### Acknowledgments

- Spring Boot team for the excellent framework
- Spring Modulith for modular architecture patterns
- All contributors who have helped shape this project

---

## 📞 Support & Contact

### Getting Help

- 📖 Check the [Documentation](docs/API_README.md)
- 🐛 Report bugs via [GitHub Issues](https://github.com/yourusername/medicore/issues)
- 💬 Join our community discussions
- 📧 Email: support@medicore.example.com

### Useful Links

- [Project Homepage](https://github.com/yourusername/medicore)
- [Issue Tracker](https://github.com/yourusername/medicore/issues)
- [Documentation](docs/)
- [Release Notes](CHANGELOG.md)

---

## 🗺️ Roadmap

### Current Version (v2.0.0-SNAPSHOT)
- ✅ Core patient management
- ✅ Provider management
- ✅ Appointment scheduling
- ✅ Clinical encounters
- ✅ JWT authentication
- ✅ API documentation

### Future Enhancements
- 🔄 Advanced reporting and analytics
- 🔄 Prescription printing
- 🔄 Lab results integration
- 🔄 Billing and invoicing
- 🔄 Multi-language support
- 🔄 Mobile application
- 🔄 Real-time notifications
- 🔄 Document management
- 🔄 Telemedicine support

---

## ⭐ Show Your Support

If you find this project useful, please consider:

- ⭐ Starring the repository
- 🐛 Reporting bugs
- 💡 Suggesting new features
- 🤝 Contributing code
- 📢 Sharing with others

---

<div align="center">

**Built with ❤️ using Spring Boot and Java**

[⬆ Back to Top](#-medicore---electronic-health-records-system)

</div>

