package com.practice.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LiveLock {
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        LiveLock liveLock = new LiveLock();
        new Thread(liveLock::workerA, "workerA").start();
        new Thread(liveLock::workerB, "workerB").start();
    }

    public void workerA () {
        while (true) {
            try {
                lock1.tryLock(1000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Worker A acquires Lock 1");
            System.out.println("Worker A try to acquire Lock 2");

            if (lock2.tryLock()) {
                System.out.println("Worker A acquires Lock 2");
                lock2.unlock();
            } else {
                System.out.println("Worker A cannot acquire Lock 2");
                continue;
            }
            break;
        }
        lock1.unlock();
        lock2.unlock();
    }

    public void workerB () {
        while (true) {
            try {
                lock2.tryLock(1000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Worker B acquires Lock 2");
            System.out.println("Worker B try to acquire Lock 1");

            if (lock1.tryLock()) {
                System.out.println("Worker B acquires Lock 1");
                lock1.unlock();
            } else {
                System.out.println("Worker B cannot acquire Lock 1");
                continue;
            }
            break;
        }
        lock1.unlock();
        lock2.unlock();
    }

    // Solution of livelock is to avoid cyclic dependency between the workers.

}
