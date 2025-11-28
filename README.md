# 🏥 Hospital Management System

My final year college project for managing hospital data. I built this using Spring Boot to learn about REST APIs and databases. This was actually pretty challenging but I learned a lot!

## What I Put In This Project

- **Spring Boot 3.2** with Java 17 (took me forever to set this up properly)
- **Simple setup** - I tried to keep it in one controller to make it easier to follow
- **Single service class** - Put all the main logic together instead of spreading it everywhere  
- **H2 Database** - Used this because MySQL was giving me connection issues
- **Sample data** - Added some fake doctors and patients so you can see it working immediately
- **Basic CRUD operations** - You can add, view, update, delete doctors and patients

---

## How to Run This Thing

### What You Need First
- **Java 17** or newer (make sure you don't have Java 8 installed - that caused me so many problems)
- **Maven** (or just use the wrapper I included - much easier)

### Get Started
1. Open command prompt/terminal (I used PowerShell but regular cmd works too)
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
- **Frontend Dashboard**: http://localhost:8080/ (Interactive web interface!)
- **Swagger API Docs**: http://localhost:8080/swagger-ui.html (Professional API documentation)
- Check if it's working: http://localhost:8080/api/v1/auth/status
- See all doctors: http://localhost:8080/api/doctors
- See all patients: http://localhost:8080/api/patients

If you get any errors, try restarting your terminal - that fixed most of my issues!

---

## Database Stuff

### H2 Console (Web Database Interface)
1. Go to: http://localhost:8080/h2-console
2. Login with these details:
   - **JDBC URL**: `jdbc:h2:mem:hospitaldb`
   - **Username**: `sa`
   - **Password**: (just leave this blank)

Now you can see all the tables and run SQL queries! I spent hours figuring out the connection string - hopefully this saves you some trouble.

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

## Challenges I Faced (Maybe This Helps You!)

- **Java Version Issues**: Spent 3 hours trying to run with Java 8 before realizing Spring Boot 3.2 needs Java 17+
- **Database Connection**: H2 console kept giving me "connection refused" - turned out I had the wrong JDBC URL
- **CORS Problems**: Frontend couldn't connect to backend - had to disable security for the demo
- **Maven Dependencies**: Some dependencies were conflicting, had to clean and rebuild multiple times
- **DTO vs Entity**: Got confused about when to use which - ended up using DTOs for APIs and Entities for database

---

## Good for College Demo

### What to Show Your Lecturer:
1. **Interactive Frontend** - Beautiful dashboard at http://localhost:8080/ 
2. **Swagger API Documentation** - Professional API docs at http://localhost:8080/swagger-ui.html
3. **Database management** - Show the H2 console with real data
4. **API testing** - Use Swagger to test adding/viewing doctors and patients live
5. **Spring Boot features** - Auto-configuration, dependency injection, clean architecture

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
