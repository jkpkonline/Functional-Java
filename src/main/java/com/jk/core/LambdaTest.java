package com.jk.core;

public class LambdaTest {
    private String myLocal = "jk";
    public static void main(String[] args) {

        // No need for return keyword
        StrReturn strReturn = () -> "Hi";

        //parenthesis and parameter type not required
        StrReturnParam strReturnParam = i -> "Hi"+"jk";
    }

    // Anonymous implementation have state (eventCount)
    MyEventConsumer myEventConsumer = new MyEventConsumer() {
        private int eventCount = 0;
        public void consume(Object event) {
            System.out.println(event.toString() + " consumed " + this.eventCount++ + " times.");
        }
    };
    MyEventConsumer myEventConsumerLambda = (event) -> {
            // below variable not represent a state for created object
            int eventCount = 0;
            System.out.println(event.toString() + " consumed " + myLocal);
        };
}

interface MyEventConsumer {
    public void consume(Object event);
}

interface StrReturn {
    public String fund();
}

interface StrReturnParam {
    public String fund(int i);
}