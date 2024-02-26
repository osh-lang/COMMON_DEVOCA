package com.ssafy.devoca.quiz.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class QuizListDTO {
    private int quizId;
    private int battleId;
    private Timestamp participateDate;
    private int rank;
    private String battleUserNickname;
    private int battleWinYN;    // 이기면 1, 지면 0, 비기면 2
}
