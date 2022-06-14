package com.hgebk.dokobackend.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Double resultJan;

    @DynamoDBAttribute(attributeName = "tim")
    @JsonProperty("tim")
    private Double resultTim;

    @DynamoDBAttribute(attributeName = "ole")
    @JsonProperty("ole")
    private Double resultOle;

    @DynamoDBAttribute(attributeName = "louisa")
    @JsonProperty("louisa")
    private Double resultLouisa;

    @DynamoDBAttribute(attributeName = "hannes")
    @JsonProperty("hannes")
    private Double resultHannes;
}
