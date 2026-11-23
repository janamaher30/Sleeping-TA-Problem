package threads;

import core.SharedState;
import core.SynchronizationManager;
import waitingroom.WaitingRoom;

public class TA extends Thread {
    private final int taId;
    private final WaitingRoom waitingRoom;
    private final SynchronizationManager synchronizationManager;
    private final SharedState sharedState;

    public TA(
            int taId,
            WaitingRoom waitingRoom,
            SynchronizationManager synchronizationManager,
            SharedState sharedState) {

        this.taId = taId;
        this.waitingRoom = waitingRoom;
        this.synchronizationManager = synchronizationManager;
        this.sharedState = sharedState;
    }

    @Override
    public void run() {
        // TODO: Implement TA behavior
    }

    public void serveStudent(Student student) {
        // TODO: Implement student service
    }

    public int getTaId() {
        return taId;
    }

    public void setTAStatus(SharedState.TAStatus status) {
        sharedState.updateTAStatus(taId, status);
    }
}

