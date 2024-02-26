package com.ssafy.devoca.dm.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@ToString
public class DmDTO implements Serializable {
    private int roomIdx;
    private String sendUserId;
    private String dmContent;
    private Timestamp dmSendDate;
    private int dmBattleQuizId;
}
