package com.centit.demo.enums;

/**
 * @author: jiayh
 * @Description:
 * @date: 2019/11/6
 */
public enum DbEnum {
    Mysql("mysql"),
    Hana("hana")
    ;
    private String dbType;

    DbEnum(String dbType) {
        this.dbType = dbType;
    }

    public String getDbType() {
        return dbType;
    }

}
