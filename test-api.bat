@echo off
echo Testing Hospital Management System API
echo =====================================
echo.

echo Make sure the application is running on http://localhost:8080
echo.

echo 1. Testing login to get JWT token...
curl -X POST http://localhost:8080/api/v1/auth/login ^
  -H "Content-Type: application/json" ^
  -d "{\"username\":\"admin\",\"password\":\"admin123\"}"
echo.

echo.
echo 2. Testing get all patients (will fail without token)...
curl -X GET http://localhost:8080/api/v1/patients
echo.

echo.
echo 3. Testing health check...
curl -X GET http://localhost:8080/actuator/health
echo.

echo.
echo 4. Testing Swagger UI availability...
curl -X GET http://localhost:8080/v3/api-docs
echo.

echo.
echo API testing completed!
echo.
echo To test protected endpoints:
echo 1. Copy the JWT token from the login response
echo 2. Use it in Authorization header: "Authorization: Bearer YOUR_TOKEN"
echo.
echo Example:
echo curl -X GET http://localhost:8080/api/v1/patients -H "Authorization: Bearer YOUR_TOKEN"
echo.

pause
