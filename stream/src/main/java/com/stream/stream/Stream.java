package com.stream.stream;

import com.stream.pojo.User;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author OVAmach
 * @date 2021/7/20
 */
public class Stream {
    /**
     * list集合
     * @return
     */
    public static List<User> users() {
        List<User> list = Arrays.asList(
                new User("李星云", 18, 0, "渝州", 1000D),
                new User("陆林轩", 16, 1, "渝州", 500D),
                new User("姬如雪", 17, 1, "幻音坊", 800D),
                new User("袁天罡", 99, 0, "藏兵谷", 100000D),
                new User("张子凡", 19, 0, "天师府", 900D),
                new User("陆佑劫", 45, 0, "不良人", 600D),
                new User("张天师", 48, 0, "天师府", 1100D),
                new User("蚩梦", 18, 1, "万毒窟", 800D)
        );
        return list;
    }

    /**
     * filter筛选（对对象的属性进行筛选），collect收集结果（转换成操作后新的集合）
     */
    @Test
    public void filter(){
        List<User> list = users();
        List<User> newlist = list.stream().filter(user -> user.getAge() > 20)
                .collect(Collectors.toList());
        for (User user : newlist) {
            System.out.println(user.getName()+" --> "+ user.getAge());
        }

        /**
         * 筛选性别
         */
        List<User> manUserList = list.stream().filter(student -> student.getSex() == 1).collect(Collectors.toList());
        System.out.println(manUserList);
    }

    /**
     *distinct去除重复数据
     */
    @Test
    public void distinct(){
        List<User> list = users();
        List<User> newlist = list.stream().distinct().collect(Collectors.toList());
        for (User user : newlist) {
            System.out.println(user.getName()+" --> "+ user.getAge());
        }
    }

    /**
     * sorted()排序方法
     * 排序 Comparator.comparingInt()根据条件转换
     * map()将数据重新定义
     */
    @Test
    public void sorted(){
        List<User> list = users();
        /**
         * 根据money进行排序
         */
        List<User> newlist = list.stream()
                .sorted(Comparator.comparingDouble(User::getMoney))
                .collect(Collectors.toList());
        System.out.println(newlist.stream().map(user -> user.getName() + ":" + user.getMoney()).collect(Collectors.toList()));
    }

    /**
     * limit分页，limit()参数返回的条数
     */
    @Test
    public void limit(){
        List<User> list = users();
        List<User> newlist = list.stream()
                .limit(5)
                .collect(Collectors.toList());
        System.out.println(newlist.toString());
    }

    /**
     * skip()跳过前几个元素
     */
    @Test
    public void skip(){
        List<User> list = users();
        List<User> newlist = list.stream()
                .skip(3)
                .collect(Collectors.toList());
        System.out.println(newlist.toString());
    }

    /**
     * map() 取出一个
     */
    @Test
    public void map(){
        List<User> list = users();
        List<Integer> newlist = list.stream()
                .map(User::getAge).distinct().collect(Collectors.toList());
        System.out.println(newlist.toString());
    }

    @Test
    public void flatmap(){
        List<String> flatmap = new ArrayList<>();
        flatmap.add("常宣灵,常昊灵");
        flatmap.add("孟婆,判官红,判官蓝");
        /*
            这里原集合中的数据由逗号分割，使用split进行拆分后，得到的是Stream<String[]>，
            字符串数组组成的流，要使用flatMap的Arrays::stream
            将Stream<String[]>转为Stream<String>,然后把流相连接
        */
        flatmap = flatmap.stream()
                .map(s -> s.split(","))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
        System.out.println(flatmap.toString());
    }

    /**
     * allMatch() 全部匹配
     */
    @Test
    public void allMatch(){
        List<User> list = users();
        boolean flag = list.stream()
                .allMatch(user -> user.getAge() >= 17);
        System.out.println(flag);
    }

