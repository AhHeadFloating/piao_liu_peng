package com.miaoyunhan.piao_liu_peng.entity;

import java.io.Serializable;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
*  dialogue
* @author 宋久斌 2019-04-14
*/
@Data
public class Dialogue implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * dialogue_id
    */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long dialogueId;

    /**
    * 我的瓶子id
    */
    private Integer myBottleId;

    /**
     * 瓶子id
     */
    private Long bottleId;

    /**
    * 发送用户id
    */
    private Integer sendUserId;

    /**
    * 接收用户id
    */
    private Integer acceptUserId;

    /**
    * 回复对话id
    */
    private Long replyDialogueId;

    /**
    * 回复内容
    */
    private String replyInfo;

    public Dialogue() {
    }

}