package com.hms.controller;

import com.hms.dto.DoctorDTO;
import com.hms.service.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
@CrossOrigin(origins = "*")
public class DoctorController {

    private static final Logger log = LoggerFactory.getLogger(DoctorController.class);
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('CLERK')")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        log.info("Fetching all doctors");
        List<DoctorDTO> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('CLERK')")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) {
        log.info("Fetching doctor with id: {}", id);
        DoctorDTO doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctor);
    }

    @GetMapping("/specialty/{specialty}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('CLERK')")
    public ResponseEntity<List<DoctorDTO>> getDoctorsBySpecialty(@PathVariable String specialty) {
        log.info("Fetching doctors by specialty: {}", specialty);
        List<DoctorDTO> doctors = doctorService.getDoctorsBySpecialty(specialty);
        return ResponseEntity.ok(doctors);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLERK')")
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctorDTO) {
        log.info("Creating new doctor: {}", doctorDTO.getName());
        DoctorDTO createdDoctor = doctorService.createDoctor(doctorDTO);
        return ResponseEntity.ok(createdDoctor);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLERK')")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Long id, @RequestBody DoctorDTO doctorDTO) {
        log.info("Updating doctor with id: {}", id);
        DoctorDTO updatedDoctor = doctorService.updateDoctor(id, doctorDTO);
        return ResponseEntity.ok(updatedDoctor);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLERK')")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        log.info("Deleting doctor with id: {}", id);
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok("Doctor deleted successfully");
    }

    @GetMapping("/count")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('CLERK')")
    public ResponseEntity<Long> getDoctorCount() {
        log.info("Getting total doctor count");
        List<DoctorDTO> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok((long) doctors.size());
    }
}
