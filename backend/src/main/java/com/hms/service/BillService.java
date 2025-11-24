package com.hms.service;

import com.hms.dto.BillDTO;
import com.hms.exception.ResourceNotFoundException;
import com.hms.model.Bill;
import com.hms.model.Patient;
import com.hms.repository.BillRepository;
import com.hms.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository billRepository;
    private final PatientRepository patientRepository;

    public BillDTO createBill(BillDTO billDTO) {
        Bill bill = mapToEntity(billDTO);
        Bill savedBill = billRepository.save(bill);
        return mapToDTO(savedBill);
    }

    public BillDTO updateBill(Long id, BillDTO billDTO) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found with id: " + id));
        
        Patient patient = patientRepository.findById(billDTO.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + billDTO.getPatientId()));
        
        bill.setPatient(patient);
        bill.setAmount(billDTO.getAmount());
        bill.setStatus(billDTO.getStatus());
        
        Bill updatedBill = billRepository.save(bill);
        return mapToDTO(updatedBill);
    }

    public BillDTO getBillById(Long id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found with id: " + id));
        return mapToDTO(bill);
    }

    public List<BillDTO> getAllBills() {
        List<Bill> bills = billRepository.findAll();
        return bills.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void deleteBill(Long id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found with id: " + id));
        billRepository.delete(bill);
    }

    public List<BillDTO> getBillsByPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + patientId));
        List<Bill> bills = billRepository.findByPatient(patient);
        return bills.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<BillDTO> getBillsByStatus(String status) {
        List<Bill> bills = billRepository.findByStatus(status);
        return bills.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private BillDTO mapToDTO(Bill bill) {
        BillDTO billDTO = new BillDTO();
        billDTO.setId(bill.getId());
        billDTO.setPatientId(bill.getPatient().getId());
        billDTO.setAmount(bill.getAmount());
        billDTO.setStatus(bill.getStatus());
        billDTO.setPatientName(bill.getPatient().getName());
        return billDTO;
    }

    private Bill mapToEntity(BillDTO billDTO) {
        Bill bill = new Bill();
        
        Patient patient = patientRepository.findById(billDTO.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + billDTO.getPatientId()));
        
        bill.setPatient(patient);
        bill.setAmount(billDTO.getAmount());
        bill.setStatus(billDTO.getStatus());
        return bill;
    }
}
