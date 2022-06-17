package com.hgebk.dokobackend.controller;

import com.hgebk.dokobackend.dto.CashReportDTO;
import com.hgebk.dokobackend.dto.SemesterReportDTO;
import com.hgebk.dokobackend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/cash")
    public CashReportDTO getCashReport() {
        return reportService.getCashReport();
    }

    @GetMapping("/semester")
    public SemesterReportDTO getSemesterReport(@RequestParam Optional<String> semester) {
        return reportService.getSemesterReport(semester);
    }
}
