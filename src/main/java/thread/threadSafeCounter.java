package thread;

public class threadSafeCounter {

    private volatile int count = 0;
    private static final int max = 100;
    private static final int min = 0;

    public synchronized void increment() {
        if (count >= max) {
//            throw new IllegalStateException("count is already at max");
            System.out.println("count is already at max");
            return;
        }
        count++;
        System.out.println("Current count : " + count);
    }

    public synchronized void decrement() {
        if (count <= min) {
//            throw new IllegalStateException("count is already at min");
            System.out.println("count is already at min");
            return;
        }
        count--;
        System.out.println("Current count : " + count);
    }
}
