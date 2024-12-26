package thread;

public class Main {

    public static void main(String[] args) {
        try {
            threadSafeCounter counter = new threadSafeCounter();
            CounterIncrementer incrementer = new CounterIncrementer(counter, 10);
            CounterDecrementer decrementer = new CounterDecrementer(counter, 5);

            Thread incrementerThread = new Thread(incrementer);
            Thread decrementerThread = new Thread(decrementer);

            incrementerThread.start();
            decrementerThread.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
