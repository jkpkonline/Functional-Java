package com.jk.core;

import java.util.stream.IntStream;

public class ThreadLocalDemo {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
    //public static Map
    public static void main(String[] args) {
        Thread t1 = new MyRunnable(); Thread t2 = new MyRunnable();
        Thread t3 = new MyRunnable(); Thread t4 = new MyRunnable();
        Thread t5 = new MyRunnable(); Thread t6 = new MyRunnable();
        t1.setName("jitu"); t2.setName("jk");t3.setName("pintu");
        t4.setName("pk"); t5.setName("saurav"); t6.setName("srv");

        t1.start(); t2.start();t3.start(); t4.start();t5.start(); t6.start();

    }


}


class MyRunnable extends Thread {
    @Override
    public void run()  {
        ThreadLocalDemo.threadLocal.set(Thread.currentThread().getName());
        IntStream.range(1,10).forEach(i -> repeatCall());
    }

    private void repeatCall() {
        System.out.println(ThreadLocalDemo.threadLocal.get() +
                "  Thread Name: " + Thread.currentThread().getName());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
