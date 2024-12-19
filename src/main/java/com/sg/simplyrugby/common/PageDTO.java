package com.sg.simplyrugby.common;

import lombok.Data;

@Data
public class PageDTO {

    private int page;//页码
    private int limit;//数量
    private String searchText;
}
