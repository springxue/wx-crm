package com.casic.weixin.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUtil {
    /**
     * 发送post请求
     * @param url
     * @param param
     * @return
     */
    public static String post(String url,Object param){
        String paramJsonStr= JSON.toJSONString(param);
        CloseableHttpClient client= HttpClients.createDefault();
        HttpPost post=new HttpPost(url);
        post.addHeader("Content-Type","application/json");
        StringEntity entity=new StringEntity(paramJsonStr, ContentType.create("application/json","utf-8"));
        post.setEntity(entity);
        try {
            CloseableHttpResponse response = client.execute(post);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
