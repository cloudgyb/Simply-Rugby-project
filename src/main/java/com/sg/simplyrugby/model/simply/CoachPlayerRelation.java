package com.sg.simplyrugby.model.simply;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class CoachPlayerRelation {

    private String id;

    private String coachId;

    private String playerId;
}
