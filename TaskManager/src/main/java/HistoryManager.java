import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author butrim
 */
public class HistoryManager {
    private final List<Integer> historyTaskIds = new ArrayList<>();

    public HistoryManager() {
    }

    public void add(Task task) {
        // check on space
        this.historyTaskIds.add(task.getId());
    }

    public List<Integer> getHistoryIds() {
        return Collections.unmodifiableList(historyTaskIds);
    }
}
