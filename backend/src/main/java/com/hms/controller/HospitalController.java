package com.hms.controller;

import com.hms.model.DoctorDTO;
import com.hms.model.PatientDTO;
import com.hms.service.HospitalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HospitalController {

    private final HospitalService hospitalService;

    // Constructor injection - Spring handles this automatically
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    // Doctor endpoints - I kept these separate from patients for clarity
    @GetMapping("/doctors")
    public List<DoctorDTO> getAllDoctors() {
        return hospitalService.getAllDoctors();
    }

    @GetMapping("/doctors/{id}")
    public DoctorDTO getDoctorById(@PathVariable Long id) {
        return hospitalService.getDoctorById(id);
    }

    @PostMapping("/doctors")
    public DoctorDTO createDoctor(@RequestBody DoctorDTO doctorDTO) {
        return hospitalService.createDoctor(doctorDTO);
    }

    @PutMapping("/doctors/{id}")
    public DoctorDTO updateDoctor(@PathVariable Long id, @RequestBody DoctorDTO doctorDTO) {
        return hospitalService.updateDoctor(id, doctorDTO);
    }

    @DeleteMapping("/doctors/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        hospitalService.deleteDoctor(id);
        return "Doctor deleted successfully";
    }

    // Patient endpoints
    @GetMapping("/patients")
    public List<PatientDTO> getAllPatients() {
        return hospitalService.getAllPatients();
    }

    @GetMapping("/patients/{id}")
    public PatientDTO getPatientById(@PathVariable Long id) {
        return hospitalService.getPatientById(id);
    }

    @PostMapping("/patients")
    public PatientDTO createPatient(@RequestBody PatientDTO patientDTO) {
        return hospitalService.createPatient(patientDTO);
    }

    @PutMapping("/patients/{id}")
    public PatientDTO updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
        return hospitalService.updatePatient(id, patientDTO);
    }

    @DeleteMapping("/patients/{id}")
    public String deletePatient(@PathVariable Long id) {
        hospitalService.deletePatient(id);
        return "Patient deleted successfully";
    }
}
