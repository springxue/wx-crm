package com.casic.weixin.bean;

import lombok.Data;

import java.util.List;

@Data
public class WeiXinMenu {
    private int id;
    private String name;
    private String type;
    private String key;
    private int pid;
    private String Url;
    private List<WeiXinMenu> sub_button;
}
