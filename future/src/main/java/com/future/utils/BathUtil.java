package com.future.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author OVAmach
 * @date 2021/7/29
 */
public class BathUtil {
    public static <T>   List<List<T>> pagingList(List<T> list, int pageSize){
        int length = list.size();
        int num = (length+pageSize-1)/pageSize;
        List<List<T>> newList =  new ArrayList<>();
        for(int i=0;i<num;i++){
            int fromIndex = i*pageSize;
            int toIndex = (i+1)*pageSize<length?(i+1)*pageSize:length;
            newList.add(list.subList(fromIndex,toIndex));
        }
        return newList;
    }
}
