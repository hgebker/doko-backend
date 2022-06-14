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
    protected String date;

    @DynamoDBAttribute(attributeName = "semester")
    @JsonProperty("semester")
    protected String semester;

    @DynamoDBAttribute(attributeName = "jan")
    @JsonProperty("jan")
    protected Double amountJan;

    @DynamoDBAttribute(attributeName = "tim")
    @JsonProperty("tim")
    protected Double amountTim;

    @DynamoDBAttribute(attributeName = "ole")
    @JsonProperty("ole")
    protected Double amountOle;

    @DynamoDBAttribute(attributeName = "louisa")
    @JsonProperty("louisa")
    protected Double amountLouisa;

    @DynamoDBAttribute(attributeName = "hannes")
    @JsonProperty("hannes")
    protected Double amountHannes;
}
