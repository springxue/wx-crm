package com.casic.weixin.controller;


import com.alibaba.fastjson.JSON;
import com.casic.weixin.bean.TemplateData;
import com.casic.weixin.bean.WxTemplate;
import com.casic.weixin.common.AccessToken;
import com.casic.weixin.service.CommonService;
import com.casic.weixin.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MessageController {
    @Autowired
    CommonService commonService;
    @Autowired
    MessageService messageService;
    /**
     * 接收微信服务器返回的消息
     */
    @PostMapping("/")
    public void getMessage(HttpServletRequest request, HttpServletResponse response){
        messageService.autoHandel(request,response);
    }

    /*
     * 微信测试账号 批量 循环推送 模板消息
     */
    @RequestMapping("/weChatPush")
    @ResponseBody
    public String weChatPush() {
        try {
            List<String> ls = commonService.getOpenId(AccessToken.getAccessToken());
            for (int i=0;i<ls.size();i++){
                System.out.println(ls.get(i));
                push(ls.get(i),"","");
            }
            return ("推送成功");
        }catch (Exception e){
            e.printStackTrace();
            return ("推送失败");
        }
    }

    /*
     * 微信测试账号 发送模板消息
     */
    @PostMapping("/push")
    public Object push(String openid, String template_id, String jumpurl) {

        RestTemplate restTemplate = new RestTemplate();
        //为了简单测试 这里每次都获取最新的access_token（在实际开发中，应该在 access_token 快过期时再重新获取）
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + AccessToken.getAccessToken();
        //推送的模版 （目前为了简单测试 很多参数都写死,固定了,但也可以根据需要动态传入参数）
        WxTemplate wxTe = new WxTemplate();
        wxTe.setTouser(openid);//用户的openid（接收人，这里应该传进来的）
        System.out.println("openid: "+openid);
        wxTe.setTemplate_id("DGNgq1RhqyohvSsMLsmkgAvgWtk9hRubNFw_UIZJuyo");//订阅消息模板id
        wxTe.setUrl("www.baidu.com");//跳转链接

        //日期格式定义 转换
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = dateformat.format(new Date());

        Map<String, TemplateData> m = new HashMap<>();
        m.put("name", new TemplateData("薛振春","#F7A36F"));
        m.put("context", new TemplateData("赚钱好机会，联系：","#79CCE9"));
        m.put("phone", new TemplateData("19931262362","#79CCE9"));
        wxTe.setData(m);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, wxTe, String.class);
        return JSON.parseObject(responseEntity.getBody());
    }



}
