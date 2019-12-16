package com.centit.demo.model;

import lombok.Data;

import java.util.List;

/**
 * @author: jiayh
 * @Description:
 * @date: 2019/11/1
 */
@Data
public class BaseGenerateCodeCfg {
    private String tableName;
    private String schemaName;
    private List<BaseTableColumn> finalColumnCfgList;
}
