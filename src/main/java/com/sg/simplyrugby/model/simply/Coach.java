package com.sg.simplyrugby.model.simply;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Coach {

    private String id;
    private String fullName;
    private String nationality;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private Integer age;
    private String gender;
    private String currentTeamId;
}
