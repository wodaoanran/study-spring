package com.poi.common;

import lombok.Data;

import java.util.List;

/**
 * 一条数据
 * Created by jwing on 2021/4/26.
 */
@Data
public class Row {

    private List<String> data;

    private Short height;

}
