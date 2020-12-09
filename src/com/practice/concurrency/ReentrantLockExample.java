package com.practice.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
*   Advantage of Reentrant lock over synchronized locking way is that
*   it gives additional features like fairness property and
*   we can also put lock and unlock statement in different methods
*   but with synchronized the lock and unlock will happen in the same method.
*
*   As a best practice, we should always use try-finally block
*   in order to ensure that the lock will unlock even if any exception occurred after locking.
*
* */

public class ReentrantLockExample {
    private static int counter = 0;
    private static Lock lock = new ReentrantLock();

    private static void increment() {
        lock.lock();
        try {
            for (int i = 0 ; i < 10000; i++) {
                counter++;
            }
        } finally {
            lock.unlock();
        }
    }

//    Example that we can unlock in different method
//    private static void add() {
//        lock.unlock();
//    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            increment();
        });

        Thread t2 = new Thread(() -> {
            increment();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(
                "Counter :: " + counter
        );

    }
}
