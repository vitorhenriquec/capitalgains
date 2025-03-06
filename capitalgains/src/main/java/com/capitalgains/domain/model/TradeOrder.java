package com.capitalgains.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TradeOrder(
        @JsonProperty("operation")
        TradeOperationType operation,
        @JsonProperty("unit-cost")
        double unitCost,
        int quantity
) {
}
