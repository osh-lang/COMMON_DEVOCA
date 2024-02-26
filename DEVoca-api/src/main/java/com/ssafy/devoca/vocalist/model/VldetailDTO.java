package com.ssafy.devoca.vocalist.model;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class VldetailDTO {
    private Integer vocaListId;
    private String vocalistName;

    private String userId;
    private String userNickName;
    private String userIntro;
    private String userImg;

    private Integer cardId;
    private String cardContent;
    private Integer cardRepostCnt;
    private Timestamp cardRegistDate;
    private Timestamp cardUpdateDate;
    private String cardLikeYN;
    private String cardLikeCnt;

    private Integer wordId;
    private String wordNameKr;
    private String wordNameEn;
    private String wordSumm;
    private String wordDef;

    private Integer originCardId;
    private String originUserNickName;
    private String originUserImg;
    private String originCardContent;
    private Integer originAuthorIdx;

}
