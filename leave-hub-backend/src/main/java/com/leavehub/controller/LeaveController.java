package com.leavehub.controller;

import com.leavehub.model.LeaveRequest;
import com.leavehub.model.LeaveStatus;
import com.leavehub.repository.LeaveRepository;
import com.leavehub.service.PdfGeneratorService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@CrossOrigin(origins = "http://localhost:4200")
public class LeaveController {

    private final LeaveRepository leaveRepository;
    private final PdfGeneratorService pdfGeneratorService;

    public LeaveController(LeaveRepository leaveRepository, PdfGeneratorService pdfGeneratorService) {
        this.leaveRepository = leaveRepository;
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping
    public List<LeaveRequest> getAllRequests() {
        return leaveRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createRequest(@RequestBody LeaveRequest request) {
        if (request.getStartDate() != null && request.getEndDate() != null) {
            long days = ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate()) + 1;
            request.setRequestedDays((int) days);
        }

        long overlaps = leaveRepository.countOverlappingApprovedLeaves(
                request.getDepartment(), request.getStartDate(), request.getEndDate(), null);

        if (overlaps > 0) {
            return ResponseEntity.badRequest().body("Atentie: Exista deja o cerere aprobata in acest departament in perioada selectata!");
        }

        LeaveRequest saved = leaveRepository.save(request);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<LeaveRequest> updateStatus(@PathVariable Long id, @RequestParam LeaveStatus status) {
        LeaveRequest req = leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cererea nu a fost gasita"));
        req.setStatus(status);
        return ResponseEntity.ok(leaveRepository.save(req));
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<InputStreamResource> downloadPdf(@PathVariable Long id) {
        LeaveRequest req = leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cererea nu a fost gasita"));

        ByteArrayInputStream bis = pdfGeneratorService.generateLeaveDocument(req);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=cerere_concediu_" + id + ".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}