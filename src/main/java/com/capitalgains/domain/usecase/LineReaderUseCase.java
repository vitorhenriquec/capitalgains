package com.capitalgains.domain.usecase;

import com.capitalgains.domain.model.TradeOrder;

import java.util.List;

public interface LineReaderUseCase {
    List<List<TradeOrder>> processLines(String[] args);
}
