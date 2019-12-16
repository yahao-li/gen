package com.centit.demo.model;

import lombok.Data;

import java.util.List;

/**
 * @author: jiayh
 * @Description:
 * @date: 2019/11/1
 */
@Data
public class EsBaseGenerateCodeCfg {
    private String tableName;
    private String schemaName;
    private List<EsBaseColumn> finalColumnCfgList;
}