    /*anyMatch（T->boolean）检测是否有任意元素满足给定的条件*/
    @Test
    public void anyMatch(){
        List<User> list = users();
        boolean flag = list.stream()
                .anyMatch(user -> user.getSex() == 1);
        System.out.println(flag);
    }

    /*noneMatchT->boolean）流中是否有元素匹配给定的 T -> boolean条件*/
    public void noneMatch(){
        List<User> list = users();
        boolean flag = list.stream()
                .noneMatch(user -> user.getAddress().contains("郑州"));
        System.out.println(flag);
    }

    /*findFirst( ):找到第一个元素*/
    public void findfirst(){
        List<User> list = users();
        Optional<User> optionalUser = list.stream()
                .sorted(Comparator.comparingInt(User::getAge))
                .findFirst();
        System.out.println(optionalUser.toString());
    }

    /*findAny( ):找到任意一个元素*/
    public void findAny(){
        List<User> list = users();
        /*Optional<User> optionalUser = list.stream()
                .findAny();*/
        Optional<User> optionalUser = list.stream()
                .findAny();
        System.out.println(optionalUser.toString());
    }

    /*计算总数*/
    public void count(){
        List<User> list = users();
        long count = list.stream().count();
        System.out.println(count);
    }

    /*最大值最小值*/
    public void max_min(){
        List<User> list = users();
        Optional<User> max = list.stream()
                .collect(
                        Collectors.maxBy(
                                Comparator.comparing(User::getAge)
                        )
                );
        Optional<User> min = list.stream()
                .collect(
                        Collectors.minBy(
                                Comparator.comparing(User::getAge)
                        )
                );
        System.out.println("max--> " + max+"  min--> "+ min);
    }

    /*求和_平均值*/
    /*public static void sum_avg(){
        List<User>list = users();
        int totalAge = list.stream()
                .collect(Collectors.summingInt(User::getAge));
        System.out.println("totalAge--> "+ totalAge);

        //获得列表对象金额， 使用reduce聚合函数,实现累加器
        BigDecimal totalMpney = list.stream()
                .map(User::getMoney)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("totalMpney--> " + totalMpney);

        double avgAge = list.stream()
                .collect(Collectors.averagingInt(User::getAge));
        System.out.println("avgAge--> " + avgAge);
    }*/

    /*一次性得到元素的个数、总和、最大值、最小值*/
    public void allVlaue(){
        List<User> list = users();
        IntSummaryStatistics statistics = list.stream()
                .collect(Collectors.summarizingInt(User::getAge));
        System.out.println(statistics);
    }

    /**
     * Collectors.joining() 连接
     */
    @Test
    public void join(){
        List<User> list = users();
        String names = list.stream()
                .map(User::getName)
                .collect(Collectors.joining(", "));
        System.out.println(names);
    }

    /*public static void group(){
        Map<Integer, List<User>> map = users().stream()
                .collect(Collectors.groupingBy(User::getSex));
        System.out.println(new Gson().toJson(map));
        System.out.println();
        Map<Integer, Map<Integer,List<User>>> map2 = users().stream()
                .collect(Collectors.groupingBy(User::getSex,
                        Collectors.groupingBy(User::getAge)));
        System.out.println(new Gson().toJson(map2));
    }*/

    @Test
    public void groupCount(){
        Map<Integer, Long> num = users().stream()
                .collect(Collectors.groupingBy(User::getSex, Collectors.counting()));
        System.out.println(num);


        Map<Integer, Long> num2 = users().stream()
                .filter(user -> user.getAge()>=18)
                .collect(Collectors.groupingBy(User::getSex, Collectors.counting()));
        System.out.println(num2);
    }

    /*public static void partitioningBy(){
        List<User> list = users();
        Map<Boolean, List<User>> part = list.stream()
                .collect(Collectors.partitioningBy(user -> user.getAge() <= 30));
        System.out.println(new Gson().toJson(part));
    }*/

    @Test
    public void f1(){
        String[] words = new String[]{"Hello","World"};
        List<String[]> a = Arrays.stream(words)
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(a.toString());
    }
}

