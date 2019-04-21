package com.miaoyunhan.piao_liu_peng.entity;

import java.io.Serializable;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
*  account
* @author 宋久斌 2019-04-14
*/
@Data
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
    * 最后一次签到日期
    */
    private Date checkInDate;

    /**
    * 金币
    */
    private Integer gold;

    public Account() {
    }

}