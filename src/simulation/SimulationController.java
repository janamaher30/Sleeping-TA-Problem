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
    private boolean initialized = false;
    private boolean running = false;

    public SimulationController(
            int studentCount,
            int taCount,
            int waitingChairs) {
        if(studentCount<=0){
            throw new IllegalArgumentException(
                "Student count must be greater than zero."
            );
        }
        if(taCount<=0){
            throw new IllegalArgumentException(
                "TA count must be greater than zero."
            );
        }
        if(waitingChairs<0){
            throw new IllegalArgumentException(
                "Waiting chairs cannot be negative."
            );
        }
        this.studentCount = studentCount;
        this.taCount = taCount;
        this.waitingChairs = waitingChairs;
        students = new ArrayList<>();
        tas = new ArrayList<>();
    }

    public void initializeSimulation() {
        if(running){
            throw new IllegalStateException(
                "Cannot initialize while simulation is running."
            );
        }
        sharedState = new SharedState();
        synchronizationManager= new SynchronizationManager(taCount,waitingChairs);
        waitingRoom=new WaitingRoom(waitingChairs);
        students.clear();
        for(int i=1;i<=studentCount;i++){
            Student student=new Student(
                    i,waitingRoom,synchronizationManager,sharedState
            );
            students.add(student);
            sharedState.updateStudentStatus(i, SharedState.StudentStatus.ARRIVED);
        }
        tas.clear();
        for(int i=1;i<=taCount;i++){
            TA ta=new TA(
                    i,waitingRoom,synchronizationManager,sharedState
            );
            tas.add(ta);
            sharedState.updateTAStatus(i, SharedState.TAStatus.SLEEPING);
        }
        initialized=true;
    }

    public void startSimulation() {
        if(!initialized){
            throw new IllegalStateException(
                "Simulation must be initialized before starting."
            );
        }
        if(running){
            throw new IllegalStateException(
                 "Simulation is already running."
            );
        }
        running=true;
        for(TA ta : tas){
            ta.start();
        }
        for(Student student:students){
            student.start();
        }
    }

    public void stopSimulation() {
        // TODO: Stop simulation safely
    }

    public void resetSimulation() {
        if(running){
            throw new IllegalStateException(
                "Cannot reset while simulation is running."
            );
        }
       initializeSimulation();
    }

    public SharedState getSharedState() {
        return sharedState;
    }

    public WaitingRoom getWaitingRoom() {
        return waitingRoom;
    }
    public boolean isInitialized() {
        return initialized;
    }

    public boolean isRunning() {
        return running;
    }
}
