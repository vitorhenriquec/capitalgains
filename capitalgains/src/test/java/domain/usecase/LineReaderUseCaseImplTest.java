package domain.usecase;

import com.capitalgains.domain.model.TradeOrder;
import com.capitalgains.domain.usecase.LineReaderUseCase;
import com.capitalgains.domain.usecase.LineReaderUseCaseImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

public class LineReaderUseCaseImplTest {
    private LineReaderUseCase lineReader;
    private ObjectMapper mapper;

    @BeforeEach
    public void setUp(){
        lineReader = new LineReaderUseCaseImpl();
        mapper = new ObjectMapper();
    }

    @Test
    public void shouldProcessLinesWithValidInput() {
        String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}]\n" +
                "[{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 5000}]";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        List<List<TradeOrder>> result = lineReader.processLines(new String[]{});

        assertEquals(2, result.size());
        assertEquals("buy", result.get(0).get(0).operation().getDescription());
        assertEquals(10.00, result.get(0).get(0).unitCost());
        assertEquals(10000, result.get(0).get(0).quantity());
        assertEquals("sell", result.get(1).get(0).operation().getDescription());
        assertEquals(20.00, result.get(1).get(0).unitCost());
        assertEquals(5000, result.get(1).get(0).quantity());
    }

    @Test
    public void testProcessLinesWithEmptyInput() throws Exception {
        // Simula uma entrada vazia
        String input = "";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Executa o método a ser testado
        List<List<TradeOrder>> result = lineReader.processLines(new String[]{});

        // Verifica que o resultado é uma lista vazia
        assertEquals(0, result.size());
    }

    @Test
    public void testProcessLinesWithInvalidJson() throws Exception {
        // Simula uma entrada com JSON inválido
        String input = "invalid json";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Executa o método a ser testado
        List<List<TradeOrder>> result = lineReader.processLines(new String[]{});

        // Verifica que o resultado é uma lista vazia
        assertEquals(0, result.size());
    }
}
