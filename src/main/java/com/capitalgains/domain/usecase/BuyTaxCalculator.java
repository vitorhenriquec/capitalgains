package com.capitalgains.domain.usecase;

import com.capitalgains.domain.model.StockTransactionHistory;
import com.capitalgains.domain.model.TradeOrder;

public class BuyTaxCalculator implements OperationTaxCalculator {

    @Override
    public double calculate(TradeOrder tradeOrder, StockTransactionHistory stockTransactionHistory) {
        if(stockTransactionHistory.getAmountActualShares() == 0) {
            stockTransactionHistory.setMeanBuyShareValue(tradeOrder.unitCost());
        } else {
            stockTransactionHistory.setMeanBuyShareValue(calculateMeanPrice(tradeOrder, stockTransactionHistory));
        }

        stockTransactionHistory.setAmountActualShares(tradeOrder.quantity());
        return 0.00;
    }

    private double calculateMeanPrice(TradeOrder tradeOrder, StockTransactionHistory stockTransactionHistory) {
        return (stockTransactionHistory.getAmountActualShares() * stockTransactionHistory.getMeanBuyShareValue()
                + tradeOrder.quantity() * tradeOrder.unitCost()) / (stockTransactionHistory.getAmountActualShares() + tradeOrder.quantity());
    }
}
