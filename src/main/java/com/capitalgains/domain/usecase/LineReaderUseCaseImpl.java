package com.capitalgains.domain.usecase;

import com.capitalgains.domain.model.TradeOrder;
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
    public List<List<TradeOrder>> processLines(String[] args) {
        List<List<TradeOrder>> tradeOrders = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = reader.readLine()) != null) {

                List<TradeOrder> tradeOrderLine = objectMapper.readValue(line, new TypeReference<List<TradeOrder>>() {});
                tradeOrders.add(tradeOrderLine);
            }
            return tradeOrders;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return List.of();
    }
}
