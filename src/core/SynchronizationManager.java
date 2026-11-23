package core;
import java.util.concurrent.Semaphore;

public class SynchronizationManager {
    private final Semaphore availableTAs;
    private final Semaphore waitingChairs;
    private final Semaphore mutex;

    public SynchronizationManager(int taCount, int chairCount) {
        availableTAs = new Semaphore(taCount);
        waitingChairs = new Semaphore(chairCount);
        mutex = new Semaphore(1);
    }

    public boolean tryAcquireTA() {
        // TODO: Try to acquire an available TA
        return false;
    }

    public void releaseTA() {
        // TODO: Release TA permit
    }

    public boolean tryAcquireChair() {
        // TODO: Try to acquire an available chair
        return false;
    }

    public void releaseChair() {
        // TODO: Release chair permit
    }

    public void acquireMutex() {
        // TODO: Acquire mutex
    }

    public void releaseMutex() {
        // TODO: Release mutex
    }

    public int getAvailableTAs() {
        return availableTAs.availablePermits();
    }

    public int getAvailableChairs() {
        return waitingChairs.availablePermits();
    }
}
