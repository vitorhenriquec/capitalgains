package com.capitalgains.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum TradeOperationType {
    BUY("buy"),
    SELL("sell");

    private String description;

    public String getDescription(){
        return this.description;
    }

    TradeOperationType(String description) {
        this.description = description;
    }

    @JsonCreator
    public static TradeOperationType fromString(String value) {
        return Arrays.stream(TradeOperationType.values()).toList().stream().filter(it -> it.description.equals(value)).findFirst().orElseThrow();
    }

    @Override
    public String toString() {
        return this.description;
    }
}
