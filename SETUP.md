# 🚀 Quick Setup Guide for Hospital Management System

## 📋 Prerequisites

### Required Software
1. **Java 17+** - Download from [Oracle Java](https://www.oracle.com/java/technologies/downloads/) or use [OpenJDK](https://openjdk.org/)
2. **Maven 3.6+** - Download from [Apache Maven](https://maven.apache.org/download.cgi)

### Installation Check
```bash
java -version
mvn -version
```

## ⚡ Quick Start (2 Steps - NO DATABASE!)

### Step 1: Navigate to Project
```bash
cd HOSPITALMANAGEMENT
```

### Step 2: Run the Application
```bash
# Run the startup script
backend\start.bat
```

**That's it!** The application uses an embedded H2 database that starts automatically with sample data.

### Step 3: Test the API (Optional)
```bash
# Test API endpoints
test-api.bat
```

## 🌐 Access Points

Once running, access the application at:

- **Main Application**: http://localhost:8080
- **Swagger Documentation**: http://localhost:8080/swagger-ui.html
- **H2 Database Console**: http://localhost:8080/h2-console
- **Health Check**: http://localhost:8080/actuator/health

## 🔑 Default Login Credentials

| Role    | Username | Password |
|---------|----------|----------|
| Admin   | admin    | admin123 |
| Doctor  | doctor1  | doctor123 |
| Clerk   | clerk1   | clerk123 |

## 📱 Testing with Postman/curl

### 1. Get JWT Token
```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'
```

### 2. Use Token for API Calls
```bash
# Replace YOUR_JWT_TOKEN with the actual token from step 1
curl -X GET http://localhost:8080/api/v1/patients \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## 🐳 Docker Setup (Alternative)

If you prefer Docker, use the included docker-compose:

```bash
# Build and start all services
docker-compose up --build

# Access the application
http://localhost:8080
```

## 🔧 Configuration

### Database Settings
Edit `backend/src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/hospital_db
    username: your_username
    password: your_password
```

### JWT Settings
```yaml
jwt:
  secret: your-secret-key-here
  expirationMs: 86400000  # 24 hours
```

## 📊 Available Features

### ✅ Authentication & Security
- JWT-based authentication
- Role-based access control (ADMIN, DOCTOR, CLERK)
- Secure password encryption

### ✅ Patient Management
- Create, read, update, delete patients
- Search patients by email
- Input validation

### ✅ Doctor Management
- Manage doctor information
- Search by specialty
- Role-based access

### ✅ Appointment Management
- Schedule appointments
- View appointments by patient/doctor
- Status tracking

### ✅ Billing Management
- Create and manage bills
- Track payment status
- Patient billing history

## 🛠️ Project Structure

```
HOSPITALMANAGEMENT/
├── backend/                    # Spring Boot application
│   ├── src/main/java/com/hms/
│   │   ├── controller/         # REST API endpoints
│   │   ├── service/           # Business logic
│   │   ├── repository/        # Database access
│   │   ├── model/             # JPA entities
│   │   ├── dto/               # Data transfer objects
│   │   ├── security/          # JWT security
│   │   ├── config/            # Configuration
│   │   └── exception/         # Custom exceptions
│   ├── src/main/resources/
│   │   ├── application.yml    # Configuration
│   │   └── db/seed.sql       # Initial data
│   ├── pom.xml               # Maven dependencies
│   ├── start.bat             # Startup script
│   └── Dockerfile            # Docker configuration
├── docker-compose.yml        # Complete setup with MySQL
├── test-api.bat             # API testing script
├── validate-project.bat     # Project validation
└── SETUP.md                 # This guide
```

## 🚨 Troubleshooting

### Common Issues

1. **Port 8080 already in use**
   - Change port in `application.yml`:
   ```yaml
   server:
     port: 8081
   ```

2. **Database connection failed**
   - Check MySQL is running
   - Verify database name and credentials
   - Check firewall settings

3. **Maven not found**
   - Install Maven and add to PATH
   - Or use Maven Wrapper: `mvnw.cmd spring-boot:run`

4. **Java not found**
   - Install Java 17+
   - Set JAVA_HOME environment variable
   - Add Java to PATH

### Validation Commands
```bash
# Check project structure
.\validate-project.bat

# Test API endpoints
.\test-api.bat

# Start application
.\backend\start.bat
```

## 📞 Support

If you encounter issues:

1. Check the validation script output
2. Review application logs for errors
3. Ensure all prerequisites are installed
4. Verify database connection and credentials

## 🎯 Next Steps

Once the application is running:

1. **Explore the API**: Use Swagger UI at http://localhost:8080/swagger-ui.html
2. **Test Different Roles**: Login with different user credentials
3. **Review the Code**: Examine the clean architecture and security implementation
4. **Customize**: Modify the configuration and add your own features

The project is now ready for development and deployment! 🎉
