package com.hgebk.dokobackend.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
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
    private String art;

    @DynamoDBAttribute(attributeName = "betrag")
    private Double betrag;

    @DynamoDBAttribute(attributeName = "semester")
    private String semester;
}
