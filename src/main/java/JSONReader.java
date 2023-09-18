import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONReader {
    private ObjectMapper objectMapper;

    public JSONReader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Order> fileToListOfOrders(String orderPath) throws IOException {
        File orderFile = new File(orderPath);
        return objectMapper.readValue(orderFile, new TypeReference<>() {});
    }

    public Store fileToStore(String storePath) throws IOException {
        File storeFile = new File(storePath);
        return objectMapper.readValue(storeFile, Store.class);
    }

}
