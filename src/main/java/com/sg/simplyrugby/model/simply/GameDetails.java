package com.sg.simplyrugby.model.simply;

import lombok.Data;




import java.util.Date;

/**
 * GameDetails 实体类，对应数据库中的 game_details 表
 */
@Data
public class GameDetails {

    /**
     * 唯一标识每场比赛的ID
     */
    private String id;

    private String gameName;

    /**
     * 主队的ID
     */
    private String homeTeamId;

    /**
     * 客队的ID
     */
    private String awayTeamId;

    /**
     * 比赛发生的日期和时间
     */
    private Date dateTime;

    /**
     * 比赛举行的体育场
     */
    private String stadium;

    /**
     * 主队的得分
     */
    private Integer homeTeamScore;

    /**
     * 客队的得分
     */
    private Integer awayTeamScore;

    /**
     * 裁判的名字
     */
    private String referee;

    /**
     * 比赛的观众人数
     */
    private Integer attendance;

    /**
     * 比赛的详细报告
     */
    private String matchReport;

    /**
     * 主队的射门次数
     */
    private Integer homeTeamShots;

    /**
     * 客队的射门次数
     */
    private Integer awayTeamShots;

    /**
     * 主队的进球数
     */
    private Integer homeTeamGoals;

    /**
     * 客队的进球数
     */
    private Integer awayTeamGoals;

    /**
     * 主队获得的红牌数
     */
    private Integer homeTeamRedCards;

    /**
     * 客队获得的红牌数
     */
    private Integer awayTeamRedCards;

    /**
     * 主队获得的黄牌数
     */
    private Integer homeTeamYellowCards;

    /**
     * 客队获得的黄牌数
     */
    private Integer awayTeamYellowCards;
}

