package com.miaoyunhan.piao_liu_peng.service.impl;

import com.github.pagehelper.PageHelper;
import com.miaoyunhan.piao_liu_peng.configration.ResponseBean;
import com.miaoyunhan.piao_liu_peng.entity.Account;
import com.miaoyunhan.piao_liu_peng.entity.Bottle;
import com.miaoyunhan.piao_liu_peng.mapper.BottleMapper;
import com.miaoyunhan.piao_liu_peng.service.AccountService;
import com.miaoyunhan.piao_liu_peng.service.BottleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
public class BottleServiceImpl implements BottleService {

    @Autowired
    private BottleMapper bottleMapper;
    @Autowired
    private AccountService accountService;

    @Override
    public HashMap<String, Object> findByPage() {

        List<Bottle> bottleList = bottleMapper.findByRandomPage();
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows",bottleList);
        return map;
    }

    @Override
    @Transactional
    public ResponseBean throwBottles(Bottle bottle) {
        bottle.setBottleId(null);
//        bottle.setUserId();
        int insert = bottleMapper.insert(bottle);
        //扔一个瓶子减5个金币
        Account account = accountService.findByUserId(bottle.getUserId());
        if(account.getGold() < 5){
            return new ResponseBean(500,"你的账户瓶子用完啦，快去看广告赚瓶子",null);
        }
        account.setGold(account.getGold()-5);
        accountService.updateByPrimaryKeySelective(account);
        return new ResponseBean(200,"扔了一个瓶子",null);
    }

    @Override
    public Bottle findById(Long bottleId) {
        Bottle bottle = bottleMapper.selectByPrimaryKey(bottleId);
        return bottle;
    }
}

