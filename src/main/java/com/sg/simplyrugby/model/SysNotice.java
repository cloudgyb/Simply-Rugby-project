package com.sg.simplyrugby.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class SysNotice {

    /** 主键 **/
    private String id;

    /** 标题 **/
    private String title;

    /** 内容 **/
    private String content;

    /** 类型 **/
    private Integer type;

    /** 创建人id **/
    private String createId;

    /** 创建人name **/
    private String createUsername;

    /** 发信时间 **/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
}
