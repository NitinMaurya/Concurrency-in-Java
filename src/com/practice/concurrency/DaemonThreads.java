package com.practice.concurrency;

/*
* Java has two types of threads - 1. Daemon Threads  and  2. Worker Threads
*
* On Java Application startup, it creates 1 worker thread called as Main (for executing main)
* and several other daemon threads for performing garbage collection operations in background.
*
* Daemon Threads are low priority threads that run in background and are used to perform I/O or GC operations.
*
* Java terminates all the daemon threads once it is done executing all the worker threads.
*
* When we create threads in main then these threads are created as a child of main thread.
* */

import java.util.Collection;

class DaemonWorker implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Daemon thread is running....");
        }
    }
}

class NormalWorker implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Normal Worker thread is running....");
    }
}

public class DaemonThreads {
    public static void main(String[] args) {
        Thread daemonWorker = new Thread(new DaemonWorker());
        Thread normalWorker = new Thread(new NormalWorker());
        daemonWorker.setDaemon(true); // creating daemon thread
        daemonWorker.start();
        normalWorker.start();
        System.out.println(daemonWorker.isDaemon()); // check if thread is daemon or not
        System.out.println(normalWorker.isDaemon());
    }
}
