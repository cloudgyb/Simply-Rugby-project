package com.sg.simplyrugby.model.simply;


import lombok.Data;

@Data
public class Team {

    /**
     * 球队ID
     */
    private String id;

    /**
     * 球队名称
     */
    private String name;

    /**
     * 球队昵称
     */
    private String nickname;

    /**
     * 成立年份
     */
    private Integer foundationYear;

    /**
     * 所属联赛ID
     */
    private String leagueId;

    /**
     * 主场名称
     */
    private String stadium;

    /**
     * 球队所在地
     */
    private String location;

    /**
     * 主教练姓名
     */
    private String coach;

    /**
     * 球队官方网站
     */
    private String website;

    /**
     * 球队标志URL
     */
    private String logoUrl;
}
