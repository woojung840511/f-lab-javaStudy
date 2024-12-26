package thread;

public class CounterDecrementer implements Runnable {

    private final threadSafeCounter counter;
    private final int decrementStep;

    public CounterDecrementer(threadSafeCounter counter, int decrementStep) {
        this.counter = counter;
        this.decrementStep = decrementStep;
    }

    @Override
    public void run() {
        System.out.println("Decrementing count by " + decrementStep);
        for (int i = 0; i < decrementStep; i++) {
            counter.decrement();
        }
    }

}
