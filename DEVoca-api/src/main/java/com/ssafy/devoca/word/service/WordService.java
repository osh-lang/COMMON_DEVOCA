package com.ssafy.devoca.word.service;

import com.ssafy.devoca.word.model.WordDTO;
import com.ssafy.devoca.word.model.WordDetailDTO;

import java.util.List;

public interface WordService {
    public List<WordDTO> getWordList(String alphabet) throws Exception;
    public WordDetailDTO getWordDetail(int wordId) throws Exception;
    public WordDTO getWordInCard(int wordId) throws Exception;
}
