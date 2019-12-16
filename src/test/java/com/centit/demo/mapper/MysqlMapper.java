package com.centit.demo.mapper;

import com.centit.demo.model.BaseTableColumn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author: jiayh
 * @Description:
 * @date: 2019/11/5
 */
@Mapper
public interface MysqlMapper {

    Set<Object> getDataTables();

    List<String> getTables(@Param("schemaName") String schemaName);

    List<BaseTableColumn> getColumns(@Param("schemaName") String schemaName, @Param("table") String table);
}
