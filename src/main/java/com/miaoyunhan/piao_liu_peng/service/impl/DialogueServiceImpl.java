package com.miaoyunhan.piao_liu_peng.service.impl;

import com.miaoyunhan.piao_liu_peng.configration.ResponseBean;
import com.miaoyunhan.piao_liu_peng.entity.Bottle;
import com.miaoyunhan.piao_liu_peng.entity.Dialogue;
import com.miaoyunhan.piao_liu_peng.entity.MyBottle;
import com.miaoyunhan.piao_liu_peng.entity.User;
import com.miaoyunhan.piao_liu_peng.mapper.DialogueMapper;
import com.miaoyunhan.piao_liu_peng.mapper.MyBottleMapper;
import com.miaoyunhan.piao_liu_peng.service.BottleService;
import com.miaoyunhan.piao_liu_peng.service.DialogueService;
import com.miaoyunhan.piao_liu_peng.service.MyBottleService;
import com.miaoyunhan.piao_liu_peng.service.UserService;
import com.miaoyunhan.piao_liu_peng.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DialogueServiceImpl implements DialogueService {

    @Autowired
    private BottleService bottleService;
    @Autowired
    private UserService userService;
    @Autowired
    private MyBottleMapper myBottleMapper;
    @Autowired
    private DialogueMapper dialogueMapper;

    @Override
    @Transactional
    public ResponseBean reply(String token, Dialogue dialogue) {
        //当前用户
        User userInfo = JWTUtil.getUserInfo(token);
        dialogue.setSendUserId(userInfo.getUserId());
        Bottle bottle = bottleService.findById(dialogue.getBottleId());
        User byId = userService.findById(bottle.getUserId());

        //如果不携带 我的瓶子Id 就创建一个我的瓶子
        if(dialogue.getMyBottleId() == null){
            MyBottle myBottle = new MyBottle(null,
                    userInfo.getUserId(),
                    bottle.getUserId(),
                    userInfo.getNickName(),
                    byId.getNickName(),
                    dialogue.getReplyInfo());
            myBottleMapper.insertSelective(myBottle);
            //创建我的瓶子
            dialogue.setMyBottleId(myBottle.getMyBottleId());
        }
        dialogue.setSendUserId(userInfo.getUserId());
        dialogue.setAcceptUserId(byId.getUserId());
        dialogueMapper.insertSelective(dialogue);

//        dialogue.setAcceptUserId();
        return new ResponseBean(200,"回复成功！",null);
    }

    @Override
    public void delete(Dialogue dialogue) {
        dialogueMapper.delete(dialogue);
    }
}
