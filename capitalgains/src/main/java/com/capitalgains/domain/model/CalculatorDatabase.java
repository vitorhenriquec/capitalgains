package com.capitalgains.domain.model;

public class CalculatorDatabase {
    private int amountActualShares = 0;

    private double meanBuyShareValue = 0;

    private final double minCostForTaxing = 20000;

    private double profit = 0;

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
    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getMinCostForTaxing() {
        return minCostForTaxing;
    }
}
