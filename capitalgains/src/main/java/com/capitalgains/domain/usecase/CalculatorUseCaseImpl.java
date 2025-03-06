package com.capitalgains.domain.usecase;

import com.capitalgains.domain.model.CalculatorDatabase;
import com.capitalgains.domain.model.OperationTaxCalculator;
import com.capitalgains.domain.model.TradeOrder;
import com.capitalgains.domain.model.TradeTax;

public class CalculatorUseCaseImpl implements CalculatorUseCase {

    private CalculatorDatabase calculatorDatabase;

    public CalculatorUseCaseImpl() {
        this.calculatorDatabase = new CalculatorDatabase();
    }

    public CalculatorUseCaseImpl(CalculatorDatabase calculatorDatabase) {
        this.calculatorDatabase = calculatorDatabase;
    }

    public CalculatorDatabase getCalculatorDatabase() {
        return calculatorDatabase;
    }

    @Override
    public TradeTax calculateTradeTax(TradeOrder tradeOrder, OperationTaxCalculator operation) {

        return new TradeTax(
                operation.calculate(tradeOrder, calculatorDatabase)
        );
    }
}
