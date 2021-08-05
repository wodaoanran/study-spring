package com.future.repository;

import com.future.pojo.GeneralTable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author OVAmach
 * @date 2021/7/29
 */
public interface BatchOperateMysqlDao extends JpaRepository<GeneralTable,Integer> {

}
