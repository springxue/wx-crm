package com.casic.warehouse.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.casic.warehouse.bean.TemplateData;
import com.casic.warehouse.bean.WxTemplate;
import com.casic.warehouse.util.CheckoutUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PushController {

    @RequestMapping("/")
    public void login(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getParameter("signature"));
        System.out.println(request.getParameter("timestamp"));
        System.out.println(request.getParameter("nonce"));
        System.out.println(request.getParameter("echostr"));
        boolean isGet = request.getMethod().toLowerCase().equals("get");
        PrintWriter print;
        if (isGet) {
            // 微信加密签名
            String signature = request.getParameter("signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 随机字符串
            String echostr = request.getParameter("echostr");
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            if (signature != null && CheckoutUtil.checkSignature(signature, timestamp, nonce)) {
                try {
                    print = response.getWriter();
                    print.write(echostr);
                    print.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
     * 微信测试账号 批量 循环推送 模板消息
     */
    @RequestMapping("/weChatPush")
    public String weChatPush() {
        try {
            List<String> ls = getOpenId(getAccessToken());
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
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + getAccessToken();
        //推送的模版 （目前为了简单测试 很多参数都写死,固定了,但也可以根据需要动态传入参数）
        WxTemplate wxTe = new WxTemplate();
        wxTe.setTouser(openid);//用户的openid（接收人，这里应该传进来的）
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

    /*
     * 获取 access_token（接口调用凭据）
     */
    @GetMapping("/getAccessToken")
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
        //System.out.println("有效时长expires_in：" + expires_in);
        return Access_Token;
    }

    /*
     * 获取 openid（关注者列表）
     */
    @GetMapping("/getOpenId")
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

}
