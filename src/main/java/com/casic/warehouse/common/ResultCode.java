package com.casic.warehouse.common;

//定义返回数据使用的状态码
public interface ResultCode {
    int SUCCESS =200;//成功状态码
    int ERROR =500; //失败状态码
    int NOAUTH =30000;//没有操作权限
}
