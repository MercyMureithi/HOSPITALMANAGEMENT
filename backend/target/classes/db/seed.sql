-- Hospital Management System Database Seed Script
-- This script initializes the database with sample data

-- Insert Roles
INSERT INTO roles (name) VALUES ('ADMIN'), ('DOCTOR'), ('CLERK'), ('PATIENT');

-- Insert Users with encrypted passwords (password: admin123, doctor123, clerk123, patient123)
INSERT INTO users (username, email, password, enabled) VALUES 
('admin', 'admin@hms.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', true),
('doctor1', 'doctor1@hms.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', true),
('clerk1', 'clerk1@hms.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', true),
('patient1', 'patient1@hms.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', true);

-- Assign roles to users
INSERT INTO user_roles (user_id, role_id) VALUES 
(1, 1), -- admin -> ADMIN
(2, 2), -- doctor1 -> DOCTOR
(3, 3), -- clerk1 -> CLERK
(4, 4); -- patient1 -> PATIENT

-- Insert Doctors
INSERT INTO doctors (name, specialty, license_number, years_of_experience) VALUES 
('Dr. John Smith', 'Cardiology', 'LIC123456', 15),
('Dr. Sarah Johnson', 'Neurology', 'LIC789012', 12),
('Dr. Emily Chen', 'Pediatrics', 'LIC345678', 8);

-- Insert Patients
INSERT INTO patients (name, email, phone, date_of_birth, medical_history) VALUES 
('Alice Brown', 'alice.brown@email.com', '+1234567890', '1985-05-15', 'Hypertension, Allergies'),
('Bob Wilson', 'bob.wilson@email.com', '+0987654321', '1990-08-22', 'Diabetes Type 2'),
('Carol Davis', 'carol.davis@email.com', '+1122334455', '1978-03-10', 'Asthma');

-- Insert Sample Appointments
INSERT INTO appointments (patient_id, doctor_id, appointment_date_time, status, reason_for_visit, notes) VALUES 
(1, 1, '2024-01-15 10:00:00', 'SCHEDULED', 'Routine cardiac checkup', 'Patient reports occasional chest pain'),
(2, 2, '2024-01-16 14:30:00', 'SCHEDULED', 'Neurological consultation', 'Follow-up for migraine treatment'),
(3, 3, '2024-01-17 09:00:00', 'COMPLETED', 'Pediatric examination', 'Child''s annual checkup');

-- Insert Sample Bills
INSERT INTO bills (patient_id, amount, status, bill_date, due_date, description, payment_date) VALUES 
(1, 250.00, 'PAID', '2024-01-15 10:30:00', '2024-02-15 10:30:00', 'Cardiac consultation and ECG', '2024-01-20 14:00:00'),
(2, 300.00, 'PENDING', '2024-01-16 15:00:00', '2024-02-16 15:00:00', 'Neurological consultation and tests', NULL),
(3, 150.00, 'PAID', '2024-01-17 09:30:00', '2024-02-17 09:30:00', 'Pediatric examination', '2024-01-17 16:00:00');
