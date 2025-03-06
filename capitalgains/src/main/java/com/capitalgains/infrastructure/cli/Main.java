package com.capitalgains.infrastructure.cli;

import com.capitalgains.domain.model.TradeOrder;
import com.capitalgains.domain.usecase.LineReaderUseCase;
import com.capitalgains.domain.usecase.LineReaderUseCaseImpl;
import com.capitalgains.domain.usecase.CaculatorUseCase;
import com.capitalgains.domain.usecase.CalculatorUseCaseImpl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class Main {
    private static LineReaderUseCase lineReader = new LineReaderUseCaseImpl();

    public static void main(String[] args) {

//        String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 100}," +
//                "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 50},"+
//                "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 50}]\n"+
//                "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}," +
//                "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 5000},"+
//                "{\"operation\":\"sell\", \"unit-cost\":5.00, \"quantity\": 5000}]\n";
//        String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}," +
//                "{\"operation\":\"sell\", \"unit-cost\":5.00, \"quantity\": 5000},"+
//                "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 3000}]\n";

//        String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}," +
//                "{\"operation\":\"buy\", \"unit-cost\":25.00, \"quantity\": 5000},"+
//                "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 10000}]\n";

//        String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}," +
//                "{\"operation\":\"buy\", \"unit-cost\":25.00, \"quantity\": 5000},"+
//                "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 10000}," +
//                "{\"operation\":\"sell\", \"unit-cost\":25.00, \"quantity\": 5000}]\n";

        String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}," +
                "{\"operation\":\"sell\", \"unit-cost\":2.00, \"quantity\": 5000},"+
                "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 2000}," +
                "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 2000}," +
                "{\"operation\":\"sell\", \"unit-cost\":25.00, \"quantity\": 1000}]\n";



        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        List<List<TradeOrder>> tradeOrders = lineReader.processLines(new String[]{});
        //List<List<TradeOrder>> tradeOrders = lineReader.processLines(args);

        for(List<TradeOrder> tradeOrder: tradeOrders) {

            CaculatorUseCase taxCaculator = new CalculatorUseCaseImpl();

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
