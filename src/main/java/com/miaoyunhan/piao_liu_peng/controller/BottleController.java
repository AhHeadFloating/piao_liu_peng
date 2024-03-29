package com.miaoyunhan.piao_liu_peng.controller;


import com.miaoyunhan.piao_liu_peng.configration.ResponseBean;
import com.miaoyunhan.piao_liu_peng.entity.Bottle;
import com.miaoyunhan.piao_liu_peng.service.BottleService;
import com.miaoyunhan.piao_liu_peng.service.UserService;
import com.miaoyunhan.piao_liu_peng.utils.ObjDataCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping(value = "bottle")
public class BottleController {

    @Autowired
    private BottleService bottleService;

    @Autowired
    private UserService userService;

    /**
     * @Author 宋久斌
     * @Description ：根据条件分页查询（不指定页数随机返回一页）
     * @Date 2019/4/15 13:38
     */
    @RequestMapping(value = "findByPage")
    public ResponseBean findByPage(Bottle bottle, Integer pageNo, @RequestParam(defaultValue = "5") Integer pageSize){
        HashMap<String,Object> map = null;
        map = bottleService.findByPage();
        return new ResponseBean(200,"根据条件分页查询瓶子",map);
    }

    /**
     * @Author 宋久斌
     * @Description ：添加一个瓶子
     * @Date 2019/4/15 14:22
     */
    @RequestMapping(value = "insert")
    public ResponseBean insert(@RequestHeader(value = "Authorization")String token,String subInfo){
        ResponseBean responseBean = null;
        try{
            if(subInfo == null || "".equals(subInfo.trim())){
                return new ResponseBean(500,"缺少参数：subInfo",null);
            }
            responseBean = bottleService.throwBottles(token,subInfo);
            return responseBean;
        }catch (Exception e){
            log.error("根据分页查询异常",e);
            return new ResponseBean(500,"瓶子没扔出去",null);
        }
    }

    /*@RequestMapping
    public ResponseBean RepyBottle(){

    }*/
}
