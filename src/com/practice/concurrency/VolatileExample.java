package com.practice.concurrency;

public class VolatileExample {
    static class Worker implements Runnable {
        // After add volatile keyword, CPU will not cache this variable
        // and is always read from the Main Memory(RAM)
        private volatile boolean terminated;

        @Override
        public void run() {
            while(!terminated) {
                System.out.println("Worker is running");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        Thread t1 = new Thread(worker);
        t1.start();
        Thread.sleep(1500);

        worker.terminated = true;
        System.out.println("Algorithm is terminated..");
    }

}
