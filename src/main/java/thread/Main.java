package thread;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        try {
//            testSynchronize();
            testLock();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void testSynchronize() throws InterruptedException {
        threadSafeCounter counter = new threadSafeCounter();
        CounterIncrementer incrementer1 = new CounterIncrementer(counter, 1);
        CounterIncrementer incrementer2 = new CounterIncrementer(counter, 2);

        Thread incrementerThread1 = new Thread(incrementer1, "Incrementer 1");
        Thread incrementerThread2 = new Thread(incrementer2, "Incrementer 2");

        incrementerThread1.start();
        incrementerThread2.start();

        incrementerThread1.join();
        incrementerThread2.join();

        System.out.println("Final count: " + counter.getCount());
    }

    private static void testLock() throws InterruptedException {
        threadSafeCounterByLock counter = new threadSafeCounterByLock();

        Thread[] threads = {
            new Thread(new CounterIncrementer(counter, 1)),
            new Thread(new CounterIncrementer(counter, 2)),
            new Thread(new CounterIncrementer(counter, 3)),
            new Thread(new CounterIncrementer(counter, 4)),
            new Thread(new CounterIncrementer(counter, 5)),
            new Thread(new CounterDecrementer(counter, 1))
        };

//        for (Thread thread : threads) {
//            thread.start();
//            thread.join();
//        }

        // 모든 스레드를 먼저 시작
        for (Thread thread : threads) {
            thread.start();
        }

        // 모든 스레드가 종료될 때까지 대기
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Final count: " + counter.getCount());
    }

}
