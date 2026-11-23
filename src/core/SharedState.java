package core;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class SharedState {

    public enum StudentStatus {
        ARRIVED,
        WAITING,
        GETTING_HELP,
        RETURNING_LATER,
        FINISHED
    }

    public enum TAStatus {
        SLEEPING,
        WORKING
    }

    private final Map<Integer, StudentStatus> studentStatuses;
    private final Map<Integer, TAStatus> taStatuses;

    public SharedState() {
        studentStatuses = new ConcurrentHashMap<>();
        taStatuses = new ConcurrentHashMap<>();
    }

    public void updateStudentStatus(int studentId, StudentStatus status) {
        // TODO: Update student status
    }

    public void updateTAStatus(int taId, TAStatus status) {
        // TODO: Update TA status
    }

    public Map<Integer, StudentStatus> getStudentStatuses() {
        return new HashMap<>(studentStatuses);
    }

    public Map<Integer, TAStatus> getTAStatuses() {
        return new HashMap<>(taStatuses);
    }

    public Map<String, Integer> getStatistics() {
        // TODO: Calculate simulation statistics
        return new HashMap<>();
    }
}
