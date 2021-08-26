package com.thread.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author OVAmach
 * @date 2021/8/9
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer age;
    private String name;
    private String sex;
}
