import java.time.LocalTime;
import java.util.List;

public class Store {

    private List<String> pickers;
    private LocalTime pickingStartTime;
    private LocalTime pickingEndTime;

    public Store() {
    }

    public Store(List<String> pickers, LocalTime pickingStartTime, LocalTime pickingEndTime) {
        this.pickers = pickers;
        this.pickingStartTime = pickingStartTime;
        this.pickingEndTime = pickingEndTime;
    }

    public List<String> getPickers() {
        return pickers;
    }

    public void setPickers(List<String> pickers) {
        this.pickers = pickers;
    }

    public LocalTime getPickingStartTime() {
        return pickingStartTime;
    }

    public void setPickingStartTime(LocalTime pickingStartTime) {
        this.pickingStartTime = pickingStartTime;
    }

    public LocalTime getPickingEndTime() {
        return pickingEndTime;
    }

    public void setPickingEndTime(LocalTime pickingEndTime) {
        this.pickingEndTime = pickingEndTime;
    }

    @Override
    public String toString() {
        return "Store{" +
                "pickers=" + pickers +
                ", pickingStartTime=" + pickingStartTime +
                ", pickingEndTime=" + pickingEndTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Store store = (Store) o;

        if (pickers != null ? !pickers.equals(store.pickers) : store.pickers != null) return false;
        if (pickingStartTime != null ? !pickingStartTime.equals(store.pickingStartTime) : store.pickingStartTime != null)
            return false;
        return pickingEndTime != null ? pickingEndTime.equals(store.pickingEndTime) : store.pickingEndTime == null;
    }

    @Override
    public int hashCode() {
        int result = pickers != null ? pickers.hashCode() : 0;
        result = 31 * result + (pickingStartTime != null ? pickingStartTime.hashCode() : 0);
        result = 31 * result + (pickingEndTime != null ? pickingEndTime.hashCode() : 0);
        return result;
    }
}
