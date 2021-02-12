package com.casic.weixin.util;

import com.alibaba.fastjson.JSON;
import com.casic.weixin.bean.WeiXinMedia;
import com.casic.weixin.common.AccessToken;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MediaUtil {
    public static WeiXinMedia postMeida(String path, String type){
        String url="https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+ AccessToken.getAccessToken()+"&type="+type;

        CloseableHttpClient client=null;
        CloseableHttpResponse response=null;

        try {
            client= HttpClients.createDefault();
            HttpPost post=new HttpPost(url);
            FileBody fileBody=new FileBody(new File(path));
            HttpEntity reqEntity= MultipartEntityBuilder.create().addPart("media",fileBody).build();
            post.setEntity(reqEntity);
            response=client.execute(post);
            int statusCode=response.getStatusLine().getStatusCode();
            if(statusCode>=200&&statusCode<300){
                String json= EntityUtils.toString(response.getEntity());
                System.out.println(json);
                return JSON.parseObject(json,WeiXinMedia.class);
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

    /**
     * 根据mediaId获取临时素材
     * @param mediaId
     * @return
     */
    public static void getTempMediaByMediaId(String mediaId){
        String url="https://api.weixin.qq.com/cgi-bin/media/get?access_token="+AccessToken.getAccessToken()+"&media_id="+mediaId;
        CloseableHttpClient client=null;
        CloseableHttpResponse response=null;

        try {
            client= HttpClients.createDefault();
            HttpGet get=new HttpGet(url);
            response=client.execute(get);
            int statusCode=response.getStatusLine().getStatusCode();
            if(statusCode>=200&&statusCode<300){
                FileUtils.copyInputStreamToFile(response.getEntity().getContent(),new File("D:/abc.jpg")); ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(client!=null){
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
