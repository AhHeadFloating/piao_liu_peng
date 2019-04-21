package com.miaoyunhan.piao_liu_peng.service.impl;

import com.miaoyunhan.piao_liu_peng.configration.ResponseBean;
import com.miaoyunhan.piao_liu_peng.entity.Account;
import com.miaoyunhan.piao_liu_peng.entity.User;
import com.miaoyunhan.piao_liu_peng.mapper.UserMapper;
import com.miaoyunhan.piao_liu_peng.service.AccountService;
import com.miaoyunhan.piao_liu_peng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

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
        List<User> userList = userMapper.select(user);
        ResponseBean responseBean = null;
        if(userList == null || userList.size() == 0){
            if(user.getSex() == null){
                user.setSex(0);
            }
            if(user.getAddress() == null){
                user.setAddress("北京");
            }
            Random random = new Random();
            String name = "";
            for (int i = 0; i < 7; i++) {
                int r = random.nextInt('z' - 'a');
                name += r;
            }
            user.setNickName(name);
            userMapper.insertSelective(user);
            Account account = new Account();
            account.setUserId(user.getUserId());
            account.setGold(200);
            accountService.insertSelective(account);
            responseBean = new ResponseBean(200,"注册成功啦",null);

        }else{
            responseBean = new ResponseBean(500,"这个手机号已经注册过了",null);
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
}
