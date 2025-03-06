package com.capitalgains;

import com.capitalgains.domain.model.TradeOrder;
import com.capitalgains.domain.usecase.LineReaderUseCase;
import com.capitalgains.domain.usecase.LineReaderUseCaseImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        LineReaderUseCase lineReader = new LineReaderUseCaseImpl();
        List<List<TradeOrder>> tradeOrders = lineReader.processLines(args);
    }
}
