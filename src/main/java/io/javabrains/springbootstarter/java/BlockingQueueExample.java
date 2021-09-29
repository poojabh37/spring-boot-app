package io.javabrains.springbootstarter.java;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BlockingQueueExample {


    public static void main(String[] args) {
        Random random = new Random();
        BlockingQueue blockingQueue = new BlockingQueue(10);
        Runnable runnable1 = () -> {
            try {
                int value = blockingQueue.dequeue();
                System.out.println(value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable runnable2 = () -> {
            try {
                blockingQueue.enqueue(random.nextInt() * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

//        for (int i = 1; i <= 10; i++) {
//            if (i % 2 == 0) {
//                Thread thread1 = new Thread(runnable2);
//                thread1.start();
//            } else {
//                Thread thread2 = new Thread(runnable1);
//                thread2.start();
//            }
//        }

        try {
            System.out.println("SayHello");
            System.exit(1);
        } finally {
            System.out.println("say Goodbye");
        }


    }

    static class BlockingQueue {

        private final List<Integer> queue = new LinkedList<>();
        private int limit = 10;

        public BlockingQueue() {
        }

        public BlockingQueue(int limit) {
            this.limit = limit;
        }


        public synchronized void enqueue(Integer item) throws InterruptedException {
            System.out.println("enqueue try " + queue.size() + " " + Thread.currentThread().getName());
            while (this.queue.size() == this.limit) {
                wait();
            }
            System.out.println("enqueued " + queue.size() + " " + Thread.currentThread().getName());
            this.queue.add(item);
            if (this.queue.size() == 1) {
                notifyAll();
            }
        }


        public synchronized Integer dequeue() throws InterruptedException {
            System.out.println("dequeue try " + queue.size() + " " + Thread.currentThread().getName());
            while (this.queue.size() == 0) {
                wait();
            }
            System.out.println("dequeued " + queue.size() + " " + Thread.currentThread().getName());
            if (this.queue.size() == this.limit) {
                notifyAll();
            }
            return this.queue.remove(0);
        }

    }
}