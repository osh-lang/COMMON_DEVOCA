package com.ssafy.devoca.quiz.model;

import lombok.Data;

import java.util.List;

@Data
public class BattleResultDTO {
    private int quizId;
    private int userIdx;
    private int score;
    private String battleUserNickname;
    private String battleUserId;
    private int battleUserScore;
    private int battleWinYn;
    private List<QuizAnswerDTO> quizAnswerDTOList;
}
