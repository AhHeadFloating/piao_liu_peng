package com.miaoyunhan.piao_liu_peng.service.impl;

import com.miaoyunhan.piao_liu_peng.entity.Account;
import com.miaoyunhan.piao_liu_peng.mapper.AccountMapper;
import com.miaoyunhan.piao_liu_peng.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account findByUserId(Integer userId) {
        Account account = new Account();
        account.setUserId(userId);
        account = accountMapper.selectOne(account);
        return account;
    }

    @Override
    public void updateByPrimaryKeySelective(Account account) {
        accountMapper.updateByPrimaryKeySelective(account);
    }

    @Override
    public void insertSelective(Account account) {
        accountMapper.insertSelective(account);
    }
}
