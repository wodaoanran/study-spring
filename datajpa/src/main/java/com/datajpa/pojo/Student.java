package com.datajpa.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author OVAmach
 * @date 2021/7/27
 */
@Entity
@Table(name = "student")
@Data
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
}
