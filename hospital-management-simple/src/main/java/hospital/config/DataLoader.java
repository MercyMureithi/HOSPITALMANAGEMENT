package hospital.config;

import hospital.models.Doctor;
import hospital.models.Patient;
import hospital.models.Appointment;
import hospital.repository.DoctorRepository;
import hospital.repository.PatientRepository;
import hospital.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void run(String... args) throws Exception {
        if (doctorRepository.count() == 0) {
            System.out.println("Loading sample data...");
            
            // Add sample doctors
            Doctor doctor1 = new Doctor("Dr. Smith", "Cardiology");
            Doctor doctor2 = new Doctor("Dr. Johnson", "Pediatrics");
            doctorRepository.save(doctor1);
            doctorRepository.save(doctor2);
            
            // Add sample patients
            Patient patient1 = new Patient("John Doe", "john@email.com");
            Patient patient2 = new Patient("Jane Smith", "jane@email.com");
            patientRepository.save(patient1);
            patientRepository.save(patient2);
            
            // Add sample appointment
            Appointment appointment = new Appointment(patient1, doctor1, LocalDateTime.now().plusDays(1));
            appointmentRepository.save(appointment);
            
            System.out.println("Sample data loaded!");
        }
    }
}
