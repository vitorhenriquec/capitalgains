package com.capitalgains.domain.usecase;

import com.capitalgains.domain.model.CalculatorDatabase;
import com.capitalgains.domain.model.OperationTaxCalculator;
import com.capitalgains.domain.model.TradeOrder;
import com.capitalgains.domain.model.TradeTax;

public class CalculatorUseCaseImpl implements CaculatorUseCase {
    private CalculatorDatabase calculatorDatabase;

    public CalculatorUseCaseImpl() {
        this.calculatorDatabase = new CalculatorDatabase();
    }


    @Override
    public TradeTax calculateTradeTax(TradeOrder tradeOrder, OperationTaxCalculator operation) {

        return new TradeTax(
                operation.calculate(tradeOrder, calculatorDatabase)
        );
    }
}
