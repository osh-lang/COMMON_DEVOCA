package com.ssafy.devoca.word.model;

import lombok.Data;

import java.util.List;

@Data
public class WordDTO {
    private int wordId;
    private String wordNameKr;
    private String wordNameEn;
    private String wordDef;
    private String wordSumm;
    private String wordLink;
    private List<String> wordCategory;
}
