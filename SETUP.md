# 🚀 Hospital Management System - Professional Setup Guide

## 📋 Prerequisites

### Required Software
1. **Java 17+** - Download from [Oracle Java](https://www.oracle.com/java/technologies/downloads/) or use [OpenJDK](https://openjdk.org/)
2. **Maven 3.6+** - Download from [Apache Maven](https://maven.apache.org/download.cgi/)

### Installation Check
```bash
java -version
mvn -version
```

## 🏗️ Project Structure

```
backend/src/main/java/com/hms/
├── config/
│   ├── SecurityConfig.java       # 🔐 JWT + Spring Security
│   └── DataInitializer.java      # 📊 Database seeding
├── security/
│   ├── JwtTokenProvider.java     # 🎫 JWT token generation
│   ├── JwtAuthenticationFilter.java  # 🔍 Request filtering
│   └── JwtAuthenticationEntryPoint.java  # ❌ Error handling
├── controller/
│   ├── AuthController.java       # 🔑 Authentication endpoints
│   ├── PatientController.java    # 👥 Patient management (RBAC)
│   └── DoctorController.java     # 👨‍⚕️ Doctor management (RBAC)
├── service/
│   ├── CustomUserDetailsService.java  # 👤 User authentication
│   ├── PatientService.java       # 🏥 Patient business logic
│   └── DoctorService.java        # 🩺 Doctor business logic
├── repository/
│   ├── UserRepository.java       # 👤 User data access
│   ├── RoleRepository.java       # 🎭 Role data access
│   ├── PatientRepository.java    # 👥 Patient data access
│   └── DoctorRepository.java     # 👨‍⚕️ Doctor data access
├── model/
│   ├── User.java                 # 👤 User entity (implements UserDetails)
│   ├── Role.java                 # 🎭 Role entity
│   ├── Patient.java              # 👥 Patient entity
│   ├── Doctor.java               # 👨‍⚕️ Doctor entity
│   ├── Appointment.java          # 📅 Appointment entity
│   └── Bill.java                 # 💰 Billing entity
└── dto/
    ├── PatientDTO.java           # 📋 Patient data transfer
    ├── DoctorDTO.java            # 📋 Doctor data transfer
    └── LoginDTO.java             # 🔑 Login data transfer
```

## 🚀 Quick Start

### 1. Start the Application
```bash
cd backend
mvn spring-boot:run
```

### 2. Access Points
- **API Base URL**: http://localhost:8080/api/v1
- **H2 Console**: http://localhost:8080/h2-console
- **Swagger UI**: http://localhost:8080/swagger-ui.html

### 3. Default Login Credentials
```json
{
  "username": "admin",
  "password": "admin123"
}
```

## 🔐 Authentication & Security

### JWT Token Usage
1. **Login** to get JWT token:
```bash
POST http://localhost:8080/api/v1/auth/login
{
  "username": "admin",
  "password": "admin123"
}
```

2. **Use token** in subsequent requests:
```bash
Authorization: Bearer <your-jwt-token>
```

### Role-Based Access Control (RBAC)
- **ADMIN**: Full access to all endpoints
- **DOCTOR**: Read access to patients/doctors, limited write access
- **CLERK**: Patient/doctor management, no admin functions
- **PATIENT**: Read-only access to doctors and appointments

## 📚 API Endpoints

### Authentication
- `POST /api/v1/auth/login` - User login
- `GET /api/v1/auth/health` - Service health check

### Patients (RBAC Protected)
- `GET /api/v1/patients` - Get all patients
- `GET /api/v1/patients/{id}` - Get patient by ID
- `POST /api/v1/patients` - Create patient (ADMIN/CLERK)
- `PUT /api/v1/patients/{id}` - Update patient (ADMIN/CLERK)
- `DELETE /api/v1/patients/{id}` - Delete patient (ADMIN)

### Doctors (RBAC Protected)
- `GET /api/v1/doctors` - Get all doctors
- `GET /api/v1/doctors/{id}` - Get doctor by ID
- `GET /api/v1/doctors/specialty/{specialty}` - Get doctors by specialty
- `POST /api/v1/doctors` - Create doctor (ADMIN)
- `PUT /api/v1/doctors/{id}` - Update doctor (ADMIN)
- `DELETE /api/v1/doctors/{id}` - Delete doctor (ADMIN)

## 🌟 Key Features

✅ **Spring Boot 3.2** with Java 17  
✅ **JWT Authentication** with stateless sessions  
✅ **Role-Based Access Control** (RBAC)  
✅ **RESTful API Design** with proper HTTP methods  
✅ **DTO Pattern** for data transfer  
✅ **Layered Architecture** (Controller → Service → Repository)  
✅ **JPA Entities** with proper relationships  
✅ **Input Validation** with Jakarta validation  
✅ **Comprehensive Logging**  
✅ **H2 Database** with auto-initialization  
✅ **Swagger Documentation**  
✅ **Professional Error Handling**  

**Perfect for academic presentations and real-world deployment!** 🚀
