package com.practice.concurrency;

public class LockingLevelExample {
    private static int counterA = 0;
    private static int counterB = 0;

    // class level locking
    public synchronized static void incrementA() {
        counterA++;
    }

/*  Example of Class Level Locking

    public static void incrementA() {
        synchronized (App.class) {
            counterA++;
        }
    }
*/


    /*  Example of Object Level Locking

        public synchronized void incrementA() {
            counterA++;
        }

        public void incrementA() {
            synchronized (this) {
                counterA++;
            }
        }
    */


    public synchronized static void incrementB() {
        counterB++;
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
