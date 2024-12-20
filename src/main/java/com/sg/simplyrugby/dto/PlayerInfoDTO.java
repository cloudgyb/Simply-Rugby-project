package com.sg.simplyrugby.dto;

import com.sg.simplyrugby.model.simply.PlayerInfo;
import lombok.Data;

import java.util.List;

@Data
public class PlayerInfoDTO extends PlayerInfo {

    private List<String> coachIds;
    private List<String> coachNames;
}
