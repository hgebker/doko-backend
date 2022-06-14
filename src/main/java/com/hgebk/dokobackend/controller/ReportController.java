package com.hgebk.dokobackend.controller;

import com.hgebk.dokobackend.dto.CashReportDTO;
import com.hgebk.dokobackend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
