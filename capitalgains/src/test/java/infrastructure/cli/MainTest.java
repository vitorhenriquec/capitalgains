package infrastructure.cli;

import com.capitalgains.infrastructure.cli.Main;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

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
        assertEquals( 0, result.get(0).get(0).tax());
        assertEquals(0, result.get(0).get(1).tax());
        assertEquals(0, result.get(0).get(2).tax());
    }

    @Test
    public void shouldCalculateWithProfitAndNoDebt(){
        String input = "[{\"operation\":\"buy\", \"unit-cost\":10, \"quantity\": 10000}," +
                "{\"operation\":\"sell\", \"unit-cost\":20, \"quantity\": 5000},"+
                "{\"operation\":\"sell\", \"unit-cost\":5, \"quantity\": 5000}]\n";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        var result = Main.calculateTax(new String[]{});

        assertEquals( 1, result.size());
        assertEquals(3, result.get(0).size());
        assertEquals( 0, result.get(0).get(0).tax());
        assertEquals(10000, result.get(0).get(1).tax());
        assertEquals(0, result.get(0).get(2).tax());
    }
}
