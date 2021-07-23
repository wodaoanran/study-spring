package com.aop.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * t_role
 * @author 
 */
@Data
@Entity
@Table(name = "t_role")
public class Role implements Serializable {
    /**
     * 角色ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 是否可用：0停用，1启用
     */
    private Integer available;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 描述
     */
    private String description;

    /**
     * 角色名
     */
    private String roleName;
}