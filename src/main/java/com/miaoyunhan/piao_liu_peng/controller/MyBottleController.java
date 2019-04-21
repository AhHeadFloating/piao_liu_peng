package com.miaoyunhan.piao_liu_peng.controller;

import com.miaoyunhan.piao_liu_peng.configration.ResponseBean;
import com.miaoyunhan.piao_liu_peng.entity.MyBottle;
import com.miaoyunhan.piao_liu_peng.service.MyBottleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "myBottle")
public class MyBottleController {
    @Autowired
    private MyBottleService myBottleService;
/*
    @RequestMapping(value = "insert")
    public ResponseBean insert(MyBottle myBottle){

    }*/

    @RequestMapping(value = "findByPage")
//    @RequiresAuthentication
    public ResponseBean findByPage(Integer pageNo, Integer pageSize, @RequestHeader(value = "Authorization") String token){
        ResponseBean responseBean = null;
        try{
            responseBean = myBottleService.findByPage(pageNo,pageSize,token);
        }catch (Exception e){
            log.error("查找我的瓶子异常",e);
            responseBean = new ResponseBean(500,"查找我的瓶子异常",null);
        }
        return responseBean;
    }

    @RequestMapping(value = "del")
    @RequiresAuthentication
    public ResponseBean del(Integer myBottleId){
        ResponseBean responseBean = null;
        try{
            responseBean = myBottleService.del(myBottleId);
        }catch (Exception e){
            log.error("删除我的瓶子异常",e);
            responseBean = new ResponseBean(500,"删除我的瓶子异常",null);
        }
        return responseBean;
    }

}
