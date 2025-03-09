package com.capitalgains.domain.model;

public interface OperationTaxCalculator {
    double calculate(TradeOrder tradeOrder, CalculatorDatabase calculatorMemory);
}
