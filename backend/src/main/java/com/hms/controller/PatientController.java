package com.hms.controller;

import com.hms.dto.PatientDTO;
import com.hms.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    private static final Logger log = LoggerFactory.getLogger(PatientController.class);
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('CLERK')")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        log.info("Fetching all patients");
        List<PatientDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('CLERK')")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        log.info("Fetching patient with id: {}", id);
        PatientDTO patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLERK')")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
        log.info("Creating new patient: {}", patientDTO.getName());
        PatientDTO createdPatient = patientService.createPatient(patientDTO);
        return ResponseEntity.ok(createdPatient);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLERK')")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
        log.info("Updating patient with id: {}", id);
        PatientDTO updatedPatient = patientService.updatePatient(id, patientDTO);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLERK')")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        log.info("Deleting patient with id: {}", id);
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully");
    }

    @GetMapping("/count")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('CLERK')")
    public ResponseEntity<Long> getPatientCount() {
        log.info("Getting total patient count");
        List<PatientDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok((long) patients.size());
    }
}
