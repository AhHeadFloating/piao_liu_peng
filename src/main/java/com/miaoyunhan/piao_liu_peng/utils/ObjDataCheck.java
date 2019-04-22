package com.miaoyunhan.piao_liu_peng.utils;

import com.miaoyunhan.piao_liu_peng.entity.Bottle;
import com.miaoyunhan.piao_liu_peng.entity.User;

import java.util.ArrayList;

/**
 * 检测数据是否完整（不检验是否正确）
 * 完整返回success，不完整返回缺少的字段
 */
public class ObjDataCheck {
    /**
     * @Author 宋久斌
     * @Description ：登录
     * @Date 2019/4/22 10:45
     */
    public static String login(User user) throws Exception {
        ArrayList<String> propertyList = new ArrayList<>();
        propertyList.add("phone");
        propertyList.add("password");
        propertyList.add("loginDeviceId");
        NullDataCheckUtils<User> checkUtils = new NullDataCheckUtils<>();
        String check = checkUtils.notNullStr(user, propertyList);
        return check;
    }

    public static String throwBottle(Bottle bottle) throws Exception {
        ArrayList<String> propertyList = new ArrayList<>();
        propertyList.add("subInfo");
        NullDataCheckUtils<Bottle> checkUtils = new NullDataCheckUtils<>();
        String check = checkUtils.notNullStr(bottle, propertyList);
        return check;
    }

    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setPhone("110");
        user.setPassword("pppp");
        user.setAddress("北京");
        String login = login(user);
        System.out.println(login);
    }
}
