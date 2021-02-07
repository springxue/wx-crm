package com.casic.weixin.config;

import com.casic.weixin.common.AccessToken;
import com.casic.weixin.service.CommonService;
import com.casic.weixin.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class ReflashAccessTokenTask {
    @Autowired
    CommonService commonService;
    //3.添加定时任务(每隔一个小时获取一次AccessToken)
    @Scheduled(cron = "0 0 */1 * * ?")
//    @Scheduled(cron = "*/1 * * * * ?")

    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        AccessToken.setAccessToken(commonService.getAccessToken());
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }
}
