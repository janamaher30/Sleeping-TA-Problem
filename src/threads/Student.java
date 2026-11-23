package threads;

import core.SharedState;
import core.SynchronizationManager;
import waitingroom.WaitingRoom;

public class Student extends Thread {

    private final int studentId;
    private final WaitingRoom waitingRoom;
    private final SynchronizationManager synchronizationManager;
    private final SharedState sharedState;

    public Student(
            int studentId,
            WaitingRoom waitingRoom,
            SynchronizationManager synchronizationManager,
            SharedState sharedState) {

        this.studentId = studentId;
        this.waitingRoom = waitingRoom;
        this.synchronizationManager = synchronizationManager;
        this.sharedState = sharedState;
    }

    @Override
    public void run() {
        // TODO: Implement student behavior
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentStatus(SharedState.StudentStatus status) {
        sharedState.updateStudentStatus(studentId, status);
    }
}
