package com.practice.concurrency;
/*
*   Counter A and Counter B are independent of each other yet they cannot be executed independently
*   because object has only single monitor lock.
*
*   On calling incrementA() by Thread t1 it acquires the lock
*   and now Thread t2 has to wait for the lock to be released by t1 even if it is independent of t1.
*
* */


public class SynchronizedProblem {
    private static int counterA = 0;
    private static int counterB = 0;

    public synchronized static void incrementA() {
        counterA++;
    }

    public synchronized static void incrementB() {
        counterB++;
    }

/*
    solution

    public static void incrementB() {
        counterB++;
    }

    */

    public static void process() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                incrementA();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                incrementB();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("CounterA=" + counterA);
        System.out.println("CounterB=" + counterB);

    }

    public static void main(String[] args) {
        process();
    }

}
