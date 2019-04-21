package com.miaoyunhan.piao_liu_peng.entity;

import java.io.Serializable;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
*  bottle
* @author 宋久斌 2019-04-14
*/
@Data
public class Bottle implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bottleId;

    /**
    * 发布用户id
    */
    private Integer userId;

    /**
    * 发布信息
    */
    private String subInfo;

    /**
     * 性格：0女，1男
     */
    private Integer sex;

    /**
     * 地区
     */
//    private String address;
    public Bottle() {
    }

}