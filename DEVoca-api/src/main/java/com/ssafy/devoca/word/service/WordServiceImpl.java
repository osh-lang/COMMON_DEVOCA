package com.ssafy.devoca.word.service;

import com.ssafy.devoca.search.model.mapper.SearchMapper;
import com.ssafy.devoca.word.model.NewsApiResponseDTO;
import com.ssafy.devoca.word.model.NewsDTO;
import com.ssafy.devoca.word.model.WordDTO;
import com.ssafy.devoca.word.model.WordDetailDTO;
import com.ssafy.devoca.word.model.mapper.WordMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WordServiceImpl implements WordService{

    private final WordMapper wordMapper;

    @Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.client.secret}")
    private String clientSecret;

    /**
     * 단어 사전 진입 시 단어 목록 조회
     *
     * @return 단어 목록
     * @throws Exception
     */
    @Override
    public List<WordDTO> getWordList(String alphabet) throws Exception {
        return wordMapper.getWordList(alphabet);
    }

    /**
     * 단어 클릭 시 상세 조회
     *
     * @param wordId 해당 단어 아이디
     * @return 단어 상세 (단어 정보, 뉴스 데이터, 카드 데이터)
     * @throws Exception
     */
    @Override
    public WordDetailDTO getWordDetail(int wordId) throws Exception {
        WordDetailDTO wordDetailDTO = new WordDetailDTO();

        // 단어 정보 가져오기
        WordDTO wordDTO = wordMapper.getWordDetail(wordId);

        log.info("wordDTO 테스트 {}", wordDTO);

        // 뉴스 정보 가져오기
        String searchText = wordDTO.getWordNameKr() + " " + wordDTO.getWordNameEn();
        List<NewsDTO> newsList = getNews(searchText);

        // wordDetailDTO에 저장
        wordDetailDTO.setWordDTO(wordDTO);
        wordDetailDTO.setNewsList(newsList);

        // 단어 조회수 업데이트
        wordMapper.updateWordViewedCnt(wordId);

        return wordDetailDTO;
    }

    /**
     * 자동완성으로 선택한 단어 정보 불러오기
     * @param wordId 선택한 단어
     * @return 단어 데이터
     * @throws Exception
     */
    @Override
    public WordDTO getWordInCard(int wordId) throws Exception {
        return wordMapper.getWordInCard(wordId);
    }

    /**
     * 뉴스 api로부터 뉴스 데이터 받아오기
     *
     * @param searchText 한국어 검색어 + " " + 영어 검색어
     * @return 뉴스 데이터 리스트
     */
    public List<NewsDTO> getNews(String searchText) {
        log.info("getNews 호출 : {}", searchText);

        String display = "6";
        String newsApiURL = "https://openapi.naver.com/v1/search/news?query=" + searchText + "&display=" + display;

        WebClient webClient = WebClient.builder()
                .baseUrl(newsApiURL)
                .defaultHeader("X-Naver-Client-Id", clientId )
                .defaultHeader("X-Naver-Client-Secret", clientSecret)
                .build();

        Flux<NewsApiResponseDTO> newsApiResponseFlux = webClient.get()
                .retrieve()
                .bodyToMono(NewsApiResponseDTO.class)
                .flatMapMany(response -> Flux.fromIterable(Collections.singletonList(response)))
                .log();

        newsApiResponseFlux.subscribe(newsApiResponseDTO -> {
            System.out.println("LastBuildDate: " + newsApiResponseDTO.getLastBuildDate());
            System.out.println("Total: " + newsApiResponseDTO.getTotal());
            System.out.println("Start: " + newsApiResponseDTO.getStart());
            System.out.println("Display: " + newsApiResponseDTO.getDisplay());
            System.out.println("Items: " + newsApiResponseDTO.getItems().toString());
        });

        List<NewsDTO> newsList = newsApiResponseFlux
                .flatMap(response -> Flux.fromIterable(response.getItems()))
                .collectList()
                .block();

        return newsList;
    }
}
