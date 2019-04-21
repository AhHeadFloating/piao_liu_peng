package com.miaoyunhan.piao_liu_peng.entity;

import java.io.Serializable;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
*  blacklist
* @author 宋久斌 2019-04-14
*/
@Data
public class Blacklist implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blacklistId;

    /**
    * 添加黑名单的用户
    */
    private Integer addUserId;

    /**
    * 被添加黑名单的用户
    */
    private String beAddUserId;

    public Blacklist() {
    }

}