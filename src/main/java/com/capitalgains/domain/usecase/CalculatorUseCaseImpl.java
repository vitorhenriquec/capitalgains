package com.capitalgains.domain.usecase;

import com.capitalgains.domain.model.StockTransactionHistory;
import com.capitalgains.domain.model.TradeOrder;
import com.capitalgains.domain.model.TradeTax;

import static com.capitalgains.infrastructure.util.FormatConverter.toBigDecimal;

public class CalculatorUseCaseImpl implements CalculatorUseCase {

    private StockTransactionHistory stockTransactionHistory;

    public CalculatorUseCaseImpl() {
        this.stockTransactionHistory = new StockTransactionHistory();
    }

    public CalculatorUseCaseImpl(StockTransactionHistory stockTransactionHistory) {
        this.stockTransactionHistory = stockTransactionHistory;
    }

    public StockTransactionHistory getCalculatorDatabase() {
        return stockTransactionHistory;
    }

    @Override
    public TradeTax calculateTradeTax(TradeOrder tradeOrder, OperationTaxCalculator operation) {

        return new TradeTax(
                toBigDecimal(operation.calculate(tradeOrder, stockTransactionHistory))
        );
    }
}
