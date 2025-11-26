package hospital.controller;

import hospital.models.Doctor;
import hospital.models.Patient;
import hospital.models.Appointment;
import hospital.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class HospitalController {
    
    @Autowired
    private HospitalService hospitalService;
    
    // Doctor endpoints
    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return hospitalService.getAllDoctors();
    }
    
    @PostMapping("/doctors")
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return hospitalService.saveDoctor(doctor);
    }
    
    // Patient endpoints
    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return hospitalService.getAllPatients();
    }
    
    @PostMapping("/patients")
    public Patient addPatient(@RequestBody Patient patient) {
        return hospitalService.savePatient(patient);
    }
    
    // Appointment endpoints
    @GetMapping("/appointments")
    public List<Appointment> getAllAppointments() {
        return hospitalService.getAllAppointments();
    }
    
    @PostMapping("/appointments")
    public Appointment bookAppointment(@RequestBody Appointment appointment) {
        return hospitalService.saveAppointment(appointment);
    }
}
