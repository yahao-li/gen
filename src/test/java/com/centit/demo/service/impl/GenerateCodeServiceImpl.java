package com.centit.demo.service.impl;

import com.centit.demo.enums.DbEnum;
import com.centit.demo.mapper.MysqlMapper;
import com.centit.demo.model.BaseGenerateCodeCfg;
import com.centit.demo.model.BaseTableColumn;
import com.centit.demo.service.GenerateCodeService;
import com.centit.demo.utils.BeanHumpUtils;
import com.centit.demo.utils.Jdbc2javaTypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author: jiayh
 * @Description:
 * @date: 2019/11/1
 */

@Slf4j
@Service
public class GenerateCodeServiceImpl implements GenerateCodeService {

    @Autowired
    private MysqlMapper mysqlMapper;

    @Value("${generate.author}")
    private String AUTHOR;

    @Value("${generate.path.model}")
    private String MODEL_PATH;

    @Value("${generate.path.model.vo}")
    private String VO_PATH;

    @Value("${generate.path.model.dto}")
    private String DTO_PATH;

    @Value("${generate.path.mapper}")
    private String MAPPER_PATH;

    @Value("${generate.path.service}")
    private String SERVICE_PATH;

    @Value("${generate.path.service.impl}")
    private String SERVICE_IMPL_PATH;

    @Value("${generate.path.controller}")
    private String CONTROLLER_PATH;

    @Value("${generate.path.dao}")
    private String DAO_PATH;

    @Override
    public ByteArrayOutputStream mySqlGenerateCode(List<String> types, String schemaName, String table) {

        List<BaseTableColumn> columns = mysqlMapper.getColumns(schemaName, table);
        BaseGenerateCodeCfg tableCfg = new BaseGenerateCodeCfg();
        tableCfg.setTableName(table);
        tableCfg.setSchemaName(schemaName);
        tableCfg.setFinalColumnCfgList(columns);
        for (BaseTableColumn btc : columns) {
            String columnName = btc.getColumnName();
            btc.setJavaName(BeanHumpUtils.underlineToCamel(columnName));
            btc.setJdbcType(Jdbc2javaTypeUtil.getMysqlJDBCType(btc.getColumnType()));
            btc.setJavaType(Jdbc2javaTypeUtil.getMysqlJAVAType(btc.getColumnType()));
        }
        return generateCode(types, tableCfg, DbEnum.Mysql.getDbType());
    }

