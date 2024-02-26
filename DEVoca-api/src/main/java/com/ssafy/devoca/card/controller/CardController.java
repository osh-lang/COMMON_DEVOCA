package com.ssafy.devoca.card.controller;

import com.ssafy.devoca.card.model.CardDTO;
import com.ssafy.devoca.card.service.CardService;
import com.ssafy.devoca.notify.service.NotifyService;
import com.ssafy.devoca.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;
    private final UserService userService;
    private final NotifyService notifyService;

    @PostMapping("")
    public ResponseEntity<String> registerCard(@RequestBody CardDTO cardDTO){
        log.info("registerCard 호출 : 카드 등록 요청");
        try{
            cardService.registerCard(cardDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            log.error("카드 등록 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{cardId}")
    public ResponseEntity<String> updateCard(@RequestBody CardDTO cardDTO){
        log.info("updateCard 호출 : 카드 수정 요청");
        try{
            cardService.updateCard(cardDTO);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            log.error("카드 수정 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<String> deleteCard(@PathVariable("cardId") int cardId){
        log.info("deleteCard 호출 : 카드 삭제 요청");
        try {
            cardService.deleteCard(cardId);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            log.error("카드 삭제 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<CardDTO> getCardDetail(@RequestHeader("token") String token
                                                    ,@PathVariable("cardId") int cardId){
        log.info("getCardDetail 호출 : 카드 상세정보 요청");
        try {
            int loginUserIdx = userService.loadUserIdx(token);
            CardDTO cardDetail = cardService.getCardDetail(cardId, loginUserIdx);
            return ResponseEntity.status(HttpStatus.OK).body(cardDetail);
        }catch(Exception e){
            log.error("카드 상세 호출 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/list/{scroll}")
    public ResponseEntity<List<CardDTO>> getCardList(@RequestHeader("token") String token
                                                    ,@PathVariable("scroll") int scroll){
        log.info("getCardList 호출 : 카드 목록 요청");
        try{
            int loginUserIdx = userService.loadUserIdx(token);
            List<CardDTO> cardList = cardService.getCardList(scroll, loginUserIdx);
            return ResponseEntity.status(HttpStatus.OK).body(cardList);
        }catch (Exception e){
            log.error("카드 목록 호출 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/list/{userId}/{scroll}")
    public ResponseEntity<List<CardDTO>> getCardListByUserId(@RequestHeader("token") String token
                                                            ,@PathVariable("userId") String userId, @PathVariable("scroll") int scroll){
        log.info("getCardListByUserId 호출 : " + userId + "의 카드 목록 요청");
        try{
            int loginUserIdx = userService.loadUserIdx(token);
            List<CardDTO> cardList = cardService.getCardListByUserId(userId, scroll, loginUserIdx);
            return ResponseEntity.status(HttpStatus.OK).body(cardList);
        }catch (Exception e){
            log.error("카드 목록 호출 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/repost")
    public ResponseEntity<String> repostCard (@RequestBody CardDTO cardDTO){
        log.info("repostCard 호출 : 카드 재게시 요청");
        try{
            cardService.repostCard(cardDTO);

            int originUserIdx = cardService.getCardUserIdx(cardDTO.getOriginCardId());

            notifyService.send(originUserIdx, 3, cardDTO.getCardId());

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            log.error("카드 재게시 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/like/{cardId}")
    public ResponseEntity<String> likeCard (@RequestHeader("token") String token
                                            , @PathVariable("cardId") int cardId,
                                            @RequestBody Map<String, Boolean> requestBody){
        log.info("likeCard 호출 : 카드 좋아요 토글");
        try{
            boolean cardLikeYN = requestBody.get("cardLikeYN");
            int loginUserIdx = userService.loadUserIdx(token);
            log.info("cardLikeYN : "+cardLikeYN);
            cardService.likeCard(loginUserIdx, cardId, cardLikeYN);

            int originCardUserIdx = cardService.getCardUserIdx(cardId);

            notifyService.send(originCardUserIdx, 2, cardId);

            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            log.error("카드 좋아요 토글 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
