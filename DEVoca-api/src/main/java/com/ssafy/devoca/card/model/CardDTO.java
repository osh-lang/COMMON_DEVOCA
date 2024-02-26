package com.ssafy.devoca.card.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CardDTO {
    // 카드 등록, 상세조회, 수정에 사용
    private String userId;
    private String userNickName;
    private String userIntro;
    private String userImg;

    private int wordId;
    private String wordNameKr;
    private String wordNameEn;
    private String wordSumm;

    private int originCardId;
    private String originUserNickName;
    private String originUserImg;
    private String originCardContent;

    private int cardId;
    private String cardContent;
    private Boolean cardLikeYN;
    private int cardLikeCnt;
    private int cardRepostCnt;
    private String cardLink;
    private String cardRelatedKeyword;  // DB로 보내기 위해 가공한 String 형태
    private String[] cardRelatedKeywordList;    // frontend에서 받아온 배열 형태
    private Timestamp cardRegistDate;
    private Timestamp cardUpdateDate;
}
