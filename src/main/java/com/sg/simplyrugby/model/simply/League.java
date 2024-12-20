package com.sg.simplyrugby.model.simply;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class League {

    /**
     * 联赛ID
     */
    @Id
    private String id;

    /**
     * 联赛名称
     */
    private String name;

    /**
     * 联赛所在国家
     */
    private String country;

    /**
     * 联赛级别
     */
    private String level;

    /**
     * 运动类型
     */
    private String sportType;

    /**
     * 联赛标志URL
     */
    private String logoUrl;

    /**
     * 联赛官方网站
     */
    private String website;

    /**
     * 成立年份
     */
    private Integer foundingYear;
}