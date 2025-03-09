package com.capitalgains.domain.usecase;

import com.capitalgains.domain.model.TradeOrder;
import com.capitalgains.domain.model.TradeTax;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class LineReaderUseCaseImpl implements LineReaderUseCase{
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<List<TradeTax>> processLines(String[] args) {
        List<List<TradeTax>> tradeTaxes = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty())  {

                List<TradeOrder> tradeOrderLine = objectMapper.readValue(line, new TypeReference<>() {});

                CalculatorUseCase calculator = new CalculatorUseCaseImpl();

                var tradeTax =  tradeOrderLine.stream().map(
                        it -> calculator.calculateTradeTax(it, it.operation().getOperationTaxCalculator())
                ).toList();

                System.out.println(
                      objectMapper.writeValueAsString(tradeTax)
                );
                tradeTaxes.add(tradeTax);

            }
            return tradeTaxes;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return List.of();
    }
}
