package com.centit.demo.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * @author: jiayh
 * @Description:
 * @date: 2019/11/1
 */
public interface GenerateCodeService {

    /**
     * @param types ["CONTROLLER", "SERVICE", "MAPPER", "XML", "MODEL"]
     * @param schemaName
     * @param table
     * @return
     */
    ByteArrayOutputStream mySqlGenerateCode(List<String> types, String schemaName, String table);

}
