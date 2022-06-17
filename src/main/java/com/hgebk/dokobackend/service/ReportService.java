package com.hgebk.dokobackend.service;

import com.hgebk.dokobackend.domain.EveningResults;
import com.hgebk.dokobackend.dto.*;
import com.hgebk.dokobackend.mapper.EveningMapper;
import com.hgebk.dokobackend.model.Evening;
import com.hgebk.dokobackend.model.Player;
import com.hgebk.dokobackend.model.Semester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {
    private final EarningService earningService;
    private final ExpenseService expenseService;
    private final EveningService eveningService;
    private final EveningMapper eveningMapper;

    @Autowired
    public ReportService(
            EarningService earningService,
            ExpenseService expenseService,
            EveningService eveningService,
            EveningMapper eveningMapper
    ) {
        this.earningService = earningService;
        this.expenseService = expenseService;
        this.eveningService = eveningService;
        this.eveningMapper  = eveningMapper;
    }

    public CashReportDTO getCashReport() {
        Double totalExpenses = expenseService.getTotalExpenses();
        Double incomeFromEarnings = earningService.getTotalEarnings();
        Double incomeFromEvenings = eveningService.getTotalIncomeFromEvenings(Optional.empty());

        return new CashReportDTO(
                totalExpenses,
                incomeFromEarnings,
                incomeFromEvenings
        );
    }

    public SemesterReportDTO getSemesterReport(Optional<String> semesterKey) {
        List<Evening> evenings = eveningService.searchEvenings(semesterKey);
        List<EveningDTO> eveningDTOs = evenings.stream()
                .map(eveningMapper::toDTO)
                .collect(Collectors.toList());

        SemesterReportDTO semesterReportDTO = new SemesterReportDTO();

        if (semesterKey.isPresent()) {
            semesterReportDTO.setSemesterKey(semesterKey.get());
        }

        semesterReportDTO.setTotalIncome(eveningService.getTotalIncomeFromEvenings(semesterKey));
        semesterReportDTO.setNumberOfEvenings(evenings.size());
        semesterReportDTO.setSemesterResults(getSemesterResults(semesterKey));
        semesterReportDTO.setEvenings(eveningDTOs);
        return semesterReportDTO;
    }

    public List<SemesterResultDTO> getSemesterResults(Optional<String> semester) {
        Map<Player, List<EveningResultDTO>> resultsByPlayer = eveningService.getEveningResultsByPlayer(semester);
        return resultsByPlayer.entrySet()
                              .stream()
                              .map(entry -> {
                                  Player player = entry.getKey();
                                  List<EveningResultDTO> results = entry.getValue();
                                  EveningResults resultsDomain = new EveningResults(results);
                                  return resultsDomain.toSemesterResult(player);
                              })
                              .collect(Collectors.toList());
    }
}
