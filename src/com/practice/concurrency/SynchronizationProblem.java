package com.practice.concurrency;

/*
*   The Threads shares the heap memory between them but has their own stack memory for
*   local variables, method arguments and methods calls.
*
*   The counter value is indeterminate hence this is a synchronization problem.
* */


public class SynchronizationProblem {
    private static int counter = 0;

    public static void process() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter++;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter++;
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

        System.out.println("Counter=" + counter);

    }

    public static void main(String[] args) {
        process();
    }
}
