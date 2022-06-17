package com.hgebk.dokobackend.dto;

import lombok.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
