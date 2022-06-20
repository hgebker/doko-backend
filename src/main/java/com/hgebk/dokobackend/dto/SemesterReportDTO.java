package com.hgebk.dokobackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.Comparator;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemesterReportDTO {
    private String semesterKey;

    private List<EveningDTO> evenings;

    private long numberOfEvenings;

    private List<SemesterResultDTO> semesterResults;

    private double totalIncome;

    public SemesterResultDTO getBest() {
        return semesterResults.stream()
                .min(Comparator.comparing(SemesterResultDTO::getAvg))
                .orElse(null);
    }

    public SemesterResultDTO getWorst() {
        return semesterResults.stream()
                .max(Comparator.comparing(SemesterResultDTO::getAvg))
                .orElse(null);
    }
}
