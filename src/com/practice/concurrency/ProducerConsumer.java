package com.practice.concurrency;

import java.util.ArrayList;
import java.util.List;

class Processor {

    private static final List<Integer> list = new ArrayList<>();
    private static final Integer UPPER_LIMIT = 5;
    private static final Integer LOWER_LIMIT = 0;
    private static final Object lock = new Object();
    private int counter = 0;

    public void producer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                Thread.sleep(500);
                if (list.size() == UPPER_LIMIT) {
                    System.out.println("Waiting for elements to be removed..");
                    lock.wait();
                } else {
                    System.out.println("Element Added: " + counter);
                    list.add(counter);
                    counter++;
                    lock.notify();
                }
            }
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                Thread.sleep(500);
                if (list.size() == LOWER_LIMIT) {
                    System.out.println("Waiting for elements to be added..");
                    counter = 0;
                    lock.wait();
                } else {
                    System.out.println("Element removed: " + list.remove(list.size() - 1));
                    lock.notify();
                }
            }
        }
    }
}

public class ProducerConsumer {
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



    }
}
