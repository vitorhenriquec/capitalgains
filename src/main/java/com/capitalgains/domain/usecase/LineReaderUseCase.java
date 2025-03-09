package com.capitalgains.domain.usecase;

import com.capitalgains.domain.model.TradeOrder;
import com.capitalgains.domain.model.TradeTax;

import java.util.List;

public interface LineReaderUseCase {
    List<List<TradeTax>> processLines(String[] args);
}
