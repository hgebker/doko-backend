package com.hgebk.dokobackend.service;

import com.hgebk.dokobackend.dto.CashReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    private final EarningService earningService;
    private final ExpenseService expenseService;
    private final EveningService eveningService;

    @Autowired
    public ReportService(
            EarningService earningService,
            ExpenseService expenseService,
            EveningService eveningService
    ) {
        this.earningService = earningService;
        this.expenseService = expenseService;
        this.eveningService = eveningService;
    }

    public CashReportDTO getCashReport() {
        Double totalExpenses = expenseService.getTotalExpenses();
        Double incomeFromEarnings = earningService.getTotalEarnings();
        Double incomeFromEvenings = eveningService.getTotalIncomeFromEvenings();

        return new CashReportDTO(totalExpenses, incomeFromEarnings, incomeFromEvenings);
    }
}
