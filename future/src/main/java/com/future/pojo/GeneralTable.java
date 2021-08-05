package com.future.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "gener_table")
public class GeneralTable implements Serializable {
    @Id
    private Integer id;
    private String colValue;
    private String colAttr;
    private String colType;
    private String colFrom;
    private Long rowKey;

}
