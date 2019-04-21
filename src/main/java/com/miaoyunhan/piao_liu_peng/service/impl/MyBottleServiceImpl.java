package com.miaoyunhan.piao_liu_peng.service.impl;

import com.github.pagehelper.PageHelper;
import com.miaoyunhan.piao_liu_peng.configration.ResponseBean;
import com.miaoyunhan.piao_liu_peng.entity.Dialogue;
import com.miaoyunhan.piao_liu_peng.entity.MyBottle;
import com.miaoyunhan.piao_liu_peng.entity.User;
import com.miaoyunhan.piao_liu_peng.mapper.MyBottleMapper;
import com.miaoyunhan.piao_liu_peng.service.DialogueService;
import com.miaoyunhan.piao_liu_peng.service.MyBottleService;
import com.miaoyunhan.piao_liu_peng.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class MyBottleServiceImpl implements MyBottleService {

    @Autowired
    private MyBottleMapper myBottleMapper;
    @Autowired
    private DialogueService dialogueService;

    @Override
    public Integer insertSelective(MyBottle myBottle) {
        Integer id = myBottleMapper.insertSelective(myBottle);
        return id;
    }

    @Override
    public ResponseBean findByPage(Integer pageNo, Integer pageSize, String token) {
        User userInfo = JWTUtil.getUserInfo(token);
        PageHelper.startPage(pageNo,pageSize);
        List<MyBottle> myBottleList = myBottleMapper.findByPage(userInfo.getUserId());
        ResponseBean responseBean = new ResponseBean(200, "查询我的瓶子列表", myBottleList);
        return responseBean;
    }

    @Override
    public ResponseBean del(Integer myBottleId) {
        myBottleMapper.deleteByPrimaryKey(myBottleId);
        Dialogue dialogue = new Dialogue();
        dialogue.setMyBottleId(myBottleId);
        dialogueService.delete(dialogue);
        ResponseBean responseBean = new ResponseBean(200, "删除成功", null);
        return responseBean;
    }
}
