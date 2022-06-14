package com.hgebk.dokobackend.dto;

import lombok.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

@Data
public class EveningDTO  {
    private String date;

    private String semester;

    private ResultDTO[] results;

    private Double sum;

    private Double avg;


    public Double getSum() {
        return Arrays
                .stream(this.results).mapToDouble(ResultDTO::getValue).sum();
    }

    public Double getAvg() {
        return this.getSum() / this.results.length;
    }

    public Optional<ResultDTO> getMinResult() {
        return Arrays.stream(this.results).filter(playerResult -> {
            return playerResult.getValue() > 0;
        }).min(Comparator.comparing(ResultDTO::getValue));
    }

    public Optional<ResultDTO> getMaxResult() {
        return Arrays.stream(this.results).filter(playerResult -> {
            return playerResult.getValue() > 0;
        }).max(Comparator.comparing(ResultDTO::getValue));
    }
}
