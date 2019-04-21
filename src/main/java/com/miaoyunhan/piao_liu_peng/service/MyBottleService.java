package com.miaoyunhan.piao_liu_peng.service;

import com.miaoyunhan.piao_liu_peng.configration.ResponseBean;
import com.miaoyunhan.piao_liu_peng.entity.MyBottle;

public interface MyBottleService {
    Integer insertSelective(MyBottle myBottle);

    ResponseBean findByPage(Integer pageNo, Integer pageSize, String token);

    ResponseBean del(Integer myBottleId);
}
