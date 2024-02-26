package com.ssafy.devoca.quiz.model;

import lombok.Data;

@Data
public class QuizAnswerDTO {
    private int quizWordId;
    private String quizAnswer;
    private int quizYn;
}
