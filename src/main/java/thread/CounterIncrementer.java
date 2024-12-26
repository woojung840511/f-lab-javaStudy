package thread;

public class CounterIncrementer implements Runnable {

    private final threadSafeCounter counter;
    private final int incrementStep;

    public CounterIncrementer(threadSafeCounter counter, int incrementStep) {
        this.counter = counter;
        this.incrementStep = incrementStep;
    }

    @Override
    public void run() {
        System.out.println("Incrementing count by " + incrementStep);
        for (int i = 0; i < incrementStep; i++) {
            counter.increment();
        }
    }

}
