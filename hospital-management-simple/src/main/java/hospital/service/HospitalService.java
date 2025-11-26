package hospital.service;

import hospital.models.Doctor;
import hospital.models.Patient;
import hospital.models.Appointment;
import hospital.repository.DoctorRepository;
import hospital.repository.PatientRepository;
import hospital.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    // Doctor methods
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
    
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
    
    // Patient methods
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }
    
    // Appointment methods
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
    
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
}
