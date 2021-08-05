package com.future.controller;

import com.future.pojo.GeneralTable;
import com.future.service.BatchOperateMysqlInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author OVAmach
 * @date 2021/7/29
 */
@RestController
public class DemoController {
    @Autowired
    private BatchOperateMysqlInf batchOperateMysqlInf;
    @RequestMapping("/insert")
    public Boolean insert() {
        GeneralTable gt = new GeneralTable();
        Random rand = new Random();
        List<GeneralTable> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            gt.setColAttr("列属性" + rand.nextInt(9) * 1000);
            gt.setColFrom("表属性" + rand.nextInt(9) * 1000);
            gt.setColValue("列值" + rand.nextInt(9) * 1000);
            gt.setColType("列类型" + rand.nextInt(9) * 1000);
            gt.setRowKey((long) rand.nextInt(1000));
            list.add(gt);
        }
        boolean a = batchOperateMysqlInf.insert(list);
        return a;
    }

}
