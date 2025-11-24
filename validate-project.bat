@echo off
echo Validating Hospital Management System Project Structure
echo ========================================================
echo.

echo Checking required files...
echo.

set "missing_files="

if not exist "backend\pom.xml" (
    echo ❌ Missing: backend\pom.xml
    set "missing_files=1"
) else (
    echo ✅ Found: backend\pom.xml
)

if not exist "backend\src\main\java\com\hms\Application.java" (
    echo ❌ Missing: backend\src\main\java\com\hms\Application.java
    set "missing_files=1"
) else (
    echo ✅ Found: backend\src\main\java\com\hms\Application.java
)

if not exist "backend\src\main\resources\application.yml" (
    echo ❌ Missing: backend\src\main\resources\application.yml
    set "missing_files=1"
) else (
    echo ✅ Found: backend\src\main\resources\application.yml
)

echo.
echo Checking H2 database configuration...
if not exist "backend\src\main\java\com\hms\config\DataInitializer.java" (
    echo ❌ Missing: DataInitializer.java
    set "missing_files=1"
) else (
    echo ✅ Found: DataInitializer.java (auto-populates H2 database)
)

echo.
echo Checking security components...
if not exist "backend\src\main\java\com\hms\config\SecurityConfig.java" (
    echo ❌ Missing: SecurityConfig.java
    set "missing_files=1"
) else (
    echo ✅ Found: SecurityConfig.java
)

if not exist "backend\src\main\java\com\hms\security\JwtTokenProvider.java" (
    echo ❌ Missing: JwtTokenProvider.java
    set "missing_files=1"
) else (
    echo ✅ Found: JwtTokenProvider.java
)

echo.
echo Checking controllers...
if not exist "backend\src\main\java\com\hms\controller\AuthController.java" (
    echo ❌ Missing: AuthController.java
    set "missing_files=1"
) else (
    echo ✅ Found: AuthController.java
)

if not exist "backend\src\main\java\com\hms\controller\PatientController.java" (
    echo ❌ Missing: PatientController.java
    set "missing_files=1"
) else (
    echo ✅ Found: PatientController.java
)

echo.
echo Checking service layer...
if not exist "backend\src\main\java\com\hms\service\PatientService.java" (
    echo ❌ Missing: PatientService.java
    set "missing_files=1"
) else (
    echo ✅ Found: PatientService.java
)

echo.
echo Checking model layer...
if not exist "backend\src\main\java\com\hms\model\User.java" (
    echo ❌ Missing: User.java
    set "missing_files=1"
) else (
    echo ✅ Found: User.java
)

if not exist "backend\src\main\java\com\hms\model\Patient.java" (
    echo ❌ Missing: Patient.java
    set "missing_files=1"
) else (
    echo ✅ Found: Patient.java
)

echo.
echo Checking repository layer...
if not exist "backend\src\main\java\com\hms\repository\UserRepository.java" (
    echo ❌ Missing: UserRepository.java
    set "missing_files=1"
) else (
    echo ✅ Found: UserRepository.java
)

echo.
if defined missing_files (
    echo ❌ Project validation FAILED - Some files are missing
) else (
    echo ✅ Project validation PASSED - All required files are present
    echo.
    echo 🚀 Project is ready to run!
    echo ✨ NO DATABASE SETUP REQUIRED - Uses embedded H2 database
    echo.
    echo Next steps:
    echo 1. Run 'backend\start.bat' to start the application
    echo 2. Access http://localhost:8080/swagger-ui.html for API documentation
    echo 3. Access http://localhost:8080/h2-console to view database
)

echo.
pause
