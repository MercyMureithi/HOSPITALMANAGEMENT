package com.hms.repository;

import com.hms.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    
    List<Bill> findByPatientId(Long patientId);
    
    List<Bill> findByStatus(String status);
}
