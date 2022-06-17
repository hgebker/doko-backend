package com.hgebk.dokobackend.domain;

import com.hgebk.dokobackend.dto.EveningResultDTO;
import com.hgebk.dokobackend.dto.SemesterResultDTO;
import com.hgebk.dokobackend.model.Player;
import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Data
public class EveningResults {
    private List<EveningResultDTO> results;

    public EveningResults(List<EveningResultDTO> results) {
        this.results = results;
    }

    public long getParticipations() {
        return this.results.stream().filter(result -> result.getValue() > 0).count();
    }

    public double getSum() {
        return this.results.stream()
                .mapToDouble(EveningResultDTO::getValue)
                .sum();
    }

    public double getAvg() {
        long participations = getParticipations();

        if (participations == 0) {
            return 0;
        }

        return getSum() / participations;
    }

    public Optional<EveningResultDTO> getMinResult() {
        return this.results.stream()
                .filter(playerResult -> playerResult.getValue() > 0)
                .min(Comparator.comparing(EveningResultDTO::getValue));
    }

    public Optional<EveningResultDTO> getMaxResult() {
        return this.results.stream()
                .filter(playerResult -> playerResult.getValue() > 0)
                .max(Comparator.comparing(EveningResultDTO::getValue));
    }

    public SemesterResultDTO toSemesterResult(Player player) {
        return SemesterResultDTO
                .builder()
                .player(player)
                .sum(getSum())
                .avg(getAvg())
                .min(getMinResult().map(EveningResultDTO::getValue).orElse(0.0))
                .max(getMaxResult().map(EveningResultDTO::getValue).orElse(0.0))
                .participations(getParticipations())
                .build();
    }
}
