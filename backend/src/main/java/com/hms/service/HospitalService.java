package com.hms.service;

import com.hms.model.Doctor;
import com.hms.model.Patient;
import com.hms.model.Appointment;
import com.hms.model.Bill;
import com.hms.model.DoctorDTO;
import com.hms.model.PatientDTO;
import com.hms.model.AppointmentDTO;
import com.hms.model.BillDTO;
import com.hms.repository.DoctorRepository;
import com.hms.repository.PatientRepository;
import com.hms.repository.AppointmentRepository;
import com.hms.repository.BillRepository;
import org.springframework.stereotype.Service;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.stream.Collectors;

// Main service for hospital operations
@Service
public class HospitalService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final BillRepository billRepository;

    public HospitalService(DoctorRepository doctorRepository, 
                          PatientRepository patientRepository,
                          AppointmentRepository appointmentRepository,
                          BillRepository billRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.billRepository = billRepository;
    }

    // Doctor operations
    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(this::convertToDoctorDTO)
                .collect(Collectors.toList());
    }

    public DoctorDTO getDoctorById(@NonNull Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        return convertToDoctorDTO(doctor);
    }

    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = convertToDoctor(doctorDTO);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return convertToDoctorDTO(savedDoctor);
    }

    public DoctorDTO updateDoctor(@NonNull Long id, DoctorDTO doctorDTO) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        
        existingDoctor.setName(doctorDTO.getName());
        existingDoctor.setSpecialty(doctorDTO.getSpecialty());
        existingDoctor.setLicenseNumber(doctorDTO.getLicenseNumber());
        existingDoctor.setYearsOfExperience(doctorDTO.getYearsOfExperience());
        
        Doctor updatedDoctor = doctorRepository.save(existingDoctor);
        return convertToDoctorDTO(updatedDoctor);
    }

    public void deleteDoctor(@NonNull Long id) {
        doctorRepository.deleteById(id);
    }

    // Patient operations
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::convertToPatientDTO)
                .collect(Collectors.toList());
    }

    public PatientDTO getPatientById(@NonNull Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return convertToPatientDTO(patient);
    }

    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient patient = convertToPatient(patientDTO);
        Patient savedPatient = patientRepository.save(patient);
        return convertToPatientDTO(savedPatient);
    }

    public PatientDTO updatePatient(@NonNull Long id, PatientDTO patientDTO) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        
        existingPatient.setName(patientDTO.getName());
        existingPatient.setEmail(patientDTO.getEmail());
        existingPatient.setPhone(patientDTO.getPhone());
        existingPatient.setDateOfBirth(patientDTO.getDateOfBirth());
        existingPatient.setMedicalHistory(patientDTO.getMedicalHistory());
        
        Patient updatedPatient = patientRepository.save(existingPatient);
        return convertToPatientDTO(updatedPatient);
    }

    public void deletePatient(@NonNull Long id) {
        patientRepository.deleteById(id);
    }

    // Appointment operations
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(this::convertToAppointmentDTO)
                .collect(Collectors.toList());
    }

    public AppointmentDTO getAppointmentById(@NonNull Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        return convertToAppointmentDTO(appointment);
    }

    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = convertToAppointment(appointmentDTO);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return convertToAppointmentDTO(savedAppointment);
    }

    public void deleteAppointment(@NonNull Long id) {
        appointmentRepository.deleteById(id);
    }

    // Billing operations
    public List<BillDTO> getAllBills() {
        return billRepository.findAll().stream()
                .map(this::convertToBillDTO)
                .collect(Collectors.toList());
    }

    public BillDTO getBillById(@NonNull Long id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));
        return convertToBillDTO(bill);
    }

    public BillDTO createBill(@NonNull BillDTO billDTO) {
        Bill bill = convertToBill(billDTO);
        Bill savedBill = billRepository.save(bill);
        return convertToBillDTO(savedBill);
    }

    public void deleteBill(@NonNull Long id) {
        billRepository.deleteById(id);
    }

    // Helper methods
    private DoctorDTO convertToDoctorDTO(@NonNull Doctor doctor) {
        DoctorDTO dto = new DoctorDTO();
        dto.setId(doctor.getId());
        dto.setName(doctor.getName());
        dto.setSpecialty(doctor.getSpecialty());
        dto.setLicenseNumber(doctor.getLicenseNumber());
        dto.setYearsOfExperience(doctor.getYearsOfExperience());
        return dto;
    }

    private Doctor convertToDoctor(DoctorDTO dto) {
        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setSpecialty(dto.getSpecialty());
        doctor.setLicenseNumber(dto.getLicenseNumber());
        doctor.setYearsOfExperience(dto.getYearsOfExperience());
        return doctor;
    }

    private PatientDTO convertToPatientDTO(@NonNull Patient patient) {
        PatientDTO dto = new PatientDTO();
        dto.setId(patient.getId());
        dto.setName(patient.getName());
        dto.setEmail(patient.getEmail());
        dto.setPhone(patient.getPhone());
        dto.setDateOfBirth(patient.getDateOfBirth());
        dto.setMedicalHistory(patient.getMedicalHistory());
        return dto;
    }

    private Patient convertToPatient(PatientDTO dto) {
        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setEmail(dto.getEmail());
        patient.setPhone(dto.getPhone());
        patient.setDateOfBirth(dto.getDateOfBirth());
        patient.setMedicalHistory(dto.getMedicalHistory());
        return patient;
    }

    private AppointmentDTO convertToAppointmentDTO(@NonNull Appointment appointment) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(appointment.getId());
        dto.setPatient(convertToPatientDTO(appointment.getPatient()));
        dto.setDoctor(convertToDoctorDTO(appointment.getDoctor()));
        dto.setAppointmentDateTime(appointment.getAppointmentDateTime());
        dto.setStatus(appointment.getStatus());
        dto.setReasonForVisit(appointment.getReasonForVisit());
        dto.setNotes(appointment.getNotes());
        return dto;
    }

    private Appointment convertToAppointment(@NonNull AppointmentDTO dto) {
        Appointment appointment = new Appointment();
        
        // Handle patient - if only ID is provided, fetch from repository
        if (dto.getPatient() != null) {
            if (dto.getPatient().getId() != null) {
                Patient patient = patientRepository.findById(dto.getPatient().getId())
                        .orElseThrow(() -> new RuntimeException("Patient not found"));
                appointment.setPatient(patient);
            } else {
                appointment.setPatient(convertToPatient(dto.getPatient()));
            }
        }
        
        // Handle doctor - if only ID is provided, fetch from repository
        if (dto.getDoctor() != null) {
            if (dto.getDoctor().getId() != null) {
                Doctor doctor = doctorRepository.findById(dto.getDoctor().getId())
                        .orElseThrow(() -> new RuntimeException("Doctor not found"));
                appointment.setDoctor(doctor);
            } else {
                appointment.setDoctor(convertToDoctor(dto.getDoctor()));
            }
        }
        
        appointment.setAppointmentDateTime(dto.getAppointmentDateTime());
        appointment.setStatus(dto.getStatus() != null ? dto.getStatus() : "SCHEDULED");
        appointment.setReasonForVisit(dto.getReasonForVisit());
        appointment.setNotes(dto.getNotes());
        return appointment;
    }

    private BillDTO convertToBillDTO(@NonNull Bill bill) {
        BillDTO dto = new BillDTO();
        dto.setId(bill.getId());
        if (bill.getPatient() != null) {
            dto.setPatient(convertToPatientDTO(bill.getPatient()));
        }
        dto.setAmount(bill.getAmount());
        dto.setStatus(bill.getStatus());
        dto.setBillDate(bill.getBillDate());
        dto.setDueDate(bill.getDueDate());
        dto.setDescription(bill.getDescription());
        dto.setPaymentDate(bill.getPaymentDate());
        return dto;
    }

    private Bill convertToBill(@NonNull BillDTO dto) {
        Bill bill = new Bill();
        if (dto.getPatient() != null) {
            bill.setPatient(convertToPatient(dto.getPatient()));
        }
        bill.setAmount(dto.getAmount());
        bill.setStatus(dto.getStatus() != null ? dto.getStatus() : "PENDING");
        bill.setBillDate(dto.getBillDate() != null ? dto.getBillDate() : java.time.LocalDateTime.now());
        bill.setDueDate(dto.getDueDate());
        bill.setDescription(dto.getDescription());
        bill.setPaymentDate(dto.getPaymentDate());
        return bill;
    }
}
