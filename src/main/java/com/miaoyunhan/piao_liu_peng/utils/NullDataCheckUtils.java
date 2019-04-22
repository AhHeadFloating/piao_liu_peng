package com.miaoyunhan.piao_liu_peng.utils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 空数据校验
 */
public class NullDataCheckUtils<T> {

    /**
     * @Author 宋久斌
     * @Description ：不能为null
     * @Date 2019/4/22 10:27
     */
    public String notNull(T t, List<String> propertyList) throws Exception {
        Class<?> aClass = t.getClass();
        Method[] methods = aClass.getMethods();
        if(propertyList != null && propertyList.size() > 0){
            for (String property:propertyList) {
                String str = firstLetterUp(property);
                Method method = aClass.getMethod("get" + str);
                Object invoke = method.invoke(t);
                if(invoke == null){
                    return property;
                }
            }
        }
        return "success";
    }

    /**
     * @Author 宋久斌
     * @Description ：不能为null或者""
     * @Date 2019/4/22 10:27
     * @return
     */
    public String notNullStr(T t, List<String> propertyList) throws Exception {
        Class<?> aClass = t.getClass();
        if(propertyList != null && propertyList.size() > 0){
            for (String property:propertyList) {
                String str = firstLetterUp(property);
                Method method = aClass.getMethod("get" + str);
                Object invoke = method.invoke(t);
                if(invoke == null || invoke.equals("")){
                    return property;
                }
            }
        }
        return "success";
    }

    /**
     * @Author 宋久斌
     * @Description ：首字母大写
     * @Date 2019/4/22 10:26
     */
    private String firstLetterUp(String str){
        char[] chars = str.toCharArray();
        if(chars[0] >= 'a' && chars[0] <= 'z'){
            chars[0] = (char)(chars[0]-('a'-'A'));
            str = new String(chars);
        }
        return str;
    }

    public static void main(String[] args) throws Exception {

    }
}
