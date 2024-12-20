package com.sg.simplyrugby.model.simply;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Coach {

    private String id;
    private String firstName;
    private String lastName;
    private String fullName; // 虚拟列，不映射到数据库
    private String nationality;
    private Date dateOfBirth;
    private Integer age; // 虚拟列，不映射到数据库
    private String gender;
    private Integer currentTeamId;
}
