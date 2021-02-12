package com.casic.weixin;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.casic.weixin.bean.WeiXinMenu;
import com.casic.weixin.common.AccessToken;
import com.casic.weixin.service.MessageService;
import com.casic.weixin.util.HttpClientUtil;
import com.casic.weixin.util.XMLUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class WarehouseApplicationTests {

    @Test
    void contextLoads() {

    }
    @Test
    void testMenu() {
        List<WeiXinMenu> menuList= new ArrayList<>();
        WeiXinMenu menu1=new WeiXinMenu();
        menu1.setId(1);
        menu1.setName("公司简介");
        menu1.setType("view");
        menu1.setUrl("http://bah6z7.natappfree.cc/toCompanyInfo");
        menuList.add(menu1);
        WeiXinMenu menu2=new WeiXinMenu();
        List<WeiXinMenu> menu2Sub=new ArrayList<>();
        menu2.setName("测试资源");
        WeiXinMenu menu2Sub1=new WeiXinMenu();
        menu2Sub1.setId(1);
        menu2Sub1.setName("事件测试");
        menu2Sub1.setType("click");
        menu2Sub1.setKey("A0001");
        menu2Sub.add(menu2Sub1);
        WeiXinMenu menu2Sub2=new WeiXinMenu();
        menu2Sub2.setId(1);
        menu2Sub2.setName("扫描测试");
        menu2Sub2.setType("scancode_waitmsg");
        menu2Sub2.setKey("A0002");
        menu2Sub.add(menu2Sub2);
        menu2.setSub_button(menu2Sub);
        menuList.add(menu2);
        Map<String,List> menuListMap=new HashMap<>();
        menuListMap.put("button",menuList);
        String menuJsonStr=JSON.toJSONString(menuListMap);
        String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+ AccessToken.getAccessToken();
//        RestTemplate restTemplate=new RestTemplate();
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, menuJsonStr, String.class);
        String result=HttpClientUtil.post(url,menuListMap);
        System.out.println(result);


//        System.out.println(menuJsonStr);
//        System.out.println(JSON.parseObject(responseEntity.getBody()));
    }

   @Test
    public void testMap2Xml(){
        Map<String,String>map=new HashMap<>();
        map.put("name","薛振春");
        map.put("content","牛年快乐！");
       String s = XMLUtil.map2Xml(map);
       System.out.println(s);
   }

}
