package waitingroom;

import java.util.LinkedList;
import java.util.Queue;
import threads.Student;

public class WaitingRoom {
    private final int totalChairs;
    private final Queue<Student> waitingStudents;

    public WaitingRoom(int totalChairs) {
        this.totalChairs = totalChairs;
        this.waitingStudents = new LinkedList<>();
    }

    public synchronized boolean tryAddStudent(Student student) {
        // TODO: Add student if a chair is available
        return false;
    }

    public synchronized Student takeNextStudent() {
        // TODO: Get next waiting student
        return null;
    }

    public synchronized boolean removeStudent(Student student) {
        // TODO: Remove student from waiting queue
        return false;
    }

    public synchronized int getWaitingCount() {
        return waitingStudents.size();
    }

    public synchronized int getAvailableChairs() {
        return totalChairs - waitingStudents.size();
    }

    public int getTotalChairs() {
        return totalChairs;
    }
}
