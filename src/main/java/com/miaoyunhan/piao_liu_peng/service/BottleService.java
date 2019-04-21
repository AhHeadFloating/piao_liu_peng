package com.miaoyunhan.piao_liu_peng.service;

import com.miaoyunhan.piao_liu_peng.configration.ResponseBean;
import com.miaoyunhan.piao_liu_peng.entity.Bottle;

import java.util.HashMap;

public interface BottleService {
    HashMap<String, Object> findByPage();

    Bottle findById(Long bottleId);

    ResponseBean throwBottles(String token, String subInfo);
}
