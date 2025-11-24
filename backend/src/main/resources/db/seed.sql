-- Insert roles
INSERT INTO roles (name) VALUES ('ROLE_ADMIN'), ('ROLE_DOCTOR'), ('ROLE_CLERK');

-- Insert users with encoded passwords (password: admin123, doctor123, clerk123)
INSERT INTO users (username, password, email, enabled) VALUES 
('admin', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'admin@hospital.com', true),
('doctor1', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'doctor1@hospital.com', true),
('clerk1', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'clerk1@hospital.com', true);

-- Assign roles to users
INSERT INTO user_roles (user_id, role_id) VALUES 
(1, 1), -- admin -> ROLE_ADMIN
(2, 2), -- doctor1 -> ROLE_DOCTOR
(3, 3); -- clerk1 -> ROLE_CLERK

-- Insert doctors
INSERT INTO doctors (name, specialty) VALUES 
('Dr. John Smith', 'Cardiology'),
('Dr. Sarah Johnson', 'Neurology');

-- Insert patients
INSERT INTO patients (name, phone, email) VALUES 
('Alice Brown', '+1234567890', 'alice.brown@email.com'),
('Bob Wilson', '+0987654321', 'bob.wilson@email.com');

-- Insert sample appointments
INSERT INTO appointments (patient_id, doctor_id, date, status) VALUES 
(1, 1, '2024-01-15 10:00:00', 'SCHEDULED'),
(2, 2, '2024-01-16 14:30:00', 'SCHEDULED');

-- Insert sample bills
INSERT INTO bills (patient_id, amount, status) VALUES 
(1, 500.00, 'PENDING'),
(2, 750.50, 'PAID');
