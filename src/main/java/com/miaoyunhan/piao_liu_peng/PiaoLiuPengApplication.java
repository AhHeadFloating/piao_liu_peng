package com.miaoyunhan.piao_liu_peng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.miaoyunhan.piao_liu_peng.mapper")
public class PiaoLiuPengApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiaoLiuPengApplication.class, args);
    }

}
