package com.casic.weixin.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.casic.weixin.bean.Customer;
import com.casic.weixin.common.AccessToken;
import com.casic.weixin.dao.CommonDao;
import com.casic.weixin.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommonService {
    @Autowired
    CommonDao commonDao;
    /*
     * 获取 access_token（接口调用凭据）
     */
    public String getAccessToken() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("APPID", "wx84b9c1ec1275a3b2");  //
        params.put("APPSECRET", "53654e03dbe36bb022d5d5cad807aa0d");  //
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={APPID}&secret={APPSECRET}", String.class, params);
        String body = responseEntity.getBody();
        JSONObject object = JSON.parseObject(body);
        String Access_Token = object.getString("access_token");
        String expires_in = object.getString("expires_in");// 过期时长（两小时）
        System.out.println("有效时长expires_in：" + expires_in);
        System.out.println(Access_Token);
        return Access_Token;
    }

    /*
     * 获取 openid（关注者列表）
     */
    public List<String> getOpenId(String access_token) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("access_token", access_token);  //
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "https://api.weixin.qq.com/cgi-bin/user/get?access_token={access_token}", String.class, params
        );

        String body = responseEntity.getBody();
        // 解析成JSONObject 并获取（叫data的JSON对象）JSONObject
        JSONObject object = JSON.parseObject(body).getJSONObject("data");
        // 获取 JSONObject 中的 JSONArray
        JSONArray array = object.getJSONArray("openid");

        System.out.println("array"+array);
        // JSONArray 转成 List<Strig> 集合（注意 alibaba 的fastjson 1.2.23以下的版本 和 1.2.3 的版本不包含 toJavaList()方法 ）
        List<String> lists = array.toJavaList(String.class);
        System.out.println(lists.get(0));

        return lists;
    }

    /**
     * 获取微信用户信息
     * @param openid
     * @return
     */
    public Customer getBasicUserInfo(String openid) {
        String url="https://api.weixin.qq.com/cgi-bin/user/info?access_token="+ AccessToken.getAccessToken() +"&openid="+openid+"&lang=zh_CN";
        String userInfoJson = RequestUtil.get(url);
        Customer user=JSON.parseObject(userInfoJson, Customer.class);
        System.out.println(user);
        return user;
    }

    public void addCustomer(Customer customer) {
        commonDao.addCustomer(customer);
    }

}
