package com.hms.controller;

import com.hms.model.DoctorDTO;
import com.hms.model.PatientDTO;
import com.hms.model.AppointmentDTO;
import com.hms.model.BillDTO;
import com.hms.service.HospitalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Hospital Management API", description = "APIs for managing doctors and patients in the hospital system")
public class HospitalController {

    private final HospitalService hospitalService;

    // Constructor injection - Spring handles this automatically
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    // Doctor endpoints - I kept these separate from patients for clarity
    @GetMapping("/doctors")
    @Operation(summary = "Get all doctors", description = "Retrieve a list of all doctors in the hospital")
    public List<DoctorDTO> getAllDoctors() {
        return hospitalService.getAllDoctors();
    }

    @GetMapping("/doctors/{id}")
    @Operation(summary = "Get doctor by ID", description = "Find a specific doctor by their ID")
    public DoctorDTO getDoctorById(@Parameter(description = "Doctor ID") @PathVariable Long id) {
        return hospitalService.getDoctorById(id);
    }

    @PostMapping("/doctors")
    @Operation(summary = "Add new doctor", description = "Create a new doctor record in the system")
    public DoctorDTO createDoctor(@Parameter(description = "Doctor data") @RequestBody DoctorDTO doctorDTO) {
        return hospitalService.createDoctor(doctorDTO);
    }

    @PutMapping("/doctors/{id}")
    @Operation(summary = "Update doctor", description = "Update existing doctor information")
    public DoctorDTO updateDoctor(@Parameter(description = "Doctor ID") @PathVariable Long id, @Parameter(description = "Updated doctor data") @RequestBody DoctorDTO doctorDTO) {
        return hospitalService.updateDoctor(id, doctorDTO);
    }

    @DeleteMapping("/doctors/{id}")
    @Operation(summary = "Delete doctor", description = "Remove a doctor from the system")
    public String deleteDoctor(@Parameter(description = "Doctor ID") @PathVariable Long id) {
        hospitalService.deleteDoctor(id);
        return "Doctor deleted successfully";
    }

    // Patient endpoints
    @GetMapping("/patients")
    @Operation(summary = "Get all patients", description = "Retrieve a list of all patients in the hospital")
    public List<PatientDTO> getAllPatients() {
        return hospitalService.getAllPatients();
    }

    @GetMapping("/patients/{id}")
    @Operation(summary = "Get patient by ID", description = "Find a specific patient by their ID")
    public PatientDTO getPatientById(@Parameter(description = "Patient ID") @PathVariable Long id) {
        return hospitalService.getPatientById(id);
    }

    @PostMapping("/patients")
    @Operation(summary = "Add new patient", description = "Create a new patient record in the system")
    public PatientDTO createPatient(@Parameter(description = "Patient data") @RequestBody PatientDTO patientDTO) {
        return hospitalService.createPatient(patientDTO);
    }

    @PutMapping("/patients/{id}")
    @Operation(summary = "Update patient", description = "Update existing patient information")
    public PatientDTO updatePatient(@Parameter(description = "Patient ID") @PathVariable Long id, @Parameter(description = "Updated patient data") @RequestBody PatientDTO patientDTO) {
        return hospitalService.updatePatient(id, patientDTO);
    }

    @DeleteMapping("/patients/{id}")
    @Operation(summary = "Delete patient", description = "Remove a patient from the system")
    public String deletePatient(@Parameter(description = "Patient ID") @PathVariable Long id) {
        hospitalService.deletePatient(id);
        return "Patient deleted successfully";
    }

    // Appointment endpoints
    @GetMapping("/appointments")
    @Operation(summary = "Get all appointments", description = "Retrieve a list of all appointments in the hospital")
    public List<AppointmentDTO> getAllAppointments() {
        return hospitalService.getAllAppointments();
    }

    @GetMapping("/appointments/{id}")
    @Operation(summary = "Get appointment by ID", description = "Find a specific appointment by their ID")
    public AppointmentDTO getAppointmentById(@Parameter(description = "Appointment ID") @PathVariable Long id) {
        return hospitalService.getAppointmentById(id);
    }

    @PostMapping("/appointments")
    @Operation(summary = "Add new appointment", description = "Create a new appointment record in the system")
    public AppointmentDTO createAppointment(@Parameter(description = "Appointment data") @RequestBody AppointmentDTO appointmentDTO) {
        return hospitalService.createAppointment(appointmentDTO);
    }

    @DeleteMapping("/appointments/{id}")
    @Operation(summary = "Delete appointment", description = "Remove an appointment from the system")
    public String deleteAppointment(@Parameter(description = "Appointment ID") @PathVariable Long id) {
        hospitalService.deleteAppointment(id);
        return "Appointment deleted successfully";
    }

    // Billing endpoints
    @GetMapping("/bills")
    @Operation(summary = "Get all bills", description = "Retrieve a list of all bills in the hospital")
    public List<BillDTO> getAllBills() {
        return hospitalService.getAllBills();
    }

    @GetMapping("/bills/{id}")
    @Operation(summary = "Get bill by ID", description = "Find a specific bill by their ID")
    public BillDTO getBillById(@Parameter(description = "Bill ID") @PathVariable Long id) {
        return hospitalService.getBillById(id);
    }

    @PostMapping("/bills")
    @Operation(summary = "Add new bill", description = "Create a new bill record in the system")
    public BillDTO createBill(@Parameter(description = "Bill data") @RequestBody BillDTO billDTO) {
        return hospitalService.createBill(billDTO);
    }

    @DeleteMapping("/bills/{id}")
    @Operation(summary = "Delete bill", description = "Remove a bill from the system")
    public String deleteBill(@Parameter(description = "Bill ID") @PathVariable Long id) {
        hospitalService.deleteBill(id);
        return "Bill deleted successfully";
    }
}
