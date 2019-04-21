package com.miaoyunhan.piao_liu_peng.service;

import com.miaoyunhan.piao_liu_peng.entity.Account;

public interface AccountService {
    Account findByUserId(Integer userId);

    void updateByPrimaryKeySelective(Account account);

    void insertSelective(Account account);
}
