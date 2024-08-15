package com.hgebk.doko.service;

import com.hgebk.doko.domain.EveningResults;
import com.hgebk.doko.dto.*;
import com.hgebk.doko.entity.Evening;
import com.hgebk.doko.entity.Player;
import com.hgebk.doko.modelassembler.EveningModelAssembler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReportService {
    private final EarningService earningService;
    private final ExpenseService expenseService;
    private final EveningService eveningService;
    private final EveningModelAssembler eveningModelAssembler;

    @Autowired
    public ReportService(
            EarningService earningService,
            ExpenseService expenseService,
            EveningService eveningService,
            EveningModelAssembler eveningModelAssembler
    ) {
        this.earningService = earningService;
        this.expenseService = expenseService;
        this.eveningService = eveningService;
        this.eveningModelAssembler = eveningModelAssembler;
    }

    public CashReportDTO getCashReport() {
        log.info("DBACK: Getting totals");
        Double totalExpenses = expenseService.getTotalExpenses();
        Double incomeFromEarnings = earningService.getTotalEarnings();
        Double incomeFromEvenings = eveningService.getTotalIncomeFromEvenings(Optional.empty());

        log.info("DBACK: Building report");
        return new CashReportDTO(totalExpenses, incomeFromEarnings, incomeFromEvenings);
    }

    public SemesterReportDTO getSemesterReport(Optional<String> semesterKey) {
        log.info("DBACK: Getting evenings");
        List<Evening> evenings = eveningService.searchEvenings(semesterKey);
        List<EveningDTO> eveningDTOs = evenings
                .stream()
                .map(eveningModelAssembler::toModel)
                .collect(Collectors.toList());

        log.info("DBACK: Building report");
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
        log.info("DBACK: Getting semester results");
        Map<Player, List<EveningResultDTO>> resultsByPlayer = eveningService.getEveningResultsByPlayer(semester);
        return resultsByPlayer.entrySet().stream().map(entry -> {
            Player player = entry.getKey();
            List<EveningResultDTO> results = entry.getValue();
            EveningResults resultsDomain = new EveningResults(results);
            return resultsDomain.toSemesterResult(player);
        }).collect(Collectors.toList());
    }
}
