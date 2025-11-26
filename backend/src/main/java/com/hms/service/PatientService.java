package com.hms.service;

import com.hms.model.PatientDTO;
import com.hms.model.Patient;
import com.hms.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class PatientService {
    
    private final PatientRepository patientRepository;
    
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    
    public List<PatientDTO> getAllPatients() {
        log.info("Fetching all patients");
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public PatientDTO getPatientById(Long id) {
        log.info("Fetching patient with id: {}", id);
        if (id == null) {
            throw new IllegalArgumentException("Patient ID cannot be null");
        }
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
        return convertToDTO(patient);
    }
    
    public PatientDTO createPatient(PatientDTO patientDTO) {
        if (patientDTO == null) {
            throw new IllegalArgumentException("PatientDTO cannot be null");
        }
        
        log.info("Creating new patient: {}", patientDTO.getName());
        
        Patient patient = convertToEntity(patientDTO);
        Patient savedPatient = patientRepository.save(patient);
        
        log.info("Successfully created patient with id: {}", savedPatient.getId());
        return convertToDTO(savedPatient);
    }
    
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        log.info("Updating patient with id: {}", id);
        
        if (id == null) {
            throw new IllegalArgumentException("Patient ID cannot be null");
        }
        if (patientDTO == null) {
            throw new IllegalArgumentException("PatientDTO cannot be null");
        }
        
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
        
        existingPatient.setName(patientDTO.getName());
        existingPatient.setEmail(patientDTO.getEmail());
        existingPatient.setPhone(patientDTO.getPhone());
        existingPatient.setDateOfBirth(patientDTO.getDateOfBirth());
        existingPatient.setMedicalHistory(patientDTO.getMedicalHistory());
        
        Patient updatedPatient = patientRepository.save(existingPatient);
        
        log.info("Successfully updated patient with id: {}", updatedPatient.getId());
        return convertToDTO(updatedPatient);
    }
    
    public void deletePatient(Long id) {
        log.info("Deleting patient with id: {}", id);
        
        if (id == null) {
            throw new IllegalArgumentException("Patient ID cannot be null");
        }
        
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
        
        // Explicit null check to satisfy type safety
        if (patient != null) {
            patientRepository.delete(patient);
            log.info("Successfully deleted patient with id: {}", id);
        }
    }
    
    private @NonNull PatientDTO convertToDTO(@NonNull Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null");
        }
        
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(patient.getId());
        patientDTO.setName(patient.getName());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setPhone(patient.getPhone());
        patientDTO.setDateOfBirth(patient.getDateOfBirth());
        patientDTO.setMedicalHistory(patient.getMedicalHistory());
        return patientDTO;
    }
    
    private @NonNull Patient convertToEntity(@NonNull PatientDTO patientDTO) {
        if (patientDTO == null) {
            throw new IllegalArgumentException("PatientDTO cannot be null");
        }
        
        Patient patient = new Patient();
        patient.setName(patientDTO.getName());
        patient.setEmail(patientDTO.getEmail());
        patient.setPhone(patientDTO.getPhone());
        patient.setDateOfBirth(patientDTO.getDateOfBirth());
        patient.setMedicalHistory(patientDTO.getMedicalHistory());
        return patient;
    }
}
