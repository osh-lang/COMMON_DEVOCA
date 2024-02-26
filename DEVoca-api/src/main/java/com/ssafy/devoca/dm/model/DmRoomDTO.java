package com.ssafy.devoca.dm.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DmRoomDTO {

    private String roomUuid;

    private String userImg;
    private String userNickName;

    private String lastMessage;
    private Timestamp lastSendDate;
    private int unReadCnt;
}