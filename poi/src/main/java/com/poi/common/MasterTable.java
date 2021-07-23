package com.poi.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 表数据
 * Created by jwing on 2021/4/26.
 */
@Data
public class MasterTable {

    private String tableName;

    private Title title;

    private List<Row> rows;

    /**
     * 获取初始化数据
     *
     * @return
     */
    public static MasterTable init() {
        MasterTable table = new MasterTable();
        table.setRows(new ArrayList<>());
        Title title = new Title();
        title.setHeader(new ArrayList<>());
        table.setTitle(title);
        return table;
    }

    /**
     * 获取初始化数据
     *
     * @return
     */
    public static MasterTable init(List<String> titles, List<Short> widths) {
        MasterTable table = new MasterTable();
        Title title = new Title();
        title.setHeader(titles);
        title.setWidths(widths);
        table.setTitle(title);
        table.setRows(Collections.EMPTY_LIST);
        return table;
    }

    /**
     * 获取初始化数据
     *
     * @return
     */
    public static MasterTable init(List<String> titles, List<Short> widths, String tableName) {
        MasterTable table = init(titles, widths);
        table.setTableName(tableName);
        return table;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(11);
        list.add("a");
        System.out.println(list.get(1));
    }
}