package com.ssafy.devoca.search.service;

import com.ssafy.devoca.card.model.CardDTO;
import com.ssafy.devoca.search.model.WordAutoDTO;
import com.ssafy.devoca.search.model.mapper.SearchMapper;
import com.ssafy.devoca.word.model.WordDTO;
import com.ssafy.devoca.word.model.WordRecoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService{

    private final SearchMapper searchMapper;

    @Override
    public void saveSearchKeyword(String keyword, int loginUserIdx) throws Exception {
        log.info("keyword : " , keyword);
        searchMapper.saveSearchKeyword(keyword, loginUserIdx);
    }

    @Override
    public List<WordDTO> searchWord(String keyword, int scroll) throws Exception{
        return searchMapper.searchWord(keyword, scroll * 10);
    }

    @Override
    public List<CardDTO> searchCard(String keyword, int scroll, int loginUserIdx) throws Exception {
        return searchMapper.searchCard(keyword, scroll * 10, loginUserIdx);
    }

    @Override
    public List<WordRecoDTO> searchRecommendWord() throws Exception {
        return searchMapper.searchRecommendWord();
    }

    @Override
    public List<String> getRecentKeyword(int loginUserIdx) throws Exception {
        return searchMapper.getRecentKeyword(loginUserIdx);
    }

    @Override
    public List<WordAutoDTO> getAutoCompList() throws Exception {
        return searchMapper.getAutoCompList();
    }
}
