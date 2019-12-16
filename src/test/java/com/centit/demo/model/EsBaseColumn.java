package com.centit.demo.model;

import lombok.Data;

/**
 * @author: jiayh
 * @Description:
 * @date: 2019/11/14
 */
@Data
public class EsBaseColumn {

    private String columnName;
    private String columnType;
    private String javaName;
    private String javaType;
}
