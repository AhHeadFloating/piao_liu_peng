package com.miaoyunhan.piao_liu_peng.service;

import com.miaoyunhan.piao_liu_peng.configration.ResponseBean;
import com.miaoyunhan.piao_liu_peng.entity.User;

import java.util.List;

public interface UserService {

    User findById(Integer userId);

    List<User> findAll();

    ResponseBean register(User user);

    User findByPhone(String phone);
}
