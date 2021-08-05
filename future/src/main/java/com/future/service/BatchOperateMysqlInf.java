package com.future.service;

import com.future.pojo.GeneralTable;

import java.util.List;

/**
 * @author OVAmach
 * @date 2021/7/29
 */
public interface BatchOperateMysqlInf {
    boolean insert(List<GeneralTable> list);
}
