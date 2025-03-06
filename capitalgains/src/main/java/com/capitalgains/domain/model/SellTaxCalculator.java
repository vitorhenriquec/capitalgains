package com.capitalgains.domain.model;

public class SellTaxCalculator implements OperationTaxCalculator {

    @Override
    public double calculate(TradeOrder tradeOrder, CalculatorDatabase calculatorDatabase) {
        calculatorDatabase.setAmountActualShares(calculatorDatabase.getAmountActualShares() - tradeOrder.quantity());
        calculatorDatabase.setProfit(calculateWeightedAveragePrice(tradeOrder, calculatorDatabase));

        if(tradeOrder.quantity() * tradeOrder.unitCost() < calculatorDatabase.getMinCostForTaxing()){
            return 0.00;
        }

        if(calculatorDatabase.getProfit() <= 0) {
            return (0.00);
        }

        return calculatorDatabase.getProfit()*0.2;
    }

    private double calculateWeightedAveragePrice(TradeOrder tradeOrder, CalculatorDatabase calculatorDatabase) {
        return (tradeOrder.quantity() * tradeOrder.unitCost() - tradeOrder.quantity() * calculatorDatabase.getMeanBuyShareValue()) + calculatorDatabase.getProfit();
    }
}
