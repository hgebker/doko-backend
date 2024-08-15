package com.hgebk.dokobackend.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
public class EveningDTO extends RepresentationModel<EveningDTO> {
    private String date;

    private String semester;

    private List<EveningResultDTO> results;

    private double sum;

    private double avg;

    private EveningResultDTO min;

    private EveningResultDTO max;
}
