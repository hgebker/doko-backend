package com.hgebk.dokobackend.controller;

import com.hgebk.dokobackend.dto.CashReportDTO;
import com.hgebk.dokobackend.dto.SemesterReportDTO;
import com.hgebk.dokobackend.modelassembler.CashReportModelAssembler;
import com.hgebk.dokobackend.modelassembler.SemesterReportModelAssembler;
import com.hgebk.dokobackend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;
    private final SemesterReportModelAssembler semesterReportModelAssembler;
    private final CashReportModelAssembler cashReportModelAssembler;

    @Autowired
    public ReportController(ReportService reportService,
                            SemesterReportModelAssembler semesterReportModelAssembler,
                            CashReportModelAssembler cashReportModelAssembler
    ) {
        this.reportService = reportService;
        this.semesterReportModelAssembler = semesterReportModelAssembler;
        this.cashReportModelAssembler = cashReportModelAssembler;
    }

    @GetMapping("/cash")
    public EntityModel<CashReportDTO> getCashReport() {
        return cashReportModelAssembler.toModel(reportService.getCashReport());
    }

    @GetMapping("/semester")
    public EntityModel<SemesterReportDTO> getSemesterReport(@RequestParam Optional<String> semester) {
        return semesterReportModelAssembler.toModel(reportService.getSemesterReport(semester));
    }
}
