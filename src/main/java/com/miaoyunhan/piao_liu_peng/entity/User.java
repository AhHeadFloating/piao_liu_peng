package com.miaoyunhan.piao_liu_peng.entity;

import java.io.Serializable;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
*  user
* @author 宋久斌 2019-04-14
*/
@Data
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    /**
     * 手机号
     */
    private String phone;
    /**
     * 密码
     */
    private String password;

    /**
    * 昵称
    */
    private String nickName;

    /**
    * 性别
    */
    private Integer sex;

    /**
    * 年龄
    */
    private Integer age;

    /**
     * 注册设备号
     */
    private String RegisterDeviceId;

    /**
     * 最近一次登录的设备号
     */
    private String loginDeviceId;

    /**
     * 地址
     */
    private String address;

    public User() {
    }

}