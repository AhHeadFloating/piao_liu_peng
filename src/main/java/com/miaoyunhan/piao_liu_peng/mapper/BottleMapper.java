package com.miaoyunhan.piao_liu_peng.mapper;

import com.miaoyunhan.piao_liu_peng.entity.Bottle;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface BottleMapper extends BaseMapper<Bottle> {
    List<Bottle> findByRandomPage();
}
