package com.casic.weixin.bean;

import lombok.Data;

import java.util.List;

@Data
public class WeiXinMedia {
    private String type;
    private String media_id;
    private String created_at;
    private List item;
}
