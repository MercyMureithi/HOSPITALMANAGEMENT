package com.hms.controller;

import com.hms.dto.BillDTO;
import com.hms.service.BillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bills")
@RequiredArgsConstructor
@Tag(name = "Bills", description = "Bill management APIs")
public class BillController {

    private final BillService billService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','CLERK')")
    @Operation(summary = "Create a new bill")
    public ResponseEntity<BillDTO> createBill(@Valid @RequestBody BillDTO billDTO) {
        BillDTO createdBill = billService.createBill(billDTO);
        return new ResponseEntity<>(createdBill, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','CLERK')")
    @Operation(summary = "Update an existing bill")
    public ResponseEntity<BillDTO> updateBill(@PathVariable Long id, @Valid @RequestBody BillDTO billDTO) {
        BillDTO updatedBill = billService.updateBill(id, billDTO);
        return ResponseEntity.ok(updatedBill);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','CLERK')")
    @Operation(summary = "Get bill by ID")
    public ResponseEntity<BillDTO> getBillById(@PathVariable Long id) {
        BillDTO bill = billService.getBillById(id);
        return ResponseEntity.ok(bill);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','CLERK')")
    @Operation(summary = "Get all bills")
    public ResponseEntity<List<BillDTO>> getAllBills() {
        List<BillDTO> bills = billService.getAllBills();
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','CLERK')")
    @Operation(summary = "Get bills by patient ID")
    public ResponseEntity<List<BillDTO>> getBillsByPatient(@PathVariable Long patientId) {
        List<BillDTO> bills = billService.getBillsByPatient(patientId);
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','CLERK')")
    @Operation(summary = "Get bills by status")
    public ResponseEntity<List<BillDTO>> getBillsByStatus(@PathVariable String status) {
        List<BillDTO> bills = billService.getBillsByStatus(status);
        return ResponseEntity.ok(bills);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a bill")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
        return ResponseEntity.noContent().build();
    }
}
