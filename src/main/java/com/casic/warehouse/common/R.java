package com.casic.warehouse.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

//定义具体的数据返回格式
@Data
public class R {

    private Boolean success;
    private Integer code;
    private String message;
    private Map<String,Object> data=new HashMap<>();

    public R (){};

    //操作成功，调用这个方法，返回成功的数据
    public static R ok(){
       R r = new R();
       r.setSuccess(true);
       r.setCode(ResultCode.SUCCESS);
       r.setMessage("操作成功");
       return r;
    }

    //操作失败，调用这个方法，返回失败数据
    public static R error(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("操作失败666");
        return r;
    }

    //链式编程
    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R Message(String message){
        this.setMessage(message);
        return this;
    }
    public R Data(String key,Object value){
        this.data.put(key,value);
        return this;
    }
    public R Data(Map<String,Object> data){
        this.setData(data);
        return this;
    }
    public R Code(Integer code){
        this.setCode(code);
        return this;
    }
}
