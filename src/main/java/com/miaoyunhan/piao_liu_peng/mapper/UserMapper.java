package com.miaoyunhan.piao_liu_peng.mapper;

import com.miaoyunhan.piao_liu_peng.entity.User;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> findAll();
}
