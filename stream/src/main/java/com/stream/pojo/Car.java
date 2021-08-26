package com.stream.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author OVAmach
 * @date 2021/8/6
 */
@Data
public class Car implements Serializable {
    private String name;
    private String color;
}
