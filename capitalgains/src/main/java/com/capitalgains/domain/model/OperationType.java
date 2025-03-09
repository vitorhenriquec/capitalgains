package com.capitalgains.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum OperationType {
    BUY("buy", new BuyTaxCalculator()),
    SELL("sell", new SellTaxCalculator());

    private String description;

    private OperationTaxCalculator operationTaxCalculator;

    public String getDescription(){
        return this.description;
    }

    public OperationTaxCalculator getOperationTaxCalculator() {
        return operationTaxCalculator;
    }

    OperationType(String description, OperationTaxCalculator operationTaxCalculator) {
        this.description = description;
        this.operationTaxCalculator = operationTaxCalculator;
    }

    @JsonCreator
    public static OperationType fromString(String value) {
        return Arrays.stream(OperationType.values()).toList().stream().filter(it -> it.description.equals(value)).findFirst().orElseThrow();
    }

    @Override
    public String toString() {
        return this.description;
    }
}
