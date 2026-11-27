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
        studentStatuses.put(studentId, status);
    }

    public void updateTAStatus(int taId, TAStatus status) {
        taStatuses.put(taId, status);
    }

    public Map<Integer, StudentStatus> getStudentStatuses() {
        return new HashMap<>(studentStatuses);
    }

    public Map<Integer, TAStatus> getTAStatuses() {
        return new HashMap<>(taStatuses);
    }

    public Map<String, Integer> getStatistics() {
        int workingTAs = 0;
        int sleepingTAs = 0;
        int waitingStudents = 0;
        int studentsReturningLater = 0;
        for (TAStatus status : taStatuses.values()) {
            if (status == TAStatus.WORKING) {
                workingTAs++;
            } else if (status == TAStatus.SLEEPING) {
                sleepingTAs++;
            }
        }
        for (StudentStatus status : studentStatuses.values()) {
            if (status == StudentStatus.WAITING) {
                waitingStudents++;
            } else if (status == StudentStatus.RETURNING_LATER) {
                studentsReturningLater++;
            }
        }
        Map<String, Integer> statistics = new HashMap<>();
        statistics.put("workingTAs", workingTAs);
        statistics.put("sleepingTAs", sleepingTAs);
        statistics.put("waitingStudents", waitingStudents);
        statistics.put("studentsReturningLater", studentsReturningLater);
        return statistics;
    }
}