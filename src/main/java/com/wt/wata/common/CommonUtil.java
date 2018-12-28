package com.wt.wata.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * 常用工具类
 * @author 添柴灬少年
 * @Date    2018/12/25
 * @version 1.0
 *
 * 方法说明：
 *      1.isEmpty()   非空验证     参数支持：String、Object、List、Object[]、Set、Map
 *      2.get32ID()    生成一个32位编码的字符串，用于生成主键
 */
public class CommonUtil implements Serializable {

    /**
     * 判断字符串是否为空
     * @param param 字符串
     * @return  true:为空     false:不为空
     */
    public static boolean isEmpty(String param){
        return param.length() <= 0||param.equals("") ? true : false;
    }

    /**
     * 判断一个对象是否为空
     * @param param 对象
     * @return  true:为空     false:不为空
     */
    public static boolean isEmpty(Object param){
        return param == null || isEmpty(param.toString())? true : false;
    }

    /**
     * 判断List泛型是否为空
     * @param param 泛型集合
     * @return  true:为空     false:不为空
     */
    public static boolean isEmpty(List param){
        return param.size() <= 0 || param == null ? true : false;
    }

    /**
     * 判断一个数组是否为空
     * @param param 数组
     * @return  true:为空     false:不为空
     */
    public static boolean isEmpty(Object[] param){
        return param.length <= 0 || param == null ? true : false;
    }

    /**
     * 判断一个set泛型是否为空
     * @param param set泛型
     * @return  true:为空     false:不为空
     */
    public static boolean isEmpty(Set param){
        return param.size() <= 0 || param == null ? true : false;
    }

    /**
     * 判断一个map集合是否为空
     * @param param map集合
     * @return  true:为空     false:不为空
     */
    public static boolean isEmpty(Map param){
        return param.size() <= 0 || param == null ? true : false;
    }

    /**
     * 生成32位的编码字符串
     * @return
     */
    public static String get32ID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
