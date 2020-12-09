package com.practice.concurrency;

public class LockingWithCustomObjects {
    private static int counterA = 0;
    private static int counterB = 0;
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void incrementA() {
        synchronized (lock1) {
            counterA++;
        }
    }
    public static void incrementB() {
        synchronized (lock2) {
            counterB++;
        }
    }

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
