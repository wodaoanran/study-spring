package com.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author OVAmach
 * @date 2021/7/30
 */
public class ListTest {
    public static void main(String[] args) {
        //给出一个value有重复元素的map
        Map<String,String> map = new HashMap<String, String>();
        map.put("a", "aaa");
        map.put("b", "aaa");
        map.put("c", "bbb");
        map.put("d", "ccc");
        System.out.println("去重前"+map.toString());

        //创建一个新的map2
        Map<String,String> map2=new HashMap<String, String>();
        for(String key:map.keySet()){
            if(!map2.containsValue(map.get(key))){
                map2.put(key, map.get(key));
            }
        }
        System.out.println("去重后"+map2.toString());
    }



}
