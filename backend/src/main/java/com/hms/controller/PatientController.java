package com.hms.controller;

import com.hms.model.PatientDTO;
import com.hms.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
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
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        log.info("Fetching all patients");
        List<PatientDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable @NonNull Long id) {
        log.info("Fetching patient with id: {}", id);
        PatientDTO patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
        log.info("Creating new patient: {}", patientDTO.getName());
        PatientDTO createdPatient = patientService.createPatient(patientDTO);
        return ResponseEntity.ok(createdPatient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable @NonNull Long id, @RequestBody PatientDTO patientDTO) {
        log.info("Updating patient with id: {}", id);
        PatientDTO updatedPatient = patientService.updatePatient(id, patientDTO);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        log.info("Deleting patient with id: {}", id);
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully");
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getPatientCount() {
        log.info("Getting total patient count");
        List<PatientDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok((long) patients.size());
    }
}