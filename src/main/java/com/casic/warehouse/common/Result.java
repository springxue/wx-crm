package com.casic.warehouse.common;

import lombok.Data;

/**
 * 统一返回对象
 */
@Data
public class Result {
    private int code;
    private String msg;
    private Long count; //总条数
    private Object data; //装前台当前页的数据

    //操作成功，调用这个方法，返回成功的数据
    public static Result ok(){
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        result.setMsg("操作成功");
        return result;
    }

    public static Result error(){
        Result result = new Result();
        result.setCode(ResultCode.ERROR);
        result.setMsg("操作失败");
        return result;
    }

    public Result Data( Object obj){
        this.setData(obj);
        return this;
    }
    public Result count(Long count){
        this.setCount(count);
        return this;
    }
    public Result msg(String msg){
        this.setMsg(msg);
        return this;
    }
}
