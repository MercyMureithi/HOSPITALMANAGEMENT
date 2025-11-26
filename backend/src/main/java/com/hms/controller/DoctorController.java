package com.hms.controller;

import com.hms.model.DoctorDTO;
import com.hms.service.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
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
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        log.info("Fetching all doctors");
        List<DoctorDTO> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable @NonNull Long id) {
        log.info("Fetching doctor with id: {}", id);
        DoctorDTO doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctor);
    }

    @GetMapping("/specialty/{specialty}")
    public ResponseEntity<List<DoctorDTO>> getDoctorsBySpecialty(@PathVariable String specialty) {
        log.info("Fetching doctors by specialty: {}", specialty);
        List<DoctorDTO> doctors = doctorService.getDoctorsBySpecialty(specialty);
        return ResponseEntity.ok(doctors);
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctorDTO) {
        log.info("Creating new doctor: {}", doctorDTO.getName());
        DoctorDTO createdDoctor = doctorService.createDoctor(doctorDTO);
        return ResponseEntity.ok(createdDoctor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable @NonNull Long id, @RequestBody DoctorDTO doctorDTO) {
        log.info("Updating doctor with id: {}", id);
        DoctorDTO updatedDoctor = doctorService.updateDoctor(id, doctorDTO);
        return ResponseEntity.ok(updatedDoctor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable @NonNull Long id) {
        log.info("Deleting doctor with id: {}", id);
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok("Doctor deleted successfully");
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getDoctorCount() {
        log.info("Getting total doctor count");
        List<DoctorDTO> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok((long) doctors.size());
    }
}