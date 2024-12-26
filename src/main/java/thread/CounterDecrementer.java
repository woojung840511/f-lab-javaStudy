package thread;

public class CounterDecrementer implements Runnable {

    private final Countable counter;
    private final int decrementStep;

    public CounterDecrementer(Countable counter, int decrementStep) {
        this.counter = counter;
        this.decrementStep = decrementStep;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Decrementing count by " + decrementStep);

            boolean maximumReached = false;
            for (int i = 0; i < decrementStep; i++) {
                try {
                    counter.decrement();
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
