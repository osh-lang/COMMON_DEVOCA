package com.ssafy.devoca.quiz.model;

import lombok.Data;

import java.util.List;

@Data
public class QuizDTO {
    private int wordId;
    private String wordNameKr;
    private String wordNameEn;
    private String wordSumm;
}
