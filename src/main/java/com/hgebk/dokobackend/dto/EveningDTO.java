package com.hgebk.dokobackend.dto;

import com.hgebk.dokobackend.model.Evening;
import lombok.*;

@Getter
@Setter
public class EveningDTO extends Evening {
    private Double sum;

    private Double avg;

    private Double min;

    private Double max;

    private String minPlayer;

    private String maxPlayer;
}
