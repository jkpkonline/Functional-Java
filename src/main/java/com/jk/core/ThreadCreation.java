package com.jk.core;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadCreation {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        Future future = pool.submit(new MyRun());
        pool.submit(new MyThread());
        pool.submit(new MyExecutor());
        pool.shutdown();

        //
        System.out.println(future.isDone() + " " + future.get());
    }
}

class MyRun implements Runnable {
    @Override
    public void run() {
        System.out.println("Run...");
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Threading...");
    }
}

class MyExecutor implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("Callable...");
        return "Callable";
    }
}