package com.practice.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchExample {

    static class Worker implements Runnable {
        private final int id;
        private final CountDownLatch countDownLatch;

        public Worker (int id, CountDownLatch countDownLatch) {
            this.id = id;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            doWork();
            countDownLatch.countDown();
        }

        private void doWork() {
            System.out.println("Thread with id " + this.id + " started working.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Worker(i+1, latch));
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All tasks are completed");

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(500, TimeUnit.MILLISECONDS)) {
                System.out.println("All the tasks were completed when the above condition is executed.");
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.out.println("Thread is interrupted as all the tasks are completed.");
            executorService.shutdownNow();
        }
    }
}
