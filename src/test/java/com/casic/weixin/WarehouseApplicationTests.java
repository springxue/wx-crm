package com.casic.weixin;

import com.alibaba.fastjson.JSON;
import com.casic.weixin.bean.WeiXinMedia;
import com.casic.weixin.bean.WeiXinMenu;
import com.casic.weixin.common.AccessToken;
import com.casic.weixin.service.CommonService;
import com.casic.weixin.util.HttpClientUtil;
import com.casic.weixin.util.MediaUtil;
import com.casic.weixin.util.XMLUtil;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class WarehouseApplicationTests {
    @Autowired
    CommonService commonService;

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
        menu1.setUrl("http://6xkc6a.natappfree.cc/toCompanyInfo");
        menuList.add(menu1);
        WeiXinMenu menu2=new WeiXinMenu();
        List<WeiXinMenu> menu2Sub=new ArrayList<>();
        menu2.setName("我的");
        WeiXinMenu menu2Sub1=new WeiXinMenu();
        menu2Sub1.setId(1);
        menu2Sub1.setName("我的商机");
        menu2Sub1.setType("view");
        menu2Sub1.setUrl("http://6xkc6a.natappfree.cc/myBusiness");
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
        Map<String,Object>map=new HashMap<>();
        map.put("name","薛振春");
        map.put("content","牛年快乐！");
       String s = XMLUtil.map2Xml(map);
       System.out.println(s);
   }
    @Test
    public void testPoseMedia(){
        WeiXinMedia meida=MediaUtil.postMeida("D:\\wildHunt.jpg","image");
        System.out.println(meida);
    }
    @Test
    public void testGetTempMedia(){
        MediaUtil.getTempMediaByMediaId("wHVsXUPbRgNHWOxFVEkMZ7OHEuWMTMCSKMydpbqQffjzu3AulLZ6WR5ZByUhNpAU");
    }

    @Test
    public void testGetBasicUserInfo(){
        commonService.getBasicUserInfo("oSMVg6lHQ3bpreSGhWlyCCZgsDI4");
    }
}
