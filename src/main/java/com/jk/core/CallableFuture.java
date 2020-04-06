package com.jk.core;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFuture {

    public static void main(String[] args) throws Exception {
        ExecutorService execute = Executors.newFixedThreadPool(2);
        Future<String> future = execute.submit(new mycall());
        System.out.println(future.get());
    }
}

class mycall implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "Callable";
    }
}
