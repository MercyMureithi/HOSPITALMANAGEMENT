package com.hms.repository;

import com.hms.model.Appointment;
import com.hms.model.Patient;
import com.hms.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    
    List<Appointment> findByPatient(Patient patient);
    
    List<Appointment> findByDoctor(Doctor doctor);
    
    List<Appointment> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    List<Appointment> findByStatus(String status);
}
