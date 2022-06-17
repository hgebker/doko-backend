package com.hgebk.dokobackend.entity;

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
@DynamoDBTable(tableName = "doko-ausgaben")
public class Expense {
    @DynamoDBHashKey
    @DynamoDBAttribute(attributeName = "art")
    @JsonProperty("art")
    private String description;

    @DynamoDBAttribute(attributeName = "betrag")
    @JsonProperty("betrag")
    private double value;

    @DynamoDBAttribute(attributeName = "semester")
    @JsonProperty("semester")
    private String semesterKey;
}
