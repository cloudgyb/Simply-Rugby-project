package com.sg.simplyrugby.model.simply;


import lombok.Data;

import java.util.Date;

@Data
public class Coach {

    private String id;
    private String fullName;
    private String nationality;
    private Date dateOfBirth;
    private Integer age;
    private String gender;
    private String currentTeamId;
}
