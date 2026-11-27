package core;
import java.util.concurrent.Semaphore;

public class SynchronizationManager {
    private final int taCount;
    private final int chairCount;

    private volatile Semaphore availableTAs;
    private volatile Semaphore waitingChairs;
    private volatile Semaphore mutex;
    private volatile Semaphore studentsWaiting;
    private volatile Semaphore turn;
    private volatile Semaphore helpDone;

    public SynchronizationManager(int taCount, int chairCount) {
        this.taCount = taCount;
        this.chairCount = chairCount;
        createSemaphores();
    }

    private void createSemaphores() {
        availableTAs = new Semaphore(taCount, true);
        waitingChairs = new Semaphore(chairCount, true);
        mutex = new Semaphore(1, true);
        studentsWaiting = new Semaphore(0, true);
        turn = new Semaphore(0, true);
        helpDone = new Semaphore(0, true);
    }

    public boolean tryAcquireTA() {
        return availableTAs.tryAcquire();
    }

    public void acquireTA() throws InterruptedException {
        availableTAs.acquire();
    }

    public void releaseTA() {
        availableTAs.release();
    }

    public boolean tryAcquireChair() {
        return waitingChairs.tryAcquire();
    }

    public void releaseChair() {
        waitingChairs.release();
    }

    public void acquireMutex() {
        mutex.acquireUninterruptibly();
    }

    public void releaseMutex() {
        mutex.release();
    }

    public void signalStudentWaiting() {
        studentsWaiting.release();
    }

    public void waitForStudent() throws InterruptedException {
        studentsWaiting.acquire();
    }

    public void callStudent() {
        turn.release();
    }

    public void waitForTurn() throws InterruptedException {
        turn.acquire();
    }

    public void finishHelping() {
        helpDone.release();
    }

    public void waitUntilHelped() throws InterruptedException {
        helpDone.acquire();
    }

    public int getAvailableTAs() {
        return availableTAs.availablePermits();
    }

    public int getAvailableChairs() {
        return waitingChairs.availablePermits();
    }

    public void reset() {
        createSemaphores();
    }
}
