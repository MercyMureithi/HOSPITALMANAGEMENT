# 🏥 Hospital Management System

My college project for managing hospital data with Spring Boot. Built this to demonstrate REST APIs and database concepts.

## What This Project Has

- **Spring Boot 3.2** with Java 17
- **Simple setup** - One controller handles everything
- **Single service class** - All the main logic in one place  
- **H2 Database** - In-memory, easy to use
- **Sample data** - Auto-loads some doctors and patients for testing
- **Basic CRUD operations** - Can add, view, update, delete doctors and patients

---

## How to Run This Thing

### What You Need First
- **Java 17** or newer
- **Maven** (or just use the wrapper I included)

### Get Started
1. Open command prompt/terminal
2. Go to the backend folder:
```bash
cd backend
```

3. Run the application:
```bash
# Use this if you don't have Maven installed
.\mvnw.cmd spring-boot:run

# OR use this if you have Maven
mvn spring-boot:run
```

4. Wait until you see something like:
```
Started Application in 3.456 seconds
Database initialization completed!
```

5. Test it in your browser:
- Check if it's working: http://localhost:8080/api/v1/auth/status
- See all doctors: http://localhost:8080/api/doctors
- See all patients: http://localhost:8080/api/patients

---

## Database Stuff

### H2 Console (Web Database Interface)
1. Go to: http://localhost:8080/h2-console
2. Login with these details:
   - **JDBC URL**: `jdbc:h2:mem:hospitaldb`
   - **Username**: `sa`
   - **Password**: (just leave this blank)

Now you can see all the tables and run SQL queries!

---

## Project Structure

Here's how I organized everything:

```
backend/
├── src/main/java/com/hms/
│   ├── Application.java           # Main class that starts everything
│   ├── config/
│   │   ├── DataInitializer        # Creates sample data when app starts
│   │   └── SecurityConfig         # Basic security setup
│   ├── model/                     # All the data classes
│   │   ├── Doctor.java, Patient.java    # Database entities
│   │   ├── DoctorDTO.java, PatientDTO.java  # Data transfer objects
│   │   └── Bill.java, Appointment.java    # Other stuff
│   ├── controller/
│   │   ├── HospitalController     # One controller for all APIs
│   │   └── AuthController        # Login stuff
│   ├── service/
│   │   └── HospitalService       # All the business logic
│   ├── repository/                # Database access
│   │   ├── DoctorRepository
│   │   ├── PatientRepository
│   │   └── AppointmentRepository
│   └── security/                  # Security components
│       └── JwtAuthenticationFilter
```

---

## API Endpoints You Can Use

### Authentication Stuff
- `GET /api/v1/auth/status` - Check if server is running
- `POST /api/v1/auth/login` - Basic login (I kept it simple)

### Doctor Management
- `GET /api/doctors` - Get all doctors
- `GET /api/doctors/{id}` - Get specific doctor
- `POST /api/doctors` - Add new doctor
- `PUT /api/doctors/{id}` - Update doctor info
- `DELETE /api/doctors/{id}` - Remove doctor

### Patient Management  
- `GET /api/patients` - Get all patients
- `GET /api/patients/{id}` - Get specific patient
- `POST /api/patients` - Add new patient
- `PUT /api/patients/{id}` - Update patient info
- `DELETE /api/patients/{id}` - Remove patient

---

## Sample Data

When you start the app, it automatically creates some test data:
- 8 doctors with different specialties
- 10 patients with medical info  
- 8 appointments between doctors and patients
- 8 bills for the appointments

You can see all this in the H2 console!

---

## Good for College Demo

### What to Show Your Lecturer:
1. **Simple code structure** - Easy to explain how it works
2. **Database management** - Show the H2 console with data
3. **API testing** - Demonstrate adding/viewing doctors and patients
4. **Spring Boot features** - Auto-configuration, dependency injection

### Things to Mention:
- "Used Spring Boot to build this quickly"
- "Single controller makes it simple to understand"
- "H2 database means no setup needed"
- "DTOs separate API models from database models"
- "Sample data loads automatically for demo"

---

## If Something Goes Wrong

### Port 8080 Already Used?
```bash
# Find what's using port 8080
netstat -ano | findstr :8080

# Kill it (replace with actual PID number)
taskkill /PID <PID> /F
```

### Getting Compilation Errors?
```bash
# Clean and rebuild everything
.\mvnw.cmd clean compile
```

### App Won't Start?
- Check Java version: `java -version` (needs to be 17 or higher)
- Check Maven version: `mvn -version`
- Make sure you're in the backend folder

---

## Built By

**Hospital Management System - College Project**
*Simple Spring Boot project for learning REST APIs*
