package com.shallow;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DogTest {

    // 测试浅拷贝
    @Test
    public void test() throws CloneNotSupportedException {
        Dog jack = new Dog(18, "jack", BigDecimal.TEN);
        List<Dog> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Dog tom = (Dog) jack.clone();
            tom.setId(19);
            tom.setName("tom");
            tom.setWeight(BigDecimal.ONE);
            list.add(tom);
        }
        System.out.println(list);
    }

   /* // 测试深拷贝
    @Test
    public void test2() throws CloneNotSupportedException {
        Dog jack = new Dog(18, "jack", BigDecimal.TEN);
        jack.setFood(new Food("冰淇淋"));
        Dog tom = (Dog) jack.clone();
        tom.setId(19);
        tom.setName("tom");
        tom.setWeight(BigDecimal.ONE);
        tom.getFood().setName("三明治");
        System.out.println(jack);
        System.out.println(tom);
    }*/
}