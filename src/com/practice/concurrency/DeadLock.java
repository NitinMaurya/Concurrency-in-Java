package com.practice.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        new Thread(deadLock::workerA, "workerA").start();
        new Thread(deadLock::workerB, "workerB").start();
    }

    public void workerA () {
        lock1.lock();
        System.out.println("Worker A acquires Lock 1");

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock2.lock();
        System.out.println("Worker A acquires Lock 2");
        lock1.unlock();
        lock2.unlock();
    }


    // Dead Lock Problem:

    // Here there is a cyclic dependency between the worker A and worker B
    // Worker A holds lock 1 and waits for lock 2
    // Worker B holds lock 2 and waits for lock 1
    public void workerB () {
        lock2.lock();
        System.out.println("Worker B acquires Lock 2");

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock1.lock();
        System.out.println("Worker B acquires Lock 1");
        lock1.unlock();
        lock2.unlock();
    }

/*
    // DeadLock Solution

    // The cyclic dependency can be removed by having the same order of locking for both the workers

    // Worker A holds lock 1 and sleeps for 800 ms and at the same time Worker B waits for lock 1
    // Worker A then holds lock 2 and releases both the locks
    // Once Worker A releases the lock 1, Worker B acquires that lock and proceeds with the execution.

 */

//    public void workerB () {
//        lock1.lock();
//        System.out.println("Worker B acquires Lock 1");
//
//        try {
//            Thread.sleep(800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        lock2.lock();
//        System.out.println("Worker B acquires Lock 2");
//        lock1.unlock();
//        lock2.unlock();
//    }

}
