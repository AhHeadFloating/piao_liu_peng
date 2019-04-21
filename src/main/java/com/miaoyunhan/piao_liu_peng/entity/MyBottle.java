package com.miaoyunhan.piao_liu_peng.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
*  my_boottle
* @author 宋久斌 2019-04-14
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyBottle implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer myBottleId;

    /**
    * 第一次发布信息的用户id
    */
    private Integer firstSendUserId;

    /**
    * 第一次接收的信息的用户id
    */
    private Integer firstAcceptUserId;

    /**
    * 第一次发布信息的用户昵称
    */
    private String firstSendNicoName;

    /**
    * 第一次接收信息的用户昵称
    */
    private String firstAcceptNicoName;

    /**
    * 第一次发送的信息内容
    */
    private String firstSendInfo;

}