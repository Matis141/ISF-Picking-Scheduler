import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public class Order implements Comparable<Order>{

    private String orderId;
    private BigDecimal orderValue;
    private Duration pickingTime;
    private LocalTime completeBy;

    public Order() {
    }

    public Order(String orderId, BigDecimal orderValue, Duration pickingTime, LocalTime completeBy) {
        this.orderId = orderId;
        this.orderValue = orderValue;
        this.pickingTime = pickingTime;
        this.completeBy = completeBy;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(BigDecimal orderValue) {
        this.orderValue = orderValue;
    }

    public Duration getPickingTime() {
        return pickingTime;
    }

    public void setPickingTime(Duration pickingTime) {
        this.pickingTime = pickingTime;
    }

    public LocalTime getCompleteBy() {
        return completeBy;
    }

    public void setCompleteBy(LocalTime completeBy) {
        this.completeBy = completeBy;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderValue=" + orderValue +
                ", pickingTime='" + pickingTime + '\'' +
                ", completeBy='" + completeBy + '\'' +
                '}';
    }

    @Override
    public int compareTo(Order o) {
        return this.completeBy.plus(pickingTime).compareTo(o.completeBy.plus(o.pickingTime));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (!orderId.equals(order.orderId)) return false;
        if (!orderValue.equals(order.orderValue)) return false;
        if (!pickingTime.equals(order.pickingTime)) return false;
        return completeBy.equals(order.completeBy);
    }

    @Override
    public int hashCode() {
        int result = orderId.hashCode();
        result = 31 * result + orderValue.hashCode();
        result = 31 * result + pickingTime.hashCode();
        result = 31 * result + completeBy.hashCode();
        return result;
    }
}
