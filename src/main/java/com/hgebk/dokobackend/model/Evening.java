package com.hgebk.dokobackend.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hgebk.dokobackend.dto.PlayerResultDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "doko-abende")
public class Evening {
    @DynamoDBHashKey
    @DynamoDBAttribute(attributeName = "Datum")
    @JsonProperty("Datum")
    private String date;

    @DynamoDBAttribute(attributeName = "semester")
    @JsonProperty("semester")
    private String semester;

    @DynamoDBAttribute(attributeName = "jan")
    @JsonProperty("jan")
    private Double amountJan;

    @DynamoDBAttribute(attributeName = "tim")
    @JsonProperty("tim")
    private Double amountTim;

    @DynamoDBAttribute(attributeName = "ole")
    @JsonProperty("ole")
    private Double amountOle;

    @DynamoDBAttribute(attributeName = "louisa")
    @JsonProperty("louisa")
    private Double amountLouisa;

    @DynamoDBAttribute(attributeName = "hannes")
    @JsonProperty("hannes")
    private Double amountHannes;

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
        return Arrays.stream(this.getPlayerResults()).mapToDouble(PlayerResultDTO::getValue).sum();
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
