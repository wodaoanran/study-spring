package com.stream.stream;

import com.demo.pojo.User;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author OVAmach
 * @date 2021/7/20
 */
public class Stream {
    public static List<User> users() {
        List<User> list = Arrays.asList(
                new User("李星云", 18, 0, "渝州", new Double(1000)),
                new User("陆林轩", 16, 1, "渝州", new Double(500)),
                new User("姬如雪", 17, 1, "幻音坊", new Double(800)),
                new User("袁天罡", 99, 0, "藏兵谷", new Double(100000)),
                new User("张子凡", 19, 0, "天师府", new Double(900)),
                new User("陆佑劫", 45, 0, "不良人", new Double(600)),
                new User("张天师", 48, 0, "天师府", new Double(1100)),
                new User("蚩梦", 18, 1, "万毒窟", new Double(800))
        );
        return list;
    }

    /*filter过滤(T-> boolean)*/

    /**
     * filter筛选，collect收集结果
     */
    @Test
    public void filter(){
        List<User> list = users();
        List<User> newlist = list.stream().filter(user -> user.getAge() > 20)
                .collect(Collectors.toList());
        for (User user : newlist) {
            System.out.println(user.getName()+" --> "+ user.getAge());
        }
    }
   /* ---结果---
    袁天罡 --> 99
    陆佑劫 --> 45
    张天师 --> 48*/

    /*distinct 去重*/

    /**
     * 去重
     */
    @Test
    public void distinct(){
        List<User> list = users();
        List<User> newlist = list.stream().distinct().collect(Collectors.toList());
        for (User user : newlist) {
            System.out.println(user.getName()+" --> "+ user.getAge());
        }
    }
    /*---结果---
    李星云 --> 18
    陆林轩 --> 16
    姬如雪 --> 17
    袁天罡 --> 99
    张子凡 --> 19
    陆佑劫 --> 45
    张天师 --> 48
    蚩梦 --> 18*/

    /*sorted排序*/

    /**
     * 排序 Comparator.comparingInt()根据条件转换
     */
    @Test
    public void sorted(){
        List<User> list = users();
        List<User> newlist = list.stream()
                .sorted(Comparator.comparingDouble(User::getMoney))
                .collect(Collectors.toList());
        for (User user : newlist) {
            System.out.println(user.getName()+" --> "+ user.getMoney());
        }
    }
   /* ---结果---
    陆林轩 --> 16
    姬如雪 --> 17
    李星云 --> 18
    蚩梦 --> 18
    张子凡 --> 19
    陆佑劫 --> 45
    张天师 --> 48
    袁天罡 --> 99*/

    /*limit返回前n个元素*/
    /**
     * limit分页
     */
    @Test
    public void limit(){
        List<User> list = users();
        List<User> newlist = list.stream()
                .sorted(Comparator.comparingInt(User::getAge))
                .limit(2)
                .collect(Collectors.toList());
        for (User user : newlist) {
            System.out.println(user.getName()+" --> "+ user.getAge());
        }
    }
    /*---结果---
    陆林轩 --> 16
    姬如雪 --> 17*/

    /*skip去除前n个元素*/
    public static void skip(){
        List<User> list = users();
        List<User> newlist = list.stream()
                .sorted(Comparator.comparingInt(User::getAge))
                .skip(2)
                .collect(Collectors.toList());
        for (User user : newlist) {
            System.out.println(user.getName()+" --> "+ user.getAge());
        }
    }
  /*  ---结果---
    李星云 --> 18
    蚩梦 --> 18
    张子凡 --> 19
    陆佑劫 --> 45
    张天师 --> 48
    袁天罡 --> 99*/

    /*map(T->R)*/
    public static void map(){
        List<User> list = users();
        List<String> newlist = list.stream()
                .map(User::getName).distinct().collect(Collectors.toList());
        for (String add : newlist) {
            System.out.println(add);
        }
    }
  /*  ---结果---
    李星云
            陆林轩
    姬如雪
            袁天罡
    张子凡
            陆佑劫
    张天师
            蚩梦*/

    /*flatMap(T -> Stream<R>)*/
    public static void flatmap(){
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
        for (String name : flatmap) {
            System.out.println(name);
        }
    }
    /*---结果---
    常宣灵
            常昊灵
    孟婆
            判官红
    判官蓝
*/
    /*allMatch（T->boolean）检测是否全部满足参数行为*/
    public static void allMatch(){
        List<User> list = users();
        boolean flag = list.stream()
                .allMatch(user -> user.getAge() >= 17);
        System.out.println(flag);
    }
	/*---结果---
            false*/

    /*anyMatch（T->boolean）检测是否有任意元素满足给定的条件*/
    public static void anyMatch(){
        List<User> list = users();
        boolean flag = list.stream()
                .anyMatch(user -> user.getSex() == 1);
        System.out.println(flag);
    }
	/*---结果---
            true*/

    /*noneMatchT->boolean）流中是否有元素匹配给定的 T -> boolean条件*/
    public static void noneMatch(){
        List<User> list = users();
        boolean flag = list.stream()
                .noneMatch(user -> user.getAddress().contains("郑州"));
        System.out.println(flag);
    }
	/*---结果---
            true*/

    /*findFirst( ):找到第一个元素*/
    public static void findfirst(){
        List<User> list = users();
        Optional<User> optionalUser = list.stream()
                .sorted(Comparator.comparingInt(User::getAge))
                .findFirst();
        System.out.println(optionalUser.toString());
    }
	/*---结果---
    Optional[User{name='陆林轩', age=16, sex=1, money=500, address='渝州'}]
*/

