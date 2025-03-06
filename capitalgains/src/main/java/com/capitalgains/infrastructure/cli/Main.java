package com.capitalgains.infrastructure.cli;

import com.capitalgains.domain.model.TradeOrder;
import com.capitalgains.domain.usecase.CalculatorUseCase;
import com.capitalgains.domain.usecase.CalculatorUseCaseImpl;
import com.capitalgains.domain.usecase.LineReaderUseCase;
import com.capitalgains.domain.usecase.LineReaderUseCaseImpl;

import java.util.List;

public class Main {
    private static LineReaderUseCase lineReader = new LineReaderUseCaseImpl();

    public static void main(String[] args) {

        List<List<TradeOrder>> tradeOrders = lineReader.processLines(args);

        for(List<TradeOrder> tradeOrder: tradeOrders) {

            CalculatorUseCase taxCaculator = new CalculatorUseCaseImpl();

            for(TradeOrder order: tradeOrder){
               System.out.println(
                       taxCaculator.calculateTradeTax(
                               order,
                               order.operation().getOperationTaxCalculator()
                       )
               );
            }
        }
    }
}
