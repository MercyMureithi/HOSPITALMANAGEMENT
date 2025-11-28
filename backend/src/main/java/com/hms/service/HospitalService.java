package com.hms.service;

import com.hms.model.Doctor;
import com.hms.model.Patient;
import com.hms.model.DoctorDTO;
import com.hms.model.PatientDTO;
import com.hms.repository.DoctorRepository;
import com.hms.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// Main service for hospital operations
@Service
public class HospitalService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public HospitalService(DoctorRepository doctorRepository, 
                          PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    // Doctor operations
    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(this::convertToDoctorDTO)
                .collect(Collectors.toList());
    }

    public DoctorDTO getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        return convertToDoctorDTO(doctor);
    }

    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = convertToDoctor(doctorDTO);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return convertToDoctorDTO(savedDoctor);
    }

    public DoctorDTO updateDoctor(Long id, DoctorDTO doctorDTO) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        
        existingDoctor.setName(doctorDTO.getName());
        existingDoctor.setSpecialty(doctorDTO.getSpecialty());
        existingDoctor.setLicenseNumber(doctorDTO.getLicenseNumber());
        existingDoctor.setYearsOfExperience(doctorDTO.getYearsOfExperience());
        
        Doctor updatedDoctor = doctorRepository.save(existingDoctor);
        return convertToDoctorDTO(updatedDoctor);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    // Patient operations
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::convertToPatientDTO)
                .collect(Collectors.toList());
    }

    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return convertToPatientDTO(patient);
    }

    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient patient = convertToPatient(patientDTO);
        Patient savedPatient = patientRepository.save(patient);
        return convertToPatientDTO(savedPatient);
    }

    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
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

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    // Helper methods
    private DoctorDTO convertToDoctorDTO(Doctor doctor) {
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

    private PatientDTO convertToPatientDTO(Patient patient) {
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
}
