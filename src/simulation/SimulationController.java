package simulation;

import core.SharedState;
import core.SynchronizationManager;
import threads.Student;
import threads.TA;
import waitingroom.WaitingRoom;
import java.util.ArrayList;
import java.util.List;

public class SimulationController {
    private final int studentCount;
    private final int taCount;
    private final int waitingChairs;
    private WaitingRoom waitingRoom;
    private SynchronizationManager synchronizationManager;
    private SharedState sharedState;
    private List<Student> students;
    private List<TA> tas;

    public SimulationController(
            int studentCount,
            int taCount,
            int waitingChairs) {
        this.studentCount = studentCount;
        this.taCount = taCount;
        this.waitingChairs = waitingChairs;
        students = new ArrayList<>();
        tas = new ArrayList<>();
    }

    public void initializeSimulation() {
        // TODO: Initialize shared objects and threads
    }

    public void startSimulation() {
        // TODO: Start TA and student threads
    }

    public void stopSimulation() {
        // TODO: Stop simulation safely
    }

    public void resetSimulation() {
        // TODO: Reset simulation data
    }

    public SharedState getSharedState() {
        return sharedState;
    }

    public WaitingRoom getWaitingRoom() {
        return waitingRoom;
    }
}
