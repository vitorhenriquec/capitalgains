package com.capitalgains.domain.model;

public class SellTaxCalculator implements OperationTaxCalculator {

    @Override
    public double calculate(TradeOrder tradeOrder, CalculatorDatabase calculatorDatabase) {
        calculatorDatabase.setAmountActualShares(calculatorDatabase.getAmountActualShares() - tradeOrder.quantity());
        var profit = calculateWeightedAveragePrice(tradeOrder, calculatorDatabase);

        boolean inDebt = profit <= 0;
        if(inDebt) {
            calculatorDatabase.setDebt(profit);
        }

        if(shouldNotPayTax(tradeOrder, calculatorDatabase, inDebt)){
            return 0.00;
        }

        return profit*0.2;
    }

    private static boolean shouldNotPayTax(TradeOrder tradeOrder, CalculatorDatabase calculatorDatabase, boolean inDebt) {
        return (tradeOrder.quantity() * tradeOrder.unitCost() < calculatorDatabase.getMinCostForTaxing()) || inDebt;
    }

    private double calculateWeightedAveragePrice(TradeOrder tradeOrder, CalculatorDatabase calculatorDatabase) {
        return ((tradeOrder.quantity() * tradeOrder.unitCost()) - (tradeOrder.quantity() * calculatorDatabase.getMeanBuyShareValue())) + calculatorDatabase.getDebt();
    }
}
