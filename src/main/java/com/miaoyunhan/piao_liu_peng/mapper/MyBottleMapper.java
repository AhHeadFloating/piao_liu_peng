package com.miaoyunhan.piao_liu_peng.mapper;

import com.miaoyunhan.piao_liu_peng.entity.MyBottle;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface MyBottleMapper extends BaseMapper<MyBottle> {
    List<MyBottle> findByPage(Integer userId);
}
