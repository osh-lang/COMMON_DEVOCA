package com.ssafy.devoca.quiz.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class QuizResultDTO {
    private int quizId;
    private int userIdx;
    private String userId;
    private int score;
    private List<QuizAnswerDTO> quizAnswerDTOList;
}
