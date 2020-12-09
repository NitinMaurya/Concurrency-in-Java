package com.practice.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ReentrantWorker {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void producer() throws InterruptedException {
        lock.lock();
        System.out.println("Producer :: method");
        condition.await();
        System.out.println("Producer :: again");
        lock.unlock();
    }

    public void consumer() throws InterruptedException {
        lock.lock();
        Thread.sleep(2000);
        System.out.println("Consumer :: method");
        condition.signal();
        System.out.println("Consumer :: again");
        lock.unlock();
    }
}

public class ProducerConsumerWithReentrantLocking {
    public static void main(String[] args) {
        ReentrantWorker reentrantWorker = new ReentrantWorker();
        Thread t1 = new Thread(() -> {
            try {
                reentrantWorker.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                reentrantWorker.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
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
    }
}
