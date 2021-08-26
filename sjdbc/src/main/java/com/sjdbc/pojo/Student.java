package com.sjdbc.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author OVAmach
 * @date 2021/8/13
 */
@Data
public class Student implements Serializable {
    //id
    private Integer id;
    //姓名
    private String name;
    //年龄
    private Integer age;
    //性别
    private String sex;
    //住址
    private String address;
    //是否逻辑删除(0:未删除，1:已删除)
    private Integer isDelete;

}
