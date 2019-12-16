package com.centit.demo.utils;

import java.util.HashMap;
import java.util.Map;
/**
 * @author: jiayh
 * @Description:
 * @date: 2019/11/5
 */
public class Jdbc2javaTypeUtil {

    private static Map<String, String> hanaJavaTypeMap = new HashMap<>(6);

    private static Map<String, String> mysqlJavaTypeMap = new HashMap<>(6);

    //TODO oracle的数据类型转java数据类型的映射关系还没有完成
    private static Map<String, String> oracleJavaTypeMap = new HashMap<>(6);

    private static Map<String, String> mysqlJdbcTypeMap = new HashMap<>(6);

    static {
        /**
         * HANA
         * Classification	            Data Type
         * Datetime types	            DATE, TIME, SECONDDATE, TIMESTAMP
         * Numeric types	            TINYINT, SMALLINT, INTEGER, BIGINT, SMALLDECIMAL, DECIMAL, REAL, DOUBLE
         * Character string types	    VARCHAR, NVARCHAR, ALPHANUM, SHORTTEXT
         * Binary types	                VARBINARY
         * Large Object types	        BLOB, CLOB, NCLOB, TEXT
         */
        hanaJavaTypeMap.put("DATE", "Date");
        hanaJavaTypeMap.put("TIME", "Date");
        hanaJavaTypeMap.put("SECONDDATE", "Date");
        hanaJavaTypeMap.put("TIMESTAMP", "Date");

        hanaJavaTypeMap.put("VARCHAR", "String");
        hanaJavaTypeMap.put("NVARCHAR", "String");
        hanaJavaTypeMap.put("ALPHANUM", "String");
        hanaJavaTypeMap.put("SHORTTEXT", "String");

        hanaJavaTypeMap.put("TINYINT", "Byte");
        hanaJavaTypeMap.put("SMALLINT", "Short");
        hanaJavaTypeMap.put("INTEGER", "Integer");
        hanaJavaTypeMap.put("BIGINT", "Long");
        hanaJavaTypeMap.put("SMALLDECIMAL", "BigDecimal");
        hanaJavaTypeMap.put("DECIMAL", "BigDecimal");
        hanaJavaTypeMap.put("REAL", "Float");
        hanaJavaTypeMap.put("FLOAT", "Double");
        hanaJavaTypeMap.put("DOUBLE", "Double");

        hanaJavaTypeMap.put("CLOB", "Clob");
        hanaJavaTypeMap.put("BLOB", "Blob");

        /**
         * Mysql
         */
        mysqlJavaTypeMap.put("char", "String");
        mysqlJavaTypeMap.put("varchar", "String");
        mysqlJavaTypeMap.put("longvarchar", "String");
        mysqlJavaTypeMap.put("text", "String");
        mysqlJavaTypeMap.put("numeric", "BigDecimal");
        mysqlJavaTypeMap.put("decimal", "BigDecimal");
        mysqlJavaTypeMap.put("boolean", "Boolean");
        mysqlJavaTypeMap.put("tinyint", "int");
        mysqlJavaTypeMap.put("smallint", "short");
        mysqlJavaTypeMap.put("int", "Integer");
        mysqlJavaTypeMap.put("bigint", "float");
        mysqlJavaTypeMap.put("float", "double");
        mysqlJavaTypeMap.put("double", "Double");
        mysqlJavaTypeMap.put("datetime", "Date");
        mysqlJavaTypeMap.put("time", "Date");
        mysqlJavaTypeMap.put("timestamp", "Date");
        mysqlJavaTypeMap.put("clob", "Clob");
        mysqlJavaTypeMap.put("blob", "Blob");


        oracleJavaTypeMap.put("DATE", "Date");
        oracleJavaTypeMap.put("TIMESTAMP", "Date");

        oracleJavaTypeMap.put("CHAR", "String");
        oracleJavaTypeMap.put("NCHAR", "String");
        oracleJavaTypeMap.put("VARCHAR", "String");
        oracleJavaTypeMap.put("VARCHAR2", "String");
        oracleJavaTypeMap.put("NVARCHAR2", "String");
        oracleJavaTypeMap.put("LONG", "String");

//        oracleJavaTypeMap.put("NUMBER", "BigDecimal");
//        oracleJavaTypeMap.put("DECIMAL", "BigDecimal");
//        oracleJavaTypeMap.put("TINYINT", "Byte");
//        oracleJavaTypeMap.put("SMALLINT", "Short");
//        oracleJavaTypeMap.put("INTEGER", "Integer");
//        oracleJavaTypeMap.put("BIGINT", "Long");
//        oracleJavaTypeMap.put("REAL", "Float");
//        oracleJavaTypeMap.put("FLOAT", "Double");
//        oracleJavaTypeMap.put("DOUBLE", "Double");
//        hanaJavaTypeMap.put("CLOB", "Clob");
//        hanaJavaTypeMap.put("BLOB", "Blob");

        //jdbcTypeMap
        mysqlJdbcTypeMap.put("char", "CHAR");
        mysqlJdbcTypeMap.put("varchar", "VARCHAR");
        mysqlJdbcTypeMap.put("text", "VARCHAR");
        mysqlJdbcTypeMap.put("int", "INTEGER");
        mysqlJdbcTypeMap.put("tinyint", "TINYINT");
        mysqlJdbcTypeMap.put("double", "DOUBLE");
        mysqlJdbcTypeMap.put("datetime", "TIMESTAMP");



    }

    public static String getMysqlJDBCType(String type) {
        for (String key : mysqlJdbcTypeMap.keySet()) {
            if (type.startsWith(key)) {
                type = key;
            }
        }
        return mysqlJdbcTypeMap.get(type);
    }

    public static String getHanaJAVAType(String type) {
        for (String key : hanaJavaTypeMap.keySet()) {
            if (type.startsWith(key)) {
                type = key;
            }
        }
        return hanaJavaTypeMap.get(type);
    }

    public static String getMysqlJAVAType(String type) {
        for (String key : mysqlJavaTypeMap.keySet()) {
            if (type.startsWith(key)) {
                type = key;
            }
        }
        return mysqlJavaTypeMap.get(type);
    }
}
