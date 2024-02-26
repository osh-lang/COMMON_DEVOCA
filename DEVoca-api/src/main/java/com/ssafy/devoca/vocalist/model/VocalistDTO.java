package com.ssafy.devoca.vocalist.model;

import lombok.Data;

@Data
public class VocalistDTO {

    private Integer userIdx;
    private Integer vocalistId;
    private Integer cardId;
    private Integer vlWordCard; // 의견 카드 : 1, 단어 : 2
    private String vocalistName;
    private String includeYN; // 특정 단어의 포함 여부
}