    /*findAny( ):找到任意一个元素*/
    public static void findAny(){
        List<User> list = users();
        /*Optional<User> optionalUser = list.stream()
                .findAny();*/
        Optional<User> optionalUser = list.stream()
                .findAny();
        System.out.println(optionalUser.toString());
    }
   /*---结果---
    Optional[User{name='李星云', age=18, sex=0, money=1000, address='渝州'}]*/


    /*计算总数*/
    public static void count(){
        List<User> list = users();
        long count = list.stream().count();
        System.out.println(count);
    }
    /*---结果---
            8*/

    /*最大值最小值*/
    public static void max_min(){
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
  /* ---结果---
    max--> Optional[User{name='袁天罡', age=99, sex=0, money=100000, address='藏兵谷'}]  min--> Optional[User{name='陆林轩', age=16, sex=1, money=500, address='渝州'}]*/

    /*求和_平均值*/
   /* public static void sum_avg(){
        List<User>list = users();
        int totalAge = list.stream()
                .collect(Collectors.summingInt(User::getAge));
        System.out.println("totalAge--> "+ totalAge);

        *//*获得列表对象金额， 使用reduce聚合函数,实现累加器*//*
        BigDecimal totalMpney = list.stream()
                .map(User::getMoney)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("totalMpney--> " + totalMpney);

        double avgAge = list.stream()
                .collect(Collectors.averagingInt(User::getAge));
        System.out.println("avgAge--> " + avgAge);
    }*/
   /*---结果---
    totalAge--> 280
    totalMpney--> 105700
    avgAge--> 35.0*/

    /*一次性得到元素的个数、总和、最大值、最小值*/
    public static void allVlaue(){
        List<User> list = users();
        IntSummaryStatistics statistics = list.stream()
                .collect(Collectors.summarizingInt(User::getAge));
        System.out.println(statistics);
    }
  /* ---结果---
    IntSummaryStatistics{count=8, sum=280, min=16, average=35.000000, max=99}
*/
    /*拼接*/
    public static void join(){
        List<User> list = users();
        String names = list.stream()
                .map(User::getName)
                .collect(Collectors.joining(", "));
        System.out.println(names);
    }
  /* ---结果---
    李星云, 陆林轩, 姬如雪, 袁天罡, 张子凡, 陆佑劫, 张天师, 蚩梦*/

   /* *//*分组*//*
    public static void group(){
        Map<Integer, List<User>> map = users().stream()
                .collect(Collectors.groupingBy(User::getSex));
        System.out.println(new Gson().toJson(map));
        System.out.println();
        Map<Integer, Map<Integer,List<User>>> map2 = users().stream()
                .collect(Collectors.groupingBy(User::getSex,
                        Collectors.groupingBy(User::getAge)));
        System.out.println(new Gson().toJson(map2));
    }
  *//* ---结果---
    {"0":[{"name":"李星云","age":18,"sex":0,"address":"渝州","money":1000},{"name":"袁天罡","age":99,"sex":0,"address":"藏兵谷","money":100000},{"name":"张子凡","age":19,"sex":0,"address":"天师府","money":900},{"name":"陆佑劫","age":45,"sex":0,"address":"不良人","money":600},{"name":"张天师","age":48,"sex":0,"address":"天师府","money":1100}],"1":[{"name":"陆林轩","age":16,"sex":1,"address":"渝州","money":500},{"name":"姬如雪","age":17,"sex":1,"address":"幻音坊","money":800},{"name":"蚩梦","age":18,"sex":1,"address":"万毒窟","money":800}]}

    {"0":{"48":[{"name":"张天师","age":48,"sex":0,"address":"天师府","money":1100}],"18":[{"name":"李星云","age":18,"sex":0,"address":"渝州","money":1000}],"19":[{"name":"张子凡","age":19,"sex":0,"address":"天师府","money":900}],"99":[{"name":"袁天罡","age":99,"sex":0,"address":"藏兵谷","money":100000}],"45":[{"name":"陆佑劫","age":45,"sex":0,"address":"不良人","money":600}]},"1":{"16":[{"name":"陆林轩","age":16,"sex":1,"address":"渝州","money":500}],"17":[{"name":"姬如雪","age":17,"sex":1,"address":"幻音坊","money":800}],"18":[{"name":"蚩梦","age":18,"sex":1,"address":"万毒窟","money":800}]}}
*//*
    *//*分组合计*//*
    public static void groupCount(){
        Map<Integer, Long> num = users().stream()
                .collect(Collectors.groupingBy(User::getSex, Collectors.counting()));
        System.out.println(num);


        Map<Integer, Long> num2 = users().stream()
                .filter(user -> user.getAge()>=18)
                .collect(Collectors.groupingBy(User::getSex, Collectors.counting()));
        System.out.println(num2);
    }
  *//* ---结果---
    {0=5, 1=3}
    {0=5, 1=1}*//*

    *//*分区*//*
    public static void partitioningBy(){
        List<User> list = users();
        Map<Boolean, List<User>> part = list.stream()
                .collect(Collectors.partitioningBy(user -> user.getAge() <= 30));
        System.out.println(new Gson().toJson(part));
    }
  *//* ---结果---
    {"false":[{"name":"袁天罡","age":99,"sex":0,"address":"藏兵谷","money":100000},{"name":"陆佑劫","age":45,"sex":0,"address":"不良人","money":600},{"name":"张天师","age":48,"sex":0,"address":"天师府","money":1100}],"true":[{"name":"李星云","age":18,"sex":0,"address":"渝州","money":1000},{"name":"陆林轩","age":16,"sex":1,"address":"渝州","money":500},{"name":"姬如雪","age":17,"sex":1,"address":"幻音坊","money":800},{"name":"张子凡","age":19,"sex":0,"address":"天师府","money":900},{"name":"蚩梦","age":18,"sex":1,"address":"万毒窟","money":800}]}
*/

}

