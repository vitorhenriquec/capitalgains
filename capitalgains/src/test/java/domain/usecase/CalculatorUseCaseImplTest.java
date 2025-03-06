package domain.usecase;

import com.capitalgains.domain.model.CalculatorDatabase;
import com.capitalgains.domain.model.OperationType;
import com.capitalgains.domain.model.TradeOrder;
import com.capitalgains.domain.model.TradeTax;
import com.capitalgains.domain.usecase.CalculatorUseCaseImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorUseCaseImplTest {

    @Test
    void testCalculateBuyTradeTax() {
        TradeOrder tradeOrder = new TradeOrder(OperationType.BUY, 10.00, 100);
        TradeTax expectedTradeTax = new TradeTax(0);

        CalculatorDatabase database = new CalculatorDatabase();
        CalculatorUseCaseImpl calculatorUseCase = new CalculatorUseCaseImpl(database);

        TradeTax result = calculatorUseCase.calculateTradeTax(tradeOrder, tradeOrder.operation().getOperationTaxCalculator());

        assertEquals(expectedTradeTax.tax(), result.tax());
        assertEquals(database.getProfit(), 0);
        assertEquals(database.getAmountActualShares(), 100);
        assertEquals(database.getMeanBuyShareValue(), 10);
    }

    @Test
    void testCalculateSellWithNoTax() {
        TradeOrder tradeOrder = new TradeOrder(OperationType.SELL, 20.00, 50);
        TradeTax expectedTradeTax = new TradeTax(0.0);

        CalculatorDatabase database = new CalculatorDatabase();
        database.setAmountActualShares(10000);
        database.setMeanBuyShareValue(10);
        CalculatorUseCaseImpl calculatorUseCase = new CalculatorUseCaseImpl(database);

        TradeTax result = calculatorUseCase.calculateTradeTax(tradeOrder, tradeOrder.operation().getOperationTaxCalculator());

        assertEquals(expectedTradeTax.tax(), result.tax());
        assertEquals(database.getProfit(), 500);
        assertEquals(database.getAmountActualShares(), 9950);
        assertEquals(database.getMeanBuyShareValue(), 10);
    }

    @Test
    void testCalculateSellWithNoTaxAndNoProfit() {
        TradeOrder tradeOrder = new TradeOrder(OperationType.SELL, 10, 5000);
        TradeTax expectedTradeTax = new TradeTax(0.0);

        CalculatorDatabase database = new CalculatorDatabase();
        database.setAmountActualShares(10000);
        database.setMeanBuyShareValue(10);
        CalculatorUseCaseImpl calculatorUseCase = new CalculatorUseCaseImpl(database);

        TradeTax result = calculatorUseCase.calculateTradeTax(tradeOrder, tradeOrder.operation().getOperationTaxCalculator());

        assertEquals(expectedTradeTax.tax(), result.tax());
        assertEquals(database.getProfit(), 0);
        assertEquals(database.getAmountActualShares(), 5000);
        assertEquals(database.getMeanBuyShareValue(), 10);
    }

    @Test
    void testCalculateSellWithNoTaxAndDebt() {
        TradeOrder tradeOrder = new TradeOrder(OperationType.SELL, 5, 5000);
        TradeTax expectedTradeTax = new TradeTax(0.0);

        CalculatorDatabase database = new CalculatorDatabase();
        database.setAmountActualShares(10000);
        database.setMeanBuyShareValue(10);
        CalculatorUseCaseImpl calculatorUseCase = new CalculatorUseCaseImpl(database);

        TradeTax result = calculatorUseCase.calculateTradeTax(tradeOrder, tradeOrder.operation().getOperationTaxCalculator());

        assertEquals(expectedTradeTax.tax(), result.tax());
        assertEquals(database.getProfit(), -25000);
        assertEquals(database.getAmountActualShares(), 5000);
        assertEquals(database.getMeanBuyShareValue(), 10);
    }

    @Test
    void testCalculateSellWithSomeTax() {
        TradeOrder tradeOrder = new TradeOrder(OperationType.SELL, 15, 5000);
        TradeTax expectedTradeTax = new TradeTax(5000.0);

        CalculatorDatabase database = new CalculatorDatabase();
        database.setAmountActualShares(10000);
        database.setMeanBuyShareValue(10);
        CalculatorUseCaseImpl calculatorUseCase = new CalculatorUseCaseImpl(database);

        TradeTax result = calculatorUseCase.calculateTradeTax(tradeOrder, tradeOrder.operation().getOperationTaxCalculator());

        assertEquals(expectedTradeTax.tax(), result.tax());
        assertEquals(database.getProfit(), 25000);
        assertEquals(database.getAmountActualShares(), 5000);
        assertEquals(database.getMeanBuyShareValue(), 10);
    }
}
