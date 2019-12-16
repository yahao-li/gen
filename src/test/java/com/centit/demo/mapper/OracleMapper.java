package com.centit.demo.mapper;

import com.centit.demo.model.BaseTableColumn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: jiayh
 * @Description:
 * @date: 2019/11/1
 */
public interface OracleMapper {

//    List<String> getSchemas();

    List<String> getTables(@Param("tablespaceName") String tablespaceName);

    List<BaseTableColumn> getColumns(@Param("tableName") String tableName);
}
