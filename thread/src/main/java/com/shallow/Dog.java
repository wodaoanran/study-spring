package com.shallow;

import java.math.BigDecimal;

public class Dog implements Cloneable {
    private Integer id;
    private String name;
    private BigDecimal weight;
    private Food food;

    public Dog(Integer id, String name, BigDecimal weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", food=" + food +
                '}';
    }

    // 浅拷贝(使用时两者选其一)
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /*// 深拷贝(使用时两者选其一)
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object object = super.clone();
        Food food = ((Dog) object).getFood();
        ((Dog) object).setFood((Food) food.clone());
        return object;
    }*/
}