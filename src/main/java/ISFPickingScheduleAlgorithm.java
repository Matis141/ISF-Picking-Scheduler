import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface ISFPickingScheduleAlgorithm {
    Map<String, List<Order>> scheduleOrders(Store store,
                                                   List<Order> orderList);
}
