import core.SynchronizationManager;
import java.util.concurrent.atomic.AtomicInteger;

public class FlowTest {
    static AtomicInteger helpedNow = new AtomicInteger();
    static AtomicInteger maxHelped = new AtomicInteger();
    static AtomicInteger sitting = new AtomicInteger();
    static AtomicInteger maxSitting = new AtomicInteger();
    static AtomicInteger finished = new AtomicInteger();
    static AtomicInteger leftOnce = new AtomicInteger();
    static AtomicInteger sleptTimes = new AtomicInteger();

    static void max(AtomicInteger m, int v) {
        int old;
        while ((old = m.get()) < v && !m.compareAndSet(old, v)) { }
    }

    public static void main(String[] args) throws Exception {
        final int tas = 3, chairs = 2, students = 40;
        final SynchronizationManager sync = new SynchronizationManager(tas, chairs);

        Thread[] taThreads = new Thread[tas];
        for (int i = 0; i < tas; i++) {
            taThreads[i] = new Thread(() -> {
                try {
                    while (true) {
                        sleptTimes.incrementAndGet();
                        sync.waitForStudent();
                        sync.callStudent();
                        max(maxHelped, helpedNow.incrementAndGet());
                        Thread.sleep(20 + (int) (Math.random() * 20));
                        helpedNow.decrementAndGet();
                        sync.finishHelping();
                        sync.releaseTA();
                    }
                } catch (InterruptedException e) { }
            });
            taThreads[i].setDaemon(true);
            taThreads[i].start();
        }

        Thread[] st = new Thread[students];
        for (int i = 0; i < students; i++) {
            st[i] = new Thread(() -> {
                try {
                    boolean done = false;
                    while (!done) {
                        if (sync.tryAcquireTA()) {
                            // direct
                        } else if (sync.tryAcquireChair()) {
                            max(maxSitting, sitting.incrementAndGet());
                            sync.acquireTA();
                            sitting.decrementAndGet();
                            sync.releaseChair();
                        } else {
                            leftOnce.incrementAndGet();
                            Thread.sleep(30 + (int) (Math.random() * 50));
                            continue;
                        }
                        sync.signalStudentWaiting();
                        sync.waitForTurn();
                        sync.waitUntilHelped();
                        finished.incrementAndGet();
                        done = true;
                    }
                } catch (InterruptedException e) { }
            });
            st[i].start();
        }

        long start = System.currentTimeMillis();
        for (Thread t : st) {
            t.join(20000);
        }
        long took = System.currentTimeMillis() - start;
        Thread.sleep(100);
        System.out.println("finished=" + finished.get() + "/" + students
                + " took=" + took + "ms");
        System.out.println("maxHelpedAtOnce=" + maxHelped.get() + " (TAs=" + tas + ")");
        System.out.println("maxSittingAtOnce=" + maxSitting.get() + " (chairs=" + chairs + ")");
        System.out.println("timesLeftBecauseFull=" + leftOnce.get());
        System.out.println("availableTAs=" + sync.getAvailableTAs() + " availableChairs="
                + sync.getAvailableChairs());
        boolean ok = finished.get() == students && maxHelped.get() <= tas
                && maxSitting.get() <= chairs && sync.getAvailableTAs() == tas
                && sync.getAvailableChairs() == chairs;
        System.out.println(ok ? "PASS" : "FAIL");
        System.exit(ok ? 0 : 1);
    }
}
