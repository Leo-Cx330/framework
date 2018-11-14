package com.lh.core.framework.utils;


import org.ho.yaml.Yaml;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class YmlUtil {


    /**
     * key:文件名索引
     * value:配置文件内容
     */
    private static Map<String, LinkedHashMap> ymls = new HashMap<>();

    /**
     * string:当前线程需要查询的文件名
     */
    private static ThreadLocal<String> nowFileName = new ThreadLocal<>();

    private static final String PACKAGENAME="spring.application.controller";
    private static final String FILENAME="init.yml"; //配置文件名称

    /**
     * 加载配置文件
     * @param fileName
     */
    public static void loadYml(String fileName) throws FileNotFoundException {
        nowFileName.set(fileName);
        if (!ymls.containsKey(fileName)) {
            ymls.put(fileName,Yaml.loadType(YmlUtil.class.getResourceAsStream("/" + fileName), LinkedHashMap.class));
        }

    }

    private static Object getValue(String key) throws Exception {
        // 首先将key进行拆分
        String[] keys = key.split("[.]");
        // 将配置文件进行复制
        Map ymlInfo = (Map) ymls.get(nowFileName.get()).clone();
        for (int i = 0; i < keys.length; i++) {
            Object value = ymlInfo.get(keys[i]);
            if (i < keys.length - 1) {
                ymlInfo = (Map) value;
            } else if (value == null) {
                throw new Exception("key不存在");
            } else {
                return value;
            }
        }
        throw new RuntimeException("不可能到这里的...");
    }

    public static Object getValue() throws Exception {
        // 首先加载配置文件
        loadYml(FILENAME);
        return getValue(PACKAGENAME);
    }

}
