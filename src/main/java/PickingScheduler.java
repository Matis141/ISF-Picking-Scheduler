import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class PickingScheduler implements ISFPickingScheduleAlgorithm {

    private void sortOrdersByCompleteBy(List<Order> orderList) {
        Collections.sort(orderList);
    }

    public Map<String, List<Order>> scheduleOrders(Store store,
                                                   List<Order> orderList) {
        sortOrdersByCompleteBy(orderList);
        //System.out.println(orderList);
        Map<String, List<Order>> ordersByPickerMap = this.createOrdersByPickerMap(store.getPickers());
        Queue<Order> orders = new LinkedList<>(orderList);

        while (!orders.isEmpty()) {
            Order order = orders.remove();
            String lowestTimePickerId = findPickerWithLowestTime(ordersByPickerMap);
            LocalTime pickerLocalTime = calculatePickerLocalTime(lowestTimePickerId, ordersByPickerMap, store.getPickingStartTime());
            if (order.getCompleteBy().compareTo(pickerLocalTime.plus(order.getPickingTime())) >= 0 &&
                    pickerLocalTime.plus(order.getPickingTime()).compareTo(store.getPickingEndTime()) <= 0) {
                printPickingOperation(lowestTimePickerId, order.getOrderId(), pickerLocalTime);
                ordersByPickerMap.get(lowestTimePickerId).add(order);
            }/* else {
                System.out.printf("order discarded %s%n", order); //print what order was discarded
            }*/

        }

        return ordersByPickerMap;

    }

    private Map<String, List<Order>> createOrdersByPickerMap(List<String> pickers) {
        Map<String, List<Order>> pickerMap = new HashMap<>();
        pickers.forEach(
                picker -> {
                    pickerMap.put(picker, new ArrayList<>());
                });
        return pickerMap;
    }

    private void printPickingOperation(String pickerID, String orderId, LocalTime pickingStartTime) {
        System.out.printf("%s %s %s%n", pickerID, orderId, pickingStartTime);
    }

    private String findPickerWithLowestTime(Map<String, List<Order>> pickerMap) {
        String lowestTimePickerId = pickerMap.keySet().iterator().next();
        Duration lowestTime = sumOrderDuration(pickerMap.get(lowestTimePickerId));
        for (Map.Entry<String, List<Order>> entry : pickerMap.entrySet()) {
            if (sumOrderDuration(entry.getValue()).compareTo(lowestTime) < 0) {
                lowestTime = sumOrderDuration(entry.getValue());
                lowestTimePickerId = entry.getKey();
            }
        }
        return lowestTimePickerId;
    }

    private Duration sumOrderDuration(List<Order> orderList) {
        Duration sumDuration = Duration.ZERO;
        for (Order order : orderList) {
            sumDuration = sumDuration.plus(order.getPickingTime());
        }
        return sumDuration;
    }

    private LocalTime calculatePickerLocalTime(String pickerId, Map<String, List<Order>> pickerMap, LocalTime pickingStartTime) {
        return pickingStartTime.plus(sumOrderDuration(pickerMap.get(pickerId)));
    }

}
