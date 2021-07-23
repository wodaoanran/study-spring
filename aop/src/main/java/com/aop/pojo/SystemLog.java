package com.aop.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * (SystemLog)实体类
 *
 * @author makejava
 * @since 2021-07-08 17:54:46
 */
@Data
@Entity
@Table(name = "t_system_log")
public class SystemLog implements Serializable {
    private static final long serialVersionUID = 648234787547324895L;
    /**
    * 日志id
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
    * 操作方法
    */
    private String method;
    /**
    * 操作模块
    */
    private String module;
    /**
    * 操作时间
    */
    private Date operTime;
    /**
    * 操作人
    */
    private String operUser;
    /**
    * 备注
    */
    private String remark;
    /**
    * 响应时间
    */
    private String responseTime;
    /**
    * 操作结果
    */
    private String result;
    /**
    * 角色
    */
    private String roleName;
}