package com.capitalgains.domain.usecase;

import com.capitalgains.domain.model.OperationTaxCalculator;
import com.capitalgains.domain.model.TradeOrder;
import com.capitalgains.domain.model.TradeTax;

public interface CalculatorUseCase {
    TradeTax calculateTradeTax(TradeOrder tradeOrder, OperationTaxCalculator operation);
}
