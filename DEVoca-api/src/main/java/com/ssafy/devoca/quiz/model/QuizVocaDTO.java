package com.ssafy.devoca.quiz.model;

import lombok.Data;

@Data
public class QuizVocaDTO {
    private String userId;
    private int vocalistId;
    private String vocalistName;
    private int vocaCnt;
}
