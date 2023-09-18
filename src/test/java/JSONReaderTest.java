import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JSONReaderTest {

    @Test
    void shouldDeserializeStore() throws IOException {
        Store expectedStore = new Store(
                List.of("P1"),
                LocalTime.of(9,0),
                LocalTime.of(10, 0)
        );
        String pathToFile =  Thread.currentThread().getContextClassLoader().getResource("store.json")
                .getPath();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        JSONReader jsonReader = new JSONReader(objectMapper);

        Store actualStore = jsonReader.fileToStore(pathToFile);

        assertEquals(expectedStore, actualStore);

    }
}
