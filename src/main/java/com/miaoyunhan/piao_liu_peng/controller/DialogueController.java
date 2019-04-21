package com.miaoyunhan.piao_liu_peng.controller;

import com.miaoyunhan.piao_liu_peng.configration.ResponseBean;
import com.miaoyunhan.piao_liu_peng.entity.Dialogue;
import com.miaoyunhan.piao_liu_peng.service.DialogueService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "dialogue")
public class DialogueController {

    @Autowired
    private DialogueService dialogueService;

    @RequestMapping(value = "reply")
    @RequiresAuthentication
    public ResponseBean reply(@RequestHeader(value = "Authorization")String token, Dialogue dialogue) {
        ResponseBean responseBean = null;
        try{
            responseBean = dialogueService.reply(token,dialogue);
        }catch (Exception e){
            log.error("回复失败",e);
            responseBean = new ResponseBean(500,"回复失败",null);
        }
        return responseBean;
//        return new ResponseBean(200,"成功了吗",null);
    }

    @RequestMapping(value = "test")
    public ResponseBean test(){
        return new ResponseBean(200,"成功了吗",null);
    }

}
