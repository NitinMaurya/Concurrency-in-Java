package com.practice.concurrency;

class WorkerA implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1);   // Thread.sleep example
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Worker A::" + i);
        }
    }
}

class WorkerB implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Worker B::" + i);
        }
    }
}

class WorkerC extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Worker C::" + i);
        }
    }
}

class WorkerD extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Worker D::" + i);
        }
    }
}

public class ThreadCreation {

    public static void main(String[] args) {

        // Method 1: Creating threads by implementing runnable interface
        WorkerA workerA = new WorkerA();
        WorkerB workerB = new WorkerB();

        Thread t1 = new Thread(workerA);
        Thread t2 = new Thread(workerB);

        t1.start();
        t2.start();

        // Method 2: Creating threads by extending Thread class
        WorkerC workerC = new WorkerC();
        WorkerD workerD = new WorkerD();

        workerC.start();
        workerD.start();

        // Method 3: Creating threads by Anonymous class
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Anonymous ::" + i);
                }
            }
        });
        t3.start();

        // Method 4: Creating threads by lambdas
        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Lambda ::" + i);
            }
        });
        t4.start();

    }
}
