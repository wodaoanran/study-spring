package com.format.utils;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * 保留小数点后几位
 * @author OVAmach
 * @date 2021/7/2
 */
public class DecimalFormatUtil {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        DecimalFormat dft = new DecimalFormat("0.000");
        Scanner scr1 = new Scanner(System.in);
        double number1 = scr1.nextDouble() ;
        String str1 = dft.format(number1);
        double str2 = Double.parseDouble(str1);
        System.out.println(str1);
    }
}
