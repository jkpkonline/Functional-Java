package com.jk.core;

import java.util.Optional;

public class DefaultStaticInterfaceDemo implements myinterface, myByinterface{
    @Override
    public String sayHi() {
        return null;
    }
    static String sayHello() {
        return "Hello from class static";
    }

    public static void main(String[] args) {
        System.out.println(DefaultStaticInterfaceDemo.sayHello());
        System.out.println(myinterface.sayHello());
        System.out.println(myByinterface.sayHello());

        Optional<String> hiStr = Optional.empty();
        Optional<String> emptyStr = Optional.empty();
        emptyStr.orElse("jk");
        emptyStr.orElseGet(() -> "pk");
    }

}


interface myinterface {
    default String sayHi() {
        return "Hi from interface";
    }

    static String sayHello() {
        return "Hello from interface static";
    }
}

interface myByinterface {
    default String sayHi() {
        return "Hi from bye interface";
    }

    static String sayHello() {
        return "Hello from bye interface static";
    }
}