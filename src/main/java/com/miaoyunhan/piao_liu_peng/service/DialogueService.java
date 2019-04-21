package com.miaoyunhan.piao_liu_peng.service;

import com.miaoyunhan.piao_liu_peng.configration.ResponseBean;
import com.miaoyunhan.piao_liu_peng.entity.Dialogue;

public interface DialogueService {
    ResponseBean reply(String token, Dialogue dialogue);

    void delete(Dialogue dialogue);
}
