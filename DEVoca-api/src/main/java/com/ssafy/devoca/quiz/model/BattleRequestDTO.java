package com.ssafy.devoca.quiz.model;

import lombok.Data;

@Data
public class BattleRequestDTO {
    String fromUserId;
    String toUserId;
    int fromUserIdx;
    int toUserIdx;
    int quizId;
    int vocaListId;
    int wordCnt;
}
