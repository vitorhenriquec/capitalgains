package com.capitalgains.domain.usecase;

import com.capitalgains.domain.model.StockTransactionHistory;
import com.capitalgains.domain.model.TradeOrder;

public interface OperationTaxCalculator {
    double calculate(TradeOrder tradeOrder, StockTransactionHistory stockTransactionHistory);
}
