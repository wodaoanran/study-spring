package com.stream.stream;

import com.demo.pojo.Person;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author OVAmach
 * @date 2021/6/2
 */
public class StreamDemo01 {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Tom", 8900, "male", "New York"));
        personList.add(new Person("Jack", 7000, "male", "Washington"));
        personList.add(new Person("Lily", 7800, "female", "Washington"));
        personList.add(new Person("Anni", 8200, "female", "New York"));
        personList.add(new Person("Owen", 4800, "male", "New York"));
        personList.add(new Person("Alisa", 5000, "female", "New York"));
        personList.stream().filter(person -> person.getSalary() > 7000).map(person -> person.getSalary()).collect(Collectors.toList()).forEach(System.out::println);
    }

    @Test
    public void Test() {
        List<Integer> list = Arrays.asList(7, -1, 6, 9, 3, 8, 2, 1, 11, 45, 69, 8);

        //筛选出大于1的数据并遍历
        System.out.println("遍历数据符合条件的数据");
        list.stream().filter(x -> x > 1).forEach(System.out::println);
        // 获取否和条件的第一个数据
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        System.out.println("大于6并且在list中索引最小的数：" + findFirst.get());
        // 匹配任意（适用于并行流）
        System.out.println("使用并行流筛选数据");
        Optional<Integer> findAny = list.parallelStream().filter(x -> x > 8).findAny();
        System.out.println("匹配任意：" + findAny.get());
        // 是否包含符合特定条件的元素
        System.out.println("是否包含特定元素的数");
        boolean anyMatch = list.stream().anyMatch(x -> x < 1);
        System.out.println(anyMatch);
    }

    @Test
    public void Test02() {
        List<Integer> list = Arrays.asList(6, 7, 3, 8, 1, 2, 9);
        //筛选出大于7的数并遍历
        list.stream().filter(s -> s > 7).forEach(System.out::println);
    }

    /**
     * 获取String集合中最长的元素。
     */
    @Test
    public void Test03() {
        List<String> list = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
        Optional<String> max = list.stream().max(Comparator.comparing(String::length));
        System.out.println("最长的字符串：" + max.get());
    }

    /**
     * 获取Integer集合中的最大值。
     */
    @Test
    public void Test04() {
        List<Integer> list = Arrays.asList(7, 6, 9, 4, 11, 6);
        // 自然排序
        Optional<Integer> max = list.stream().max(Integer::compareTo);
        Optional<Integer> max1 = list.stream().max(Integer::compareTo);
        Optional<Integer> min = list.stream().min(Integer::compareTo);
        // 自定义排序
        Optional<Integer> max2 = list.stream().max(Comparator.naturalOrder());
        System.out.println("自然排序的最大值：" + max1.get());
        System.out.println("自定义排序的最大值：" + max2.get());
        System.out.println("自定义排序最小值：" + min.get());
    }

    //获取员工工资最高的人。
    @Test
    public void Test05() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        Optional<Person> max = personList.stream().max(Comparator.comparingInt(Person::getSalary));
        Optional<Person> min = personList.stream().min(Comparator.comparingInt(person -> person.getSalary()));
        System.out.println("员工工资最大值：" + max.get().getSalary());
        System.out.println("员工工资最小值：" + min.get().getSalary());
    }

    /**
     * 计算Integer集合中大于6的元素的个数。
     */
    @Test
    public void Test06() {
        List<Integer> list = Arrays.asList(7, 6, 4, 8, 2, 11, 9);
        long count = list.stream().filter(x -> x > 6).count();
        System.out.println("元素中小于4的元素的个数" + list.stream().filter(num -> num < 4).count());
        System.out.println("list中大于6的元素个数：" + count);
    }

    /**
     * 英文字符串数组的元素全部改为大写。整数数组每个元素+3。
     * map用来创建新的元素集合
     */
    @Test
    public void Test07() {
        String[] strArr = {"abcd", "bcdd", "defde", "fTr"};
        String[] wxyf = {"QXYF", "CXYF", "RXYF", "GXYF"};
        List<String> strList = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());
        //String.toUpperCase()转换成大写
        List<String> list = Arrays.stream(strArr).map(s -> s.toUpperCase()).collect(Collectors.toList());
        List<String> lowList = Arrays.stream(wxyf).map(s -> s.toLowerCase()).collect(Collectors.toList());
        List<Integer> intList = Arrays.asList(1, 3, 5, 7, 9, 11);
        List<Integer> intListNew = intList.stream().map(x -> x + 3).collect(Collectors.toList());

        System.out.println("每个元素大写：" + strList);
        System.out.println("每个元素的小写：" + lowList);
        System.out.println("每个元素+3：" + intListNew);
    }

    /**
     * 将员工按薪资是否高于8000分为两部分；
     * 将员工按性别和地区分组
     */
    @Test
    public void Test08() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, "male", "New York"));
        personList.add(new Person("Jack", 7000, "male", "Washington"));
        personList.add(new Person("Lily", 7800, "female", "Washington"));
        personList.add(new Person("Anni", 8200, "female", "New York"));
        personList.add(new Person("Owen", 9500, "male", "New York"));
        personList.add(new Person("Alisa", 7900, "female", "New York"));

        // 将员工按薪资是否高于8000分组
        /**
         * 不同于将员工工资按高于低于来进行分组，这样的分组方式更快捷
         */
        Map<Boolean, List<Person>> part = personList.stream().collect(Collectors.partitioningBy(x -> x.getSalary() > 8000));
        Map<Boolean, List<Person>> lowPart = personList.stream().collect(Collectors.partitioningBy(person -> person.getSalary() < 8000));
        // 将员工按性别分组
        Map<String, List<Person>> group = personList.stream().collect(Collectors.groupingBy(Person::getSex));
        Map<String, List<Person>> sexGroup = personList.stream().collect(Collectors.groupingBy(person -> person.getSex()));
        // 将员工先按性别分组，再按地区分组
        Map<String, Map<String, List<Person>>> group2 = personList.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getArea)));
        System.out.println("员工按薪资是否大于8000分组情况：" + part);
        System.out.println("员工按薪资是否小于8000分组情况：" + lowPart);
        System.out.println("员工按性别分组情况：" + sexGroup);
        System.out.println("员工按性别、地区：" + group2);
    }

    /**
     * joining可以将stream中的元素用特定的连接符（没有的话，则直接连接）连接成一个字符串。
     */
    @Test
    public void Test09() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));

        String names = personList.stream().map(p -> p.getName()).collect(Collectors.joining(UUID.randomUUID().toString()));
        System.out.println("所有员工的姓名：" + names);
        List<String> list = Arrays.asList("A", "B", "C");
        String string = list.stream().collect(Collectors.joining("-"));
        System.out.println("拼接后的字符串：" + string);
    }

    /**
     * Collectors类提供的reducing方法，相比于stream本身的reduce方法，增加了对自定义归约的支持。
     */
    @Test
    public void Test10() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));

        // 每个员工减去起征点后的薪资之和（这个例子并不严谨，但一时没想到好的例子）
        /**
         * identity相当于声明一个 int sum,
         * 第二个参数相当于遍历获取值
         * 第三个参数进行运算操作
         */
        //进行遍历操作
        Integer sum = personList.stream().map(Person::getSalary).reduce(0, (i, j) -> (i + j - 5000));
        //相当于
        /*Integer sum = 0;
        for (Person person : personList) {
            Integer salary = person.getSalary();
            sum = (sum + salary - 5000);
        }*/
        System.out.println("员工扣税薪资总和：" + sum);

        // stream的reduce
        Optional<Integer> sum2 = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        System.out.println("员工薪资总和：" + sum2.get());
    }

    /**
     * sorted，中间操作。有两种排序：
     * <p>
     * sorted()：自然排序，流中元素需实现Comparable接口
     * sorted(Comparator com)：Comparator排序器自定义排序
     */
    @Test
    public void Test11() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Sherry", 9000, 24, "female", "New York"));
        personList.add(new Person("Tom", 8900, 22, "male", "Washington"));
        personList.add(new Person("Jack", 9000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 8800, 26, "male", "New York"));
        personList.add(new Person("Alisa", 9000, 26, "female", "New York"));
        // 按工资升序排序（自然排序）
        List<String> newList = personList.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName)
                .collect(Collectors.toList());
        // 按工资倒序排序
        List<String> newList2 = personList.stream().sorted(Comparator.comparing(Person::getSalary).reversed())
                .map(Person::getName).collect(Collectors.toList());
        // 先按工资再按年龄升序排序
        List<String> newList3 = personList.stream()
                .sorted(Comparator.comparing(Person::getSalary).thenComparing(Person::getAge)).map(Person::getName)
                .collect(Collectors.toList());
        // 先按工资再按年龄自定义排序（降序）
        List<String> newList4 = personList.stream().sorted((p1, p2) -> {
            if (p1.getSalary() == p2.getSalary()) {
                return p2.getAge() - p1.getAge();
            } else {
                return p2.getSalary() - p1.getSalary();
            }
        }).map(Person::getName).collect(Collectors.toList());
        System.out.println("按工资升序排序：" + newList);
        System.out.println("按工资降序排序：" + newList2);
        System.out.println("先按工资再按年龄升序排序：" + newList3);
        System.out.println("先按工资再按年龄自定义降序排序：" + newList4);
    }

    @Test
    public void Test12() {
        String[] arr1 = {"a", "b", "c", "d"};
        String[] arr2 = {"d", "e", "f", "g"};

        Stream<String> stream1 = Stream.of(arr1);
        Stream<String> stream2 = Stream.of(arr2);
        // concat:合并两个流 distinct：去重
        List<String> newList = Stream.concat(stream1, stream2).distinct().collect(Collectors.toList());
        // limit：限制从流中获得前n个数据
        List<Integer> collect = Stream.iterate(1, x -> x + 2).limit(10).collect(Collectors.toList());
        // skip：跳过前n个数据
        List<Integer> collect2 = Stream.iterate(1, x -> x + 2).skip(1).limit(5).collect(Collectors.toList());

        System.out.println("流合并：" + newList);
        System.out.println("limit：" + collect);
        System.out.println("skip：" + collect2);
    }
}
