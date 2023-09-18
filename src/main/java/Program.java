
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.util.List;

public class Program {

    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        JSONReader jsonReader = new JSONReader(objectMapper);

        Store store = jsonReader.fileToStore(args[0]);
        List<Order> orderList = jsonReader.fileToListOfOrders(args[1]);

        runAlgorithm(new PickingScheduler(), store, orderList);

    }

    private static void runAlgorithm(ISFPickingScheduleAlgorithm pickingScheduleAlgorithm, Store store, List<Order> orderList) {
        pickingScheduleAlgorithm.scheduleOrders(store, orderList);
    }

}
