package com.hgebk.dokobackend.dto;

import com.hgebk.dokobackend.domain.EveningResults;
import com.hgebk.dokobackend.model.Player;
import com.hgebk.dokobackend.model.Semester;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class SemesterResultDTO {
    private Player player;

    private double sum;

    private double avg;

    private double min;

    private double max;

    private long participations;
}
