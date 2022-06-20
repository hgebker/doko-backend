package com.hgebk.dokobackend.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hgebk.dokobackend.dto.EveningResultDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "doko-abende")
public class Evening {
    @DynamoDBHashKey
    @DynamoDBAttribute(attributeName = "Datum")
    @JsonProperty("date")
    private String date;

    @DynamoDBAttribute(attributeName = "semester")
    @JsonProperty("semester")
    private String semester;

    @DynamoDBAttribute(attributeName = "jan")
    @JsonProperty("jan")
    private double resultJan;

    @DynamoDBAttribute(attributeName = "tim")
    @JsonProperty("tim")
    private double resultTim;

    @DynamoDBAttribute(attributeName = "ole")
    @JsonProperty("ole")
    private double resultOle;

    @DynamoDBAttribute(attributeName = "louisa")
    @JsonProperty("louisa")
    private double resultLouisa;

    @DynamoDBAttribute(attributeName = "hannes")
    @JsonProperty("hannes")
    private double resultHannes;

    @DynamoDBIgnore
    public List<EveningResultDTO> getResults() {
        return List.of(new EveningResultDTO(Player.JAN, this.getResultJan()),
                       new EveningResultDTO(Player.TIM, this.getResultTim()),
                       new EveningResultDTO(Player.OLE, this.getResultOle()),
                       new EveningResultDTO(Player.LOUISA, this.getResultLouisa()),
                       new EveningResultDTO(Player.HANNES, this.getResultHannes()));
    }
}
