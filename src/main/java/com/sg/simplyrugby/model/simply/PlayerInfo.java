package com.sg.simplyrugby.model.simply;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("player_info")
public class PlayerInfo {
    private String id;
    private String name;
    private int age;
    private BigDecimal height;
    private BigDecimal weight;
    private String position;
    private String teamId;
    private Integer jerseyNumber;
    private String nationality;
    private Date birthDate;
    private Integer careerStartYear;
    private Date contractExpiration;
}
