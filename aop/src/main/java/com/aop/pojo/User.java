package com.aop.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2021-07-08 16:34:35
 */
@Data
@Entity
@Table(name = "t_user")
public class User implements Serializable {
    private static final long serialVersionUID = 196157697249941375L;
    /**
    * 用户ID
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
    * 账号状态
    */
    private Integer accountStatus;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 密码
    */
    private String password;
    /**
    * 联系电话
    */
    private String phone;
    /**
    * 真实姓名
    */
    private String realName;
    /**
    * 性别
    */
    private Integer sex;
    /**
    * 用户名
    */
    private String userName;
    /**
    * 集团
    */
    private String company;
    /**
    * 部门
    */
    private String department;
}