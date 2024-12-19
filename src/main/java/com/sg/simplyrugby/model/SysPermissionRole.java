package com.sg.simplyrugby.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SysPermissionRole implements Serializable {
    private String id;

    private String roleId;

    private String permissionId;

    private static final long serialVersionUID = 1L;


    
    
}