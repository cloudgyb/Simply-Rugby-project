package com.sg.simplyrugby.model.simply;

import lombok.Data;


import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 训练课表
 */
@Data
public class TrainingSession implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 训练课名称
     */
    private String name;

    /**
     * 训练日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    /**
     * 训练时间
     */
    @JsonFormat(pattern = "HH:mm:ss")
    private Date time;

    /**
     * 教练ID
     */
    private String coachId;

    /**
     * 球员ID
     */
    private String playerId;

    /**
     * 训练地点
     */
    private String location;

    /**
     * 训练课描述
     */
    private String description;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;
}
