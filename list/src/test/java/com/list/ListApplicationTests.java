package com.list;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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
        if(list.contains(2)){
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
        s.insert(0,"夏");
        System.out.println(s.toString());
    }

    @Test
    void test02() {
        String str=",1,2";
        System.out.println(str.substring(str.indexOf(",")+1));
    }

}
