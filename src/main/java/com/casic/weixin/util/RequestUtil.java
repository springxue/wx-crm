package com.casic.weixin.util;

import com.alibaba.fastjson.JSON;
import com.casic.weixin.bean.WeiXinMedia;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

public class RequestUtil {

    public static String get(String url){
        CloseableHttpClient client=null;
        CloseableHttpResponse response;

        try {
            client= HttpClients.createDefault();
            HttpGet get=new HttpGet(url);
            response=client.execute(get);
            get.addHeader("Content-Type","application/json;chartset=UTF-8");
            int statusCode=response.getStatusLine().getStatusCode();
            if(statusCode>=200&&statusCode<300){
                String json= EntityUtils.toString(response.getEntity(),"UTF-8");
                System.out.println(json);
                return json;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            if(client!=null){
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    public static String post(String url,String data,String type){

        CloseableHttpClient client=null;
        CloseableHttpResponse response;

        try {
            client= HttpClients.createDefault();
            HttpPost post=new HttpPost(url);
            post.addHeader("Content-Type",type);
            StringEntity entity=new StringEntity(data, ContentType.create(type,"UTF-8"));
            post.setEntity(entity);
            response=client.execute(post);
            int statusCode=response.getStatusLine().getStatusCode();
            if(statusCode>=200&&statusCode<300){
                String json= EntityUtils.toString(response.getEntity());
                System.out.println(json);
                return json;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            if(client!=null){
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
