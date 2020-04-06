package com.jk.core;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FunctionalComposition {

    static List<String> stringList = new ArrayList<String>();
    static List<String> stringSentenceList = new ArrayList<String>();

    static {
        stringSentenceList.add("One flew over the cuckoo's nest");
        stringSentenceList.add("To kill a muckingbird");
        stringSentenceList.add("Gone with the wind");

        stringList.add("ONE");
        stringList.add("TWO");
        stringList.add("THREE");
        stringList.add("NINE");
        stringList.add("TWO");
        stringList.add("TEN");
    }

    public static void main(String[] args) {
        //customPredicateComposition();
        //composeFunctionDemo();
        //filterDemo();
        //mapDemo();
        //flatMapDemo();
        //limitDemo();
        //distinctDemo();
        //peekDemo();
        //anyMatchDemo();
        //allMatchDemo();
        //noneMatchDemo();
        //collectDemo();
        //countDemo();
        //findAnyDemo();
        //minDemo();
        //reduceDemo();
        parallelStreamDemo();
    }

    private static void parallelStreamDemo() {
        LocalTime lt = LocalTime.now();
        IntStream intStreamParallel = IntStream.range(1, 1500000).parallel();
        intStreamParallel.map(i -> i*100+600).
                forEach(System.out::println);
        System.out.println("Time taken: " + Duration.between(lt, LocalTime.now()));
        // 19.434S
        // With parallel  16.345S

    }

    private static void reduceDemo() {
        Stream<String> stream = stringList.stream();
        String str = stream.reduce((i,a) -> {
            return i+"-"+a;
        }).get();
        System.out.println(str);
    }

    private static void minDemo() {
        Stream<String> stream = stringList.stream();
        Comparator<String> strComparator = (s, d) -> s.compareTo(d);
        String str = stream.filter(s -> s.length() > 3).min(strComparator).get();
        System.out.println(str);
    }

    private static void findAnyDemo() {
        Stream<String> stream = stringList.stream();
        String str = stream.filter(s -> s.length() > 3).findAny().get();
        System.out.println(str);
    }
    private static void countDemo() {
        Stream<String> stream = stringList.stream();
        long strLen = stream.filter(s -> s.length() > 3).count();
        System.out.println(strLen);
    }

    private static void collectDemo() {
        Stream<String> stream = stringList.stream();
        List<Integer> strLenList = stream.map(s -> s.length()).
                collect(Collectors.toList());
        System.out.println(strLenList);
    }

    private static void noneMatchDemo() {
        Stream<String> stream = stringList.stream();
        Predicate<String> threeChar = s -> s.length() >= 9;
        System.out.println(stream.noneMatch(threeChar));
    }

    private static void allMatchDemo() {
        Stream<String> stream = stringList.stream();
        Predicate<String> threeChar = s -> s.length() >= 3;
        System.out.println(stream.allMatch(threeChar));
    }

    private static void anyMatchDemo() {
        Stream<String> stream = stringList.stream();
        Predicate<String> threeChar = s -> s.length() ==3;
        System.out.println(stream.anyMatch(threeChar));
    }

    private static void peekDemo() {
        Stream<String> stream = stringList.stream();
        // It will not print anything because there is no modification in the stream
        //stream.peek(System.out::println);

        // Will print bcz it called terminal operator.
        stream.peek(System.out::println).count();
    }

    private static void distinctDemo() {
        Stream<String> stream = stringList.stream();
        stream.distinct().forEach(System.out::println);
    }

    private static void limitDemo() {
        Stream<String> stream = stringList.stream();
        stream.limit(2).forEach(System.out::println);
    }

    private static void flatMapDemo() {
        Stream<String> stream = stringSentenceList.stream();
        stream.flatMap((value) -> {
            String[] split = value.split(" ");
            return (Stream<String>) Arrays.asList(split).stream();
        })
          .forEach((value) -> System.out.println(value))
        ;

    }
    private static void filterDemo() {
        Stream stream = stringList.stream().filter(v -> v.length() ==3);
        stream.forEach(v -> {
            System.out.println(v);
        });

    }

    private static void mapDemo() {
        Stream stream = stringList.stream().map(s -> s.toLowerCase());
        stream.forEach(v -> {
            System.out.println(v);
        });

    }

    private static void composeFunctionDemo() {
        Function<Integer, Integer> multiply = (value) -> value * 2;
        Function<Integer, Integer> add      = (value) -> value + 3;

        Function<Integer, Integer> addThenMultiply = multiply.compose(add);

        Integer result1 = addThenMultiply.apply(3);
        System.out.println(result1);

        Function<Integer, Integer> multiplyThenAdd = multiply.andThen(add);

        result1 = multiplyThenAdd.apply(3);
        System.out.println(result1);
    }

    private static void customPredicateComposition() {
        Predicate<String> startsWithA = (text) -> text.startsWith("A");
        Predicate<String> endsWithX   = (text) -> text.endsWith("R");

        Predicate<String> startsWithAAndEndsWithR =
                (text) -> startsWithA.test(text) && endsWithX.test(text);
        String inputOne = "Arya kumar is my friend, mr kumaR";
        String inputTwo = "Arya kumar is my friend";

        System.out.println(startsWithAAndEndsWithR.test(inputOne));
        System.out.println(startsWithAAndEndsWithR.test(inputTwo));
    }
}
