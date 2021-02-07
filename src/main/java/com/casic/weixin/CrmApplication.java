package com.casic.weixin;

import com.casic.weixin.common.AccessToken;
import com.casic.weixin.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@SpringBootApplication
@Slf4j
@MapperScan("com.casic.weixin.dao")
public class CrmApplication implements CommandLineRunner {
    @Autowired
    CommonService commonService;

    public static void main(String[] args) {
        SpringApplication.run(CrmApplication.class, args);
    }

    @Override
    public void run(String... args){
        AccessToken.setAccessToken(commonService.getAccessToken());
        log.info("启动方法");
    }
}
