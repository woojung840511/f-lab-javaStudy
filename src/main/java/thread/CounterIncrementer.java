package thread;

public class CounterIncrementer implements Runnable {

    private final Countable counter;
    private final int incrementStep;

    public CounterIncrementer(Countable counter, int incrementStep) {
        this.counter = counter;
        this.incrementStep = incrementStep;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Incrementing count by " + incrementStep);

            boolean maximumReached = false;
            for (int i = 0; i < incrementStep; i++) {
                try {
                    counter.increment();
                } catch (IllegalStateException e) {
                    maximumReached = true;
                    break;
                }
            }

            if (maximumReached) {
                break;
            }

            Thread.yield();
        }
    }

}