    public ByteArrayOutputStream generateCode(List<String> types, BaseGenerateCodeCfg tableCfg, String dbType) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = null;
        try {
            String upCaseClassName = BeanHumpUtils.table2clazzName(tableCfg.getTableName());
            zos = new ZipOutputStream(baos, Charset.forName("UTF-8"));

            if (types.contains("XML")) {
                ZipEntry tEntry = new ZipEntry(tableCfg.getTableName() + "/" + upCaseClassName + "Mapper.xml");
                zos.putNextEntry(tEntry);
                String template = "";
                if(dbType.equals(DbEnum.Mysql.getDbType())){

                    template = "template/GenerateCodeXml-Mysql.vm";
                }else if(dbType.equals(DbEnum.Hana.getDbType())){
                    template = "template/GenerateCodeXml-Mysql.vm";
                }
                byte[] xml = createFile(tableCfg.getSchemaName(), tableCfg, template);
                zos.write(xml);
                zos.closeEntry();
            }
            if (types.contains("MAPPER")) {
                ZipEntry tEntry = new ZipEntry(tableCfg.getTableName() + "/" + upCaseClassName + "Mapper.java");
                zos.putNextEntry(tEntry);

                byte[] xml = createFile(tableCfg.getSchemaName(), tableCfg, "template/GenerateCodeMapper-new.vm");
                zos.write(xml);
                zos.closeEntry();
            }
            if (types.contains("MODEL")) {
                ZipEntry tEntry = new ZipEntry(tableCfg.getTableName() + "/" + upCaseClassName + ".java");
                zos.putNextEntry(tEntry);
                byte[] java = createFile(tableCfg.getSchemaName(), tableCfg, "template/GenerateCodeModel-new.vm");
                zos.write(java);
                zos.closeEntry();

                ZipEntry tEntryVO = new ZipEntry(tableCfg.getTableName() + "/" + upCaseClassName + "VO.java");
                zos.putNextEntry(tEntryVO);
                byte[] javaVO = createFile(tableCfg.getSchemaName(), tableCfg, "template/GenerateCodeModelVO.vm");
                zos.write(javaVO);
                zos.closeEntry();

                ZipEntry tEntryDTO = new ZipEntry(tableCfg.getTableName()+ "/" + upCaseClassName + "Filter.java");
                zos.putNextEntry(tEntryDTO);
                byte[] javaDTO = createFile(tableCfg.getSchemaName(), tableCfg, "template/GenerateCodeModelFilter.vm");
                zos.write(javaDTO);
                zos.closeEntry();

            }

            if (types.contains("SERVICE")) {
                ZipEntry tEntry = new ZipEntry(tableCfg.getTableName()+ "/" + upCaseClassName + "Service.java");
                zos.putNextEntry(tEntry);
                byte[] service = createFile(tableCfg.getSchemaName(), tableCfg, "template/GenerateCodeService-new.vm");
                zos.write(service);
                zos.closeEntry();

                ZipEntry tEntryImpl = new ZipEntry(tableCfg.getTableName()+ "/" + upCaseClassName + "ServiceImpl.java");
                zos.putNextEntry(tEntryImpl);
                byte[] serviceImpl = createFile(tableCfg.getSchemaName(), tableCfg, "template/GenerateCodeServiceImpl-new.vm");
                zos.write(serviceImpl);
                zos.closeEntry();
            }

            if (types.contains("CONTROLLER")) {
                ZipEntry tEntry = new ZipEntry(tableCfg.getTableName()+ "/" + upCaseClassName + "Controller.java");
                zos.putNextEntry(tEntry);
                byte[] xml = createFile(tableCfg.getSchemaName(), tableCfg, "template/GenerateCodeController-new.vm");
                zos.write(xml);
                zos.closeEntry();
            }

        } catch (Exception e) {
            log.error(e.toString(), e);
        } finally {
            try {
                zos.close();
            } catch (IOException e) {
                log.error(e.toString(), e);
            }
        }


        return baos;
    }

    private byte[] createFile(String schemaName, BaseGenerateCodeCfg tableCfg, String vmPath) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            VelocityEngine ve = new VelocityEngine();
            ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
            ve.init();
            VelocityContext context = new VelocityContext();
            context.put("author", AUTHOR);
            context.put("date", DateFormatUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
            context.put("columns", tableCfg.getFinalColumnCfgList());
            context.put("size", tableCfg.getFinalColumnCfgList().size());
            context.put("table", tableCfg.getTableName());
            context.put("className", BeanHumpUtils.table2clazzName(tableCfg.getTableName()));
            context.put("tableName", BeanHumpUtils.underTableToCamel(tableCfg.getTableName()));
            context.put("pathName", BeanHumpUtils.toLowerCase(tableCfg.getTableName()));
            context.put("schemaName", schemaName);
            context.put("daoPath", DAO_PATH);
            context.put("mapperPath", MAPPER_PATH);
            context.put("modelPath", MODEL_PATH);
            context.put("voPath", VO_PATH);
            context.put("controllerPath", CONTROLLER_PATH);
            context.put("dtoPath", DTO_PATH);
            context.put("servicePath", SERVICE_PATH);
            context.put("serviceImpPath", SERVICE_IMPL_PATH);
            Template t = ve.getTemplate(vmPath, "UTF-8");
            OutputStreamWriter osw = new OutputStreamWriter(baos, "UTF-8");
            t.merge(context, osw);
            osw.close();
        } catch (Exception e) {
            log.error(e.toString(), e);
        }
        return baos.toByteArray();
    }
}
