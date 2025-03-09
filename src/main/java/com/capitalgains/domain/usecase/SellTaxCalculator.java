package com.capitalgains.domain.usecase;

import com.capitalgains.domain.model.StockTransactionHistory;
import com.capitalgains.domain.model.TradeOrder;

public class SellTaxCalculator implements OperationTaxCalculator {

    @Override
    public double calculate(TradeOrder tradeOrder, StockTransactionHistory stockTransactionHistory) {
        stockTransactionHistory.setAmountActualShares(stockTransactionHistory.getAmountActualShares() - tradeOrder.quantity());
        var profit = calculateWeightedAveragePrice(tradeOrder, stockTransactionHistory);

        boolean inDebt = profit <= 0;
        if(inDebt) {
            stockTransactionHistory.setDebt(profit);
        }

        if(shouldNotPayTax(tradeOrder, stockTransactionHistory, inDebt)){
            return 0.00;
        }

        return profit*0.2;
    }

    private static boolean shouldNotPayTax(TradeOrder tradeOrder, StockTransactionHistory stockTransactionHistory, boolean inDebt) {
        return (tradeOrder.quantity() * tradeOrder.unitCost() < stockTransactionHistory.getMinCostForTaxing()) || inDebt;
    }

    private double calculateWeightedAveragePrice(TradeOrder tradeOrder, StockTransactionHistory stockTransactionHistory) {
        return ((tradeOrder.quantity() * tradeOrder.unitCost()) - (tradeOrder.quantity() * stockTransactionHistory.getMeanBuyShareValue())) + stockTransactionHistory.getDebt();
    }
}
