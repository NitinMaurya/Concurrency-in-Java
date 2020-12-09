package com.practice.concurrency;

class Process {

    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer :: runnning....");
            wait();
            System.out.println("Producer :: back to running....");
        }
    }

    public void consume() throws InterruptedException {
        synchronized (this) {
            Thread.sleep(1000); // to allow producer to start first and then wait and release the
                                    // lock for consumer
            System.out.println("Consumer :: running....");
            notify();
            System.out.println("Consumer :: finished");
            Thread.sleep(5000); // to ensure consumer will release the lock for waiting producer
                                    // once it finishes its complete method not immediately
                                    // when the notify() is called.
        }
    }
}

public class WaitAndNotifyExample {
    public static void main(String[] args) {
        Process process = new Process();
        Thread t1 = new Thread(() -> {
            try {
                process.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                process.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}
