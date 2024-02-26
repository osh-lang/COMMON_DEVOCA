package com.ssafy.devoca.comment.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentDTO {
    private int commentId;
    private int cardId;
    private int boardId;

    private String userId;
    private String userNickName;
    private String userImg;

    private String commentContent;
    private Boolean commentPicked;
    private Timestamp commentRegistDate;
}
