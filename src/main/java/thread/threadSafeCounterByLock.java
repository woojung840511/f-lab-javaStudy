package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class threadSafeCounterByLock implements Countable {

    private int count = 0;
    private static final int max = 100;
    private static final int min = 0;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition forIncrementer = lock.newCondition();
    private final Condition forDecrementer = lock.newCondition();

    @Override
    public void increment() {
        lock.lock();
        try {
            if (count >= (max * 0.8)) {
                try {
                    forDecrementer.signal();
                    forIncrementer.await();
//                    forDecrementer.signal();  // 문제코드 await() 상태에서 signal()을 호출하는 것은 의미가 없습니다
                } catch (InterruptedException ignored) {}
            } else if (count >= max) {
                throw new IllegalStateException("count is already at max");
            } else {
                count++;
                System.out.println("Current count : " + count);
            }

        } finally {
            lock.unlock();
        }
    }

    @Override
    public void decrement() {
        lock.lock();
        try {
            if (count <= min) {
                try {
                    forIncrementer.signal();
                    forDecrementer.await();
                } catch (InterruptedException ignored) {}
            } else {
                count--;
                forDecrementer.await();
                System.out.println("Current count : " + count);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getCount() {
        return count;
    }
}
