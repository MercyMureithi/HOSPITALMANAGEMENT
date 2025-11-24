@echo off
echo Starting Hospital Management System Backend...
echo.

echo Checking Java installation...
java -version
if %ERRORLEVEL% neq 0 (
    echo Error: Java is not installed or not in PATH
    echo Please install Java 17+ and add it to PATH
    pause
    exit /b 1
)

echo.
echo Checking Maven installation...
mvn -version
if %ERRORLEVEL% neq 0 (
    echo Error: Maven is not installed or not in PATH
    echo Please install Maven 3.6+ and add it to PATH
    pause
    exit /b 1
)

echo.
echo ✨ NO DATABASE SETUP REQUIRED - Using embedded H2 database
echo.

echo Compiling the project...
mvn clean compile
if %ERRORLEVEL% neq 0 (
    echo Error: Compilation failed
    pause
    exit /b 1
)

echo.
echo 🚀 Starting the application...
echo.
echo 📍 Application will be available at: http://localhost:8080
echo 📚 Swagger UI: http://localhost:8080/swagger-ui.html
echo 🗄️  H2 Console: http://localhost:8080/h2-console
echo.
echo 🔑 Default Login Credentials:
echo    Username: admin    Password: admin123
echo    Username: doctor1  Password: doctor123
echo    Username: clerk1   Password: clerk123
echo.
echo Press Ctrl+C to stop the application
echo.

mvn spring-boot:run
