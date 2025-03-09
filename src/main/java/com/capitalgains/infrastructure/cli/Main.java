package com.capitalgains.infrastructure.cli;

import com.capitalgains.domain.model.TradeTax;
import com.capitalgains.domain.usecase.CalculatorUseCase;
import com.capitalgains.domain.usecase.CalculatorUseCaseImpl;
import com.capitalgains.domain.usecase.LineReaderUseCase;
import com.capitalgains.domain.usecase.LineReaderUseCaseImpl;

import java.util.List;

public class Main {
    private static final LineReaderUseCase lineReader = new LineReaderUseCaseImpl();

    public static void main(String[] args) {
        var tradeTaxes = calculateTax(args);
        System.out.println(tradeTaxes);
    }

    public static List<List<TradeTax>> calculateTax(String[] args) {
        var tradeOrders = lineReader.processLines(args);

        return tradeOrders.stream().map(tradeOrder -> {
            CalculatorUseCase calculator = new CalculatorUseCaseImpl();
            return tradeOrder.stream().map(
                    it -> calculator.calculateTradeTax(it, it.operation().getOperationTaxCalculator())
            ).toList();
        }).toList();
    }

}
