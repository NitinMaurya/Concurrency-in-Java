package com.practice.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/*
*   Atomic Variables are used instead of synchronized methods or blocks
*   because it provides low level synchronization on the variable itself.
* */

public class AtomicVariableExample {

    static class Worker implements Runnable {
        private static final AtomicInteger counter = new AtomicInteger(0);

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.getAndIncrement();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Worker());
        Thread t2 = new Thread(new Worker());
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Counter :: " + Worker.counter);
    }
}
