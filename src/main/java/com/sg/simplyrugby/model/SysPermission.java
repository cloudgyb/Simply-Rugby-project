package com.sg.simplyrugby.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysPermission implements Serializable {
    private String id;

    private String name;

    private String descripion;

    private String url;

    private Integer isBlank;

    private String pid;

    private String perms;

    private Integer type;

    private String icon;

    private Integer orderNum;
    
    private Integer visible;
    
    private Integer childCount;
    
    private static final long serialVersionUID = 1L;

}