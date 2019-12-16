package com.centit.demo.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @author: jiayh
 * @Description:
 * @date: 2019/11/5
 */
public class BeanHumpUtils {


    //转变的依赖字符
    public static final char UNDERLINE = '_';

    /**
     * 将驼峰转换成"_"(userId:user_id)
     *
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        param = param.toLowerCase();
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 将"_"转成驼峰(user_id:userId)
     *
     * @param param
     * @return
     */
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        param = param.toLowerCase();
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     *去掉_前两个字符后 首字母大写（t_sys_catalog_val => CatalogVal）
     * @param tableName
     * @return
     */
    public static String table2clazzName(String tableName) {
        String result = "";
        if(StringUtils.isEmpty(tableName)){
            return "";
        }
        tableName = tableName.toLowerCase();
        if (tableName.indexOf(UNDERLINE) != -1) {
            String[] arrays = tableName.split("_");
            for (int i=0;i<arrays.length;i++) {
                if (i==0 || i==1) continue;
                String str = arrays[i];
                result += str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
            }
        } else {
            result = tableName.substring(0, 1).toUpperCase() + tableName.substring(1, tableName.length());
        }
        return result;
    }

    /**
     *去掉_前两个字符后 字母小写（t_sys_catalog_val => catalogval）
     * @param tableName
     * @return
     */
    public static String toLowerCase(String tableName) {
        String result = "";
        if(StringUtils.isEmpty(tableName)){
            return "";
        }
        tableName = tableName.toLowerCase();
        if (tableName.indexOf(UNDERLINE) != -1) {
            String[] arrays = tableName.split("_");
            for (int i=0;i<arrays.length;i++) {
                if (i==0 || i==1) continue;
                String str = arrays[i];
                result += str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
                result = result.toLowerCase();
            }
        } else {
            result = tableName.substring(0, 1).toUpperCase() + tableName.substring(1, tableName.length());
            result = result.toLowerCase();
        }
        return result;
    }

    /**
     *去掉_前两个字符后 将"_"转成驼峰（t_sys_catalog_val => catalogVal）
     * @param tableName
     * @return
     */
    public static String underTableToCamel(String tableName) {
        String result = "";
        if(StringUtils.isEmpty(tableName)){
            return "";
        }
        tableName = tableName.toLowerCase();
        if (tableName.indexOf(UNDERLINE) != -1) {
            String[] arrays = tableName.split("_");
            for (int i=0;i<arrays.length;i++) {
                if (i==0 || i==1) continue;
                String str = arrays[i];
                result += str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
            }
        } else {
            result = tableName.substring(0, 1).toUpperCase() + tableName.substring(1, tableName.length());
        }
        result = result.substring(0, 1).toLowerCase() + result.substring(1);

        return result;
    }
}
