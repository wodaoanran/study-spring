package com.shiwu.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author OVAmach
 * @date 2021/8/4
 */
@Entity
@Data
@Table(name = "student")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String sex;
    private Integer age;
}
