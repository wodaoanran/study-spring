package com.stream.stream;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author OVAmach
 * @date 2021/6/1
 */
@SpringBootTest
public class StreamDemo {
    /*
     流的元素
     stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect|
     */
    @Test
    public void test() {
        System.out.println("=====通过流对数据进行筛选=====");
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        //通过流对数据进行筛选
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        filtered.forEach(System.out::println);
    }

    @Test
    public void test01() {
        int a = 100;
        int b = 100;
        Integer c = 128;
        Integer d = 128;
        System.out.println(a == b);
        System.out.println(c.intValue() == d.intValue());
    }

    /**
     * lamda常用的函数接口
     * Supplier
     * Predicate
     * Consumer
     * Function
     */
    @Test
    public void test02() {

        //使用Lambda表达式提供Supplier接口实现，返回OK字符串
        Supplier<String> stringSupplier = () -> "OK";
        //使用方法引用提供Supplier接口实现，返回空字符串
        Supplier<String> supplier = String::new;
        String s1 = stringSupplier.get();
        System.out.println("使用Supplier定义的返回条件:" + s1);
        //Predicate接口是输入一个参数，返回布尔值。我们通过and方法组合两个Predicate条件，判断是否值大于0并且是偶数
        Predicate<Integer> positiveNumber = i -> i > 0;
        Predicate<Integer> evenNumber = i -> i % 2 == 0;
        assertTrue(positiveNumber.and(evenNumber).test(2));

        //Consumer接口是消费一个数据。我们通过andThen方法组合调用两个Consumer，输出两行abcdefg
        Consumer<String> println = System.out::println;
        println.andThen(println).accept("abcdefg");

        //Function接口是输入一个数据，计算后输出一个数据。我们先把字符串转换为大写，然后通过andThen组合另一个Function实现字符串拼接
        Function<String, String> upperCase = String::toUpperCase;
        Function<String, String> duplicate = s -> s.concat(s);
        assertThat(upperCase.andThen(duplicate).apply("test"), is("TESTTEST"));

        //Supplier是提供一个数据的接口。这里我们实现获取一个随机数
        Supplier<Integer> random = () -> ThreadLocalRandom.current().nextInt();
        System.out.println(random.get());

        //BinaryOperator是输入两个同类型参数，输出一个同类型参数的接口。这里我们通过方法引用获得一个整数加法操作，通过Lambda表达式定义一个减法操作，然后依次调用
        BinaryOperator<Integer> add = Integer::sum;
        BinaryOperator<Integer> subtraction = (a, b) -> a - b;
        assertThat(subtraction.apply(add.apply(1, 2), 3), is(0));
    }

    public void StreamTest01() {
        List<String> list = Arrays.asList("a", "b", "c");
        // 创建一个顺序流
        Stream<String> stream = list.stream();
        // 创建一个并行流
        Stream<String> parallelStream = list.parallelStream();
    }

    @Test
    public void StreamTest02() {
        Integer[] array = {1, 3, 5, 6, 8};
        List<Integer> list = Arrays.asList(array);
        list.forEach(System.out::println);
        /*IntStream stream = Arrays.stream(array);*/
        System.out.println("======");
        Stream<Integer> stream1 = Stream.of(3, 4, 2, 14, 1, 6);
        stream1.forEach(System.out::println);

        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 3).limit(4);
        stream2.forEach(System.out::println);

        Stream<Double> stream3 = Stream.generate(Math::random).limit(3);
        stream3.forEach(System.out::println);
    }
}
