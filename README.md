# Hospital Management System (HMS) Backend

A simple, clean, and production-ready Spring Boot backend for hospital management with JWT authentication and role-based access control.

## 🚀 Quick Start - **NO DATABASE REQUIRED!**

### Prerequisites
- Java 17+
- Maven 3.6+

### Run the Application
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

**That's it!** The application uses an embedded H2 database that starts automatically.

The application will start on `http://localhost:8080`

## 📚 API Documentation

### Swagger UI
Access interactive API documentation at:
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs
- **H2 Database Console**: http://localhost:8080/h2-console

### Authentication
All endpoints (except `/api/v1/auth/**`) require JWT authentication.

**Login Endpoint:**
```
POST /api/v1/auth/login
{
  "username": "admin",
  "password": "admin123"
}
```

**Default Users:**
- **Admin**: username `admin`, password `admin123`
- **Doctor**: username `doctor1`, password `doctor123`  
- **Clerk**: username `clerk1`, password `clerk123`

## 🔐 Security & Roles

### Role-Based Access Control
- **ADMIN**: Full access to all operations
- **DOCTOR**: Read access to patients/appointments/bills, manage own appointments
- **CLERK**: Create/update patients/appointments/bills, read access to doctors

### Protected Endpoints
- **Patients**: `/api/v1/patients/**`
- **Doctors**: `/api/v1/doctors/**`
- **Appointments**: `/api/v1/appointments/**`
- **Bills**: `/api/v1/bills/**`

## 📁 Project Structure

```
backend/
├── pom.xml                     # Maven dependencies
├── src/main/java/com/hms/
│   ├── Application.java       # Main application class
│   ├── config/
│   │   └── SecurityConfig.java # Security configuration
│   ├── controller/            # REST controllers
│   │   ├── AuthController.java
│   │   ├── PatientController.java
│   │   ├── DoctorController.java
│   │   ├── AppointmentController.java
│   │   ├── BillController.java
│   │   └── GlobalExceptionHandler.java
│   ├── dto/                   # Data Transfer Objects
│   ├── exception/             # Custom exceptions
│   ├── model/                 # JPA entities
│   ├── repository/            # Data access layer
│   ├── security/              # JWT security components
│   └── service/               # Business logic layer
└── src/main/resources/
    ├── application.yml        # Configuration
    └── db/seed.sql           # Initial data
```

## 🛠️ Technologies Used

- **Java 17** - Programming language
- **Spring Boot 3.2.0** - Application framework
- **Spring Security** - Authentication & authorization
- **Spring Data JPA** - Database access
- **H2 Database** - Embedded database (no setup required!)
- **JWT** - Token-based authentication
- **Lombok** - Reduce boilerplate code
- **SpringDoc OpenAPI** - API documentation
- **Maven** - Build tool

## 📊 Database Schema

### Core Tables
- **users** - Authentication users
- **roles** - User roles (ADMIN, DOCTOR, CLERK)
- **patients** - Patient information
- **doctors** - Doctor information
- **appointments** - Patient-doctor appointments
- **bills** - Patient billing information

## 🧪 Testing the API

### 1. Get JWT Token
```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'
```

### 2. Use Token in API Calls
```bash
curl -X GET http://localhost:8080/api/v1/patients \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## 🔧 Configuration

### Application Properties (`application.yml`)
- Database connection settings
- JWT secret and expiration
- Server port (default: 8080)
- Swagger configuration

### Environment Variables
You can override configuration using environment variables:
- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `JWT_SECRET`
- `JWT_EXPIRATIONMS`

## 🚀 Deployment

### Docker (Optional)
```bash
docker build -t hms-backend .
docker run -p 8080:8080 hms-backend
```

### Production Considerations
- Change default JWT secret
- Use environment variables for sensitive data
- Configure proper database connection pool
- Enable HTTPS in production
- Set appropriate CORS policies

## 📝 Features

- ✅ JWT-based authentication
- ✅ Role-based authorization
- ✅ RESTful API design
- ✅ Input validation
- ✅ Error handling
- ✅ API documentation (Swagger)
- ✅ Database seeding
- ✅ Clean architecture
- ✅ Production-ready configuration

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License.
