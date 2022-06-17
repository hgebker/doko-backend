package com.hgebk.dokobackend.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Player {
    JAN("jan"),
    TIM("tim"),
    OLE("ole"),
    LOUISA("louisa"),
    HANNES("hannes");

    private String name;

    Player(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
