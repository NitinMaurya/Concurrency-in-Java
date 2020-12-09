package com.practice.concurrency;

public class ThreadWait {
    public static void main(String[] args) throws InterruptedException {
        WorkerA workerA = new WorkerA();
        WorkerB workerB = new WorkerB();

        Thread t1 = new Thread(workerA);
        Thread t2 = new Thread(workerB);

        t1.start();
        t2.start();

        // Used join() method to tell the "MAIN" thread to wait until the "Thread t1" finished its execution
        t1.join();
        // Used join() method to tell the "MAIN" thread to wait until the "Thread t2" finished its execution
        t2.join();

        System.out.println("Finished all threads......");
    }
}
