package com.miaoyunhan.piao_liu_peng.service.impl;

import com.miaoyunhan.piao_liu_peng.configration.ResponseBean;
import com.miaoyunhan.piao_liu_peng.entity.Account;
import com.miaoyunhan.piao_liu_peng.entity.User;
import com.miaoyunhan.piao_liu_peng.mapper.UserMapper;
import com.miaoyunhan.piao_liu_peng.service.AccountService;
import com.miaoyunhan.piao_liu_peng.service.UserService;
import com.miaoyunhan.piao_liu_peng.utils.DateUtils;
import com.miaoyunhan.piao_liu_peng.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AccountService accountService;

    @Override
    public User findById(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> all = userMapper.findAll();
        return all;
    }

    @Override
    @Transactional
    public ResponseBean register(User user) {
        User userQuery = new User();
        userQuery.setPhone(user.getPhone());
        List<User> userList = userMapper.select(userQuery);
        ResponseBean responseBean = null;
        //如果没有注册过才可以注册
        if (userList == null || userList.size() == 0) {
            //注册完用户性别默认是女，地址默认是北京
            if (user.getSex() == null) {
                user.setSex(0);
            }
            if (user.getAddress() == null) {
                user.setAddress("北京");
            }
            //创建随机昵称
            Random random = new Random();
            String name = "";
            for (int i = 0; i < 7; i++) {
                int r = random.nextInt('z' - 'a');
                name += r;
            }
            user.setNickName(name);
            userMapper.insertSelective(user);

            //创建账户，默认20个金币
            Account account = new Account();
            account.setUserId(user.getUserId());
            account.setGold(20);
            accountService.insertSelective(account);
            responseBean = new ResponseBean(200, "注册成功啦", null);

        } else {
            responseBean = new ResponseBean(500, "这个手机号已经注册过了", null);
        }

        return responseBean;
    }

    @Override
    public User findByPhone(String phone) {
        User user = new User();
        user.setPhone(phone);
        user = userMapper.selectOne(user);
        return user;
    }

    @Override
    public Integer updateByPrimaryKeySelective(User user) {
        int i = userMapper.updateByPrimaryKeySelective(user);
        return i;
    }

    @Override
    public ResponseBean checkIn(String token) {
        User user = userMapper.selectByPrimaryKey(JWTUtil.getUserInfo(token).getUserId());
        if(user == null){
            return new ResponseBean(500,"用户不存在,请查看登录状态",null);
        }else{
            Account byUserId = accountService.findByUserId(user.getUserId());
            //比较最后一次签到时间，如果签到时间大于上次签到时间才可以继续签到（粒度为天）
            Date fistTimeByDate = DateUtils.getFistTimeByDate(new Date());
            if(byUserId.getCheckInDate() == null || fistTimeByDate.getTime() > byUserId.getCheckInDate().getTime()){
                byUserId.setGold(byUserId.getGold() + 5);
                byUserId.setCheckInDate(fistTimeByDate);
                accountService.updateByPrimaryKeySelective(byUserId);
            }else{
                return new ResponseBean(500,"今天已经签到过了，明天在来吧",null);
            }
        }
        return new ResponseBean(200,"签到成功",null);
    }

}