package com.ssafy.devoca.search.controller;

import com.ssafy.devoca.card.model.CardDTO;
import com.ssafy.devoca.search.model.WordAutoDTO;
import com.ssafy.devoca.search.service.SearchService;
import com.ssafy.devoca.user.service.UserService;
import com.ssafy.devoca.word.model.WordDTO;
import com.ssafy.devoca.word.model.WordRecoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<String> saveSearchKeyword(@RequestHeader("token") String token
                                                    ,@RequestBody String keyword){
        log.info("saveSearchKeyword 호출 : 검색 키워드 저장 요청");
        try{
            keyword = keyword.replaceAll("\"", "");
            int loginUserIdx = userService.loadUserIdx(token);
            searchService.saveSearchKeyword(keyword, loginUserIdx);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            log.error("검색 키워드 저장 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/word/{keyword}/{scroll}")
    public ResponseEntity<List<WordDTO>> searchWord(@PathVariable("keyword") String keyword, @PathVariable("scroll") int scroll){
        log.info("searchWord 호출 : 단어 검색 요청");
        try{
            List<WordDTO> wordList = searchService.searchWord(keyword, scroll);
            return ResponseEntity.status(HttpStatus.OK).body(wordList);
        }catch (Exception e){
            log.error("단어 검색 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/card/{keyword}/{scroll}")
    public ResponseEntity<List<CardDTO>> searchCard(@RequestHeader("token") String token
                                ,@PathVariable("keyword") String keyword, @PathVariable("scroll") int scroll){
        log.info("searchCard 호출 : 카드 검색 요청");
        try{
            int loginUserIdx = userService.loadUserIdx(token);
            List<CardDTO> cardList = searchService.searchCard(keyword, scroll, loginUserIdx);
            return ResponseEntity.status(HttpStatus.OK).body(cardList);
        }catch (Exception e){
            log.error("카드 검색 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/reco/word")
    public ResponseEntity<List<WordRecoDTO>> searchRecommendWord(){
        log.info("searchRecommendWord 호출 : 검색 조회수 상위 단어 조회");
        try{
            List<WordRecoDTO> wordList = searchService.searchRecommendWord();
            return ResponseEntity.status(HttpStatus.OK).body(wordList);
        }catch (Exception e){
            log.error("검색 조회수 상위 단어 조회 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/reco/keyword")
    public ResponseEntity<List<String>> getRecentKeyword(@RequestHeader("token") String token){
        log.info("getRecentKeyword 호출 : 최근 검색어 조회");
        try{
            int loginUserIdx = userService.loadUserIdx(token);
            List<String> keywordList = searchService.getRecentKeyword(loginUserIdx);
            return ResponseEntity.status(HttpStatus.OK).body(keywordList);
        }catch (Exception e){
            log.error("검색 조회수 상위 단어 조회 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/autocomplete")
    public ResponseEntity<List<WordAutoDTO>> getAutoCompList(){
        log.info("getAutoCompList 호출 : 자동완성용 단어 조회");
        try{
            List<WordAutoDTO> autoList = searchService.getAutoCompList();
            return ResponseEntity.status(HttpStatus.OK).body(autoList);
        }catch (Exception e){
            log.error("자동완성용 단어 조회 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
