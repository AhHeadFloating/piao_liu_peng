package com.miaoyunhan.piao_liu_peng.controller;

import com.miaoyunhan.piao_liu_peng.configration.ResponseBean;
import com.miaoyunhan.piao_liu_peng.entity.Account;
import com.miaoyunhan.piao_liu_peng.entity.User;
import com.miaoyunhan.piao_liu_peng.service.AccountService;
import com.miaoyunhan.piao_liu_peng.service.UserService;
import com.miaoyunhan.piao_liu_peng.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    /**
     * @Author 宋久斌
     * @Description ：获取用户信息
     * @Date 2019/4/14 22:30
     */
//    @RequiresAuthentication
    @RequestMapping("getUserInfo")
    public ResponseBean getUserInfo(@RequestHeader(value = "Authorization")String token){
        User userInfo = JWTUtil.getUserInfo(token);
        User user = userService.findById(userInfo.getUserId());
        Account account = accountService.findByUserId(user.getUserId());
        user.setPassword(null);
        HashMap<String, Object> map = new HashMap<>();
        map.put("user",user);
        map.put("account",account);
        return new ResponseBean(200,"获取用户信息",map);
    }

    @RequestMapping(value = "register")
    public ResponseBean register(User user){
        ResponseBean responseBean = null;
        try{
            user.setUserId(null);
            responseBean = userService.register(user);
        }catch (Exception e){
            log.error("注册用户异常：",e);
            return new ResponseBean(500,"注册失败啦",null);
        }
        return responseBean;
    }

    /**
     * @Author 宋久斌
     * @Description ：更新用户信息
     * @Date 2019/4/19 18:16
     */
    @RequestMapping(value = "updateUser")
    public ResponseBean updateUser(@RequestHeader(value = "Authorization")String token, User user){
        try{
            User userInfo = JWTUtil.getUserInfo(token);
            user.setUserId(userInfo.getUserId());
            Integer row = userService.updateByPrimaryKeySelective(user);
            if(row <= 0){
                throw new Exception();
            }
            return new ResponseBean(200,"更新完成",null);
        }catch (Exception e){
            log.error("更新用户信息异常",e);
            return new ResponseBean(500,"因为天气原因，并没有更新成功",null);
        }
    }

    @RequestMapping(value = "checkIn")
    public ResponseBean checkIn(@RequestHeader(value = "Authorization")String token){
        ResponseBean responseBean = null;
        try{
            responseBean = userService.checkIn(token);
            return responseBean;
        }catch (Exception e){
            log.error("签到异常",e);
            return new ResponseBean(500,"签到失败，未知原因",null);
        }
    }

    //测试用
    @RequestMapping(value = "findAll")
    public List<User> findAll(){
        List<User> userList = null;
        try{
            userList = userService.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return userList;
    }

}
