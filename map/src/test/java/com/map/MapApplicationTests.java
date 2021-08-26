package com.map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class MapApplicationTests {

    @Test
    void contextLoads() {
        Map<String, Object> map = new HashMap<>();
        map.put("1",1);
        map.put("2",2);
        map.put("3",3);
        map.put("4",4);

        Map<String, Object> map1 = new HashMap<>();
        map1.put("1",1);
        map1.put("2",2);
        map1.put("3",3);
        map1.put("4",4);
        System.out.println(map.toString());
        System.out.println(map.size());
        map.clear();
        System.out.println(map.isEmpty());
        System.out.println(map.equals(map1));
    }

    @Test
    public void test01() {
        String str = "hello java, i am vary happy! nice to meet you";

        // jdk1.8的写法
        HashMap<Character, Integer> result2 = new HashMap<>(32);
        for (int i = 0; i < str.length(); i++) {
            char curChar = str.charAt(i);
            result2.compute(curChar, (k, v) -> {
                if (v == null) {
                    v = 1;
                } else {
                    v += 1;
                }
                return v;
            });
        }
        System.out.println(result2);
    }

}
