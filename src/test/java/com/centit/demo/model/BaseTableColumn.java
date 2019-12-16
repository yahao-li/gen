package com.centit.demo.model;

import lombok.Data;

/**
 * @author: jiayh
 * @Description:
 * @date: 2019/11/1
 */
@Data
public class BaseTableColumn {
    private String columnName;
    private String columnType;
    private String remarks;

    private String javaName;
    private String jdbcType;

    private int type;
    private String javaType;

}
