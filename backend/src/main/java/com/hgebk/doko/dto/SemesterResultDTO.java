package com.hgebk.doko.dto;

import com.hgebk.doko.entity.Player;
import lombok.Builder;
import lombok.Data;

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
