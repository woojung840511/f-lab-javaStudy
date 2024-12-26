package thread;

public class threadSafeCounter implements Countable {

    private int count = 0;
    private static final int max = 100;
    private static final int min = 0;

    @Override
    public synchronized void increment() {
        if (count >= max) {
            throw new IllegalStateException("count is already at max");
        }
        count++;
        System.out.println("Current count : " + count);
    }

    @Override
    public synchronized void decrement() {
        if (count <= min) {
            throw new IllegalStateException("count is already at min");
        }
        count--;
        System.out.println("Current count : " + count);
    }

    @Override
    public int getCount() {
        return count;
    }
}
