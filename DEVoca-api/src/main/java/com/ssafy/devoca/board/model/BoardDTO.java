package com.ssafy.devoca.board.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BoardDTO {
    private String userNickName;
    private String userId;
    private Integer userIdx;
    private String userImg;
    private Integer boardId;
    private String boardTitle;
    private String boardContent;
    private Integer boardType;
    private Timestamp boardRegistDate;
    private Timestamp boardUpdateDate;
    private String updateYN; // 수정 이력 여부
}