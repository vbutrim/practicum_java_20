/**
 * @author butrim
 *
 * singleton
 */
public abstract class Managers {

    private static HistoryManager HISTORY_MANAGER;
    private static TaskManager TASK_MANAGER;

    public static HistoryManager historyManager() {
        if (HISTORY_MANAGER == null) {
            HISTORY_MANAGER = new HistoryManager.BasedOnCustomLinkedListHistoryManager();
        }
        return HISTORY_MANAGER;
    }

    public static TaskManager taskManager() {
        if (TASK_MANAGER == null) {
            TASK_MANAGER = new TaskManager(historyManager());
        }
        return TASK_MANAGER;
    }
}
