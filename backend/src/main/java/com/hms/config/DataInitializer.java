package com.hms.config;

import com.hms.model.Role;
import com.hms.model.User;
import com.hms.model.Patient;
import com.hms.model.Doctor;
import com.hms.model.Appointment;
import com.hms.model.Bill;
import com.hms.repository.RoleRepository;
import com.hms.repository.UserRepository;
import com.hms.repository.PatientRepository;
import com.hms.repository.DoctorRepository;
import com.hms.repository.AppointmentRepository;
import com.hms.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final BillRepository billRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        initializeRoles();
        initializeUsers();
        initializeDoctors();
        initializePatients();
        initializeAppointments();
        initializeBills();
    }

    private void initializeRoles() {
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role(null, "ROLE_ADMIN"));
            roleRepository.save(new Role(null, "ROLE_DOCTOR"));
            roleRepository.save(new Role(null, "ROLE_CLERK"));
            System.out.println("✅ Roles initialized");
        }
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {
            Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElseThrow();
            Role doctorRole = roleRepository.findByName("ROLE_DOCTOR").orElseThrow();
            Role clerkRole = roleRepository.findByName("ROLE_CLERK").orElseThrow();

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@hospital.com");
            admin.setEnabled(true);
            admin.setRoles(java.util.List.of(adminRole));
            userRepository.save(admin);

            User doctor = new User();
            doctor.setUsername("doctor1");
            doctor.setPassword(passwordEncoder.encode("doctor123"));
            doctor.setEmail("doctor1@hospital.com");
            doctor.setEnabled(true);
            doctor.setRoles(java.util.List.of(doctorRole));
            userRepository.save(doctor);

            User clerk = new User();
            clerk.setUsername("clerk1");
            clerk.setPassword(passwordEncoder.encode("clerk123"));
            clerk.setEmail("clerk1@hospital.com");
            clerk.setEnabled(true);
            clerk.setRoles(java.util.List.of(clerkRole));
            userRepository.save(clerk);

            System.out.println("✅ Users initialized");
        }
    }

    private void initializeDoctors() {
        if (doctorRepository.count() == 0) {
            doctorRepository.save(new Doctor(null, "Dr. John Smith", "Cardiology"));
            doctorRepository.save(new Doctor(null, "Dr. Sarah Johnson", "Neurology"));
            System.out.println("✅ Doctors initialized");
        }
    }

    private void initializePatients() {
        if (patientRepository.count() == 0) {
            patientRepository.save(new Patient(null, "Alice Brown", "+1234567890", "alice.brown@email.com"));
            patientRepository.save(new Patient(null, "Bob Wilson", "+0987654321", "bob.wilson@email.com"));
            System.out.println("✅ Patients initialized");
        }
    }

    private void initializeAppointments() {
        if (appointmentRepository.count() == 0) {
            Patient patient1 = patientRepository.findAll().get(0);
            Patient patient2 = patientRepository.findAll().get(1);
            Doctor doctor1 = doctorRepository.findAll().get(0);
            Doctor doctor2 = doctorRepository.findAll().get(1);

            Appointment appointment1 = new Appointment();
            appointment1.setPatient(patient1);
            appointment1.setDoctor(doctor1);
            appointment1.setDate(LocalDateTime.of(2024, 1, 15, 10, 0));
            appointment1.setStatus("SCHEDULED");
            appointmentRepository.save(appointment1);

            Appointment appointment2 = new Appointment();
            appointment2.setPatient(patient2);
            appointment2.setDoctor(doctor2);
            appointment2.setDate(LocalDateTime.of(2024, 1, 16, 14, 30));
            appointment2.setStatus("SCHEDULED");
            appointmentRepository.save(appointment2);

            System.out.println("✅ Appointments initialized");
        }
    }

    private void initializeBills() {
        if (billRepository.count() == 0) {
            Patient patient1 = patientRepository.findAll().get(0);
            Patient patient2 = patientRepository.findAll().get(1);

            Bill bill1 = new Bill();
            bill1.setPatient(patient1);
            bill1.setAmount(new BigDecimal("500.00"));
            bill1.setStatus("PENDING");
            billRepository.save(bill1);

            Bill bill2 = new Bill();
            bill2.setPatient(patient2);
            bill2.setAmount(new BigDecimal("750.50"));
            bill2.setStatus("PAID");
            billRepository.save(bill2);

            System.out.println("✅ Bills initialized");
        }
    }
}
