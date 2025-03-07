package infrastructure.cli;

import com.capitalgains.infrastructure.cli.Main;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static com.capitalgains.infrastructure.util.FormatConverter.toBigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    public void shouldCalculateWithNoTaxAtAll(){
        String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 100}," +
                "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 50},"+
                "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 50}]\n";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        var result = Main.calculateTax(new String[]{});

        assertEquals( 1, result.size());
        assertEquals(3, result.get(0).size());
        assertEquals(toBigDecimal(0), result.get(0).get(0).tax());
        assertEquals(toBigDecimal(0), result.get(0).get(1).tax());
        assertEquals(toBigDecimal(0), result.get(0).get(2).tax());
    }

    @Test
    public void shouldCalculateWithProfitAndNoLoss(){
        String input = "[{\"operation\":\"buy\", \"unit-cost\":10, \"quantity\": 10000}," +
                "{\"operation\":\"sell\", \"unit-cost\":20, \"quantity\": 5000},"+
                "{\"operation\":\"sell\", \"unit-cost\":5, \"quantity\": 5000}]\n";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        var result = Main.calculateTax(new String[]{});

        assertEquals( 1, result.size());
        assertEquals(3, result.get(0).size());
        assertEquals(toBigDecimal(0), result.get(0).get(0).tax());
        assertEquals(toBigDecimal(10000), result.get(0).get(1).tax());
        assertEquals(toBigDecimal(0), result.get(0).get(2).tax());
    }

    @Test
    public void shouldCalculateWithTwoLines(){
        String input = """
                [{"operation":"buy", "unit-cost":10.00, "quantity": 100},\
                {"operation":"sell", "unit-cost":15.00, "quantity": 50},\
                {"operation":"sell", "unit-cost":15.00, "quantity": 50}]
                [{"operation":"buy", "unit-cost":10, "quantity": 10000},\
                {"operation":"sell", "unit-cost":20, "quantity": 5000},\
                {"operation":"sell", "unit-cost":5, "quantity": 5000}]
                """;

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        var result = Main.calculateTax(new String[]{});

        assertEquals( 2, result.size());

        assertEquals(3, result.get(0).size());
        assertEquals(toBigDecimal(0), result.get(0).get(0).tax());
        assertEquals(toBigDecimal(0), result.get(0).get(1).tax());
        assertEquals(toBigDecimal(0), result.get(0).get(2).tax());

        assertEquals(3, result.get(1).size());
        assertEquals(toBigDecimal(0), result.get(1).get(0).tax());
        assertEquals(toBigDecimal(10000), result.get(1).get(1).tax());
        assertEquals(toBigDecimal(0), result.get(1).get(2).tax());
    }

    @Test
    public void shouldCalculateWithLossFollowedByProfit(){
        String input = "[{\"operation\":\"buy\", \"unit-cost\":10, \"quantity\": 10000}," +
                "{\"operation\":\"sell\", \"unit-cost\":5, \"quantity\": 5000},"+
                "{\"operation\":\"sell\", \"unit-cost\":20, \"quantity\": 3000}]\n";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        var result = Main.calculateTax(new String[]{});

        assertEquals( 1, result.size());
        assertEquals(3, result.get(0).size());
        assertEquals(toBigDecimal(0), result.get(0).get(0).tax());
        assertEquals(toBigDecimal(0), result.get(0).get(1).tax());
        assertEquals(toBigDecimal(1000), result.get(0).get(2).tax());
    }

    @Test
    public void shouldCalculateWithNoProfitAndNoLoss(){
        String input = "[{\"operation\":\"buy\", \"unit-cost\":10, \"quantity\": 10000}," +
                "{\"operation\":\"buy\", \"unit-cost\":25, \"quantity\": 5000},"+
                "{\"operation\":\"sell\", \"unit-cost\":15, \"quantity\": 10000}]\n";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        var result = Main.calculateTax(new String[]{});

        assertEquals( 1, result.size());
        assertEquals(3, result.get(0).size());
        assertEquals(toBigDecimal(0), result.get(0).get(0).tax());
        assertEquals(toBigDecimal(0), result.get(0).get(1).tax());
        assertEquals(toBigDecimal(0), result.get(0).get(2).tax());
    }

    @Test
    public void shouldCalculateWithNoProfitAndNoLossFollowedByProfit(){
        String input = "[{\"operation\":\"buy\", \"unit-cost\":10, \"quantity\": 10000}," +
                "{\"operation\":\"buy\", \"unit-cost\":25, \"quantity\": 5000},"+
                "{\"operation\":\"sell\", \"unit-cost\":15, \"quantity\": 10000}," +
                "{\"operation\":\"sell\", \"unit-cost\":25, \"quantity\": 5000}]\n";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        var result = Main.calculateTax(new String[]{});

        assertEquals( 1, result.size());
        assertEquals(4, result.get(0).size());
        assertEquals(toBigDecimal(0), result.get(0).get(0).tax());
        assertEquals(toBigDecimal(0), result.get(0).get(1).tax());
        assertEquals(toBigDecimal(0), result.get(0).get(2).tax());
        assertEquals(toBigDecimal(10000), result.get(0).get(3).tax());
    }

    @Test
    public void shouldCalculateWithTwoProfitAndTwoLoss(){
        String input = "[{\"operation\":\"buy\", \"unit-cost\":10, \"quantity\": 10000}," +
                "{\"operation\":\"sell\", \"unit-cost\":2, \"quantity\": 5000}," +
                "{\"operation\":\"sell\", \"unit-cost\":20, \"quantity\": 2000}," +
                "{\"operation\":\"sell\", \"unit-cost\":20, \"quantity\": 2000}," +
                "{\"operation\":\"sell\", \"unit-cost\":25, \"quantity\": 1000}]\n";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        var result = Main.calculateTax(new String[]{});

        assertEquals( 1, result.size());
        assertEquals(5, result.get(0).size());
        assertEquals(toBigDecimal(0), result.get(0).get(0).tax());
        assertEquals(toBigDecimal(0), result.get(0).get(1).tax());
        assertEquals(toBigDecimal(0), result.get(0).get(2).tax());
        assertEquals(toBigDecimal(0), result.get(0).get(3).tax());
        assertEquals(toBigDecimal(3000), result.get(0).get(4).tax());
    }

    @Test
    public void shouldCalculateWithFiveProfitAndTwoLossAndTwoBuy(){
        String input = "[{\"operation\":\"buy\", \"unit-cost\":10, \"quantity\": 10000}," +
                "{\"operation\":\"sell\", \"unit-cost\":2, \"quantity\": 5000}," +
                "{\"operation\":\"sell\", \"unit-cost\":20, \"quantity\": 2000}," +
                "{\"operation\":\"sell\", \"unit-cost\":20, \"quantity\": 2000}," +
                "{\"operation\":\"sell\", \"unit-cost\":25, \"quantity\": 1000}," +
                "{\"operation\":\"buy\", \"unit-cost\":20, \"quantity\": 10000}," +
                "{\"operation\":\"sell\", \"unit-cost\":15, \"quantity\": 5000}," +
                "{\"operation\":\"sell\", \"unit-cost\":30, \"quantity\": 4350}," +
                "{\"operation\":\"sell\", \"unit-cost\":30, \"quantity\": 650}]\n";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        var result = Main.calculateTax(new String[]{});

        assertEquals( 1, result.size());
        assertEquals(9, result.get(0).size());
        assertEquals(toBigDecimal(0), result.get(0).get(0).tax());
        assertEquals(toBigDecimal(0), result.get(0).get(1).tax());
        assertEquals(toBigDecimal(0), result.get(0).get(2).tax());
        assertEquals(toBigDecimal(0), result.get(0).get(3).tax());
        assertEquals(toBigDecimal(3000), result.get(0).get(4).tax());
        assertEquals(toBigDecimal(0), result.get(0).get(5).tax());
        assertEquals(toBigDecimal(0), result.get(0).get(6).tax());
        assertEquals(toBigDecimal(3700), result.get(0).get(7).tax());
        assertEquals(toBigDecimal(0), result.get(0).get(8).tax());
    }
}
