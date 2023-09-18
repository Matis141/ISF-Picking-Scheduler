

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class PickingSchedulerTest {

    @Test
    void shouldScheduleOrderToPickerWithLowestTime() {
        List<String> pickers = List.of("P1", "P2");

        LocalTime pickingStartTime = LocalTime.of(9,0);
        LocalTime pickingEndTime = LocalTime.of(10, 30);

        Order order1 =
                new Order("order1", new BigDecimal("2.5"),
                        Duration.ofMinutes(10), pickingStartTime.plusMinutes(15));
        Order order2 = new Order("order2", new BigDecimal("2.5"),
                Duration.ofMinutes(15), pickingStartTime.plusMinutes(20));
        Order order3 = new Order("order3", new BigDecimal("3.5"), Duration.ofMinutes(30),
                pickingStartTime.plusMinutes(40));
        List<Order> orders = new ArrayList<>(List.of(order1, order2, order3));
        Store store = new Store(pickers, pickingStartTime, pickingEndTime);

        PickingScheduler pickingScheduler = new PickingScheduler();
        Map<String, List<Order>> scheduledOrders = pickingScheduler.scheduleOrders(
                store, orders
        );

        Map<String, List<Order>> expectedOrders = Map.of(
                "P1", List.of(order1, order3),
                "P2", List.of(order2)
        );
        assertEquals(expectedOrders, scheduledOrders );
    }

    @Test
    void shouldDiscardOrderThatCouldNotBeCompletedOnTime() {
        List<String> pickers = List.of("P1", "P2");

        LocalTime pickingStartTime = LocalTime.of(9,0);
        LocalTime pickingEndTime = LocalTime.of(10, 30);

        Order order1 =
                new Order("order1", new BigDecimal("2.5"),
                        Duration.ofMinutes(10), pickingStartTime.plusMinutes(15));
        Order order2 = new Order("order2", new BigDecimal("2.5"),
                Duration.ofMinutes(15), pickingStartTime.plusMinutes(10));
        Order order3 = new Order("order3", new BigDecimal("3.5"), Duration.ofMinutes(30),
                pickingStartTime.plusMinutes(40));
        List<Order> orders = new ArrayList<>(List.of(order1, order2, order3));
        Store store = new Store(pickers, pickingStartTime, pickingEndTime);

        PickingScheduler pickingScheduler = new PickingScheduler();
        Map<String, List<Order>> scheduledOrders = pickingScheduler.scheduleOrders(
                store, orders
        );

        Map<String, List<Order>> expectedOrders = Map.of(
                "P1", List.of(order1),
                "P2", List.of(order3)
        );
        assertEquals(expectedOrders, scheduledOrders );
    }
}
