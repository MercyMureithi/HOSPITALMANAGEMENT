package com.hms.service;

import com.hms.model.DoctorDTO;
import com.hms.model.Doctor;
import com.hms.repository.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DoctorService {
    
    private static final Logger log = LoggerFactory.getLogger(DoctorService.class);
    private final DoctorRepository doctorRepository;
    
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    
    public List<DoctorDTO> getAllDoctors() {
        log.info("Fetching all doctors");
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public DoctorDTO getDoctorById(@NonNull Long id) {
        log.info("Fetching doctor with id: {}", id);
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
        return convertToDTO(doctor);
    }
    
    public List<DoctorDTO> getDoctorsBySpecialty(String specialty) {
        log.info("Fetching doctors by specialty: {}", specialty);
        List<Doctor> doctors = doctorRepository.findBySpecialty(specialty);
        return doctors.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
        log.info("Creating new doctor with license: {}", doctorDTO.getLicenseNumber());
        
        if (doctorRepository.existsByLicenseNumber(doctorDTO.getLicenseNumber())) {
            throw new RuntimeException("Doctor with license number " + doctorDTO.getLicenseNumber() + " already exists");
        }
        
        Doctor doctor = convertToEntity(doctorDTO);
        
        // Explicit null check to satisfy type safety
        if (doctor != null) {
            Doctor savedDoctor = doctorRepository.save(doctor);
            log.info("Successfully created doctor with id: {}", savedDoctor.getId());
            return convertToDTO(savedDoctor);
        }
        
        throw new RuntimeException("Failed to create doctor");
    }
    
    public DoctorDTO updateDoctor(Long id, DoctorDTO doctorDTO) {
        log.info("Updating doctor with id: {}", id);
        
        if (id == null) {
            throw new IllegalArgumentException("Doctor ID cannot be null");
        }
        
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
        
        // Explicit null check to satisfy type safety
        if (existingDoctor != null) {
            if (!existingDoctor.getLicenseNumber().equals(doctorDTO.getLicenseNumber()) && 
                doctorRepository.existsByLicenseNumber(doctorDTO.getLicenseNumber())) {
                throw new RuntimeException("Doctor with license number " + doctorDTO.getLicenseNumber() + " already exists");
            }
            
            existingDoctor.setName(doctorDTO.getName());
            existingDoctor.setSpecialty(doctorDTO.getSpecialty());
            existingDoctor.setLicenseNumber(doctorDTO.getLicenseNumber());
            existingDoctor.setYearsOfExperience(doctorDTO.getYearsOfExperience());
            
            Doctor updatedDoctor = doctorRepository.save(existingDoctor);
            log.info("Successfully updated doctor with id: {}", id);
            return convertToDTO(updatedDoctor);
        }
        
        throw new RuntimeException("Doctor not found with id: " + id);
    }
    
    public void deleteDoctor(Long id) {
        log.info("Deleting doctor with id: {}", id);
        
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
        
        doctorRepository.delete(doctor);
        log.info("Successfully deleted doctor with id: {}", id);
    }
    
    private @NonNull DoctorDTO convertToDTO(@NonNull Doctor doctor) {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(doctor.getId());
        doctorDTO.setName(doctor.getName());
        doctorDTO.setSpecialty(doctor.getSpecialty());
        doctorDTO.setLicenseNumber(doctor.getLicenseNumber());
        doctorDTO.setYearsOfExperience(doctor.getYearsOfExperience());
        return doctorDTO;
    }
    
    private Doctor convertToEntity(@NonNull DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setName(doctorDTO.getName());
        doctor.setSpecialty(doctorDTO.getSpecialty());
        doctor.setLicenseNumber(doctorDTO.getLicenseNumber());
        doctor.setYearsOfExperience(doctorDTO.getYearsOfExperience());
        return doctor;
    }
}
