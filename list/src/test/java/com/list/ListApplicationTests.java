package com.list;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
class ListApplicationTests {

    @Test
    void contextLoads() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        //先用contains判断是否有该元素
        if (list.contains(2)) {
            System.out.println(list.indexOf(2));
        }

        System.out.println(list.size());
        System.out.println("==========");
        System.out.println(list.lastIndexOf(3));
    }

    @Test
    void test01() {
        StringBuffer s = new StringBuffer();
        s.append("云");
        //先用contains判断是否有该元素
        s.insert(0, "夏");
        System.out.println(s.toString());
    }

    @Test
    void test02() {
        String str = ",1,2";
        System.out.println(str.substring(str.indexOf(",") + 1));
    }

    @Test
    void test03() {
        List<String> list = new ArrayList<>();
        List<String> data = new ArrayList<>();
        List<String> data1 = new ArrayList<>();
        data.add("111");
        data.add("222");
        data.add("333");
        data.add("444");
        data1.add("555");
        data1.add("666");
        data1.add("777");
        data1.add("888");
        list.addAll(0, data);
        list.addAll(data.size(), data1);
        list.containsAll(data);
        System.out.println(list.isEmpty());
        System.out.println(list.indexOf(111));
        System.out.println(list.containsAll(data));
    }

    @Test
    void test04() {
        List<String> list = new ArrayList<>();
        List<String> data = new ArrayList<>();
        data.add("111");
        data.add("222");
        data.add("333");
        data.add("444");
        list.addAll(data);
        //获取迭代器
        Iterator<String> iterator = list.iterator();

        boolean contains = list.contains("xia");
        System.out.println(contains);
    }

    @Test
    void test05() {
        List<String> collection1 = new ArrayList();
        collection1.add("a");
        collection1.add("b");
        collection1.add("c");
        List<Integer> collection2 = new ArrayList();
        collection2.add(1);
        collection2.add(2);
        collection2.add(3);
        System.out.println(collection1);
        boolean flag = collection1.retainAll(collection2);
        System.out.println(flag);
        System.out.println(collection1);
    }

    @Test
    void test06() {
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(2);
        list.add(3);
        list.sort(new Comparator<Integer>() {
                      @Override
                      public int compare(Integer o1, Integer o2) {
                          int i = o2.compareTo(o1);
                          return i;
                      }
                  }
        );
        List<Integer> list1 = list.subList(0, 2);
        System.out.println(list);
    }

    @Test
    void test07() {
        List<Integer> numList = new ArrayList<>();
        numList.add(1);
        numList.add(2);
        numList.add(3);
        //这里将函数改成了t -> t + 1
        numList.replaceAll(t -> t + 1);

        for (int i = 0; i < numList.size(); i++) {
            System.out.println(numList.get(i));
        }
    }

}
