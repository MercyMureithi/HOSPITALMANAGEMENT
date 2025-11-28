package com.hms.config;

import com.hms.model.*;
import com.hms.repository.DoctorRepository;
import com.hms.repository.PatientRepository;
import com.hms.repository.AppointmentRepository;
import com.hms.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    // These repositories let me save data to the H2 database
    // Spring injects them automatically - pretty cool!
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final BillRepository billRepository;

    @Override
    public void run(String... args) throws Exception {
        // Only load data if the database is empty - don't want duplicates!
        if (doctorRepository.count() == 0) {
            log.info("Initializing database with sample data...");
            initializeDoctors();
            initializePatients();
            initializeAppointments();
            initializeBills();
            log.info("Database initialization completed!");
        } else {
            log.info("Database already contains data. Skipping initialization.");
        }
    }

    // Create some realistic doctors for the demo
    // I tried to make the data look like a real hospital!

    private void initializeDoctors() {
        List<Doctor> doctors = Arrays.asList(
            createDoctor("Dr. Sarah Johnson", "Cardiology", "MD123456", 12),
            createDoctor("Dr. Michael Chen", "Neurology", "MD789012", 8),
            createDoctor("Dr. Emily Rodriguez", "Pediatrics", "MD345678", 15),
            createDoctor("Dr. James Wilson", "Orthopedics", "MD901234", 10),
            createDoctor("Dr. Lisa Thompson", "Dermatology", "MD567890", 7),
            createDoctor("Dr. Robert Martinez", "General Surgery", "MD234567", 20),
            createDoctor("Dr. Amanda Foster", "Psychiatry", "MD890123", 9),
            createDoctor("Dr. David Kim", "Oncology", "MD456789", 11)
        );

        doctorRepository.saveAll(doctors);
        log.info("Created {} doctors", doctors.size());
    }

    private Doctor createDoctor(String name, String specialty, String licenseNumber, Integer yearsOfExperience) {
        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setSpecialty(specialty);
        doctor.setLicenseNumber(licenseNumber);
        doctor.setYearsOfExperience(yearsOfExperience);
        return doctor;
    }

    private void initializePatients() {
        List<Patient> patients = Arrays.asList(
            createPatient("John Smith", "john.smith@email.com", "555-0101", LocalDate.of(1985, 5, 15), "Hypertension, Type 2 Diabetes"),
            createPatient("Mary Johnson", "mary.johnson@email.com", "555-0102", LocalDate.of(1990, 8, 22), "Asthma, Allergies"),
            createPatient("Robert Williams", "robert.williams@email.com", "555-0103", LocalDate.of(1978, 3, 10), "Heart Disease, High Cholesterol"),
            createPatient("Patricia Brown", "patricia.brown@email.com", "555-0104", LocalDate.of(1995, 12, 3), "No significant medical history"),
            createPatient("James Davis", "james.davis@email.com", "555-0105", LocalDate.of(1982, 7, 18), "Migraines, Knee Injury"),
            createPatient("Jennifer Miller", "jennifer.miller@email.com", "555-0106", LocalDate.of(1988, 11, 25), "Anxiety, Vitamin D deficiency"),
            createPatient("William Garcia", "william.garcia@email.com", "555-0107", LocalDate.of(1975, 2, 14), "Arthritis, Previous Surgery"),
            createPatient("Linda Martinez", "linda.martinez@email.com", "555-0108", LocalDate.of(1992, 9, 8), "Eczema, Seasonal Allergies"),
            createPatient("Richard Anderson", "richard.anderson@email.com", "555-0109", LocalDate.of(1980, 4, 30), "Back Pain, Stress"),
            createPatient("Barbara Taylor", "barbara.taylor@email.com", "555-0110", LocalDate.of(1973, 6, 12), "Diabetes, Hypertension")
        );

        patientRepository.saveAll(patients);
        log.info("Created {} patients", patients.size());
    }

    private Patient createPatient(String name, String email, String phone, LocalDate dateOfBirth, String medicalHistory) {
        Patient patient = new Patient();
        patient.setName(name);
        patient.setEmail(email);
        patient.setPhone(phone);
        patient.setDateOfBirth(dateOfBirth);
        patient.setMedicalHistory(medicalHistory);
        return patient;
    }

    private void initializeAppointments() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<Patient> patients = patientRepository.findAll();

        List<Appointment> appointments = Arrays.asList(
            createAppointment(patients.get(0), doctors.get(0), 
                LocalDateTime.now().plusDays(1).withHour(10).withMinute(0), 
                "SCHEDULED", "Regular checkup for heart condition", "Patient needs ECG and blood pressure monitoring"),
            
            createAppointment(patients.get(1), doctors.get(2), 
                LocalDateTime.now().plusDays(2).withHour(14).withMinute(30), 
                "SCHEDULED", "Child's annual wellness visit", "Vaccination update needed"),
            
            createAppointment(patients.get(2), doctors.get(3), 
                LocalDateTime.now().minusDays(1).withHour(11).withMinute(0), 
                "COMPLETED", "Follow-up on knee surgery", "Patient recovering well, physical therapy recommended"),
            
            createAppointment(patients.get(3), doctors.get(4), 
                LocalDateTime.now().plusDays(3).withHour(9).withMinute(15), 
                "SCHEDULED", "Skin examination", "Routine skin cancer screening"),
            
            createAppointment(patients.get(4), doctors.get(1), 
                LocalDateTime.now().minusDays(2).withHour(15).withMinute(45), 
                "COMPLETED", "Migraine consultation", "New medication prescribed, follow up in 2 weeks"),
            
            createAppointment(patients.get(5), doctors.get(6), 
                LocalDateTime.now().plusDays(4).withHour(13).withMinute(0), 
                "SCHEDULED", "Therapy session", "Discuss anxiety management techniques"),
            
            createAppointment(patients.get(6), doctors.get(5), 
                LocalDateTime.now().minusDays(3).withHour(8).withMinute(30), 
                "COMPLETED", "Pre-operative consultation", "Surgery scheduled for next month"),
            
            createAppointment(patients.get(7), doctors.get(7), 
                LocalDateTime.now().plusDays(5).withHour(16).withMinute(0), 
                "SCHEDULED", "Cancer treatment follow-up", "Review chemotherapy progress")
        );

        appointmentRepository.saveAll(appointments);
        log.info("Created {} appointments", appointments.size());
    }

    private Appointment createAppointment(Patient patient, Doctor doctor, LocalDateTime dateTime, 
                                         String status, String reason, String notes) {
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDateTime(dateTime);
        appointment.setStatus(status);
        appointment.setReasonForVisit(reason);
        appointment.setNotes(notes);
        return appointment;
    }

    private void initializeBills() {
        List<Patient> patients = patientRepository.findAll();

        List<Bill> bills = Arrays.asList(
            createBill(patients.get(0), new BigDecimal("250.00"), "PAID", 
                LocalDateTime.now().minusDays(30), LocalDateTime.now().minusDays(15), 
                "Cardiology consultation fee", LocalDateTime.now().minusDays(10)),
            
            createBill(patients.get(1), new BigDecimal("150.00"), "PENDING", 
                LocalDateTime.now().minusDays(7), LocalDateTime.now().plusDays(8), 
                "Pediatric visit and vaccination", null),
            
            createBill(patients.get(2), new BigDecimal("500.00"), "PAID", 
                LocalDateTime.now().minusDays(45), LocalDateTime.now().minusDays(30), 
                "Orthopedic surgery follow-up", LocalDateTime.now().minusDays(25)),
            
            createBill(patients.get(3), new BigDecimal("200.00"), "OVERDUE", 
                LocalDateTime.now().minusDays(60), LocalDateTime.now().minusDays(15), 
                "Dermatology examination", null),
            
            createBill(patients.get(4), new BigDecimal("300.00"), "PAID", 
                LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(5), 
                "Neurology consultation and tests", LocalDateTime.now().minusDays(3)),
            
            createBill(patients.get(5), new BigDecimal("180.00"), "PENDING", 
                LocalDateTime.now().minusDays(3), LocalDateTime.now().plusDays(12), 
                "Psychiatry session", null),
            
            createBill(patients.get(6), new BigDecimal("750.00"), "PENDING", 
                LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(14), 
                "Surgical consultation fee", null),
            
            createBill(patients.get(7), new BigDecimal("1200.00"), "PAID", 
                LocalDateTime.now().minusDays(90), LocalDateTime.now().minusDays(75), 
                "Oncology treatment session", LocalDateTime.now().minusDays(70))
        );

        billRepository.saveAll(bills);
        log.info("Created {} bills", bills.size());
    }

    private Bill createBill(Patient patient, BigDecimal amount, String status, 
                          LocalDateTime billDate, LocalDateTime dueDate, 
                          String description, LocalDateTime paymentDate) {
        Bill bill = new Bill();
        bill.setPatient(patient);
        bill.setAmount(amount);
        bill.setStatus(status);
        bill.setBillDate(billDate);
        bill.setDueDate(dueDate);
        bill.setDescription(description);
        bill.setPaymentDate(paymentDate);
        return bill;
    }
}
