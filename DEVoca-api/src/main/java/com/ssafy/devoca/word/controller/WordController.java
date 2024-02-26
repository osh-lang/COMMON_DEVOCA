package com.ssafy.devoca.word.controller;

import com.ssafy.devoca.card.model.CardDTO;
import com.ssafy.devoca.card.service.CardService;
import com.ssafy.devoca.user.service.UserService;
import com.ssafy.devoca.word.model.NewsDTO;
import com.ssafy.devoca.word.model.WordDTO;
import com.ssafy.devoca.word.model.WordDetailDTO;
import com.ssafy.devoca.word.service.WordService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


/**
 * 단어 사전 Controller
 *
 * @author kimjuyi
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/dictionary")
public class WordController {

    private final WordService wordService;
    private final UserService userService;
    private final CardService cardService;

    /**
     * 단어 사전 진입 시 단어 목록 조회
     *
     * @return HttpStatus.OK, 단어 목록
     */
    @GetMapping("/{alphabet}")
    public ResponseEntity<List<WordDTO>> getWordList(@PathVariable("alphabet") String alphabet) {

        log.info("getWordList 호출 : " + alphabet);

        /**
         * 올바른 PATH인지 확인
         *
         * alphabet의 길이가 1이 아니거나,
         * 1글자일 때 a~z, A~Z 사이가 아닐 경우
         *
         * 400 BAD_REQUEST 반환
         */
        if(alphabet.length() != 1 ||
                !((alphabet.charAt(0) >= 'a' && alphabet.charAt(0) <= 'z') || (alphabet.charAt(0) >= 'A') && (alphabet.charAt(0) <= 'Z'))) {
            log.error("wordList 조회 실패 : Bad Request");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            List<WordDTO> wordList = wordService.getWordList(alphabet);
            return ResponseEntity.status(HttpStatus.OK).body(wordList);
        } catch (Exception e) {
            log.error("wordList 조회 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 단어 상세 (단어, 뉴스) 조회
     *
     * @param wordId 단어 아이디
     * @return 단어 데이터, 뉴스 데이터 리스트
     */
    @GetMapping("/detail/{wordId}")
    public ResponseEntity<WordDetailDTO> getWordDetail(@PathVariable("wordId") int wordId) {
        log.info("getWordDetail 호출 : {}", wordId);

        try {
            WordDetailDTO wordDetailDTO = wordService.getWordDetail(wordId);
            return ResponseEntity.status(HttpStatus.OK).body(wordDetailDTO);
        } catch (Exception e) {
            log.error("getWordDetail 조회 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    /**
     * 단어 상세 페이지에서 카드 조회
     * @param wordId 단어 아이디
     * @param scroll 몇 번째 스크롤인지
     * @return 카드 리스트
     */
    @GetMapping("/detail/{wordId}/{scroll}")
    public ResponseEntity<List<CardDTO>> getCardListByWord(@RequestHeader("token") String token
                            ,@PathVariable("wordId") int wordId, @PathVariable("scroll") int scroll) {
        log.info("getCardListByWord 호출 : {} {}", wordId, scroll);

        try {
            int loginUserIdx = userService.loadUserIdx(token);

            List<CardDTO> wordCardList = cardService.getCardListByWord(wordId, scroll, loginUserIdx);
            return ResponseEntity.status(HttpStatus.OK).body(wordCardList);
        } catch (Exception e) {
            log.error("getCardListByWord 조회 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 자동완성에서 선택된 단어 정보 조회
     *
     * @param wordId 선택된 단어
     * @return 단어 데이터
     */
    @GetMapping("/select/{wordId}")
    public ResponseEntity<WordDTO> getWordInCard(@PathVariable("wordId") int wordId) {
        log.info("getWordInCard 호출 : {}", wordId);

        try {
            WordDTO wordInCard = wordService.getWordInCard(wordId);
            return ResponseEntity.status(HttpStatus.OK).body(wordInCard);
        } catch (Exception e) {
            log.error("getWordInCard 조회 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
