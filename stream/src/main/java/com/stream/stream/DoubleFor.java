package com.stream.stream;

import com.stream.pojo.User;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author OVAmach
 * @date 2021/8/6
 */
public class DoubleFor {
    public static void main(String[] args) {

        List<User> users1 = users1();
        List<User> users2 = users2();

        System.out.println("users1的值: " + users1.toString());
        System.out.println("users2的值" + users2.toString());

        //嵌套for循环操作
        /*List<User> users = new ArrayList<>();
        for (User user1 : users1) {
            for (User user2 : users2) {
                if (user1.getName().equals(user2.getName()) ) {
                    User user = new User();
                    BeanUtils.copyProperties(user1, user);
                    users.add(user);
                }
            }
        }
        System.out.println("嵌套for循环筛选后的结果" + users.toString());*/


        //java 8 stream操作
        List<User> userList = users1.stream().filter(a -> users2.stream()
                .anyMatch(b -> a.getName().equals(b.getName())))
                .collect(Collectors.toList());
        System.out.println("stream操作筛选后的结果" + userList.toString());
    }

    public static List<User> users1() {
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setName("111");
        User user1 = new User();
        user1.setName("222");
        User user2 = new User();
        user2.setName("333");
        list.add(user);
        list.add(user1);
        list.add(user2);
        return list;
    }

    public static List<User> users2() {
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setName("111");
        User user1 = new User();
        user1.setName("222");
        User user2 = new User();
        user2.setName("333");
        list.add(user);
        list.add(user1);
        list.add(user2);
        return list;
    }
}
