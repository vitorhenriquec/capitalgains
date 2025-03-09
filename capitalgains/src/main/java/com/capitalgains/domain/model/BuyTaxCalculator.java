package com.capitalgains.domain.model;

public class BuyTaxCalculator implements OperationTaxCalculator {

    @Override
    public double calculate(TradeOrder tradeOrder, CalculatorDatabase calculatorDatabase) {
        if(calculatorDatabase.getAmountActualShares() == 0) {
            calculatorDatabase.setMeanBuyShareValue(tradeOrder.unitCost());
        } else {
            calculatorDatabase.setMeanBuyShareValue(calculateMeanPrice(tradeOrder, calculatorDatabase));
        }

        calculatorDatabase.setAmountActualShares(tradeOrder.quantity());
        return 0.00;
    }

    private double calculateMeanPrice(TradeOrder tradeOrder, CalculatorDatabase calculatorDatabase) {
        return (calculatorDatabase.getAmountActualShares() * calculatorDatabase.getMeanBuyShareValue()
                + tradeOrder.quantity() * tradeOrder.unitCost()) / (calculatorDatabase.getAmountActualShares() + tradeOrder.quantity());
    }
}
