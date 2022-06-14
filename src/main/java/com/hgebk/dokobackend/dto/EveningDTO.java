package com.hgebk.dokobackend.dto;

import com.hgebk.dokobackend.model.Evening;
import lombok.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

@Getter
@Setter
public class EveningDTO extends Evening {
    private Double sum;

    private Double avg;

    private Double min;

    private Double max;

    private String minPlayer;

    private String maxPlayer;

    private PlayerResultDTO[] getPlayerResults() {
        return new PlayerResultDTO[]{
                new PlayerResultDTO("jan", this.amountJan),
                new PlayerResultDTO("tim", this.amountTim),
                new PlayerResultDTO("ole", this.amountOle),
                new PlayerResultDTO("louisa", this.amountLouisa),
                new PlayerResultDTO("hannes", this.amountHannes)
        };
    }

    public Double getSum() {
        return Arrays
                .stream(this.getPlayerResults()).mapToDouble(PlayerResultDTO::getValue).sum();
    }

    public Double getAvg() {
        return this.getSum() / this.getPlayerResults().length;
    }

    public Optional<PlayerResultDTO> getMinResult() {
        return Arrays.stream(this.getPlayerResults()).filter(playerResult -> {
            return playerResult.getValue() > 0;
        }).min(Comparator.comparing(PlayerResultDTO::getValue));
    }

    public Optional<PlayerResultDTO> getMaxResult() {
        return Arrays.stream(this.getPlayerResults()).filter(playerResult -> {
            return playerResult.getValue() > 0;
        }).max(Comparator.comparing(PlayerResultDTO::getValue));
    }
}
