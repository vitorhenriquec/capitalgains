package com.capitalgains.domain.model;

public class CalculatorDatabase {
    private int amountActualShares = 0;

    private double meanBuyShareValue = 0;

    private final double minCostForTaxing = 20000;

    private double debt = 0;

    public int getAmountActualShares() {
        return amountActualShares;
    }

    public void setAmountActualShares(int amountActualShares) {
        this.amountActualShares = amountActualShares;
    }

    public double getMeanBuyShareValue() {
        return meanBuyShareValue;
    }

    public void setMeanBuyShareValue(double meanBuyShareValue) {
        this.meanBuyShareValue = meanBuyShareValue;
    }
    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    public double getMinCostForTaxing() {
        return minCostForTaxing;
    }
}
