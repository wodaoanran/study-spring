package com.thread.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * (TSimilar)实体类
 *
 * @author makejava
 * @since 2021-07-16 17:57:30
 */
@Data
@Entity
@Table(name = "t_similar")
public class Similar implements Serializable {
    private static final long serialVersionUID = -23765951919440297L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "createTable_id")
    private String createTableId;
    @Column(name = "similar_id1")
    private String similarId1;
    @Column(name = "similar_id2")
    private String similarId2;
    
    private String similarScore;

}