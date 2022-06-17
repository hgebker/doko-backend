package com.hgebk.dokobackend.dto;

import com.hgebk.dokobackend.model.Evening;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
