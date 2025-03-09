package domain.usecase;

import com.capitalgains.domain.model.TradeTax;
import com.capitalgains.domain.usecase.LineReaderUseCase;
import com.capitalgains.domain.usecase.LineReaderUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineReaderUseCaseImplTest {
    private LineReaderUseCase lineReader;

    @BeforeEach
    public void setUp(){
        lineReader = new LineReaderUseCaseImpl();
    }

    @Test
    public void shouldProcessLinesWithValidInput() {
        String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}]\n" +
                "[{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 5000}]";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        List<List<TradeTax>> result = lineReader.processLines(new String[]{});

        assertEquals(2, result.size());
    }

    @Test
    public void testProcessLinesWithEmptyInput() throws Exception {
        String input = "";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        List<List<TradeTax>> result = lineReader.processLines(new String[]{});

        assertEquals(0, result.size());
    }

    @Test
    public void testProcessLinesWithInvalidJson() throws Exception {
        String input = "invalid json";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        List<List<TradeTax>> result = lineReader.processLines(new String[]{});

        assertEquals(0, result.size());
    }
}
