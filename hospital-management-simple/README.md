# Hospital Management System - Simple Student Project

A basic hospital management system built with Spring Boot for tracking doctors, patients, and appointments.

## Features
- Add and view doctors
- Add and view patients  
- Book and manage appointments
- Built-in H2 database for testing

## How to Run

1. Make sure you have Java 17 and Maven installed
2. Open terminal in the project directory
3. Run: `mvn spring-boot:run`
4. Access the application at: http://localhost:8080

## API Endpoints

### Doctors
- GET `/api/doctors` - Get all doctors
- POST `/api/doctors` - Add new doctor

### Patients  
- GET `/api/patients` - Get all patients
- POST `/api/patients` - Add new patient

### Appointments
- GET `/api/appointments` - Get all appointments
- POST `/api/appointments` - Book new appointment

## Database Console
Access H2 console at: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:hospital`
- Username: `sa`
- Password: (empty)

## Project Structure
```
src/main/java/hospital/
├── Main.java              # Application entry point
├── models/                # Data models
├── controller/            # REST controllers
├── service/              # Business logic
├── repository/           # Database access
└── config/               # Configuration
```

## Sample Data
The application automatically loads sample data on startup for testing.
